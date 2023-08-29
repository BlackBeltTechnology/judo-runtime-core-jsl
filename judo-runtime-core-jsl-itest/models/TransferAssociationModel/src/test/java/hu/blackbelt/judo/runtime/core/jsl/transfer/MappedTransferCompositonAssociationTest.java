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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitya.EntityAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferAAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferCAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferCompositonAssociationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith({JudoDatasourceByClassExtension.class, JudoRuntimeExtension.class})
public class MappedTransferCompositonAssociationTest {

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


    public Module getModelDaoModule() {
        return new MappedTransferCompositonAssociationDaoModules();
    }

    static public String getModelName() {
        return "MappedTransferCompositonAssociation";
    }

    @BeforeAll
    static public void prepare(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.prepare(getModelName(), datasource);
    }

    @BeforeEach
    protected void init(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.init(getModelDaoModule(),this, datasource);
        fixture.beginTransaction();
    }

    @AfterEach
    protected void tearDown(JudoRuntimeFixture fixture) {
        fixture.tearDown();
    }

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

        TransferB transferB = transferBDao.create(TransferB.builder().withNameB("B1").build());

        assertEquals(1, transferBDao.query().execute().size());

        TransferA transferA = transferADao.create(TransferA.builder()
                .build(),
                TransferAAttachedRelationsForCreate.builder().withSingleEntityB(transferB).build()
        );

        // TODO JNG-4317 Composition should copy the composition element or not allow to bind an existed element.
//        assertEquals(2,transferBDao.query().execute().size());
//        assertEquals(2,entityBDao.query().execute().size());

        // Check transferA contains transferB
        assertEquals(transferB.identifier(), transferADao.querySingleEntityB(transferA).orElseThrow().identifier());
        assertEquals(
                entityBDao.getById(transferB.adaptTo(EntityBIdentifier.class)).orElseThrow().identifier(),
                entityADao.querySingleEntityB((entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow())).orElseThrow().identifier()
        );

        // Check transferA cannot bind a different B element
        TransferA referenceForLambda = transferA;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> transferADao.createSingleEntityB(referenceForLambda, TransferB.builder().withNameB("B2").build())
        );
        assertTrue(thrown.getMessage().contains("Upper cardinality violated"));

        // Check transferA can set to null
        transferBDao.delete(transferB);
        transferA = transferADao.update(transferA);

        assertTrue(transferADao.querySingleEntityB(transferA).isEmpty());
        assertTrue(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getSingleEntityB().isEmpty());
        assertTrue(transferBDao.getById(transferB.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB.identifier().adaptTo(EntityBIdentifier.class)).isEmpty());

        transferADao.createSingleEntityB(transferA, TransferB.builder().withNameB("B3").build());
        assertTrue(transferADao.querySingleEntityB(transferA).isPresent());
        assertEquals(Optional.of("B3"), transferADao.querySingleEntityB(transferA).orElseThrow().getNameB());

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

        TransferD transferD = transferDDao.create(TransferD.builder().withNameD("D1").build());

        assertEquals(1, transferDDao.query().execute().size());

        TransferC transferC = transferCDao.create(TransferC.builder().build(),
                TransferCAttachedRelationsForCreate.builder().withSingleRequiredEntityD(transferD).build()
        );

        // TODO JNG-4317 Composition should copy the composition element or not allow to bind an existed element.
//        assertEquals(2,transferDDao.query().execute().size());
//        assertEquals(2,entityDDao.query().execute().size());

        //Try to create without required element
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> transferCDao.create(TransferC.builder().build())
        );

        assertTrue(thrown.getMessage().contains("There is missing mandatory attribute/reference on"));
        assertTrue(thrown.getMessage().contains("singleRequiredEntityD"));

        //Try to delete the required element

        IllegalStateException thrown1 = assertThrows(
                IllegalStateException.class,
                () -> transferDDao.delete(transferD)
        );

        assertTrue(thrown1.getMessage().contains("There are mandatory references that cannot be removed"));
        assertTrue(thrown1.getMessage().contains("#singleRequiredEntityD"));

        //Create new required element and check the old is deleted

        IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> transferCDao.createSingleRequiredEntityD(transferC, TransferD.builder().build())
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

        TransferB transferB1 = transferBDao.create(TransferB.builder().withNameB("B1").build());
        TransferB transferB2 = transferBDao.create(TransferB.builder().withNameB("B2").build());
        TransferB transferB3 = transferBDao.create(TransferB.builder().withNameB("B3").build());

        assertEquals(3, transferBDao.query().execute().size());

        TransferA transferA = transferADao.create(TransferA.builder().build(),
                TransferAAttachedRelationsForCreate.builder().withMultiEntityB(List.of(transferB1, transferB2, transferB3)).build()
        );

        // TODO JNG-4317 Composition should copy the composition element or not allow to bind an existed element.
//        assertEquals(6, transferBDao.query().execute().size());
//        assertEquals(6, entityBDao.query().execute().size());

        // Check transferA contains transferB
        assertThat(transferADao.queryMultiEntityB(transferA).execute().stream().map(t -> t.identifier()).toList(), containsInAnyOrder( transferB1.identifier(), transferB2.identifier(), transferB3.identifier()));

        // Check the entity level
        EntityB entityB1 = entityBDao.getById(transferB1.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB2 = entityBDao.getById(transferB2.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB3 = entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertThat(
                entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getMultiEntityB().stream().map(t -> t.identifier()).toList(),
                containsInAnyOrder( entityB1.identifier(), entityB2.identifier(), entityB3.identifier())
        );

        //Delete one element
        transferBDao.delete(transferB3);

        assertTrue(transferBDao.getById(transferB3.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).isEmpty());

        transferA = transferADao.getById(transferA.identifier()).orElseThrow();

        assertEquals(2, transferADao.countMultiEntityB(transferA));

        // Add new List empty
        transferADao.createMultiEntityB(transferA, List.of());
        assertEquals(2, transferADao.countMultiEntityB(transferA));

        // Create new List with elements
        transferADao.createMultiEntityB(transferA, List.of(TransferB.builder().build()));
        assertEquals(3, transferADao.countMultiEntityB(transferA));


    }
}
