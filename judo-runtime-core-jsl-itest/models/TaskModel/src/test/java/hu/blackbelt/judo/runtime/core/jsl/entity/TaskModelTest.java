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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.marketplace.MarketPlace;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.marketplace.MarketPlaceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.marketplace.MarketPlaceForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.person.Person;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.person.PersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.person.PersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.salesperson.SalesPerson;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.salesperson.SalesPersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.salesperson.SalesPersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.task.TaskDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.task.TaskForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.workplace.Workplace;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.workplace.WorkplaceForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TaskModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TaskModelTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TaskModel", new TaskModelDaoModules());

    @Inject
    PersonDao personDao;

    @Inject
    TaskDao taskDao;

    @Inject
    SalesPersonDao salesPersonDao;

    @Inject
    MarketPlaceDao marketPlaceDao;

    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4150")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022"
    })
    public void testSumDerivedWithOtherDerived() {

        MarketPlace marketPlace = marketPlaceDao.create(MarketPlaceForCreate.builder().build());

        SalesPerson person1 = salesPersonDao.create(SalesPersonForCreate.builder().build());
        SalesPerson person2 = salesPersonDao.create(SalesPersonForCreate.builder().build());

        List<SalesPerson> list = new ArrayList<>(Arrays.asList(person1, person2));

        marketPlaceDao.addSalesPersons(marketPlace, list);
        salesPersonDao.setWorkplace(person1, marketPlace);
        salesPersonDao.setWorkplace(person2, marketPlace);

        marketPlace = marketPlaceDao.getById(marketPlace.identifier()).orElseThrow();

        assertEquals(Optional.of(2), marketPlace.getAllTime());

    }


    @Test
    @Disabled("https://blackbelt.atlassian.net/browse/JNG-4150")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022"
    })
    public void testSumDerivedInSumDerived() {

        Person person1 = personDao.create(PersonForCreate.builder().withFirstName("Adam").build());
        Workplace blackbelt = personDao.createWorkplace(person1, WorkplaceForCreate.builder().withName("Blackbelt").withAddress("Ganz utca 2").build());
        Person person2 = personDao.create(PersonForCreate.builder().withFirstName("Patrik")
                .withWorkplace(blackbelt)
                .build());

        personDao.createTasks(person1, new ArrayList<>(Arrays.asList(
                TaskForCreate.builder().withName("Build").withTaskTimeInDay(2).build(),
                TaskForCreate.builder().withName("Architect").withTaskTimeInDay(3).build(),
                TaskForCreate.builder().withName("Think").withTaskTimeInDay(5).build()
        )));

        personDao.createTasks(person2, new ArrayList<>(Arrays.asList(
                TaskForCreate.builder().withName("Drink").withTaskTimeInDay(1).build(),
                TaskForCreate.builder().withName("Architect").withTaskTimeInDay(5).build()
        )));


        person1 = personDao.getById(person1.identifier()).orElseThrow();

        assertEquals(Optional.of(10), person1.getAllTaskLong());
        assertEquals(Optional.of(16), blackbelt.getAllTime());
    }

}
