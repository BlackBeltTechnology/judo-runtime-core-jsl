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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.pear.Pear;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.pear.PearDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.pear.PearForCreate;
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
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.shaded.com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class TransferRangeTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TransferRange", new TransferRangeDaoModules());

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
    public void testRange() {
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
    void testDeletedRangeReference() {
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
    void testRangeOfContainment() {

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
        //assertThat(transferCarDao.getRangeOfSpareWheel(TransferCarForCreate).stream().map(TransferWheel::getProduced).collect(Collectors.toSet()), equalTo(ImmutableSet.of(2005, 2006, 2007, 2008, 1995)));

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
