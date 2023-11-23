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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.lead.Lead;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.lead.LeadDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.lead.LeadForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootallleads.RootAllLeadsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootallleadsbetween.RootAllLeadsBetweenDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootallleadsbetween.RootAllLeadsBetweenParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootcountallleadsbetween.RootCountAllLeadsBetweenDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootcountallleadsbetween.RootCountAllLeadsBetweenParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.rootonelead.RootOneLeadDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.querymodel.querymodel.totalnumberofleads.TotalNumberOfLeadsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.QueryModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class QueryTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("QueryModel", new QueryModelDaoModules());

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
        leadDao.create(LeadForCreate.builder().withValue(50).build());
        leadDao.create(LeadForCreate.builder().withValue(175).build());

        assertEquals(2, totalNumberOfLeadsDao.selectValue());
        assertEquals(2, rootAllLeadsDao.query().selectList().size());
        assertTrue(rootOneLeadDao.selectOne().isPresent());

        List<Lead> rootAllLeadsBetween = rootAllLeadsBetweenDao.query(RootAllLeadsBetweenParameter.builder().withMax(80).withMin(10).build()).selectList();
        assertEquals(1, rootAllLeadsBetween.size());
        assertEquals(Optional.of(50), rootAllLeadsBetween.get(0).getValue());

        Integer rootCountAllLeadsBetween = rootCountAllLeadsBetweenDao
                .selectValue(RootCountAllLeadsBetweenParameter.builder()
                        .withMin(10)
                        .withMax(80)
                        .build());

        assertEquals(1, rootCountAllLeadsBetween);

        Integer rootCountAllLeadsBetweenDefault = rootCountAllLeadsBetweenDao
                .selectValue(RootCountAllLeadsBetweenParameter.builder()
                        .build());

        assertEquals(2, rootCountAllLeadsBetweenDefault);
    }
}
