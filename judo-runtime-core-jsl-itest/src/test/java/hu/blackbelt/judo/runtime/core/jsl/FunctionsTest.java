package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.functions.guice.functions.FunctionsDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.functions.sdk.functions.functions.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class FunctionsTest {
    Injector injector;

    @Inject
    Entity.EntityDao entityDao;

    @Inject
    EntityWithPrimitiveDefaults.EntityWithPrimitiveDefaultsDao entityWithPrimitiveDefaultsDao;

    @Inject
    AnyTypeFunctions.AnyTypeFunctionsDao anyTypeFunctionsDao;

    AnyTypeFunctions anyTypeFunctions;

    @Inject
    StringFunctions.StringFunctionsDao stringFunctionsDao;

    @Inject
    NumericFunctions.NumericFunctionsDao numericFunctionsDao;

    @Inject
    BooleanFunctions.BooleanFunctionsDao booleanFunctionsDao;

    @Inject
    DateFunctions.DateFunctionsDao dateFunctionsDao;

    @Inject
    TimeFunctions.TimeFunctionsDao timeFunctionsDao;

    @Inject
    TimestampFunctions.TimestampFunctionsDao timestampFunctionsDao;

    @Inject
    EnumFunctions.EnumFunctionsDao enumFunctionsDao;

    @Inject
    InstanceFunctions.InstanceFunctionsDao instanceFunctionsDao;

    @Inject
    Parent.ParentDao parentDao;

    @Inject
    Child.ChildDao childDao;

    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("Functions", FunctionsTest.class.getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new FunctionsDaoModules(),
                new JudoDefaultModule(this, modelHolder));

        Entity entity = entityDao
                        .create(Entity.builder().build());
        EntityWithPrimitiveDefaults entityWithPrimitiveDefaults = entityWithPrimitiveDefaultsDao
                        .create(EntityWithPrimitiveDefaults.builder().build());

        anyTypeFunctions = anyTypeFunctionsDao.create(AnyTypeFunctions.builder()
                        .withEntity(entity)
                        .withEntityWithPrimitives(entityWithPrimitiveDefaults)
                        .build());
    }

    @Test
    public void testAnyType() {
        assertEquals(Optional.of(false), anyTypeFunctions.getIntegerIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getIntegerIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getIntegerIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getIntegerIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getScaledIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getScaledIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getScaledIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getScaledIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getStringIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getStringIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getStringIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getStringIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getBoolIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getBoolIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getBoolIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getBoolIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getDateIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getDateIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getDateIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getDateIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getTimestampIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimestampIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimestampIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getTimestampIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getTimeIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimeIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimeIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getTimeIsUnDefinedFalse());
    }

    @Test
    public void testStrings() {
        StringFunctions str = stringFunctionsDao.create(StringFunctions.builder().build());

        assertEquals(Optional.of(5), str.getLength());
        assertEquals(Optional.of("apple"), str.getTrim());
    }

    @Test
    public void testNumerics() {
        NumericFunctions num = numericFunctionsDao.create(NumericFunctions.builder().build());

        // TODO: JNG-3898 add tests for round() once bugs are fixed
        // TODO: JNG-3797 add tests for floor(), ceil(), abs() once bugs are fixed

        assertEquals(Optional.of("1"), num.getIntAsString());
        assertEquals(Optional.of("123456.789"), num.getScaledAsString());
    }

    @Test
    public void testBooleans() {
        BooleanFunctions bool = booleanFunctionsDao.create(BooleanFunctions.builder().build());

        assertEquals(Optional.of("TRUE"), bool.getOwnBoolAsString());
        assertEquals(Optional.of("TRUE"), bool.getTrueAsString());
        assertEquals(Optional.of("FALSE"), bool.getFalseAsString());
    }

    @Test
    public void testDates() {
        DateFunctions date = dateFunctionsDao.create(DateFunctions.builder().build());

        assertEquals(Optional.of("2022-07-11"), date.getOwnDateAsString());
        assertEquals(Optional.of("2021-03-02"), date.getDateAsString());
        assertEquals(Optional.of(2021), date.getYear());
        assertEquals(Optional.of(3), date.getMonth());
        assertEquals(Optional.of(2), date.getDay());
        // TODO: add missing test after JNG-3899 is fixed
    }

    @Test
    public void testTime() {
        TimeFunctions time = timeFunctionsDao.create(TimeFunctions.builder().build());

        // assertEquals(Optional.of("23:15:59"), time.getOwnTimeAsString()); FIXME: JNG-3900
        // assertEquals(Optional.of("23:15:59"), time.getTimeAsString()); FIXME: JNG-3900
        assertEquals(Optional.of(23), time.getHour());
        assertEquals(Optional.of(15), time.getMinute());
        assertEquals(Optional.of(59), time.getSecond());
        // TODO: add missing test after JNG-3901 is fixed
    }

    @Test
    public void testTimestamp() {
        TimestampFunctions timestamp = timestampFunctionsDao.create(TimestampFunctions.builder().build());

        // assertEquals(Optional.of("2019-07-18T01:11:12+02:00"), timestamp.getOwnTimestamp1AsString()); FIXME: JNG-3902
        // assertEquals(Optional.of("2019-07-18T01:11:12Z"), timestamp.getOwnTimestamp2AsString()); FIXME: JNG-3902
        // TODO: add missing test after JNG-3902 is fixed
        // TODO: add missing test after JNG-3903 is fixed
    }

    @Test
    public void testEnum() {
        EnumFunctions myEnum = enumFunctionsDao.create(EnumFunctions.builder().build());

        // assertEquals(Optional.of("Atomic"), myEnum.getOwnEnumAsString()); FIXME: JNG-3905
        // TODO: add missing test after JNG-3904 is fixed
    }

    @Test
    public void testInstance() {
//        Parent parent1 = parentDao.create(Parent.builder().withName("James Webb").build());
//        Child child1 = childDao.create(Child.builder().withName("Erika Young").withAge(11).build());
//        InstanceFunctions instanceFunctions = instanceFunctionsDao.create(InstanceFunctions.builder()
//                        .withParent(Parent.builder().withName("Another Person").build())
//                        .withChild(Child.builder().withName("Another Child").withAge(31).build())
//                        .build());


    }
}
