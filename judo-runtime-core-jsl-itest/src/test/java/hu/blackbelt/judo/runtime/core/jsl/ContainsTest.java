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
import hu.blackbelt.judo.test.Requirement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
    @Requirement(reqs = {
            "REQ-EXPR-022"
    })
    public void testContains() {
        A a = aDao.create(A.builder().build());
        assertFalse(a.getContainsTest().orElseThrow());
        assertFalse(a.getContainsTest1().orElseThrow());

        B b = bDao.create(B.builder().withName("test").build());
        B b1 = bDao.create(B.builder().withName("not test").build());
        A a1 = aDao.create(A.builder()
                            .withB(b)
                            .withBs(List.of(b, b1))
                            .build());
        assertTrue(a1.getContainsTest().orElseThrow());
        assertTrue(a1.getContainsTest1().orElseThrow());
    }

}
