package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.myenum.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entitywithoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entitywithrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entitywithoptionalprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.entitywithrequiredprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonoptionalprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfer.automappedtransfer.automappedtransferonrequiredprimitivedefaults.*;
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
    EntityWithOptionalPrimitivesDao entityWithOptionalPrimitivesDao;

    @Inject
    EntityWithRequiredPrimitivesDao entityWithRequiredPrimitivesDao;

    @Inject
    EntityWithOptionalPrimitiveDefaultsDao entityWithOptionalPrimitiveDefaultsDao;

    @Inject
    EntityWithRequiredPrimitiveDefaultsDao entityWithRequiredPrimitiveDefaultsDao;

    @Inject
    AutoMappedTransferOnOptionalPrimitivesDao autoMappedTransferOnOptionalPrimitivesDao;

    @Inject
    AutoMappedTransferOnRequiredPrimitivesDao autoMappedTransferOnRequiredPrimitivesDao;

    @Inject
    AutoMappedTransferOnOptionalPrimitiveDefaultsDao autoMappedTransferOnOptionalPrimitiveDefaultsDao;

    @Inject
    AutoMappedTransferOnRequiredPrimitiveDefaultsDao autoMappedTransferOnRequiredPrimitiveDefaultsDao;

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
        AutoMappedTransferOnOptionalPrimitives autoMappedTransferOnOptionalPrimitives =
                autoMappedTransferOnOptionalPrimitivesDao.create(AutoMappedTransferOnOptionalPrimitives.builder().build());

        assertTrue(autoMappedTransferOnOptionalPrimitives.getBoolAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getIntegerAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getRegexAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getScaledAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getDateAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getTimeAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getTimestampAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getEnumAttr().isEmpty());
        assertTrue(autoMappedTransferOnOptionalPrimitives.getStringAttr().isEmpty());

        Optional<EntityWithOptionalPrimitives> entityWithOptionalPrimitivesOptional = entityWithOptionalPrimitivesDao.getById(autoMappedTransferOnOptionalPrimitives.adaptTo(EntityWithOptionalPrimitivesIdentifier.class));
        assertTrue(entityWithOptionalPrimitivesOptional.isPresent());
        EntityWithOptionalPrimitives entityWithOptionalPrimitives = entityWithOptionalPrimitivesOptional.orElseThrow();

        assertTrue(entityWithOptionalPrimitives.getBoolAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getIntegerAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getRegexAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getScaledAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getDateAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getTimeAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getTimestampAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getEnumAttr().isEmpty());
        assertTrue(entityWithOptionalPrimitives.getStringAttr().isEmpty());

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
    @Disabled("JNG-4888")
    @TestCase("CheckOfTransferAutoMappedTransferObjectsCreationWithValidOptionalPrimitiveDefaultFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTransferObjectsCreationWithValidOptionalPrimitiveDefaultFields() {

        //check all field is mapped
        AutoMappedTransferOnOptionalPrimitiveDefaults transferOnOptionalPrimitiveDefaults = autoMappedTransferOnOptionalPrimitiveDefaultsDao.create(AutoMappedTransferOnOptionalPrimitiveDefaults.builder().build());

        assertEquals(1, autoMappedTransferOnOptionalPrimitiveDefaultsDao.countAll());
        assertEquals(Optional.of(1), transferOnOptionalPrimitiveDefaults.getIntegerAttr());
        assertEquals(Optional.of(2.34), transferOnOptionalPrimitiveDefaults.getScaledAttr());
        assertEquals(Optional.of("test"), transferOnOptionalPrimitiveDefaults.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferOnOptionalPrimitiveDefaults.getRegexAttr());
        assertEquals(Optional.of(true), transferOnOptionalPrimitiveDefaults.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOnOptionalPrimitiveDefaults.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOnOptionalPrimitiveDefaults.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOnOptionalPrimitiveDefaults.getTimeAttr());
        assertEquals(Optional.of(MyEnum.Bombastic), transferOnOptionalPrimitiveDefaults.getEnumAttr());

        Optional<EntityWithOptionalPrimitiveDefaults> entityWithOptionalPrimitiveDefaults = entityWithOptionalPrimitiveDefaultsDao.getById(transferOnOptionalPrimitiveDefaults.adaptTo(EntityWithOptionalPrimitiveDefaultsIdentifier.class));

        assertTrue(entityWithOptionalPrimitiveDefaults.isPresent());

        EntityWithOptionalPrimitiveDefaults entityWithOptionalPrimitiveDefaults1 = entityWithOptionalPrimitiveDefaults.orElseThrow();

        assertEquals(Optional.of(1), entityWithOptionalPrimitiveDefaults1.getIntegerAttr());
        assertEquals(Optional.of(2.34), entityWithOptionalPrimitiveDefaults1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), entityWithOptionalPrimitiveDefaults1.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), entityWithOptionalPrimitiveDefaults1.getRegexAttr());
        assertEquals(Optional.of(true), entityWithOptionalPrimitiveDefaults1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityWithOptionalPrimitiveDefaults1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityWithOptionalPrimitiveDefaults1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithOptionalPrimitiveDefaults1.getTimeAttr());
        assertEquals(Optional.of(MyEnum.Bombastic), entityWithOptionalPrimitiveDefaults1.getEnumAttr());

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
                () -> autoMappedTransferOnRequiredPrimitivesDao.create(AutoMappedTransferOnRequiredPrimitives.builder().build())
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
        AutoMappedTransferOnRequiredPrimitives transferOnRequiredPrimitives = autoMappedTransferOnRequiredPrimitivesDao.create(
                AutoMappedTransferOnRequiredPrimitives
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

        assertEquals(1, autoMappedTransferOnRequiredPrimitivesDao.countAll());
        assertEquals(1, transferOnRequiredPrimitives.getIntegerAttr());
        assertEquals(2.34, transferOnRequiredPrimitives.getScaledAttr());
        assertEquals("Hello there", transferOnRequiredPrimitives.getStringAttr());
        assertEquals("+36 333-333-3333", transferOnRequiredPrimitives.getRegexAttr());
        assertEquals(true, transferOnRequiredPrimitives.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), transferOnRequiredPrimitives.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferOnRequiredPrimitives.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), transferOnRequiredPrimitives.getTimeAttr());
        assertEquals(MyEnum.Bombastic, transferOnRequiredPrimitives.getEnumAttr());

        Optional<EntityWithRequiredPrimitives> entityWithRequiredPrimitivesOptional = entityWithRequiredPrimitivesDao.getById(transferOnRequiredPrimitives.adaptTo(EntityWithRequiredPrimitivesIdentifier.class));

        assertTrue(entityWithRequiredPrimitivesOptional.isPresent());

        EntityWithRequiredPrimitives withRequiredPrimitives = entityWithRequiredPrimitivesOptional.orElseThrow();

        assertEquals(1, withRequiredPrimitives.getIntegerAttr());
        assertEquals(2.34, withRequiredPrimitives.getScaledAttr());
        assertEquals("Hello there", withRequiredPrimitives.getStringAttr());
        assertEquals("+36 333-333-3333", withRequiredPrimitives.getRegexAttr());
        assertEquals(true, withRequiredPrimitives.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), withRequiredPrimitives.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), withRequiredPrimitives.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), withRequiredPrimitives.getTimeAttr());
        assertEquals(MyEnum.Bombastic, withRequiredPrimitives.getEnumAttr());

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
    @Disabled("JNG-4888")
    @TestCase("CheckOfTransferAutoMappedTransferObjectsCreationWithValidRequiredPrimitiveDefaultFields")
    @Requirement(reqs = {
            "",
    })
    void testCheckOfTransferAutoMappedTransferObjectsCreationWithValidRequiredPrimitiveDefaultFields() {

        //check all field is mapped
        AutoMappedTransferOnRequiredPrimitiveDefaults transferOnRequiredPrimitiveDefaults = autoMappedTransferOnRequiredPrimitiveDefaultsDao.create(AutoMappedTransferOnRequiredPrimitiveDefaults.builder().build());

        assertEquals(1, autoMappedTransferOnRequiredPrimitiveDefaultsDao.countAll());
        assertEquals(1, transferOnRequiredPrimitiveDefaults.getIntegerAttr());
        assertEquals(2.34, transferOnRequiredPrimitiveDefaults.getScaledAttr());
        assertEquals("Hello there", transferOnRequiredPrimitiveDefaults.getStringAttr());
        assertEquals("+36 333-333-3333", transferOnRequiredPrimitiveDefaults.getRegexAttr());
        assertEquals(true, transferOnRequiredPrimitiveDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), transferOnRequiredPrimitiveDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferOnRequiredPrimitiveDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), transferOnRequiredPrimitiveDefaults.getTimeAttr());
        assertEquals(MyEnum.Bombastic, transferOnRequiredPrimitiveDefaults.getEnumAttr());

        Optional<EntityWithRequiredPrimitiveDefaults> entityWithRequiredPrimitiveDefaultsOptional = entityWithRequiredPrimitiveDefaultsDao.getById(transferOnRequiredPrimitiveDefaults.adaptTo(EntityWithRequiredPrimitiveDefaultsIdentifier.class));

        assertTrue(entityWithRequiredPrimitiveDefaultsOptional.isPresent());

        EntityWithRequiredPrimitiveDefaults entityWithRequiredPrimitiveDefaults = entityWithRequiredPrimitiveDefaultsOptional.orElseThrow();

        assertEquals(1, entityWithRequiredPrimitiveDefaults.getIntegerAttr());
        assertEquals(2.34, entityWithRequiredPrimitiveDefaults.getScaledAttr());
        assertEquals("Hello there", entityWithRequiredPrimitiveDefaults.getStringAttr());
        assertEquals("+36 333-333-3333", entityWithRequiredPrimitiveDefaults.getRegexAttr());
        assertEquals(true, entityWithRequiredPrimitiveDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityWithRequiredPrimitiveDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entityWithRequiredPrimitiveDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityWithRequiredPrimitiveDefaults.getTimeAttr());
        assertEquals(MyEnum.Bombastic, entityWithRequiredPrimitiveDefaults.getEnumAttr());

    }

    private Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}
