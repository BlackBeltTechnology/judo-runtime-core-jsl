package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferSDKTestModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;


@Slf4j
public class TransferField extends AbstractJslTest {


    @Override
    public Module getModelDaoModule() {
        return null;
    }

    @Override
    public String getModelName() {
        return "";
    }

    /**
     * [Description]
     *
     * @prerequisites
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel [needAName].jsl
     *  model [needAName];
     *
     * Body
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("TransferField")
    @Requirement(reqs = {
            "REQ-SYNT-001",
    })
    void test() {

    }

}
