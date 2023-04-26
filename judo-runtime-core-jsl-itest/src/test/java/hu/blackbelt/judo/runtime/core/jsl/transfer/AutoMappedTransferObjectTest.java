package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.abstractentityfields.AbstractEntityFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.abstractentityfields.AbstractEntityFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.automappedtransferonabstractoptionalfields.AutoMappedTransferOnAbstractOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.automappedtransferonabstractoptionalfields.AutoMappedTransferOnAbstractOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.automappedtransferonoptionalfields.AutoMappedTransferOnOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.automappedtransferonoptionalfields.AutoMappedTransferOnOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.automappedtransferonrequiredfields.AutoMappedTransferOnRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfermodel.automappedtransfermodel.automappedtransferonrequiredfields.AutoMappedTransferOnRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AutoMappedTransferObjectTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new AutoMappedTransferModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "AutoMappedTransferModel";
    }

    @Inject
    AutoMappedTransferOnOptionalFieldsDao autoMappedTransferOnOptionalFieldsDao;

    @Inject
    AutoMappedTransferOnRequiredFieldsDao autoMappedTransferOnRequiredFieldsDao;

    @Inject
    AutoMappedTransferOnAbstractOptionalFieldsDao autoMappedTransferOnAbstractOptionalFieldsDao;

    @Inject
    AbstractEntityFieldsDao abstractEntityFieldsDao;

    /**
     * [DESCRIPTION]
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferModel.jsl
     * model AutoMappedTransferModel;
     *
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("AutoMappedTransferObjectFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTransferObjectsCreationWithValidFields() {

        //check all field is mapped
        AutoMappedTransferOnOptionalFields autoMappedTransferOnOptionalFields =
                autoMappedTransferOnOptionalFieldsDao.create(AutoMappedTransferOnOptionalFields.builder().build());

        assertTrue(autoMappedTransferOnOptionalFields.getBoolAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getBinaryAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getIntegerAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getRegexAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getScaledAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getDateAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getTimeAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getTimestampAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getEnumAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getStringAttr().isEmpty());


    }

    @Test
    @TestCase("AutoMappedTransferObjectFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTOAbstractEntity() {

        //check all field is mapped
        AutoMappedTransferOnAbstractOptionalFields autoMappedTransferOnAbstractOptionalFields =
                autoMappedTransferOnAbstractOptionalFieldsDao.create(AutoMappedTransferOnAbstractOptionalFields.builder().build());

        assertTrue(autoMappedTransferOnAbstractOptionalFields.getBoolAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getBinaryAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getIntegerAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getRegexAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getScaledAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getDateAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getTimeAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getTimestampAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getEnumAttr().isEmpty());
        assertTrue(autoMappedTransferOnAbstractOptionalFields.getStringAttr().isEmpty());

        assertEquals(1,abstractEntityFieldsDao.getAll().size());

        AbstractEntityFields a = abstractEntityFieldsDao.create(AbstractEntityFields.builder().withStringAttr("Hello").build());

        assertTrue(a.getStringAttr().isPresent());

        assertEquals(2,abstractEntityFieldsDao.getAll().size());


    }

}
