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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entitya.EntityAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityc.EntityC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityc.EntityCIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityd.EntityDIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entitye.EntityE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entitye.EntityEIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityf.EntityF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityf.EntityFIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityg.EntityG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityg.EntityGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityg.EntityGIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityh.EntityH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityh.EntityHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityh.EntityHIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityi.EntityI;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityi.EntityIDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityi.EntityIIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityj.EntityJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityj.EntityJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityj.EntityJIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityk.EntityK;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityk.EntityKDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityk.EntityKIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityl.EntityL;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityl.EntityLDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.entityl.EntityLIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.th.TH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.th.THDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.tj.TJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.tj.TJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.tk.TK;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.tk.TKDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfere.TransferE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfere.TransferEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferf.TransferF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferf.TransferFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferg.TransferG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferg.TransferGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferh.TransferH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferh.TransferHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferi.TransferI;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferi.TransferIDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferj.TransferJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferj.TransferJAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferj.TransferJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferk.TransferK;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferk.TransferKDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferl.TransferL;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferl.TransferLAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferl.TransferLDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferm.TransferM;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transferm.TransferMDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfern.TransferN;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfern.TransferNDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfero.TransferO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociationaggregation.mappedtransferassociationaggregation.transfero.TransferODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferAssociationAggregationDaoModules;
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
public class MappedTransferAssociationAggregationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("MappedTransferAssociationAggregation", new MappedTransferAssociationAggregationDaoModules());

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
    EntityJDao entityJDao;

    @Inject
    TransferJDao transferJDao;

    @Inject
    EntityIDao entityIDao;

    @Inject
    TransferIDao transferIDao;

    @Inject
    EntityKDao entityKDao;

    @Inject
    TransferKDao transferKDao;

    @Inject
    EntityLDao entityLDao;

    @Inject
    TransferLDao transferLDao;

    @Inject
    TransferMDao transferMDao;

    @Inject
    TransferNDao transferNDao;

    @Inject
    TransferODao transferODao;

    @Inject
    THDao thDao;

    @Inject
    TJDao tjDao;

    @Inject
    TKDao tkDao;

    /**
     * The test checks the aggregation mapped single relation work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleAssociationAggregationRelationOnTransfer")
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
    public void testSingleAssociationAggregationRelationOnTransfer() {

        TransferB tb1 = transferBDao.create(TransferB.builder().build());
        TransferB tb2 = transferBDao.create(TransferB.builder().build());

        TransferA transferA = transferADao.create(TransferA
                .builder()
                .withRelationBonA(tb1)
                .build());

        assertEquals(tb1.identifier(), transferA.getRelationBonA().orElseThrow().identifier());

        EntityA a = entityADao.getById(transferA.identifier().adaptTo(EntityAIdentifier.class)).orElseThrow();
        EntityB b1 = entityBDao.getById(tb1.identifier().adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals(b1.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferA.setRelationBonA(tb2);
        transferA = transferADao.update(transferA);

        assertEquals(tb2.identifier(), transferA.getRelationBonA().orElseThrow().identifier());

        EntityB b2 = entityBDao.getById(tb2.identifier().adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals(b2.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferA.setRelationBonA(null);
        transferA = transferADao.update(transferA);

        assertTrue(transferA.getRelationBonA().isEmpty());
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
     * The test checks the aggregation mapped single-required relation work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleRequiredAssociationAggregationRelationOnTransfer")
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
    public void testSingleRequiredAssociationAggregationRelationOnTransfer() {

        TransferD td1 = transferDDao.create(TransferD.builder().build());
        TransferD td2 = transferDDao.create(TransferD.builder().build());

        TransferC transferC = transferCDao.create(TransferC
                .builder()
                .withRelationDonC(td1)
                .build());

        assertEquals(td1.identifier(), transferC.getRelationDonC().identifier());

        EntityC c = entityCDao.getById(transferC.identifier().adaptTo(EntityCIdentifier.class)).orElseThrow();
        EntityD d1 = entityDDao.getById(td1.identifier().adaptTo(EntityDIdentifier.class)).orElseThrow();

        assertEquals(d1.identifier(), entityCDao.queryRelationDonC(c).identifier());

        transferC.setRelationDonC(td2);
        transferC = transferCDao.update(transferC);

        assertEquals(td2.identifier(), transferC.getRelationDonC().identifier());

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
                () -> transferCDao.create(TransferC.builder().build())
        );

        assertThat(thrown1.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("relationDonC")))
        ));

        // Test dao set, unset

        transferCDao.setRelationDonC(transferC, td1);
        transferC = transferCDao.getById(transferC.identifier()).orElseThrow();

        assertEquals(td1.identifier(), transferC.getRelationDonC().identifier());
        assertEquals(d1.identifier(), entityCDao.queryRelationDonC(c).identifier());

    }

    /**
     * The test checks the aggregation mapped multi relation work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAggregation.jsl
     *
     */
    @Test
    @TestCase("MultiAssociationAggregationRelationOnTransfer")
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
    public void testMultiAssociationAggregationRelationOnTransfer() {

        TransferF tf1 = transferFDao.create(TransferF.builder().build());
        TransferF tf2 = transferFDao.create(TransferF.builder().build());
        TransferF tf3 = transferFDao.create(TransferF.builder().build());

        TransferE transferE = transferEDao.create(TransferE
                .builder()
                .withRelationFonE(List.of(tf1, tf2, tf3))
                .build());

        // Check the entity presentations are existing.

        assertThat(transferE.getRelationFonE(), containsInAnyOrder(tf1, tf2, tf3));

        EntityE e = entityEDao.getById(transferE.identifier().adaptTo(EntityEIdentifier.class)).orElseThrow();
        List<EntityF> listOfEntityF = entityFDao.query().selectList();

        assertEquals(3, listOfEntityF.size());

        // Delete one related element

        assertTrue(transferE.getRelationFonE().contains(tf3));

        transferFDao.delete(tf3);
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        assertFalse(transferE.getRelationFonE().contains(tf3));
        assertEquals(2, transferE.getRelationFonE().size());
        assertTrue(entityFDao.getById(tf3.identifier().adaptTo(EntityFIdentifier.class)).isEmpty());

        // Add an element

        tf3 = transferFDao.create(TransferF.builder().build());
        List<TransferF> relationFonE = transferE.getRelationFonE();
        relationFonE.add(tf3);
        transferE.setRelationFonE(relationFonE);
        transferE = transferEDao.update(transferE);

        assertEquals(3, transferE.getRelationFonE().size());
        assertThat(transferE.getRelationFonE(), containsInAnyOrder(tf1, tf2, tf3));

        tf1 = TransferF.builder().withNameF("tf1").build();
        tf2 = TransferF.builder().withNameF("tf2").build();
        tf3 = TransferF.builder().withNameF("tf3").build();
        transferEDao.createRelationFonE(transferE, List.of(tf1, tf2, tf3));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        List<String> relationFonEs = transferE.getRelationFonE().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(3, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3"));

        //Add
        TransferF tf4 = transferFDao.create(TransferF.builder().withNameF("tf4").build());
        transferEDao.addRelationFonE(transferE, List.of(tf4));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        relationFonEs = transferE.getRelationFonE().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
        assertEquals(4, relationFonEs.size());
        assertEquals(new HashSet<>(relationFonEs), Set.of("tf1", "tf2", "tf3", "tf4"));

        //Remove
        transferEDao.removeRelationFonE(transferE, List.of(tf4));
        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();

        relationFonEs = transferE.getRelationFonE().stream().map(ee -> ee.getNameF()).filter(Optional::isPresent).map(Optional::get).toList();
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
     * The test checks the two-way aggregation mapped relation with one side is optional to be created well on a transfer object.
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
    @TestCase("TwoWayAssAggRelationOneSideIsOptionalOnTransferCreate")
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
    public void testTwoWayAssAggRelationOneSideIsOptionalOnTransferCreate() {
        /*
        optional <-> optional
        optional <-> required
        optional <-> collection
         */

        TransferH th1 = transferHDao.create(TransferH.builder().withNameH("TH1").build());
        TransferH th2 = transferHDao.create(TransferH.builder().withNameH("TH2").build());
        // for collection
        TransferH th3 = transferHDao.create(TransferH.builder().withNameH("TH3").build());
        TransferH th4 = transferHDao.create(TransferH.builder().withNameH("TH4").build());
        TransferH th5 = transferHDao.create(TransferH.builder().withNameH("TH5").build());

        TransferG tg = transferGDao.create(
                TransferG.builder().withNameG("G")
                        .withRelationOptionalH(th1)
                        .withRelationRequiredH(th2)
                        .withRelationCollectionH(List.of(th3, th4, th5))
                        .build()
        );


        // check the relation is good in both sides
        // tg side
        assertEquals(th1.identifier(), tg.getRelationOptionalH().orElseThrow().identifier());
        assertEquals(th2.identifier(), tg.getRelationRequiredH().identifier());
        assertThat(tg.getRelationCollectionH(), containsInAnyOrder(th3, th4, th5));
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
     * The test checks the two-way aggregation mapped relation with one side is optional sdk functionality work well on a transfer object.
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
    @TestCase("TwoWayAssAggRelationOneSideIsOptionalOnTransferDaoFunctionality")
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
    public void testTwoWayAssAggRelationOneSideIsOptionalOnTransferDaoFunctionality() {
        /*
        optional <-> optional
        optional <-> required
        optional <-> collection
         */

        TransferH th1 = transferHDao.create(TransferH.builder().withNameH("TH1").build());
        TransferH th2 = transferHDao.create(TransferH.builder().withNameH("TH2").build());
        // for collection
        TransferH th3 = transferHDao.create(TransferH.builder().withNameH("TH3").build());
        TransferH th4 = transferHDao.create(TransferH.builder().withNameH("TH4").build());
        TransferH th5 = transferHDao.create(TransferH.builder().withNameH("TH5").build());

        TransferG tg1 = transferGDao.create(
                TransferG.builder().withNameG("G1")
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
        tg1 = transferGDao.getById(tg1.identifier()).orElseThrow();
        assertTrue(tg1.getRelationOptionalH().isEmpty());
        assertTrue(transferHDao.queryRelationGForOptionalH(th1).isEmpty());
        assertTrue(entityGDao.queryRelationOptionalH(g1).isEmpty());
        assertTrue(entityHDao.queryRelationGForOptionalH(h1).isEmpty());

        transferGDao.setRelationOptionalH(tg1, th1);
        tg1 = transferGDao.getById(tg1.identifier()).orElseThrow();
        assertTrue(tg1.getRelationOptionalH().isPresent());
        assertTrue(transferHDao.queryRelationGForOptionalH(th1).isPresent());
        assertTrue(entityGDao.queryRelationOptionalH(g1).isPresent());
        assertTrue(entityHDao.queryRelationGForOptionalH(h1).isPresent());

        transferHDao.unsetRelationGForOptionalH(th1);
        tg1 = transferGDao.getById(tg1.identifier()).orElseThrow();
        assertTrue(tg1.getRelationOptionalH().isEmpty());
        assertTrue(transferHDao.queryRelationGForOptionalH(th1).isEmpty());
        assertTrue(entityGDao.queryRelationOptionalH(g1).isEmpty());
        assertTrue(entityHDao.queryRelationGForOptionalH(h1).isEmpty());

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> transferHDao.unsetRelationGForRequiredH(th2));
        assertTrue(thrown.getMessage().contains("There is reference remove which let referrer violate mandatory constraint"));

        // collection unset
        transferHDao.unsetRelationGForCollectionH(th3);
        tg1 = transferGDao.getById(tg1.identifier()).orElseThrow();
        assertTrue(transferHDao.queryRelationGForCollectionH(th3).isEmpty());
        assertEquals(2, tg1.getRelationCollectionH().size());
        assertThat(tg1.getRelationCollectionH(), containsInAnyOrder(th4, th5));
        assertTrue(entityHDao.queryRelationGForCollectionH(h3).isEmpty());
        assertEquals(2, entityGDao.queryRelationCollectionH(g1).count());
        assertThat(entityGDao.queryRelationCollectionH(g1).selectList(), containsInAnyOrder(h4, h5));

        // set
        TransferH th6 = transferHDao.create(TransferH.builder().withNameH("TH6").build());
        EntityH h6 = entityHDao.getById(th6.adaptTo(EntityHIdentifier.class)).orElseThrow();

        TransferG tg2 = transferGDao.create(
                TransferG.builder().withNameG("G2")
                        .withRelationRequiredH(th6)
                        .build()
        );
        EntityG g2 = entityGDao.getById(tg2.adaptTo(EntityGIdentifier.class)).orElseThrow();

        transferGDao.setRelationOptionalH(tg2, th1);
        assertEquals(th1.identifier(), transferGDao.queryRelationOptionalH(tg2).orElseThrow().identifier());
        assertEquals(tg2.identifier(), transferHDao.queryRelationGForOptionalH(th1).orElseThrow().identifier());
        assertEquals(h1.identifier(), entityGDao.queryRelationOptionalH(g2).orElseThrow().identifier());
        assertEquals(g2.identifier(), entityHDao.queryRelationGForOptionalH(h1).orElseThrow().identifier());

        TransferH th7 = transferHDao.create(TransferH.builder().withNameH("TH7").build());
        EntityH h7 = entityHDao.getById(th7.adaptTo(EntityHIdentifier.class)).orElseThrow();

        transferGDao.setRelationOptionalH(tg2, th7);
        assertEquals(th7.identifier(), transferGDao.queryRelationOptionalH(tg2).orElseThrow().identifier());
        assertEquals(tg2.identifier(), transferHDao.queryRelationGForOptionalH(th7).orElseThrow().identifier());
        assertEquals(h7.identifier(), entityGDao.queryRelationOptionalH(g2).orElseThrow().identifier());
        assertEquals(g2.identifier(), entityHDao.queryRelationGForOptionalH(h7).orElseThrow().identifier());

        transferHDao.setRelationGForOptionalH(th7, tg1);
        tg1 = transferGDao.getById(tg1.identifier()).orElseThrow();
        assertEquals(th7.identifier(), tg1.getRelationOptionalH().orElseThrow().identifier());
        assertEquals(tg1.identifier(), transferHDao.queryRelationGForOptionalH(th7).orElseThrow().identifier());
        assertEquals(h7.identifier(), entityGDao.queryRelationOptionalH(g1).orElseThrow().identifier());
        assertEquals(g1.identifier(), entityHDao.queryRelationGForOptionalH(h7).orElseThrow().identifier());

        TransferH th8 = transferHDao.create(TransferH.builder().withNameH("TH8").build());
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

        TransferH th9 = transferHDao.create(TransferH.builder().withNameH("TH9").build());
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
     * The test checks the two-way aggregation mapped relation with one side is required to be created well on a transfer object.
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
    @TestCase("TwoWayAssAggRelationOneSideIsRequiredOnTransferCreate")
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
    public void testTwoWayAssAggRelationOneSideIsRequiredOnTransferCreate() {
        /*
        required <-> optional
        required <-> collection
         */

        TransferI ti1 = transferIDao.create(TransferI.builder().withNameI("TI1").build());
        TransferI ti2 = transferIDao.create(TransferI.builder().withNameI("TI2").build());
        TransferI tiCollect = transferIDao.create(TransferI.builder().withNameI("TICollect").build());

        TransferJ tj1 = transferJDao.create(
                TransferJ.builder().withNameJ("J1")
                        .build(),
                TransferJAttachedRelationsForCreate.builder()
                        .withRelationRequiredIForOptionalJ(ti1)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        TransferJ tj2 = transferJDao.create(
                TransferJ.builder().withNameJ("J2")
                        .build(),
                TransferJAttachedRelationsForCreate.builder()
                        .withRelationRequiredIForOptionalJ(ti2)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        //refresh ti1,ti2,tiCollect

        ti1 = transferIDao.getById(ti1.identifier()).orElseThrow();
        ti2 = transferIDao.getById(ti2.identifier()).orElseThrow();
        tiCollect = transferIDao.getById(tiCollect.identifier()).orElseThrow();

        // check the relation is good in both sides
        // ti side
        assertEquals(tj1.identifier(), ti1.getRelationOptionalJ().orElseThrow().identifier());
        assertEquals(tj2.identifier(), ti2.getRelationOptionalJ().orElseThrow().identifier());
        assertThat(tiCollect.getRelationCollectionJ(), containsInAnyOrder(tj1, tj2));

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
     * The test checks the two-way aggregation mapped relation with one side is required sdk functionality work well on a transfer object.
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
    @TestCase("TwoWayAssAggRelationOneSideIsRequiredOnTransferDaoFunctionality")
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
    public void testTwoWayAssAggRelationOneSideIsRequiredDaoFunctionality() {

        /*
        required <-> optional
        required <-> collection
         */

        TransferI ti1 = transferIDao.create(TransferI.builder().withNameI("TI1").build());
        TransferI ti2 = transferIDao.create(TransferI.builder().withNameI("TI2").build());
        TransferI tiCollect = transferIDao.create(TransferI.builder().withNameI("TICollect").build());

        TransferJ tj1 = transferJDao.create(
                TransferJ.builder().withNameJ("J1")
                        .build(),
                TransferJAttachedRelationsForCreate.builder()
                        .withRelationRequiredIForOptionalJ(ti1)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        TransferJ tj2 = transferJDao.create(
                TransferJ.builder().withNameJ("J2")
                        .build(),
                TransferJAttachedRelationsForCreate.builder()
                        .withRelationRequiredIForOptionalJ(ti2)
                        .withRelationRequiredIorCollectionJ(tiCollect)
                        .build()
        );

        //refresh ti1,ti2,tiCollect

        ti1 = transferIDao.getById(ti1.identifier()).orElseThrow();
        ti2 = transferIDao.getById(ti2.identifier()).orElseThrow();
        tiCollect = transferIDao.getById(tiCollect.identifier()).orElseThrow();

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

        ti1 = transferIDao.getById(ti1.identifier()).orElseThrow();
        ti2 = transferIDao.getById(ti2.identifier()).orElseThrow();
        tiCollect = transferIDao.getById(tiCollect.identifier()).orElseThrow();

        assertEquals(Optional.of("CT1"), ti1.getRelationOptionalJ().orElseThrow().getNameJ());
        assertEquals(Optional.of("CT2"), ti2.getRelationOptionalJ().orElseThrow().getNameJ());
        assertThat(tiCollect.getRelationCollectionJ().stream().map(t -> t.getNameJ()).filter(Optional::isPresent).map(Optional::get).toList(), containsInAnyOrder("CT1", "CT2"));

        assertEquals(Optional.of("CT1"), entityIDao.queryRelationOptionalJ(i1).orElseThrow().getNameJ());
        assertEquals(Optional.of("CT2"), entityIDao.queryRelationOptionalJ(i2).orElseThrow().getNameJ());
        assertThat(entityIDao.queryRelationCollectionJ(iCollect).selectList().stream().map(t -> t.getNameJ()).filter(Optional::isPresent).map(Optional::get).toList(), containsInAnyOrder("CT1", "CT2"));

        // set another t

        TransferI ti3 = transferIDao.create(TransferI.builder().withNameI("TI3").build());
        EntityI i3 = entityIDao.getById(ti3.adaptTo(EntityIIdentifier.class)).orElseThrow();

        transferJDao.setRelationRequiredIForOptionalJ(tj1, ti3);
        ti3 = transferIDao.getById(ti3.identifier()).orElseThrow();
        ti1 = transferIDao.getById(ti1.identifier()).orElseThrow();

        assertEquals(ti3.identifier(), transferJDao.queryRelationRequiredIForOptionalJ(tj1).identifier());
        assertTrue(ti1.getRelationOptionalJ().isEmpty());
        assertEquals(i3.identifier(), entityJDao.queryRelationRequiredIForOptionalJ(j1).identifier());
        assertTrue(entityIDao.queryRelationOptionalJ(i1).isEmpty());

        // set with the other side error

        TransferJ refertj1 = tj1;
        TransferI referti1 = ti1;
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> transferIDao.setRelationOptionalJ(referti1, refertj1));
        assertTrue(thrown.getMessage().contains("There is reference add which let referrer violate mandatory constraint"));

        TransferI referti3 = ti3;
        IllegalStateException thrown1 = assertThrows(IllegalStateException.class, () -> transferIDao.unsetRelationOptionalJ(referti3));
        assertTrue(thrown1.getMessage().contains("There is reference remove which let referrer violate mandatory constraint"));

        // collection

        TransferI ti4 = transferIDao.create(TransferI.builder().withNameI("TI4").build());
        EntityI i4 = entityIDao.getById(ti4.adaptTo(EntityIIdentifier.class)).orElseThrow();

        transferJDao.setRelationRequiredIorCollectionJ(tj1, ti4);
        assertEquals(ti4.identifier(), transferJDao.queryRelationRequiredIorCollectionJ(tj1).identifier());
        assertEquals(1, transferIDao.queryRelationCollectionJ(tiCollect).count());
        assertEquals(i4.identifier(), entityJDao.queryRelationRequiredIorCollectionJ(j1).identifier());
        assertEquals(1, entityIDao.queryRelationCollectionJ(iCollect).count());

        // remove throw error

        TransferJ refertj2 = tj2;
        TransferI refertiCollect = tiCollect;
        IllegalStateException thrown2 = assertThrows(IllegalStateException.class, () -> transferIDao.removeRelationCollectionJ(refertiCollect, List.of(refertj2)));
        assertTrue(thrown2.getMessage().contains("There is reference remove which let referrer violate mandatory constraint"));

        // delete
        TransferI referti2 = ti2;
        IllegalStateException thrown3 = assertThrows(IllegalStateException.class, () -> transferIDao.delete(referti2));
        assertTrue(thrown3.getMessage().contains("There are mandatory references that cannot be removed"));

        transferJDao.delete(tj2);
        assertTrue(transferJDao.getById(tj2.identifier()).isEmpty());
        assertTrue(entityJDao.getById(j2.identifier()).isEmpty());

        transferIDao.delete(ti2);
        assertTrue(transferIDao.getById(ti2.identifier()).isEmpty());
        assertTrue(entityIDao.getById(i2.identifier()).isEmpty());

    }

    /**
     * The test checks the two-way aggregation mapped relation with one side is collection to be created well on a transfer object.
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
    @TestCase("TwoWayAssAggRelationOneSideIsCollectionOnTransferCreate")
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
    public void testTwoWayAssAggRelationOneSideIsCollectionCreate() {
        /*
        collection <-> collection
         */

        TransferK tk1 = transferKDao.create(TransferK.builder().withNameK("TK1").build());
        TransferL tl1 = transferLDao.create(
                TransferL.builder().withNameL("TL1").build(),
                TransferLAttachedRelationsForCreate.builder().withRelationCollectionKForCollectionL(List.of(tk1)).build()

        );
        TransferK tk2 = transferKDao.create(
                TransferK.builder().withNameK("TK2").withRelationCollectionL(List.of(tl1)).build()
        );
        TransferL tl2 = transferLDao.create(
                TransferL.builder().withNameL("TL2").build(),
                TransferLAttachedRelationsForCreate.builder().withRelationCollectionKForCollectionL(List.of(tk2)).build()

        );
        TransferK tk3 = transferKDao.create(
                TransferK.builder().withNameK("TK3").withRelationCollectionL(List.of(tl2)).build()
        );
        TransferL tl3 = transferLDao.create(
                TransferL.builder().withNameL("TL3").build(),
                TransferLAttachedRelationsForCreate.builder().withRelationCollectionKForCollectionL(List.of(tk3)).build()

        );

        assertEquals(3, transferKDao.countAll());
        assertEquals(3, transferLDao.countAll());

        assertThat(transferLDao.queryRelationCollectionKForCollectionL(tl1).selectList().stream().map(TransferK::identifier).toList(), containsInAnyOrder(tk1.identifier(), tk2.identifier()));
        assertThat(transferLDao.queryRelationCollectionKForCollectionL(tl2).selectList().stream().map(TransferK::identifier).toList(), containsInAnyOrder(tk2.identifier(), tk3.identifier()));
        assertThat(transferLDao.queryRelationCollectionKForCollectionL(tl3).selectList().stream().map(TransferK::identifier).toList(), containsInAnyOrder(tk3.identifier()));

        tk1 = transferKDao.getById(tk1.identifier()).orElseThrow();
        tk2 = transferKDao.getById(tk2.identifier()).orElseThrow();
        tk3 = transferKDao.getById(tk3.identifier()).orElseThrow();

        assertThat(tk1.getRelationCollectionL().stream().map(TransferL::identifier).toList(), containsInAnyOrder(tl1.identifier()));
        assertThat(tk2.getRelationCollectionL().stream().map(TransferL::identifier).toList(), containsInAnyOrder(tl1.identifier(), tl2.identifier()));
        assertThat(tk3.getRelationCollectionL().stream().map(TransferL::identifier).toList(), containsInAnyOrder(tl2.identifier(), tl3.identifier()));


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
     * The test checks the two-way aggregation mapped relation with one side is collection sdk functionality work well on a transfer object.
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
    @TestCase("TwoWayAssAggRelationOneSideIsCollectionDaoFunctionality")
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
    public void testTwoWayAssAggRelationOneSideIsCollectionOnTransferDaoFunctionality() {
        /*
        collection <-> collection
         */

        TransferK tk1 = transferKDao.create(TransferK.builder().withNameK("TK1").build());
        TransferL tl1 = transferLDao.create(
                TransferL.builder().withNameL("TL1").build(),
                TransferLAttachedRelationsForCreate.builder().withRelationCollectionKForCollectionL(List.of(tk1)).build()

        );
        TransferK tk2 = transferKDao.create(
                TransferK.builder().withNameK("TK2").withRelationCollectionL(List.of(tl1)).build()
        );
        TransferL tl2 = transferLDao.create(
                TransferL.builder().withNameL("TL2").build(),
                TransferLAttachedRelationsForCreate.builder().withRelationCollectionKForCollectionL(List.of(tk2)).build()

        );
        TransferK tk3 = transferKDao.create(
                TransferK.builder().withNameK("TK3").withRelationCollectionL(List.of(tl2)).build()
        );
        TransferL tl3 = transferLDao.create(
                TransferL.builder().withNameL("TL3").build(),
                TransferLAttachedRelationsForCreate.builder().withRelationCollectionKForCollectionL(List.of(tk3)).build()

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

        tk1 = transferKDao.getById(tk1.identifier()).orElseThrow();
        tk2 = transferKDao.getById(tk2.identifier()).orElseThrow();
        tk3 = transferKDao.getById(tk3.identifier()).orElseThrow();


        assertTrue(tk1.getRelationCollectionL().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());
        assertTrue(tk2.getRelationCollectionL().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());

        assertTrue(entityKDao.queryRelationCollectionL(k1).selectList().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());
        assertTrue(entityKDao.queryRelationCollectionL(k2).selectList().stream().map(e -> e.getNameL()).filter(Optional::isPresent).map(Optional::get).filter(s -> "CTL1".equals(s)).findAny().isPresent());


        //add

        transferLDao.addRelationCollectionKForCollectionL(tl1, List.of(tk3));
        tk1 = transferKDao.getById(tk1.identifier()).orElseThrow();
        tk2 = transferKDao.getById(tk2.identifier()).orElseThrow();
        tk3 = transferKDao.getById(tk3.identifier()).orElseThrow();
        assertEquals(3, transferLDao.queryRelationCollectionKForCollectionL(tl1).count());
        assertEquals(3, entityLDao.queryRelationCollectionKForCollectionL(l1).count());
        assertEquals(3, tk3.getRelationCollectionL().size());
        assertEquals(3, entityKDao.queryRelationCollectionL(k3).count());

        //remove

        transferLDao.removeRelationCollectionKForCollectionL(tl1, List.of(tk3));
        tk3 = transferKDao.getById(tk3.identifier()).orElseThrow();
        assertEquals(2, transferLDao.queryRelationCollectionKForCollectionL(tl1).count());
        assertEquals(2, entityLDao.queryRelationCollectionKForCollectionL(l1).count());
        assertEquals(2, tk3.getRelationCollectionL().size());
        assertEquals(2, entityKDao.queryRelationCollectionL(k3).count());

        //delete

        transferLDao.delete(tl1);
        tk1 = transferKDao.getById(tk1.identifier()).orElseThrow();
        tk2 = transferKDao.getById(tk2.identifier()).orElseThrow();
        assertTrue(transferLDao.getById(tl1.identifier()).isEmpty());
        assertEquals(0, tk1.getRelationCollectionL().size());
        assertEquals(1, tk2.getRelationCollectionL().size());
        assertTrue(entityLDao.getById(l1.identifier()).isEmpty());
        assertEquals(0, entityKDao.queryRelationCollectionL(k1).count());
        assertEquals(1, entityKDao.queryRelationCollectionL(k2).count());

    }
    
    /**
     * The test checks the navigation between relations work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAssociationAggregation.jsl
     *
     *
     */
    @Test
    @TestCase("NavigationInRelationOnTransfer")
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
            "REQ-ENT-005",
            "REQ-SRV-001"
    })
    public void testNavigationInRelationOnTransfer() {

        TransferO to1 = transferODao.create(TransferO.builder().build());
        TransferO to2 = transferODao.create(TransferO.builder().build());
        TransferO to3 = transferODao.create(TransferO.builder().build());

        TransferN transfeN = transferNDao.create(TransferN.builder().withRelationOonN(List.of(to1, to2, to3)).build());

        TransferM transferG = transferMDao.create(TransferM.builder().withRelationNonM(transfeN).build());

        assertEquals(transfeN.identifier(), transferG.getRelationNonM().orElseThrow().identifier());
        assertThat(transferG.getRelationNonM().orElseThrow().getRelationOonN(), containsInAnyOrder(to1, to2, to3));


    }

    @Test
    void testDeepCopyCreate() {

        TK transferK1 = tkDao.create(TK.builder().withStringK("K1").build());
        TK transferK2 = tkDao.create(TK.builder().withStringK("K2").build());
        TJ transferJ = tjDao.create(TJ.builder().withStringJ("J1").withMultipleKonI(List.of(transferK1, transferK2)).build());
        TH transferH = thDao.create(TH.builder().withSingleRequiredJonH(transferJ).withStringH("H1").build());

        assertNotEquals(transferJ.identifier().getIdentifier() ,transferH.getSingleRequiredJonH().identifier().getIdentifier());
        assertEquals("H1", transferH.getStringH().orElseThrow());

        List<TK> ksTransfer = transferH.getSingleRequiredJonH().getMultipleKonI();

        TK testK1Transfer = ksTransfer.stream().filter(d -> d.getStringK().orElseThrow().equals("K1")).findFirst().orElseThrow();
        TK testK2Transfer = ksTransfer.stream().filter(d -> d.getStringK().orElseThrow().equals("K2")).findFirst().orElseThrow();
        assertNotEquals(transferK1.identifier().getIdentifier(), testK1Transfer.identifier().getIdentifier());
        assertEquals(transferK1.getStringK().orElseThrow(), testK1Transfer.getStringK().orElseThrow());
        assertNotEquals(transferK2.identifier().getIdentifier(), testK2Transfer.identifier().getIdentifier());
        assertEquals(transferK2.getStringK().orElseThrow(), testK2Transfer.getStringK().orElseThrow());

    }

    @Test
    void testDeepCopyUpdate() {
        TH h2Transfer = thDao.create(TH.builder().withSingleRequiredJonH(TJ.builder().build()).build());
        assertEquals(Optional.empty(), h2Transfer.getSingleJonH());
        assertEquals(0, h2Transfer.getCollectionJonH().size());

        assertEquals(1, tjDao.countAll());
        assertEquals(0, tkDao.countAll());

        h2Transfer.setSingleJonH(TJ.builder().withStringJ("J1").build());
        h2Transfer.setCollectionJonH(List.of(TJ.builder().withStringJ("J2").withMultipleKonI(List.of(TK.builder().withStringK("K").build())).build()));

        h2Transfer = thDao.update(h2Transfer);

        assertEquals(3, tjDao.countAll());
        assertEquals(1, tkDao.countAll());
        assertEquals("J1", h2Transfer.getSingleJonH().orElseThrow().getStringJ().orElseThrow());
        assertEquals("J2", h2Transfer.getCollectionJonH().get(0).getStringJ().orElseThrow());
        assertEquals("K", h2Transfer.getCollectionJonH().get(0).getMultipleKonI().get(0).getStringK().orElseThrow());

        TJ j3Transfer = tjDao.create(TJ.builder().withStringJ("J3").build());
        TJ j4Transfer = tjDao.create(TJ.builder().withStringJ("J4").withMultipleKonI(List.of(TK.builder().withStringK("K").build())).build());
        TH h3Transfer = thDao.create(TH.builder().withSingleJonH(j3Transfer).withSingleRequiredJonH(j4Transfer).build());

        assertEquals(7, tjDao.countAll());
        assertEquals(3, tkDao.countAll());

        h3Transfer.setSingleJonH(TJ.builder().withStringJ("J3Updated").build());
        h3Transfer.setSingleRequiredJonH(TJ.builder().withStringJ("J4Updated").withMultipleKonI(List.of(TK.builder().withStringK("KUpdated").build())).build());

        h3Transfer = thDao.update(h3Transfer);

        assertEquals(7, tjDao.countAll());
        // TODO: JNG-5213 update does not create new EntityD instance
        //assertEquals(3, tkDao.countAll());
        assertEquals("J3Updated", h3Transfer.getSingleJonH().orElseThrow().getStringJ().orElseThrow());
        assertEquals("J4Updated", h3Transfer.getSingleRequiredJonH().getStringJ().orElseThrow());
        //assertEquals("KUpdated", h3Transfer.getSingleRequiredJonH().getMultipleKonI().get(0).getStringK().orElseThrow());


        TH a4 = thDao.create(TH.builder().withSingleRequiredJonH(TJ.builder().build()).build());
        assertEquals(Optional.empty(), a4.getSingleJonH());
        assertEquals(0, a4.getCollectionJonH().size());

        TJ c5 = tjDao.create(TJ.builder().withStringJ("C5").build());
        TJ c6 = tjDao.create(TJ.builder().withStringJ("C6").withMultipleKonI(List.of(TK.builder().withStringK("D4").build())).build());

        a4.setSingleJonH(c5);
        a4.setCollectionJonH(List.of(c6));
        final TH a5 = thDao.update(a4);

        assertEquals(12, tjDao.countAll());
        //assertEquals(5, tkDao.countAll());

        assertEquals("C5", a5.getSingleJonH().orElseThrow().getStringJ().orElseThrow());
        assertEquals("C6", a5.getCollectionJonH().get(0).getStringJ().orElseThrow());
        assertEquals("D4", a5.getCollectionJonH().get(0).getMultipleKonI().get(0).getStringK().orElseThrow());

        TJ c7 = tjDao.create(TJ.builder().withStringJ("C7").build());
        TJ c8 = tjDao.create(TJ.builder().withStringJ("C8").withMultipleKonI(List.of(TK.builder().withStringK("D5").build())).build());

        a5.setSingleJonH(c7);
        a5.setCollectionJonH(List.of(c8));

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> thDao.update(a5)
        );

    }

}
