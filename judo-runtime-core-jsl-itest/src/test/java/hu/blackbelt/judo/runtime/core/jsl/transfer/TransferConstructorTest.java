package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithoptionalfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithrequiredfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithrequiredfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldsmapsentitywithoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldsmapsentitywithoptionalfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldsmapsentitywithrequiredfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldsmapsentitywithrequiredfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldsmapsentitywithoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldsmapsentitywithoptionalfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldsmapsentitywithrequiredfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldsmapsentitywithrequiredfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfields.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfieldswithdefault.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithmultirelation.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithsinglerelation.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.mappedentitywithmultirelation.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.mappedentitywithsinglerelation.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferConstructorDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    EntityWithOptionalFieldsDao entityWithOptionalFieldsDao;

    @Inject
    TransferWithOptionalFieldsMapsEntityWithOptionalFieldsDao transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao;


    @Inject
    TransferWithRequiredFieldsMapsEntityWithOptionalFieldsDao transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao;


    @Inject
    EntityWithRequiredFieldsDao entityWithRequiredFieldsDao;

    @Inject
    TransferWithOptionalFieldsMapsEntityWithRequiredFieldsDao transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao;


    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao;

    @Inject
    TransferWithRequiredFieldsMapsEntityWithRequiredFieldsDao transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao;

    @Inject
    EntityWithOptionalFieldsWithDefaultDao entityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    EntityWithRequiredFieldsWithDefaultDao entityWithRequiredFieldsWithDefaultDao;
    @Inject
    TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    MappedEntityWithMultiRelationDao mappedEntityWithMultiRelationDao;

    @Inject
    MappedEntityWithSingleRelationDao mappedEntityWithSingleRelationDao;

    @Inject
    EntityWithSingleRelationDao entityWithSingleRelationDao;

    @Inject
    EntityWithMultiRelationDao entityWithMultiRelationDao;


    /**
     * This test check the mapped transfer object maps an entity with contains primitive optional field.
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
     *  Create one instance of TransferWithOptionalFieldsMapsEntityWithOptionalFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields.
     *
     *  Check that the creation throws ValidationException.
     *
     *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("EntityOptionalPrimitives")
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
    void testTransferMapsEntityWithOptionalFields() {
        TransferWithOptionalFieldsMapsEntityWithOptionalFields t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.create(TransferWithOptionalFieldsMapsEntityWithOptionalFields.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(Optional.empty(), t1.getIntegerAttr());
        assertEquals(Optional.empty(), t1.getScaledAttr());
        assertEquals(Optional.empty(), t1.getStringAttr());
        assertEquals(Optional.empty(), t1.getRegexAttr());
        assertEquals(Optional.empty(), t1.getBoolAttr());
        assertEquals(Optional.empty(), t1.getDateAttr());
        assertEquals(Optional.empty(), t1.getTimestampAttr());
        assertEquals(Optional.empty(), t1.getTimeAttr());
        assertEquals(Optional.empty(), t1.getEnumAttr());

        Optional<EntityWithOptionalFields> e1Optional = entityWithOptionalFieldsDao.getById(t1.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        EntityWithOptionalFields e1 = e1Optional.orElseThrow();

        assertEquals(Optional.empty(), e1.getIntegerAttr());
        assertEquals(Optional.empty(), e1.getScaledAttr());
        assertEquals(Optional.empty(), e1.getStringAttr());
        assertEquals(Optional.empty(), e1.getRegexAttr());
        assertEquals(Optional.empty(), e1.getBoolAttr());
        assertEquals(Optional.empty(), e1.getDateAttr());
        assertEquals(Optional.empty(), e1.getTimestampAttr());
        assertEquals(Optional.empty(), e1.getTimeAttr());
        assertEquals(Optional.empty(), e1.getEnumAttr());

        TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(Optional.of(1), t2.getIntegerAttr());
        assertEquals(Optional.of(2.34), t2.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t2.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), t2.getRegexAttr());
        assertEquals(Optional.of(true), t2.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), t2.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), t2.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t2.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t2.getEnumAttr());

        e1Optional = entityWithOptionalFieldsDao.getById(t2.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());


        ValidationException exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.create(TransferWithRequiredFieldsMapsEntityWithOptionalFields.builder().build()));


        TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFields t3 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFields.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, t3.getIntegerAttr());
        assertEquals(2.34, t3.getScaledAttr());
        assertEquals("Hello there", t3.getStringAttr());
        assertEquals("+36 (30) 123 1234", t3.getRegexAttr());
        assertEquals(true, t3.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), t3.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t3.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t3.getTimeAttr());
        assertEquals(Enum.EnumA, t3.getEnumAttr());

        e1Optional = entityWithOptionalFieldsDao.getById(t3.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());


    }


    /**
    * This test check the mapped transfer object maps an entity with contains primitive required field.
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
    *  Create one instance of TransferWithOptionalFieldsMapsEntityWithRequiredFields.
    *
    *  Check that the creation throws ValidationException.
    *
    *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFields.
    *
    *  Check the transfer instance contains the mapped values.
    *
    *  Get the entity representation the transfer instance.
    *
    *  Check the entity representation contains the constructor assigned values.
    *
    *  Create one instance of TransferWithRequiredFieldsMapsEntityWithRequiredFields.
    *
    *  Check that the creation throws ValidationException.
    *
    *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFields.
    *
    *  Check the transfer instance contains the mapped values.
    *
    *  Get the entity representation the transfer instance.
    *
    *  Check the entity representation contains the constructor assigned values.
    *
    */
    @Test
    @TestCase("EntityRequiredPrimitives")
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
    void testTransferMapsEntityWithRequiredFields() {
        ValidationException exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.create(TransferWithOptionalFieldsMapsEntityWithRequiredFields.builder().build()));

        TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFields t1 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFields.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(Optional.of(1), t1.getIntegerAttr());
        assertEquals(Optional.of(2.34), t1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t1.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), t1.getRegexAttr());
        assertEquals(Optional.of(true), t1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), t1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), t1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t1.getEnumAttr());

        Optional<EntityWithRequiredFields> e1 = entityWithRequiredFieldsDao.getById(t1.adaptTo(EntityWithRequiredFieldsIdentifier.class));

        assertTrue(e1.isPresent());

        EntityWithRequiredFields entity = e1.orElseThrow();

        assertEquals(1, entity.getIntegerAttr());
        assertEquals(2.34, entity.getScaledAttr());
        assertEquals("Hello there", entity.getStringAttr());
        assertEquals("+36 (30) 123 1234", entity.getRegexAttr());
        assertEquals(true, entity.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entity.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entity.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entity.getTimeAttr());
        assertEquals(Enum.EnumA, entity.getEnumAttr());


        exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.create(TransferWithRequiredFieldsMapsEntityWithRequiredFields.builder().build()));


        TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFields t2 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFields.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, t2.getIntegerAttr());
        assertEquals(2.34, t2.getScaledAttr());
        assertEquals("Hello there", t2.getStringAttr());
        assertEquals("+36 (30) 123 1234", t2.getRegexAttr());
        assertEquals(true, t2.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), t2.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t2.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t2.getTimeAttr());
        assertEquals(Enum.EnumA, t2.getEnumAttr());

        e1 = entityWithRequiredFieldsDao.getById(t2.adaptTo(EntityWithRequiredFieldsIdentifier.class));

        assertTrue(e1.isPresent());

        entity = e1.orElseThrow();

        assertEquals(1, entity.getIntegerAttr());
        assertEquals(2.34, entity.getScaledAttr());
        assertEquals("Hello there", entity.getStringAttr());
        assertEquals("+36 (30) 123 1234", entity.getRegexAttr());
        assertEquals(true, entity.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entity.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entity.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entity.getTimeAttr());
        assertEquals(Enum.EnumA, entity.getEnumAttr());


    }

    /**
     * This test check the mapped transfer object maps an entity with contains primitive optional field with default values.
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
     *  Create one instance of TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("EntityOptionalPrimitivesWithDefaultValue")
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
    void testTransferMapsEntityWithOptionalFieldsWithDefaultValues() {
        TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(1), t1.getIntegerAttr());
        assertEquals(Optional.of(2.34), t1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t1.getStringAttr());
        assertEquals(Optional.of("+36-1-223-123"), t1.getRegexAttr());
        assertEquals(Optional.of(true), t1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2021, 7, 11)), t1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2021-07-11T19:09:33")), t1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t1.getEnumAttr());

        Optional<EntityWithOptionalFieldsWithDefault> e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t1.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        EntityWithOptionalFieldsWithDefault e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36-1-223-123"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2021, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2021-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());


        TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(2), t2.getIntegerAttr());
        assertEquals(Optional.of(3.34), t2.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), t2.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 5678"), t2.getRegexAttr());
        assertEquals(Optional.of(false), t2.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), t2.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), t2.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), t2.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), t2.getEnumAttr());

        e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t2.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(2), e1.getIntegerAttr());
        assertEquals(Optional.of(3.34), e1.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), e1.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 5678"), e1.getRegexAttr());
        assertEquals(Optional.of(false), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), e1.getEnumAttr());

        // TODO: JNG-4922
        //TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault t3 = transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault.builder().build());
//
        // TODO: JNG-4921
        //assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        //assertEquals(1, t3.getIntegerAttr());
        //assertEquals(2.34, t3.getScaledAttr());
        //assertEquals("Hello there", t3.getStringAttr());
        //assertEquals("+36 (30) 123 1234", t3.getRegexAttr());
        //assertEquals(true, t3.getBoolAttr());
        //assertEquals(LocalDate.of(2022, 7, 11), t3.getDateAttr());
        //assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t3.getTimestampAttr());
        //assertEquals(LocalTime.parse("23:59:59"), t3.getTimeAttr());
        //assertEquals(Enum.EnumA, t3.getEnumAttr());
//
        //e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t3.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));
//
        //assertTrue(e1Optional.isPresent());
//
        //e1 = e1Optional.orElseThrow();
//
        //assertEquals(1, e1.getIntegerAttr());
        //assertEquals(2.34, e1.getScaledAttr());
        //assertEquals("Hello there", e1.getStringAttr());
        //assertEquals("+36 (30) 123 1234", e1.getRegexAttr());
        //assertEquals(true, e1.getBoolAttr());
        //assertEquals(LocalDate.of(2022, 7, 11), e1.getDateAttr());
        //assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), e1.getTimestampAttr());
        //assertEquals(LocalTime.parse("23:59:59"), e1.getTimeAttr());
        //assertEquals(Enum.EnumA, e1.getEnumAttr());


        TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault transfer4 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(2, transfer4.getIntegerAttr());
        assertEquals(3.34, transfer4.getScaledAttr());
        assertEquals("Lorem Ipsum", transfer4.getStringAttr());
        assertEquals("+36 (30) 123 5678", transfer4.getRegexAttr());
        assertEquals(false, transfer4.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), transfer4.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), transfer4.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), transfer4.getTimeAttr());
        assertEquals(Enum.EnumB, transfer4.getEnumAttr());

        e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(transfer4.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(2), e1.getIntegerAttr());
        assertEquals(Optional.of(3.34), e1.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), e1.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 5678"), e1.getRegexAttr());
        assertEquals(Optional.of(false), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), e1.getEnumAttr());

    }

    /**
     * This test check the mapped transfer object maps an entity with contains primitive required field with default values.
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
     *  Create one instance of TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("EntityRequiredPrimitivesWithDefaultValue")
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
    void testTransferMapsEntityWithRequiredFieldsWithDefault() {
        TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefault t1 = transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefault.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(1), t1.getIntegerAttr());
        assertEquals(Optional.of(2.34), t1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t1.getStringAttr());
        assertEquals(Optional.of("+36-1-223-123"), t1.getRegexAttr());
        assertEquals(Optional.of(true), t1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2021, 7, 11)), t1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2021-07-11T19:09:33")), t1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t1.getEnumAttr());

        Optional<EntityWithRequiredFieldsWithDefault> e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(t1.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        EntityWithRequiredFieldsWithDefault e1 = e1Optional.orElseThrow();

        assertEquals(1, e1.getIntegerAttr());
        assertEquals(2.34, e1.getScaledAttr());
        assertEquals("Hello there", e1.getStringAttr());
        assertEquals("+36-1-223-123", e1.getRegexAttr());
        assertEquals(true, e1.getBoolAttr());
        assertEquals(LocalDate.of(2021, 7, 11), e1.getDateAttr());
        assertEquals(LocalDateTime.parse("2021-07-11T19:09:33"), e1.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), e1.getTimeAttr());
        assertEquals(Enum.EnumA, e1.getEnumAttr());


        TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(2), t2.getIntegerAttr());
        assertEquals(Optional.of(3.34), t2.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), t2.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 5678"), t2.getRegexAttr());
        assertEquals(Optional.of(false), t2.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), t2.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), t2.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), t2.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), t2.getEnumAttr());

        e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(t2.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(2, e1.getIntegerAttr());
        assertEquals(3.34, e1.getScaledAttr());
        assertEquals("Lorem Ipsum", e1.getStringAttr());
        assertEquals("+36 (30) 123 5678", e1.getRegexAttr());
        assertEquals(false, e1.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), e1.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), e1.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), e1.getTimeAttr());
        assertEquals(Enum.EnumB, e1.getEnumAttr());

        // TODO: JNG-4922
        //TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefault t3 = transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefault.builder().build());
//
        // TODO: JNG-4921
        //assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        //assertEquals(1, t3.getIntegerAttr());
        //assertEquals(2.34, t3.getScaledAttr());
        //assertEquals("Hello there", t3.getStringAttr());
        //assertEquals("+36 (30) 123 1234", t3.getRegexAttr());
        //assertEquals(true, t3.getBoolAttr());
        //assertEquals(LocalDate.of(2022, 7, 11), t3.getDateAttr());
        //assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t3.getTimestampAttr());
        //assertEquals(LocalTime.parse("23:59:59"), t3.getTimeAttr());
        //assertEquals(Enum.EnumA, t3.getEnumAttr());
//
        //e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(transfer3.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));
//
        //assertTrue(e1Optional.isPresent());
//
        //e1 = e1Optional.orElseThrow();
//
        //assertEquals(Optional.of(2), e1.getIntegerAttr());
        //assertEquals(Optional.of(3.34), e1.getScaledAttr());
        //assertEquals(Optional.of("Lorem Ipsum"), e1.getStringAttr());
        //assertEquals(Optional.of("+36 (30) 123 5678"), e1.getRegexAttr());
        //assertEquals(Optional.of(false), e1.getBoolAttr());
        //assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), e1.getDateAttr());
        //assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), e1.getTimestampAttr());
        //assertEquals(Optional.of(LocalTime.parse("23:59:58")), e1.getTimeAttr());
        //assertEquals(Optional.of(Enum.EnumB), e1.getEnumAttr());


        TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault transfer4 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault.builder().build());

        // TODO: JNG-4921
        //assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(2, transfer4.getIntegerAttr());
        assertEquals(3.34, transfer4.getScaledAttr());
        assertEquals("Lorem Ipsum", transfer4.getStringAttr());
        assertEquals("+36 (30) 123 5678", transfer4.getRegexAttr());
        assertEquals(false, transfer4.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), transfer4.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), transfer4.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), transfer4.getTimeAttr());
        assertEquals(Enum.EnumB, transfer4.getEnumAttr());

        e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(transfer4.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(2, e1.getIntegerAttr());
        assertEquals(3.34, e1.getScaledAttr());
        assertEquals("Lorem Ipsum", e1.getStringAttr());
        assertEquals("+36 (30) 123 5678", e1.getRegexAttr());
        assertEquals(false, e1.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), e1.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), e1.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), e1.getTimeAttr());
        assertEquals(Enum.EnumB, e1.getEnumAttr());

    }

    /**
     * This test check the transfer constructor on an entity with relations.
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
     *  Create one instance of mappedEntityWithMultiRelation.
     *
     *  Create one instance of mappedEntityWithSingleRelation.
     *
     *  Check the mappedEntityWithSingleRelation instance contains the constructor expressions.
     *
     *  Get the entity representation the MappedEntityWithSingleRelation instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    public void testTransferAssossiationConstrtuctor() {

        MappedEntityWithMultiRelation mappedEntityWithMultiRelation = mappedEntityWithMultiRelationDao.create(MappedEntityWithMultiRelation.builder().build());

        MappedEntityWithSingleRelation mappedEntityWithSingleRelation = mappedEntityWithSingleRelationDao.create(MappedEntityWithSingleRelation.builder().build());

        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociation(mappedEntityWithSingleRelation).orElseThrow().identifier(),mappedEntityWithMultiRelation.identifier());
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationCollection(mappedEntityWithSingleRelation).count(),1);
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationCollection(mappedEntityWithSingleRelation).execute().get(0).identifier(),mappedEntityWithMultiRelation.identifier());
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationOpposite(mappedEntityWithSingleRelation).orElseThrow().identifier(),mappedEntityWithMultiRelation.identifier());
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationOppositeCollection(mappedEntityWithSingleRelation).count(),1);
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationOppositeCollection(mappedEntityWithSingleRelation).execute().get(0).identifier(),mappedEntityWithMultiRelation.identifier());

        Optional<EntityWithSingleRelation> entityWithSingleRelationOptional = entityWithSingleRelationDao.getById(mappedEntityWithSingleRelation.adaptTo(EntityWithSingleRelationIdentifier.class));

        assertTrue(entityWithSingleRelationOptional.isPresent());

        EntityWithSingleRelation entityWithSingleRelation = entityWithSingleRelationOptional.orElseThrow();

        EntityWithMultiRelation entityWithMultiRelation = entityWithMultiRelationDao.getById(mappedEntityWithMultiRelation.adaptTo(EntityWithMultiRelationIdentifier.class)).orElseThrow();

        assertEquals(entityWithSingleRelationDao.queryAssociation(entityWithSingleRelation).orElseThrow().identifier(),entityWithMultiRelation.identifier());
        assertEquals(entityWithSingleRelationDao.queryAssociationCollection(entityWithSingleRelation).count(),1);
        assertEquals(entityWithSingleRelationDao.queryAssociationCollection(entityWithSingleRelation).execute().get(0).identifier(),entityWithMultiRelation.identifier());
        assertEquals(entityWithSingleRelationDao.queryEntityWithTwoWayOpposite(entityWithSingleRelation).orElseThrow().identifier(),entityWithMultiRelation.identifier());
        assertEquals(entityWithSingleRelationDao.queryEntityWithTwoWayOppositeCollection(entityWithSingleRelation).count(),1);
        assertEquals(entityWithSingleRelationDao.queryEntityWithTwoWayOppositeCollection(entityWithSingleRelation).execute().get(0).identifier(),entityWithMultiRelation.identifier());


        //transient relations
//        assertEquals(mappedEntityWithSingleRelationDao.queryTransientRelation(mappedEntityWithSingleRelation).orElseThrow().identifier(),mappedEntityWithMultiRelation.identifier());
//        assertEquals(mappedEntityWithSingleRelationDao.queryTransientRelationCollection(mappedEntityWithSingleRelation).count(),1);
//        assertEquals(mappedEntityWithSingleRelationDao.queryTransientRelationCollection(mappedEntityWithSingleRelation).execute().get(0).identifier(),mappedEntityWithMultiRelation.identifier());

    }


}
