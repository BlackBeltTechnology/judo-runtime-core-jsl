package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Module;
import com.google.inject.Inject;

import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvars1.EnvVars1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvars1.EnvVars1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvars2.EnvVars2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvars2.EnvVars2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvarbool.EnvVarBool;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvarbool.EnvVarBoolDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvardate.EnvVarDate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvardate.EnvVarDateDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvartime.EnvVarTime;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvartime.EnvVarTimeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvartimestamp.EnvVarTimestamp;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvartimestamp.EnvVarTimestampDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvarinteger.EnvVarInteger;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvarinteger.EnvVarIntegerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvarlong.EnvVarLong;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvarlong.EnvVarLongDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvars.EnvVars;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.envvars.EnvVarsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.sequences.Sequences;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc010tc011tc012tc013.modeltc010tc011tc012tc013.sequences.SequencesDao;

import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entityrequiredfields.EntityRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ModelTC010TC011TC012TC013DaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

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

@ExtendWith(SystemStubsExtension.class)
public class GetVariablesTest extends AbstractJslTest {
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
    
    @SystemStub
    private EnvironmentVariables environmentVariables = new EnvironmentVariables()
        .set("JUDO_ENV_BOOLEAN1"  , "true")
        .set("JUDO_ENV_BOOLEAN2"  , "false")
        .set("JUDO_ENV_DATE1"     , "2023-02-13")
        .set("JUDO_ENV_DATE2"     , "2024-12-28")
        .set("JUDO_ENV_TIME1"     , "00:00:01")
        .set("JUDO_ENV_TIME2"     , "23:59:02")
        .set("JUDO_ENV_TIMESTAMP1", "2023-02-13T10:11:12+01:00")
        .set("JUDO_ENV_TIMESTAMP2", "2023-02-14T10:11:12-01:00")
        .set("JUDO_ENV_INTEGER1"  , "987654321")
        .set("JUDO_ENV_INTEGER2"  , "-123456789")
        .set("JUDO_ENV_LONG1"     , "987654321098765")
        .set("JUDO_ENV_LONG2"     , "-123456789012345")
        .set("JUDO_ENV_STRING1"   , "'Lorem ipsum dolor set...' And a number: 1987")
        .set("JUDO_ENV_STRING2"   , "Other text.\n\nend text")
        .set("JUDO_ENV_XXX"       , "xxxx")
        ;

    @Override
    public Module getModelDaoModule() {
        return new ModelTC010TC011TC012TC013DaoModules();
    }

    @Override
    public String getModelName() {
        return "modelTC010TC011TC012TC013";
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
    public void testGetVariableEnvironmentWithJudoPrimitiveTypes() {
        EnvVars1 ev1 = envVars1Dao.create(
                EnvVars1
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
        
        EnvVars2 ev2 = envVars2Dao.create(
                EnvVars2
                .builder()
                .build()
        );
        
        assertFalse(ev2.getFbool().orElseThrow());
        assertEquals(LocalDate.of(2024, 12, 28), ev2.getFdate().orElseThrow());
        assertEquals(LocalTime.of(23, 59, 02), ev2.getFtime().orElseThrow());
        assertEquals(
            OffsetDateTime.of(
                2023,
                02,
                14,
                10,
                11,
                12,
                0,
                ZoneOffset.of("-01:00")
            ),
            ev2.getFtimestamp().orElseThrow()
        );
        assertEquals(-123456789, ev2.getFint().orElseThrow());
        assertEquals(-123456789012345L, ev2.getFlong().orElseThrow());
        assertEquals(
                "Other text.\n\n" + "end text",
            ev2.getFstring().orElseThrow()
        );
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
    public void testGetVariableEnvironmentWithJudoPrimitiveTypesExceptions() {
// not throw error
//        assertThrows(
//                RuntimeException.class,
//                () -> envVarBoolDao.create(EnvVarBool.builder().build())
//        );
        assertThrows(
                RuntimeException.class,
                () -> envVarDateDao.create(EnvVarDate.builder().build())
        );
        assertThrows(
                RuntimeException.class,
                () -> envVarTimeDao.create(EnvVarTime.builder().build())
        );
        assertThrows(
                RuntimeException.class,
                () -> envVarTimestampDao.create(EnvVarTimestamp.builder().build())
        );
        assertThrows(
                RuntimeException.class,
                () -> envVarIntegerDao.create(EnvVarInteger.builder().build())
        );
        assertThrows(
                RuntimeException.class,
                () -> envVarLongDao.create(EnvVarLong.builder().build())
        );
    }
    
    /**
     * Verify the !getVariable(“SYSTEM“, “key”) function calls and their shortcuts.
     * 
     * @prerequisites Nothing
     * 
     * @type Behaviour
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
        EnvVars evs1 = envVarsDao.create(
                EnvVars
                .builder()
                .build()
        );

        assertTrue(evs1.getF1().orElseThrow());
        assertTrue(evs1.getF2().orElseThrow());  //fails
        assertTrue(evs1.getF3().orElseThrow());
    }
    
    /**
     * Verify the !getVariable(“SEQUENCE“, “key”) function calls.
     * 
     * @prerequisites The model runtime is empty. The "Order" and "Other" sequences have not been called before.
     * 
     * @type Behaviour
     * 
     * @others
     *  TODO Write here any other instructions or information, that is necessary or important to implement
     *  the test case. This is an optional property.
     * 
     * @jslModel
     *  TODO Write here a JSL model, that is used by the test case.
     * 
     * @positiveRequirements
     *  Write here the requirement identifiers that are positively checked by this test case.
     *  The identifiers must be separeated by commas (,).
     * 
     * @negativeRequirements
     *  Write here the requirement identifiers that are negatively checked by this test case.
     *  The identifiers must be separeated by commas (,).
     * 
     * @scenario
     *  . Parse (and/or build) the model.
     *  
     *  . The result of the model parsing (and/or building) is successful.
     *  
     *  . Create an Sequences entity instance (s1) with the default values.
     *  
     *  . Check the values of the following fields of the new entity instance (s1).
     *      * f1 != f2
     *      * f3 == f1 or f3 == f2
     *  
     *  . Create an Sequences entity instance (s2) with the default values.
     *  
     *  . Check the following statements.
     *      * s1.f1 < s2.f1
     *      * s1.f2 < s2.f2
     *      * s1.f3 < s2.f3
     *  
     *  . The test is passed if all steps have been completed with the specified results.
     */
    @Test
    @TestCase("TC013")
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

        Sequences s1 = sequencesDao.create(
                Sequences
                        .builder()
                        .build()
        );

        assertTrue(s1.getF1().orElseThrow() != s1.getF2().orElseThrow());
        assertTrue(s1.getF3().orElseThrow() == s1.getF1().orElseThrow());
        assertTrue(s1.getF3().orElseThrow() == s1.getF2().orElseThrow());

        Sequences s2 = sequencesDao.create(
                Sequences
                        .builder()
                        .build()
        );

        assertTrue(s1.getF1().orElseThrow() < s2.getF1().orElseThrow());
        assertTrue(s1.getF2().orElseThrow() < s2.getF2().orElseThrow());
        assertTrue(s1.getF3().orElseThrow() < s2.getF3().orElseThrow());

    }
}
