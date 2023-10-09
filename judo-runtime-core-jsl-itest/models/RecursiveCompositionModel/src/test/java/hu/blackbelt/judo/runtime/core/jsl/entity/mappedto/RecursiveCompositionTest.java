package hu.blackbelt.judo.runtime.core.jsl.entity.mappedto;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferato.TransferATO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferato.TransferATODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferbto.TransferBTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferbto.TransferBTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferxto.TransferXTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferxto.TransferXTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferyto.TransferYTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferyto.TransferYTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RecursiveCompositionDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RecursiveCompositionTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("RecursiveComposition", new RecursiveCompositionDaoModules());

    @Inject
    TransferXTODao transferXTODao;

    @Inject
    TransferYTODao transferYTODao;


    @Inject
    EntityXDao entityXDao;

    @Inject
    EntityYDao entityYDao;

    @Inject
    TransferATODao transferATODao;

    @Inject
    TransferBTODao transferBTODao;


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
            "REQ-ENT-007",
            "REQ-SRV-002"
    })
    void testRecursiveCompositionOnMappedTo() {

        TransferXTO x1 = transferXTODao.create(TransferXTO.builder().withName("x1")
                .withX(TransferXTO.builder().withName("x2")
                        .withX(TransferXTO.builder().withName("x5").build())
                        .withXs(List.of(TransferXTO.builder().withName("x3").build(), TransferXTO.builder().withName("x4")
                                .withY(TransferYTO.builder().withName("y4").build()).build()))
                        .build())
                .withXs(List.of(TransferXTO.builder().withName("x6")
                        .withX(TransferXTO.builder().withName("x7")
                                .withXs(List.of(TransferXTO.builder().withName("x8").build(), TransferXTO.builder().withName("x9")
                                        .withYs(List.of(TransferYTO.builder().withName("y5")
                                                .withYx(TransferXTO.builder().withName("x10")
                                                        .withYs(List.of(TransferYTO.builder().withName("y7").build(), TransferYTO.builder().withName("y8")
                                                                .withYx(TransferXTO.builder().withName("x13").build()).build()))
                                                        .build()).build(), TransferYTO.builder().withName("y6")
                                                .withYxs(List.of(TransferXTO.builder().withName("x11").build(), TransferXTO.builder().withName("x12")
                                                        .withY(TransferYTO.builder().withName("y9").build()).build()))
                                                .build()))
                                        .build()))
                                .build())
                        .build()))
                .withY(TransferYTO.builder().withName("y1").build())
                .withYs(List.of(TransferYTO.builder().withName("y2").build(), TransferYTO.builder().withName("y3").build())).build());

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

        TransferXTO x2Test = x1.getX().orElseThrow();

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

        TransferXTO x6Test = x1.getXs().get(0);

        assertEquals("x7", x6Test.getX().orElseThrow().getName().orElseThrow());
        assertEquals(0, x6Test.getXs().size());
        assertFalse(x6Test.getY().isPresent());
        assertEquals(0, x6Test.getYs().size());

        EntityX x6EntityX = entityXDao.getById(x6Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("x7", x6EntityX.getX().orElseThrow().getName().orElseThrow());
        assertEquals(0, x6EntityX.getXs().size());
        assertFalse(x6EntityX.getY().isPresent());
        assertEquals(0, x6EntityX.getYs().size());

        TransferXTO x4Test = x2Test.getXs().stream().filter(c -> "x4".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("y4", x4Test.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x4Test.getX().isPresent());
        assertEquals(0, x4Test.getXs().size());
        assertEquals(0, x4Test.getYs().size());

        EntityX x4EntityX = entityXDao.getById(x4Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("y4", x4EntityX.getY().orElseThrow().getName().orElseThrow());
        assertFalse(x4EntityX.getX().isPresent());
        assertEquals(0, x4EntityX.getXs().size());
        assertEquals(0, x4EntityX.getYs().size());

        TransferXTO x7Test = x6Test.getX().orElseThrow();

        assertEquals(2, x7Test.getXs().size());
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x8".equals(y.getName().orElseThrow())));
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x9".equals(y.getName().orElseThrow())));
        assertFalse(x7Test.getX().isPresent());
        assertFalse(x7Test.getY().isPresent());
        assertEquals(0, x7Test.getYs().size());

        EntityX x7EntityX = entityXDao.getById(x7Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals(2, x7EntityX.getXs().size());
        assertTrue(x7EntityX.getXs().stream().anyMatch(y -> "x8".equals(y.getName().orElseThrow())));
        assertTrue(x7EntityX.getXs().stream().anyMatch(y -> "x9".equals(y.getName().orElseThrow())));
        assertFalse(x7EntityX.getX().isPresent());
        assertFalse(x7EntityX.getY().isPresent());
        assertEquals(0, x7EntityX.getYs().size());

        TransferXTO x9Test = x7Test.getXs().stream().filter(c -> "x9".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

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

        TransferYTO y5Test = x9Test.getYs().stream().filter(c -> "y5".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("x10", y5Test.getYx().orElseThrow().getName().orElseThrow());
        assertEquals(0, y5Test.getYxs().size());

        EntityY y5EntityY = entityYDao.getById(y5Test.adaptTo(EntityYIdentifier.class)).orElseThrow();

        assertEquals("x10", y5EntityY.getYx().orElseThrow().getName().orElseThrow());
        assertEquals(0, y5EntityY.getYxs().size());

        TransferYTO y6Test = x9Test.getYs().stream().filter(c -> "y6".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, y6Test.getYxs().size());
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x11".equals(y.getName().orElseThrow())));
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x12".equals(y.getName().orElseThrow())));
        assertFalse(y6Test.getYx().isPresent());

        EntityY y6EntityY = entityYDao.getById(y6Test.adaptTo(EntityYIdentifier.class)).orElseThrow();

        assertEquals(2, y6EntityY.getYxs().size());
        assertTrue(y6EntityY.getYxs().stream().anyMatch(y -> "x11".equals(y.getName().orElseThrow())));
        assertTrue(y6EntityY.getYxs().stream().anyMatch(y -> "x12".equals(y.getName().orElseThrow())));
        assertFalse(y6EntityY.getYx().isPresent());

        TransferXTO x10Test = y5Test.getYx().orElseThrow();

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

        TransferXTO x12test = y6Test.getYxs().stream().filter(c -> "x12".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

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
            "REQ-ENT-007",
            "REQ-SRV-002"
    })
    void testRecursiveCompositionOnInheritedEntity() {

        TransferBTO b1 = transferBTODao.create(TransferBTO.builder().withName("b1")
                .withBa(TransferATO.builder().withName("a1")
                        .withA(TransferATO.builder().withName("a4").build())
                        .withAs(List.of(TransferATO.builder().withName("a5").build(), TransferATO.builder().withName("a6").build())).build())
                .withBas(List.of(TransferATO.builder().withName("a2")
                        .withA(TransferATO.builder().withName("a5").build()).build(), TransferATO.builder().withName("a3")
                        .withAs(List.of(TransferATO.builder().withName("a5").build(), TransferATO.builder().withName("a6").build())).build()))
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

        TransferATO a1Test = b1.getBa().orElseThrow();

        assertEquals("a4", a1Test.getA().orElseThrow().getName().orElseThrow());
        assertEquals(2, a1Test.getAs().size());
        assertTrue(a1Test.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a1Test.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));

        EntityA a1TransferATO = entityADao.getById(a1Test.adaptTo(EntityAIdentifier.class)).orElseThrow();

        assertEquals("a4", a1TransferATO.getA().orElseThrow().getName().orElseThrow());
        assertEquals(2, a1TransferATO.getAs().size());
        assertTrue(a1TransferATO.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a1TransferATO.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));

        TransferATO a2Test = b1.getBas().stream().filter(c -> "a2".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals("a5", a2Test.getA().orElseThrow().getName().orElseThrow());

        EntityA a2TransferATO = entityADao.getById(a2Test.adaptTo(EntityAIdentifier.class)).orElseThrow();

        assertEquals("a5", a2TransferATO.getA().orElseThrow().getName().orElseThrow());

        TransferATO a3Test = b1.getBas().stream().filter(c -> "a3".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, a3Test.getAs().size());
        assertTrue(a3Test.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a3Test.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));

        EntityA a3TransferATO = entityADao.getById(a3Test.adaptTo(EntityAIdentifier.class)).orElseThrow();

        assertEquals(2, a3TransferATO.getAs().size());
        assertTrue(a3TransferATO.getAs().stream().anyMatch(c -> "a5".equals(c.getName().orElseThrow())));
        assertTrue(a3TransferATO.getAs().stream().anyMatch(c -> "a6".equals(c.getName().orElseThrow())));
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
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-012",
            "REQ-SRV-002"
    })
    void testForMultiLevelSingleComposition() {

        TransferATO a = transferATODao.create(TransferATO.builder().withName("level1")
                .withA(TransferATO.builder().withName("level2")
                        .withA(TransferATO.builder().withName("level3")
                                .withA(TransferATO.builder().withName("level4").build()).build()
                        ).build()
                ).build()
        );

        assertEquals(4, transferATODao.countAll());

        assertEquals("level2", a.getA().get().getName().get());
        assertEquals("level3", a.getA().get().getA().get().getName().get());
        assertEquals("level4", a.getA().get().getA().get().getA().get().getName().get());

        // childName with derived
        assertEquals("level2", a.getChildName().get());
        assertEquals("level3", a.getA().get().getChildName().get());
        assertEquals("level4", a.getA().get().getA().get().getChildName().get());
        assertEquals("", a.getA().get().getA().get().getA().get().getChildName().get());

        // recursive navigation through derived
        assertEquals("level3", a.getChildNameThirdLevel().get());
        assertEquals("level4", a.getChildNameFourthLevel().get());

        // After update
        a = transferATODao.update(a);
        assertEquals("level2", a.getA().get().getName().get());
        assertEquals("level3", a.getA().get().getA().get().getName().get());
        assertEquals("level4", a.getA().get().getA().get().getA().get().getName().get());

        // childName with derived
        assertEquals("level2", a.getChildName().get());
        assertEquals("level3", a.getA().get().getChildName().get());
        assertEquals("level4", a.getA().get().getA().get().getChildName().get());
        assertEquals("", a.getA().get().getA().get().getA().get().getChildName().get());

        // recursive navigation through derived
        assertEquals("level3", a.getChildNameThirdLevel().get());
        assertEquals("level4", a.getChildNameFourthLevel().get());

        // Remove element
        a.getA().orElseThrow().getA().orElseThrow().setA(null);
        a = transferATODao.update(a);

        assertEquals(3, transferATODao.countAll());

        assertEquals("level2", a.getA().get().getName().get());
        assertEquals("level3", a.getA().get().getA().get().getName().get());
        assertTrue(a.getA().get().getA().get().getA().isEmpty());

        // childName with derived
        assertEquals("level2", a.getChildName().get());
        assertEquals("level3", a.getA().get().getChildName().get());
        assertEquals("", a.getA().get().getA().get().getChildName().get());

        // recursive navigation through derived
        assertEquals("level3", a.getChildNameThirdLevel().orElseThrow());
        assertEquals(Optional.empty(), a.getChildNameFourthLevel());
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
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-012",
            "REQ-SRV-002"
    })
    void testForInheritedMultiLevelSingleComposition() {

        TransferBTO b = transferBTODao.create(TransferBTO.builder().withName("level1")
                .withA(TransferATO.builder().withName("level2")
                        .withA(TransferATO.builder().withName("level3")
                                .withA(TransferATO.builder().withName("level4").build()).build()
                        ).build()
                )
                .withBa(TransferATO.builder().withName("level2")
                        .withA(TransferATO.builder().withName("level3")
                                .withA(TransferATO.builder().withName("level4").build()).build()
                        ).build()
                )
                .build()
        );

        assertEquals(7, transferATODao.countAll());
        assertEquals(1, transferBTODao.countAll());

        assertEquals("level2", b.getA().get().getName().get());
        assertEquals("level3", b.getA().get().getA().get().getName().get());
        assertEquals("level4", b.getA().get().getA().get().getA().get().getName().get());
        assertEquals("level2", b.getBa().get().getName().get());
        assertEquals("level3", b.getBa().get().getA().get().getName().get());
        assertEquals("level4", b.getBa().get().getA().get().getA().get().getName().get());

        // childName with derived
        assertEquals("level2", b.getChildName().get());
        assertEquals("level3", b.getA().get().getChildName().get());
        assertEquals("level4", b.getA().get().getA().get().getChildName().get());
        assertEquals("", b.getA().get().getA().get().getA().get().getChildName().get());
        assertEquals("level3", b.getBa().get().getChildName().get());
        assertEquals("level4", b.getBa().get().getA().get().getChildName().get());
        assertEquals("", b.getBa().get().getA().get().getA().get().getChildName().get());

        // recursive navigation through derived
        assertEquals("level3", b.getChildNameThirdLevel().get());
        assertEquals("level4", b.getChildNameFourthLevel().get());
        assertEquals("level4", b.getBa().get().getChildNameThirdLevel().get());
        assertEquals(Optional.empty(), b.getBa().get().getChildNameFourthLevel());

        // After update
        b = transferBTODao.update(b);
        assertEquals("level2", b.getA().get().getName().get());
        assertEquals("level3", b.getA().get().getA().get().getName().get());
        assertEquals("level4", b.getA().get().getA().get().getA().get().getName().get());
        assertEquals("level2", b.getBa().get().getName().get());
        assertEquals("level3", b.getBa().get().getA().get().getName().get());
        assertEquals("level4", b.getBa().get().getA().get().getA().get().getName().get());

        // childName with derived
        assertEquals("level2", b.getChildName().get());
        assertEquals("level3", b.getA().get().getChildName().get());
        assertEquals("level4", b.getA().get().getA().get().getChildName().get());
        assertEquals("", b.getA().get().getA().get().getA().get().getChildName().get());
        assertEquals("level3", b.getBa().get().getChildName().get());
        assertEquals("level4", b.getBa().get().getA().get().getChildName().get());
        assertEquals("", b.getBa().get().getA().get().getA().get().getChildName().get());

        // recursive navigation through derived
        assertEquals("level3", b.getChildNameThirdLevel().get());
        assertEquals("level4", b.getChildNameFourthLevel().get());
        assertEquals("level4", b.getBa().get().getChildNameThirdLevel().get());
        assertEquals(Optional.empty(), b.getBa().get().getChildNameFourthLevel());

        // Remove element
        b.getA().orElseThrow().getA().orElseThrow().setA(null);
        b.getBa().orElseThrow().getA().orElseThrow().setA(null);
        b = transferBTODao.update(b);

        assertEquals(5, transferATODao.countAll());
        assertEquals(1, transferBTODao.countAll());

        assertEquals("level2", b.getA().get().getName().get());
        assertEquals("level3", b.getA().get().getA().get().getName().get());
        assertTrue(b.getA().get().getA().get().getA().isEmpty());
        assertEquals("level2", b.getBa().get().getName().get());
        assertEquals("level3", b.getBa().get().getA().get().getName().get());
        assertTrue(b.getBa().get().getA().get().getA().isEmpty());

        // childName with derived
        assertEquals("level2", b.getChildName().get());
        assertEquals("level3", b.getA().get().getChildName().get());
        assertEquals("", b.getA().get().getA().get().getChildName().get());
        assertEquals("level3", b.getBa().get().getChildName().get());
        assertEquals("", b.getBa().get().getA().get().getChildName().get());

        // recursive navigation through derived
        assertEquals("level3", b.getChildNameThirdLevel().get());
        assertEquals(Optional.empty(), b.getChildNameFourthLevel());
        assertEquals(Optional.empty(), b.getBa().get().getChildNameThirdLevel());
        assertEquals(Optional.empty(), b.getBa().get().getChildNameFourthLevel());
    }
}
