package hu.blackbelt.judo.runtime.core.jsl;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.NavigationTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class NavigationTest extends AbstractJslTest {

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Override
    public Module getModelDaoModule() {
        return new NavigationTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "NavigationTest";
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003"
    })
    public void test() {
        A a = aDao.create(A.builder().build());
        B b = bDao.create(B.builder().build());
        C c = cDao.create(C.builder().build());

        aDao.addBlist(a, List.of(b));
        bDao.setC(b, c);

        // Read derived list over DAO call
        List<C> cList = aDao.queryClist(a).execute();
        assertEquals(1, cList.size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-008",
            "REQ-EXPR-022"
    })
    public void testStaticNavigation() {
        A a = aDao.create(A.builder().build());

        assertEmpty(aDao.queryBAll(a).execute());
        assertEmpty(aDao.queryBAny(a));

        assertEmpty(a.getBbAnyName());
        assertEmpty(a.getSelfBName());

        assertEmpty(aDao.queryBAllFilter(a).execute());
        assertEmpty(aDao.queryBAllFilterAny(a));
        assertEmpty(aDao.queryBAllFilterAny1(a));

        assertEmpty(a.getBbAllFilterAnyName());
        assertEmpty(a.getBbAllFilterAnyName1());
        assertEmpty(a.getBbAllFilterAnyName2());

        assertEmpty(aDao.querySelfbAllC(a).execute());
        assertEmpty(aDao.querySelfbAllCAny(a));
        assertEmpty(a.getSelfbAllCAnyName());

        assertEmpty(aDao.queryBAllC(a).execute());
        assertEmpty(aDao.queryBAllCAny(a));
        assertEmpty(a.getBbAllCAnyName());

        C c = cDao.create(C.builder().withName("c").build());
        UUID cId = c.get__identifier();
        UUID bId = bDao.create(B.builder().withName("b").build(),
                               BAttachedRelationsForCreate.builder().withC(c).build()).get__identifier();

        a = aDao.getById(a.get__identifier()).orElseThrow();

        assertIsB(bId, aDao.queryBAll(a).execute());
        assertEquals(bId, aDao.queryBAny(a).orElseThrow().get__identifier());

        assertEquals("b", a.getBbAnyName().orElseThrow());
        assertEquals("b", a.getSelfBName().orElseThrow());

        assertIsB(bId, aDao.queryBAllFilter(a).execute());
        assertEquals(bId, aDao.queryBAllFilterAny(a).orElseThrow().get__identifier());
        assertEquals(bId, aDao.queryBAllFilterAny1(a).orElseThrow().get__identifier());

        assertEquals("b", a.getBbAllFilterAnyName().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName1().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName2().orElseThrow());

        assertIsC(cId, aDao.querySelfbAllC(a).execute());
        assertEquals(cId, aDao.querySelfbAllCAny(a).orElseThrow().get__identifier());
        assertEquals("c", a.getSelfbAllCAnyName().orElseThrow());

        assertIsC(cId, aDao.queryBAllC(a).execute());
        assertEquals(cId, aDao.queryBAllCAny(a).orElseThrow().get__identifier());
        assertEquals("c", a.getBbAllCAnyName().orElseThrow());
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static void assertEmpty(Optional<?> optional) {
        assertNotNull(optional);
        assertTrue(optional.isEmpty());
    }

    private static void assertEmpty(Collection<?> collection) {
        assertNotNull(collection);
        assertTrue(collection.isEmpty());
    }

    private static void assertIsB(UUID bId, List<B> bs) {
        assertNotNull(bId);
        assertNotNull(bs);
        assertEquals(1, bs.size());
        assertEquals(bId, bs.get(0).get__identifier());
    }

    private static void assertIsC(UUID cId, List<C> cs) {
        assertNotNull(cId);
        assertNotNull(cs);
        assertEquals(1, cs.size());
        assertEquals(cId, cs.get(0).get__identifier());
    }

}
