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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.compositionrelationships.compositionrelationships.entityd.EntityDMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.Abstract;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.AbstractDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.AbstractForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CForCreateBuilder;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.case_.Case;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.case_.CaseDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.case_.CaseForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.class_.ClassDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.class_.Class_;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.class_.Class_ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compmultiupper.CompMultiUpper;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compmultiupper.CompMultiUpperDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compmultiupper.CompMultiUpperForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compmultiupper.CompMultiUpperReference;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compupper.CompUpper;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compupper.CompUpperDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compupper.CompUpperForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.compupper.CompUpperReference;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containertransfer.ContainerTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containertransfer.ContainerTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containertransfer.ContainerTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containertransfer.ContainerTransferMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containment1transfer.Containment1Transfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containment1transfer.Containment1TransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containment1transfer.Containment1TransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containment1transfer.Containment1TransferMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containment2transfer.Containment2Transfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containment2transfer.Containment2TransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.containment2transfer.Containment2TransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.continent.Continent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.DForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.E;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.EDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.EForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.EMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityf.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityg.EntityG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityg.EntityGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityg.EntityGForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.f.F;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.f.FDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.f.FForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.f.FMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.f.queryentity.FQueryEntityParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.f.queryprimitive.FQueryPrimitiveParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.if_.If;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.if_.IfDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.if_.IfForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.primupper.PrimUpper;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.primupper.PrimUpperAttribute;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.primupper.PrimUpperDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.primupper.PrimUpperForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.ref.Ref;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.ref.RefDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.ref.RefForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relmultiupper.RelMultiUpper;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relmultiupper.RelMultiUpperDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relmultiupper.RelMultiUpperForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relmultiupper.RelMultiUpperReference;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relupper.RelUpper;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relupper.RelUpperDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relupper.RelUpperForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.relupper.RelUpperReference;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.static_.Static;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.static_.StaticDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.static_.StaticForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferd.TransferDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transfere.TransferEForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferf.TransferF;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferf.TransferFDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferf.TransferFForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferf.TransferFMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferg.TransferG;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferg.TransferGDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.transferg.TransferGForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.SpecialCasesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.Identifiable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class SpecialCasesTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("SpecialCases", new SpecialCasesDaoModules());

    @Inject
    EntityADao entityADao;

    @Inject
    EntityBDao entityBDao;

    @Inject
    ReferenceEntityDao referenceEntityDao;

    @Inject
    AbstractDao abstractDao;

    @Inject
    TestEntityDao testEntityDao;

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

        ReferenceEntity ref = referenceEntityDao.create(
                ReferenceEntityForCreate
                        .builder()
                        .withName("ReferenceEntity")
                        .build()
        );

        EntityA entA = entityADao.create(EntityAForCreate.builder().build());
        EntityB entB = entityBDao.create(EntityBForCreate.builder().build());

        assertEquals(ref.identifier().getIdentifier(), entityADao.queryQuerySameName(entA).get().identifier().getIdentifier());
        assertEquals(ref.identifier().getIdentifier(), entityBDao.queryQuerySameName(entB).get().identifier().getIdentifier());
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
        TestEntity evs1 = testEntityDao.create(TestEntityForCreate.builder().build());

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

        entityFss = entityFDao.createAll(
                List.of(EntityFForCreate.builder().withStringF("II").build()),
                EntityFMask.entityFMask().withStringF()
        );

        assertEquals(1, entityFss.size());
        assertEquals("II", entityFss.get(0).getStringF());
        assertNull(entityFss.get(0).getMultipleDonF());

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

        entityFs = entityFDao.createAll(
                List.of(entityFForCreate1, entityFForCreate2, entityFForCreate3),
                EntityFMask.entityFMask()
                        .withStringF()
                        .addByName("multipleDonF", EntityDMask.entityDMask().withStringD())
        );

        assertThat(entityFs.stream().map(EntityF::getStringF).toList(), hasItems("F1", "F2", "F3"));
        assertThat(entityFs.stream().flatMap(f -> f.getMultipleDonF().stream()).map(D::getStringD).map(Optional::orElseThrow).toList(),
                hasItems("D1", "D2", "D3", "D4", "D5")
        );
        assertTrue(entityFs.stream().flatMap(f -> f.getMultipleDonF().stream()).map(D::getCompE).allMatch(Objects::isNull));
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

        entityF1 = entityFs.get(0);

        entityF1.setStringF("F1Updated");
        entityF1.setMultipleDonF(List.of(D.builder().withStringD("D1Updated").build()));
        entityF2.setStringF("F2Updated");
        entityF2.setMultipleDonF(List.of(D.builder().withStringD("D2Updated").build(), D.builder().withStringD("D3Updated").build()));
        entityF3.setStringF("F3Updated");
        entityF3.setMultipleDonF(List.of(D.builder().withStringD("D4Updated").build(), D.builder().withStringD("D5Updated").build()));
        entityF3.addToMultipleDonF(D.builder().withStringD("D6Updated").build());

        entityFs = entityFDao.updateAll(entityF1, entityF2, entityF3);

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

        entityF1.setStringF("F1UpdatedMask");
        entityF1.setMultipleDonF(List.of(D.builder().withStringD("D1UpdatedMask").build()));
        entityF2.setStringF("F2UpdatedMask");
        entityF2.setMultipleDonF(List.of(D.builder().withStringD("D2UpdatedMask").build(), D.builder().withStringD("D3UpdatedMask").build()));
        entityF3.setStringF("F3UpdatedMask");
        entityF3.setMultipleDonF(List.of(D.builder().withStringD("D4UpdatedMask").build(), D.builder().withStringD("D5UpdatedMask").build()));
        entityF3.addToMultipleDonF(D.builder().withStringD("D6UpdatedMask").build());

        entityFs = entityFDao.updateAll(List.of(entityF1, entityF2, entityF3), EntityFMask.entityFMask().withStringF());

        assertThat(entityFs.stream().map(EntityF::getStringF).toList(), hasItems("F1UpdatedMask", "F2UpdatedMask", "F3UpdatedMask"));
        assertTrue(entityFs.stream().map(EntityF::getMultipleDonF).allMatch(Objects::isNull));
        assertThat(dDao.query().selectList().stream().map(D::getStringD).map(Optional::orElseThrow).toList(),
                hasItems("D1UpdatedMask", "D2UpdatedMask", "D3UpdatedMask", "D4UpdatedMask", "D5UpdatedMask")
        );
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
                () -> entityFDao.deleteAll(List.of(entityF1final.identifier().getIdentifier(),
                        entityF2final.identifier().getIdentifier(),
                        entityF3final.identifier().getIdentifier())));

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

        transferFss = transferFDao.createAll(
                List.of(TransferFForCreate1, TransferFForCreate2, TransferFForCreate3),
                TransferFMask.transferFMask()
                        .withStringF()
                        .addByName("multipleDonF", EntityDMask.entityDMask().withStringD())
        );

        assertThat(transferFss.stream().map(TransferF::getStringF).toList(), hasItems("F1", "F2", "F3"));
        assertThat(transferFss.stream().flatMap(f -> f.getMultipleDonF().stream()).map(TransferD::getStringD).map(Optional::orElseThrow).toList(),
                hasItems("D1", "D2", "D3", "D4", "D5")
        );
        assertTrue(transferFss.stream().flatMap(f -> f.getMultipleDonF().stream()).map(TransferD::getCompE).allMatch(Objects::isNull));
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

        transferF1 = transferFs.get(0);

        transferF1.setStringF("F1Updated");
        transferF1.setMultipleDonF(List.of(TransferD.builder().withStringD("D1Updated").build()));
        transferF2.setStringF("F2Updated");
        transferF2.setMultipleDonF(List.of(TransferD.builder().withStringD("D2Updated").build(), TransferD.builder().withStringD("D3Updated").build()));
        transferF3.setStringF("F3Updated");
        transferF3.setMultipleDonF(List.of(TransferD.builder().withStringD("D4Updated").build(), TransferD.builder().withStringD("D5Updated").build()));
        transferF3.addToMultipleDonF(TransferD.builder().withStringD("D6Updated").build());

        transferFs = transferFDao.updateAll(transferF1, transferF2, transferF3);

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

        assertEquals(3, entityGDao.countAll());

        transferF1.setStringF("F1UpdatedMask");
        transferF1.setMultipleDonF(List.of(TransferD.builder().withStringD("D1UpdatedMask").build()));
        transferF2.setStringF("F2UpdatedMask");
        transferF2.setMultipleDonF(List.of(TransferD.builder().withStringD("D2UpdatedMask").build(), TransferD.builder().withStringD("D3UpdatedMask").build()));
        transferF3.setStringF("F3UpdatedMask");
        transferF3.setMultipleDonF(List.of(TransferD.builder().withStringD("D4UpdatedMask").build(), TransferD.builder().withStringD("D5UpdatedMask").build()));
        transferF3.addToMultipleDonF(TransferD.builder().withStringD("D6UpdatedMask").build());

        transferFs = transferFDao.updateAll(List.of(transferF1, transferF2, transferF3), TransferFMask.transferFMask().withStringF());

        assertThat(transferFs.stream().map(TransferF::getStringF).toList(), hasItems("F1UpdatedMask", "F2UpdatedMask", "F3UpdatedMask"));
        assertTrue(transferFs.stream().map(TransferF::getMultipleDonF).allMatch(Objects::isNull));
        assertThat(dDao.query().selectList().stream().map(D::getStringD).map(Optional::orElseThrow).toList(),
                hasItems("D1UpdatedMask", "D2UpdatedMask", "D3UpdatedMask", "D4UpdatedMask", "D5UpdatedMask")
        );

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
                () -> transferFDao.deleteAll(List.of(transferF1final.identifier().getIdentifier(),
                        transferF2final.identifier().getIdentifier(),
                        transferF3final.identifier().getIdentifier())));

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

    @Inject
    FDao fDao;
    
    @Test
    @TestCase("UUIDMethods")
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
    public void TestUUIDMethods() {

        E eOpRel = eDao.create(EForCreate.builder().build(), EMask.eMask());
        E eReqRel = eDao.create(EForCreate.builder().build(), EMask.eMask());
        E eColRel1 = eDao.create(EForCreate.builder().build(), EMask.eMask());
        E eColRel2 = eDao.create(EForCreate.builder().build(), EMask.eMask());
        assertNull(eOpRel.getStringE());

        F f = fDao.create(FForCreate.builder()
                .withRelE(eOpRel)
                .withReqRelE(eReqRel)
                .withRelECol(List.of(eColRel1, eColRel2))
                .withCompE(EForCreate.builder().build())
                .withReqCompE(EForCreate.builder().build())
                .withCompECol(List.of(EForCreate.builder().build(), EForCreate.builder().build()))
                .build(),
                FMask.fMask().withCompE(EMask.eMask()).withCompECol(EMask.eMask()).withReqCompE(EMask.eMask())
        );

        E eOpComp = f.getCompE().get();
        E eReqComp = f.getReqCompE();
        E eColComp1 = f.getCompECol().get(0);
        E eColComp2 = f.getCompECol().get(1);

        // queries

        UUID uuidF = (UUID) f.identifier().getIdentifier();

        assertEquals(eOpRel.identifier().getIdentifier(), fDao.queryRelE(uuidF).get().identifier().getIdentifier());
        assertEquals(eReqRel.identifier().getIdentifier(), fDao.queryReqRelE(uuidF).identifier().getIdentifier());
        assertThat(
                List.of(eColRel1.identifier().getIdentifier(), eColRel2.identifier().getIdentifier()),
                containsInAnyOrder(fDao.queryRelECol(uuidF).selectList().stream().map(e -> e.identifier().getIdentifier()).toArray())
        );

        assertEquals(eOpComp.identifier().getIdentifier(), fDao.queryCompE(uuidF).get().identifier().getIdentifier());
        assertEquals(eReqComp.identifier().getIdentifier(), fDao.queryReqCompE(uuidF).identifier().getIdentifier());
        assertThat(
                List.of(eColComp1.identifier().getIdentifier(), eColComp2.identifier().getIdentifier()),
                containsInAnyOrder(fDao.queryCompECol(uuidF).selectList().stream().map(e -> e.identifier().getIdentifier()).toArray())
        );

        assertEquals(1, fDao.queryCalculatedPrimitive(uuidF).get());
        assertEquals(eReqRel.identifier().getIdentifier(), fDao.queryCalculatedEntity(uuidF).get().identifier().getIdentifier());

        assertEquals(3, fDao.queryQueryPrimitive(uuidF, FQueryPrimitiveParameter.builder().withNum(3).build()).get());
        assertEquals(eReqRel.identifier().getIdentifier(), fDao.queryQueryEntity(uuidF, FQueryEntityParameter.builder().withNum(3).build()).get().identifier().getIdentifier());


        // Not related identifier added
        UUID uuidEOpRel = (UUID) eOpRel.identifier().getIdentifier();
        assertFalse(fDao.getById(uuidEOpRel, FMask.fMask()).isPresent());
        assertFalse(eDao.getById(uuidF, EMask.eMask()).isPresent());

        assertFalse(fDao.queryCompE(uuidEOpRel).isPresent());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> fDao.delete(uuidEOpRel)
        );
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("ENTITY_NOT_FOUND")))
        ));

        // delete and getById
        assertTrue(fDao.getById(uuidF, FMask.fMask()).isPresent());

        fDao.delete(uuidF);

        assertFalse(fDao.getById(uuidF, FMask.fMask()).isPresent());
    }

    @Inject
    ClassDao classDao;

    @Inject
    IfDao ifDao;

    @Inject
    CaseDao caseDao;

    @Inject
    StaticDao staticDao;

    @Test
    @TestCase("SafeFieldNames")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
    })
    void testSafeFieldNames() {

        Class_ForCreate.builder()
                .withClass_("name").build();

        If if_ = ifDao.create(IfForCreate.builder()
                .withCase_(CaseForCreate.builder().build())
                .withStatic_(List.of(StaticForCreate.builder().build()))
                .addToStatic_(StaticForCreate.builder().build())
                .build()
        );

        Case case_1 = caseDao.create(CaseForCreate.builder().build());
        Case case_2 = caseDao.create(CaseForCreate.builder().build());

        Class_ForCreate builder = Class_ForCreate.builder()
                .withClass_("name")
                .withSafeName("name")
                .withIf_(if_)
                .withSafeRel(if_)
                .withCase_(List.of(case_1, case_2))
                .build();

        Class_ class_ = classDao.create(builder);

        // Test relation fields
        assertEquals(2, classDao.queryCase_(class_).selectList().size());
        classDao.removeCase_(class_ ,case_1);
        assertEquals(1, classDao.queryCase_(class_).selectList().size());
        classDao.removeCase_(class_ ,case_2);
        assertEquals(0, classDao.queryCase_(class_).selectList().size());
        classDao.addCase_(class_ ,List.of(case_1, case_2));
        assertEquals(2, classDao.queryCase_(class_).selectList().size());
        classDao.createCase_(class_, CaseForCreate.builder().build());
        assertEquals(3, classDao.queryCase_(class_).selectList().size());

        assertEquals(if_.identifier().getIdentifier(), classDao.queryIf_(class_).orElseThrow().identifier().getIdentifier());
        assertEquals(if_.identifier().getIdentifier(), classDao.querySafeRel(class_).orElseThrow().identifier().getIdentifier());
        classDao.unsetIf_(class_);
        assertTrue(classDao.queryIf_(class_).isEmpty());
        classDao.setIf_(class_, if_);
        assertTrue(classDao.queryIf_(class_).isPresent());

        assertEquals("name", class_.getClass_().orElseThrow());
        class_.setClass_("name1");
        assertEquals("name1", class_.getClass_().orElseThrow());
        class_ = classDao.update(class_);
        assertEquals("name1", class_.getClass_().orElseThrow());

        // Test navigation

        assertFalse(classDao.querySafeRel(class_).orElseThrow().getStatic_().isEmpty());
        assertTrue(classDao.querySafeRel(class_).orElseThrow().getCase_().isPresent());

        // Test Composition fields
        assertTrue(if_.getCase_().isPresent());
        assertEquals(2, if_.getStatic_().size());

        if_.setCase_(null);
        if_.setStatic_(List.of());

        if_ = ifDao.update(if_);

        assertFalse(if_.getCase_().isPresent());
        assertTrue(if_.getStatic_().isEmpty());

        if_.addToStatic_(StaticForCreate.builder().build().adaptTo(Static.class));
        assertEquals(1, if_.getStatic_().size());

        if_ = ifDao.update(if_);
        if_.removeFromStatic_(if_.getStatic_().get(0));

        assertEquals(0, if_.getStatic_().size());

    }

    @Inject
    RefDao refDao;

    @Inject
    PrimUpperDao primUpperDao;

    @Inject
    RelUpperDao relUpperDao;

    @Inject
    RelMultiUpperDao relMultiUpperDao;

    @Inject
    CompUpperDao compUpperDao;

    @Inject
    CompMultiUpperDao compMultiUpperDao;

    @Test
    @TestCase("UpperFieldNames")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
    })
    void testUpperFieldNames() {

        PrimUpper primUpper = primUpperDao.create(PrimUpperForCreate.builder()
                .withAbc("Abc")
                .withABd("ABd")
                .withABE("ABE")
                .withAbF("AbF")
                .build()
        );

        assertEquals("Abc", primUpper.getAbc().orElseThrow());
        assertEquals("ABd", primUpper.getABd().orElseThrow());
        assertEquals("ABE", primUpper.getABE().orElseThrow());
        assertEquals("AbF", primUpper.getAbF().orElseThrow());

        primUpper.setAbc(null);
        primUpper.setABd(null);
        primUpper.setABE(null);
        primUpper.setAbF(null);

        primUpper = primUpperDao.update(primUpper);

        assertTrue(primUpper.getAbc().isEmpty());
        assertTrue(primUpper.getABd().isEmpty());
        assertTrue(primUpper.getABE().isEmpty());
        assertTrue(primUpper.getAbF().isEmpty());

        Ref ref = refDao.create(RefForCreate.builder().withName("Name").build());

        RelUpper relUpper = relUpperDao.create(RelUpperForCreate.builder()
                .withAbc(ref)
                .withABd(ref)
                .withABE(ref)
                .withAbF(ref)
                .build()
        );

        assertEquals(ref.identifier().getIdentifier(), relUpperDao.queryAbc(relUpper).orElseThrow().identifier().getIdentifier());
        assertEquals(ref.identifier().getIdentifier(), relUpperDao.queryABd(relUpper).orElseThrow().identifier().getIdentifier());
        assertEquals(ref.identifier().getIdentifier(), relUpperDao.queryABE(relUpper).orElseThrow().identifier().getIdentifier());
        assertEquals(ref.identifier().getIdentifier(), relUpperDao.queryAbF(relUpper).orElseThrow().identifier().getIdentifier());

        assertEquals("Name", relUpperDao.queryAbc(relUpper).orElseThrow().getName().orElseThrow());
        assertEquals("Name", relUpperDao.queryABd(relUpper).orElseThrow().getName().orElseThrow());
        assertEquals("Name", relUpperDao.queryABE(relUpper).orElseThrow().getName().orElseThrow());
        assertEquals("Name", relUpperDao.queryAbF(relUpper).orElseThrow().getName().orElseThrow());

        ref.setName("Name1");
        ref = refDao.update(ref);

        assertEquals("Name1", relUpperDao.queryAbc(relUpper).orElseThrow().getName().orElseThrow());
        assertEquals("Name1", relUpperDao.queryABd(relUpper).orElseThrow().getName().orElseThrow());
        assertEquals("Name1", relUpperDao.queryABE(relUpper).orElseThrow().getName().orElseThrow());
        assertEquals("Name1", relUpperDao.queryAbF(relUpper).orElseThrow().getName().orElseThrow());

        refDao.delete(ref);

        assertTrue(relUpperDao.queryAbc(relUpper).isEmpty());
        assertTrue(relUpperDao.queryABd(relUpper).isEmpty());
        assertTrue(relUpperDao.queryABE(relUpper).isEmpty());
        assertTrue(relUpperDao.queryAbF(relUpper).isEmpty());

        // multi relation

        Ref ref1 = refDao.create(RefForCreate.builder().withName("Ref1").build());
        Ref ref2 = refDao.create(RefForCreate.builder().withName("Ref2").build());

        RelMultiUpper relMultiUpper = relMultiUpperDao.create(RelMultiUpperForCreate.builder()
                .withAbc(List.of(ref1, ref2))
                .withABd(List.of(ref1, ref2))
                .withABE(List.of(ref1, ref2))
                .withAbF(List.of(ref1, ref2))
                .build()
        );

        assertThat(relMultiUpperDao.queryAbc(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier(), ref2.identifier().getIdentifier()));
        assertThat(relMultiUpperDao.queryABd(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier(), ref2.identifier().getIdentifier()));
        assertThat(relMultiUpperDao.queryABE(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier(), ref2.identifier().getIdentifier()));
        assertThat(relMultiUpperDao.queryAbF(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier(), ref2.identifier().getIdentifier()));

        relMultiUpperDao.removeAbc(relMultiUpper, ref2);
        relMultiUpperDao.removeABd(relMultiUpper, ref2);
        relMultiUpperDao.removeABE(relMultiUpper, ref2);
        relMultiUpperDao.removeAbF(relMultiUpper, ref2);

        assertThat(relMultiUpperDao.queryAbc(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier()));
        assertThat(relMultiUpperDao.queryABd(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier()));
        assertThat(relMultiUpperDao.queryABE(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier()));
        assertThat(relMultiUpperDao.queryAbF(relMultiUpper).selectList().stream().map(Ref::identifier).map(Identifiable::getIdentifier).toList(),
                hasItems(ref1.identifier().getIdentifier()));

        refDao.delete(ref1);
        refDao.delete(ref2);

        assertEquals(0, refDao.countAll());

        // comp

        CompUpper compUpper = compUpperDao.create(CompUpperForCreate.builder()
                .withAbc(RefForCreate.builder().withName("Ref1").build())
                .withABd(RefForCreate.builder().withName("Ref2").build())
                .withABE(RefForCreate.builder().withName("Ref3").build())
                .withAbF(RefForCreate.builder().withName("Ref4").build())
                .build()
        );

        assertEquals(4, refDao.countAll());

        assertTrue(compUpper.getAbc().isPresent());
        assertTrue(compUpper.getABd().isPresent());
        assertTrue(compUpper.getABE().isPresent());
        assertTrue(compUpper.getAbF().isPresent());

        assertEquals("Ref1", compUpper.getAbc().orElseThrow().getName().orElseThrow());
        assertEquals("Ref2", compUpper.getABd().orElseThrow().getName().orElseThrow());
        assertEquals("Ref3", compUpper.getABE().orElseThrow().getName().orElseThrow());
        assertEquals("Ref4", compUpper.getAbF().orElseThrow().getName().orElseThrow());

        compUpper.setAbc(null);
        compUpper.setABd(null);
        compUpper.setABE(null);
        compUpper.setAbF(null);

        compUpper = compUpperDao.update(compUpper);

        assertTrue(compUpper.getAbc().isEmpty());
        assertTrue(compUpper.getABd().isEmpty());
        assertTrue(compUpper.getABE().isEmpty());
        assertTrue(compUpper.getAbF().isEmpty());

        compUpperDao.delete(compUpper);
        assertEquals(0, refDao.countAll());

        CompMultiUpper compMultiUpper = compMultiUpperDao.create(CompMultiUpperForCreate.builder()
                .withAbc(List.of(RefForCreate.builder().withName("Ref11").build(), RefForCreate.builder().withName("Ref12").build()))
                .withABd(List.of(RefForCreate.builder().withName("Ref21").build(), RefForCreate.builder().withName("Ref22").build()))
                .withABE(List.of(RefForCreate.builder().withName("Ref31").build(), RefForCreate.builder().withName("Ref32").build()))
                .withAbF(List.of(RefForCreate.builder().withName("Ref41").build(), RefForCreate.builder().withName("Ref42").build()))
                .build()
        );

        assertEquals(8, refDao.countAll());

        assertThat(compMultiUpperDao.queryAbc(compMultiUpper).selectList().stream().map(Ref::getName).map(Optional::orElseThrow).toList(),
                hasItems("Ref11", "Ref12"));
        assertThat(compMultiUpperDao.queryABd(compMultiUpper).selectList().stream().map(Ref::getName).map(Optional::orElseThrow).toList(),
                hasItems("Ref21", "Ref22"));
        assertThat(compMultiUpperDao.queryABE(compMultiUpper).selectList().stream().map(Ref::getName).map(Optional::orElseThrow).toList(),
                hasItems("Ref31", "Ref32"));
        assertThat(compMultiUpperDao.queryAbF(compMultiUpper).selectList().stream().map(Ref::getName).map(Optional::orElseThrow).toList(),
                hasItems("Ref41", "Ref42"));

        refDao.delete(compMultiUpper.getAbc().get(0));
        compMultiUpper = compMultiUpperDao.getById(compMultiUpper.identifier()).orElseThrow();
        compMultiUpper.setABE(List.of());
        compMultiUpper = compMultiUpperDao.update(compMultiUpper);

        assertEquals(5, refDao.countAll());

        assertEquals(1, compMultiUpper.getAbc().size());
        assertEquals(2, compMultiUpper.getABd().size());
        assertEquals(0, compMultiUpper.getABE().size());
        assertEquals(2, compMultiUpper.getAbF().size());

    }

    @Inject
    ContainerTransferDao containerTransferDao;

    @Inject
    Containment1TransferDao containment1TransferDao;

    @Inject
    Containment2TransferDao containment2TransferDao;

    @Test
    @TestCase("DerivedAndTransientRelationAreNotValidated")
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
            "REQ-EXPR-022",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-005",

    })
    void testDerivedAndTransientRelationAreNotValidated() {

        Containment2Transfer c2 = containment2TransferDao.create(Containment2TransferForCreate.builder().withName("C2").build());

        Containment1Transfer c1 = containment1TransferDao.create(Containment1TransferForCreate.builder().withName("C1").withContainmet2(Containment2TransferForCreate.builderFrom(c2).build()).build());

        containerTransferDao.create(ContainerTransferForCreate.builder().withName("Container").build());

        ContainerTransfer container = containerTransferDao.query().filterBy("this.name == 'Container'").maskedBy(ContainerTransferMask.containerTransferMask().withName().withDerivedContainment1(Containment1TransferMask.containment1TransferMask())).selectOne().orElseThrow();

        container = ContainerTransfer.from(identifierRemove(container.toMap(),"derivedContainment1"));

        // Derived doesn't have identifier
        container = containerTransferDao.update(container);

        container.setTransientRel(Containment1Transfer.builder().build());

        // Transient relation doesn't have any required field
        container = containerTransferDao.update(container);

    }

    Map<String, Object> identifierRemove(Map<String, Object> map, String relationName) {
        Map<String, Object> relationMap = (Map<String, Object>) map.get(relationName);
        relationMap.remove("__identifier");
        relationMap.remove("__entityType");
        relationMap.remove("__version");
        return map;
    }

    @Test
    @TestCase("EnumGetByFunctions")
    @Requirement(reqs = {
            "REQ-TYPE-002",
    })
    void testEnumGetByFunctions() {
        assertEquals(Continent.Africa, Continent.getByName("Africa"));
        assertEquals(Continent.Asia, Continent.getByName("Asia"));
        assertEquals(Continent.Europe, Continent.getByName("Europe"));
        assertEquals(Continent.America, Continent.getByName("America"));
        assertEquals(Continent.Antarctica, Continent.getByName("Antarctica"));
        assertEquals(Continent.Australia, Continent.getByName("Australia"));

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Continent.getByName("Arctics"));
        assertEquals("Invalid name: Arctics", thrown.getMessage());


        assertEquals(Continent.Africa, Continent.getByOrdinal(1));
        assertEquals(Continent.Asia, Continent.getByOrdinal(2));
        assertEquals(Continent.Europe, Continent.getByOrdinal(3));
        assertEquals(Continent.America, Continent.getByOrdinal(4));
        assertEquals(Continent.Antarctica, Continent.getByOrdinal(5));
        assertEquals(Continent.Australia, Continent.getByOrdinal(6));

        thrown = assertThrows(IllegalArgumentException.class, () -> Continent.getByOrdinal(69));
        assertEquals("Invalid ordinal: 69", thrown.getMessage());
    }

    @Test
    @TestCase("AttributesAndReferenceGetByNameFunction")
    @Requirement(reqs = {
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
    })
    void testAttributesAndReferenceGetByNameFunction() {
        assertEquals(PrimUpperAttribute.ABC, PrimUpperAttribute.getByName("Abc"));
        assertEquals(PrimUpperAttribute.A_BD, PrimUpperAttribute.getByName("ABd"));
        assertEquals(PrimUpperAttribute.A_B_E, PrimUpperAttribute.getByName("ABE"));
        assertEquals(PrimUpperAttribute.AB_F, PrimUpperAttribute.getByName("AbF"));

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> PrimUpperAttribute.getByName("abc"));
        assertEquals("Invalid name: abc", thrown.getMessage());

        assertEquals(RelUpperReference.ABC, RelUpperReference.getByName("Abc"));
        assertEquals(RelUpperReference.A_BD, RelUpperReference.getByName("ABd"));
        assertEquals(RelUpperReference.A_B_E, RelUpperReference.getByName("ABE"));
        assertEquals(RelUpperReference.AB_F, RelUpperReference.getByName("AbF"));

        thrown = assertThrows(IllegalArgumentException.class, () -> RelUpperReference.getByName("abc"));
        assertEquals("Invalid name: abc", thrown.getMessage());

        assertEquals(RelMultiUpperReference.ABC, RelMultiUpperReference.getByName("Abc"));
        assertEquals(RelMultiUpperReference.A_BD, RelMultiUpperReference.getByName("ABd"));
        assertEquals(RelMultiUpperReference.A_B_E, RelMultiUpperReference.getByName("ABE"));
        assertEquals(RelMultiUpperReference.AB_F, RelMultiUpperReference.getByName("AbF"));

        assertEquals(CompUpperReference.ABC, CompUpperReference.getByName("Abc"));
        assertEquals(CompUpperReference.A_BD, CompUpperReference.getByName("ABd"));
        assertEquals(CompUpperReference.A_B_E, CompUpperReference.getByName("ABE"));
        assertEquals(CompUpperReference.AB_F, CompUpperReference.getByName("AbF"));

        assertEquals(CompMultiUpperReference.ABC, CompMultiUpperReference.getByName("Abc"));
        assertEquals(CompMultiUpperReference.A_BD, CompMultiUpperReference.getByName("ABd"));
        assertEquals(CompMultiUpperReference.A_B_E, CompMultiUpperReference.getByName("ABE"));
        assertEquals(CompMultiUpperReference.AB_F, CompMultiUpperReference.getByName("AbF"));
    }


}
