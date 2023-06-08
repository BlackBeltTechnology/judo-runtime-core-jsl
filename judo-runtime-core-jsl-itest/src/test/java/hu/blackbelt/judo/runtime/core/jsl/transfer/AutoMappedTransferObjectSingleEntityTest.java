package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.containersinglecompositionentity.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.referenceentity.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferSingleEntityDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
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
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AutoMappedTransferObjectSingleEntityTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new AutoMappedTransferSingleEntityDaoModules();
    }

    @Override
    public String getModelName() {
        return "AutoMappedTransferSingleEntity";
    }

    @Inject
    ReferenceEntityDao referenceEntityDao;

    @Inject
    ContainerSingleCompositionEntityDao containerSingleCompositionEntityDao;

    /**
     *  [description]
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferSingleEntity.jsl
     *
     * @positiveRequirements REQ-SRV-006
     *
     * @scenario
     *
     *
     */
    @Test
    @Disabled("")
    @TestCase("AutoMappedTransferCreationWithValidOptionalPrimitiveFields")
    @Requirement(reqs = {
            "",

    })
    void testAutoMappedTransferCreationWithSingleCompositionVariations() {

        ReferenceEntity  referenceEntityCompositionDefault = referenceEntityDao.create(
                ReferenceEntity
                        .builder()
                        .withName("CompositionDefault")
                        .build()
        );

        ReferenceEntity  referenceEntityRequiredCompositionDefault = referenceEntityDao.create(
                ReferenceEntity
                        .builder()
                        .withName("RequiredCompositionDefault")
                        .build()
        );

        ReferenceEntity  singleCompositionEntityReference =  referenceEntityDao.create(ReferenceEntity.builder().build());
        ReferenceEntity  requireSingleCompositionEntity =  referenceEntityDao.create(ReferenceEntity.builder().build());

        ContainerSingleCompositionEntity containerSingleCompositionEntity =
                containerSingleCompositionEntityDao.create(ContainerSingleCompositionEntity
                        .builder()
                        .withSingleCompositionEntity(singleCompositionEntityReference)
                        .withRequireSingleCompositionEntity(requireSingleCompositionEntity)
                        .build()
                );

    }


    private Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}
