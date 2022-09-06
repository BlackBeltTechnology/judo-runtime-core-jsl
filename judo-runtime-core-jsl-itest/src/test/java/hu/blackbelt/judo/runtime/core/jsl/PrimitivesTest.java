package hu.blackbelt.judo.runtime.core.jsl;

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

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.guice.primitives.PrimitivesDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.sdk.primitives.primitives.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class PrimitivesTest extends AbstractJslTest {
    @Inject
    MyEntityWithOptionalFields.MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    @Inject
    EntityRequiredFields.EntityRequiredFieldsDao entityRequiredFieldsDao;

    @Inject
    EntityWithIdentifiers.EntityWithIdentifiersDao entityWithIdentifiersDao;

    @Inject
    EntityWithIdentifiersContainer.EntityWithIdentifiersContainerDao entityWithIdentifiersContainerDao;

    @Inject
    EntityWithPrimitiveDefaults.EntityWithPrimitiveDefaultsDao entityWithPrimitiveDefaultsDao;

    @Inject
    EntityWithPrimitiveDefaultExpressions.EntityWithPrimitiveDefaultExpressionsDao entityWithPrimitiveDefaultExpressionsDao;

    @Inject
    EntityRequiredWithPrimitiveDefaults.EntityRequiredWithPrimitiveDefaultsDao entityRequiredWithPrimitiveDefaultsDao;

    @Override
    public Module getModelDaoModule() {
        return new PrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "Primitives";
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
                        .withRegexAttr("+36 333-333-3333")
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
        assertEquals(Optional.of("+36 333-333-3333"), myEntityWithOptionalFields.getRegexAttr());
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
        ValidationException thrown = assertThrows(
            ValidationException.class,
            () -> entityRequiredFieldsDao.create(EntityRequiredFields.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchMissingAttribute("integerAttr"),
                matchMissingAttribute("scaledAttr"),
                matchMissingAttribute("stringAttr"),
                matchMissingAttribute("regexAttr"),
                matchMissingAttribute("boolAttr"),
                matchMissingAttribute("dateAttr"),
                matchMissingAttribute("timestampAttr"),
                matchMissingAttribute("timeAttr"),
                matchMissingAttribute("binaryAttr"),
                matchMissingAttribute("enumAttr")
        ));
        List<EntityRequiredFields> list = entityRequiredFieldsDao.query().execute();

        assertEquals(0, list.size());
    }

    private org.hamcrest.Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

    @Test
    public void testIdentifierFieldsAreUnique() {
        LocalDate now = LocalDate.now();
        EntityWithIdentifiers ent1 = entityWithIdentifiersDao.create(EntityWithIdentifiers.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(MyEnum.Bombastic)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, ent1.getIntegerAttr().get());

        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> entityWithIdentifiersDao.create(EntityWithIdentifiers.builder()
                        .withIntegerAttr(1)
                        .withBoolAttr(true)
                        .withDateAttr(now)
                        .withEnumAttr(MyEnum.Bombastic)
                        .withStringAttr("blabla")
                        .build()));

        assertTrue(thrown.getMessage().startsWith("Identifier uniqueness violation(s): "));
        assertTrue(thrown.getMessage().contains("stringAttr=blabla"));
        assertTrue(thrown.getMessage().contains("enumAttr=1"));
        assertTrue(thrown.getMessage().contains("integerAttr=1"));
        assertTrue(thrown.getMessage().contains("boolAttr=true"));
        assertTrue(thrown.getMessage().contains("dateAttr=" + now.toString()));


        thrown = assertThrows(
                IllegalStateException.class,
                () ->
                        entityWithIdentifiersContainerDao.create(
                                EntityWithIdentifiersContainer.builder()
                                        .withEntiiesWithIdentifiers(ImmutableList.of(
                                                EntityWithIdentifiers.builder()
                                                        .withIntegerAttr(2)
                                                        .build(),
                                                EntityWithIdentifiers.builder()
                                                        .withIntegerAttr(2)
                                                        .build()
                                        ))
                                        .build()));

        assertTrue(thrown.getMessage().startsWith("Identifier uniqueness violation(s): "));
        assertTrue(thrown.getMessage().contains("integerAttr=2"));
    }

    @Test
    public void testFieldsAreNonUnique() {
        LocalDate now = LocalDate.now();
        MyEntityWithOptionalFields ent1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(MyEnum.Bombastic)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, ent1.getIntegerAttr().get());

        MyEntityWithOptionalFields ent2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                        .withIntegerAttr(1)
                        .withBoolAttr(true)
                        .withDateAttr(now)
                        .withEnumAttr(MyEnum.Bombastic)
                        .withStringAttr("blabla")
                        .build());

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
        myEntityWithOptionalFields.setRegexAttr("+36 333-333-3333");
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
        assertEquals(Optional.of("+36 333-333-3333"), myEntityWithOptionalFields.getRegexAttr());
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

    @Test
    public void testEntityCreationRequiredWithPrimitiveDefaults() {
        EntityRequiredWithPrimitiveDefaults entityRequiredWithDefaults = entityRequiredWithPrimitiveDefaultsDao.create(EntityRequiredWithPrimitiveDefaults.builder().build());

        List<EntityRequiredWithPrimitiveDefaults> list = entityRequiredWithPrimitiveDefaultsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(1, entityRequiredWithDefaults.getIntegerAttr());
        assertEquals(2.34, entityRequiredWithDefaults.getScaledAttr());
        assertEquals("test", entityRequiredWithDefaults.getStringAttr());
        assertEquals("+36-1-123-123", entityRequiredWithDefaults.getRegexAttr());
        assertEquals(true, entityRequiredWithDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityRequiredWithDefaults.getDateAttr());
        assertEquals(OffsetDateTime.parse("2022-07-11T19:09:33Z"), entityRequiredWithDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityRequiredWithDefaults.getTimeAttr());
        // There is no way to define default value in JSL for binary
        // assertEquals("test.txt", entityWithDefaults.getBinaryAttr().get().getFileName());
        assertEquals(MyEnum.Bombastic, entityRequiredWithDefaults.getEnumAttr());
    }

    @Test
    public void testEntityCreationWithPrimitiveDefaultExpressions() {
        EntityWithPrimitiveDefaultExpressions entityWithDefaultExpressions = entityWithPrimitiveDefaultExpressionsDao.create(EntityWithPrimitiveDefaultExpressions.builder().build());

        List<EntityWithPrimitiveDefaultExpressions> list = entityWithPrimitiveDefaultExpressionsDao.query().execute();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), entityWithDefaultExpressions.getIntegerAttr());
        // assertEquals(Optional.of(2.34), entityWithDefaultExpressions.getScaledAttr());
        assertEquals(Optional.of("TRUE"), entityWithDefaultExpressions.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), entityWithDefaultExpressions.getRegexAttr());
        assertEquals(Optional.of(true), entityWithDefaultExpressions.getBoolAttr());
        assertEquals(Optional.of(LocalDate.now()), entityWithDefaultExpressions.getDateAttr());
        assertEquals(OffsetDateTime.now().toString().substring(1, 10),
        entityWithDefaultExpressions.getTimestampAttr().get().toString().substring(1, 10));
        // assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithDefaultExpressions.getTimeAttr());
    }

    @Test
    public void testRegexValidatorFailsForInvalidInput() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                        .withRegexAttr("hello-bello")
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PATTERN_VALIDATION_FAILED")),
                hasProperty("location", equalTo("regexAttr")))
        ));
    }

    @Test
    public void testPrecisionValidatorFailsForInvalidInput() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                        .withIntegerAttr(1234567890)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                hasProperty("location", equalTo("integerAttr")))
        ));
    }

    @Test
    public void testScaleValidatorFailsForInvalidInput() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                        .withScaledAttr(123456.123)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("SCALE_VALIDATION_FAILED")),
                hasProperty("location", equalTo("scaledAttr")))
        ));
    }

    @Test
    public void testScaleValidatorPassesForValueWithoutScale() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                        .withScaledAttr(123456789.0)
                        .build()));

        assertEquals(0, thrown.getValidationResults().size());
    }

    @Test
    public void testMaxLengthValidatorFailsForInvalidInput() {
        String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Imperdiet sed euismod nisi porta lorem mollis." +
                "Ac ut consequat semper viverra." +
                "Ultrices mi tempus imperdiet nulla malesuada pellentesque elit." +
                "Venenatis lectus magna fringilla urna porttitor rhoncus. In vitae turpis massa sed." +
                "Congue mauris rhoncus aenean vel elit. ";

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFields.builder()
                        .withStringAttr(lipsum)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MAX_LENGTH_VALIDATION_FAILED")),
                hasProperty("location", equalTo("stringAttr")))
        ));
    }
}
