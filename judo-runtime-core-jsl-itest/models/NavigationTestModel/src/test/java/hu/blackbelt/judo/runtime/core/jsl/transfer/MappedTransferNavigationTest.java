package hu.blackbelt.judo.runtime.core.jsl.transfer;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a.AIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ta.TA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ta.TADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ta.TAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tb.TB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tb.TBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tb.TBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tb.TBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tc.TC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tc.TCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tc.TCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.tc.TCIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.NavigationTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.Identifiable;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class MappedTransferNavigationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("NavigationTest", new NavigationTestDaoModules());

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Inject
    TADao taDao;

    @Inject
    TBDao tbDao;

    @Inject
    TCDao tcDao;

    @Test
    @TestCase("DerivedNavigationOnTransfer")
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003",
            "REQ-SRV-002"
    })
    public void testDerivedNavigationOnTransfer() {
        TA ta = taDao.create(TAForCreate.builder().build());
        TB tb = tbDao.create(TBForCreate.builder().build());
        TC tc = tcDao.create(TCForCreate.builder().build());

        taDao.addBlist(ta, List.of(tb));
        tbDao.setC(tb, tc);

        // Read derived list over DAO call
        assertEquals(1, taDao.queryClist(ta).selectList().size());

        // Entity representation
        A a = aDao.getById(ta.identifier().adaptTo(AIdentifier.class)).orElseThrow();

        assertEquals(1, aDao.queryClist(a).selectList().size());
    }

    @Test
    @TestCase("StaticNavigationOnTransfer")
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
            "REQ-EXPR-022",
            "REQ-SRV-002"
    })
    public void testStaticNavigationOnTransfer() {
        TA ta = taDao.create(TAForCreate.builder().build());

        assertEmptyBAndC(ta);

        TC tc = tcDao.create(TCForCreate.builder().withName("c").build());
        TCIdentifier tcId = tc.identifier();
        TB tb = tbDao.create(TBForCreate.builder().withName("b").withC(tc).build());
        TBIdentifier tbId = tb.identifier();

        assertAttributesAndRelations(taDao.getById(ta.identifier()).orElseThrow(), List.of(tbId), List.of(tcId));

        TC tc2 = tcDao.create(TCForCreate.builder().withName("c").build());
        TCIdentifier tc2Id = tc2.identifier();
        TB tb2 = tbDao.create(TBForCreate.builder().withName("b").withC(tc).build());
        TBIdentifier b2Id = tb2.identifier();

        assertAttributesAndRelations(taDao.getById(ta.identifier()).orElseThrow(), List.of(tbId, b2Id), List.of(tcId, tc2Id));
    }

    private void assertEmptyBAndC(TA ta) {
        assertEmpty(taDao.queryBbAll(ta).selectList());
        assertEmpty(taDao.queryBbAny(ta));

        assertEmpty(ta.getBbAnyName());
        assertEmpty(ta.getSelfBName());

        assertEmpty(taDao.queryBbAllFilter(ta).selectList());
        assertEmpty(taDao.queryBbAllFilterAny(ta));
        assertEmpty(taDao.queryBbAllFilterAny1(ta));

        assertEmpty(ta.getBbAllFilterAnyName());
        assertEmpty(ta.getBbAllFilterAnyName1());
        assertEmpty(ta.getBbAllFilterAnyName2());

        assertEmpty(taDao.querySelfbAllC(ta).selectList());
        assertEmpty(taDao.querySelfbAllCAny(ta));
        assertEmpty(ta.getSelfbAllCAnyName());

        assertEmpty(taDao.queryBbAllC(ta).selectList());
        assertEmpty(taDao.queryBbAllCAny(ta));
        assertEmpty(ta.getBbAllCAnyName());
    }

    @SuppressWarnings("unchecked")
    private void assertAttributesAndRelations(TA ta, Collection<Identifiable> tbIds, Collection<Identifiable> tcIds) {
        assertThat(taDao.queryBbAll(ta).selectList().stream().map(TB::identifier).collect(Collectors.toList()), anyOf(toHasItems(tbIds)));
        assertThat(taDao.queryBbAny(ta).orElseThrow().identifier(), anyOf(toIss(tbIds)));

        assertEquals("b", ta.getBbAnyName().orElseThrow());
        assertEquals("b", ta.getSelfBName().orElseThrow());

        assertThat(taDao.queryBbAllFilter(ta).selectList().stream().map(TB::identifier).collect(Collectors.toList()), anyOf(toHasItems(tbIds)));
        assertThat(taDao.queryBbAllFilterAny(ta).orElseThrow().identifier(), anyOf(toIss(tbIds)));
        assertThat(taDao.queryBbAllFilterAny1(ta).orElseThrow().identifier(), anyOf(toIss(tbIds)));

        assertEquals("b", ta.getBbAllFilterAnyName().orElseThrow());
        assertEquals("b", ta.getBbAllFilterAnyName1().orElseThrow());
        assertEquals("b", ta.getBbAllFilterAnyName2().orElseThrow());

        assertThat(taDao.querySelfbAllC(ta).selectList().stream().map(TC::identifier).collect(Collectors.toList()), anyOf(toHasItems(tcIds)));
        assertThat(taDao.querySelfbAllCAny(ta).orElseThrow().identifier(), anyOf(toIss(tcIds)));
        assertEquals("c", ta.getSelfbAllCAnyName().orElseThrow());

        assertThat(taDao.queryBbAllC(ta).selectList().stream().map(TC::identifier).collect(Collectors.toList()), anyOf(toHasItems(tcIds)));
        assertThat(taDao.queryBbAllCAny(ta).orElseThrow().identifier(), anyOf(toIss(tcIds)));
        assertEquals("c", ta.getBbAllCAnyName().orElseThrow());
    }

    @SuppressWarnings("rawtypes")
    private static Matcher[] toHasItems(Collection<Identifiable> ids) {
        return ids.stream().map(CoreMatchers::hasItem).collect(Collectors.toList()).toArray(Matcher[]::new);
    }

    @SuppressWarnings("rawtypes")
    private static Matcher[] toIss(Collection<Identifiable> ids) {
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
