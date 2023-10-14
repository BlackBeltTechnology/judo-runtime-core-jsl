package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithdefaultparamquery.BooleanWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithdefaultparamquery.BooleanWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithoutparamquery.BooleanWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithparamquery.BooleanWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.booleanwithparamquery.BooleanWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithdefaultparamquery.DateWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithdefaultparamquery.DateWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithoutparamquery.DateWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithparamquery.DateWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.datewithparamquery.DateWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitycollectionwithdefaultparamquery.EntityCollectionWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitycollectionwithdefaultparamquery.EntityCollectionWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitycollectionwithoutparamquery.EntityCollectionWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitycollectionwithparamquery.EntityCollectionWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitycollectionwithparamquery.EntityCollectionWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitycollectionwithvalueparamquery.EntityCollectionWithValueParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitycollectionwithvalueparamquery.EntityCollectionWithValueParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entityqueryelement.EntityQueryElement;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entityqueryelement.EntityQueryElementAttribute;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entityqueryelement.EntityQueryElementDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entityqueryelement.EntityQueryElementMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithdefaultparamquery.EntityWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithoutparamquery.EntityWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithparamquery.EntityWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithparamquery.EntityWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithprimitives.EntityWithPrimitives;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.entitywithprimitives.EntityWithPrimitivesDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.enumwithoutparamquery.EnumWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithdefaultparamquery.IntegerWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithdefaultparamquery.IntegerWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithoutparamquery.IntegerWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithparamquery.IntegerWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.integerwithparamquery.IntegerWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithdefaultparamquery.ScaledWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithdefaultparamquery.ScaledWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithoutparamquery.ScaledWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithparamquery.ScaledWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.scaledwithparamquery.ScaledWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithdefaultparamquery.StringWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithdefaultparamquery.StringWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithoutparamquery.StringWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithparamquery.StringWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.stringwithparamquery.StringWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timestampwithdefaultparamquery.TimestampWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timestampwithdefaultparamquery.TimestampWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timestampwithoutparamquery.TimestampWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timestampwithparamquery.TimestampWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timestampwithparamquery.TimestampWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithdefaultparamquery.TimeWithDefaultParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithdefaultparamquery.TimeWithDefaultParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithoutparamquery.TimeWithoutParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithparamquery.TimeWithParamQueryDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.staticquerydaotest.staticquerydaotest.timewithparamquery.TimeWithParamQueryParameter;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.StaticQueryDaoTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class TestStaticQueryDao {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("StaticQueryDaoTest", new StaticQueryDaoTestDaoModules());

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
    TimestampWithoutParamQueryDao timestampWithoutParamQueryDao;

    @Inject
    TimestampWithParamQueryDao timestampWithParamQueryDao;

    @Inject
    TimestampWithDefaultParamQueryDao timestampWithDefaultParamQueryDao;

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
    EntityCollectionWithoutParamQueryDao entityCollectionWithoutParamQueryDao;

    @Inject
    EntityCollectionWithParamQueryDao entityCollectionWithParamQueryDao;

    @Inject
    EntityCollectionWithValueParamQueryDao entityCollectionWithValueParamQueryDao;

    @Inject
    EntityCollectionWithDefaultParamQueryDao entityCollectionWithDefaultParamQueryDao;

    @Inject
    EntityQueryElementDao entityQueryElementDao;

    /**
     * This test check the static query dao functionality when the return type is a primitive.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements REQ-ENT-011
     *
     * @scenario
     *
     * Create one instance of EntityWithPrimitives.
     *
     * Checks queries are returned with the expected value.
     *
     */
    @Test
    @TestCase("PrimitiveQueriesDao")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-011"

    })
    void testPrimitiveQueriesDao() {

        EntityWithPrimitives entityWithPrimitives = entityWithPrimitivesDao.create(EntityWithPrimitives.builder().build());

        // Integer query
        assertEquals(1, integerWithoutParamQueryDao.execute());
        assertEquals(2, integerWithParamQueryDao.execute(IntegerWithParamQueryParameter.builder().withNum(1).build()));
        assertEquals(2, integerWithDefaultParamQueryDao.execute(IntegerWithDefaultParamQueryParameter.builder().build()));

        // Scaled query
        assertEquals(2.34, scaledWithoutParamQueryDao.execute());
        assertEquals(3.34, scaledWithParamQueryDao.execute(ScaledWithParamQueryParameter.builder().withNum(1.0).build()));
        assertEquals(4.68, scaledWithDefaultParamQueryDao.execute(ScaledWithDefaultParamQueryParameter.builder().build()));

        // String query
        assertEquals("test", stringWithoutParamQueryDao.execute());
        assertEquals("testhello", stringWithParamQueryDao.execute(StringWithParamQueryParameter.builder().withString("hello").build()));
        assertEquals("testhello", stringWithDefaultParamQueryDao.execute(StringWithDefaultParamQueryParameter.builder().build()));

        // Boolean query
        assertEquals(true, booleanWithoutParamQueryDao.execute());
        assertEquals(false, booleanWithParamQueryDao.execute(BooleanWithParamQueryParameter.builder().withBool(false).build()));
        assertEquals(true, booleanWithDefaultParamQueryDao.execute(BooleanWithDefaultParamQueryParameter.builder().build()));

        // Date query
        assertEquals(LocalDate.of(2022, 7, 11), dateWithoutParamQueryDao.execute());
        assertEquals(
                LocalDate.of(2023, 4, 3),
                dateWithParamQueryDao.execute(DateWithParamQueryParameter.builder().withDateYear(2023).withDateMonth(4).withDateDay(3).build())
        );
        assertEquals(
                LocalDate.of(2023, 3, 3),
                dateWithDefaultParamQueryDao.execute(DateWithDefaultParamQueryParameter.builder().build())
        );

        // Time
        assertEquals(LocalTime.of(22, 59, 59), timeWithoutParamQueryDao.execute());
        assertEquals(
                LocalTime.of(12, 24, 45),
                timeWithParamQueryDao.execute(TimeWithParamQueryParameter.builder().withTimeHour(12).withTimeMinute(24).withTimeSecond(45).build())
        );
        assertEquals(
                LocalTime.of(13, 4, 5),
                timeWithDefaultParamQueryDao.execute(TimeWithDefaultParamQueryParameter.builder().build())
        );

        // Timestamp
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), timestampWithoutParamQueryDao.execute());
        assertEquals(
                LocalDateTime.parse("2023-03-03T12:24:45"),
                timestampWithParamQueryDao.execute(TimestampWithParamQueryParameter
                        .builder()
                        .withTimestampDate(LocalDate.of(2023, 3, 3))
                        .withTimestampTime(LocalTime.of(12, 24, 45))
                        .build())
        );
        assertEquals(
                LocalDateTime.parse("2021-02-28T10:30:01"),
                timestampWithDefaultParamQueryDao.execute(TimestampWithDefaultParamQueryParameter.builder().build())
        );

        //Enum
        assertEquals(MyEnum.Bombastic, enumWithoutParamQueryDao.execute());

        entityWithPrimitivesDao.delete(entityWithPrimitives);

        assertNull(integerWithoutParamQueryDao.execute());

    }

    /**
     * This test check the static query dao functionality when the return type is a single Entity.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements REQ-ENT-011
     *
     * @scenario
     *
     * Create one instance of EntityQueryElement.
     *
     * Checks queries are returned with the expected value.
     *
     */
    @Test
    @TestCase("EntitySingleQueriesDao")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-011"
    })
    void testEntitySingleQueriesDao() {

        EntityQueryElement a = entityQueryElementDao.create(EntityQueryElement.builder().withName("A").withValue(1).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement b = entityQueryElementDao.create(EntityQueryElement.builder().withName("B").withValue(2).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement c = entityQueryElementDao.create(EntityQueryElement.builder().withName("C").withValue(3).withCategory(MyEnum.Crazy).build());
        EntityQueryElement d = entityQueryElementDao.create(EntityQueryElement.builder().withName("D").withValue(4).withCategory(MyEnum.Atomic).build());

        assertEquals(a.identifier(), entityWithoutParamQueryDao.execute().map(e -> e.identifier()).orElseThrow());
        assertEquals(
                b.identifier(),
                entityWithParamQueryDao.execute(EntityWithParamQueryParameter
                        .builder()
                        .withName("B")
                        .withValue(2)
                        .withMyenum(MyEnum.Bombastic)
                        .build()).map(e -> e.identifier()).orElseThrow()
        );
        assertEquals(b.identifier(), entityWithDefaultParamQueryDao.execute().map(e -> e.identifier()).orElseThrow());

    }

    /**
     * This test check the static query dao functionality when the return type is a collection of entities.
     *
     * @prerequisites The test must start and finish on the same day. Therefore, don't run this test close to midnight.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @positiveRequirements REQ-ENT-011
     *
     * @scenario
     *
     * Create one instance of EntityQueryElement.
     *
     * Checks queries are returned with the expected value.
     *
     */
    @Test
    @TestCase("EntityCollectionQueriesDao")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-011"
    })
    void testEntityCollectionQueriesDao() {

        EntityQueryElement a = entityQueryElementDao.create(EntityQueryElement.builder().withName("A").withValue(1).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement b1 = entityQueryElementDao.create(EntityQueryElement.builder().withName("B").withValue(2).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement b2 = entityQueryElementDao.create(EntityQueryElement.builder().withName("B").withValue(2).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement c = entityQueryElementDao.create(EntityQueryElement.builder().withName("C").withValue(3).withCategory(MyEnum.Crazy).build());
        EntityQueryElement d = entityQueryElementDao.create(EntityQueryElement.builder().withName("D").withValue(4).withCategory(MyEnum.Atomic).build());
        EntityQueryElement e = entityQueryElementDao.create(EntityQueryElement.builder().withName("E").withValue(4).withCategory(MyEnum.Atomic).build());
        EntityQueryElement f = entityQueryElementDao.create(EntityQueryElement.builder().withName("F").withValue(3).withCategory(MyEnum.Bombastic).build());
        EntityQueryElement g = entityQueryElementDao.create(EntityQueryElement.builder().withName("G").withValue(3).withCategory(MyEnum.Crazy).build());
        EntityQueryElement empty = entityQueryElementDao.create(EntityQueryElement.builder().build());


        // Without parameter
        assertEquals(3, entityCollectionWithoutParamQueryDao.query().execute().size());
        assertThat(
                entityCollectionWithoutParamQueryDao.query().execute().stream().map(p -> p.identifier()).collect(Collectors.toList()),
                containsInAnyOrder(c.identifier(), g.identifier(), f.identifier())
        );

        // With parameter
        assertThat(
                entityCollectionWithParamQueryDao.query(EntityCollectionWithParamQueryParameter
                        .builder()
                        .withName("B")
                        .withValue(2)
                        .withMyenum(MyEnum.Bombastic)
                        .build()).execute().stream().map(p -> p.identifier()).collect(Collectors.toList()),
                containsInAnyOrder(b1.identifier(), b2.identifier())
        );

        // This is the expected behaviour
        List<EntityQueryElement> execute = entityCollectionWithValueParamQueryDao.query().execute();
        assertEquals(0, execute.size());

        assertThat(
                entityCollectionWithValueParamQueryDao
                        .query(EntityCollectionWithValueParamQueryParameter
                                .builder()
                                .withValue(4)
                                .build())
                        .execute().stream().map(p -> p.identifier()).collect(Collectors.toList()),
                containsInAnyOrder(d.identifier(), e.identifier())
        );

        // QueryQueryCustomizer functionality

        // With parameter and filter
        assertThat(
                entityCollectionWithValueParamQueryDao
                        .query(EntityCollectionWithValueParamQueryParameter
                                .builder()
                                .withValue(4)
                                .build())
                        .filterByName(StringFilter.equalTo("D"))
                        .execute()
                        .stream().map(p -> p.identifier()).collect(Collectors.toList()),
                containsInAnyOrder(d.identifier())
        );

        // With parameter, limit and offset
        List<EntityQueryElement> list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(1);

        assertEquals(1, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());

        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(null);

        assertEquals(2, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());
        assertEquals(d.identifier(), list.get(1).identifier());

        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(0);

        assertEquals(2, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());
        assertEquals(d.identifier(), list.get(1).identifier());

        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(2);

        list = entityCollectionWithValueParamQueryDao
                        .query(EntityCollectionWithValueParamQueryParameter
                                .builder()
                                .withValue(4)
                                .build())
                        .orderByDescending(EntityQueryElementAttribute.NAME)
                        .execute(1, null);

        assertEquals(1, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());

        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(null, null);

        assertEquals(2, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());
        assertEquals(d.identifier(), list.get(1).identifier());

        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(0, null);

        assertEquals(2, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());
        assertEquals(d.identifier(), list.get(1).identifier());

        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(2, null);

        assertEquals(2, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());
        assertEquals(d.identifier(), list.get(1).identifier());

        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(2, 0);

        assertEquals(2, list.size());
        assertEquals(e.identifier(), list.get(0).identifier());
        assertEquals(d.identifier(), list.get(1).identifier());


        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(2, 1);

        assertEquals(1, list.size());
        assertEquals(d.identifier(), list.get(0).identifier());


        list = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute(2, 2);

        assertEquals(0, list.size());

        // With parameter and maskedBy
        List<EntityQueryElement> maskedList = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .maskedBy(EntityQueryElementMask.entityQueryElementMask().withName())
                .execute();

        assertNull(maskedList.get(0).getCategory());
        assertNull(maskedList.get(0).getValue());
        assertNotNull(maskedList.get(0).getName());

        assertNull(maskedList.get(1).getCategory());
        assertNull(maskedList.get(1).getValue());
        assertNotNull(maskedList.get(1).getName());

        // With parameter and orderBy
        List<EntityQueryElement> orderByList = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderBy(EntityQueryElementAttribute.NAME)
                .execute();

        assertEquals(d.identifier(), orderByList.get(0).identifier());
        assertEquals(e.identifier(), orderByList.get(1).identifier());

        // With parameter and orderByDesc
        List<EntityQueryElement> orderByDescList = entityCollectionWithValueParamQueryDao
                .query(EntityCollectionWithValueParamQueryParameter
                        .builder()
                        .withValue(4)
                        .build())
                .orderByDescending(EntityQueryElementAttribute.NAME)
                .execute();

        assertEquals(e.identifier(), orderByDescList.get(0).identifier());
        assertEquals(d.identifier(), orderByDescList.get(1).identifier());

        // With parameter and count
        assertEquals(2, entityCollectionWithValueParamQueryDao.query(EntityCollectionWithValueParamQueryParameter.builder().withValue(4).build()).execute().size());
        assertEquals(2, entityCollectionWithValueParamQueryDao.query(EntityCollectionWithValueParamQueryParameter.builder().withValue(4).build()).count());

        // With parameter default
        assertThat(
                entityCollectionWithDefaultParamQueryDao.query(EntityCollectionWithDefaultParamQueryParameter
                                .builder()
                                .build())
                        .execute().stream().map(p -> p.identifier()).collect(Collectors.toList()),
                containsInAnyOrder(b1.identifier(), b2.identifier())
        );

        assertThat(
                entityCollectionWithDefaultParamQueryDao.query(EntityCollectionWithDefaultParamQueryParameter
                                .builder()
                                .withName("C")
                                .withValue(3)
                                .withMyenum(MyEnum.Crazy)
                                .build())
                        .execute().stream().map(p -> p.identifier()).collect(Collectors.toList()),
                containsInAnyOrder(c.identifier())
        );

    }

}
