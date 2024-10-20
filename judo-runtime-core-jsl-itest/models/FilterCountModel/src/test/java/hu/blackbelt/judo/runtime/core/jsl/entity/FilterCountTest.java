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

import com.google.common.base.Strings;
import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.AForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.AMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.b.BForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FilterCountModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.Assert.assertEquals;

@Slf4j
public class FilterCountTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("FilterCountModel", new FilterCountModelDaoModules());

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Test
    void testAncestorFilterCount(JudoRuntimeFixture fixture) {
        // Filter on the entity attributes inherited from the ancestor and count the matched ones.

        C c = cDao.create(CForCreate.builder().withNum(2).build());

        B b = bDao.create(BForCreate.builder().withName("B").build());

        A a = aDao.create(AForCreate.builder()
                .withName("A")
                .withAncestorName("PA")
                .withNumb(2).withC(c)
                .withAncestorB(b).build());

        assertEquals("A", a.getName().orElseThrow());
        assertEquals("PA", a.getAncestorName().orElseThrow());
        assertEquals("B", a.getAncestorBName().orElseThrow());
        assertEquals("B", a.getBname().orElseThrow());


        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.ancestorBName!like('B')");
        assertEquals(1, aDao.query().filterBy("this.ancestorBName!like('B')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.ancestorBName!like('B')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.ancestorName!like('PA')");
        assertEquals(1, aDao.query().filterBy("this.ancestorName!like('PA')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.ancestorName!like('PA')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.bname!like('B')");
        assertEquals(1, aDao.query().filterBy("this.bname!like('B')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.bname!like('B')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.name!like('A')");
        assertEquals(1, aDao.query().filterBy("this.name!like('A')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.name!like('A')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.cnum == 2");
        assertEquals(1, aDao.query().filterBy("this.cnum == 2").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.cnum == 2").count());

    }

}
