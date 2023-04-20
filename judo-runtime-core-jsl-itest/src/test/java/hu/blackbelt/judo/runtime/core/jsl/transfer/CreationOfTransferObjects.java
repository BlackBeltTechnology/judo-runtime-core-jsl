package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transfera.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferd.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AllTypeOfTransferObjectsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
     * Test transfer object Dao to create new Transfer objects successfully
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AllTypeOfTransferObjects.jsl
     * model AllTypeOfTransferObjects;
     *
     * import judo::types;
     *
     * entity A {
     * }
     *
     * transfer TransferA (A a) {
     * }
     *
     * transfer UnmappedTransfer {
     * }
     *
     * entity B {
     *     field String nameB;
     * }
     *
     * transfer TransferB (B b) {
     *     field String attributeTransferB;
     *     field String mappedNameB maps b.nameB;
     * }
     *
     * entity C {
     *     field String nameC;
     * }
     *
     * transfer TransferC maps C as c {
     *     field String attributeTransferC;
     *     field String readCName reads c.nameC;
     * }
     *
     * entity D {
     *     field String nameD;
     * }
     *
     * transfer TransferD maps D as d {
     *     field String attributeTransferD;
     *
     *     constructor {
     *         self.attributeTransferD = "SomeName";
     *     }
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
     * @scenario Create each type of transfer object mapping variations and check the that doesn't throw errors.
     *
     */
    @Test
    @TestCase("CreationOfTransferObjectsWithDao")
    @Requirement(reqs = {
            "",
    })
    void testCreationOfTransferObjects() {

        assertDoesNotThrow(()->{transferADao.create(TransferA.builder().build());});
        assertDoesNotThrow(()->{transferBDao.create(TransferB.builder().build());});
        assertDoesNotThrow(()->{transferCDao.create(TransferC.builder().build());});
        assertDoesNotThrow(()->{transferDDao.create(TransferD.builder().build());});

    }

}
