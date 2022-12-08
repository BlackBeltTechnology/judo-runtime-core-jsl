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
import hu.blackbelt.judo.runtime.core.jsl.itest.ternarytest.guice.ternarytest.TernaryTestDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.ternarytest.sdk.ternarytest.ternarytest.AAA;
import hu.blackbelt.judo.test.Requirement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TernaryTest extends AbstractJslTest {

    @Inject AAA.AAADao aDao;

    @Override
    public Module getModelDaoModule() {
        return new TernaryTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "TernaryTest";
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-004",
            "REQ-SYNT-005"
    })
    public void testTernaries() {
        AAA a = aDao.create(AAA.builder().build());

        assertEquals("true", a.getTs().orElseThrow());
        assertEquals("aaa", a.getTs1().orElseThrow());
        assertEquals("true  ", a.getTs2().orElseThrow());
        assertEquals("aaa", a.getTs3().orElseThrow());
        assertEquals("false", a.getTs4().orElseThrow());
        assertEquals("false", a.getTs5().orElseThrow());
        assertEquals("!!false!!", a.getTs6().orElseThrow());
        assertEquals("  aaa", a.getTs7().orElseThrow());

        // TODO: JNG-3839
//        Optional<BBB> t2 = aDao.getT2(a);
//        assertTrue(t2.isEmpty());
//        List<BBB> t3 = aDao.getT3(a);
//        assertTrue(t3.isEmpty());
    }

}
