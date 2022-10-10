package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.A;

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
    Workplace.WorkplaceDao workplaceDao;

    @Inject
    Task.TaskDao taskDao;

    @Inject
    SalerPerson.SalerPersonDao salerPersonDao;

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

        MarketPlace marketPlace=marketPlaceDao.create(MarketPlace.builder().build());

        SalerPerson person1=salerPersonDao.create(SalerPerson.builder().build());
        SalerPerson person2=salerPersonDao.create(SalerPerson.builder().build());

        List<SalerPerson> list= new ArrayList<>(Arrays.asList(person1,person2));

        marketPlaceDao.addSalerpersons(marketPlace,list);
        salerPersonDao.setWorkplace(person1,marketPlace);
        salerPersonDao.setWorkplace(person2,marketPlace);


        assertEquals(2,marketPlace.getAllTime());

    }


    @Test
    @Disabled("JNG-4150")
    public void testSumDirivedInSumDirived() {

        Person person1=personDao.create(Person.builder().withFirstName("Adam").build());
        Workplace blackbelt=personDao.createWorkplace(person1, Workplace.builder().withName("Blackbelt").withAddres("Ganz utca 2").build());
        Person person2=personDao.create(Person.builder().withFirstName("Patrik").withWorkplace(blackbelt).build());

        personDao.createTasks(person1,new ArrayList<>(Arrays.asList(
                taskDao.create(Task.builder().withName("Build").withTaskTimeInDay(2).build()),
                taskDao.create(Task.builder().withName("Architect").withTaskTimeInDay(3).build()),
                taskDao.create(Task.builder().withName("Think").withTaskTimeInDay(5).build()
                ))));

        personDao.createTasks(person2,new ArrayList<>(Arrays.asList(
                taskDao.create(Task.builder().withName("Drink").withTaskTimeInDay(1).build()),
                taskDao.create(Task.builder().withName("Architect").withTaskTimeInDay(5).build()
                ))));

        person1= personDao.update(person1);

        assertEquals(Optional.of(10),person1.getAllTaskLong());
        assertEquals(16,blackbelt.getAllTime());
    }

}
