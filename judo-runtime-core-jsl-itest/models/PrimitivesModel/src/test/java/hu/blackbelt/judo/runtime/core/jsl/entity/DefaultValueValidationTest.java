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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidintegerdefaultvalue.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidprecisiondefaultvalue.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidregexdefaultvalue.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidscaledefaultvalue.*;
//import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidstringdefaultvalue.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslWithOneInjectionTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class DefaultValueValidationTest extends AbstractJslWithOneInjectionTest {
    @Inject
    EntityWithNotValidIntegerDefaultValueDao entityWithNotValidIntegerDefaultValueDao;

    @Inject
    EntityWithNotValidPrecisionDefaultValueDao entityWithNotValidPrecisionDefaultValueDao;

    @Inject
    EntityWithNotValidScaleDefaultValueDao entityWithNotValidScaleDefaultValueDao;

    // TODO JNG-JNG-4899 The injection is failed in this dao
//    @Inject
//    EntityWithNotValidStringDefaultValueDao entityWithNotValidStringDefaultValueDao;

    @Inject
    EntityWithNotValidRegexDefaultValueDao entityWithNotValidRegexDefaultValueDao;

    // TODO JNG-JNG-4899 The injection is failed in this module
    @Override
    public Module getModelDaoModule() {
        return new PrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "Primitives";
    }


    /**
     * This case checks the invalid default value behaviors, when try to create an Entity instance of them.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel Primitives.jsl
     *
     *
     * @negativeRequirements
     *
     * @scenario
     *
     * Create instances for each invalid default value Entity
     *
     * Check the creation is good
     *
     * Check the update is failed with validation error.
     *
     */
    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4899")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-EXPR-001"
    })
    public void testNotValidDefaultValueValidationTest() {


        DataIntegrityViolationException thrown = assertThrows(
                DataIntegrityViolationException.class,
                () -> entityWithNotValidIntegerDefaultValueDao.create(EntityWithNotValidIntegerDefaultValue.builder().build())
        );
        assertTrue(thrown.getMessage().contains("numeric value out of range"));

        EntityWithNotValidPrecisionDefaultValue entityWithNotValidPrecisionDefaultValue = entityWithNotValidPrecisionDefaultValueDao.create(EntityWithNotValidPrecisionDefaultValue.builder().build());

        ValidationException thrown1 = assertThrows(
                ValidationException.class,
                () -> entityWithNotValidPrecisionDefaultValueDao.update(entityWithNotValidPrecisionDefaultValue)
        );

        assertThat(thrown1.getValidationResults(), containsInAnyOrder(allOf(
                        hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                        hasProperty("location", equalTo("scaledAttrWithWrongPrecision")))
        ));

        EntityWithNotValidScaleDefaultValue entityWithNotValidScaleDefaultValue = entityWithNotValidScaleDefaultValueDao.create(EntityWithNotValidScaleDefaultValue.builder().build());
        //We are round the too much scale
        assertEquals(Optional.of(2.35),entityWithNotValidScaleDefaultValue.getScaledAttrWithWrongScale() );
        entityWithNotValidScaleDefaultValue = entityWithNotValidScaleDefaultValueDao.update(entityWithNotValidScaleDefaultValue);
        assertEquals(Optional.of(2.35),entityWithNotValidScaleDefaultValue.getScaledAttrWithWrongScale() );

        EntityWithNotValidRegexDefaultValue entityWithNotValidRegexDefaultValue = entityWithNotValidRegexDefaultValueDao.create(EntityWithNotValidRegexDefaultValue.builder().build());

        ValidationException thrown2 = assertThrows(
                ValidationException.class,
                () ->  entityWithNotValidRegexDefaultValueDao.update(entityWithNotValidRegexDefaultValue)
        );

        assertThat(thrown2.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PATTERN_VALIDATION_FAILED")),
                hasProperty("location", equalTo("regexAttrWrongPattern")))
        ));

    }

}
