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
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.a.AAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ContainsModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith({JudoDatasourceByClassExtension.class, JudoRuntimeExtension.class})
public class ContainsTest {

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    public Module getModelDaoModule() {
        return new ContainsModelDaoModules();
    }

    static public String getModelName() {
        return "ContainsModel";
    }



    @BeforeAll
    static public void prepare(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.prepare(getModelName(), datasource);
    }

    @BeforeEach
    protected void init(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.init(getModelDaoModule(),this, datasource);
        fixture.beginTransaction();
    }

    @AfterEach
    protected void tearDown(JudoRuntimeFixture fixture) {
        fixture.tearDown();
    }


    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-008",
            "REQ-EXPR-004",
            "REQ-EXPR-022",
    })
    public void testContains() {
        A a = aDao.create(A.builder().build());
        assertFalse(a.getContainsTest().orElseThrow());
        assertFalse(a.getContainsTest1().orElseThrow());

        B b = bDao.create(B.builder().withName("test").build());
        B b1 = bDao.create(B.builder().withName("not test").build());
        A a1 = aDao.create(A.builder()
                            .build(), AAttachedRelationsForCreate.builder()
                            .withB(b)
                            .withBs(List.of(b, b1))
                            .build());
        assertTrue(a1.getContainsTest().orElseThrow());
        assertTrue(a1.getContainsTest1().orElseThrow());

    }

}
