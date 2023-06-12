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
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RequiredWithDefaultModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class RequiredWithDefaultTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new RequiredWithDefaultModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "RequiredWithDefaultModel";
    }

    @Test
    public void testOptionalMapping() {
    }

    @Test
    public void testOptionalWithDefaultMapping() {
    }

    @Test
    public void testRequiredMapping() {
    }

    @Test
    public void testRequiredWithDefaultMapping() {
    }

}
