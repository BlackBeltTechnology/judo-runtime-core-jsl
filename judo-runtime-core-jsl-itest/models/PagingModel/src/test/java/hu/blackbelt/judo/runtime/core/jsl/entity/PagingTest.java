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

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.dao.api.DAO;
import hu.blackbelt.judo.dao.api.Payload;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.item.Item;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.item.ItemBuilder;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.item.ItemDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.list.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.paging.paging.list.List;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.PagingDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
public class PagingTest extends AbstractJslTest {
    @Inject
    ItemDao itemDao;

    @Inject
    ListDao listDao;

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);

        final Map<Character, UUID> listIds = new HashMap<>();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            final java.util.List<Item> items = new ArrayList<>();
            for (int i = 0; i < 'Z' - ch + 1; i++) {
                final String topic;
                if (ch < 'E' && i < 3) {
                    topic = "Topic" + (char) (ch + i);
                } else {
                    topic = null;
                }
                items.add(Item.builder().withNumber(i + 1).withTopic(topic).build());
            }
            List list = listDao.create(List.builder().withName("List_"+ch).withItems(items).build());

            listIds.put(ch, (UUID)list.identifier().getIdentifier());
        }



    }

    @Override
    public Module getModelDaoModule() {
        return new PagingDaoModules();
    }

    @Override
    public String getModelName() {
        return "Paging";
    }

    @Test
    public void testFilterWithMultipleFilters() {

    }
}
