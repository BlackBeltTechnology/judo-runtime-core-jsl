package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithrequiredprimitivesdefaults.EntityWithRequiredPrimitivesDefaults;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithrequiredprimitivesdefaults.EntityWithRequiredPrimitivesDefaultsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithrequiredprimitivesdefaults.EntityWithRequiredPrimitivesDefaultsIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithprimitivesdefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferoptionalprimitiveswithconstructor.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferrequiredprimitivesdefaultswithconstructor.TransferRequiredPrimitivesDefaultsWithConstructor;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferrequiredprimitivesdefaultswithconstructor.TransferRequiredPrimitivesDefaultsWithConstructorDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferrequiredprimitiveswithconstructor.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferoptionalprimitivesdefaultswithconstructor.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferConstructorDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TransferConstructorTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new TransferConstructorDaoModules();
    }

    @Override
    public String getModelName() {
        return "TransferConstructor";
    }

    @Inject
    EntityWithPrimitivesDao entityWithPrimitivesDao;

    @Inject
    TransferOptionalPrimitivesWithConstructorDao transferOptionalPrimitivesWithConstructorDao;

    @Inject
    EntityWithRequiredPrimitivesDao entityWithRequiredPrimitivesDao;

    @Inject
    TransferRequiredPrimitivesWithConstructorDao transferRequiredPrimitivesWithConstructorDao;

    @Inject
    EntityWithPrimitivesDefaultsDao entityRequiredWithPrimitiveDefaultsDao;

    @Inject
    TransferOptionalPrimitivesDefaultsWithConstructorDao transferOptionalPrimitivesDefaultsWithConstructorDao;

    @Inject
    EntityWithRequiredPrimitivesDefaultsDao entityWithRequiredPrimitivesDefaultsDao;

    @Inject
    TransferRequiredPrimitivesDefaultsWithConstructorDao transferRequiredPrimitivesDefaultsWithConstructorDao;

    /**
     * This test check the transfer constructor on an entity with contains primitive optional field.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferOptionalPrimitivesWithConstructor.
     *
     *  Check the transfer instance contains the constructor assigned values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("TransferOptionalPrimitivesWithConstructor")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferOptionalPrimitivesWithConstructor() {

        TransferOptionalPrimitivesWithConstructor transferWithPrimitives = transferOptionalPrimitivesWithConstructorDao.create(TransferOptionalPrimitivesWithConstructor.builder().build());

        assertEquals(1, transferOptionalPrimitivesWithConstructorDao.countAll());
        assertEquals(Optional.of(1), transferWithPrimitives.getIntegerAttr());
        assertEquals(Optional.of(2.34), transferWithPrimitives.getScaledAttr());
        assertEquals(Optional.of("Hello there"), transferWithPrimitives.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferWithPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), transferWithPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferWithPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferWithPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferWithPrimitives.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), transferWithPrimitives.getEnumAttr());

        Optional<EntityWithPrimitives> entityWithPrimitivesOptional = entityWithPrimitivesDao.getById(transferWithPrimitives.adaptTo(EntityWithPrimitivesIdentifier.class));

        assertTrue(entityWithPrimitivesOptional.isPresent());

        EntityWithPrimitives entityWithPrimitives = entityWithPrimitivesOptional.orElseThrow();

        assertEquals(Optional.of(1), entityWithPrimitives.getIntegerAttr());
        assertEquals(Optional.of(2.34), entityWithPrimitives.getScaledAttr());
        assertEquals(Optional.of("Hello there"), entityWithPrimitives.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), entityWithPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), entityWithPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityWithPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityWithPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithPrimitives.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), entityWithPrimitives.getEnumAttr());

    }

    /**
     * This test check the transfer constructor on an entity with contains primitive required field.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferRequiredPrimitivesWithConstructor.
     *
     *  Check the transfer instance contains the constructor assigned values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("TransferRequiredPrimitivesWithConstructor")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferRequiredPrimitivesWithConstructor() {

        TransferRequiredPrimitivesWithConstructor transferRequiredPrimitives = transferRequiredPrimitivesWithConstructorDao.create(TransferRequiredPrimitivesWithConstructor.builder().build());

        assertEquals(1, transferRequiredPrimitivesWithConstructorDao.countAll());
        assertEquals(1, transferRequiredPrimitives.getIntegerAttr());
        assertEquals(2.34, transferRequiredPrimitives.getScaledAttr());
        assertEquals("Hello there", transferRequiredPrimitives.getStringAttr());
        assertEquals("+36 (30) 123 1234", transferRequiredPrimitives.getRegexAttr());
        assertEquals(true, transferRequiredPrimitives.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), transferRequiredPrimitives.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferRequiredPrimitives.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), transferRequiredPrimitives.getTimeAttr());
        assertEquals(Enum.EnumA, transferRequiredPrimitives.getEnumAttr());

        Optional<EntityWithRequiredPrimitives> entityWithRequiredPrimitivesOptional = entityWithRequiredPrimitivesDao.getById(transferRequiredPrimitives.adaptTo(EntityWithRequiredPrimitivesIdentifier.class));

        assertTrue(entityWithRequiredPrimitivesOptional.isPresent());

        EntityWithRequiredPrimitives entityWithRequiredPrimitives = entityWithRequiredPrimitivesOptional.orElseThrow();

        assertEquals(1, entityWithRequiredPrimitives.getIntegerAttr());
        assertEquals(2.34, entityWithRequiredPrimitives.getScaledAttr());
        assertEquals("Hello there", entityWithRequiredPrimitives.getStringAttr());
        assertEquals("+36 (30) 123 1234", entityWithRequiredPrimitives.getRegexAttr());
        assertEquals(true, entityWithRequiredPrimitives.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityWithRequiredPrimitives.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entityWithRequiredPrimitives.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityWithRequiredPrimitives.getTimeAttr());
        assertEquals(Enum.EnumA, entityWithRequiredPrimitives.getEnumAttr());

    }

    /**
     * This test check the transfer constructor on an entity with contains primitive fields with defaults.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferOptionalPrimitivesDefaultsWithConstructor.
     *
     *  Check the transfer instance contains the constructor assigned values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("TransferPrimitivesDefaultWithConstructor")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferPrimitivesDefaultWithConstructor() {

        TransferOptionalPrimitivesDefaultsWithConstructor transferDefaultPrimitives = transferOptionalPrimitivesDefaultsWithConstructorDao.create(TransferOptionalPrimitivesDefaultsWithConstructor.builder().build());

        assertEquals(1, transferOptionalPrimitivesDefaultsWithConstructorDao.countAll());
        assertEquals(Optional.of(1), transferDefaultPrimitives.getIntegerAttr());
        assertEquals(Optional.of(2.34), transferDefaultPrimitives.getScaledAttr());
        assertEquals(Optional.of("Hello there"), transferDefaultPrimitives.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferDefaultPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), transferDefaultPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferDefaultPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferDefaultPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferDefaultPrimitives.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), transferDefaultPrimitives.getEnumAttr());

        Optional<EntityWithPrimitivesDefaults> entityWithPrimitivesDefaultsOptional = entityRequiredWithPrimitiveDefaultsDao.getById(transferDefaultPrimitives.adaptTo(EntityWithPrimitivesDefaultsIdentifier.class));

        assertTrue(entityWithPrimitivesDefaultsOptional.isPresent());

        EntityWithPrimitivesDefaults entityWithPrimitivesDefaults = entityWithPrimitivesDefaultsOptional.orElseThrow();

        assertEquals(Optional.of(1), entityWithPrimitivesDefaults.getIntegerAttr());
        assertEquals(Optional.of(2.34), entityWithPrimitivesDefaults.getScaledAttr());
        assertEquals(Optional.of("Hello there"), entityWithPrimitivesDefaults.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), entityWithPrimitivesDefaults.getRegexAttr());
        assertEquals(Optional.of(true), entityWithPrimitivesDefaults.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityWithPrimitivesDefaults.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityWithPrimitivesDefaults.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithPrimitivesDefaults.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), entityWithPrimitivesDefaults.getEnumAttr());

    }

    /**
     * This test check the transfer constructor on an entity with contains primitive fields with defaults.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferRequiredPrimitivesDefaultsWithConstructor.
     *
     *  Check the transfer instance contains the constructor assigned values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("TransferPrimitivesRequiredDefaultWithConstructor")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferPrimitivesRequiredDefaultWithConstructor() {

        TransferRequiredPrimitivesDefaultsWithConstructor transferRequiredPrimitivesDefaults = transferRequiredPrimitivesDefaultsWithConstructorDao.create(TransferRequiredPrimitivesDefaultsWithConstructor.builder().build());

        assertEquals(1, transferRequiredPrimitivesDefaultsWithConstructorDao.countAll());
        assertEquals(1, transferRequiredPrimitivesDefaults.getIntegerAttr());
        assertEquals(2.34, transferRequiredPrimitivesDefaults.getScaledAttr());
        assertEquals("Hello there", transferRequiredPrimitivesDefaults.getStringAttr());
        assertEquals("+36 (30) 123 1234", transferRequiredPrimitivesDefaults.getRegexAttr());
        assertEquals(true, transferRequiredPrimitivesDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), transferRequiredPrimitivesDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferRequiredPrimitivesDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), transferRequiredPrimitivesDefaults.getTimeAttr());
        assertEquals(Enum.EnumA, transferRequiredPrimitivesDefaults.getEnumAttr());

        Optional<EntityWithRequiredPrimitivesDefaults> entityWithRequiredPrimitivesDefaultsOptional = entityWithRequiredPrimitivesDefaultsDao.getById(transferRequiredPrimitivesDefaults.adaptTo(EntityWithRequiredPrimitivesDefaultsIdentifier.class));

        assertTrue(entityWithRequiredPrimitivesDefaultsOptional.isPresent());

        EntityWithRequiredPrimitivesDefaults entityWithRequiredPrimitivesDefaults = entityWithRequiredPrimitivesDefaultsOptional.orElseThrow();

        assertEquals(1, entityWithRequiredPrimitivesDefaults.getIntegerAttr());
        assertEquals(2.34, entityWithRequiredPrimitivesDefaults.getScaledAttr());
        assertEquals("Hello there", entityWithRequiredPrimitivesDefaults.getStringAttr());
        assertEquals("+36 (30) 123 1234", entityWithRequiredPrimitivesDefaults.getRegexAttr());
        assertEquals(true, entityWithRequiredPrimitivesDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityWithRequiredPrimitivesDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entityWithRequiredPrimitivesDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityWithRequiredPrimitivesDefaults.getTimeAttr());
        assertEquals(Enum.EnumA, entityWithRequiredPrimitivesDefaults.getEnumAttr());

    }

}
