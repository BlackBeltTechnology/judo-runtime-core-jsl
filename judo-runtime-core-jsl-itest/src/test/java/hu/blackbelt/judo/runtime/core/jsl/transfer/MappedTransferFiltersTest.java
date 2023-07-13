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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.continent.Continent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercar.TransferCar;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercar.TransferCarAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercar.TransferCarDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercity.TransferCity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercity.TransferCityAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercity.TransferCityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercountry.TransferCountry;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfercountry.TransferCountryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transferfilterentity.TransferFilterEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transferfilterentity.TransferFilterEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfermyentitywithoptionalfields.TransferMyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transferperson.TransferPerson;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transferperson.TransferPersonAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transferperson.TransferPersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfertester.TransferTester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferfilter.mappedtransferfilter.transfertester.TransferTesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferFilterDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.sdk.query.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class MappedTransferFiltersTest extends AbstractJslTest {
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
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);

        transfer1 = transferMyEntityWithOptionalFieldsDao.create(TransferMyEntityWithOptionalFields.builder()
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

        transfer2 = transferMyEntityWithOptionalFieldsDao.create(TransferMyEntityWithOptionalFields.builder()
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

        transfer3 = transferMyEntityWithOptionalFieldsDao.create(TransferMyEntityWithOptionalFields.builder()
                .build());

        transferFilterEntity = transferFilterEntityDao.create(TransferFilterEntity.builder().build());
    }

    @Override
    public Module getModelDaoModule() {
        return new MappedTransferFilterDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferFilter";
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
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        List<TransferMyEntityWithOptionalFields> multiFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .filterByScaledAttr(NumberFilter.lessThan(INTEGER_2))
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute();

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
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr == 1")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr != 1")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilter.size());

        TransferMyEntityWithOptionalFields greaterByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr > 1")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greaterByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr <= 2")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        TransferMyEntityWithOptionalFields lessByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), lessByStringFilter.identifier());

        TransferMyEntityWithOptionalFields equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equals.identifier());

        TransferMyEntityWithOptionalFields notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEquals.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterOrEqualThan(INTEGER_1))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        TransferMyEntityWithOptionalFields greater = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterThan(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greater.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessOrEqualThan(INTEGER_2))
                .execute();

        assertEquals(2, lessOrEqual.size());

        TransferMyEntityWithOptionalFields less = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), less.identifier());

        // Zero element

        List<TransferMyEntityWithOptionalFields> zeroResult = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_1))
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute();

        assertTrue(zeroResult.isEmpty());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr > 1 and this.integerAttr < 2")
                .execute();

        assertTrue(filteredByZeroResultString.isEmpty());

        // TODO JNG-3980
        // filter for undefined

//        TransferMyEntityWithOptionalFields undefinedResult = transferMyEntityWithOptionalFieldsDao
//                .query()
//                .filterBy("this.integerAttr!isDefined()")
//                .execute()
//                .get(0);
//
//        assertEquals(transfer3.identifier(),
//                undefinedResult.identifier());

        // Derived attributes

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr > 1 and this.derivedIntegerAttr < 2")
                .execute();

        assertTrue(filteredByZeroResultStringDerived.isEmpty());

        TransferMyEntityWithOptionalFields equalsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr == 1")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr != 1")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr >= 1")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields greaterByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr > 1")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greaterByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr <= 2")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields lessByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedIntegerAttr < 2")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), lessByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.greaterOrEqualThan(INTEGER_1))
                .execute();

        assertEquals(2, greaterOrEqualsDerived.size());

        TransferMyEntityWithOptionalFields greaterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.greaterThan(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greaterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessOrEqualThan(INTEGER_2))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());


        TransferMyEntityWithOptionalFields lessDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), lessDerived.identifier());

        List<TransferMyEntityWithOptionalFields> zeroResultDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testScaledNumberFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();
        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1 and this.scaledAttr <= 2")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr == 1.23")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr != 1.23")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1.23")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilter.size());

        TransferMyEntityWithOptionalFields greaterByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr > 1.23")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greaterByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr <= 2.34")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        TransferMyEntityWithOptionalFields lessByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr < 2.34")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), lessByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr > 0 and this.scaledAttr < 1")
                .execute();

        assertTrue(filteredByZeroResultString.isEmpty());

        TransferMyEntityWithOptionalFields equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.equalTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equals.identifier());

        TransferMyEntityWithOptionalFields notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEquals.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterOrEqualThan(SCALED_1))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        TransferMyEntityWithOptionalFields greater = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterThan(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greater.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessOrEqualThan(SCALED_2))
                .execute();

        assertEquals(2, lessOrEqual.size());

        TransferMyEntityWithOptionalFields less = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessThan(SCALED_2))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), less.identifier());

        // Derived

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr >= 1 and this.derivedScaledAttr <= 2")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        List<TransferMyEntityWithOptionalFields> filteredByZeroResultStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr > 0 and this.derivedScaledAttr < 1")
                .execute();

        assertTrue(filteredByZeroResultStringDerived.isEmpty());

        TransferMyEntityWithOptionalFields equalsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr == 1.23")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr != 1.23")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr >= 1.23")
                .execute();

        assertEquals(2, greaterOrEqualsByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields greaterByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr > 1.23")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greaterByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr <= 2.34")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields lessByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedScaledAttr < 2.34")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), lessByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.equalTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.greaterOrEqualThan(SCALED_1))
                .execute();

        assertEquals(2, greaterOrEqualsDerived.size());

        TransferMyEntityWithOptionalFields greaterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.greaterThan(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), greaterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.lessOrEqualThan(SCALED_2))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        TransferMyEntityWithOptionalFields lessDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedScaledAttr(NumberFilter.lessThan(SCALED_2))
                .execute()
                .get(0);

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
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        // costume filter

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!matches('te.*')")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr == 'test'")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr != 'test'")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> iLikeByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .filterBy("this.stringAttr!ilike('TEST')")
                .execute();

        assertEquals(1, iLikeByStringFilter.size());
        assertEquals(transfer1.identifier(), iLikeByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> likeByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!ilike('test')")
                .execute();

        assertEquals(1, likeByStringFilter.size());
        assertEquals(transfer1.identifier(), likeByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr < 'test'")
                .execute();

        assertEquals(transfer2.identifier(), lessThanByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr > 'Another'")
                .execute();

        assertEquals(transfer1.identifier(), greaterThanByStringFilter.get(0).identifier());

        Long greaterOrEqualThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .filterBy("this.stringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilter);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr <= 'test'")
                .execute();

        assertEquals(2, lessOrEqualThanByStringFilter.size());

        TransferMyEntityWithOptionalFields equals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equals.identifier());

        TransferMyEntityWithOptionalFields notEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.notEqualTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEquals.identifier());

        List<TransferMyEntityWithOptionalFields> iLike = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .execute();

        assertEquals(1, iLike.size());
        assertEquals(transfer1.identifier(), iLike.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> like = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.like(STRING_1))
                .execute();

        assertEquals(1, like.size());
        assertEquals(transfer1.identifier(), like.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessThan(STRING_1))
                .execute();

        assertEquals(transfer2.identifier(), lessThan.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterThan(STRING_2))
                .execute();

        assertEquals(transfer1.identifier(), greaterThan.get(0).identifier());

        Long greaterOrEqualThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThan);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessOrEqualThan(STRING_1))
                .execute();

        assertEquals(2, lessOrEqualThan.size());

        // Derived

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!matches('te.*')")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        TransferMyEntityWithOptionalFields equalsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr == 'test'")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr != 'test'")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> iLikeByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .filterBy("this.derivedStringAttr!ilike('TEST')")
                .execute();

        assertEquals(1, iLikeByStringFilterDerived.size());
        assertEquals(transfer1.identifier(), iLikeByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> likeByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr!ilike('test')")
                .execute();

        assertEquals(1, likeByStringFilterDerived.size());
        assertEquals(transfer1.identifier(), likeByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr < 'test'")
                .execute();

        assertEquals(transfer2.identifier(), lessThanByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr > 'Another'")
                .execute();

        assertEquals(transfer1.identifier(), greaterThanByStringFilterDerived.get(0).identifier());

        Long greaterOrEqualThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .filterBy("this.derivedStringAttr >= 'Another'")
                .count();

        assertEquals(2, greaterOrEqualThanByStringFilterDerived);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedStringAttr <= 'test'")
                .execute();

        assertEquals(2, lessOrEqualThanByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields equalsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.equalTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalsDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.notEqualTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualsDerived.identifier());

        List<TransferMyEntityWithOptionalFields> iLikeDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .execute();

        assertEquals(1, iLikeDerived.size());
        assertEquals(transfer1.identifier(), iLikeDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> likeDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.like(STRING_1))
                .execute();

        assertEquals(1, likeDerived.size());
        assertEquals(transfer1.identifier(), likeDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.lessThan(STRING_1))
                .execute();

        assertEquals(transfer2.identifier(), lessThanDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.greaterThan(STRING_2))
                .execute();

        assertEquals(transfer1.identifier(), greaterThanDerived.get(0).identifier());

        Long greaterOrEqualThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .count();

        assertEquals(2, greaterOrEqualThanDerived);

        List<TransferMyEntityWithOptionalFields> lessOrEqualThanDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testBooleanFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields filteredByString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr == true")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByString.identifier());

        TransferMyEntityWithOptionalFields filteredByStringNotEquals = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr != true")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), filteredByStringNotEquals.identifier());

        TransferMyEntityWithOptionalFields filteredByStringOnlyAttr = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.boolAttr")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringOnlyAttr.identifier());

        TransferMyEntityWithOptionalFields isTrue = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isTrue())
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), isTrue.identifier());

        TransferMyEntityWithOptionalFields isFalse = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByBoolAttr(BooleanFilter.isFalse())
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), isFalse.identifier());

        // derived

        TransferMyEntityWithOptionalFields filteredByStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr == true")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringDerived.identifier());

        TransferMyEntityWithOptionalFields filteredByStringNotEqualsDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr != true")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), filteredByStringNotEqualsDerived.identifier());

        TransferMyEntityWithOptionalFields filteredByStringOnlyAttrDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedBoolAttr")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), filteredByStringOnlyAttrDerived.identifier());

        TransferMyEntityWithOptionalFields isTrueDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isTrue())
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), isTrueDerived.identifier());

        TransferMyEntityWithOptionalFields isFalseDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedBoolAttr(BooleanFilter.isFalse())
                .execute()
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
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalToByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr == `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByFilterString.identifier());

        TransferMyEntityWithOptionalFields notEqualToByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr != `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByFilterString.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr >= `1999-09-19`")
                .execute();

        assertEquals(2, greaterOrEqualByFilterString.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .filterBy("this.dateAttr <= `2022-07-11`")
                .execute();

        assertEquals(2, lessOrEqualByFilterString.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr > `1999-09-19`")
                .execute();

        assertEquals(1, greaterThanByFilterString.size());

        List<TransferMyEntityWithOptionalFields> lessThanByFilterString = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.dateAttr < `2022-07-11`")
                .execute();

        assertEquals(1, lessThanByFilterString.size());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.equalTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.notEqualTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.greaterOrEqualThan(DATE_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.greaterThan(DATE_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessThan(DATE_1))
                .execute();

        assertEquals(1, lessThan.size());

        // derived

        TransferMyEntityWithOptionalFields equalToByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr == `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByFilterStringDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr != `2022-07-11`")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByFilterStringDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr >= `1999-09-19`")
                .execute();

        assertEquals(2, greaterOrEqualByFilterStringDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .filterBy("this.derivedDateAttr <= `2022-07-11`")
                .execute();

        assertEquals(2, lessOrEqualByFilterStringDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr > `1999-09-19`")
                .execute();

        assertEquals(1, greaterThanByFilterStringDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanByFilterStringDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedDateAttr < `2022-07-11`")
                .execute();

        assertEquals(1, lessThanByFilterStringDerived.size());

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.equalTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.notEqualTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.greaterOrEqualThan(DATE_2))
                .execute();

        assertEquals(2, greaterOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedDateAttr(DateFilter.greaterThan(DATE_2))
                .execute();

        assertEquals(1, greaterThanDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testTimestampFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .filterBy("this.timestampAttr == `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr != `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr >= `1999-09-19T09:09:09`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .filterBy("this.timestampAttr <= `2022-07-11T19:09:33`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .filterBy("this.timestampAttr > `1999-09-19T09:09:09`")
                .execute();

        assertEquals(1, greaterThanByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timestampAttr < `2022-07-11T19:09:33`")
                .execute();

        assertEquals(1, lessThanByStringFilter.size());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessThan(TIMESTAMP_1))
                .execute();

        assertEquals(1, lessThan.size());

        // derived

        TransferMyEntityWithOptionalFields equalToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .filterBy("this.derivedTimestampAttr == `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr != `2022-07-11T19:09:33`")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr >= `1999-09-19T09:09:09`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .filterBy("this.derivedTimestampAttr <= `2022-07-11T19:09:33`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .filterBy("this.derivedTimestampAttr > `1999-09-19T09:09:09`")
                .execute();

        assertEquals(1, greaterThanByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimestampAttr < `2022-07-11T19:09:33`")
                .execute();

        assertEquals(1, lessThanByStringFilterDerived.size());

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .execute();

        assertEquals(2, greaterOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .execute();

        assertEquals(1, greaterThanDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testTimeFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr == `23:59:59`")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilter.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr != `23:59:59`")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilter.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr >= `12:34:56`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr <= `23:59:59`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilter.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr > `12:34:56`")
                .execute();

        assertEquals(1, greaterThanByStringFilter.size());
        assertEquals(transfer1.identifier(), greaterThanByStringFilter.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilter = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.timeAttr < `23:59:59`")
                .execute();

        assertEquals(1, lessThanByStringFilter.size());
        assertEquals(transfer2.identifier(), lessThanByStringFilter.get(0).identifier());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.equalTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterOrEqualThan(TIME_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqual = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.lessOrEqualThan(TIME_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<TransferMyEntityWithOptionalFields> greaterThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterThan(TIME_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<TransferMyEntityWithOptionalFields> lessThan = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByTimeAttr(TimeFilter.lessThan(TIME_1))
                .execute();

        assertEquals(1, lessThan.size());

        TransferMyEntityWithOptionalFields equalToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr == `23:59:59`")
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToByStringFilterDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr != `23:59:59`")
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToByStringFilterDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr >= `12:34:56`")
                .execute();

        assertEquals(2, greaterOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr <= `23:59:59`")
                .execute();

        assertEquals(2, lessOrEqualByStringFilterDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr > `12:34:56`")
                .execute();

        assertEquals(1, greaterThanByStringFilterDerived.size());
        assertEquals(transfer1.identifier(), greaterThanByStringFilterDerived.get(0).identifier());

        List<TransferMyEntityWithOptionalFields> lessThanByStringFilterDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.derivedTimeAttr < `23:59:59`")
                .execute();

        assertEquals(1, lessThanByStringFilterDerived.size());
        assertEquals(transfer2.identifier(), lessThanByStringFilterDerived.get(0).identifier());

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.equalTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualToDerived.identifier());

        List<TransferMyEntityWithOptionalFields> greaterOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.greaterOrEqualThan(TIME_2))
                .execute();

        assertEquals(2, greaterOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> lessOrEqualDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.lessOrEqualThan(TIME_1))
                .execute();

        assertEquals(2, lessOrEqualDerived.size());

        List<TransferMyEntityWithOptionalFields> greaterThanDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedTimeAttr(TimeFilter.greaterThan(TIME_2))
                .execute();

        assertEquals(1, greaterThanDerived.size());

        List<TransferMyEntityWithOptionalFields> lessThanDerived = transferMyEntityWithOptionalFieldsDao
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
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    public void testEnumFilterOnTransfer() {
        List<TransferMyEntityWithOptionalFields> list = transferMyEntityWithOptionalFieldsDao.query().execute();

        assertEquals(3, list.size());

        TransferMyEntityWithOptionalFields equalTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalTo.identifier());

        TransferMyEntityWithOptionalFields notEqualTo = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(transfer2.identifier(), notEqualTo.identifier());

        // Derived

        TransferMyEntityWithOptionalFields equalToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.equalTo(MyEnum.Bombastic))
                .execute()
                .get(0);

        assertEquals(transfer1.identifier(), equalToDerived.identifier());

        TransferMyEntityWithOptionalFields notEqualToDerived = transferMyEntityWithOptionalFieldsDao
                .query()
                .filterByDerivedEnumAttr(EnumerationFilter.notEqualTo(MyEnum.Bombastic))
                .execute()
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
        assertThat(transferFilterEntityDao.queryHaveTOnTheStringAttr(transferFilterEntity).execute().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(transfer1.identifier(),transfer2.identifier()));

        assertEquals(1, transferFilterEntityDao.queryTestIsTheStringAttr(transferFilterEntity).count());
        assertThat(transferFilterEntityDao.queryTestIsTheStringAttr(transferFilterEntity).execute().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(transfer1.identifier()));

        assertEquals(0, transferFilterEntityDao.queryHaveNoMatchOnTheStringAttr(transferFilterEntity).count());

        assertEquals(1, transferFilterEntityDao.queryHaveUndefinedOnTheStringAttr(transferFilterEntity).count());
        assertThat(transferFilterEntityDao.queryHaveUndefinedOnTheStringAttr(transferFilterEntity).execute().stream().map(e -> e.identifier()).toList(),
                containsInAnyOrder(transfer3.identifier()));

        assertEquals(2, transferFilterEntityDao.queryHaveDefinedOnTheStringAttr(transferFilterEntity).count());
        assertThat(transferFilterEntityDao.queryHaveDefinedOnTheStringAttr(transferFilterEntity).execute().stream().map(e -> e.identifier()).toList(),
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

        TransferCountry tcountry1 = transferCountryDao.create(TransferCountry.builder().withName("Hungary").withContinent(Continent.Europe).build());
        TransferCity tcity1 = transferCityDao.create(TransferCity.builder().withName("Budapest").build(), TransferCityAttachedRelationsForCreate.builder().withCountry(tcountry1).build());
        TransferCity tcity2 = transferCityDao.create(TransferCity.builder().withName("Debrecen").build(), TransferCityAttachedRelationsForCreate.builder().withCountry(tcountry1).build());
        TransferPerson tp1 = transferPersonDao.create(TransferPerson.builder().withName("Gibpsz Jakab").build(), TransferPersonAttachedRelationsForCreate.builder().withCity(tcity1).build());
        TransferPerson tp2 = transferPersonDao.create(TransferPerson.builder().withName("Teszt Elek").build(), TransferPersonAttachedRelationsForCreate.builder().withCity(tcity2).build());
        TransferCar tc1 = transferCarDao.create(TransferCar.builder().withLicensePlate("ABC-123").build(), TransferCarAttachedRelationsForCreate.builder().withOwner(tp1).build());
        TransferCar tc2 = transferCarDao.create(TransferCar.builder().withLicensePlate("ABC-124").build(), TransferCarAttachedRelationsForCreate.builder().withOwner(tp2).build());

        TransferTester tester = transferTesterDao.create(TransferTester.builder().build());

        assertEquals(1, transferTesterDao.countCarsOfTesztElek(tester));
        assertThat(transferTesterDao.queryCarsOfTesztElek(tester).execute(), containsInAnyOrder(tc2));

        assertEquals(1, transferTesterDao.countCarsInBudapest(tester));
        assertThat(transferTesterDao.queryCarsInBudapest(tester).execute(), containsInAnyOrder(tc1));

        assertEquals(2, transferTesterDao.countCarsInHungary(tester));
        assertThat(transferTesterDao.queryCarsInHungary(tester).execute(), containsInAnyOrder(tc2, tc1));

        assertEquals(2, transferTesterDao.countCarsOfKnownContinents(tester));
        assertThat(transferTesterDao.queryCarsInHungary(tester).execute(), containsInAnyOrder(tc1, tc2));

    }

}
