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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.BAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.e.E;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.e.EDao;

import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ContainerTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ContainerTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("ContainerTest", new ContainerTestDaoModules());

    @Inject BDao bDao;
    @Inject CDao cDao;
    @Inject
    DDao dDao;
    @Inject EDao eDao;

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
    public void testInheritedContainerFunction() {

        E e = eDao.create(E.builder().withName("E").build());

        B b = bDao.create(B.builder()
                .withConA(C.builder().build())
                .withDonB(D.builder().build())
                .build(),
                BAttachedRelationsForCreate.builder()
                        .withRelEonB(e)
                        .build()
        );

        // the container is on the parent, the relation is on the child entity
        C c = b.getConA();

        assertEquals(e.identifier(), cDao.queryContainerAasBrelEonB(c).get().identifier());
        // TODO No value present
        //assertEquals(e.identifier(), cDao.queryContainerBrelEonB(c).get().identifier());

        // Recursive C relation
        assertFalse(cDao.queryContainerAasBrelConB(c).isPresent());
        // TODO When no relConB relation is attached, the recursive relation contains the c instance.
        //assertFalse(cDao.queryContainerBrelConB(c).isPresent()); // not work

        C c1 = bDao.createRelConB(b, C.builder().build());
        assertEquals(c1.identifier(), cDao.queryContainerAasBrelConB(c).get().identifier());
        // TODO Recursive relation contains the c instance always, not the c.container.relConB if it is present
        //assertEquals(c1.identifier(), cDao.queryContainerBrelConB(c).get().identifier()); // not work

        // the container and the relation are in the same entity
        D d = b.getDonB();

        assertEquals(e.identifier(), dDao.queryContainerAasBrelEonB(d).get().identifier());
        assertEquals(e.identifier(), dDao.queryContainerBrelEonB(d).get().identifier());

        // Recursive D relation
        assertFalse(dDao.queryContainerAasBrelDonB(d).isPresent());
        assertFalse(dDao.queryContainerBrelDonB(d).isPresent());

        D d1 = bDao.createRelDonB(b, D.builder().build());
        assertEquals(d1.identifier(), dDao.queryContainerAasBrelDonB(d).get().identifier());
        assertEquals(d1.identifier(), dDao.queryContainerBrelDonB(d).get().identifier());

    }

}
