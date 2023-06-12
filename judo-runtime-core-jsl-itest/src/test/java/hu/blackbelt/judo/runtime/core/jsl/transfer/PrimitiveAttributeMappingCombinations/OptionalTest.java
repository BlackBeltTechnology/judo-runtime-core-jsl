package hu.blackbelt.judo.runtime.core.jsl.transfer.PrimitiveAttributeMappingCombinations;

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

import com.google.inject.Module;
import com.google.inject.*;
import hu.blackbelt.judo.dao.api.ValidationResult;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optionalmodel.optionalmodel.optional.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optionalmodel.optionalmodel.optionalmappingoptional.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optionalmodel.optionalmodel.optionalwithdefaultmappingoptional.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optionalmodel.optionalmodel.requiredmappingoptional.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optionalmodel.optionalmodel.requiredwithdefaultmappingoptional.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.OptionalModelDaoModules;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class OptionalTest extends AbstractJslTest {

    @Inject OptionalDao optionalDao;
    @Inject OptionalMappingOptionalDao optionalMappingOptionalDao;
    @Inject OptionalWithDefaultMappingOptionalDao optionalWithDefaultMappingOptionalDao;
    @Inject RequiredMappingOptionalDao requiredMappingOptionalDao;
    @Inject RequiredWithDefaultMappingOptionalDao requiredWithDefaultMappingOptionalDao;

    @Override
    public Module getModelDaoModule() {
        return new OptionalModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "OptionalModel";
    }

    @AfterEach
    public void teardown() {
        for (Optional optional : optionalDao.query().execute()) {
            optionalDao.delete(optional);
        }
    }

    @Test
    public void testOptionalMapping() {
        OptionalMappingOptional optionalMappingOptional = optionalMappingOptionalDao.create(OptionalMappingOptional.create());
        assertTrue(optionalMappingOptional.getAttr().isEmpty());
        assertEquals(1, optionalDao.countAll());
        assertTrue(optionalDao.query().execute().get(0).getAttr().isEmpty());

        optionalMappingOptional = optionalMappingOptionalDao.create(OptionalMappingOptional.builder().withAttr("initial").build());
        assertEquals("initial", optionalMappingOptional.getAttr().orElseThrow());
        assertEquals(2, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        optionalMappingOptional.setAttr("updated");
        optionalMappingOptional = optionalMappingOptionalDao.update(optionalMappingOptional);
        assertEquals("updated", optionalMappingOptional.getAttr().orElseThrow());
        assertEquals(2, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
        assertEquals(0, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        optionalMappingOptional.setAttr(null);
        optionalMappingOptional = optionalMappingOptionalDao.update(optionalMappingOptional);
        assertTrue(optionalMappingOptional.getAttr().isEmpty());
        assertEquals(2, optionalDao.query().count());
        assertEquals(0, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
    }

    @Test
    public void testOptionalWithDefaultMapping() {
        OptionalWithDefaultMappingOptional optionalWithDefaultMappingOptional =
                optionalWithDefaultMappingOptionalDao.create(OptionalWithDefaultMappingOptional.create());
        assertEquals("OptionalWithDefaultMappingOptional", optionalWithDefaultMappingOptional.getAttr().orElseThrow());
        assertEquals(1, optionalWithDefaultMappingOptionalDao.countAll());
        assertEquals("OptionalWithDefaultMappingOptional", optionalWithDefaultMappingOptionalDao.query().execute().get(0).getAttr().orElseThrow());

        optionalWithDefaultMappingOptional =
                optionalWithDefaultMappingOptionalDao.create(OptionalWithDefaultMappingOptional.builder().withAttr("initial").build());
        assertEquals("initial", optionalWithDefaultMappingOptional.getAttr().orElseThrow());
        assertEquals(2, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        optionalWithDefaultMappingOptional.setAttr("updated");
        optionalWithDefaultMappingOptional = optionalWithDefaultMappingOptionalDao.update(optionalWithDefaultMappingOptional);
        assertEquals("updated", optionalWithDefaultMappingOptional.getAttr().orElseThrow());
        assertEquals(2, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
        assertEquals(0, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        optionalWithDefaultMappingOptional.setAttr(null);
        optionalWithDefaultMappingOptional = optionalWithDefaultMappingOptionalDao.update(optionalWithDefaultMappingOptional);
        assertTrue(optionalWithDefaultMappingOptional.getAttr().isEmpty());
        assertEquals(2, optionalDao.query().count());
        assertEquals(0, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
    }

    @Test
    public void testRequiredMapping() {
        ValidationException exception = assertThrows(ValidationException.class, () -> requiredMappingOptionalDao.create(RequiredMappingOptional.create()));
        assertEquals(1, exception.getValidationResults().size());
        ValidationResult validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("attr", validationResult.getLocation());

        RequiredMappingOptional requiredMappingOptional = requiredMappingOptionalDao.create(RequiredMappingOptional.builder().withAttr("initial").build());
        assertEquals("initial", requiredMappingOptional.getAttr());
        assertEquals(1, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        requiredMappingOptional.setAttr("updated");
        requiredMappingOptional = requiredMappingOptionalDao.update(requiredMappingOptional);
        assertEquals("updated", requiredMappingOptional.getAttr());
        assertEquals(1, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
        assertEquals(0, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        requiredMappingOptional.setAttr(null);
        final RequiredMappingOptional finalRequiredMappingOptional = requiredMappingOptional;
        ValidationException exception1 = assertThrows(ValidationException.class, () -> requiredMappingOptionalDao.update(finalRequiredMappingOptional));
        assertEquals(1, exception1.getValidationResults().size());
        ValidationResult validationResult1 = exception1.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult1.getCode());
        assertEquals("attr", validationResult1.getLocation());
        assertEquals(1, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
    }

    @Test
    public void testRequiredWithDefaultMapping() {
        RequiredWithDefaultMappingOptional requiredWithDefaultMappingOptional =
                requiredWithDefaultMappingOptionalDao.create(RequiredWithDefaultMappingOptional.create());
        assertEquals("RequiredWithDefaultMappingOptional", requiredWithDefaultMappingOptional.getAttr());
        assertEquals(1, requiredWithDefaultMappingOptionalDao.countAll());
        assertEquals("RequiredWithDefaultMappingOptional", requiredWithDefaultMappingOptionalDao.query().execute().get(0).getAttr());

        requiredWithDefaultMappingOptional =
                requiredWithDefaultMappingOptionalDao.create(RequiredWithDefaultMappingOptional.builder().withAttr("initial").build());
        assertEquals("initial", requiredWithDefaultMappingOptional.getAttr());
        assertEquals(2, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        requiredWithDefaultMappingOptional.setAttr("updated");
        requiredWithDefaultMappingOptional = requiredWithDefaultMappingOptionalDao.update(requiredWithDefaultMappingOptional);
        assertEquals("updated", requiredWithDefaultMappingOptional.getAttr());
        assertEquals(2, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
        assertEquals(0, optionalDao.query().filterByAttr(StringFilter.equalTo("initial")).count());

        requiredWithDefaultMappingOptional.setAttr(null);
        final RequiredWithDefaultMappingOptional finalRequiredWithDefaultMappingOptional = requiredWithDefaultMappingOptional;
        ValidationException exception1 =
                assertThrows(ValidationException.class, () -> requiredWithDefaultMappingOptionalDao.update(finalRequiredWithDefaultMappingOptional));
        assertEquals(1, exception1.getValidationResults().size());
        ValidationResult validationResult1 = exception1.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult1.getCode());
        assertEquals("attr", validationResult1.getLocation());
        assertEquals(2, optionalDao.query().count());
        assertEquals(1, optionalDao.query().filterByAttr(StringFilter.equalTo("updated")).count());
    }

}
