package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;

import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testregexvalidation.testregexvalidation.textregexentity.TextRegexEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testregexvalidation.testregexvalidation.textregexentity.TextRegexEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestRegexValidationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestRegex extends AbstractJslTest {

    @Inject
    TextRegexEntityDao textRegexEntityDao;

    @Override
    public Module getModelDaoModule() {
        return new TestRegexValidationDaoModules();
    }

    @Override
    public String getModelName() {
        return "TestRegexValidation";
    }

    /**
     * Text Regex Validation
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TestRegexValidation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("testRegexValidation")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-SRV-001"
    })
    public void testRegexValidation() {
        TextRegexEntity entity = textRegexEntityDao.create(TextRegexEntity.builder().build());
        System.out.println(entity.getRegexAttr());
        entity.setRegexAttr("+36");
        //textRegexEntityDao.update(entity);
    }
}
