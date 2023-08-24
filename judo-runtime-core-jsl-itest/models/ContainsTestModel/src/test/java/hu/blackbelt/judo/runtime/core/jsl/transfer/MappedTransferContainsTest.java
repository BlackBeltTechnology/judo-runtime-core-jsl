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
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.a.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.b.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.ta.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containsmodel.containsmodel.tb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ContainsModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Slf4j
public class MappedTransferContainsTest extends AbstractJslTest {

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    TADao taDao;

    @Inject
    TBDao tbDao;

    @Override
    public Module getModelDaoModule() {return new ContainsModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "ContainsModel";
    }

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
        TA ta = taDao.create(TA.builder().build());
        assertFalse(ta.getContainsTest().orElseThrow());
        assertFalse(ta.getContainsTest1().orElseThrow());

        TB tb = tbDao.create(TB.builder().withName("test").build());
        TB tb1 = tbDao.create(TB.builder().withName("not test").build());
        TA ta1 = taDao.create(TA.builder()
                            .build(), TAAttachedRelationsForCreate.builder()
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
