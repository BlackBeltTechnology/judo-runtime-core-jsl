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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TAMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tb.TB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tb.TBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tb.TBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tb.TBMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tc.TC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tc.TCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tc.TCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferValidationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class TransientRelationValidationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TransferValidation", new TransferValidationDaoModules(), null);

    @Inject
    TADao taDao;

    @Inject
    TBDao tbDao;

    @Inject
    TCDao tcDao;

    @Test
    @TestCase("TransientRelationValidation")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-EXPR-022",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-005",

    })
    void testTransientRelationValidation() {

        TC c2 = tcDao.create(TCForCreate.builder().withName("C2").build());

        tbDao.create(TBForCreate.builder().withName("C1").withAggregatedC(TCForCreate.builderFrom(c2).build()).build());

        taDao.create(TAForCreate.builder().withName("TA").build());

        TA ta = taDao.query().filterBy("this.name == 'TA'")
                .maskedBy(TAMask.tAMask()
                        .withName()
                        .withDerivedB(TBMask.tBMask()))
                .selectOne()
                .orElseThrow();

        // Transient relation doesn't have any required field
        ta.setTransientB(TB.builder().build());

        final TA finalTA = ta;
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> taDao.update(finalTA));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                        hasProperty("location", equalTo("transientB.name"))),
                allOf(
                        hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                        hasProperty("location", equalTo("transientB.aggregatedC")))
        ));

        ValidationException thrown1 = assertThrows(
                ValidationException.class,
                () -> taDao.createTransientB(finalTA, TBForCreate.builder().build())
        );

        assertThat(thrown1.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                        hasProperty("location", equalTo("name"))),
                allOf(
                        hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                        hasProperty("location", equalTo("aggregatedC")))
        ));

        // sub relations
        ta.setTransientB(TB.builder()
                .withName("L1")
                .withAggregatedC(c2)
                .withTransientC(TC.builder().build())
                .build()
        );

        ValidationException thrown2 = assertThrows(
                ValidationException.class,
                () -> taDao.update(finalTA)
        );

        assertThat(thrown2.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                        hasProperty("location", equalTo("transientB.transientC.name")))
        ));

        ValidationException thrown3 = assertThrows(
                ValidationException.class,
                () -> taDao.createTransientB(ta, TBForCreate.builder()
                        .withName("L1")
                        .withAggregatedC(c2.adaptTo(TCForCreate.class))
                        .withTransientC(TCForCreate.builder().build())
                        .build())
        );

        assertThat(thrown3.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                        hasProperty("location", equalTo("transientC.name")))
        ));
    }

}
