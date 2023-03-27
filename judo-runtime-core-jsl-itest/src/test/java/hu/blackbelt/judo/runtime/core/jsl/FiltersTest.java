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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.sdk.query.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class FiltersTest extends AbstractJslTest {
    @Inject
    MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    MyEntityWithOptionalFields entity1;

    MyEntityWithOptionalFields entity2;

    static final int INTEGER_1 = 1;
    static final int INTEGER_2 = 2;
    static final double SCALED_1 = 1.23;
    static final double SCALED_2 = 2.34;
    static final String STRING_1 = "test";
    static final String STRING_2 = "Another";
    static final String REGEX_1 = "+36 333-333-3333";
    static final String REGEX_2 = "+36 333-333-3334";
    static final boolean BOOL_1 = true;
    static final boolean BOOL_2 = false;
    static final LocalDate DATE_1 = LocalDate.of(2022, 7, 11);
    static final LocalDate DATE_2 = LocalDate.of(1999, 9, 19);
    static final LocalDateTime TIMESTAMP_1 = LocalDateTime.parse("2022-07-11T19:09:33");
    static final LocalDateTime TIMESTAMP_2 = LocalDateTime.parse("1999-09-19T09:09:09");
    static final LocalTime TIME_1 = LocalTime.parse("23:59:59");
    static final LocalTime TIME_2 = LocalTime.parse("12:34:56");
    static final MyEnum ENUM_1 = MyEnum.Bombastic;
    static final MyEnum ENUM_2 = MyEnum.Atomic;

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);

        entity1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(INTEGER_1)
                .withScaledAttr(SCALED_1)
                .withStringAttr(STRING_1)
                .withRegexAttr(REGEX_1)
                .withBoolAttr(BOOL_1)
                .withDateAttr(DATE_1)
                .withTimestampAttr(TIMESTAMP_1)
                .withTimeAttr(TIME_1)
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(ENUM_1)
                .build());

        entity2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(INTEGER_2)
                .withScaledAttr(SCALED_2)
                .withStringAttr(STRING_2)
                .withRegexAttr(REGEX_2)
                .withBoolAttr(BOOL_2)
                .withDateAttr(DATE_2)
                .withTimestampAttr(TIMESTAMP_2)
                .withTimeAttr(TIME_2)
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(ENUM_2)
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
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testFilterWithMultipleFilters() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        List<MyEntityWithOptionalFields> multiFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .filterByScaledAttr(NumberFilter.lessThan(INTEGER_2))
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute();

        assertEquals(1, multiFilter.size());

        assertEquals(entity1.get__identifier(), multiFilter.get(0).get__identifier());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testIntegerNumberFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), filteredByString.get__identifier());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equals.get__identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEquals.get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterOrEqualThan(INTEGER_1))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        MyEntityWithOptionalFields greater = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterThan(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), greater.get__identifier());

        List<MyEntityWithOptionalFields> lessOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessOrEqualThan(INTEGER_2))
                .execute();

        assertEquals(2, lessOrEqual.size());

        MyEntityWithOptionalFields less = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), less.get__identifier());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testScaledNumberFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1 and this.scaledAttr <= 2")
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), filteredByString.get__identifier());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.equalTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equals.get__identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEquals.get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterOrEqualThan(SCALED_1))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        MyEntityWithOptionalFields greater = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterThan(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), greater.get__identifier());

        List<MyEntityWithOptionalFields> lessOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessOrEqualThan(SCALED_2))
                .execute();

        assertEquals(2, lessOrEqual.size());

        MyEntityWithOptionalFields less = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessThan(SCALED_2))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), less.get__identifier());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testStringFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!matches('test')")
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), filteredByString.get__identifier());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equals.get__identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.notEqualTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEquals.get__identifier());

        List<MyEntityWithOptionalFields> iLike = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .execute();

        assertEquals(1, iLike.size());
        assertEquals(entity1.get__identifier(), iLike.get(0).get__identifier());

        List<MyEntityWithOptionalFields> like = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.like(STRING_1))
                .execute();

        assertEquals(1, like.size());
        assertEquals(entity1.get__identifier(), like.get(0).get__identifier());

        List<MyEntityWithOptionalFields> lessThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessThan(STRING_1))
                .execute();

        assertEquals(entity2.get__identifier(), lessThan.get(0).get__identifier());

        List<MyEntityWithOptionalFields> greaterThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterThan(STRING_2))
                .execute();

        assertEquals(entity1.get__identifier(), greaterThan.get(0).get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .execute();

        assertEquals(2, greaterOrEqualThan.size());

        List<MyEntityWithOptionalFields> lessOrEqualThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessOrEqualThan(STRING_1))
                .execute();

        assertEquals(2, lessOrEqualThan.size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testBooleanFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr == true")
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), filteredByString.get__identifier());

        MyEntityWithOptionalFields isTrue = myEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isTrue())
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), isTrue.get__identifier());

        MyEntityWithOptionalFields isFalse = myEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isFalse())
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), isFalse.get__identifier());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testDateFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.equalTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equalTo.get__identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.notEqualTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEqualTo.get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.greaterOrEqualThan(DATE_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<MyEntityWithOptionalFields> lessOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<MyEntityWithOptionalFields> greaterThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.greaterThan(DATE_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<MyEntityWithOptionalFields> lessThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessThan(DATE_1))
                .execute();

        assertEquals(1, lessThan.size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testTimestampFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equalTo.get__identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEqualTo.get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<MyEntityWithOptionalFields> lessOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<MyEntityWithOptionalFields> greaterThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<MyEntityWithOptionalFields> lessThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessThan(TIMESTAMP_1))
                .execute();

        assertEquals(1, lessThan.size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testTimeFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.equalTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equalTo.get__identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEqualTo.get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterOrEqualThan(TIME_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<MyEntityWithOptionalFields> lessOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.lessOrEqualThan(TIME_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<MyEntityWithOptionalFields> greaterThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterThan(TIME_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<MyEntityWithOptionalFields> lessThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.lessThan(TIME_1))
                .execute();

        assertEquals(1, lessThan.size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testEnumFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equalTo.get__identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEqualTo.get__identifier());
    }
}
