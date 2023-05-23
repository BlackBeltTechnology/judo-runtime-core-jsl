package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithdefaultparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithdefaultparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithdefaultparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithdefaultparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithdefaultparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithdefaultparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.enumwithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithdefaultparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithoutparamquery.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entityqueryelement.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.StaticQueryDaoTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestStaticQueryDao extends AbstractJslTest {

    @Inject
    EntityWithPrimitivesDao entityWithPrimitivesDao;

    @Inject
    IntegerWithoutParamQueryDao integerWithoutParamQueryDao;

    @Inject
    IntegerWithParamQueryDao integerWithParamQueryDao;

    @Inject
    IntegerWithDefaultParamQueryDao integerWithDefaultParamQueryDao;

    @Inject
    ScaledWithoutParamQueryDao scaledWithoutParamQueryDao;

    @Inject
    ScaledWithParamQueryDao scaledWithParamQueryDao;

    @Inject
    ScaledWithDefaultParamQueryDao scaledWithDefaultParamQueryDao;

    @Inject
    StringWithoutParamQueryDao stringWithoutParamQueryDao;

    @Inject
    StringWithParamQueryDao stringWithParamQueryDao;

    @Inject
    StringWithDefaultParamQueryDao stringWithDefaultParamQueryDao;

    @Inject
    BooleanWithoutParamQueryDao booleanWithoutParamQueryDao;

    @Inject
    BooleanWithParamQueryDao booleanWithParamQueryDao;

    @Inject
    BooleanWithDefaultParamQueryDao booleanWithDefaultParamQueryDao;

    @Inject
    DateWithoutParamQueryDao dateWithoutParamQueryDao;

    @Inject
    DateWithParamQueryDao dateWithParamQueryDao;

    @Inject
    DateWithDefaultParamQueryDao dateWithDefaultParamQueryDao;

    @Inject
    TimeWithoutParamQueryDao timeWithoutParamQueryDao;

    @Inject
    TimeWithParamQueryDao timeWithParamQueryDao;

    @Inject
    TimeWithDefaultParamQueryDao timeWithDefaultParamQueryDao;

    @Inject
    EnumWithoutParamQueryDao enumWithoutParamQueryDao;

    @Inject
    EntityWithoutParamQueryDao entityWithoutParamQueryDao;

    @Inject
    EntityWithParamQueryDao entityWithParamQueryDao;

    @Inject
    EntityWithDefaultParamQueryDao entityWithDefaultParamQueryDao;

    @Inject
    EntityQueryElementDao entityQueryElementDao;


    @Override
    public Module getModelDaoModule() {
        return new StaticQueryDaoTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "StaticQueryDaoTest";
    }

    /**
     *
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("")
    @Requirement(reqs = {

    })
    void testPrimitiveQueries() {

        EntityWithPrimitives entityWithPrimitives = entityWithPrimitivesDao.create(EntityWithPrimitives.builder().build());

        //Integer query
        assertEquals(1, integerWithoutParamQueryDao.execute());
        assertEquals(2, integerWithParamQueryDao.execute(IntegerWithParamQueryParameter.builder().withNum(1).build()));
        assertEquals(2, integerWithDefaultParamQueryDao.execute(IntegerWithDefaultParamQueryParameter.builder().build()));

        //Scaled query
        assertEquals(2.34, scaledWithoutParamQueryDao.execute());
        assertEquals(3.34, scaledWithParamQueryDao.execute(ScaledWithParamQueryParameter.builder().withNum(1.0).build()));
        assertEquals(4.68, scaledWithDefaultParamQueryDao.execute(ScaledWithDefaultParamQueryParameter.builder().build()));

        //String query
        assertEquals("test", stringWithoutParamQueryDao.execute());
        assertEquals("testhello", stringWithParamQueryDao.execute(StringWithParamQueryParameter.builder().withString("hello").build()));
        assertEquals("testhello", stringWithDefaultParamQueryDao.execute(StringWithDefaultParamQueryParameter.builder().build()));

        //Boolean query
        assertEquals(true, booleanWithoutParamQueryDao.execute());
        assertEquals(false, booleanWithParamQueryDao.execute(BooleanWithParamQueryParameter.builder().withBool(false).build()));
        assertEquals(true, booleanWithDefaultParamQueryDao.execute(BooleanWithDefaultParamQueryParameter.builder().build()));

        //Date query
        assertEquals(LocalDate.of(2022, 7, 11), dateWithoutParamQueryDao.execute());
        assertEquals(
                LocalDate.of(2023, 4, 3),
                dateWithParamQueryDao.execute(DateWithParamQueryParameter.builder().withDateYear(2023).withDateMonth(4).withDateDay(3).build())
        );
        assertEquals(
                LocalDate.of(2023, 3, 3),
                dateWithDefaultParamQueryDao.execute(DateWithDefaultParamQueryParameter.builder().build())
        );

        //Time
        assertEquals(LocalTime.of(22, 59, 59), timeWithoutParamQueryDao.execute());
        assertEquals(
                LocalTime.of(12, 24, 45),
                timeWithParamQueryDao.execute(TimeWithParamQueryParameter.builder().withTimeHour(12).withTimeMinute(24).withTimeSecond(45).build())
        );
        assertEquals(
                LocalTime.of(13, 4, 5),
                timeWithDefaultParamQueryDao.execute(TimeWithDefaultParamQueryParameter.builder().build())
        );
        //Enum
        //TODO The query throw error when the execution happend
        //assertEquals(MyEnum.Bombastic,enumWithoutParamQueryDao.execute());

        entityWithPrimitivesDao.delete(entityWithPrimitives);

        assertEquals(null, integerWithoutParamQueryDao.execute());

    }

    /**
     *
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("")
    @Requirement(reqs = {

    })
    void testEntitySingleQueries() {

        EntityQueryElement a = entityQueryElementDao.create(EntityQueryElement.builder().withName("A").withValue(1).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement b = entityQueryElementDao.create(EntityQueryElement.builder().withName("B").withValue(2).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement c = entityQueryElementDao.create(EntityQueryElement.builder().withName("C").withValue(3).withCategory(MyEnum.Crazy).build());
        EntityQueryElement d = entityQueryElementDao.create(EntityQueryElement.builder().withName("D").withValue(4).withCategory(MyEnum.Atomic).build());

        //TODO Should be not a list because it can be only one element.
        assertEquals(List.of(a.identifier()),entityWithoutParamQueryDao.query().execute().stream().map(e -> e.identifier()).collect(Collectors.toList()));

    }

    /**
     *
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     */
    @Test
    @TestCase("")
    @Requirement(reqs = {

    })
    void testEntityCollectionQueries() {



    }


}
