package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.lastaddedmyentity.LastAddedMyEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.myentity.MyEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.myentity.MyEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.snapshot1.Snapshot1;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.snapshot1.Snapshot1Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.snapshot2.Snapshot2;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.modeltc015.modeltc015.snapshot2.Snapshot2Dao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ModelTC015DaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStaticQueriesWithConstantParameters extends AbstractJslTest {

    @Inject
    MyEntityDao myEntityDao;

    @Inject
    Snapshot1Dao snapshot1Dao;

    @Inject
    Snapshot2Dao snapshot2Dao;

    @Inject
    LastAddedMyEntityDao lastAddedMyEntityDao;

    @Override
    public Module getModelDaoModule() {
        return new ModelTC015DaoModules();
    }

    @Override
    public String getModelName() {
        return "modelTC015";
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
     * @jslModel
     *  model modelTC015;
     *
     *  import judo::types;
     *
     *  // my decimal
     *  type numeric Decimal(precision = 13, scale = 4);
     *
     *  // my enum
     *  enum MyEnum {
     *      A01 = 1;
     *      A02 = 2;
     *      A03 = 3;
     *      A00 = 0;
     *  }
     *
     *  // MyEntity
     *  entity MyEntity {
     *      field Timestamp ffCreated = Timestamp!now();
     *      field Boolean   ffBool;
     *      field Date      ffDate;
     *      field Time      ffTime;
     *      field Timestamp ffTimestamp;
     *      field Long      ffLong;
     *      field String    ffString;
     *      field Decimal   ffDecimal;
     *      field MyEnum    ffEnum;
     *  }
     *
     *  // Entity for the results of queries
     *  entity Snapshot1 {
     *      field Timestamp created = Timestamp!now();
     *
     *      // fields
     *      field Boolean   ffBool      = anyMyEntityFBool(str = "ABC");
     *      field Date      ffDate      = anyMyEntityFDate(min = -12.2301, max = 10000.9999);
     *      field Time      ffTime      = anyMyEntityFTime(ll = 1234567890, me = MyEnum#A02);
     *      field Timestamp ffTimestamp = anyMyEntityFTimestamp(str = "AAA", min = 2.71);
     *      field Long      ffLong      = anyMyEntityFLong(d = `2023-01-01`, t = `12:00:13`);
     *      field String    ffString    = anyMyEntityFString(l = 1234567890, ts = `2020-01-01T01:11:12-12:00`);
     *      field Decimal   ffDecimal   = anyMyEntityFDecimal(str = "cbaaa", d = `2023-01-05`, b = false);
     *      field MyEnum    ffEnum      = anyMyEntityFEnum(d = 3.14, t1 = `08:00:00`, t2 = `09:00:00`);
     *  }
     *
     *  // Entity with derived fields for the results of queries
     *  entity Snapshot2 {
     *      field Timestamp created = Timestamp!now();
     *      // derived
     *      derived Boolean   ffBool      => anyMyEntityFBool(str = "ABC");
     *      derived Date      ffDate      => anyMyEntityFDate(min = -12.2301, max = 10000.9999);
     *      derived Time      ffTime      => anyMyEntityFTime(ll = 1234567890, me = MyEnum#A02);
     *      derived Timestamp ffTimestamp => anyMyEntityFTimestamp(str = "AAA", min = 2.71);
     *      derived Long      ffLong      => anyMyEntityFLong(d = `2023-01-01`, t = `12:00:13`);
     *      derived String    ffString    => anyMyEntityFString(l = 1234567890, ts = `2020-01-01T01:11:12-12:00`);
     *      derived Decimal   ffDecimal   => anyMyEntityFDecimal(str = "cbaaa", d = `2023-01-05`, b = false);
     *      derived MyEnum    ffEnum      => anyMyEntityFEnum(d = 3.14, t1 = `08:00:00`, t2 = `09:00:00`);
     *
     *      // relations
     *      derived MyEntity[] entities => listOfMyEntities(l = 5555555555, b = false, d = `2023-01-01`, str = "abc");
     *  }
     *
     *  // static queries
     *  query Boolean    anyMyEntityFBool(String str) =>
     *                      MyEntity!all()!filter(e | e.ffString == str)!any().ffBool;
     *
     *  query Date       anyMyEntityFDate(Decimal min, Decimal max) =>
     *                      MyEntity!all()!filter(e | min <= e.ffDecimal and e.ffDecimal <= max)!any().ffDate;
     *
     *  query Time       anyMyEntityFTime(Long ll, MyEnum me) =>
     *                      MyEntity!all()!filter(e | e.ffLong != ll and e.ffEnum == me)!any().ffTime;
     *
     *  query Timestamp  anyMyEntityFTimestamp(String str, Decimal min) =>
     *                      MyEntity!all()!filter(e | e.ffString == str and min < e.ffDecimal)!any().ffTimestamp;
     *
     *  query Long       anyMyEntityFLong(Date d, Time t) =>
     *                      MyEntity!all()!filter(e | e.ffDate == d and e.ffTime < t)!any().ffLong;
     *
     *  query String     anyMyEntityFString(Timestamp ts, Long l) =>
     *                      MyEntity!all()!filter(e | e.ffTimestamp <= ts and l == e.ffLong)!any().ffString;
     *
     *  query Decimal    anyMyEntityFDecimal(String str, Date d, Boolean b) =>
     *                      MyEntity!all()!filter(e | e.ffString == str and e.ffDate == d and e.ffBool == b)!any().ffDecimal;
     *
     *  query MyEnum     anyMyEntityFEnum(Decimal d, Time t1, Time t2) =>
     *                      MyEntity!all()!filter(e | e.ffDecimal == d or (t1 <= e.ffTime and e.ffTime < t2))!any().ffEnum;
     *
     *  query MyEntity[] listOfMyEntities(String str, Date d, Boolean b, Long l) =>
     *                      MyEntity!all()!filter(e | (e.ffString!lower() == str and e.ffDate == d) or (e.ffBool == b and e.ffLong < l));
     *
     *  query MyEntity   lastAddedMyEntity() =>
     *                      MyEntity!all()!first(e | e.ffCreated)!any();
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
     *    * s13.ffDate!isDefined()
     *    * s13.ffTime!isDefined()
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
     *    * s21.ffDate!isDefined()
     *    * s21.ffTime!isDefined()
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
     *    * s13.created!isDefined()
     *    * s13.ffBool!isDefined()      and s13.fBool == true
     *    * s13.ffDate!isDefined()      and s13.fDate == `2023-01-01`
     *    * s13.ffTime!isDefined()      and s13.fTime == `08:00:00`
     *    * s13.ffTimestamp!isUndefined()
     *    * s13.ffLong!isDefined()      and s13.fLong == 9999999999
     *    * s13.ffString!isUndefined()
     *    * s13.ffDecimal!isUndefined()
     *    * s13.ffEnum!isDefined()      and s13.fEnum == MyEnum#A02
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
            // TODO: JNG-4392 "REQ-EXPR-012",
            "REQ-EXPR-022"
    })
    void testStaticQueryWithConstantParameters() {
        MyEntity e1 = myEntityDao.create(MyEntity.builder().build());
        assertFalse(e1.getFfCreated().equals(Optional.empty()));
        assertTrue(e1.getFfBool().equals(Optional.empty()));
        assertTrue(e1.getFfDate().equals(Optional.empty()));
        assertTrue(e1.getFfTime().equals(Optional.empty()));
        assertTrue(e1.getFfTimestamp().equals(Optional.empty()));
        assertTrue(e1.getFfLong().equals(Optional.empty()));
        assertTrue(e1.getFfString().equals(Optional.empty()));
        assertTrue(e1.getFfDecimal().equals(Optional.empty()));
        assertTrue(e1.getFfEnum().equals(Optional.empty()));

        MyEntity e2 = myEntityDao.create(MyEntity.builder()
                .withFfBool(true)
                .withFfDate(LocalDate.parse("2023-01-01"))
                .withFfTime(LocalTime.parse("12:00:13"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T01:11:13-12:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(Long.valueOf(1234567890))
                .withFfString("AAA")
                .withFfDecimal(-12.2302)
                .withFfEnum(MyEnum.A02)
                .build());

        Snapshot1 s11 = snapshot1Dao.create(Snapshot1.builder().build());

        assertFalse(s11.getCreated().equals(Optional.empty()));
        assertTrue(s11.getFfBool().equals(Optional.empty()));
        assertTrue(s11.getFfDate().equals(Optional.empty()));
        assertTrue(s11.getFfTime().equals(Optional.empty()));
        assertTrue(s11.getFfTimestamp().equals(Optional.empty()));
        assertTrue(s11.getFfLong().equals(Optional.empty()));
        assertTrue(s11.getFfString().equals(Optional.empty()));
        assertTrue(s11.getFfDecimal().equals(Optional.empty()));
        assertTrue(s11.getFfEnum().equals(Optional.empty()));

        Snapshot2 s21 = snapshot2Dao.create(Snapshot2.builder().build());

        assertFalse(s21.getCreated().equals(Optional.empty()));
        assertTrue(s21.getFfBool().equals(Optional.empty()));
        assertTrue(s21.getFfDate().equals(Optional.empty()));
        assertTrue(s21.getFfTime().equals(Optional.empty()));
        assertTrue(s21.getFfTimestamp().equals(Optional.empty()));
        assertTrue(s21.getFfLong().equals(Optional.empty()));
        assertTrue(s21.getFfString().equals(Optional.empty()));
        assertTrue(s21.getFfDecimal().equals(Optional.empty()));
        assertTrue(s21.getFfEnum().equals(Optional.empty()));
        assertTrue(snapshot2Dao.countEntities(s21) == 0);

        MyEntity e3 = myEntityDao.create(MyEntity.builder()
                .withFfBool(true)
                .withFfDate(LocalDate.parse("2023-01-01"))
                .withFfTime(LocalTime.parse("08:00:00"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T12:11:13+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(Long.parseLong("9999999999"))
                .withFfString("ABC")
                .withFfDecimal(-1.9999)
                .withFfEnum(MyEnum.A02)
                .build());

        Snapshot1 s12 = snapshot1Dao.create(Snapshot1.builder().build());

        assertFalse(s12.getCreated().equals(Optional.empty()));
        assertTrue(!s12.getFfBool().equals(Optional.empty()) && s12.getFfBool().equals(Optional.of(true)));
        assertTrue(!s12.getFfDate().equals(Optional.empty()) && s12.getFfDate().equals(Optional.of(LocalDate.parse("2023-01-01"))));
        assertTrue(!s12.getFfTime().equals(Optional.empty()) && s12.getFfTime().equals(Optional.of(LocalTime.parse("08:00:00"))));
        assertTrue(s12.getFfTimestamp().equals(Optional.empty()));
        assertTrue(!s12.getFfLong().equals(Optional.empty()) && s12.getFfLong().equals(Optional.of(Long.parseLong("9999999999"))));
        assertTrue(s12.getFfString().equals(Optional.empty()));
        assertTrue(s12.getFfDecimal().equals(Optional.empty()));
        assertTrue(!s12.getFfEnum().equals(Optional.empty()) && s12.getFfEnum().equals(Optional.of(MyEnum.A02)));

        MyEntity e4 = myEntityDao.create(MyEntity.builder()
                .withFfBool(true)
                .withFfDate(LocalDate.parse("2023-01-01"))
                .withFfTime(LocalTime.parse("12:00:13"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T14:11:12+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(Long.valueOf(1234567890))
                .withFfString("AAA")
                .withFfDecimal(2.7109)
                .withFfEnum(MyEnum.A01)
                .build());

        MyEntity e5 = myEntityDao.create(MyEntity.builder()
                .withFfBool(false)
                .withFfDate(LocalDate.parse("2023-01-05"))
                .withFfTime(LocalTime.parse("12:00:13"))
                .withFfTimestamp(OffsetDateTime.parse("2020-01-01T01:11:13-12:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())
                .withFfLong(Long.valueOf(-1234567890))
                .withFfString("cbaaa")
                .withFfDecimal(13.0001)
                .withFfEnum(MyEnum.A03)
                .build());

        Snapshot1 s13 = snapshot1Dao.create(Snapshot1.builder().build());

        s21 = snapshot2Dao.query().execute().get(0);

        assertTrue(!s21.getFfBool().equals(Optional.empty()) && s21.getFfBool().equals(Optional.of(true)));
        assertFalse(s21.getFfDate().equals(Optional.empty()));
        assertFalse(s21.getFfTime().equals(Optional.empty()));
        assertTrue(!s21.getFfTimestamp().equals(Optional.empty()) &&
                s21.getFfTimestamp().equals(Optional.of(OffsetDateTime.parse("2020-01-01T14:11:12+01:00").atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime())));
        assertTrue(!s21.getFfLong().equals(Optional.empty()) && s21.getFfLong().equals(Optional.of(Long.parseLong("9999999999"))));
        assertTrue(!s21.getFfString().equals(Optional.empty()) && s21.getFfString().equals(Optional.of("AAA")));
        assertTrue(!s21.getFfDecimal().equals(Optional.empty()) && s21.getFfDecimal().equals(Optional.of(13.0001)));
        assertTrue(!s21.getFfEnum().equals(Optional.empty()) && s21.getFfEnum().equals(Optional.of(MyEnum.A02)));
        assertFalse(snapshot2Dao.queryEntities(s21).execute().contains(e1));
        assertFalse(snapshot2Dao.queryEntities(s21).execute().contains(e2));
        assertTrue(snapshot2Dao.queryEntities(s21).execute().contains(e3));
        assertFalse(snapshot2Dao.queryEntities(s21).execute().contains(e4));
        assertTrue(snapshot2Dao.queryEntities(s21).execute().contains(e5));
        

        Optional<Snapshot1> s12FromDatabse = snapshot1Dao.getById(s12.get__identifier());

        assertFalse(s12FromDatabse.orElseThrow().getCreated().equals(Optional.empty()));
        assertTrue(!s12FromDatabse.orElseThrow().getFfBool().equals(Optional.empty()) && s12FromDatabse.orElseThrow().getFfBool().equals(Optional.of(true)));
        assertTrue(!s12FromDatabse.orElseThrow().getFfDate().equals(Optional.empty()) && s12.getFfDate().equals(Optional.of(LocalDate.parse("2023-01-01"))));
        assertTrue(!s12FromDatabse.orElseThrow().getFfTime().equals(Optional.empty()) && s12.getFfTime().equals(Optional.of(LocalTime.parse("08:00:00"))));
        assertTrue(s12FromDatabse.orElseThrow().getFfTimestamp().equals(Optional.empty()));
        assertTrue(!s12FromDatabse.orElseThrow().getFfLong().equals(Optional.empty()) && s12.getFfLong().equals(Optional.of(Long.parseLong("9999999999"))));
        assertTrue(s12FromDatabse.orElseThrow().getFfString().equals(Optional.empty()));
        assertTrue(s12FromDatabse.orElseThrow().getFfDecimal().equals(Optional.empty()));
        assertTrue(!s12FromDatabse.orElseThrow().getFfEnum().equals(Optional.empty()) && s12.getFfEnum().equals(Optional.of(MyEnum.A02)));

        assertTrue(lastAddedMyEntityDao.query().execute().contains(e5));
    }
}
