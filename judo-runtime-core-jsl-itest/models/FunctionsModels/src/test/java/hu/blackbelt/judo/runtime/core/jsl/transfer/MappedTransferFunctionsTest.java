package hu.blackbelt.judo.runtime.core.jsl.transfer;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
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

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferanytypefunctions.TransferAnyTypeFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferanytypefunctions.TransferAnyTypeFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferbooleanfunctions.TransferBooleanFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferbooleanfunctions.TransferBooleanFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferbooler.TransferBooler;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferbooler.TransferBoolerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferboolertester.TransferBoolerTester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferboolertester.TransferBoolerTesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferchild.TransferChild;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferchild.TransferChildDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfercollectionfunctions.TransferCollectionFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfercollectionfunctions.TransferCollectionFunctionsAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfercollectionfunctions.TransferCollectionFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferdatefunctions.TransferDateFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferdatefunctions.TransferDateFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferentity.TransferEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferentity.TransferEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferentitywithprimitivedefaults.TransferEntityWithPrimitiveDefaults;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferentitywithprimitivedefaults.TransferEntityWithPrimitiveDefaultsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferenumfunctions.TransferEnumFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferenumfunctions.TransferEnumFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferinstancefunctions.TransferInstanceFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferinstancefunctions.TransferInstanceFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferkleene.TransferKleene;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferkleene.TransferKleeneDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfermember.TransferMember;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfermember.TransferMemberDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfernumericfunctions.TransferNumericFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfernumericfunctions.TransferNumericFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferparent.TransferParent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferparent.TransferParentDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferparent.TransferParentIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfersimple.TransferSimple;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfersimple.TransferSimpleDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferstringfunctions.TransferStringFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transferstringfunctions.TransferStringFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertester.TransferTester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertester.TransferTesterAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertester.TransferTesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertimefunctions.TransferTimeFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertimefunctions.TransferTimeFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertimestampasstring.TransferTimestampAsString;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertimestampasstring.TransferTimestampAsStringDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertimestampfunctions.TransferTimestampFunctions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.functions.functions.transfertimestampfunctions.TransferTimestampFunctionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FunctionsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslWithOneInjectionTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;
import java.util.Optional;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferFunctionsTest extends AbstractJslWithOneInjectionTest {
    @Inject
    TransferEntityDao transferEntityDao;

    @Inject
    TransferEntityWithPrimitiveDefaultsDao transferEntityWithPrimitiveDefaultsDao;

    @Inject
    TransferAnyTypeFunctionsDao transferAnyTypeFunctionsDao;

    TransferAnyTypeFunctions transferAnyTypeFunctions;

    @Inject
    TransferStringFunctionsDao transferStringFunctionsDao;

    @Inject
    TransferNumericFunctionsDao transferNumericFunctionsDao;

    @Inject
    TransferBooleanFunctionsDao transferBooleanFunctionsDao;

    @Inject
    TransferDateFunctionsDao transferDateFunctionsDao;

    @Inject
    TransferTimeFunctionsDao transferTimeFunctionsDao;

    @Inject
    TransferTimestampFunctionsDao transferTimestampFunctionsDao;

    @Inject
    TransferTimestampAsStringDao transferTimestampAsStringDao;

    @Inject
    TransferEnumFunctionsDao transferEnumFunctionsDao;

    @Inject
    TransferInstanceFunctionsDao transferInstanceFunctionsDao;

    @Inject
    TransferCollectionFunctionsDao transferCollectionFunctionsDao;

    @Inject
    TransferParentDao transferParentDao;

    @Inject
    TransferChildDao transferChildDao;

    @Inject
    TransferKleeneDao transferKleeneDao;

    @Inject
    TransferBoolerDao transferBoolerDao;

    @Inject
    TransferBoolerTesterDao transferBoolerTesterDao;

    @Inject
    TransferMemberDao transferMemberDao;

    @Inject
    TransferTesterDao transferTesterDao;

    @Inject
    TransferSimpleDao transferSimpleDao;

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) {
        super.init(datasource);
        TransferEntity tentity = transferEntityDao
                        .create(TransferEntity.builder().build());
        TransferEntityWithPrimitiveDefaults entityWithPrimitiveDefaults = transferEntityWithPrimitiveDefaultsDao
                        .create(TransferEntityWithPrimitiveDefaults.builder().build());

        transferAnyTypeFunctions = transferAnyTypeFunctionsDao.create(TransferAnyTypeFunctions.builder()
                        .withEntity(tentity)
                        .withEntityWithPrimitives(entityWithPrimitiveDefaults)
                        .build());
    }

    @Override
    public Module getModelDaoModule() {
        return  new FunctionsDaoModules();
    }

    @Override
    public String getModelName() {
        return "Functions";
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-003",
            "REQ-EXPR-010",
            "REQ-SRV-002"

    })
    public void testAnyTypeOnMappedTransfer() {
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getIntegerIsDefinedFalse());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getIntegerIsDefinedTrue());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getIntegerIsUndefinedTrue());
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getIntegerIsUndefinedFalse());

        assertEquals(Optional.of(false), transferAnyTypeFunctions.getScaledIsDefinedFalse());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getScaledIsDefinedTrue());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getScaledIsUndefinedTrue());
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getScaledIsUndefinedFalse());

        assertEquals(Optional.of(false), transferAnyTypeFunctions.getStringIsDefinedFalse());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getStringIsDefinedTrue());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getStringIsUndefinedTrue());
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getStringIsUndefinedFalse());

        assertEquals(Optional.of(false), transferAnyTypeFunctions.getBoolIsDefinedFalse());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getBoolIsDefinedTrue());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getBoolIsUndefinedTrue());
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getBoolIsUndefinedFalse());

        assertEquals(Optional.of(false), transferAnyTypeFunctions.getDateIsDefinedFalse());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getDateIsDefinedTrue());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getDateIsUndefinedTrue());
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getDateIsUndefinedFalse());

        assertEquals(Optional.of(false), transferAnyTypeFunctions.getTimestampIsDefinedFalse());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getTimestampIsDefinedTrue());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getTimestampIsUndefinedTrue());
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getTimestampIsUndefinedFalse());

        assertEquals(Optional.of(false), transferAnyTypeFunctions.getTimeIsDefinedFalse());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getTimeIsDefinedTrue());
        assertEquals(Optional.of(true), transferAnyTypeFunctions.getTimeIsUndefinedTrue());
        assertEquals(Optional.of(false), transferAnyTypeFunctions.getTimeIsUndefinedFalse());

        assertEquals(Optional.of("apple"), transferAnyTypeFunctions.getStringOrElse());
        assertEquals(2, transferAnyTypeFunctions.getNumberOrElse().get());
        assertEquals(3, transferAnyTypeFunctions.getNumberOrElse2().get());
        assertEquals(4, transferAnyTypeFunctions.getNumberOrElse3().get());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-010",
            "REQ-EXPR-013",
            "REQ-EXPR-014",
            "REQ-SRV-002"
    })
    public void testStringsOnMappedTransfer() {
        TransferStringFunctions str = transferStringFunctionsDao.create(TransferStringFunctions.builder().build());

        assertEquals(5, str.getLength().orElseThrow());
        String first = str.getFirst().orElseThrow();
        assertEquals("ap", first);
        String last = str.getLast().orElseThrow();
        assertEquals("e", last);
        Long position = str.getPosition().orElseThrow();
        assertEquals(2, position);
        String substring = str.getSubstring().orElseThrow();
        assertEquals("ppl", substring);
        String lower = str.getLower().orElseThrow();
        assertEquals("apple", lower);
        String lowerCase = str.getLowerCase().orElseThrow();
        assertEquals("apple", lowerCase);
        String upper = str.getUpper().orElseThrow();
        assertEquals("APPLE", upper);
        String upperCase = str.getUpperCase().orElseThrow();
        assertEquals("APPLE", upperCase);
        String capitalize = str.getCapitalize().orElseThrow();
        assertEquals("Apple", capitalize);
        Boolean matches = str.getMatches().orElseThrow();
        assertTrue(matches);

        assertTrue(str.getLike().orElseThrow());
        assertFalse(str.getLikeFalse().orElseThrow());
        assertTrue(str.getLikeOnUndefined().isEmpty());
        assertTrue(str.getIlike().orElseThrow());
        assertFalse(str.getIlikeFalse().orElseThrow());
        assertTrue(str.getIlikeOnUndefined().isEmpty());

        String replace = str.getReplace().orElseThrow();
        assertEquals("appendix", replace);
        String trim = str.getTrim().orElseThrow();
        assertEquals("apple", trim);
        String ltrim = str.getLtrim().orElseThrow();
        assertEquals("apple ", ltrim);
        String rtrim = str.getRtrim().orElseThrow();
        assertEquals(" apple", rtrim);

        assertEquals("     apple", str.getLpad().orElseThrow());
        assertTrue(str.getLpadTrue().orElseThrow());
        assertFalse(str.getLpadFalse().orElseThrow());
        assertEquals("*****apple", str.getLpad1().orElseThrow());
        assertEquals("ap", str.getLpad2().orElseThrow());
        assertEquals("ap", str.getLpad3().orElseThrow());
        assertEquals("apple     ", str.getRpad().orElseThrow());
        assertEquals("apple*****", str.getRpad1().orElseThrow());
        assertEquals("ap", str.getRpad2().orElseThrow());
        assertEquals("ap", str.getRpad3().orElseThrow());

        assertTrue(str.getLpadUndefined().isEmpty());
        assertFalse(str.getLpadUndefinedFalse().orElseThrow());
        assertTrue(str.getLpadUndefinedTrue().orElseThrow());
        assertTrue(str.getLpadUndefined1().isEmpty());
        assertTrue(str.getRpadUndefined().isEmpty());
        assertTrue(str.getRpadUndefined1().isEmpty());

        String parenthizedAsString = str.getParenthizedAsString().orElseThrow();
        assertEquals("26", parenthizedAsString);
    }


    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-014",
            "REQ-SRV-002"
    })
    public void testNumericsOnMappedTransfer() {
        TransferNumericFunctions numericFunctions = transferNumericFunctionsDao.create(TransferNumericFunctions.builder().build());

        Long roundInt = numericFunctions.getRoundInt().orElseThrow();
        assertEquals(1, roundInt);
        Long roundInt2 = numericFunctions.getRoundInt2().orElseThrow();
        assertEquals(1, roundInt2);
        Long roundScaled1 = numericFunctions.getRoundScaled1().orElseThrow();
        assertEquals(1, roundScaled1);
        Long roundScaled2 = numericFunctions.getRoundScaled2().orElseThrow();
        assertEquals(8, roundScaled2);
        Long roundScaled3 = numericFunctions.getRoundScaled3().orElseThrow();
        assertEquals(3, roundScaled3);
        Long roundScaledNegative1 = numericFunctions.getRoundScaledNegative1().orElseThrow();
        assertEquals(-2, roundScaledNegative1);
        Long roundScaledNegative2 = numericFunctions.getRoundScaledNegative2().orElseThrow();
        assertEquals(-1, roundScaledNegative2);
        Long roundScaledNegative3 = numericFunctions.getRoundScaledNegative3().orElseThrow();
        assertEquals(-8, roundScaledNegative3);

        assertEquals(1.0, numericFunctions.getRoundIntScaled().orElseThrow());
        assertEquals(1.0, numericFunctions.getRoundInt2Scaled().orElseThrow());
        assertEquals(1.2, numericFunctions.getRoundScaled1Scaled().orElseThrow());
        assertEquals(7.9, numericFunctions.getRoundScaled2Scaled().orElseThrow());
        assertEquals(2.5, numericFunctions.getRoundScaled3Scaled().orElseThrow());
        assertEquals(-2.5, numericFunctions.getRoundScaledNegative1Scaled().orElseThrow());
        assertEquals(-1.2, numericFunctions.getRoundScaledNegative2Scaled().orElseThrow());
        assertEquals(-7.9, numericFunctions.getRoundScaledNegative3Scaled().orElseThrow());

        Long floorInt = numericFunctions.getFloorInt().orElseThrow();
        assertEquals(1, floorInt);
        Long floorScaled1 = numericFunctions.getFloorScaled1().orElseThrow();
        assertEquals(2, floorScaled1);
        Long floorScaled2 = numericFunctions.getFloorScaled2().orElseThrow();
        assertEquals(-3, floorScaled2);

        Long ceilInt = numericFunctions.getCeilInt().orElseThrow();
        assertEquals(1, ceilInt);
        Long ceilScaled1 = numericFunctions.getCeilScaled1().orElseThrow();
        assertEquals(3, ceilScaled1);
        Long ceilScaled2 = numericFunctions.getCeilScaled2().orElseThrow();
        assertEquals(-2, ceilScaled2);

        Long absInt = numericFunctions.getAbsInt().orElseThrow();
        assertEquals(1, absInt);
        Long absInt2 = numericFunctions.getAbsInt2().orElseThrow();
        assertEquals(3, absInt2);
        Double absScaled1 = numericFunctions.getAbsScaled1().orElseThrow();
        assertEquals(2.9, absScaled1);
        Double absScaled2 = numericFunctions.getAbsScaled2().orElseThrow();
        assertEquals(2.9, absScaled2);

        String intAsString = numericFunctions.getIntAsString().orElseThrow();
        assertEquals("1", intAsString);
        String scaledAsString = numericFunctions.getScaledAsString().orElseThrow();
        assertEquals("123456.789", scaledAsString);
    }

    @Test
    @Requirement(reqs = {
            "REQ-EXPR-015",
            "REQ-SRV-002"
    })
    public void testBooleansOnMappedTransfer() {
        TransferBooleanFunctions bool = transferBooleanFunctionsDao.create(TransferBooleanFunctions.builder().build());

        assertEquals(Optional.of("true"), bool.getOwnBoolAsString());
        assertEquals(Optional.of("true"), bool.getTrueAsString());
        assertEquals(Optional.of("false"), bool.getFalseAsString());
        assertEquals(Optional.of("true"), bool.getLogicalExpressionAsString());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-006",
            "REQ-SRV-002"
    })
    public void testKleeneOnMappedTransfer() {
        TransferKleene kleene = transferKleeneDao.create(TransferKleene.builder().build());

        /*
        |=============================================================================================
        |`p`            |`q`            |`p or q`       |`p and q`      |`p xor q`      |`p implies q`
        |`true`            |`true`            |`true`         |`true`            |`false`        |`true`
        |`true`            |`false`        |`true`         |`false`        |`true`         |`false`
        |`true`            |`undefined`    |`true`            |`undefined`    |`undefined`    |`undefined`
        |`false`        |`true`            |`true`            |`false`        |`true`         |`true`
        |`false`        |`false`        |`false`        |`false`        |`false`        |`true`
        |`false`        |`undefined`    |`undefined`    |`false`        |`undefined`    |`true`
        |`undefined`    |`true`            |`true`            |`undefined`    |`undefined`    |`true`
        |`undefined`    |`false`        |`undefined`    |`false`        |`undefined`    |`undefined`
        |`undefined`    |`undefined`    |`undefined`    |`undefined`    |`undefined`    |`undefined`
        |=============================================================================================
            */

           assertEquals(Optional.of(true), kleene.getTrueOrTrue());
           assertEquals(Optional.of(true), kleene.getTrueOrFalse());
           assertEquals(Optional.of(true), kleene.getTrueOrUndefined());
           assertEquals(Optional.of(true), kleene.getFalseOrTrue());
           assertEquals(Optional.of(false), kleene.getFalseOrFalse());
           assertEquals(Optional.empty(), kleene.getFalseOrUndefined());
           assertEquals(Optional.of(true), kleene.getUndefinedOrTrue());
           assertEquals(Optional.empty(), kleene.getUndefinedOrFalse());
           assertEquals(Optional.empty(), kleene.getUndefinedOrUndefined());

           assertEquals(Optional.of(true), kleene.getTrueAndTrue());
           assertEquals(Optional.of(false), kleene.getTrueAndFalse());
           assertEquals(Optional.empty(), kleene.getTrueAndUndefined());
           assertEquals(Optional.of(false), kleene.getFalseAndTrue());
           assertEquals(Optional.of(false), kleene.getFalseAndFalse());
           assertEquals(Optional.of(false), kleene.getFalseAndUndefined());
           assertEquals(Optional.empty(), kleene.getUndefinedAndTrue());
           assertEquals(Optional.of(false), kleene.getUndefinedAndFalse());
           assertEquals(Optional.empty(), kleene.getUndefinedAndUndefined());

           assertEquals(Optional.of(false), kleene.getTrueXorTrue());
           assertEquals(Optional.of(true), kleene.getTrueXorFalse());
           assertEquals(Optional.empty(), kleene.getTrueXorUndefined());
           assertEquals(Optional.of(true), kleene.getFalseXorTrue());
           assertEquals(Optional.of(false), kleene.getFalseXorFalse());
           assertEquals(Optional.empty(), kleene.getFalseXorUndefined());
           assertEquals(Optional.empty(), kleene.getUndefinedXorTrue());
           assertEquals(Optional.empty(), kleene.getUndefinedXorFalse());
           assertEquals(Optional.empty(), kleene.getUndefinedXorUndefined());

           assertEquals(Optional.of(true), kleene.getTrueImpliesTrue());
           assertEquals(Optional.of(false), kleene.getTrueImpliesFalse());
           assertEquals(Optional.empty(), kleene.getTrueImpliesUndefined());
           assertEquals(Optional.of(true), kleene.getFalseImpliesTrue());
           assertEquals(Optional.of(true), kleene.getFalseImpliesFalse());
           assertEquals(Optional.of(true), kleene.getFalseImpliesUndefined());
           assertEquals(Optional.of(true), kleene.getUndefinedImpliesTrue());
           assertEquals(Optional.empty(), kleene.getUndefinedImpliesFalse());
           assertEquals(Optional.empty(), kleene.getUndefinedImpliesUndefined());
    }

    @Test
    @Requirement(reqs = {
            "REQ-EXPR-010",
            "REQ-EXPR-016",
            "REQ-SRV-002"
    })
    public void testDatesOnMappedTransfer() {
        TransferDateFunctions date = transferDateFunctionsDao.create(TransferDateFunctions.builder().build());

        assertEquals(Optional.of("2022-07-11"), date.getOwnDateAsString());
        assertEquals(Optional.of("2021-03-02"), date.getDateAsString());
        assertEquals(Optional.of(2021L), date.getYear());
        assertEquals(Optional.of(3L), date.getMonth());
        assertEquals(Optional.of(2L), date.getDay());

        assertEquals(LocalDate.of(2011, 1, 28), date.getOf().orElseThrow());

        assertEquals(280L, date.getDayOfYear().orElseThrow());
        assertEquals(1L, date.getDayOfWeekMonday().orElseThrow());
        assertEquals(2L, date.getDayOfWeekTuesday().orElseThrow());
        assertEquals(3L, date.getDayOfWeekWednesday().orElseThrow());
        assertEquals(4L, date.getDayOfWeekThursday().orElseThrow());
        assertEquals(5L, date.getDayOfWeekFriday().orElseThrow());
        assertEquals(6L, date.getDayOfWeekSaturday().orElseThrow());
        assertEquals(7L, date.getDayOfWeekSunday().orElseThrow());

        assertTrue(date.getDayOfYearUndefined().isEmpty());
        assertTrue(date.getDayOfWeekUndefined().isEmpty());
    }

    @Test
    @Requirement(reqs = {
            "REQ-EXPR-010",
            "REQ-EXPR-017",
            "REQ-SRV-002"
    })
    public void testTimeOnMappedTransfer() {
        LocalTime timeHM = LocalTime.parse("12:34");
        LocalTime timeHMS = LocalTime.parse("12:34:56");
        LocalTime timeHMSF = LocalTime.parse("12:34:56.789");
        TransferTimeFunctions time =
                transferTimeFunctionsDao.create(TransferTimeFunctions.builder()
                                                     .withTimeHM(timeHM)
                                                     .withTimeHMS(timeHMS)
                                                     .withTimeHMSF(timeHMSF)
                                                     .withNumber(timeHMSF.toNanoOfDay() / 1_000_000)
                                                     .build());

        assertEquals(timeHM, time.getTimeHM().orElseThrow());
        assertEquals(timeHM, time.getTimeHM1().orElseThrow());
        assertEquals(timeHMS, time.getTimeHMS().orElseThrow());
        assertEquals(timeHMS, time.getTimeHMS1().orElseThrow());
        assertEquals(timeHMSF, time.getTimeHMSF().orElseThrow());
        assertEquals(timeHMSF, time.getTimeHMSF1().orElseThrow());
        assertEquals(timeHMSF.withSecond(0), time.getTimeHMSF2().orElseThrow());

        assertEquals("12:34", time.getSelfTimeHMAsString().orElseThrow());
        assertEquals(12, time.getSelfTimeHMHour().orElseThrow());
        assertEquals(34, time.getSelfTimeHMMinute().orElseThrow());
        assertEquals(0, time.getSelfTimeHMSecond().orElseThrow());
        assertEquals(0, time.getSelfTimeHMMillisecond().orElseThrow());
        assertEquals(timeHM.toNanoOfDay() / 1_000_000, time.getSelfTimeHMAsMillisecond().orElseThrow());

        assertEquals("12:34:56", time.getSelfTimeHMSAsString().orElseThrow());
        assertEquals(12, time.getSelfTimeHMSHour().orElseThrow());
        assertEquals(34, time.getSelfTimeHMSMinute().orElseThrow());
        assertEquals(56, time.getSelfTimeHMSSecond().orElseThrow());
        assertEquals(0, time.getSelfTimeHMSMillisecond().orElseThrow());
        assertEquals(timeHMS.toNanoOfDay() / 1_000_000, time.getSelfTimeHMSAsMillisecond().orElseThrow());

        assertEquals("12:34:56.789", time.getSelfTimeHMSFAsString().orElseThrow());
        assertEquals(12, time.getSelfTimeHMSFHour().orElseThrow());
        assertEquals(34, time.getSelfTimeHMSFMinute().orElseThrow());
        assertEquals(56, time.getSelfTimeHMSFSecond().orElseThrow());
        assertEquals(789, time.getSelfTimeHMSFMillisecond().orElseThrow());
        assertEquals(timeHMSF.toNanoOfDay() / 1_000_000, time.getSelfTimeHMSFAsMillisecond().orElseThrow());

        assertTrue(time.getSelfUndefinedTimeAsString().isEmpty());
        assertTrue(time.getSelfUndefinedTimeHour().isEmpty());
        assertTrue(time.getSelfUndefinedTimeMinute().isEmpty());
        assertTrue(time.getSelfUndefinedTimeSecond().isEmpty());
        assertTrue(time.getSelfUndefinedTimeMillisecond().isEmpty());
        assertTrue(time.getSelfUndefinedTimeAsMillisecond().isEmpty());

        assertEquals("12:34", time.getTimeConstantHMAsString().orElseThrow());
        assertEquals(12, time.getTimeConstantHMHour().orElseThrow());
        assertEquals(34, time.getTimeConstantHMMinute().orElseThrow());
        assertEquals(0, time.getTimeConstantHMSecond().orElseThrow());
        assertEquals(0, time.getTimeConstantHMMillisecond().orElseThrow());
        assertEquals(timeHM.toNanoOfDay() / 1_000_000, time.getTimeConstantHMAsMillisecond().orElseThrow());

        assertEquals("12:34:56", time.getTimeConstantHMSAsString().orElseThrow());
        assertEquals(12, time.getTimeConstantHMSHour().orElseThrow());
        assertEquals(34, time.getTimeConstantHMSMinute().orElseThrow());
        assertEquals(56, time.getTimeConstantHMSSecond().orElseThrow());
        assertEquals(0, time.getTimeConstantHMSMillisecond().orElseThrow());
        assertEquals(timeHMS.toNanoOfDay() / 1_000_000, time.getTimeConstantHMSAsMillisecond().orElseThrow());

        assertEquals("12:34:56.789", time.getTimeConstantHMSFAsString().orElseThrow());
        assertEquals(12, time.getTimeConstantHMSFHour().orElseThrow());
        assertEquals(34, time.getTimeConstantHMSFMinute().orElseThrow());
        assertEquals(56, time.getTimeConstantHMSFSecond().orElseThrow());
        assertEquals(789, time.getTimeConstantHMSFMillisecond().orElseThrow());
        assertEquals(timeHMSF.toNanoOfDay() / 1_000_000, time.getTimeConstantHMSFAsMillisecond().orElseThrow());

        assertTrue(time.getTimeFromMillisecondOfUndefined().isEmpty());
        assertEquals(timeHMSF, time.getTimeFromMillisecondOfSelfNumber().orElseThrow());
        assertEquals(LocalTime.ofNanoOfDay(999_999_000_000L), time.getTimeFromMillisecondOfConstant().orElseThrow());
    }

    @Test
    @TestCase("TC023")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-EXPR-010",
            "REQ-EXPR-018",
            "REQ-SRV-002"
    })
    public void testTimestampOnMappedTransfer() {
        TransferTimestampFunctions timestampFunctions = transferTimestampFunctionsDao.create(TransferTimestampFunctions.builder().build());

        assertEquals(LocalDateTime.of(2022, 9, 29, 0, 0, 0, 0), timestampFunctions.getTimestampOfDate().orElseThrow());
        assertEquals(LocalDateTime.of(2022, 9, 29, 11, 11, 11, 0), timestampFunctions.getTimestampOfDateAndTime().orElseThrow());

        assertEquals(LocalDate.of(2022, 9, 29), timestampFunctions.getDateOfTimestampWithDate().orElseThrow());
        assertEquals(LocalDate.of(2022, 9, 29), timestampFunctions.getDateOfTimestampWithDateAndTime().orElseThrow());
        assertEquals(LocalDate.of(2019, 7, 18), timestampFunctions.getDateOfTimestampSelf().orElseThrow());
        assertEquals(LocalTime.of(0, 0, 0), timestampFunctions.getTimeOfTimestampWithDate().orElseThrow());
        assertEquals(LocalTime.of(11, 11, 11), timestampFunctions.getTimeOfTimestampWithDateAndTime().orElseThrow());
        assertEquals(LocalTime.of(1, 11, 12), timestampFunctions.getTimeOfTimestampSelf().orElseThrow());

        assertEquals(1664409600000L, timestampFunctions.getAsMilliseconds().orElseThrow());
        assertEquals(1664449871000L, timestampFunctions.getAsMilliseconds2().orElseThrow());

        assertEquals(Instant.ofEpochMilli(1664449871000L).atOffset(ZoneOffset.UTC).toLocalDateTime(), timestampFunctions.getFromMilliseconds().orElseThrow());

        assertEquals(LocalDateTime.of(2023, 10, 30, 12, 12, 12, 1000000), timestampFunctions.getPlusAll().orElseThrow());
        assertEquals(LocalDateTime.of(2023, 10, 30, 11, 11, 11, 0), timestampFunctions.getPlusDate().orElseThrow());

        assertEquals(LocalDateTime.of(2023, 10, 30, 12, 12, 12, 1000000), timestampFunctions.getPlusAllReversed().orElseThrow());
        assertEquals(LocalDateTime.of(2023, 10, 30, 11, 11, 11, 0), timestampFunctions.getPlusDateReversed().orElseThrow());

        assertEquals(LocalDateTime.of(2022, 9, 29, 11, 11, 11, 1000000), timestampFunctions.getPlusMilliseconds().orElseThrow());

        assertEquals(LocalDateTime.of(2021, 8, 28, 10, 10, 9, 999000000), timestampFunctions.getMinusAll().orElseThrow());
        assertEquals(LocalDateTime.of(2021, 8, 28, 11, 11, 11, 0), timestampFunctions.getMinusDate().orElseThrow());

        assertEquals(LocalDateTime.of(2021, 8, 28, 10, 10, 9, 999000000), timestampFunctions.getMinusAllReversed().orElseThrow());
        assertEquals(LocalDateTime.of(2021, 8, 28, 11, 11, 11, 0), timestampFunctions.getMinusDateReversed().orElseThrow());

        assertEquals(LocalDateTime.of(2022, 9, 29, 11, 11, 10, 999000000), timestampFunctions.getMinusMilliseconds().orElseThrow());
    }

    @Test
    @TestCase("TC023")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-EXPR-010",
            "REQ-EXPR-018",
            "REQ-SRV-002"
    })
    public void testTimestampAsStringOnMappedTransfer() {
        TransferTimestampAsString timestampAsString = transferTimestampAsStringDao.create(TransferTimestampAsString.builder().build());

        assertEquals("2022-03-03T14:00", timestampAsString.getTimestamp1AsString().orElseThrow());
        assertEquals("2022-03-03T14:00:00.123", timestampAsString.getTimestamp2AsString().orElseThrow());
        assertEquals("2022-03-03T13:57", timestampAsString.getTimestamp3AsString().orElseThrow());
        assertEquals("2022-03-03T11:00", timestampAsString.getTimestamp4AsString().orElseThrow());
        assertEquals("2022-03-03T10:57", timestampAsString.getTimestamp5AsString().orElseThrow());
        assertEquals("2022-03-03T14:03", timestampAsString.getTimestamp6AsString().orElseThrow());
        assertEquals("2022-03-03T17:00", timestampAsString.getTimestamp7AsString().orElseThrow());
        assertEquals("2022-03-03T17:03", timestampAsString.getTimestamp8AsString().orElseThrow());
        assertEquals("2022-03-03T13:57:00.123", timestampAsString.getTimestamp9AsString().orElseThrow());
        assertEquals("2022-03-03T11:00:00.123", timestampAsString.getTimestamp10AsString().orElseThrow());
        assertEquals("2022-03-03T10:57:00.123", timestampAsString.getTimestamp12AsString().orElseThrow());
        assertEquals("2022-03-03T14:03:00.123", timestampAsString.getTimestamp13AsString().orElseThrow());
        assertEquals("2022-03-03T17:00:00.123", timestampAsString.getTimestamp14AsString().orElseThrow());
        assertEquals("2022-03-03T17:03:00.123", timestampAsString.getTimestamp15AsString().orElseThrow());

        assertEquals("2022-03-03T14:00", timestampAsString.getTimestamp1AsString1().orElseThrow());
        assertEquals("2022-03-03T14:00:00.123", timestampAsString.getTimestamp2AsString1().orElseThrow());
        assertEquals("2022-03-03T13:57", timestampAsString.getTimestamp3AsString1().orElseThrow());
        assertEquals("2022-03-03T11:00", timestampAsString.getTimestamp4AsString1().orElseThrow());
        assertEquals("2022-03-03T10:57", timestampAsString.getTimestamp5AsString1().orElseThrow());
        assertEquals("2022-03-03T14:03", timestampAsString.getTimestamp6AsString1().orElseThrow());
        assertEquals("2022-03-03T17:00", timestampAsString.getTimestamp7AsString1().orElseThrow());
        assertEquals("2022-03-03T17:03", timestampAsString.getTimestamp8AsString1().orElseThrow());
        assertEquals("2022-03-03T13:57:00.123", timestampAsString.getTimestamp9AsString1().orElseThrow());
        assertEquals("2022-03-03T11:00:00.123", timestampAsString.getTimestamp10AsString1().orElseThrow());
        assertEquals("2022-03-03T10:57:00.123", timestampAsString.getTimestamp12AsString1().orElseThrow());
        assertEquals("2022-03-03T14:03:00.123", timestampAsString.getTimestamp13AsString1().orElseThrow());
        assertEquals("2022-03-03T17:00:00.123", timestampAsString.getTimestamp14AsString1().orElseThrow());
        assertEquals("2022-03-03T17:03:00.123", timestampAsString.getTimestamp15AsString1().orElseThrow());

        assertEquals(LocalDateTime.of(2022, 9, 29, 0, 0, 0, 0), timestampAsString.getTimestampOfDate().orElseThrow());
        assertEquals("2022-09-29T00:00", timestampAsString.getTimestampOfDateAsString().orElseThrow());
        assertEquals("2022-09-29T00:00", timestampAsString.getTimestampOfDateAsString1().orElseThrow());

        assertEquals(LocalDateTime.of(2022, 9, 29, 11, 11, 11, 0), timestampAsString.getTimestampOfDateAndTime().orElseThrow());
        assertEquals("2022-09-29T11:11:11", timestampAsString.getTimestampOfDateAndTimeAsString().orElseThrow());
        assertEquals("2022-09-29T11:11:11", timestampAsString.getTimestampOfDateAndTimeAsString1().orElseThrow());

        assertEquals(Instant.ofEpochMilli(1664449871000L).atOffset(ZoneOffset.UTC).toLocalDateTime(), timestampAsString.getFromMilliseconds().orElseThrow());
        assertEquals(Instant.ofEpochMilli(1664449871000L).atOffset(ZoneOffset.UTC).format(ISO_LOCAL_DATE_TIME), timestampAsString.getFromMillisecondsAsString().orElseThrow());
        assertEquals(Instant.ofEpochMilli(1664449871000L).atOffset(ZoneOffset.UTC).format(ISO_LOCAL_DATE_TIME), timestampAsString.getFromMillisecondsAsString1().orElseThrow());
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-EXPR-020",
            "REQ-SRV-002"
    })
    public void testEnumOnMappedTransfer() {
        TransferEnumFunctions myEnum = transferEnumFunctionsDao.create(TransferEnumFunctions.builder().build());

        assertEquals(Optional.of("Atomic"), myEnum.getOwnEnumAsString());
        assertEquals(Optional.of("Atomic"), myEnum.getEnumAsString());
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-003",
            "REQ-ENT-004",
            "REQ-ENT-007",
            "REQ-EXPR-021",
            "REQ-EXPR-022",
            "REQ-SRV-002"
    })
    public void testInstanceOnMappedTransfer() {
        TransferParent parent1 = transferParentDao.create(TransferParent.builder().withName("James Webb").build());
        TransferChild child1 = transferChildDao.create(TransferChild.builder().withName("Erika Young").withAge(11L).build());

        TransferInstanceFunctions instanceFunctions = transferInstanceFunctionsDao.create(TransferInstanceFunctions.builder()
                        .withParent(TransferParent.builder().withName("Another Person").build())
                        .withChild(TransferChild.builder().withName("Another Child").withAge(31L).build())
                        .build());

        TransferInstanceFunctions instanceFunctions1 = transferInstanceFunctionsDao.create(TransferInstanceFunctions.builder()
                        .withParent(transferParentDao.getById(child1.identifier().adaptTo(TransferParentIdentifier.class)).orElseThrow())
                        .build());

        assertTrue(instanceFunctions.getTypeOfParent().get());
        assertFalse(instanceFunctions.getKindOfChild().get());
        assertTrue(instanceFunctions.getKindOfParent().get());
        assertFalse(instanceFunctions.getNotTypeOfChild().get());

        String parentName = transferInstanceFunctionsDao.queryAsParentType(instanceFunctions).orElseThrow().getName().orElseThrow();
        assertEquals("Another Child", parentName);
        String childName = transferInstanceFunctionsDao.queryAsChildType(instanceFunctions1).orElseThrow().getName().orElseThrow();
        assertEquals("Erika Young", childName);

        transferInstanceFunctionsDao.addParents(instanceFunctions, ImmutableList.of(parent1));

        instanceFunctions = transferInstanceFunctionsDao.getById(instanceFunctions.identifier()).orElseThrow();

        assertEquals(10, instanceFunctions.getNavigationWithCalls().get());

        TransferInstanceFunctions instanceFunctions2 = transferInstanceFunctionsDao.create(TransferInstanceFunctions.builder()
                                                                                            .withParent(TransferParent.builder().withName("P1").build())
                                                                                            .withChild(TransferChild.builder().withName("C1").build())
                                                                                            .build());
        TransferParent p1 = transferParentDao.getAll().stream().filter(p -> p.getName().orElseThrow().equals("P1")).findAny().orElseThrow();
        TransferChild c1 = transferChildDao.getAll().stream().filter(c -> c.getName().orElseThrow().equals("C1")).findAny().orElseThrow();

        transferInstanceFunctionsDao.addParents(instanceFunctions2, List.of(p1));
        transferInstanceFunctionsDao.addChildren(instanceFunctions2, List.of(c1));

        instanceFunctions2 = transferInstanceFunctionsDao.getById(instanceFunctions2.identifier()).orElseThrow();

        assertTrue(instanceFunctions2.getParentMemberOfParents().orElseThrow());
        assertTrue(instanceFunctions2.getParentMemberOfParentsExtra().orElseThrow());
        assertFalse(instanceFunctions2.getParentMemberOfChildren().orElseThrow());
        assertFalse(instanceFunctions2.getChildMemberOfParents().orElseThrow());
        assertTrue(instanceFunctions2.getChildMemberOfChildren().orElseThrow());

    }

    @Test
    @Requirement(reqs = {
            "REQ-EXPR-022",
            "REQ-SRV-002"
    })
    public void testCollectionOnMappedTransfer() {
        TransferCollectionFunctions collectionFunctions = transferCollectionFunctionsDao.create(TransferCollectionFunctions.builder()
                .withParentsField(
                        List.of(
                                TransferParent.builder().withName("John").build(),
                                TransferParent.builder().withName("Another Person").build()
                        )
                )
                .withChildrenField(
                        List.of(
                                TransferChild.builder().withName("Rebecca").withAge(33L).build(),
                                TransferChild.builder().withName("Cindy").withAge(23L).build(),
                                TransferChild.builder().withName("Monica").withAge(23L).build(),
                                TransferChild.builder().withName("Peter").withAge(46L).build(),
                                TransferChild.builder().withName("Andrew").withAge(46L).build()
                        )
                )
                .build(), TransferCollectionFunctionsAttachedRelationsForCreate.builder()
                .withParentsRelation(
                        List.of(
                                TransferParent.builder().withName("Mark").build(),
                                TransferParent.builder().withName("Billy").build()
                        )
                )
                .withChildrenRelation(
                        List.of(
                                TransferChild.builder().withName("Mario").withAge(33L).build(),
                                TransferChild.builder().withName("Stacey").withAge(16L).build(),
                                TransferChild.builder().withName("Ruby").withAge(16L).build(),
                                TransferChild.builder().withName("Anna").withAge(34L).build(),
                                TransferChild.builder().withName("Clark").withAge(34L).build(),
                                TransferChild.builder().withName("Daniel").build()
                        )
                )
                .build()
                );

        assertEquals(2, collectionFunctions.getSizeParentsField().get());
        assertEquals(2, collectionFunctions.getSizeParentsRelation().get());

        assertNotNull(transferCollectionFunctionsDao.queryAnyParentsField(collectionFunctions));
        assertNotNull(transferCollectionFunctionsDao.queryAnyParentsRelation(collectionFunctions));

        List<TransferParent> asCollectionChildrenParentField = transferCollectionFunctionsDao
                .queryAsCollectionChildrenParentField(collectionFunctions).execute();
        assertEquals(2, asCollectionChildrenParentField.size());
        assertTrue(asCollectionChildrenParentField.stream().anyMatch(p -> p.getName().orElseThrow().equals("John")));
        assertTrue(asCollectionChildrenParentField.stream().anyMatch(p -> p.getName().orElseThrow().equals("Another Person")));

        List<TransferParent> asCollectionChildrenParentRelation = transferCollectionFunctionsDao
                .queryAsCollectionChildrenParentRelation(collectionFunctions).execute();
        assertEquals(2, asCollectionChildrenParentRelation.size());
        assertTrue(asCollectionChildrenParentRelation.stream().anyMatch(p -> p.getName().orElseThrow().equals("Mark")));
        assertTrue(asCollectionChildrenParentRelation.stream().anyMatch(p -> p.getName().orElseThrow().equals("Billy")));

        List<TransferChild> asCollectionChildrenChildField = transferCollectionFunctionsDao
                .queryAsCollectionChildrenChildField(collectionFunctions).execute();
        assertEquals(0, asCollectionChildrenChildField.size());

        List<TransferChild> asCollectionChildrenChildRelation = transferCollectionFunctionsDao
                .queryAsCollectionChildrenChildRelation(collectionFunctions).execute();
        assertEquals(0, asCollectionChildrenChildRelation.size());

        assertEquals(Optional.of(23L), collectionFunctions.getMinChildrenField());
        assertEquals(Optional.of(16L), collectionFunctions.getMinChildrenRelation());

        assertEquals(Optional.of(46L), collectionFunctions.getMaxChildrenField());
        assertEquals(Optional.of(34L), collectionFunctions.getMaxChildrenRelation());

        assertEquals(Optional.of(171L), collectionFunctions.getSumChildrenField());
        assertEquals(Optional.of(133L), collectionFunctions.getSumChildrenRelation());

        assertEquals(Optional.of(34L), collectionFunctions.getAvgChildrenField());
        assertEquals(Optional.of(26L), collectionFunctions.getAvgChildrenRelation());

        assertEquals(Optional.of(34.2), collectionFunctions.getAvgScaledChildrenField());
        assertEquals(Optional.of(26.6), collectionFunctions.getAvgScaledChildrenRelation());

        assertEquals(Optional.of(34L), collectionFunctions.getDivisionConst());
        assertEquals(Optional.of(35L), collectionFunctions.getRoundConst());

        assertEquals(2, transferCollectionFunctionsDao.queryFirstChildrenField(collectionFunctions).execute().size());
        assertEquals(2, transferCollectionFunctionsDao.queryFirstChildrenRelation(collectionFunctions).execute().size());

        assertEquals(2, transferCollectionFunctionsDao.queryLastChildrenField(collectionFunctions).execute().size());
        assertEquals(2, transferCollectionFunctionsDao.queryLastChildrenRelation(collectionFunctions).execute().size());

        assertEquals(2, transferCollectionFunctionsDao.queryFrontChildrenField(collectionFunctions).execute().size());
        assertEquals(2, transferCollectionFunctionsDao.queryFrontChildrenRelation(collectionFunctions).execute().size());

        assertEquals(2, transferCollectionFunctionsDao.queryBackChildrenField(collectionFunctions).execute().size());
        assertEquals(2, transferCollectionFunctionsDao.queryBackChildrenRelation(collectionFunctions).execute().size());

        assertTrue(collectionFunctions.getContainsParent().orElseThrow());

        assertEquals(1, transferCollectionFunctionsDao.queryFilterParentsField(collectionFunctions).execute().size());
        assertEquals(1, transferCollectionFunctionsDao.queryFilterParentsRelation(collectionFunctions).execute().size());

        assertEquals(1, transferCollectionFunctionsDao.queryFilterChildrenField(collectionFunctions).execute().size());
        assertEquals(1, transferCollectionFunctionsDao.queryFilterChildrenRelation(collectionFunctions).execute().size());

        assertEquals(Optional.of(true), collectionFunctions.getAnyTrueChildrenField());
        assertEquals(Optional.of(false), collectionFunctions.getAnyTrueChildrenRelation());

        assertEquals(Optional.of(true), collectionFunctions.getAllTrueChildrenField());
        assertEquals(Optional.of(false), collectionFunctions.getAllTrueChildrenRelation());

        assertEquals(Optional.of(false), collectionFunctions.getAnyFalseChildrenField());
        assertEquals(Optional.of(true), collectionFunctions.getAnyFalseChildrenRelation());

        assertEquals(Optional.of(false), collectionFunctions.getAllFalseChildrenField());
        assertEquals(Optional.of(false), collectionFunctions.getAllFalseChildrenRelation());
    }

    @Test
    @Requirement(reqs = {
            "REQ-EXPR-022",
            "REQ-SRV-002"
    })
    public void testBooleanAggregatorFunctionsOnMappedTransfer() {
        /*

        |--------------------------------------------|
        | Transfer || 3 | 2 | 2 | 1 | 1 | 1 | 0 | 0 | 0 | 0 |
        | F || 0 | 1 | 0 | 2 | 1 | 0 | 3 | 2 | 1 | 0 |
        | U || 0 | 0 | 1 | 0 | 1 | 2 | 0 | 1 | 2 | 3 |
        |--------------------------------------------|

        |------------------------------------------------------|
        | A | B | C || anyTrue | allTrue | anyFalse | allFalse |
        | - | - | - ||    F    |    T    |    F     |    T     |
        | U | - | - ||    F    |    F    |    F     |    F     |
        | T | - | - ||    T    |    T    |    F     |    F     |
        | F | - | - ||    F    |    F    |    T     |    T     |
        | T | T | T ||    T    |    T    |    F     |    F     |
        | T | T | F ||    T    |    F    |    T     |    F     |
        | T | T | U ||    T    |    F    |    F     |    F     |
        | T | F | F ||    T    |    F    |    T     |    F     |
        | T | F | U ||    T    |    F    |    T     |    F     |
        | T | U | U ||    T    |    F    |    F     |    F     |
        | F | F | F ||    F    |    F    |    T     |    T     |
        | F | F | U ||    F    |    F    |    T     |    F     |
        | F | U | U ||    F    |    F    |    T     |    F     |
        | U | U | U ||    F    |    F    |    F     |    F     |
        |------------------------------------------------------|

        */

        transferBoolerTesterDao.create(TransferBoolerTester.builder().build());

        // | - | - | - ||    F    |    T    |    F     |    T     |
        assertBoolers(false, true, false, true);

        // | U | - | - ||    F    |    F    |    F     |    F     |
        TransferBooler a = createBooler(null);
        assertBoolers(false, false, false, false);

        // | T | - | - ||    T    |    T    |    F     |    F     |
        a = updateBooler(a, true);
        assertBoolers(true, true, false, false);

        // | F | - | - ||    F    |    F    |    T     |    T     |
        a = updateBooler(a, false);
        assertBoolers(false, false, true, true);

        // | T | T | T ||    T    |    T    |    F     |    F     |
        a = updateBooler(a, true);
        TransferBooler b = createBooler(true);
        TransferBooler c = createBooler(true);
        assertBoolers(true, true, false, false);

        // | T | T | F ||    T    |    F    |    T     |    F     |
        c = updateBooler(c, false);
        assertBoolers(true, false, true, false);

        // | T | T | U ||    T    |    F    |    F     |    F     |
        c = updateBooler(c, null);
        assertBoolers(true, false, false, false);

        // | T | F | F ||    T    |    F    |    T     |    F     |
        b = updateBooler(b, false);
        c = updateBooler(c, false);
        assertBoolers(true, false, true, false);

        // | T | F | U ||    T    |    F    |    T     |    F     |
        c = updateBooler(c, null);
        assertBoolers(true, false, true, false);

        // | T | U | U ||    T    |    F    |    F     |    F     |
        b = updateBooler(b, null);
        assertBoolers(true, false, false, false);

        // | F | F | F ||    F    |    F    |    T     |    T     |
        a = updateBooler(a, false);
        b = updateBooler(b, false);
        c = updateBooler(c, false);
        assertBoolers(false, false, true, true);

        // | F | F | U ||    F    |    F    |    T     |    F     |
        c = updateBooler(c, null);
        assertBoolers(false, false, true, false);

        // | F | U | U ||    F    |    F    |    T     |    F     |
        b = updateBooler(b, null);
        assertBoolers(false, false, true, false);

        // | U | U | U ||    F    |    F    |    F     |    F     |
        a = updateBooler(a, null);
        assertBoolers(false, false, false, false);
    }

    private void assertBoolers(Boolean anyTrue, Boolean allTrue, Boolean anyFalse, Boolean allFalse) {
        Optional<TransferBoolerTester> testerOptional = transferBoolerTesterDao.query().execute().stream().findAny();
        assertTrue(testerOptional.isPresent());
        TransferBoolerTester tester = testerOptional.get();
        Boolean anyTrueGot = tester.getAnyTrue().orElseThrow();
        assertEquals(anyTrue, anyTrueGot, String.format("anyTrue should be %s, but is %s", anyTrue, anyTrueGot));
        Boolean allTrueGot =  tester.getAllTrue().orElseThrow();
        assertEquals(allTrue, allTrueGot, String.format("allTrue should be %s, but is %s", allTrue, allTrueGot));
        Boolean anyFalseGot =  tester.getAnyFalse().orElseThrow();
        assertEquals(anyFalse, anyFalseGot, String.format("anyFalse should be %s, but is %s", anyFalse, anyFalseGot));
        Boolean allFalseGot = tester.getAllFalse().orElseThrow();
        assertEquals(allFalse, allFalseGot, String.format("allFalse should be %s, but is %s", allFalse, allFalseGot));
    }

    private TransferBooler createBooler(Boolean b) {
        return transferBoolerDao.create(TransferBooler.builder().withB(b).build());
    }

    private TransferBooler updateBooler(TransferBooler booler, Boolean b) {
        booler.setB(b);
        return transferBoolerDao.update(booler);
    }

    @Test
    @Requirement(reqs = {
            "REQ-EXPR-021",
            "REQ-SRV-002"
    })
    public void testMemberOnMappedTransfer() {
        TransferMember m1 = transferMemberDao.create(TransferMember.builder().withName("M1").build());
        TransferMember m2 = transferMemberDao.create(TransferMember.builder().withName("M2").build());
        TransferMember m3 = transferMemberDao.create(TransferMember.builder().withName("M3").build());

        TransferTester tester = transferTesterDao.create(
                TransferTester.builder().build(),
                TransferTesterAttachedRelationsForCreate.builder()
                            .withMember(m1)
                            .withMembers(List.of(m1, m2, m3))
                            .build());
        tester = transferTesterDao.getById(tester.identifier()).orElseThrow();
        assertTrue(tester.getMemberMemberOfMembers().orElseThrow());
    }

    @Test
    public void testFirstOnMappedTransfer() {
        TransferSimple s1 = transferSimpleDao.create(TransferSimple.builder().withStringAttr("A").build());
        TransferSimple s2 = transferSimpleDao.create(TransferSimple.builder().withStringAttr("a").build());
        TransferSimple s3 = transferSimpleDao.create(TransferSimple.builder().withStringAttr("b").build());
        s1 = transferSimpleDao.update(s1);
        assertEquals(Optional.of(Long.valueOf(1)), s1.getFirstNum());
    }

}
