package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;

import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferreadsprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.entityformapping.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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
    TransferReadsPrimitivesDao transferReadsPrimitivesDao;

    @Inject
    TransferRequiredPrimitivesDao transferRequiredPrimitivesDao;

    @Inject
    EntityForMappingDao entityForMappingDao;

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
    private org.hamcrest.Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}
