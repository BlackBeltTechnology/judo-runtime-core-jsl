package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.abstractentityfields.AbstractEntityFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonabstractoptionalfields.AutoMappedTransferOnAbstractOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonabstractoptionalfields.AutoMappedTransferOnAbstractOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonoptionalfields.AutoMappedTransferOnOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonoptionalfields.AutoMappedTransferOnOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonrequiredfields.AutoMappedTransferOnRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//        /$$$$$$                 /$$$$$$$
//       |_  $$_/                | $$__  $$
//         | $$   /$$$$$$$       | $$  \ $$ /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$$ /$$$$$$$
//         | $$  | $$__  $$      | $$$$$$$//$$__  $$ /$$__  $$ /$$__  $$ /$$__  $$ /$$__  $$ /$$_____//$$_____/
//         | $$  | $$  \ $$      | $$____/| $$  \__/| $$  \ $$| $$  \ $$| $$  \__/| $$$$$$$$|  $$$$$$|  $$$$$$
//         | $$  | $$  | $$      | $$     | $$      | $$  | $$| $$  | $$| $$      | $$_____/ \____  $$\____  $$
//         /$$$$$$| $$  | $$      | $$     | $$      |  $$$$$$/|  $$$$$$$| $$      |  $$$$$$$ /$$$$$$$//$$$$$$$/
//         |______/|__/  |__/      |__/     |__/       \______/  \____  $$|__/       \_______/|_______/|_______/
//                                                               /$$  \ $$
//                                                               |  $$$$$$/
//                                                                \______/

@Slf4j
public class AutoMappedTransferObjectTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new AutoMappedTransferDaoModules();
    }

    @Override
    public String getModelName() {
        return "AutoMappedTransfer";
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
     * @jslModel AutoMappedTransfer.jsl
     * model AutoMappedTransfer;
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

    /**
     * [description]
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel [modelName].jsl
     *
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *
     */
    @Test
    @Disabled("")
    @TestCase("AutoMappedTransferObjectWithAbstractMappedField")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTOAbstractEntity() {

        assertThrows(
                ValidationException.class,
                () -> autoMappedTransferOnAbstractOptionalFieldsDao.create(AutoMappedTransferOnAbstractOptionalFields.builder()
                        .build())
        );

        //TODO If JNG-XXXX implemented add more detailed error massage of here

    }

}
