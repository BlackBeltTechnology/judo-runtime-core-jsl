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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelation.CollectorWithSingleOptionalDefaultRelation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelation.CollectorWithSingleOptionalDefaultRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelation.CollectorWithSingleOptionalDefaultRelationForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationextended.CollectorWithSingleOptionalDefaultRelationExtended;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationextended.CollectorWithSingleOptionalDefaultRelationExtendedDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsingleoptionaldefaultrelationextended.CollectorWithSingleOptionalDefaultRelationExtendedForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelation.CollectorWithSingleRequiredDefaultRelation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelation.CollectorWithSingleRequiredDefaultRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelation.CollectorWithSingleRequiredDefaultRelationForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelationextended.CollectorWithSingleRequiredDefaultRelationExtended;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelationextended.CollectorWithSingleRequiredDefaultRelationExtendedDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelationextended.CollectorWithSingleRequiredDefaultRelationExtendedForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.item.Item;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.item.ItemDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.item.ItemForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RelationWithDefaultsModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.Assert.*;

@Slf4j
public class EntityRelationWithDefaultsModelTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("RelationWithDefaultsModel", new RelationWithDefaultsModelDaoModules());

    @Inject
    ItemDao itemDao;

    @Inject
    CollectorWithSingleOptionalDefaultRelationDao collectorWithSingleOptionalDefaultRelationDao;

    @Test
    public void singleOptionalDefaultRelationTest() {
        // shortname
        CollectorWithSingleOptionalDefaultRelationDao collectorDao = collectorWithSingleOptionalDefaultRelationDao;

        // The Default element is defined and no value added during the creation
        Item item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleOptionalDefaultRelation collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemDao.delete(item);

        // Default element is undefined and no value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // No matching default element and no value added during the creation
        item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationForCreate.builder()
                .withOptItem(Item.builder().withName("A1").withNumber(5).build())
                .build()
        );

        Item itemA1 = itemDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemDao.delete(itemA1);

        // Default element is not undefined and value added during the creation
        item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationForCreate.builder()
                .withOptItem(Item.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemDao.deleteAll(item, itemA1);

        // No matching default element and value added during the creation
        item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationForCreate.builder()
                .withOptItem(Item.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleRequiredDefaultRelationDao collectorWithSingleRequiredDefaultRelationDao;

    @Test
    public void singleRequiredDefaultRelationTest() {
        // shortname
        CollectorWithSingleRequiredDefaultRelationDao collectorDao = collectorWithSingleRequiredDefaultRelationDao;
        // Default element is not undefined and no value added during the creation
        Item item = itemDao
                .create(ItemForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleRequiredDefaultRelation collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryReqItem(collector).identifier());

        // Default element is undefined and no value added during the creation
        collectorDao.delete(collector);
        itemDao
                .delete(item);

        // Error
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleRequiredDefaultRelationForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

        // No matching default element and no value added during the creation // Error
        item = itemDao
                .create(ItemForCreate.builder().withName("A").withNumber(5).build());
        thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleRequiredDefaultRelationForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

        itemDao
                .delete(item);
        // Default element is not undefined and value added during the creation
        item = itemDao
                .create(ItemForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationForCreate.builder()
                .withReqItem(Item.builder().withName("A1").withNumber(11).build())
                .build()
        );

        Item itemA1 = itemDao
                .query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        collectorDao.delete(collector);
        itemDao
                .deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
//        collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationForCreate.builder()
//                .withReqItem(Item.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemDao
//        .query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemDao
//        .delete(itemA1);

        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
        // No matching default element and value added during the creation
//        item = itemDao
//        .create(ItemForCreate.builder().withName("A").withNumber(5).build());
//        collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationForCreate.builder()
//                .withReqItem(Item.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemDao
//        .query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemDao.delete(itemA1);
    }

    // Inherited default on entity
    @Inject
    CollectorWithSingleOptionalDefaultRelationExtendedDao collectorWithSingleOptionalDefaultRelationExtendedDao;

    @Test
    public void singleOptionalDefaultRelationInheritedTest() {
        // shortname
        CollectorWithSingleOptionalDefaultRelationExtendedDao collectorDao = collectorWithSingleOptionalDefaultRelationExtendedDao;

        // Default element is not undefined and no value added during the creation
        Item item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleOptionalDefaultRelationExtended collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationExtendedForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemDao.delete(item);

        // Default element is undefined and no value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationExtendedForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // No matching default element and no value added during the creation
        item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationExtendedForCreate.builder().build());

        assertTrue(collectorDao.queryOptItem(collector).isEmpty());

        // Default element is undefined and value added during the creation
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationExtendedForCreate.builder()
                .withOptItem(Item.builder().withName("A1").withNumber(5).build())
                .build()
        );

        Item itemA1 = itemDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemDao.delete(itemA1);

        // Default element is not undefined and value added during the creation
        item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationExtendedForCreate.builder()
                .withOptItem(Item.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

        itemDao.deleteAll(item, itemA1);

        // No matching default element and value added during the creation
        item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(5).build());
        collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationExtendedForCreate.builder()
                .withOptItem(Item.builder().withName("A1").withNumber(11).build())
                .build()
        );
        itemA1 = itemDao.query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleRequiredDefaultRelationExtendedDao collectorWithSingleRequiredDefaultRelationExtendedDao;

    @Test
    public void singleRequiredDefaultRelationInheritedTest() {
        // shortname
        CollectorWithSingleRequiredDefaultRelationExtendedDao collectorDao = collectorWithSingleRequiredDefaultRelationExtendedDao;

        // Default element is not undefined and no value added during the creation
        Item item = itemDao
                .create(ItemForCreate.builder().withName("A").withNumber(1).build());
        CollectorWithSingleRequiredDefaultRelationExtended collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationExtendedForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryReqItem(collector).identifier());

        // Default element is undefined and no value added during the creation
        collectorDao.delete(collector);
        itemDao
                .delete(item);

        // Error
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleRequiredDefaultRelationExtendedForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

        // No matching default element and no value added during the creation // Error
        item = itemDao
                .create(ItemForCreate.builder().withName("A").withNumber(5).build());
        thrown = assertThrows(IllegalStateException.class, () -> collectorDao.create(CollectorWithSingleRequiredDefaultRelationExtendedForCreate.builder().build()));
        assertTrue(thrown.getMessage().contains("Default reference value is undefined"));

        itemDao
                .delete(item);
        // Default element is not undefined and value added during the creation
        item = itemDao
                .create(ItemForCreate.builder().withName("A").withNumber(1).build());
        collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationExtendedForCreate.builder()
                .withReqItem(Item.builder().withName("A1").withNumber(11).build())
                .build()
        );

        Item itemA1 = itemDao
                .query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());

        collectorDao.delete(collector);
        itemDao
                .deleteAll(item, itemA1);

        // Default element is undefined and value added during the creation
        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
//        collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationExtendedForCreate.builder()
//                .withReqItem(Item.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemDao
//        .query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemDao
//        .delete(itemA1);

        // TODO Default is undefined, IllegalStateException Default reference value is undefined
        // TODO JNG-4194
        // No matching default element and value added during the creation
//        item = itemDao
//        .create(ItemForCreate.builder().withName("A").withNumber(5).build());
//        collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationExtendedForCreate.builder()
//                .withReqItem(Item.builder().withName("A1").withNumber(11).build())
//                .build()
//        );
//
//        itemA1 = itemDao
//        .query().filterByName(StringFilter.equalTo("A1")).selectOne().orElseThrow();
//        assertEquals(itemA1.identifier(), collectorDao.queryReqItem(collector).identifier());
//        itemDao.delete(itemA1);

    }

}
