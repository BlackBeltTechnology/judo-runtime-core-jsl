package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.guice.salesmodel.SalesModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodel.Person;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodel.SalesPerson;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class SalesModel {

    Injector injector;

    @Inject
    SalesPerson.SalesPersonDao salesPersonDao;

    @Inject
    Person.PersonDao personDao;

    void init() throws Exception {

        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("SalesModel", this.getClass().getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new SalesModelDaoModules(),
                new JudoDefaultModule(this, modelHolder));

    }

    public void run() {
        SalesPerson createdSalesPerson = salesPersonDao.create(SalesPerson.builder()
                        .withFirstName("Test")
                        .withLastName("Elek")
                        .build());

        List<SalesPerson> personList = salesPersonDao.query()
                        .filterByFirstName(StringFilter.equalTo("Test"))
                .execute();

        Person createdPerson = personDao.create(Person.builder()
                .withFirstName("Masik")
                .withLastName("Test")
                .build());
    }

    public static void main(String[] args) throws Exception {
        SalesModel salesModel = new SalesModel();
        salesModel.init();
        salesModel.run();
    }
}