package hu.blackbelt.judo.runtime.core.jsl.entity.automappedto;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entitya.EntityAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityX;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityXDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityXIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityY;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityYDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityYIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferaautomappedto.TransferAAutoMappedTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferaautomappedto.TransferAAutoMappedTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferbautomappedto.TransferBAutoMappedTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferbautomappedto.TransferBAutoMappedTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferxautomappedto.TransferXAutoMappedTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferxautomappedto.TransferXAutoMappedTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferyautomappedto.TransferYAutoMappedTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferyautomappedto.TransferYAutoMappedTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RecursiveCompositionDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith({JudoDatasourceByClassExtension.class, JudoRuntimeExtension.class})
public class RecursiveCompositionTest {

    @Inject
    TransferXAutoMappedTODao transferXAutoMappedTODao;

    @Inject
    TransferYAutoMappedTODao transferYAutoMappedTODao;

    @Inject
    EntityXDao entityXDao;

    @Inject
    EntityYDao entityYDao;

    @Inject
    TransferAAutoMappedTODao transferAAutoMappedTODao;

    @Inject
    TransferBAutoMappedTODao transferBAutoMappedTODao;
    
    @Inject
    EntityADao entityADao;

    @Inject
    EntityBDao entityBDao;

    public Module getModelDaoModule() {
        return new RecursiveCompositionDaoModules();
    }

    static public String getModelName() {
        return "RecursiveComposition";
    }

    @BeforeAll
    static public void prepare(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.prepare(getModelName(), datasource);
    }

    @BeforeEach
    protected void init(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.init(getModelDaoModule(),this, datasource);
        fixture.beginTransaction();
    }

    @AfterEach
    protected void tearDown(JudoRuntimeFixture fixture) {
        fixture.tearDown();
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
    void testRecursiveCompositionOnAutoMappedTo() {

        TransferXAutoMappedTO x1 = transferXAutoMappedTODao.create(TransferXAutoMappedTO.builder().withName("x1")
                .withX(TransferXAutoMappedTO.builder().withName("x2")
                        .withX(TransferXAutoMappedTO.builder().withName("x5").build())
                        .withXs(List.of(TransferXAutoMappedTO.builder().withName("x3").build(), TransferXAutoMappedTO.builder().withName("x4")
                                .withY(TransferYAutoMappedTO.builder().withName("y4").build()).build()))
                        .build())
                .withXs(List.of(TransferXAutoMappedTO.builder().withName("x6")
                        .withX(TransferXAutoMappedTO.builder().withName("x7")
                                .withXs(List.of(TransferXAutoMappedTO.builder().withName("x8").build(), TransferXAutoMappedTO.builder().withName("x9")
                                        .withYs(List.of(TransferYAutoMappedTO.builder().withName("y5")
                                                .withYx(TransferXAutoMappedTO.builder().withName("x10")
                                                        .withYs(List.of(TransferYAutoMappedTO.builder().withName("y7").build(), TransferYAutoMappedTO.builder().withName("y8")
                                                                .withYx(TransferXAutoMappedTO.builder().withName("x13").build()).build()))
                                                        .build()).build(), TransferYAutoMappedTO.builder().withName("y6")
                                                .withYxs(List.of(TransferXAutoMappedTO.builder().withName("x11").build(), TransferXAutoMappedTO.builder().withName("x12")
                                                        .withY(TransferYAutoMappedTO.builder().withName("y9").build()).build()))
                                                .build()))
                                        .build()))
                                .build())
                        .build()))
                .withY(TransferYAutoMappedTO.builder().withName("y1").build())
                .withYs(List.of(TransferYAutoMappedTO.builder().withName("y2").build(), TransferYAutoMappedTO.builder().withName("y3").build())).build());

        assertEquals("x1", x1.getName().orElseThrow());
        assertEquals("x2", x1.getX().orElseThrow().getName().orElseThrow());
        assertEquals(1, x1.getXs().size());
        assertEquals("x6", x1.getXs().get(0).getName().orElseThrow());
        assertEquals("y1", x1.getY().orElseThrow().getName().orElseThrow());
        assertEquals(2, x1.getYs().size());
        assertTrue(x1.getYs().stream().anyMatch(y -> "y2".equals(y.getName().orElseThrow())));
        assertTrue(x1.getYs().stream().anyMatch(y -> "y3".equals(y.getName().orElseThrow())));

        EntityX x1EntityX = entityXDao.getById(x1.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("x1", x1EntityX.getName().orElseThrow());
        assertEquals("x2", x1EntityX.getX().orElseThrow().getName().orElseThrow());
        assertEquals(1, x1EntityX.getXs().size());
        assertEquals("x6", x1EntityX.getXs().get(0).getName().orElseThrow());
        assertEquals("y1", x1EntityX.getY().orElseThrow().getName().orElseThrow());
        assertEquals(2, x1EntityX.getYs().size());
        assertTrue(x1EntityX.getYs().stream().anyMatch(y -> "y2".equals(y.getName().orElseThrow())));
        assertTrue(x1EntityX.getYs().stream().anyMatch(y -> "y3".equals(y.getName().orElseThrow())));

        TransferXAutoMappedTO x2Test = x1.getX().orElseThrow();

        assertEquals("x5", x2Test.getX().orElseThrow().getName().orElseThrow());
        assertEquals(2, x2Test.getXs().size());
        assertTrue(x2Test.getXs().stream().anyMatch(y -> "x3".equals(y.getName().orElseThrow())));
        assertTrue(x2Test.getXs().stream().anyMatch(y -> "x4".equals(y.getName().orElseThrow())));
        assertFalse(x2Test.getY().isPresent());
        assertEquals(0, x2Test.getYs().size());

        EntityX x2EntityX = entityXDao.getById(x2Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("x5", x2EntityX.getX().orElseThrow().getName().orElseThrow());
        assertEquals(2, x2EntityX.getXs().size());
        assertTrue(x2EntityX.getXs().stream().anyMatch(y -> "x3".equals(y.getName().orElseThrow())));
        assertTrue(x2EntityX.getXs().stream().anyMatch(y -> "x4".equals(y.getName().orElseThrow())));
        assertFalse(x2EntityX.getY().isPresent());
        assertEquals(0, x2EntityX.getYs().size());

        TransferXAutoMappedTO x6Test = x1.getXs().get(0);

        assertEquals("x7", x6Test.getX().orElseThrow().getName().orElseThrow());
        assertEquals(0, x6Test.getXs().size());
        assertFalse(x6Test.getY().isPresent());
        assertEquals(0, x6Test.getYs().size());

        EntityX x6EntityX = entityXDao.getById(x6Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("x7", x6EntityX.getX().orElseThrow().getName().orElseThrow());
        assertEquals(0, x6EntityX.getXs().size());
        assertFalse(x6EntityX.getY().isPresent());
        assertEquals(0, x6EntityX.getYs().size());

        TransferXAutoMappedTO x4Test = x2Test.getXs().stream().filter(c -> "x4".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("y4", x4Test.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x4Test.getX().isPresent());
        assertEquals(0, x4Test.getXs().size());
        assertEquals(0, x4Test.getYs().size());

        EntityX x4EntityX = entityXDao.getById(x4Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("y4", x4EntityX.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x4EntityX.getX().isPresent());
        assertEquals(0, x4EntityX.getXs().size());
        assertEquals(0, x4EntityX.getYs().size());

        TransferXAutoMappedTO x7Test = x6Test.getX().orElseThrow();

        assertEquals(2, x7Test.getXs().size());
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x8".equals(y.getName().orElseThrow())));
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x9".equals(y.getName().orElseThrow())));
        // TODO: JNG-5089
        //assertFalse(x7Test.getX().isPresent());
        assertFalse(x7Test.getY().isPresent());
        assertEquals(0, x7Test.getYs().size());

        EntityX x7EntityX = entityXDao.getById(x7Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals(2, x7EntityX.getXs().size());
        assertTrue(x7EntityX.getXs().stream().anyMatch(y -> "x8".equals(y.getName().orElseThrow())));
        assertTrue(x7EntityX.getXs().stream().anyMatch(y -> "x9".equals(y.getName().orElseThrow())));
        assertFalse(x7EntityX.getX().isPresent());
        assertFalse(x7EntityX.getY().isPresent());
        assertEquals(0, x7EntityX.getYs().size());

        TransferXAutoMappedTO x9Test = x7Test.getXs().stream().filter(c -> "x9".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, x9Test.getYs().size());
        assertTrue(x9Test.getYs().stream().anyMatch(y -> "y5".equals(y.getName().orElseThrow())));
        assertTrue(x9Test.getYs().stream().anyMatch(y -> "y6".equals(y.getName().orElseThrow())));
        assertFalse(x9Test.getX().isPresent());
        assertFalse(x9Test.getY().isPresent());
        assertEquals(0, x9Test.getXs().size());

        EntityX x9EntityX = entityXDao.getById(x9Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals(2, x9EntityX.getYs().size());
        assertTrue(x9EntityX.getYs().stream().anyMatch(y -> "y5".equals(y.getName().orElseThrow())));
        assertTrue(x9EntityX.getYs().stream().anyMatch(y -> "y6".equals(y.getName().orElseThrow())));
        assertFalse(x9EntityX.getX().isPresent());
        assertFalse(x9EntityX.getY().isPresent());
        assertEquals(0, x9EntityX.getXs().size());

        TransferYAutoMappedTO y5Test = x9Test.getYs().stream().filter(c -> "y5".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("x10", y5Test.getYx().orElseThrow().getName().orElseThrow());
        assertEquals(0, y5Test.getYxs().size());

        EntityY y5EntityY = entityYDao.getById(y5Test.adaptTo(EntityYIdentifier.class)).orElseThrow();

        assertEquals("x10", y5EntityY.getYx().orElseThrow().getName().orElseThrow());
        assertEquals(0, y5EntityY.getYxs().size());

        TransferYAutoMappedTO y6Test = x9Test.getYs().stream().filter(c -> "y6".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, y6Test.getYxs().size());
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x11".equals(y.getName().orElseThrow())));
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x12".equals(y.getName().orElseThrow())));
        assertFalse(y6Test.getYx().isPresent());

        EntityY y6EntityY = entityYDao.getById(y6Test.adaptTo(EntityYIdentifier.class)).orElseThrow();

        assertEquals(2, y6EntityY.getYxs().size());
        assertTrue(y6EntityY.getYxs().stream().anyMatch(y -> "x11".equals(y.getName().orElseThrow())));
        assertTrue(y6EntityY.getYxs().stream().anyMatch(y -> "x12".equals(y.getName().orElseThrow())));
        assertFalse(y6EntityY.getYx().isPresent());

        TransferXAutoMappedTO x10Test = y5Test.getYx().orElseThrow();

        assertEquals(2, x10Test.getYs().size());
        assertTrue(x10Test.getYs().stream().anyMatch(y -> "y7".equals(y.getName().orElseThrow())));
        assertTrue(x10Test.getYs().stream().anyMatch(y -> "y8".equals(y.getName().orElseThrow())));
        assertFalse(x10Test.getX().isPresent());
        assertFalse(x10Test.getY().isPresent());
        assertEquals(0, x10Test.getXs().size());

        EntityX x10EntityX = entityXDao.getById(x10Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals(2, x10EntityX.getYs().size());
        assertTrue(x10EntityX.getYs().stream().anyMatch(y -> "y7".equals(y.getName().orElseThrow())));
        assertTrue(x10EntityX.getYs().stream().anyMatch(y -> "y8".equals(y.getName().orElseThrow())));
        assertFalse(x10EntityX.getX().isPresent());
        assertFalse(x10EntityX.getY().isPresent());
        assertEquals(0, x10EntityX.getXs().size());

        TransferXAutoMappedTO x12test = y6Test.getYxs().stream().filter(c -> "x12".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("y9", x12test.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x12test.getX().isPresent());
        assertEquals(0, x12test.getXs().size());
        assertEquals(0, x12test.getYs().size());

        EntityX x12EntityX = entityXDao.getById(x12test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("y9", x12EntityX.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x12EntityX.getX().isPresent());
        assertEquals(0, x12EntityX.getXs().size());
        assertEquals(0, x12EntityX.getYs().size());
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

        TransferBAutoMappedTO b1 = transferBAutoMappedTODao.create(TransferBAutoMappedTO.builder().withName("b1")
                .withBa(TransferAAutoMappedTO.builder().withName("a1")
                        .withA(TransferAAutoMappedTO.builder().withName("a4").build())
                        .withAs(List.of(TransferAAutoMappedTO.builder().withName("a5").build(), TransferAAutoMappedTO.builder().withName("a6").build())).build())
                .withBas(List.of(TransferAAutoMappedTO.builder().withName("a2")
                        .withA(TransferAAutoMappedTO.builder().withName("a5").build()).build(), TransferAAutoMappedTO.builder().withName("a3")
                        .withAs(List.of(TransferAAutoMappedTO.builder().withName("a5").build(), TransferAAutoMappedTO.builder().withName("a6").build())).build()))
                .build());

        assertEquals("b1", b1.getName().orElseThrow());
        assertEquals("a1", b1.getBa().orElseThrow().getName().orElseThrow());
        assertEquals(2, b1.getBas().size());
        assertTrue(b1.getBas().stream().anyMatch(c -> "a2".equals(c.getName().orElseThrow())));
        assertTrue(b1.getBas().stream().anyMatch(c -> "a3".equals(c.getName().orElseThrow())));
        assertFalse(b1.getA().isPresent());
        assertEquals(0, b1.getAs().size());

        EntityB a1TransferBTO = entityBDao.getById(b1.adaptTo(EntityBIdentifier.class)).orElseThrow();

        assertEquals("b1", a1TransferBTO.getName().orElseThrow());
        assertEquals("a1", a1TransferBTO.getBa().orElseThrow().getName().orElseThrow());
        assertEquals(2, a1TransferBTO.getBas().size());
        assertTrue(a1TransferBTO.getBas().stream().anyMatch(c -> "a2".equals(c.getName().orElseThrow())));
        assertTrue(a1TransferBTO.getBas().stream().anyMatch(c -> "a3".equals(c.getName().orElseThrow())));
        assertFalse(a1TransferBTO.getA().isPresent());
        assertEquals(0, a1TransferBTO.getAs().size());

        TransferAAutoMappedTO a1Test = b1.getBa().orElseThrow();

        assertEquals("a4", a1Test.getA().orElseThrow().getName().orElseThrow());
        assertEquals(2, a1Test.getAs().size());
        assertTrue(a1Test.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a1Test.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));

        EntityA a1TransferAAutoMappedTO = entityADao.getById(a1Test.adaptTo(EntityAIdentifier.class)).orElseThrow();

        assertEquals("a4", a1TransferAAutoMappedTO.getA().orElseThrow().getName().orElseThrow());
        assertEquals(2, a1TransferAAutoMappedTO.getAs().size());
        assertTrue(a1TransferAAutoMappedTO.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a1TransferAAutoMappedTO.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));

        TransferAAutoMappedTO a2Test = b1.getBas().stream().filter(c -> "a2".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("a5", a2Test.getA().orElseThrow().getName().orElseThrow());

        EntityA a2TransferAAutoMappedTO = entityADao.getById(a2Test.adaptTo(EntityAIdentifier.class)).orElseThrow();

        assertEquals("a5", a2TransferAAutoMappedTO.getA().orElseThrow().getName().orElseThrow());

        TransferAAutoMappedTO a3Test = b1.getBas().stream().filter(c -> "a3".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, a3Test.getAs().size());
        assertTrue(a3Test.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a3Test.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));

        EntityA a3TransferAAutoMappedTO = entityADao.getById(a3Test.adaptTo(EntityAIdentifier.class)).orElseThrow();

        assertEquals(2, a3TransferAAutoMappedTO.getAs().size());
        assertTrue(a3TransferAAutoMappedTO.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a3TransferAAutoMappedTO.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));
    }
}
