package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Module;
import com.google.inject.Inject;

import hu.blackbelt.judo.meta.expression.builder.jql.function.variable.GetVariableFunctionTransformer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010.modeltc010.envvars.EnvVars;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010.modeltc010.envvars.EnvVarsDao;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;
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
    //@SetEnvironmentVariable(key = "JUDO_ENV_BOOLEAN1", value = "true")
    //@SetEnvironmentVariable(key = "JUDO_ENV_BOOLEAN2", value = "false")
    //@SetEnvironmentVariable(key = "JUDO_ENV_DATE1", value = "2023-02-13")
    //@SetEnvironmentVariable(key = "JUDO_ENV_DATE2", value = "2023-02-28")
    //@SetEnvironmentVariable(key = "JUDO_ENV_TIME1", value = "00:00:01")
    //@SetEnvironmentVariable(key = "JUDO_ENV_TIME2", value = "23:59:02")
    //@SetEnvironmentVariable(key = "JUDO_ENV_TIMESTAMP1", value = "2023-02-13T10:11:12+01:00")
    //@SetEnvironmentVariable(key = "JUDO_ENV_TIMESTAMP2", value = "2023-02-14T10:11:12+01:00")
    //@SetEnvironmentVariable(key = "JUDO_ENV_INTEGER1", value = "987654321")
    //@SetEnvironmentVariable(key = "JUDO_ENV_INTEGER2", value = "123456789")
    //@SetEnvironmentVariable(key = "JUDO_ENV_LONG1", value = "987654321098765")
    //@SetEnvironmentVariable(key = "JUDO_ENV_LONG2", value = "123456789012345")
    //@SetEnvironmentVariable(key = "JUDO_ENV_STRING1", value = "'Lorem ipsum dolor set...' And a number: 1987")
    //@SetEnvironmentVariable(key = "JUDO_ENV_STRING2", value = "Other text.")
    public void getVariablesEnvironmentWithJudoPrimitiveTypes() {
        System.out.println(System.getenv("JUDO_ENV_STRING1"));
        
        System.out.println("JUDO_ENV_BOOLEAN1: "  + System.getenv("JUDO_ENV_BOOLEAN1"));
        System.out.println("JUDO_ENV_BOOLEAN2: "  + System.getenv("JUDO_ENV_BOOLEAN2"));
        System.out.println("JUDO_ENV_DATE1: "     + System.getenv("JUDO_ENV_DATE1"));
        System.out.println("JUDO_ENV_DATE2: "     + System.getenv("JUDO_ENV_DATE2"));
        System.out.println("JUDO_ENV_TIME1: "     + System.getenv("JUDO_ENV_TIME1"));
        System.out.println("JUDO_ENV_TIME2: "     + System.getenv("JUDO_ENV_TIME2"));
        System.out.println("JUDO_ENV_TIMESTAMP1: "+ System.getenv("JUDO_ENV_TIMESTAMP1"));
        System.out.println("JUDO_ENV_TIMESTAMP2: "+ System.getenv("JUDO_ENV_TIMESTAMP2"));
        System.out.println("JUDO_ENV_INTEGER1: "  + System.getenv("JUDO_ENV_INTEGER1"));
        System.out.println("JUDO_ENV_INTEGER2: "  + System.getenv("JUDO_ENV_INTEGER2"));
        System.out.println("JUDO_ENV_LONG1: "     + System.getenv("JUDO_ENV_LONG1"));
        System.out.println("JUDO_ENV_LONG2: "     + System.getenv("JUDO_ENV_LONG2"));
        System.out.println("JUDO_ENV_STRING1: "   + System.getenv("JUDO_ENV_STRING1"));
        System.out.println("JUDO_ENV_STRING2: "   + System.getenv("JUDO_ENV_STRING2"));
        
        // Create an EnvVars entity instance (ev1) with the default values.
        EnvVars ev1 = envVarsDao.create(
                EnvVars
                .builder()
                .build()
        );
        
        assertTrue(ev1.getFbool().orElseThrow());
        assertEquals(LocalDate.of(2023, 02, 13), ev1.getFdate().orElseThrow());
        assertEquals(LocalTime.of(0, 0, 01), ev1.getFtime().orElseThrow());
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
            ev1.getFtimestamp().orElseThrow()
        );
        assertEquals(987654321, ev1.getFint().orElseThrow());
        assertEquals(987654321098765L, ev1.getFlong().orElseThrow());
        assertEquals(
            "'Lorem ipsum dolor set...' And a number: 1987",
            ev1.getFstring().orElseThrow()
        );
    }
}
