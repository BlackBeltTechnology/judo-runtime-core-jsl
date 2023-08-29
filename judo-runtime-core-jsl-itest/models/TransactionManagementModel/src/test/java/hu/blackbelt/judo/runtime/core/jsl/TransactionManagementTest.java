package hu.blackbelt.judo.runtime.core.jsl;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transactionmanagementmodel.transactionmanagementmodel.tester.Tester;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transactionmanagementmodel.transactionmanagementmodel.tester.TesterDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransactionManagementModelDaoModules;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith({JudoDatasourceByClassExtension.class, JudoRuntimeExtension.class})
public class TransactionManagementTest {

    @Inject TesterDao testerDao;

    public Module getModelDaoModule() {
        return new TransactionManagementModelDaoModules();
    }

    static public String getModelName() {
        return "TransactionManagementModel";
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
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    void testManualTransactionManagementCommitAndRollback(JudoRuntimeFixture fixture) {
        // beginTransaction(); in BeforeEach

        Tester tester = testerDao.create(Tester.builder().withName("TEST-A").build());

        fixture.commitTransaction();
        fixture.beginTransaction();

        assertEquals("TEST-A", testerDao.getById(tester.identifier()).orElseThrow().getName());

        tester.setName("BLAAA");
        testerDao.update(tester);

        fixture.rollbackTransaction();
        fixture.beginTransaction();

        assertEquals("TEST-A", testerDao.getById(tester.identifier()).orElseThrow().getName());

        // rollbackTransaction(); in AfterEach
    }

}
