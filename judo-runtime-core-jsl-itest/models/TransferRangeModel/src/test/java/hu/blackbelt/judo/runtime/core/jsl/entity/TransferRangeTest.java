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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferfruit.TransferFruit;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferrange.transferrange.transferfruit.TransferFruitForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferRangeDaoModules;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

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

        // out of the range
        // TODO not validate the range call in reference set
        transferBasketDao.setBreakfast(transferBasket, williamsPear.adaptTo(TransferFruit.class));
        transferBasket = transferBasketDao.getById(transferBasket.identifier()).orElseThrow();
        // Should be false
        assertEquals(williamsPear.identifier().getIdentifier(), transferBasket.getBreakfast().orElseThrow().identifier().getIdentifier());

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

}
