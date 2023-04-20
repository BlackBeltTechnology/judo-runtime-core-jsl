package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transfera.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.a.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.b.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.c.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferd.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.d.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AllTypeOfTransferObjectsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


@Slf4j
public class CreationOfTransferObjects extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new AllTypeOfTransferObjectsDaoModules();
    }

    @Override
    public String getModelName() {
        return "AllTypeOfTransferObjects";
    }

    @Inject
    TransferADao transferADao;

    @Inject
    TransferBDao transferBDao;

    @Inject
    TransferCDao transferCDao;

    @Inject
    TransferDDao transferDDao;

    /**
     * Test transfer object Dao to create new Transfer object successfully
     *
     * @prerequisites
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferSDKTestModel.jsl
     *  model TransferSDKTestModel;
     *
     * entity A {
     *
     * }
     *
     * transfer TransferA (A a) {
     *
     * }
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("CreationOfTransferObjectsWithDao")
    @Requirement(reqs = {
            "",
    })
    void testCreationOfTransferObjects() {

        

    }

}
