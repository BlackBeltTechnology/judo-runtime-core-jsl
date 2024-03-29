package hu.blackbelt.judo.runtime.core.jsl.transfer;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.a.AIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.ta.TA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.ta.TADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.ta.TAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.tb.TB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.tb.TBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.tb.TBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ContainsModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class MappedTransferContainsTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("ContainsModel", new ContainsModelDaoModules());

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    TADao taDao;

    @Inject
    TBDao tbDao;

    /**
     * The test checks the contains Collection function work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel ContainsModel.jsl
     *
     * @positiveRequirements REQ-EXPR-021
     *
     */
    @Test
    @TestCase("ContainsOnMappedTransfer")
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-008",
            "REQ-EXPR-004",
            "REQ-EXPR-022",
            "REQ-SRV-002"
    })
    public void testContainsOnMappedTransfer() {
        TA ta = taDao.create(TAForCreate.builder().build());
        assertFalse(ta.getContainsTest().orElseThrow());
        assertFalse(ta.getContainsTest1().orElseThrow());

        TB tb = tbDao.create(TBForCreate.builder().withName("test").build());
        TB tb1 = tbDao.create(TBForCreate.builder().withName("not test").build());
        TA ta1 = taDao.create(TAForCreate.builder()
                            .withB(tb)
                            .withBs(List.of(tb, tb1))
                            .build());
        assertTrue(ta1.getContainsTest().orElseThrow());
        assertTrue(ta1.getContainsTest1().orElseThrow());

        // Entity representation

        A a = aDao.getById(ta.identifier().adaptTo(AIdentifier.class)).orElseThrow();
        assertFalse(a.getContainsTest().orElseThrow());
        assertFalse(a.getContainsTest1().orElseThrow());

        A a1 = aDao.getById(ta1.identifier().adaptTo(AIdentifier.class)).orElseThrow();
        assertTrue(a1.getContainsTest().orElseThrow());
        assertTrue(a1.getContainsTest1().orElseThrow());
    }

}
