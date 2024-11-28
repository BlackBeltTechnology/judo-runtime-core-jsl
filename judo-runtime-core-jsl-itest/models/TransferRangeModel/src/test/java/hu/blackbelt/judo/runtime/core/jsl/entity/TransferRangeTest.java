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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.apple.Apple;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.apple.AppleDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.apple.AppleForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.basket.BasketDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithdefaultrange.CollectionAggRelationTransferWithDefaultRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithdefaultrange.CollectionAggRelationTransferWithDefaultRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithdefaultrange.CollectionAggRelationTransferWithDefaultRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithderivedrange.CollectionAggRelationTransferWithDerivedRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithderivedrange.CollectionAggRelationTransferWithDerivedRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithderivedrange.CollectionAggRelationTransferWithDerivedRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithselfrange.CollectionAggRelationTransferWithSelfRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithselfrange.CollectionAggRelationTransferWithSelfRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionaggrelationtransferwithselfrange.CollectionAggRelationTransferWithSelfRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithdefaultrange.CollectionAssRelationTransferWithDefaultRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithdefaultrange.CollectionAssRelationTransferWithDefaultRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithdefaultrange.CollectionAssRelationTransferWithDefaultRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithderivedrange.CollectionAssRelationTransferWithDerivedRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithderivedrange.CollectionAssRelationTransferWithDerivedRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithderivedrange.CollectionAssRelationTransferWithDerivedRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithselfrange.CollectionAssRelationTransferWithSelfRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithselfrange.CollectionAssRelationTransferWithSelfRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionassrelationtransferwithselfrange.CollectionAssRelationTransferWithSelfRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionrelationtransferwithtransientrange.CollectionRelationTransferWithTransientRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionrelationtransferwithtransientrange.CollectionRelationTransferWithTransientRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.collectionrelationtransferwithtransientrange.CollectionRelationTransferWithTransientRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.fruit.FruitDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.itemtransfer.ItemTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.itemtransfer.ItemTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.itemtransfer.ItemTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.pear.Pear;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.pear.PearDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.pear.PearForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithdefaultrange.SingleAggRelationTransferWithDefaultRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithdefaultrange.SingleAggRelationTransferWithDefaultRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithdefaultrange.SingleAggRelationTransferWithDefaultRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithderivedrange.SingleAggRelationTransferWithDerivedRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithderivedrange.SingleAggRelationTransferWithDerivedRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithderivedrange.SingleAggRelationTransferWithDerivedRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithselfrange.SingleAggRelationTransferWithSelfRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithselfrange.SingleAggRelationTransferWithSelfRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleaggrelationtransferwithselfrange.SingleAggRelationTransferWithSelfRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithdefaultrange.SingleAssRelationTransferWithDefaultRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithdefaultrange.SingleAssRelationTransferWithDefaultRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithdefaultrange.SingleAssRelationTransferWithDefaultRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithderivedrange.SingleAssRelationTransferWithDerivedRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithderivedrange.SingleAssRelationTransferWithDerivedRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithderivedrange.SingleAssRelationTransferWithDerivedRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithselfrange.SingleAssRelationTransferWithSelfRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithselfrange.SingleAssRelationTransferWithSelfRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithselfrange.SingleAssRelationTransferWithSelfRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singlerelationtransferwithtransientrange.SingleRelationTransferWithTransientRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singlerelationtransferwithtransientrange.SingleRelationTransferWithTransientRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singlerelationtransferwithtransientrange.SingleRelationTransferWithTransientRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferbasket.TransferBasket;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferbasket.TransferBasketDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferbasket.TransferBasketForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transfercar.TransferCar;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transfercar.TransferCarDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transfercar.TransferCarForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transfercreature.TransferCreature;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transfercreature.TransferCreatureDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transfercreature.TransferCreatureForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferfruit.TransferFruit;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferfruit.TransferFruitForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferplanet.TransferPlanet;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferplanet.TransferPlanetDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferplanet.TransferPlanetForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferwheel.TransferWheel;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferwheel.TransferWheelDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferRangeDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import liquibase.pro.packaged.J;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.shaded.com.google.common.collect.ImmutableSet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture.MARK_SELECTED_RANGE_ITEMS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class TransferRangeTest {

    final static Map<String, Object> context = new HashMap<>(Map.of(MARK_SELECTED_RANGE_ITEMS, true));

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TransferRange", new TransferRangeDaoModules(), context);

    @Inject
    AppleDao appleDao;

    @Inject
    PearDao pearDao;

    @Inject
    FruitDao fruitDao;

    @Inject
    BasketDao basketDao;

    @Inject
    TransferBasketDao transferBasketDao;


    // Single Relation RangeTests

    @Inject
    ItemTransferDao itemTransferDao;

    @Inject
    SingleAssRelationTransferWithDefaultRangeDao singleAssRelationTransferWithDefaultRangeDao;

    @Inject
    SingleAggRelationTransferWithDefaultRangeDao singleAggRelationTransferWithDefaultRangeDao;

    @Inject
    SingleAssRelationTransferWithDerivedRangeDao singleAssRelationTransferWithDerivedRangeDao;

    @Inject
    SingleAggRelationTransferWithDerivedRangeDao singleAggRelationTransferWithDerivedRangeDao;

    @Inject
    SingleRelationTransferWithTransientRangeDao singleRelationTransferWithTransientRangeDao;

    @Inject
    SingleAssRelationTransferWithSelfRangeDao singleAssRelationTransferWithSelfRangeDao;

    @Inject
    SingleAggRelationTransferWithSelfRangeDao singleAggRelationTransferWithSelfRangeDao;

    @Test
    public void testSingleAssAndAggRelationTransferWithDefaultRange() {
        SingleAssRelationTransferWithDefaultRangeDao transferAssDao = singleAssRelationTransferWithDefaultRangeDao;
        SingleAggRelationTransferWithDefaultRangeDao transferAggDao = singleAggRelationTransferWithDefaultRangeDao;

        // Single Compositon Association Range
        SingleAssRelationTransferWithDefaultRange transferAss = transferAssDao.create(
                SingleAssRelationTransferWithDefaultRangeForCreate.builder().build()
        );

        SingleAggRelationTransferWithDefaultRange transferAgg = transferAggDao.create(
                SingleAggRelationTransferWithDefaultRangeForCreate.builder().build()
        );
        // no range methods for default range
        assertFalse(hasMethodWithName("getRangeOf", transferAssDao));
        assertFalse(hasMethodWithName("getRangeOf", transferAggDao));
    }

    @Test
    public void testSingleAssociationAssociationRelationTransferWithDerivedRange() {
        SingleAssRelationTransferWithDerivedRangeDao transferAssDao = singleAssRelationTransferWithDerivedRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        // Single Compositon Association Range
        SingleAssRelationTransferWithDerivedRange transferAss = transferAssDao.create(
                SingleAssRelationTransferWithDerivedRangeForCreate.builder().build()
        );

        assertTrue(hasMethodWithName("getRangeOfSingleAssAssItem", transferAssDao));

        List<ItemTransfer> rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAssItem(transferAss);

        assertTrue(rangeOfSingleAssAssItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(nameOfBItems::contains)
        );
        assertThat(
                rangeOfSingleAssAssItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(new HashSet<>(nameOfAItems))
        );

        // check none of the range element is selected
        assertTrue(rangeOfSingleAssAssItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAssDao.setSingleAssAssItem(transferAss, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().equals("A1"))
                .findAny().orElseThrow()
        );

        // check selected
        rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAssItem(transferAss);

        List<ItemTransfer> selected = getSelectedItems(rangeOfSingleAssAssItem);

        assertEquals(1, selected.size());
        assertEquals("A1", selected.get(0).getName().orElseThrow());

        transferAssDao.unsetSingleAssAssItem(transferAss);

        rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAssItem(transferAss);

        assertEquals(0, getSelectedItems(rangeOfSingleAssAssItem).size());

    }

    @Test
    public void testSingleAssociationAggregationRelationTransferWithDerivedRange() {
        SingleAggRelationTransferWithDerivedRangeDao transferAggDao = singleAggRelationTransferWithDerivedRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        SingleAggRelationTransferWithDerivedRange transferAgg = transferAggDao.create(
                SingleAggRelationTransferWithDerivedRangeForCreate.builder().build()
        );

        assertTrue(hasMethodWithName("getRangeOfSingleAssAggItem", transferAggDao));

        List<ItemTransfer> rangeOfSingleAssAggItem = transferAggDao.getRangeOfSingleAssAggItem(transferAgg);

        assertTrue(rangeOfSingleAssAggItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(nameOfBItems::contains)
        );
        assertThat(
                rangeOfSingleAssAggItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(new HashSet<>(nameOfAItems))
        );

        // check none of the range element is selected
        assertTrue(rangeOfSingleAssAggItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAggDao.setSingleAssAggItem(transferAgg, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().equals("A1"))
                .findAny().orElseThrow()
        );

        // check selected
        rangeOfSingleAssAggItem = transferAggDao.getRangeOfSingleAssAggItem(transferAgg);

        List<ItemTransfer> selected = getSelectedItems(rangeOfSingleAssAggItem);

        assertEquals(1, selected.size());
        assertEquals("A1", selected.get(0).getName().orElseThrow());

        transferAggDao.unsetSingleAssAggItem(transferAgg);

        rangeOfSingleAssAggItem = transferAggDao.getRangeOfSingleAssAggItem(transferAgg);

        assertEquals(0, getSelectedItems(rangeOfSingleAssAggItem).size());

    }

    @Test
    public void testSingleAssociationAssociationRelationTransferWithSelfRelatedRange() {
        SingleAssRelationTransferWithSelfRangeDao transferAssDao = singleAssRelationTransferWithSelfRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        HashSet<String> itemNames = new HashSet<>(nameOfAItems);
        itemNames.addAll(nameOfBItems);

        SingleAssRelationTransferWithSelfRange transferAss = transferAssDao.create(
                SingleAssRelationTransferWithSelfRangeForCreate.builder().withItems(
                        aItems.stream()
                                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                                .toList()
                ).build()
        );

        List<ItemTransfer> rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAssItem(transferAss);

        List<String> notRelatedItemElementNames = itemNames.stream().filter(n -> !n.matches("A1|A2|A3")).toList();
        assertTrue(rangeOfSingleAssAssItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(notRelatedItemElementNames::contains)
        );

        assertThat(
                rangeOfSingleAssAssItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(nameOfAItems.stream()
                        .filter(n -> n.matches("A1|A2|A3"))
                        .collect(Collectors.toSet()))
        );

        // check none of the range element is selected
        assertTrue(rangeOfSingleAssAssItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAssDao.setSingleAssAssItem(transferAss, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().equals("A1"))
                .findAny().orElseThrow()
        );

        // check selected
        rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAssItem(transferAss);

        List<ItemTransfer> selected = getSelectedItems(rangeOfSingleAssAssItem);

        assertEquals(1, selected.size());
        assertEquals("A1", selected.get(0).getName().orElseThrow());

        transferAssDao.unsetSingleAssAssItem(transferAss);

        rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAssItem(transferAss);

        assertEquals(0, getSelectedItems(rangeOfSingleAssAssItem).size());

    }

    @Test
    public void testSingleAssociationAggregationRelationTransferWithSelfRelatedRange() {
        SingleAggRelationTransferWithSelfRangeDao transferAssDao = singleAggRelationTransferWithSelfRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        HashSet<String> itemNames = new HashSet<>(nameOfAItems);
        itemNames.addAll(nameOfBItems);

        SingleAggRelationTransferWithSelfRange transferAss = transferAssDao.create(
                SingleAggRelationTransferWithSelfRangeForCreate.builder().withItems(
                        aItems.stream()
                                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                                .map(i -> ItemTransferForCreate.from(i.toMap()))
                                .toList()
                ).build()
        );

        List<ItemTransfer> rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAggItem(transferAss);

        List<String> notRelatedItemElementNames = itemNames.stream().filter(n -> !n.matches("A1|A2|A3")).toList();
        assertTrue(rangeOfSingleAssAssItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(notRelatedItemElementNames::contains)
        );

        assertThat(
                rangeOfSingleAssAssItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(nameOfAItems.stream()
                        .filter(n -> n.matches("A1|A2|A3"))
                        .collect(Collectors.toSet()))
        );

        // check none of the range element is selected
        assertTrue(rangeOfSingleAssAssItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAssDao.setSingleAssAggItem(transferAss, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().equals("A1"))
                .findAny().orElseThrow()
        );

        // check selected
        rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAggItem(transferAss);

        List<ItemTransfer> selected = getSelectedItems(rangeOfSingleAssAssItem);

        assertEquals(1, selected.size());
        assertEquals("A1", selected.get(0).getName().orElseThrow());

        transferAssDao.unsetSingleAssAggItem(transferAss);

        rangeOfSingleAssAssItem = transferAssDao.getRangeOfSingleAssAggItem(transferAss);

        assertEquals(0, getSelectedItems(rangeOfSingleAssAssItem).size());

    }

    @Test
    public void testSingleTransientRelationTransferWithRange() {
        SingleRelationTransferWithTransientRangeDao transferTransientDao = singleRelationTransferWithTransientRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        SingleRelationTransferWithTransientRange transferWithTransientRelation = transferTransientDao.create(
                SingleRelationTransferWithTransientRangeForCreate.builder().withItems(
                        aItems.stream()
                            .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                            .toList()
                ).build()
        );

        // Any Transient Range
        List<ItemTransfer> rangeOfTransientItemWithAnyRange = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);

        HashSet<String> itemNames = new HashSet<>(nameOfAItems);
        itemNames.addAll(nameOfBItems);
        assertThat(
                rangeOfTransientItemWithAnyRange.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(itemNames)
        );

        // check none of the range element is selected
        assertTrue(rangeOfTransientItemWithAnyRange.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferWithTransientRelation.setTransientItemWithAnyRange(aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().equals("A1"))
                .findAny().orElseThrow()
        );

        transferWithTransientRelation = transferTransientDao.update(transferWithTransientRelation);

        // check selected
        rangeOfTransientItemWithAnyRange = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);
        // check none of the range element is selected
        assertEquals(0, getSelectedItems(rangeOfTransientItemWithAnyRange).size());
        // Derived Transient Range
        List<ItemTransfer> rangeOfTransientItemWithDerivedRangeItem = transferTransientDao.getRangeOfTransientItemWithDerivedRange(transferWithTransientRelation);

        assertTrue(rangeOfTransientItemWithDerivedRangeItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(nameOfBItems::contains)
        );

        assertThat(
                rangeOfTransientItemWithDerivedRangeItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(new HashSet<>(nameOfAItems))
        );

        // check none of the range element is selected
        assertTrue(rangeOfTransientItemWithDerivedRangeItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferWithTransientRelation.setTransientItemWithAnyRange(aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().equals("A1"))
                .findAny().orElseThrow()
        );

        transferWithTransientRelation = transferTransientDao.update(transferWithTransientRelation);

        // check selected
        rangeOfTransientItemWithDerivedRangeItem = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);
        // check none of the range element is selected
        assertEquals(0, getSelectedItems(rangeOfTransientItemWithDerivedRangeItem).size());
        // Self Range for transient relation
        List<ItemTransfer> rangeOfTransientItemWithSelfRange = transferTransientDao.getRangeOfTransientItemWithSelfRange(transferWithTransientRelation);

        List<String> notItemElements = itemNames.stream().filter(n -> !n.matches("A1|A2|A3")).toList();
        assertTrue(rangeOfTransientItemWithSelfRange.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(notItemElements::contains)
        );

        assertThat(
                rangeOfTransientItemWithSelfRange.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(nameOfAItems.stream()
                        .filter(n -> n.matches("A1|A2|A3"))
                        .collect(Collectors.toSet()))
        );

        // check none of the range element is selected
        assertTrue(rangeOfTransientItemWithSelfRange.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferWithTransientRelation.setTransientItemWithAnyRange(aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().equals("A1"))
                .findAny().orElseThrow()
        );

        transferWithTransientRelation = transferTransientDao.update(transferWithTransientRelation);
        // check selected
        rangeOfTransientItemWithSelfRange = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);
        // check none of the range element is selected
        assertEquals(0, getSelectedItems(rangeOfTransientItemWithSelfRange).size());

    }

    @Inject
    CollectionAssRelationTransferWithDefaultRangeDao collectionAssRelationTransferWithDefaultRangeDao;

    @Inject
    CollectionAggRelationTransferWithDefaultRangeDao collectionAggRelationTransferWithDefaultRangeDao;

    @Inject
    CollectionAssRelationTransferWithDerivedRangeDao collectionAssRelationTransferWithDerivedRangeDao;

    @Inject
    CollectionAggRelationTransferWithDerivedRangeDao collectionAggRelationTransferWithDerivedRangeDao;

    @Inject
    CollectionRelationTransferWithTransientRangeDao collectionRelationTransferWithTransientRangeDao;

    @Inject
    CollectionAssRelationTransferWithSelfRangeDao collectionAssRelationTransferWithSelfRangeDao;

    @Inject
    CollectionAggRelationTransferWithSelfRangeDao collectionAggRelationTransferWithSelfRangeDao;

    @Test
    public void testCollectionAssAndAggRelationTransferWithDefaultRange() {
        CollectionAssRelationTransferWithDefaultRangeDao transferAssDao = collectionAssRelationTransferWithDefaultRangeDao;
        CollectionAggRelationTransferWithDefaultRangeDao transferAggDao = collectionAggRelationTransferWithDefaultRangeDao;

        // Collection Compositon Association Range
        CollectionAssRelationTransferWithDefaultRange transferAss = transferAssDao.create(
                CollectionAssRelationTransferWithDefaultRangeForCreate.builder().build()
        );

        CollectionAggRelationTransferWithDefaultRange transferAgg = transferAggDao.create(
                CollectionAggRelationTransferWithDefaultRangeForCreate.builder().build()
        );
        // no range methods for default range
        assertFalse(hasMethodWithName("getRangeOf", transferAssDao));
        assertFalse(hasMethodWithName("getRangeOf", transferAggDao));
    }

    @Test
    public void testCollectionAssociationAssociationRelationTransferWithDerivedRange() {
        CollectionAssRelationTransferWithDerivedRangeDao transferAssDao = collectionAssRelationTransferWithDerivedRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        // Collection Compositon Association Range
        CollectionAssRelationTransferWithDerivedRange transferAss = transferAssDao.create(
                CollectionAssRelationTransferWithDerivedRangeForCreate.builder().build()
        );

        assertTrue(hasMethodWithName("getRangeOfCollectionAssAssItem", transferAssDao));

        List<ItemTransfer> rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAssItem(transferAss);

        assertTrue(rangeOfCollectionAssAssItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(nameOfBItems::contains)
        );
        assertThat(
                rangeOfCollectionAssAssItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(new HashSet<>(nameOfAItems))
        );

        // check none of the range element is selected
        assertTrue(rangeOfCollectionAssAssItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAssDao.addCollectionAssAssItem(transferAss, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                .toList()
        );

        // check selected
        rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAssItem(transferAss);

        List<ItemTransfer> selected = getSelectedItems(rangeOfCollectionAssAssItem);

        assertEquals(3, selected.size());
        assertThat(new HashSet<>(List.of("A1", "A2", "A3")),
                equalTo(selected.stream().map(s -> s.getName().orElseThrow()).collect(Collectors.toSet()))
        );

        transferAssDao.removeCollectionAssAssItem(transferAss, selected);

        rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAssItem(transferAss);

        assertEquals(0, getSelectedItems(rangeOfCollectionAssAssItem).size());

    }

    @Test
    public void testCollectionAssociationAggregationRelationTransferWithDerivedRange() {
        CollectionAggRelationTransferWithDerivedRangeDao transferAggDao = collectionAggRelationTransferWithDerivedRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        CollectionAggRelationTransferWithDerivedRange transferAgg = transferAggDao.create(
                CollectionAggRelationTransferWithDerivedRangeForCreate.builder().build()
        );

        assertTrue(hasMethodWithName("getRangeOfCollectionAssAggItem", transferAggDao));

        List<ItemTransfer> rangeOfCollectionAssAggItem = transferAggDao.getRangeOfCollectionAssAggItem(transferAgg);

        assertTrue(rangeOfCollectionAssAggItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(nameOfBItems::contains)
        );
        assertThat(
                rangeOfCollectionAssAggItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(new HashSet<>(nameOfAItems))
        );

        // check none of the range element is selected
        assertTrue(rangeOfCollectionAssAggItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAggDao.addCollectionAssAggItem(transferAgg, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                .toList()
        );

        // check selected
        rangeOfCollectionAssAggItem = transferAggDao.getRangeOfCollectionAssAggItem(transferAgg);

        List<ItemTransfer> selected = getSelectedItems(rangeOfCollectionAssAggItem);

        assertEquals(3, selected.size());
        assertThat(new HashSet<>(List.of("A1", "A2", "A3")),
                equalTo(selected.stream().map(s -> s.getName().orElseThrow()).collect(Collectors.toSet()))
        );

        transferAggDao.removeCollectionAssAggItem(transferAgg, selected);

        rangeOfCollectionAssAggItem = transferAggDao.getRangeOfCollectionAssAggItem(transferAgg);

        assertEquals(0, getSelectedItems(rangeOfCollectionAssAggItem).size());

    }

    @Test
    public void testCollectionAssociationAssociationRelationTransferWithSelfRelatedRange() {
        CollectionAssRelationTransferWithSelfRangeDao transferAssDao = collectionAssRelationTransferWithSelfRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        HashSet<String> itemNames = new HashSet<>(nameOfAItems);
        itemNames.addAll(nameOfBItems);

        CollectionAssRelationTransferWithSelfRange transferAss = transferAssDao.create(
                CollectionAssRelationTransferWithSelfRangeForCreate.builder().withItems(
                        aItems.stream()
                                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                                .toList()
                ).build()
        );

        List<ItemTransfer> rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAssItem(transferAss);

        List<String> notRelatedItemElementNames = itemNames.stream().filter(n -> !n.matches("A1|A2|A3")).toList();
        assertTrue(rangeOfCollectionAssAssItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(notRelatedItemElementNames::contains)
        );

        assertThat(
                rangeOfCollectionAssAssItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(nameOfAItems.stream()
                        .filter(n -> n.matches("A1|A2|A3"))
                        .collect(Collectors.toSet()))
        );

        // check none of the range element is selected
        assertTrue(rangeOfCollectionAssAssItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAssDao.addCollectionAssAssItem(transferAss, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                .toList()
        );

        // check selected
        rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAssItem(transferAss);

        List<ItemTransfer> selected = getSelectedItems(rangeOfCollectionAssAssItem);

        assertEquals(3, selected.size());
        assertThat(new HashSet<>(List.of("A1", "A2", "A3")),
                equalTo(selected.stream().map(s -> s.getName().orElseThrow()).collect(Collectors.toSet()))
        );

        transferAssDao.removeCollectionAssAssItem(transferAss, selected);

        rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAssItem(transferAss);

        assertEquals(0, getSelectedItems(rangeOfCollectionAssAssItem).size());

    }

    @Test
    public void testCollectionAssociationAggregationRelationTransferWithSelfRelatedRange() {
        CollectionAggRelationTransferWithSelfRangeDao transferAssDao = collectionAggRelationTransferWithSelfRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        HashSet<String> itemNames = new HashSet<>(nameOfAItems);
        itemNames.addAll(nameOfBItems);

        CollectionAggRelationTransferWithSelfRange transferAss = transferAssDao.create(
                CollectionAggRelationTransferWithSelfRangeForCreate.builder().withItems(
                        aItems.stream()
                                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                                .map(i -> ItemTransferForCreate.from(i.toMap()))
                                .toList()
                ).build()
        );

        List<ItemTransfer> rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAggItem(transferAss);

        List<String> notRelatedItemElementNames = itemNames.stream().filter(n -> !n.matches("A1|A2|A3")).toList();
        assertTrue(rangeOfCollectionAssAssItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(notRelatedItemElementNames::contains)
        );

        assertThat(
                rangeOfCollectionAssAssItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(nameOfAItems.stream()
                        .filter(n -> n.matches("A1|A2|A3"))
                        .collect(Collectors.toSet()))
        );

        // check none of the range element is selected
        assertTrue(rangeOfCollectionAssAssItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferAssDao.addCollectionAssAggItem(transferAss, aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                .toList()
        );

        // check selected
        rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAggItem(transferAss);

        List<ItemTransfer> selected = getSelectedItems(rangeOfCollectionAssAssItem);

        assertEquals(3, selected.size());
        assertThat(new HashSet<>(List.of("A1", "A2", "A3")),
                equalTo(selected.stream().map(s -> s.getName().orElseThrow()).collect(Collectors.toSet()))
        );
        transferAssDao.removeCollectionAssAggItem(transferAss, selected);

        rangeOfCollectionAssAssItem = transferAssDao.getRangeOfCollectionAssAggItem(transferAss);

        assertEquals(0, getSelectedItems(rangeOfCollectionAssAssItem).size());

    }

    @Test
    public void testCollectionTransientRelationTransferWithRange() {
        CollectionRelationTransferWithTransientRangeDao transferTransientDao = collectionRelationTransferWithTransientRangeDao;

        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();

        List<ItemTransfer> aItemsA1toA3 = aItems
                .stream()
                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                .toList();
        CollectionRelationTransferWithTransientRange transferWithTransientRelation = transferTransientDao.create(
                CollectionRelationTransferWithTransientRangeForCreate.builder().withItems(
                        aItemsA1toA3
                ).build()
        );

        // Any Transient Range
        List<ItemTransfer> rangeOfTransientItemWithAnyRange = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);

        HashSet<String> itemNames = new HashSet<>(nameOfAItems);
        itemNames.addAll(nameOfBItems);
        assertThat(
                rangeOfTransientItemWithAnyRange.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(itemNames)
        );

        // check none of the range element is selected
        assertTrue(rangeOfTransientItemWithAnyRange.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferWithTransientRelation.addToTransientItemWithAnyRange(
                aItemsA1toA3.get(0),
                aItemsA1toA3.get(1),
                aItemsA1toA3.get(2)
        );

        transferWithTransientRelation = transferTransientDao.update(transferWithTransientRelation);

        // check selected
        rangeOfTransientItemWithAnyRange = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);
        // check none of the range element is selected
        assertEquals(0, getSelectedItems(rangeOfTransientItemWithAnyRange).size());
        // Derived Transient Range
        List<ItemTransfer> rangeOfTransientItemWithDerivedRangeItem = transferTransientDao.getRangeOfTransientItemWithDerivedRange(transferWithTransientRelation);

        assertTrue(rangeOfTransientItemWithDerivedRangeItem.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(nameOfBItems::contains)
        );

        assertThat(
                rangeOfTransientItemWithDerivedRangeItem.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(new HashSet<>(nameOfAItems))
        );

        // check none of the range element is selected
        assertTrue(rangeOfTransientItemWithDerivedRangeItem.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferWithTransientRelation.addToTransientItemWithAnyRange(
                aItemsA1toA3.get(0),
                aItemsA1toA3.get(1),
                aItemsA1toA3.get(2)
        );

        transferWithTransientRelation = transferTransientDao.update(transferWithTransientRelation);

        // check selected
        rangeOfTransientItemWithDerivedRangeItem = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);
        // check none of the range element is selected
        assertEquals(0, getSelectedItems(rangeOfTransientItemWithDerivedRangeItem).size());
        // Self Range for transient relation
        List<ItemTransfer> rangeOfTransientItemWithSelfRange = transferTransientDao.getRangeOfTransientItemWithSelfRange(transferWithTransientRelation);

        List<String> notItemElements = itemNames.stream().filter(n -> !n.matches("A1|A2|A3")).toList();
        assertTrue(rangeOfTransientItemWithSelfRange.stream()
                .map(ItemTransfer::getName)
                .map(Optional::orElseThrow)
                .noneMatch(notItemElements::contains)
        );

        assertThat(
                rangeOfTransientItemWithSelfRange.stream()
                        .map(itemTransfer -> itemTransfer.getName().orElseThrow())
                        .collect(Collectors.toSet()),
                equalTo(nameOfAItems.stream()
                        .filter(n -> n.matches("A1|A2|A3"))
                        .collect(Collectors.toSet()))
        );

        // check none of the range element is selected
        assertTrue(rangeOfTransientItemWithSelfRange.stream()
                .map(ItemTransfer::toMap)
                .noneMatch(m -> m.containsKey("__selected"))
        );

        transferWithTransientRelation.addToTransientItemWithAnyRange(
                aItemsA1toA3.get(0),
                aItemsA1toA3.get(1),
                aItemsA1toA3.get(2)
        );

        transferWithTransientRelation = transferTransientDao.update(transferWithTransientRelation);
        // check selected
        rangeOfTransientItemWithSelfRange = transferTransientDao.getRangeOfTransientItemWithAnyRange(transferWithTransientRelation);
        // check none of the range element is selected
        assertEquals(0, getSelectedItems(rangeOfTransientItemWithSelfRange).size());

    }

    // Negative Collection Relation RangeTests

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-6034")
    public void testCollectionAssRelationTransferRangeValidations(JudoRuntimeFixture fixture) {
        CollectionAssRelationTransferWithDerivedRangeDao transferAssDerivedDao = collectionAssRelationTransferWithDerivedRangeDao;
        CollectionAssRelationTransferWithSelfRangeDao transferAssSelfDao = collectionAssRelationTransferWithSelfRangeDao;


        List<ItemTransfer> aItems = createItemListUpTo("A", 1, 10);
        List<ItemTransfer> bItems = createItemListUpTo("B", 1, 10);

        List<String> nameOfAItems = aItems.stream().map(i -> i.getName().orElseThrow()).toList();
        List<String> nameOfBItems = bItems.stream().map(i -> i.getName().orElseThrow()).toList();


        // derived range
        CollectionAssRelationTransferWithDerivedRange transferAssDerived = transferAssDerivedDao.create(
                CollectionAssRelationTransferWithDerivedRangeForCreate.builder().build()
        );

        // try to set an element out of range
        // no range validation here
        transferAssDerivedDao.addCollectionAssAssItem(transferAssDerived, bItems.get(0));

        // self range
        CollectionAssRelationTransferWithSelfRange transferAssSelf = transferAssSelfDao.create(
                CollectionAssRelationTransferWithSelfRangeForCreate.builder().withItems(
                        aItems.stream()
                                .filter(i -> i.getName().orElseThrow().matches("A1|A2|A3"))
                                .toList()
                ).build()
        );

        // try to set an element out of range
        // no range validation here
        // TODO
        transferAssSelfDao.addCollectionAssAssItem(transferAssSelf, bItems.get(0));


        transferAssSelfDao.createCollectionAssAssItem(
                transferAssSelf,
                ItemTransferForCreate.builder().withName("B2").build()
        );

    }


    public boolean hasMethodWithName(String methodName,Object object) {
        return Arrays.stream(object.getClass().getDeclaredMethods())
                .anyMatch(f -> f.getName().contains(methodName));
    }

    public List<ItemTransfer> createItemListUpTo(String name, int startNumber, int endNumber) {
        return IntStream.rangeClosed(startNumber, endNumber)
                .mapToObj(number ->  itemTransferDao.create(ItemTransferForCreate.builder().withName(name + number).build()))
                .collect(Collectors.toList());
    }

    private static @NotNull List<ItemTransfer> getSelectedItems(List<ItemTransfer> rangeOfItem) {
        return rangeOfItem.stream()
                .map(ItemTransfer::toMap)
                .filter(m -> m.containsKey("__selected"))
                .map(ItemTransfer::from)
                .toList();
    }

    // Tests from ESM

    @Test
    @TestCase("Range")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-012",
            "REQ-SRV-002"
    })
    public void testRangeOnFruit() {
        Apple galaApple =  appleDao.create(AppleForCreate.builder().withVariety("GALA").build());
        Apple pinkLadyApple =  appleDao.create(AppleForCreate.builder().withVariety("PINK_LADY").build());
        Pear williamsPear =  pearDao.create(PearForCreate.builder().withVariety("WILLIAMS").build());

        TransferBasket transferBasket = transferBasketDao.create(TransferBasketForCreate.builder().build());

        assertThat(
                transferBasketDao.getRangeOfBreakfast(transferBasket).stream().map(a -> a.identifier().getIdentifier()).collect(Collectors.toSet()),
                equalTo(Set.of(galaApple.identifier().getIdentifier(), pinkLadyApple.identifier().getIdentifier()))
        );

        assertThat(
                transferBasketDao.getRangeOfGalaFruit(transferBasket).stream().map(a -> a.identifier().getIdentifier()).collect(Collectors.toSet()),
                equalTo(Set.of(galaApple.identifier().getIdentifier()))
        );

        // transient range
        assertThat(
                transferBasketDao.getRangeOfFruits(transferBasket).stream().map(a -> a.identifier().getIdentifier()).collect(Collectors.toSet()),
                equalTo(Set.of(williamsPear.identifier().getIdentifier()))
        );

        // set relation in the range
        transferBasket.setBreakfast(galaApple.adaptTo(TransferFruit.class));
        transferBasket = transferBasketDao.update(transferBasket);
        assertEquals(galaApple.identifier().getIdentifier(), transferBasket.getBreakfast().orElseThrow().identifier().getIdentifier());
        transferBasket.setBreakfast(pinkLadyApple.adaptTo(TransferFruit.class));
        transferBasket = transferBasketDao.update(transferBasket);
        assertEquals(pinkLadyApple.identifier().getIdentifier(), transferBasket.getBreakfast().orElseThrow().identifier().getIdentifier());

        transferBasket.setBreakfast(williamsPear.adaptTo(TransferFruit.class));
        TransferBasket referenceBasket = transferBasket;
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferBasketDao.update(referenceBasket)
        );
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", Matchers.equalTo("NOT_ACCEPTED_BY_RANGE")),
                hasProperty("location", Matchers.equalTo("breakfast")))
        ));


    }

    @Test
    void testDeletedRangeReferenceOnFruitsModel(JudoRuntimeFixture fixture) {
        Apple galaApple =  appleDao.create(AppleForCreate.builder().withVariety("GALA").build());

        appleDao.delete(galaApple.identifier());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferBasketDao.create(TransferBasketForCreate.builder().withBreakfast(galaApple.adaptTo(TransferFruitForCreate.class)).build())
        );
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", Matchers.equalTo("NOT_ACCEPTED_BY_RANGE")),
                hasProperty("location", Matchers.equalTo("breakfast")))
        ));
    }

    @Inject
    TransferCarDao transferCarDao;

    @Inject
    TransferWheelDao transferWheelDao;

    @Test
    @TestCase("RangeOfContainment")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-SRV-002"
    })
    void testRangeOfContainmentOnCarModel() {

        TransferCar car1 = transferCarDao.create(TransferCarForCreate.builder()
                .withLicensePlate("ABC123")
                .withColor("red")
                .withWheels(List.of(
                                TransferWheel.builder().withProduced(2018).build(),
                                TransferWheel.builder().withProduced(2019).build(),
                                TransferWheel.builder().withProduced(2020).build(),
                                TransferWheel.builder().withProduced(2021).build(),
                                TransferWheel.builder().withProduced(2015).build()
                        )
                )
                .build()
        );

        assertThat(transferCarDao.getRangeOfSpareWheel(car1).stream().map(TransferWheel::getProduced).collect(Collectors.toSet()), equalTo(ImmutableSet.of(2018, 2019, 2020, 2021, 2015)));

        TransferCarForCreate car2 = TransferCarForCreate.builder()
                .withColor("black")
                .withWheels(List.of(
                                TransferWheel.builder().withProduced(2005).build(),
                                TransferWheel.builder().withProduced(2006).build(),
                                TransferWheel.builder().withProduced(2007).build(),
                                TransferWheel.builder().withProduced(2008).build(),
                                TransferWheel.builder().withProduced(1995).build()
                        )
                )
                .build();

        // TODO JNG-5603 getRangeOfSpareWheel with TransferCarForCreate parameter and stateful is true
//        assertThat(transferCarDao.getRangeOfSpareWheel(car2.adaptTo(TransferCar.class)).stream().map(TransferWheel::getProduced).collect(Collectors.toSet()), equalTo(ImmutableSet.of(2005, 2006, 2007, 2008, 1995)));

    }

    @Inject
    TransferPlanetDao transferPlanetDao;

    @Inject
    TransferCreatureDao transferCreatureDao;

    @Test
    @TestCase("RangeOfContainment")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-SRV-002"
    })
    void testRangeOnTwoWayRelationExpression() {

        TransferPlanet venus = transferPlanetDao.create(TransferPlanetForCreate.builder().withName("Venus").build());
        TransferCreature creature = transferCreatureDao.create(TransferCreatureForCreate.builder().withName("Alien").withPlanet(venus).build());
        TransferCreature creatureWithOutPlanet = transferCreatureDao.create(TransferCreatureForCreate.builder().withName("Visitor").build());

        List<TransferCreature> availableCreatures = transferPlanetDao.queryAvailableVisitors(venus).selectList();
        List<TransferCreature> selectableCreatures = transferPlanetDao.getRangeOfVisitors(venus);

        assertThat(availableCreatures.stream().map(TransferCreature::getName).collect(Collectors.toSet()), equalTo(ImmutableSet.of("Alien")));
        assertThat(selectableCreatures.stream().map(TransferCreature::getName).collect(Collectors.toSet()), equalTo(ImmutableSet.of("Visitor")));

    }


}
