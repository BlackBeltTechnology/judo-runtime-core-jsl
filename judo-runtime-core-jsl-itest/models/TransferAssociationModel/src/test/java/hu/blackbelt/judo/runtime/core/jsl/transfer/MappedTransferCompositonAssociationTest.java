package hu.blackbelt.judo.runtime.core.jsl.transfer;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.bankaccount.BankAccountDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.company.CompanyDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitya.EntityADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entitya.EntityAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityb.EntityBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.entityd.EntityDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.mappedbankaccount.MappedBankAccount;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.mappedbankaccount.MappedBankAccountDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.mappedbankaccount.MappedBankAccountForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.mappedcompany.MappedCompany;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.mappedcompany.MappedCompanyDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.mappedcompany.MappedCompanyForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transfera.TransferAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferb.TransferBIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferc.TransferCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercompositonassociation.mappedtransfercompositonassociation.transferd.TransferDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferCompositonAssociationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferCompositonAssociationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("MappedTransferCompositonAssociation", new MappedTransferCompositonAssociationDaoModules());

    @Inject
    EntityADao entityADao;

    @Inject
    TransferADao transferADao;

    @Inject
    EntityBDao entityBDao;

    @Inject
    TransferBDao transferBDao;

    @Inject
    TransferCDao transferCDao;

    @Inject
    EntityDDao entityDDao;

    @Inject
    TransferDDao transferDDao;

    /**
     * The test checks the association mapped single field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleCompositionAssociationOnTransfer")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-SRV-001"
    })
    public void testSingleCompositionAssociationOnTransfer() {

        TransferB transferB = transferBDao.create(TransferBForCreate.builder().withNameB("B1").build());

        assertEquals(1, transferBDao.query().selectList().size());

        TransferA transferA = transferADao.create(TransferAForCreate.builder()
                .withSingleEntityB(transferB).build()
        );

        assertEquals(2,transferBDao.query().selectList().size());
        assertEquals(2,entityBDao.query().selectList().size());

        // Check transferA contains transferB
        assertNotEquals(transferB.identifier().getIdentifier(), transferADao.querySingleEntityB(transferA).orElseThrow().identifier().getIdentifier());
        assertNotEquals(
                entityBDao.getById(transferB.adaptTo(EntityBIdentifier.class)).orElseThrow().identifier(),
                entityADao.querySingleEntityB((entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow())).orElseThrow().identifier()
        );

        // Check transferA cannot bind a different B element
        TransferA referenceForLambda = transferA;
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> transferADao.createSingleEntityB(referenceForLambda, TransferBForCreate.builder().withNameB("B2").build())
        );
        assertTrue(thrown.getMessage().contains("Upper cardinality violated"));

        // Check transferA can set to null
        transferBDao.delete(transferB);
        transferA = transferADao.update(transferA);

        assertFalse(transferADao.querySingleEntityB(transferA).isEmpty());
        assertFalse(entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getSingleEntityB().isEmpty());
        assertTrue(transferBDao.getById(transferB.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB.identifier().adaptTo(EntityBIdentifier.class)).isEmpty());

    }


    /**
     * The test checks the association mapped single reqiured field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("SingleRequiredCompositionAssociationOnTransfer")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-SRV-001"
    })
    public void testSingleRequiredCompositionAssociationOnTransfer() {

        TransferD transferD = transferDDao.create(TransferDForCreate.builder().withNameD("D1").build());

        assertEquals(1, transferDDao.query().selectList().size());

        TransferC transferC = transferCDao.create(TransferCForCreate.builder().withSingleRequiredEntityD(transferD).build()
        );

        assertEquals(2,transferDDao.query().selectList().size());
        assertEquals(2,entityDDao.query().selectList().size());

        //Try to create without required element
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> transferCDao.create(TransferCForCreate.builder().build())
        );

        Assertions.assertEquals(1, thrown.getValidationResults().size());
        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredEntityD")))
        ));

        //Create new required element and check the old is deleted

        IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> transferCDao.createSingleRequiredEntityD(transferC, TransferDForCreate.builder().build())
        );
        assertTrue(thrown2.getMessage().contains("Upper cardinality violated"));

    }

    /**
     * The test checks the association mapped multi field with entity type work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel MappedTransferAggregation.jsl
     *
     * @positiveRequirements REQ-ENT-005
     *
     */
    @Test
    @TestCase("MultiCompositionAssociationOnTransfer")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-SRV-001"
    })
    public void testMultiCompositionAssociationOnTransfer() {

        TransferB transferB1 = transferBDao.create(TransferBForCreate.builder().withNameB("B1").build());
        TransferB transferB2 = transferBDao.create(TransferBForCreate.builder().withNameB("B2").build());
        TransferB transferB3 = transferBDao.create(TransferBForCreate.builder().withNameB("B3").build());

        assertEquals(3, transferBDao.query().selectList().size());

        TransferA transferA = transferADao.create(TransferAForCreate.builder().withMultiEntityB(List.of(transferB1, transferB2, transferB3)).build()
        );

        assertEquals(6, transferBDao.query().selectList().size());
        assertEquals(6, entityBDao.query().selectList().size());

        // Check transferA contains transferB
        List<TransferBIdentifier> multiTransferBIDS = transferADao.queryMultiEntityB(transferA).selectList().stream().map(t -> t.identifier()).toList();
        assertFalse(multiTransferBIDS.contains(transferB1.identifier()));
        assertFalse(multiTransferBIDS.contains(transferB2.identifier()));
        assertFalse(multiTransferBIDS.contains(transferB3.identifier()));

        // Check the entity level
        EntityB entityB1 = entityBDao.getById(transferB1.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB2 = entityBDao.getById(transferB2.adaptTo(EntityBIdentifier.class)).orElseThrow();
        EntityB entityB3 = entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).orElseThrow();

        List<EntityBIdentifier> multiEntityBIDS = entityADao.getById(transferA.adaptTo(EntityAIdentifier.class)).orElseThrow().getMultiEntityB().stream().map(t -> t.identifier()).toList();

        assertFalse(multiEntityBIDS.contains(entityB1.identifier()));
        assertFalse(multiEntityBIDS.contains(entityB2.identifier()));
        assertFalse(multiEntityBIDS.contains(entityB3.identifier()));

        //Delete one element
        transferBDao.delete(transferB3);

        assertTrue(transferBDao.getById(transferB3.identifier()).isEmpty());
        assertTrue(entityBDao.getById(transferB3.adaptTo(EntityBIdentifier.class)).isEmpty());

        transferA = transferADao.getById(transferA.identifier()).orElseThrow();

        assertEquals(3, transferADao.countMultiEntityB(transferA));

        // Add new List empty
        transferADao.createMultiEntityB(transferA, List.of());
        assertEquals(3, transferADao.countMultiEntityB(transferA));

        // Create new List with elements
        transferADao.createMultiEntityB(transferA, List.of(TransferBForCreate.builder().build()));
        assertEquals(4, transferADao.countMultiEntityB(transferA));


    }

    @Inject
    BankAccountDao bankAccountDao;

    @Inject
    CompanyDao companyDao;

    @Inject
    MappedBankAccountDao mappedBankAccountDao;

    @Inject
    MappedCompanyDao mappedCompanyDao;

    @Test
    @TestCase("TwoWayAssociationAlongWithCompositionTest")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-008",
            "REQ-SRV-002"
    })
    void testTwoWayAssociationAlongWithCompositionTest() {

        MappedBankAccount accountOutOfRange = mappedBankAccountDao.create(MappedBankAccountForCreate.builder().withAccountNumber("12345").build());

        MappedCompany company = mappedCompanyDao.create(MappedCompanyForCreate.builder().withName("MyCompany").build());
        MappedBankAccount bankAccount1 = mappedCompanyDao.createBankAccounts(company, MappedBankAccountForCreate.builder().withAccountNumber("99999999").build());
        MappedBankAccount bankAccount2= mappedCompanyDao.createBankAccounts(company, MappedBankAccountForCreate.builder().withAccountNumber("00000000").build());
        mappedCompanyDao.unsetMainBankAccounts(company);

        company = mappedCompanyDao.getById(company.identifier()).orElseThrow();

        assertTrue(mappedBankAccountDao.queryCompany(bankAccount1).isEmpty());
        assertTrue(mappedBankAccountDao.queryCompany(bankAccount2).isEmpty());

        company.setMainBankAccounts(bankAccount1);
        company = mappedCompanyDao.update(company);

        assertEquals(company.identifier().getIdentifier(), mappedBankAccountDao.queryCompany(bankAccount1).orElseThrow().identifier().getIdentifier());
        assertTrue(mappedBankAccountDao.queryCompany(bankAccount2).isEmpty());

        assertEquals(bankAccount1.identifier().getIdentifier(), mappedCompanyDao.queryMainBankAccounts(company).orElseThrow().identifier().getIdentifier());

        company.setMainBankAccounts(bankAccount2);
        company = mappedCompanyDao.update(company);

        assertEquals(company.identifier().getIdentifier(), mappedBankAccountDao.queryCompany(bankAccount2).orElseThrow().identifier().getIdentifier());
        assertTrue(mappedBankAccountDao.queryCompany(bankAccount1).isEmpty());

        assertEquals(bankAccount2.identifier().getIdentifier(), mappedCompanyDao.queryMainBankAccounts(company).orElseThrow().identifier().getIdentifier());

        //Give back the range elements
        assertEquals(2, mappedCompanyDao.query_mainBankAccounts_RelationRange_MappedCompany(company).count());

        // Check range validation
        company.setMainBankAccounts(accountOutOfRange);
        MappedCompany companyRef = company;
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> mappedCompanyDao.update(companyRef)
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("NOT_ACCEPTED_BY_RANGE")),
                hasProperty("location", equalTo("mainBankAccounts")))
        ));

    }
}
