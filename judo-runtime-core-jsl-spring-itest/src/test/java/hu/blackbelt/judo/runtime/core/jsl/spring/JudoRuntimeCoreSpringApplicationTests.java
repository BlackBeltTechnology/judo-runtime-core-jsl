package hu.blackbelt.judo.runtime.core.jsl.spring;

/*-
 * #%L
 * JUDO Runtime Core JSL :: Parent
 * %%
 * Copyright (C) 2018 - 2022 BlackBelt Technology
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

import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.Lead;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.Person;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel.SalesPerson;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.salesmodel.sdk.salesmodel.salesmodel._SalesPerson_leadsOver_Parameters;
import hu.blackbelt.judo.sdk.query.NumberFilter;
import hu.blackbelt.judo.sdk.query.StringFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

		assertEquals(Optional.of("Test"), createdSalesPerson.getFirstName());
		assertEquals(Optional.of("Elek"), createdSalesPerson.getLastName());

		List<SalesPerson> personList = salesPersonDao.query()
				.filterByFirstName(StringFilter.equalTo("Test"))
				.execute();

		assertEquals(1, personList.size());

		Lead lead1 = leadDao.create(Lead.builder()
				.withSalesPerson(createdSalesPerson)
				.build());
		assertEquals(Optional.of(100000), lead1.getValue());
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
		assertEquals(Optional.of(100000), leadListOfQuery.get(0).getValue());

		leadListOfQuery = salesPersonDao
				.queryLeads(createdSalesPerson)
				.filterByValue(NumberFilter.equalTo(100000))
				.execute();
		assertEquals(1, leadListOfQuery.size());
		assertEquals(Optional.of(100000), leadListOfQuery.get(0).getValue());

	}

}
