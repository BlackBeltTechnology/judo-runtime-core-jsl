package hu.blackbelt.judo.runtime.core.jsl.entity;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
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

import java.util.Collection;

import com.google.inject.Inject;
import hu.blackbelt.judo.dao.api.ValidationResult;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationoptionaldefaulttransfer.CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationoptionaldefaulttransfer.CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationoptionaldefaulttransfer.CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationoptionaltransfer.CollectorWithSingleOptionalDefaultRelationOptionalTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationoptionaltransfer.CollectorWithSingleOptionalDefaultRelationOptionalTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationoptionaltransfer.CollectorWithSingleOptionalDefaultRelationOptionalTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationrequireddefaulttransfer.CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationrequireddefaulttransfer.CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationrequireddefaulttransfer.CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationrequiredtransfer.CollectorWithSingleOptionalDefaultRelationRequiredTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationrequiredtransfer.CollectorWithSingleOptionalDefaultRelationRequiredTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationrequiredtransfer.CollectorWithSingleOptionalDefaultRelationRequiredTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationoptionaldefaulttransfer.CollectorWithSingleOptionalRelationOptionalDefaultTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationoptionaldefaulttransfer.CollectorWithSingleOptionalRelationOptionalDefaultTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationoptionaldefaulttransfer.CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationoptionaltransfer.CollectorWithSingleOptionalRelationOptionalTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationoptionaltransfer.CollectorWithSingleOptionalRelationOptionalTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationoptionaltransfer.CollectorWithSingleOptionalRelationOptionalTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationrequireddefaulttransfer.CollectorWithSingleOptionalRelationRequiredDefaultTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationrequireddefaulttransfer.CollectorWithSingleOptionalRelationRequiredDefaultTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationrequireddefaulttransfer.CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationrequiredtransfer.CollectorWithSingleOptionalRelationRequiredTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionalrelationrequiredtransfer.CollectorWithSingleOptionalRelationRequiredTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelation.CollectorWithSingleRequiredRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationoptionaldefaulttransfer.CollectorWithSingleRequiredRelationOptionalDefaultTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationoptionaldefaulttransfer.CollectorWithSingleRequiredRelationOptionalDefaultTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationoptionaldefaulttransfer.CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationoptionaltransfer.CollectorWithSingleRequiredRelationOptionalTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationoptionaltransfer.CollectorWithSingleRequiredRelationOptionalTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationrequireddefaulttransfer.CollectorWithSingleRequiredRelationRequiredDefaultTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationrequireddefaulttransfer.CollectorWithSingleRequiredRelationRequiredDefaultTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationrequireddefaulttransfer.CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationrequiredtransfer.CollectorWithSingleRequiredRelationRequiredTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationrequiredtransfer.CollectorWithSingleRequiredRelationRequiredTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.itemtransfer.ItemTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.itemtransfer.ItemTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.itemtransfer.ItemTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.itemtransfer.ItemTransferQueryCustomizer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RelationWithDefaultsModelDaoModules;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.validator.Validator;
import hu.blackbelt.judo.sdk.query.NumberFilter;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TransferRelationWithDefaultsModelTest {

    // TODO All possibilities https://docs.google.com/spreadsheets/d/18rggGdM1DrjUBQRuq5Y1z1M_8n587W0DgPY_1iZJhAI/edit?gid=0#gid=0

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("RelationWithDefaultsModel", new RelationWithDefaultsModelDaoModules());

    @Inject
    ItemTransferDao itemTransferDao;

    // Variations
    /*
    opt - opt
    opt - opt default
    opt - req
    opt - req default
     */

    @Inject
    CollectorWithSingleOptionalRelationOptionalTransferDao collectorWithSingleOptionalRelationOptionalTransferDao;

    // opt - opt

    @Test
    public void singleOptionalRelationOptionalTransferTest() {
        // shortname
        CollectorWithSingleOptionalRelationOptionalTransferDao collectorDao = collectorWithSingleOptionalRelationOptionalTransferDao;

        CollectorWithSingleOptionalRelationOptionalTransfer collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

    }

    @Inject
    CollectorWithSingleOptionalRelationOptionalDefaultTransferDao collectorWithSingleOptionalDefaultRelationTransferDao;


    // opt - opt default

    @Test
    public void singleOptionalRelationOptionalDefaultTransferTest() {
        // shortname
        CollectorWithSingleOptionalRelationOptionalDefaultTransferDao collectorDao = collectorWithSingleOptionalDefaultRelationTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleOptionalRelationOptionalDefaultTransfer collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        // Default element is undefined and no value added during the creation
        itemTransferDao.delete(item);
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // No matching default element and no value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        itemTransferDao.delete(item);

        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleOptionalRelationRequiredTransferDao singleOptionalRelationRequiredTransferDao;

    // opt - req

    @Test
    public void singleRequiredRelationRequiredTransferTest() {
        // shortname
        CollectorWithSingleOptionalRelationRequiredTransferDao collectorDao = singleOptionalRelationRequiredTransferDao;

        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        ValidationException thrown = assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleOptionalRelationRequiredTransferForCreate.builder().build()));
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("reqItem")))
        ));

    }

    @Inject
    CollectorWithSingleOptionalRelationRequiredDefaultTransferDao collectorWithSingleOptionalRelationRequiredDefaultTransferDao;

    // opt - req default

    @Test
    public void singleOptionalRelationRequiredDefaultTransferTest() {
        // shortname
        CollectorWithSingleOptionalRelationRequiredDefaultTransferDao collectorDao = collectorWithSingleOptionalRelationRequiredDefaultTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleOptionalRelationRequiredDefaultTransfer collector = collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryReqItem(collector).identifier());

        // Default element is undefined and no value added during the creation

        itemTransferDao.delete(item);

        // Error
        ValidationException validationException =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException, "reqItem");

        // No matching default element and no value added during the creation // Error
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        ValidationException validationException1 =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException1, "reqItem");

        itemTransferDao.delete(item);
        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
        itemTransferDao.delete(itemA1);

    }

    // Variations
    /*
    req - opt
    req - opt default
    req - req
    req - req default
     */

    @Inject
    CollectorWithSingleRequiredRelationOptionalTransferDao collectorWithSingleRequiredRelationOptionalTransferDao;

    @Inject
    CollectorWithSingleRequiredRelationDao collectorWithSingleRequiredRelationDao;

    // req - opt

    @Test
    public void singleRequiredRelationOptionalTransfer() {
        // shortname
        CollectorWithSingleRequiredRelationOptionalTransferDao collectorDao = collectorWithSingleRequiredRelationOptionalTransferDao;

        // Should throw error
        ValidationException validationException =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationOptionalTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException, "optItem", Validator.ERROR_MISSING_REQUIRED_RELATION_ON_ENTITY);
    }


    @Inject
    CollectorWithSingleRequiredRelationOptionalDefaultTransferDao collectorWithSingleRequiredRelationOptionalDefaultTransferDao;

    // req - opt default

    @Test
    public void singleRequiredRelationOptionalDefaultTransferTest() {
        // shortname
        CollectorWithSingleRequiredRelationOptionalDefaultTransferDao collectorDao = collectorWithSingleRequiredRelationOptionalDefaultTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleRequiredRelationOptionalDefaultTransfer collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        // Default element is undefined and no value added during the creation
        collectorDao.delete(collector);
        itemTransferDao.delete(item);

        ValidationException validationException =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException, "optItem", Validator.ERROR_MISSING_REQUIRED_RELATION_ON_ENTITY);

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // No matching default element and no value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        ValidationException validationException1 =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException1, "optItem", Validator.ERROR_MISSING_REQUIRED_RELATION_ON_ENTITY);

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        itemTransferDao.delete(item);

        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        collectorDao.delete(collector);
        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        collectorDao.delete(collector);
        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleRequiredRelationRequiredTransferDao collectorWithSingleRequiredRelationRequiredTransferDao;

    // req - req

    @Test
    public void singleRequiredRelationRequiredTransfer() {
        // shortname
        CollectorWithSingleRequiredRelationRequiredTransferDao collectorDao = collectorWithSingleRequiredRelationRequiredTransferDao;

        // Empty query result for required reference: reqItem
        ValidationException thrown = assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationRequiredTransferForCreate.builder().build()));
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                  hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                  hasProperty("location", equalTo("reqItem")))
        ));

    }

    @Inject
    CollectorWithSingleRequiredRelationRequiredDefaultTransferDao collectorWithSingleRequiredRelationRequiredDefaultTransferDao;

    // req - req default

    @Test
    public void SingleOptionalRelationRequiredDefaultTransferTest() {
        // shortname
        CollectorWithSingleRequiredRelationRequiredDefaultTransferDao collectorDao = collectorWithSingleRequiredRelationRequiredDefaultTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleRequiredRelationRequiredDefaultTransfer collector = collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryReqItem(collector).identifier());

        // Default element is undefined and no value added during the creation
        collectorDao.delete(collector);
        itemTransferDao.delete(item);

        // Error
        ValidationException validationException =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException, "reqItem");

        // No matching default element and no value added during the creation // Error
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        ValidationException validationException1 =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException1, "reqItem");

        itemTransferDao.delete(item);
        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        collectorDao.delete(collector);
        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        collectorDao.delete(collector);
        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        collectorDao.delete(collector);
        itemTransferDao.delete(itemA1);

    }

    @Inject
    CollectorWithSingleOptionalDefaultRelationOptionalTransferDao collectorWithSingleOptionalDefaultRelationOptionalTransferDao;

    // Variations
    /*
    opt default - opt
    opt default - opt default
    opt default - req
    opt default - req default
     */

    // opt default - opt

    @Test
    public void SingleOptionalDefaultRelationOptionalTransferTest() {
        CollectorWithSingleOptionalDefaultRelationOptionalTransferDao collectorDao = collectorWithSingleOptionalDefaultRelationOptionalTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleOptionalDefaultRelationOptionalTransfer collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        // Default element is undefined and no value added during the creation
        itemTransferDao.delete(item);
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // No matching default element and no value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        itemTransferDao.delete(item);

        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferDao collectorWithSingleOptionalDefaultRelationOptionalDefaultTransferDao;

    // opt default - opt default

    @Test
    public void SingleOptionalDefaultRelationOptionalDefaultTransferTest() {
        CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferDao collectorDao = collectorWithSingleOptionalDefaultRelationOptionalDefaultTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A1").withNumber(1).build());
        CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransfer collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        // Default element is undefined and no value added during the creation
        itemTransferDao.delete(item);
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // No matching default element and no value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferForCreate.builder().build());

        //TODO should be null instead of entity default
        // TODO JNG-6106
        //assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        itemTransferDao.delete(item);

        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A1").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).filterByNumber(NumberFilter.equalTo(11)).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransferForCreate.builder()
                .withOptItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleOptionalDefaultRelationRequiredTransferDao collectorWithSingleOptionalDefaultRelationRequiredTransferDao;

    // opt default - req

    @Test
    public void SingleOptionalDefaultRelationRequiredTransferTest() {
        // shortname
        CollectorWithSingleOptionalDefaultRelationRequiredTransferDao collectorDao = collectorWithSingleOptionalDefaultRelationRequiredTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());

        CollectorWithSingleOptionalDefaultRelationRequiredTransfer collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryReqItem(collector).identifier());

        // Default element is undefined and no value added during the creation
        collectorDao.delete(collector);
        itemTransferDao.delete(item);

        // Error
        ValidationException validationException =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException, "reqItem");

        // No matching default element and no value added during the creation // Error
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        ValidationException validationException1 =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException1, "reqItem");

        itemTransferDao.delete(item);
        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        ItemTransferQueryCustomizer filterItemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).filterByNumber(NumberFilter.equalTo(11));
        ItemTransfer itemA1 = filterItemA1.selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        collectorDao.delete(collector);
        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = filterItemA1.selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = filterItemA1.selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
        itemTransferDao.delete(itemA1);

    }

    @Inject
    CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferDao collectorWithSingleOptionalDefaultRelationRequiredDefaultTransferDao;

    // opt default - req

    @Test
    public void SingleOptionalDefaultRelationRequiredDefaultTransferTest() {
        // shortname
        CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferDao collectorDao = collectorWithSingleOptionalDefaultRelationRequiredDefaultTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A1").withNumber(1).build());

        CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransfer collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryReqItem(collector).identifier());

        // Default element is undefined and no value added during the creation
        collectorDao.delete(collector);
        itemTransferDao.delete(item);

        // Error
        ValidationException validationException =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException, "reqItem");

        // No matching default element and no value added during the creation // Error
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        ValidationException validationException1 =
                assertThrows(ValidationException.class, () -> collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferForCreate.builder().build()));
        assertMissingRequiredRelation(validationException1, "reqItem");

        itemTransferDao.delete(item);
        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A1").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        ItemTransferQueryCustomizer filterItemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).filterByNumber(NumberFilter.equalTo(11));
        ItemTransfer itemA1 = filterItemA1.selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        collectorDao.delete(collector);
        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = filterItemA1.selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationRequiredDefaultTransferForCreate.builder()
                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );

        itemA1 = filterItemA1.selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
        itemTransferDao.delete(itemA1);

    }

    // TODO Should add after or during the default rework
    // TODO JNG-6105
    // Variations
    /*
    req default - opt
    req default - opt default
    req default - req
    req default - req default
     */

    private static void assertMissingRequiredRelation(ValidationException validationException, String name) {
        assertMissingRequiredRelation(validationException, name, Validator.ERROR_MISSING_REQUIRED_RELATION);
    }

    private static void assertMissingRequiredRelation(ValidationException validationException, String name, String code) {
        Collection<ValidationResult> validationResults = validationException.getValidationResults();
        assertEquals(1, validationResults.size());
        ValidationResult validationResult = validationResults.stream().findAny().orElseThrow();
        assertEquals(code, validationResult.getCode());
        assertEquals(ValidationResult.Level.ERROR, validationResult.getLevel());
        assertEquals(name, validationResult.getLocation());
    }

}
