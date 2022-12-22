package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.marketplace.MarketPlace;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.marketplace.MarketPlaceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.person.Person;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.person.PersonAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.person.PersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.salesperson.SalesPerson;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.salesperson.SalesPersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.task.Task;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.task.TaskDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.taskmodel.taskmodel.workplace.Workplace;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TaskModelDaoModules;
import hu.blackbelt.judo.test.Requirement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TaskModelTest extends AbstractJslTest {

    @Inject
    PersonDao personDao;

    @Inject
    TaskDao taskDao;

    @Inject
    SalesPersonDao salesPersonDao;

    @Inject
    MarketPlaceDao marketPlaceDao;

    @Override
    public Module getModelDaoModule() {
        return new TaskModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "TaskModel";
    }

    @Test
    @Disabled("JNG-4150")
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
    public void testSumDirivedWithOtherDirived() {

        MarketPlace marketPlace = marketPlaceDao.create(MarketPlace.builder().build());

        SalesPerson person1 = salesPersonDao.create(SalesPerson.builder().build());
        SalesPerson person2 = salesPersonDao.create(SalesPerson.builder().build());

        List<SalesPerson> list = new ArrayList<>(Arrays.asList(person1, person2));

        marketPlaceDao.addSalesPersons(marketPlace, list);
        salesPersonDao.setWorkplace(person1, marketPlace);
        salesPersonDao.setWorkplace(person2, marketPlace);

        marketPlace = marketPlaceDao.getById(marketPlace.get__identifier()).orElseThrow();

        assertEquals(Optional.of(2), marketPlace.getAllTime());

    }


    @Test
    @Disabled("JNG-4150")
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
    public void testSumDirivedInSumDirived() {

        Person person1 = personDao.create(Person.builder().withFirstName("Adam").build());
        Workplace blackbelt = personDao.createWorkplace(person1, Workplace.builder().withName("Blackbelt").withAddress("Ganz utca 2").build());
        Person person2 = personDao.create(Person.builder().withFirstName("Patrik").build(), PersonAttachedRelationsForCreate.builder()
                .withWorkplace(blackbelt)
                .build());

        personDao.createTasks(person1, new ArrayList<>(Arrays.asList(
                taskDao.create(Task.builder().withName("Build").withTaskTimeInDay(2).build()),
                taskDao.create(Task.builder().withName("Architect").withTaskTimeInDay(3).build()),
                taskDao.create(Task.builder().withName("Think").withTaskTimeInDay(5).build()
                ))));

        personDao.createTasks(person2, new ArrayList<>(Arrays.asList(
                taskDao.create(Task.builder().withName("Drink").withTaskTimeInDay(1).build()),
                taskDao.create(Task.builder().withName("Architect").withTaskTimeInDay(5).build()
                ))));


        person1 = personDao.getById(person1.get__identifier()).orElseThrow();

        assertEquals(Optional.of(10), person1.getAllTaskLong());
        assertEquals(Optional.of(16), blackbelt.getAllTime());
    }

}
