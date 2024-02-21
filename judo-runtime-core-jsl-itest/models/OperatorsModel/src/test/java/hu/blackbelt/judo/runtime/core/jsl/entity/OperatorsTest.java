package hu.blackbelt.judo.runtime.core.jsl.entity;

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

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.defaultoperators.DefaultOperators;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.defaultoperators.DefaultOperatorsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.defaultoperators.DefaultOperatorsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.derivedoperators.DerivedOperators;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.derivedoperators.DerivedOperatorsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.derivedoperators.DerivedOperatorsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.derivedsource.DerivedSourceForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.e1.E1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.e1.E1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.e1.E1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.equal.EqualDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.equal.EqualParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.great.GreatDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.great.GreatParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.greatequal.GreatEqualDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.greatequal.GreatEqualParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.less.LessDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.less.LessParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.lessequal.LessEqualDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.lessequal.LessEqualParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.notequal.NotEqualDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.notequal.NotEqualParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.operators.operators.testliteral.TestLiteral;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.OperatorsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class OperatorsTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Operators", new OperatorsDaoModules());

    @Inject
    DefaultOperatorsDao defaultOperatorsDao;

    @Inject
    DerivedOperatorsDao derivedOperatorsDao;

    @Inject E1Dao e1Dao;

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-EXPR-002",
            "REQ-EXPR-006",
            "REQ-EXPR-014"
    })
    void testOperatorsForDefaultValues() {
        DefaultOperators operators = defaultOperatorsDao.create(DefaultOperatorsForCreate.builder().build());

        assertEquals(Optional.of(1), operators.getRounded());
        assertEquals(Optional.of(-6), operators.getUnary());
        assertEquals(Optional.of(2), operators.getAbs());
        assertEquals(Optional.of(7), operators.getAddition());
        assertEquals(Optional.of(3), operators.getSubtraction());
        assertEquals(Optional.of(25), operators.getPow());
        assertEquals(Optional.of(10), operators.getMultiplication());
        assertEquals(Optional.of(Float.valueOf("2.5")), operators.getDivision());
        assertEquals(Optional.of(2), operators.getDivisionWhole());
        assertEquals(Optional.of(1), operators.getModulo());
        assertEquals(Optional.of(false), operators.getLt());
        assertEquals(Optional.of(true), operators.getLt2());
        assertEquals(Optional.of(false), operators.getGt());
        assertEquals(Optional.of(true), operators.getGt2());
        assertEquals(Optional.of(true), operators.getLte());
        assertEquals(Optional.of(true), operators.getLte2());
        assertEquals(Optional.of(true), operators.getGte());
        assertEquals(Optional.of(true), operators.getGte2());
        assertEquals(Optional.of(false), operators.getEq());
        assertEquals(Optional.of(true), operators.getNeq());
        assertEquals(Optional.of(1), operators.getConditional());
        assertEquals(Optional.of(false), operators.getGroupAnd());
        assertEquals(Optional.of(true), operators.getGroupOr());

        assertEquals(0, operators.getOneModOne().orElseThrow());
        assertEquals(1.0f, operators.getOneModOnePointNine().orElseThrow());
        assertEquals(0.0f, operators.getOnePointNineModOnePointNine().orElseThrow());
        assertEquals(0.9f, operators.getOnePointNineModOne().orElseThrow());
        assertEquals(1, operators.getOneDivOne().orElseThrow());
        assertTrue(0.526 <= (double) operators.getOneDivOnePointNine().orElseThrow());
        assertTrue((double) operators.getOneDivOnePointNine().orElseThrow() < 0.527);
        assertEquals(1.0f, operators.getOnePointNineDivOnePointNine().orElseThrow());
        assertEquals(1.9f, operators.getOnePointNineDivOne().orElseThrow());

        assertEquals(Optional.of(true), operators.getDateLt());
        assertEquals(Optional.of(true), operators.getDateLte());
        assertEquals(Optional.of(true), operators.getDateLte2());
        assertEquals(Optional.of(true), operators.getDateGt());
        assertEquals(Optional.of(true), operators.getDateGte());
        assertEquals(Optional.of(true), operators.getDateGte2());
        assertEquals(Optional.of(true), operators.getDateEq());
        assertEquals(Optional.of(true), operators.getDateNeq());

        assertEquals(Optional.of(true), operators.getTimeLt());
        assertEquals(Optional.of(true), operators.getTimeLte());
        assertEquals(Optional.of(true), operators.getTimeLte2());
        assertEquals(Optional.of(true), operators.getTimeGt());
        assertEquals(Optional.of(true), operators.getTimeGte());
        assertEquals(Optional.of(true), operators.getTimeGte2());
        assertEquals(Optional.of(true), operators.getTimeEq());
        assertEquals(Optional.of(true), operators.getTimeNeq());

        assertEquals(Optional.of(true), operators.getTimestampEq());
        assertEquals(Optional.of(true), operators.getTimestampNeq());

        assertEquals(Optional.of(true), operators.getStringLt());
        assertEquals(Optional.of(true), operators.getStringLte());
        assertEquals(Optional.of(true), operators.getStringLte2());
        assertEquals(Optional.of(true), operators.getStringGt());
        assertEquals(Optional.of(true), operators.getStringGte());
        assertEquals(Optional.of(true), operators.getStringGte2());
        assertEquals(Optional.of(true), operators.getStringEq());
        assertEquals(Optional.of(true), operators.getStringNeq());

        // TODO JNG-4045
        // assertEquals(Optional.of(true), operators.getCsStringLt());
        // assertEquals(Optional.of(true), operators.getCsStringLte2());
        // assertEquals(Optional.of(true), operators.getCsStringGt());
        // assertEquals(Optional.of(true), operators.getCsStringGte2());
        assertEquals(Optional.of(true), operators.getCsStringLte());
        assertEquals(Optional.of(true), operators.getCsStringGte());
        assertEquals(Optional.of(true), operators.getCsStringEq());
        assertEquals(Optional.of(false), operators.getCsStringEqFalse());
        assertEquals(Optional.of(true), operators.getCsStringNeq());
}

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-010",
            "REQ-EXPR-012",
            "REQ-EXPR-013",
            "REQ-EXPR-014"
    })
    void testOperatorsForDerivedFields() {
        DerivedOperators operators = derivedOperatorsDao.create(DerivedOperatorsForCreate.builder()
                .withSource(DerivedSourceForCreate.builder().build())
                .build());

        assertEquals(Optional.of("John Pro"), operators.getStringConcat());
        assertEquals(Optional.of(4), operators.getFirstNameLength());
        assertEquals(Optional.of(99), operators.getRounded());
        assertEquals(Optional.of(37), operators.getAddition());
        assertTrue(operators.getAdditionTrue().orElseThrow());
        assertFalse(operators.getAdditionFalse().orElseThrow());
        assertTrue(operators.getAdditionUndefinedTrue().orElseThrow());
        assertFalse(operators.getAdditionUndefinedFalse().orElseThrow());
        assertEquals(Optional.of(33), operators.getSubtraction());
        assertEquals(Optional.of(70), operators.getMultiplication());
        assertEquals(Optional.of(17), operators.getDivision());
        assertEquals(Optional.of(17), operators.getDivisionWhole());
        assertEquals(Optional.of(1), operators.getModulo());
        assertEquals(Optional.of(false), operators.getLt());
        assertTrue(operators.getLtTrue().orElseThrow());
        assertFalse(operators.getLtFalse().orElseThrow());
        assertTrue(operators.getLtUndefinedTrue().orElseThrow());
        assertFalse(operators.getLtUndefinedFalse().orElseThrow());
        assertEquals(Optional.of(true), operators.getLt2());
        assertEquals(Optional.of(true), operators.getGt());
        assertEquals(Optional.of(true), operators.getGt2());
        assertEquals(Optional.of(false), operators.getLte());
        assertEquals(Optional.of(true), operators.getLte2());
        assertEquals(Optional.of(true), operators.getGte());
        assertEquals(Optional.of(true), operators.getGte2());
        assertEquals(Optional.of(false), operators.getEq());
        assertEquals(Optional.of(true), operators.getNeq());
        assertEquals(Optional.of(1), operators.getConditional());
        assertTrue(operators.getConditionalTrue().orElseThrow());
        assertFalse(operators.getConditionalFalse().orElseThrow());
        assertTrue(operators.getConditionalUndefinedTrue().orElseThrow());
        assertFalse(operators.getConditionalUndefinedFalse().orElseThrow());
    }

    /**
     * Testing the operators and functions of enum.
     *
     * @prerequisites Nothing
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create an E1 entity instance (e1) with the default values.
     *
     *  . Check the values of the following fields of the new entity instance (e1).
     *     * f1 == TestLiteral#ZZ00
     *     * f2 == TestLiteral#Aaa01
     *     * f3 == true
     *     * f4 == true
     *     * f5 == false
     *     * f6 == false
     *     * f7 == false
     *     * f8 == true
     *     * f9 == "ZZ00"
     *
     *  . Change the e1 entity instance f1 field to TestLiteral#Aaa01 and save the e1 entity instance.
     *
     *  . Check the values of the following fields of the modified e1 entity instance.
     *     * f1 == TestLiteral#Aaa01
     *     * f2 == TestLiteral#Aaa01
     *     * f3 == false
     *     * f4 == true
     *     * f5 == false
     *     * f6 == true
     *     * f7 == true
     *     * f8 == false
     *     * f9 == "Aaa01"
     *
     *  . Change the e1 entity instance's f1 field to TestLiteral#Ccc03 and f2 field to TestLiteral#ZZ00 and save the e1 entity instance.
     *
     *  . Check the values of the following fields of the modified e1 entity instance.
     *     * f1 == TestLiteral#Ccc03
     *     * f2 == TestLiteral#ZZ00
     *     * f3 == false
     *     * f4 == false
     *     * f5 == true
     *     * f6 == true
     *     * f7 == false
     *     * f8 == true
     *     * f9 == "Ccc03"
     *
     *  . The test is passed if all checks are successful, and there were no exceptions.
     */
    @Test
    @TestCase("TC004")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-MDL-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-EXPR-020"
    })
    void testEnumOperatorsAndFunctions() {
        E1 e1 = e1Dao.create(E1ForCreate.builder().build());
        assertEquals(TestLiteral.ZZ00, e1.getF1().orElseThrow());
        assertEquals(TestLiteral.Aaa01, e1.getF2().orElseThrow());
        assertTrue(e1.getF3().orElseThrow());
        assertTrue(e1.getF4().orElseThrow());
        assertFalse(e1.getF5().orElseThrow());
        assertFalse(e1.getF6().orElseThrow());
        assertFalse(e1.getF7().orElseThrow());
        assertTrue(e1.getF8().orElseThrow());
        assertEquals("ZZ00", e1.getF9().orElseThrow());

        e1.setF1(TestLiteral.Aaa01);
        e1 = e1Dao.update(e1);
        assertEquals(TestLiteral.Aaa01, e1.getF1().orElseThrow());
        assertEquals(TestLiteral.Aaa01, e1.getF2().orElseThrow());
        assertFalse(e1.getF3().orElseThrow());
        assertTrue(e1.getF4().orElseThrow());
        assertFalse(e1.getF5().orElseThrow());
        assertTrue(e1.getF6().orElseThrow());
        assertTrue(e1.getF7().orElseThrow());
        assertFalse(e1.getF8().orElseThrow());
        assertEquals("Aaa01", e1.getF9().orElseThrow());

        e1.setF1(TestLiteral.Ccc03);
        e1.setF2(TestLiteral.ZZ00);
        e1 = e1Dao.update(e1);
        assertEquals(TestLiteral.Ccc03, e1.getF1().orElseThrow());
        assertEquals(TestLiteral.ZZ00, e1.getF2().orElseThrow());
        assertFalse(e1.getF3().orElseThrow());
        assertFalse(e1.getF4().orElseThrow());
        assertTrue(e1.getF5().orElseThrow());
        assertTrue(e1.getF6().orElseThrow());
        assertFalse(e1.getF7().orElseThrow());
        assertTrue(e1.getF8().orElseThrow());
        assertEquals("Ccc03", e1.getF9().orElseThrow());

    }

    @Inject
    LessDao lessDao;

    @Inject
    LessEqualDao lessEqualDao;

    @Inject
    GreatDao greatDao;

    @Inject
    GreatEqualDao greatEqualsDao;

    @Inject
    EqualDao equalDao;

    @Inject
    NotEqualDao notEqualDao;

    @Test
    @TestCase("HungarianStringComparisonTest")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-MDL-001",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-ENT-009",
            "REQ-EXPR-002"
    })
    @EnabledIfSystemProperty(named = "dialect", matches = "postgresql")
    void testHungarianStringComparisonTest() throws ParseException {
        String rules = "< a < A < á < Á < b < B < c < C < cs < cS < Cs < CS < d < D < dz < dZ < Dz < DZ < dzs < dzS < dZs < Dzs " +
                "< DzS < DZs < DZS < e < E < é < É < f < F < g < G < gy < gY < Gy < GY < h < H < i < I < í < Í < j < J < k " +
                "< K < l < L < ly < lY < Ly < LY < m < M < n < N < ny < nY < Ny < NY < o < O < ó < Ó < ö < Ö < ő < Ő < p " +
                "< P < q < Q < r < R < s < S < sz < sZ < Sz < SZ < t < T < ty < tY < Ty < TY < u < U < ú < Ú < ü < Ü < ű " +
                "< Ű < v < V < w < W < x < X < y < Y < z < Z < zs < zS < Zs < ZS";

        RuleBasedCollator collator = new RuleBasedCollator(rules);
        collator.setStrength(Collator.PRIMARY);
        collator.setDecomposition(Collator.CANONICAL_DECOMPOSITION);

        List<String> letters = List.of(rules.replaceAll(" ", "").replaceFirst("<", "").split("<"));

        for (int i = 0; i < letters.size() - 1; i++) {
            String left = letters.get(i);
            String right = letters.get(i + 1);

            assertEquals(collator.compare(left, right) < 0, lessDao.selectValue(LessParameter.builder().withA(left).withB(right).build()));
            assertEquals(collator.compare(left, right) <= 0, lessEqualDao.selectValue(LessEqualParameter.builder().withA(left).withB(right).build()));
            assertEquals(collator.compare(left, right) > 0, greatDao.selectValue(GreatParameter.builder().withA(left).withB(right).build()));
            assertEquals(collator.compare(left, right) >= 0, greatEqualsDao.selectValue(GreatEqualParameter.builder().withA(left).withB(right).build()));
            assertEquals(collator.compare(left, right) == 0, equalDao.selectValue(EqualParameter.builder().withA(left).withB(right).build()));
            assertEquals(collator.compare(left, right) != 0, notEqualDao.selectValue(NotEqualParameter.builder().withA(left).withB(right).build()));
        }
    }
}
