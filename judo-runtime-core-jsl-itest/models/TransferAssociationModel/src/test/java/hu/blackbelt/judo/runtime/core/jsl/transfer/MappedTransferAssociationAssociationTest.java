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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityg.EntityG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityg.EntityGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityg.EntityGIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityh.EntityH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityh.EntityHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityh.EntityHIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityi.EntityI;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityi.EntityIDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityi.EntityIIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityj.EntityJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityj.EntityJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityj.EntityJIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityk.EntityK;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityk.EntityKDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityk.EntityKIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityl.EntityL;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityl.EntityLDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.entityl.EntityLIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfera.TransferAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferb.TransferBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferc.TransferCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferd.TransferDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfere.TransferE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfere.TransferEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transfere.TransferEForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferf.TransferF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferf.TransferFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferf.TransferFForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferf.TransferFMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferg.TransferG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferg.TransferGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferg.TransferGForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferh.TransferH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferh.TransferHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferh.TransferHForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferi.TransferI;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferi.TransferIDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferi.TransferIForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferj.TransferJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferj.TransferJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferj.TransferJForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferk.TransferK;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferk.TransferKDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferk.TransferKForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferl.TransferL;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferl.TransferLDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationassociation.mappedtransferassociationassociation.transferl.TransferLForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferAssociationAssociationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferAssociationAssociationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("MappedTransferAssociationAssociation", new MappedTransferAssociationAssociationDaoModules());

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

    @Inject
    TransferGDao transferGDao;

    @Inject
    EntityHDao entityHDao;

    @Inject
    TransferHDao transferHDao;

    @Inject
    EntityIDao entityIDao;

    @Inject
    TransferIDao transferIDao;

    @Inject
    EntityJDao entityJDao;

    @Inject
    TransferJDao transferJDao;

    @Inject
    EntityLDao entityLDao;

    @Inject
    TransferLDao transferLDao;

    @Inject
    EntityKDao entityKDao;

    @Inject
    TransferKDao transferKDao;

    /**
     * The test checks the association mapped single relation work well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     * @scenario 
     *  
     *  
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

        TransferB tb1 = transferBDao.create(TransferBForCreate.builder().build());
        TransferB tb2 = transferBDao.create(TransferBForCreate.builder().build());

        TransferA transferA = transferADao.create(TransferAForCreate
                        .builder()
                        .withRelationBonA(tb1)
                        .build()
        );

        assertEquals(tb1.identifier(), transferADao.queryRelationBonA(transferA).orElseThrow().identifier());

        EntityA a = entityADao.getById(transferA.identifier().adaptTo(EntityAIdentifier.class)).orElseThrow();
        EntityB b1 = entityBDao.getById(tb1.identifier().adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals(b1.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferADao.setRelationBonA(transferA, tb2);
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
     * The test checks the association mapped single-required relation work well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
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

        TransferD td1 = transferDDao.create(TransferDForCreate.builder().build());
        TransferD td2 = transferDDao.create(TransferDForCreate.builder().build());

        TransferC transferC = transferCDao.create(TransferCForCreate
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

        ValidationException thrown1 = assertThrows(
                ValidationException.class,
                () -> transferCDao.create(TransferCForCreate.builder().build())
        );

        assertEquals(1, thrown1.getValidationResults().size());
        assertThat(thrown1.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("relationDonC")))
        ));

        // Test dao set, unset
        transferCDao.setRelationDonC(transferC, td1);
        transferC = transferCDao.getById(transferC.identifier()).orElseThrow();

        assertEquals(td1.identifier(), transferCDao.queryRelationDonC(transferC).identifier());
        assertEquals(d1.identifier(), entityCDao.queryRelationDonC(c).identifier());

    }

    /**
     * The test checks the association mapped multi relation work well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
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

        TransferF tf1 = transferFDao.create(TransferFForCreate.builder().build());
        TransferF tf2 = transferFDao.create(TransferFForCreate.builder().build());
        TransferF tf3 = transferFDao.create(TransferFForCreate.builder().build());

        TransferE transferE = transferEDao.create(TransferEForCreate
                        .builder()
                        .withRelationFonE(List.of(tf1, tf2, tf3))
                        .build()
        );

        TransferE te = transferEDao.create(TransferEForCreate.builder().build());

        // Check the entity presentations are existing.

        assertThat(transferEDao.queryRelationFonE(transferE).selectList(), containsInAnyOrder(tf1, tf2, tf3));

        EntityE e = entityEDao.getById(transferE.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();
        List<EntityF> listOfEntityF = entityFDao.query().selectList();

        assertEquals(3, listOfEntityF.size());

        // Delete one related element

        assertTrue(transferEDao.queryRelationFonE(transferE).selectList().contains(tf3));

        transferFDao.delete(tf3);
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        assertFalse(transferEDao.queryRelationFonE(transferE).selectList().contains(tf3));
        assertEquals(2, transferEDao.queryRelationFonE(transferE).count());
        assertTrue(entityFDao.getById(tf3.identifier().adaptTo(EntityFIdentifier.class)).isEmpty());

        // Add an element

        tf3 = transferFDao.create(TransferFForCreate.builder().build());
        List<TransferF> relationFonE = transferEDao.queryRelationFonE(transferE).selectList();
        transferEDao.addRelationFonE(transferE, List.of(tf3));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        assertEquals(3, transferEDao.queryRelationFonE(transferE).count());
        assertThat(transferEDao.queryRelationFonE(transferE).selectList(), containsInAnyOrder(tf1, tf2, tf3));

        TransferFForCreate tf1ForCreate = TransferFForCreate.builder().withNameF("tf1").build();
        TransferFForCreate tf2ForCreate = TransferFForCreate.builder().withNameF("tf2").build();
        TransferFForCreate tf3ForCreate = TransferFForCreate.builder().withNameF("tf3").build();
        List<TransferF> maskedFs = transferEDao.createRelationFonE(transferE, List.of(tf1ForCreate, tf2ForCreate, tf3ForCreate), TransferFMask.transferFMask().withNameF());
        assertThat(maskedFs.stream().map(TransferF::getNameF).map(Optional::orElseThrow).toList(), hasItems("tf1", "tf2", "tf3"));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        List<String> relationFonEs = transferEDao.queryRelationFonE(transferE).selectList().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(3, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3"));

        //Add
        TransferF tf4 = transferFDao.create(TransferFForCreate.builder().withNameF("tf4").build());
        transferEDao.addRelationFonE(transferE, List.of(tf4));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        relationFonEs = transferEDao.queryRelationFonE(transferE).selectList().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(4, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3", "tf4"));

        //Remove
        transferEDao.removeRelationFonE(transferE, List.of(tf4));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        relationFonEs = transferEDao.queryRelationFonE(transferE).selectList().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(3, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3"));

        List<TransferF> relationContent = transferEDao.queryRelationFonE(transferE).selectList().stream().filter(ee -> ee.getNameF().isPresent()).toList();
        assertEquals(3, relationContent.size());
        TransferF elementToRemove = relationContent.get(0);
        transferEDao.removeRelationFonE(transferE, List.of(elementToRemove));
        relationContent = transferEDao.queryRelationFonE(transferE).selectList().stream().filter(ee -> ee.getNameF().isPresent()).toList();
        assertEquals(relationContent.stream().filter(ee -> !ee.getNameF().equals(elementToRemove.getNameF()))
                        .map(TransferF::identifier)
                        .collect(Collectors.toSet()),
                relationContent.stream().map(TransferF::identifier).collect(Collectors.toSet()));
    }


    /**
     * The test checks the two-way association mapped relation with one side is optional to be created well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
     *
     */
    @Test
    @TestCase("TwoWayAssAssRelationOneSideIsOptionalOnTransferCreate")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-SRV-001"
    })
    public void testTwoWayAssAssRelationOneSideIsOptionalOnTransferCreate() {
        /*
        optional <-> optional
        optional <-> required
        optional <-> collection
         */

        TransferH th1 = transferHDao.create(TransferHForCreate.builder().withNameH("TH1").build());
        TransferH th2 = transferHDao.create(TransferHForCreate.builder().withNameH("TH2").build());
        // for collection
        TransferH th3 = transferHDao.create(TransferHForCreate.builder().withNameH("TH3").build());
        TransferH th4 = transferHDao.create(TransferHForCreate.builder().withNameH("TH4").build());
        TransferH th5 = transferHDao.create(TransferHForCreate.builder().withNameH("TH5").build());

        TransferG tg = transferGDao.create(
                TransferGForCreate.builder().withNameG("G")
                        .withRelationOptionalH(th1)
                        .withRelationRequiredH(th2)
                        .withRelationCollectionH(List.of(th3, th4, th5))
                        .build()
        );


        // check the relation is good in both sides
        // tg side
        assertEquals(th1.identifier(), transferGDao.queryRelationOptionalH(tg).orElseThrow().identifier());
        assertEquals(th2.identifier(), transferGDao.queryRelationRequiredH(tg).identifier());
        assertThat(transferGDao.queryRelationCollectionH(tg).selectList(), containsInAnyOrder(th3, th4, th5));
        // th side
        assertEquals(tg.identifier(), transferHDao.queryRelationGForOptionalH(th1).orElseThrow().identifier());
        assertEquals(tg.identifier(), transferHDao.queryRelationGForRequiredH(th2).orElseThrow().identifier());
        assertEquals(tg.identifier(), transferHDao.queryRelationGForCollectionH(th3).orElseThrow().identifier());
        assertEquals(tg.identifier(), transferHDao.queryRelationGForCollectionH(th4).orElseThrow().identifier());
        assertEquals(tg.identifier(), transferHDao.queryRelationGForCollectionH(th5).orElseThrow().identifier());

        // entity representation
        // g side
        EntityH h1 = entityHDao.getById(th1.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h2 = entityHDao.getById(th2.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h3 = entityHDao.getById(th3.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h4 = entityHDao.getById(th4.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h5 = entityHDao.getById(th5.adaptTo(EntityHIdentifier.class)).orElseThrow();

        EntityG g = entityGDao.getById(tg.adaptTo(EntityGIdentifier.class)).orElseThrow();

        assertEquals(h1.identifier(), entityGDao.queryRelationOptionalH(g).orElseThrow().identifier());
        assertEquals(h2.identifier(), entityGDao.queryRelationRequiredH(g).identifier());
        assertThat(entityGDao.queryRelationCollectionH(g).selectList(), containsInAnyOrder(h3, h4, h5));
        // h side
        assertEquals(g.identifier(), entityHDao.queryRelationGForOptionalH(h1).orElseThrow().identifier());
        assertEquals(g.identifier(), entityHDao.queryRelationGForRequiredH(h2).orElseThrow().identifier());
        assertEquals(g.identifier(), entityHDao.queryRelationGForCollectionH(h3).orElseThrow().identifier());
        assertEquals(g.identifier(), entityHDao.queryRelationGForCollectionH(h4).orElseThrow().identifier());
        assertEquals(g.identifier(), entityHDao.queryRelationGForCollectionH(h5).orElseThrow().identifier());

    }

    /**
     * The test checks the two-way association mapped relation with one side is optional sdk functionality work well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
     *
     */
    @Test
    @TestCase("TwoWayAssAssRelationOneSideIsOptionalOnTransferDaoFunctionality")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-SRV-001"
    })
    public void testTwoWayAssAssRelationOneSideIsOptionalOnTransferDaoFunctionality() {
        /*
        optional <-> optional
        optional <-> required
        optional <-> collection
         */

        TransferH th1 = transferHDao.create(TransferHForCreate.builder().withNameH("TH1").build());
        TransferH th2 = transferHDao.create(TransferHForCreate.builder().withNameH("TH2").build());
        // for collection
        TransferH th3 = transferHDao.create(TransferHForCreate.builder().withNameH("TH3").build());
        TransferH th4 = transferHDao.create(TransferHForCreate.builder().withNameH("TH4").build());
        TransferH th5 = transferHDao.create(TransferHForCreate.builder().withNameH("TH5").build());

        TransferG tg1 = transferGDao.create(
                TransferGForCreate.builder().withNameG("G1")
                        .withRelationOptionalH(th1)
                        .withRelationRequiredH(th2)
                        .withRelationCollectionH(List.of(th3, th4, th5))
                        .build()
        );

        EntityH h1 = entityHDao.getById(th1.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h2 = entityHDao.getById(th2.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h3 = entityHDao.getById(th3.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h4 = entityHDao.getById(th4.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityH h5 = entityHDao.getById(th5.adaptTo(EntityHIdentifier.class)).orElseThrow();
        EntityG g1 = entityGDao.getById(tg1.adaptTo(EntityGIdentifier.class)).orElseThrow();

        // update
        TransferG tgRef = transferHDao.queryRelationGForOptionalH(th1).orElseThrow();
        tgRef.setNameG("CG1");
        transferGDao.update(tgRef);

        assertEquals(Optional.of("CG1"), transferHDao.queryRelationGForOptionalH(th1).orElseThrow().getNameG());
        assertEquals(Optional.of("CG1"), transferHDao.queryRelationGForRequiredH(th2).orElseThrow().getNameG());
        assertEquals(Optional.of("CG1"), transferHDao.queryRelationGForCollectionH(th3).orElseThrow().getNameG());
        assertEquals(Optional.of("CG1"), transferHDao.queryRelationGForCollectionH(th4).orElseThrow().getNameG());
        assertEquals(Optional.of("CG1"), transferHDao.queryRelationGForCollectionH(th5).orElseThrow().getNameG());

        // unset
        transferGDao.unsetRelationOptionalH(tg1);
        assertTrue(transferGDao.queryRelationOptionalH(tg1).isEmpty());
        assertTrue(transferHDao.queryRelationGForOptionalH(th1).isEmpty());
        assertTrue(entityGDao.queryRelationOptionalH(g1).isEmpty());
        assertTrue(entityHDao.queryRelationGForOptionalH(h1).isEmpty());

        transferGDao.setRelationOptionalH(tg1, th1);
        assertTrue(transferGDao.queryRelationOptionalH(tg1).isPresent());
        assertTrue(transferHDao.queryRelationGForOptionalH(th1).isPresent());
        assertTrue(entityGDao.queryRelationOptionalH(g1).isPresent());
        assertTrue(entityHDao.queryRelationGForOptionalH(h1).isPresent());

        transferHDao.unsetRelationGForOptionalH(th1);
        assertTrue(transferGDao.queryRelationOptionalH(tg1).isEmpty());
        assertTrue(transferHDao.queryRelationGForOptionalH(th1).isEmpty());
        assertTrue(entityGDao.queryRelationOptionalH(g1).isEmpty());
        assertTrue(entityHDao.queryRelationGForOptionalH(h1).isEmpty());

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> transferHDao.unsetRelationGForRequiredH(th2));
        assertTrue(thrown.getMessage().contains("There is reference remove which let referrer violate mandatory constraint"));

        // collection unset
        transferHDao.unsetRelationGForCollectionH(th3);
        assertTrue(transferHDao.queryRelationGForCollectionH(th3).isEmpty());
        assertEquals(2, transferGDao.queryRelationCollectionH(tg1).count());
        assertThat(transferGDao.queryRelationCollectionH(tg1).selectList(), containsInAnyOrder(th4, th5));
        assertTrue(entityHDao.queryRelationGForCollectionH(h3).isEmpty());
        assertEquals(2, entityGDao.queryRelationCollectionH(g1).count());
        assertThat(entityGDao.queryRelationCollectionH(g1).selectList(), containsInAnyOrder(h4, h5));

        // set
        TransferH th6 = transferHDao.create(TransferHForCreate.builder().withNameH("TH6").build());
        EntityH h6 = entityHDao.getById(th6.adaptTo(EntityHIdentifier.class)).orElseThrow();

        TransferG tg2 = transferGDao.create(
                TransferGForCreate.builder().withNameG("G2")
                        .withRelationRequiredH(th6)
                        .build()
        );
        EntityG g2 = entityGDao.getById(tg2.adaptTo(EntityGIdentifier.class)).orElseThrow();

        transferGDao.setRelationOptionalH(tg2, th1);
        assertEquals(th1.identifier(), transferGDao.queryRelationOptionalH(tg2).orElseThrow().identifier());
        assertEquals(tg2.identifier(), transferHDao.queryRelationGForOptionalH(th1).orElseThrow().identifier());
        assertEquals(h1.identifier(), entityGDao.queryRelationOptionalH(g2).orElseThrow().identifier());
        assertEquals(g2.identifier(), entityHDao.queryRelationGForOptionalH(h1).orElseThrow().identifier());

        TransferH th7 = transferHDao.create(TransferHForCreate.builder().withNameH("TH7").build());
        EntityH h7 = entityHDao.getById(th7.adaptTo(EntityHIdentifier.class)).orElseThrow();

        transferGDao.setRelationOptionalH(tg2, th7);
        assertEquals(th7.identifier(), transferGDao.queryRelationOptionalH(tg2).orElseThrow().identifier());
        assertEquals(tg2.identifier(), transferHDao.queryRelationGForOptionalH(th7).orElseThrow().identifier());
        assertEquals(h7.identifier(), entityGDao.queryRelationOptionalH(g2).orElseThrow().identifier());
        assertEquals(g2.identifier(), entityHDao.queryRelationGForOptionalH(h7).orElseThrow().identifier());

        transferHDao.setRelationGForOptionalH(th7, tg1);
        assertEquals(th7.identifier(), transferGDao.queryRelationOptionalH(tg1).orElseThrow().identifier());
        assertEquals(tg1.identifier(), transferHDao.queryRelationGForOptionalH(th7).orElseThrow().identifier());
        assertEquals(h7.identifier(), entityGDao.queryRelationOptionalH(g1).orElseThrow().identifier());
        assertEquals(g1.identifier(), entityHDao.queryRelationGForOptionalH(h7).orElseThrow().identifier());

        TransferH th8 = transferHDao.create(TransferHForCreate.builder().withNameH("TH8").build());
        EntityH h8 = entityHDao.getById(th8.adaptTo(EntityHIdentifier.class)).orElseThrow();

        transferGDao.setRelationRequiredH(tg2, th8);
        assertEquals(th8.identifier(), transferGDao.queryRelationRequiredH(tg2).identifier());
        assertEquals(tg2.identifier(), transferHDao.queryRelationGForRequiredH(th8).orElseThrow().identifier());
        assertEquals(h8.identifier(), entityGDao.queryRelationRequiredH(g2).identifier());
        assertEquals(g2.identifier(), entityHDao.queryRelationGForRequiredH(h8).orElseThrow().identifier());

        //set the required relation from the h side
        IllegalStateException thrown1 = assertThrows(IllegalStateException.class, () -> transferHDao.setRelationGForRequiredH(th6, tg2));
        assertTrue(thrown1.getMessage().contains("There is reference add which let referrer violate mandatory constraint"));

        // collection add
        transferHDao.setRelationGForCollectionH(th4, tg2);
        assertEquals(1, transferGDao.queryRelationCollectionH(tg2).count());
        assertThat(transferGDao.queryRelationCollectionH(tg2).selectList(), containsInAnyOrder(th4));
        assertEquals(1, entityGDao.queryRelationCollectionH(g2).count());
        assertThat(entityGDao.queryRelationCollectionH(g2).selectList(), containsInAnyOrder(h4));

        TransferH th9 = transferHDao.create(TransferHForCreate.builder().withNameH("TH9").build());
        EntityH h9 = entityHDao.getById(th9.adaptTo(EntityHIdentifier.class)).orElseThrow();

        transferGDao.addRelationCollectionH(tg2, List.of(th9));
        assertEquals(2, transferGDao.queryRelationCollectionH(tg2).count());
        assertThat(transferGDao.queryRelationCollectionH(tg2).selectList(), containsInAnyOrder(th4, th9));
        assertEquals(2, entityGDao.queryRelationCollectionH(g2).count());
        assertThat(entityGDao.queryRelationCollectionH(g2).selectList(), containsInAnyOrder(h4, h9));

        assertEquals(tg2.identifier(), transferHDao.queryRelationGForCollectionH(th9).orElseThrow().identifier());
        assertEquals(g2.identifier(), entityHDao.queryRelationGForCollectionH(h9).orElseThrow().identifier());

        // collection remove
        transferGDao.removeRelationCollectionH(tg2, List.of(th9));
        assertEquals(1, transferGDao.queryRelationCollectionH(tg2).count());
        assertThat(transferGDao.queryRelationCollectionH(tg2).selectList(), containsInAnyOrder(th4));
        assertEquals(1, entityGDao.queryRelationCollectionH(g2).count());
        assertThat(entityGDao.queryRelationCollectionH(g2).selectList(), containsInAnyOrder(h4));

        assertTrue(transferHDao.queryRelationGForCollectionH(th9).isEmpty());
        assertTrue(entityHDao.queryRelationGForCollectionH(h9).isEmpty());

        //delete

        transferHDao.delete(th1);
        assertTrue(transferHDao.getById(th1.identifier()).isEmpty());
        assertTrue(entityHDao.getById(h1.identifier()).isEmpty());

        IllegalStateException thrown2 = assertThrows(IllegalStateException.class, () -> transferHDao.delete(th2));
        assertTrue(thrown2.getMessage().contains("There are mandatory references that cannot be removed"));

        transferGDao.delete(tg1);
        assertTrue(transferGDao.getById(tg1.identifier()).isEmpty());
        assertTrue(entityGDao.getById(g1.identifier()).isEmpty());

        transferHDao.delete(th2);
        assertTrue(transferHDao.getById(th2.identifier()).isEmpty());
        assertTrue(entityHDao.getById(h2.identifier()).isEmpty());

    }

    /**
     * The test checks the two-way association mapped relation with one side is required to be created well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
     *
     */
    @Test
    @TestCase("TwoWayAssAssRelationOneSideIsRequiredOnTransferCreate")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-SRV-001"
    })
    public void testTwoWayAssAssRelationOneSideIsRequiredOnTransferCreate() {
        /*
        required <-> optional
        required <-> collection
         */

        TransferI ti1 = transferIDao.create(TransferIForCreate.builder().withNameI("TI1").build());
        TransferI ti2 = transferIDao.create(TransferIForCreate.builder().withNameI("TI2").build());
        TransferI tiCollect = transferIDao.create(TransferIForCreate.builder().withNameI("TICollect").build());

        TransferJ tj1 = transferJDao.create(
                TransferJForCreate.builder().withNameJ("J1")
                        .withRelationRequiredIForOptionalJ(ti1)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        TransferJ tj2 = transferJDao.create(
                TransferJForCreate.builder().withNameJ("J2")
                        .withRelationRequiredIForOptionalJ(ti2)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        // check the relation is good in both sides
        // ti side
        assertEquals(tj1.identifier(), transferIDao.queryRelationOptionalJ(ti1).orElseThrow().identifier());
        assertEquals(tj2.identifier(), transferIDao.queryRelationOptionalJ(ti2).orElseThrow().identifier());
        assertThat(transferIDao.queryRelationCollectionJ(tiCollect).selectList(), containsInAnyOrder(tj1, tj2));

        // tj side
        assertEquals(ti1.identifier(), transferJDao.queryRelationRequiredIForOptionalJ(tj1).identifier());
        assertEquals(ti2.identifier(), transferJDao.queryRelationRequiredIForOptionalJ(tj2).identifier());
        assertEquals(tiCollect.identifier(), transferJDao.queryRelationRequiredIorCollectionJ(tj1).identifier());
        assertEquals(tiCollect.identifier(), transferJDao.queryRelationRequiredIorCollectionJ(tj2).identifier());

        // entity representation
        // t side
        EntityI i1 = entityIDao.getById(ti1.adaptTo(EntityIIdentifier.class)).orElseThrow();
        EntityI i2 = entityIDao.getById(ti2.adaptTo(EntityIIdentifier.class)).orElseThrow();
        EntityI iCollect = entityIDao.getById(tiCollect.adaptTo(EntityIIdentifier.class)).orElseThrow();

        EntityJ j1 = entityJDao.getById(tj1.adaptTo(EntityJIdentifier.class)).orElseThrow();
        EntityJ j2 = entityJDao.getById(tj2.adaptTo(EntityJIdentifier.class)).orElseThrow();

        // ti side
        assertEquals(j1.identifier(), entityIDao.queryRelationOptionalJ(i1).orElseThrow().identifier());
        assertEquals(j2.identifier(), entityIDao.queryRelationOptionalJ(i2).orElseThrow().identifier());
        assertThat(entityIDao.queryRelationCollectionJ(iCollect).selectList(), containsInAnyOrder(j1, j2));

        // tj side
        assertEquals(i1.identifier(), entityJDao.queryRelationRequiredIForOptionalJ(j1).identifier());
        assertEquals(i2.identifier(), entityJDao.queryRelationRequiredIForOptionalJ(j2).identifier());
        assertEquals(iCollect.identifier(), entityJDao.queryRelationRequiredIorCollectionJ(j1).identifier());
        assertEquals(iCollect.identifier(), entityJDao.queryRelationRequiredIorCollectionJ(j2).identifier());

    }

    /**
     * The test checks the two-way association mapped relation with one side is required sdk functionality work well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
     *
     */
    @Test
    @TestCase("TwoWayAssAssRelationOneSideIsRequiredOnTransferDaoFunctionality")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-SRV-001"
    })
    public void testTwoWayAssAssRelationOneSideIsRequiredDaoFunctionality() {

        /*
        required <-> optional
        required <-> collection
         */

        TransferI ti1 = transferIDao.create(TransferIForCreate.builder().withNameI("TI1").build());
        TransferI ti2 = transferIDao.create(TransferIForCreate.builder().withNameI("TI2").build());
        TransferI tiCollect = transferIDao.create(TransferIForCreate.builder().withNameI("TICollect").build());

        TransferJ tj1 = transferJDao.create(
                TransferJForCreate.builder().withNameJ("J1")
                        .withRelationRequiredIForOptionalJ(ti1)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        TransferJ tj2 = transferJDao.create(
                TransferJForCreate.builder().withNameJ("J2")
                        .withRelationRequiredIForOptionalJ(ti2)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        EntityI i1 = entityIDao.getById(ti1.adaptTo(EntityIIdentifier.class)).orElseThrow();
        EntityI i2 = entityIDao.getById(ti2.adaptTo(EntityIIdentifier.class)).orElseThrow();
        EntityI iCollect = entityIDao.getById(tiCollect.adaptTo(EntityIIdentifier.class)).orElseThrow();
        EntityJ j1 = entityJDao.getById(tj1.adaptTo(EntityJIdentifier.class)).orElseThrow();
        EntityJ j2 = entityJDao.getById(tj2.adaptTo(EntityJIdentifier.class)).orElseThrow();

        // update
        tj1.setNameJ("CT1");
        tj2.setNameJ("CT2");
        tj1 = transferJDao.update(tj1);
        tj2 = transferJDao.update(tj2);

        assertEquals(Optional.of("CT1"), transferIDao.queryRelationOptionalJ(ti1).orElseThrow().getNameJ());
        assertEquals(Optional.of("CT2"), transferIDao.queryRelationOptionalJ(ti2).orElseThrow().getNameJ());
        assertThat(transferIDao.queryRelationCollectionJ(tiCollect).selectList().stream().map(t -> t.getNameJ()).filter(Optional::isPresent).map(Optional::get).toList(), containsInAnyOrder("CT1", "CT2"));

        assertEquals(Optional.of("CT1"), entityIDao.queryRelationOptionalJ(i1).orElseThrow().getNameJ());
        assertEquals(Optional.of("CT2"), entityIDao.queryRelationOptionalJ(i2).orElseThrow().getNameJ());
        assertThat(entityIDao.queryRelationCollectionJ(iCollect).selectList().stream().map(t -> t.getNameJ()).filter(Optional::isPresent).map(Optional::get).toList(), containsInAnyOrder("CT1", "CT2"));

        // set another t

        TransferI ti3 = transferIDao.create(TransferIForCreate.builder().withNameI("TI3").build());
        EntityI i3 = entityIDao.getById(ti3.adaptTo(EntityIIdentifier.class)).orElseThrow();

        transferJDao.setRelationRequiredIForOptionalJ(tj1, ti3);
        assertEquals(ti3.identifier(), transferJDao.queryRelationRequiredIForOptionalJ(tj1).identifier());
        assertTrue(transferIDao.queryRelationOptionalJ(ti1).isEmpty());
        assertEquals(i3.identifier(), entityJDao.queryRelationRequiredIForOptionalJ(j1).identifier());
        assertTrue(entityIDao.queryRelationOptionalJ(i1).isEmpty());

        // set with the other side error

        TransferJ refertj1 = tj1;
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> transferIDao.setRelationOptionalJ(ti1, refertj1));
        assertTrue(thrown.getMessage().contains("There is reference add which let referrer violate mandatory constraint"));

        IllegalStateException thrown1 = assertThrows(IllegalStateException.class, () -> transferIDao.unsetRelationOptionalJ(ti3));
        assertTrue(thrown1.getMessage().contains("There is reference remove which let referrer violate mandatory constraint"));

        // collection

        TransferI ti4 = transferIDao.create(TransferIForCreate.builder().withNameI("TI4").build());
        EntityI i4 = entityIDao.getById(ti4.adaptTo(EntityIIdentifier.class)).orElseThrow();

        transferJDao.setRelationRequiredIorCollectionJ(tj1, ti4);
        assertEquals(ti4.identifier(), transferJDao.queryRelationRequiredIorCollectionJ(tj1).identifier());
        assertEquals(1, transferIDao.queryRelationCollectionJ(tiCollect).count());
        assertEquals(i4.identifier(), entityJDao.queryRelationRequiredIorCollectionJ(j1).identifier());
        assertEquals(1, entityIDao.queryRelationCollectionJ(iCollect).count());

        // remove throw error

        TransferJ refertj2 = tj2;
        IllegalStateException thrown2 = assertThrows(IllegalStateException.class, () -> transferIDao.removeRelationCollectionJ(tiCollect, List.of(refertj2)));
        assertTrue(thrown2.getMessage().contains("There is reference remove which let referrer violate mandatory constraint"));

        // delete
        IllegalStateException thrown3 = assertThrows(IllegalStateException.class, () -> transferIDao.delete(ti2));
        assertTrue(thrown3.getMessage().contains("There are mandatory references that cannot be removed"));

        transferJDao.delete(tj2);
        assertTrue(transferJDao.getById(tj2.identifier()).isEmpty());
        assertTrue(entityJDao.getById(j2.identifier()).isEmpty());

        transferIDao.delete(ti2);
        assertTrue(transferIDao.getById(ti2.identifier()).isEmpty());
        assertTrue(entityIDao.getById(i2.identifier()).isEmpty());

    }

    /**
     * The test checks the two-way association mapped relation with one side is collection to be created well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
     *
     */
    @Test
    @TestCase("TwoWayAssAssRelationOneSideIsCollectionOnTransferCreate")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-SRV-001"
    })
    public void testTwoWayAssAssRelationOneSideIsCollectionCreate() {
        /*
        collection <-> collection
         */

        TransferK tk1 = transferKDao.create(TransferKForCreate.builder().withNameK("TK1").build());
        TransferL tl1 = transferLDao.create(
                TransferLForCreate.builder().withNameL("TL1").withRelationCollectionKForCollectionL(List.of(tk1)).build()

        );
        TransferK tk2 = transferKDao.create(
                TransferKForCreate.builder().withNameK("TK2").withRelationCollectionL(List.of(tl1)).build()
        );
        TransferL tl2 = transferLDao.create(
                TransferLForCreate.builder().withNameL("TL2").withRelationCollectionKForCollectionL(List.of(tk2)).build()

        );
        TransferK tk3 = transferKDao.create(
                TransferKForCreate.builder().withNameK("TK3").withRelationCollectionL(List.of(tl2)).build()
        );
        TransferL tl3 = transferLDao.create(
                TransferLForCreate.builder().withNameL("TL3").withRelationCollectionKForCollectionL(List.of(tk3)).build()

        );

        assertEquals(3, transferKDao.countAll());
        assertEquals(3, transferLDao.countAll());

        assertThat(transferLDao.queryRelationCollectionKForCollectionL(tl1).selectList(), containsInAnyOrder(tk1, tk2));
        assertThat(transferLDao.queryRelationCollectionKForCollectionL(tl2).selectList(), containsInAnyOrder(tk2, tk3));
        assertThat(transferLDao.queryRelationCollectionKForCollectionL(tl3).selectList(), containsInAnyOrder(tk3));

        assertThat(transferKDao.queryRelationCollectionL(tk1).selectList(), containsInAnyOrder(tl1));
        assertThat(transferKDao.queryRelationCollectionL(tk2).selectList(), containsInAnyOrder(tl1, tl2));
        assertThat(transferKDao.queryRelationCollectionL(tk3).selectList(), containsInAnyOrder(tl2, tl3));


        EntityL l1 = entityLDao.getById(tl1.adaptTo(EntityLIdentifier.class)).orElseThrow();
        EntityL l2 = entityLDao.getById(tl2.adaptTo(EntityLIdentifier.class)).orElseThrow();
        EntityL l3 = entityLDao.getById(tl3.adaptTo(EntityLIdentifier.class)).orElseThrow();

        EntityK k1 = entityKDao.getById(tk1.adaptTo(EntityKIdentifier.class)).orElseThrow();
        EntityK k2 = entityKDao.getById(tk2.adaptTo(EntityKIdentifier.class)).orElseThrow();
        EntityK k3 = entityKDao.getById(tk3.adaptTo(EntityKIdentifier.class)).orElseThrow();

        assertEquals(3, entityLDao.countAll());
        assertEquals(3, entityKDao.countAll());

        assertThat(entityLDao.queryRelationCollectionKForCollectionL(l1).selectList(), containsInAnyOrder(k1, k2));
        assertThat(entityLDao.queryRelationCollectionKForCollectionL(l2).selectList(), containsInAnyOrder(k2, k3));
        assertThat(entityLDao.queryRelationCollectionKForCollectionL(l3).selectList(), containsInAnyOrder(k3));

        assertThat(entityKDao.queryRelationCollectionL(k1).selectList(), containsInAnyOrder(l1));
        assertThat(entityKDao.queryRelationCollectionL(k2).selectList(), containsInAnyOrder(l1, l2));
        assertThat(entityKDao.queryRelationCollectionL(k3).selectList(), containsInAnyOrder(l2, l3));
    }

    /**
     * The test checks the two-way association mapped relation with one side is collection sdk functionality work well on a transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAssociation.jsl
     *
     */
    @Test
    @TestCase("TwoWayAssAssRelationOneSideIsCollectionDaoFunctionality")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-SRV-001"
    })
    public void testTwoWayAssAssRelationOneSideIsCollectionOnTransferDaoFunctionality() {
        /*
        collection <-> collection
         */

        TransferK tk1 = transferKDao.create(TransferKForCreate.builder().withNameK("TK1").build());
        TransferL tl1 = transferLDao.create(
                TransferLForCreate.builder().withNameL("TL1").withRelationCollectionKForCollectionL(List.of(tk1)).build()

        );
        TransferK tk2 = transferKDao.create(
                TransferKForCreate.builder().withNameK("TK2").withRelationCollectionL(List.of(tl1)).build()
        );
        TransferL tl2 = transferLDao.create(
                TransferLForCreate.builder().withNameL("TL2").withRelationCollectionKForCollectionL(List.of(tk2)).build()

        );
        TransferK tk3 = transferKDao.create(
                TransferKForCreate.builder().withNameK("TK3").withRelationCollectionL(List.of(tl2)).build()
        );
        TransferL tl3 = transferLDao.create(
                TransferLForCreate.builder().withNameL("TL3").withRelationCollectionKForCollectionL(List.of(tk3)).build()

        );

        EntityL l1 = entityLDao.getById(tl1.adaptTo(EntityLIdentifier.class)).orElseThrow();
        EntityL l2 = entityLDao.getById(tl2.adaptTo(EntityLIdentifier.class)).orElseThrow();
        EntityL l3 = entityLDao.getById(tl3.adaptTo(EntityLIdentifier.class)).orElseThrow();

        EntityK k1 = entityKDao.getById(tk1.adaptTo(EntityKIdentifier.class)).orElseThrow();
        EntityK k2 = entityKDao.getById(tk2.adaptTo(EntityKIdentifier.class)).orElseThrow();
        EntityK k3 = entityKDao.getById(tk3.adaptTo(EntityKIdentifier.class)).orElseThrow();

        //update

        tl1.setNameL("CTL1");
        tl1 = transferLDao.update(tl1);

        assertTrue(transferKDao.queryRelationCollectionL(tk1).selectList().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());
        assertTrue(transferKDao.queryRelationCollectionL(tk2).selectList().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());

        assertTrue(entityKDao.queryRelationCollectionL(k1).selectList().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());
        assertTrue(entityKDao.queryRelationCollectionL(k2).selectList().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());


        //add

        transferLDao.addRelationCollectionKForCollectionL(tl1, List.of(tk3));
        assertEquals(3, transferLDao.queryRelationCollectionKForCollectionL(tl1).count());
        assertEquals(3, entityLDao.queryRelationCollectionKForCollectionL(l1).count());
        assertEquals(3, transferKDao.queryRelationCollectionL(tk3).count());
        assertEquals(3, entityKDao.queryRelationCollectionL(k3).count());

        //remove

        transferLDao.removeRelationCollectionKForCollectionL(tl1, List.of(tk3));
        assertEquals(2, transferLDao.queryRelationCollectionKForCollectionL(tl1).count());
        assertEquals(2, entityLDao.queryRelationCollectionKForCollectionL(l1).count());
        assertEquals(2, transferKDao.queryRelationCollectionL(tk3).count());
        assertEquals(2, entityKDao.queryRelationCollectionL(k3).count());

        //delete

        transferLDao.delete(tl1);
        assertTrue(transferLDao.getById(tl1.identifier()).isEmpty());
        assertEquals(0, transferKDao.queryRelationCollectionL(tk1).count());
        assertEquals(1, transferKDao.queryRelationCollectionL(tk2).count());
        assertTrue(entityLDao.getById(l1.identifier()).isEmpty());
        assertEquals(0, entityKDao.queryRelationCollectionL(k1).count());
        assertEquals(1, entityKDao.queryRelationCollectionL(k2).count());
        
    }

}
