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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testing.testing.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestingDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import liquibase.pro.packaged.P;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class Testing extends AbstractJslTest {

    @Inject ADao aDao;

    @Inject BDao bDao;

    @Override
    public Module getModelDaoModule() {
        return new TestingDaoModules();
    }

    @Override
    public String getModelName() {
        return "Testing";
    }

    @Test
    public void testContainerFunction() {

        A a =aDao.create(A.builder().withCompB(B.builder().build()).build());

        B b = a.getCompB().get();

        bDao.queryContainerARelC(b);

    }

}
