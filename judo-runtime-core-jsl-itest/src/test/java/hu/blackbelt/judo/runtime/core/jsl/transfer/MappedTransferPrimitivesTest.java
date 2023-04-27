package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;

import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.mappedtransferwithreadsprimitivefields.MappedTransferWithReadsPrimitiveFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.mappedtransferwithreadsprimitivefields.MappedTransferWithReadsPrimitiveFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
public class MappedTransferPrimitivesTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new MappedTransferPrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "AllTypeOfTransferObjects";
    }


    @Inject
    MappedTransferWithReadsPrimitiveFieldsDao mappedTransferWithReadsPrimitiveFieldsDao;

    /**
     *
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel .jsl
     * model ;
     *
     * @positiveRequirements REQ-SRV-002
     *
     * @scenario
     *
     */
    @Test
    @TestCase("CreationOfTransferObjectsWithDao")
    @Requirement(reqs = {
            "",
    })
    void testMappedTransferReadsPrimitives() {
        mappedTransferWithReadsPrimitiveFieldsDao.create(MappedTransferWithReadsPrimitiveFields.builder().build());
    }

}
