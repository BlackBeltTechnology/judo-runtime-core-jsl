package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfercollectionentity.automappedtransfercollectionentity.transfercollectionentity.TransferCollectionEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfercollectionentity.automappedtransfercollectionentity.transfercollectionentity.TransferCollectionEntityAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfercollectionentity.automappedtransfercollectionentity.transfercollectionentity.TransferCollectionEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfercollectionentity.automappedtransfercollectionentity.transferreferenceentity.TransferReferenceEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.automappedtransfercollectionentity.automappedtransfercollectionentity.transferreferenceentity.TransferReferenceEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AutoMappedTransferCollectionEntityDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeJudoDatasourceByClassExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AutoMappedTransferObjectCollectionEntityTest {

    @RegisterExtension
    static JudoRuntimeJudoDatasourceByClassExtension runtimeExtension = new JudoRuntimeJudoDatasourceByClassExtension("AutoMappedTransferCollectionEntity", new AutoMappedTransferCollectionEntityDaoModules());

    @Inject
    TransferReferenceEntityDao transferReferenceEntityDao;

    @Inject
    TransferCollectionEntityDao transferCollectionEntityDao;


    /**
     * This test checks the auto mapped transfer object on multi entity composition fields work well.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferCollectionEntity.jsl
     *
     */
    @Test
    @TestCase("AutoMappedTransferWithMultiAggregationVariations")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-007",
            "REQ-SRV-006"
    })
    void testAutoMappedTransferWithMultiAggregationVariations() {

        TransferCollectionEntity collection = transferCollectionEntityDao.create(
                TransferCollectionEntity
                        .builder()
                        .withCompositionCollection(List.of(
                                TransferReferenceEntity.builder().withName("A").build(),
                                TransferReferenceEntity.builder().withName("B").build(),
                                TransferReferenceEntity.builder().withName("C").build(),
                                TransferReferenceEntity.builder().withName("D").build()
                        ))
                        .build()
        );

        assertTrue(transferCollectionEntityDao.getById(collection.identifier()).isPresent());
        assertEquals(4, transferReferenceEntityDao.countAll());
        List<TransferReferenceEntity> allRef = transferReferenceEntityDao.getAll();

        assertThat(transferCollectionEntityDao.queryCompositionCollection(collection).execute(),
                containsInAnyOrder(allRef.toArray()));

        // Update
        collection.getCompositionCollection().stream().forEach(t -> t.setName(t.getName().get() + "R"));
        collection = transferCollectionEntityDao.update(collection);
        collection.getCompositionCollection().stream().forEach(t ->
                assertTrue(t.getName().orElseThrow().contains("R"))
        );
        transferReferenceEntityDao.getAll().stream().forEach(t ->
                assertTrue(t.getName().orElseThrow().contains("R"))
        );
        allRef = transferReferenceEntityDao.getAll();

        TransferReferenceEntity a = allRef.stream().filter(t -> t.getName().get().equals("AR")).findFirst().get();
        TransferReferenceEntity b = allRef.stream().filter(t -> t.getName().get().equals("BR")).findFirst().get();
        TransferReferenceEntity c = allRef.stream().filter(t -> t.getName().get().equals("CR")).findFirst().get();
        TransferReferenceEntity d = allRef.stream().filter(t -> t.getName().get().equals("DR")).findFirst().get();

        // delete one

        transferReferenceEntityDao.delete(d);

        assertEquals(3, transferReferenceEntityDao.countAll());
        allRef = transferReferenceEntityDao.getAll();
        assertThat(allRef.stream().map(TransferReferenceEntity::identifier).toList(), containsInAnyOrder(a.identifier(), b.identifier(), c.identifier()));
        assertTrue(transferReferenceEntityDao.getById(d.identifier()).isEmpty());

        // create

        // TODO: JNG-5046 give back only the identifier correctly , fields are nulls
        d = transferCollectionEntityDao.createCompositionCollection(collection, TransferReferenceEntity.builder().withName("D").build());
        d = transferReferenceEntityDao.getById(d.identifier()).orElseThrow(); //temporary
        assertEquals(Optional.of("D"), d.getName());

        // delete collection

        transferCollectionEntityDao.delete(collection);

        //collection and all composition deleted
        assertEquals(0, transferCollectionEntityDao.countAll());
        assertEquals(0, transferReferenceEntityDao.countAll());

    }

    /**
     * This test checks the auto mapped transfer object on multi entity relation work well.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferCollectionEntity.jsl
     *
     */
    @Test
    @TestCase("AutoMappedTransferWithMultiAggregationVariations")
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
            "REQ-ENT-006",
            "REQ-SRV-006"

    })
    void testAutoMappedTransferWithMultiAssociationVariations() {

        TransferReferenceEntity uniRel1 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("uniRel1").build());
        TransferReferenceEntity uniRel2 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("uniRel2").build());
        TransferReferenceEntity uniRel3 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("uniRel3").build());
        TransferReferenceEntity uniRel4 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("uniRel4").build());

        TransferReferenceEntity biRel1 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("biRel1").build());
        TransferReferenceEntity biRel2 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("biRel2").build());
        TransferReferenceEntity biRel3 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("biRel3").build());

        TransferCollectionEntity collection = transferCollectionEntityDao.create(
                TransferCollectionEntity
                        .builder()
                        .build(),
                TransferCollectionEntityAttachedRelationsForCreate
                        .builder()
                        .withRelationCollection(List.of(uniRel1, uniRel2, uniRel3, uniRel4))
                        .withTwoWayRelationCollection(List.of(biRel1, biRel2, biRel3))
                        .build()
        );

        assertEquals(7, transferReferenceEntityDao.countAll());
        assertEquals(1, transferCollectionEntityDao.countAll());

        assertThat(transferCollectionEntityDao.queryRelationCollection(collection).execute().stream().map(t -> t.identifier()).toList(),
                containsInAnyOrder(uniRel1.identifier(), uniRel2.identifier(), uniRel3.identifier(), uniRel4.identifier()));

        assertThat(transferCollectionEntityDao.queryTwoWayRelationCollection(collection).execute().stream().map(t -> t.identifier()).toList(),
                containsInAnyOrder(biRel1.identifier(), biRel2.identifier(), biRel3.identifier()));

        assertEquals(transferReferenceEntityDao.queryRelationOptional(biRel1).orElseThrow().identifier(), collection.identifier());
        assertEquals(transferReferenceEntityDao.queryRelationOptional(biRel2).orElseThrow().identifier(), collection.identifier());
        assertEquals(transferReferenceEntityDao.queryRelationOptional(biRel3).orElseThrow().identifier(), collection.identifier());

        // update

        uniRel1.setName(uniRel1.getName().get() + "R");
        uniRel2.setName(uniRel2.getName().get() + "R");
        uniRel3.setName(uniRel3.getName().get() + "R");
        uniRel4.setName(uniRel4.getName().get() + "R");

        uniRel1 = transferReferenceEntityDao.update(uniRel1);
        uniRel2 = transferReferenceEntityDao.update(uniRel2);
        uniRel3 = transferReferenceEntityDao.update(uniRel3);
        uniRel4 = transferReferenceEntityDao.update(uniRel4);

        biRel1.setName(biRel1.getName().get() + "R");
        biRel2.setName(biRel2.getName().get() + "R");
        biRel3.setName(biRel3.getName().get() + "R");

        biRel1 = transferReferenceEntityDao.update(biRel1);
        biRel2 = transferReferenceEntityDao.update(biRel2);
        biRel3 = transferReferenceEntityDao.update(biRel3);

        transferCollectionEntityDao.queryRelationCollection(collection).execute().forEach(
                t -> assertTrue(t.getName().get().contains("R"))
        );
        transferCollectionEntityDao.queryTwoWayRelationCollection(collection).execute().forEach(
                t -> assertTrue(t.getName().get().contains("R"))
        );

        // delete one

        transferReferenceEntityDao.delete(uniRel4);

        assertTrue(transferReferenceEntityDao.getById(uniRel4.identifier()).isEmpty());
        TransferReferenceEntity ref = uniRel4;
        assertFalse(transferCollectionEntityDao.queryRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref.identifier())));

        transferReferenceEntityDao.delete(biRel3);

        assertTrue(transferReferenceEntityDao.getById(biRel3.identifier()).isEmpty());
        TransferReferenceEntity ref2 = biRel3;
        assertFalse(transferCollectionEntityDao.queryTwoWayRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref2.identifier())));

        // add

        uniRel4 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("uniRel4").build());

        transferCollectionEntityDao.addRelationCollection(collection, uniRel4);
        TransferReferenceEntity ref3 = uniRel4;
        assertTrue(transferCollectionEntityDao.queryRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref3.identifier())));

        biRel3 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("biRel3").build());

        transferCollectionEntityDao.addTwoWayRelationCollection(collection, biRel3);
        TransferReferenceEntity ref4 = biRel3;
        assertTrue(transferCollectionEntityDao.queryTwoWayRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref4.identifier())));
        assertEquals(collection.identifier(), transferReferenceEntityDao.queryRelationOptional(biRel3).orElseThrow().identifier());

        // create

        TransferReferenceEntity uniRel5 = transferCollectionEntityDao.createRelationCollection(collection, TransferReferenceEntity.builder().withName("uniRel5").build());
        TransferReferenceEntity ref5 = uniRel5;
        assertTrue(transferCollectionEntityDao.queryRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref5.identifier())));

        TransferReferenceEntity biRel4 = transferCollectionEntityDao.createTwoWayRelationCollection(collection, TransferReferenceEntity.builder().withName("biRel4").build());
        TransferReferenceEntity ref6 = biRel4;
        assertTrue(transferCollectionEntityDao.queryTwoWayRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref6.identifier())));


        // remove

        transferCollectionEntityDao.removeRelationCollection(collection, uniRel5);
        assertFalse(transferCollectionEntityDao.queryRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref5.identifier())));

        transferCollectionEntityDao.removeTwoWayRelationCollection(collection, biRel4);
        assertFalse(transferCollectionEntityDao.queryTwoWayRelationCollection(collection).execute().stream().anyMatch(t -> t.identifier().equals(ref6.identifier())));


        transferCollectionEntityDao.delete(collection);
        assertEquals(0, transferCollectionEntityDao.countAll());
        assertEquals(9, transferReferenceEntityDao.countAll());

    }

    /**
     * This test checks the auto mapped transfer object on multi entity derived members work well.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AutoMappedTransferCollectionEntity.jsl
     *
     */
    @Test
    @TestCase("AutoMappedTransferWithMultiDerivedVariations")
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
            "REQ-ENT-006",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-0010",
            "REQ-SRV-006"

    })
    void testAutoMappedTransferWithMultiDerivedVariations() {

        TransferReferenceEntity uniRel1 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("uniRel").build());
        TransferReferenceEntity uniRel2 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("uniRel2").build());

        TransferReferenceEntity biRel1 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("biRel").build());
        TransferReferenceEntity biRel2 = transferReferenceEntityDao.create(TransferReferenceEntity.builder().withName("biRel2").build());

        TransferCollectionEntity collection = transferCollectionEntityDao.create(
                TransferCollectionEntity
                        .builder()
                        .withCompositionCollection(List.of(
                                TransferReferenceEntity.builder().withName("comp").build(),
                                TransferReferenceEntity.builder().withName("comp").build()
                        ))
                        .build(),
                TransferCollectionEntityAttachedRelationsForCreate
                        .builder()
                        .withRelationCollection(List.of(uniRel1, uniRel2))
                        .withTwoWayRelationCollection(List.of(biRel1, biRel2))
                        .build()
        );

        assertEquals(6, transferReferenceEntityDao.countAll());
        assertEquals(1, transferCollectionEntityDao.countAll());

        assertThat(transferCollectionEntityDao.queryRelationCollection(collection).execute().stream().map(t -> t.identifier()).toList(),
                containsInAnyOrder(uniRel1.identifier(), uniRel2.identifier()));

        assertThat(transferCollectionEntityDao.queryTwoWayRelationCollection(collection).execute().stream().map(t -> t.identifier()).toList(),
                containsInAnyOrder(biRel1.identifier(), biRel2.identifier()));

        assertEquals(transferReferenceEntityDao.queryRelationOptional(biRel1).orElseThrow().identifier(), collection.identifier());
        assertEquals(transferReferenceEntityDao.queryRelationOptional(biRel2).orElseThrow().identifier(), collection.identifier());

        List<TransferReferenceEntity> comps = transferReferenceEntityDao.getAll().stream().filter(t -> t.getName().get().equals("comp")).toList();

        //derived members

        assertThat(transferCollectionEntityDao.queryCollectionCompositionEntityExpressionDerived(collection).execute(),
                containsInAnyOrder(comps.toArray())
        );

        assertThat(transferCollectionEntityDao.queryCollectionRelationEntityExpressionDerived(collection).execute(),
                containsInAnyOrder(uniRel1)
        );

        assertThat(transferCollectionEntityDao.queryCollectionTwoWayRelationEntityExpressionDerived(collection).execute(),
                containsInAnyOrder(biRel1)
        );

        assertThat(transferCollectionEntityDao.queryCollectionCompositionEntityMemberDerived(collection).execute(),
                containsInAnyOrder(comps.toArray())
        );

        assertThat(transferCollectionEntityDao.queryCollectionRelationEntityMemberDerived(collection).execute(),
                containsInAnyOrder(uniRel1, uniRel2)
        );

        assertThat(transferCollectionEntityDao.queryCollectionTwoWayRelationEntityMemberDerived(collection).execute(),
                containsInAnyOrder(biRel2, biRel1)
        );

        assertThat(transferCollectionEntityDao.queryCollectionCompositionEntityQueryMemberDerived(collection).execute(),
                containsInAnyOrder(comps.toArray())
        );

        assertThat(transferCollectionEntityDao.queryCollectionRelationEntityQueryMemberDerived(collection).execute(),
                containsInAnyOrder(uniRel1)
        );

        assertThat(transferCollectionEntityDao.queryCollectionTwoWayRelationEntityQueryMemberDerived(collection).execute(),
                containsInAnyOrder(biRel1)
        );



    }

}
