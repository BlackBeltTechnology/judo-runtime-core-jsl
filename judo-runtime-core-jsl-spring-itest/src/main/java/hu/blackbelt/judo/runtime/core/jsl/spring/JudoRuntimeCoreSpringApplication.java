package hu.blackbelt.judo.runtime.core.jsl.spring;

import hu.blackbelt.judo.dispatcher.api.Dispatcher;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.Person;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.SalesPerson;
import hu.blackbelt.judo.sdk.query.StringFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class JudoRuntimeCoreSpringApplication {

	@Autowired
	Dispatcher dispatcher;

	@Autowired
	Person.PersonDao personDao;

	@Autowired
	SalesPerson.SalesPersonDao salesPersonDao;

	public static void main(String[] args) {
		SpringApplication.run(JudoRuntimeCoreSpringApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void ready() {

		SalesPerson createdSalesPerson = salesPersonDao.create(SalesPerson.builder()
				.withFirstName("Test")
				.withLastName("Elek")
				.build());
		System.out.println(createdSalesPerson);

		List<SalesPerson> personList = salesPersonDao.search()
				.filterByFirstName(StringFilter.equalTo("Test"))
				.execute();
		System.out.println(personList);

		Person createdPerson = personDao.create(Person.builder()
				.withFirstName("Masik")
				.withLastName("Test")
				.build());
		System.out.println(createdPerson);

	}
}
