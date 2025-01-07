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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelation.CollectorWithSingleRequiredDefaultRelation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelation.CollectorWithSingleRequiredDefaultRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.collectorwithsinglerequireddefaultrelation.CollectorWithSingleRequiredDefaultRelationForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.item.Item;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.item.ItemDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationwithdefaultsmodel.relationwithdefaultsmodel.item.ItemForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RelationWithDefaultsModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.Assert.assertEquals;

@Slf4j
public class EntityRelationWithDefaultsModelTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("RelationWithDefaultsModel", new RelationWithDefaultsModelDaoModules());

    @Inject
    ItemDao itemDao;

    @Inject
    CollectorWithSingleOptionalDefaultRelationDao collectorWithSingleOptionalDefaultRelationDao;

    @Test
    @Disabled
    public void singleOptionalDefaultRelationTest() {
        // shortname
        CollectorWithSingleOptionalDefaultRelationDao collectorDao = collectorWithSingleOptionalDefaultRelationDao;
        Item item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(1).build());

        CollectorWithSingleOptionalDefaultRelation collector = collectorDao.create(CollectorWithSingleOptionalDefaultRelationForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryOptItem(collector).orElseThrow().identifier());

    }

    @Inject
    CollectorWithSingleRequiredDefaultRelationDao collectorWithSingleRequiredDefaultRelationDao;

    @Test
    @Disabled
    public void singleRequiredDefaultRelationTest() {
        // shortname
        CollectorWithSingleRequiredDefaultRelationDao collectorDao = collectorWithSingleRequiredDefaultRelationDao;
        Item item = itemDao.create(ItemForCreate.builder().withName("A").withNumber(1).build());

        CollectorWithSingleRequiredDefaultRelation collector = collectorDao.create(CollectorWithSingleRequiredDefaultRelationForCreate.builder().build());

        assertEquals(item.identifier(), collectorDao.queryReqItem(collector).identifier());

    }

}
