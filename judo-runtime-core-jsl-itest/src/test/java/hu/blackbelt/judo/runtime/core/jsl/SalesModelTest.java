package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.guice.salesmodel.SalesModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodel.Lead;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodel.Person;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodel.SalesPerson;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodel._SalesPerson_leadsOver_Parameters;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SalesModelTest {

    Injector injector;

    @Inject
    SalesPerson.SalesPersonDao salesPersonDao;

    @Inject
    Person.PersonDao personDao;

    @Inject
    Lead.LeadDao leadDao;

    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("SalesModel", SalesModelTest.class.getClassLoader(), new HsqldbDialect(), true);

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

        List<SalesPerson> personList = salesPersonDao.query()
                        .filterByFirstName(StringFilter.equalTo("Test"))
                .execute();

        assertEquals(1, personList.size());

        Person createdPerson = personDao.create(Person.builder()
                .withFirstName("Masik")
                .withLastName("Test")
                .build());

        assertEquals("Masik", createdPerson.getFirstName());
        assertEquals("Test", createdPerson.getLastName());

        Lead lead1 = leadDao.create(Lead.builder()
                .withSalesPerson(createdSalesPerson)
                .withValue(100)
                .build());
        assertEquals(100, lead1.getValue());
        assertEquals("Test", leadDao.getSalesPerson(lead1).getFirstName());

        Lead lead2 = leadDao.create(Lead.builder()
                .withSalesPerson(createdSalesPerson)
                .withValue(9)
                .build());
        assertEquals(9, lead2.getValue());
        assertEquals("Test", leadDao.getSalesPerson(lead2).getFirstName());

        List<Lead> leadListOfQuery = salesPersonDao
                .queryLeadsOver(createdSalesPerson)
                .execute(_SalesPerson_leadsOver_Parameters.builder()
                        .withLimit(10)
                        .build());
        assertEquals(1, leadListOfQuery.size());
        assertEquals(100, leadListOfQuery.get(0).getValue());


    }
}