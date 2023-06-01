package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithrequiredprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferoptionalprimitiveswithconstructor.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.transferrequiredprimitiveswithconstructor.*;
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



    /**
     *
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
     */
    @Test
    @TestCase("TransferOptionalPrimitivesWithConstructor")
    @Requirement(reqs = {
            ""
    })
    void testTransferOptionalPrimitivesWithConstructor() {

        TransferOptionalPrimitivesWithConstructor transferWithPrimitives = transferOptionalPrimitivesWithConstructorDao.create(TransferOptionalPrimitivesWithConstructor.builder().build());

        assertEquals(1, transferOptionalPrimitivesWithConstructorDao.query().execute().size());
        assertEquals(Optional.of(1), transferWithPrimitives.getIntegerAttr());
        assertEquals(Optional.of(2.34), transferWithPrimitives.getScaledAttr());
        assertEquals(Optional.of("Hello there"), transferWithPrimitives.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), transferWithPrimitives.getRegexAttr());
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
        assertEquals(Optional.of("+36-1-123-123"), entityWithPrimitives.getRegexAttr());
        assertEquals(Optional.of(true), entityWithPrimitives.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityWithPrimitives.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityWithPrimitives.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithPrimitives.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), entityWithPrimitives.getEnumAttr());

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
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("TransferOptionalPrimitivesWithConstructor")
    @Requirement(reqs = {
            ""
    })
    void testTransferRequiredPrimitivesWithConstructor() {

        TransferRequiredPrimitivesWithConstructor transferRequiredPrimitives = transferRequiredPrimitivesWithConstructorDao.create(TransferRequiredPrimitivesWithConstructor.builder().build());

        assertEquals(1, transferRequiredPrimitivesWithConstructorDao.query().execute().size());
        assertEquals(1, transferRequiredPrimitives.getIntegerAttr());
        assertEquals(2.34, transferRequiredPrimitives.getScaledAttr());
        assertEquals("Hello there", transferRequiredPrimitives.getStringAttr());
        assertEquals("+36-1-123-123", transferRequiredPrimitives.getRegexAttr());
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
        assertEquals("+36-1-123-123", entityWithRequiredPrimitives.getRegexAttr());
        assertEquals(true, entityWithRequiredPrimitives.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityWithRequiredPrimitives.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entityWithRequiredPrimitives.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityWithRequiredPrimitives.getTimeAttr());
        assertEquals(Enum.EnumA, entityWithRequiredPrimitives.getEnumAttr());

    }

}
