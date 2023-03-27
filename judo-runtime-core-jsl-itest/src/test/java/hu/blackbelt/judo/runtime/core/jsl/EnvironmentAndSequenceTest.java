package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvarbool.EnvVarBool;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvarbool.EnvVarBoolDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvardate.EnvVarDate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvardate.EnvVarDateDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvarinteger.EnvVarInteger;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvarinteger.EnvVarIntegerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvarlong.EnvVarLong;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvarlong.EnvVarLongDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvars.EnvVars;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvars.EnvVarsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvars1.EnvVars1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvars1.EnvVars1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvars2.EnvVars2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvars2.EnvVars2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvartime.EnvVarTime;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvartime.EnvVarTimeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvartimestamp.EnvVarTimestamp;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.envvartimestamp.EnvVarTimestampDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.sequences.Sequences;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.environmentandsequencemodel.environmentandsequencemodel.sequences.SequencesDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.EnvironmentAndSequenceModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.util.EnvironmentVariableMocker;
import hu.blackbelt.judo.runtime.core.jsl.util.EnvironmentVariables;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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

public class EnvironmentAndSequenceTest extends AbstractJslTest {

    @Inject
    EnvVars1Dao envVars1Dao;
    @Inject
    EnvVars2Dao envVars2Dao;
    @Inject
    EnvVarBoolDao envVarBoolDao;
    @Inject
    EnvVarDateDao envVarDateDao;
    @Inject
    EnvVarTimeDao envVarTimeDao;
    @Inject
    EnvVarTimestampDao envVarTimestampDao;
    @Inject
    EnvVarIntegerDao envVarIntegerDao;
    @Inject
    EnvVarLongDao envVarLongDao;
    @Inject
    EnvVarsDao envVarsDao;
    @Inject
    SequencesDao sequencesDao;

    @Override
    public Module getModelDaoModule() {
        return new EnvironmentAndSequenceModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "EnvironmentAndSequenceModel";
    }

    /**
     * With all judo-types primitives test the !getVariables(“ENVIRONMENT“, “key”) that read the key environment variable, and check the returned values.
     * 
     * @prerequisites The following environment variables and their values have to be set as you see below:
     *  * JUDO_ENV_BOOLEAN1 = "true"
     *  * JUDO_ENV_BOOLEAN2 = "false"
     *  * JUDO_ENV_DATE1 = "2023-02-13"
     *  * JUDO_ENV_DATE2 = "2024-12-28"
     *  * JUDO_ENV_TIME1 = "00:00:01"
     *  * JUDO_ENV_TIME2 = "23:59:02"
     *  * JUDO_ENV_TIMESTAMP1 = "2023-02-13T10:11:12+01:00"
     *  * JUDO_ENV_TIMESTAMP2 = "2023-02-14T10:11:12-01:00"
     *  * JUDO_ENV_INTEGER1 = "987654321"
     *  * JUDO_ENV_INTEGER2 = "-123456789"
     *  * JUDO_ENV_LONG1 = "987654321098765"
     *  * JUDO_ENV_LONG2 = "-123456789012345"
     *  * JUDO_ENV_STRING1 = "'Lorem ipsum dolor set...' And a number: 1987"
     *  * JUDO_ENV_STRING2 = "Other text.

end text"
     * 
     * @type 'Behaviour'.
     * 
     * @scenario
     *  . Parse (and/or build) the model.
     *  
     *  . The result of the model parsing (and/or building) is successful.
     *  
     *  . Create an EnvVars1 entity instance (ev1) with the default values.
     *  
     *  . Check the values of the following fields of the new entity instance (ev1).
     *      * fBool == true
     *      * fDate == `2023-02-13`
     *      * fTime == `00:00:01`
     *      * fTimestamp == `2023-02-13T10:11:12+01:00`
     *      * fInt == 987654321
     *      * fLong == 987654321098765
     *      * fString == "'Lorem ipsum dolor set...' And a number: 1987"
     *  
     *  . Create an EnvVars2 entity instance (ev2) with the default values.
     *  
     *  . Check the values of the following fields of the new entity instance (ev2).
     *      * fBool == false
     *      * fDate == `2024-12-28`
     *      * fTime == `23:59:02`
     *      * fTimestamp == `2023-02-14T10:11:12-01:00`
     *      * fInt == -123456789
     *      * fLong == -123456789012345
     *      * fString == "Other text.

end text"
     *  
     *  . The test is passed if all modifications and checks are successful, and there were no exceptions.
     */
    @Test
    @TestCase("TC010")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-MDL-001",
            "REQ-MDL-003",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-002",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-009",
            "REQ-EXPR-012"
    })
    public void testGetVariableEnvironmentWithJudoPrimitiveTypes() throws Exception {
        EnvironmentVariableMocker.initMocked();
        final boolean JUDO_ENV_BOOLEAN1 = true;
        final boolean JUDO_ENV_BOOLEAN2 = false;
        final LocalDate JUDO_ENV_DATE1 = LocalDate.parse("2023-02-13");
        final LocalDate JUDO_ENV_DATE2 = LocalDate.parse("2024-12-28");
        final int JUDO_ENV_INTEGER1 = 987654321;
        final int JUDO_ENV_INTEGER2 = -123456789;
        final long JUDO_ENV_LONG1 = 987654321098765L;
        final long JUDO_ENV_LONG2 = -123456789012345L;
        final String JUDO_ENV_STRING1 = "'Lorem ipsum dolor set...' And a number: 1987";
        final String JUDO_ENV_STRING2 = "Other text.\n\nend text";
        final LocalTime JUDO_ENV_TIME1 = LocalTime.parse("00:00:01");
        final LocalTime JUDO_ENV_TIME2 = LocalTime.parse("23:59:02");
        final OffsetDateTime JUDO_ENV_TIMESTAMP1 = OffsetDateTime.parse("2023-02-13T10:11:12+01:00");
        final OffsetDateTime JUDO_ENV_TIMESTAMP2 = OffsetDateTime.parse("2023-02-14T10:11:12-01:00");
        EnvironmentVariables environmentVariables = new EnvironmentVariables()
                .set("JUDO_ENV_BOOLEAN1", JUDO_ENV_BOOLEAN1)
                .set("JUDO_ENV_BOOLEAN2", JUDO_ENV_BOOLEAN2)
                .set("JUDO_ENV_DATE1", JUDO_ENV_DATE1)
                .set("JUDO_ENV_DATE2", JUDO_ENV_DATE2)
                .set("JUDO_ENV_INTEGER1", JUDO_ENV_INTEGER1)
                .set("JUDO_ENV_INTEGER2", JUDO_ENV_INTEGER2)
                .set("JUDO_ENV_LONG1", JUDO_ENV_LONG1)
                .set("JUDO_ENV_LONG2", JUDO_ENV_LONG2)
                .set("JUDO_ENV_STRING1", JUDO_ENV_STRING1)
                .set("JUDO_ENV_STRING2", JUDO_ENV_STRING2)
                .set("JUDO_ENV_TIME1", JUDO_ENV_TIME1)
                .set("JUDO_ENV_TIME2", JUDO_ENV_TIME2)
                .set("JUDO_ENV_TIMESTAMP1", JUDO_ENV_TIMESTAMP1)
                .set("JUDO_ENV_TIMESTAMP2", JUDO_ENV_TIMESTAMP2);
        try {
            environmentVariables
                    .execute(() -> {
                        EnvVars1 ev1 = envVars1Dao.create(EnvVars1.builder().build());

                        assertTrue(ev1.getFbool().orElseThrow());
                        assertEquals(JUDO_ENV_BOOLEAN1, ev1.getFbool().orElseThrow());
                        assertEquals(JUDO_ENV_DATE1, ev1.getFdate().orElseThrow());
                        assertEquals(JUDO_ENV_INTEGER1, ev1.getFint().orElseThrow());
                        assertEquals(JUDO_ENV_LONG1, ev1.getFlong().orElseThrow());
                        assertEquals(JUDO_ENV_STRING1, ev1.getFstring().orElseThrow());
                        assertEquals(JUDO_ENV_TIME1, ev1.getFtime().orElseThrow());
                        assertEquals(JUDO_ENV_TIMESTAMP1.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime(), ev1.getFtimestamp().orElseThrow());

                        EnvVars2 ev2 = envVars2Dao.create(EnvVars2.builder().build());

                        assertFalse(ev2.getFbool().orElseThrow());
                        assertEquals(JUDO_ENV_BOOLEAN2, ev2.getFbool().orElseThrow());
                        assertEquals(JUDO_ENV_DATE2, ev2.getFdate().orElseThrow());
                        assertEquals(JUDO_ENV_INTEGER2, ev2.getFint().orElseThrow());
                        assertEquals(JUDO_ENV_LONG2, ev2.getFlong().orElseThrow());
                        assertEquals(JUDO_ENV_STRING2, ev2.getFstring().orElseThrow());
                        assertEquals(JUDO_ENV_TIME2, ev2.getFtime().orElseThrow());
                        assertEquals(JUDO_ENV_TIMESTAMP2.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime(), ev2.getFtimestamp().orElseThrow());
                    });
        } finally {
            environmentVariables.teardown();
            EnvironmentVariableMocker.deinitMocked();
        }
    }

    /**
     * With all judo-types primitives, except the String, test the !getVariables(“ENVIRONMENT“, “key”) that read the key environment variable that's value isn't convertable to the usaged type.
     * 
     * @prerequisites The following environment variables and their values have to be set as you see below:
     *  * JUDO_ENV_XXX = "xxxx"
     * 
     * @type Behaviour
     * 
     * @negativeRequirements REQ-EXPR-009
     * 
     * @scenario
     *  . Parse (and/or build) the model.
     *  
     *  . The result of the model parsing (and/or building) is successful.
     *  
     *  . Create an EnvVarBool entity instance with the default values. The creation process will fail with an exception.
     *  
     *  . Create an EnvVarDate entity instance with the default values. The creation process will fail with an exception.
     *  
     *  . Create an EnvVarTime entity instance with the default values. The creation process will fail with an exception.
     *  
     *  . Create an EnvVarTimestamp entity instance with the default values. The creation process will fail with an exception.
     *  
     *  . Create an EnvVarInteger entity instance with the default values. The creation process will fail with an exception.
     *  
     *  . Create an EnvVarLong entity instance with the default values. The creation process will fail with an exception.
     *  
     *  . The test is passed if all steps have been completed with the specified results.
     */
    @Test
    @TestCase("TC011")
    @Disabled("JNG-4621")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-MDL-001",
            "REQ-MDL-003",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-009",
            "REQ-EXPR-012"
    })
    public void testGetVariableEnvironmentWithJudoPrimitiveTypesExceptions() throws Exception {
        EnvironmentVariableMocker.initMocked();
        EnvironmentVariables environmentVariables = new EnvironmentVariables(Map.of(
                "JUDO_ENV_XXX", "xxxx"
        ));
        try {
            environmentVariables
                    .execute(() -> {
                        assertThrows(RuntimeException.class, () -> envVarBoolDao.create(EnvVarBool.builder().build()));
                        assertThrows(RuntimeException.class, () -> envVarDateDao.create(EnvVarDate.builder().build()));
                        assertThrows(RuntimeException.class, () -> envVarTimeDao.create(EnvVarTime.builder().build()));
                        assertThrows(RuntimeException.class, () -> envVarTimestampDao.create(EnvVarTimestamp.builder().build()));
                        assertThrows(RuntimeException.class, () -> envVarIntegerDao.create(EnvVarInteger.builder().build()));
                        assertThrows(RuntimeException.class, () -> envVarLongDao.create(EnvVarLong.builder().build()));
                    });
        } finally {
            environmentVariables.teardown();
            EnvironmentVariableMocker.deinitMocked();
        }
    }

    /**
     * Verify the !getVariable(“SYSTEM“, “key”) function calls and their shortcuts.
     *
     * @prerequisites Nothing
     *
     * @type Behaviour
     * 
     * @others If the parts of the expression won't evaluate in the "same time". The result of this test case is divergent.
     *  Sometimes OK, sometimes Failed. In this case the equality need to replace with an investigation of the subtraction's result.
     *
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create an EnvVars entity instance (evs1) with the default values.
     *
     *  . Check the values of the following fields of the new entity instance (evs1).
     *      * f1 == true
     *      * f2 == true
     *      * f3 == true
     *
     *  . The test is passed if all steps have been completed with the specified results.
     */
    @Test
    @TestCase("TC012")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-TYPE-001",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-MDL-001",
            "REQ-MDL-003",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-009",
            "REQ-EXPR-012"
    })
    void testVerifyTheGetVariableShortcuts() {
        EnvVars envVars = envVarsDao.create(EnvVars.builder().build());

        LocalDate localDate = envVars.getCd().orElseThrow();
        LocalDate localDate1 = envVars.getCd1().orElseThrow();
        if (localDate.isBefore(localDate1)) {
            assertTrue(localDate1.minusDays(1).isBefore(localDate));
        } else if (localDate1.isBefore(localDate)) {
            assertTrue(localDate.minusDays(1).isBefore(localDate1));
        } else {
            assertEquals(localDate, localDate1);
        }

        LocalDateTime localDateTime = envVars.getCts().orElseThrow();
        LocalDateTime localDateTime1 = envVars.getCts1().orElseThrow();
        if (localDateTime.isBefore(localDateTime1)) {
            assertTrue(localDateTime1.minusNanos(500_000_000).isBefore(localDateTime));
        } else if (localDateTime1.isBefore(localDateTime)) {
            assertTrue(localDateTime.minusNanos(500_000_000).isBefore(localDateTime1));
        } else {
            assertEquals(localDateTime, localDateTime1);
        }

        LocalTime localTime = envVars.getCt().orElseThrow();
        LocalTime localTime1 = envVars.getCt1().orElseThrow();
        if (localTime.isBefore(localTime1)) {
            assertTrue(localTime1.minusNanos(500_000_000).isBefore(localTime) || localTime1.minusSeconds(2).isBefore(localTime.minusSeconds(1)));
        } else if (localTime1.isBefore(localTime)) {
            assertTrue(localTime.minusNanos(500_000_000).isBefore(localTime1) || localTime.minusSeconds(2).isBefore(localTime1.minusSeconds(1)));
        }
    }

    /**
     * Verify the !getVariable(“SEQUENCE“, “key”) function calls.
     *
     * @prerequisites The model runtime is empty. The "Order" and "Other" sequences have not been called before.
     *
     * @type Behaviour
     *
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create an Sequences entity instance (s1) with the default values.
     *
     *  . Check the values of the following fields of the new entity instance (s1).
     *      * f1 == 1
     *      * f2 == 2
     *      * f3 == 1
     *
     *  . Check the values of the following fields of the new entity instance (s2).
     *      * f1 == 3
     *      * f2 == 4
     *      * f3 == 2
     *
     *  . The test is passed if all steps have been completed with the specified results.
     */
    @Test
    @TestCase("TC013")
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4671")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-MDL-001",
            "REQ-MDL-003",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-001",
            "REQ-EXPR-002",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-009",
            "REQ-EXPR-012"
    })
    void testGetVariableSequence() {
        Sequences s1 = sequencesDao.create(Sequences.builder().build());
        assertEquals(1L, s1.getF1().orElseThrow());
        assertEquals(2L, s1.getF2().orElseThrow());
        assertEquals(1L, s1.getF3().orElseThrow());

        Sequences s2 = sequencesDao.create(Sequences.builder().build());
        assertEquals(3L, s2.getF1().orElseThrow());
        assertEquals(4L, s2.getF2().orElseThrow());
        assertEquals(2L, s2.getF3().orElseThrow());
    }

}
