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
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
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

        assertEmptyBAndC(a);

        C c = cDao.create(C.builder().withName("c").build());
        UUID cId = c.get__identifier();
        B b = bDao.create(B.builder().withName("b").build(), BAttachedRelationsForCreate.builder().withC(c).build());
        UUID bId = b.get__identifier();

        assertAttributesAndRelations(aDao.getById(a.get__identifier()).orElseThrow(), List.of(bId), List.of(cId));

        C c2 = cDao.create(C.builder().withName("c").build());
        UUID c2Id = c2.get__identifier();
        B b2 = bDao.create(B.builder().withName("b").build(), BAttachedRelationsForCreate.builder().withC(c).build());
        UUID b2Id = b2.get__identifier();

        assertAttributesAndRelations(aDao.getById(a.get__identifier()).orElseThrow(), List.of(bId, b2Id), List.of(cId, c2Id));
    }

    private void assertEmptyBAndC(A a) {
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
    }

    @SuppressWarnings("unchecked")
    private void assertAttributesAndRelations(A a, Collection<UUID> bIds, Collection<UUID> cIds) {
        assertThat(aDao.queryBAll(a).execute().stream().map(B::get__identifier).collect(Collectors.toList()), anyOf(toHasItems(bIds)));
        assertThat(aDao.queryBAny(a).orElseThrow().get__identifier(), anyOf(toIss(bIds)));

        assertEquals("b", a.getBbAnyName().orElseThrow());
        assertEquals("b", a.getSelfBName().orElseThrow());

        assertThat(aDao.queryBAllFilter(a).execute().stream().map(B::get__identifier).collect(Collectors.toList()), anyOf(toHasItems(bIds)));
        assertThat(aDao.queryBAllFilterAny(a).orElseThrow().get__identifier(), anyOf(toIss(bIds)));
        assertThat(aDao.queryBAllFilterAny1(a).orElseThrow().get__identifier(), anyOf(toIss(bIds)));

        assertEquals("b", a.getBbAllFilterAnyName().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName1().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName2().orElseThrow());

        assertThat(aDao.querySelfbAllC(a).execute().stream().map(C::get__identifier).collect(Collectors.toList()), anyOf(toHasItems(cIds)));
        assertThat(aDao.querySelfbAllCAny(a).orElseThrow().get__identifier(), anyOf(toIss(cIds)));
        assertEquals("c", a.getSelfbAllCAnyName().orElseThrow());

        assertThat(aDao.queryBAllC(a).execute().stream().map(C::get__identifier).collect(Collectors.toList()), anyOf(toHasItems(cIds)));
        assertThat(aDao.queryBAllCAny(a).orElseThrow().get__identifier(), anyOf(toIss(cIds)));
        assertEquals("c", a.getBbAllCAnyName().orElseThrow());
    }

    @SuppressWarnings("rawtypes")
    private static Matcher[] toHasItems(Collection<UUID> ids) {
        return ids.stream().map(CoreMatchers::hasItem).collect(Collectors.toList()).toArray(Matcher[]::new);
    }

    @SuppressWarnings("rawtypes")
    private static Matcher[] toIss(Collection<UUID> ids) {
        return ids.stream().map(CoreMatchers::is).collect(Collectors.toList()).toArray(Matcher[]::new);
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

}
