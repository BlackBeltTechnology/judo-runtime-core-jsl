package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.test.test.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.test.test.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.sdk.Identifiable;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class Test extends AbstractJslTest {

    @Inject
    ADao aDao;


    @Override
    public Module getModelDaoModule() {
        return new TestDaoModules();
    }

    @Override
    public String getModelName() {
        return "Test";
    }


    @org.junit.jupiter.api.Test
    public void testStaticNavigation() {
        A a = aDao.create(A.builder().build());

        assertEquals("Hello", a.getStringQuery().orElseThrow());
        a.setStringQuery("Mom");
        assertEquals("Mom", a.getStringQuery().orElseThrow());
        a = aDao.update(a);
        assertEquals("Hello", aDao.queryStringQuery(a).get());



    }

}
