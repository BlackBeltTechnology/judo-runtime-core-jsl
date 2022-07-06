package hu.blackbelt.judo.runtime.core.jsl.spring;

import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.Lead;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.Person;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.SalesPerson;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel._SalesPerson_leadsOver_Parameters;
import hu.blackbelt.judo.sdk.query.StringFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JudoRuntimeCoreSpringApplicationTests {


	@Autowired
	Person.PersonDao personDao;

	@Autowired
	SalesPerson.SalesPersonDao salesPersonDao;

	@Autowired
	Lead.LeadDao leadDao;

	@Test
	void testDaoFunctions() {

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
				.build());
		assertEquals(100000, lead1.getValue());
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
		assertEquals(100000, leadListOfQuery.get(0).getValue());

	}

}
