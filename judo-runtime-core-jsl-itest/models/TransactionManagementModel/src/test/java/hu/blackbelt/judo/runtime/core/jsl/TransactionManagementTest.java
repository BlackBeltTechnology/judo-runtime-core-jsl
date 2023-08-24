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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TransactionManagementTest extends AbstractJslWithOneInjectionTest {

    @Inject TesterDao testerDao;

    @Override
    public Module getModelDaoModule() {
        return new TransactionManagementModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "TransactionManagementModel";
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    void testManualTransactionManagementCommitAndRollback() {
        // beginTransaction(); in BeforeEach

        Tester tester = testerDao.create(Tester.builder().withName("TEST-A").build());

        commitTransaction();
        beginTransaction();

        assertEquals("TEST-A", testerDao.getById(tester.identifier()).orElseThrow().getName());

        tester.setName("BLAAA");
        testerDao.update(tester);

        rollbackTransaction();
        beginTransaction();

        assertEquals("TEST-A", testerDao.getById(tester.identifier()).orElseThrow().getName());

        // rollbackTransaction(); in AfterEach
    }

}
