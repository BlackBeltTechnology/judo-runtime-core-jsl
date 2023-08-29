package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.a.AIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.c.CIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.d.DIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.alltypeoftransferobjects.alltypeoftransferobjects.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AllTypeOfTransferObjectsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith({JudoDatasourceByClassExtension.class, JudoRuntimeExtension.class})
public class CreationOfTransferObjects {


    public Module getModelDaoModule() {
        return new AllTypeOfTransferObjectsDaoModules();
    }

    static public String getModelName() {
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

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Inject
    DDao dDao;

    @BeforeAll
    static public void prepare(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.prepare(getModelName(), datasource);
    }

    @BeforeEach
    protected void init(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.init(getModelDaoModule(),this, datasource);
        fixture.beginTransaction();
    }

    @AfterEach
    protected void tearDown(JudoRuntimeFixture fixture) {
        fixture.tearDown();
    }

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
     * @positiveRequirements REQ-SRV-002
     *
     * @scenario
     *
     *  Create each type of transfer object mapping variations and check the that doesn't throw errors.
     *
     */
    @Test
    @TestCase("CreationOfTransferObjectsWithDao")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004"
    })
    void testCreationOfTransferObjects() {

        assertDoesNotThrow(() -> {
            transferADao.create(TransferA.builder().build());
        });
        assertDoesNotThrow(() -> {
            transferBDao.create(TransferB.builder().build());
        });
        assertDoesNotThrow(() -> {
            transferCDao.create(TransferC.builder().build());
        });
        assertDoesNotThrow(() -> {
            transferDDao.create(TransferD.builder().build());
        });

        TransferA transferA = transferADao.create(TransferA.builder().build());
        TransferB transferB = transferBDao.create(TransferB.builder().build());
        TransferC transferC = transferCDao.create(TransferC.builder().build());
        TransferD transferD = transferDDao.create(TransferD.builder().build());

        assertTrue(transferADao.getById(transferA.identifier()).isPresent());
        assertTrue(transferBDao.getById(transferB.identifier()).isPresent());
        assertTrue(transferCDao.getById(transferC.identifier()).isPresent());
        assertTrue(transferDDao.getById(transferD.identifier()).isPresent());

        assertTrue(aDao.getById(transferA.identifier().adaptTo(AIdentifier.class)).isPresent());
        assertTrue(bDao.getById(transferB.identifier().adaptTo(BIdentifier.class)).isPresent());
        assertTrue(cDao.getById(transferC.identifier().adaptTo(CIdentifier.class)).isPresent());
        assertTrue(dDao.getById(transferD.identifier().adaptTo(DIdentifier.class)).isPresent());

    }


}
