package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Module;
import com.google.inject.Inject;

import hu.blackbelt.judo.meta.expression.builder.jql.function.variable.GetVariableFunctionTransformer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010.modeltc010.envvars.EnvVars;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010.modeltc010.envvars.EnvVarsDao;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetSystemProperty;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ModelTC010DaoModules;

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

public class GetVariablesTest extends AbstractJslTest {
    @Inject
    EnvVarsDao envVarsDao;

    @Override
    public Module getModelDaoModule() {
        return new ModelTC010DaoModules();
    }

    @Override
    public String getModelName() {
        return "modelTC010";
    }
    
    /**
     * With all judo-types primitives test the !getVariables(“ENVIRONMENT“, “key”) that read the key environment variable, and check the returned values.
     * 
     * @prerequisites The following environment variables and their values have to be set as you see below:
     *  * JUDO_ENV_BOOLEAN1 = "true"
     *  * JUDO_ENV_BOOLEAN2 = "false"
     *  * JUDO_ENV_DATE1 = "2023-02-13"
     *  * JUDO_ENV_DATE2 = "2023-02-28"
     *  * JUDO_ENV_TIME1 = "00:00:01"
     *  * JUDO_ENV_TIME2 = "23:59:02"
     *  * JUDO_ENV_TIMESTAMP1 = "2023-02-13T10:11:12+01:00"
     *  * JUDO_ENV_TIMESTAMP2 = "2023-02-14T10:11:12+01:00"
     *  * JUDO_ENV_INTEGER1 = "987654321"
     *  * JUDO_ENV_INTEGER2 = "123456789"
     *  * JUDO_ENV_LONG1 = "987654321098765"
     *  * JUDO_ENV_LONG2 = "123456789012345"
     *  * JUDO_ENV_STRING1 = "'Lorem ipsum dolor set...' And a number: 1987"
     *  * JUDO_ENV_STRING2 = "Other text."
     * 
     * @type 'Behaviour'.
     * 
     * @scenario
     *  . Parse (and/or build) the model.
     *  
     *  . The result of the model parsing (and/or building) is successful.
     *  
     *  . Create an EnvVars entity instance (ev1) with the default values.
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
     *  . Change the values of the following fields of the `ev1` entity instance as you can see below.
     *      * fBool      = Boolean!getVariable(
     *                         category = "ENVIRIONMENT",
     *                         key = "JUDO_ENV_BOOLEAN2"
     *                     );
     *      * fDate      = Date!getVariable(
     *                         category = "ENVIRIONMENT",
     *                         key = "JUDO_ENV_DATE2"
     *                     );
     *      * fTime      = Time!getVariable(
     *                         category = "ENVIRIONMENT",
     *                         key = "JUDO_ENV_TIME2"
     *                     );
     *      * fTimestamp = Timestamp!getVariable(
     *                         category = "ENVIRIONMENT",
     *                         key = "JUDO_ENV_TIMESTAMP2"
     *                     );
     *      * fInt       = Integer!getVariable(
     *                         category = "ENVIRIONMENT",
     *                         key = "JUDO_ENV_INTEGER2"
     *                     );
     *      * fLong      = Long!getVariable(
     *                         category = "ENVIRIONMENT",
     *                         key = "JUDO_ENV_LONG2"
     *                     );
     *      * fString    = String!getVariable(
     *                         category = "ENVIRIONMENT",
     *                         key = "JUDO_ENV_STRING2"
     *                     );
     *  
     *  . Check the values of the following fields of the new entity instance (ev1).
     *      * fBool == false
     *      * fDate == `2023-02-28`
     *      * fTime == `23:59:02`
     *      * fTimestamp == `2023-02-14T10:11:12+01:00`
     *      * fInt == 123456789
     *      * fLong == 123456789012345
     *      * fString == "Other text."
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
    @SetSystemProperty(key = "JUDO_ENV_BOOLEAN1", value = "true")
    @SetSystemProperty(key = "JUDO_ENV_BOOLEAN2", value = "false")
    @SetSystemProperty(key = "JUDO_ENV_DATE1", value = "2023-02-13")
    @SetSystemProperty(key = "JUDO_ENV_DATE2", value = "2023-02-28")
    @SetSystemProperty(key = "JUDO_ENV_TIME1", value = "00:00:01")
    @SetSystemProperty(key = "JUDO_ENV_TIME2", value = "23:59:02")
    @SetSystemProperty(key = "JUDO_ENV_TIMESTAMP1", value = "2023-02-13T10:11:12+01:00")
    @SetSystemProperty(key = "JUDO_ENV_TIMESTAMP2", value = "2023-02-14T10:11:12+01:00")
    @SetSystemProperty(key = "JUDO_ENV_INTEGER1", value = "987654321")
    @SetSystemProperty(key = "JUDO_ENV_INTEGER2", value = "123456789")
    @SetSystemProperty(key = "JUDO_ENV_LONG1", value = "987654321098765")
    @SetSystemProperty(key = "JUDO_ENV_LONG2", value = "123456789012345")
    @SetSystemProperty(key = "JUDO_ENV_STRING1", value = "'Lorem ipsum dolor set...' And a number: 1987")
    @SetSystemProperty(key = "JUDO_ENV_STRING2", value = "Other text.")
    public void getVariablesEnvironmentWithJudoPrimitiveTypes() {
        System.setProperty("JUDO_ENV_STRING1", "'Lorem ipsum dolor set...' And a number: 1987");
        // Create an EnvVars entity instance (ev1) with the default values.
        EnvVars ev1 = envVarsDao.create(
                EnvVars
                .builder()
                .build()
        );
        
        assertTrue(ev1.getFBool().orElseThrow());
        assertEquals(LocalDate.of(2023, 02, 13), ev1.getFDate().orElseThrow());
        assertEquals(LocalTime.of(0, 0, 01), ev1.getFTime().orElseThrow());
        assertEquals(
            OffsetDateTime.of(
                2023,
                02,
                13,
                10,
                11,
                12,
                0,
                ZoneOffset.of("+01:00")
            ),
            ev1.getFTimestamp().orElseThrow()
        );
        assertEquals(987654321, ev1.getFInt().orElseThrow());
        assertEquals(987654321098765L, ev1.getFLong().orElseThrow());
        assertEquals(
            "'Lorem ipsum dolor set...' And a number: 1987",
            ev1.getFString().orElseThrow()
        );
        
        /*
        . Change the values of the following fields of the `ev1` entity instance as you can see below.
            * fBool      = Boolean!getVariable(
                               category = "ENVIRIONMENT",
                               key = "JUDO_ENV_BOOLEAN2"
                           );
            * fDate      = Date!getVariable(
                               category = "ENVIRIONMENT",
                               key = "JUDO_ENV_DATE2"
                           );
            * fTime      = Time!getVariable(
                               category = "ENVIRIONMENT",
                               key = "JUDO_ENV_TIME2"
                           );
            * fTimestamp = Timestamp!getVariable(
                               category = "ENVIRIONMENT",
                               key = "JUDO_ENV_TIMESTAMP2"
                           );
            * fInt       = Integer!getVariable(
                               category = "ENVIRIONMENT",
                               key = "JUDO_ENV_INTEGER2"
                           );
            * fLong      = Long!getVariable(
                               category = "ENVIRIONMENT",
                               key = "JUDO_ENV_LONG2"
                           );
            * fString    = String!getVariable(
                               category = "ENVIRIONMENT",
                               key = "JUDO_ENV_STRING2"
                           );
        */
        //TODO
        
        assertFalse(ev1.getFBool().orElseThrow());
        assertEquals(LocalDate.of(2023, 02, 28), ev1.getFDate().orElseThrow());
        assertEquals(LocalTime.of(23, 59, 02), ev1.getFTime().orElseThrow());
        assertEquals(
            OffsetDateTime.of(
                2023,
                02,
                14,
                10,
                11,
                12,
                0,
                ZoneOffset.of("+01:00")
            ),
            ev1.getFTimestamp().orElseThrow()
        );
        assertEquals(123456789, ev1.getFInt().orElseThrow());
        assertEquals(123456789012345L, ev1.getFLong().orElseThrow());
        assertEquals(
            "Other text.",
            ev1.getFString().orElseThrow()
        );
    }
}
