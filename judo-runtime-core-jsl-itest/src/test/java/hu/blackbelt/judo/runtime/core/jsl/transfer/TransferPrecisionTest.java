package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class TransferPrecisionTest extends AbstractJslTest {

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
    
    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-012"
    })
    public void testPrecisionValidatorFailsWithPrecisionOverflow() {
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
            "REQ-ENT-012"
    })
    public void testScaleValidatorFailsWithScaleOverflow() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
                        .withScaledAttr(123456.123)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("SCALE_VALIDATION_FAILED")),
                hasProperty("location", equalTo("scaledAttr")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-012"
    })
    public void testValidateDoubleWithPrecisionAndScaleOverflow() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferPrimitivesDao.create(TransferPrimitives.builder()
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

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-012"
    })
    public void testScaleValidatorPassesForValueWithoutScale() {
        TransferPrimitives created = transferPrimitivesDao.create(TransferPrimitives.builder()
                .withScaledAttr(123456789.0)
                .build());

        assertThat(created.getScaledAttr(), equalTo(Optional.of(123456789.0)));

    }

}
