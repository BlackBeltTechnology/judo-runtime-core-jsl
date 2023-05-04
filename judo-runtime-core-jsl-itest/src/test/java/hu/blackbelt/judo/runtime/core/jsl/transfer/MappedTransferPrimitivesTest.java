package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;

import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaultexpressions.TransferWithPrimitiveDefaultExpressions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaultexpressions.TransferWithPrimitiveDefaultExpressionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithprimitivedefaults.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.entityformapping.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithidentifiers.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredprimitivedefaults.TransferWithRequiredPrimitiveDefaults;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferwithrequiredprimitivedefaults.TransferWithRequiredPrimitiveDefaultsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFields;
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
    TransferPrimitivesDao transferPrimitivesDao;

    @Inject
    TransferWithPrimitiveDefaultsDao transferWithPrimitiveDefaultsDao;

    @Inject
    TransferRequiredPrimitivesDao transferRequiredPrimitivesDao;

    @Inject
    EntityForMappingDao entityForMappingDao;

    @Inject
    TransferWithIdentifiersDao transferWithIdentifiersDao;

    @Inject
    TransferWithRequiredPrimitiveDefaultsDao transferWithRequiredPrimitiveDefaultsDao;

    @Inject
    TransferWithPrimitiveDefaultExpressionsDao transferWithPrimitiveDefaultExpressionsDao;

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
    @TestCase("MappedTransferPrimitives")
    @Requirement(reqs = {
            "",
    })
    void testMappedTransferPrimitivesWithoutValue() {

        TransferPrimitives transferPrimitives = transferPrimitivesDao.create(TransferPrimitives.builder().build());

        assertEquals(Optional.empty(), transferPrimitives.getIntegerAttr());
        assertEquals(Optional.empty(), transferPrimitives.getScaledAttr());
        assertEquals(Optional.empty(), transferPrimitives.getStringAttr());
        assertEquals(Optional.empty(), transferPrimitives.getRegexAttr());
        assertEquals(Optional.empty(), transferPrimitives.getBoolAttr());
        assertEquals(Optional.empty(), transferPrimitives.getDateAttr());
        assertEquals(Optional.empty(), transferPrimitives.getTimestampAttr());
        assertEquals(Optional.empty(), transferPrimitives.getTimeAttr());
        assertEquals(Optional.empty(), transferPrimitives.getEnumAttr());
        assertEquals(Optional.empty(), transferPrimitives.getBinaryAttr());

    }

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
    @TestCase("MappedTransferRequiredPrimitives")
    @Requirement(reqs = {
            "",
    })
    void testMappedTransferRequiredPrimitives() {

        TransferPrimitives transferPrimitives = transferPrimitivesDao.create(TransferPrimitives.builder()
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


        assertEquals(Optional.of(1), transferPrimitives.getIntegerAttr());
        assertEquals(Optional.of(1.23), transferPrimitives.getScaledAttr());
        assertEquals(Optional.of("test"), transferPrimitives.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), transferPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), transferPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferPrimitives.getTimeAttr());
        assertEquals("test.txt", transferPrimitives.getBinaryAttr().orElseThrow().getFileName());
        assertEquals(Optional.of(Enum.EnumA), transferPrimitives.getEnumAttr());
    }

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
    @Test()
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-ENT-001",
            "REQ-ENT-002"
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
    @Test()
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testMissingRequiredFieldsInEntitiesButNotRequiredInTransferThrowExceptions() {
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
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003"
    })
    public void testIdentifierFieldsAreUnique() {
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

//        thrown = assertThrows(
//                ValidationException.class,
//                () ->
//                        entityWithIdentifiersContainerDao.create(
//                                EntityWithIdentifiersContainer.builder()
//                                        .withEntitiesWithIdentifiers(ImmutableList.of(
//                                                EntityWithIdentifiers.builder()
//                                                        .withIntegerAttr(2)
//                                                        .build(),
//                                                EntityWithIdentifiers.builder()
//                                                        .withIntegerAttr(2)
//                                                        .build()
//                                        ))
//                                        .build()));
//
//        assertThat(thrown.getValidationResults(), containsInAnyOrder(
//                allOf(
//                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
//                        hasProperty("location", equalTo("entitiesWithIdentifiers[1].integerAttr")))
//        ));

    }


    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testFieldsAreNonUnique() {
        LocalDate now = LocalDate.now();
        TransferPrimitives transf1 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(Enum.EnumA)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, transf1.getIntegerAttr().get());

        TransferPrimitives transf2 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(Enum.EnumA)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, transf2.getIntegerAttr().get());
    }

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
            "REQ-ENT-012"
    })
    public void testUpdateOptionalTransferAfterCreation() {
        TransferPrimitives transferPrimitives = transferPrimitivesDao.create(TransferPrimitives.builder().build());

        List<TransferPrimitives> list = transferPrimitivesDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.empty(), transferPrimitives.getIntegerAttr());
        assertEquals(Optional.empty(), transferPrimitives.getScaledAttr());
        assertEquals(Optional.empty(), transferPrimitives.getStringAttr());
        assertEquals(Optional.empty(), transferPrimitives.getRegexAttr());
        assertEquals(Optional.empty(), transferPrimitives.getBoolAttr());
        assertEquals(Optional.empty(), transferPrimitives.getDateAttr());
        assertEquals(Optional.empty(), transferPrimitives.getTimestampAttr());
        assertEquals(Optional.empty(), transferPrimitives.getTimeAttr());
        assertEquals(Optional.empty(), transferPrimitives.getBinaryAttr());
        assertEquals(Optional.empty(), transferPrimitives.getEnumAttr());

        transferPrimitives.setIntegerAttr(1);
        transferPrimitives.setScaledAttr(1.23);
        transferPrimitives.setStringAttr("test");
        transferPrimitives.setRegexAttr("+36 333-333-3333");
        transferPrimitives.setBoolAttr(true);
        transferPrimitives.setDateAttr(LocalDate.of(2022, 7, 11));
        transferPrimitives.setTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"));
        transferPrimitives.setTimeAttr(LocalTime.parse("23:59:59"));
        transferPrimitives.setBinaryAttr(FileType.builder().fileName("test.txt").build());
        transferPrimitives.setEnumAttr(Enum.EnumA);

        transferPrimitives = transferPrimitivesDao.update(transferPrimitives);

        assertEquals(Optional.of(1), transferPrimitives.getIntegerAttr());
        assertEquals(Optional.of(1.23), transferPrimitives.getScaledAttr());
        assertEquals(Optional.of("test"), transferPrimitives.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), transferPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), transferPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), transferPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), transferPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), transferPrimitives.getTimeAttr());
        assertEquals("test.txt", transferPrimitives.getBinaryAttr().get().getFileName());
        assertEquals(Optional.of(Enum.EnumA), transferPrimitives.getEnumAttr());
    }

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
            "REQ-ENT-002"
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
            "REQ-ENT-002"
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
            "REQ-ENT-002"
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

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testRegexValidatorFailsForInvalidInput() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
                        .withRegexAttr("hello-bello")
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PATTERN_VALIDATION_FAILED")),
                hasProperty("location", equalTo("regexAttr")))
        ));
    }

    @Test
    @Disabled("")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002"
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
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
                        .withStringAttr(lipsum)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MAX_LENGTH_VALIDATION_FAILED")),
                hasProperty("location", equalTo("stringAttr")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testPrecisionValidation() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
                        .withIntegerAttr(1234567890)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                hasProperty("location", equalTo("integerAttr")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testMaxPrecision() {
        ValidationException validationException = assertThrows(
                ValidationException.class,
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
                        .withScaledAttr(1234567890.0)
                        .build())
        );
        assertThat(validationException.getValidationResults(), containsInAnyOrder(matchPrecisionValidationForAttribute("scaledAttr")));

        ValidationException validationException1 = assertThrows(
                ValidationException.class,
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
                        .withScaledAttr(123456789.1)
                        .build())
        );
        assertThat(validationException1.getValidationResults(), containsInAnyOrder(matchPrecisionValidationForAttribute("scaledAttr")));

        TransferPrimitives t1 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withScaledAttr(123456789.0)
                .build());
        assertEquals(Optional.of(123456789.0), t1.getScaledAttr());

        TransferPrimitives t2 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withScaledAttr(12345678.1)
                .build());
        assertEquals(Optional.of(12345678.1), t2.getScaledAttr());

        TransferPrimitives t3 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withScaledAttr(1234567.12)
                .build());
        assertEquals(Optional.of(1234567.12), t3.getScaledAttr());

        TransferPrimitives t4 = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withScaledAttr(1234567.1200)
                .build());
        assertEquals(Optional.of(1234567.12), t4.getScaledAttr());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testScaleValidation() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
                        .withScaledAttr(123456.789)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchScaleValidationForAttribute("scaledAttr")
        ));
    }

}
