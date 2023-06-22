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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.entitywithsamequeryname.entitywithsamequeryname.entitya.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.entitywithsamequeryname.entitywithsamequeryname.entitya.querysamename.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.entitywithsamequeryname.entitywithsamequeryname.entityb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.entitywithsamequeryname.entitywithsamequeryname.entityb.querysamename.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.entitywithsamequeryname.entitywithsamequeryname.referenceentity.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.EntityWithSameQueryNameDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class EntityWithTheSameQueryNamesTest extends AbstractJslTest {

    @Inject
    EntityADao entityADao;

    @Inject
    EntityBDao entityBDao;

    @Inject
    ReferenceEntityDao referenceEntityDao;

    @Override
    public Module getModelDaoModule() {
        return new EntityWithSameQueryNameDaoModules();
    }

    @Override
    public String getModelName() {
        return "EntityWithSameQueryName";
    }

    /**
     * This test checks the entities with the same query name declaration.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel EntityWithSameQueryName.jsl
     *
     *
     * @scenario
     *
     *  Create an ReferenceEntity instance.
     *
     *  Create an EntityA instance.
     *
     *  Create an EntityB instance.
     *
     *  Check the entityA and entityB query with the same name parameter refer to the referenceEntity instance.
     *
     */
    @Test
    @TestCase("EntityWithTheSameQueryName")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SRV-006"
    })
    public void testEntityWithTheSameQueryName() {

        ReferenceEntity ref = referenceEntityDao.create(
                ReferenceEntity
                        .builder()
                        .withName("ReferenceEntity")
                        .build()
        );

        EntityA entA = entityADao.create(EntityA.builder().build());
        EntityB entB = entityBDao.create(EntityB.builder().build());

        assertEquals(ref.identifier(), entityADao.queryQuerySameName(entA, EntityAQuerySameNameParameter.builder().withName("ReferenceEntity").build()).orElseThrow().identifier() );
        assertEquals(ref.identifier(), entityBDao.queryQuerySameName(entB, EntityBQuerySameNameParameter.builder().withName("ReferenceEntity").build()).orElseThrow().identifier() );

    }

}
