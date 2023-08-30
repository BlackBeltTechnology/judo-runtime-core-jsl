package hu.blackbelt.judo.runtime.core.jsl.entity.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entitya.EntityA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityX;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityXDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityY;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityYDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RecursiveCompositionDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeJudoDatasourceByClassExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RecursiveCompositionTest {

    @RegisterExtension
    static JudoRuntimeJudoDatasourceByClassExtension runtimeExtension = new JudoRuntimeJudoDatasourceByClassExtension("RecursiveComposition", new RecursiveCompositionDaoModules());

    @Inject
    EntityXDao entityXDao;

    @Inject
    EntityYDao entityYDao;

    @Inject
    EntityADao entityADao;

    @Inject
    EntityBDao entityBDao;

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-SRV-001"
    })
    void testRecursiveCompositionOnEntity() {

        EntityX x1 = entityXDao.create(EntityX.builder().withName("x1")
                .withX(EntityX.builder().withName("x2")
                        .withX(EntityX.builder().withName("x5").build())
                        .withXs(List.of(EntityX.builder().withName("x3").build(), EntityX.builder().withName("x4")
                                .withY(EntityY.builder().withName("y4").build()).build()))
                        .build())
                .withXs(List.of(EntityX.builder().withName("x6")
                        .withX(EntityX.builder().withName("x7")
                                .withXs(List.of(EntityX.builder().withName("x8").build(), EntityX.builder().withName("x9")
                                        .withYs(List.of(EntityY.builder().withName("y5")
                                                .withYx(EntityX.builder().withName("x10")
                                                        .withYs(List.of(EntityY.builder().withName("y7").build(), EntityY.builder().withName("y8")
                                                                .withYx(EntityX.builder().withName("x13").build()).build()))
                                                        .build()).build(), EntityY.builder().withName("y6")
                                                .withYxs(List.of(EntityX.builder().withName("x11").build(), EntityX.builder().withName("x12")
                                                        .withY(EntityY.builder().withName("y9").build()).build()))
                                                .build()))
                                        .build()))
                                .build())
                        .build()))
                .withY(EntityY.builder().withName("y1").build())
                .withYs(List.of(EntityY.builder().withName("y2").build(), EntityY.builder().withName("y3").build())).build());

        assertEquals("x1", x1.getName().orElseThrow());
        assertEquals("x2", x1.getX().orElseThrow().getName().orElseThrow());
        assertEquals(1, x1.getXs().size());
        assertEquals("x6", x1.getXs().get(0).getName().orElseThrow());
        assertEquals("y1", x1.getY().orElseThrow().getName().orElseThrow());
        assertEquals(2, x1.getYs().size());
        assertTrue(x1.getYs().stream().anyMatch(y -> "y2".equals(y.getName().orElseThrow())));
        assertTrue(x1.getYs().stream().anyMatch(y -> "y3".equals(y.getName().orElseThrow())));

        EntityX x2Test = x1.getX().orElseThrow();

        assertEquals("x5", x2Test.getX().orElseThrow().getName().orElseThrow());
        assertEquals(2, x2Test.getXs().size());
        assertTrue(x2Test.getXs().stream().anyMatch(y -> "x3".equals(y.getName().orElseThrow())));
        assertTrue(x2Test.getXs().stream().anyMatch(y -> "x4".equals(y.getName().orElseThrow())));
        assertFalse(x2Test.getY().isPresent());
        assertEquals(0, x2Test.getYs().size());

        EntityX x6Test = x1.getXs().get(0);
        assertEquals("x7", x6Test.getX().orElseThrow().getName().orElseThrow());
        assertEquals(0, x6Test.getXs().size());
        assertFalse(x6Test.getY().isPresent());
        assertEquals(0, x6Test.getYs().size());

        EntityX x4Test = x2Test.getXs().stream().filter(c -> "x4".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("y4", x4Test.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x4Test.getX().isPresent());
        assertEquals(0, x4Test.getXs().size());
        assertEquals(0, x4Test.getYs().size());

        EntityX x7Test = x6Test.getX().orElseThrow();

        assertEquals(2, x7Test.getXs().size());
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x8".equals(y.getName().orElseThrow())));
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x9".equals(y.getName().orElseThrow())));
        // TODO: JNG-5089
        //assertFalse(x7Test.getX().isPresent());
        assertFalse(x7Test.getY().isPresent());
        assertEquals(0, x7Test.getYs().size());

        EntityX x9Test = x7Test.getXs().stream().filter(c -> "x9".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, x9Test.getYs().size());
        assertTrue(x9Test.getYs().stream().anyMatch(y -> "y5".equals(y.getName().orElseThrow())));
        assertTrue(x9Test.getYs().stream().anyMatch(y -> "y6".equals(y.getName().orElseThrow())));
        assertFalse(x9Test.getX().isPresent());
        assertFalse(x9Test.getY().isPresent());
        assertEquals(0, x9Test.getXs().size());

        EntityY y5Test = x9Test.getYs().stream().filter(c -> "y5".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("x10", y5Test.getYx().orElseThrow().getName().orElseThrow());
        assertEquals(0, y5Test.getYxs().size());

        EntityY y6Test = x9Test.getYs().stream().filter(c -> "y6".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, y6Test.getYxs().size());
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x11".equals(y.getName().orElseThrow())));
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x12".equals(y.getName().orElseThrow())));
        assertFalse(y6Test.getYx().isPresent());

        EntityX x10Test = y5Test.getYx().orElseThrow();

        assertEquals(2, x10Test.getYs().size());
        assertTrue(x10Test.getYs().stream().anyMatch(y -> "y7".equals(y.getName().orElseThrow())));
        assertTrue(x10Test.getYs().stream().anyMatch(y -> "y8".equals(y.getName().orElseThrow())));
        assertFalse(x10Test.getX().isPresent());
        assertFalse(x10Test.getY().isPresent());
        assertEquals(0, x10Test.getXs().size());

        EntityX x12test = y6Test.getYxs().stream().filter(c -> "x12".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("y9", x12test.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x12test.getX().isPresent());
        assertEquals(0, x12test.getXs().size());
        assertEquals(0, x12test.getYs().size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-SRV-001"
    })
    void testRecursiveCompositionOnInheritedEntity() {

        EntityB b1 = entityBDao.create(EntityB.builder().withName("b1")
                .withBa(EntityA.builder().withName("a1")
                        .withA(EntityA.builder().withName("a4").build())
                        .withAs(List.of(EntityA.builder().withName("a5").build(), EntityA.builder().withName("a6").build())).build())
                .withBas(List.of(EntityA.builder().withName("a2")
                        .withA(EntityA.builder().withName("a5").build()).build(), EntityA.builder().withName("a3")
                        .withAs(List.of(EntityA.builder().withName("a5").build(), EntityA.builder().withName("a6").build())).build()))
                .build());

        assertEquals("b1", b1.getName().orElseThrow());
        assertEquals("a1", b1.getBa().orElseThrow().getName().orElseThrow());
        assertEquals(2, b1.getBas().size());
        assertTrue(b1.getBas().stream().anyMatch(c -> "a2".equals(c.getName().orElseThrow())));
        assertTrue(b1.getBas().stream().anyMatch(c -> "a3".equals(c.getName().orElseThrow())));
        assertFalse(b1.getA().isPresent());
        assertEquals(0, b1.getAs().size());

        EntityA a1Test = b1.getBa().orElseThrow();

        assertEquals("a4", a1Test.getA().orElseThrow().getName().orElseThrow());
        assertEquals(2, a1Test.getAs().size());
        assertTrue(a1Test.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a1Test.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));

        EntityA a2Test = b1.getBas().stream().filter(c -> "a2".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("a5", a2Test.getA().orElseThrow().getName().orElseThrow());

        EntityA a3Test = b1.getBas().stream().filter(c -> "a3".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, a3Test.getAs().size());
        assertTrue(a3Test.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a3Test.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));


    }
}
