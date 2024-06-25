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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.composition.Composition;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.composition.CompositionDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.composition.CompositionForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.container.Container;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.container.ContainerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.container.ContainerForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.containment1.Containment1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.containment1.Containment1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.containment1.Containment1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.containment2.Containment2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.containment2.Containment2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.containment2.Containment2ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitya.EntityAMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityc.EntityCMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitye.EntityE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entitye.EntityEForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf.EntityF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf.EntityFForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf2.EntityF2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf2.EntityF2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf2.EntityF2ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf3.EntityF3;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf3.EntityF3Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf3.EntityF3ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf4.EntityF4;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf4.EntityF4Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityf4.EntityF4ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityg.EntityG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityg.EntityGForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityh.EntityH;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityh.EntityHDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityh.EntityHForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityj.EntityJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityj.EntityJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityj.EntityJForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityk.EntityKDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityk.EntityKForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityl.EntityLDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityl.EntityLForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.CompositionRelationshipsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThrows;
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
    CompositionDao compositionDao;

    EntityA entityA;
    EntityC singleConA;
    EntityC singleRequiredConA;
    EntityD entityD1;
    EntityD entityD2;

    @BeforeEach
    protected void init() {

        entityD1 = entityDDao.create(EntityDForCreate.builder().withStringD("D1")
                .build());
        entityD2 = entityDDao.create(EntityDForCreate.builder().withStringD("D2")
                .build());
        singleRequiredConA = entityCDao.create(EntityCForCreate.builder()
                .withStringC("TEST-C")
                .withMultipleDonB(List.of(EntityDForCreate.builderFrom(entityD1).build(), EntityDForCreate.builderFrom(entityD2).build()))
                .build());
        singleConA = entityCDao.create(EntityCForCreate.builder()
                .build());
        entityA = entityADao.create(EntityAForCreate.builder()
                .withStringA("TEST-A")
                .withSingleRequiredConA(EntityCForCreate.builderFrom(singleRequiredConA).build())
                .withSingleConA(EntityCForCreate.builderFrom(singleConA).build())
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
                () -> entityADao.create(EntityAForCreate.builder().build())
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
        assertEquals(4, entityCDao.query().selectList().size());

        entityA.setSingleConA(null);
        entityADao.update(entityA);

        assertEquals(Optional.empty(), entityADao.querySingleConA(entityA));
        assertEquals(3, entityCDao.query().selectList().size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testDeleteOptionalRelation() {
        assertNotEquals(singleConA.identifier().getIdentifier(), entityADao.querySingleConA(entityA).orElseThrow().identifier().getIdentifier());
        assertEquals(4, entityCDao.query().selectList().size());

        entityCDao.delete(singleConA);
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testDeleteRequiredRelationThrowsException() {
        EntityC c = entityADao.querySingleRequiredConA((UUID) entityA.identifier().getIdentifier(), EntityCMask.entityCMask().withStringC());
        assertNull(c.getStringB());
        assertNull(c.getMultipleDonB());
        assertNotNull(c.getStringC());
        assertEquals("TEST-C", c.getStringC().orElseThrow());
        assertNotNull(c);
        assertNotNull(c.identifier().getIdentifier());

        c = entityADao.querySingleRequiredConA(entityA, EntityCMask.entityCMask().withStringC());
        assertNull(c.getStringB());
        assertNull(c.getMultipleDonB());
        assertNotNull(c.getStringC());
        assertEquals("TEST-C", c.getStringC().orElseThrow());
        assertNotNull(c);
        assertNotNull(c.identifier().getIdentifier());

        c = entityADao.querySingleRequiredConA(entityA.identifier(), EntityCMask.entityCMask().withStringC());
        assertNull(c.getStringB());
        assertNull(c.getMultipleDonB());
        assertNotNull(c.getStringC());
        assertEquals("TEST-C", c.getStringC().orElseThrow());
        assertNotNull(c);
        assertNotNull(c.identifier().getIdentifier());

        EntityC finalC = c;
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> entityCDao.delete(finalC.identifier())
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
        EntityA maskedA = entityADao.query().selectOne().get();
        EntityC singleRequiredConA = maskedA.getSingleRequiredConA();

        assertEquals(Optional.of("TEST-A"), maskedA.getStringA());
        assertNotEquals(Optional.empty(), maskedA.getSingleConA());
        assertNotEquals(null, maskedA.getSingleRequiredConA());
        assertEquals(Optional.of("TEST-C"), singleRequiredConA.getStringC());
        assertEquals(2, singleRequiredConA.getMultipleDonB().size());
        assertNotEquals(Optional.of(entityD1), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.identifier().getIdentifier().equals(entityD1.identifier().getIdentifier())).findFirst());
        assertNotEquals(Optional.of(entityD2), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.identifier().getIdentifier().equals(entityD2.identifier().getIdentifier())).findFirst());
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
                EntityAMask.entityAMask().withSingleRequiredConA(EntityCMask.entityCMask().withStringC())).selectList();

        EntityA maskedA = maskedAs.get(0);
        EntityC requiredC = maskedA.getSingleRequiredConA();

        assertEquals(1, maskedAs.size());
        assertEquals(null, maskedA.getSingleConA());
        assertEquals(null, maskedA.getStringA());
        assertNotEquals(singleRequiredConA.identifier().getIdentifier(), maskedA.getSingleRequiredConA().identifier().getIdentifier());
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
    void testMaskedByName() {
        Optional<EntityA> maskedA = entityADao.query()
                .maskedBy(EntityAMask
                        .entityAMask()
                        .addByName("stringA")
                )
                .selectOne();

        assertTrue(maskedA.isPresent());
        assertEquals("TEST-A", maskedA.get().getStringA().get());
        assertNull(maskedA.get().getSingleConA());
        assertNull(maskedA.get().getCollectionConA());
        assertNull(maskedA.get().getSingleRequiredConA());

        maskedA = entityADao.query()
                .maskedBy(EntityAMask
                        .entityAMask()
                        .addByName("stringA")
                        .addByName("singleRequiredConA", EntityCMask.entityCMask().addByName("stringC"))
                )
                .selectOne();

        assertTrue(maskedA.isPresent());
        assertEquals("TEST-A", maskedA.get().getStringA().get());
        assertEquals("TEST-C", maskedA.get().getSingleRequiredConA().getStringC().get());
        assertNull(maskedA.get().getSingleRequiredConA().getMultipleDonB());
        assertNull(maskedA.get().getSingleRequiredConA().getStringB());
        assertNull(maskedA.get().getSingleConA());
        assertNull(maskedA.get().getCollectionConA());

        // check a not existed attribute
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> entityADao.query()
                        .maskedBy(EntityAMask
                                .entityAMask()
                                .addByName("stringANotExisted")
                        )
                        .selectOne()
        );
        assertTrue(thrown.getMessage().contains("No enum constant for"));
        assertTrue(thrown.getMessage().contains("EntityAAttribute#stringANotExisted"));

        // check a not existed reference
        thrown = assertThrows(
                RuntimeException.class,
                () -> entityADao.query()
                        .maskedBy(EntityAMask
                                .entityAMask()
                                .addByName("singleRequiredConANotExisted", EntityCMask.entityCMask().addByName("stringC"))
                        )
                        .selectOne()
        );
        assertTrue(thrown.getMessage().contains("No enum constant for"));
        assertTrue(thrown.getMessage().contains("EntityAReference#singleRequiredConANotExisted"));

        // check a not existed nested attribute
        thrown = assertThrows(
                RuntimeException.class,
                () -> entityADao.query()
                        .maskedBy(EntityAMask
                                .entityAMask()
                                .addByName("singleRequiredConANotExisted", EntityCMask.entityCMask().addByName("stringCNotExisted"))
                        )
                        .selectOne()
        );
        assertTrue(thrown.getMessage().contains("No enum constant for"));
        assertTrue(thrown.getMessage().contains("EntityCAttribute#stringCNotExisted"));
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
        EntityA entityA2 = entityADao.query().selectOne().get();
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
        EntityE entityE = entityEDao.create(EntityEForCreate.builder()
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

        EntityE entityE2 = entityEDao.create(EntityEForCreate.builder()
                .withStringB("B2")
                .withStringC("C2")
                .withStringD("D2")
                .withMultipleDonB(List.of(EntityDForCreate.builder().withStringD("D1").build(),
                        EntityDForCreate.builder().withStringD("D2").build()))
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
        EntityC entityC = entityCDao.create(EntityCForCreate.builder().build());

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
        entityD1 = entityDDao.create(EntityDForCreate.builder()
                .build());
        entityD2 = entityDDao.create(EntityDForCreate.builder()
                .build());
        singleRequiredConA = entityCDao.create(EntityCForCreate.builder()
                .withStringC("C")
                .withMultipleDonB(List.of(entityD1.adaptTo(EntityDForCreate.class), entityD2.adaptTo(EntityDForCreate.class)))
                .build());
        entityA = entityADao.create(EntityAForCreate.builder()
                .withStringA("A")
                .withSingleRequiredConA(singleRequiredConA.adaptTo(EntityCForCreate.class))
                .build());

        assertNotEquals(entityA.getSingleRequiredConA().identifier().getIdentifier(), singleRequiredConA.identifier().getIdentifier());
        List<Serializable> collect = singleRequiredConA.getMultipleDonB().stream().map(c -> c.identifier().getIdentifier()).collect(Collectors.toList());
        assertFalse(collect.contains(entityD1.identifier().getIdentifier()));
        assertFalse(collect.contains(entityD2.identifier().getIdentifier()));

    }

    @Test
    @Disabled("JNG-4194")
    void testCompositionWithUndefinedRequiredFields() {
        // TODO: JNG-4194
        EntityFForCreate fForCreate = EntityFForCreate.builder().build();

        // Default attribute value is undefined on required attribute: _name_Default_EntityG
        fForCreate.setG(EntityGForCreate.builder().withName("Entity").build());
        EntityF f = entityFDao.create(fForCreate);
        assertTrue(f.getG().orElseThrow().getName().equals("Entity"));

        EntityFForCreate f12ForCreate = EntityFForCreate.builder().build();

        EntityH h = entityHDao.create(EntityHForCreate.builder().withAlwaysUndefined("Entity").build());
        f12ForCreate.setG(EntityGForCreate.builder().build());
        EntityF f12 = entityFDao.create(f12ForCreate);

        assertTrue(f12.getG().orElseThrow().getName().equals("Entity"));

        entityHDao.delete(h);

        EntityF f13 = entityFDao.create(EntityFForCreate.builder().build());

        // Default attribute value is undefined on required attribute: _name_Default_EntityG
        // TODO: JNG-5046
        EntityG g13 = entityFDao.createG(f13, EntityGForCreate.builder().withName("Entity").build());
        f13 = entityFDao.getById(f13.identifier()).orElseThrow();
        assertTrue(g13.getName().equals("Entity"));
        assertTrue(f13.getG().orElseThrow().getName().equals("Entity"));

        EntityF2ForCreate f2ForCreate = EntityF2ForCreate.builder().build();

        f2ForCreate.setH(EntityHForCreate.builder().withAlwaysUndefined("Entity").build());
        EntityF2 f2 = entityF2Dao.create(f2ForCreate);

        assertTrue(f2.getH().orElseThrow().getAlwaysUndefined().orElseThrow().equals("Entity"));
        entityHDao.delete(f2.getH().orElseThrow());

        EntityF2 f21 = entityF2Dao.create(EntityF2ForCreate.builder().build());
        EntityH h2 = entityF2Dao.createH(f21, EntityHForCreate.builder().withAlwaysUndefined("Entity").build());
        f21 = entityF2Dao.getById(f21.identifier()).orElseThrow();
        assertTrue(f21.getH().orElseThrow().getAlwaysUndefined().orElseThrow().equals("Entity"));
        // TODO: JNG-5046
        assertTrue(h2.getAlwaysUndefined().orElseThrow().equals("Entity"));
        entityHDao.delete(h2);

        EntityF3ForCreate f3ForCreate = EntityF3ForCreate.builder().build();

        f3ForCreate.setG(List.of(EntityGForCreate.builder().withName("Entity2").build()));
        //Default attribute value is undefined on required attribute: _name_Default_EntityG
        EntityF3 f3 = entityF3Dao.create(f3ForCreate);

        assertTrue(f3.getG().get(0).getName().equals("Entity2"));

        h2 = entityHDao.create(EntityHForCreate.builder().withAlwaysUndefined("Entity").build());

        EntityF3ForCreate f31ForCreate = EntityF3ForCreate.builder().build();

        f31ForCreate.setG(List.of(EntityGForCreate.builder().build()));
        EntityF3 f31 = entityF3Dao.create(f31ForCreate);

        assertTrue(f31.getG().get(0).getName().equals("Entity"));

        entityHDao.delete(h2);

        EntityF3 f32 = entityF3Dao.create(EntityF3ForCreate.builder().build());
        // Default attribute value is undefined on required attribute: _name_Default_EntityG
        // TODO: JNG-5046
        EntityG g3 = entityF3Dao.createG(f32, EntityGForCreate.builder().withName("Entity2").build());
        f32 = entityF3Dao.getById(f32.identifier()).orElseThrow();
        assertTrue(g3.getName().equals("Entity2"));
        assertTrue(f32.getG().get(0).getName().equals("Entity2"));

        EntityF4ForCreate f4ForCreate = EntityF4ForCreate.builder().build();

        f4ForCreate.setH(List.of(EntityHForCreate.builder().withAlwaysUndefined("Entity").build()));
        EntityF4 f4 = entityF4Dao.create(f4ForCreate);

        assertTrue(f4.getH().get(0).getAlwaysUndefined().orElseThrow().equals("Entity"));

        EntityF4 f42 = entityF4Dao.create(EntityF4ForCreate.builder().build());
        EntityH h4 = entityF4Dao.createH(f42, EntityHForCreate.builder().withAlwaysUndefined("Entity").build());
        f42 = entityF4Dao.getById(f42.identifier()).orElseThrow();
        // TODO: JNG-5046
        assertTrue(h4.getAlwaysUndefined().orElseThrow().equals("Entity"));
        assertTrue(f42.getH().get(0).getAlwaysUndefined().orElseThrow().equals("Entity"));
    }

    @Test
    void testDeepCopyCreate() {
        assertNotEquals(singleConA.identifier().getIdentifier(), entityA.getSingleConA().orElseThrow().identifier().getIdentifier());
        assertNotEquals(singleRequiredConA.identifier().getIdentifier(), entityA.getSingleRequiredConA().identifier().getIdentifier());
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
        EntityA a2 = entityADao.create(EntityAForCreate.builder().withSingleRequiredConA(EntityCForCreate.builder().build()).build());
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

        EntityC c3 = entityCDao.create(EntityCForCreate.builder().withStringC("C3").build());
        EntityC c4 = entityCDao.create(EntityCForCreate.builder().withStringC("C4").withMultipleDonB(List.of(EntityDForCreate.builder().withStringD("D3").build())).build());
        EntityA a3 = entityADao.create(EntityAForCreate.builder().withCollectionConA(List.of(c3.adaptTo(EntityCForCreate.class), c4.adaptTo(EntityCForCreate.class))).withSingleConA(c3.adaptTo(EntityCForCreate.class)).withSingleRequiredConA(c4.adaptTo(EntityCForCreate.class)).build());

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


        EntityA a4 = entityADao.create(EntityAForCreate.builder().withSingleRequiredConA(EntityCForCreate.builder().build()).build());
        assertEquals(Optional.empty(), a4.getSingleConA());
        assertEquals(0, a4.getCollectionConA().size());

        EntityC c5 = entityCDao.create(EntityCForCreate.builder().withStringC("C5").build());
        EntityC c6 = entityCDao.create(EntityCForCreate.builder().withStringC("C6").withMultipleDonB(List.of(EntityDForCreate.builder().withStringD("D4").build())).build());

        a4.setSingleConA(c5);
        a4.setCollectionConA(List.of(c6));
        final EntityA a5 = entityADao.update(a4);

        assertEquals(18, entityCDao.countAll());
        //assertEquals(12, entityDDao.countAll());

        assertEquals("C5", a5.getSingleConA().orElseThrow().getStringC().orElseThrow());
        assertEquals("C6", a5.getCollectionConA().get(0).getStringC().orElseThrow());
        assertEquals("D4", a5.getCollectionConA().get(0).getMultipleDonB().get(0).getStringD().orElseThrow());

        EntityC c7 = entityCDao.create(EntityCForCreate.builder().withStringC("C7").build());
        EntityC c8 = entityCDao.create(EntityCForCreate.builder().withStringC("C8").withMultipleDonB(List.of(EntityDForCreate.builder().withStringD("D5").build())).build());

        a5.setSingleConA(c7);
        a5.setCollectionConA(List.of(c8));

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> entityADao.update(a5)
        );
    }

    @Test
    void testAddMethodOnBuilder() {
        EntityC c1 = entityCDao.create(EntityCForCreate.builder().withStringC("C1").build());
        EntityC c2 = entityCDao.create(EntityCForCreate.builder().withStringC("C2").build());
        EntityC c3 = entityCDao.create(EntityCForCreate.builder().withStringC("C3").build());
        EntityC c4 = entityCDao.create(EntityCForCreate.builder().withStringC("C4").build());

        EntityA entityA = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c2).build())
                .build());

        assertEquals(1, entityA.getCollectionConA().size());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C2")).count());

        entityA = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c2).build(), EntityCForCreate.builderFrom(c3).build())
                .build());

        assertEquals(2, entityA.getCollectionConA().size());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C2")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C3")).count());

        entityA = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c2).build(), EntityCForCreate.builderFrom(c2).build())
                .build());

        assertEquals(2, entityA.getCollectionConA().size());
        assertEquals(2, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C2")).count());

        entityA = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c2).build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c2).build())
                .build());

        assertEquals(2, entityA.getCollectionConA().size());
        assertEquals(2, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C2")).count());

        entityA = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c2).build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c3).build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c4).build())
                .build());

        assertEquals(3, entityA.getCollectionConA().size());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C2")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C3")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C4")).count());

        entityA = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .withCollectionConA(List.of(EntityCForCreate.builderFrom(c1).build(), EntityCForCreate.builderFrom(c2).build()))
                .addToCollectionConA(EntityCForCreate.builderFrom(c3).build())
                .addToCollectionConA(EntityCForCreate.builderFrom(c4).build())
                .build());

        assertEquals(4, entityA.getCollectionConA().size());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C1")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C2")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C3")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C4")).count());

        entityA = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .withCollectionConA(List.of(EntityCForCreate.builderFrom(c1).build(), EntityCForCreate.builderFrom(c2).build()))
                .addToCollectionConA(EntityCForCreate.builderFrom(c3).build(), EntityCForCreate.builderFrom(c4).build())
                .build());

        assertEquals(4, entityA.getCollectionConA().size());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C1")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C2")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C3")).count());
        assertEquals(1, entityA.getCollectionConA().stream().filter(entityC -> entityC.getStringC().orElseThrow().equals("C4")).count());
    }

    @Test
    void testAddAndRemoveOnCollections() {
        EntityC c2 = entityCDao.create(EntityCForCreate.builder().withStringC("C2").build());
        EntityC c3 = entityCDao.create(EntityCForCreate.builder().withStringC("C3").build());
        EntityA entityA1 = entityADao.create(EntityAForCreate.builder()
                .withSingleRequiredConA(EntityCForCreate.builder().withStringC("C1").build())
                .withCollectionConA(List.of(EntityCForCreate.builderFrom(c2).build(), EntityCForCreate.builderFrom(c3).build()))
                .build());

        assertEquals("C1", entityA1.getSingleRequiredConA().getStringC().orElseThrow());
        assertEquals(2, entityA1.getCollectionConA().size());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C2")).count());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C3")).count());

        EntityC c4 = EntityC.builder().withStringC("C4").build();
        EntityC c5 = EntityC.builder().withStringC("C5").build();
        EntityC c6 = EntityC.builder().withStringC("C6").build();
        entityA1.addToCollectionConA(c4);
        entityA1.addToCollectionConA(c5, c6);

        assertEquals("C1", entityA1.getSingleRequiredConA().getStringC().orElseThrow());
        assertEquals(5, entityA1.getCollectionConA().size());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C2")).count());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C3")).count());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C4")).count());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C5")).count());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C6")).count());

        entityA1.addToCollectionConA(null);
        assertEquals(6, entityA1.getCollectionConA().size());
        EntityC c7 = EntityC.builder().withStringC("C7").build();
        entityA1.addToCollectionConA(null, c7);
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c != null && c.getStringC().orElseThrow().equals("C6")).count());
        assertEquals(1, entityA1.getCollectionConA().stream()
                .filter(c -> c != null && c.getStringC().orElseThrow().equals("C7")).count());
        assertEquals(8, entityA1.getCollectionConA().size());

        // The ID of c3 was changed after entity A1 was created
        entityA1.removeFromCollectionConA(c3, c4);
        assertEquals(7, entityA1.getCollectionConA().size());
        assertEquals(1, entityA1.getCollectionConA().stream().filter(c -> c != null && c.getStringC().orElseThrow().equals("C3")).count());
        assertEquals(0, entityA1.getCollectionConA().stream().filter(c -> c != null && c.getStringC().orElseThrow().equals("C4")).count());

        entityA1.removeFromCollectionConA(c6);
        assertEquals(6, entityA1.getCollectionConA().size());
        assertEquals(0, entityA1.getCollectionConA().stream().filter(c -> c != null && c.getStringC().orElseThrow().equals("C6")).count());

        c5.setStringC("C5Updated");
        entityA1.removeFromCollectionConA(c5);

        assertEquals(5, entityA1.getCollectionConA().size());
        assertEquals(0, entityA1.getCollectionConA().stream().filter(c -> c != null && c.getStringC().orElseThrow().equals("C5")).count());
        assertEquals(0, entityA1.getCollectionConA().stream().filter(c -> c != null && c.getStringC().orElseThrow().equals("C5Updated")).count());

        EntityC c8 = EntityC.builder().withStringC("C8").build();
        EntityC c9 = EntityC.builder().withStringC("C9").build();
        EntityC c10 = EntityC.builder().withStringC("C10").build();
        EntityA entityA2 = EntityA.builder().withStringA("A2").withSingleRequiredConA(EntityC.builder().build()).build();
        EntityA entityA3 = EntityA.builder().withStringA("A3").withSingleRequiredConA(EntityC.builder().build()).build();
        EntityA entityA4 = EntityA.builder().withStringA("A4").withSingleRequiredConA(EntityC.builder().build()).build();
        Composition composition = Composition.builder().build();
        entityA2.addToCollectionConA(c9, c10);
        entityA3.addToCollectionConA(c3, c4);
        entityA4.addToCollectionConA(c7, c8);
        composition.setEntityA(entityA2);
        composition.addToEntityAs(entityA3, entityA4);

        assertEquals(2, composition.getEntityAs().size());

        entityA2 = composition.getEntityA().orElseThrow();
        entityA3 = composition.getEntityAs().stream().filter(a -> a.getStringA().orElseThrow().equals("A3")).findFirst().orElseThrow();
        entityA4 = composition.getEntityAs().stream().filter(a -> a.getStringA().orElseThrow().equals("A4")).findFirst().orElseThrow();
        assertEquals("A2", entityA2.getStringA().orElseThrow());
        assertEquals("A3", entityA3.getStringA().orElseThrow());
        assertEquals("A4", entityA4.getStringA().orElseThrow());
        assertEquals(1, entityA3.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C3")).count());
        assertEquals(1, entityA3.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C4")).count());
        assertEquals(1, entityA4.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C7")).count());
        assertEquals(1, entityA4.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C8")).count());
        assertEquals(1, entityA2.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C9")).count());
        assertEquals(1, entityA2.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C10")).count());

        entityA3.removeFromCollectionConA(c3);
        composition.getEntityA().orElseThrow().removeFromCollectionConA(c9);

        assertEquals(0, entityA2.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C9")).count());
        assertEquals(0, entityA3.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C3")).count());

        composition = compositionDao.create(CompositionForCreate.builderFrom(composition).build());

        assertEquals(2, composition.getEntityAs().size());

        entityA2 = composition.getEntityA().orElseThrow();
        entityA3 = composition.getEntityAs().stream().filter(a -> a.getStringA().orElseThrow().equals("A3")).findFirst().orElseThrow();
        entityA4 = composition.getEntityAs().stream().filter(a -> a.getStringA().orElseThrow().equals("A4")).findFirst().orElseThrow();
        assertEquals("A2", entityA2.getStringA().orElseThrow());
        assertEquals("A3", entityA3.getStringA().orElseThrow());
        assertEquals("A4", entityA4.getStringA().orElseThrow());
        assertEquals(1, entityA3.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C4")).count());
        assertEquals(1, entityA4.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C7")).count());
        assertEquals(1, entityA4.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C8")).count());
        assertEquals(1, entityA2.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C10")).count());

        EntityA entityA5 = EntityA.builder().withStringA("AA").withSingleRequiredConA(EntityC.builder().withStringC("CC").build()).build();
        entityA5.addToCollectionConA(EntityC.builder().withStringC("C12").build());
        composition.addToEntityAs(entityA5);
        EntityC c11 = EntityC.builder().withStringC("C11").build();
        entityA2.addToCollectionConA(c11);
        c7 = entityA3.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C4")).findFirst().orElseThrow();
        entityA4.removeFromCollectionConA(c7);

        composition = compositionDao.update(composition);

        assertEquals(3, composition.getEntityAs().size());

        entityA2 = composition.getEntityA().orElseThrow();
        entityA3 = composition.getEntityAs().stream().filter(a -> a.getStringA().orElseThrow().equals("A3")).findFirst().orElseThrow();
        entityA4 = composition.getEntityAs().stream().filter(a -> a.getStringA().orElseThrow().equals("A4")).findFirst().orElseThrow();
        entityA5 = composition.getEntityAs().stream().filter(a -> a.getStringA().orElseThrow().equals("AA")).findFirst().orElseThrow();
        assertEquals("A2", entityA2.getStringA().orElseThrow());
        assertEquals("A3", entityA3.getStringA().orElseThrow());
        assertEquals("A4", entityA4.getStringA().orElseThrow());
        assertEquals("A4", entityA4.getStringA().orElseThrow());
        assertEquals("AA", entityA5.getStringA().orElseThrow());
        assertEquals(1, entityA3.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C4")).count());
        assertEquals(1, entityA4.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C8")).count());
        assertEquals(1, entityA2.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C10")).count());
        assertEquals("CC", entityA5.getSingleRequiredConA().getStringC().orElseThrow());
        assertEquals(1, entityA5.getCollectionConA().stream().filter(c -> c.getStringC().orElseThrow().equals("C12")).count());

        EntityA entityA = entityADao.create(EntityAForCreate.builder().withSingleRequiredConA(EntityCForCreate.builder().build()).build());

        entityA.setCollectionConA(List.of(EntityC.builder().withStringC("C1").build(), EntityC.builder().withStringC("C2").build()));
    }

    @Inject
    ContainerDao containerDao;

    @Inject
    Containment1Dao containment1Dao;

    @Inject
    Containment2Dao containment2Dao;

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-ENT-007"
    })
    @TestCase("DeleteParentWithBidirectionalCompositions")
    void testDeleteParentWithBidirectionalCompositions() {

        Container container = containerDao.create(ContainerForCreate.builder()
                .addToCompCont1(Containment1ForCreate.builder().build())
                .addToCompCont2(Containment2ForCreate.builder().build())
                .build()
        );

        Containment1 containment1 = container.getCompCont1().get(0);
        Containment2 containment2 = container.getCompCont2().get(0);

        containment1Dao.addContainment2(containment1, containment2);

        assertTrue(containment2Dao.queryContainment1(containment2).selectOne().isPresent());
        assertTrue(containment1Dao.queryContainment2(containment1).selectOne().isPresent());

        containerDao.delete(container);

        assertEquals(0, containerDao.query().count());
        assertEquals(0, containment1Dao.query().count());
        assertEquals(0, containment2Dao.query().count());

    }

    @Inject
    EntityJDao entityJDao;

    @Inject
    EntityKDao entityKDao;

    @Inject
    EntityLDao entityLDao;

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-ENT-007"
    })
    @TestCase("DeleteParentWithRequiredRelationBetweenCompositions")
    void testDeleteParentWithRequiredRelationBetweenCompositions() {

        EntityJ j = entityJDao.create(EntityJForCreate.builder().withName("J").withCompK(EntityKForCreate.builder().withName("K").build()).build());

        entityJDao.createCompL(j, EntityLForCreate.builder().withName("L").withRelK(j.getCompK().get()).build());

        entityJDao.delete(j);

        assertEquals(0, entityJDao.query().count());
        assertEquals(0, entityKDao.query().count());
        assertEquals(0, entityLDao.query().count());

    }

}
