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
import hu.blackbelt.judo.runtime.core.jsl.itest.containsmodel.guice.containsmodel.ContainsModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.containsmodel.sdk.containsmodel.containsmodel.A;
import hu.blackbelt.judo.runtime.core.jsl.itest.containsmodel.sdk.containsmodel.containsmodel.B;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ContainsTest extends AbstractJslTest {

    @Inject
    A.ADao aDao;

    @Inject
    B.BDao bDao;

    @Override
    public Module getModelDaoModule() {
        return new ContainsModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "ContainsModel";
    }

    @Test
    @Disabled
    public void testContains() {
        B b = bDao.create(B.builder().withName("test").build());

        A a = aDao.create(A.builder()
                           .withB(b)
                           .withBs(List.of(b))
                           .build());

        assertTrue(a.getContainsTest().orElseThrow());
        assertTrue(a.getContainsTest1().orElseThrow());
    }

}
