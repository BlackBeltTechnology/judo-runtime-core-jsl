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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercar.TransferCar;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercar.TransferCarDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercar.TransferCarForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercity.TransferCity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercity.TransferCityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercity.TransferCityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercountry.TransferCountry;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercountry.TransferCountryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfercountry.TransferCountryForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferfilterentity.TransferFilterEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferfilterentity.TransferFilterEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferfilterentity.TransferFilterEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferperson.TransferPerson;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferperson.TransferPersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transferperson.TransferPersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfertester.TransferTester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfertester.TransferTesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filter.filter.transfertester.TransferTesterForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FilterDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
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

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr == 1")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr != 1")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1")
                .selectList();

        assertEquals(2, greaterOrEqualsByStringFilter.size());

        TransferMyEntityWithOptionalFields greaterByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr > 1")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr <= 2")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilter.size());

        TransferMyEntityWithOptionalFields lessByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr < 2")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessByStringFilter.identifier());

        TransferMyEntityWithOptionalFields equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equals.identifier());

        TransferMyEntityWithOptionalFields notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEquals.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterOrEqualThan(INTEGER_1))
                .selectList();

        assertEquals(2, greaterOrEquals.size());

        TransferMyEntityWithOptionalFields greater = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterThan(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greater.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessOrEqualThan(INTEGER_2))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        TransferMyEntityWithOptionalFields less = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), less.identifier());

        List<TransferMyEntityWithOptionalFields> zeroResult = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResult.isEmpty());

        // filterByName

        equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.equalTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equals.identifier());

        notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.notEqualTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEquals.identifier());

        greaterOrEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.greaterOrEqualThan(INTEGER_1))
                .selectList();

        assertEquals(2, greaterOrEquals.size());

        greater = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.greaterThan(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greater.identifier());

        lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.lessOrEqualThan(INTEGER_2))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        less = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), less.identifier());

        // Zero element

        zeroResult = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("integerAttr", NumberFilter.lessThan(INTEGER_1))
                .filterByName("integerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResult.isEmpty());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr > 1 and this.integerAttr < 2")
                .selectList();

        assertTrue(filteredByZeroResultString.isEmpty());

        // TODO JNG-3980
        // filter for undefined

//        TransferMyEntityWithOptionalFields undefinedResult = transferMyEntityWithOptionalFieldsDao
//                .query()
//                .filterBy("this.integerAttr!isDefined()")
//                .selectOne()
//                .get();
//
//        assertEquals(transfer3.identifier(),
//                undefinedResult.identifier());

        // Derived attributes

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr > 1 and this.derivedIntegerAttr < 2")
                .selectList();

        assertTrue(filteredByZeroResultStringDerived.isEmpty());

        TransferMyEntityWithOptionalFields equalsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr == 1")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr != 1")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr >= 1")
                .selectList();

        assertEquals(2, greaterOrEqualsByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields greaterByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr > 1")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr <= 2")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields lessByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr < 2")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.greaterOrEqualThan(INTEGER_1))
                .selectList();

        assertEquals(2, greaterOrEqualsDerived.size());

        TransferMyEntityWithOptionalFields greaterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.greaterThan(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessOrEqualThan(INTEGER_2))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());


        TransferMyEntityWithOptionalFields lessDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessDerived.identifier());

        List<TransferMyEntityWithOptionalFields> zeroResultDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .selectList();

        assertTrue(zeroResultDerived.isEmpty());

        // filterByName

        equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.equalTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.notEqualTo(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        greaterOrEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.greaterOrEqualThan(INTEGER_1))
                .selectList();

        assertEquals(2, greaterOrEqualsDerived.size());

        greaterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.greaterThan(INTEGER_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterDerived.identifier());

        lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.lessOrEqualThan(INTEGER_2))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());


        lessDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessDerived.identifier());

        zeroResultDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedIntegerAttr", NumberFilter.lessThan(INTEGER_1))
                .filterByName("derivedIntegerAttr", NumberFilter.lessThan(INTEGER_2))
                .selectList();

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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testScaledNumberFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();
        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1 and this.scaledAttr <= 2")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr == 1.23")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr != 1.23")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1.23")
                .selectList();

        assertEquals(2, greaterOrEqualsByStringFilter.size());

        TransferMyEntityWithOptionalFields greaterByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr > 1.23")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr <= 2.34")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilter.size());

        TransferMyEntityWithOptionalFields lessByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr < 2.34")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr > 0 and this.scaledAttr < 1")
                .selectList();

        assertTrue(filteredByZeroResultString.isEmpty());

        TransferMyEntityWithOptionalFields equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.equalTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equals.identifier());

        TransferMyEntityWithOptionalFields notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEquals.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterOrEqualThan(SCALED_1))
                .selectList();

        assertEquals(2, greaterOrEquals.size());

        TransferMyEntityWithOptionalFields greater = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterThan(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greater.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessOrEqualThan(SCALED_2))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        TransferMyEntityWithOptionalFields less = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessThan(SCALED_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), less.identifier());

        // FilterByName

        equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("scaledAttr", NumberFilter.equalTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equals.identifier());

        notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("scaledAttr", NumberFilter.notEqualTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEquals.identifier());

        greaterOrEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("scaledAttr", NumberFilter.greaterOrEqualThan(SCALED_1))
                .selectList();

        assertEquals(2, greaterOrEquals.size());

        greater = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("scaledAttr", NumberFilter.greaterThan(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greater.identifier());

        lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("scaledAttr", NumberFilter.lessOrEqualThan(SCALED_2))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        less = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("scaledAttr", NumberFilter.lessThan(SCALED_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), less.identifier());
        
        // Derived

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr >= 1 and this.derivedScaledAttr <= 2")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr > 0 and this.derivedScaledAttr < 1")
                .selectList();

        assertTrue(filteredByZeroResultStringDerived.isEmpty());

        TransferMyEntityWithOptionalFields equalsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr == 1.23")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr != 1.23")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr >= 1.23")
                .selectList();

        assertEquals(2, greaterOrEqualsByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields greaterByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr > 1.23")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr <= 2.34")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields lessByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr < 2.34")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.equalTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.greaterOrEqualThan(SCALED_1))
                .selectList();

        assertEquals(2, greaterOrEqualsDerived.size());

        TransferMyEntityWithOptionalFields greaterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.greaterThan(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.lessOrEqualThan(SCALED_2))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        TransferMyEntityWithOptionalFields lessDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.lessThan(SCALED_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessDerived.identifier());

        // FilterByName

        equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedScaledAttr", NumberFilter.equalTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedScaledAttr", NumberFilter.notEqualTo(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        greaterOrEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedScaledAttr", NumberFilter.greaterOrEqualThan(SCALED_1))
                .selectList();

        assertEquals(2, greaterOrEqualsDerived.size());

        greaterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedScaledAttr", NumberFilter.greaterThan(SCALED_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), greaterDerived.identifier());

        lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedScaledAttr", NumberFilter.lessOrEqualThan(SCALED_2))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        lessDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedScaledAttr", NumberFilter.lessThan(SCALED_2))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), lessDerived.identifier());
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

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!matches('te.*')")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr == 'test'")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr != 'test'")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> iLikeByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!ilike('TEST')")
                .selectList();

        assertEquals(1, iLikeByStringFilter.size());
        assertEquals(transfer1.identifier(), iLikeByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> likeByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!ilike('test')")
                .selectList();

        assertEquals(1, likeByStringFilter.size());
        assertEquals(transfer1.identifier(), likeByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr < 'test'")
                .selectList();

        assertEquals(transfer2.identifier(), lessThanByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr > 'Another'")
                .selectList();

        assertEquals(transfer1.identifier(), greaterThanByStringFilter.get(0).identifier());

        Long greaterOrEqualThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilter);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr <= 'test'")
                .selectList();

        assertEquals(2, lessOrEqualThanByStringFilter.size());

        TransferMyEntityWithOptionalFields equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equals.identifier());

        TransferMyEntityWithOptionalFields notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.notEqualTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEquals.identifier());

        List<TransferMyEntityWithOptionalFields> iLike = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .selectList();

        assertEquals(1, iLike.size());
        assertEquals(transfer1.identifier(), iLike.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> like = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.like(STRING_1))
                .selectList();

        assertEquals(1, like.size());
        assertEquals(transfer1.identifier(), like.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessThan(STRING_1))
                .selectList();

        assertEquals(transfer2.identifier(), lessThan.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterThan(STRING_2))
                .selectList();

        assertEquals(transfer1.identifier(), greaterThan.get(0).identifier());

        Long greaterOrEqualThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThan);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessOrEqualThan(STRING_1))
                .selectList();

        assertEquals(2, lessOrEqualThan.size());

        // FilterByName

        equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.equalTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equals.identifier());

        notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.notEqualTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEquals.identifier());

        iLike = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.ilike(STRING_1.toUpperCase()))
                .selectList();

        assertEquals(1, iLike.size());
        assertEquals(transfer1.identifier(), iLike.get(0).identifier());

        like = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.like(STRING_1))
                .selectList();

        assertEquals(1, like.size());
        assertEquals(transfer1.identifier(), like.get(0).identifier());

        lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.lessThan(STRING_1))
                .selectList();

        assertEquals(transfer2.identifier(), lessThan.get(0).identifier());

        greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.greaterThan(STRING_2))
                .selectList();

        assertEquals(transfer1.identifier(), greaterThan.get(0).identifier());

        greaterOrEqualThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThan);

        lessOrEqualThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("stringAttr", StringFilter.lessOrEqualThan(STRING_1))
                .selectList();

        assertEquals(2, lessOrEqualThan.size());

        // Derived

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!matches('te.*')")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr == 'test'")
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr != 'test'")
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> iLikeByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!ilike('TEST')")
                .selectList();

        assertEquals(1, iLikeByStringFilterDerived.size());
        assertEquals(transfer1.identifier(), iLikeByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> likeByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!ilike('test')")
                .selectList();

        assertEquals(1, likeByStringFilterDerived.size());
        assertEquals(transfer1.identifier(), likeByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr < 'test'")
                .selectList();

        assertEquals(transfer2.identifier(), lessThanByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr > 'Another'")
                .selectList();

        assertEquals(transfer1.identifier(), greaterThanByStringFilterDerived.get(0).identifier());

        Long greaterOrEqualThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilterDerived);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr <= 'test'")
                .selectList();

        assertEquals(2, lessOrEqualThanByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.equalTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.notEqualTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        List<TransferMyEntityWithOptionalFields> iLikeDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .selectList();

        assertEquals(1, iLikeDerived.size());
        assertEquals(transfer1.identifier(), iLikeDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> likeDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.like(STRING_1))
                .selectList();

        assertEquals(1, likeDerived.size());
        assertEquals(transfer1.identifier(), likeDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.lessThan(STRING_1))
                .selectList();

        assertEquals(transfer2.identifier(), lessThanDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.greaterThan(STRING_2))
                .selectList();

        assertEquals(transfer1.identifier(), greaterThanDerived.get(0).identifier());

        Long greaterOrEqualThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThanDerived);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.lessOrEqualThan(STRING_1))
                .selectList();

        assertEquals(2, lessOrEqualThanDerived.size());
        
        // FilterByName

        equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.equalTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.notEqualTo(STRING_1))
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        iLikeDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.ilike(STRING_1.toUpperCase()))
                .selectList();

        assertEquals(1, iLikeDerived.size());
        assertEquals(transfer1.identifier(), iLikeDerived.get(0).identifier());

        likeDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.like(STRING_1))
                .selectList();

        assertEquals(1, likeDerived.size());
        assertEquals(transfer1.identifier(), likeDerived.get(0).identifier());

        lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.lessThan(STRING_1))
                .selectList();

        assertEquals(transfer2.identifier(), lessThanDerived.get(0).identifier());

        greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.greaterThan(STRING_2))
                .selectList();

        assertEquals(transfer1.identifier(), greaterThanDerived.get(0).identifier());

        greaterOrEqualThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThanDerived);

        lessOrEqualThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedStringAttr", StringFilter.lessOrEqualThan(STRING_1))
                .selectList();

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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testBooleanFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr == true")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields filteredByStringNotEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr != true")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), filteredByStringNotEquals.identifier());

        TransferMyEntityWithOptionalFields filteredByStringOnlyAttr = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringOnlyAttr.identifier());

        TransferMyEntityWithOptionalFields isTrue = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isTrue())
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), isTrue.identifier());

        TransferMyEntityWithOptionalFields isFalse = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isFalse())
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), isFalse.identifier());
        
        // FilterByName

        isTrue = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("boolAttr", BooleanFilter.isTrue())
                .selectOne()
                .get();

        assertEquals(transfer1.identifier(), isTrue.identifier());

        isFalse = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("boolAttr", BooleanFilter.isFalse())
                .selectOne()
                .get();

        assertEquals(transfer2.identifier(), isFalse.identifier());
        
        // derived

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr == true")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        TransferMyEntityWithOptionalFields filteredByStringNotEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr != true")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), filteredByStringNotEqualsDerived.identifier());

        TransferMyEntityWithOptionalFields filteredByStringOnlyAttrDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringOnlyAttrDerived.identifier());

        TransferMyEntityWithOptionalFields isTrueDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isTrue())
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), isTrueDerived.identifier());

        TransferMyEntityWithOptionalFields isFalseDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isFalse())
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), isFalseDerived.identifier());

        // FilterByName

        isTrueDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedBoolAttr", BooleanFilter.isTrue())
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), isTrueDerived.identifier());

        isFalseDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedBoolAttr", BooleanFilter.isFalse())
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), isFalseDerived.identifier());
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

        TransferMyEntityWithOptionalFields equalToByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr == `2022-07-11`")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByFilterString.identifier());

        TransferMyEntityWithOptionalFields notEqualToByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr != `2022-07-11`")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByFilterString.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr >= `1999-09-19`")
                .selectList();

        assertEquals(2, greaterOrEqualByFilterString.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr <= `2022-07-11`")
                .selectList();

        assertEquals(2, lessOrEqualByFilterString.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr > `1999-09-19`")
                .selectList();

        assertEquals(1, greaterThanByFilterString.size());

        List<TransferMyEntityWithOptionalFields> lessThanByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr < `2022-07-11`")
                .selectList();

        assertEquals(1, lessThanByFilterString.size());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.equalTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.notEqualTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.greaterOrEqualThan(DATE_2))
                .selectList();

        assertEquals(2, greaterOrEqual.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.greaterThan(DATE_2))
                .selectList();

        assertEquals(1, greaterThan.size());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessThan(DATE_1))
                .selectList();

        assertEquals(1, lessThan.size());

        // FilterByName

        equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("dateAttr", DateFilter.equalTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("dateAttr", DateFilter.notEqualTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("dateAttr", DateFilter.greaterOrEqualThan(DATE_2))
                .selectList();

        assertEquals(2, greaterOrEqual.size());

        lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("dateAttr", DateFilter.lessOrEqualThan(DATE_1))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("dateAttr", DateFilter.greaterThan(DATE_2))
                .selectList();

        assertEquals(1, greaterThan.size());

        lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("dateAttr", DateFilter.lessThan(DATE_1))
                .selectList();

        assertEquals(1, lessThan.size());
        
        // derived

        TransferMyEntityWithOptionalFields equalToByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr == `2022-07-11`")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByFilterStringDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr != `2022-07-11`")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByFilterStringDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr >= `1999-09-19`")
                .selectList();

        assertEquals(2, greaterOrEqualByFilterStringDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr <= `2022-07-11`")
                .selectList();

        assertEquals(2, lessOrEqualByFilterStringDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr > `1999-09-19`")
                .selectList();

        assertEquals(1, greaterThanByFilterStringDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr < `2022-07-11`")
                .selectList();

        assertEquals(1, lessThanByFilterStringDerived.size());

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.equalTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.notEqualTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.greaterOrEqualThan(DATE_2))
                .selectList();

        assertEquals(2, greaterOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.greaterThan(DATE_2))
                .selectList();

        assertEquals(1, greaterThanDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.lessThan(DATE_1))
                .selectList();

        assertEquals(1, lessThanDerived.size());

        // FilterByName

        equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedDateAttr", DateFilter.equalTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedDateAttr", DateFilter.notEqualTo(DATE_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedDateAttr", DateFilter.greaterOrEqualThan(DATE_2))
                .selectList();

        assertEquals(2, greaterOrEqualDerived.size());

        lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedDateAttr", DateFilter.lessOrEqualThan(DATE_1))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedDateAttr", DateFilter.greaterThan(DATE_2))
                .selectList();

        assertEquals(1, greaterThanDerived.size());

        lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedDateAttr", DateFilter.lessThan(DATE_1))
                .selectList();

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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testTimestampFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr == `2022-07-11T19:09:33`")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr != `2022-07-11T19:09:33`")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr >= `1999-09-19T09:09:09`")
                .selectList();

        assertEquals(2, greaterOrEqualByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr <= `2022-07-11T19:09:33`")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr > `1999-09-19T09:09:09`")
                .selectList();

        assertEquals(1, greaterThanByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr < `2022-07-11T19:09:33`")
                .selectList();

        assertEquals(1, lessThanByStringFilter.size());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .selectList();

        assertEquals(2, greaterOrEqual.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .selectList();

        assertEquals(1, greaterThan.size());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessThan(TIMESTAMP_1))
                .selectList();

        assertEquals(1, lessThan.size());

        // FilterByName

        equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timestampAttr", TimestampFilter.equalTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timestampAttr", TimestampFilter.notEqualTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timestampAttr", TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .selectList();

        assertEquals(2, greaterOrEqual.size());

        lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timestampAttr", TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timestampAttr", TimestampFilter.greaterThan(TIMESTAMP_2))
                .selectList();

        assertEquals(1, greaterThan.size());

        lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timestampAttr", TimestampFilter.lessThan(TIMESTAMP_1))
                .selectList();

        assertEquals(1, lessThan.size());

        // derived

        TransferMyEntityWithOptionalFields equalToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr == `2022-07-11T19:09:33`")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr != `2022-07-11T19:09:33`")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr >= `1999-09-19T09:09:09`")
                .selectList();

        assertEquals(2, greaterOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr <= `2022-07-11T19:09:33`")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr > `1999-09-19T09:09:09`")
                .selectList();

        assertEquals(1, greaterThanByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr < `2022-07-11T19:09:33`")
                .selectList();

        assertEquals(1, lessThanByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .selectList();

        assertEquals(2, greaterOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .selectList();

        assertEquals(1, greaterThanDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.lessThan(TIMESTAMP_1))
                .selectList();

        assertEquals(1, lessThanDerived.size());

        // FilterByName

        equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimestampAttr", TimestampFilter.equalTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimestampAttr", TimestampFilter.notEqualTo(TIMESTAMP_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimestampAttr", TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .selectList();

        assertEquals(2, greaterOrEqualDerived.size());

        lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimestampAttr", TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimestampAttr", TimestampFilter.greaterThan(TIMESTAMP_2))
                .selectList();

        assertEquals(1, greaterThanDerived.size());

        lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimestampAttr", TimestampFilter.lessThan(TIMESTAMP_1))
                .selectList();

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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testTimeFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr == `23:59:59`")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr != `23:59:59`")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr >= `12:34:56`")
                .selectList();

        assertEquals(2, greaterOrEqualByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr <= `23:59:59`")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilter.size());

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

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.equalTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterOrEqualThan(TIME_2))
                .selectList();

        assertEquals(2, greaterOrEqual.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.lessOrEqualThan(TIME_1))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterThan(TIME_2))
                .selectList();

        assertEquals(1, greaterThan.size());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.lessThan(TIME_1))
                .selectList();

        assertEquals(1, lessThan.size());

        // FilterByName

        equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timeAttr", TimeFilter.equalTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timeAttr", TimeFilter.notEqualTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timeAttr", TimeFilter.greaterOrEqualThan(TIME_2))
                .selectList();

        assertEquals(2, greaterOrEqual.size());

        lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timeAttr", TimeFilter.lessOrEqualThan(TIME_1))
                .selectList();

        assertEquals(2, lessOrEqual.size());

        greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timeAttr", TimeFilter.greaterThan(TIME_2))
                .selectList();

        assertEquals(1, greaterThan.size());

        lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("timeAttr", TimeFilter.lessThan(TIME_1))
                .selectList();

        assertEquals(1, lessThan.size());
        
        // derived
        
        TransferMyEntityWithOptionalFields equalToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr == `23:59:59`")
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr != `23:59:59`")
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr >= `12:34:56`")
                .selectList();

        assertEquals(2, greaterOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr <= `23:59:59`")
                .selectList();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr > `12:34:56`")
                .selectList();

        assertEquals(1, greaterThanByStringFilterDerived.size());
        assertEquals(transfer1.identifier(), greaterThanByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr < `23:59:59`")
                .selectList();

        assertEquals(1, lessThanByStringFilterDerived.size());
        assertEquals(transfer2.identifier(), lessThanByStringFilterDerived.get(0).identifier());

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.equalTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.greaterOrEqualThan(TIME_2))
                .selectList();

        assertEquals(2, greaterOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.lessOrEqualThan(TIME_1))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.greaterThan(TIME_2))
                .selectList();

        assertEquals(1, greaterThanDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.lessThan(TIME_1))
                .selectList();

        assertEquals(1, lessThanDerived.size());

        // FilterByName

        equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimeAttr", TimeFilter.equalTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimeAttr", TimeFilter.notEqualTo(TIME_1))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimeAttr", TimeFilter.greaterOrEqualThan(TIME_2))
                .selectList();

        assertEquals(2, greaterOrEqualDerived.size());

        lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimeAttr", TimeFilter.lessOrEqualThan(TIME_1))
                .selectList();

        assertEquals(2, lessOrEqualDerived.size());

        greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimeAttr", TimeFilter.greaterThan(TIME_2))
                .selectList();

        assertEquals(1, greaterThanDerived.size());

        lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedTimeAttr", TimeFilter.lessThan(TIME_1))
                .selectList();

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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testEnumFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        // Derived

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        // FilterByName

        equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("enumAttr", EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("enumAttr", EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        // Derived

        equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedEnumAttr", EnumerationFilter.equalTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByName("derivedEnumAttr", EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .selectList()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

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
                containsInAnyOrder(transfer1.identifier(),transfer2.identifier()));

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

}
