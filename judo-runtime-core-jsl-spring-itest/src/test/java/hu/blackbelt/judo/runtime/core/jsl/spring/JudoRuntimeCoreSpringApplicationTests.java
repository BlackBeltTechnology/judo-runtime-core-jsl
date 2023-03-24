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

import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.lead.Lead;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.lead.LeadAttachedRelationsForCreate;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.lead.LeadDao;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.leadsover.LeadsOverParameter;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.person.PersonDao;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.rootallleads.RootAllLeadsDao;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.rootonelead.RootOneLeadDao;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.salesperson.SalesPerson;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.salesperson.SalesPersonDao;
import hu.blackbelt.judo.runtime.core.jsl.spring.test.api.salesmodel.salesmodel.totalnumberofleads.TotalNumberOfLeadsDao;
import hu.blackbelt.judo.sdk.query.NumberFilter;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableTransactionManagement
@Slf4j
class JudoRuntimeCoreSpringApplicationTests {

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    PersonDao personDao;

    @Autowired
    SalesPersonDao salesPersonDao;

    @Autowired
    LeadDao leadDao;

    @Autowired
    RootAllLeadsDao rootAllLeadsDao;

    @Autowired
    RootOneLeadDao rootOneLeadDao;

    @Autowired
    TotalNumberOfLeadsDao totalNumberOfLeadsDao;


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

        Lead lead1 = leadDao.create(Lead.builder().build(),
                LeadAttachedRelationsForCreate.builder()
                        .withSalesPerson(createdSalesPerson)
                        .build());

        assertEquals(Optional.of(100000), lead1.getValue());
        assertEquals(Optional.of("Test"), leadDao.querySalesPerson(lead1).getFirstName());

        Lead lead2 = leadDao.create(
                Lead.builder()
                        .withValue(9)
                        .build(),
                LeadAttachedRelationsForCreate.builder()
                        .withSalesPerson(createdSalesPerson)
                        .build()
                );
        assertEquals(Optional.of(9), lead2.getValue());
        assertEquals(Optional.of("Test"), leadDao.querySalesPerson(lead2).getFirstName());

        List<Lead> leadListOfQuery = salesPersonDao
                .queryLeadsOver(createdSalesPerson, LeadsOverParameter.builder()
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

        assertEquals(2, totalNumberOfLeadsDao.execute());
        assertEquals(2, rootAllLeadsDao.query().execute().size());
        assertNotNull(rootOneLeadDao.query().execute());
    }


    @Test
    void testManualTransactionManagementRollback()  {
        assertFalse(TransactionSynchronizationManager.isActualTransactionActive());

        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);

        // Create and commit a salesperson
        SalesPerson salesPerson = salesPersonDao.create(SalesPerson.builder()
                .withFirstName("Test")
                .withLastName("Elek")
                .build());

        UUID uuid = salesPerson.get__identifier();

        transactionManager.commit(transactionStatus);

        // Create test transaction
        transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);

        assertEquals(Optional.of("Test"), salesPersonDao.getById(uuid).get().getFirstName());
        salesPerson.setFirstName("BLAAA");
        salesPersonDao.update(salesPerson);
        transactionManager.rollback(transactionStatus);
        assertEquals(Optional.of("Test"), salesPersonDao.getById(uuid).get().getFirstName());

        // Delete created person to avoid collosion with other tests
        transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);
        salesPersonDao.delete(salesPerson);
        transactionManager.commit(transactionStatus);
    }

    @Test
    void testManualTransactionManagementCommit() {
        assertFalse(TransactionSynchronizationManager.isActualTransactionActive());

        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);

        // Create and commit a salesperson
        SalesPerson salesPerson = salesPersonDao.create(SalesPerson.builder()
                .withFirstName("Test")
                .withLastName("Elek")
                .build());

        transactionManager.commit(transactionStatus);
        transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);

        // Create test transaction
        assertEquals(Optional.of("Test"), salesPersonDao.getById(salesPerson.get__identifier()).get().getFirstName());
        salesPerson.setFirstName("BLAAA");
        salesPersonDao.update(salesPerson);
        transactionManager.commit(transactionStatus);
        assertEquals(Optional.of("BLAAA"), salesPersonDao.getById(salesPerson.get__identifier()).get().getFirstName());

        // Delete created person to avoid collosion with other tests
        transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);
        salesPersonDao.delete(salesPerson);
        transactionManager.commit(transactionStatus);
    }

    @Test
    @Transactional
    public void testTransactionalAnnotationIsEffective() {
        assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
    }

}
