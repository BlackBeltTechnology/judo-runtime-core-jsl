package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferoperatorandfunctionofenumtest.mappedtransferoperatorandfunctionofenumtest.e1.E1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferoperatorandfunctionofenumtest.mappedtransferoperatorandfunctionofenumtest.e1.E1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferoperatorandfunctionofenumtest.mappedtransferoperatorandfunctionofenumtest.e1.E1Identifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferoperatorandfunctionofenumtest.mappedtransferoperatorandfunctionofenumtest.te1.TE1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferoperatorandfunctionofenumtest.mappedtransferoperatorandfunctionofenumtest.te1.TE1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferoperatorandfunctionofenumtest.mappedtransferoperatorandfunctionofenumtest.testliteral.TestLiteral;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferOperatorAndFunctionOfEnumTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferOperatorAndFunctionOfEnumTest extends AbstractJslTest {

    @Inject
    E1Dao e1Dao;

    @Inject
    TE1Dao te1Dao;

    @Override
    public Module getModelDaoModule() {
        return new MappedTransferOperatorAndFunctionOfEnumTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferOperatorAndFunctionOfEnumTest";
    }

    /**
     * Testing the operators and functions of enum on a transfer object.
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
    @TestCase("EnumOperatorsAndFunctionsOnTransfer")
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
            "REQ-EXPR-020",
            "REQ-SRV-002"
    })
    void testEnumOperatorsAndFunctionsOnTransfer() {

        TE1 te1 = te1Dao.create(TE1.builder().build());
        assertEquals(TestLiteral.ZZ00, te1.getF1().orElseThrow());
        assertEquals(TestLiteral.Aaa01, te1.getF2().orElseThrow());
        assertTrue(te1.getF3().orElseThrow());
        assertTrue(te1.getF4().orElseThrow());
        assertFalse(te1.getF5().orElseThrow());
        assertFalse(te1.getF6().orElseThrow());
        assertFalse(te1.getF7().orElseThrow());
        assertTrue(te1.getF8().orElseThrow());
        assertEquals("ZZ00", te1.getF9().orElseThrow());

        // entity representation

        E1 e1 = e1Dao.getById(te1.identifier().adaptTo(E1Identifier.class)).orElseThrow();
        assertEquals(TestLiteral.ZZ00, e1.getF1().orElseThrow());
        assertEquals(TestLiteral.Aaa01, e1.getF2().orElseThrow());
        assertTrue(e1.getF3().orElseThrow());
        assertTrue(e1.getF4().orElseThrow());
        assertFalse(e1.getF5().orElseThrow());
        assertFalse(e1.getF6().orElseThrow());
        assertFalse(e1.getF7().orElseThrow());
        assertTrue(e1.getF8().orElseThrow());
        assertEquals("ZZ00", e1.getF9().orElseThrow());


        te1.setF1(TestLiteral.Aaa01);
        te1 = te1Dao.update(te1);
        assertEquals(TestLiteral.Aaa01, te1.getF1().orElseThrow());
        assertEquals(TestLiteral.Aaa01, te1.getF2().orElseThrow());
        assertFalse(te1.getF3().orElseThrow());
        assertTrue(te1.getF4().orElseThrow());
        assertFalse(te1.getF5().orElseThrow());
        assertTrue(te1.getF6().orElseThrow());
        assertTrue(te1.getF7().orElseThrow());
        assertFalse(te1.getF8().orElseThrow());
        assertEquals("Aaa01", te1.getF9().orElseThrow());

        // entity rep
        e1 = e1Dao.getById(te1.identifier().adaptTo(E1Identifier.class)).orElseThrow();
        assertEquals(TestLiteral.Aaa01, e1.getF1().orElseThrow());
        assertEquals(TestLiteral.Aaa01, e1.getF2().orElseThrow());
        assertFalse(e1.getF3().orElseThrow());
        assertTrue(e1.getF4().orElseThrow());
        assertFalse(e1.getF5().orElseThrow());
        assertTrue(e1.getF6().orElseThrow());
        assertTrue(e1.getF7().orElseThrow());
        assertFalse(e1.getF8().orElseThrow());
        assertEquals("Aaa01", e1.getF9().orElseThrow());


        te1.setF1(TestLiteral.Ccc03);
        te1.setF2(TestLiteral.ZZ00);
        te1 = te1Dao.update(te1);
        assertEquals(TestLiteral.Ccc03, te1.getF1().orElseThrow());
        assertEquals(TestLiteral.ZZ00, te1.getF2().orElseThrow());
        assertFalse(te1.getF3().orElseThrow());
        assertFalse(te1.getF4().orElseThrow());
        assertTrue(te1.getF5().orElseThrow());
        assertTrue(te1.getF6().orElseThrow());
        assertFalse(te1.getF7().orElseThrow());
        assertTrue(te1.getF8().orElseThrow());
        assertEquals("Ccc03", te1.getF9().orElseThrow());

        // entity representation
        e1 = e1Dao.getById(te1.identifier().adaptTo(E1Identifier.class)).orElseThrow();
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
}
