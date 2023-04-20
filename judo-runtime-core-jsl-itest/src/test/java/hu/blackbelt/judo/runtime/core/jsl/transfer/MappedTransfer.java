package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfersdktestmodel.transfersdktestmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfersdktestmodel.transfersdktestmodel.b.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfersdktestmodel.transfersdktestmodel.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfersdktestmodel.transfersdktestmodel.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfersdktestmodel.transfersdktestmodel.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfersdktestmodel.transfersdktestmodel.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferSDKTestModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransfer extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new TransferSDKTestModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "TransferSDKTestModel";
    }

    @Inject
    ADao aDao;

    @Inject
    TransferADao transferADao;

    @Inject
    BDao bDao;

    @Inject
    TransferBDao transferBDao;

    /**
     * Test mapped transfer object Dao to create new Transfer object
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
    @TestCase("MappedTransfer")
    @Requirement(reqs = {
            "",
    })
    void testSomething() {

        //assertDoesNotThrow(()->{transferADao.create(TransferA.builder().build());});

        //B b = bDao.create(B.builder().withAttributeB("entityB").build());

        //TransferB transferB = transferBDao.create(TransferB.builder().withAttributeTransferB("B").build());



        //assertEquals(Optional.of("entityB"),transferB.getMapped());
    }

}
