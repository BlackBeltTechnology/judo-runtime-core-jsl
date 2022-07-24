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
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodelcontract.Contract;
import hu.blackbelt.judo.sdk.query.NumberFilter;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Inject
    Contract.ContractDao contractDao;

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

        assertEquals(Optional.of("Test"), createdSalesPerson.getFirstName());
        assertEquals(Optional.of("Elek"), createdSalesPerson.getLastName());

        List<SalesPerson> personList = salesPersonDao.query()
                        .filterByFirstName(StringFilter.equalTo("Test"))
                .execute();

        assertEquals(1, personList.size());

        Person createdPerson = personDao.create(Person.builder()
                .withFirstName("Masik")
                .withLastName("Test")
                .build());

        assertEquals(Optional.of("Masik"), createdPerson.getFirstName());
        assertEquals(Optional.of("Test"), createdPerson.getLastName());
        assertEquals(Optional.of("Masik Test"), createdPerson.getFullName());

        Lead lead1 = leadDao.create(Lead.builder()
                .withSalesPerson(createdSalesPerson)
                .withValue(100)
                .build());
        assertEquals(Optional.of(100), lead1.getValue());
        assertEquals(Optional.of("Test"), leadDao.getSalesPerson(lead1).getFirstName());

        Lead lead2 = leadDao.create(Lead.builder()
                .withSalesPerson(createdSalesPerson)
                .withValue(9)
                .build());
        assertEquals(Optional.of(9), lead2.getValue());
        assertEquals(Optional.of("Test"), leadDao.getSalesPerson(lead2).getFirstName());

        List<Lead> leadListOfQuery = salesPersonDao
                .queryLeadsOver(createdSalesPerson, _SalesPerson_leadsOver_Parameters.builder()
                        .withLimit(10)
                        .build())
                .execute();
        assertEquals(1, leadListOfQuery.size());
        assertEquals(Optional.of(100), leadListOfQuery.get(0).getValue());

        leadListOfQuery = salesPersonDao
                .queryLeads(createdSalesPerson)
                .filterByValue(NumberFilter.equalTo(100))
                .execute();
        assertEquals(1, leadListOfQuery.size());
        assertEquals(Optional.of(100), leadListOfQuery.get(0).getValue());

        List<Lead> leadsBetween = salesPersonDao.queryLeadsBetween(createdSalesPerson).execute();

        assertEquals(1, leadsBetween.size());

        salesPersonDao.addLeads(createdSalesPerson, List.of(lead1, lead2));

        createdSalesPerson = salesPersonDao.getById(createdSalesPerson.get__identifier());
        assertEquals(Optional.of(2), createdSalesPerson.getNumberOfLeads());
    }

    @Test
    public void testDefaultValue() {
        SalesPerson createdSalesPerson = salesPersonDao.create(SalesPerson.builder()
                .build());

        Lead lead = leadDao.create(Lead.builder()
                .withSalesPerson(createdSalesPerson)
                .build());

        assertEquals(Optional.of(100000), lead.getValue());
    }

    @Test
    public void testImportedEntityType() {
        Contract contract = contractDao.create(Contract.builder().withCreationDate(LocalDate.parse("2022-07-21")).build());

        SalesPerson createdSalesPerson = salesPersonDao.create(SalesPerson.builder()
                .withContracts(List.of(contract))
                .build());

        List<Contract> checkContracts = salesPersonDao.getContracts(createdSalesPerson);
        Contract checkContract = checkContracts.get(0);

        assertEquals(1, checkContracts.size());
        assertEquals(contract, checkContract);
        assertEquals(Optional.of(LocalDate.parse("2022-07-21")), checkContract.getCreationDate());
    }
}