package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferoptionalprimitives.TransferOptionalPrimitives;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferoptionalprimitives.TransferOptionalPrimitivesDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class TransferPrecisionTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("MappedTransferPrimitives", new MappedTransferPrimitivesDaoModules());

    @Inject
    TransferOptionalPrimitivesDao transferOptionalPrimitivesDao;

    /**
     * The test checks if the numeric type precision is greater than the max.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-TYPE-005
     *
     * @scenario
     *
     *  Try to create a TransferOptionalPrimitives instance with integerAttr that have greater precision than 9
     *
     *  Check if the instance creation gives back Validation exception and contains PRECISION_VALIDATION_FAILED code.
     *
     */
    @Test
    @TestCase("TransferPrecisionValidatorFailsWithPrecisionOverflow")
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
    public void testPrecisionValidatorFailsWithPrecisionOverflow() {
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
     * The test checks if the numeric type scale is greater than max.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-TYPE-005
     *
     * @scenario
     *
     *  Try to create a TransferOptionalPrimitives instance with integerAttr that have greater scale than 2
     *
     *  Check if the instance creation gives back Validation exception and contains SCALE_VALIDATION_FAILED code.
     */
    @Test
    @TestCase("TransferScaleValidatorFailsWithScaleOverflow")
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
    public void testScaleValidatorFailsWithScaleOverflow() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withScaledAttr(123456.123)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("SCALE_VALIDATION_FAILED")),
                hasProperty("location", equalTo("scaledAttr")))
        ));
    }

    /**
     * The test checks if the numeric type precision and scale is greater than max.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @negativeRequirements REQ-TYPE-005
     *
     * @scenario
     *
     *  Try to create a TransferOptionalPrimitives instance with integerAttr that have greater scale than 2 and greater precision than 9
     *
     *  Check if the instance creation gives back Validation exception and contains SCALE_VALIDATION_FAILED and PRECISION_VALIDATION_FAILED code.
     *
     */
    @Test
    @TestCase("TransferValidatePrecisionAndScaleOverflow")
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
    public void testValidatePrecisionAndScaleOverflow() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                        .withScaledAttr(1234567.123)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("SCALE_VALIDATION_FAILED")),
                        hasProperty("location", equalTo("scaledAttr"))),
                allOf(
                        hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                        hasProperty("location", equalTo("scaledAttr")))
        ));

    }

    /**
     * The test checks if the numeric type  precision is the max and the scale is zero on a mapped transfer object
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferPrimitives.jsl
     *
     * @positiveRequirements REQ-TYPE-005
     *
     * @scenario
     *
     *  Create a TransferOptionalPrimitives instance with integerAttr that have zero in scale.
     *
     *  Check if is converted to a valid Decimal number.
     *
     */
    @Test
    @TestCase("TransferScaleValidatorPassesForValueWithoutScale")
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
    public void testScaleValidatorPassesForValueWithoutScale() {
        TransferOptionalPrimitives created = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder().withScaledAttr(123456789.0).build());

        assertThat(created.getScaledAttr(), equalTo(Optional.of(123456789.0)));

    }

}
