package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersingleassociation.AutoMappedContainerSingleAssociation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersingleassociation.AutoMappedContainerSingleAssociationAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersingleassociation.AutoMappedContainerSingleAssociationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglecomposition.AutoMappedContainerSingleComposition;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglecomposition.AutoMappedContainerSingleCompositionDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglecompositionderivedentity.AutoMappedContainerSingleCompositionDerivedEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglecompositionderivedentity.AutoMappedContainerSingleCompositionDerivedEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglerelationderivedentity.AutoMappedContainerSingleRelationDerivedEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglerelationderivedentity.AutoMappedContainerSingleRelationDerivedEntityAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedcontainersinglerelationderivedentity.AutoMappedContainerSingleRelationDerivedEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedreferenceentity.AutoMappedReferenceEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedreferenceentity.AutoMappedReferenceEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedtwowayreferenceentity.AutoMappedTwoWayReferenceEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfersingleentity.automappedtransfersingleentity.automappedtwowayreferenceentity.AutoMappedTwoWayReferenceEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferSingleEntityDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeJudoDatasourceByClassExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AutoMappedTransferObjectSingleEntityTest {

    @RegisterExtension
    static JudoRuntimeJudoDatasourceByClassExtension runtimeExtension = new JudoRuntimeJudoDatasourceByClassExtension("AutoMappedTransferSingleEntity", new AutoMappedTransferSingleEntityDaoModules());

    @Inject
    AutoMappedContainerSingleAssociationDao autoMappedContainerSingleAssociationDao;

    @Inject
    AutoMappedReferenceEntityDao autoMappedReferenceEntityDao;

    @Inject
    AutoMappedContainerSingleCompositionDao autoMappedContainerSingleCompositionDao;

    @Inject
    AutoMappedContainerSingleCompositionDerivedEntityDao autoMappedContainerSingleCompositionDerivedEntityDao;

    @Inject
    AutoMappedContainerSingleRelationDerivedEntityDao autoMappedContainerSingleRelationDerivedEntityDao;

    @Inject
    AutoMappedTwoWayReferenceEntityDao twoWayReferenceEntityDao;

    /**
     * This test checks the auto mapped transfer object on single entity composition fields.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferSingleEntity.jsl
     *
     *
     * @scenario
     *
     *  Create an auto mapped transfer object that contains all type of single composited entity fields.
     *
     *  Check the dao functionality works well.
     *
     */
    @Test
    @TestCase("AutoMappedTransferWithSingleAggregationVariations")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SRV-006"

    })
    void testAutoMappedTransferWithSingleAggregationVariations() {

        AutoMappedContainerSingleComposition autoMappedContainerSingleComposition =
                autoMappedContainerSingleCompositionDao.create(AutoMappedContainerSingleComposition
                        .builder()
                        .withSingleComposition(AutoMappedReferenceEntity.builder().withName("singleComposition").build())
                        .withSingleRequiredComposition(AutoMappedReferenceEntity.builder().withName("singleRequiredComposition").build())
                        .build()
                );

        // TODO JNG-4909 The field not contains the default value
//        assertEquals(autoMappedReferenceEntityRequiredCompositionDefault.identifier(), autoMappedContainerSingleComposition.getSingleCompositionDefaultEntity().orElseThrow().identifier());
//        autoMappedContainerSingleComposition = autoMappedContainerSingleCompositionDao.update(autoMappedContainerSingleComposition);
//        assertEquals(autoMappedReferenceEntityRequiredCompositionDefault.identifier(), autoMappedContainerSingleComposition.getSingleCompositionDefaultEntity().orElseThrow().identifier());

        assertEquals(Optional.of("singleComposition"), autoMappedContainerSingleComposition.getSingleComposition().orElseThrow().getName());
        assertEquals(Optional.of("singleRequiredComposition"), autoMappedContainerSingleComposition.getSingleRequiredComposition().getName());

        // update the name of the compositions

        autoMappedContainerSingleComposition.getSingleComposition().orElseThrow().setName("singleCompositionRenamed");
        autoMappedContainerSingleComposition.getSingleRequiredComposition().setName("singleRequiredCompositionRenamed");

        autoMappedContainerSingleComposition = autoMappedContainerSingleCompositionDao.update(autoMappedContainerSingleComposition);

        assertEquals(Optional.of("singleCompositionRenamed"), autoMappedContainerSingleComposition.getSingleComposition().orElseThrow().getName());
        assertEquals(Optional.of("singleRequiredCompositionRenamed"), autoMappedContainerSingleComposition.getSingleRequiredComposition().getName());

        // composition deletion

        autoMappedContainerSingleComposition.setSingleComposition(null);

        autoMappedContainerSingleComposition = autoMappedContainerSingleCompositionDao.update(autoMappedContainerSingleComposition);

        assertTrue(autoMappedReferenceEntityDao.query().execute().stream().filter(t -> t.getName().equals(Optional.of("singleCompositionRenamed"))).toList().isEmpty());

        autoMappedContainerSingleComposition.setSingleComposition(autoMappedReferenceEntityDao.create(AutoMappedReferenceEntity.builder().withName("singleCompositionBind").build()));

        AutoMappedContainerSingleComposition referenceForLambda = autoMappedContainerSingleComposition;
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> autoMappedContainerSingleCompositionDao.update(referenceForLambda)
        );

        assertTrue(thrown.getMessage().contains("Identifier cannot be set on new association reference element"));
        assertTrue(thrown.getMessage().contains("#singleComposition"));


        autoMappedContainerSingleComposition.setSingleRequiredComposition(null);
        AutoMappedContainerSingleComposition referenceForLambda1 = autoMappedContainerSingleComposition;
        ValidationException thrown1 = assertThrows(
                ValidationException.class,
                () -> autoMappedContainerSingleCompositionDao.update(referenceForLambda1)
        );

        assertThat(thrown1.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredComposition")))
        ));

    }


    /**
     *  This test checks the auto mapped transfer object on single entity relation fields.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferSingleEntity.jsl
     *
     *
     * @scenario
     *
     *  Create an auto mapped transfer object that contains all type of single relation entity fields.
     *
     *  Check the dao functionality works well.
     *
     */
    @Test
    @TestCase("AutoMappedTransferWithSingleAssociationVariations")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-SRV-006"

    })
    void testAutoMappedTransferWithSingleAssociationVariations() {

        AutoMappedReferenceEntity singleRelation = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("SingleRelation")
                        .build()
        );

        AutoMappedReferenceEntity singleRequiredRelation = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("SingleRequiredRelation")
                        .build()
        );

        AutoMappedTwoWayReferenceEntity twoWayReferenceEntity = twoWayReferenceEntityDao.create(
                AutoMappedTwoWayReferenceEntity
                        .builder()
                        .withName("TwoWayRelation")
                        .build()
        );

        AutoMappedContainerSingleAssociation containerSingleAssociation =
                autoMappedContainerSingleAssociationDao.create(
                        AutoMappedContainerSingleAssociation
                                .builder()
                                .build(),
                        AutoMappedContainerSingleAssociationAttachedRelationsForCreate
                                .builder()
                                .withSingleRelation(singleRelation)
                                .withSingleRequiredRelation(singleRequiredRelation)
                                .withTwoWayReferenceEntityRelation(twoWayReferenceEntity)
                                .build()
                );

        assertEquals(Optional.of(singleRelation), autoMappedContainerSingleAssociationDao.querySingleRelation(containerSingleAssociation));
        assertEquals(singleRequiredRelation, autoMappedContainerSingleAssociationDao.querySingleRequiredRelation(containerSingleAssociation));
        assertEquals(Optional.of(twoWayReferenceEntity), autoMappedContainerSingleAssociationDao.queryTwoWayReferenceEntityRelation(containerSingleAssociation));
        assertEquals(Optional.of(containerSingleAssociation), twoWayReferenceEntityDao.queryTwoWayContainerRelation(twoWayReferenceEntity));

        // update name

        singleRelation.setName("SingleRelationRenamed");
        singleRequiredRelation.setName("SingleRequiredRelationRenamed");
        twoWayReferenceEntity.setName("TwoWayRelationRenamed");

        singleRelation = autoMappedReferenceEntityDao.update(singleRelation);
        singleRequiredRelation = autoMappedReferenceEntityDao.update(singleRequiredRelation);
        twoWayReferenceEntity = twoWayReferenceEntityDao.update(twoWayReferenceEntity);

        assertEquals(Optional.of("SingleRelationRenamed"), autoMappedContainerSingleAssociationDao.querySingleRelation(containerSingleAssociation).orElseThrow().getName());
        assertEquals(Optional.of("SingleRequiredRelationRenamed"), autoMappedContainerSingleAssociationDao.querySingleRequiredRelation(containerSingleAssociation).getName());
        assertEquals(Optional.of("TwoWayRelationRenamed"), autoMappedContainerSingleAssociationDao.queryTwoWayReferenceEntityRelation(containerSingleAssociation).orElseThrow().getName());

        // unset association

        autoMappedContainerSingleAssociationDao.unsetSingleRelation(containerSingleAssociation);

        assertTrue(autoMappedContainerSingleAssociationDao.querySingleRelation(containerSingleAssociation).isEmpty());
        assertTrue(autoMappedReferenceEntityDao.getById(singleRelation.identifier()).isPresent());

        autoMappedContainerSingleAssociationDao.unsetTwoWayReferenceEntityRelation(containerSingleAssociation);

        assertTrue(autoMappedContainerSingleAssociationDao.queryTwoWayReferenceEntityRelation(containerSingleAssociation).isEmpty());
        assertTrue(twoWayReferenceEntityDao.getById(twoWayReferenceEntity.identifier()).isPresent());

        // set

        autoMappedContainerSingleAssociationDao.setSingleRelation(containerSingleAssociation, singleRelation);

        assertTrue(autoMappedContainerSingleAssociationDao.querySingleRelation(containerSingleAssociation).isPresent());

        autoMappedContainerSingleAssociationDao.setTwoWayReferenceEntityRelation(containerSingleAssociation, twoWayReferenceEntity);

        assertTrue(autoMappedContainerSingleAssociationDao.queryTwoWayReferenceEntityRelation(containerSingleAssociation).isPresent());

        // try delete required

        AutoMappedReferenceEntity referenceForLambda = singleRequiredRelation;
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> autoMappedReferenceEntityDao.delete(referenceForLambda)
        );
        assertTrue(thrown.getMessage().contains("There are mandatory references that cannot be removed"));
        assertTrue(thrown.getMessage().contains("#singleRequiredRelation"));

        // delete not required

        autoMappedReferenceEntityDao.delete(singleRelation);

        assertTrue(autoMappedContainerSingleAssociationDao.querySingleRelation(containerSingleAssociation).isEmpty());
        assertTrue(autoMappedReferenceEntityDao.getById(singleRelation.identifier()).isEmpty());

    }

    /**
     *  This test checks the auto mapped transfer object on single entity derived members that refer composition fields.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferSingleEntity.jsl
     *
     *
     * @scenario
     *
     *  Create an auto mapped transfer object that contains derived members that refer composition fields.
     *
     *  Check the derived member contains the expressions.
     *
     */
    @Test
    @TestCase("AutoMappedTransferWithCompositionDerivedVariations")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-SRV-006"

    })
    void testAutoMappedTransferWithCompositionDerivedVariations() {

        AutoMappedContainerSingleCompositionDerivedEntity autoMappedContainerSingleCompositionDerivedEntity =
                autoMappedContainerSingleCompositionDerivedEntityDao.create(AutoMappedContainerSingleCompositionDerivedEntity
                        .builder()
                        .withSingleComposition(AutoMappedReferenceEntity.builder().withName("singleComposition").build())
                        .withSingleRequiredComposition(AutoMappedReferenceEntity.builder().withName("singleRequiredComposition").build())
                        .build()
        );

        assertEquals(autoMappedContainerSingleCompositionDerivedEntityDao
                .querySingleCompositionEntityExpressionDerived(autoMappedContainerSingleCompositionDerivedEntity).orElseThrow().identifier(),
                autoMappedContainerSingleCompositionDerivedEntity.getSingleComposition().orElseThrow().identifier()
        );
        assertEquals(autoMappedContainerSingleCompositionDerivedEntityDao
                .querySingleComposition(autoMappedContainerSingleCompositionDerivedEntity).orElseThrow().identifier(),
                autoMappedContainerSingleCompositionDerivedEntity.getSingleComposition().orElseThrow().identifier()
        );
        assertEquals(autoMappedContainerSingleCompositionDerivedEntityDao
                .querySingleRequiredComposition(autoMappedContainerSingleCompositionDerivedEntity).identifier(),
                 autoMappedContainerSingleCompositionDerivedEntity.getSingleRequiredComposition().identifier()
        );
        assertEquals(autoMappedContainerSingleCompositionDerivedEntityDao
                        .querySingleCompositionEntityQueryMemberDerived(autoMappedContainerSingleCompositionDerivedEntity).orElseThrow().identifier(),
                autoMappedContainerSingleCompositionDerivedEntity.getSingleComposition().orElseThrow().identifier()
        );

    }

    /**
     *  This test checks the auto mapped transfer object on single entity derived members that refer relation fields.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferSingleEntity.jsl
     *
     *
     * @scenario
     *
     *  Create an auto mapped transfer object that contains derived members that refer composition fields.
     *
     *  Check the derived member contains the expressions.
     */
    @Test
    @TestCase("AutoMappedTransferWithRelationOnDerivedVariations")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-SRV-006"

    })
    void testAutoMappedTransferWithRelationOnDerivedVariations() {

        AutoMappedReferenceEntity singleRelation = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("SingleRelation")
                        .build()
        );

        AutoMappedReferenceEntity singleRequiredRelation = autoMappedReferenceEntityDao.create(
                AutoMappedReferenceEntity
                        .builder()
                        .withName("SingleRequiredRelation")
                        .build()
        );

        AutoMappedTwoWayReferenceEntity twoWayReferenceEntity = twoWayReferenceEntityDao.create(
                AutoMappedTwoWayReferenceEntity
                        .builder()
                        .withName("TwoWayRelation")
                        .build()
        );

        AutoMappedContainerSingleRelationDerivedEntity containerSingleRelationDerivedEntity =
                autoMappedContainerSingleRelationDerivedEntityDao.create(
                        AutoMappedContainerSingleRelationDerivedEntity
                                .builder()
                                .build(),
                        AutoMappedContainerSingleRelationDerivedEntityAttachedRelationsForCreate
                                .builder()
                                .withSingleRelation(singleRelation)
                                .withSingleRequiredRelation(singleRequiredRelation)
                                .withTwoWayReferenceEntityRelation(twoWayReferenceEntity)
                                .build()
                );

        assertEquals(singleRelation.identifier(), autoMappedContainerSingleRelationDerivedEntityDao.querySingleRelation(containerSingleRelationDerivedEntity).orElseThrow().identifier());
        assertEquals(singleRelation.identifier(), autoMappedContainerSingleRelationDerivedEntityDao.querySingleRelationEntityMemberDerived(containerSingleRelationDerivedEntity).orElseThrow().identifier());
        assertEquals(singleRequiredRelation.identifier(), autoMappedContainerSingleRelationDerivedEntityDao.querySingleRelationEntityRequiredMemberDerived(containerSingleRelationDerivedEntity).orElseThrow().identifier());
        assertEquals(singleRelation.identifier(), autoMappedContainerSingleRelationDerivedEntityDao.querySingleRelationEntityQueryMemberDerived(containerSingleRelationDerivedEntity).orElseThrow().identifier());

    }

    private Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}
