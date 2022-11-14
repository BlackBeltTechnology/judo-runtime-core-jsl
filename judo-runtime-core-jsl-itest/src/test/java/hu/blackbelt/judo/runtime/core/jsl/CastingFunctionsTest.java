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
import hu.blackbelt.judo.runtime.core.jsl.itest.castingfunctionsmodel.guice.castingfunctionsmodel.CastingFunctionsModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.castingfunctionsmodel.sdk.castingfunctionsmodel.castingfunctionsmodel.*;
import hu.blackbelt.judo.runtime.core.jsl.itest.castingfunctionsmodel.sdk.castingfunctionsmodel.castingfunctionsmodel.B.BDao;
import hu.blackbelt.judo.runtime.core.jsl.itest.castingfunctionsmodel.sdk.castingfunctionsmodel.castingfunctionsmodel.CA.CADao;
import hu.blackbelt.judo.runtime.core.jsl.itest.castingfunctionsmodel.sdk.castingfunctionsmodel.castingfunctionsmodel.Tester.TesterDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CastingFunctionsTest extends AbstractJslTest {

    @Inject BDao bDao;
    @Inject CADao caDao;
    @Inject TesterDao testerDao;

    private Tester tester;

    @Override
    public Module getModelDaoModule() {
        return new CastingFunctionsModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "CastingFunctionsModel";
    }

    @Override
    @BeforeEach
    protected void init() throws Exception {
        super.init();
        CA ca = caDao.create(CA.builder().withNameA("aca1").withNameB("bca1").withNameCA("ca1").build());
        CA ca1 = caDao.create(CA.builder().withNameA("aca2").withNameB("bca2").withNameCA("ca2").build());
        CA ca2 = caDao.create(CA.builder().withNameA("aca3").withNameB("bca3").withNameCA("ca3").build());
        B caAsB = bDao.getById(ca.get__identifier()).orElseThrow();
        B caAsB1 = bDao.getById(ca1.get__identifier()).orElseThrow();
        B caAsB2 = bDao.getById(ca2.get__identifier()).orElseThrow();

        tester = testerDao.create(Tester.builder()
                                        .withB(bDao.create(B.builder().withNameA("ab").withNameB("b").build()))
                                        .withBs(List.of(
                                                bDao.create(B.builder().withNameA("ab1").withNameB("b1").build()),
                                                bDao.create(B.builder().withNameA("ab2").withNameB("b2").build())
                                        ))
                                        .withCaAsB(caAsB)
                                        .withCaAsBs(List.of(caAsB1, caAsB2))
                                        .build());
    }

    @Test
    public void testKindOf() {
        assertTrue(tester.getKindOfA().orElseThrow());
        assertTrue(tester.getKindOfB().orElseThrow());
        assertFalse(tester.getKindOfCA().orElseThrow());
        assertTrue(tester.getKindOfCA1().orElseThrow());
        assertFalse(tester.getKindOfCB().orElseThrow());
    }

    @Test
    public void testTypeOf() {
        assertFalse(tester.getTypeOfA().orElseThrow());
        assertTrue(tester.getTypeOfB().orElseThrow());
        assertFalse(tester.getTypeOfCA().orElseThrow());
        assertTrue(tester.getTypeOfCA1().orElseThrow());
        assertFalse(tester.getTypeOfCB().orElseThrow());
    }

    @Test
    public void testAsType() {
        assertEquals("ab", testerDao.getAsTypeA(tester).orElseThrow().getNameA().orElseThrow());

        B b = testerDao.getAsTypeB(tester).orElseThrow();
        assertEquals("ab", b.getNameA().orElseThrow());
        assertEquals("b", b.getNameB().orElseThrow());

        assertTrue(testerDao.getAsTypeCA(tester).isEmpty());

        CA caFromT = testerDao.getAsTypeCA1(tester).orElseThrow();
        assertEquals("aca1", caFromT.getNameA().orElseThrow());
        assertEquals("bca1", caFromT.getNameB().orElseThrow());
        assertEquals("ca1", caFromT.getNameCA().orElseThrow());

        assertTrue(testerDao.getAsTypeCB(tester).isEmpty());
    }

    @Test
    public void testAsCollection() {
        List<A> asCollectionA = testerDao.getAsCollectionA(tester);
        assertEquals(2, asCollectionA.size());
        assertTrue(asCollectionA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab1")));
        assertTrue(asCollectionA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab2")));

        List<B> asCollectionB = testerDao.getAsCollectionB(tester);
        assertEquals(2, asCollectionB.size());
        assertTrue(asCollectionB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab1") &&
                                                         lb.getNameB().orElseThrow().equals("b1")));
        assertTrue(asCollectionB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab2") &&
                                                         lb.getNameB().orElseThrow().equals("b2")));

        assertEquals(0, testerDao.getAsCollectionCA(tester).size());

        List<CA> asCollectionCA1 = testerDao.getAsCollectionCA1(tester);
        assertEquals(2, asCollectionCA1.size());
        assertTrue(asCollectionCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca2") &&
                                                            lca.getNameB().orElseThrow().equals("bca2") &&
                                                            lca.getNameCA().orElseThrow().equals("ca2")));
        assertTrue(asCollectionCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca3") &&
                                                            lca.getNameB().orElseThrow().equals("bca3") &&
                                                            lca.getNameCA().orElseThrow().equals("ca3")));
    }

}