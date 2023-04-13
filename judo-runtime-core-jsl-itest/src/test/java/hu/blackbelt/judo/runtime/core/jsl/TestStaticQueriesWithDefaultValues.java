package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithdefaultvalues.teststaticquerieswithdefaultvalues.myentity.MyEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithdefaultvalues.teststaticquerieswithdefaultvalues.myentity.MyEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithdefaultvalues.teststaticquerieswithdefaultvalues.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithdefaultvalues.teststaticquerieswithdefaultvalues.snapshot1.Snapshot1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithdefaultvalues.teststaticquerieswithdefaultvalues.snapshot1.Snapshot1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestStaticQueriesWithDefaultValuesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TestStaticQueriesWithDefaultValues extends AbstractJslTest {

    @Inject
    MyEntityDao myEntityDao;

    @Inject
    Snapshot1Dao snapshot1Dao;

    @Override
    public Module getModelDaoModule() {
        return new TestStaticQueriesWithDefaultValuesDaoModules();
    }

    @Override
    public String getModelName() {
        return "TestStaticQueriesWithDefaultValues";
    }

    /**
     * Testing the static queries with default values that are defined by constants or expressions.
     * This queries are called without parameters and constant parameters.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements
     *  Write here the requirement identifiers that are positively checked by this test case.
     *  The identifiers must be separated by commas (,).
     *
     * @negativeRequirements
     *  Write here the requirement identifiers that are negatively checked by this test case.
     *  The identifiers must be separated by commas (,).
     *
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create and save a MyEntity instance without any field values. (*e1*)
     *
     *  . Create and save a MyEntity instance with the following field values. (*e2*)
     *  (for Snapshot1.fldMyEntity001 and setOfMyEntities)
     *    * fldBool = true
     *    * fldDate = `1900-01-01`
     *    * fldTime = `12:00:13`
     *    * fldTimestamp = `2020-01-01T01:11:13-12:00`
     *    * fldLong = 1234567890
     *    * fldString = "AAA"
     *    * fldDecimal = -1.2302
     *    * fldEnum = MyEnum#A03
     *
     *  . Create and save a MyEntity instance with the following field values. (*e3*)
     *  (for Snapshot1.fldMyEntity002, Snapshot1.fldMyEntity003, Snapshot1.fldMyEntity004)
     *    * fldBool = false
     *    * fldDate = Timestamp!now()!date()
     *    * fldTime = `15:15:01`
     *    * fldTimestamp = `2020-01-01T01:11:13+01:00`
     *    * fldLong = 12345678999
     *    * fldString = "AAA"
     *    * fldDecimal = -2.2302
     *    * fldEnum = MyEnum#A02
     *
     *  . Create and save a MyEntity instance with the following field values. (*e4*)
     *  (for Snapshot1.fldMyEntity003, fldMyEntity005)
     *    * fldBool = true
     *    * fldDate = `2023-01-01`
     *    * fldTime = `15:20`
     *    * fldTimestamp = `2020-01-01T01:11:13+01:00`
     *    * fldLong = -16
     *    * fldString = "Lorem ipsum"
     *    * fldDecimal = 999999999.9999
     *    * fldEnum = MyEnum#A01
     *
     *  . Create and save a Snapshot1 instance without any field values. (*s1*)
     *
     *  . Check the value of the fields of the created *s1* instance. All of the following boolean expressions must be true.
     *    * s1.fldMyEntity001 == e2
     *    * s1.fldMyEntity002 == e3
     *    * s1.fldMyEntity003 == e3 or s1.fldMyEntity003 == e4
     *    * s1.fldMyEntity004 == e3
     *    * s1.fldMyEntity005 == e4
     *    * s1.fldMyEntity006 == e2 or s1.fldMyEntity006 == e3 or s1.fldMyEntity006 == e4
     *    * s1.defEntities   == [e2]
     *    * s1.otherEntities == [e4]
     *
     *  . The test is passed if all steps have been completed with the specified results.
     *
     */
    @Test
    @TestCase("TC017")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-SYNT-005",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
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
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-009",
            "REQ-ENT-011",
            "REQ-EXPR-002",
            "REQ-EXPR-004",
            "REQ-EXPR-005",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-008",
            "REQ-EXPR-012",
            "REQ-EXPR-022"
    })
    void testStaticQueriesWithDefaultValuesByConstantsOrExpressions() {
        MyEntity e1 = myEntityDao.create(MyEntity.builder().build());
        MyEntity e2 = myEntityDao.create(MyEntity.builder()
                .withFldBool(true)
                .withFldDate(LocalDate.parse("1900-01-01"))
                .withFldTime(LocalTime.parse("12:00:13"))
                .withFldTimestamp(OffsetDateTime.parse("2020-01-01T01:11:13-12:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFldLong(1234567890L)
                .withFldString("AAA")
                .withFldDecimal(-1.2302)
                .withFldEnum(MyEnum.A03)
                .build());

        MyEntity e3 = myEntityDao.create(MyEntity.builder()
                .withFldBool(false)
                .withFldDate(LocalDate.now())
                .withFldTime(LocalTime.parse("15:15:01"))
                .withFldTimestamp(OffsetDateTime.parse("2020-01-01T01:11:13+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFldLong(12345678999L)
                .withFldString("AAA")
                .withFldDecimal(-2.2302)
                .withFldEnum(MyEnum.A02)
                .build());

        MyEntity e4 = myEntityDao.create(MyEntity.builder()
                .withFldBool(true)
                .withFldDate(LocalDate.parse("2023-01-01"))
                .withFldTime(LocalTime.parse("15:20"))
                .withFldTimestamp(OffsetDateTime.parse("2020-01-01T01:11:13+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFldLong(-16L)
                .withFldString("Lorem ipsum")
                .withFldDecimal(999999999.9999)
                .withFldEnum(MyEnum.A01)
                .build());

        
        Snapshot1 s1 = snapshot1Dao.create(Snapshot1.builder().build());
        assertEquals(e2.identifier().getIdentifier(), snapshot1Dao.queryFldMyEntity001(s1).orElseThrow().identifier().getIdentifier());
        assertEquals(e3.identifier().getIdentifier(), snapshot1Dao.queryFldMyEntity002(s1).orElseThrow().identifier().getIdentifier());
        Serializable fldMyEntity003ID = snapshot1Dao.queryFldMyEntity003(s1).orElseThrow().identifier().getIdentifier();
        assertTrue(
                fldMyEntity003ID.equals(e3.identifier().getIdentifier())
                    || fldMyEntity003ID.equals(e4.identifier().getIdentifier())
                );
        assertEquals(e3.identifier().getIdentifier(), snapshot1Dao.queryFldMyEntity004(s1).orElseThrow().identifier().getIdentifier());
        assertEquals(e4.identifier().getIdentifier(), snapshot1Dao.queryFldMyEntity005(s1).orElseThrow().identifier().getIdentifier());
        Serializable fldMyEntity006ID = snapshot1Dao.queryFldMyEntity006(s1).orElseThrow().identifier().getIdentifier();
        assertTrue(
                fldMyEntity006ID.equals(e2.identifier().getIdentifier())
                || fldMyEntity006ID.equals(e3.identifier().getIdentifier())
                || fldMyEntity006ID.equals(e4.identifier().getIdentifier())
            );
        List<MyEntity> defEntities = new ArrayList<>(List.of(e2));
        assertEquals(defEntities, snapshot1Dao.queryDefEntities(s1).execute());
        List<MyEntity> otherEntities = new ArrayList<>(List.of(e4));
        assertEquals(otherEntities, snapshot1Dao.queryOtherEntities(s1).execute());

    }

}
