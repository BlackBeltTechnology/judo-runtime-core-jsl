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
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.guice.operators.OperatorsDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.sdk.operators.operators.DefaultOperators;
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.sdk.operators.operators.DerivedOperators;
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.sdk.operators.operators.DerivedSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class OperatorsTest extends AbstractJslTest {
    @Inject
    DefaultOperators.DefaultOperatorsDao defaultOperatorsDao;

    @Inject
    DerivedOperators.DerivedOperatorsDao derivedOperatorsDao;

    @Override
    public Module getModelDaoModule() {
        return new OperatorsDaoModules();
    }

    @Override
    public String getModelName() {
        return "Operators";
    }

    @Test
    void testOperatorsForDefaultValues() {
        DefaultOperators operators = defaultOperatorsDao.create(DefaultOperators.builder().build());

        assertEquals(Optional.of(1), operators.getRounded());
        assertEquals(Optional.of(7), operators.getAddition());
        assertEquals(Optional.of(3), operators.getSubtraction());
        assertEquals(Optional.of(25), operators.getPow());
        assertEquals(Optional.of(10), operators.getMultiplication());
        assertEquals(Optional.of(Float.valueOf("2.5")), operators.getDivision());
        assertEquals(Optional.of(2), operators.getDivisionWhole());
        assertEquals(Optional.of(1), operators.getModulo());
        assertEquals(Optional.of(false), operators.getLt());
        assertEquals(Optional.of(true), operators.getLt2());
        assertEquals(Optional.of(false), operators.getGt());
        assertEquals(Optional.of(true), operators.getGt2());
        assertEquals(Optional.of(true), operators.getLte());
        assertEquals(Optional.of(true), operators.getLte2());
        assertEquals(Optional.of(true), operators.getGte());
        assertEquals(Optional.of(true), operators.getGte2());
        assertEquals(Optional.of(false), operators.getEq());
        assertEquals(Optional.of(true), operators.getNeq());
        assertEquals(Optional.of(1), operators.getConditional());
        assertEquals(Optional.of(false), operators.getGroupAnd());
        assertEquals(Optional.of(true), operators.getGroupOr());
    }

    @Test
    void testOperatorsForDerivedFields() {
        DerivedOperators operators = derivedOperatorsDao.create(DerivedOperators.builder()
                .withSource(DerivedSource.builder().build())
                .build());

        assertEquals(Optional.of("John Pro"), operators.getStringConcat());
        assertEquals(Optional.of(4), operators.getFirstNameLength());  // FIXME: JNG-4080
        assertEquals(Optional.of(99), operators.getRounded());
        assertEquals(Optional.of(37), operators.getAddition());
        assertEquals(Optional.of(33), operators.getSubtraction());
        assertEquals(Optional.of(70), operators.getMultiplication());
        assertEquals(Optional.of(17), operators.getDivision());
        assertEquals(Optional.of(17), operators.getDivisionWhole());
        assertEquals(Optional.of(1), operators.getModulo());
        assertEquals(Optional.of(false), operators.getLt());
        assertEquals(Optional.of(true), operators.getLt2());
        assertEquals(Optional.of(true), operators.getGt());
        assertEquals(Optional.of(true), operators.getGt2());
        assertEquals(Optional.of(false), operators.getLte());
        assertEquals(Optional.of(true), operators.getLte2());
        assertEquals(Optional.of(true), operators.getGte());
        assertEquals(Optional.of(true), operators.getGte2());
        assertEquals(Optional.of(false), operators.getEq());
        assertEquals(Optional.of(true), operators.getNeq());
        assertEquals(Optional.of(1), operators.getConditional());
    }
}
