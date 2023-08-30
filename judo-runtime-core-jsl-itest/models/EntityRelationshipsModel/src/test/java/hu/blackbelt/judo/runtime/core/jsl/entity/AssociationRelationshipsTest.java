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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityf.EntityFIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AssociationRelationshipsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeJudoDatasourceByClassExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AssociationRelationshipsTest {

    @RegisterExtension
    static JudoRuntimeJudoDatasourceByClassExtension runtimeExtension = new JudoRuntimeJudoDatasourceByClassExtension("AssociationRelationships", new AssociationRelationshipsDaoModules());

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
    protected void init() {

        entityD = entityDDao.create(EntityD.builder()
                .build());
        entityC = entityCDao.create(EntityC.builder()
                .build());
        entityA = createA(entityC, List.of(entityD));
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

        assertEquals(entityC.identifier().getIdentifier(), entityADao.querySingleConA(entityA).orElseThrow().identifier().getIdentifier());

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

    /**
     * This test checks the collection sdk methods with one instance (create, add, remove) work well.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AssociationRelationships.jsl
     *
     */
    @Test
    @TestCase("testCreateAddRemoveMultipleRelationsMethodsWithOneElements")
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003"
    })
    public void testCreateAddRemoveMultipleRelationsMethodsWithOneElements() {

        EntityF f1 = entityFDao.create(EntityF.builder().build());
        EntityF f2 = entityFDao.create(EntityF.builder().build());

        // create method's with one instance

        EntityE entityE = entityEDao.create(EntityE.builder().build());

        assertEquals(0, entityEDao.queryMultipleFOnE(entityE).count());

        EntityF f3 = entityEDao.createMultipleFOnE(entityE, EntityF.builder().build());

        assertEquals(1, entityEDao.queryMultipleFOnE(entityE).count());
        assertThat(ListOfMultipleFOnEIds(entityE), hasItems(f3.identifier()));

        EntityF f4 = entityEDao.createMultipleFOnE(entityE, f1);

        assertNotEquals(f4.identifier(), f1.identifier());
        assertEquals(2, entityEDao.queryMultipleFOnE(entityE).count());
        assertThat(ListOfMultipleFOnEIds(entityE), hasItems(f3.identifier(), f4.identifier()));

        // add method's with one instance

        entityEDao.addMultipleFOnE(entityE, f1);
        entityEDao.addMultipleFOnE(entityE, f1); // try to add twice

        assertEquals(3, entityEDao.queryMultipleFOnE(entityE).count());
        assertThat(ListOfMultipleFOnEIds(entityE), hasItems(f1.identifier(), f3.identifier(), f4.identifier()));

        // remove method's with one instance

        entityEDao.removeMultipleFOnE(entityE, f2); // remove one that not part of the relation

        assertEquals(3, entityEDao.queryMultipleFOnE(entityE).count());
        assertThat(ListOfMultipleFOnEIds(entityE), hasItems(f1.identifier(), f3.identifier(), f4.identifier()));

        entityEDao.removeMultipleFOnE(entityE, f3);

        assertEquals(2, entityEDao.queryMultipleFOnE(entityE).count());
        assertThat(ListOfMultipleFOnEIds(entityE), hasItems(f1.identifier(), f4.identifier()));

        entityEDao.removeMultipleFOnE(entityE, f4);

        assertEquals(1, entityEDao.queryMultipleFOnE(entityE).count());
        assertThat(ListOfMultipleFOnEIds(entityE), hasItems(f1.identifier()));

    }

    private  List<EntityFIdentifier> ListOfMultipleFOnEIds(EntityE e){
        return entityEDao.queryMultipleFOnE(e).execute().stream().map(EntityF::identifier).toList();
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

        EntityC entityC2 = entityADao.querySingleRequiredConA(startA.orElseThrow());

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

        assertTrue(thrown.getMessage().contains("There are mandatory references that cannot be removed"));
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
        assertEquals(entityE.identifier().getIdentifier(), entityFDao.querySingleEAdded(entityF1).orElseThrow().identifier().getIdentifier());
        assertEquals(entityE.identifier().getIdentifier(), entityFDao.querySingleEAdded(entityF2).orElseThrow().identifier().getIdentifier());
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
