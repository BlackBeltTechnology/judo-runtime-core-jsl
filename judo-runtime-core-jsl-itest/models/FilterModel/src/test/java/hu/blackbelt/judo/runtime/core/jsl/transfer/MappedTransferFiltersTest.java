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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.continent.Continent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferbucket.TransferBucket;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferbucket.TransferBucketDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferbucket.TransferBucketForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferbuckettester.TransferBucketTester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferbuckettester.TransferBucketTesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferbuckettester.TransferBucketTesterForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercar.TransferCar;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercar.TransferCarDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercar.TransferCarForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercity.TransferCity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercity.TransferCityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercity.TransferCityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercountry.TransferCountry;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercountry.TransferCountryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercountry.TransferCountryForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercustomer.TransferCustomer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercustomer.TransferCustomerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercustomer.TransferCustomerForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferfilterentity.TransferFilterEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferfilterentity.TransferFilterEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferfilterentity.TransferFilterEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferitem.TransferItemForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferorder.TransferOrder;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferorder.TransferOrderDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferorder.TransferOrderForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferorderdetail.TransferOrderDetailDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferorderdetail.TransferOrderDetailForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferperson.TransferPerson;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferperson.TransferPersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferperson.TransferPersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferproduct.TransferProduct;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferproduct.TransferProductDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferproduct.TransferProductForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfertester.TransferTester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfertester.TransferTesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfertester.TransferTesterForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FilterDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.*;
import lombok.extern.slf4j.Slf4j;
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
public class MappedTransferFiltersTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Filter", new FilterDaoModules());

    @Inject
    TransferMyEntityWithOptionalFieldsDao transferMyEntityWithOptionalFieldsDao;

    @Inject
    TransferFilterEntityDao transferFilterEntityDao;

    @Inject
    TransferCountryDao transferCountryDao;

    @Inject
    TransferCityDao transferCityDao;

    @Inject
    TransferPersonDao transferPersonDao;

    @Inject
    TransferCarDao transferCarDao;

    @Inject
    TransferTesterDao transferTesterDao;


    TransferMyEntityWithOptionalFields transfer1;

    TransferMyEntityWithOptionalFields transfer2;

    TransferMyEntityWithOptionalFields transfer3;

    TransferFilterEntity transferFilterEntity;

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

        transfer1 = transferMyEntityWithOptionalFieldsDao.create(TransferMyEntityWithOptionalFieldsForCreate.builder()
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

        transfer2 = transferMyEntityWithOptionalFieldsDao.create(TransferMyEntityWithOptionalFieldsForCreate.builder()
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

        transfer3 = transferMyEntityWithOptionalFieldsDao.create(TransferMyEntityWithOptionalFieldsForCreate.builder()
                .build());

        transferFilterEntity = transferFilterEntityDao.create(TransferFilterEntityForCreate.builder().build());
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
            "REQ-ENT-008",
            "REQ-SRV-002"

    })
    public void testFilterWithMultipleFiltersOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        List<TransferMyEntityWithOptionalFields> multiFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .filterByScaledAttr(NumberFilter.lessThan(INTEGER_2))
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .selectList();

        assertEquals(1, multiFilter.size());
        assertEquals(transfer1.identifier(), multiFilter.get(0).identifier());

        multiFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.equalTo(INTEGER_1))
                .filterByName("scaledAttr", NumberFilter.lessThan(INTEGER_2))
                .filterByName("stringAttr", StringFilter.equalTo(STRING_1))
                .selectList();

        assertEquals(1, multiFilter.size());

        assertEquals(transfer1.identifier(), multiFilter.get(0).identifier());
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testIntegerNumberFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(transfer1, "this.integerAttr >= 1 and this.integerAttr < 2");
        assertEntityMatchesFilter(transfer1, "this.integerAttr == 1");
        assertEntityMatchesFilter(transfer2, "this.integerAttr != 1");
        assertFilterMatchesNumberOfResults(2, "this.integerAttr >= 1");
        assertEntityMatchesFilter(transfer2, "this.integerAttr > 1");
        assertFilterMatchesNumberOfResults(2, "this.integerAttr <= 2");
        assertEntityMatchesFilter(transfer1, "this.integerAttr < 2");

        assertEntityMatchesFilter(transfer1, NumberFilter.equalTo(INTEGER_1));
        assertEntityMatchesFilter(transfer2, NumberFilter.notEqualTo(INTEGER_1));
        assertFilterMatchesNumberOfResults(2, NumberFilter.greaterOrEqualThan(INTEGER_1));
        assertEntityMatchesFilter(transfer2, NumberFilter.greaterThan(INTEGER_1));
        assertFilterMatchesNumberOfResults(2, NumberFilter.lessOrEqualThan(INTEGER_2));
        assertEntityMatchesFilter(transfer1, NumberFilter.lessThan(INTEGER_2));

        // Zero element
        List<TransferMyEntityWithOptionalFields> zeroResult = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResult.isEmpty());

        // filterByName

        assertFilterByNameMatchesEntity(transfer1, "integerAttr", NumberFilter.equalTo(INTEGER_1));
        assertFilterByNameMatchesEntity(transfer2, "integerAttr", NumberFilter.notEqualTo(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "integerAttr", NumberFilter.greaterOrEqualThan(INTEGER_1));
        assertFilterByNameMatchesEntity(transfer2, "integerAttr", NumberFilter.greaterThan(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "integerAttr", NumberFilter.lessOrEqualThan(INTEGER_2));
        assertFilterByNameMatchesEntity(transfer1, "integerAttr", NumberFilter.lessThan(INTEGER_2));

        // Zero element

        zeroResult = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.lessThan(INTEGER_1))
                .filterByName("integerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResult.isEmpty());

        assertFilterMatchesNumberOfResults(0, "this.integerAttr > 1 and this.integerAttr < 2");

        // TODO JNG-3980
        // filter for undefined

//        TransferMyEntityWithOptionalFields undefinedResult = transferMyEntityWithOptionalFieldsDao
//                .query()
//                .filterBy("this.integerAttr!isDefined()")
//                .selectOne()
//                .get();
//
//        assertEquals(entity3.identifier(),
//                undefinedResult.identifier());

        // Derived attributes

        assertEntityMatchesFilter(transfer1, "this.integerAttr >= 1 and this.integerAttr < 2");
        assertFilterMatchesNumberOfResults(0, "this.derivedIntegerAttr > 1 and this.derivedIntegerAttr < 2");
        assertEntityMatchesFilter(transfer1, "this.derivedIntegerAttr == 1");
        assertEntityMatchesFilter(transfer2, "this.derivedIntegerAttr != 1");
        assertFilterMatchesNumberOfResults(2, "this.derivedIntegerAttr >= 1");
        assertEntityMatchesFilter(transfer2, "this.derivedIntegerAttr > 1");
        assertFilterMatchesNumberOfResults(2, "this.derivedIntegerAttr <= 2");
        assertEntityMatchesFilter(transfer1, "this.derivedIntegerAttr < 2");

        List<TransferMyEntityWithOptionalFields> zeroResultDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResultDerived.isEmpty());

        // filterByName

        assertFilterByNameMatchesEntity(transfer1, "derivedIntegerAttr", NumberFilter.equalTo(INTEGER_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedIntegerAttr", NumberFilter.notEqualTo(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedIntegerAttr", NumberFilter.greaterOrEqualThan(INTEGER_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedIntegerAttr", NumberFilter.greaterThan(INTEGER_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedIntegerAttr", NumberFilter.lessOrEqualThan(INTEGER_2));
        assertFilterByNameMatchesEntity(transfer1, "derivedIntegerAttr", NumberFilter.lessThan(INTEGER_2));

        zeroResultDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.lessThan(INTEGER_1))
                .filterByName("derivedIntegerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResultDerived.isEmpty());
    }

    private void assertFilterMatchesNumberOfResults(int expected, NumberFilter filter) {
        List<TransferMyEntityWithOptionalFields> filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<TransferMyEntityWithOptionalFields> filteredDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());
    }

    private void assertEntityMatchesFilter(TransferMyEntityWithOptionalFields entity, NumberFilter filter) {
        TransferMyEntityWithOptionalFields filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        TransferMyEntityWithOptionalFields filteredDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testScaledNumberFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();
        assertEquals(3, list.size());

        assertEntityMatchesFilter(transfer1, "this.scaledAttr >= 1 and this.scaledAttr <= 2");
        assertEntityMatchesFilter(transfer1, "this.scaledAttr == 1.23");
        assertEntityMatchesFilter(transfer2, "this.scaledAttr != 1.23");
        assertFilterMatchesNumberOfResults(2, "this.scaledAttr >= 1.23");
        assertEntityMatchesFilter(transfer2, "this.scaledAttr > 1.23");
        assertFilterMatchesNumberOfResults(2, "this.scaledAttr <= 2.34");
        assertEntityMatchesFilter(transfer1, "this.scaledAttr < 2.34");
        assertFilterMatchesNumberOfResults(0, "this.scaledAttr > 0 and this.scaledAttr < 1");

        assertEntityMatchesFilterForScaledAttr(transfer1, NumberFilter.equalTo(SCALED_1));
        assertEntityMatchesFilterForScaledAttr(transfer2, NumberFilter.notEqualTo(SCALED_1));
        assertFilterMatchesNumberOfResultsForScaledAttr(2, NumberFilter.greaterOrEqualThan(SCALED_1));
        assertEntityMatchesFilterForScaledAttr(transfer2, NumberFilter.greaterThan(SCALED_1));
        assertFilterMatchesNumberOfResultsForScaledAttr(2, NumberFilter.lessOrEqualThan(SCALED_2));
        assertEntityMatchesFilterForScaledAttr(transfer1, NumberFilter.lessThan(SCALED_2));

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "scaledAttr", NumberFilter.equalTo(SCALED_1));
        assertFilterByNameMatchesEntity(transfer2, "scaledAttr", NumberFilter.notEqualTo(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "scaledAttr", NumberFilter.greaterOrEqualThan(SCALED_1));
        assertFilterByNameMatchesEntity(transfer2, "scaledAttr", NumberFilter.greaterThan(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "scaledAttr", NumberFilter.lessOrEqualThan(SCALED_2));
        assertFilterByNameMatchesEntity(transfer1, "scaledAttr", NumberFilter.lessThan(SCALED_2));

        // Derived

        assertEntityMatchesFilter(transfer1, "this.derivedScaledAttr >= 1 and this.derivedScaledAttr <= 2");
        assertFilterMatchesNumberOfResults(0, "this.derivedScaledAttr > 0 and this.derivedScaledAttr < 1");
        assertEntityMatchesFilter(transfer1, "this.derivedScaledAttr == 1.23");
        assertEntityMatchesFilter(transfer2, "this.derivedScaledAttr != 1.23");
        assertFilterMatchesNumberOfResults(2, "this.derivedScaledAttr >= 1.23");
        assertEntityMatchesFilter(transfer2, "this.derivedScaledAttr > 1.23");
        assertFilterMatchesNumberOfResults(2, "this.derivedScaledAttr <= 2.34");
        assertEntityMatchesFilter(transfer1, "this.derivedScaledAttr < 2.34");

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "derivedScaledAttr", NumberFilter.equalTo(SCALED_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedScaledAttr", NumberFilter.notEqualTo(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedScaledAttr", NumberFilter.greaterOrEqualThan(SCALED_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedScaledAttr", NumberFilter.greaterThan(SCALED_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedScaledAttr", NumberFilter.lessOrEqualThan(SCALED_2));
        assertFilterByNameMatchesEntity(transfer1, "derivedScaledAttr", NumberFilter.lessThan(SCALED_2));

    }

    private void assertEntityMatchesFilterForScaledAttr(TransferMyEntityWithOptionalFields entity, NumberFilter filter) {
        TransferMyEntityWithOptionalFields filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        TransferMyEntityWithOptionalFields filteredDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResultsForScaledAttr(int expected, NumberFilter filter) {
        List<TransferMyEntityWithOptionalFields> filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<TransferMyEntityWithOptionalFields> filteredDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testStringFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        // custom filter

        assertEntityMatchesFilter(transfer1, "this.stringAttr!matches('te.*')");
        assertEntityMatchesFilter(transfer1, "this.stringAttr == 'test'");
        assertEntityMatchesFilter(transfer2, "this.stringAttr != 'test'");
        assertEntityMatchesFilter(transfer1, "this.stringAttr!ilike('TEST')");
        assertEntityMatchesFilter(transfer1, "this.stringAttr!ilike('test')");
        assertEntityMatchesFilter(transfer2, "this.stringAttr < 'test'");
        assertEntityMatchesFilter(transfer1, "this.stringAttr > 'Another'");
        assertFilterMatchesNumberOfResults(2, "this.stringAttr >= 'Another'");
        assertFilterMatchesNumberOfResults(2, "this.stringAttr <= 'test'");

        assertEntityMatchesFilter(transfer1, StringFilter.equalTo(STRING_1));
        assertEntityMatchesFilter(transfer2, StringFilter.notEqualTo(STRING_1));
        assertEntityMatchesFilter(transfer1, StringFilter.ilike(STRING_1.toUpperCase()));
        assertEntityMatchesFilter(transfer1, StringFilter.like(STRING_1));
        assertEntityMatchesFilter(transfer2, StringFilter.lessThan(STRING_1));
        assertEntityMatchesFilter(transfer1, StringFilter.greaterThan(STRING_2));
        assertFilterMatchesNumberOfResults(2, StringFilter.greaterOrEqualThan(STRING_2));
        assertFilterMatchesNumberOfResults(2, StringFilter.lessOrEqualThan(STRING_1));

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "stringAttr", StringFilter.equalTo(STRING_1));
        assertFilterByNameMatchesEntity(transfer2, "stringAttr", StringFilter.notEqualTo(STRING_1));
        assertFilterByNameMatchesEntity(transfer1, "stringAttr", StringFilter.ilike(STRING_1.toUpperCase()));
        assertFilterByNameMatchesEntity(transfer1, "stringAttr", StringFilter.like(STRING_1));
        assertFilterByNameMatchesEntity(transfer2, "stringAttr", StringFilter.lessThan(STRING_1));
        assertFilterByNameMatchesEntity(transfer1, "stringAttr", StringFilter.greaterThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "stringAttr", StringFilter.greaterOrEqualThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "stringAttr", StringFilter.lessOrEqualThan(STRING_1));

        // Derived

        assertEntityMatchesFilter(transfer1, "this.derivedStringAttr!matches('te.*')");
        assertEntityMatchesFilter(transfer1, "this.derivedStringAttr == 'test'");
        assertEntityMatchesFilter(transfer2, "this.derivedStringAttr != 'test'");
        assertEntityMatchesFilter(transfer1, "this.derivedStringAttr!ilike('TEST')");
        assertEntityMatchesFilter(transfer1, "this.derivedStringAttr!ilike('test')");
        assertEntityMatchesFilter(transfer2, "this.derivedStringAttr < 'test'");
        assertEntityMatchesFilter(transfer1, "this.derivedStringAttr > 'Another'");

        Long greaterOrEqualThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilterDerived);

        assertFilterMatchesNumberOfResults(2, "this.derivedStringAttr <= 'test'");

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "derivedStringAttr", StringFilter.equalTo(STRING_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedStringAttr", StringFilter.notEqualTo(STRING_1));
        assertFilterByNameMatchesEntity(transfer1, "derivedStringAttr", StringFilter.ilike(STRING_1.toUpperCase()));
        assertFilterByNameMatchesEntity(transfer1, "derivedStringAttr", StringFilter.like(STRING_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedStringAttr", StringFilter.lessThan(STRING_1));
        assertFilterByNameMatchesEntity(transfer1, "derivedStringAttr", StringFilter.greaterThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedStringAttr", StringFilter.greaterOrEqualThan(STRING_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedStringAttr", StringFilter.lessOrEqualThan(STRING_1));

    }

    private void assertEntityMatchesFilter(TransferMyEntityWithOptionalFields entity, StringFilter filter) {
        TransferMyEntityWithOptionalFields filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        TransferMyEntityWithOptionalFields filteredDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, StringFilter filter) {
        List<TransferMyEntityWithOptionalFields> filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<TransferMyEntityWithOptionalFields> filteredDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testBooleanFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(transfer1, "this.boolAttr == true");
        assertEntityMatchesFilter(transfer2, "this.boolAttr != true");
        assertEntityMatchesFilter(transfer1, "this.boolAttr");

        TransferMyEntityWithOptionalFields isTrue = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isTrue())
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), isTrue.identifier());

        TransferMyEntityWithOptionalFields isFalse = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isFalse())
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), isFalse.identifier());

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "boolAttr", BooleanFilter.isTrue());
        assertFilterByNameMatchesEntity(transfer2, "boolAttr", BooleanFilter.isFalse());

        // derived

        assertEntityMatchesFilter(transfer1, "this.derivedBoolAttr == true");
        assertEntityMatchesFilter(transfer2, "this.derivedBoolAttr != true");
        assertEntityMatchesFilter(transfer1, "this.derivedBoolAttr");

        TransferMyEntityWithOptionalFields isTrueDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isTrue())
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), isTrueDerived.identifier());

        TransferMyEntityWithOptionalFields isFalseDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isFalse())
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), isFalseDerived.identifier());

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "derivedBoolAttr", BooleanFilter.isTrue());
        assertFilterByNameMatchesEntity(transfer2, "derivedBoolAttr", BooleanFilter.isFalse());
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testDateFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(transfer1, "this.dateAttr == `2022-07-11`");
        assertEntityMatchesFilter(transfer2, "this.dateAttr != `2022-07-11`");
        assertFilterMatchesNumberOfResults(2, "this.dateAttr >= `1999-09-19`");
        assertFilterMatchesNumberOfResults(2, "this.dateAttr <= `2022-07-11`");
        assertFilterMatchesNumberOfResults(1, "this.dateAttr > `1999-09-19`");
        assertFilterMatchesNumberOfResults(1, "this.dateAttr < `2022-07-11`");

        assertEntityMatchesFilter(transfer1, DateFilter.equalTo(DATE_1));
        assertEntityMatchesFilter(transfer2, DateFilter.notEqualTo(DATE_1));
        assertFilterMatchesNumberOfResults(2, DateFilter.greaterOrEqualThan(DATE_2));
        assertFilterMatchesNumberOfResults(2, DateFilter.lessOrEqualThan(DATE_1));
        assertFilterMatchesNumberOfResults(1, DateFilter.greaterThan(DATE_2));
        assertFilterMatchesNumberOfResults(1, DateFilter.lessThan(DATE_1));

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "dateAttr", DateFilter.equalTo(DATE_1));
        assertFilterByNameMatchesEntity(transfer2, "dateAttr", DateFilter.notEqualTo(DATE_1));
        assertFilterByNameMatchesNumberOfResults(2, "dateAttr", DateFilter.greaterOrEqualThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(2, "dateAttr", DateFilter.lessOrEqualThan(DATE_1));
        assertFilterByNameMatchesNumberOfResults(1, "dateAttr", DateFilter.greaterThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(1, "dateAttr", DateFilter.lessThan(DATE_1));

        // derived

        assertEntityMatchesFilter(transfer1, "this.derivedDateAttr == `2022-07-11`");
        assertEntityMatchesFilter(transfer2, "this.derivedDateAttr != `2022-07-11`");
        assertFilterMatchesNumberOfResults(2, "this.derivedDateAttr >= `1999-09-19`");
        assertFilterMatchesNumberOfResults(2, "this.derivedDateAttr <= `2022-07-11`");
        assertFilterMatchesNumberOfResults(1, "this.derivedDateAttr > `1999-09-19`");
        assertFilterMatchesNumberOfResults(1, "this.derivedDateAttr < `2022-07-11`");

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "derivedDateAttr", DateFilter.equalTo(DATE_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedDateAttr", DateFilter.notEqualTo(DATE_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedDateAttr", DateFilter.greaterOrEqualThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedDateAttr", DateFilter.lessOrEqualThan(DATE_1));
        assertFilterByNameMatchesNumberOfResults(1, "derivedDateAttr", DateFilter.greaterThan(DATE_2));
        assertFilterByNameMatchesNumberOfResults(1, "derivedDateAttr", DateFilter.lessThan(DATE_1));

    }

    private void assertEntityMatchesFilter(TransferMyEntityWithOptionalFields entity, DateFilter filter) {
        TransferMyEntityWithOptionalFields filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        TransferMyEntityWithOptionalFields filteredDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, DateFilter filter) {
        List<TransferMyEntityWithOptionalFields> filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<TransferMyEntityWithOptionalFields> filteredDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testTimestampFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        assertEntityMatchesFilter(transfer1, "this.timestampAttr == `2022-07-11T19:09:33`");
        assertEntityMatchesFilter(transfer2, "this.timestampAttr != `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(2, "this.timestampAttr >= `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(2, "this.timestampAttr <= `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(1, "this.timestampAttr > `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(1, "this.timestampAttr < `2022-07-11T19:09:33`");

        assertEntityMatchesFilter(transfer1, TimestampFilter.equalTo(TIMESTAMP_1));
        assertEntityMatchesFilter(transfer2, TimestampFilter.notEqualTo(TIMESTAMP_1));
        assertFilterMatchesNumberOfResults(2, TimestampFilter.greaterOrEqualThan(TIMESTAMP_2));
        assertFilterMatchesNumberOfResults(2, TimestampFilter.lessOrEqualThan(TIMESTAMP_1));
        assertFilterMatchesNumberOfResults(1, TimestampFilter.greaterThan(TIMESTAMP_2));
        assertFilterMatchesNumberOfResults(1, TimestampFilter.lessThan(TIMESTAMP_1));

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "timestampAttr", TimestampFilter.equalTo(TIMESTAMP_1));
        assertFilterByNameMatchesEntity(transfer2, "timestampAttr", TimestampFilter.notEqualTo(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(2, "timestampAttr", TimestampFilter.greaterOrEqualThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(2, "timestampAttr", TimestampFilter.lessOrEqualThan(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(1, "timestampAttr", TimestampFilter.greaterThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(1, "timestampAttr", TimestampFilter.lessThan(TIMESTAMP_1));

        // derived

        assertEntityMatchesFilter(transfer1, "this.derivedTimestampAttr == `2022-07-11T19:09:33`");
        assertEntityMatchesFilter(transfer2, "this.derivedTimestampAttr != `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimestampAttr >= `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimestampAttr <= `2022-07-11T19:09:33`");
        assertFilterMatchesNumberOfResults(1, "this.derivedTimestampAttr > `1999-09-19T09:09:09`");
        assertFilterMatchesNumberOfResults(1, "this.derivedTimestampAttr < `2022-07-11T19:09:33`");

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "derivedTimestampAttr", TimestampFilter.equalTo(TIMESTAMP_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedTimestampAttr", TimestampFilter.notEqualTo(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimestampAttr", TimestampFilter.greaterOrEqualThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimestampAttr", TimestampFilter.lessOrEqualThan(TIMESTAMP_1));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimestampAttr", TimestampFilter.greaterThan(TIMESTAMP_2));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimestampAttr", TimestampFilter.lessThan(TIMESTAMP_1));

    }

    private void assertEntityMatchesFilter(TransferMyEntityWithOptionalFields entity, TimestampFilter filter) {
        TransferMyEntityWithOptionalFields filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        TransferMyEntityWithOptionalFields filteredDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, TimestampFilter filter) {
        List<TransferMyEntityWithOptionalFields> filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<TransferMyEntityWithOptionalFields> filteredDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testTimeFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());
        assertEntityMatchesFilter(transfer1, "this.timeAttr == `23:59:59`");
        assertEntityMatchesFilter(transfer2, "this.timeAttr != `23:59:59`");
        assertFilterMatchesNumberOfResults(2, "this.timeAttr >= `12:34:56`");
        assertFilterMatchesNumberOfResults(2, "this.timeAttr <= `23:59:59`");

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr > `12:34:56`")
                .selectList();

        assertEquals(1, greaterThanByStringFilter.size());
        assertEquals(transfer1.identifier(), greaterThanByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr < `23:59:59`")
                .selectList();

        assertEquals(1, lessThanByStringFilter.size());
        assertEquals(transfer2.identifier(), lessThanByStringFilter.get(0).identifier());

        assertEntityMatchesFilter(transfer1, TimeFilter.equalTo(TIME_1));
        assertEntityMatchesFilter(transfer2, TimeFilter.notEqualTo(TIME_1));
        assertFilterMatchesNumberOfResults(2, TimeFilter.greaterOrEqualThan(TIME_2));
        assertFilterMatchesNumberOfResults(2, TimeFilter.lessOrEqualThan(TIME_1));
        assertFilterMatchesNumberOfResults(1, TimeFilter.greaterThan(TIME_2));
        assertFilterMatchesNumberOfResults(1, TimeFilter.lessThan(TIME_1));

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "timeAttr", TimeFilter.equalTo(TIME_1));
        assertFilterByNameMatchesEntity(transfer2, "timeAttr", TimeFilter.notEqualTo(TIME_1));
        assertFilterByNameMatchesNumberOfResults(2, "timeAttr", TimeFilter.greaterOrEqualThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(2, "timeAttr", TimeFilter.lessOrEqualThan(TIME_1));
        assertFilterByNameMatchesNumberOfResults(1, "timeAttr", TimeFilter.greaterThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(1, "timeAttr", TimeFilter.lessThan(TIME_1));

        // derived

        assertEntityMatchesFilter(transfer1, "this.derivedTimeAttr == `23:59:59`");
        assertEntityMatchesFilter(transfer2, "this.derivedTimeAttr != `23:59:59`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimeAttr >= `12:34:56`");
        assertFilterMatchesNumberOfResults(2, "this.derivedTimeAttr <= `23:59:59`");
        assertEntityMatchesFilter(transfer1, "this.derivedTimeAttr > `12:34:56`");
        assertEntityMatchesFilter(transfer2, "this.derivedTimeAttr < `23:59:59`");

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "derivedTimeAttr", TimeFilter.equalTo(TIME_1));
        assertFilterByNameMatchesEntity(transfer2, "derivedTimeAttr", TimeFilter.notEqualTo(TIME_1));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimeAttr", TimeFilter.greaterOrEqualThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(2, "derivedTimeAttr", TimeFilter.lessOrEqualThan(TIME_1));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimeAttr", TimeFilter.greaterThan(TIME_2));
        assertFilterByNameMatchesNumberOfResults(1, "derivedTimeAttr", TimeFilter.lessThan(TIME_1));
    }

    private void assertEntityMatchesFilter(TransferMyEntityWithOptionalFields entity, TimeFilter filter) {
        TransferMyEntityWithOptionalFields filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filtered.identifier());

        TransferMyEntityWithOptionalFields filteredDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredDerived.identifier());
    }

    private void assertFilterMatchesNumberOfResults(int expected, TimeFilter filter) {
        List<TransferMyEntityWithOptionalFields> filtered = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(filter)
                .selectList();

        assertEquals(expected, filtered.size());

        List<TransferMyEntityWithOptionalFields> filteredDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testEnumFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        // Derived

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        // FilterByName

        assertFilterByNameMatchesEntity(transfer1, "enumAttr", EnumerationFilter.equalTo(MyEnum.Bombastic));
        assertFilterByNameMatchesEntity(transfer2, "enumAttr", EnumerationFilter.notEqualTo(MyEnum.Bombastic));

        // Derived

        assertFilterByNameMatchesEntity(transfer1, "derivedEnumAttr", EnumerationFilter.equalTo(MyEnum.Bombastic));
        assertFilterByNameMatchesEntity(transfer2, "derivedEnumAttr", EnumerationFilter.notEqualTo(MyEnum.Bombastic));

    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-002",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    void testFilterByNameWithNonExistName() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    void testDerivedWithFilterOnTransfer() {

        assertEquals(3, transferMyEntityWithOptionalFieldsDao.query().count());

        assertEquals(2, transferFilterEntityDao.queryHaveTOnTheStringAttr(transferFilterEntity).count());
        assertThat(transferFilterEntityDao.queryHaveTOnTheStringAttr(transferFilterEntity).selectList().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(transfer1.identifier(), transfer2.identifier()));

        assertEquals(1, transferFilterEntityDao.queryTestIsTheStringAttr(transferFilterEntity).count());
        assertThat(transferFilterEntityDao.queryTestIsTheStringAttr(transferFilterEntity).selectList().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(transfer1.identifier()));

        assertEquals(0, transferFilterEntityDao.queryHaveNoMatchOnTheStringAttr(transferFilterEntity).count());

        assertEquals(1, transferFilterEntityDao.queryHaveUndefinedOnTheStringAttr(transferFilterEntity).count());
        assertThat(transferFilterEntityDao.queryHaveUndefinedOnTheStringAttr(transferFilterEntity).selectList().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(transfer3.identifier()));

        assertEquals(2, transferFilterEntityDao.queryHaveDefinedOnTheStringAttr(transferFilterEntity).count());
        assertThat(transferFilterEntityDao.queryHaveDefinedOnTheStringAttr(transferFilterEntity).selectList().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(transfer1.identifier(), transfer2.identifier()));

    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    void testNavigationOnFilterOnTransfer() {

        TransferCountry tcountry1 = transferCountryDao.create(TransferCountryForCreate.builder().withName("Hungary").withContinent(Continent.Europe).build());
        TransferCity tcity1 = transferCityDao.create(TransferCityForCreate.builder().withName("Budapest").withCountry(tcountry1).build());
        TransferCity tcity2 = transferCityDao.create(TransferCityForCreate.builder().withName("Debrecen").withCountry(tcountry1).build());
        TransferPerson tp1 = transferPersonDao.create(TransferPersonForCreate.builder().withName("Gibpsz Jakab").withCity(tcity1).build());
        TransferPerson tp2 = transferPersonDao.create(TransferPersonForCreate.builder().withName("Teszt Elek").withCity(tcity2).build());
        TransferCar tc1 = transferCarDao.create(TransferCarForCreate.builder().withLicensePlate("ABC-123").withOwner(tp1).build());
        TransferCar tc2 = transferCarDao.create(TransferCarForCreate.builder().withLicensePlate("ABC-124").withOwner(tp2).build());

        TransferTester tester = transferTesterDao.create(TransferTesterForCreate.builder().build());

        assertEquals(1, transferTesterDao.countCarsOfTesztElek(tester));
        assertThat(transferTesterDao.queryCarsOfTesztElek(tester).selectList(), containsInAnyOrder(tc2));

        assertEquals(1, transferTesterDao.countCarsInBudapest(tester));
        assertThat(transferTesterDao.queryCarsInBudapest(tester).selectList(), containsInAnyOrder(tc1));

        assertEquals(2, transferTesterDao.countCarsInHungary(tester));
        assertThat(transferTesterDao.queryCarsInHungary(tester).selectList(), containsInAnyOrder(tc2, tc1));

        assertEquals(2, transferTesterDao.countCarsOfKnownContinents(tester));
        assertThat(transferTesterDao.queryCarsInHungary(tester).selectList(), containsInAnyOrder(tc1, tc2));

    }

    private void assertFilterByNameMatchesEntity(TransferMyEntityWithOptionalFields entity, String attributeName, Filter filter) {
        TransferMyEntityWithOptionalFields equalsByName = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName(attributeName, filter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), equalsByName.identifier());
    }

    private void assertFilterByNameMatchesNumberOfResults(int expected, String attributeName, Filter filter1) {
        List<TransferMyEntityWithOptionalFields> equalsByName = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName(attributeName, filter1)
                .selectList();

        assertEquals(expected, equalsByName.size());
    }

    private void assertFilterMatchesNumberOfResults(int expected, String customFilter) {
        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy(customFilter)
                .selectList();

        assertEquals(expected, greaterOrEqualsByStringFilter.size());
    }

    private void assertEntityMatchesFilter(TransferMyEntityWithOptionalFields entity, String customFilter) {
        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy(customFilter)
                .selectOne()
                .get();

        assertEquals(entity.identifier(), filteredByString.identifier());
    }

    @Inject
    TransferProductDao transferProductDao;

    @Inject
    TransferBucketDao transferBucketDao;

    @Inject
    TransferBucketTesterDao transferBucketTesterDao;

    @Test
    @TestCase("EmbeddedFilterOnTransfer")
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
            "REQ-SRV-002"
    })
    void EmbeddedFilterTestOnTransfer() {

        TransferProduct product1 = transferProductDao.create(TransferProductForCreate.builder().withName("Product1").build());

        TransferBucket bucket1 = transferBucketDao.create(TransferBucketForCreate.builder()
                .withItems(List.of(
                        TransferItemForCreate.builder()
                                        .withName("i1")
                                        .withWeight(22.0)
                                        .withProduct(TransferProductForCreate.builderFrom(product1).build())
                                        .build(),
                        TransferItemForCreate.builder()
                                        .withName("i2")
                                        .withWeight(5.0)
                                        .build(),
                        TransferItemForCreate.builder()
                                        .withName("i3")
                                        .withWeight(15D)
                                        .build()
                        )
                )
                .build()
        );
        TransferBucket bucket2 = transferBucketDao.create(TransferBucketForCreate.builder()
                .withItems(List.of(
                                TransferItemForCreate.builder()
                                        .withName("i1")
                                        .withWeight(20.0)
                                        .build(),
                                TransferItemForCreate.builder()
                                        .withName("i2")
                                        .withWeight(15.0)
                                        .build(),
                                TransferItemForCreate.builder()
                                        .withName("i3")
                                        .withWeight(16.0)
                                        .build()
                        )
                )
                .build()
        );
        assertEquals(1, transferProductDao.countAll());

        Optional<TransferBucket> result1 = transferBucketDao.getById(bucket1.identifier());
        assertThat(result1.isPresent(), is(true));
        assertThat(result1.orElseThrow().getItems(), hasSize(3));
        assertThat(result1.orElseThrow().getProduct1Items(), hasSize(1));
        // TODO JNG-4376
        //assertThat(result1.orElseThrow().getItemsHeavierThanBucketAvg(), hasSize(2));
        assertThat(result1.orElseThrow().getItemsHeavierThanAvg(), hasSize(1));

        Optional<TransferBucket> result2 = transferBucketDao.getById(bucket2.identifier());
        assertThat(result2.isPresent(), is(true));
        assertThat(result2.orElseThrow().getItems(), hasSize(3));
        assertThat(result2.orElseThrow().getProduct1Items(), hasSize(0));
        // TODO JNG-4376
        //assertThat(result2.orElseThrow().getItemsHeavierThanBucketAvg(), hasSize(1));
        assertThat(result2.orElseThrow().getItemsHeavierThanAvg(), hasSize(2));

        TransferBucketTester bucketTester = transferBucketTesterDao.create(TransferBucketTesterForCreate.builder().build());
        assertThat(bucketTester.getBucketsWithProduct1(), hasSize(1));
        assertThat(bucketTester.getBucketsWithMainProduct1(), hasSize(1));
    }


    @Inject
    TransferOrderDetailDao transferOrderDetailDao;

    @Inject
    TransferOrderDao transferOrderDao;

    @Inject
    TransferCustomerDao transferCustomerDao;

    @Test
    @TestCase("AggregatedFilterOnTransfer")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-006",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-012",
            "REQ-SRV-002"
    })
    void testAggregatedFilterOnTransfer() {

        TransferCustomer customer = transferCustomerDao.create(TransferCustomerForCreate.builder().build());

        TransferOrder order1 = transferOrderDao.create(TransferOrderForCreate.builder()
                .withItems(List.of(
                        TransferOrderDetailForCreate.builder().withProduct("P1").withQuantity(2).build(),
                        TransferOrderDetailForCreate.builder().withProduct("P2").withQuantity(3).build()
                        )
                )
                .build()
        );

        TransferOrder order2 = transferOrderDao.create(TransferOrderForCreate.builder()
                .withItems(List.of(
                        TransferOrderDetailForCreate.builder().withProduct("P3").withQuantity(5).build()
                        )
                )
                .build()
        );

        transferCustomerDao.addOrders(customer, List.of(order1, order2));
        assertEquals(1, transferCustomerDao.countOrdersWithMultipleItems(customer));
    }
}
