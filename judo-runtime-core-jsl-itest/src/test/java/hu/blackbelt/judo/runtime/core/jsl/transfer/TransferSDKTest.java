package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;

import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfersdktestmodel.transfersdktestmodel.a.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferSDKTestModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class TransferSDKTest extends AbstractJslTest {


    @Override
    public Module getModelDaoModule() {
        return new TransferSDKTestModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "TransferSDKTestModel";
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
    @TestCase("TransferSDKTest")
    @Requirement(reqs = {
            "REQ-SYNT-001",
    })
    void test() {

    }

}
