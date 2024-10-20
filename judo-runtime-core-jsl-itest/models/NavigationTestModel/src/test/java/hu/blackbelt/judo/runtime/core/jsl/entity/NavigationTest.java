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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a2.A2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a2.A2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a2.A2ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a3.A3;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a3.A3Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.a3.A3ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ac.AC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ac.ACDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ac.ACForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ap.AP;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ap.APDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.ap.APForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.BForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b1.B1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b1.B1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b1.B1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b2.B2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b2.B2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b2.B2ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b3.B3;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b3.B3Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.b3.B3ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base1.Base1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base1.Base1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base1.Base1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base2.Base2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base2.Base2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.base2.Base2ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.c.CIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.class_.ClassDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.class_.Class_;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.class_.Class_ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.derivedattributecollector.DerivedAttributeCollector;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.derivedattributecollector.DerivedAttributeCollectorDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.derivedattributecollector.DerivedAttributeCollectorForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.nativea2.NativeA2ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.nativeb2.NativeB2ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person.Person;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person.PersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person.PersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person.PersonMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person1.Person1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.person1.Person1ForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.school.School;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.school.SchoolDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.school.SchoolForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.sextype.SexType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.student.Student;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.student.StudentDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.student.StudentForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.student.StudentIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.twowaycollector.TwoWayCollector;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.twowaycollector.TwoWayCollectorDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.navigationtest.navigationtest.twowaycollector.TwoWayCollectorForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.NavigationTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.ImmutableSet.of;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertNull;
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

        assertAttributesAndRelations(aDao.getById(a.identifier()).orElseThrow(), List.of(bId.getIdentifier()), List.of(cId.getIdentifier()));

        C c2 = cDao.create(CForCreate.builder().withName("c").build());
        CIdentifier c2Id = c2.identifier();
        B b2 = bDao.create(BForCreate.builder().withName("b").withC(c).build());
        BIdentifier b2Id = b2.identifier();

        assertAttributesAndRelations(aDao.getById(a.identifier()).orElseThrow(), List.of(bId.getIdentifier(), b2Id.getIdentifier()), List.of(cId.getIdentifier(), c2Id.getIdentifier()));
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

    @Inject
    ACDao acDao;

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4986")
    @TestCase("InheritedRelationNavigationAfterAny")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022"
    })
    public void testInheritedRelationNavigationAfterAny() {
        B b1 = bDao.create(BForCreate.builder().withName("sameName").build());
        B b2 = bDao.create(BForCreate.builder().withName("sameName").build());
        AC ac = acDao.create(ACForCreate.builder().withBlist(List.of(b1, b2)).build());

        Optional<B> oneBOnFilteredA = acDao.queryOneBOnFilteredA(ac);
        assertTrue(oneBOnFilteredA.isPresent());
        assertThat(List.of(b1.identifier().getIdentifier(), b2.identifier().getIdentifier()), hasItem(oneBOnFilteredA.get().identifier().getIdentifier()));

        acDao.delete(ac);

        AC ac1 = acDao.create(ACForCreate.builder().build());
        assertTrue( acDao.queryOneBOnFilteredA(ac1).isEmpty());
    }

        @SuppressWarnings("unchecked")
    private void assertAttributesAndRelations(A a, Collection<Serializable> bIds, Collection<Serializable> cIds) {
        assertThat(aDao.queryBbAll(a).selectList().stream().map(B::identifier).map(BIdentifier::getIdentifier).collect(Collectors.toList()), anyOf(toHasItems(bIds)));
        assertThat(aDao.queryBbAny(a).orElseThrow().identifier().getIdentifier(), anyOf(toIss(bIds)));

        assertEquals("b", a.getBbAnyName().orElseThrow());
        assertEquals("b", a.getSelfBName().orElseThrow());

        assertThat(aDao.queryBbAllFilter(a).selectList().stream().map(B::identifier).map(BIdentifier::getIdentifier).collect(Collectors.toList()), anyOf(toHasItems(bIds)));
        assertThat(aDao.queryBbAllFilterAny(a).orElseThrow().identifier().getIdentifier(), anyOf(toIss(bIds)));
        assertThat(aDao.queryBbAllFilterAny1(a).orElseThrow().identifier().getIdentifier(), anyOf(toIss(bIds)));

        assertEquals("b", a.getBbAllFilterAnyName().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName1().orElseThrow());
        assertEquals("b", a.getBbAllFilterAnyName2().orElseThrow());

        assertThat(aDao.querySelfbAllC(a).selectList().stream().map(C::identifier).map(CIdentifier::getIdentifier).collect(Collectors.toList()), anyOf(toHasItems(cIds)));
        assertThat(aDao.querySelfbAllCAny(a).orElseThrow().identifier().getIdentifier(), anyOf(toIss(cIds)));
        assertEquals("c", a.getSelfbAllCAnyName().orElseThrow());

        assertThat(aDao.queryBbAllC(a).selectList().stream().map(C::identifier).map(CIdentifier::getIdentifier).collect(Collectors.toList()), anyOf(toHasItems(cIds)));
        assertThat(aDao.queryBbAllCAny(a).orElseThrow().identifier().getIdentifier(), anyOf(toIss(cIds)));
        assertEquals("c", a.getBbAllCAnyName().orElseThrow());
    }

    @SuppressWarnings("rawtypes")
    private static Matcher[] toHasItems(Collection<Serializable> ids) {
        return ids.stream().map(CoreMatchers::hasItem).collect(Collectors.toList()).toArray(Matcher[]::new);
    }

    @SuppressWarnings("rawtypes")
    private static Matcher[] toIss(Collection<Serializable> ids) {
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
        Person father1 = personDao.createParents(
                person1,
                PersonForCreate.builder().withName("Father1").withSex(SexType.MALE).withBirthDate(LocalDate.of(1957, 1, 2)).build(),
                PersonMask.personMask().withName().withSex()
        );
        assertNotNull(father1.getName());
        assertNotNull(father1.getSex());
        assertNull(father1.getFatherName());
        assertNull(father1.getFatherName());
        assertNull(father1.getBirthDate());
        assertNull(father1.getGrandFather2Name());
        assertNull(father1.getGrandMother1Name());

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

        assertThat(personDao.queryMother(person1Loaded.get()).orElseThrow().identifier().getIdentifier(), equalTo(mother1.identifier().getIdentifier()));
        assertThat(personDao.queryFather(person1Loaded.get()).orElseThrow().identifier().getIdentifier(), equalTo(father1.identifier().getIdentifier()));
        assertThat(personDao.queryMother(personDao.queryMother(person1Loaded.get()).orElseThrow()).orElseThrow().identifier().getIdentifier(), equalTo(grandMother1.identifier().getIdentifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryMother(person1Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier().getIdentifier(), equalTo(greatGrandMother1.identifier().getIdentifier()));

        Optional<Person> person2Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person2.identifier().getIdentifier())).findAny();
        assertTrue(person2Loaded.isPresent());
        assertThat(person2Loaded.get().getMotherName(), equalTo(mother1.getName()));
        assertThat(person2Loaded.get().getFatherName(), equalTo(father1.getName()));
        assertThat(person2Loaded.get().getGrandMother1Name(), equalTo(grandMother1.getName()));
        assertThat(person2Loaded.get().getGreatGrandMother1Name(), equalTo(greatGrandMother1.getName()));
        assertTrue(person2Loaded.get().getMotherYoungerThanFather().orElseThrow());

        assertThat(personDao.queryMother(person2Loaded.get()).orElseThrow().identifier().getIdentifier(), equalTo(mother1.identifier().getIdentifier()));
        assertThat(personDao.queryFather(person2Loaded.get()).orElseThrow().identifier().getIdentifier(), equalTo(father1.identifier().getIdentifier()));
        assertThat(personDao.queryMother(personDao.queryMother(person2Loaded.get()).orElseThrow()).orElseThrow().identifier().getIdentifier(), equalTo(grandMother1.identifier().getIdentifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryMother(person2Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier().getIdentifier(), equalTo(greatGrandMother1.identifier().getIdentifier()));

        Optional<Person> person3Loaded = people.stream().filter(p -> p.identifier().getIdentifier().equals(person3.identifier().getIdentifier())).findAny();
        assertTrue(person3Loaded.isPresent());
        assertTrue(person3Loaded.get().getMotherName().isEmpty());
        assertThat(person3Loaded.get().getFatherName(), equalTo(person1.getName()));
        assertThat(person3Loaded.get().getGrandMother2Name(), equalTo(mother1.getName()));
        assertThat(person3Loaded.get().getGrandFather2Name(), equalTo(father1.getName()));

        assertTrue(personDao.queryMother(person3Loaded.get()).isEmpty());
        assertThat(personDao.queryFather(person3Loaded.get()).orElseThrow().identifier().getIdentifier(), equalTo(person1.identifier().getIdentifier()));
        assertThat(personDao.queryMother(personDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow().identifier().getIdentifier(), equalTo(mother1.identifier().getIdentifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow().identifier().getIdentifier(), equalTo(grandMother1.identifier().getIdentifier()));
        assertThat(personDao.queryMother(personDao.queryMother(personDao.queryMother(personDao.queryFather(person3Loaded.get()).orElseThrow()).orElseThrow()).orElseThrow()).orElseThrow().identifier().getIdentifier(), equalTo(greatGrandMother1.identifier().getIdentifier()));
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
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
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
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-5576")
    @TestCase("CollectionToObjectNavigationFromAll")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
    })
    void testCollectionToObjectNavigationFromAll() {

        A1 a1 = a1Dao.create(A1ForCreate.builder().withNumber(1).build());
        A1 a2 = a1Dao.create(A1ForCreate.builder().withNumber(2).build());
        A1 a3 = a1Dao.create(A1ForCreate.builder().withNumber(3).build());
        Base1 base1 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        Base1 base2 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withRelA(a2.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        Base1 base3 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withRelA(a3.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );
        assertEquals(6, base1Dao.query().selectList()
                .stream().map(Base1::getRelA)
                .map(A1::getNumber)
                .reduce(0, Integer::sum));
        // TODO JNG-5576
        DerivedAttributeCollector derivedAttributeCollector = derivedAttributeCollectorDao.create(DerivedAttributeCollectorForCreate.builder().build());
        assertEquals(6, derivedAttributeCollector.getSumRelAonAllBase1());
    }

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-5576")
    @TestCase("CollectionToCollectionNavigationFromAll")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
    })
    void testCollectionToCollectionNavigationFromAll() {

        A1 a1 = a1Dao.create(A1ForCreate.builder().withNumber(1).build());
        B1 b1 = b1Dao.create(B1ForCreate.builder().withNumber(1).withA(a1).build());
        B1 b2 = b1Dao.create(B1ForCreate.builder().withNumber(2).withA(a1).build());
        B1 b3 = b1Dao.create(B1ForCreate.builder().withNumber(3).withA(a1).build());
        B1 b4 = b1Dao.create(B1ForCreate.builder().withNumber(4).withA(a1).build());
        B1 b5 = b1Dao.create(B1ForCreate.builder().withNumber(5).withA(a1).build());
        B1 b6 = b1Dao.create(B1ForCreate.builder().withNumber(6).withA(a1).build());

        Base1 base1 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withBs(List.of(b1.adaptTo(B1ForCreate.class), b2.adaptTo(B1ForCreate.class)))
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        Base1 base2 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withBs(List.of(b3.adaptTo(B1ForCreate.class), b4.adaptTo(B1ForCreate.class)))
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        Base1 base3 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(3)
                .withBs(List.of(b5.adaptTo(B1ForCreate.class), b6.adaptTo(B1ForCreate.class)))
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        assertEquals(21, base1Dao.query().selectList()
                .stream().flatMap(b -> b.getBs().stream())
                .map(B1::getNumber)
                .reduce(0, Integer::sum));
        // TODO JNG-5576
        DerivedAttributeCollector derivedAttributeCollector = derivedAttributeCollectorDao.create(DerivedAttributeCollectorForCreate.builder().build());
        assertEquals(21, derivedAttributeCollector.getSumBsonAllBase1());
    }

    @Test
    @TestCase("ObjectToContainerNavigationFromSelf")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
    })
    void testObjectToContainerNavigationFromSelf() {
        A1 a1 = a1Dao.create(A1ForCreate.builder().withNumber(3).build());
        Base1 base1 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(420)
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(1).build())
                .build()
        );
        assertEquals(420, base1Dao.queryCompANumber(base1).orElseThrow());
    }

    @Inject
    A2Dao a2Dao;

    @Inject
    B2Dao b2Dao;

    @Inject
    Base2Dao base2Dao;

    @Test
    @TestCase("ObjectToObjectAsTypeFromSelf")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
    })
    void testObjectToObjectAsTypeFromSelf() {
        A2 a1 = a2Dao.create(A2ForCreate.builder().withNumber(314).build());

        Base2 base1 = base2Dao.create(Base2ForCreate.builder()
                .withA(a1.adaptTo(NativeA2ForCreate.class))
                .build()
        );
        assertEquals(314, base1.getNumNativeA2().orElseThrow());
    }

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-5576")
    @TestCase("ObjectToCollectionAsTypeFromSelf")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022",
    })
    void testObjectToCollectionAsTypeFromSelf() {
        A2 a1 = a2Dao.create(A2ForCreate.builder().withNumber(314).build());
        B2 b1 = b2Dao.create(B2ForCreate.builder().withNumber(1).build());
        B2 b2 = b2Dao.create(B2ForCreate.builder().withNumber(2).build());
        B2 b3 = b2Dao.create(B2ForCreate.builder().withNumber(3).build());

        Base2 base1 = base2Dao.create(Base2ForCreate.builder()
                .withA(a1.adaptTo(NativeA2ForCreate.class))
                .withBs(List.of(b1.adaptTo(NativeB2ForCreate.class),
                                b2.adaptTo(NativeB2ForCreate.class),
                                b3.adaptTo(NativeB2ForCreate.class)
                        )
                )
                .build()
        );
        // TODO JNG-5576
        //assertEquals(6, base1.getSumNativeBs().orElseThrow());

    }

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-5576")
    @TestCase("CollectionAsTypeFromAll")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022",
    })
    void testCollectionAsTypeFromAll() {
        A2 a1 = a2Dao.create(A2ForCreate.builder().withNumber(1).build());
        A2 a2 = a2Dao.create(A2ForCreate.builder().withNumber(2).build());
        A2 a3 = a2Dao.create(A2ForCreate.builder().withNumber(3).build());

        Base2 base1 = base2Dao.create(Base2ForCreate.builder()
                .withA(a1.adaptTo(NativeA2ForCreate.class))
                .build()
        );

        // TODO JNG-5576
        //assertEquals(6, base1.getSumAllNativeA().orElseThrow());
    }

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-5575")
    @TestCase("ObjectFilterFromSelf")
    @Requirement(reqs = {

    })
    void testObjectFilterFromSelf() {
        A1 a1 = a1Dao.create(A1ForCreate.builder().withNumber(1).build());

        Base1 base1 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(25)
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        Base1 base2 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(100)
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        // TODO JNG-5575
        //assertTrue(base1.getBase1NumberIs100().isEmpty());
        //assertEquals(100, base2.getBase1NumberIs100().orElseThrow());

    }

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-5575")
    @TestCase("ObjectToObjectFilterFromSelf")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022",
    })
    void testObjectToObjectFilterFromSelf() {
        A1 a1 = a1Dao.create(A1ForCreate.builder().withNumber(25).build());
        A1 a2 = a1Dao.create(A1ForCreate.builder().withNumber(100).build());

        Base1 base1 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(1)
                .withRelA(a1.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        Base1 base2 = base1Dao.create(Base1ForCreate.builder()
                .withNumber(1)
                .withRelA(a2.adaptTo(A1ForCreate.class))
                .withCompA(A1ForCreate.builder().withNumber(4).build())
                .build()
        );

        // TODO JNG-5575
        //assertTrue(base1.getRelANumberIs100().isEmpty());
        //assertEquals(100, base2.getRelANumberIs100().orElseThrow());
    }

    @Inject
    A3Dao a3Dao;

    @Inject
    B3Dao b3Dao;

    @Inject
    TwoWayCollectorDao twoWayCollectorDao;

    @Test
    @TestCase("NavigatingBetweenTwoWayRelations")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
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
            "REQ-EXPR-008",
            "REQ-EXPR-022",
    })
    void testNavigatingBetweenTwoWayRelations() {

        B3 b1 = b3Dao.create(B3ForCreate.builder().withNumber(1).build());
        B3 b2 = b3Dao.create(B3ForCreate.builder().withNumber(2).build());
        B3 b3 = b3Dao.create(B3ForCreate.builder().withNumber(3).build());

        A3 a1 = a3Dao.create(A3ForCreate.builder()
                .withNumber(10)
                .withBs(List.of(b1.adaptTo(B3ForCreate.class), b2.adaptTo(B3ForCreate.class), b3.adaptTo(B3ForCreate.class)))
                .build()
        );

        assertEquals(10, a1.getSumBsA().orElseThrow());
        assertEquals(6, a1.getSumBsABs().orElseThrow());

        final Set<Optional<Integer>> setOfSumABsA = b3Dao.query().selectList().stream().map(B3::getSumABsA).collect(Collectors.toSet());
        final Set<Optional<Integer>> setOfSumABS = b3Dao.query().selectList().stream().map(B3::getSumABs).collect(Collectors.toSet());
        assertThat(setOfSumABsA, equalTo(of(Optional.of(10))));
        assertThat(setOfSumABS, equalTo(of(Optional.of(6))));

        TwoWayCollector collector = twoWayCollectorDao
                .create(TwoWayCollectorForCreate
                        .builder()
                        .build());
        // TODO JNG-5576
        //assertEquals(10, collector.getSumAllBsFromA().orElseThrow());
        assertEquals(6, collector.getSumAllBsFromAFiltered().orElseThrow());
        // TODO JNG-5576
        //assertEquals(6, collector.getSumAllAFromBs().orElseThrow());

    }

    @Test
    @TestCase("ContainsFunction")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022",
    })
    void testContainsFunction() {
        B3 b1 = b3Dao.create(B3ForCreate.builder().withNumber(3).build());

        A3 a1 = a3Dao.create(A3ForCreate.builder()
                .withNumber(10)
                .withBs(List.of(b1.adaptTo(B3ForCreate.class)))
                .withB(b1)
                .build()
        );

        assertTrue(a1.getContainsTest().orElseThrow());
        assertTrue(a1.getContainsTest1().orElseThrow());
    }

    @Inject
    StudentDao studentDao;

    @Inject
    ClassDao classDao;

    @Inject
    SchoolDao schoolDao;

    @Inject
    APDao apDao;
    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-5579")
    @TestCase("DerivedHead")
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
    void testDerivedHead() {

        School school1 = schoolDao.create(SchoolForCreate.builder().withName("School1").build());

        Class_ class1 = schoolDao.createClasses(school1, Class_ForCreate.builder().withName("Class1/School1").build());
        Class_ class2 = schoolDao.createClasses(school1, Class_ForCreate.builder().withName("Class2/School1").build());

        Student student_1_1 = classDao.createStudents(class1, StudentForCreate.builder().withName("Student1/Class1").withHeight(180).build());
        Person1 father_1_1 = studentDao.createParents(student_1_1, Person1ForCreate.builder().withName("Father/Student1").withHeight(190).withSex(SexType.MALE).build());
        Person1 mother_1_1 = studentDao.createParents(student_1_1, Person1ForCreate.builder().withName("Mother/Student1/Class1").withHeight(180).withSex(SexType.FEMALE).build());

        Student student_2_1 = classDao.createStudents(class1, StudentForCreate.builder().withName("Student2/Class1").withHeight(170).build());
        Person1 mother_2_1 = studentDao.createParents(student_2_1, Person1ForCreate.builder().withName("Mother/Student2/Class1").withHeight(160).withSex(SexType.FEMALE).build());

        Student student_3_1 = classDao.createStudents(class1, StudentForCreate.builder().withName("Student3/Class1").withHeight(180).build());
        Person1 mother_3_1 = studentDao.createParents(student_3_1, Person1ForCreate.builder().withName("Mother/Student3/Class1").withHeight(180).withSex(SexType.FEMALE).build());

        Student student_1_2 = classDao.createStudents(class2, StudentForCreate.builder().withName("Student1/Class2").withHeight(160).build());
        Person1 mother_1_2 = studentDao.createParents(student_1_2, Person1ForCreate.builder().withName("Mother/Student1/Class2").withHeight(150).withSex(SexType.FEMALE).build());

        AP ap = apDao.create(APForCreate.builder().build());

        assertEquals(2, schoolDao.queryClasses(school1).count());
        assertThat(List.of(classDao.queryTallestStudent(class1).orElseThrow().identifier().getIdentifier())
                , Matchers.anyOf(hasItem(student_1_1.identifier().getIdentifier()), hasItem(student_3_1.identifier().getIdentifier()))
        );

        assertThat(classDao.queryTallestStudents(class1).selectList().stream().map(Student::identifier).map(StudentIdentifier::getIdentifier).toList()
                , Matchers.allOf(hasItem(student_1_1.identifier().getIdentifier()), hasItem(student_3_1.identifier().getIdentifier()))
        );

        // TODO JNG-5579
        assertEquals(2, schoolDao.queryTallestStudentMothers(school1).count());
        // TODO JNG-5579
        assertEquals(3, schoolDao.queryTallestStudentsMothers(school1).count());

        // TODO JNG-5579
        assertEquals(2, apDao.queryTallestStudentInEachClass(ap).selectList().size());
        // TODO JNG-5579
        assertEquals(2, apDao.queryTallestStudentInEachClassMother(ap).selectList().size());

        // bad sql grammar exception check after JNG-5579 fix
        //apDao.queryTallestStudentInEachClassMother(ap).count();


    }


}
