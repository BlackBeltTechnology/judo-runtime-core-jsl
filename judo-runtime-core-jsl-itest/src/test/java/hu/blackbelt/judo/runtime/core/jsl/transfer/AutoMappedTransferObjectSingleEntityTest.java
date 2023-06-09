package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersingleassociation.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.containersingleassociationentity.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.containersinglecompositionentity.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.referenceentity.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglecomposition.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedreferenceentity.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferSingleEntityDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
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
    AutoMappedContainerSingleAssociationDao autoMappedContainerSingleAssociationDao;

    @Inject
    AutoMappedReferenceEntityDao autoMappedReferenceEntityDao;

    @Inject
    AutoMappedContainerSingleCompositionDao autoMappedContainerSingleCompositionDao;

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
    @TestCase("AutoMappedTransferWithSingleAggregationVariations")
    @Requirement(reqs = {
            "",

    })
    void testAutoMappedTransferWithSingleAggregationVariations() {

        AutoMappedReferenceEntity  autoMappedReference = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("CompositionDefault")
                        .build()
        );

        AutoMappedReferenceEntity  autoMappedReferenceEntityRequiredCompositionDefault = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("RequiredCompositionDefault")
                        .build()
        );

        AutoMappedContainerSingleComposition autoMappedContainerSingleComposition =
                autoMappedContainerSingleCompositionDao.create(AutoMappedContainerSingleComposition
                        .builder()
                        .withSingleCompositionEntity(autoMappedReferenceEntityDao.create(AutoMappedReferenceEntity.builder().withName("singleComposition").build()))
                        .withSingleRequiredCompositionEntity(autoMappedReferenceEntityDao.create(AutoMappedReferenceEntity.builder().withName("singleRequiredComposition").build()))
                        .build()
                );


        //TODO The field not contains the default value
//        assertEquals(autoMappedReferenceEntityRequiredCompositionDefault.identifier(), autoMappedContainerSingleComposition.getSingleCompositionDefaultEntity().orElseThrow().identifier());
//        autoMappedContainerSingleComposition = autoMappedContainerSingleCompositionDao.update(autoMappedContainerSingleComposition);
//        assertEquals(autoMappedReferenceEntityRequiredCompositionDefault.identifier(), autoMappedContainerSingleComposition.getSingleCompositionDefaultEntity().orElseThrow().identifier());

        assertEquals(Optional.of("singleComposition"), autoMappedContainerSingleComposition.getSingleCompositionEntity().orElseThrow().getName());
        assertEquals(Optional.of("singleRequiredComposition"), autoMappedContainerSingleComposition.getSingleRequiredCompositionEntity().getName());

        //update the name of the compositions

        autoMappedContainerSingleComposition.getSingleCompositionEntity().orElseThrow().setName("singleCompositionRenamed");
        autoMappedContainerSingleComposition.getSingleRequiredCompositionEntity().setName("singleRequiredCompositionRenamed");

        autoMappedContainerSingleComposition = autoMappedContainerSingleCompositionDao.update(autoMappedContainerSingleComposition);

        assertEquals(Optional.of("singleCompositionRenamed"), autoMappedContainerSingleComposition.getSingleCompositionEntity().orElseThrow().getName());
        assertEquals(Optional.of("singleRequiredCompositionRenamed"), autoMappedContainerSingleComposition.getSingleRequiredCompositionEntity().getName());

        //composition deletion

        autoMappedContainerSingleComposition.setSingleCompositionEntity(null);
        autoMappedContainerSingleComposition.setSingleCompositionEntity(autoMappedReferenceEntityDao.create(AutoMappedReferenceEntity.builder().withName("singleCompositionBind").build()));

        AutoMappedContainerSingleComposition referenceForLambda  = autoMappedContainerSingleComposition;
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> autoMappedContainerSingleCompositionDao.update(referenceForLambda)
        );

        assertTrue(thrown.getMessage().contains("Identifier cannot be different on containment reference element"));
        assertTrue(thrown.getMessage().contains("#singleCompositionEntity"));


        autoMappedContainerSingleComposition.setSingleRequiredCompositionEntity(null);
        AutoMappedContainerSingleComposition referenceForLambda1  = autoMappedContainerSingleComposition;
        ValidationException thrown1 = assertThrows(
                ValidationException.class,
                () -> autoMappedContainerSingleCompositionDao.update(referenceForLambda1)
        );

        assertThat(thrown1.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredCompositionEntity")))
        ));

    }


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
    @Disabled("JNG-4906")
    @TestCase("AutoMappedTransfernWithValidOptionalPrimitiveFields")
    @Requirement(reqs = {
            "",

    })
    void testAutoMappedTransferWithSingleAssociationVariations() {

        AutoMappedReferenceEntity  singleRelation = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("SingleRelation")
                        .build()
        );

        AutoMappedReferenceEntity  singleRequiredRelation = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("SingleRequiredRelation")
                        .build()
        );
    // TODO JNG-4906

//        AutoMappedContainerSingleAssociation containerSingleAssociation =
//                autoMappedContainerSingleAssociationDao.

    }

    private Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}
