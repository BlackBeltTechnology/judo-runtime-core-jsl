package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.defaultrequiredentity.DefaultRequiredEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.defaultrequiredentity.DefaultRequiredEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.defaultrequiredentity.DefaultRequiredEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entityrequiredfields.EntityRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entityrequiredfields.EntityRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entityrequiredfields.EntityRequiredFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entityrequiredwithprimitivedefaults.EntityRequiredWithPrimitiveDefaults;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entityrequiredwithprimitivedefaults.EntityRequiredWithPrimitiveDefaultsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entityrequiredwithprimitivedefaults.EntityRequiredWithPrimitiveDefaultsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithidentifiers.EntityWithIdentifiers;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithidentifiers.EntityWithIdentifiersDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithidentifiers.EntityWithIdentifiersForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithidentifierscontainer.EntityWithIdentifiersContainerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithidentifierscontainer.EntityWithIdentifiersContainerForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithprimitivedefaultexpressions.EntityWithPrimitiveDefaultExpressions;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithprimitivedefaultexpressions.EntityWithPrimitiveDefaultExpressionsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithprimitivedefaultexpressions.EntityWithPrimitiveDefaultExpressionsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithprimitivedefaults.EntityWithPrimitiveDefaults;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithprimitivedefaults.EntityWithPrimitiveDefaultsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.entitywithprimitivedefaults.EntityWithPrimitiveDefaultsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myentitywithoptionalfields.MyEntityWithOptionalFieldsIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.mytransferwithoptionalfields.MyTransferWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.mytransferwithoptionalfields.MyTransferWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.mytransferwithoptionalfields.MyTransferWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.referenceentity.ReferenceEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.referenceentity.ReferenceEntityForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.timestampfromconstant.TimestampFromConstant;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.timestampfromconstant.TimestampFromConstantDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.primitives.primitives.timestampfromconstant.TimestampFromConstantForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.mapper.impl.DefaultCoercer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class PrimitivesTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Primitives", new PrimitivesDaoModules());

    @Inject
    MyEntityWithOptionalFieldsDao myEntityWithOptionalFieldsDao;

    @Inject
    EntityRequiredFieldsDao entityRequiredFieldsDao;

    @Inject
    EntityWithIdentifiersDao entityWithIdentifiersDao;

    @Inject
    EntityWithIdentifiersContainerDao entityWithIdentifiersContainerDao;

    @Inject
    EntityWithPrimitiveDefaultsDao entityWithPrimitiveDefaultsDao;

    @Inject
    EntityWithPrimitiveDefaultExpressionsDao entityWithPrimitiveDefaultExpressionsDao;

    @Inject
    EntityRequiredWithPrimitiveDefaultsDao entityRequiredWithPrimitiveDefaultsDao;

    @Inject
    ReferenceEntityDao referenceEntityDao;

    @Inject
    DefaultRequiredEntityDao defaultRequiredEntityDao;

    @Inject
    MyTransferWithOptionalFieldsDao myTransferWithOptionalFieldsDao;

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testPlainOptionalEntityCreationWithoutValues() {
        MyEntityWithOptionalFields myEntityWithOptionalFields = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder().build());

        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

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
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testPlainOptionalEntityCreationWithValues() {
        MyEntityWithOptionalFields myEntityWithOptionalFields = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withIntegerAttr(1)
                        .withScaledAttr(1.23)
                        .withStringAttr("test")
                        .withRegexAttr("+36 333-333-3333")
                        .withBoolAttr(true)
                        .withDateAttr(LocalDate.of(2022, 7, 11))
                        .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                        .withTimeAttr(LocalTime.parse("23:59:59"))
                        .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                        .withEnumAttr(MyEnum.Bombastic)
                        .build());

        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), myEntityWithOptionalFields.getIntegerAttr());
        assertEquals(Optional.of(1.23), myEntityWithOptionalFields.getScaledAttr());
        assertEquals(Optional.of("test"), myEntityWithOptionalFields.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), myEntityWithOptionalFields.getRegexAttr());
        assertEquals(Optional.of(true), myEntityWithOptionalFields.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), myEntityWithOptionalFields.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), myEntityWithOptionalFields.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), myEntityWithOptionalFields.getTimeAttr());
        assertEquals("test.txt", myEntityWithOptionalFields.getBinaryAttr().orElseThrow().getFileName());
        assertEquals(Optional.of(MyEnum.Bombastic), myEntityWithOptionalFields.getEnumAttr());
    }

    @Test()
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testMissingRequiredFieldsThrowExceptions() {
        ValidationException thrown = assertThrows(
            ValidationException.class,
            () -> entityRequiredFieldsDao.create(EntityRequiredFieldsForCreate.builder().build())
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
        List<EntityRequiredFields> list = entityRequiredFieldsDao.query().selectList();

        assertEquals(0, list.size());
    }

    private org.hamcrest.Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

    private org.hamcrest.Matcher matchPrecisionValidationForAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                hasProperty("location", equalTo(attrName)));
    }

    private org.hamcrest.Matcher matchScaleValidationForAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("SCALE_VALIDATION_FAILED")),
                hasProperty("location", equalTo(attrName)));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003"
    })
    public void testIdentifierFieldsAreUnique() {
        LocalDate now = LocalDate.now();
        EntityWithIdentifiers ent1 = entityWithIdentifiersDao.create(EntityWithIdentifiersForCreate.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(MyEnum.Bombastic)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, ent1.getIntegerAttr().get());

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityWithIdentifiersDao.create(EntityWithIdentifiersForCreate.builder()
                        .withIntegerAttr(1)
                        .withBoolAttr(true)
                        .withDateAttr(now)
                        .withEnumAttr(MyEnum.Bombastic)
                        .withStringAttr("blabla")
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("stringAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("enumAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("integerAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("boolAttr"))),
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("dateAttr")))
        ));

        thrown = assertThrows(
                ValidationException.class,
                () ->
                        entityWithIdentifiersContainerDao.create(
                                EntityWithIdentifiersContainerForCreate.builder()
                                        .withEntitiesWithIdentifiers(ImmutableList.of(
                                                EntityWithIdentifiersForCreate.builder()
                                                        .withIntegerAttr(2)
                                                        .build(),
                                                EntityWithIdentifiersForCreate.builder()
                                                        .withIntegerAttr(2)
                                                        .build()
                                        ))
                                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                allOf(
                        hasProperty("code", equalTo("IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION")),
                        hasProperty("location", equalTo("entitiesWithIdentifiers[1].integerAttr")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testFieldsAreNonUnique() {
        LocalDate now = LocalDate.now();
        MyEntityWithOptionalFields ent1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .withIntegerAttr(1)
                .withBoolAttr(true)
                .withDateAttr(now)
                .withEnumAttr(MyEnum.Bombastic)
                .withStringAttr("blabla")
                .build());

        assertEquals(1, ent1.getIntegerAttr().get());

        MyEntityWithOptionalFields ent2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withIntegerAttr(1)
                        .withBoolAttr(true)
                        .withDateAttr(now)
                        .withEnumAttr(MyEnum.Bombastic)
                        .withStringAttr("blabla")
                        .build());

        assertEquals(1, ent2.getIntegerAttr().get());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012"
    })
    public void testUpdateOptionalEntityAfterCreation() {
        MyEntityWithOptionalFields myEntityWithOptionalFields = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder().build());

        List<MyEntityWithOptionalFields> list = myEntityWithOptionalFieldsDao.query().selectList();

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
        myEntityWithOptionalFields.setTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"));
        myEntityWithOptionalFields.setTimeAttr(LocalTime.parse("23:59:59"));
        myEntityWithOptionalFields.setBinaryAttr(FileType.builder().fileName("test.txt").build());
        myEntityWithOptionalFields.setEnumAttr(MyEnum.Bombastic);

        myEntityWithOptionalFieldsDao.update(myEntityWithOptionalFields);

        assertEquals(Optional.of(1), myEntityWithOptionalFields.getIntegerAttr());
        assertEquals(Optional.of(1.23), myEntityWithOptionalFields.getScaledAttr());
        assertEquals(Optional.of("test"), myEntityWithOptionalFields.getStringAttr());
        assertEquals(Optional.of("+36 333-333-3333"), myEntityWithOptionalFields.getRegexAttr());
        assertEquals(Optional.of(true), myEntityWithOptionalFields.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), myEntityWithOptionalFields.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), myEntityWithOptionalFields.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), myEntityWithOptionalFields.getTimeAttr());
        assertEquals("test.txt", myEntityWithOptionalFields.getBinaryAttr().get().getFileName());
        assertEquals(Optional.of(MyEnum.Bombastic), myEntityWithOptionalFields.getEnumAttr());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testEntityCreationWithPrimitiveDefaults() {
        EntityWithPrimitiveDefaults entityWithDefaults = entityWithPrimitiveDefaultsDao.create(EntityWithPrimitiveDefaultsForCreate.builder().build());

        List<EntityWithPrimitiveDefaults> list = entityWithPrimitiveDefaultsDao.query().selectList();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), entityWithDefaults.getIntegerAttr());
        assertEquals(Optional.of(2.34), entityWithDefaults.getScaledAttr());
        assertEquals(Optional.of("test"), entityWithDefaults.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), entityWithDefaults.getRegexAttr());
        assertEquals(Optional.of(true), entityWithDefaults.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), entityWithDefaults.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), entityWithDefaults.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithDefaults.getTimeAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59")), entityWithDefaults.getShortTimeAttr());
        // There is no way to define default value in JSL for binary
        // assertEquals("test.txt", entityWithDefaults.getBinaryAttr().get().getFileName());
        assertEquals(Optional.of(MyEnum.Bombastic), entityWithDefaults.getEnumAttr());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testEntityCreationRequiredWithPrimitiveDefaults() {
        EntityRequiredWithPrimitiveDefaults entityRequiredWithDefaults = entityRequiredWithPrimitiveDefaultsDao.create(EntityRequiredWithPrimitiveDefaultsForCreate.builder().build());

        List<EntityRequiredWithPrimitiveDefaults> list = entityRequiredWithPrimitiveDefaultsDao.query().selectList();

        assertEquals(1, list.size());

        assertEquals(1, entityRequiredWithDefaults.getIntegerAttr());
        assertEquals(2.34, entityRequiredWithDefaults.getScaledAttr());
        assertEquals("test", entityRequiredWithDefaults.getStringAttr());
        assertEquals("+36 (30) 123 1234", entityRequiredWithDefaults.getRegexAttr());
        assertEquals(true, entityRequiredWithDefaults.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entityRequiredWithDefaults.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entityRequiredWithDefaults.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entityRequiredWithDefaults.getTimeAttr());
        // There is no way to define default value in JSL for binary
        // assertEquals("test.txt", entityWithDefaults.getBinaryAttr().get().getFileName());
        assertEquals(MyEnum.Bombastic, entityRequiredWithDefaults.getEnumAttr());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-002",
            "REQ-EXPR-014",
            "REQ-EXPR-015",
            "REQ-EXPR-002",
            "REQ-EXPR-009",
            "REQ-ENT-001",
            "REQ-EXPR-017"
    })
    public void testEntityCreationWithPrimitiveDefaultExpressions() {
        EntityWithPrimitiveDefaultExpressions entityWithDefaultExpressions = entityWithPrimitiveDefaultExpressionsDao.create(EntityWithPrimitiveDefaultExpressionsForCreate.builder().build());

        List<EntityWithPrimitiveDefaultExpressions> list = entityWithPrimitiveDefaultExpressionsDao.query().selectList();

        assertEquals(1, list.size());

        assertEquals(Optional.of(1), entityWithDefaultExpressions.getIntegerAttr());
        assertEquals(Optional.of(2.9), entityWithDefaultExpressions.getScaledAttr());
        assertEquals(Optional.of("true"), entityWithDefaultExpressions.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), entityWithDefaultExpressions.getRegexAttr());
        assertEquals(Optional.of(true), entityWithDefaultExpressions.getBoolAttr());
        assertEquals(Optional.of(LocalDate.now()), entityWithDefaultExpressions.getDateAttr());
        assertEquals(OffsetDateTime.now().toString().substring(1, 10),
        entityWithDefaultExpressions.getTimestampAttr().get().toString().substring(1, 10));
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), entityWithDefaultExpressions.getTimeAttr());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testRegexValidatorFailsForInvalidInput() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withRegexAttr("hello-bello")
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PATTERN_VALIDATION_FAILED")),
                hasProperty("location", equalTo("regexAttr")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testMaxLengthValidatorFailsForInvalidInput() {
        String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Imperdiet sed euismod nisi porta lorem mollis." +
                "Ac ut consequat semper viverra." +
                "Ultrices mi tempus imperdiet nulla malesuada pellentesque elit." +
                "Venenatis lectus magna fringilla urna porttitor rhoncus. In vitae turpis massa sed." +
                "Congue mauris rhoncus aenean vel elit. ";

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withStringAttr(lipsum)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MAX_LENGTH_VALIDATION_FAILED")),
                hasProperty("location", equalTo("stringAttr")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testPrecisionValidation() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withIntegerAttr(1234567890)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("PRECISION_VALIDATION_FAILED")),
                hasProperty("location", equalTo("integerAttr")))
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testMaxPrecision() {
        ValidationException validationException = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withScaledAttr(1234567890.0)
                        .build())
        );
        assertThat(validationException.getValidationResults(), containsInAnyOrder(matchPrecisionValidationForAttribute("scaledAttr")));

        ValidationException validationException1 = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withScaledAttr(123456789.1)
                        .build())
        );
        assertThat(validationException1.getValidationResults(), containsInAnyOrder(matchPrecisionValidationForAttribute("scaledAttr")));

        MyEntityWithOptionalFields e1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .withScaledAttr(123456789.0)
                .build());
        assertEquals(Optional.of(123456789.0), e1.getScaledAttr());

        MyEntityWithOptionalFields e2 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .withScaledAttr(12345678.1)
                .build());
        assertEquals(Optional.of(12345678.1), e2.getScaledAttr());

        MyEntityWithOptionalFields e3 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .withScaledAttr(1234567.12)
                .build());
        assertEquals(Optional.of(1234567.12), e3.getScaledAttr());

        MyEntityWithOptionalFields e4 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .withScaledAttr(1234567.1200)
                .build());
        assertEquals(Optional.of(1234567.12), e4.getScaledAttr());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002"
    })
    public void testScaleValidation() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                        .withScaledAttr(123456.789)
                        .build()));

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchScaleValidationForAttribute("scaledAttr")
        ));
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-016",
            "REQ-EXPR-022"
    })
    public void testDefaultRequiredValuesInEntity() {

        referenceEntityDao.create(ReferenceEntityForCreate.builder().build());
        referenceEntityDao.create(ReferenceEntityForCreate.builder().build());

        DefaultRequiredEntity defaultEntity = defaultRequiredEntityDao.create(DefaultRequiredEntityForCreate.builder().build());

        assertEquals(6, defaultEntity.getSumEntitiesIntegerValue());
        assertEquals(LocalDate.of(2022, 11, 4), defaultEntity.getCreateDate());
        assertThrows(ValidationException.class, () -> defaultRequiredEntityDao.create(DefaultRequiredEntityForCreate.builder()
                .withCreateDate(LocalDate.of(2022, 11, 4))
                .build()));

        DefaultRequiredEntity defaultEntity1 = defaultRequiredEntityDao.create(DefaultRequiredEntityForCreate.builder()
                .withCreateDate(LocalDate.of(2022, 11, 5))
                .withSumEntitiesIntegerValue(5)
                .build());

        assertEquals(5, defaultEntity1.getSumEntitiesIntegerValue());
        assertEquals(LocalDate.of(2022, 11, 5), defaultEntity1.getCreateDate());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-016",
            "REQ-EXPR-022"
    })
    public void testStringSubstitution() {
        MyEntityWithOptionalFields entity1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .withStringAttr("name %d", 1)
                .build());

        assertEquals("name 1", entity1.getStringAttr().orElseThrow());

        entity1.setStringAttr("%s %d", "name", 2);

        entity1 = myEntityWithOptionalFieldsDao.update(entity1);

        assertEquals("name 2", entity1.getStringAttr().orElseThrow());

        entity1 = myEntityWithOptionalFieldsDao.create(MyEntityWithOptionalFieldsForCreate.builder()
                .withStringAttr("%s %.2f %d", "name", 2.34, 1)
                .build());

        assertEquals("name 2.34 1", entity1.getStringAttr().orElseThrow());

        entity1.setStringAttr("%s", "name");

        entity1 = myEntityWithOptionalFieldsDao.update(entity1);

        assertEquals("name", entity1.getStringAttr().orElseThrow());

        entity1.setStringAttr("name");

        entity1 = myEntityWithOptionalFieldsDao.update(entity1);

        assertEquals("name", entity1.getStringAttr().orElseThrow());

        MyEntityWithOptionalFields myEntityWithOptionalFields = MyEntityWithOptionalFields.builder()
                .withStringAttr("name %d", 1)
                .build();

        entity1 = myEntityWithOptionalFieldsDao.create(myEntityWithOptionalFields.adaptTo(MyEntityWithOptionalFieldsForCreate.class));

        assertEquals("name 1", entity1.getStringAttr().orElseThrow());

        myEntityWithOptionalFields = MyEntityWithOptionalFields.builder()
                .withStringAttr("%s %.2f %d", "name", 2.34, 1)
                .build();

        entity1 = myEntityWithOptionalFieldsDao.create(myEntityWithOptionalFields.adaptTo(MyEntityWithOptionalFieldsForCreate.class));

        assertEquals("name 2.34 1", entity1.getStringAttr().orElseThrow());
    }


    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-016",
            "REQ-EXPR-022"
    })
    public void testStringSubstitutionOnTransfer() {
        MyTransferWithOptionalFields transfer = myTransferWithOptionalFieldsDao.create(MyTransferWithOptionalFieldsForCreate.builder()
                .withStringAttr("name %d", 1)
                .build());

        assertEquals("name 1", transfer.getStringAttr().orElseThrow());

        MyEntityWithOptionalFields entity = myEntityWithOptionalFieldsDao.getById(transfer.identifier().adaptTo(MyEntityWithOptionalFieldsIdentifier.class)).orElseThrow();

        assertEquals("name 1", entity.getStringAttr().orElseThrow());

        transfer.setStringAttr("%s %d", "name", 2);

        transfer = myTransferWithOptionalFieldsDao.update(transfer);

        assertEquals("name 2", transfer.getStringAttr().orElseThrow());

        entity = myEntityWithOptionalFieldsDao.getById(transfer.identifier().adaptTo(MyEntityWithOptionalFieldsIdentifier.class)).orElseThrow();

        assertEquals("name 2", entity.getStringAttr().orElseThrow());

        transfer = myTransferWithOptionalFieldsDao.create(MyTransferWithOptionalFieldsForCreate.builder()
                .withStringAttr("%s %.2f %d", "name", 2.34, 1)
                .build());

        assertEquals("name 2.34 1", transfer.getStringAttr().orElseThrow());

        entity = myEntityWithOptionalFieldsDao.getById(transfer.identifier().adaptTo(MyEntityWithOptionalFieldsIdentifier.class)).orElseThrow();

        assertEquals("name 2.34 1", entity.getStringAttr().orElseThrow());

        transfer.setStringAttr("%s", "name");

        transfer = myTransferWithOptionalFieldsDao.update(transfer);

        assertEquals("name", transfer.getStringAttr().orElseThrow());

        entity = myEntityWithOptionalFieldsDao.getById(transfer.identifier().adaptTo(MyEntityWithOptionalFieldsIdentifier.class)).orElseThrow();

        assertEquals("name", entity.getStringAttr().orElseThrow());

        transfer.setStringAttr("name");

        transfer = myTransferWithOptionalFieldsDao.update(transfer);

        assertEquals("name", transfer.getStringAttr().orElseThrow());

        entity = myEntityWithOptionalFieldsDao.getById(transfer.identifier().adaptTo(MyEntityWithOptionalFieldsIdentifier.class)).orElseThrow();

        assertEquals("name", entity.getStringAttr().orElseThrow());

        transfer = MyTransferWithOptionalFields.builder().withStringAttr("name %d",1).build();

        transfer = myTransferWithOptionalFieldsDao.create(transfer.adaptTo(MyTransferWithOptionalFieldsForCreate.class));

        assertEquals("name 1", transfer.getStringAttr().orElseThrow());

        entity = myEntityWithOptionalFieldsDao.getById(transfer.identifier().adaptTo(MyEntityWithOptionalFieldsIdentifier.class)).orElseThrow();

        assertEquals("name 1", entity.getStringAttr().orElseThrow());

        transfer = MyTransferWithOptionalFields.builder().withStringAttr("%s %.2f %d", "name", 2.34, 1).build();

        transfer = myTransferWithOptionalFieldsDao.create(transfer.adaptTo(MyTransferWithOptionalFieldsForCreate.class));

        assertEquals("name 2.34 1", transfer.getStringAttr().orElseThrow());

        entity = myEntityWithOptionalFieldsDao.getById(transfer.identifier().adaptTo(MyEntityWithOptionalFieldsIdentifier.class)).orElseThrow();

        assertEquals("name 2.34 1", entity.getStringAttr().orElseThrow());
    }

    @Inject
    TimestampFromConstantDao timestampFromConstantDao;

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-SRV-002"
    })
    public void testTimestampConstructionWithConstant() {
        TimestampFromConstant timestamp = timestampFromConstantDao.create(TimestampFromConstantForCreate.builder().build());

        DefaultCoercer coercer = new DefaultCoercer();

        assertEquals(coercer.coerce("2023-03-20T11:11", LocalDateTime.class), timestamp.getTimestampFromConstantHM().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11Z", LocalDateTime.class), timestamp.getTimestampFromConstantHMZ().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11+05", LocalDateTime.class), timestamp.getTimestampFromConstantHMP5().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11-05", LocalDateTime.class), timestamp.getTimestampFromConstantHMM5().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11+05:05", LocalDateTime.class), timestamp.getTimestampFromConstantHMP55().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11-05:05", LocalDateTime.class), timestamp.getTimestampFromConstantHMM55().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11", LocalDateTime.class), timestamp.getTimestampFromConstantHMS().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11Z", LocalDateTime.class), timestamp.getTimestampFromConstantHMSZ().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11+05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSP5().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11-05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSM5().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11+05:05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSP55().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11-05:05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSM55().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11.111", LocalDateTime.class), timestamp.getTimestampFromConstantHMSF().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11.111Z", LocalDateTime.class), timestamp.getTimestampFromConstantHMSFZ().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11.111+05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSFP5().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11.111-05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSFM5().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11.111+05:05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSFP55().orElseThrow());
        assertEquals(coercer.coerce("2023-03-20T11:11:11.111-05:05", LocalDateTime.class), timestamp.getTimestampFromConstantHMSFM55().orElseThrow());
    }

}
