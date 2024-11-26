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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.fruit.FruitDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.item.ItemForCreate;
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithdefaultrange.SingleAssRelationTransferWithDefaultRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithdefaultrange.SingleAssRelationTransferWithDefaultRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithdefaultrange.SingleAssRelationTransferWithDefaultRangeForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithderivedrange.SingleAssRelationTransferWithDerivedRange;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithderivedrange.SingleAssRelationTransferWithDerivedRangeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.singleassrelationtransferwithderivedrange.SingleAssRelationTransferWithDerivedRangeForCreate;
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
import liquibase.pro.packaged.S;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.analysis.function.Sin;
import org.hamcrest.Matchers;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.shaded.com.google.common.collect.ImmutableSet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
