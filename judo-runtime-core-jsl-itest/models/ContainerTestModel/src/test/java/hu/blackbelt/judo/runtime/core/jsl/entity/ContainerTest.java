package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ContainerTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ContainerTest extends AbstractJslTest {

    @Inject BDao bDao;
    @Inject CDao cDao;
    @Inject DDao dDao;

    @Override
    public Module getModelDaoModule() {
        return new ContainerTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "ContainerTest";
    }

    @Test
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
            "REQ-SYNT-003"
    })
    public void testContainerFunction() {
        B b = bDao.create(B.builder()
                           .withConA(C.builder().build())
                           .withDonB(D.builder().build())
                           .build());
        C c = b.getConA();
        A cA = cDao.queryContainerA(c).orElseThrow();
        B cB = cDao.queryContainerB(c).orElseThrow();
        B cB1 = cDao.queryContainerB1(c).orElseThrow();
        assertEquals(b.identifier().getIdentifier(), cA.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB1.identifier().getIdentifier());

        B b1 = bDao.create(B.builder()
                            .withConA(C.builder().build())
                            .withDonB(D.builder().build())
                            .build());
        D d = b1.getDonB();
        A dA = dDao.queryContainerA(d).orElseThrow();
        A dA1 = dDao.queryContainerA(d).orElseThrow();
        B dB = dDao.queryContainerB(d).orElseThrow();
        assertEquals(b1.identifier().getIdentifier(), dA.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dA1.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dB.identifier().getIdentifier());
    }

}
