package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;

import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaultexpressions.TransferWithPrimitiveDefaultExpressions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaultexpressions.TransferWithPrimitiveDefaultExpressionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithidentifiers.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredentityprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredprimitivedefaults.TransferWithRequiredPrimitiveDefaults;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.time.OffsetDateTime;

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
    TransferWithPrimitiveDefaultExpressionsDao transferWithPrimitiveDefaultExpressionsDao;

    @Inject
    TransferWithRequiredEntityPrimitivesDao transferWithRequiredEntityPrimitivesDao;

    /**
     * Test the optional fields are empty and mapped on a transfer objet.
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
     *  Create one TransferOptionalPrimitives.
     *
     *  Check all field mapped field is exsist and it is empty.
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
     * Test the fields are mapped on a transfer objet and contains the added primitives.
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
     *  Create one TransferOptionalPrimitives.
     *
     *  Check all fields contain the Dao added values.
     *
     */
    @Test
    @TestCase("MappedTransferRequiredPrimitives")
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
    }

    /**
     * Test required fields on a transfer objet and thrown errors when the Dao instance not contains the required values.
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
     */
    @Test()
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
     *  //TODO JNG-XXXX
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
     */
    @Test()
    @Disabled("JNG-XXXX")
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
     * Test the identifiers fields are mapped on a transfer objet and thrown errors when the Dao instance is not unique.
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
     * Test the optional fields are mapped on a transfer objet and do not throw any errors when the Dao made same instances.
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
     *  Check the second instance creation does not throw any errors.
     *
     */
    @Test
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
     * Test the update method on Dao when the mapped transfer object has optional fields.
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
     * @positiveRequirements REQ-SRV-002
     *
     * @scenario
     *
     *  Create one TransferOptionalPrimitives instance without parameters.
     *
     *  Add all field a primitive parameter.
     *
     *  Update with the dao.
     *
     *  Check the field are updated.
     *
     */
    @Test
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
    }

    /**
     * Test the fields with default value mapped on a transfer object and the instance contains the default value.
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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *
     */
    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4815")
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
    public void testEntityCreationWithPrimitiveDefaults() {
        TransferWithPrimitiveDefaults transferWithPrimitiveDefaults = transferWithPrimitiveDefaultsDao.create(TransferWithPrimitiveDefaults.builder().build());

        List<TransferWithPrimitiveDefaults> list = transferWithPrimitiveDefaultsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), transferWithPrimitiveDefaults.getIntegerAttr());
        assertEquals(Optional.of(2.34), transferWithPrimitiveDefaults.getScaledAttr());
        assertEquals(Optional.of("test"), transferWithPrimitiveDefaults.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), transferWithPrimitiveDefaults.getRegexAttr());
        assertEquals(Optional.of(true), transferWithPrimitiveDefaults.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferWithPrimitiveDefaults.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferWithPrimitiveDefaults.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferWithPrimitiveDefaults.getTimeAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59")), transferWithPrimitiveDefaults.getShortTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), transferWithPrimitiveDefaults.getEnumAttr());
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
     * @jslModel MappedTransferPrimitives.jsl
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
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4815")
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
    public void testEntityCreationRequiredWithPrimitiveDefaults() {
        TransferWithRequiredPrimitiveDefaults transferWithRequiredPrimitiveDefaults = transferWithRequiredPrimitiveDefaultsDao.create(TransferWithRequiredPrimitiveDefaults.builder().build());

        List<TransferWithRequiredPrimitiveDefaults> list = transferWithRequiredPrimitiveDefaultsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(1, transferWithRequiredPrimitiveDefaults.getIntegerAttr());
        assertEquals(2.34, transferWithRequiredPrimitiveDefaults.getScaledAttr());
        assertEquals("test", transferWithRequiredPrimitiveDefaults.getStringAttr());
        assertEquals("+36-1-123-123", transferWithRequiredPrimitiveDefaults.getRegexAttr());
        assertEquals(true, transferWithRequiredPrimitiveDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), transferWithRequiredPrimitiveDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), transferWithRequiredPrimitiveDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), transferWithRequiredPrimitiveDefaults.getTimeAttr());
        // There is no way to define default value in JSL for binary
        // assertEquals("test.txt", entityWithDefaults.getBinaryAttr().get().getFileName());
        assertEquals(Enum.EnumA, transferWithRequiredPrimitiveDefaults.getEnumAttr());
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
     * @jslModel MappedTransferPrimitives.jsl
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
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4815")
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
    public void testEntityCreationWithPrimitiveDefaultExpressions() {
        TransferWithPrimitiveDefaultExpressions transferWithPrimitiveDefaultExpressions = transferWithPrimitiveDefaultExpressionsDao.create(TransferWithPrimitiveDefaultExpressions.builder().build());

        List<TransferWithPrimitiveDefaultExpressions> list = transferWithPrimitiveDefaultExpressionsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), transferWithPrimitiveDefaultExpressions.getIntegerAttr());
        assertEquals(Optional.of(2.9), transferWithPrimitiveDefaultExpressions.getScaledAttr());
        assertEquals(Optional.of("true"), transferWithPrimitiveDefaultExpressions.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), transferWithPrimitiveDefaultExpressions.getRegexAttr());
        assertEquals(Optional.of(true), transferWithPrimitiveDefaultExpressions.getBoolAttr());
        assertEquals(Optional.of(LocalDate.now()), transferWithPrimitiveDefaultExpressions.getDateAttr());
        assertEquals(OffsetDateTime.now().toString().substring(1, 10),
                transferWithPrimitiveDefaultExpressions.getTimestampAttr().get().toString().substring(1, 10));
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferWithPrimitiveDefaultExpressions.getTimeAttr());
    }

    private org.hamcrest.Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

    private org.hamcrest.Matcher matchPrecisionValidationForAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                hasProperty("location", equalTo(attrName)));
    }

    private org.hamcrest.Matcher matchScaleValidationForAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("SCALE_VALIDATION_FAILED")),
                hasProperty("location", equalTo(attrName)));
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
     * @jslModel MappedTransferPrimitives.jsl
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
     * [description]
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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *
     */
    @Test
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
     * [description]
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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *
     */
    @Test
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
     * [description]
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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *
     */
    @Test
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
     * [description]
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
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *
     */
    @Test
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
