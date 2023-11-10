package hu.blackbelt.judo.runtime.core.jsl.transfer;

/*-
 * #%L
 * JUDO Runtime Core JSL :: Parent
 * %%
 * Copyright (C) 2018 - 2022 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.entitya.EntityAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transfere.TransferE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transfere.TransferEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferf.TransferF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferf.TransferFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferg.TransferG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferg.TransferGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferg.TransferGIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferh.TransferH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferh.TransferHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferj.TransferJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferj.TransferJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferk.TransferK;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonaggregation.mappedtransfercompositonaggregation.transferk.TransferKDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferCompositonAggregationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class MappedTransferCompositonAggregationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("MappedTransferCompositonAggregation", new MappedTransferCompositonAggregationDaoModules());

    @Inject
    EntityADao entityADao;

    @Inject
    TransferADao transferADao;

    @Inject
    EntityBDao entityBDao;

    @Inject
    TransferBDao transferBDao;

    @Inject
    TransferCDao transferCDao;

    @Inject
    EntityDDao entityDDao;

    @Inject
    TransferDDao transferDDao;

    @Inject
    TransferEDao transferEDao;

    @Inject
    TransferFDao transferFDao;
    @Inject
    TransferGDao transferGDao;
    @Inject
    TransferHDao transferHDao;
    @Inject
    TransferJDao transferJDao;
    @Inject
    TransferKDao transferKDao;

    /**
     * The test checks the aggregation mapped single field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferCompositonAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleCompositionAggregationOnTransfer")
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
            "REQ-SRV-001"
    })
    public void testSingleCompositionAggregationOnTransfer() {

        TransferB transferB = transferBDao.create(TransferB.builder().withNameB("B1").build());

        assertEquals(1, transferBDao.query().execute().size());

        TransferA transferA = transferADao.create(TransferA.builder()
                .withSingleEntityB(transferB)
                .build()
        );

        assertEquals(2,transferBDao.query().execute().size());
        assertEquals(2,entityBDao.query().execute().size());

        // Check transferA contains transferB
        assertNotEquals(transferB.identifier().getIdentifier(),transferA.getSingleEntityB().orElseThrow().identifier().getIdentifier());
        assertNotEquals(
                entityBDao.getById(transferB.adaptTo(EntityBIdentifier.class)).orElseThrow().identifier(),
                entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getSingleEntityB().orElseThrow().identifier()
        );

        // Check transferA cannot bind a different B element
        transferA.setSingleEntityB(transferBDao.create(TransferB.builder().withNameB("B2").build()));

        TransferA referenceForLambda = transferA;
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> transferADao.update(referenceForLambda)
        );
        assertTrue(thrown.getMessage().contains("Identifier cannot be different on containment reference element"));
        assertTrue(thrown.getMessage().contains("#singleEntityB"));

        // Check transferA can set to null
        transferA.setSingleEntityB(null);
        transferA = transferADao.update(transferA);

        assertTrue(transferA.getSingleEntityB().isEmpty());
        assertTrue(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getSingleEntityB().isEmpty());
        assertFalse(transferBDao.getById(transferB.identifier()).isEmpty());
        assertFalse(entityBDao.getById(transferB.identifier().adaptTo(EntityBIdentifier.class)).isEmpty());

        // Check transferA cannot bind a new B element
        transferB = transferBDao.create(TransferB.builder().withNameB("B2").build());
        transferA.setSingleEntityB(transferB);

        transferA = transferADao.update(transferA);
        final TransferA finalTransferA = transferA;

        IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> transferADao.createSingleEntityB(finalTransferA, TransferB.builder().withNameB("B2").build())
        );

    }

    /**
     * The test checks the aggregation mapped single reqiured field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferCompositonAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleRequiredCompositionAggregationOnTransfer")
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
            "REQ-SRV-001"
    })
    public void testSingleRequiredCompositionAggregationOnTransfer() {

        TransferD transferD = transferDDao.create(TransferD.builder().withNameD("D1").build());

        assertEquals(1, transferDDao.query().execute().size());

        TransferC transferC = transferCDao.create(TransferC.builder()
                .withSingleRequiredEntityD(transferD)
                .build()
        );

        assertEquals(2,transferDDao.query().execute().size());
        assertEquals(2,entityDDao.query().execute().size());

        //Try to create without required element

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferCDao.create(TransferC.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEntityD")))
        ));

        //Create new required element and check the old is deleted

        IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> transferCDao.createSingleRequiredEntityD(transferC, TransferD.builder().build())
        );
        assertTrue(thrown2.getMessage().contains("Containment already set"));

    }

    /**
     * The test checks the aggregation mapped multi field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferCompositonAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("MultiCompositionAggregationOnTransfer")
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
            "REQ-SRV-001"
    })
    public void testMultiCompositionAggregationOnTransfer() {

        TransferB transferB1 = transferBDao.create(TransferB.builder().withNameB("B1").build());
        TransferB transferB2 = transferBDao.create(TransferB.builder().withNameB("B2").build());
        TransferB transferB3 = transferBDao.create(TransferB.builder().withNameB("B3").build());

        assertEquals(3, transferBDao.query().execute().size());

        TransferA transferA = transferADao.create(TransferA.builder()
                .withMultiEntityB(List.of(transferB1, transferB2, transferB3))
                .build()
        );

        assertEquals(6, transferBDao.query().execute().size());
        assertEquals(6, entityBDao.query().execute().size());

        assertFalse(transferA.getMultiEntityB().stream().map(t -> t.identifier()).toList().contains(transferB1.identifier()));
        assertFalse(transferA.getMultiEntityB().stream().map(t -> t.identifier()).toList().contains(transferB2.identifier()));
        assertFalse(transferA.getMultiEntityB().stream().map(t -> t.identifier()).toList().contains(transferB3.identifier()));

        // Check the entity level
        EntityB entityB1 = entityBDao.getById(transferB1.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB2 = entityBDao.getById(transferB2.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB3 = entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertFalse(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getMultiEntityB().stream().map(t -> t.identifier()).toList()
                .contains(entityB1.identifier()));
        assertFalse(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getMultiEntityB().stream().map(t -> t.identifier()).toList()
                .contains(entityB2.identifier()));
        assertFalse(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getMultiEntityB().stream().map(t -> t.identifier()).toList()
                .contains(entityB3.identifier()));

        //Delete one element

        transferBDao.delete(transferB3);

        assertTrue(transferBDao.getById(transferB3.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).isEmpty());

        transferA = transferADao.getById(transferA.identifier()).orElseThrow();

        // Add new List empty
        transferADao.createMultiEntityB(transferA, List.of());
        assertEquals(3, transferADao.countMultiEntityB(transferA));

        // Create new List with elements
        transferADao.createMultiEntityB(transferA, List.of(TransferB.builder().build()));
        assertEquals(4, transferADao.countMultiEntityB(transferA));

    }

    /**
     * The test checks the navigation between compositon association work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferCompositonAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     *
     */
    @Test
    @TestCase("CompositionAggregationNavigationOnTransfer")
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
            "REQ-SRV-001"
    })
    public void testCompositionAggregationNavigationOnTransfer() {

        TransferE transferE = transferEDao.create(TransferE
                .builder()
                .withSingleEntityF(transferFDao.create(TransferF
                        .builder()
                        .withMultiEntityG(List.of(
                                transferGDao.create(TransferG.builder().build()),
                                transferGDao.create(TransferG.builder().build()),
                                transferGDao.create(TransferG.builder().build())
                        ))
                        .build())
                )
                .build());

        TransferF transferF = transferE.getSingleEntityF().get();
        List<TransferGIdentifier> listOfG = transferE.getSingleEntityF().get().getMultiEntityG().stream().map(t -> t.identifier()).toList();

        assertThat(
                transferF.getMultiEntityG().stream().map(t -> t.identifier()).toList(),
                containsInAnyOrder(listOfG.toArray())
        );
    }

    @Test
    void testDeepCopyCreate() {

        TransferK transferK1 = transferKDao.create(TransferK.builder().withStringK("K1").build());
        TransferK transferK2 = transferKDao.create(TransferK.builder().withStringK("K2").build());
        TransferJ transferJ = transferJDao.create(TransferJ.builder().withStringJ("J1").withMultipleKonI(List.of(transferK1, transferK2)).build());
        TransferH transferH = transferHDao.create(TransferH.builder().withSingleRequiredJonH(transferJ).withStringH("H1").build());

        assertNotEquals(transferJ.identifier().getIdentifier() ,transferH.getSingleRequiredJonH().identifier().getIdentifier());
        assertEquals("H1", transferH.getStringH().orElseThrow());

        List<TransferK> ksTransfer = transferH.getSingleRequiredJonH().getMultipleKonI();

        TransferK testK1Transfer = ksTransfer.stream().filter(d -> d.getStringK().orElseThrow().equals("K1")).findFirst().orElseThrow();
        TransferK testK2Transfer = ksTransfer.stream().filter(d -> d.getStringK().orElseThrow().equals("K2")).findFirst().orElseThrow();
        assertNotEquals(transferK1.identifier().getIdentifier(), testK1Transfer.identifier().getIdentifier());
        assertEquals(transferK1.getStringK().orElseThrow(), testK1Transfer.getStringK().orElseThrow());
        assertNotEquals(transferK2.identifier().getIdentifier(), testK2Transfer.identifier().getIdentifier());
        assertEquals(transferK2.getStringK().orElseThrow(), testK2Transfer.getStringK().orElseThrow());

    }

    @Test
    void testDeepCopyUpdate() {
        TransferH h2Transfer = transferHDao.create(TransferH.builder().withSingleRequiredJonH(TransferJ.builder().build()).build());
        assertEquals(Optional.empty(), h2Transfer.getSingleJonH());
        assertEquals(0, h2Transfer.getCollectionJonH().size());

        assertEquals(1, transferJDao.countAll());
        assertEquals(0, transferKDao.countAll());

        h2Transfer.setSingleJonH(TransferJ.builder().withStringJ("J1").build());
        h2Transfer.setCollectionJonH(List.of(TransferJ.builder().withStringJ("J2").withMultipleKonI(List.of(TransferK.builder().withStringK("K").build())).build()));

        h2Transfer = transferHDao.update(h2Transfer);

        assertEquals(3, transferJDao.countAll());
        assertEquals(1, transferKDao.countAll());
        assertEquals("J1", h2Transfer.getSingleJonH().orElseThrow().getStringJ().orElseThrow());
        assertEquals("J2", h2Transfer.getCollectionJonH().get(0).getStringJ().orElseThrow());
        assertEquals("K", h2Transfer.getCollectionJonH().get(0).getMultipleKonI().get(0).getStringK().orElseThrow());

        TransferJ j3Transfer = transferJDao.create(TransferJ.builder().withStringJ("J3").build());
        TransferJ j4Transfer = transferJDao.create(TransferJ.builder().withStringJ("J4").withMultipleKonI(List.of(TransferK.builder().withStringK("K").build())).build());
        TransferH h3Transfer = transferHDao.create(TransferH.builder().withSingleJonH(j3Transfer).withSingleRequiredJonH(j4Transfer).build());

        assertEquals(7, transferJDao.countAll());
        assertEquals(3, transferKDao.countAll());

        h3Transfer.setSingleJonH(TransferJ.builder().withStringJ("J3Updated").build());
        h3Transfer.setSingleRequiredJonH(TransferJ.builder().withStringJ("J4Updated").withMultipleKonI(List.of(TransferK.builder().withStringK("KUpdated").build())).build());

        h3Transfer = transferHDao.update(h3Transfer);

        assertEquals(7, transferJDao.countAll());
        // TODO: JNG-5213 update does not create new EntityD instance
        //assertEquals(3, transferKDao.countAll());
        assertEquals("J3Updated", h3Transfer.getSingleJonH().orElseThrow().getStringJ().orElseThrow());
        assertEquals("J4Updated", h3Transfer.getSingleRequiredJonH().getStringJ().orElseThrow());
        //assertEquals("KUpdated", h3Transfer.getSingleRequiredJonH().getMultipleKonI().get(0).getStringK().orElseThrow());


        TransferH a4 = transferHDao.create(TransferH.builder().withSingleRequiredJonH(TransferJ.builder().build()).build());
        assertEquals(Optional.empty(), a4.getSingleJonH());
        assertEquals(0, a4.getCollectionJonH().size());

        TransferJ c5 = transferJDao.create(TransferJ.builder().withStringJ("C5").build());
        TransferJ c6 = transferJDao.create(TransferJ.builder().withStringJ("C6").withMultipleKonI(List.of(TransferK.builder().withStringK("D4").build())).build());

        a4.setSingleJonH(c5);
        a4.setCollectionJonH(List.of(c6));
        final TransferH a5 = transferHDao.update(a4);

        assertEquals(12, transferJDao.countAll());
        //assertEquals(5, transferKDao.countAll());

        assertEquals("C5", a5.getSingleJonH().orElseThrow().getStringJ().orElseThrow());
        assertEquals("C6", a5.getCollectionJonH().get(0).getStringJ().orElseThrow());
        assertEquals("D4", a5.getCollectionJonH().get(0).getMultipleKonI().get(0).getStringK().orElseThrow());

        TransferJ c7 = transferJDao.create(TransferJ.builder().withStringJ("C7").build());
        TransferJ c8 = transferJDao.create(TransferJ.builder().withStringJ("C8").withMultipleKonI(List.of(TransferK.builder().withStringK("D5").build())).build());

        a5.setSingleJonH(c7);
        a5.setCollectionJonH(List.of(c8));

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> transferHDao.update(a5)
        );

    }


    @Test
    void testAddMethodOnBuilder() {
        TransferB b1 = transferBDao.create(TransferB.builder().withNameB("B1").build());
        TransferB b2 = transferBDao.create(TransferB.builder().withNameB("B2").build());
        TransferB b3 = transferBDao.create(TransferB.builder().withNameB("B3").build());
        TransferB b4 = transferBDao.create(TransferB.builder().withNameB("B4").build());

        TransferA transferA = transferADao.create(TransferA.builder()
                .addToMultiEntityB(b2)
                .build());

        assertEquals(1, transferA.getMultiEntityB().size());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B2")).count());

        transferA = transferADao.create(TransferA.builder()
                .addToMultiEntityB(b2, b3)
                .build());

        assertEquals(2, transferA.getMultiEntityB().size());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B2")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B3")).count());

        transferA = transferADao.create(TransferA.builder()
                .addToMultiEntityB(b2, b2)
                .build());

        assertEquals(2, transferA.getMultiEntityB().size());
        assertEquals(2, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B2")).count());

        transferA = transferADao.create(TransferA.builder()
                .addToMultiEntityB(b2)
                .addToMultiEntityB(b2)
                .build());

        assertEquals(2, transferA.getMultiEntityB().size());
        assertEquals(2, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B2")).count());

        transferA = transferADao.create(TransferA.builder()
                .addToMultiEntityB(b2)
                .addToMultiEntityB(b3)
                .addToMultiEntityB(b4)
                .build());

        assertEquals(3, transferA.getMultiEntityB().size());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B2")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B3")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B4")).count());

        transferA = transferADao.create(TransferA.builder()
                .withMultiEntityB(List.of(b1, b2))
                .addToMultiEntityB(b3)
                .addToMultiEntityB(b4)
                .build());

        assertEquals(4, transferA.getMultiEntityB().size());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B1")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B2")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B3")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B4")).count());

        transferA = transferADao.create(TransferA.builder()
                .withMultiEntityB(List.of(b1, b2))
                .addToMultiEntityB(b3, b4)
                .build());

        assertEquals(4, transferA.getMultiEntityB().size());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B1")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B2")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B3")).count());
        assertEquals(1, transferA.getMultiEntityB().stream().filter(entityC -> entityC.getNameB().orElseThrow().equals("B4")).count());
    }

}
