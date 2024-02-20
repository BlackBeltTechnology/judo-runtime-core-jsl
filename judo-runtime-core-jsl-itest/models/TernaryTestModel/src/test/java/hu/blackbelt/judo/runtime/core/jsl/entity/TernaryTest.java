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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.ternarytest.ternarytest.aaa.AAA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.ternarytest.ternarytest.aaa.AAADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.ternarytest.ternarytest.aaa.AAAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.ternarytest.ternarytest.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TernaryTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TernaryTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TernaryTest", new TernaryTestDaoModules());

    @Inject
    AAADao aDao;

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-004",
            "REQ-SYNT-005"
    })
    public void testTernaries() {

        final String stringValue = "STRING";
        final Integer integerValue = 10;
        final Double doubleValue = 3.14159265;
        final LocalDate dateValue = LocalDate.of(2020, 10, 20);
        final LocalDateTime timestampValue = LocalDateTime.of(2020, 10, 20, 16, 30, 5, 0);

        AAA a = aDao.create(AAAForCreate.builder()
                .withStringR(stringValue)
                .withIntegerR(integerValue)
                .withDoubleR(BigDecimal.valueOf(doubleValue))
                .withBooleanR(true)
                .withDateR(dateValue)
                .withTimestampR(timestampValue)
                .withEnumR(Enum.Literal1)
                .build()
        );

        assertThat(a.getConstantString().orElseThrow(), equalTo("X"));
        assertThat(a.getString().orElseThrow(), equalTo(stringValue));
        assertThat(a.getInteger().orElseThrow(), equalTo(integerValue));
        assertThat(a.getDouble().orElseThrow(), equalTo(BigDecimal.valueOf(doubleValue)));
        assertThat(a.getBoolean().orElseThrow(), equalTo(true));
        assertThat(a.getDate().orElseThrow(), equalTo(dateValue));
        assertThat(a.getTimestamp().orElseThrow(), equalTo(timestampValue));
        assertThat(a.getUnknownCondition().orElseThrow(), equalTo(stringValue));


        assertEquals("true", a.getTs().orElseThrow());
        assertEquals("aaa", a.getTs1().orElseThrow());
        assertEquals("true  ", a.getTs2().orElseThrow());
        assertEquals("aaa", a.getTs3().orElseThrow());
        assertEquals("false", a.getTs4().orElseThrow());
        assertEquals("false", a.getTs5().orElseThrow());
        assertEquals("!!false!!", a.getTs6().orElseThrow());
        assertEquals("  aaa", a.getTs7().orElseThrow());

        // TODO: JNG-3839
//        Optional<BBB> t2 = aDao.getT2(a);
//        assertTrue(t2.isEmpty());
//        List<BBB> t3 = aDao.getT3(a);
//        assertTrue(t3.isEmpty());

        AAA a1 = aDao.create(AAAForCreate.builder()
                .withStringR(stringValue)
                .withIntegerR(integerValue)
                .withDoubleR(BigDecimal.valueOf(doubleValue))
                .withBooleanR(false)
                .withDateR(dateValue)
                .withTimestampR(timestampValue)
                .withEnumR(Enum.Literal2)
                .build()
        );

        assertThat(a1.getConstantString().orElseThrow(), equalTo("Y"));
        assertThat(a1.getConstantString1().orElseThrow(), equalTo("Y"));
        assertTrue(a1.getString().isEmpty());
        assertTrue(a1.getInteger().isEmpty());
        assertTrue(a1.getDouble().isEmpty());
        assertTrue(a1.getBoolean().isEmpty());
        assertTrue(a1.getBooleanO().isEmpty());
        assertTrue(a1.getDate().isEmpty());
        assertTrue(a1.getTimestamp().isEmpty());
        assertThat(a1.getUnknownCondition().orElseThrow(), equalTo(stringValue));


        // TODO https://blackbelt.atlassian.net/browse/JNG-5543
        //assertTrue(a1.getFirstLetterIsUpper().isEmpty());
        //assertTrue(a1.getFirstLetterIsUpperWithDefault().isPresent());


    }

}
