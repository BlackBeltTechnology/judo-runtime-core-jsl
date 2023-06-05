package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.myenum.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entityoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entityrequiredfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entitywithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entityrequiredwithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonentitywithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonrequiredfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonentityrequiredwithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import liquibase.pro.packaged.M;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

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
    EntityOptionalFieldsDao entityOptionalFieldsDao;

    @Inject
    EntityWithPrimitiveDefaultsDao entityWithPrimitiveDefaultsDao;

    @Inject
    AutoMappedTransferOnEntityWithPrimitiveDefaultsDao autoMappedTransferOnEntityWithPrimitiveDefaultsDao;

    @Inject
    EntityRequiredFieldsDao entityRequiredFieldsDao;

    @Inject
    AutoMappedTransferOnRequiredFieldsDao autoMappedTransferOnRequiredFieldsDao;

    @Inject
    EntityRequiredWithPrimitiveDefaultsDao entityRequiredWithPrimitiveDefaultsDao;

    @Inject
    AutoMappedTransferOnEntityRequiredWithPrimitiveDefaultsDao autoMappedTransferOnEntityRequiredWithPrimitiveDefaultsDao;


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
    @TestCase("CheckOfTransferAutoMappedTransferObjectsCreationWithValidOptionalPrimitiveFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTransferObjectsCreationWithValidOptionalPrimitiveFields() {

        //check all field is mapped
        AutoMappedTransferOnOptionalFields autoMappedTransferOnOptionalFields =
                autoMappedTransferOnOptionalFieldsDao.create(AutoMappedTransferOnOptionalFields.builder().build());

        assertTrue(autoMappedTransferOnOptionalFields.getBoolAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getIntegerAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getRegexAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getScaledAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getDateAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getTimeAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getTimestampAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getEnumAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalFields.getStringAttr().isEmpty());

        Optional<EntityOptionalFields> entityOptionalFieldsOptional = entityOptionalFieldsDao.getById(autoMappedTransferOnOptionalFields.adaptTo(EntityOptionalFieldsIdentifier.class));
        assertTrue(entityOptionalFieldsOptional.isPresent());
        EntityOptionalFields entityOptionalFields = entityOptionalFieldsOptional.orElseThrow();

        assertTrue(entityOptionalFields.getBoolAttr().isEmpty());
        assertTrue(entityOptionalFields.getIntegerAttr().isEmpty());
        assertTrue(entityOptionalFields.getRegexAttr().isEmpty());
        assertTrue(entityOptionalFields.getScaledAttr().isEmpty());
        assertTrue(entityOptionalFields.getDateAttr().isEmpty());
        assertTrue(entityOptionalFields.getTimeAttr().isEmpty());
        assertTrue(entityOptionalFields.getTimestampAttr().isEmpty());
        assertTrue(entityOptionalFields.getEnumAttr().isEmpty());
        assertTrue(entityOptionalFields.getStringAttr().isEmpty());

    }

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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @Disabled("ProblemWithDefaultValuesMapping")
    @TestCase("CheckOfTransferAutoMappedTransferObjectsCreationWithValidOptionalPrimitiveDefaultFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTransferObjectsCreationWithValidOptionalPrimitiveDefaultFields() {

        //check all field is mapped
        AutoMappedTransferOnEntityWithPrimitiveDefaults transferOnEntityWithPrimitiveDefaults = autoMappedTransferOnEntityWithPrimitiveDefaultsDao.create(AutoMappedTransferOnEntityWithPrimitiveDefaults.builder().build());

        assertEquals(1, autoMappedTransferOnEntityWithPrimitiveDefaultsDao.countAll());
        assertEquals(Optional.of(1), transferOnEntityWithPrimitiveDefaults.getIntegerAttr());
        assertEquals(Optional.of(2.34), transferOnEntityWithPrimitiveDefaults.getScaledAttr());
        assertEquals(Optional.of("test"), transferOnEntityWithPrimitiveDefaults.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferOnEntityWithPrimitiveDefaults.getRegexAttr());
        assertEquals(Optional.of(true), transferOnEntityWithPrimitiveDefaults.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOnEntityWithPrimitiveDefaults.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOnEntityWithPrimitiveDefaults.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOnEntityWithPrimitiveDefaults.getTimeAttr());
        assertEquals(Optional.of(MyEnum.Bombastic), transferOnEntityWithPrimitiveDefaults.getEnumAttr());

        Optional<EntityWithPrimitiveDefaults> entityWithPrimitiveDefaultsOptional = entityWithPrimitiveDefaultsDao.getById(transferOnEntityWithPrimitiveDefaults.adaptTo(EntityWithPrimitiveDefaultsIdentifier.class));

        assertTrue(entityWithPrimitiveDefaultsOptional.isPresent());

        EntityWithPrimitiveDefaults entityWithPrimitivesDefault = entityWithPrimitiveDefaultsOptional.orElseThrow();

        assertEquals(Optional.of(1), entityWithPrimitivesDefault.getIntegerAttr());
        assertEquals(Optional.of(2.34), entityWithPrimitivesDefault.getScaledAttr());
        assertEquals(Optional.of("Hello there"), entityWithPrimitivesDefault.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), entityWithPrimitivesDefault.getRegexAttr());
        assertEquals(Optional.of(true), entityWithPrimitivesDefault.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityWithPrimitivesDefault.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityWithPrimitivesDefault.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithPrimitivesDefault.getTimeAttr());
        assertEquals(Optional.of(MyEnum.Bombastic), entityWithPrimitivesDefault.getEnumAttr());

    }

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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("CheckOfTransferAutoMappedTransferObjectsCreationWithValidRequiredPrimitiveFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTransferObjectsCreationWithValidRequiredPrimitiveFields() {

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> autoMappedTransferOnRequiredFieldsDao.create(AutoMappedTransferOnRequiredFields.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchMissingAttribute("integerAttr"),
                matchMissingAttribute("scaledAttr"),
                matchMissingAttribute("stringAttr"),
                matchMissingAttribute("regexAttr"),
                matchMissingAttribute("boolAttr"),
                matchMissingAttribute("dateAttr"),
                matchMissingAttribute("timestampAttr"),
                matchMissingAttribute("timeAttr"),
                matchMissingAttribute("enumAttr")
        ));


        //check all field is mapped
        AutoMappedTransferOnRequiredFields transferOnRequiredFields = autoMappedTransferOnRequiredFieldsDao.create(
                AutoMappedTransferOnRequiredFields
                        .builder()
                        .withIntegerAttr(1)
                        .withScaledAttr(2.34)
                        .withStringAttr("Hello there")
                        .withRegexAttr("+36 333-333-3333")
                        .withBoolAttr(true)
                        .withDateAttr(LocalDate.of(2022, 7, 11))
                        .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                        .withTimeAttr(LocalTime.parse("23:59:59"))
                        .withEnumAttr(MyEnum.Bombastic)
                        .build()
        );

        assertEquals(1, autoMappedTransferOnRequiredFieldsDao.countAll());
        assertEquals(1, transferOnRequiredFields.getIntegerAttr());
        assertEquals(2.34, transferOnRequiredFields.getScaledAttr());
        assertEquals("Hello there", transferOnRequiredFields.getStringAttr());
        assertEquals("+36 333-333-3333", transferOnRequiredFields.getRegexAttr());
        assertEquals(true, transferOnRequiredFields.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), transferOnRequiredFields.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferOnRequiredFields.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), transferOnRequiredFields.getTimeAttr());
        assertEquals(MyEnum.Bombastic, transferOnRequiredFields.getEnumAttr());

        Optional<EntityRequiredFields> entityRequiredFieldsOptional = entityRequiredFieldsDao.getById(transferOnRequiredFields.adaptTo(EntityRequiredFieldsIdentifier.class));

        assertTrue(entityRequiredFieldsOptional.isPresent());

        EntityRequiredFields entityRequiredFields = entityRequiredFieldsOptional.orElseThrow();

        assertEquals(1, entityRequiredFields.getIntegerAttr());
        assertEquals(2.34, entityRequiredFields.getScaledAttr());
        assertEquals("Hello there", entityRequiredFields.getStringAttr());
        assertEquals("+36 333-333-3333", entityRequiredFields.getRegexAttr());
        assertEquals(true, entityRequiredFields.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityRequiredFields.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entityRequiredFields.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityRequiredFields.getTimeAttr());
        assertEquals(MyEnum.Bombastic, entityRequiredFields.getEnumAttr());

    }

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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @Disabled("ProblemWithDefaultValuesMapping")
    @TestCase("CheckOfTransferAutoMappedTransferObjectsCreationWithValidRequiredPrimitiveDefaultFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTransferObjectsCreationWithValidRequiredPrimitiveDefaultFields() {

        //check all field is mapped
        AutoMappedTransferOnEntityRequiredWithPrimitiveDefaults transferOnEntityRequiredWithPrimitiveDefaults = autoMappedTransferOnEntityRequiredWithPrimitiveDefaultsDao.create(AutoMappedTransferOnEntityRequiredWithPrimitiveDefaults.builder().build());

        assertEquals(1, autoMappedTransferOnEntityRequiredWithPrimitiveDefaultsDao.countAll());
        assertEquals(1, transferOnEntityRequiredWithPrimitiveDefaults.getIntegerAttr());
        assertEquals(2.34, transferOnEntityRequiredWithPrimitiveDefaults.getScaledAttr());
        assertEquals("Hello there", transferOnEntityRequiredWithPrimitiveDefaults.getStringAttr());
        assertEquals("+36 333-333-3333", transferOnEntityRequiredWithPrimitiveDefaults.getRegexAttr());
        assertEquals(true, transferOnEntityRequiredWithPrimitiveDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), transferOnEntityRequiredWithPrimitiveDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferOnEntityRequiredWithPrimitiveDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), transferOnEntityRequiredWithPrimitiveDefaults.getTimeAttr());
        assertEquals(MyEnum.Bombastic, transferOnEntityRequiredWithPrimitiveDefaults.getEnumAttr());

        Optional<EntityRequiredWithPrimitiveDefaults> entityRequiredWithPrimitiveDefaultsOptional = entityRequiredWithPrimitiveDefaultsDao.getById(transferOnEntityRequiredWithPrimitiveDefaults.adaptTo(EntityRequiredWithPrimitiveDefaultsIdentifier.class));

        assertTrue(entityRequiredWithPrimitiveDefaultsOptional.isPresent());

        EntityRequiredWithPrimitiveDefaults entityRequiredWithPrimitiveDefaults = entityRequiredWithPrimitiveDefaultsOptional.orElseThrow();

        assertEquals(1, entityRequiredWithPrimitiveDefaults.getIntegerAttr());
        assertEquals(2.34, entityRequiredWithPrimitiveDefaults.getScaledAttr());
        assertEquals("Hello there", entityRequiredWithPrimitiveDefaults.getStringAttr());
        assertEquals("+36 333-333-3333", entityRequiredWithPrimitiveDefaults.getRegexAttr());
        assertEquals(true, entityRequiredWithPrimitiveDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityRequiredWithPrimitiveDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entityRequiredWithPrimitiveDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityRequiredWithPrimitiveDefaults.getTimeAttr());
        assertEquals(MyEnum.Bombastic, entityRequiredWithPrimitiveDefaults.getEnumAttr());

    }

    private Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}
