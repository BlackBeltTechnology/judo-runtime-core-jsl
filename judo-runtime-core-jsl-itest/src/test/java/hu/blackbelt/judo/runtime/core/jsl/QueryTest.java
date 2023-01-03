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
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootallleads.RootAllLeadsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootallleadsbetween.RootAllLeadsBetweenDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootallleadsbetween.RootAllLeadsBetweenParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootcountallleadsbetween.RootCountAllLeadsBetweenDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootcountallleadsbetween.RootCountAllLeadsBetweenParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootonelead.RootOneLeadDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.totalnumberofleads.TotalNumberOfLeadsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.lead.Lead;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.lead.LeadDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.QueryModelDaoModules;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class QueryTest extends AbstractJslTest {
    @Inject
    TotalNumberOfLeadsDao totalNumberOfLeadsDao;

    @Inject
    RootOneLeadDao rootOneLeadDao;

    @Inject
    RootAllLeadsDao rootAllLeadsDao;

    @Inject
    RootAllLeadsBetweenDao rootAllLeadsBetweenDao;

    @Inject
    RootCountAllLeadsBetweenDao rootCountAllLeadsBetweenDao;

    @Inject
    LeadDao leadDao;

    @Override
    public Module getModelDaoModule() {
        return new QueryModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "QueryModel";
    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-009",
            "REQ-ENT-010",
            "REQ-ENT-011",
            "REQ-EXPR-008",
            "REQ-EXPR-022"
    })
    public void testStaticQuery() {
        leadDao.create(Lead.builder().withValue(50).build());
        leadDao.create(Lead.builder().withValue(175).build());

        assertEquals(2, totalNumberOfLeadsDao.execute());
        assertEquals(2, rootAllLeadsDao.query().execute().size());
        assertNotNull(rootOneLeadDao.query().execute());

        List<Lead> rootAllLeadsBetween = rootAllLeadsBetweenDao.query()
                .execute(RootAllLeadsBetweenParameter.builder()
                        .withMax(80)
                        .withMin(10)
                        .build()
                );
        assertEquals(1, rootAllLeadsBetween.size());
        assertEquals(Optional.of(50), rootAllLeadsBetween.get(0).getValue());

        Integer rootCountAllLeadsBetween = rootCountAllLeadsBetweenDao
                .execute(RootCountAllLeadsBetweenParameter.builder()
                        .withMin(10)
                        .withMax(80)
                        .build());

        assertEquals(1, rootCountAllLeadsBetween);
    }
}
