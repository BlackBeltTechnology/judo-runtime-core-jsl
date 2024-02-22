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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.bucket.Bucket;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.bucket.BucketDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.bucket.BucketForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.buckettester.BucketTester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.buckettester.BucketTesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.buckettester.BucketTesterForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.car.Car;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.car.CarDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.car.CarForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.city.City;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.city.CityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.city.CityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.continent.Continent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.country.Country;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.country.CountryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.country.CountryForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.filterentity.FilterEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.filterentity.FilterEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.filterentity.FilterEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.item.ItemForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myentitywithoptionalfields.MyEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myentitywithoptionalfields.MyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myentitywithoptionalfields.MyEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.person.Person;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.person.PersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.person.PersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.product.Product;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.product.ProductDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.product.ProductForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.tester.Tester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.tester.TesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.tester.TesterForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FilterDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.*;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FiltersTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Filter", new FilterDaoModules());

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
    protected void init() {

        entity1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
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

        entity2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
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

        entity3 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .build());

        filterEntity = filterEntityDao.create(FilterEntityForCreate.builder().build());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        List<MyEntityWithOptionalFields> multiFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .filterByScaledAttr(NumberFilter.lessThan(INTEGER_2))
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .selectList();

        assertEquals(1, multiFilter.size());

        assertEquals(entity1.identifier(), multiFilter.get(0).identifier());

        multiFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.equalTo(INTEGER_1))
                .filterByName("scaledAttr", NumberFilter.lessThan(INTEGER_2))
                .filterByName("stringAttr", StringFilter.equalTo(STRING_1))
                .selectList();

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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(entity1, "this.integerAttr >= 1 and this.integerAttr < 2");
        assertEntityMatchesFilter(entity1, "this.integerAttr == 1");
        assertEntityMatchesFilter(entity2, "this.integerAttr != 1");
        assertFilterMatchesNumberOfResults(2, "this.integerAttr >= 1");
        assertEntityMatchesFilter(entity2, "this.integerAttr > 1");
        assertFilterMatchesNumberOfResults(2, "this.integerAttr <= 2");
        assertEntityMatchesFilter(entity1, "this.integerAttr < 2");

        assertEntityMatchesFilter(entity1, NumberFilter.equalTo(INTEGER_1));
        assertEntityMatchesFilter(entity2, NumberFilter.notEqualTo(INTEGER_1));
        assertFilterMatchesNumberOfResults(2, NumberFilter.greaterOrEqualThan(INTEGER_1));
        assertEntityMatchesFilter(entity2, NumberFilter.greaterThan(INTEGER_1));
        assertFilterMatchesNumberOfResults(2, NumberFilter.lessOrEqualThan(INTEGER_2));
        assertEntityMatchesFilter(entity1, NumberFilter.lessThan(INTEGER_2));

        // Zero element
        List<MyEntityWithOptionalFields> zeroResult = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResult.isEmpty());

        // filterByName

        assertFilterByNameMatchesEntity(entity1, "integerAttr", NumberFilter.equalTo(INTEGER_1));
        assertFilterByNameMatchesEntity(entity2, "integerAttr", NumberFilter.notEqualTo(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "integerAttr", NumberFilter.greaterOrEqualThan(INTEGER_1));
        assertFilterByNameMatchesEntity(entity2, "integerAttr", NumberFilter.greaterThan(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "integerAttr", NumberFilter.lessOrEqualThan(INTEGER_2));
        assertFilterByNameMatchesEntity(entity1, "integerAttr", NumberFilter.lessThan(INTEGER_2));

        // Zero element

        zeroResult = myEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.lessThan(INTEGER_1))
                .filterByName("integerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResult.isEmpty());

        assertFilterMatchesNumberOfResults(0, "this.integerAttr > 1 and this.integerAttr < 2");

        // TODO JNG-3980
        // filter for undefined

//        MyEntityWithOptionalFields undefinedResult = myEntityWithOptionalFieldsDao
//                .query()
//                .filterBy("this.integerAttr!isDefined()")
//                .selectOne()
//                .get();
//
//        assertEquals(entity3.identifier(),
//                undefinedResult.identifier());

        // Derived attributes

        assertEntityMatchesFilter(entity1, "this.integerAttr >= 1 and this.integerAttr < 2");
        assertFilterMatchesNumberOfResults(0, "this.derivedIntegerAttr > 1 and this.derivedIntegerAttr < 2");
        assertEntityMatchesFilter(entity1, "this.derivedIntegerAttr == 1");
        assertEntityMatchesFilter(entity2, "this.derivedIntegerAttr != 1");
        assertFilterMatchesNumberOfResults(2, "this.derivedIntegerAttr >= 1");
        assertEntityMatchesFilter(entity2, "this.derivedIntegerAttr > 1");
        assertFilterMatchesNumberOfResults(2, "this.derivedIntegerAttr <= 2");
        assertEntityMatchesFilter(entity1, "this.derivedIntegerAttr < 2");

        List<MyEntityWithOptionalFields> zeroResultDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResultDerived.isEmpty());

        // filterByName

        assertFilterByNameMatchesEntity(entity1, "derivedIntegerAttr", NumberFilter.equalTo(INTEGER_1));
        assertFilterByNameMatchesEntity(entity2, "derivedIntegerAttr", NumberFilter.notEqualTo(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedIntegerAttr", NumberFilter.greaterOrEqualThan(INTEGER_1));
        assertFilterByNameMatchesEntity(entity2, "derivedIntegerAttr", NumberFilter.greaterThan(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedIntegerAttr", NumberFilter.lessOrEqualThan(INTEGER_2));
        assertFilterByNameMatchesEntity(entity1, "derivedIntegerAttr", NumberFilter.lessThan(INTEGER_2));

        zeroResultDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.lessThan(INTEGER_1))
                .filterByName("derivedIntegerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResultDerived.isEmpty());
    }

    private void assertFilterMatchesNumberOfResults(int expected, NumberFilter filter) {
        List<MyEntityWithOptionalFields> filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<MyEntityWithOptionalFields> filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());
    }

    private void assertEntityMatchesFilter(MyEntityWithOptionalFields entity, NumberFilter filter) {
        MyEntityWithOptionalFields filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        MyEntityWithOptionalFields filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();
        assertEquals(3, list.size());

        assertEntityMatchesFilter(entity1, "this.scaledAttr >= 1 and this.scaledAttr <= 2");
        assertEntityMatchesFilter(entity1, "this.scaledAttr == 1.23");
        assertEntityMatchesFilter(entity2, "this.scaledAttr != 1.23");
        assertFilterMatchesNumberOfResults(2, "this.scaledAttr >= 1.23");
        assertEntityMatchesFilter(entity2, "this.scaledAttr > 1.23");
        assertFilterMatchesNumberOfResults(2, "this.scaledAttr <= 2.34");
        assertEntityMatchesFilter(entity1, "this.scaledAttr < 2.34");
        assertFilterMatchesNumberOfResults(0, "this.scaledAttr > 0 and this.scaledAttr < 1");

        assertEntityMatchesFilterForScaledAttr(entity1, NumberFilter.equalTo(SCALED_1));
        assertEntityMatchesFilterForScaledAttr(entity2, NumberFilter.notEqualTo(SCALED_1));
        assertFilterMatchesNumberOfResultsForScaledAttr(2, NumberFilter.greaterOrEqualThan(SCALED_1));
        assertEntityMatchesFilterForScaledAttr(entity2, NumberFilter.greaterThan(SCALED_1));
        assertFilterMatchesNumberOfResultsForScaledAttr(2, NumberFilter.lessOrEqualThan(SCALED_2));
        assertEntityMatchesFilterForScaledAttr(entity1, NumberFilter.lessThan(SCALED_2));

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "scaledAttr", NumberFilter.equalTo(SCALED_1));
        assertFilterByNameMatchesEntity(entity2, "scaledAttr", NumberFilter.notEqualTo(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "scaledAttr", NumberFilter.greaterOrEqualThan(SCALED_1));
        assertFilterByNameMatchesEntity(entity2, "scaledAttr", NumberFilter.greaterThan(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "scaledAttr", NumberFilter.lessOrEqualThan(SCALED_2));
        assertFilterByNameMatchesEntity(entity1, "scaledAttr", NumberFilter.lessThan(SCALED_2));

        // Derived

        assertEntityMatchesFilter(entity1, "this.derivedScaledAttr >= 1 and this.derivedScaledAttr <= 2");
        assertFilterMatchesNumberOfResults(0, "this.derivedScaledAttr > 0 and this.derivedScaledAttr < 1");
        assertEntityMatchesFilter(entity1, "this.derivedScaledAttr == 1.23");
        assertEntityMatchesFilter(entity2, "this.derivedScaledAttr != 1.23");
        assertFilterMatchesNumberOfResults(2, "this.derivedScaledAttr >= 1.23");
        assertEntityMatchesFilter(entity2, "this.derivedScaledAttr > 1.23");
        assertFilterMatchesNumberOfResults(2, "this.derivedScaledAttr <= 2.34");
        assertEntityMatchesFilter(entity1, "this.derivedScaledAttr < 2.34");

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "derivedScaledAttr", NumberFilter.equalTo(SCALED_1));
        assertFilterByNameMatchesEntity(entity2, "derivedScaledAttr", NumberFilter.notEqualTo(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedScaledAttr", NumberFilter.greaterOrEqualThan(SCALED_1));
        assertFilterByNameMatchesEntity(entity2, "derivedScaledAttr", NumberFilter.greaterThan(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedScaledAttr", NumberFilter.lessOrEqualThan(SCALED_2));
        assertFilterByNameMatchesEntity(entity1, "derivedScaledAttr", NumberFilter.lessThan(SCALED_2));

    }

    private void assertEntityMatchesFilterForScaledAttr(MyEntityWithOptionalFields entity, NumberFilter filter) {
        MyEntityWithOptionalFields filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        MyEntityWithOptionalFields filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResultsForScaledAttr(int expected, NumberFilter filter) {
        List<MyEntityWithOptionalFields> filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<MyEntityWithOptionalFields> filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(filter)
                .selectList();

        assertEquals(expected, filteredDerived.size());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        // custom filter

        assertEntityMatchesFilter(entity1, "this.stringAttr!matches('te.*')");
        assertEntityMatchesFilter(entity1, "this.stringAttr == 'test'");
        assertEntityMatchesFilter(entity2, "this.stringAttr != 'test'");
        assertEntityMatchesFilter(entity1, "this.stringAttr!ilike('TEST')");
        assertEntityMatchesFilter(entity1, "this.stringAttr!ilike('test')");
        assertEntityMatchesFilter(entity2, "this.stringAttr < 'test'");
        assertEntityMatchesFilter(entity1, "this.stringAttr > 'Another'");
        assertFilterMatchesNumberOfResults(2, "this.stringAttr >= 'Another'");
        assertFilterMatchesNumberOfResults(2, "this.stringAttr <= 'test'");

        assertEntityMatchesFilter(entity1, StringFilter.equalTo(STRING_1));
        assertEntityMatchesFilter(entity2, StringFilter.notEqualTo(STRING_1));
        assertEntityMatchesFilter(entity1, StringFilter.ilike(STRING_1.toUpperCase()));
        assertEntityMatchesFilter(entity1, StringFilter.like(STRING_1));
        assertEntityMatchesFilter(entity2, StringFilter.lessThan(STRING_1));
        assertEntityMatchesFilter(entity1, StringFilter.greaterThan(STRING_2));
        assertFilterMatchesNumberOfResults(2, StringFilter.greaterOrEqualThan(STRING_2));
        assertFilterMatchesNumberOfResults(2, StringFilter.lessOrEqualThan(STRING_1));

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "stringAttr", StringFilter.equalTo(STRING_1));
        assertFilterByNameMatchesEntity(entity2, "stringAttr", StringFilter.notEqualTo(STRING_1));
        assertFilterByNameMatchesEntity(entity1, "stringAttr", StringFilter.ilike(STRING_1.toUpperCase()));
        assertFilterByNameMatchesEntity(entity1, "stringAttr", StringFilter.like(STRING_1));
        assertFilterByNameMatchesEntity(entity2, "stringAttr", StringFilter.lessThan(STRING_1));
        assertFilterByNameMatchesEntity(entity1, "stringAttr", StringFilter.greaterThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "stringAttr", StringFilter.greaterOrEqualThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "stringAttr", StringFilter.lessOrEqualThan(STRING_1));

        // Derived

        assertEntityMatchesFilter(entity1, "this.derivedStringAttr!matches('te.*')");
        assertEntityMatchesFilter(entity1, "this.derivedStringAttr == 'test'");
        assertEntityMatchesFilter(entity2, "this.derivedStringAttr != 'test'");
        assertEntityMatchesFilter(entity1, "this.derivedStringAttr!ilike('TEST')");
        assertEntityMatchesFilter(entity1, "this.derivedStringAttr!ilike('test')");
        assertEntityMatchesFilter(entity2, "this.derivedStringAttr < 'test'");
        assertEntityMatchesFilter(entity1, "this.derivedStringAttr > 'Another'");

        Long greaterOrEqualThanByStringFilterDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilterDerived);

        assertFilterMatchesNumberOfResults(2, "this.derivedStringAttr <= 'test'");

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "derivedStringAttr", StringFilter.equalTo(STRING_1));
        assertFilterByNameMatchesEntity(entity2, "derivedStringAttr", StringFilter.notEqualTo(STRING_1));
        assertFilterByNameMatchesEntity(entity1, "derivedStringAttr", StringFilter.ilike(STRING_1.toUpperCase()));
        assertFilterByNameMatchesEntity(entity1, "derivedStringAttr", StringFilter.like(STRING_1));
        assertFilterByNameMatchesEntity(entity2, "derivedStringAttr", StringFilter.lessThan(STRING_1));
        assertFilterByNameMatchesEntity(entity1, "derivedStringAttr", StringFilter.greaterThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedStringAttr", StringFilter.greaterOrEqualThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedStringAttr", StringFilter.lessOrEqualThan(STRING_1));

    }

    private void assertEntityMatchesFilter(MyEntityWithOptionalFields entity, StringFilter filter) {
        MyEntityWithOptionalFields filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        MyEntityWithOptionalFields filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, StringFilter filter) {
        List<MyEntityWithOptionalFields> filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<MyEntityWithOptionalFields> filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(filter)
                .selectList();

        assertEquals(expected, filteredDerived.size());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(entity1, "this.boolAttr == true");
        assertEntityMatchesFilter(entity2, "this.boolAttr != true");
        assertEntityMatchesFilter(entity1, "this.boolAttr");

        MyEntityWithOptionalFields isTrue = myEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isTrue())
                .selectOne()
                .get();

        assertEquals(entity1.identifier(), isTrue.identifier());

        MyEntityWithOptionalFields isFalse = myEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isFalse())
                .selectOne()
                .get();

        assertEquals(entity2.identifier(), isFalse.identifier());

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "boolAttr", BooleanFilter.isTrue());
        assertFilterByNameMatchesEntity(entity2, "boolAttr", BooleanFilter.isFalse());

        // derived

        assertEntityMatchesFilter(entity1, "this.derivedBoolAttr == true");
        assertEntityMatchesFilter(entity2, "this.derivedBoolAttr != true");
        assertEntityMatchesFilter(entity1, "this.derivedBoolAttr");

        MyEntityWithOptionalFields isTrueDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isTrue())
                .selectOne()
                .get();

        assertEquals(entity1.identifier(), isTrueDerived.identifier());

        MyEntityWithOptionalFields isFalseDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isFalse())
                .selectOne()
                .get();

        assertEquals(entity2.identifier(), isFalseDerived.identifier());

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "derivedBoolAttr", BooleanFilter.isTrue());
        assertFilterByNameMatchesEntity(entity2, "derivedBoolAttr", BooleanFilter.isFalse());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(entity1, "this.dateAttr == `2022-07-11`");
        assertEntityMatchesFilter(entity2, "this.dateAttr != `2022-07-11`");
        assertFilterMatchesNumberOfResults(2, "this.dateAttr >= `1999-09-19`");
        assertFilterMatchesNumberOfResults(2, "this.dateAttr <= `2022-07-11`");
        assertFilterMatchesNumberOfResults(1, "this.dateAttr > `1999-09-19`");
        assertFilterMatchesNumberOfResults(1, "this.dateAttr < `2022-07-11`");

        assertEntityMatchesFilter(entity1, DateFilter.equalTo(DATE_1));
        assertEntityMatchesFilter(entity2, DateFilter.notEqualTo(DATE_1));
        assertFilterMatchesNumberOfResults(2, DateFilter.greaterOrEqualThan(DATE_2));
        assertFilterMatchesNumberOfResults(2, DateFilter.lessOrEqualThan(DATE_1));
        assertFilterMatchesNumberOfResults(1, DateFilter.greaterThan(DATE_2));
        assertFilterMatchesNumberOfResults(1, DateFilter.lessThan(DATE_1));

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "dateAttr", DateFilter.equalTo(DATE_1));
        assertFilterByNameMatchesEntity(entity2, "dateAttr", DateFilter.notEqualTo(DATE_1));
        assertFilterByNameMatchesNumberOfResults(2, "dateAttr", DateFilter.greaterOrEqualThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(2, "dateAttr", DateFilter.lessOrEqualThan(DATE_1));
        assertFilterByNameMatchesNumberOfResults(1, "dateAttr", DateFilter.greaterThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(1, "dateAttr", DateFilter.lessThan(DATE_1));

        // derived

        assertEntityMatchesFilter(entity1, "this.derivedDateAttr == `2022-07-11`");
        assertEntityMatchesFilter(entity2, "this.derivedDateAttr != `2022-07-11`");
        assertFilterMatchesNumberOfResults(2, "this.derivedDateAttr >= `1999-09-19`");
        assertFilterMatchesNumberOfResults(2, "this.derivedDateAttr <= `2022-07-11`");
        assertFilterMatchesNumberOfResults(1, "this.derivedDateAttr > `1999-09-19`");
        assertFilterMatchesNumberOfResults(1, "this.derivedDateAttr < `2022-07-11`");

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "derivedDateAttr", DateFilter.equalTo(DATE_1));
        assertFilterByNameMatchesEntity(entity2, "derivedDateAttr", DateFilter.notEqualTo(DATE_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedDateAttr", DateFilter.greaterOrEqualThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedDateAttr", DateFilter.lessOrEqualThan(DATE_1));
        assertFilterByNameMatchesNumberOfResults(1, "derivedDateAttr", DateFilter.greaterThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(1, "derivedDateAttr", DateFilter.lessThan(DATE_1));

    }

    private void assertEntityMatchesFilter(MyEntityWithOptionalFields entity, DateFilter filter) {
        MyEntityWithOptionalFields filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        MyEntityWithOptionalFields filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, DateFilter filter) {
        List<MyEntityWithOptionalFields> filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<MyEntityWithOptionalFields> filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(filter)
                .selectList();

        assertEquals(expected, filteredDerived.size());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(entity1, "this.timestampAttr == `2022-07-11T19:09:33`");
        assertEntityMatchesFilter(entity2, "this.timestampAttr != `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(2, "this.timestampAttr >= `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(2, "this.timestampAttr <= `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(1, "this.timestampAttr > `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(1, "this.timestampAttr < `2022-07-11T19:09:33`");

        assertEntityMatchesFilter(entity1, TimestampFilter.equalTo(TIMESTAMP_1));
        assertEntityMatchesFilter(entity2, TimestampFilter.notEqualTo(TIMESTAMP_1));
        assertFilterMatchesNumberOfResults(2, TimestampFilter.greaterOrEqualThan(TIMESTAMP_2));
        assertFilterMatchesNumberOfResults(2, TimestampFilter.lessOrEqualThan(TIMESTAMP_1));
        assertFilterMatchesNumberOfResults(1, TimestampFilter.greaterThan(TIMESTAMP_2));
        assertFilterMatchesNumberOfResults(1, TimestampFilter.lessThan(TIMESTAMP_1));

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "timestampAttr", TimestampFilter.equalTo(TIMESTAMP_1));
        assertFilterByNameMatchesEntity(entity2, "timestampAttr", TimestampFilter.notEqualTo(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(2, "timestampAttr", TimestampFilter.greaterOrEqualThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(2, "timestampAttr", TimestampFilter.lessOrEqualThan(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(1, "timestampAttr", TimestampFilter.greaterThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(1, "timestampAttr", TimestampFilter.lessThan(TIMESTAMP_1));

        // derived

        assertEntityMatchesFilter(entity1, "this.derivedTimestampAttr == `2022-07-11T19:09:33`");
        assertEntityMatchesFilter(entity2, "this.derivedTimestampAttr != `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimestampAttr >= `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimestampAttr <= `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(1, "this.derivedTimestampAttr > `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(1, "this.derivedTimestampAttr < `2022-07-11T19:09:33`");

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "derivedTimestampAttr", TimestampFilter.equalTo(TIMESTAMP_1));
        assertFilterByNameMatchesEntity(entity2, "derivedTimestampAttr", TimestampFilter.notEqualTo(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimestampAttr", TimestampFilter.greaterOrEqualThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimestampAttr", TimestampFilter.lessOrEqualThan(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimestampAttr", TimestampFilter.greaterThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimestampAttr", TimestampFilter.lessThan(TIMESTAMP_1));

    }

    private void assertEntityMatchesFilter(MyEntityWithOptionalFields entity, TimestampFilter filter) {
        MyEntityWithOptionalFields filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        MyEntityWithOptionalFields filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, TimestampFilter filter) {
        List<MyEntityWithOptionalFields> filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<MyEntityWithOptionalFields> filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(filter)
                .selectList();

        assertEquals(expected, filteredDerived.size());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());
        assertEntityMatchesFilter(entity1, "this.timeAttr == `23:59:59`");
        assertEntityMatchesFilter(entity2, "this.timeAttr != `23:59:59`");
        assertFilterMatchesNumberOfResults(2, "this.timeAttr >= `12:34:56`");
        assertFilterMatchesNumberOfResults(2, "this.timeAttr <= `23:59:59`");
        assertEntityMatchesFilter(entity1, "this.timeAttr > `12:34:56`");
        assertEntityMatchesFilter(entity2, "this.timeAttr < `23:59:59`");

        assertEntityMatchesFilter(entity1, TimeFilter.equalTo(TIME_1));
        assertEntityMatchesFilter(entity2, TimeFilter.notEqualTo(TIME_1));
        assertFilterMatchesNumberOfResults(2, TimeFilter.greaterOrEqualThan(TIME_2));
        assertFilterMatchesNumberOfResults(2, TimeFilter.lessOrEqualThan(TIME_1));
        assertFilterMatchesNumberOfResults(1, TimeFilter.greaterThan(TIME_2));
        assertFilterMatchesNumberOfResults(1, TimeFilter.lessThan(TIME_1));

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "timeAttr", TimeFilter.equalTo(TIME_1));
        assertFilterByNameMatchesEntity(entity2, "timeAttr", TimeFilter.notEqualTo(TIME_1));
        assertFilterByNameMatchesNumberOfResults(2, "timeAttr", TimeFilter.greaterOrEqualThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(2, "timeAttr", TimeFilter.lessOrEqualThan(TIME_1));
        assertFilterByNameMatchesNumberOfResults(1, "timeAttr", TimeFilter.greaterThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(1, "timeAttr", TimeFilter.lessThan(TIME_1));

        // derived

        assertEntityMatchesFilter(entity1, "this.derivedTimeAttr == `23:59:59`");
        assertEntityMatchesFilter(entity2, "this.derivedTimeAttr != `23:59:59`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimeAttr >= `12:34:56`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimeAttr <= `23:59:59`");
        assertEntityMatchesFilter(entity1, "this.derivedTimeAttr > `12:34:56`");
        assertEntityMatchesFilter(entity2, "this.derivedTimeAttr < `23:59:59`");

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "derivedTimeAttr", TimeFilter.equalTo(TIME_1));
        assertFilterByNameMatchesEntity(entity2, "derivedTimeAttr", TimeFilter.notEqualTo(TIME_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimeAttr", TimeFilter.greaterOrEqualThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimeAttr", TimeFilter.lessOrEqualThan(TIME_1));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimeAttr", TimeFilter.greaterThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimeAttr", TimeFilter.lessThan(TIME_1));
    }

    private void assertEntityMatchesFilter(MyEntityWithOptionalFields entity, TimeFilter filter) {
        MyEntityWithOptionalFields filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        MyEntityWithOptionalFields filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, TimeFilter filter) {
        List<MyEntityWithOptionalFields> filtered = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<MyEntityWithOptionalFields> filteredDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(filter)
                .selectList();

        assertEquals(expected, filteredDerived.size());
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
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        MyEntityWithOptionalFields equalTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(entity1.identifier(), equalTo.identifier());

        MyEntityWithOptionalFields notEqualTo = myEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(entity2.identifier(), notEqualTo.identifier());

        // Derived

        MyEntityWithOptionalFields equalToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(entity1.identifier(), equalToDerived.identifier());

        MyEntityWithOptionalFields notEqualToDerived = myEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(entity2.identifier(), notEqualToDerived.identifier());

        // FilterByName

        assertFilterByNameMatchesEntity(entity1, "enumAttr", EnumerationFilter.equalTo(MyEnum.Bombastic));
        assertFilterByNameMatchesEntity(entity2, "enumAttr", EnumerationFilter.notEqualTo(MyEnum.Bombastic));

        // Derived

        assertFilterByNameMatchesEntity(entity1, "derivedEnumAttr", EnumerationFilter.equalTo(MyEnum.Bombastic));
        assertFilterByNameMatchesEntity(entity2, "derivedEnumAttr", EnumerationFilter.notEqualTo(MyEnum.Bombastic));

    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-002",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008"
    })
    void testFilterByNameWithNonExistName() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> myEntityWithOptionalFieldsDao
                        .query()
                        .filterByName("enumAtt", EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                        .selectList()
                        .get(0)
        );
        assertTrue(thrown.getMessage().contains("No enum constant for"));
        assertTrue(thrown.getMessage().contains("MyEntityWithOptionalFieldsAttribute#enumAtt"));
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
        assertThat(filterEntityDao.queryHaveTOnTheStringAttr(filterEntity).selectList().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(entity1.identifier(), entity2.identifier()));

        assertEquals(1, filterEntityDao.queryTestIsTheStringAttr(filterEntity).count());
        assertThat(filterEntityDao.queryTestIsTheStringAttr(filterEntity).selectList().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(entity1.identifier()));

        assertEquals(0, filterEntityDao.queryHaveNoMatchOnTheStringAttr(filterEntity).count());

        assertEquals(1, filterEntityDao.queryHaveUndefinedOnTheStringAttr(filterEntity).count());
        assertThat(filterEntityDao.queryHaveUndefinedOnTheStringAttr(filterEntity).selectList().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(entity3.identifier()));

        assertEquals(2, filterEntityDao.queryHaveDefinedOnTheStringAttr(filterEntity).count());
        assertThat(filterEntityDao.queryHaveDefinedOnTheStringAttr(filterEntity).selectList().stream().map(e -> e.identifier()).toList(),
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

        Country country1 = countryDao.create(CountryForCreate.builder().withName("Hungary").withContinent(Continent.Europe).build());
        City city1 = cityDao.create(CityForCreate.builder().withName("Budapest").withCountry(country1).build());
        City city2 = cityDao.create(CityForCreate.builder().withName("Debrecen").withCountry(country1).build());
        Person p1 = personDao.create(PersonForCreate.builder().withName("Gibpsz Jakab").withCity(city1).build());
        Person p2 = personDao.create(PersonForCreate.builder().withName("Teszt Elek").withCity(city2).build());
        Car c1 = carDao.create(CarForCreate.builder().withLicensePlate("ABC-123").withOwner(p1).build());
        Car c2 = carDao.create(CarForCreate.builder().withLicensePlate("ABC-124").withOwner(p2).build());

        Tester tester = testerDao.create(TesterForCreate.builder().build());

        assertEquals(1, testerDao.countCarsOfTesztElek(tester));
        assertThat(testerDao.queryCarsOfTesztElek(tester).selectList(), containsInAnyOrder(c2));

        assertEquals(1, testerDao.countCarsInBudapest(tester));
        assertThat(testerDao.queryCarsInBudapest(tester).selectList(), containsInAnyOrder(c1));

        assertEquals(2, testerDao.countCarsInHungary(tester));
        assertThat(testerDao.queryCarsInHungary(tester).selectList(), Matchers.containsInAnyOrder(c2, c1));

        assertEquals(2, testerDao.countCarsOfKnownContinents(tester));
        assertThat(testerDao.queryCarsInHungary(tester).selectList(), Matchers.containsInAnyOrder(c1, c2));

    }

    private void assertFilterByNameMatchesEntity(MyEntityWithOptionalFields entity, String attributeName, Filter filter) {
        MyEntityWithOptionalFields equalsByName = myEntityWithOptionalFieldsDao
                .query()
                .filterByName(attributeName, filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), equalsByName.identifier());
    }

    private void assertFilterByNameMatchesNumberOfResults(int expected, String attributeName, Filter filter1) {
        List<MyEntityWithOptionalFields> equalsByName = myEntityWithOptionalFieldsDao
                .query()
                .filterByName(attributeName, filter1)
                .selectList();

        assertEquals(expected, equalsByName.size());
    }

    private void assertFilterMatchesNumberOfResults(int expected, String customFilter) {
        List<MyEntityWithOptionalFields> greaterOrEqualsByStringFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterBy(customFilter)
                .selectList();

        assertEquals(expected, greaterOrEqualsByStringFilter.size());
    }

    private void assertEntityMatchesFilter(MyEntityWithOptionalFields entity, String customFilter) {
        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy(customFilter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredByString.identifier());
    }

    @Inject
    ProductDao productDao;

    @Inject
    BucketDao bucketDao;

    @Inject
    BucketTesterDao bucketTesterDao;

    @Test
    @TestCase("EmbeddedFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-012",
    })
    void testEmbeddedFilter() {

        Product product1 = productDao.create(ProductForCreate.builder().withName("Product1").build());

        Bucket bucket1 = bucketDao.create(BucketForCreate.builder()
                .withItems(List.of(
                                ItemForCreate.builder()
                                        .withName("i1")
                                        .withWeight(22.0)
                                        .withProduct(ProductForCreate.builderFrom(product1).build())
                                        .build(),
                                ItemForCreate.builder()
                                        .withName("i2")
                                        .withWeight(5.0)
                                        .build(),
                                ItemForCreate.builder()
                                        .withName("i3")
                                        .withWeight(15D)
                                        .build()
                        )
                )
                .build()
        );
        Bucket bucket2 = bucketDao.create(BucketForCreate.builder()
                .withItems(List.of(
                                ItemForCreate.builder()
                                        .withName("i1")
                                        .withWeight(20.0)
                                        .build(),
                                ItemForCreate.builder()
                                        .withName("i2")
                                        .withWeight(15.0)
                                        .build(),
                                ItemForCreate.builder()
                                        .withName("i3")
                                        .withWeight(16.0)
                                        .build()
                        )
                )
                .build()
        );
        assertEquals(1, productDao.countAll());

        Optional<Bucket> result1 = bucketDao.getById(bucket1.identifier());
        assertThat(result1.isPresent(), is(true));
        assertThat(result1.orElseThrow().getItems(), hasSize(3));
        assertThat(result1.orElseThrow().getProduct1Items(), hasSize(1));
        // TODO JNG-4376
        //assertThat(result1.orElseThrow().getItemsHeavierThanBucketAvg(), hasSize(2));
        assertThat(result1.orElseThrow().getItemsHeavierThanAvg(), hasSize(1));

        Optional<Bucket> result2 = bucketDao.getById(bucket2.identifier());
        assertThat(result2.isPresent(), is(true));
        assertThat(result2.orElseThrow().getItems(), hasSize(3));
        assertThat(result2.orElseThrow().getProduct1Items(), hasSize(0));
        // TODO JNG-4376
        //assertThat(result2.orElseThrow().getItemsHeavierThanBucketAvg(), hasSize(1));
        assertThat(result2.orElseThrow().getItemsHeavierThanAvg(), hasSize(2));

        BucketTester bucketTester = bucketTesterDao.create(BucketTesterForCreate.builder().build());
        assertThat(bucketTester.getBucketsWithProduct1(), hasSize(1));
        assertThat(bucketTester.getBucketsWithMainProduct1(), hasSize(1));
    }

}
