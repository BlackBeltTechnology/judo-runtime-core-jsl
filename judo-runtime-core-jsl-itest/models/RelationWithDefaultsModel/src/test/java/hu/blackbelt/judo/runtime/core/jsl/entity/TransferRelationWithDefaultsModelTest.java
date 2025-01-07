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

import com.google.inject.Inject;
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequiredrelationoptionaltransfer.CollectorWithSingleRequiredRelationOptionalTransfer;
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RelationWithDefaultsModelDaoModules;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@Slf4j
public class TransferRelationWithDefaultsModelTest {

    // TODO All possibilities https://docs.google.com/spreadsheets/d/18rggGdM1DrjUBQRuq5Y1z1M_8n587W0DgPY_1iZJhAI/edit?gid=0#gid=0

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("RelationWithDefaultsModel", new RelationWithDefaultsModelDaoModules());

    @Inject
    ItemTransferDao itemTransferDao;

    // Variations
    /*
    op - op
    op - op default
    op - req
    op - req default
     */

    @Inject
    CollectorWithSingleOptionalRelationOptionalTransferDao collectorWithSingleOptionalRelationOptionalTransferDao;

    // op - op

    @Test
    public void singleOptionalRelationOptionalTransferTest() {
        // shortname
        CollectorWithSingleOptionalRelationOptionalTransferDao collectorDao = collectorWithSingleOptionalRelationOptionalTransferDao;

        CollectorWithSingleOptionalRelationOptionalTransfer collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOpItem(collector).isEmpty());

    }

    @Inject
    CollectorWithSingleOptionalRelationOptionalDefaultTransferDao collectorWithSingleOptionalDefaultRelationTransferDao;


    // op - op default

    @Test
    public void singleOptionalRelationOptionalDefaultTransferTest() {
        // shortname
        CollectorWithSingleOptionalRelationOptionalDefaultTransferDao collectorDao = collectorWithSingleOptionalDefaultRelationTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleOptionalRelationOptionalDefaultTransfer collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

        // Default element is undefined and no value added during the creation
        itemTransferDao.delete(item);
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOpItem(collector).isEmpty());

        // No matching default element and no value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOpItem(collector).isEmpty());

        itemTransferDao.delete(item);

        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder()
                .withOpItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder()
                .withOpItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalRelationOptionalDefaultTransferForCreate.builder()
                .withOpItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleOptionalRelationRequiredTransferDao singleOptionalRelationRequiredTransferDao;

    // op - req

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

    // op - req default

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
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

        // No matching default element and no value added during the creation // Error
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

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
        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
//        collector = collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder()
//                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemTransferDao.delete(itemA1);

        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
        // No matching default element and value added during the creation
//        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
//        collector = collectorDao.create(CollectorWithSingleOptionalRelationRequiredDefaultTransferForCreate.builder()
//                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemTransferDao.delete(itemA1);

    }

    // Variations
    /*
    req - op
    req - op default
    req - req
    req - req default
     */

    @Inject
    CollectorWithSingleRequiredRelationOptionalTransferDao collectorWithSingleRequiredRelationOptionalTransferDao;

    @Inject
    CollectorWithSingleRequiredRelationDao collectorWithSingleRequiredRelationDao;

    // req - op

    // TODO: Creation should throw an error if the required entity relation remains empty in the transfer optional.
    //  @Test
    @Disabled()
    public void singleRequiredRelationOptionalTransfer() {
        // shortname
        CollectorWithSingleRequiredRelationOptionalTransferDao collectorDao = collectorWithSingleRequiredRelationOptionalTransferDao;

        // Should throw error
        CollectorWithSingleRequiredRelationOptionalTransfer collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalTransferForCreate.builder().build());

        assertTrue(collectorDao.queryOpItem(collector).isEmpty());

        // Empty query result for required reference: reqItem
        collectorWithSingleRequiredRelationDao.queryReqItem((UUID) collector.identifier().getIdentifier());

    }


    @Inject
    CollectorWithSingleRequiredRelationOptionalDefaultTransferDao collectorWithSingleRequiredRelationOptionalDefaultTransferDao;

    // req - op default

    @Test
    public void singleRequiredRelationOptionalDefaultTransferTest() {
        // shortname
        CollectorWithSingleRequiredRelationOptionalDefaultTransferDao collectorDao = collectorWithSingleRequiredRelationOptionalDefaultTransferDao;

        // Default element is not undefined and no value added during the creation
        ItemTransfer item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleRequiredRelationOptionalDefaultTransfer collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

        // Default element is undefined and no value added during the creation
        collectorDao.delete(collector);
        itemTransferDao.delete(item);
        // TODO Should throw error
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder().build());

        //assertTrue(collectorDao.queryOpItem(collector).isEmpty());
        // Empty query result for required reference: reqItem
        //collectorWithSingleRequiredRelationDao.queryReqItem((UUID) collector.identifier().getIdentifier());

        // No matching default element and no value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        // TODO Should throw error
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder().build());

        //assertTrue(collectorDao.queryOpItem(collector).isEmpty());
        //collectorWithSingleRequiredRelationDao.queryReqItem((UUID) collector.identifier().getIdentifier());

        itemTransferDao.delete(item);

        // Default element is not undefined and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder()
                .withOpItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        ItemTransfer itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

        collectorDao.delete(collector);
        itemTransferDao.deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder()
                .withOpItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

        collectorDao.delete(collector);
        itemTransferDao.delete(itemA1);

        // No matching default element and value added during the creation
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleRequiredRelationOptionalDefaultTransferForCreate.builder()
                .withOpItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOpItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleRequiredRelationRequiredTransferDao collectorWithSingleRequiredRelationRequiredTransferDao;

    // req - req

    @Test
    @Disabled()
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
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

        // No matching default element and no value added during the creation // Error
        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
        thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

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
        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
//        collector = collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder()
//                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemTransferDao.delete(itemA1);

        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
        // No matching default element and value added during the creation
//        item = itemTransferDao.create(ItemTransferForCreate.builder().withName("A").withNumber(5).build());
//        collector = collectorDao.create(CollectorWithSingleRequiredRelationRequiredDefaultTransferForCreate.builder()
//                .withReqItem(ItemTransfer.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemTransferDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemTransferDao.delete(itemA1);

    }

}
