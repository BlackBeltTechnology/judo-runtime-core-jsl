package hu.blackbelt.judo.runtime.core.jsl.transfer;

/*-
 * #%L
 * JUDO Runtime Core JSL :: Parent
 * %%
 * Copyright (C) 2018 - 2022 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TransferPrimitiveDaoFunctionsTest extends AbstractJslTest {
    @Inject
    TransferPrimitivesDao transferPrimitivesDao;

    TransferPrimitives transf1;

    TransferPrimitives transf2;

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);

        transf1 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withIntegerAttr(2)
                .withScaledAttr(2.34)
                .withStringAttr("test")
                .withRegexAttr("+36 333-333-3333")
                .withBoolAttr(true)
                .withDateAttr(LocalDate.of(2022, 7, 11))
                .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                .withTimeAttr(LocalTime.parse("23:59:59"))
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(Enum.EnumC)
                .build());

        transf2 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withIntegerAttr(1)
                .withScaledAttr(1.23)
                .withStringAttr("Another")
                .withRegexAttr("+36 333-333-3331")
                .withBoolAttr(false)
                .withDateAttr(LocalDate.of(1999, 9, 19))
                .withTimestampAttr(LocalDateTime.parse("1999-09-19T09:09:09"))
                .withTimeAttr(LocalTime.parse("12:34:56"))
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(Enum.EnumA)
                .build());
    }

    @Override
    public Module getModelDaoModule() {
        return new MappedTransferPrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferPrimitives";
    }

    @Test
    public void testLimit() {
        List<TransferPrimitives> list = transferPrimitivesDao
                .query()
                .limit(1)
                .execute();

        assertEquals(1, list.size());
    }

    @Test
    public void testOrderBy() {
        assertOrderBy(TransferPrimitivesAttribute.INTEGER_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.SCALED_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.STRING_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.REGEX_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.BOOL_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.DATE_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.TIMESTAMP_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.TIME_ATTR, transf2);
        assertOrderBy(TransferPrimitivesAttribute.ENUM_ATTR, transf2);
    }

    @Test
    public void testOrderByDescending() {
        assertOrderByDescending(TransferPrimitivesAttribute.INTEGER_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.SCALED_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.STRING_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.REGEX_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.BOOL_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.DATE_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.TIMESTAMP_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.TIME_ATTR, transf1);
        assertOrderByDescending(TransferPrimitivesAttribute.ENUM_ATTR, transf1);
    }

    @Test
    public void testMask() {
        TransferPrimitives maskedResult = transferPrimitivesDao
                .query()
                .maskedBy(TransferPrimitivesMask.transferPrimitivesMask()
                        .withStringAttr()
                        .withIntegerAttr())
                .filterByStringAttr(StringFilter.equalTo("test"))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), maskedResult.identifier());
        assertEquals(transf1.getIntegerAttr(), maskedResult.getIntegerAttr());
        assertEquals(null, maskedResult.getScaledAttr());
        assertEquals(transf1.getStringAttr(), maskedResult.getStringAttr());
        assertEquals(null, maskedResult.getRegexAttr());
    }

    private void assertOrderBy(TransferPrimitivesAttribute attribute, TransferPrimitives firstTransfer) {
        TransferPrimitives orderBy = transferPrimitivesDao
                .query()
                .orderBy(attribute)
                .execute()
                .get(0);

        assertEquals(firstTransfer.identifier(), orderBy.identifier());
    }

    private void assertOrderByDescending(TransferPrimitivesAttribute attribute, TransferPrimitives firstEntity) {
        TransferPrimitives orderByDescending = transferPrimitivesDao
                .query()
                .orderByDescending(attribute)
                .execute()
                .get(0);

        assertEquals(firstEntity.identifier(), orderByDescending.identifier());
    }
}
