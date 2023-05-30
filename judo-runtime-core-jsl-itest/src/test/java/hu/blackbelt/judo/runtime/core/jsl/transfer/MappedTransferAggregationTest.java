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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.transfera.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferaggregation.mappedtransferaggregation.transferb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferAggregationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static  org.junit.jupiter.api.Assertions.*;

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

        // TODO Composition should copy the composition element or not allow to bind an existed element.
        //assertEquals(2,transferBDao.query().execute().size());
        //assertEquals(2,entityBDao.query().execute().size());

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
        //transferADao.createSingleEntityB(transferA, transferBDao.create(TransferB.builder().withNameB("B2").build()));
        //assertTrue(transferA.getSingleEntityB().isPresent());

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



    }



}
