package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonderivedwithcomplexprimitiveexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonderivedwithdefaultfieldsexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonderivedwithfieldsexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonderivedwithqueryexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonderivedwithsimpleprimitiveexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.derivedwithcomplexprimitiveexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.derivedwithdefaultfieldsexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.derivedwithfieldsexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.derivedwithqueryexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.derivedwithsimpleprimitiveexpression.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.myenum.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.entitywithoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.entitywithrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.entitywithoptionalprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.entitywithrequiredprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonoptionalprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransferprimitives.automappedtransferprimitives.automappedtransferonrequiredprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
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
public class AutoMappedTransferObjectPrimitivesTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new AutoMappedTransferPrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "AutoMappedTransferPrimitives";
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
    DerivedWithFieldsExpressionDao derivedWithFieldsExpressionDao;

    @Inject
    DerivedWithDefaultFieldsExpressionDao derivedWithDefaultFieldsExpressionDao;

    @Inject
    DerivedWithSimplePrimitiveExpressionDao derivedWithSimplePrimitiveExpressionDao;

    @Inject
    DerivedWithComplexPrimitiveExpressionDao derivedWithComplexPrimitiveExpressionDao;

    @Inject
    DerivedWithQueryExpressionDao derivedWithQueryExpressionDao;

    @Inject
    AutoMappedTransferOnOptionalPrimitivesDao autoMappedTransferOnOptionalPrimitivesDao;

    @Inject
    AutoMappedTransferOnRequiredPrimitivesDao autoMappedTransferOnRequiredPrimitivesDao;

    @Inject
    AutoMappedTransferOnOptionalPrimitiveDefaultsDao autoMappedTransferOnOptionalPrimitiveDefaultsDao;

    @Inject
    AutoMappedTransferOnRequiredPrimitiveDefaultsDao autoMappedTransferOnRequiredPrimitiveDefaultsDao;

    @Inject
    AutoMappedTransferOnDerivedWithFieldsExpressionDao autoMappedTransferOnDerivedWithFieldsExpressionDao;

    @Inject
    AutoMappedTransferOnDerivedWithDefaultFieldsExpressionDao autoMappedTransferOnDerivedWithDefaultFieldsExpressionDao;

    @Inject
    AutoMappedTransferOnDerivedWithSimplePrimitiveExpressionDao autoMappedTransferOnDerivedWithSimplePrimitiveExpressionDao;

    @Inject
    AutoMappedTransferOnDerivedWithComplexPrimitiveExpressionDao autoMappedTransferOnDerivedWithComplexPrimitiveExpressionDao;

    @Inject
    AutoMappedTransferOnDerivedWithQueryExpressionDao autoMappedTransferOnDerivedWithQueryExpressionDao;


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
    @TestCase("AutoMappedTransferCreationWithValidOptionalPrimitiveFields")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationWithValidOptionalPrimitiveFields() {

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
    @TestCase("AutoMappedTransferCreationWithValidOptionalPrimitiveDefaultFields")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationWithValidOptionalPrimitiveDefaultFields() {

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
    @TestCase("AutoMappedTransferCreationWithValidRequiredPrimitiveFields")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationWithValidRequiredPrimitiveFields() {

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
    @TestCase("AutoMappedTransferCreationWithValidRequiredPrimitiveDefaultFields")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationWithValidRequiredPrimitiveDefaultFields() {

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
    @TestCase("AutoMappedTransferCreationValidDerivedWithFieldsExpression")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationValidDerivedWithFieldsExpression() {

        //check all field is mapped
        AutoMappedTransferOnDerivedWithFieldsExpression transferOnDerivedWithFieldsExpression = autoMappedTransferOnDerivedWithFieldsExpressionDao.create(
                AutoMappedTransferOnDerivedWithFieldsExpression
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

        assertEquals(1, autoMappedTransferOnDerivedWithFieldsExpressionDao.countAll());
        assertEquals(Optional.of(1), transferOnDerivedWithFieldsExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), transferOnDerivedWithFieldsExpression.getScaledDerived());
        assertEquals(Optional.of("Hello there"), transferOnDerivedWithFieldsExpression.getStringDerived());
        assertEquals(Optional.of("+36 333-333-3333"), transferOnDerivedWithFieldsExpression.getRegexDerived());
        assertEquals(Optional.of(true), transferOnDerivedWithFieldsExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOnDerivedWithFieldsExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOnDerivedWithFieldsExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOnDerivedWithFieldsExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), transferOnDerivedWithFieldsExpression.getEnumDerived());

        Optional<DerivedWithFieldsExpression> derivedWithFieldsExpressionOptional = derivedWithFieldsExpressionDao.getById(transferOnDerivedWithFieldsExpression.adaptTo(DerivedWithFieldsExpressionIdentifier.class));

        assertTrue(derivedWithFieldsExpressionOptional.isPresent());

        DerivedWithFieldsExpression derivedWithFieldsExpression = derivedWithFieldsExpressionOptional.orElseThrow();

        assertEquals(1, derivedWithFieldsExpressionDao.countAll());
        assertEquals(Optional.of(1), derivedWithFieldsExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), derivedWithFieldsExpression.getScaledDerived());
        assertEquals(Optional.of("Hello there"), derivedWithFieldsExpression.getStringDerived());
        assertEquals(Optional.of("+36 333-333-3333"), derivedWithFieldsExpression.getRegexDerived());
        assertEquals(Optional.of(true), derivedWithFieldsExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), derivedWithFieldsExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), derivedWithFieldsExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), derivedWithFieldsExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), derivedWithFieldsExpression.getEnumDerived());

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
    @TestCase("AutoMappedTransferCreationValidDerivedWithDefaultFieldsExpression")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationValidDerivedWithDefaultFieldsExpression() {

        //check all field is mapped
        AutoMappedTransferOnDerivedWithDefaultFieldsExpression transferOnDerivedWithDefaultFieldsExpression = autoMappedTransferOnDerivedWithDefaultFieldsExpressionDao.create(
                AutoMappedTransferOnDerivedWithDefaultFieldsExpression.builder().build()
        );

        assertEquals(1, autoMappedTransferOnDerivedWithDefaultFieldsExpressionDao.countAll());
        assertEquals(Optional.of(1), transferOnDerivedWithDefaultFieldsExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), transferOnDerivedWithDefaultFieldsExpression.getScaledDerived());
        assertEquals(Optional.of("test"), transferOnDerivedWithDefaultFieldsExpression.getStringDerived());
        assertEquals(Optional.of("+36 333-333-3333"), transferOnDerivedWithDefaultFieldsExpression.getRegexDerived());
        assertEquals(Optional.of(true), transferOnDerivedWithDefaultFieldsExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOnDerivedWithDefaultFieldsExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOnDerivedWithDefaultFieldsExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOnDerivedWithDefaultFieldsExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), transferOnDerivedWithDefaultFieldsExpression.getEnumDerived());

        Optional<DerivedWithDefaultFieldsExpression> derivedWithDefaultFieldsExpressionOptional = derivedWithDefaultFieldsExpressionDao.getById(transferOnDerivedWithDefaultFieldsExpression.adaptTo(DerivedWithDefaultFieldsExpressionIdentifier.class));

        assertTrue(derivedWithDefaultFieldsExpressionOptional.isPresent());

        DerivedWithDefaultFieldsExpression derivedWithDefaultFieldsExpression = derivedWithDefaultFieldsExpressionOptional.orElseThrow();

        assertEquals(1, derivedWithDefaultFieldsExpressionDao.countAll());
        assertEquals(Optional.of(1), derivedWithDefaultFieldsExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), derivedWithDefaultFieldsExpression.getScaledDerived());
        assertEquals(Optional.of("test"), derivedWithDefaultFieldsExpression.getStringDerived());
        assertEquals(Optional.of("+36 (30) 123 1234"), derivedWithDefaultFieldsExpression.getRegexDerived());
        assertEquals(Optional.of(true), derivedWithDefaultFieldsExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), derivedWithDefaultFieldsExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), derivedWithDefaultFieldsExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), derivedWithDefaultFieldsExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), derivedWithDefaultFieldsExpression.getEnumDerived());

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
    @TestCase("AutoMappedTransferCreationDerivedWithSimplePrimitiveExpression")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationDerivedWithSimplePrimitiveExpression() {

        //check all field is mapped
        AutoMappedTransferOnDerivedWithSimplePrimitiveExpression transferOnDerivedWithSimplePrimitiveExpression = autoMappedTransferOnDerivedWithSimplePrimitiveExpressionDao.create(
                AutoMappedTransferOnDerivedWithSimplePrimitiveExpression.builder().build()
        );

        assertEquals(1, autoMappedTransferOnDerivedWithSimplePrimitiveExpressionDao.countAll());
        assertEquals(Optional.of(1), transferOnDerivedWithSimplePrimitiveExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), transferOnDerivedWithSimplePrimitiveExpression.getScaledDerived());
        assertEquals(Optional.of("test"), transferOnDerivedWithSimplePrimitiveExpression.getStringDerived());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferOnDerivedWithSimplePrimitiveExpression.getRegexDerived());
        assertEquals(Optional.of(true), transferOnDerivedWithSimplePrimitiveExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOnDerivedWithSimplePrimitiveExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOnDerivedWithSimplePrimitiveExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOnDerivedWithSimplePrimitiveExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), transferOnDerivedWithSimplePrimitiveExpression.getEnumDerived());

        Optional<DerivedWithSimplePrimitiveExpression> derivedWithSimplePrimitiveExpressionOptional = derivedWithSimplePrimitiveExpressionDao.getById(transferOnDerivedWithSimplePrimitiveExpression.adaptTo(DerivedWithSimplePrimitiveExpressionIdentifier.class));

        assertTrue(derivedWithSimplePrimitiveExpressionOptional.isPresent());

        DerivedWithSimplePrimitiveExpression derivedWithSimplePrimitiveExpression = derivedWithSimplePrimitiveExpressionOptional.orElseThrow();

        assertEquals(1, derivedWithSimplePrimitiveExpressionDao.countAll());
        assertEquals(Optional.of(1), derivedWithSimplePrimitiveExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), derivedWithSimplePrimitiveExpression.getScaledDerived());
        assertEquals(Optional.of("test"), derivedWithSimplePrimitiveExpression.getStringDerived());
        assertEquals(Optional.of("+36 (30) 123 1234"), derivedWithSimplePrimitiveExpression.getRegexDerived());
        assertEquals(Optional.of(true), derivedWithSimplePrimitiveExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), derivedWithSimplePrimitiveExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), derivedWithSimplePrimitiveExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), derivedWithSimplePrimitiveExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), derivedWithSimplePrimitiveExpression.getEnumDerived());

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
    @TestCase("AutoMappedTransferCreationDerivedWithComplexPrimitiveExpression")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationDerivedWithComplexPrimitiveExpression() {

        //check all field is mapped
        AutoMappedTransferOnDerivedWithComplexPrimitiveExpression transferOnDerivedWithComplexPrimitiveExpression = autoMappedTransferOnDerivedWithComplexPrimitiveExpressionDao.create(
                AutoMappedTransferOnDerivedWithComplexPrimitiveExpression.builder().build()
        );

        assertEquals(1, autoMappedTransferOnDerivedWithComplexPrimitiveExpressionDao.countAll());
        assertEquals(Optional.of(1), transferOnDerivedWithComplexPrimitiveExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), transferOnDerivedWithComplexPrimitiveExpression.getScaledDerived());
        assertEquals(Optional.of(true), transferOnDerivedWithComplexPrimitiveExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOnDerivedWithComplexPrimitiveExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOnDerivedWithComplexPrimitiveExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOnDerivedWithComplexPrimitiveExpression.getTimeDerived());

        Optional<DerivedWithComplexPrimitiveExpression> derivedWithComplexPrimitiveExpressionOptional = derivedWithComplexPrimitiveExpressionDao.getById(transferOnDerivedWithComplexPrimitiveExpression.adaptTo(DerivedWithComplexPrimitiveExpressionIdentifier.class));

        assertTrue(derivedWithComplexPrimitiveExpressionOptional.isPresent());

        DerivedWithComplexPrimitiveExpression derivedWithComplexPrimitiveExpression = derivedWithComplexPrimitiveExpressionOptional.orElseThrow();

        assertEquals(1, derivedWithComplexPrimitiveExpressionDao.countAll());
        assertEquals(Optional.of(1), derivedWithComplexPrimitiveExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), derivedWithComplexPrimitiveExpression.getScaledDerived());
        assertEquals(Optional.of(true), derivedWithComplexPrimitiveExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), derivedWithComplexPrimitiveExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), derivedWithComplexPrimitiveExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), derivedWithComplexPrimitiveExpression.getTimeDerived());

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
    @TestCase("AutoMappedTransferCreationDerivedWithQueryExpression")
    @Requirement(reqs = {
            "",
    })
    void testAutoMappedTransferCreationDerivedWithQueryExpression() {

        //check all field is mapped
        AutoMappedTransferOnDerivedWithQueryExpression transferOnDerivedWithQueryExpression = autoMappedTransferOnDerivedWithQueryExpressionDao.create(
                AutoMappedTransferOnDerivedWithQueryExpression.builder().build()
        );

        assertEquals(1, autoMappedTransferOnDerivedWithQueryExpressionDao.countAll());
        assertEquals(Optional.of(1), transferOnDerivedWithQueryExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), transferOnDerivedWithQueryExpression.getScaledDerived());
        assertEquals(Optional.of("test"), transferOnDerivedWithQueryExpression.getStringDerived());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferOnDerivedWithQueryExpression.getRegexDerived());
        assertEquals(Optional.of(true), transferOnDerivedWithQueryExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOnDerivedWithQueryExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOnDerivedWithQueryExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOnDerivedWithQueryExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), transferOnDerivedWithQueryExpression.getEnumDerived());

        Optional<DerivedWithQueryExpression> derivedWithQueryExpressionOptional = derivedWithQueryExpressionDao.getById(transferOnDerivedWithQueryExpression.adaptTo(DerivedWithQueryExpressionIdentifier.class));

        assertTrue(derivedWithQueryExpressionOptional.isPresent());

        DerivedWithQueryExpression derivedWithQueryExpression = derivedWithQueryExpressionOptional.orElseThrow();

        assertEquals(1, derivedWithQueryExpressionDao.countAll());
        assertEquals(Optional.of(1), derivedWithQueryExpression.getIntegerDerived());
        assertEquals(Optional.of(2.34), derivedWithQueryExpression.getScaledDerived());
        assertEquals(Optional.of("test"), derivedWithQueryExpression.getStringDerived());
        assertEquals(Optional.of("+36 (30) 123 1234"), derivedWithQueryExpression.getRegexDerived());
        assertEquals(Optional.of(true), derivedWithQueryExpression.getBoolDerived());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), derivedWithQueryExpression.getDateDerived());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), derivedWithQueryExpression.getTimestampDerived());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), derivedWithQueryExpression.getTimeDerived());
        assertEquals(Optional.of(MyEnum.Bombastic), derivedWithQueryExpression.getEnumDerived());

    }


    private Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}