package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.jsl.itest.taskmodel.guice.taskmodel.TaskModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.taskmodel.sdk.taskmodel.taskmodel.*;
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
    Person.PersonDao personDao;

    @Inject
    Task.TaskDao taskDao;

    @Inject
    SalesPerson.SalesPersonDao salesPersonDao;

    @Inject
    MarketPlace.MarketPlaceDao marketPlaceDao;

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
    public void testSumDirivedInSumDirived() {

        Person person1 = personDao.create(Person.builder().withFirstName("Adam").build());
        Workplace blackbelt = personDao.createWorkplace(person1, Workplace.builder().withName("Blackbelt").withAddress("Ganz utca 2").build());
        Person person2 = personDao.create(Person.builder().withFirstName("Patrik").withWorkplace(blackbelt).build());

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
