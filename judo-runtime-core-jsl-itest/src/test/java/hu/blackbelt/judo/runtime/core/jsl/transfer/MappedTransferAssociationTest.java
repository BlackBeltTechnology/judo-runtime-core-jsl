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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entitya.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entityb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entityc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entityd.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entitye.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entityf.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entityg.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entityh.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.entityj.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transfera.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferd.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transfere.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferf.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferg.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferh.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferj.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferAssociationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferAssociationTest extends AbstractJslTest {

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


    @Override
    public Module getModelDaoModule() {
        return new MappedTransferAssociationDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferAssociation";
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
    @TestCase("SingleRelationOnTransfer")
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
    public void testSingleRelationOnTransfer() {

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
    @TestCase("SingleRequiredRelationOnTransfer")
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
    public void testSingleRequiredRelationOnTransfer() {

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
    @TestCase("MultiRelationOnTransfer")
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
    public void testMultiRelationOnTransfer() {

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
        List<EntityF> listOfEntityF = entityFDao.query().execute();

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

    /**
     * The test checks the navigation between relations work well on transfer object.
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

        TransferJ tj1 = transferJDao.create(TransferJ.builder().build());
        TransferJ tj2 = transferJDao.create(TransferJ.builder().build());
        TransferJ tj3 = transferJDao.create(TransferJ.builder().build());

        TransferH transferH = transferHDao.create(TransferH.builder().withRelationJonH(List.of(tj1, tj2, tj3)).build());

        TransferG transferG = transferGDao.create(TransferG.builder().withRelationHonG(transferH).build());

        assertEquals(transferH.identifier(), transferG.getRelationHonG().orElseThrow().identifier());
        assertThat(transferG.getRelationHonG().orElseThrow().getRelationJonH(), containsInAnyOrder(tj1, tj2, tj3));

    }

}
