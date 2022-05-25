package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.dao.api.DAO;
import hu.blackbelt.judo.dispatcher.api.Dispatcher;
import hu.blackbelt.judo.dispatcher.api.Sequence;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelHolder;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.salesmodel.daoprovider.salesmodel.SalesModelDaoModules;
import hu.blackbelt.judo.runtime.salesmodel.sdk.salesmodel.salesmodel.Person;
import hu.blackbelt.judo.runtime.salesmodel.sdk.salesmodel.salesmodel.SalesPerson;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SalesModelTest {

    Injector injector;

    @Inject
    SalesPerson.SalesPersonDao salesPersonDao;

    @Inject
    Person.PersonDao personDao;

    @BeforeEach
    void init() throws Exception {
        JudoModelHolder modelHolder = JudoModelHolder.
                loadFromURL("SalesModel", new File("target/model").toURI(), new HsqldbDialect());

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new SalesModelDaoModules(),
                new JudoDefaultModule(this, modelHolder));

    }

    @Test
    public void test() {
        SalesPerson createdSalesPerson = salesPersonDao.create(SalesPerson.builder()
                        .withFirstName("Test")
                        .withLastName("Elek")
                        .build());

        assertEquals("Test", createdSalesPerson.getFirstName());
        assertEquals("Elek", createdSalesPerson.getLastName());

        List<SalesPerson> personList = salesPersonDao.search()
                        .filterByFirstName(StringFilter.equalTo("Test"))
                .execute();

        assertEquals(1, personList.size());

        Person createdPerson = personDao.create(Person.builder()
                .withFirstName("Masik")
                .withLastName("Test")
                .build());

        assertEquals("Masik", createdPerson.getFirstName());
        assertEquals("Test", createdPerson.getLastName());

    }
}