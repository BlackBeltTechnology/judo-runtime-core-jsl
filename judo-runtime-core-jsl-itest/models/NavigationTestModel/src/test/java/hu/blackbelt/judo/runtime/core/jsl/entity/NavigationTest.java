package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a.AForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a1.A1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a1.A1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a1.A1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.BForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b1.B1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b1.B1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b1.B1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base1.Base1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base1.Base1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base1.Base1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.derivedattributecollector.DerivedAttributeCollector;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.derivedattributecollector.DerivedAttributeCollectorDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.derivedattributecollector.DerivedAttributeCollectorForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person.Person;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person.PersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person.PersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.sextype.SexType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.NavigationTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.Identifiable;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Disabled;
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
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class NavigationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("NavigationTest", new NavigationTestDaoModules());

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003"
    })
    public void test() {
        A a = aDao.create(AForCreate.builder().build());
        B b = bDao.create(BForCreate.builder().build());
        C c = cDao.create(CForCreate.builder().build());

        aDao.addBlist(a, List.of(b));
        bDao.setC(b, c);

        // Read derived list over DAO call
        List<C> cList = aDao.queryClist(a).selectList();
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
        A a = aDao.create(AForCreate.builder().build());

        assertEmptyBAndC(a);

        C c = cDao.create(CForCreate.builder().withName("c").build());
        CIdentifier cId = c.identifier();
        B b = bDao.create(BForCreate.builder().withName("b").withC(c).build());
        BIdentifier bId = b.identifier();

        assertAttributesAndRelations(aDao.getById(a.identifier()).orElseThrow(), List.of(bId), List.of(cId));

        C c2 = cDao.create(CForCreate.builder().withName("c").build());
        CIdentifier c2Id = c2.identifier();
        B b2 = bDao.create(BForCreate.builder().withName("b").withC(c).build());
        BIdentifier b2Id = b2.identifier();

        assertAttributesAndRelations(aDao.getById(a.identifier()).orElseThrow(), List.of(bId, b2Id), List.of(cId, c2Id));
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
    public void testSelfNavigation() {
        C c1 = cDao.create(CForCreate.builder().build());
        C c2 = cDao.create(CForCreate.builder().build());
        B b1 = bDao.create(BForCreate.builder().withC(c1).build());
        B b2 = bDao.create(BForCreate.builder().withC(c2).build());
        A a = aDao.create(AForCreate.builder().withBlist(List.of(b1, b2)).build());

        assertEquals(a.identifier().getIdentifier(), aDao.querySelf(a).orElseThrow().identifier().getIdentifier());

        assertThat(aDao.queryBlistTroughDerivedSelf(a).selectList().stream().map(e -> e.identifier().getIdentifier()).toList(),
                containsInAnyOrder(b1.identifier().getIdentifier(), b2.identifier().getIdentifier()));

        assertThat(aDao.queryClistTroughDerivedSelf(a).selectList().stream().map(e -> e.identifier().getIdentifier()).toList(),
                containsInAnyOrder(c1.identifier().getIdentifier(), c2.identifier().getIdentifier()));

    }

    private void assertEmptyBAndC(A a) {
        assertEmpty(aDao.queryBbAll(a).selectList());
        assertEmpty(aDao.queryBbAny(a));

        assertEmpty(a.getBbAnyName());
        assertEmpty(a.getSelfBName());

        assertEmpty(aDao.queryBbAllFilter(a).selectList());
        assertEmpty(aDao.queryBbAllFilterAny(a));
        assertEmpty(aDao.queryBbAllFilterAny1(a));

        assertEmpty(a.getBbAllFilterAnyName());
        assertEmpty(a.getBbAllFilterAnyName1());
        assertEmpty(a.getBbAllFilterAnyName2());

        assertEmpty(aDao.querySelfbAllC(a).selectList());
        assertEmpty(aDao.querySelfbAllCAny(a));
        assertEmpty(a.getSelfbAllCAnyName());

        assertEmpty(aDao.queryBbAllC(a).selectList());
        assertEmpty(aDao.queryBbAllCAny(a));
        assertEmpty(a.getBbAllCAnyName());
    }

    @SuppressWarnings("unchecked")
    private void assertAttributesAndRelations(A a, Collection<Identifiable> bIds, Collection<Identifiable> cIds) {
        assertThat(aDao.queryBbAll(a).selectList().stream().map(B::identifier).collect(Collectors.toList()), anyOf(toHasItems(bIds)));
        assertThat(aDao.queryBbAny(a).orElseThrow().identifier(), anyOf(toIss(bIds)));

        assertEquals("b", a.getBbAnyName().orElseThrow());
        assertEquals("b", a.getSelfBName().orElseThrow());

        assertThat(aDao.queryBbAllFilter(a).selectList().stream().map(B::identifier).collect(Collectors.toList()), anyOf(toHasItems(bIds)));
        assertThat(aDao.queryBbAllFilterAny(a).orElseThrow().identifier(), anyOf(toIss(bIds)));
        assertThat(aDao.queryBbAllFilterAny1(a).orElseThrow().identifier(), anyOf(toIss(bIds)));

        assertEquals("b", a.getBbAllFilterAnyName().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName1().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName2().orElseThrow());

        assertThat(aDao.querySelfbAllC(a).selectList().stream().map(C::identifier).collect(Collectors.toList()), anyOf(toHasItems(cIds)));
        assertThat(aDao.querySelfbAllCAny(a).orElseThrow().identifier(), anyOf(toIss(cIds)));
        assertEquals("c", a.getSelfbAllCAnyName().orElseThrow());

        assertThat(aDao.queryBbAllC(a).selectList().stream().map(C::identifier).collect(Collectors.toList()), anyOf(toHasItems(cIds)));
        assertThat(aDao.queryBbAllCAny(a).orElseThrow().identifier(), anyOf(toIss(cIds)));
        assertEquals("c", a.getBbAllCAnyName().orElseThrow());
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
    PersonDao personDao;

    @Test
    @TestCase("NavigationPersonWithParents")
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
            "REQ-EXPR-022"
    })
    public void testNavigationPersonWithParents() {
        Person person1 = personDao.create(PersonForCreate.builder().withName("Person1").withSex(SexType.MALE).withBirthDate(LocalDate.of(1987, 10, 11)).build());
        Person father1 = personDao.createParents(person1, PersonForCreate.builder().withName("Father1").withSex(SexType.MALE).withBirthDate(LocalDate.of(1957, 1, 2)).build());
        Person mother1 = personDao.createParents(person1, PersonForCreate.builder().withName("Mother1").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1960, 8, 17)).build());
        Person grandMother1 = personDao.createParents(mother1, PersonForCreate.builder().withName("GrandMother1").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1935, 3, 13)).build());
        Person greatGrandMother1 = personDao.createParents(grandMother1, PersonForCreate.builder().withName("GreatGrandMother").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1927, 9, 4)).build());

        Person person2 = personDao.create(PersonForCreate.builder().withName("Person2").withSex(SexType.FEMALE).withBirthDate(LocalDate.of(1992, 4, 21)).build());
        Person person3 = personDao.create(PersonForCreate.builder().withName("Person3").withSex(SexType.MALE).withBirthDate(LocalDate.of(2009, 8, 13)).build());

        personDao.addParents(person2, List.of(mother1, father1));
        personDao.addParents(person3, List.of(person1));

        List<Person> people = personDao.findAllById(List.of((UUID) person1.identifier().getIdentifier(), (UUID) person2.identifier().getIdentifier(), (UUID) person3.identifier().getIdentifier()));
        assertEquals(3, people.size());
        Optional<Person> person1Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person1.identifier().getIdentifier())).findAny();
        assertTrue(person1Loaded.isPresent());
        assertThat(person1Loaded.get().getMotherName(), equalTo(mother1.getName()));
        assertThat(person1Loaded.get().getFatherName(), equalTo(father1.getName()));
        assertThat(person1Loaded.get().getGrandMother1Name(), equalTo(grandMother1.getName()));
        assertThat(person1Loaded.get().getGreatGrandMother1Name(), equalTo(greatGrandMother1.getName()));
        assertTrue(person1Loaded.get().getMotherYoungerThanFather().orElseThrow());

        assertThat(personDao.queryMother(person1Loaded.get()).orElseThrow().identifier(), equalTo(mother1.identifier()));
        assertThat(personDao.queryFather(person1Loaded.get()).orElseThrow().identifier(), equalTo(father1.identifier()));
        assertThat(personDao.queryMother(personDao.queryMother(person1Loaded.get()).orElseThrow()).orElseThrow().identifier(), equalTo(grandMother1.identifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryMother(person1Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(greatGrandMother1.identifier()));

        Optional<Person> person2Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person2.identifier().getIdentifier())).findAny();
        assertTrue(person2Loaded.isPresent());
        assertThat(person2Loaded.get().getMotherName(), equalTo(mother1.getName()));
        assertThat(person2Loaded.get().getFatherName(), equalTo(father1.getName()));
        assertThat(person2Loaded.get().getGrandMother1Name(), equalTo(grandMother1.getName()));
        assertThat(person2Loaded.get().getGreatGrandMother1Name(), equalTo(greatGrandMother1.getName()));
        assertTrue(person2Loaded.get().getMotherYoungerThanFather().orElseThrow());

        assertThat(personDao.queryMother(person2Loaded.get()).orElseThrow().identifier(), equalTo(mother1.identifier()));
        assertThat(personDao.queryFather(person2Loaded.get()).orElseThrow().identifier(), equalTo(father1.identifier()));
        assertThat(personDao.queryMother(personDao.queryMother(person2Loaded.get()).orElseThrow()).orElseThrow().identifier(), equalTo(grandMother1.identifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryMother(person2Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(greatGrandMother1.identifier()));

        Optional<Person> person3Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person3.identifier().getIdentifier())).findAny();
        assertTrue(person3Loaded.isPresent());
        assertTrue(person3Loaded.get().getMotherName().isEmpty());
        assertThat(person3Loaded.get().getFatherName(), equalTo(person1.getName()));
        assertThat(person3Loaded.get().getGrandMother2Name(), equalTo(mother1.getName()));
        assertThat(person3Loaded.get().getGrandFather2Name(), equalTo(father1.getName()));

        assertTrue(personDao.queryMother(person3Loaded.get()).isEmpty());
        assertThat(personDao.queryFather(person3Loaded.get()).orElseThrow().identifier(), equalTo(person1.identifier()));
        assertThat(personDao.queryMother(personDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow().identifier(), equalTo(mother1.identifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(grandMother1.identifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryMother(personDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow()).orElseThrow().identifier(), equalTo(greatGrandMother1.identifier()));
    }


    @Inject
    A1Dao a1Dao;

    @Inject
    B1Dao b1Dao;

    @Inject
    Base1Dao base1Dao;

    @Inject
    DerivedAttributeCollectorDao derivedAttributeCollectorDao;

    @Test
    @TestCase("CollectionToObjectNavigationFromSelf")
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
    void testCollectionToObjectNavigationFromSelf() {

        A1 a1 = a1Dao.create(A1ForCreate.builder().withNumber(3).build());
        A1 a2 = a1Dao.create(A1ForCreate.builder().withNumber(4).build());

        B1 b1 = b1Dao.create(B1ForCreate.builder().withNumber(1).withA(a1).build());
        B1 b2 = b1Dao.create(B1ForCreate.builder().withNumber(2).withA(a2).build());

        Base1 base1 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withBs(List.of(b1.adaptTo(B1ForCreate.class), b2.adaptTo(B1ForCreate.class)))
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        Base1 base2 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withBs(List.of(b1.adaptTo(B1ForCreate.class), b2.adaptTo(B1ForCreate.class)))
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        assertEquals(7, base1Dao.querySumRelAonBs(base1).orElseThrow());

    }

    @Test
    @Disabled()
    @TestCase("CollectionToObjectNavigationFromAll")
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
    void testCollectionToObjectNavigationFromAll() {
        // TODO
        DerivedAttributeCollector derivedAttributeCollector = derivedAttributeCollectorDao.create(DerivedAttributeCollectorForCreate.builder().build());
    }

    @Test
    @TestCase("CollectionToCollectionNavigationFromAll")
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
    void testCollectionToCollectionNavigationFromAll() {

    }

    @Test
    @TestCase("ObjectToContainerNavigationFromSelf")
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
    void testObjectToContainerNavigationFromSelf() {

    }

    @Test
    @TestCase("ObjectToObjectAsTypeFromSelf")
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
    void testObjectToObjectAsTypeFromSelf() {

    }

    @Test
    @TestCase("ObjectToCollectionAsTypeFromSelf")
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
    void testObjectToCollectionAsTypeFromSelf() {

    }

    @Test
    @TestCase("CollectionAsTypeFromAll")
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
    void testCollectionAsTypeFromAll() {

    }

    @Test
    @TestCase("ObjectFilterFromSelf")
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
    void testObjectFilterFromSelf() {

    }

    @Test
    @TestCase("ObjectToObjectFilterFromSelf")
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
    void testObjectToObjectFilterFromSelf() {

    }

    @Test
    @TestCase("NavigatingBetweenTwoWayRelations")
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
    void testNavigatingBetweenTwoWayRelations() {

    }

    @Test
    @TestCase("ContainsFunction")
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
    void testContainsFunction() {

    }
}
