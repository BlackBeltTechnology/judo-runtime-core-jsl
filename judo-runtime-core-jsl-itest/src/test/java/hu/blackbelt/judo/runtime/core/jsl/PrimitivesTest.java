package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.dispatcher.api.Dispatcher;
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.guice.primitives.PrimitivesDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.sdk.primitives.primitives.*;
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
public class PrimitivesTest {
    Injector injector;

    @Inject
    MyEntityWithOptionalFields.MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    @Inject
    EntityRequiredFields.EntityRequiredFieldsDao entityRequiredFieldsDao;

    @Inject
    EntityWithIdentifiers.EntityWithIdentifiersDao entityWithIdentifiersDao;

    @Inject
    EntityWithPrimitiveDefaults.EntityWithPrimitiveDefaultsDao entityWithPrimitiveDefaultsDao;

    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("Primitives", PrimitivesTest.class.getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new PrimitivesDaoModules(),
                new JudoDefaultModule(this, modelHolder));
    }

    @Test
    public void testPlainOptionalEntityCreationWithoutValues() {
        MyEntityWithOptionalFields myEntityWithOptionalFields = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder().build());

        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.empty(), myEntityWithOptionalFields.getIntegerAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getScaledAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getStringAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getRegexAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getBoolAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getDateAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getTimestampAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getTimeAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getBinaryAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getEnumAttr());
    }

    @Test
    public void testPlainOptionalEntityCreationWithValues() {
        MyEntityWithOptionalFields myEntityWithOptionalFields = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                        .withIntegerAttr(1)
                        .withScaledAttr(1.23)
                        .withStringAttr("test")
                        .withRegexAttr("+36-1-123-123")
                        .withBoolAttr(true)
                        .withDateAttr(LocalDate.of(2022, 7, 11))
                        .withTimestampAttr(OffsetDateTime.parse("2022-07-11T19:09:33Z"))
                        .withTimeAttr(LocalTime.parse("23:59:59"))
                // FIXME JNG-3842
                        .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                        .withEnumAttr(MyEnum.Bombastic)
                        .build());

        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), myEntityWithOptionalFields.getIntegerAttr());
        assertEquals(Optional.of(1.23), myEntityWithOptionalFields.getScaledAttr());
        assertEquals(Optional.of("test"), myEntityWithOptionalFields.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), myEntityWithOptionalFields.getRegexAttr());
        assertEquals(Optional.of(true), myEntityWithOptionalFields.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), myEntityWithOptionalFields.getDateAttr());
        assertEquals(Optional.of(OffsetDateTime.parse("2022-07-11T19:09:33Z")), myEntityWithOptionalFields.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), myEntityWithOptionalFields.getTimeAttr());
        // FIXME JNG-3842
        // assertEquals("test.txt", myEntityWithOptionalFields.getBinaryAttr().get().getFileName());
         assertEquals(Optional.of(MyEnum.Bombastic), myEntityWithOptionalFields.getEnumAttr());
    }

    @Test()
    public void testMissingRequiredFieldsThrowExceptions() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> entityRequiredFieldsDao.create(EntityRequiredFields.builder().build())
        );

        assertTrue(thrown.getMessage().contains("missing mandatory attribute"));
        assertTrue(thrown.getMessage().contains("name: integerAttr"));
        assertTrue(thrown.getMessage().contains("name: scaledAttr"));
        assertTrue(thrown.getMessage().contains("name: stringAttr"));
        assertTrue(thrown.getMessage().contains("name: regexAttr"));
        assertTrue(thrown.getMessage().contains("name: boolAttr"));
        assertTrue(thrown.getMessage().contains("name: dateAttr"));
        assertTrue(thrown.getMessage().contains("name: timestampAttr"));
        assertTrue(thrown.getMessage().contains("name: timeAttr"));
        assertTrue(thrown.getMessage().contains("name: binaryAttr"));
        assertTrue(thrown.getMessage().contains("name: enumAttr"));

        List<EntityRequiredFields> list = entityRequiredFieldsDao.query().execute();

        assertEquals(0, list.size());
    }

    // FIXME JNG-3841
    @Disabled
    public void testIdentifierFieldsAreUnique() {
        EntityWithIdentifiers ent1 = entityWithIdentifiersDao.create(EntityWithIdentifiers.builder().withIntegerAttr(1).build());
        EntityWithIdentifiers ent2 = entityWithIdentifiersDao.create(EntityWithIdentifiers.builder().withIntegerAttr(1).build());

        assertEquals(1, ent1.getIntegerAttr().get());
        assertEquals(1, ent2.getIntegerAttr().get());
    }

    @Test
    public void testUpdateOptionalEntityAfterCreation() {
        MyEntityWithOptionalFields myEntityWithOptionalFields = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder().build());

        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.empty(), myEntityWithOptionalFields.getIntegerAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getScaledAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getStringAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getRegexAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getBoolAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getDateAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getTimestampAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getTimeAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getBinaryAttr());
        assertEquals(Optional.empty(), myEntityWithOptionalFields.getEnumAttr());

        myEntityWithOptionalFields.setIntegerAttr(1);
        myEntityWithOptionalFields.setScaledAttr(1.23);
        myEntityWithOptionalFields.setStringAttr("test");
        myEntityWithOptionalFields.setRegexAttr("+36-1-123-123");
        myEntityWithOptionalFields.setBoolAttr(true);
        myEntityWithOptionalFields.setDateAttr(LocalDate.of(2022, 7, 11));
        myEntityWithOptionalFields.setTimestampAttr(OffsetDateTime.parse("2022-07-11T19:09:33Z"));
        myEntityWithOptionalFields.setTimeAttr(LocalTime.parse("23:59:59"));
        // FIXME JNG-3842
        myEntityWithOptionalFields.setBinaryAttr(FileType.builder().fileName("test.txt").build());
        myEntityWithOptionalFields.setEnumAttr(MyEnum.Bombastic);

        myEntityWithOptionalFieldsDao.update(myEntityWithOptionalFields);

        assertEquals(Optional.of(1), myEntityWithOptionalFields.getIntegerAttr());
        assertEquals(Optional.of(1.23), myEntityWithOptionalFields.getScaledAttr());
        assertEquals(Optional.of("test"), myEntityWithOptionalFields.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), myEntityWithOptionalFields.getRegexAttr());
        assertEquals(Optional.of(true), myEntityWithOptionalFields.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), myEntityWithOptionalFields.getDateAttr());
        assertEquals(Optional.of(OffsetDateTime.parse("2022-07-11T19:09:33Z")), myEntityWithOptionalFields.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), myEntityWithOptionalFields.getTimeAttr());
        assertEquals("test.txt", myEntityWithOptionalFields.getBinaryAttr().get().getFileName());
        assertEquals(Optional.of(MyEnum.Bombastic), myEntityWithOptionalFields.getEnumAttr());
    }

    @Test
    public void testEntityCreationWithPrimitiveDefaults() {
        EntityWithPrimitiveDefaults entityWithDefaults = entityWithPrimitiveDefaultsDao.create(EntityWithPrimitiveDefaults.builder().build());

        List<EntityWithPrimitiveDefaults> list = entityWithPrimitiveDefaultsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), entityWithDefaults.getIntegerAttr());
        assertEquals(Optional.of(2.34), entityWithDefaults.getScaledAttr());
        assertEquals(Optional.of("test"), entityWithDefaults.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), entityWithDefaults.getRegexAttr());
        assertEquals(Optional.of(true), entityWithDefaults.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityWithDefaults.getDateAttr());
        assertEquals(Optional.of(OffsetDateTime.parse("2022-07-11T19:09:33Z")), entityWithDefaults.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithDefaults.getTimeAttr());
        // There is no way to define default value in JSL for binary
        // assertEquals("test.txt", entityWithDefaults.getBinaryAttr().get().getFileName());
        assertEquals(Optional.of(MyEnum.Bombastic), entityWithDefaults.getEnumAttr());
    }

    // FIXME JNG-3849
    @Disabled
    public void testRegexValidatorFailsForInvalidInput() {
        MyEntityWithOptionalFields myEntityWithOptionalFields = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withRegexAttr("hello-bello")
                .build());

        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().execute();

        assertEquals(1, list.size());
    }
}
