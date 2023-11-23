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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitya.EntityAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitye.EntityE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitye.EntityEForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitye.EntityEIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfere.TransferE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfere.TransferEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfere.TransferEForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferf.TransferF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferf.TransferFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferf.TransferFForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferCompositonAssociationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferCompositonAssociationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("MappedTransferCompositonAssociation", new MappedTransferCompositonAssociationDaoModules());

    @Inject
    EntityADao entityADao;

    @Inject
    TransferADao transferADao;

    @Inject
    EntityBDao entityBDao;

    @Inject
    TransferBDao transferBDao;

    @Inject
    EntityCDao entityCDao;

    @Inject
    TransferCDao transferCDao;

    @Inject
    EntityDDao entityDDao;

    @Inject
    TransferDDao transferDDao;

    @Inject
    EntityEDao entityEDao;

    @Inject
    TransferEDao transferEDao;

    @Inject
    EntityFDao entityFDao;

    @Inject
    TransferFDao transferFDao;

    /**
     * The test checks the association mapped single field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleCompositionAssociationOnTransfer")
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
    public void testSingleCompositionAssociationOnTransfer() {

        TransferB transferB = transferBDao.create(TransferBForCreate.builder().withNameB("B1").build());

        assertEquals(1, transferBDao.query().selectList().size());

        TransferA transferA = transferADao.create(TransferAForCreate.builder()
                .withSingleEntityB(transferB).build()
        );

        assertEquals(2,transferBDao.query().selectList().size());
        assertEquals(2,entityBDao.query().selectList().size());

        // Check transferA contains transferB
        assertNotEquals(transferB.identifier().getIdentifier(), transferADao.querySingleEntityB(transferA).orElseThrow().identifier().getIdentifier());
        assertNotEquals(
                entityBDao.getById(transferB.adaptTo(EntityBIdentifier.class)).orElseThrow().identifier(),
                entityADao.querySingleEntityB((entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow())).orElseThrow().identifier()
        );

        // Check transferA cannot bind a different B element
        TransferA referenceForLambda = transferA;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> transferADao.createSingleEntityB(referenceForLambda, TransferBForCreate.builder().withNameB("B2").build())
        );
        assertTrue(thrown.getMessage().contains("Upper cardinality violated"));

        // Check transferA can set to null
        transferBDao.delete(transferB);
        transferA = transferADao.update(transferA);

        assertFalse(transferADao.querySingleEntityB(transferA).isEmpty());
        assertFalse(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getSingleEntityB().isEmpty());
        assertTrue(transferBDao.getById(transferB.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB.identifier().adaptTo(EntityBIdentifier.class)).isEmpty());

    }


    /**
     * The test checks the association mapped single reqiured field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleRequiredCompositionAssociationOnTransfer")
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
    public void testSingleRequiredCompositionAssociationOnTransfer() {

        TransferD transferD = transferDDao.create(TransferDForCreate.builder().withNameD("D1").build());

        assertEquals(1, transferDDao.query().selectList().size());

        TransferC transferC = transferCDao.create(TransferCForCreate.builder().withSingleRequiredEntityD(transferD).build()
        );

        assertEquals(2,transferDDao.query().selectList().size());
        assertEquals(2,entityDDao.query().selectList().size());

        //Try to create without required element
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferCDao.create(TransferCForCreate.builder().build())
        );

        Assertions.assertEquals(1, thrown.getValidationResults().size());
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEntityD")))
        ));

        //Create new required element and check the old is deleted

        IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> transferCDao.createSingleRequiredEntityD(transferC, TransferDForCreate.builder().build())
        );
        assertTrue(thrown2.getMessage().contains("Upper cardinality violated"));

    }

    /**
     * The test checks the association mapped multi field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("MultiCompositionAssociationOnTransfer")
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
    public void testMultiCompositionAssociationOnTransfer() {

        TransferB transferB1 = transferBDao.create(TransferBForCreate.builder().withNameB("B1").build());
        TransferB transferB2 = transferBDao.create(TransferBForCreate.builder().withNameB("B2").build());
        TransferB transferB3 = transferBDao.create(TransferBForCreate.builder().withNameB("B3").build());

        assertEquals(3, transferBDao.query().selectList().size());

        TransferA transferA = transferADao.create(TransferAForCreate.builder().withMultiEntityB(List.of(transferB1, transferB2, transferB3)).build()
        );

        assertEquals(6, transferBDao.query().selectList().size());
        assertEquals(6, entityBDao.query().selectList().size());

        // Check transferA contains transferB
        List<TransferBIdentifier> multiTransferBIDS = transferADao.queryMultiEntityB(transferA).selectList().stream().map(t -> t.identifier()).toList();
        assertFalse(multiTransferBIDS.contains(transferB1.identifier()));
        assertFalse(multiTransferBIDS.contains(transferB2.identifier()));
        assertFalse(multiTransferBIDS.contains(transferB3.identifier()));

        // Check the entity level
        EntityB entityB1 = entityBDao.getById(transferB1.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB2 = entityBDao.getById(transferB2.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB3 = entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).orElseThrow();

        List<EntityBIdentifier> multiEntityBIDS = entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getMultiEntityB().stream().map(t -> t.identifier()).toList();

        assertFalse(multiEntityBIDS.contains(entityB1.identifier()));
        assertFalse(multiEntityBIDS.contains(entityB2.identifier()));
        assertFalse(multiEntityBIDS.contains(entityB3.identifier()));

        //Delete one element
        transferBDao.delete(transferB3);

        assertTrue(transferBDao.getById(transferB3.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).isEmpty());

        transferA = transferADao.getById(transferA.identifier()).orElseThrow();

        assertEquals(3, transferADao.countMultiEntityB(transferA));

        // Add new List empty
        transferADao.createMultiEntityB(transferA, List.of());
        assertEquals(3, transferADao.countMultiEntityB(transferA));

        // Create new List with elements
        transferADao.createMultiEntityB(transferA, List.of(TransferBForCreate.builder().build()));
        assertEquals(4, transferADao.countMultiEntityB(transferA));


    }

    @Test
    void testCreateAll() {
        List<TransferE> transferEss = transferEDao.createAll(new ArrayList<>());

        Assertions.assertEquals(0, transferEss.size());

        transferEss = transferEDao.createAll(List.of(TransferEForCreate.builder().withStringE("II").build()));

        Assertions.assertEquals(1, transferEss.size());
        Assertions.assertEquals("II", transferEss.get(0).getStringE());

        TransferEForCreate TransferEForCreate1 = TransferEForCreate.builder()
                .withStringE("E1")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D1").build())
                .build();

        TransferEForCreate TransferEForCreate2 = TransferEForCreate.builder()
                .withStringE("E2")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D2").build())
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D3").build())
                .build();

        TransferEForCreate TransferEForCreate3 = TransferEForCreate.builder()
                .withStringE("E3")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D4").build())
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D5").build())
                .build();

        List<TransferE> transferEs = transferEDao.createAll(List.of(TransferEForCreate1, TransferEForCreate2, TransferEForCreate3));

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).count());

        TransferE transferE1 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", transferE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).count());

        TransferE transferE2 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE2.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        TransferE transferE3 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE3.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5")).count());

        EntityE entityE1 = entityEDao.getById(transferE1.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        EntityE entityE2 = entityEDao.getById(transferE2.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        EntityE entityE3 = entityEDao.getById(transferE3.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE3.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5")).count());


        TransferEForCreate1 = TransferEForCreate.builder()
                .withStringE("E1")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D1").build())
                .build();

        TransferEForCreate2 = TransferEForCreate.builder()
                .withStringE("E2")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D2").build())
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D3").build())
                .build();

        TransferEForCreate3 = TransferEForCreate.builder()
                .withStringE("E3")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D4").build())
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D5").build())
                .build();

        transferEs = transferEDao.createAll(TransferEForCreate1, TransferEForCreate2);

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).count());

        transferE1 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", transferE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).count());

        transferE2 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE2.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        entityE1 = entityEDao.getById(transferE1.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        entityE2 = entityEDao.getById(transferE2.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        transferEs = transferEDao.createAll(TransferEForCreate1, TransferEForCreate2, TransferEForCreate3);

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).count());

        transferE1 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", transferE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).count());

        transferE2 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE2.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        transferE3 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE3.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5")).count());

        entityE1 = entityEDao.getById(transferE1.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        entityE2 = entityEDao.getById(transferE2.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        entityE3 = entityEDao.getById(transferE3.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE3.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5")).count());

        Assertions.assertEquals(9, transferEDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.createAll(TransferEForCreate.builder().withStringE("E1").build(),
                        TransferEForCreate.builder().build(),
                        TransferEForCreate.builder().withStringE("E1").build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo("stringE")))));

        Assertions.assertEquals(9, transferEDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.createAll(List.of(TransferEForCreate.builder().withStringE("E1").build(),
                        TransferEForCreate.builder().build(),
                        TransferEForCreate.builder().withStringE("E1").build())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo("stringE")))));

        Assertions.assertEquals(9, transferEDao.countAll());
    }

    @Test
    void testUpdateAll() {
        TransferEForCreate TransferEForCreate1 = TransferEForCreate.builder()
                .withStringE("E1")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D1").build())
                .build();

        TransferEForCreate TransferEForCreate2 = TransferEForCreate.builder()
                .withStringE("E2")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D2").build())
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D3").build())
                .build();

        TransferEForCreate TransferEForCreate3 = TransferEForCreate.builder()
                .withStringE("E3")
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D4").build())
                .addToMultipleDonE(TransferDForCreate.builder().withNameD("D5").build())
                .build();

        List<TransferE> transferEs = transferEDao.createAll(List.of(TransferEForCreate1, TransferEForCreate2, TransferEForCreate3));

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).count());

        TransferE transferE1 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", transferE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).count());

        TransferE transferE2 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE2.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        TransferE transferE3 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE3.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5")).count());

        EntityE entityE1 = entityEDao.getById(transferE1.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        EntityE entityE2 = entityEDao.getById(transferE2.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        EntityE entityE3 = entityEDao.getById(transferE3.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE3.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5")).count());


        transferE1.setStringE("E1Updated");
        transferE1.setMultipleDonE(List.of(TransferD.builder().withNameD("D1Updated").build()));
        transferE2.setStringE("E2Updated");
        transferE2.setMultipleDonE(List.of(TransferD.builder().withNameD("D2Updated").build(), TransferD.builder().withNameD("D3Updated").build()));
        transferE3.setStringE("E3Updated");
        transferE3.setMultipleDonE(List.of(TransferD.builder().withNameD("D4Updated").build(), TransferD.builder().withNameD("D5Updated").build()));
        transferE3.addToMultipleDonE(TransferD.builder().withNameD("D6Updated").build());

        transferEs = transferEDao.updateAll(List.of(transferE1, transferE2, transferE3));

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1Updated")).count());

        transferE1 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1Updated")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferE1.getMultipleDonE().size());
        Assertions.assertEquals("D1Updated", transferE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2Updated")).count());

        transferE2 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2Updated")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE2.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2Updated")).count());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3Updated")).count());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3Updated")).count());

        transferE3 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3Updated")).findFirst().orElseThrow();

        Assertions.assertEquals(3, transferE3.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4Updated")).count());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5Updated")).count());
        Assertions.assertEquals(1, transferE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D6Updated")).count());

        entityE1 = entityEDao.getById(transferE1.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonE().size());
        Assertions.assertEquals("D1Updated", entityE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        entityE2 = entityEDao.getById(transferE2.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2Updated")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3Updated")).count());


        entityE3 = entityEDao.getById(transferE3.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(3, entityE3.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D4Updated")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D5Updated")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D6Updated")).count());

        transferE1.setStringE("E1");
        transferE1.setMultipleDonE(List.of(TransferD.builder().withNameD("D1").build()));
        transferE2.setStringE("E2");
        transferE2.setMultipleDonE(List.of(TransferD.builder().withNameD("D2").build(), TransferD.builder().withNameD("D3").build()));
        transferE3.setStringE("E3");
        transferE3.setMultipleDonE(List.of(TransferD.builder().withNameD("D4").build(), TransferD.builder().withNameD("D5").build()));
        transferE3.addToMultipleDonE(TransferD.builder().withNameD("D6").build());

        transferEs = transferEDao.updateAll(transferE1, transferE2);

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).count());

        transferE1 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", transferE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        Assertions.assertEquals(1, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).count());

        transferE2 = transferEs.stream().filter(transferE -> transferE.getStringE().equals("E2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferE2.getMultipleDonE().size());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(0, transferEs.stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        entityE1 = entityEDao.getById(transferE1.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonE().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonE().get(0).getNameD().orElseThrow());

        entityE2 = entityEDao.getById(transferE2.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonE().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D3")).count());

        transferE1.setStringE("E1Updated");
        transferE1.addToMultipleDonE(TransferD.builder().withNameD("D7").build());

        transferEs = transferEDao.updateAll(List.of(transferE1));

        Assertions.assertEquals(1, transferEs.size());
        Assertions.assertEquals("E1Updated", transferEs.get(0).getStringE());
        Assertions.assertEquals(2, transferEs.get(0).getMultipleDonE().size());
        Assertions.assertEquals(1, transferE1.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D1")).count());
        Assertions.assertEquals(1, transferE1.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D7")).count());

        entityE1 = entityEDao.getById(transferE1.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D1")).count());
        Assertions.assertEquals(1, entityE1.getMultipleDonE().stream().filter(entityD -> entityD.getNameD().orElseThrow().equals("D7")).count());

        transferEs = transferEDao.updateAll(new ArrayList<>());

        Assertions.assertEquals(0, transferEs.size());

        final TransferF transferF1 = transferFDao.create(TransferFForCreate.builder().withSingleRequiredDonA(TransferDForCreate.builder().build()).build());
        final TransferF transferF2 = transferFDao.create(TransferFForCreate.builder().withSingleRequiredDonA(TransferDForCreate.builder().build()).build());
        final TransferF transferF3 = transferFDao.create(TransferFForCreate.builder().withSingleRequiredDonA(TransferDForCreate.builder().build()).build());

        Assertions.assertEquals(3, transferFDao.countAll());

        transferF2.setSingleRequiredDonA(null);

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.updateAll(transferF1,
                        transferF2,
                        transferF3));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredDonA")))));

        Assertions.assertEquals(3, transferFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.updateAll(List.of(transferF1,
                        transferF2,
                        transferF3)));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredDonA")))));

        Assertions.assertEquals(3, transferFDao.countAll());
    }

    @Test
    void testDeleteAllWithInstances() {
        TransferE transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        TransferE transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        TransferE transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(new ArrayList<>());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(List.of(transferE2));

        Assertions.assertEquals(2, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(2, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(List.of(transferE1, transferE3));

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());


        transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());
        TransferE transferE4 = transferEDao.create(TransferEForCreate.builder().withStringE("E4").build());
        TransferE transferE5 = transferEDao.create(TransferEForCreate.builder().withStringE("E5").build());
        TransferE transferE6 = transferEDao.create(TransferEForCreate.builder().withStringE("E6").build());

        Assertions.assertEquals(6, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(6, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferEDao.deleteAll(transferE1, transferE2);

        Assertions.assertEquals(4, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(4, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferEDao.deleteAll(transferE3, transferE4, transferE5, transferE6);

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(transferE1, transferE2, transferE3);

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        final TransferE transferE1final = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        final TransferE transferE2final = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        final TransferE transferE3final = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        transferEDao.delete(transferE2final);

        assertEquals(2, transferEDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.deleteAll(transferE1final,
                        transferE2final,
                        transferE3final));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferEDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.deleteAll(List.of(transferE1final,
                        transferE2final,
                        transferE3final)));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferEDao.countAll());
    }

    @Test
    void testDeleteAllWithIdentifier() {
        TransferE transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        TransferE transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        TransferE transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(new ArrayList<>());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(List.of(transferE2.identifier()));

        Assertions.assertEquals(2, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(2, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(List.of(transferE1.identifier(), transferE3.identifier()));

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());


        transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());
        TransferE transferE4 = transferEDao.create(TransferEForCreate.builder().withStringE("E4").build());
        TransferE transferE5 = transferEDao.create(TransferEForCreate.builder().withStringE("E5").build());
        TransferE transferE6 = transferEDao.create(TransferEForCreate.builder().withStringE("E6").build());

        Assertions.assertEquals(6, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(6, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferEDao.deleteAll(transferE1.identifier(), transferE2.identifier());

        Assertions.assertEquals(4, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(4, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferEDao.deleteAll(transferE3.identifier(), transferE4.identifier(), transferE5.identifier(), transferE6.identifier());

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(transferE1.identifier(), transferE2.identifier(), transferE3.identifier());

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        final TransferE transferE1final = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        final TransferE transferE2final = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        final TransferE transferE3final = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        transferEDao.delete(transferE2final);

        assertEquals(2, transferEDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.deleteAll(transferE1final.identifier(),
                        transferE2final.identifier(),
                        transferE3final.identifier()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferEDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.deleteAll(List.of(transferE1final.identifier(),
                        transferE2final.identifier(),
                        transferE3final.identifier())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferEDao.countAll());
    }

    @Test
    void testDeleteAllWithUUIDs() {
        TransferE transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        TransferE transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        TransferE transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(new ArrayList<>());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(List.of(transferE2.identifier().getIdentifier()));

        Assertions.assertEquals(2, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(2, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll(List.of(transferE1.identifier().getIdentifier(), transferE3.identifier().getIdentifier()));

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());


        transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());
        TransferE transferE4 = transferEDao.create(TransferEForCreate.builder().withStringE("E4").build());
        TransferE transferE5 = transferEDao.create(TransferEForCreate.builder().withStringE("E5").build());
        TransferE transferE6 = transferEDao.create(TransferEForCreate.builder().withStringE("E6").build());

        Assertions.assertEquals(6, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(6, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferEDao.deleteAll((UUID) transferE1.identifier().getIdentifier(), (UUID) transferE2.identifier().getIdentifier());

        Assertions.assertEquals(4, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(4, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferEDao.deleteAll((UUID) transferE3.identifier().getIdentifier()
                , (UUID) transferE4.identifier().getIdentifier()
                , (UUID) transferE5.identifier().getIdentifier()
                , (UUID) transferE6.identifier().getIdentifier());

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E4")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E5")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E6")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E4")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E5")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E6")).count());

        transferE1 = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        transferE2 = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        transferE3 = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        Assertions.assertEquals(3, transferEDao.countAll());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(3, entityEDao.countAll());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(1, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        transferEDao.deleteAll((UUID) transferE1.identifier().getIdentifier(), (UUID) transferE2.identifier().getIdentifier(), (UUID) transferE3.identifier().getIdentifier());

        Assertions.assertEquals(0, transferEDao.countAll());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, transferEDao.getAll().stream().filter(transferE -> transferE.getStringE().equals("E3")).count());

        Assertions.assertEquals(0, entityEDao.countAll());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E1")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E2")).count());
        Assertions.assertEquals(0, entityEDao.getAll().stream().filter(entityE -> entityE.getStringE().equals("E3")).count());

        final TransferE transferE1final = transferEDao.create(TransferEForCreate.builder().withStringE("E1").build());
        final TransferE transferE2final = transferEDao.create(TransferEForCreate.builder().withStringE("E2").build());
        final TransferE transferE3final = transferEDao.create(TransferEForCreate.builder().withStringE("E3").build());

        transferEDao.delete(transferE2final);

        assertEquals(2, transferEDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.deleteAll((UUID) transferE1final.identifier().getIdentifier(),
                        (UUID) transferE2final.identifier().getIdentifier(),
                        (UUID) transferE3final.identifier().getIdentifier()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferEDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferEDao.deleteAll(List.of(transferE1final.identifier().getIdentifier(),
                        transferE2final.identifier().getIdentifier(),
                        transferE3final.identifier().getIdentifier())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferEDao.countAll());
    }
}
