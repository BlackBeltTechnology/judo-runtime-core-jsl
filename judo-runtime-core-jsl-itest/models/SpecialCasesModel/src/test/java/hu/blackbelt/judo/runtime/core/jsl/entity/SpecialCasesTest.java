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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.associationrelationships.associationrelationships.entitye.EntityEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.Abstract;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.AbstractDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.AbstractForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CForCreateBuilder;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.DForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.EDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.EForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityf.EntityF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityf.EntityFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityf.EntityFForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityf.EntityFIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityg.EntityG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityg.EntityGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityg.EntityGForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferd.TransferDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transfere.TransferEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transfere.TransferEForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferf.TransferF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferf.TransferFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferf.TransferFForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferg.TransferG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferg.TransferGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferg.TransferGForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.SpecialCasesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class SpecialCasesTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("SpecialCases", new SpecialCasesDaoModules());

    @Inject
    EntityADao entityADao;

    @Inject
    EntityBDao entityBDao;

    @Inject
    ReferenceEntityDao referenceDao;

    @Inject
    AbstractDao abstractDao;

    @Inject
    TestEntityDao testDao;

    @Inject
    EntityFDao entityFDao;

    @Inject
    TransferFDao transferFDao;

    @Inject
    EntityGDao entityGDao;

    @Inject
    TransferGDao transferGDao;


    /**
     * This test checks the entities with the same query name declaration.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel SpecialCasesDao.jsl
     *
     *
     * @scenario
     *
     *  Create an ReferenceEntity instance.
     *
     *  Create an EntityA instance.
     *
     *  Create an EntityB instance.
     *
     *  Check the entityA and entityB query with the same name parameter refer to the referenceEntity instance.
     *
     */
    @Test
    @TestCase("EntityWithTheSameQueryName")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-009",
    })
    public void testEntityWithTheSameQueryName() {

        ReferenceEntity ref = referenceDao.create(
                ReferenceEntityForCreate
                        .builder()
                        .withName("ReferenceEntity")
                        .build()
        );

        EntityA entA = entityADao.create(EntityAForCreate.builder().build());
        EntityB entB = entityBDao.create(EntityBForCreate.builder().build());

        assertEquals(ref.identifier(), entityADao.queryQuerySameName(entA).get().identifier());
        assertEquals(ref.identifier(), entityBDao.queryQuerySameName(entB).get().identifier());
    }


    /**
     * This test case checks that a static function of a primitive type is works even if the primitive type isn't
     * appeared in any field definitions in the model.
     *
     * @prerequisites Nothing
     *
     * @type Behaviour
     *
     * @others The JSL model of this test is commented out in the pom.xml because it is  the cause of a build exception.
     *  See: JNG-4617. This test case can be implemented and activated when the bug will be solved.
     *
     *  If the parts of the expression won't evaluate in the "same time". The result of this test case is divergent.
     *  Sometimes OK, sometimes Failed. In this case the equality need to replace with an investigation of the subtraction's result.
     *
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create an EnvVars entity instance (evs1) with the default values.
     *
     *  . Check the values of the following fields of the new entity instance (evs1).
     *      * dateSmaller == true
     *      * timeSmaller == true
     *      * timestampSmaller == true
     *
     *  . The test is passed if all steps have been completed with the specified results.
     */
    @Test
    @TestCase("TC022")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-TYPE-001",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-MDL-001",
            "REQ-MDL-003",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-009",
            "REQ-EXPR-012"
    })
    void testPrimitiveTypeUseOnlyExpression() {
        TestEntity evs1 = testDao.create(TestEntityForCreate.builder().build());

        assertTrue(evs1.getDateSmaller().orElseThrow());
        assertTrue(evs1.getTimeSmaller().orElseThrow());
        assertTrue(evs1.getTimestampSmaller().orElseThrow());
    }

    @Test
    @TestCase("TestEntityThatHasJavaKeywordInItsPackageName")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void TestEntityThatHasJavaKeywordInItsPackageName() {
        Abstract entity = abstractDao.create(AbstractForCreate.builder().build());

        assertEquals(1, abstractDao.countAll());
    }

    @Inject
    CDao cDao;

    @Inject
    DDao dDao;

    @Inject
    EDao eDao;

    @Test
    @TestCase("TestBuilderCopyTheCollectionRecursively")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-007",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void TestBuilderCopyTheCollectionRecursively() {
        
        CForCreateBuilder cBuilder1 = CForCreate.builder()
                .withCompD(List.of(DForCreate.builder()
                                .withCompE(List.of(EForCreate.builder().build(), EForCreate.builder().build()))
                                .build(),
                        DForCreate.builder().build())
                );

        CForCreateBuilder cBuilder2 = cBuilder1.withName("Name");

        CForCreate c1 = cBuilder1.build();
        CForCreate c11 = cBuilder1.build();
        CForCreate c2 = cBuilder2.build();

        c1.getCompD().get(0).getCompE().remove(c1.getCompD().get(0).getCompE().get(0));

        assertEquals(1, c1.getCompD().get(0).getCompE().size());
        assertEquals(2, c11.getCompD().get(0).getCompE().size());
        assertEquals(2, c2.getCompD().get(0).getCompE().size());

        c2.getCompD().get(0).getCompE().remove(c2.getCompD().get(0).getCompE().get(0));

        assertEquals(1, c1.getCompD().get(0).getCompE().size());
        assertEquals(2, c11.getCompD().get(0).getCompE().size());
        assertEquals(1, c2.getCompD().get(0).getCompE().size());

    }

    @Test
    void testCreateAll() {
        List<EntityF> entityFss = entityFDao.createAll(new ArrayList<>());

        assertEquals(0, entityFss.size());

        entityFss = entityFDao.createAll(List.of(EntityFForCreate.builder().withStringF("II").build()));

        assertEquals(1, entityFss.size());
        assertEquals("II", entityFss.get(0).getStringF());

        EntityFForCreate entityFForCreate1 = EntityFForCreate.builder()
                .withStringF("F1")
                .addToMultipleDonF(DForCreate.builder().withStringD("D1").build())
                .build();

        EntityFForCreate entityFForCreate2 = EntityFForCreate.builder()
                .withStringF("F2")
                .addToMultipleDonF(DForCreate.builder().withStringD("D2").build())
                .addToMultipleDonF(DForCreate.builder().withStringD("D3").build())
                .build();

        EntityFForCreate entityFForCreate3 = EntityFForCreate.builder()
                .withStringF("F3")
                .addToMultipleDonF(DForCreate.builder().withStringD("D4").build())
                .addToMultipleDonF(DForCreate.builder().withStringD("D5").build())
                .build();

        List<EntityF> entityFs = entityFDao.createAll(List.of(entityFForCreate1, entityFForCreate2, entityFForCreate3));

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).count());

        EntityF entityF1 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).findFirst().orElseThrow();

        assertEquals(1, entityF1.getMultipleDonF().size());
        assertEquals("D1", entityF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).count());

        EntityF entityF2 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).findFirst().orElseThrow();

        assertEquals(2, entityF2.getMultipleDonF().size());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        EntityF entityF3 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3")).findFirst().orElseThrow();

        assertEquals(2, entityF3.getMultipleDonF().size());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());


        entityFForCreate1 = EntityFForCreate.builder()
                .withStringF("F1")
                .addToMultipleDonF(DForCreate.builder().withStringD("D1").build())
                .build();

        entityFForCreate2 = EntityFForCreate.builder()
                .withStringF("F2")
                .addToMultipleDonF(DForCreate.builder().withStringD("D2").build())
                .addToMultipleDonF(DForCreate.builder().withStringD("D3").build())
                .build();

        entityFForCreate3 = EntityFForCreate.builder()
                .withStringF("F3")
                .addToMultipleDonF(DForCreate.builder().withStringD("D4").build())
                .addToMultipleDonF(DForCreate.builder().withStringD("D5").build())
                .build();

        entityFs = entityFDao.createAll(entityFForCreate1, entityFForCreate2);

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).count());

        entityF1 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).findFirst().orElseThrow();

        assertEquals(1, entityF1.getMultipleDonF().size());
        assertEquals("D1", entityF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).count());

        entityF2 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).findFirst().orElseThrow();

        assertEquals(2, entityF2.getMultipleDonF().size());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        entityFs = entityFDao.createAll(entityFForCreate1, entityFForCreate2, entityFForCreate3);

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).count());

        entityF1 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).findFirst().orElseThrow();

        assertEquals(1, entityF1.getMultipleDonF().size());
        assertEquals("D1", entityF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).count());

        entityF2 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).findFirst().orElseThrow();

        assertEquals(2, entityF2.getMultipleDonF().size());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityF3 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3")).findFirst().orElseThrow();

        assertEquals(2, entityF3.getMultipleDonF().size());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());

        assertEquals(9, entityFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.createAll(EntityFForCreate.builder().withStringF("F1").build(),
                        EntityFForCreate.builder().build(),
                        EntityFForCreate.builder().withStringF("F1").build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo("stringF")))));

        assertEquals(9, entityFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.createAll(List.of(EntityFForCreate.builder().withStringF("F1").build(),
                        EntityFForCreate.builder().build(),
                        EntityFForCreate.builder().withStringF("F1").build())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo("stringF")))));

        assertEquals(9, entityFDao.countAll());
    }

    @Test
    void testUpdateAll() {
        EntityFForCreate entityFForCreate1 = EntityFForCreate.builder()
                .withStringF("F1")
                .addToMultipleDonF(DForCreate.builder().withStringD("D1").build())
                .build();

        EntityFForCreate entityFForCreate2 = EntityFForCreate.builder()
                .withStringF("F2")
                .addToMultipleDonF(DForCreate.builder().withStringD("D2").build())
                .addToMultipleDonF(DForCreate.builder().withStringD("D3").build())
                .build();

        EntityFForCreate entityFForCreate3 = EntityFForCreate.builder()
                .withStringF("F3")
                .addToMultipleDonF(DForCreate.builder().withStringD("D4").build())
                .addToMultipleDonF(DForCreate.builder().withStringD("D5").build())
                .build();

        List<EntityF> entityFs = entityFDao.createAll(List.of(entityFForCreate1, entityFForCreate2, entityFForCreate3));

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).count());

        EntityF entityF1 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).findFirst().orElseThrow();

        assertEquals(1, entityF1.getMultipleDonF().size());
        assertEquals("D1", entityF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).count());

        EntityF entityF2 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).findFirst().orElseThrow();

        assertEquals(2, entityF2.getMultipleDonF().size());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        EntityF entityF3 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3")).findFirst().orElseThrow();

        assertEquals(2, entityF3.getMultipleDonF().size());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());


        entityF1.setStringF("F1Updated");
        entityF1.setMultipleDonF(List.of(D.builder().withStringD("D1Updated").build()));
        entityF2.setStringF("F2Updated");
        entityF2.setMultipleDonF(List.of(D.builder().withStringD("D2Updated").build(), D.builder().withStringD("D3Updated").build()));
        entityF3.setStringF("F3Updated");
        entityF3.setMultipleDonF(List.of(D.builder().withStringD("D4Updated").build(), D.builder().withStringD("D5Updated").build()));
        entityF3.addToMultipleDonF(D.builder().withStringD("D6Updated").build());

        entityFs = entityFDao.updateAll(List.of(entityF1, entityF2, entityF3));

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1Updated")).count());

        entityF1 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1Updated")).findFirst().orElseThrow();

        assertEquals(1, entityF1.getMultipleDonF().size());
        assertEquals("D1Updated", entityF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2Updated")).count());

        entityF2 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2Updated")).findFirst().orElseThrow();

        assertEquals(2, entityF2.getMultipleDonF().size());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2Updated")).count());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3Updated")).count());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3Updated")).count());

        entityF3 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3Updated")).findFirst().orElseThrow();

        assertEquals(3, entityF3.getMultipleDonF().size());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4Updated")).count());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5Updated")).count());
        assertEquals(1, entityF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D6Updated")).count());

        entityF1.setStringF("F1");
        entityF1.setMultipleDonF(List.of(D.builder().withStringD("D1").build()));
        entityF2.setStringF("F2");
        entityF2.setMultipleDonF(List.of(D.builder().withStringD("D2").build(), D.builder().withStringD("D3").build()));
        entityF3.setStringF("F3");
        entityF3.setMultipleDonF(List.of(D.builder().withStringD("D4").build(), D.builder().withStringD("D5").build()));
        entityF3.addToMultipleDonF(D.builder().withStringD("D6").build());

        entityFs = entityFDao.updateAll(entityF1, entityF2);

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).count());

        entityF1 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F1")).findFirst().orElseThrow();

        assertEquals(1, entityF1.getMultipleDonF().size());
        assertEquals("D1", entityF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        assertEquals(1, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).count());

        entityF2 = entityFs.stream().filter(entityF -> entityF.getStringF().equals("F2")).findFirst().orElseThrow();

        assertEquals(2, entityF2.getMultipleDonF().size());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        assertEquals(1, entityF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        assertEquals(0, entityFs.stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityF1.setStringF("F1Updated");
        entityF1.addToMultipleDonF(D.builder().withStringD("D7").build());

        entityFs = entityFDao.updateAll(List.of(entityF1));

        assertEquals(1, entityFs.size());
        assertEquals("F1Updated", entityFs.get(0).getStringF());
        assertEquals(2, entityFs.get(0).getMultipleDonF().size());
        assertEquals(1, entityF1.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D1")).count());
        assertEquals(1, entityF1.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D7")).count());

        entityFs = entityFDao.updateAll(new ArrayList<>());

        assertEquals(0, entityFs.size());

        final EntityG entityG1 = entityGDao.create(EntityGForCreate.builder().withSingleRequiredEonG(EForCreate.builder().build()).build());
        final EntityG entityG2 = entityGDao.create(EntityGForCreate.builder().withSingleRequiredEonG(EForCreate.builder().build()).build());
        final EntityG entityG3 = entityGDao.create(EntityGForCreate.builder().withSingleRequiredEonG(EForCreate.builder().build()).build());

        assertEquals(3, entityGDao.countAll());

        entityG2.setSingleRequiredEonG(null);

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityGDao.updateAll(entityG1,
                        entityG2,
                        entityG3));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEonG")))));

        assertEquals(3, entityGDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> entityGDao.updateAll(List.of(entityG1,
                        entityG2,
                        entityG3)));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEonG")))));

        assertEquals(3, entityGDao.countAll());
    }

    @Test
    void testDeleteAllWithInstances() {
        EntityF entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        EntityF entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        EntityF entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        assertEquals(3, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(new ArrayList<>());

        assertEquals(3, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(List.of(entityF2));

        assertEquals(2, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(List.of(entityF1, entityF3));

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());


        entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());
        EntityF entityF4 = entityFDao.create(EntityFForCreate.builder().withStringF("F4").build());
        EntityF entityF5 = entityFDao.create(EntityFForCreate.builder().withStringF("F5").build());
        EntityF entityF6 = entityFDao.create(EntityFForCreate.builder().withStringF("F6").build());

        assertEquals(6, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityFDao.deleteAll(entityF1, entityF2);

        assertEquals(4, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityFDao.deleteAll(entityF3, entityF4, entityF5, entityF6);

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        assertEquals(3, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(entityF1, entityF2, entityF3);

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        final EntityF entityF1final = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        final EntityF entityF2final = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        final EntityF entityF3final = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        entityFDao.delete(entityF2final);

        assertEquals(2, entityFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.deleteAll(entityF1final,
                        entityF2final,
                        entityF3final));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, entityFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.deleteAll(List.of(entityF1final,
                        entityF2final,
                        entityF3final)));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, entityFDao.countAll());
    }

    @Test
    void testDeleteAllWithIdentifier() {
        EntityF entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        EntityF entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        EntityF entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        assertEquals(3, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(List.of(entityF2.identifier()));

        assertEquals(2, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(List.of(entityF1.identifier(), entityF3.identifier()));

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());


        entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());
        EntityF entityF4 = entityFDao.create(EntityFForCreate.builder().withStringF("F4").build());
        EntityF entityF5 = entityFDao.create(EntityFForCreate.builder().withStringF("F5").build());
        EntityF entityF6 = entityFDao.create(EntityFForCreate.builder().withStringF("F6").build());

        assertEquals(6, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityFDao.deleteAll(entityF1.identifier(), entityF2.identifier());

        assertEquals(4, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityFDao.deleteAll(entityF3.identifier(), entityF4.identifier(), entityF5.identifier(), entityF6.identifier());

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        assertEquals(3, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(entityF1.identifier(), entityF2.identifier(), entityF3.identifier());

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        final EntityF entityF1final = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        final EntityF entityF2final = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        final EntityF entityF3final = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        entityFDao.delete(entityF2final);

        assertEquals(2, entityFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.deleteAll(entityF1final,
                        entityF2final,
                        entityF3final));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, entityFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.deleteAll(List.of(entityF1final.identifier(),
                        entityF2final.identifier(),
                        entityF3final.identifier())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, entityFDao.countAll());
    }

    @Test
    void testDeleteAllWithUUIDs() {
        EntityF entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        EntityF entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        EntityF entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        assertEquals(3, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(List.of(entityF2.identifier().getIdentifier()));

        assertEquals(2, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll(List.of(entityF1.identifier().getIdentifier(), entityF3.identifier().getIdentifier()));

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());


        entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());
        EntityF entityF4 = entityFDao.create(EntityFForCreate.builder().withStringF("F4").build());
        EntityF entityF5 = entityFDao.create(EntityFForCreate.builder().withStringF("F5").build());
        EntityF entityF6 = entityFDao.create(EntityFForCreate.builder().withStringF("F6").build());

        assertEquals(6, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityFDao.deleteAll((UUID) entityF1.identifier().getIdentifier(),(UUID) entityF2.identifier().getIdentifier());

        assertEquals(4, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityFDao.deleteAll((UUID) entityF3.identifier().getIdentifier(), (UUID) entityF4.identifier().getIdentifier(), (UUID) entityF5.identifier().getIdentifier(), (UUID) entityF6.identifier().getIdentifier());

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F4")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F5")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F6")).count());

        entityF1 = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        entityF2 = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        entityF3 = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        assertEquals(3, entityFDao.countAll());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(1, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        entityFDao.deleteAll((UUID) entityF1.identifier().getIdentifier(), (UUID) entityF2.identifier().getIdentifier(), (UUID) entityF3.identifier().getIdentifier());

        assertEquals(0, entityFDao.countAll());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F1")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F2")).count());
        assertEquals(0, entityFDao.getAll().stream().filter(entityF -> entityF.getStringF().equals("F3")).count());

        final EntityF entityF1final = entityFDao.create(EntityFForCreate.builder().withStringF("F1").build());
        final EntityF entityF2final = entityFDao.create(EntityFForCreate.builder().withStringF("F2").build());
        final EntityF entityF3final = entityFDao.create(EntityFForCreate.builder().withStringF("F3").build());

        entityFDao.delete(entityF2final);

        assertEquals(2, entityFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.deleteAll((UUID) entityF1final.identifier().getIdentifier(),
                        (UUID) entityF2final.identifier().getIdentifier(),
                        (UUID) entityF3final.identifier().getIdentifier()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, entityFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> entityFDao.deleteAll(List.of(entityF1final.identifier().getIdentifier(),
                        entityF2final.identifier().getIdentifier(),
                        entityF3final.identifier().getIdentifier())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, entityFDao.countAll());
    }

    @Test
    void testCreateAllOnTransfer() {
        List<TransferF> transferFss = transferFDao.createAll(new ArrayList<>());

        Assertions.assertEquals(0, transferFss.size());

        transferFss = transferFDao.createAll(List.of(TransferFForCreate.builder().withStringF("II").build()));

        Assertions.assertEquals(1, transferFss.size());
        Assertions.assertEquals("II", transferFss.get(0).getStringF());

        TransferFForCreate TransferFForCreate1 = TransferFForCreate.builder()
                .withStringF("F1")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D1").build())
                .build();

        TransferFForCreate TransferFForCreate2 = TransferFForCreate.builder()
                .withStringF("F2")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D2").build())
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D3").build())
                .build();

        TransferFForCreate TransferFForCreate3 = TransferFForCreate.builder()
                .withStringF("F3")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D4").build())
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D5").build())
                .build();

        List<TransferF> transferFs = transferFDao.createAll(List.of(TransferFForCreate1, TransferFForCreate2, TransferFForCreate3));

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).count());

        TransferF transferF1 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferF1.getMultipleDonF().size());
        Assertions.assertEquals("D1", transferF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).count());

        TransferF transferF2 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF2.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        TransferF transferF3 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF3.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());

        EntityF entityE1 = entityFDao.getById(transferF1.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonF().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonF().get(0).getStringD().orElseThrow());

        EntityF entityE2 = entityFDao.getById(transferF2.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        EntityF entityE3 = entityFDao.getById(transferF3.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE3.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());


        TransferFForCreate1 = TransferFForCreate.builder()
                .withStringF("F1")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D1").build())
                .build();

        TransferFForCreate2 = TransferFForCreate.builder()
                .withStringF("F2")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D2").build())
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D3").build())
                .build();

        TransferFForCreate3 = TransferFForCreate.builder()
                .withStringF("F3")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D4").build())
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D5").build())
                .build();

        transferFs = transferFDao.createAll(TransferFForCreate1, TransferFForCreate2);

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).count());

        transferF1 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferF1.getMultipleDonF().size());
        Assertions.assertEquals("D1", transferF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).count());

        transferF2 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF2.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        entityE1 = entityFDao.getById(transferF1.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonF().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonF().get(0).getStringD().orElseThrow());

        entityE2 = entityFDao.getById(transferF2.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        transferFs = transferFDao.createAll(TransferFForCreate1, TransferFForCreate2, TransferFForCreate3);

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).count());

        transferF1 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferF1.getMultipleDonF().size());
        Assertions.assertEquals("D1", transferF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).count());

        transferF2 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF2.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        transferF3 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF3.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());

        entityE1 = entityFDao.getById(transferF1.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonF().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonF().get(0).getStringD().orElseThrow());

        entityE2 = entityFDao.getById(transferF2.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        entityE3 = entityFDao.getById(transferF3.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE3.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());

        Assertions.assertEquals(9, transferFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.createAll(TransferFForCreate.builder().withStringF("F1").build(),
                        TransferFForCreate.builder().build(),
                        TransferFForCreate.builder().withStringF("F1").build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo("stringF")))));

        Assertions.assertEquals(9, transferFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.createAll(List.of(TransferFForCreate.builder().withStringF("F1").build(),
                        TransferFForCreate.builder().build(),
                        TransferFForCreate.builder().withStringF("F1").build())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo("stringF")))));

        Assertions.assertEquals(9, transferFDao.countAll());
    }

    @Test
    void testUpdateAllOnTransfer() {
        TransferFForCreate TransferFForCreate1 = TransferFForCreate.builder()
                .withStringF("F1")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D1").build())
                .build();

        TransferFForCreate TransferFForCreate2 = TransferFForCreate.builder()
                .withStringF("F2")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D2").build())
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D3").build())
                .build();

        TransferFForCreate TransferFForCreate3 = TransferFForCreate.builder()
                .withStringF("F3")
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D4").build())
                .addToMultipleDonF(TransferDForCreate.builder().withStringD("D5").build())
                .build();

        List<TransferF> transferFs = transferFDao.createAll(List.of(TransferFForCreate1, TransferFForCreate2, TransferFForCreate3));

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).count());

        TransferF transferF1 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferF1.getMultipleDonF().size());
        Assertions.assertEquals("D1", transferF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).count());

        TransferF transferF2 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF2.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        TransferF transferF3 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF3.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());

        EntityF entityE1 = entityFDao.getById(transferF1.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonF().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonF().get(0).getStringD().orElseThrow());

        EntityF entityE2 = entityFDao.getById(transferF2.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        EntityF entityE3 = entityFDao.getById(transferF3.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE3.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5")).count());


        transferF1.setStringF("F1Updated");
        transferF1.setMultipleDonF(List.of(TransferD.builder().withStringD("D1Updated").build()));
        transferF2.setStringF("F2Updated");
        transferF2.setMultipleDonF(List.of(TransferD.builder().withStringD("D2Updated").build(), TransferD.builder().withStringD("D3Updated").build()));
        transferF3.setStringF("F3Updated");
        transferF3.setMultipleDonF(List.of(TransferD.builder().withStringD("D4Updated").build(), TransferD.builder().withStringD("D5Updated").build()));
        transferF3.addToMultipleDonF(TransferD.builder().withStringD("D6Updated").build());

        transferFs = transferFDao.updateAll(List.of(transferF1, transferF2, transferF3));

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1Updated")).count());

        transferF1 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1Updated")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferF1.getMultipleDonF().size());
        Assertions.assertEquals("D1Updated", transferF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2Updated")).count());

        transferF2 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2Updated")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF2.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2Updated")).count());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3Updated")).count());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3Updated")).count());

        transferF3 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3Updated")).findFirst().orElseThrow();

        Assertions.assertEquals(3, transferF3.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4Updated")).count());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5Updated")).count());
        Assertions.assertEquals(1, transferF3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D6Updated")).count());

        entityE1 = entityFDao.getById(transferF1.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonF().size());
        Assertions.assertEquals("D1Updated", entityE1.getMultipleDonF().get(0).getStringD().orElseThrow());

        entityE2 = entityFDao.getById(transferF2.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2Updated")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3Updated")).count());


        entityE3 = entityFDao.getById(transferF3.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(3, entityE3.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D4Updated")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D5Updated")).count());
        Assertions.assertEquals(1, entityE3.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D6Updated")).count());

        transferF1.setStringF("F1");
        transferF1.setMultipleDonF(List.of(TransferD.builder().withStringD("D1").build()));
        transferF2.setStringF("F2");
        transferF2.setMultipleDonF(List.of(TransferD.builder().withStringD("D2").build(), TransferD.builder().withStringD("D3").build()));
        transferF3.setStringF("F3");
        transferF3.setMultipleDonF(List.of(TransferD.builder().withStringD("D4").build(), TransferD.builder().withStringD("D5").build()));
        transferF3.addToMultipleDonF(TransferD.builder().withStringD("D6").build());

        transferFs = transferFDao.updateAll(transferF1, transferF2);

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).count());

        transferF1 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F1")).findFirst().orElseThrow();

        Assertions.assertEquals(1, transferF1.getMultipleDonF().size());
        Assertions.assertEquals("D1", transferF1.getMultipleDonF().get(0).getStringD().orElseThrow());

        Assertions.assertEquals(1, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).count());

        transferF2 = transferFs.stream().filter(transferF -> transferF.getStringF().equals("F2")).findFirst().orElseThrow();

        Assertions.assertEquals(2, transferF2.getMultipleDonF().size());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, transferF2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        Assertions.assertEquals(0, transferFs.stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        entityE1 = entityFDao.getById(transferF1.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonF().size());
        Assertions.assertEquals("D1", entityE1.getMultipleDonF().get(0).getStringD().orElseThrow());

        entityE2 = entityFDao.getById(transferF2.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(2, entityE2.getMultipleDonF().size());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D2")).count());
        Assertions.assertEquals(1, entityE2.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D3")).count());

        transferF1.setStringF("F1Updated");
        transferF1.addToMultipleDonF(TransferD.builder().withStringD("D7").build());

        transferFs = transferFDao.updateAll(List.of(transferF1));

        Assertions.assertEquals(1, transferFs.size());
        Assertions.assertEquals("F1Updated", transferFs.get(0).getStringF());
        Assertions.assertEquals(2, transferFs.get(0).getMultipleDonF().size());
        Assertions.assertEquals(1, transferF1.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D1")).count());
        Assertions.assertEquals(1, transferF1.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D7")).count());

        entityE1 = entityFDao.getById(transferF1.identifier().adaptTo(EntityFIdentifier.class)).orElseThrow();

        Assertions.assertEquals(1, entityE1.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D1")).count());
        Assertions.assertEquals(1, entityE1.getMultipleDonF().stream().filter(entityD -> entityD.getStringD().orElseThrow().equals("D7")).count());

        transferFs = transferFDao.updateAll(new ArrayList<>());

        Assertions.assertEquals(0, transferFs.size());

        final TransferG transferG1 = transferGDao.create(TransferGForCreate.builder().withSingleRequiredEonG(TransferEForCreate.builder().build()).build());
        final TransferG transferG2 = transferGDao.create(TransferGForCreate.builder().withSingleRequiredEonG(TransferEForCreate.builder().build()).build());
        final TransferG transferG3 = transferGDao.create(TransferGForCreate.builder().withSingleRequiredEonG(TransferEForCreate.builder().build()).build());

        Assertions.assertEquals(3, transferGDao.countAll());

        transferG2.setSingleRequiredEonG(null);

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferGDao.updateAll(transferG1,
                        transferG2,
                        transferG3));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEonG")))));

        Assertions.assertEquals(3, transferGDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferGDao.updateAll(List.of(transferG1,
                        transferG2,
                        transferG3)));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEonG")))));

        Assertions.assertEquals(3, transferGDao.countAll());
    }

    @Test
    void testDeleteAllWithInstancesOnTransfer() {
        TransferF transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        TransferF transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        TransferF transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(new ArrayList<>());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(List.of(transferF2));

        Assertions.assertEquals(2, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(2, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(List.of(transferF1, transferF3));

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());


        transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());
        TransferF transferF4 = transferFDao.create(TransferFForCreate.builder().withStringF("F4").build());
        TransferF transferF5 = transferFDao.create(TransferFForCreate.builder().withStringF("F5").build());
        TransferF transferF6 = transferFDao.create(TransferFForCreate.builder().withStringF("F6").build());

        Assertions.assertEquals(6, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(6, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferFDao.deleteAll(transferF1, transferF2);

        Assertions.assertEquals(4, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(4, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferFDao.deleteAll(transferF3, transferF4, transferF5, transferF6);

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(transferF1, transferF2, transferF3);

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        final TransferF transferF1final = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        final TransferF transferF2final = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        final TransferF transferF3final = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        transferFDao.delete(transferF2final);

        assertEquals(2, transferFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.deleteAll(transferF1final,
                        transferF2final,
                        transferF3final));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.deleteAll(List.of(transferF1final,
                        transferF2final,
                        transferF3final)));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferFDao.countAll());
    }

    @Test
    void testDeleteAllWithIdentifierOnTransfer() {
        TransferF transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        TransferF transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        TransferF transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(new ArrayList<>());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(List.of(transferF2.identifier()));

        Assertions.assertEquals(2, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(2, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(List.of(transferF1.identifier(), transferF3.identifier()));

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());


        transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());
        TransferF transferF4 = transferFDao.create(TransferFForCreate.builder().withStringF("F4").build());
        TransferF transferF5 = transferFDao.create(TransferFForCreate.builder().withStringF("F5").build());
        TransferF transferF6 = transferFDao.create(TransferFForCreate.builder().withStringF("F6").build());

        Assertions.assertEquals(6, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(6, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferFDao.deleteAll(transferF1.identifier(), transferF2.identifier());

        Assertions.assertEquals(4, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(4, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferFDao.deleteAll(transferF3.identifier(), transferF4.identifier(), transferF5.identifier(), transferF6.identifier());

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(transferF1.identifier(), transferF2.identifier(), transferF3.identifier());

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        final TransferF transferF1final = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        final TransferF transferF2final = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        final TransferF transferF3final = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        transferFDao.delete(transferF2final);

        assertEquals(2, transferFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.deleteAll(transferF1final.identifier(),
                        transferF2final.identifier(),
                        transferF3final.identifier()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.deleteAll(List.of(transferF1final.identifier(),
                        transferF2final.identifier(),
                        transferF3final.identifier())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferFDao.countAll());
    }

    @Test
    void testDeleteAllWithUUIDsOnTransfer() {
        TransferF transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        TransferF transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        TransferF transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(new ArrayList<>());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(List.of(transferF2.identifier().getIdentifier()));

        Assertions.assertEquals(2, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(2, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll(List.of(transferF1.identifier().getIdentifier(), transferF3.identifier().getIdentifier()));

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());


        transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());
        TransferF transferF4 = transferFDao.create(TransferFForCreate.builder().withStringF("F4").build());
        TransferF transferF5 = transferFDao.create(TransferFForCreate.builder().withStringF("F5").build());
        TransferF transferF6 = transferFDao.create(TransferFForCreate.builder().withStringF("F6").build());

        Assertions.assertEquals(6, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(6, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferFDao.deleteAll((UUID) transferF1.identifier().getIdentifier(), (UUID) transferF2.identifier().getIdentifier());

        Assertions.assertEquals(4, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(4, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferFDao.deleteAll((UUID) transferF3.identifier().getIdentifier()
                , (UUID) transferF4.identifier().getIdentifier()
                , (UUID) transferF5.identifier().getIdentifier()
                , (UUID) transferF6.identifier().getIdentifier());

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F4")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F5")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F6")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F4")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F5")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F6")).count());

        transferF1 = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        transferF2 = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        transferF3 = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        Assertions.assertEquals(3, transferFDao.countAll());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(3, entityFDao.countAll());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(1, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        transferFDao.deleteAll((UUID) transferF1.identifier().getIdentifier(), (UUID) transferF2.identifier().getIdentifier(), (UUID) transferF3.identifier().getIdentifier());

        Assertions.assertEquals(0, transferFDao.countAll());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, transferFDao.getAll().stream().filter(transferF -> transferF.getStringF().equals("F3")).count());

        Assertions.assertEquals(0, entityFDao.countAll());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F1")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F2")).count());
        Assertions.assertEquals(0, entityFDao.getAll().stream().filter(entityE -> entityE.getStringF().equals("F3")).count());

        final TransferF transferF1final = transferFDao.create(TransferFForCreate.builder().withStringF("F1").build());
        final TransferF transferF2final = transferFDao.create(TransferFForCreate.builder().withStringF("F2").build());
        final TransferF transferF3final = transferFDao.create(TransferFForCreate.builder().withStringF("F3").build());

        transferFDao.delete(transferF2final);

        assertEquals(2, transferFDao.countAll());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.deleteAll((UUID) transferF1final.identifier().getIdentifier(),
                        (UUID) transferF2final.identifier().getIdentifier(),
                        (UUID) transferF3final.identifier().getIdentifier()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferFDao.countAll());

        thrown = assertThrows(
                ValidationException.class,
                () -> transferFDao.deleteAll(List.of(transferF1final.identifier().getIdentifier(),
                        transferF2final.identifier().getIdentifier(),
                        transferF3final.identifier().getIdentifier())));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))));

        assertEquals(2, transferFDao.countAll());
    }

}
