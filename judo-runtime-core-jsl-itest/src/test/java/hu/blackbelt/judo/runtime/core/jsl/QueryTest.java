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
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class QueryTest {
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
                .withIntegerAttr(2)
                .withScaledAttr(2.34)
                .withStringAttr("test")
                .withRegexAttr("+36 333-333-3333")
                .withBoolAttr(true)
                .withDateAttr(LocalDate.of(2022, 7, 11))
                .withTimestampAttr(OffsetDateTime.parse("2022-07-11T19:09:33Z"))
                .withTimeAttr(LocalTime.parse("23:59:59"))
                // FIXME JNG-3842
                //      .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(MyEnum.Bombastic)
                .build());

        entity2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(1)
                .withScaledAttr(1.23)
                .withStringAttr("Another")
                .withRegexAttr("+36 333-333-3331")
                .withBoolAttr(false)
                .withDateAttr(LocalDate.of(1999, 9, 19))
                .withTimestampAttr(OffsetDateTime.parse("1999-09-19T09:09:09Z"))
                .withTimeAttr(LocalTime.parse("12:34:56"))
                // FIXME JNG-3842
                //      .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(MyEnum.Atomic)
                .build());
    }

    @Test
    public void testLimit() {
        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao
                .query()
                .limit(1)
                .execute();

        assertEquals(1, list.size());
    }

    @Disabled
    public void testGetByIdThrowsForNonExistingElement() {
        myEntityWithOptionalFieldsDao.delete(entity1);

        // FIXME JNG-3860
//        IllegalArgumentException thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> myEntityWithOptionalFieldsDao.getById(entity1.get__identifier())
//        );
//
//        assertNotNull(thrown);
    }

    @Test
    public void testOrderBy() {
        assertOrderBy(MyEntityWithOptionalFields.Attribute.INTEGER_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.SCALED_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.STRING_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.REGEX_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.BOOL_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.DATE_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.TIMESTAMP_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.TIME_ATTR, entity2);
        assertOrderBy(MyEntityWithOptionalFields.Attribute.ENUM_ATTR, entity2);
    }

    @Test
    public void testOrderByDescending() {
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.INTEGER_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.SCALED_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.STRING_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.REGEX_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.BOOL_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.DATE_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.TIMESTAMP_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.TIME_ATTR, entity1);
        assertOrderByDescending(MyEntityWithOptionalFields.Attribute.ENUM_ATTR, entity1);
    }

    @Test
    public void testMask() {
        MyEntityWithOptionalFields maskedResult = myEntityWithOptionalFieldsDao
                .query()
                .maskedBy(MyEntityWithOptionalFields.MyEntityWithOptionalFieldsDao.Mask.myEntityWithOptionalFieldsMask()
                        .withStringAttr()
                        .withIntegerAttr())
                .filterByStringAttr(StringFilter.equalTo("test"))
                .execute()
                .get(0);

        assertEquals(entity1.get__identifier(), maskedResult.get__identifier());
        assertEquals(entity1.getIntegerAttr(), maskedResult.getIntegerAttr());
        assertEquals(Optional.empty(), maskedResult.getScaledAttr());
        assertEquals(entity1.getStringAttr(), maskedResult.getStringAttr());
        assertEquals(Optional.empty(), maskedResult.getRegexAttr());
    }

    private void assertOrderBy(MyEntityWithOptionalFields.Attribute attribute, MyEntityWithOptionalFields firstEntity) {
        MyEntityWithOptionalFields orderBy = myEntityWithOptionalFieldsDao
                .query()
                .orderBy(attribute)
                .execute()
                .get(0);

        assertEquals(firstEntity.get__identifier(), orderBy.get__identifier());
    }

    private void assertOrderByDescending(MyEntityWithOptionalFields.Attribute attribute, MyEntityWithOptionalFields firstEntity) {
        MyEntityWithOptionalFields orderByDescending = myEntityWithOptionalFieldsDao
                .query()
                .orderByDescending(attribute)
                .execute()
                .get(0);

        assertEquals(firstEntity.get__identifier(), orderByDescending.get__identifier());
    }
}
