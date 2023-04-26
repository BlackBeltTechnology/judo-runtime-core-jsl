package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitya.EntityAAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityc.EntityC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityd.EntityDAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitye.EntityE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitye.EntityEAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityf.EntityF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AssociationRelationshipsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AssociationRelationshipsTest extends AbstractJslTest {
    @Inject
    EntityADao entityADao;

    @Inject
    EntityCDao entityCDao;

    @Inject
    EntityDDao entityDDao;

    @Inject
    EntityEDao entityEDao;

    @Inject
    EntityFDao entityFDao;

    EntityD entityD;
    EntityC entityC;
    EntityA entityA;

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);
        entityD = entityDDao.create(EntityD.builder()
                .build());
        entityC = entityCDao.create(EntityC.builder()
                .build());
        entityA = createA(entityC, List.of(entityD));
    }

    @Override
    public Module getModelDaoModule() {
        return new AssociationRelationshipsDaoModules();
    }

    @Override
    public String getModelName() {
        return "AssociationRelationships";
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-012"
    })
    public void testSetUnsetSingleRelation() {
        assertEquals(Optional.empty(), entityADao.querySingleConA(entityA));

        entityADao.setSingleConA(entityA, entityC);

        assertEquals(Optional.of(entityC), entityADao.querySingleConA(entityA));

        entityADao.unsetSingleConA(entityA);

        assertEquals(Optional.empty(), entityADao.querySingleConA(entityA));
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-012"
    })
    public void testAddRemoveMultipleRelations() {
        assertEquals(List.of(), entityCDao.queryMultipleAonB(entityC).execute());

        EntityA entityA2 = createA(entityC, List.of(entityD));

        entityCDao.addMultipleAonB(entityC, List.of(entityA, entityA2));

        assertEquals(2, entityCDao.queryMultipleAonB(entityC).execute().size());

        entityCDao.removeMultipleAonB(entityC, List.of(entityA));

        assertEquals(List.of(entityA2), entityCDao.queryMultipleAonB(entityC).execute());
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-012"
    })
    public void testRequiredRelationEnforced() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> entityADao.create(EntityA.builder().build())
        );

        assertTrue(thrown.getMessage().contains("missing mandatory attribute"));
        assertTrue(thrown.getMessage().contains("name: singleRequiredConA"));

        List<EntityA> list = entityADao.query().execute();

        assertEquals(1, list.size()); // we are expecting 1, because the beforeEach() setup already created 1 element
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-006",
            "REQ-ENT-012"
    })
    public void testTraverse() {
        EntityD entityD2 = entityDDao.create(EntityD.builder().build());

        entityCDao.addMultipleDonC(entityC, List.of(entityD2));

        Optional<EntityA> startA = entityADao.getById(entityA.identifier());

        EntityC entityC2 = entityADao.querySingleRequiredConA(startA.get());

        assertEquals(entityC, entityC2);

        List<EntityA> entityA2 = entityCDao.queryTwoWayMultipleAonC(entityC2).execute();

        assertEquals(1, entityA2.size());
        assertEquals(entityA, entityA2.get(0));
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-012"
    })
    public void testDeletingRequiredRelationThrowsException() {
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> entityCDao.delete(entityC)
        );

        assertTrue(thrown.getMessage().contains("There is mandatory references which is not removable"));
        assertTrue(thrown.getMessage().contains("#singleRequiredConA"));
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-012"
    })
    public void testDeletingRelatedElementUnsetsRelationship() {
        entityDDao.delete(entityD);

        List<EntityD> ds = entityADao.queryMultipleDonA(entityA).execute();

        assertEquals(0, ds.size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-006",
            "REQ-ENT-012"
    })
    public void testOppositeAdd() {
        EntityF entityF1 = entityFDao.create(EntityF.builder().build());
        EntityF entityF2 = entityFDao.create(EntityF.builder().build());
        EntityE entityE = entityEDao.create(EntityE.builder()
                .build(),
                EntityEAttachedRelationsForCreate
                .builder()
                .withMultipleFOnE(List.of(entityF1, entityF2))
                .build()
                );

        assertEquals(2, entityEDao.queryMultipleFOnE(entityE).execute().size());
        assertEquals(Optional.of(entityE), entityFDao.querySingleEAdded(entityF1));
        assertEquals(Optional.of(entityE), entityFDao.querySingleEAdded(entityF2));
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-006",
            "REQ-ENT-012"
    })
    public void testNavigateTwoWay() {
        EntityA entityA1 = createA(entityC, List.of());
        EntityA entityA2 = createA(entityC, List.of());
        EntityA entityA3 = createA(entityC, List.of());
        EntityD entityD1 = entityDDao.create(EntityD.builder().build(), EntityDAttachedRelationsForCreate.builder()
                .withMultipleAonD(List.of(entityA1, entityA2, entityA3))
                .build());

        assertEquals(3, entityDDao.queryMultipleAonD(entityD1).execute().size());
        assertEquals(1, entityADao.queryMultipleDonA(entityA1).execute().size());
    }

    private EntityA createA(EntityC entityC, List<EntityD> entityDs) {
        return entityADao.create(EntityA.builder().build(),
                EntityAAttachedRelationsForCreate.builder()
                .withMultipleDonA(entityDs)
                .withSingleRequiredConA(entityC)
                .build());
    }
}