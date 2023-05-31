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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.entitya.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.entityb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.entityc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.entityd.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.transfera.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.transferb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.transferc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.transferd.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferAggregationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static  org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class MappedTransferAggregationTest extends AbstractJslTest {

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


    @Override
    public Module getModelDaoModule() {
        return new MappedTransferAggregationDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferAggregation";
    }

    /**
     * The test checks the single aggregation work well on transfer object.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     * @senario
     *
     *
     *
     */
    @Test
    @TestCase("SingleAggregationBindingOnTransfer")
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
    public void testSingleAggregationBindingOnTransfer() {

        TransferB transferB = transferBDao.create(TransferB.builder().withNameB("B1").build());

        assertEquals(1, transferBDao.query().execute().size());

        TransferA transferA = transferADao.create(TransferA.builder()
                .withSingleEntityB(transferB)
                .build()
        );

        // TODO JNG-4317 Composition should copy the composition element or not allow to bind an existed element.
//        assertEquals(2,transferBDao.query().execute().size());
//        assertEquals(2,entityBDao.query().execute().size());

        // Check transferA contains transferB
        assertEquals(transferB.identifier(),transferA.getSingleEntityB().orElseThrow().identifier());
        assertEquals(
                entityBDao.getById(transferB.adaptTo(EntityBIdentifier.class)).orElseThrow().identifier(),
                entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getSingleEntityB().orElseThrow().identifier()
        );

        // Check transferA cannot bind a different B element
        transferA.setSingleEntityB(transferBDao.create(TransferB.builder().withNameB("B2").build()));
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> transferADao.update(transferA)
        );
        assertTrue(thrown.getMessage().contains("Identifier cannot be different on containment reference element"));
        assertTrue(thrown.getMessage().contains("#singleEntityB"));


        // Check transferA can set to null
        transferA.setSingleEntityB(null);
        transferADao.update(transferA);

        assertTrue(transferA.getSingleEntityB().isEmpty());
        assertTrue(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getSingleEntityB().isEmpty());
        assertTrue(transferBDao.getById(transferB.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB.identifier().adaptTo(EntityBIdentifier.class)).isEmpty());

        // Check transferA cannot bind a new B element
        transferB = transferBDao.create(TransferB.builder().withNameB("B2").build());
        transferA.setSingleEntityB(transferB);

        IllegalStateException thrown1 = assertThrows(
                IllegalStateException.class,
                () -> transferADao.update(transferA)
        );
        assertTrue(thrown1.getMessage().contains("Identifier cannot be set on new association reference element"));
        assertTrue(thrown1.getMessage().contains("#singleEntityB"));

        //TODO JNG-4877 create dao function throw error
//        transferADao.createSingleEntityB(transferA, TransferB.builder().withNameB("B2").build());
//        assertTrue(transferA.getSingleEntityB().isPresent());

    }

    /**
     * The test checks the single required aggregation work well on transfer object.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     * @senario
     *
     *
     *
     */
    @Test
    @TestCase("SingleRequiredAggregationBindingOnTransfer")
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
    public void testSingleRequiredAggregationBindingOnTransfer() {

        TransferD transferD = transferDDao.create(TransferD.builder().withNameD("D1").build());

        assertEquals(1, transferDDao.query().execute().size());

        TransferC transferC = transferCDao.create(TransferC.builder()
                .withSingleRequiredEntityD(transferD)
                .build()
        );

        // TODO JNG-4317 Composition should copy the composition element or not allow to bind an existed element.
//        assertEquals(2,transferDDao.query().execute().size());
//        assertEquals(2,entityDDao.query().execute().size());

        //Try to create without required element

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferCDao.create(TransferC.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEntityD")))
        ));

        //Try to delete the required element

        IllegalStateException thrown1 = assertThrows(
                IllegalStateException.class,
                () -> transferDDao.delete(transferD)
        );

        assertTrue(thrown1.getMessage().contains("There are mandatory references that cannot be removed"));
        assertTrue(thrown1.getMessage().contains("#singleRequiredEntityD"));

        //Create new required element and check the old is deleted

        //TODO JNG-4877 create dao function throw error
//        transferCDao.createSingleRequiredEntityD(transferC, TransferD.builder().build());

//        assertTrue(transferDDao.getById(transferD.identifier()).isEmpty());
//        assertTrue(entityDDao.getById(transferD.adaptTo(EntityDIdentifier.class)).isEmpty());

    }

    /**
     * The test checks the multi aggregation work well on transfer object.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     * @senario
     *
     *
     *
     */
    @Test
    @TestCase("MultiCompositonAggregationBindingOnTransfer")
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
    public void testMultiCompositonAggregationBindingOnTransfer() {

        TransferB transferB1 = transferBDao.create(TransferB.builder().withNameB("B1").build());
        TransferB transferB2 = transferBDao.create(TransferB.builder().withNameB("B2").build());
        TransferB transferB3 = transferBDao.create(TransferB.builder().withNameB("B3").build());

        assertEquals(3, transferBDao.query().execute().size());

        TransferA transferA = transferADao.create(TransferA.builder()
                .withMultiEntityB(List.of(transferB1, transferB2, transferB3))
                .build()
        );

        // TODO JNG-4317 Composition should copy the composition element or not allow to bind an existed element.
//        assertEquals(6, transferBDao.query().execute().size());
//        assertEquals(6, entityBDao.query().execute().size());

        // Check transferA contains transferB
        assertThat(transferA.getMultiEntityB().stream().map(t -> t.identifier()).toList(), containsInAnyOrder( transferB1.identifier(), transferB2.identifier(), transferB3.identifier()));


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

        // TODO JNG-4877
//        transferADao.createMultiEntityB(transferA, List.of());
//        assertEquals(0, transferADao.countMultiEntityB(transferA));

        // Create new List with elements

        // TODO JNG-4877
//        transferADao.createMultiEntityB(transferA, List.of(TransferB.builder().build()));
//        assertEquals(1, transferADao.countMultiEntityB(transferA));

    }

}
