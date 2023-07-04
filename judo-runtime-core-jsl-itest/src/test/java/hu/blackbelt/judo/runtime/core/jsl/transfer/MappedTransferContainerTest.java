package hu.blackbelt.judo.runtime.core.jsl.transfer;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.ta.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.tb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.tc.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercontainertest.mappedtransfercontainertest.td.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferContainerTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class MappedTransferContainerTest extends AbstractJslTest {

    @Inject BDao bDao;
    @Inject CDao cDao;
    @Inject DDao dDao;
    @Inject TBDao tbDao;
    @Inject TCDao tcDao;
    @Inject TDDao tdDao;
    @Override
    public Module getModelDaoModule() {
        return new MappedTransferContainerTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferContainerTest";
    }

    /**
     * The test checks the container Instance function work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferContainerTest.jsl
     *
     * @positiveRequirements REQ-EXPR-021
     *
     */
    @Test
    @TestCase("ContainerFunctionOnMappedTransfer")
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-021",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SRV-002"
    })
    public void testContainerFunctionOnMappedTransfer() {
        TB tb = tbDao.create(TB.builder()
                .withConA(TC.builder().build())
                .withDonB(TD.builder().build())
                .build());
        TC tc = tb.getConA();
        TA tcA = tcDao.queryContainerA(tc).orElseThrow();
        TB tcB = tcDao.queryContainerB(tc).orElseThrow();
        TB tcB1 = tcDao.queryContainerB1(tc).orElseThrow();
        assertEquals(tb.identifier().getIdentifier(), tcA.identifier().getIdentifier());
        assertEquals(tb.identifier().getIdentifier(), tcB.identifier().getIdentifier());
        assertEquals(tb.identifier().getIdentifier(), tcB1.identifier().getIdentifier());

        TB tb1 = tbDao.create(TB.builder()
                .withConA(TC.builder().build())
                .withDonB(TD.builder().build())
                .build());
        TD td = tb1.getDonB();
        TA tdA = tdDao.queryContainerA(td).orElseThrow();
        TA tdA1 = tdDao.queryContainerA(td).orElseThrow();
        TB tdB = tdDao.queryContainerB(td).orElseThrow();
        assertEquals(tb1.identifier().getIdentifier(), tdA.identifier().getIdentifier());
        assertEquals(tb1.identifier().getIdentifier(), tdA1.identifier().getIdentifier());
        assertEquals(tb1.identifier().getIdentifier(), tdB.identifier().getIdentifier());

        // Entity representation

        B b = bDao.getById(tb.identifier().adaptTo(BIdentifier.class)).orElseThrow();

        C c = b.getConA();
        A cA = cDao.queryContainerA(c).orElseThrow();
        B cB = cDao.queryContainerB(c).orElseThrow();
        B cB1 = cDao.queryContainerB1(c).orElseThrow();
        assertEquals(b.identifier().getIdentifier(), cA.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB1.identifier().getIdentifier());

        B b1 = bDao.getById(tb1.identifier().adaptTo(BIdentifier.class)).orElseThrow();
        D d = b1.getDonB();
        A dA = dDao.queryContainerA(d).orElseThrow();
        A dA1 = dDao.queryContainerA(d).orElseThrow();
        B dB = dDao.queryContainerB(d).orElseThrow();
        assertEquals(b1.identifier().getIdentifier(), dA.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dA1.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dB.identifier().getIdentifier());

    }

}
