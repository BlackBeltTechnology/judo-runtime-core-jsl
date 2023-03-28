package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc004.modeltc004.e1.E1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc004.modeltc004.e1.E1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc004.modeltc004.testliteral.TestLiteral;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ModelTC004DaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class OperatorAndFunctionOfEnumTest extends AbstractJslTest {

    @Inject
    E1Dao e1Dao;

    @Override
    public Module getModelDaoModule() {
        return new ModelTC004DaoModules();
    }

    @Override
    public String getModelName() {
        return "modelTC004";
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
     * @jslModel modelTC004.jsl
     *  model modelTC004;
     *
     *  type boolean Bool;
     *  type string String(min-size=0, max-size=10);
     *
     *  enum TestLiteral {
     *      Aaa01 = 1;
     *      Bbb02 = 2;
     *      Ccc03 = 9999;
     *      ZZ00  = 0;
     *  }
     *
     *  entity E1 {
     *      field TestLiteral f1 = TestLiteral#ZZ00;
     *      field TestLiteral f2 = TestLiteral#Aaa01;
     *      derived Bool   f3 => self.f1 < self.f2;
     *      derived Bool   f4 => self.f1 <= self.f2;
     *      derived Bool   f5 => self.f1 > self.f2;
     *      derived Bool   f6 => self.f1 >= self.f2;
     *      derived Bool   f7 => self.f1 == self.f2;
     *      derived Bool   f8 => self.f1 != self.f2;
     *      derived String f9 => self.f1!asString();
     *  }
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
     *  . Change the e1 entity instance f1 field to TestLiteral#Ccc03 and save the e1 entity instance..
     *
     *  . Change the e1 entity instance f2 field to TestLiteral#ZZZ.
     *
     *  . Check the values of the following fields of the modified e1 entity instance.
     *     * f1 == TestLiteral#Ccc03
     *     * f2 == TestLiteral#Bbb02
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
        // TODO: JNG-4664 Implementing '<' '>'  '<='  '>=' operations for enum in JQL
        // TODO: check f3, f4, f6, f6 fields
        E1 e1 = e1Dao.create(E1.builder().build());
        assertTrue(e1.getF1().equals(Optional.of(TestLiteral.ZZ00)));
        assertTrue(e1.getF2().equals(Optional.of(TestLiteral.Aaa01)));
        assertTrue(e1.getF7().equals(Optional.of(false)));
        assertTrue(e1.getF8().equals(Optional.of(true)));
        assertTrue(e1.getF9().equals(Optional.of("ZZ00")));

        e1.setF1(TestLiteral.Aaa01);
        e1 = e1Dao.update(e1);
        assertTrue(e1.getF1().equals(Optional.of(TestLiteral.Aaa01)));
        assertTrue(e1.getF2().equals(Optional.of(TestLiteral.Aaa01)));
        assertTrue(e1.getF7().equals(Optional.of(true)));
        assertTrue(e1.getF8().equals(Optional.of(false)));
        assertTrue(e1.getF9().equals(Optional.of("Aaa01")));

        e1.setF1(TestLiteral.Ccc03);
        e1 = e1Dao.update(e1);
        e1.setF2(TestLiteral.ZZ00);
        assertTrue(e1.getF1().equals(Optional.of(TestLiteral.Ccc03)));
        assertTrue(e1.getF2().equals(Optional.of(TestLiteral.ZZ00)));
        assertTrue(e1.getF7().equals(Optional.of(false)));
        assertTrue(e1.getF8().equals(Optional.of(true)));
        assertTrue(e1.getF9().equals(Optional.of("Ccc03")));

    }
}
