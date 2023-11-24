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
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferQueryCustomizerFunctionsTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("MappedTransferPrimitives", new MappedTransferPrimitivesDaoModules());

    @Inject
    TransferOptionalPrimitivesDao transferOptionalPrimitivesDao;

    TransferOptionalPrimitives transf1;

    TransferOptionalPrimitives transf2;

    @BeforeEach
    protected void init() {

        transf1 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitivesForCreate.builder()
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

        transf2 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitivesForCreate.builder()
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

    /**
     * Testing the transfer Dao query orderBy method.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     *
     * @scenario
     *
     * Create two instance of TransferOptionalPrimitives like transf1,transf2
     *
     * Use the Dao query OrderbBy method for each primitive type.
     *
     * Check all list first element is the transf2 intsance
     *
     */
    @Test
    @TestCase("TransferDaoQueryOrderByFunction")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testOrderBy() {
        assertOrderBy(TransferOptionalPrimitivesAttribute.INTEGER_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.SCALED_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.STRING_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.REGEX_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.BOOL_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DATE_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.TIMESTAMP_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.TIME_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.ENUM_ATTR, transf2);
    }

    /**
     * Testing the transfer Dao query orderByDescending method.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     *
     * @scenario
     *
     * Create two instance of TransferOptionalPrimitives like transf1,transf2
     *
     * Use the Dao query OrderByDescending method for each primitive type.
     *
     * Check all list first element is the transf1 intsance
     *
     */
    @Test
    @TestCase("TransferDaoQueryOrderByDescendingFunction")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testOrderByDescending() {
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.INTEGER_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.SCALED_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.STRING_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.REGEX_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.BOOL_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DATE_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.TIMESTAMP_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.TIME_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.ENUM_ATTR, transf1);
    }
    
    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-SRV-002",
    })
    public void testDerivedOrderBy() {
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_INTEGER_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_STRING_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_REGEX_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_DATE_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIME_ATTR, transf2);
        assertOrderBy(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR, transf2);
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-SRV-002",
    })
    public void testDerivedOrderByDescending() {
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_INTEGER_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_STRING_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_REGEX_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_DATE_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_TIME_ATTR, transf1);
        assertOrderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR, transf1);
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SRV-002",
    })
    public void testMultiPrimitiveOrderBy() {
        TransferOptionalPrimitives orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.INTEGER_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.SCALED_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.REGEX_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.BOOL_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DATE_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.TIMESTAMP_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.TIME_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(transf2.identifier(), orderBy.identifier());

        TransferOptionalPrimitivesOrder orderASC = TransferOptionalPrimitivesOrder.ASC;

        orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.INTEGER_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.SCALED_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.REGEX_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.BOOL_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DATE_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.TIMESTAMP_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.TIME_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.ENUM_ATTR, orderASC)
                .selectOne()
                .get();

        assertEquals(transf2.identifier(), orderBy.identifier());
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SRV-002",
    })
    public void testMultiPrimitiveOrderByDescending() {
        TransferOptionalPrimitives orderBy = transferOptionalPrimitivesDao
                .query()
                .orderByDescending(TransferOptionalPrimitivesAttribute.INTEGER_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.SCALED_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.REGEX_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.BOOL_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DATE_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.TIMESTAMP_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.TIME_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(transf1.identifier(), orderBy.identifier());

        TransferOptionalPrimitivesOrder orderDESC = TransferOptionalPrimitivesOrder.DESC;

        orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.INTEGER_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.SCALED_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.REGEX_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.BOOL_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DATE_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.TIMESTAMP_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.TIME_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.ENUM_ATTR, orderDESC)
                .selectOne()
                .get();

        assertEquals(transf1.identifier(), orderBy.identifier());

    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-SRV-002",
    })
    public void testMultiDerivedOrderBy() {
        TransferOptionalPrimitives orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_INTEGER_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_REGEX_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_DATE_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIME_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(transf2.identifier(), orderBy.identifier());

        TransferOptionalPrimitivesOrder orderASC = TransferOptionalPrimitivesOrder.ASC;

        orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_INTEGER_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_REGEX_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_DATE_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIME_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR, orderASC)
                .selectOne()
                .get();

        assertEquals(transf2.identifier(), orderBy.identifier());


    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-SRV-002",
    })
    public void testMultiDerivedOrderByDescending() {
        TransferOptionalPrimitives orderBy = transferOptionalPrimitivesDao
                .query()
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_INTEGER_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_REGEX_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_DATE_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_TIME_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(transf1.identifier(), orderBy.identifier());

        TransferOptionalPrimitivesOrder orderDESC = TransferOptionalPrimitivesOrder.DESC;

        orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_INTEGER_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_REGEX_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_DATE_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIME_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR, orderDESC)
                .selectOne()
                .get();

        assertEquals(transf1.identifier(), orderBy.identifier());
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-SRV-002",
    })
    public void testMultiDerivedAndPrimitiveOrderBy() {
        TransferOptionalPrimitives orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.INTEGER_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.REGEX_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DATE_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.TIME_ATTR)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(transf2.identifier(), orderBy.identifier());

        TransferOptionalPrimitivesOrder orderASC = TransferOptionalPrimitivesOrder.ASC;

        orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.INTEGER_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.REGEX_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DATE_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.TIME_ATTR, orderASC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR, orderASC)
                .selectOne()
                .get();

        assertEquals(transf2.identifier(), orderBy.identifier());
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-SRV-002",
    })
    public void testMultiDerivedAndPrimitiveOrderByDescending() {
        TransferOptionalPrimitives orderBy = transferOptionalPrimitivesDao
                .query()
                .orderByDescending(TransferOptionalPrimitivesAttribute.INTEGER_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.REGEX_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DATE_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.TIME_ATTR)
                .orderByDescending(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(transf1.identifier(), orderBy.identifier());

        TransferOptionalPrimitivesOrder orderDESC = TransferOptionalPrimitivesOrder.DESC;

        orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(TransferOptionalPrimitivesAttribute.INTEGER_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_SCALED_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.REGEX_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_BOOL_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DATE_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_TIMESTAMP_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.TIME_ATTR, orderDESC)
                .orderBy(TransferOptionalPrimitivesAttribute.DERIVED_ENUM_ATTR, orderDESC)
                .selectOne()
                .get();

        assertEquals(transf1.identifier(), orderBy.identifier());

    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-SRV-002"
    })
    public void testOrderEnumMethods() {

        TransferOptionalPrimitivesOrder order = TransferOptionalPrimitivesOrder.ASC;

        assertTrue(order.isASC());
        assertFalse(order.isDESC());
        assertEquals("ASC", order.getByName());

        order = TransferOptionalPrimitivesOrder.DESC;

        assertFalse(order.isASC());
        assertTrue(order.isDESC());
        assertEquals("DESC", order.getByName());

    }

    /**
     * Testing the transfer Dao query maskedBy method.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @scenario
     *
     *  Create one instance of TransferOptionalPrimitives like transf1.
     *
     *  Use the Dao query maskedBy method and filter one of the attribute to the transf1 instance.
     *
     *  Check if just the masked attributes are available, the other ones should have null value.
     *
     */
    @Test
    @TestCase("TransferDaoQueryMaskedByFunction")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testMask() {
        TransferOptionalPrimitives maskedResult = transferOptionalPrimitivesDao
                .query()
                .maskedBy(TransferOptionalPrimitivesMask.transferOptionalPrimitivesMask()
                        .withStringAttr()
                        .withIntegerAttr())
                .filterByStringAttr(StringFilter.equalTo("test"))
                .selectOne()
                .get();

        assertEquals(transf1.identifier(), maskedResult.identifier());
        assertEquals(transf1.getIntegerAttr(), maskedResult.getIntegerAttr());
        assertEquals(null, maskedResult.getScaledAttr());
        assertEquals(transf1.getStringAttr(), maskedResult.getStringAttr());
        assertEquals(null, maskedResult.getRegexAttr());
    }

    private void assertOrderBy(TransferOptionalPrimitivesAttribute attribute, TransferOptionalPrimitives firstTransfer) {
        TransferOptionalPrimitives orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(attribute)
                .selectOne()
                .get();

        assertEquals(firstTransfer.identifier(), orderBy.identifier());

        orderBy = transferOptionalPrimitivesDao
                .query()
                .orderBy(attribute, TransferOptionalPrimitivesOrder.ASC)
                .selectOne()
                .get();

        assertEquals(firstTransfer.identifier(), orderBy.identifier());
    }

    private void assertOrderByDescending(TransferOptionalPrimitivesAttribute attribute, TransferOptionalPrimitives firstEntity) {
        TransferOptionalPrimitives orderByDescending = transferOptionalPrimitivesDao
                .query()
                .orderByDescending(attribute)
                .selectOne()
                .get();


        assertEquals(firstEntity.identifier(), orderByDescending.identifier());

        orderByDescending = transferOptionalPrimitivesDao
                .query()
                .orderBy(attribute, TransferOptionalPrimitivesOrder.DESC)
                .selectOne()
                .get();

        assertEquals(firstEntity.identifier(), orderByDescending.identifier());
    }
}
