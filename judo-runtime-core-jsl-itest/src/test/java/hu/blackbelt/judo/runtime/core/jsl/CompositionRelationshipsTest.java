package hu.blackbelt.judo.runtime.core.jsl;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.CompositionRelationshipsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CompositionRelationshipsTest extends AbstractJslTest {
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

    EntityA entityA;
    EntityC singleConA;
    EntityC singleRequiredConA;
    EntityD entityD1;
    EntityD entityD2;


    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);

        entityD1 = entityDDao.create(EntityD.builder()
                .build());
        entityD2 = entityDDao.create(EntityD.builder()
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

    @Override
    public Module getModelDaoModule() {
        return new CompositionRelationshipsDaoModules();
    }

    @Override
    public String getModelName() {
        return "CompositionRelationships";
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
        assertEquals(Optional.of(singleConA), entityADao.querySingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityA.setSingleConA(null);
        entityADao.update(entityA);

        assertEquals(Optional.empty(), entityADao.querySingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
        assertEquals(Optional.empty(), entityCDao.getById(singleConA.identifier()));
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testUnsetOptionalRelationRemovesNested() {
        assertEquals(Optional.of(singleConA), entityADao.querySingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityADao.unsetSingleConA(entityA);

        assertEquals(Optional.empty(), entityADao.querySingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
        assertEquals(Optional.empty(), entityCDao.getById(singleConA.identifier()));
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testDeleteOptionalRelation() {
        assertEquals(Optional.of(singleConA), entityADao.querySingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityCDao.delete(singleConA);

        assertEquals(Optional.empty(), entityADao.querySingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007",
            "REQ-ENT-012"
    })
    void testDeleteRequiredRelationThrowsException() {
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
        assertEquals(Optional.of(entityD1), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.identifier().equals(entityD1.identifier())).findFirst());
        assertEquals(Optional.of(entityD2), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.identifier().equals(entityD2.identifier())).findFirst());
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
        assertEquals(singleRequiredConA, maskedA.getSingleRequiredConA());
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
        assertEquals(Optional.of(singleConA), entityA2.getSingleConA());

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
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4317")
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
}
