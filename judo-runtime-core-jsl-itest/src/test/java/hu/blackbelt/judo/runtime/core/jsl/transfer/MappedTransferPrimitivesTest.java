package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;

import hu.blackbelt.judo.dao.api.ValidationResult;
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.entityrequiredwithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaultexpressions.TransferWithPrimitiveDefaultExpressions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaultexpressions.TransferWithPrimitiveDefaultExpressionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithidentifiers.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredentityprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredprimitivedefaults.TransferWithRequiredPrimitiveDefaults;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.entityformapping.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferPrimitivesTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new MappedTransferPrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferPrimitives";
    }

    @Inject
    EntityForMappingDao entityForMappingDao;

    @Inject
    TransferOptionalPrimitivesDao transferOptionalPrimitivesDao;

    @Inject
    TransferWithPrimitiveDefaultsDao transferWithPrimitiveDefaultsDao;

    @Inject
    TransferRequiredPrimitivesDao transferRequiredPrimitivesDao;

    @Inject
    TransferWithIdentifiersDao transferWithIdentifiersDao;

    @Inject
    TransferWithRequiredPrimitiveDefaultsDao transferWithRequiredPrimitiveDefaultsDao;

    @Inject
    TransferPrimitiveDefaultsDao transferPrimitiveDefaultsDao;

    @Inject
    TransferWithPrimitiveDefaultExpressionsDao transferWithPrimitiveDefaultExpressionsDao;

    @Inject
    TransferWithRequiredEntityPrimitivesDao transferWithRequiredEntityPrimitivesDao;

    /**
     * The test checks if the entity's optional fields are mapped to the transfer object and are empty.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @positiveRequirements REQ-SRV-002
     *
     * @scenario
     *
     *  Create one TransferOptionalPrimitives Dao instance.
     *
     *  Check all mapped fields exist and are empty.
     */
    @Test
    @TestCase("MappedTransferOptionalPrimitivesWithoutValue")
    @Requirement(reqs = {
c
    })
    void testMappedTransferOptionalPrimitivesWithoutValue() {

        TransferOptionalPrimitives transferOptionalPrimitives = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder().build());

        assertEquals(Optional.empty(), transferOptionalPrimitives.getIntegerAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getScaledAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getStringAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getRegexAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getBoolAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getDateAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getTimestampAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getTimeAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getEnumAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getBinaryAttr());

    }

    /**
     * Transfer object mapped entity with optional primitives fields. The test checks the transfer object instance created with primitive values.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @positiveRequirements REQ-SRV-002
     *
     * @scenario
     *
     *  Create one TransferOptionalPrimitives instance with primitive Values.
     *
     *  Check all fields contain the Dao added values.
     *
     *  Check the mapped entity all fields contain the Dao added values.
     *
     */
    @Test
    @TestCase("MappedTransferOptionalPrimitives")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    void testMappedTransferOptionalPrimitives() {

        TransferOptionalPrimitives transferOptionalPrimitives = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withIntegerAttr(1)
                .withScaledAttr(1.23)
                .withStringAttr("test")
                .withRegexAttr("+36 333-333-3333")
                .withBoolAttr(true)
                .withDateAttr(LocalDate.of(2022, 7, 11))
                .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                .withTimeAttr(LocalTime.parse("23:59:59"))
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(Enum.EnumA)
                .build());

        assertEquals(1, transferOptionalPrimitivesDao.query().execute().size());
        assertEquals(Optional.of(1), transferOptionalPrimitives.getIntegerAttr());
        assertEquals(Optional.of(1.23), transferOptionalPrimitives.getScaledAttr());
        assertEquals(Optional.of("test"), transferOptionalPrimitives.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), transferOptionalPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), transferOptionalPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOptionalPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOptionalPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOptionalPrimitives.getTimeAttr());
        assertEquals("test.txt", transferOptionalPrimitives.getBinaryAttr().orElseThrow().getFileName());
        assertEquals(Optional.of(Enum.EnumA), transferOptionalPrimitives.getEnumAttr());

        EntityForMapping entityForMapping = entityForMappingDao.getById(transferOptionalPrimitives.identifier().adaptTo(EntityForMappingIdentifier.class)).orElseThrow();
        assertEquals(1, entityForMappingDao.query().execute().size());

        assertEquals(Optional.of(1), entityForMapping.getIntegerAttr());
        assertEquals(Optional.of(1.23), entityForMapping.getScaledAttr());
        assertEquals(Optional.of("test"), entityForMapping.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), entityForMapping.getRegexAttr());
        assertEquals(Optional.of(true), entityForMapping.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityForMapping.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityForMapping.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityForMapping.getTimeAttr());
        assertEquals("test.txt", entityForMapping.getBinaryAttr().orElseThrow().getFileName());
        assertEquals(Optional.of(Enum.EnumA), entityForMapping.getEnumAttr());


    }

    /**
     * The transfer object mapped entity with required primitives fields. The test checks the Dao instance doesn't contain the mandatory attributes.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-SRV-002
     *
     * @scenario
     *
     *  Try to create one TransferRequiredPrimitives instance.
     *
     *  Check the thrown Validation exception contains all the fields with MISSING_REQUIRED_ATTRIBUTE code.
     *
     */
    @Test
    @TestCase("TransferMissingRequiredFieldsThrowExceptions")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testMissingRequiredFieldsThrowExceptions() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferRequiredPrimitivesDao.create(TransferRequiredPrimitives.builder().build())
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
                matchMissingAttribute("binaryAttr"),
                matchMissingAttribute("enumAttr")
        ));

        List<TransferRequiredPrimitives> list = transferRequiredPrimitivesDao.query().execute();
        assertEquals(0, list.size());
    }

    /**
     * The transfer object mapped entity with required primitives fields but the transfer object field are not required.
     * The test checks transfer object Dao instance doesn't contain the mandatory attribute.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-SRV-002
     *
     * @scenario
     *
     *  Try to create one TransferWithRequiredEntityPrimitivesDao instance.
     *
     *  Check the thrown Validation exception contains all the fields with MISSING_REQUIRED_ATTRIBUTE code.
     */
    @Test
    @TestCase("TransferMissingRequiredFieldsInEntitiesButNotRequiredInTransferThrowExceptions")
    @Disabled("JNG-4824")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testMissingRequiredFieldsInEntitiesButNotRequiredInTransferThrowExceptions() {
        //TODO JNG-4824
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferWithRequiredEntityPrimitivesDao.create(TransferWithRequiredEntityPrimitives.builder().build())
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
                matchMissingAttribute("binaryAttr"),
                matchMissingAttribute("enumAttr")
        ));

        List<TransferRequiredPrimitives> list = transferRequiredPrimitivesDao.query().execute();
        assertEquals(0, list.size());
    }

    /**
     * The transfer object mapped entity with identifiers primitives. The test checks the Dao instances to have the same identifiers.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-SRV-002, REQ-ENT-003
     *
     * @scenario
     *
     *  Try to create two TransferRequiredPrimitives instance with the same parameters.
     *
     *  Check the second instance creation thrown Validation exception contains all the fields with IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION code.
     */
    @Test
    @TestCase("TransferMapsIdentifierFieldsAreUnique")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testTransferMapsIdentifierFieldsAreUnique() {
        LocalDate now = LocalDate.now();
        TransferWithIdentifiers transf1 = transferWithIdentifiersDao.create(TransferWithIdentifiers.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(Enum.EnumA)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, transf1.getIntegerAttr().get());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferWithIdentifiersDao.create(TransferWithIdentifiers.builder()
                        .withIntegerAttr(1)
                        .withBoolAttr(true)
                        .withDateAttr(now)
                        .withEnumAttr(Enum.EnumA)
                        .withStringAttr("blabla")
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("stringAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("enumAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("integerAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("boolAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("dateAttr")))
        ));

    }

    /**
     * The test checks transfer object mapped entity with optional primitives fields and the Dao instances to have the same attributes.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @positiveRequirements REQ-SRV-002
     *
     *
     * @scenario
     *
     *  Create two TransferOptionalPrimitives instance with the same parameters.
     *
     *  Check the second instance creation does not give any errors.
     *
     */
    @Test
    @TestCase("TransferFieldsAreNonUnique")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testTransferFieldsAreNonUnique() {
        LocalDate now = LocalDate.now();
        TransferOptionalPrimitives transf1 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(Enum.EnumA)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, transf1.getIntegerAttr().get());

        TransferOptionalPrimitives transf2 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(Enum.EnumA)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, transf2.getIntegerAttr().get());
    }

    /**
     * The test checks the update Dao method when the mapped transfer object has optional primitives fields.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     *
     * @scenario
     *
     *  Create one TransferOptionalPrimitives instance without parameters.
     *
     *  Add all fields a primitive parameter.
     *
     *  Update with the dao.
     *
     *  Check if the fields are updated.
     *
     *  Check if the mapped entity fields are updated.
     */
    @Test
    @TestCase("UpdateOptionalTransferAfterCreation")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testUpdateOptionalTransferAfterCreation() {
        TransferOptionalPrimitives transferOptionalPrimitives = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder().build());

        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.empty(), transferOptionalPrimitives.getIntegerAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getScaledAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getStringAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getRegexAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getBoolAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getDateAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getTimestampAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getTimeAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getBinaryAttr());
        assertEquals(Optional.empty(), transferOptionalPrimitives.getEnumAttr());

        transferOptionalPrimitives.setIntegerAttr(1);
        transferOptionalPrimitives.setScaledAttr(1.23);
        transferOptionalPrimitives.setStringAttr("test");
        transferOptionalPrimitives.setRegexAttr("+36 333-333-3333");
        transferOptionalPrimitives.setBoolAttr(true);
        transferOptionalPrimitives.setDateAttr(LocalDate.of(2022, 7, 11));
        transferOptionalPrimitives.setTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"));
        transferOptionalPrimitives.setTimeAttr(LocalTime.parse("23:59:59"));
        transferOptionalPrimitives.setBinaryAttr(FileType.builder().fileName("test.txt").build());
        transferOptionalPrimitives.setEnumAttr(Enum.EnumA);

        transferOptionalPrimitives = transferOptionalPrimitivesDao.update(transferOptionalPrimitives);

        assertEquals(Optional.of(1), transferOptionalPrimitives.getIntegerAttr());
        assertEquals(Optional.of(1.23), transferOptionalPrimitives.getScaledAttr());
        assertEquals(Optional.of("test"), transferOptionalPrimitives.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), transferOptionalPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), transferOptionalPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferOptionalPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferOptionalPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferOptionalPrimitives.getTimeAttr());
        assertEquals("test.txt", transferOptionalPrimitives.getBinaryAttr().get().getFileName());
        assertEquals(Optional.of(Enum.EnumA), transferOptionalPrimitives.getEnumAttr());

        EntityForMapping entityForMapping = entityForMappingDao.getById(transferOptionalPrimitives.identifier().adaptTo(EntityForMappingIdentifier.class)).orElseThrow();
        assertEquals(1, entityForMappingDao.query().execute().size());

        assertEquals(Optional.of(1), entityForMapping.getIntegerAttr());
        assertEquals(Optional.of(1.23), entityForMapping.getScaledAttr());
        assertEquals(Optional.of("test"), entityForMapping.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), entityForMapping.getRegexAttr());
        assertEquals(Optional.of(true), entityForMapping.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityForMapping.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityForMapping.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityForMapping.getTimeAttr());
        assertEquals("test.txt", entityForMapping.getBinaryAttr().orElseThrow().getFileName());
        assertEquals(Optional.of(Enum.EnumA), entityForMapping.getEnumAttr());

    }

    /**
     * The transfer object mapped entity with default primitive values. The test checks the default values are present in the transfer object too.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @scenario
     *
     *  Create one TransferWithPrimitiveDefaults instance.
     *
     *  Check all fields contain the default primitives values.
     *
     */
    @Test
    @TestCase("TransferCreationWithPrimitiveDefaults")
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
            "REQ-EXPR-001",
    })
    public void testTransferCreationWithPrimitiveDefaults() {
        TransferWithPrimitiveDefaults transferWithPrimitiveDefaults = transferWithPrimitiveDefaultsDao.create(TransferWithPrimitiveDefaults.builder().build());

        List<TransferWithPrimitiveDefaults> list = transferWithPrimitiveDefaultsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), transferWithPrimitiveDefaults.getIntegerAttr());
        assertEquals(Optional.of(2.34), transferWithPrimitiveDefaults.getScaledAttr());
        assertEquals(Optional.of("test"), transferWithPrimitiveDefaults.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferWithPrimitiveDefaults.getRegexAttr());
        assertEquals(Optional.of(true), transferWithPrimitiveDefaults.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferWithPrimitiveDefaults.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferWithPrimitiveDefaults.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferWithPrimitiveDefaults.getTimeAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59")), transferWithPrimitiveDefaults.getShortTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), transferWithPrimitiveDefaults.getEnumAttr());
    }

    /**
     * The transfer object mapped entity with required default primitive values. The test checks the required default values are present in the transfer object too.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     *
     * @scenario
     *
     *  Create one TransferWithRequiredPrimitiveDefaults instance.
     *
     *  Check all fields contain the default primitives values.
     *
     */
    @Test
    @TestCase("TransferCreationRequiredWithPrimitiveDefaults")
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
            "REQ-EXPR-001",
    })
    public void testTransferCreationRequiredWithPrimitiveDefaults() {
        TransferPrimitiveDefaults transferPrimitiveDefaults = transferPrimitiveDefaultsDao.create(TransferPrimitiveDefaults.builder().build());
        List<TransferWithRequiredPrimitiveDefaults> list = transferWithRequiredPrimitiveDefaultsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(1, transferPrimitiveDefaults.getIntegerAttr().orElseThrow());
        assertEquals(2.34, transferPrimitiveDefaults.getScaledAttr().orElseThrow());
        assertEquals("test", transferPrimitiveDefaults.getStringAttr().orElseThrow());
        assertEquals("+36 (30) 123 1234", transferPrimitiveDefaults.getRegexAttr().orElseThrow());
        assertEquals(true, transferPrimitiveDefaults.getBoolAttr().orElseThrow());
        assertEquals(LocalDate.of(2022, 7, 11), transferPrimitiveDefaults.getDateAttr().orElseThrow());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferPrimitiveDefaults.getTimestampAttr().orElseThrow());
        assertEquals(LocalTime.parse("23:59:59"), transferPrimitiveDefaults.getTimeAttr().orElseThrow());
        assertEquals(Enum.EnumA, transferPrimitiveDefaults.getEnumAttr().orElseThrow());
    }

    /**
     * The transfer object mapped entity with required default primitive values. The test checks the required default values are present in the transfer object too.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     *
     * @scenario
     *
     *  Create one TransferWithRequiredPrimitiveDefaults instance.
     *
     *  Check all fields contain the default primitives values.
     *
     */
    @Test
    @TestCase("TransferCreationWithRequiredPrimitiveDefaults")
    @Disabled("JNG-4888")
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
            "REQ-EXPR-001",
    })
    public void testTransferCreationWithRequiredPrimitiveDefaults() {
        ValidationException exception = assertThrows(ValidationException.class, () -> transferWithRequiredPrimitiveDefaultsDao.create(TransferWithRequiredPrimitiveDefaults.builder().build()));
        assertEquals(9, exception.getValidationResults().size());
        Set<String> validationCodes = exception.getValidationResults().stream().map(ValidationResult::getCode).collect(Collectors.toSet());
        assertThat(validationCodes.size(), equalTo(1));
        assertThat(validationCodes.stream().findAny().get(), equalTo("MISSING_REQUIRED_ATTRIBUTE"));
        assertEquals(0, transferWithRequiredPrimitiveDefaultsDao.countAll());

        TransferWithRequiredPrimitiveDefaults transferWithRequiredPrimitiveDefaults =
                transferWithRequiredPrimitiveDefaultsDao.create(
                        TransferWithRequiredPrimitiveDefaults.builder()
                                                             .withIntegerAttr(2)
                                                             .withScaledAttr(3.34)
                                                             .withStringAttr("str")
                                                             .withRegexAttr("+36 123 123 4567")
                                                             .withBoolAttr(false)
                                                             .withDateAttr(LocalDate.of(2022, 8, 14))
                                                             .withTimeAttr(LocalTime.parse("23:00:00"))
                                                             .withTimestampAttr(LocalDateTime.parse("2022-08-12T10:09:33"))
                                                             .withEnumAttr(Enum.EnumB)
                                                             .build());

        assertEquals(2, transferWithRequiredPrimitiveDefaults.getIntegerAttr());
        assertEquals(3.34, transferWithRequiredPrimitiveDefaults.getScaledAttr());
        assertEquals("str", transferWithRequiredPrimitiveDefaults.getStringAttr());
        assertEquals("+36 123 123 4567", transferWithRequiredPrimitiveDefaults.getRegexAttr());
        assertEquals(false, transferWithRequiredPrimitiveDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 8, 14), transferWithRequiredPrimitiveDefaults.getDateAttr());
        assertEquals(LocalTime.parse("23:00:00"), transferWithRequiredPrimitiveDefaults.getTimeAttr());
        assertEquals(LocalDateTime.parse("2022-08-12T10:09:33"), transferWithRequiredPrimitiveDefaults.getTimestampAttr());
        assertEquals(Enum.EnumB, transferWithRequiredPrimitiveDefaults.getEnumAttr());
    }

    /**
     * The transfer object mapped entity with required default primitive expression. The test checks the required default expression are present in the transfer object too.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @scenario
     *
     *  Create one TransferWithPrimitiveDefaultExpressions instance.
     *
     *  Check all fields contain the default primitives expression.
     *
     */
    @Test
    @TestCase("TransferCreationWithPrimitiveDefaultExpressions")
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
            "REQ-EXPR-001",
    })
    public void testTransferCreationWithPrimitiveDefaultExpressions() {
        TransferWithPrimitiveDefaultExpressions transferWithPrimitiveDefaultExpressions = transferWithPrimitiveDefaultExpressionsDao.create(TransferWithPrimitiveDefaultExpressions.builder().build());

        List<TransferWithPrimitiveDefaultExpressions> list = transferWithPrimitiveDefaultExpressionsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), transferWithPrimitiveDefaultExpressions.getIntegerAttr());
        assertEquals(Optional.of(2.9), transferWithPrimitiveDefaultExpressions.getScaledAttr());
        assertEquals(Optional.of("true"), transferWithPrimitiveDefaultExpressions.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), transferWithPrimitiveDefaultExpressions.getRegexAttr());
        assertEquals(Optional.of(true), transferWithPrimitiveDefaultExpressions.getBoolAttr());
        assertEquals(Optional.of(LocalDate.now()), transferWithPrimitiveDefaultExpressions.getDateAttr());
        assertEquals(OffsetDateTime.now().toString().substring(1, 10),
                transferWithPrimitiveDefaultExpressions.getTimestampAttr().get().toString().substring(1, 10)); //use constant value
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferWithPrimitiveDefaultExpressions.getTimeAttr());
    }

    private Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

    private Matcher matchPrecisionValidationForAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                hasProperty("location", equalTo(attrName)));
    }

    private Matcher matchScaleValidationForAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("SCALE_VALIDATION_FAILED")),
                hasProperty("location", equalTo(attrName)));
    }

    /**
     * The transfer object mapped entity with optional primitive fields. The test checks the regex validation.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-SRV-002, REQ-ENT-003
     *
     * @scenario
     *
     *  Try to create one TransferOptionalPrimitivesDao instance with wrong regex pattern.
     *
     *  Check if the instance creation gives back Validation exception and contains PATTERN_VALIDATION_FAILED code.
     */
    @Test
    @TestCase("TransferRegexValidatorFailsForInvalidInput")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testRegexValidatorFailsForInvalidInput() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withRegexAttr("hello-bello")
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PATTERN_VALIDATION_FAILED")),
                hasProperty("location", equalTo("regexAttr")))
        ));
    }

    /**
     * The transfer object mapped entity with optional primitive fields. The test checks the max string length validation.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-SRV-002, REQ-ENT-003
     *
     * @scenario
     *
     *  Try to create one TransferOptionalPrimitivesDao instance with too long string.
     *
     *  Check if the instance creation gives back Validation exception and contains MAX_LENGTH_VALIDATION_FAILED code.
     *
     */
    @Test
    @TestCase("TransferMaxLengthValidatorFailsForInvalidInput")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testMaxLengthValidatorFailsForInvalidInput() {
        String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Imperdiet sed euismod nisi porta lorem mollis." +
                "Ac ut consequat semper viverra." +
                "Ultrices mi tempus imperdiet nulla malesuada pellentesque elit." +
                "Venenatis lectus magna fringilla urna porttitor rhoncus. In vitae turpis massa sed." +
                "Congue mauris rhoncus aenean vel elit. ";

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withStringAttr(lipsum)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MAX_LENGTH_VALIDATION_FAILED")),
                hasProperty("location", equalTo("stringAttr")))
        ));
    }

    /**
     * The transfer object mapped entity with optional primitive fields. The test checks the precision validation on Integer type number.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-SRV-002, REQ-ENT-005
     *
     * @scenario
     *
     *  Try to create one TransferOptionalPrimitivesDao instance with bigger precision that 9 number.
     *
     *  Check if the instance creation gives back Validation exception and contains PRECISION_VALIDATION_FAILED code.
     *
     */
    @Test
    @TestCase("TransferPrecisionValidation")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testPrecisionValidation() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withIntegerAttr(1234567890)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                hasProperty("location", equalTo("integerAttr")))
        ));
    }

    /**
     * The transfer object mapped entity with optional primitive fields. The test checks the precision validation on Decimal type number.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     *
     * @scenario
     *
     *  Try to create TransferOptionalPrimitivesDao instances with the all variation of wrong precision number.
     *
     *  Check if the instance creation gives back Validation exception and contains SCALE_VALIDATION_FAILED code.
     *
     *  Create TransferOptionalPrimitivesDao instances with the all variation of precision number that are valid decimals.
     *
     *  Check the instances creation doesn't give back any Validation exception.
     *
     */
    @Test
    @TestCase("TransferMaxPrecision")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testMaxPrecision() {
        ValidationException validationException = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withScaledAttr(1234567890.0)
                        .build())
        );
        assertThat(validationException.getValidationResults(), containsInAnyOrder(matchPrecisionValidationForAttribute("scaledAttr")));

        ValidationException validationException1 = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withScaledAttr(123456789.1)
                        .build())
        );
        assertThat(validationException1.getValidationResults(), containsInAnyOrder(matchPrecisionValidationForAttribute("scaledAttr")));

        TransferOptionalPrimitives t1 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withScaledAttr(123456789.0)
                .build());
        assertEquals(Optional.of(123456789.0), t1.getScaledAttr());

        TransferOptionalPrimitives t2 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withScaledAttr(12345678.1)
                .build());
        assertEquals(Optional.of(12345678.1), t2.getScaledAttr());

        TransferOptionalPrimitives t3 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withScaledAttr(1234567.12)
                .build());
        assertEquals(Optional.of(1234567.12), t3.getScaledAttr());

        TransferOptionalPrimitives t4 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withScaledAttr(1234567.1200)
                .build());
        assertEquals(Optional.of(1234567.12), t4.getScaledAttr());
    }

    /**
     * The transfer object mapped entity with optional primitive fields. The test checks the scaled validation on Decimal type number.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-SRV-002, REQ-ENT-005
     *
     * @scenario
     *
     *  Try to create TransferOptionalPrimitivesDao instances with bigger scale then 2.
     *
     *
     *  Check if the instance creation gives back Validation exception and contains MAX_LENGTH_VALIDATION_FAILED code.
     *
     */
    @Test
    @TestCase("TransferScaleValidation")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void testScaleValidation() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withScaledAttr(123456.789)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchScaleValidationForAttribute("scaledAttr")
        ));
    }

}
