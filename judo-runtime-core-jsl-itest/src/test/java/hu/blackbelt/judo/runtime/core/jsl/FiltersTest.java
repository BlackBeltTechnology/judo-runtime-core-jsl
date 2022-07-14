package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.guice.primitives.PrimitivesDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.sdk.primitives.primitives.MyEntityWithOptionalFields;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.sdk.primitives.primitives.MyEnum;
import hu.blackbelt.judo.sdk.query.NumberFilter;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class FiltersTest {
    Injector injector;

    @Inject
    MyEntityWithOptionalFields.MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    MyEntityWithOptionalFields entity1;

    MyEntityWithOptionalFields entity2;

    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("Primitives", PrimitivesTest.class.getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new PrimitivesDaoModules(),
                new JudoDefaultModule(this, modelHolder));

        entity1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(1)
                .withScaledAttr(1.23)
                .withStringAttr("test")
                .withRegexAttr("+36-1-123-123")
                .withBoolAttr(true)
                .withDateAttr(LocalDate.of(2022, 7, 11))
                .withTimestampAttr(OffsetDateTime.parse("2022-07-11T19:09:33Z"))
                // FIXME JNG-3842
                //      .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(MyEnum.Bombastic)
                .build());

        entity2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(2)
                .withScaledAttr(2.34)
                .withStringAttr("Another")
                .withRegexAttr("+36-80-111-222")
                .withBoolAttr(false)
                .withDateAttr(LocalDate.of(1999, 9, 19))
                .withTimestampAttr(OffsetDateTime.parse("1999-09-19T09:09:09Z"))
                // FIXME JNG-3842
                //      .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(MyEnum.Atomic)
                .build());
    }

    @Test
    public void testFilterWithMultipleFilters() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        List<MyEntityWithOptionalFields> multiFilter = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(1))
                .filterByScaledAttr(NumberFilter.lessThan(2))
                .filterByStringAttr(StringFilter.equalTo("test"))
                .execute();

        assertEquals(1, multiFilter.size());

        assertEquals(entity1.get__identifier(), multiFilter.get(0).get__identifier());
    }

    @Test
    public void testIntegerNumberFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), filteredByString.get__identifier());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(1))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equals.get__identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.notEqualTo(1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEquals.get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterOrEqualThan(1))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        MyEntityWithOptionalFields greater = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterThan(1))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), greater.get__identifier());

        List<MyEntityWithOptionalFields> lessOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessOrEqualThan(2))
                .execute();

        assertEquals(2, lessOrEqual.size());

        MyEntityWithOptionalFields less = myEntityWithOptionalFieldsDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(2))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), less.get__identifier());
    }

    @Test
    public void testScaledNumberFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.scaledAttr >= 1 and this.scaledAttr <= 2")
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), filteredByString.get__identifier());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.equalTo(1.23))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equals.get__identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.notEqualTo(1.23))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEquals.get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterOrEqualThan(1.23))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        MyEntityWithOptionalFields greater = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterThan(1.23))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), greater.get__identifier());

        List<MyEntityWithOptionalFields> lessOrEqual = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessOrEqualThan(2.34))
                .execute();

        assertEquals(2, lessOrEqual.size());

        MyEntityWithOptionalFields less = myEntityWithOptionalFieldsDao
                .query()
                .filterByScaledAttr(NumberFilter.lessThan(2.34))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), less.get__identifier());
    }

    @Test
    public void testStringFilter() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(2, list.size());

        MyEntityWithOptionalFields filteredByString = myEntityWithOptionalFieldsDao
                .query()
                .filterBy("this.stringAttr!matches('test')")
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), filteredByString.get__identifier());

        MyEntityWithOptionalFields equals = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.equalTo("test"))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), equals.get__identifier());

        MyEntityWithOptionalFields notEquals = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.notEqualTo("test"))
                .execute()
                .get(0);

        assertEquals(entity2.get__identifier(), notEquals.get__identifier());

        List<MyEntityWithOptionalFields> iLike = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.ilike("TEST"))
                .execute();

        assertEquals(1, iLike.size());
        assertEquals(entity1.get__identifier(), iLike.get(0).get__identifier());

        List<MyEntityWithOptionalFields> like = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.like("test"))
                .execute();

        assertEquals(1, like.size());
        assertEquals(entity1.get__identifier(), like.get(0).get__identifier());

        List<MyEntityWithOptionalFields> lessThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessThan("test"))
                .execute();

        assertEquals(entity2.get__identifier(), lessThan.get(0).get__identifier());

        List<MyEntityWithOptionalFields> greaterThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterThan("Another"))
                .execute();

        assertEquals(entity1.get__identifier(), greaterThan.get(0).get__identifier());

        List<MyEntityWithOptionalFields> greaterOrEqualThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan("Another"))
                .execute();

        assertEquals(2, greaterOrEqualThan.size());

        List<MyEntityWithOptionalFields> lessOrEqualThan = myEntityWithOptionalFieldsDao
                .query()
                .filterByStringAttr(StringFilter.lessOrEqualThan("test"))
                .execute();

        assertEquals(2, lessOrEqualThan.size());
    }
}
