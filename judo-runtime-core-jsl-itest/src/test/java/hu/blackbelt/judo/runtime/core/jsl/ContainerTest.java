package hu.blackbelt.judo.runtime.core.jsl;

/*-
 * #%L
 * JUDO Runtime Core JSL :: Parent
 * %%
 * Copyright (C) 2018 - 2022 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http:/B/www.eclipse.org/legal/epl-2.0.
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
import hu.blackbelt.judo.runtime.core.jsl.itest.containertest.guice.containertest.ContainerTestDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.containertest.sdk.containertest.containertest.*;
import hu.blackbelt.judo.runtime.core.jsl.itest.containertest.sdk.containertest.containertest.B.BDao;
import hu.blackbelt.judo.runtime.core.jsl.itest.containertest.sdk.containertest.containertest.C.CDao;
import hu.blackbelt.judo.runtime.core.jsl.itest.containertest.sdk.containertest.containertest.D.DDao;
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
    public void testContainerFunction() {
        B b = bDao.create(B.builder().withConA(C.builder().build()).build());
        C c = b.getConA();
        A cA = cDao.getContainerA(c).orElseThrow();
        B cB = cDao.getContainerB(c).orElseThrow();
        assertEquals(b.get__identifier(), cA.get__identifier());
        assertEquals(b.get__identifier(), cB.get__identifier());

        B b1 = bDao.create(B.builder().withDonB(D.builder().build()).build());
        D d = b1.getDonB();
        A dA = dDao.getContainerA(d).orElseThrow();
        B dB = dDao.getContainerB(d).orElseThrow();
        assertEquals(b1.get__identifier(), dA.get__identifier());
        assertEquals(b1.get__identifier(), dB.get__identifier());
    }

}
