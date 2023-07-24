package hu.blackbelt.judo.runtime.core.jsl;


import lombok.extern.slf4j.Slf4j;
import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.importedtypeswithnofielddefinitions.importedtypeswithnofielddefinitions.testentity.TestEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.importedtypeswithnofielddefinitions.importedtypeswithnofielddefinitions.testentity.TestEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ImportedTypesWithNoFieldDefinitionsDaoModules;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ImportedTypesWithNoFieldDefinitions  extends AbstractJslTest {

    @Inject
    TestEntityDao testEntityDao;


    @Override
    public Module getModelDaoModule() {
        return new ImportedTypesWithNoFieldDefinitionsDaoModules();
    }

    @Override
    public String getModelName() {
        return "ImportedTypesWithNoFieldDefinitions";
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
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create an EnvVars entity instance (evs1) with the default values.
     *
     *  . Check the values of the following fields of the new entity instance (evs1).
     *      * dateSmaller == true
     *      * timeSmaller == true
     *      * timestampSmaller == true
     *
     *  . The test is passed if all steps have been completed with the specified results.
     */
    @Test
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
        TestEntity evs1 = testEntityDao.create(TestEntity.builder().build());

        assertTrue(evs1.getDateSmaller().orElseThrow());
        assertTrue(evs1.getTimeSmaller().orElseThrow());
        assertTrue(evs1.getTimestampSmaller().orElseThrow());
    }
}
