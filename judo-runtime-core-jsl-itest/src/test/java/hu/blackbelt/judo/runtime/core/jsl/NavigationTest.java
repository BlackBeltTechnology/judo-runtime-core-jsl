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
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.guice.navigationtest.NavigationTestDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.A;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.B;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.C;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class NavigationTest extends AbstractJslTest {
    @Inject
    A.ADao aDao;

    @Inject
    B.BDao bDao;

    @Inject
    C.CDao cDao;

    @Override
    public Module getModelDaoModule() {
        return new NavigationTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "NavigationTest";
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003"
    })
    public void test() {
        A a = aDao.create(A.builder().build());
        B b = bDao.create(B.builder().build());
        C c = cDao.create(C.builder().build());

        aDao.addBlist(a, List.of(b));
        bDao.setC(b, c);

        // Read derived list over DAO call
        List<C> cList = aDao.getClist(a);
        assertEquals(1, cList.size());

    }
}

