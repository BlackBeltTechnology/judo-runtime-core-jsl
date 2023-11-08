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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.Abstract;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.abstract_.AbstractDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CBuilder;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.E;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.e.EDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entitya.querysamename.EntityAQuerySameNameParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.entityb.querysamename.EntityBQuerySameNameParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.referenceentity.ReferenceEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.specialcases.specialcases.testentity.TestEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.SpecialCasesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
                ReferenceEntity
                        .builder()
                        .withName("ReferenceEntity")
                        .build()
        );

        EntityA entA = entityADao.create(EntityA.builder().build());
        EntityB entB = entityBDao.create(EntityB.builder().build());

        assertEquals(ref.identifier(), entityADao.queryQuerySameName(entA, EntityAQuerySameNameParameter.builder().withName("ReferenceEntity").build()).orElseThrow().identifier() );
        assertEquals(ref.identifier(), entityBDao.queryQuerySameName(entB, EntityBQuerySameNameParameter.builder().withName("ReferenceEntity").build()).orElseThrow().identifier() );

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
        TestEntity evs1 = testEntityDao.create(TestEntity.builder().build());

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
        Abstract entity = abstractDao.create(Abstract.builder().build());

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


        CBuilder cBuilder1 = C.builder()
                .withCompD(List.of(D.builder()
                                .withCompE(List.of(E.builder().build(), E.builder().build()))
                                .build(),
                        D.builder().build())
                );

        CBuilder cBuilder2 = cBuilder1.withName("Name");

        C c1 = cBuilder1.build();
        C c11 = cBuilder1.build();
        C c2 = cBuilder2.build();

        c1.getCompD().get(0).getCompE().remove(c1.getCompD().get(0).getCompE().get(0));

        assertEquals(1, c1.getCompD().get(0).getCompE().size());
        assertEquals(2, c11.getCompD().get(0).getCompE().size());
        assertEquals(2, c2.getCompD().get(0).getCompE().size());

        cDao.queryCompD(c1).count();

        c2.getCompD().get(0).getCompE().remove(c2.getCompD().get(0).getCompE().get(0));

        assertEquals(1, c1.getCompD().get(0).getCompE().size());
        assertEquals(2, c11.getCompD().get(0).getCompE().size());
        assertEquals(1, c2.getCompD().get(0).getCompE().size());

    }

}
