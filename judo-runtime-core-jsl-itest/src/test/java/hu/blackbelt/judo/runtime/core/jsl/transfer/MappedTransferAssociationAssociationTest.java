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
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entitya.EntityAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityc.EntityC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityc.EntityCIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityd.EntityDIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entitye.EntityE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entitye.EntityEIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityf.EntityF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityf.EntityFIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityg.EntityGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityh.EntityHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityj.EntityJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfera.TransferAAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferc.TransferCAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfere.TransferE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfere.TransferEAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfere.TransferEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferf.TransferF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferf.TransferFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferAssociationAssociationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferAssociationAssociationTest extends AbstractJslTest {

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

    @Inject
    EntityGDao entityGDao;


    @Override
    public Module getModelDaoModule() {
        return new MappedTransferAssociationAssociationDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferAssociationAssociation";
    }

    /**
     * The test checks the single relation work well on transfer object.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleAssociationAssociationRelationOnTransfer")
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
    public void testSingleAssociationAssociationRelationOnTransfer() {

        TransferB tb1 = transferBDao.create(TransferB.builder().build());
        TransferB tb2 = transferBDao.create(TransferB.builder().build());

        TransferA transferA = transferADao.create(TransferA
                .builder()
                .build(),
                TransferAAttachedRelationsForCreate
                        .builder()
                        .withRelationBonA(tb1)
                        .build()
        );

        assertEquals(tb1.identifier(), transferADao.queryRelationBonA(transferA).orElseThrow().identifier());

        EntityA a = entityADao.getById(transferA.identifier().adaptTo(EntityAIdentifier.class)).orElseThrow();
        EntityB b1 = entityBDao.getById(tb1.identifier().adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals(b1.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferADao.setRelationBonA(transferA,tb2);
        transferA = transferADao.getById(transferA.identifier()).orElseThrow();

        assertEquals(tb2.identifier(), transferADao.queryRelationBonA(transferA).orElseThrow().identifier());

        EntityB b2 = entityBDao.getById(tb2.identifier().adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals(b2.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferADao.unsetRelationBonA(transferA);
        transferA = transferADao.getById(transferA.identifier()).orElseThrow();

        assertTrue(transferADao.queryRelationBonA(transferA).isEmpty());
        assertTrue(entityADao.queryRelationBonA(a).isEmpty());

        //test Dao functions set, unset

        transferADao.setRelationBonA(transferA, tb2);

        assertEquals(tb2.identifier(), transferADao.queryRelationBonA(transferA).orElseThrow().identifier());
        assertEquals(b2.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferADao.unsetRelationBonA(transferA);

        assertTrue(transferADao.queryRelationBonA(transferA).isEmpty());
        assertTrue(entityADao.queryRelationBonA(a).isEmpty());

    }

    /**
     * The test checks the single required relation work well on transfer object.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleRequiredAssociationAssociationRelationOnTransfer")
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
    public void testSingleRequiredAssociationAssociationRelationOnTransfer() {

        TransferD td1 = transferDDao.create(TransferD.builder().build());
        TransferD td2 = transferDDao.create(TransferD.builder().build());

        TransferC transferC = transferCDao.create(TransferC
                .builder()
                .build(),
                TransferCAttachedRelationsForCreate
                        .builder()
                        .withRelationDonC(td1)
                        .build()

        );

        assertEquals(td1.identifier(), transferCDao.queryRelationDonC(transferC).identifier());

        EntityC c = entityCDao.getById(transferC.identifier().adaptTo(EntityCIdentifier.class)).orElseThrow();
        EntityD d1 = entityDDao.getById(td1.identifier().adaptTo(EntityDIdentifier.class)).orElseThrow();

        assertEquals(d1.identifier(), entityCDao.queryRelationDonC(c).identifier());

        transferCDao.setRelationDonC(transferC, td2);

        assertEquals(td2.identifier(), transferCDao.queryRelationDonC(transferC).identifier());

        EntityD d2 = entityDDao.getById(td2.identifier().adaptTo(EntityDIdentifier.class)).orElseThrow();

        assertEquals(d2.identifier(), entityCDao.queryRelationDonC(c).identifier());

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> transferDDao.delete(td2)
        );

        assertTrue(thrown.getMessage().contains("There are mandatory references that cannot be removed"));
        assertTrue(thrown.getMessage().contains("#relationDonC"));

        //creation without mandatory element

        IllegalArgumentException thrown1 = assertThrows(
                IllegalArgumentException.class,
                () -> transferCDao.create(TransferC.builder().build())
        );

        assertTrue(thrown1.getMessage().contains("There is missing mandatory attribute/reference"));
        assertTrue(thrown1.getMessage().contains("relationDonC"));

        // Test dao set, unset
        transferCDao.setRelationDonC(transferC, td1);
        transferC = transferCDao.getById(transferC.identifier()).orElseThrow();

        assertEquals(td1.identifier(), transferCDao.queryRelationDonC(transferC).identifier());
        assertEquals(d1.identifier(), entityCDao.queryRelationDonC(c).identifier());

    }

    /**
     * The test checks the multi relation work well on transfer object.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociation.jsl
     *
     * @positiveRequirements
     *
     */
    @Test
    @TestCase("MultiAssociationAssociationRelationOnTransfer")
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
    public void testMultiAssociationAssociationRelationOnTransfer() {

        TransferF tf1 = transferFDao.create(TransferF.builder().build());
        TransferF tf2 = transferFDao.create(TransferF.builder().build());
        TransferF tf3 = transferFDao.create(TransferF.builder().build());

        TransferE transferE = transferEDao.create(TransferE
                .builder()
                .build(),
                TransferEAttachedRelationsForCreate
                        .builder()
                        .withRelationFonE(List.of(tf1, tf2, tf3))
                        .build()
        );

        // Check the entity presentations are existing.

        assertThat(transferEDao.queryRelationFonE(transferE).execute(), containsInAnyOrder(tf1, tf2, tf3));

        EntityE e = entityEDao.getById(transferE.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();
        List<EntityF> listOfEntityF = entityFDao.query().execute();

        assertEquals(3, listOfEntityF.size());

        // Delete one related element

        assertTrue(transferEDao.queryRelationFonE(transferE).execute().contains(tf3));

        transferFDao.delete(tf3);
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        assertFalse(transferEDao.queryRelationFonE(transferE).execute().contains(tf3));
        assertEquals(2, transferEDao.queryRelationFonE(transferE).count());
        assertTrue(entityFDao.getById(tf3.identifier().adaptTo(EntityFIdentifier.class)).isEmpty());

        // Add an element

        tf3 = transferFDao.create(TransferF.builder().build());
        List<TransferF> relationFonE = transferEDao.queryRelationFonE(transferE).execute();
        transferEDao.addRelationFonE(transferE, List.of(tf3));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        assertEquals(3, transferEDao.queryRelationFonE(transferE).count());
        assertThat(transferEDao.queryRelationFonE(transferE).execute(), containsInAnyOrder(tf1, tf2, tf3));

        tf1 = TransferF.builder().withNameF("tf1").build();
        tf2 = TransferF.builder().withNameF("tf2").build();
        tf3 = TransferF.builder().withNameF("tf3").build();
        transferEDao.createRelationFonE(transferE, List.of(tf1, tf2, tf3));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        List<String> relationFonEs = transferEDao.queryRelationFonE(transferE).execute().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(3, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3"));

        //Add
        TransferF tf4 = transferFDao.create(TransferF.builder().withNameF("tf4").build());
        transferEDao.addRelationFonE(transferE, List.of(tf4));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        relationFonEs = transferEDao.queryRelationFonE(transferE).execute().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(4, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3", "tf4"));

        //Remove
        transferEDao.removeRelationFonE(transferE, List.of(tf4));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        relationFonEs = transferEDao.queryRelationFonE(transferE).execute().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(3, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3"));

        List<TransferF> relationContent = transferEDao.queryRelationFonE(transferE).execute().stream().filter(ee -> ee.getNameF().isPresent()).toList();
        assertEquals(3, relationContent.size());
        TransferF elementToRemove = relationContent.get(0);
        transferEDao.removeRelationFonE(transferE, List.of(elementToRemove));
        relationContent = transferEDao.queryRelationFonE(transferE).execute().stream().filter(ee -> ee.getNameF().isPresent()).toList();
        assertEquals(relationContent.stream().filter(ee -> !ee.getNameF().equals(elementToRemove.getNameF()))
                                    .map(TransferF::identifier)
                                    .collect(Collectors.toSet()),
                     relationContent.stream().map(TransferF::identifier).collect(Collectors.toSet()));
    }


}
