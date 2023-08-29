package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.lastaddedmyentity.LastAddedMyEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.myentity.MyEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.myentity.MyEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.snapshot1.Snapshot1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.snapshot1.Snapshot1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.snapshot2.Snapshot2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.teststaticquerieswithconstantparameters.teststaticquerieswithconstantparameters.snapshot2.Snapshot2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestStaticQueriesWithConstantParametersDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.Serializable;
import java.time.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith({JudoDatasourceByClassExtension.class, JudoRuntimeExtension.class})
public class TestStaticQueriesWithConstantParameters {

    @Inject
    MyEntityDao myEntityDao;

    @Inject
    Snapshot1Dao snapshot1Dao;

    @Inject
    Snapshot2Dao snapshot2Dao;

    @Inject
    LastAddedMyEntityDao lastAddedMyEntityDao;

    public Module getModelDaoModule() {
        return new TestStaticQueriesWithConstantParametersDaoModules();
    }

    static public String getModelName() {
        return "TestStaticQueriesWithConstantParameters";
    }

    @BeforeAll
    static public void prepare(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.prepare(getModelName(), datasource);
    }

    @BeforeEach
    protected void init(JudoRuntimeFixture fixture, JudoDatasourceFixture datasource) throws Exception {
        fixture.init(getModelDaoModule(),this, datasource);
        fixture.beginTransaction();
    }

    @AfterEach
    protected void tearDown(JudoRuntimeFixture fixture) {
        fixture.tearDown();
    }

    /**
     * Testing the working of static queries with constant parameters.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others
     *  Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @scenario
     *  . Parse (and/or build) the model.
     *
     *  . The result of the model parsing (and/or building) is successful.
     *
     *  . Create and save a MyEntity instance without any field values. (*e1*)
     *
     *  . Check the value of the fields of the created *e1* instance. All of the following boolean expressions must be true.
     *    * e1.created!isDefined()
     *    * e1.ffBool!isUndefined()
     *    * e1.ffDate!isUndefined()
     *    * e1.ffTime!isUndefined()
     *    * e1.ffTimestamp!isUndefined()
     *    * e1.ffLong!isUndefined()
     *    * e1.ffString!isUndefined()
     *    * e1.ffDecimal!isUndefined()
     *    * e1.ffEnum!isUndefined()
     *
     *  . Create and save a MyEntity instance with the following field values. (*e2*)
     *    * ffBool = true
     *    * ffDate = `2023-01-01`
     *    * ffTime = `12:00:13`
     *    * ffTimestamp = `2020-01-01T01:11:13-12:00`
     *    * ffLong = 1234567890
     *    * ffString = "AAA"
     *    * ffDecimal = -12.2302
     *    * ffEnum = MyEnum#A02
     *
     *  . Create and save a Snapshot1 instance without any field values. (*s11*)
     *
     *  . Check the value of the fields of the created *s11* instance. All of the following boolean expressions must be true.
     *    * s11.created!isDefined()
     *    * s11.ffBool!isUndefined()
     *    * s11.ffDate!isUndefined()
     *    * s11.ffTime!isUndefined()
     *    * s11.ffTimestamp!isUndefined()
     *    * s11.ffLong!isUndefined()
     *    * s11.ffString!isUndefined()
     *    * s11.ffDecimal!isUndefined()
     *    * s11.ffEnum!isUndefined()
     *
     *  . Create and save a Snapshot2 instance without any field values. (*s21*)
     *
     *  . Check the value of the fields of the created *s21* instance. All of the following boolean expressions must be true.
     *    * s21.created!isDefined()
     *    * s21.ffBool!isUndefined()
     *    * s21.ffDate!isUndefined()
     *    * s21.ffTime!isUndefined()
     *    * s21.ffTimestamp!isUndefined()
     *    * s21.ffLong!isUndefined()
     *    * s21.ffString!isUndefined()
     *    * s21.ffDecimal!isUndefined()
     *    * s21.ffEnum!isUndefined()
     *    * s21.entites!size() == 0
     *
     *  . Create and save a MyEntity instance with the following field values. (*e3*)
     *    * ffBool = true
     *    * ffDate = `2023-01-01`
     *    * ffTime = `08:00:00`
     *    * ffTimestamp = `2020-01-01T12:11:13+01:00`
     *    * ffLong = 9999999999
     *    * ffString = "ABC"
     *    * ffDecimal = -1.9999
     *    * ffEnum = MyEnum#A02
     *
     *  . Create and save a Snapshot1 instance without any field values. (*s12*)
     *
     *  . Check the value of the fields of the created *s12* instance. All of the following boolean expressions must be true.
     *    * s12.created!isDefined()
     *    * s12.ffBool!isDefined()      and s12.fBool == true
     *    * s12.ffDate!isDefined()      and s12.fDate == `2023-01-01`
     *    * s12.ffTime!isDefined()      and s12.fTime == `08:00:00`
     *    * s12.ffTimestamp!isUndefined()
     *    * s12.ffLong!isDefined()      and s12.fLong == 9999999999
     *    * s12.ffString!isUndefined()
     *    * s12.ffDecimal!isUndefined()
     *    * s12.ffEnum!isDefined()      and s12.fEnum == MyEnum#A02
     *
     *  // for fTimestamp and fString
     *  . Create and save a MyEntity instance with the following field values. (*e4*)
     *    * ffBool = true
     *    * ffDate = `2023-01-01`
     *    * ffTime = `12:00:13`
     *    * ffTimestamp = `2020-01-01T14:11:12+01:00`
     *    * ffLong = 1234567890
     *    * ffString = "AAA"
     *    * ffDecimal = 2.7109
     *    * ffEnum = MyEnum#A01
     *
     *  // for fDecimal
     *  . Create and save a MyEntity instance with the following field values. (*e5*)
     *    * ffBool = false
     *    * ffDate = `2023-01-05`
     *    * ffTime = `12:00:13`
     *    * ffTimestamp = `2020-01-01T01:11:13-12:00`
     *    * ffLong = -1234567890
     *    * ffString = "cbaaa"
     *    * ffDecimal = 13.0001
     *    * ffEnum = MyEnum#A03
     *
     *  . Create and save a Snapshot1 instance without any field values. (*s13*)
     *
     *  // Check the fTimestamp, fString and fDecimal
     *  . Check the value of the fields of the created *s13* instance. All of the following boolean expressions must be true.
     *    * s13.ffBool!isDefined()      and s13.fBool == true
     *    * s13.ffDate!isDefined()      and (s13.ffDate == `2023-01-01` or s13.ffDate == `2023-01-05`)
     *    * s13.ffTime!isDefined()      and s13.ffTime == `08:00:00`
     *    * s13.ffTimestamp!isDefined() and s13.fTimstamp == `2020-01-01T14:11:12+01:00`
     *    * s13.ffLong!isDefined()      and s13.fLong == 9999999999
     *    * s13.ffString!isDefined()    and s13.fString == "AAA"
     *    * s13.ffDecimal!isDefined()   and s13.fDecimal == 13.0001
     *    * s13.ffEnum!isDefined()      and s13.fEnum == MyEnum#A02
     *
     *  . Retrieve the s21 Snapshot2 instance.
     *
     *  . All of the following boolean expressions must be true.
     *    * s21.ffBool!isDefined()      and s21.fBool == true
     *    * s21.ffDate!isDefined()      and (s21.ffDate == `2023-01-01` or s21.ffDate == `2023-01-05`)
     *    * s21.ffTime!isDefined()      and s21.ffTime == `08:00:00`
     *    * s21.ffTimestamp!isDefined() and s21.fTimstamp == `2020-01-01T14:11:12+01:00`
     *    * s21.ffLong!isDefined()      and s21.fLong == 9999999999
     *    * s21.ffString!isDefined()    and s21.fString == "AAA"
     *    * s21.ffDecimal!isDefined()   and s21.fDecimal == 13.0001
     *    * s21.ffEnum!isDefined()      and s21.fEnum == MyEnum#A02
     *    * s21.entities!contains(e1) == false
     *    * s21.entities!contains(e2) == false
     *    * s21.entities!contains(e3) == true
     *    * s21.entities!contains(e4) == false
     *    * s21.entities!contains(e5) == true
     *
     *  . Retrieve the s12 entity from the database again.
     *
     *  . Check the value of the fields of the retrieved *s12* instance. All of the following boolean expressions must be true.
     *    * s12.created!isDefined()
     *    * s12.ffBool!isDefined()      and s13.fBool == true
     *    * s12.ffDate!isDefined()      and s13.fDate == `2023-01-01`
     *    * s12.ffTime!isDefined()      and s13.fTime == `08:00:00`
     *    * s12.ffTimestamp!isUndefined()
     *    * s12.ffLong!isDefined()      and s13.fLong == 9999999999
     *    * s12.ffString!isUndefined()
     *    * s12.ffDecimal!isUndefined()
     *    * s12.ffEnum!isDefined()      and s13.fEnum == MyEnum#A02
     *
     *  . Run the lastAddedMyEntity() query. The return value of the query is the e1 MyEntity instance.
     *
     *  . The test is passed if all steps have been completed with the specified results.
     *
     */
    @Test
    @TestCase("TC015")
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
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-011",
            "REQ-EXPR-002",
            "REQ-EXPR-004",
            "REQ-EXPR-005",
            "REQ-EXPR-006",
            "REQ-EXPR-007",
            "REQ-EXPR-008",
            "REQ-EXPR-010",
            "REQ-EXPR-012",
            "REQ-EXPR-022"
    })
    void testStaticQueryWithConstantParameters() {
        MyEntity e1 = myEntityDao.create(MyEntity.builder().build());
        assertTrue(e1.getFfCreated().isPresent());
        assertTrue(LocalDateTime.now().minusSeconds(2).isBefore(e1.getFfCreated().orElseThrow()));
        assertTrue(e1.getFfBool().isEmpty());
        assertTrue(e1.getFfDate().isEmpty());
        assertTrue(e1.getFfTime().isEmpty());
        assertTrue(e1.getFfTimestamp().isEmpty());
        assertTrue(e1.getFfLong().isEmpty());
        assertTrue(e1.getFfString().isEmpty());
        assertTrue(e1.getFfDecimal().isEmpty());
        assertTrue(e1.getFfEnum().isEmpty());

        MyEntity e2 = myEntityDao.create(MyEntity.builder()
                .withFfBool(true)
                .withFfDate(LocalDate.parse("2023-01-01"))
                .withFfTime(LocalTime.parse("12:00:13"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T01:11:13-12:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(1234567890L)
                .withFfString("AAA")
                .withFfDecimal(-12.2302)
                .withFfEnum(MyEnum.A02)
                .build());

        Snapshot1 s11 = snapshot1Dao.create(Snapshot1.builder().build());

        assertTrue(s11.getCreated().isPresent());
        assertTrue(LocalDateTime.now().minusSeconds(2).isBefore(s11.getCreated().orElseThrow()));
        assertTrue(s11.getFfBool().isEmpty());
        assertTrue(s11.getFfDate().isEmpty());
        assertTrue(s11.getFfTime().isEmpty());
        assertTrue(s11.getFfTimestamp().isEmpty());
        assertTrue(s11.getFfLong().isEmpty());
        assertTrue(s11.getFfString().isEmpty());
        assertTrue(s11.getFfDecimal().isEmpty());
        assertTrue(s11.getFfEnum().isEmpty());

        Snapshot2 s21 = snapshot2Dao.create(Snapshot2.builder().build());

        assertTrue(s21.getCreated().isPresent());
        assertTrue(LocalDateTime.now().minusSeconds(2).isBefore(s21.getCreated().orElseThrow()));
        assertTrue(s21.getFfBool().isEmpty());
        assertTrue(s21.getFfDate().isEmpty());
        assertTrue(s21.getFfTime().isEmpty());
        assertTrue(s21.getFfTimestamp().isEmpty());
        assertTrue(s21.getFfLong().isEmpty());
        assertTrue(s21.getFfString().isEmpty());
        assertTrue(s21.getFfDecimal().isEmpty());
        assertTrue(s21.getFfEnum().isEmpty());
        assertEquals(0, snapshot2Dao.countEntities(s21));

        MyEntity e3 = myEntityDao.create(MyEntity.builder()
                .withFfBool(true)
                .withFfDate(LocalDate.parse("2023-01-01"))
                .withFfTime(LocalTime.parse("08:00:00"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T12:11:13+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(9999999999L)
                .withFfString("ABC")
                .withFfDecimal(-1.9999)
                .withFfEnum(MyEnum.A02)
                .build());

        Snapshot1 s12 = snapshot1Dao.create(Snapshot1.builder().build());

        assertTrue(s12.getCreated().isPresent());
        assertTrue(LocalDateTime.now().minusSeconds(2).isBefore(s12.getCreated().orElseThrow()));
        assertTrue(s12.getFfBool().isPresent());
        assertTrue(s12.getFfBool().orElseThrow());
        assertTrue(s12.getFfDate().isPresent());
        assertEquals(LocalDate.parse("2023-01-01"), s12.getFfDate().orElseThrow());
        assertTrue(s12.getFfTime().isPresent());
        assertEquals(LocalTime.parse("08:00:00"), s12.getFfTime().orElseThrow());
        assertTrue(s12.getFfTimestamp().isEmpty());
        assertTrue(s12.getFfLong().isPresent());
        assertEquals(9999999999L, s12.getFfLong().orElseThrow());
        assertTrue(s12.getFfString().isEmpty());
        assertTrue(s12.getFfDecimal().isEmpty());
        assertTrue(s12.getFfEnum().isPresent());
        assertEquals(MyEnum.A02, s12.getFfEnum().orElseThrow());

        MyEntity e4 = myEntityDao.create(MyEntity.builder()
                .withFfBool(true)
                .withFfDate(LocalDate.parse("2023-01-01"))
                .withFfTime(LocalTime.parse("12:00:13"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T14:11:12+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(1234567890L)
                .withFfString("AAA")
                .withFfDecimal(2.7109)
                .withFfEnum(MyEnum.A01)
                .build());

        MyEntity e5 = myEntityDao.create(MyEntity.builder()
                .withFfBool(false)
                .withFfDate(LocalDate.parse("2023-01-05"))
                .withFfTime(LocalTime.parse("12:00:13"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T01:11:13-12:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(-1234567890L)
                .withFfString("cbaaa")
                .withFfDecimal(13.0001)
                .withFfEnum(MyEnum.A03)
                .build());

        Snapshot1 s13 = snapshot1Dao.create(Snapshot1.builder().build());

        assertTrue(s13.getFfBool().isPresent());
        assertTrue(s13.getFfBool().orElseThrow());
        assertTrue(s13.getFfDate().isPresent());
        LocalDate s13Date = s13.getFfDate().orElseThrow();
        assertTrue(s13Date.equals(LocalDate.parse("2023-01-01")) || s13Date.equals(LocalDate.parse("2023-01-05")));
        assertTrue(s13.getFfTime().isPresent());
        assertEquals(LocalTime.parse("08:00:00"), s13.getFfTime().orElseThrow());
        assertTrue(s13.getFfTimestamp().isPresent());
        assertEquals(OffsetDateTime.parse("2020-01-01T14:11:12+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime(), s13.getFfTimestamp().orElseThrow());
        assertTrue(s13.getFfLong().isPresent());
        assertEquals(9999999999L, s13.getFfLong().orElseThrow());
        assertTrue(s13.getFfString().isPresent());
        assertEquals("AAA", s13.getFfString().orElseThrow());
        assertTrue(s13.getFfDecimal().isPresent());
        assertEquals(13.0001, s13.getFfDecimal().orElseThrow());
        assertTrue(s13.getFfEnum().isPresent());
        assertEquals(MyEnum.A02, s13.getFfEnum().orElseThrow());

        s21 = snapshot2Dao.getById(s21.identifier()).orElseThrow();
        List<Serializable> s21Ids = snapshot2Dao.queryEntities(s21).execute().stream().map(e -> e.identifier().getIdentifier()).collect(Collectors.toList());
        assertEquals(2, s21Ids.size());
        Set<Serializable> s21IdSet = new HashSet<>(s21Ids);

        assertTrue(s21.getFfBool().isPresent());
        assertTrue(s12.getFfBool().orElseThrow());
        assertTrue(s21.getFfDate().isPresent());
        LocalDate s21Date = s21.getFfDate().orElseThrow();
        assertTrue(s21Date.equals(LocalDate.parse("2023-01-01")) || s21Date.equals(LocalDate.parse("2023-01-05")));
        assertTrue(s21.getFfTime().isPresent());
        assertEquals(LocalTime.parse("08:00:00"), s21.getFfTime().orElseThrow());
        assertTrue(s21.getFfTimestamp().isPresent());
        assertEquals(OffsetDateTime.parse("2020-01-01T14:11:12+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime(), s21.getFfTimestamp().orElseThrow());
        assertTrue(s21.getFfLong().isPresent());
        assertEquals(9999999999L, s21.getFfLong().orElseThrow());
        assertTrue(s21.getFfString().isPresent());
        assertEquals("AAA", s21.getFfString().orElseThrow());
        assertTrue(s21.getFfDecimal().isPresent());
        assertEquals(13.0001, s21.getFfDecimal().orElseThrow());
        assertTrue(s21.getFfEnum().isPresent());
        assertEquals(MyEnum.A02, s21.getFfEnum().orElseThrow());
        assertEquals(Set.of(e3.identifier().getIdentifier(), e5.identifier().getIdentifier()), s21IdSet);


        Optional<Snapshot1> s12FromDatabseOpt = snapshot1Dao.getById(s12.identifier());
        assertTrue(s12FromDatabseOpt.isPresent());
        Snapshot1 s12FromDatabse = s12FromDatabseOpt.get();
        Serializable lastAddedEntity = lastAddedMyEntityDao.execute().map(e -> e.identifier().getIdentifier()).orElseThrow();

        assertTrue(s12FromDatabse.getCreated().isPresent());
        assertTrue(LocalDateTime.now().minusSeconds(2).isBefore(s12FromDatabse.getCreated().orElseThrow()));
        assertTrue(s12FromDatabse.getFfBool().isPresent());
        assertTrue(s12FromDatabse.getFfBool().orElseThrow());
        assertTrue(s12FromDatabse.getFfDate().isPresent());
        assertEquals(LocalDate.parse("2023-01-01"), s12FromDatabse.getFfDate().orElseThrow());
        assertTrue(s12FromDatabse.getFfTime().isPresent());
        assertEquals(LocalTime.parse("08:00:00"), s12FromDatabse.getFfTime().orElseThrow());
        assertTrue(s12FromDatabse.getFfTimestamp().isEmpty());
        assertTrue(s12FromDatabse.getFfLong().isPresent());
        assertEquals(9999999999L, s12FromDatabse.getFfLong().orElseThrow());
        assertTrue(s12FromDatabse.getFfString().isEmpty());
        assertTrue(s12FromDatabse.getFfDecimal().isEmpty());
        assertTrue(s12FromDatabse.getFfEnum().isPresent());
        assertEquals(MyEnum.A02, s12FromDatabse.getFfEnum().orElseThrow());

        assertEquals(e5.identifier().getIdentifier(), lastAddedEntity);
    }
}
