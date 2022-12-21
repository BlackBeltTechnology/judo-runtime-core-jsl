package hu.blackbelt.judo.runtime.core.jsl;

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

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.guice.salesmodel.SalesModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodel.*;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodelcontract.Contract;
import hu.blackbelt.judo.runtime.core.jsl.itest.salesmodel.sdk.salesmodel.salesmodelcontract.ContractDetail;
import hu.blackbelt.judo.sdk.query.NumberFilter;
import hu.blackbelt.judo.sdk.query.StringFilter;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class SalesModelTest extends AbstractJslTest {
    @Inject
    SalesPerson.SalesPersonDao salesPersonDao;

    @Inject
    Person.PersonDao personDao;

    @Inject
    Lead.LeadDao leadDao;

    @Inject
    Contract.ContractDao contractDao;

    @Inject
    ContractsAggregator.ContractsAggregatorDao contractsAggregatorDao;

    @Override
    public Module getModelDaoModule() {
        return new SalesModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "SalesModel";
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-009",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-010",
            "REQ-ENT-012",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-008",
            "REQ-EXPR-022"
    })
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

        createdSalesPerson = salesPersonDao.getById(createdSalesPerson.get__identifier()).get();
        assertEquals(Optional.of(2), createdSalesPerson.getNumberOfLeads());

        List<Lead> leadsOver10 = salesPersonDao.getLeadsOver10(createdSalesPerson);

        assertEquals(1, leadsOver10.size());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-EXPR-001"
    })
    public void testDefaultValue() {
        SalesPerson createdSalesPerson = salesPersonDao.create(SalesPerson.builder()
                .build());

        Lead lead = leadDao.create(Lead.builder()
                .withSalesPerson(createdSalesPerson)
                .build());

        assertEquals(Optional.of(100000), lead.getValue());
    }

    @Test
    @Requirement(reqs = {
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006"
    })
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

    @Test
    @Requirement(reqs = {
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-007",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-006",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-EXPR-001",
            "REQ-EXPR-003"
    })
    public void testNavigationOnStaticCollection() {
        Contract contract1 = contractDao.create(Contract.builder()
                .withCreationDate(LocalDate.parse("2022-07-21"))
                .withDetail(ContractDetail.builder()
                        .withDetails("Hello")
                        .build())
                .build()
        );
        Contract contract2 = contractDao.create(Contract.builder()
                .withCreationDate(LocalDate.parse("2022-08-01"))
                .build()
        );

        ContractsAggregator staticNavigationHost = contractsAggregatorDao.create(ContractsAggregator.builder()
                .build());

        Optional<ContractsAggregator> fetched = contractsAggregatorDao.getById(staticNavigationHost.get__identifier());

        // Collection<ContractDetail> details = fetched.get().getContractDetails() - derived relations are not embedded
        Collection<ContractDetail> details = contractsAggregatorDao.getContractDetails(fetched.get());

        assertEquals(Optional.of("Hello"), contract1.getDetail().get().getDetails());
        assertEquals(Optional.empty(), contract2.getDetail());

        // Collection<Contract> contracts = contractsAggregatorDao.getContracts(fetched.get()) - derived relations are not embedded
        Collection<Contract> contracts = contractsAggregatorDao.getContracts(fetched.get());

        assertEquals(2, contracts.size());
        assertEquals(1, details.size());
    }

    @Test
    public void testErrorInheritance() {
        MyExtendedError error = MyExtendedError.builder()
                .withCode(403)
                .withExtra(101)
                .withMsg("Hello")
                .build();

        assertEquals(Optional.of(403), error.getCode());
        assertEquals(Optional.of(101), error.getExtra());
        assertEquals(Optional.of("Hello"), error.getMsg());
    }

    @Test
    @Disabled // FIXME: JNG-3893
    public void testErrorDefaultValue() {
        MyExtendedError error = MyExtendedError.builder()
                .withCode(403)
                .build();

        assertEquals(403, error.getCode());
        assertEquals(Optional.of("Internal Server Error"), error.getMsg());
        assertEquals(Optional.of(1), error.getExtra());
    }
}
