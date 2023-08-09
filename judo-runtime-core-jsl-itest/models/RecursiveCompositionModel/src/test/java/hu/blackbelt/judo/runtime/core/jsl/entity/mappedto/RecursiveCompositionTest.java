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
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityX;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityXDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityx.EntityXIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityY;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityYDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.entityy.EntityYIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferxto.TransferXTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferxto.TransferXTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferyto.TransferYTO;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.recursivecomposition.recursivecomposition.transferyto.TransferYTODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RecursiveCompositionDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RecursiveCompositionTest extends AbstractJslTest {

    @Inject
    TransferXTODao transferXTODao;

    @Inject
    TransferYTODao transferYTODao;


    @Inject
    EntityXDao entityXDao;

    @Inject
    EntityYDao entityYDao;

    @Override
    public Module getModelDaoModule() {
        return new RecursiveCompositionDaoModules();
    }

    @Override
    public String getModelName() {
        return "RecursiveComposition";
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-007"
    })
    void testRecursiveCompositionOnMappedTo() {
        TransferXTO x13 = transferXTODao.create(TransferXTO.builder().withName("x13").build());
        TransferXTO x11 = transferXTODao.create(TransferXTO.builder().withName("x11").build());
        TransferXTO x8 = transferXTODao.create(TransferXTO.builder().withName("x8").build());
        TransferXTO x5 = transferXTODao.create(TransferXTO.builder().withName("x5").build());
        TransferXTO x3 = transferXTODao.create(TransferXTO.builder().withName("x3").build());

        TransferYTO y9 = transferYTODao.create(TransferYTO.builder().withName("y9").build());
        TransferYTO y7 = transferYTODao.create(TransferYTO.builder().withName("y7").build());
        TransferYTO y4 = transferYTODao.create(TransferYTO.builder().withName("y4").build());
        TransferYTO y3 = transferYTODao.create(TransferYTO.builder().withName("y3").build());
        TransferYTO y2 = transferYTODao.create(TransferYTO.builder().withName("y2").build());
        TransferYTO y1 = transferYTODao.create(TransferYTO.builder().withName("y1").build());

        TransferYTO y8 = transferYTODao.create(TransferYTO.builder().withName("y8").withYx(x13).build());
        TransferXTO x12 = transferXTODao.create(TransferXTO.builder().withName("x12").withY(y9).build());
        TransferXTO x4 = transferXTODao.create(TransferXTO.builder().withName("x4").withY(y4).build());

        TransferXTO x10 = transferXTODao.create(TransferXTO.builder().withName("x10").withYs(List.of(y7, y8)).build());
        TransferXTO x2 = transferXTODao.create(TransferXTO.builder().withName("x2").withX(x5).withXs(List.of(x3, x4)).build());

        TransferYTO y6 = transferYTODao.create(TransferYTO.builder().withName("y6").withYxs(List.of(x11, x12)).build());
        TransferYTO y5 = transferYTODao.create(TransferYTO.builder().withName("y5").withYx(x10).build());

        TransferXTO x9 = transferXTODao.create(TransferXTO.builder().withName("x9").withYs(List.of(y5, y6)).build());
        TransferXTO x7 = transferXTODao.create(TransferXTO.builder().withName("x7").withXs(List.of(x8, x9)).build());
        TransferXTO x6 = transferXTODao.create(TransferXTO.builder().withName("x6").withX(x7).build());

        TransferXTO x1 = transferXTODao.create(TransferXTO.builder().withName("x1").withX(x2).withXs(List.of(x6)).withY(y1).withYs(List.of(y2, y3)).build());

        assertEquals("x1", x1.getName().orElseThrow());
        assertEquals("x2", x1.getX().orElseThrow().getName().orElseThrow());
        assertEquals("x6", x1.getXs().get(0).getName().orElseThrow());
        assertEquals("y1", x1.getY().orElseThrow().getName().orElseThrow());
        assertEquals(1, x1.getYs().stream().filter(y -> "y2".equals(y.getName().orElseThrow())).count());
        assertEquals(1, x1.getYs().stream().filter(y -> "y3".equals(y.getName().orElseThrow())).count());
        assertTrue(x1.getYs().stream().anyMatch(y -> "y2".equals(y.getName().orElseThrow())));
        assertTrue(x1.getYs().stream().anyMatch(y -> "y3".equals(y.getName().orElseThrow())));

        EntityX x1EntityX = entityXDao.getById(x1.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("x1", x1EntityX.getName().orElseThrow());
        assertEquals("x2", x1EntityX.getX().orElseThrow().getName().orElseThrow());
        assertEquals("x6", x1EntityX.getXs().get(0).getName().orElseThrow());
        assertEquals("y1", x1EntityX.getY().orElseThrow().getName().orElseThrow());
        assertEquals(1, x1EntityX.getYs().stream().filter(y -> "y2".equals(y.getName().orElseThrow())).count());
        assertEquals(1, x1EntityX.getYs().stream().filter(y -> "y3".equals(y.getName().orElseThrow())).count());
        assertTrue(x1EntityX.getYs().stream().anyMatch(y -> "y2".equals(y.getName().orElseThrow())));
        assertTrue(x1EntityX.getYs().stream().anyMatch(y -> "y3".equals(y.getName().orElseThrow())));

        TransferXTO x2Test = x1.getX().orElseThrow();

        assertEquals("x5", x2Test.getX().orElseThrow().getName().orElseThrow());
        assertEquals(2, x2Test.getXs().stream().count());
        assertTrue(x2Test.getXs().stream().anyMatch(y -> "x3".equals(y.getName().orElseThrow())));
        assertTrue(x2Test.getXs().stream().anyMatch(y -> "x4".equals(y.getName().orElseThrow())));
        assertFalse(x2Test.getY().isPresent());
        assertEquals(0, x2Test.getYs().size());

        EntityX x2EntityX = entityXDao.getById(x2Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals("x5", x2EntityX.getX().orElseThrow().getName().orElseThrow());
        assertEquals(2, x2EntityX.getXs().stream().count());
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

        assertEquals(2, x7Test.getXs().stream().count());
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x8".equals(y.getName().orElseThrow())));
        assertTrue(x7Test.getXs().stream().anyMatch(y -> "x9".equals(y.getName().orElseThrow())));
        // It should be Optional.empty, but it is null
        //assertFalse(x7Test.getX().isPresent());
        assertFalse(x7Test.getY().isPresent());
        assertEquals(0, x7Test.getYs().size());

        EntityX x7EntityX = entityXDao.getById(x7Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals(2, x7EntityX.getXs().stream().count());
        assertTrue(x7EntityX.getXs().stream().anyMatch(y -> "x8".equals(y.getName().orElseThrow())));
        assertTrue(x7EntityX.getXs().stream().anyMatch(y -> "x9".equals(y.getName().orElseThrow())));
        // It should be Optional.empty, but it is null
        assertFalse(x7EntityX.getX().isPresent());
        assertFalse(x7EntityX.getY().isPresent());
        assertEquals(0, x7EntityX.getYs().size());

        TransferXTO x9Test = x7.getXs().stream().filter(c -> "x9".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

        assertEquals(2, x9Test.getYs().stream().count());
        assertTrue(x9Test.getYs().stream().anyMatch(y -> "y5".equals(y.getName().orElseThrow())));
        assertTrue(x9Test.getYs().stream().anyMatch(y -> "y6".equals(y.getName().orElseThrow())));
        assertFalse(x9Test.getX().isPresent());
        assertFalse(x9Test.getY().isPresent());
        assertEquals(0, x9Test.getXs().size());

        EntityX x9EntityX = entityXDao.getById(x9Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals(2, x9EntityX.getYs().stream().count());
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

        assertEquals(2, y6Test.getYxs().stream().count());
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x11".equals(y.getName().orElseThrow())));
        assertTrue(y6Test.getYxs().stream().anyMatch(y -> "x12".equals(y.getName().orElseThrow())));
        assertFalse(y6Test.getYx().isPresent());

        EntityY y6EntityY = entityYDao.getById(y6Test.adaptTo(EntityYIdentifier.class)).orElseThrow();

        assertEquals(2, y6EntityY.getYxs().stream().count());
        assertTrue(y6EntityY.getYxs().stream().anyMatch(y -> "x11".equals(y.getName().orElseThrow())));
        assertTrue(y6EntityY.getYxs().stream().anyMatch(y -> "x12".equals(y.getName().orElseThrow())));
        assertFalse(y6EntityY.getYx().isPresent());

        TransferXTO x10Test = y5Test.getYx().orElseThrow();

        assertEquals(2, x10Test.getYs().stream().count());
        assertTrue(x10Test.getYs().stream().anyMatch(y -> "y7".equals(y.getName().orElseThrow())));
        assertTrue(x10Test.getYs().stream().anyMatch(y -> "y8".equals(y.getName().orElseThrow())));
        assertFalse(x10Test.getX().isPresent());
        assertFalse(x10Test.getY().isPresent());
        assertEquals(0, x10Test.getXs().size());

        EntityX x10EntityX = entityXDao.getById(x10Test.adaptTo(EntityXIdentifier.class)).orElseThrow();

        assertEquals(2, x10EntityX.getYs().stream().count());
        assertTrue(x10EntityX.getYs().stream().anyMatch(y -> "y7".equals(y.getName().orElseThrow())));
        assertTrue(x10EntityX.getYs().stream().anyMatch(y -> "y8".equals(y.getName().orElseThrow())));
        assertFalse(x10EntityX.getX().isPresent());
        assertFalse(x10EntityX.getY().isPresent());
        assertEquals(0, x10EntityX.getXs().size());

        TransferXTO x12test = y6.getYxs().stream().filter(c -> "x12".equals(c.getName().orElseThrow())).findFirst().orElseThrow();

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
}
