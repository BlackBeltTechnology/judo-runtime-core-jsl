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
import com.google.inject.Module;
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.car.Car;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.car.CarAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.car.CarDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.city.City;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.city.CityAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.city.CityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.continent.Continent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.country.Country;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.country.CountryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.filterentity.FilterEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.filterentity.FilterEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myentitywithoptionalfields.MyEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myentitywithoptionalfields.MyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.person.Person;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.person.PersonAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.person.PersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.tester.Tester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.tester.TesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FilterDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.sdk.query.*;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FiltersTest extends AbstractJslTest {
    @Inject
    MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    @Inject
    FilterEntityDao filterEntityDao;

    @Inject
    CountryDao countryDao;

    @Inject
    CityDao cityDao;

    @Inject
    PersonDao personDao;

    @Inject
    CarDao carDao;

    @Inject
    TesterDao testerDao;

    MyEntityWithOptionalFields entity1;

    MyEntityWithOptionalFields entity2;

    MyEntityWithOptionalFields entity3;

    FilterEntity filterEntity;

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

        entity3 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .build());

        filterEntity = filterEntityDao.create(FilterEntity.builder().build());
    }

    @Override
    public Module getModelDaoModule() {
        return new FilterDaoModules();
    }

    @Override
    public String getModelName() {
        return "Filter";
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
            "REQ-ENT-008"
    })
    public void testFilterWithMultipleFilters() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        List<MyEntityWithOptionalFields> multiFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .filterByScaledAttr(NumberFilter.lessThan(INTEGER_2))
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute();

        assertEquals(1, multiFilter.size());

        assertEquals(entity1.identifier(), multiFilter.get(0).identifier());
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
            "REQ-ENT-008"
    })
    public void testIntegerNumberFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByString.identifier());

        MyEntityWithOptionalFields equalsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr == 1")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsByStringFilter.identifier());

        MyEntityWithOptionalFields notEqualsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr != 1")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsByStringFilter.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilter.size());

        MyEntityWithOptionalFields greaterByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr > 1")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), greaterByStringFilter.identifier());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr <= 2")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        MyEntityWithOptionalFields lessByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), lessByStringFilter.identifier());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equals.identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEquals.identifier());

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

        assertEquals(entity2.identifier(), greater.identifier());

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

        assertEquals(entity1.identifier(), less.identifier());

        // Zero element

        List<MyEntityWithOptionalFields> zeroResult = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute();

        assertTrue(zeroResult.isEmpty());

        List<MyEntityWithOptionalFields> filteredByZeroResultString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr > 1 and this.integerAttr < 2")
                .execute();

        assertTrue(filteredByZeroResultString.isEmpty());

        // TODO JNG-3980
        // filter for undefined

//        MyEntityWithOptionalFields undefinedResult = myEntityWithOptionalFieldsDao
//                .query()
//                .filterBy("this.integerAttr!isDefined()")
//                .execute()
//                .get(0);
//
//        assertEquals(entity3.identifier(),
//                undefinedResult.identifier());

        // Derived attributes

        MyEntityWithOptionalFields filteredByStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByStringDerived.identifier());

        List<MyEntityWithOptionalFields> filteredByZeroResultStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr > 1 and this.derivedIntegerAttr < 2")
                .execute();

        assertTrue(filteredByZeroResultStringDerived.isEmpty());

        MyEntityWithOptionalFields equalsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr == 1")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsByStringFilterDerived.identifier());

        MyEntityWithOptionalFields notEqualsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr != 1")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr >= 1")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilterDerived.size());

        MyEntityWithOptionalFields greaterByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr > 1")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), greaterByStringFilterDerived.identifier());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr <= 2")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        MyEntityWithOptionalFields lessByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr < 2")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), lessByStringFilterDerived.identifier());

        MyEntityWithOptionalFields equalsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsDerived.identifier());

        MyEntityWithOptionalFields notEqualsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.greaterOrEqualThan(INTEGER_1))
                .execute();

        assertEquals(2, greaterOrEqualsDerived.size());

        MyEntityWithOptionalFields greaterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.greaterThan(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), greaterDerived.identifier());

        List<MyEntityWithOptionalFields> lessOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessOrEqualThan(INTEGER_2))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());


        MyEntityWithOptionalFields lessDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), lessDerived.identifier());

        List<MyEntityWithOptionalFields> zeroResultDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute();

        assertTrue(zeroResultDerived.isEmpty());

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
            "REQ-ENT-008"
    })
    public void testScaledNumberFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();
        assertEquals(3, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1 and this.scaledAttr <= 2")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByString.identifier());

        MyEntityWithOptionalFields equalsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr == 1.23")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsByStringFilter.identifier());

        MyEntityWithOptionalFields notEqualsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr != 1.23")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsByStringFilter.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1.23")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilter.size());

        MyEntityWithOptionalFields greaterByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr > 1.23")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), greaterByStringFilter.identifier());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr <= 2.34")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        MyEntityWithOptionalFields lessByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr < 2.34")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), lessByStringFilter.identifier());

        List<MyEntityWithOptionalFields> filteredByZeroResultString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr > 0 and this.scaledAttr < 1")
                .execute();

        assertTrue(filteredByZeroResultString.isEmpty());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.equalTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equals.identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEquals.identifier());

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

        assertEquals(entity2.identifier(), greater.identifier());

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

        assertEquals(entity1.identifier(), less.identifier());

        // Derived

        MyEntityWithOptionalFields filteredByStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr >= 1 and this.derivedScaledAttr <= 2")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByStringDerived.identifier());

        List<MyEntityWithOptionalFields> filteredByZeroResultStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr > 0 and this.derivedScaledAttr < 1")
                .execute();

        assertTrue(filteredByZeroResultStringDerived.isEmpty());

        MyEntityWithOptionalFields equalsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr == 1.23")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsByStringFilterDerived.identifier());

        MyEntityWithOptionalFields notEqualsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr != 1.23")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr >= 1.23")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilterDerived.size());

        MyEntityWithOptionalFields greaterByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr > 1.23")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), greaterByStringFilterDerived.identifier());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr <= 2.34")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        MyEntityWithOptionalFields lessByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr < 2.34")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), lessByStringFilterDerived.identifier());

        MyEntityWithOptionalFields equalsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.equalTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsDerived.identifier());

        MyEntityWithOptionalFields notEqualsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.greaterOrEqualThan(SCALED_1))
                .execute();

        assertEquals(2, greaterOrEqualsDerived.size());

        MyEntityWithOptionalFields greaterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.greaterThan(SCALED_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), greaterDerived.identifier());

        List<MyEntityWithOptionalFields> lessOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.lessOrEqualThan(SCALED_2))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        MyEntityWithOptionalFields lessDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.lessThan(SCALED_2))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), lessDerived.identifier());


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
            "REQ-ENT-008"
    })
    public void testStringFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        // custom filter

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!matches('te.*')")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByString.identifier());

        MyEntityWithOptionalFields equalsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr == 'test'")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsByStringFilter.identifier());

        MyEntityWithOptionalFields notEqualsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr != 'test'")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsByStringFilter.identifier());

        List<MyEntityWithOptionalFields> iLikeByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!ilike('TEST')")
                .execute();

        assertEquals(1, iLikeByStringFilter.size());
        assertEquals(entity1.identifier(), iLikeByStringFilter.get(0).identifier());

        List<MyEntityWithOptionalFields> likeByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!ilike('test')")
                .execute();

        assertEquals(1, likeByStringFilter.size());
        assertEquals(entity1.identifier(), likeByStringFilter.get(0).identifier());

        List<MyEntityWithOptionalFields> lessThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr < 'test'")
                .execute();

        assertEquals(entity2.identifier(), lessThanByStringFilter.get(0).identifier());

        List<MyEntityWithOptionalFields> greaterThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr > 'Another'")
                .execute();

        assertEquals(entity1.identifier(), greaterThanByStringFilter.get(0).identifier());

        Long greaterOrEqualThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilter);

        List<MyEntityWithOptionalFields> lessOrEqualThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr <= 'test'")
                .execute();

        assertEquals(2, lessOrEqualThanByStringFilter.size());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equals.identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.notEqualTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEquals.identifier());

        List<MyEntityWithOptionalFields> iLike = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .execute();

        assertEquals(1, iLike.size());
        assertEquals(entity1.identifier(), iLike.get(0).identifier());

        List<MyEntityWithOptionalFields> like = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.like(STRING_1))
                .execute();

        assertEquals(1, like.size());
        assertEquals(entity1.identifier(), like.get(0).identifier());

        List<MyEntityWithOptionalFields> lessThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessThan(STRING_1))
                .execute();

        assertEquals(entity2.identifier(), lessThan.get(0).identifier());

        List<MyEntityWithOptionalFields> greaterThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterThan(STRING_2))
                .execute();

        assertEquals(entity1.identifier(), greaterThan.get(0).identifier());

        Long greaterOrEqualThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThan);

        List<MyEntityWithOptionalFields> lessOrEqualThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessOrEqualThan(STRING_1))
                .execute();

        assertEquals(2, lessOrEqualThan.size());

        // Derived

        MyEntityWithOptionalFields filteredByStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!matches('te.*')")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByStringDerived.identifier());

        MyEntityWithOptionalFields equalsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr == 'test'")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsByStringFilterDerived.identifier());

        MyEntityWithOptionalFields notEqualsByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr != 'test'")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<MyEntityWithOptionalFields> iLikeByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!ilike('TEST')")
                .execute();

        assertEquals(1, iLikeByStringFilterDerived.size());
        assertEquals(entity1.identifier(), iLikeByStringFilterDerived.get(0).identifier());

        List<MyEntityWithOptionalFields> likeByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!ilike('test')")
                .execute();

        assertEquals(1, likeByStringFilterDerived.size());
        assertEquals(entity1.identifier(), likeByStringFilterDerived.get(0).identifier());

        List<MyEntityWithOptionalFields> lessThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr < 'test'")
                .execute();

        assertEquals(entity2.identifier(), lessThanByStringFilterDerived.get(0).identifier());

        List<MyEntityWithOptionalFields> greaterThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr > 'Another'")
                .execute();

        assertEquals(entity1.identifier(), greaterThanByStringFilterDerived.get(0).identifier());

        Long greaterOrEqualThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilterDerived);

        List<MyEntityWithOptionalFields> lessOrEqualThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr <= 'test'")
                .execute();

        assertEquals(2, lessOrEqualThanByStringFilterDerived.size());

        MyEntityWithOptionalFields equalsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.equalTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalsDerived.identifier());

        MyEntityWithOptionalFields notEqualsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.notEqualTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualsDerived.identifier());

        List<MyEntityWithOptionalFields> iLikeDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .execute();

        assertEquals(1, iLikeDerived.size());
        assertEquals(entity1.identifier(), iLikeDerived.get(0).identifier());

        List<MyEntityWithOptionalFields> likeDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.like(STRING_1))
                .execute();

        assertEquals(1, likeDerived.size());
        assertEquals(entity1.identifier(), likeDerived.get(0).identifier());

        List<MyEntityWithOptionalFields> lessThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.lessThan(STRING_1))
                .execute();

        assertEquals(entity2.identifier(), lessThanDerived.get(0).identifier());

        List<MyEntityWithOptionalFields> greaterThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.greaterThan(STRING_2))
                .execute();

        assertEquals(entity1.identifier(), greaterThanDerived.get(0).identifier());

        Long greaterOrEqualThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThanDerived);

        List<MyEntityWithOptionalFields> lessOrEqualThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.lessOrEqualThan(STRING_1))
                .execute();

        assertEquals(2, lessOrEqualThanDerived.size());

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
            "REQ-ENT-008"
    })
    public void testBooleanFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr == true")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByString.identifier());

        MyEntityWithOptionalFields filteredByStringNotEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr != true")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), filteredByStringNotEquals.identifier());

        MyEntityWithOptionalFields filteredByStringOnlyAttr = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByStringOnlyAttr.identifier());

        MyEntityWithOptionalFields isTrue = myEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isTrue())
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), isTrue.identifier());

        MyEntityWithOptionalFields isFalse = myEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isFalse())
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), isFalse.identifier());

        // derived

        MyEntityWithOptionalFields filteredByStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr == true")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByStringDerived.identifier());

        MyEntityWithOptionalFields filteredByStringNotEqualsDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr != true")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), filteredByStringNotEqualsDerived.identifier());

        MyEntityWithOptionalFields filteredByStringOnlyAttrDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), filteredByStringOnlyAttrDerived.identifier());

        MyEntityWithOptionalFields isTrueDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isTrue())
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), isTrueDerived.identifier());

        MyEntityWithOptionalFields isFalseDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isFalse())
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), isFalseDerived.identifier());
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
            "REQ-ENT-008"
    })
    public void testDateFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        MyEntityWithOptionalFields equalToByFilterString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr == `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToByFilterString.identifier());

        MyEntityWithOptionalFields notEqualToByFilterString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr != `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToByFilterString.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualByFilterString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr >= `1999-09-19`")
                .execute();

        assertEquals(2, greaterOrEqualByFilterString.size());

        List<MyEntityWithOptionalFields> lessOrEqualByFilterString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr <= `2022-07-11`")
                .execute();

        assertEquals(2, lessOrEqualByFilterString.size());

        List<MyEntityWithOptionalFields> greaterThanByFilterString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr > `1999-09-19`")
                .execute();

        assertEquals(1, greaterThanByFilterString.size());

        List<MyEntityWithOptionalFields> lessThanByFilterString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr < `2022-07-11`")
                .execute();

        assertEquals(1, lessThanByFilterString.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.equalTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalTo.identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.notEqualTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualTo.identifier());

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

        // derived

        MyEntityWithOptionalFields equalToByFilterStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr == `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToByFilterStringDerived.identifier());

        MyEntityWithOptionalFields notEqualToByFilterStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr != `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToByFilterStringDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualByFilterStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr >= `1999-09-19`")
                .execute();

        assertEquals(2, greaterOrEqualByFilterStringDerived.size());

        List<MyEntityWithOptionalFields> lessOrEqualByFilterStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr <= `2022-07-11`")
                .execute();

        assertEquals(2, lessOrEqualByFilterStringDerived.size());

        List<MyEntityWithOptionalFields> greaterThanByFilterStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr > `1999-09-19`")
                .execute();

        assertEquals(1, greaterThanByFilterStringDerived.size());

        List<MyEntityWithOptionalFields> lessThanByFilterStringDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr < `2022-07-11`")
                .execute();

        assertEquals(1, lessThanByFilterStringDerived.size());

        MyEntityWithOptionalFields equalToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.equalTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToDerived.identifier());

        MyEntityWithOptionalFields notEqualToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.notEqualTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.greaterOrEqualThan(DATE_2))
                .execute();

        assertEquals(2, greaterOrEqualDerived.size());

        List<MyEntityWithOptionalFields> lessOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        List<MyEntityWithOptionalFields> greaterThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.greaterThan(DATE_2))
                .execute();

        assertEquals(1, greaterThanDerived.size());

        List<MyEntityWithOptionalFields> lessThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.lessThan(DATE_1))
                .execute();

        assertEquals(1, lessThanDerived.size());


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
            "REQ-ENT-008"
    })
    public void testTimestampFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        MyEntityWithOptionalFields equalToByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr == `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToByStringFilter.identifier());

        MyEntityWithOptionalFields notEqualToByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr != `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToByStringFilter.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr >= `1999-09-19T09:09:09`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilter.size());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr <= `2022-07-11T19:09:33`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        List<MyEntityWithOptionalFields> greaterThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr > `1999-09-19T09:09:09`")
                .execute();

        assertEquals(1, greaterThanByStringFilter.size());

        List<MyEntityWithOptionalFields> lessThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr < `2022-07-11T19:09:33`")
                .execute();

        assertEquals(1, lessThanByStringFilter.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalTo.identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualTo.identifier());

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

        // derived

        MyEntityWithOptionalFields equalToByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr == `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToByStringFilterDerived.identifier());

        MyEntityWithOptionalFields notEqualToByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr != `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToByStringFilterDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr >= `1999-09-19T09:09:09`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilterDerived.size());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr <= `2022-07-11T19:09:33`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        List<MyEntityWithOptionalFields> greaterThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr > `1999-09-19T09:09:09`")
                .execute();

        assertEquals(1, greaterThanByStringFilterDerived.size());

        List<MyEntityWithOptionalFields> lessThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr < `2022-07-11T19:09:33`")
                .execute();

        assertEquals(1, lessThanByStringFilterDerived.size());

        MyEntityWithOptionalFields equalToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToDerived.identifier());

        MyEntityWithOptionalFields notEqualToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .execute();

        assertEquals(2, greaterOrEqualDerived.size());

        List<MyEntityWithOptionalFields> lessOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        List<MyEntityWithOptionalFields> greaterThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .execute();

        assertEquals(1, greaterThanDerived.size());

        List<MyEntityWithOptionalFields> lessThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.lessThan(TIMESTAMP_1))
                .execute();

        assertEquals(1, lessThanDerived.size());


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
            "REQ-ENT-008"
    })
    public void testTimeFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        MyEntityWithOptionalFields equalToByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr == `23:59:59`")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToByStringFilter.identifier());

        MyEntityWithOptionalFields notEqualToByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr != `23:59:59`")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToByStringFilter.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr >= `12:34:56`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilter.size());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr <= `23:59:59`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        List<MyEntityWithOptionalFields> greaterThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr > `12:34:56`")
                .execute();

        assertEquals(1, greaterThanByStringFilter.size());
        assertEquals(entity1.identifier(), greaterThanByStringFilter.get(0).identifier());

        List<MyEntityWithOptionalFields> lessThanByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr < `23:59:59`")
                .execute();

        assertEquals(1, lessThanByStringFilter.size());
        assertEquals(entity2.identifier(), lessThanByStringFilter.get(0).identifier());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.equalTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalTo.identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualTo.identifier());

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

        MyEntityWithOptionalFields equalToByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr == `23:59:59`")
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToByStringFilterDerived.identifier());

        MyEntityWithOptionalFields notEqualToByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr != `23:59:59`")
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToByStringFilterDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr >= `12:34:56`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilterDerived.size());

        List<MyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr <= `23:59:59`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        List<MyEntityWithOptionalFields> greaterThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr > `12:34:56`")
                .execute();

        assertEquals(1, greaterThanByStringFilterDerived.size());
        assertEquals(entity1.identifier(), greaterThanByStringFilterDerived.get(0).identifier());

        List<MyEntityWithOptionalFields> lessThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr < `23:59:59`")
                .execute();

        assertEquals(1, lessThanByStringFilterDerived.size());
        assertEquals(entity2.identifier(), lessThanByStringFilterDerived.get(0).identifier());

        MyEntityWithOptionalFields equalToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.equalTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToDerived.identifier());

        MyEntityWithOptionalFields notEqualToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToDerived.identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.greaterOrEqualThan(TIME_2))
                .execute();

        assertEquals(2, greaterOrEqualDerived.size());

        List<MyEntityWithOptionalFields> lessOrEqualDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.lessOrEqualThan(TIME_1))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        List<MyEntityWithOptionalFields> greaterThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.greaterThan(TIME_2))
                .execute();

        assertEquals(1, greaterThanDerived.size());

        List<MyEntityWithOptionalFields> lessThanDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.lessThan(TIME_1))
                .execute();

        assertEquals(1, lessThanDerived.size());
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
            "REQ-ENT-008"
    })
    public void testEnumFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalTo.identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualTo.identifier());

        // Derived

        MyEntityWithOptionalFields equalToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(entity1.identifier(), equalToDerived.identifier());

        MyEntityWithOptionalFields notEqualToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(entity2.identifier(), notEqualToDerived.identifier());

    }


    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008"
    })
    void testDerivedWithFilter() {

        assertEquals(3, myEntityWithOptionalFieldsDao.query().count());

        assertEquals(2, filterEntityDao.queryHaveTOnTheStringAttr(filterEntity).count());
        assertThat(filterEntityDao.queryHaveTOnTheStringAttr(filterEntity).execute().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(entity1.identifier(),entity2.identifier()));

        assertEquals(1, filterEntityDao.queryTestIsTheStringAttr(filterEntity).count());
        assertThat(filterEntityDao.queryTestIsTheStringAttr(filterEntity).execute().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(entity1.identifier()));

        assertEquals(0, filterEntityDao.queryHaveNoMatchOnTheStringAttr(filterEntity).count());

        assertEquals(1, filterEntityDao.queryHaveUndefinedOnTheStringAttr(filterEntity).count());
        assertThat(filterEntityDao.queryHaveUndefinedOnTheStringAttr(filterEntity).execute().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(entity3.identifier()));

        assertEquals(2, filterEntityDao.queryHaveDefinedOnTheStringAttr(filterEntity).count());
        assertThat(filterEntityDao.queryHaveDefinedOnTheStringAttr(filterEntity).execute().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(entity1.identifier(), entity2.identifier()));

    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-005",
            "REQ-ENT-008"
    })
    void testNavigationOnFilter() {

        Country country1 = countryDao.create(Country.builder().withName("Hungary").withContinent(Continent.Europe).build());
        City city1 = cityDao.create(City.builder().withName("Budapest").build(), CityAttachedRelationsForCreate.builder().withCountry(country1).build());
        City city2 = cityDao.create(City.builder().withName("Debrecen").build(), CityAttachedRelationsForCreate.builder().withCountry(country1).build());
        Person p1 = personDao.create(Person.builder().withName("Gibpsz Jakab").build(), PersonAttachedRelationsForCreate.builder().withCity(city1).build());
        Person p2 = personDao.create(Person.builder().withName("Teszt Elek").build(), PersonAttachedRelationsForCreate.builder().withCity(city2).build());
        Car c1 = carDao.create(Car.builder().withLicensePlate("ABC-123").build(), CarAttachedRelationsForCreate.builder().withOwner(p1).build());
        Car c2 = carDao.create(Car.builder().withLicensePlate("ABC-124").build(), CarAttachedRelationsForCreate.builder().withOwner(p2).build());

        Tester tester = testerDao.create(Tester.builder().build());

        assertEquals(1, testerDao.countCarsOfTesztElek(tester));
        assertThat(testerDao.queryCarsOfTesztElek(tester).execute(), containsInAnyOrder(c2));

        assertEquals(1, testerDao.countCarsInBudapest(tester));
        assertThat(testerDao.queryCarsInBudapest(tester).execute(), containsInAnyOrder(c1));

        assertEquals(2, testerDao.countCarsInHungary(tester));
        assertThat(testerDao.queryCarsInHungary(tester).execute(), Matchers.containsInAnyOrder(c2, c1));

        assertEquals(2, testerDao.countCarsOfKnownContinents(tester));
        assertThat(testerDao.queryCarsInHungary(tester).execute(), Matchers.containsInAnyOrder(c1, c2));

    }




}
