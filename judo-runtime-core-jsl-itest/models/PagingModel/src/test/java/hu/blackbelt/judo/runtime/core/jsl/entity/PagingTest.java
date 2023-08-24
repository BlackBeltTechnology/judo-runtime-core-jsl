package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.item.Item;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.item.ItemAttribute;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.item.ItemDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.list.List;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.list.ListAttribute;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.list.ListDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.list.ListIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.logentry.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PagingDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslWithOneInjectionTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class PagingTest extends AbstractJslWithOneInjectionTest {
    @Inject
    ItemDao itemDao;

    @Inject
    ListDao listDao;

    @Inject
    LogEntryDao logEntryDao;


    @Override
    public Module getModelDaoModule() {
        return new PagingDaoModules();
    }

    @Override
    public String getModelName() {
        return "Paging";
    }

    /**
     * Testing the limit and offset in bigger data and combine other query customizer methods (filter, order).
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel Paging.jsl
     *
     */
    @Test
    @TestCase("Windowing")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-006",
            "REQ-EXPR-021"
    })
    public void testWindowing() {

        Map<Character, ListIdentifier> listIds = new TreeMap<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            final java.util.List<Item> items = new ArrayList<>();
            for (int i = 0; i < 'Z' - ch + 1; i++) {
                final String topic;
                if (ch < 'E' && i < 3) {
                    topic = "Topic" + (char) (ch + i);
                } else {
                    topic = null;
                }
                items.add(Item.builder().withNumber(i + 1).withTopic(topic).build());
            }
            List list = listDao.create(List.builder().withName("List_" + ch).withItems(items).build());

            listIds.put(ch, list.identifier());
        }

        // List 1 to 4

        java.util.List<ListIdentifier> list1to4Ids = listDao.query().orderBy(ListAttribute.NAME).execute(4).stream().map(List::identifier).toList();
        java.util.List<ListIdentifier> expectedList1to4Ids = listIds.values().stream().limit(4).toList();
        assertThat(list1to4Ids, equalTo(expectedList1to4Ids));
        assertThat(list1to4Ids, equalTo(java.util.List.of(listIds.get('A'), listIds.get('B'), listIds.get('C'), listIds.get('D'))));

        // List 5 to 8

        java.util.List<ListIdentifier> list5to8Ids = listDao.query().orderBy(ListAttribute.NAME).execute(4, 4).stream().map(List::identifier).toList();
        java.util.List<ListIdentifier> expectedList5to8Ids = listIds.values().stream().skip(4).limit(4).toList();
        assertThat(list5to8Ids, equalTo(expectedList5to8Ids));
        assertThat(list5to8Ids, equalTo(java.util.List.of(listIds.get('E'), listIds.get('F'), listIds.get('G'), listIds.get('H'))));

        // Lists 1 to 4 ['A', 'B']

        java.util.List<ListIdentifier> lists2Of4Ids = listDao
                .query()
                .orderBy(ListAttribute.NAME)
                .filterBy("this.name == 'List_A' or this.name == 'List_B'")
                .execute(4)
                .stream().map(List::identifier)
                .toList();
        java.util.List<ListIdentifier> expectedLists2Of4Ids = listIds.entrySet().stream()
                .filter(e -> e.getKey() == 'A' || e.getKey() == 'B')
                .limit(4)
                .map(Map.Entry::getValue)
                .toList();
        assertThat(lists2Of4Ids, equalTo(expectedLists2Of4Ids));
        assertThat(lists2Of4Ids, equalTo(java.util.List.of(listIds.get('A'), listIds.get('B'))));

        // List 1 to 4 reversed

        java.util.List<ListIdentifier> reversedList1to4Ids = listDao
                .query()
                .orderByDescending(ListAttribute.NAME)
                .execute(4)
                .stream().map(List::identifier)
                .toList();
        assertThat(reversedList1to4Ids, equalTo(java.util.List.of(listIds.get('Z'), listIds.get('Y'), listIds.get('X'), listIds.get('W'))));

        // List 5 to 8 reversed

        java.util.List<ListIdentifier> reversedList5to8Ids = listDao
                .query()
                .orderByDescending(ListAttribute.NAME)
                .execute(4, 4)
                .stream()
                .map(List::identifier)
                .toList();
        assertThat(reversedList5to8Ids, equalTo(java.util.List.of(listIds.get('V'), listIds.get('U'), listIds.get('T'), listIds.get('S'))));

        // List items 1 to 10

        java.util.List<String> itemsList1To10FqNames = itemDao
                .query().orderByDescending(ItemAttribute.NUMBER)
                .orderBy(ItemAttribute.LIST_NAME)
                .execute(10)
                .stream()
                .map(l -> l.getListName().get() + "." + l.getNumber().get())
                .toList();
        assertThat(itemsList1To10FqNames, equalTo(java.util.List.of("List_A.26", "List_A.25", "List_B.25", "List_A.24", "List_B.24", "List_C.24", "List_A.23", "List_B.23", "List_C.23", "List_D.23")));

        // List items 11 to 20

        java.util.List<String> itemsList11To20FqNames = itemDao
                .query().orderByDescending(ItemAttribute.NUMBER)
                .orderBy(ItemAttribute.LIST_NAME)
                .execute(10, 10)
                .stream()
                .map(l -> l.getListName().get() + "." + l.getNumber().get())
                .toList();
        assertThat(itemsList11To20FqNames, equalTo(java.util.List.of("List_A.22", "List_B.22", "List_C.22", "List_D.22", "List_E.22", "List_A.21", "List_B.21", "List_C.21", "List_D.21", "List_E.21")));

        // List items by Topic 1 to 10

        java.util.List<String> itemsByTopic1To10FqNames = itemDao
                .query().orderBy(ItemAttribute.TOPIC)
                .orderByDescending(ItemAttribute.NUMBER)
                .orderBy(ItemAttribute.LIST_NAME)
                .execute(10)
                .stream()
                .map(l -> l.getListName().get() + "." + l.getNumber().get() + "#" + l.getTopic().get())
                .toList();
        assertThat(itemsByTopic1To10FqNames, equalTo(java.util.List.of("List_A.1#TopicA", "List_A.2#TopicB", "List_B.1#TopicB", "List_A.3#TopicC", "List_B.2#TopicC", "List_C.1#TopicC", "List_B.3#TopicD", "List_C.2#TopicD", "List_D.1#TopicD", "List_C.3#TopicE")));

        // List items by Topic 11 to 20

        java.util.List<String> itemsByTopic11To20FqNames = itemDao
                .query().orderBy(ItemAttribute.TOPIC)
                .orderByDescending(ItemAttribute.NUMBER)
                .orderBy(ItemAttribute.LIST_NAME)
                .execute(10,10)
                .stream()
                .map(l -> l.getListName().get() + "." + l.getNumber().get() + "#" + l.getTopic().orElse(null))
                .toList();
        assertThat(itemsByTopic11To20FqNames, equalTo(java.util.List.of("List_D.2#TopicE", "List_D.3#TopicF", "List_A.26#null", "List_A.25#null", "List_B.25#null", "List_A.24#null", "List_B.24#null", "List_C.24#null", "List_A.23#null", "List_B.23#null")));

        // List items by Topic 21 to 30

        java.util.List<String> itemsByTopic21To30FqNames= itemDao
                .query().orderBy(ItemAttribute.TOPIC)
                .orderByDescending(ItemAttribute.NUMBER)
                .orderBy(ItemAttribute.LIST_NAME)
                .execute(10,20)
                .stream()
                .map(l -> l.getListName().get() + "." + l.getNumber().get() + "#" + l.getTopic().orElse(null))
                .toList();
        assertThat(itemsByTopic21To30FqNames, equalTo(java.util.List.of("List_C.23#null", "List_D.23#null", "List_A.22#null", "List_B.22#null", "List_C.22#null", "List_D.22#null", "List_E.22#null", "List_A.21#null", "List_B.21#null", "List_C.21#null")));

        final java.util.List<Integer> integersFrom1To26 = IntStream.rangeClosed(1, 26).boxed().collect(Collectors.toList());
        final java.util.List<Integer> integersFrom26To1 = IntStream.rangeClosed(1, 26).boxed().sorted((i, j) -> j - i).collect(Collectors.toList());

        List a = listDao.getById(listIds.get('A')).orElseThrow();

        java.util.List<Integer> customSortedItemsOfListA = listDao.queryItems(a).orderBy(ItemAttribute.NUMBER).execute().stream().map(i -> i.getNumber().get()).toList();
        assertThat(customSortedItemsOfListA, equalTo(integersFrom1To26));

        java.util.List<Integer> customReservedSortedItemsOfListA = listDao.queryItems(a).orderByDescending(ItemAttribute.NUMBER).execute().stream().map(i -> i.getNumber().get()).toList();
        assertThat(customReservedSortedItemsOfListA, equalTo(integersFrom26To1));

    }


    /**
     * Testing the query customizer execute method with limit and offset parameter variants.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel Paging.jsl
     *
     */
    @Test
    @TestCase("LimitAndOffsetVariations")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testLimitAndOffsetVariations() {

        Item ent1 = itemDao.create(Item.builder().withNumber(2).build());
        Item ent2 = itemDao.create(Item.builder().withNumber(1).build());

        java.util.List<Item> list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(1);

        assertEquals(1, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(null);

        assertEquals(2, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());
        assertEquals(ent1.identifier(), list.get(1).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(0);

        assertEquals(2, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());
        assertEquals(ent1.identifier(), list.get(1).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(3);

        assertEquals(2, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());
        assertEquals(ent1.identifier(), list.get(1).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(1, null);

        assertEquals(1, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(null, null);

        assertEquals(2, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());
        assertEquals(ent1.identifier(), list.get(1).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(0, null);

        assertEquals(2, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());
        assertEquals(ent1.identifier(), list.get(1).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(3, null);

        assertEquals(2, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());
        assertEquals(ent1.identifier(), list.get(1).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(2, 0);

        assertEquals(2, list.size());
        assertEquals(ent2.identifier(), list.get(0).identifier());
        assertEquals(ent1.identifier(), list.get(1).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(2, 1);

        assertEquals(1, list.size());
        assertEquals(ent1.identifier(), list.get(0).identifier());

        list = itemDao
                .query()
                .orderBy(ItemAttribute.NUMBER)
                .execute(2, 2);

        assertEquals(0, list.size());
    }

    /**
     * Testing the limit and offset with timestamp type attribute.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel Paging.jsl
     *
     */
    @Test
    @TestCase("PaginationByTimestamp")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testPaginationByTimestamp() {

        LogEntry entry1 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 111_000_000))).withMessage("Message1").build());
        LogEntry entry2 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 110_000_000))).withMessage("Message2").build());
        LogEntry entry3 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 100_000_000))).withMessage("Message3").build());
        LogEntry entry4 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 0))).withMessage("Message4").build());
        LogEntry entry5 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 111_000_000))).withMessage("Message5").build());
        LogEntry entry6 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 110_000_000))).withMessage("Message6").build());
        LogEntry entry7 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 100_000_000))).withMessage("Message7").build());
        LogEntry entry8 = logEntryDao.create(LogEntry.builder().withTimestamp(LocalDateTime.of(LocalDate.of( 2021, 7, 29), LocalTime.of(15, 7, 1, 0))).withMessage("Message8").build());

        java.util.List<LogEntryIdentifier> logEntries48 = logEntryDao.query().orderBy(LogEntryAttribute.TIMESTAMP).execute(2).stream().map(LogEntry::identifier).toList();

        assertThat(logEntries48, containsInAnyOrder(entry4.identifier(), entry8.identifier()));

        java.util.List<LogEntryIdentifier> logEntries37 = logEntryDao.query().orderBy(LogEntryAttribute.TIMESTAMP).execute(2,2).stream().map(LogEntry::identifier).toList();

        assertThat(logEntries37, containsInAnyOrder(entry3.identifier(), entry7.identifier()));

        java.util.List<LogEntryIdentifier> logEntries26 = logEntryDao.query().orderBy(LogEntryAttribute.TIMESTAMP).execute(2,4).stream().map(LogEntry::identifier).toList();

        assertThat(logEntries26, containsInAnyOrder(entry2.identifier(), entry6.identifier()));

        java.util.List<LogEntryIdentifier> logEntries15 = logEntryDao.query().orderBy(LogEntryAttribute.TIMESTAMP).execute(2,6).stream().map(LogEntry::identifier).toList();

        assertThat(logEntries15, containsInAnyOrder(entry1.identifier(), entry5.identifier()));
    }

}
