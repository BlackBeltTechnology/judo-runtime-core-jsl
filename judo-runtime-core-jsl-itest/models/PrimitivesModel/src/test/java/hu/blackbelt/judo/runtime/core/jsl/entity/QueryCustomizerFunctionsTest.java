package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class QueryCustomizerFunctionsTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Primitives", new PrimitivesDaoModules());

    @Inject
    MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    MyEntityWithOptionalFields entity1;

    MyEntityWithOptionalFields entity2;

    @BeforeEach
    protected void init() {

        entity1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
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

        entity2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
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
            "REQ-ENT-012",
    })
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
            "REQ-ENT-012",
    })
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
            "REQ-ENT-012",
    })
    public void testDerivedOrderBy() {
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_INTEGER_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_SCALED_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_STRING_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_REGEX_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_BOOL_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_DATE_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_TIMESTAMP_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_TIME_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_ENUM_ATTR, entity2);
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
            "REQ-ENT-012",
    })
    public void testDerivedOrderByDescending() {
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_INTEGER_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_SCALED_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_STRING_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_REGEX_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_BOOL_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_DATE_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_TIMESTAMP_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_TIME_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_ENUM_ATTR, entity1);
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
            "REQ-ENT-012",
    })
    public void testMultiPrimitiveOrderBy() {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderBy(MyEntityWithOptionalFieldsAttribute.INTEGER_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.SCALED_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.REGEX_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.BOOL_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DATE_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.TIMESTAMP_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.TIME_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(entity2.identifier(), orderBy.identifier());
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
            "REQ-ENT-012",
    })
    public void testMultiPrimitiveOrderByDescending() {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.INTEGER_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.SCALED_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.REGEX_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.BOOL_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DATE_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.TIMESTAMP_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.TIME_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(entity1.identifier(), orderBy.identifier());
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
            "REQ-ENT-012",
    })
    public void testMultiDerivedOrderBy() {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_INTEGER_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_SCALED_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_REGEX_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_BOOL_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_DATE_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_TIME_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(entity2.identifier(), orderBy.identifier());
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
            "REQ-ENT-012",
    })
    public void testMultiDerivedOrderByDescending() {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_INTEGER_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_SCALED_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_REGEX_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_BOOL_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_DATE_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_TIME_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(entity1.identifier(), orderBy.identifier());
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
            "REQ-ENT-012",
    })
    public void testMultiDerivedAndPrimitiveOrderBy() {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderBy(MyEntityWithOptionalFieldsAttribute.INTEGER_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_SCALED_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.REGEX_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_BOOL_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DATE_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.TIME_ATTR)
                .orderBy(MyEntityWithOptionalFieldsAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(entity2.identifier(), orderBy.identifier());
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
            "REQ-ENT-012",
    })
    public void testMultiDerivedAndPrimitiveOrderByDescending() {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.INTEGER_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_SCALED_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.REGEX_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_BOOL_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DATE_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_TIMESTAMP_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.TIME_ATTR)
                .orderByDescending(MyEntityWithOptionalFieldsAttribute.DERIVED_ENUM_ATTR)
                .selectOne()
                .get();

        assertEquals(entity1.identifier(), orderBy.identifier());
    }

    @Test
    public void testMask() {
        MyEntityWithOptionalFields maskedResult = myEntityWithOptionalFieldsDao
                .query()
                .maskedBy(MyEntityWithOptionalFieldsMask.myEntityWithOptionalFieldsMask()
                        .withStringAttr()
                        .withIntegerAttr())
                .filterByStringAttr(StringFilter.equalTo("test"))
                .selectOne()
                .get();

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
                .selectOne()
                .get();

        assertEquals(firstEntity.identifier(), orderBy.identifier());
    }

    private void assertOrderByDescending(MyEntityWithOptionalFieldsAttribute attribute, MyEntityWithOptionalFields firstEntity) {
        MyEntityWithOptionalFields orderByDescending = myEntityWithOptionalFieldsDao
                .query()
                .orderByDescending(attribute)
                .selectOne()
                .get();

        assertEquals(firstEntity.identifier(), orderByDescending.identifier());
    }
}
