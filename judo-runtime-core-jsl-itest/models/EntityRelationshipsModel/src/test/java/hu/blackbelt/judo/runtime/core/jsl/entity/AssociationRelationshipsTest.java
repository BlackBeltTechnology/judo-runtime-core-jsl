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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.a.AForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.a.AMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.b.BForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.b.BMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.c.CMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.collector.Collector;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.collector.CollectorDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.collector.CollectorForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.collectorfornotsupported.CollectorForNotSupportedDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.d.DForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.e.E;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.e.EDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.e.EForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitya.EntityAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityc.EntityC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityc.EntityCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityc.EntityCMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityd.EntityDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitye.EntityE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitye.EntityEForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityf.EntityF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityf.EntityFForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entityf.EntityFIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.f.F;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.f.FDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.f.FForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AssociationRelationshipsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.structured.map.proxy.MapHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AssociationRelationshipsTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("AssociationRelationships", new AssociationRelationshipsDaoModules());

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

        entityD = entityDDao.create(EntityDForCreate.builder()
                .build());
        entityC = entityCDao.create(EntityCForCreate.builder()
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

        assertEquals(entityC.identifier().getIdentifier(), entityADao.querySingleConA((UUID) entityA.identifier().getIdentifier(), EntityCMask.entityCMask()).orElseThrow().identifier().getIdentifier());

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
        assertEquals(List.of(), entityCDao.queryMultipleAonB(entityC).selectList());

        EntityA entityA2 = createA(entityC, List.of(entityD));

        entityCDao.addMultipleAonB(entityC, List.of(entityA, entityA2));

        assertEquals(2, entityCDao.queryMultipleAonB(entityC).selectList().size());

        entityCDao.removeMultipleAonB(entityC, List.of(entityA));

        assertEquals(List.of(entityA2), entityCDao.queryMultipleAonB(entityC).selectList());
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

        EntityF f1 = entityFDao.create(EntityFForCreate.builder().build());
        EntityF f2 = entityFDao.create(EntityFForCreate.builder().build());

        // create method's with one instance

        EntityE entityE = entityEDao.create(EntityEForCreate.builder().build());

        assertEquals(0, entityEDao.queryMultipleFOnE(entityE).count());

        EntityF f3 = entityEDao.createMultipleFOnE(entityE, EntityFForCreate.builder().build());

        assertEquals(1, entityEDao.queryMultipleFOnE(entityE).count());
        assertThat(ListOfMultipleFOnEIds(entityE), hasItems(f3.identifier()));

        EntityF f4 = entityEDao.createMultipleFOnE(entityE,  EntityFForCreate.builderFrom(f1).build());

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
        return entityEDao.queryMultipleFOnE(e).selectList().stream().map(EntityF::identifier).toList();
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-012"
    })
    public void testRequiredRelationEnforced() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityADao.create(EntityAForCreate.builder().build())
        );

        Assertions.assertEquals(1, thrown.getValidationResults().size());
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredConA")))
        ));

        List<EntityA> list = entityADao.query().selectList();

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
        EntityD entityD2 = entityDDao.create(EntityDForCreate.builder().build());

        entityCDao.addMultipleDonC(entityC, List.of(entityD2));

        Optional<EntityA> startA = entityADao.getById(entityA.identifier());

        EntityC entityC2 = entityADao.querySingleRequiredConA(startA.orElseThrow());

        assertEquals(entityC, entityC2);

        List<EntityA> entityA2 = entityCDao.queryTwoWayMultipleAonC(entityC2).selectList();

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

        List<EntityD> ds = entityADao.queryMultipleDonA(entityA).selectList();

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
        EntityF entityF1 = entityFDao.create(EntityFForCreate.builder().build());
        EntityF entityF2 = entityFDao.create(EntityFForCreate.builder().build());
        EntityE entityE = entityEDao.create(EntityEForCreate.builder()
                .withMultipleFOnE(List.of(entityF1, entityF2))
                .build()
                );

        assertEquals(2, entityEDao.queryMultipleFOnE(entityE).selectList().size());
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
        EntityD entityD1 = entityDDao.create(EntityDForCreate.builder()
                .withMultipleAonD(List.of(entityA1, entityA2, entityA3))
                .build());

        assertEquals(3, entityDDao.queryMultipleAonD(entityD1).selectList().size());
        assertEquals(1, entityADao.queryMultipleDonA(entityA1).selectList().size());
    }

    private EntityA createA(EntityC entityC, List<EntityD> entityDs) {
        return entityADao.create(EntityAForCreate.builder()
                .withMultipleDonA(entityDs)
                .withSingleRequiredConA(entityC)
                .build());
    }

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Test
    @TestCase("MultiLevelMask")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
    })
    void testMultiLevelMask() {
        A a = aDao.create(AForCreate.builder().withName("a").build());

        B b = aDao.createB(a, BForCreate.builder().withName("b").build());
        C c1 = aDao.createCs(a, CForCreate.builder().withName("c_1").build());
        C c11 = cDao.createCs(c1, CForCreate.builder().withName("c_1_1").build());
        C c2 = bDao.createC(b, CForCreate.builder().withName("c_2").build());
        C c21 = cDao.createCs(c2, CForCreate.builder().withName("c_2_1").build());
        C c22 = cDao.createCs(c2, CForCreate.builder().withName("c_2_2").build());
        C c23 = cDao.createCs(c2, CForCreate.builder().withName("c_2_3").build());
        C c211 = cDao.createCs(c21, CForCreate.builder().withName("c_2_1_1").build());
        C c221 = cDao.createCs(c22, CForCreate.builder().withName("c_2_2_1").build());
        C c231 = cDao.createCs(c23, CForCreate.builder().withName("c_2_3_1").build());

        AMask aMask = AMask.aMask().addByName("name")
                .addByName("b",
                        BMask.bMask()
                                .addByName("name")
                                .addByName("c", CMask.cMask()
                                        .addByName("name")
                                        .addByName("cs", CMask.cMask()
                                                .addByName("name")
                                                .addByName("cs", CMask.cMask()
                                                        .addByName("name"))))
                )
                .addByName("cs", CMask.cMask()
                        .addByName("name")
                        .addByName("cs", CMask.cMask()
                                .addByName("name")
                                .addByName("cs", CMask.cMask()
                                        .addByName("name"))));

        assertEquals(1, aDao.query().count());
        Optional<A> aWithoutMask = aDao.query().selectOne();
        Optional<A> aWithMask = aDao.query().maskedBy(aMask).selectOne();

        assertTrue(aWithoutMask.isPresent());
        assertTrue(aWithMask.isPresent());

        Map<String, Object> aWithoutMaskOriginalMap = ((MapHolder) aWithoutMask.get()).$originalMap();
        Map<String, Object> aWithMaskOriginalMap = ((MapHolder) aWithMask.get()).$originalMap();
        assertThat(aWithMaskOriginalMap, equalTo(aWithoutMaskOriginalMap));

        AMask maskForGetByID = AMask.aMask().addByName("name")
                .addByName("b",
                        BMask.bMask()
                                .addByName("name")
                                .addByName("c", CMask.cMask()
                                        .addByName("name")));

        checkAMask(aDao.getById(a.identifier(), maskForGetByID).orElseThrow());
        checkAMask(aDao.getById((UUID) a.identifier().getIdentifier(), maskForGetByID).orElseThrow());
        assertEquals(1, aDao.findAllById(List.of((UUID) a.identifier().getIdentifier()), maskForGetByID).size());
        checkAMask(aDao.findAllById(List.of((UUID) a.identifier().getIdentifier()), maskForGetByID).get(0));

        //Check recursive association mask
        maskForGetByID = AMask.aMask()
                .addByName("cs", CMask.cMask()
                        .addByName("name")
                        .addByName("cs", CMask.cMask()
                                .addByName("cs", CMask.cMask()
                                        .addByName("name"))));
        ;

        checkRecursiveAMask(aDao.getById(a.identifier(), maskForGetByID).orElseThrow());
        checkRecursiveAMask(aDao.getById((UUID) a.identifier().getIdentifier(), maskForGetByID).orElseThrow());
        assertEquals(1, aDao.findAllById(List.of((UUID) a.identifier().getIdentifier()), maskForGetByID).size());
        checkRecursiveAMask(aDao.findAllById(List.of((UUID) a.identifier().getIdentifier()), maskForGetByID).get(0));

        B maskedB = aDao.queryB((UUID) a.identifier().getIdentifier(), BMask.bMask().addByName("c", CMask.cMask().addByName("name"))).orElseThrow();

        assertNull(maskedB.getName());
        assertNotNull(maskedB.getC());
        assertNotNull(maskedB.getC().orElseThrow().getName());
        assertNull(maskedB.getC().orElseThrow().getCs());

    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
    })
    public void testSetUnsetEagerRelation() {

        B b1 = bDao.create(BForCreate.builder().withName("b1").build());
        B b2 = bDao.create(BForCreate.builder().withName("b2").build());

        C c1 = cDao.create(CForCreate.builder().withName("c1").build());
        C c2 = cDao.create(CForCreate.builder().withName("c2").build());

        A a = aDao.create(AForCreate.builder().withName("a").build());

        assertTrue(a.getB().isEmpty());

        a.setB(b1);
        a = aDao.update(a);

        assertTrue(a.getB().isPresent());
        assertEquals("b1", a.getB().orElseThrow().getName().orElseThrow());

        a.setB(b2);
        a = aDao.update(a);

        assertTrue(a.getB().isPresent());
        assertEquals("b2", a.getB().orElseThrow().getName().orElseThrow());

        a.setB(null);
        a = aDao.update(a);

        assertTrue(a.getB().isEmpty());

        // multi

        assertTrue(a.getCs().isEmpty());

        a.addToCs(c1);
        a = aDao.update(a);

        assertEquals(1, a.getCs().size());
        assertThat(a.getCs().stream().map(C::getName).map(Optional::orElseThrow).collect(Collectors.toSet()), equalTo(Set.of("c1")));

        a.addToCs(c2);
        a = aDao.update(a);

        assertEquals(2, a.getCs().size());
        assertThat(a.getCs().stream().map(C::getName).map(Optional::orElseThrow).collect(Collectors.toSet()), equalTo(Set.of("c1", "c2")));

        a.removeFromCs(c1);
        a = aDao.update(a);

        assertEquals(1, a.getCs().size());
        assertThat(a.getCs().stream().map(C::getName).map(Optional::orElseThrow).collect(Collectors.toSet()), equalTo(Set.of("c2")));

        a.setCs(List.of());
        a = aDao.update(a);

        assertTrue(a.getCs().isEmpty());

    }

    @Inject
    DDao dDao;

    @Inject
    EDao eDao;

    @Inject
    FDao fDao;


    @Inject
    CollectorDao collectorDao;

    @Inject
    CollectorForNotSupportedDao collectorForNotSupportedDao;

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
    })
    public void testEagerCalculatedMembers() {

        F f1 = fDao.create(FForCreate.builder().withName("f1").build());

        E e1 = eDao.create(EForCreate.builder().withName("e1").withF(f1.adaptTo(FForCreate.class)).build());

        D d = dDao.create(DForCreate.builder().withName("d").build());

        d.setE(e1);

        d = dDao.update(d);

        Collector collector = collectorDao.create(CollectorForCreate.builder().build());

        assertEquals(d.identifier().getIdentifier(), collector.getD().orElseThrow().identifier().getIdentifier());
        assertEquals(e1.identifier().getIdentifier(), collector.getD().orElseThrow().getE().orElseThrow().identifier().getIdentifier());
        assertEquals(f1.identifier().getIdentifier(), collector.getD().orElseThrow().getE().orElseThrow().getF().orElseThrow().identifier().getIdentifier());

        fDao.delete(f1);
        collector = collectorDao.getById(collector.identifier()).orElseThrow();

        assertEquals(d.identifier().getIdentifier(), collector.getD().orElseThrow().identifier().getIdentifier());
        assertEquals(e1.identifier().getIdentifier(), collector.getD().orElseThrow().getE().orElseThrow().identifier().getIdentifier());
        assertTrue(collector.getD().orElseThrow().getE().orElseThrow().getF().isEmpty());

        eDao.delete(e1);
        collector = collectorDao.getById(collector.identifier()).orElseThrow();

        assertEquals(d.identifier().getIdentifier(), collector.getD().orElseThrow().identifier().getIdentifier());
        assertTrue(collector.getD().orElseThrow().getE().isEmpty());

        // TODO JNG-5620
        //CollectorForNotSupported collectorForNotSupported = collectorForNotSupportedDao.create(CollectorForNotSupportedForCreate.builder().build());

    }

    void checkAMask(A a) {
        assertTrue(a.getName().isPresent());
        assertTrue(a.getB().isPresent());
        assertTrue(a.getB().orElseThrow().getName().isPresent());
        assertTrue(a.getB().orElseThrow().getC().isPresent());
        assertTrue(a.getB().orElseThrow().getC().orElseThrow().getName().isPresent());
        assertNull(a.getB().orElseThrow().getC().orElseThrow().getCs());
        assertNull(a.getCs());
    }

    void checkRecursiveAMask(A a) {
        assertNull(a.getName());
        assertNull(a.getB());
        assertEquals(1, a.getCs().size());
        assertTrue(a.getCs().get(0).getName().isPresent());
        assertEquals(1, a.getCs().get(0).getCs().size());
        assertNull(a.getCs().get(0).getCs().get(0).getName());
        assertEquals(0, a.getCs().get(0).getCs().get(0).getCs().size());
    }
}
