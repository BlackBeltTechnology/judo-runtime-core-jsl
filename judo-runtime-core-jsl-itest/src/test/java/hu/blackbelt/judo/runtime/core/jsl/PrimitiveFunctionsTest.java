package hu.blackbelt.judo.runtime.core.jsl;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFieldsAttribute;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFieldsMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PrimitivesDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class PrimitiveFunctionsTest extends AbstractJslTest {
    @Inject
    MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    MyEntityWithOptionalFields entity1;

    MyEntityWithOptionalFields entity2;

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);

        entity1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(2)
                .withScaledAttr(2.34)
                .withStringAttr("test")
                .withRegexAttr("+36 333-333-3333")
                .withBoolAttr(true)
                .withDateAttr(LocalDate.of(2022, 7, 11))
                .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                .withTimeAttr(LocalTime.parse("23:59:59"))
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(MyEnum.Bombastic)
                .build());

        entity2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(1)
                .withScaledAttr(1.23)
                .withStringAttr("Another")
                .withRegexAttr("+36 333-333-3331")
                .withBoolAttr(false)
                .withDateAttr(LocalDate.of(1999, 9, 19))
                .withTimestampAttr(LocalDateTime.parse("1999-09-19T09:09:09"))
                .withTimeAttr(LocalTime.parse("12:34:56"))
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(MyEnum.Atomic)
                .build());
    }

    @Override
    public Module getModelDaoModule() {
        return new PrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "Primitives";
    }

    @Test
    public void testLimit() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao
                .query()
                .limit(1)
                .execute();

        assertEquals(1, list.size());
    }

    @Test
    public void testOrderBy() {
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.INTEGER_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.SCALED_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.STRING_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.REGEX_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.BOOL_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DATE_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.TIMESTAMP_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.TIME_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.ENUM_ATTR, entity2);
    }

    @Test
    public void testOrderByDescending() {
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.INTEGER_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.SCALED_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.STRING_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.REGEX_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.BOOL_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DATE_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.TIMESTAMP_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.TIME_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.ENUM_ATTR, entity1);
    }

    @Test
    public void testMask() {
        MyEntityWithOptionalFields maskedResult = myEntityWithOptionalFieldsDao
                .query()
                .maskedBy(MyEntityWithOptionalFieldsMask.myEntityWithOptionalFieldsMask()
                        .withStringAttr()
                        .withIntegerAttr())
                .filterByStringAttr(StringFilter.equalTo("test"))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), maskedResult.identifier());
        assertEquals(entity1.getIntegerAttr(), maskedResult.getIntegerAttr());
        assertEquals(null, maskedResult.getScaledAttr());
        assertEquals(entity1.getStringAttr(), maskedResult.getStringAttr());
        assertEquals(null, maskedResult.getRegexAttr());
    }

    private void assertOrderBy(MyEntityWithOptionalFieldsAttribute attribute, MyEntityWithOptionalFields firstEntity) {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderBy(attribute)
                .execute()
                .get(0);

        assertEquals(firstEntity.identifier(), orderBy.identifier());
    }

    private void assertOrderByDescending(MyEntityWithOptionalFieldsAttribute attribute, MyEntityWithOptionalFields firstEntity) {
        MyEntityWithOptionalFields orderByDescending = myEntityWithOptionalFieldsDao
                .query()
                .orderByDescending(attribute)
                .execute()
                .get(0);

        assertEquals(firstEntity.identifier(), orderByDescending.identifier());
    }
}
