package hu.blackbelt.judo.runtime.core.jsl;

import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PrimitivesDaoModules;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.google.inject.Module;

import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ModelTC022DaoModules;

public class PrimitiveTypeUseOnlyExpression extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        // TODO JNG-4617
        return new ModelTC022DaoModules();
    }
    
    @Override
    public String getModelName() {
        return "modelTC022";
    }
    
    /**
     * This test case checks that a static function of a primitive type is works even if the primitive type isn't
     * appeared in any field definitions in the model.    
     * 
     * @prerequisites Nothing
     * 
     * @type Behaviour
     * 
     * @others The JSL model of this test is commented out in the pom.xml because it is  the cause of a build exception.
     *  See: JNG-4617. This test case can be implemented and activated when the bug will be solved.
     *  
     *  If the parts of the expression won't evaluate in the "same time". The result of this test case is divergent.
     *  Sometimes OK, sometimes Failed. In this case the equality need to replace with an investigation of the subtraction's result.
     * 
     * @jslModel modelTC022.jsl
     * 
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create an EnvVars entity instance (evs1) with the default values.
     *
     *  . Check the values of the following fields of the new entity instance (evs1).
     *      * f1 == true
     *      * f2 == true
     *      * f3 == true
     *
     *  . The test is passed if all steps have been completed with the specified results.
     */
    @Test
    @Disabled("JNG-4617")
    @TestCase("TC022")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-TYPE-001",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-MDL-001",
            "REQ-MDL-003",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-009",
            "REQ-EXPR-012"
    })
    void testPrimitiveTypeUseOnlyExpression() {
        // TODO JNG-4617
    }
    
}
