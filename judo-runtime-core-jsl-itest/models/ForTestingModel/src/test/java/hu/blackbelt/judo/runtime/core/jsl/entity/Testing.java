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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FormsTestDaoModules;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestingDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Testing {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Testing", new TestingDaoModules());


    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Test
    public void testContainerFunction() {

        A a =aDao.create(A.builder().withCompB(B.builder().build()).build());

        B bRel = bDao.create(B.builder().build());

        aDao.setRelB(a, bRel);

        B b = a.getCompB().get();

        A containerA =bDao.queryDerivedElement(b).orElseThrow();

        assertEquals(a.identifier() , containerA.identifier());
        assertEquals(b.identifier() , containerA.getCompB().get().identifier());
        assertEquals(bRel.identifier() , aDao.queryRelB(a).get().identifier());


        bDao.queryContainerARelC(b);

    }

}
