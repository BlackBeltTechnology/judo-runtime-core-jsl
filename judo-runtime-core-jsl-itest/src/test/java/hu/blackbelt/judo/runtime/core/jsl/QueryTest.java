package hu.blackbelt.judo.runtime.core.jsl;

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
import hu.blackbelt.judo.runtime.core.jsl.itest.querymodel.guice.querymodel.QueryModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.querymodel.sdk.querymodel.querymodel.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class QueryTest extends AbstractJslTest {
    @Inject
    TotalNumberOfLeads.TotalNumberOfLeadsDao totalNumberOfLeadsDao;

    @Inject
    RootOneLead.RootOneLeadDao rootOneLeadDao;

    @Inject
    RootAllLeads.RootAllLeadsDao rootAllLeadsDao;

    @Inject
    RootAllLeadsBetween.RootAllLeadsBetweenDao rootAllLeadsBetweenDao;

    @Inject
    Lead.LeadDao leadDao;

    @Override
    public Module getModelDaoModule() {
        return new QueryModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "QueryModel";
    }

    @Test
    public void testStaticWithoutParameters() {
        leadDao.create(Lead.builder().withValue(50).build());
        leadDao.create(Lead.builder().withValue(175).build());

        assertEquals(2, totalNumberOfLeadsDao.getTotalNumberOfLeads());
        assertEquals(2, rootAllLeadsDao.getRootAllLeads().size());
        assertNotNull(rootOneLeadDao.getRootOneLead());
    }
}
