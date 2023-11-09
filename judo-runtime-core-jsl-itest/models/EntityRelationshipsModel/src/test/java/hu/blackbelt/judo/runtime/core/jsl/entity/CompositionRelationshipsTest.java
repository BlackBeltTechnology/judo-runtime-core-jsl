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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityAMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityCMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityDIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitye.EntityE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf.EntityF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf2.EntityF2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf2.EntityF2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf3.EntityF3;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf3.EntityF3Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf4.EntityF4;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf4.EntityF4Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityg.EntityG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityh.EntityH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityh.EntityHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.CompositionRelationshipsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CompositionRelationshipsTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("CompositionRelationships", new CompositionRelationshipsDaoModules());

    @Inject
    EntityADao entityADao;

    @Inject
    EntityBDao entityBDao;

    @Inject
    EntityCDao entityCDao;

    @Inject
    EntityDDao entityDDao;

    @Inject
    EntityEDao entityEDao;

    @Inject
    EntityFDao entityFDao;

    @Inject
    EntityF3Dao entityF3Dao;

    @Inject
    EntityF2Dao entityF2Dao;

    @Inject
    EntityF4Dao entityF4Dao;

    @Inject
    EntityHDao entityHDao;

    @Inject
    TransferADao transferADao;

    @Inject
    TransferCDao transferCDao;

    @Inject
    TransferDDao transferDDao;

    EntityA entityA;
    EntityC singleConA;
    EntityC singleRequiredConA;
    EntityD entityD1;
    EntityD entityD2;

    @BeforeEach
    protected void init() {

        entityD1 = entityDDao.create(EntityD.builder().withStringD("D1")
                .build());
        entityD2 = entityDDao.create(EntityD.builder().withStringD("D2")
                .build());
        singleRequiredConA = entityCDao.create(EntityC.builder()
                .withStringC("TEST-C")
                .withMultipleDonB(List.of(entityD1, entityD2))
                .build());
        singleConA = entityCDao.create(EntityC.builder()
                .build());
        entityA = entityADao.create(EntityA.builder()
                .withStringA("TEST-A")
                .withSingleRequiredConA(singleRequiredConA)
                .withSingleConA(singleConA)
                .build());
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007"
    })
    void testMissingRequiredRelationshipThrowsException() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityADao.create(EntityA.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredConA")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testNullOutOptionalRelationRemovesNested() {
        assertNotEquals(singleConA.identifier().getIdentifier(), entityADao.querySingleConA(entityA).orElseThrow().identifier().getIdentifier());
        assertEquals(4, entityCDao.query().execute().size());

        entityA.setSingleConA(null);
        entityADao.update(entityA);

        assertEquals(Optional.empty(), entityADao.querySingleConA(entityA));
        assertEquals(3, entityCDao.query().execute().size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testDeleteOptionalRelation() {
        assertNotEquals(singleConA.identifier().getIdentifier(), entityADao.querySingleConA(entityA).orElseThrow().identifier().getIdentifier());
        assertEquals(4, entityCDao.query().execute().size());

        entityCDao.delete(singleConA);
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testDeleteRequiredRelationThrowsException() {
        EntityC c = entityADao.querySingleRequiredConA(entityA.identifier());
        assertNotNull(c);
        assertNotNull(c.identifier());
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> entityCDao.delete(c.identifier())
        );

        assertThat(exception.getMessage(), CoreMatchers.startsWith("There are mandatory references that cannot be removed"));

        entityA.setSingleRequiredConA(null);
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityADao.update(entityA)
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredConA")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testTraverse() {
        List<EntityA> maskedAs = entityADao.query().execute();
        EntityA maskedA = maskedAs.get(0);
        EntityC singleRequiredConA = maskedA.getSingleRequiredConA();


        assertEquals(Optional.of("TEST-A"), maskedA.getStringA());
        assertNotEquals(Optional.empty(), maskedA.getSingleConA());
        assertNotEquals(null, maskedA.getSingleRequiredConA());
        assertEquals(Optional.of("TEST-C"), singleRequiredConA.getStringC());
        assertEquals(2, singleRequiredConA.getMultipleDonB().size());
        assertNotEquals(Optional.of(entityD1), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.identifier().equals(entityD1.identifier())).findFirst());
        assertNotEquals(Optional.of(entityD2), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.identifier().equals(entityD2.identifier())).findFirst());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testMask() {
        List<EntityA> maskedAs = entityADao.query().maskedBy(
                EntityAMask.entityAMask().withSingleRequiredConA(EntityCMask.entityCMask().withStringC())).execute();

        EntityA maskedA = maskedAs.get(0);
        EntityC requiredC = maskedA.getSingleRequiredConA();

        assertEquals(1, maskedAs.size());
        assertEquals(null, maskedA.getSingleConA());
        assertEquals(null, maskedA.getStringA());
        assertNotEquals(singleRequiredConA.identifier(), maskedA.getSingleRequiredConA().identifier());
        assertEquals(null, requiredC.getStringB());
        assertEquals(Optional.of("TEST-C"), requiredC.getStringC());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testUpdateRootUpdatesInterimElement() {
        EntityA entityA2 = entityADao.query().execute().get(0);
        EntityC requiredC = entityA2.getSingleRequiredConA();
        EntityC singleC = entityA2.getSingleConA().get();

        assertEquals(Optional.empty(), requiredC.getStringB());
        assertEquals(Optional.empty(), singleC.getStringB());
        assertEquals(Optional.empty(), singleC.getStringC());
        assertNotEquals(Optional.of(singleConA), entityA2.getSingleConA());

        requiredC.setStringB("Hello!");
        singleC.setStringB("NEW-B");
        singleC.setStringC("NEW-C");

        EntityA updatedA = entityADao.update(entityA2);

        assertEquals(Optional.of("Hello!"), updatedA.getSingleRequiredConA().getStringB());
        assertEquals(Optional.of("NEW-B"), entityA2.getSingleConA().get().getStringB());
        assertEquals(Optional.of("NEW-C"), entityA2.getSingleConA().get().getStringC());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    void testMultipleInheritance() {
        EntityE entityE = entityEDao.create(EntityE.builder()
                                                   .withStringB("B")
                                                   .withStringC("C")
                                                   .withStringD("D")
                                                   .build()
        );

        assertEquals(Optional.of("B"), entityE.getStringB());
        assertEquals(Optional.of("C"), entityE.getStringC());
        assertEquals(Optional.of("D"), entityE.getStringD());
        List<EntityD> multipleDonB = entityE.getMultipleDonB();
        assertNotNull(multipleDonB);
        assertTrue(multipleDonB.isEmpty());

        EntityE entityE2 = entityEDao.create(EntityE.builder()
                                                    .withStringB("B2")
                                                    .withStringC("C2")
                                                    .withStringD("D2")
                                                    .withMultipleDonB(List.of(EntityD.builder().withStringD("D1").build(),
                                                                              EntityD.builder().withStringD("D2").build()))
                                                    .build()
        );

        assertEquals(Optional.of("B2"), entityE2.getStringB());
        assertEquals(Optional.of("C2"), entityE2.getStringC());
        assertEquals(Optional.of("D2"), entityE2.getStringD());
        List<EntityD> multipleDonB2 = entityE2.getMultipleDonB();
        assertNotNull(multipleDonB2);
        assertEquals(2, multipleDonB2.size());
        assertTrue(multipleDonB2.stream().anyMatch(d -> "D1".equals(d.getStringD().orElse(null))));
        assertTrue(multipleDonB2.stream().anyMatch(d -> "D2".equals(d.getStringD().orElse(null))));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    void testAbstractDAOOperations() {
        EntityC entityC = entityCDao.create(EntityC.builder().build());

        assertEquals(Optional.empty(), entityC.getStringB());
        assertEquals(Optional.empty(), entityC.getStringC());

        Optional<EntityB> entityB = entityBDao.getById(entityC.identifier().adaptTo(EntityBIdentifier.class));

        assertTrue(entityB.isPresent());

        entityC.setStringB("B");

        EntityC updatedC = entityCDao.update(entityC);

        assertEquals(Optional.of("B"), updatedC.getStringB());

        Optional<EntityB> updatedB = entityBDao.getById(entityC.identifier().adaptTo(EntityBIdentifier.class));

        assertEquals(Optional.of("B"), updatedB.get().getStringB());
    }

    @Test
    void testDeepCopyConstructor() {
        //When we add a composition Entity we must copy it, because that comp entity belong the created entity

        //Build entities for the test
        entityD1 = entityDDao.create(EntityD.builder()
                .build());
        entityD2 = entityDDao.create(EntityD.builder()
                .build());
        singleRequiredConA = entityCDao.create(EntityC.builder()
                .withStringC("C")
                .withMultipleDonB(List.of(entityD1, entityD2))
                .build());
        entityA = entityADao.create(EntityA.builder()
                .withStringA("A")
                .withSingleRequiredConA(singleRequiredConA)
                .build());

        assertNotEquals(entityA.getSingleRequiredConA().identifier(), singleRequiredConA.identifier());
        List<EntityDIdentifier> collect = singleRequiredConA.getMultipleDonB().stream().map(c -> c.identifier()).collect(Collectors.toList());
        assertFalse(collect.contains(entityD1.identifier()));
        assertFalse(collect.contains(entityD2.identifier()));

    }

    @Test
    @Disabled("JNG-4194")
    void testCompositionWithUndefinedRequiredFields() {
        // TODO: JNG-4194
        EntityF f = EntityF.builder().build();

        // Default attribute value is undefined on required attribute: _name_Default_EntityG
        f.setG(EntityG.builder().withName("Entity").build());
        f = entityFDao.create(f);
        assertTrue(f.getG().orElseThrow().getName().equals("Entity"));

        EntityF f12 = EntityF.builder().build();

        EntityH h = entityHDao.create(EntityH.builder().withAlwaysUndefined("Entity").build());
        f12.setG(EntityG.builder().build());
        f12 = entityFDao.create(f12);

        assertTrue(f12.getG().orElseThrow().getName().equals("Entity"));

        entityHDao.delete(h);

        EntityF f13 = entityFDao.create(EntityF.builder().build());

        // Default attribute value is undefined on required attribute: _name_Default_EntityG
        // TODO: JNG-5046
        EntityG g13 = entityFDao.createG(f13, EntityG.builder().withName("Entity").build());
        f13 = entityFDao.getById(f13.identifier()).orElseThrow();
        assertTrue(g13.getName().equals("Entity"));
        assertTrue(f13.getG().orElseThrow().getName().equals("Entity"));

        EntityF2 f2 = EntityF2.builder().build();

        f2.setH(EntityH.builder().withAlwaysUndefined("Entity").build());
        f2 = entityF2Dao.create(f2);

        assertTrue(f2.getH().orElseThrow().getAlwaysUndefined().orElseThrow().equals("Entity"));
        entityHDao.delete(f2.getH().orElseThrow());

        EntityF2 f21 = entityF2Dao.create(EntityF2.builder().build());
        EntityH h2 = entityF2Dao.createH(f21, EntityH.builder().withAlwaysUndefined("Entity").build());
        f21 = entityF2Dao.getById(f21.identifier()).orElseThrow();
        assertTrue(f21.getH().orElseThrow().getAlwaysUndefined().orElseThrow().equals("Entity"));
        // TODO: JNG-5046
        assertTrue(h2.getAlwaysUndefined().orElseThrow().equals("Entity"));
        entityHDao.delete(h2);

        EntityF3 f3 = EntityF3.builder().build();

        f3.setG(List.of(EntityG.builder().withName("Entity2").build()));
        //Default attribute value is undefined on required attribute: _name_Default_EntityG
        f3 = entityF3Dao.create(f3);

        assertTrue(f3.getG().get(0).getName().equals("Entity2"));

        h2 = entityHDao.create(EntityH.builder().withAlwaysUndefined("Entity").build());

        EntityF3 f31 = EntityF3.builder().build();

        f31.setG(List.of(EntityG.builder().build()));
        f31 = entityF3Dao.create(f31);

        assertTrue(f31.getG().get(0).getName().equals("Entity"));

        entityHDao.delete(h2);

        EntityF3 f32 = entityF3Dao.create(EntityF3.builder().build());
        // Default attribute value is undefined on required attribute: _name_Default_EntityG
        // TODO: JNG-5046
        EntityG g3 = entityF3Dao.createG(f32, EntityG.builder().withName("Entity2").build());
        f32 = entityF3Dao.getById(f32.identifier()).orElseThrow();
        assertTrue(g3.getName().equals("Entity2"));
        assertTrue(f32.getG().get(0).getName().equals("Entity2"));

        EntityF4 f4 = EntityF4.builder().build();

        f4.setH(List.of(EntityH.builder().withAlwaysUndefined("Entity").build()));
        f4 = entityF4Dao.create(f4);

        assertTrue(f4.getH().get(0).getAlwaysUndefined().orElseThrow().equals("Entity"));

        EntityF4 f42 = entityF4Dao.create(EntityF4.builder().build());
        EntityH h4 = entityF4Dao.createH(f42, EntityH.builder().withAlwaysUndefined("Entity").build());
        f42 = entityF4Dao.getById(f42.identifier()).orElseThrow();
        // TODO: JNG-5046
        assertTrue(h4.getAlwaysUndefined().orElseThrow().equals("Entity"));
        assertTrue(f42.getH().get(0).getAlwaysUndefined().orElseThrow().equals("Entity"));
    }

    @Test
    void testDeepCopyCreate() {
        assertNotEquals(singleConA.identifier().getIdentifier() ,entityA.getSingleConA().orElseThrow().identifier().getIdentifier());
        assertNotEquals(singleRequiredConA.identifier().getIdentifier() ,entityA.getSingleRequiredConA().identifier().getIdentifier());
        assertEquals("TEST-A", entityA.getStringA().orElseThrow());

        Collection<EntityD> ds = entityA.getSingleRequiredConA().getMultipleDonB();

        EntityD testD1 = ds.stream().filter(d -> d.getStringD().orElseThrow().equals("D1")).findFirst().orElseThrow();
        EntityD testD2 = ds.stream().filter(d -> d.getStringD().orElseThrow().equals("D2")).findFirst().orElseThrow();
        assertNotEquals(entityD1.identifier().getIdentifier(), testD1.identifier().getIdentifier());
        assertEquals(entityD1.getStringD().orElseThrow(), testD1.getStringD().orElseThrow());
        assertNotEquals(entityD2.identifier().getIdentifier(), testD2.identifier().getIdentifier());
        assertEquals(entityD2.getStringD().orElseThrow(), testD2.getStringD().orElseThrow());
    }

    @Test
    void testDeepCopyUpdate() {
        EntityA a2 = entityADao.create(EntityA.builder().withSingleRequiredConA(EntityC.builder().build()).build());
        assertEquals(Optional.empty(), a2.getSingleConA());
        assertEquals(0, a2.getCollectionConA().size());

        assertEquals(5, entityCDao.countAll());
        assertEquals(6, entityDDao.countAll());

        a2.setSingleConA(EntityC.builder().withStringC("C1").build());
        a2.setCollectionConA(List.of(EntityC.builder().withStringC("C2").withMultipleDonB(List.of(EntityD.builder().withStringD("D3").build())).build()));

        a2 = entityADao.update(a2);

        assertEquals(7, entityCDao.countAll());
        assertEquals(7, entityDDao.countAll());
        assertEquals("C1", a2.getSingleConA().orElseThrow().getStringC().orElseThrow());
        assertEquals("C2", a2.getCollectionConA().get(0).getStringC().orElseThrow());
        assertEquals("D3", a2.getCollectionConA().get(0).getMultipleDonB().get(0).getStringD().orElseThrow());

        EntityC c3 = entityCDao.create(EntityC.builder().withStringC("C3").build());
        EntityC c4 = entityCDao.create(EntityC.builder().withStringC("C4").withMultipleDonB(List.of(EntityD.builder().withStringD("D3").build())).build());
        EntityA a3 = entityADao.create(EntityA.builder().withCollectionConA(List.of(c3,c4)).withSingleConA(c3).withSingleRequiredConA(c4).build());

        assertEquals(13, entityCDao.countAll());
        assertEquals(10, entityDDao.countAll());

        a3.setSingleConA(EntityC.builder().withStringC("C3Updated").build());
        a3.setSingleRequiredConA(EntityC.builder().withStringC("C4Updated").withMultipleDonB(List.of(EntityD.builder().withStringD("D3Updated").build())).build());

        a3 = entityADao.update(a3);

        assertEquals(13, entityCDao.countAll());
        // TODO: JNG-5213 update does not create new EntityD instance
        //assertEquals(10, entityDDao.countAll());
        assertEquals("C3Updated", a3.getSingleConA().orElseThrow().getStringC().orElseThrow());
        assertEquals("C4Updated", a3.getSingleRequiredConA().getStringC().orElseThrow());
        //assertEquals("D3Updated", a3.getSingleRequiredConA().getMultipleDonB().get(0).getStringD().orElseThrow());


        EntityA a4 = entityADao.create(EntityA.builder().withSingleRequiredConA(EntityC.builder().build()).build());
        assertEquals(Optional.empty(), a4.getSingleConA());
        assertEquals(0, a4.getCollectionConA().size());

        EntityC c5 = entityCDao.create(EntityC.builder().withStringC("C5").build());
        EntityC c6 = entityCDao.create(EntityC.builder().withStringC("C6").withMultipleDonB(List.of(EntityD.builder().withStringD("D4").build())).build());

        a4.setSingleConA(c5);
        a4.setCollectionConA(List.of(c6));
        final EntityA a5 = entityADao.update(a4);

        assertEquals(18, entityCDao.countAll());
        //assertEquals(12, entityDDao.countAll());

        assertEquals("C5", a5.getSingleConA().orElseThrow().getStringC().orElseThrow());
        assertEquals("C6", a5.getCollectionConA().get(0).getStringC().orElseThrow());
        assertEquals("D4", a5.getCollectionConA().get(0).getMultipleDonB().get(0).getStringD().orElseThrow());

        EntityC c7 = entityCDao.create(EntityC.builder().withStringC("C7").build());
        EntityC c8 = entityCDao.create(EntityC.builder().withStringC("C8").withMultipleDonB(List.of(EntityD.builder().withStringD("D5").build())).build());

        a5.setSingleConA(c7);
        a5.setCollectionConA(List.of(c8));

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> entityADao.update(a5)
        );
    }
}
