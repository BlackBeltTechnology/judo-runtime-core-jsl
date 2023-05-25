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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transfera.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferassociation.mappedtransferassociation.transferd.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferAssociationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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


    @Override
    public Module getModelDaoModule() {
        return new MappedTransferAssociationDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferAssociation";
    }

    @Test
    @TestCase("")
    @Requirement(reqs = {
            ""
    })
    public void testSetUnsetSingleRelationOnTransfer() {

        TransferB tb1 = transferBDao.create(TransferB.builder().build());
        TransferB tb2 = transferBDao.create(TransferB.builder().build());

        TransferA transferA = transferADao.create(TransferA
                .builder()
                .withRelationBonA(tb1)
//                .withRelationConBTwoWay(b2)
                .build());

        assertEquals(tb1.identifier(),transferA.getRelationBonA().orElseThrow().identifier());

        EntityA a = entityADao.getById(transferA.identifier().adaptTo(EntityAIdentifier.class)).orElseThrow();
        EntityB b1 = entityBDao.getById(tb1.identifier().adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals(b1.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferA.setRelationBonA(tb2);
        transferA = transferADao.update(transferA);

        assertEquals(tb2.identifier(),transferA.getRelationBonA().orElseThrow().identifier());

        EntityB b2 = entityBDao.getById(tb2.identifier().adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals(b2.identifier(), entityADao.queryRelationBonA(a).orElseThrow().identifier());

        transferA.setRelationBonA(null);
        transferA = transferADao.update(transferA);

        assertTrue(transferA.getRelationBonA().isEmpty());
        assertTrue(entityADao.queryRelationBonA(a).isEmpty());

    }

    @Test
    @TestCase("")
    @Requirement(reqs = {
            ""
    })
    public void testSetUnsetSingleRequeredRelationOnTransfer() {

        TransferD td1 = transferDDao.create(TransferD.builder().build());
        TransferD td2 = transferDDao.create(TransferD.builder().build());

        TransferC transferC = transferCDao.create(TransferC
                .builder()
                .withRelationDonC(td1)
//                .withRelationConBTwoWay(b2)
                .build());

        assertEquals(td1.identifier(),transferC.getRelationDonC().identifier());

        EntityC c = entityCDao.getById(transferC.identifier().adaptTo(EntityCIdentifier.class)).orElseThrow();
        EntityD d1 = entityDDao.getById(td1.identifier().adaptTo(EntityDIdentifier.class)).orElseThrow();

        assertEquals(d1.identifier(), entityCDao.queryRelationDonC(c).identifier());

        transferC.setRelationDonC(td2);
        transferC = transferCDao.update(transferC);

        assertEquals(td2.identifier(),transferC.getRelationDonC().identifier());

        EntityD d2 = entityDDao.getById(td2.identifier().adaptTo(EntityDIdentifier.class)).orElseThrow();

        assertEquals(d2.identifier(), entityCDao.queryRelationDonC(c).identifier());

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> transferDDao.delete(td2)
        );

        assertTrue(thrown.getMessage().contains("There are mandatory references that cannot be removed"));
        assertTrue(thrown.getMessage().contains("#relationDonC"));

        ValidationException thrown1 = assertThrows(
                ValidationException.class,
                () -> transferCDao.create(TransferC.builder().build())
        );

        assertThat(thrown1.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("relationDonC")))
        ));

    }

}
