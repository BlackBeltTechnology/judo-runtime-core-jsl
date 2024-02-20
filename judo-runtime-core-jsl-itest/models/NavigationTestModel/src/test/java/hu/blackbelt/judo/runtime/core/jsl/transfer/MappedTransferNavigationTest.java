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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.sextype.SexType;
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.transferperson.TransferPerson;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.transferperson.TransferPersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.transferperson.TransferPersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.NavigationTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.Identifiable;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
            "REQ-EXPR-022",
            "REQ-SRV-002"
    })
    public void testSelfNavigation() {
        TC c1 = tcDao.create(TCForCreate.builder().build());
        TC c2 = tcDao.create(TCForCreate.builder().build());
        TB b1 = tbDao.create(TBForCreate.builder().withC(c1).build());
        TB b2 = tbDao.create(TBForCreate.builder().withC(c2).build());
        TA a = taDao.create(TAForCreate.builder().withBlist(List.of(b1, b2)).build());

        Assertions.assertEquals(a.identifier().getIdentifier(), taDao.querySelf(a).orElseThrow().identifier().getIdentifier());

        assertThat(taDao.queryBlistTroughDerivedSelf(a).selectList().stream().map(e -> e.identifier().getIdentifier()).toList(),
                containsInAnyOrder(b1.identifier().getIdentifier(), b2.identifier().getIdentifier()));

        assertThat(taDao.queryClistTroughDerivedSelf(a).selectList().stream().map(e -> e.identifier().getIdentifier()).toList(),
                containsInAnyOrder(c1.identifier().getIdentifier(), c2.identifier().getIdentifier()));

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
    
    @Inject
    TransferPersonDao transferPersonDao;
    
    @Test
    @TestCase("NavigationTransferPersonWithParentsOnTransfer")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-006",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-008",
            "REQ-EXPR-022",
            "REQ-SRV-002"
    })
    public void testNavigationTransferPersonWithParentsOnTransfer() {
        TransferPerson person1 = transferPersonDao.create(TransferPersonForCreate.builder().withName("TransferPerson1").withSex(SexType.MALE).withBirthDate(LocalDate.of(1987, 10, 11)).build());
        TransferPerson father1 = transferPersonDao.createParents(person1, TransferPersonForCreate.builder().withName("Father1").withSex(SexType.MALE).withBirthDate(LocalDate.of(1957, 1, 2)).build());
        TransferPerson mother1 = transferPersonDao.createParents(person1, TransferPersonForCreate.builder().withName("Mother1").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1960, 8, 17)).build());
        TransferPerson grandMother1 = transferPersonDao.createParents(mother1, TransferPersonForCreate.builder().withName("GrandMother1").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1935, 3, 13)).build());
        TransferPerson greatGrandMother1 = transferPersonDao.createParents(grandMother1, TransferPersonForCreate.builder().withName("GreatGrandMother").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1927, 9, 4)).build());

        TransferPerson person2 = transferPersonDao.create(TransferPersonForCreate.builder().withName("TransferPerson2").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1992, 4, 21)).build());
        TransferPerson person3 = transferPersonDao.create(TransferPersonForCreate.builder().withName("TransferPerson3").withSex(SexType.MALE).withBirthDate(LocalDate.of(2009, 8, 13)).build());

        transferPersonDao.addParents(person2, List.of(mother1, father1));
        transferPersonDao.addParents(person3, List.of(person1));

        List<TransferPerson> people = transferPersonDao.findAllById(List.of((UUID) person1.identifier().getIdentifier(), (UUID) person2.identifier().getIdentifier(), (UUID) person3.identifier().getIdentifier()));
        Assertions.assertEquals(3, people.size());
        Optional<TransferPerson> person1Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person1.identifier().getIdentifier())).findAny();
        assertTrue(person1Loaded.isPresent());
        assertThat(person1Loaded.get().getMotherName(), equalTo(mother1.getName()));
        assertThat(person1Loaded.get().getFatherName(), equalTo(father1.getName()));
        assertThat(person1Loaded.get().getGrandMother1Name(), equalTo(grandMother1.getName()));
        assertThat(person1Loaded.get().getGreatGrandMother1Name(), equalTo(greatGrandMother1.getName()));
        assertTrue(person1Loaded.get().getMotherYoungerThanFather().orElseThrow());

        assertThat(transferPersonDao.queryMother(person1Loaded.get()).orElseThrow().identifier(), equalTo(mother1.identifier()));
        assertThat(transferPersonDao.queryFather(person1Loaded.get()).orElseThrow().identifier(), equalTo(father1.identifier()));
        assertThat(transferPersonDao.queryMother(transferPersonDao.queryMother(person1Loaded.get()).orElseThrow()).orElseThrow().identifier(), equalTo(grandMother1.identifier()));
        assertThat(transferPersonDao.queryMother(transferPersonDao.queryMother(transferPersonDao.queryMother(person1Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(greatGrandMother1.identifier()));

        Optional<TransferPerson> person2Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person2.identifier().getIdentifier())).findAny();
        assertTrue(person2Loaded.isPresent());
        assertThat(person2Loaded.get().getMotherName(), equalTo(mother1.getName()));
        assertThat(person2Loaded.get().getFatherName(), equalTo(father1.getName()));
        assertThat(person2Loaded.get().getGrandMother1Name(), equalTo(grandMother1.getName()));
        assertThat(person2Loaded.get().getGreatGrandMother1Name(), equalTo(greatGrandMother1.getName()));
        assertTrue(person2Loaded.get().getMotherYoungerThanFather().orElseThrow());

        assertThat(transferPersonDao.queryMother(person2Loaded.get()).orElseThrow().identifier(), equalTo(mother1.identifier()));
        assertThat(transferPersonDao.queryFather(person2Loaded.get()).orElseThrow().identifier(), equalTo(father1.identifier()));
        assertThat(transferPersonDao.queryMother(transferPersonDao.queryMother(person2Loaded.get()).orElseThrow()).orElseThrow().identifier(), equalTo(grandMother1.identifier()));
        assertThat(transferPersonDao.queryMother(transferPersonDao.queryMother(transferPersonDao.queryMother(person2Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(greatGrandMother1.identifier()));

        Optional<TransferPerson> person3Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person3.identifier().getIdentifier())).findAny();
        assertTrue(person3Loaded.isPresent());
        assertTrue(person3Loaded.get().getMotherName().isEmpty());
        assertThat(person3Loaded.get().getFatherName(), equalTo(person1.getName()));
        assertThat(person3Loaded.get().getGrandMother2Name(), equalTo(mother1.getName()));
        assertThat(person3Loaded.get().getGrandFather2Name(), equalTo(father1.getName()));

        assertTrue(transferPersonDao.queryMother(person3Loaded.get()).isEmpty());
        assertThat(transferPersonDao.queryFather(person3Loaded.get()).orElseThrow().identifier(), equalTo(person1.identifier()));
        assertThat(transferPersonDao.queryMother(transferPersonDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow().identifier(), equalTo(mother1.identifier()));
        assertThat(transferPersonDao.queryMother(transferPersonDao.queryMother(transferPersonDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(grandMother1.identifier()));
        assertThat(transferPersonDao.queryMother(transferPersonDao.queryMother(transferPersonDao.queryMother(transferPersonDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(greatGrandMother1.identifier()));

    }

}
