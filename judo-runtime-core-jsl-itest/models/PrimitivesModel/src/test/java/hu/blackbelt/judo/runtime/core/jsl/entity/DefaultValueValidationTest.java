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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidintegerdefaultvalue.EntityWithNotValidIntegerDefaultValueDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidintegerdefaultvalue.EntityWithNotValidIntegerDefaultValueForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidprecisiondefaultvalue.EntityWithNotValidPrecisionDefaultValueDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidprecisiondefaultvalue.EntityWithNotValidPrecisionDefaultValueForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidregexdefaultvalue.EntityWithNotValidRegexDefaultValueDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidregexdefaultvalue.EntityWithNotValidRegexDefaultValueForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidscaledefaultvalue.EntityWithNotValidScaleDefaultValueDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidscaledefaultvalue.EntityWithNotValidScaleDefaultValueForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidstringdefaultvalue.EntityWithNotValidStringDefaultValueDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithnotvalidstringdefaultvalue.EntityWithNotValidStringDefaultValueForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.mappedentitywithnotvalidregexdefaultvalue.MappedEntityWithNotValidRegexDefaultValueDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.mappedentitywithnotvalidregexdefaultvalue.MappedEntityWithNotValidRegexDefaultValueForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class DefaultValueValidationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Primitives", new PrimitivesDaoModules());

    @Inject
    EntityWithNotValidIntegerDefaultValueDao entityWithNotValidIntegerDefaultValueDao;

    @Inject
    EntityWithNotValidPrecisionDefaultValueDao entityWithNotValidPrecisionDefaultValueDao;

    @Inject
    EntityWithNotValidScaleDefaultValueDao entityWithNotValidScaleDefaultValueDao;

    @Inject
    EntityWithNotValidStringDefaultValueDao entityWithNotValidStringDefaultValueDao;

    @Inject
    EntityWithNotValidRegexDefaultValueDao entityWithNotValidRegexDefaultValueDao;

    @Inject
    MappedEntityWithNotValidRegexDefaultValueDao mappedEntityWithNotValidRegexDefaultValueDao;

    /**
     * This case checks the invalid default value behaviors, when try to create an Entity instance of them.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     * @type Behaviour
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     * @jslModel Primitives.jsl
     * @negativeRequirements
     * @scenario Create instances for each invalid default value Entity
     * Check the creation is good
     * Check the update is failed with validation error.
     */
    @Test
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
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityWithNotValidIntegerDefaultValueDao.create(EntityWithNotValidIntegerDefaultValueForCreate.create())
        );
        assertValidation(thrown, "PRECISION_VALIDATION_FAILED", "integerAttr");

        ValidationException thrown1 = assertThrows(
                ValidationException.class,
                () -> entityWithNotValidPrecisionDefaultValueDao.create(EntityWithNotValidPrecisionDefaultValueForCreate.create())
        );
        assertValidation(thrown1, "PRECISION_VALIDATION_FAILED", "scaledAttrWithWrongPrecision");

        ValidationException thrown2 = assertThrows(
                ValidationException.class,
                () -> entityWithNotValidScaleDefaultValueDao.create(EntityWithNotValidScaleDefaultValueForCreate.create())
        );
        assertValidation(thrown2, "SCALE_VALIDATION_FAILED", "scaledAttrWithWrongScale");

        ValidationException thrown3 = assertThrows(
                ValidationException.class,
                () -> entityWithNotValidRegexDefaultValueDao.create(EntityWithNotValidRegexDefaultValueForCreate.create())
        );
        assertValidation(thrown3, "PATTERN_VALIDATION_FAILED", "regexAttrWrongPattern");

        ValidationException thrown4 = assertThrows(
                ValidationException.class,
                () -> mappedEntityWithNotValidRegexDefaultValueDao.create(MappedEntityWithNotValidRegexDefaultValueForCreate.create())
        );
        assertValidation(thrown4, "PATTERN_VALIDATION_FAILED", "regexAttrWrongPattern");

        ValidationException thrown5 = assertThrows(
                ValidationException.class,
                () -> entityWithNotValidStringDefaultValueDao.create(EntityWithNotValidStringDefaultValueForCreate.create())
        );
        assertValidation(thrown5, "MAX_LENGTH_VALIDATION_FAILED", "stringAttr");

    }

    private static void assertValidation(ValidationException thrown, String code, String location) {
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo(code)),
                hasProperty("location", equalTo(location)))
        ));
    }

}
