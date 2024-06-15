package hu.blackbelt.judo.runtime.core.jsl.entity;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.ta.TA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.ta.TADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.ta.TAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.tb.TB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.tb.TBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.tb.TBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.tc.TCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestModelTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TestModel", new TestModelDaoModules());

    @Inject
    TADao taDao;

    @Inject
    TBDao tbDao;

    @Test
    @Requirement(reqs = {
            "",
    })
    public void test() {
        TA ta = taDao.create(TAForCreate.builder().withName("ta").build());

        ta.setB(TB.from(TBForCreate.builder().withName("tb").withC(TCForCreate.builder().withName("tc").build()).build().toMap()));

        ta = taDao.update(ta);

        ta.getB().get().setName("tbChanged");

        ta = taDao.update(ta);

        assertEquals(1, tbDao.countAll());
        assertEquals(1, taDao.countAll());

        ta.setB(TB.from(TBForCreate.builder().withName("tb").withC(TCForCreate.builder().withName("tc").build()).build().toMap()));
        ta = taDao.update(ta);

        assertEquals(2, tbDao.countAll());
        assertEquals(1, taDao.countAll());

    }

}
