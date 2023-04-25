package hu.blackbelt.judo.runtime.core.jsl.entity;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.InheritanceModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class InheritanceTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new InheritanceModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "InheritanceModel";
    }

    @Inject
    CDao cDao;

    @Inject
    DDao dDao;

    /**
     * This test check of a child entity contains all the parent entities attributes.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel InheritanceModel.jsl
     *
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *  Create on instance of the C entity
     *
     *  Check all the attributes is present of the parent entity in the C instance.
     *
     *  Create on instance of the D entity
     *
     *  Check all the attributes is present of the parents entities (parentA,ParentB,AbstractParentF) in the D instance.
     *
     */
    @Test
    @TestCase("InheritedEntitiesContainsAllOfParentAttributes")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-EXPR-005",
    })
    void testInheritedEntitiesContainsAllOfParentAttributes() {

        //check all field is inherited
        C c = cDao.create(C.builder().build());

        assertTrue(hasDaoContainsMethod(c,"getNameA"));
        assertTrue(hasDaoContainsMethod(c,"getIdentifierA"));
        assertTrue(hasDaoContainsMethod(c,"getDerivedA"));
        assertTrue(hasDaoContainsMethod(c,"getQueryA"));
        assertTrue(hasDaoContainsMethod(cDao,"queryRelationEntities")); //relation

        //check all field is inherited
        D d = dDao.create(D.builder().build());

        assertTrue(hasDaoContainsMethod(d,"getNameA"));
        assertTrue(hasDaoContainsMethod(d,"getNameB"));
        assertTrue(hasDaoContainsMethod(d,"getNameF"));
        assertTrue(hasDaoContainsMethod(d,"getIdentifierA"));
        assertTrue(hasDaoContainsMethod(d,"getDerivedA"));
        assertTrue(hasDaoContainsMethod(d,"getQueryA"));
        assertTrue(hasDaoContainsMethod(dDao,"queryRelationEntities")); //relation

    }


    public boolean hasDaoContainsMethod(Object object, String methodName) {
        return Arrays.stream(object.getClass().getDeclaredMethods())
                .anyMatch(f -> f.getName().equals(methodName));
    }

}
