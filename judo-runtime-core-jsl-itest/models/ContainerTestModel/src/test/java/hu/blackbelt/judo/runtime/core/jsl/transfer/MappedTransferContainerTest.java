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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.additionalservice.AdditionalService;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.additionalservice.AdditionalServiceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.ta.TA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.ta.TADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.ta.TAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tadditionalservice.TAdditionalService;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tadditionalservice.TAdditionalServiceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tadditionalservice.TAdditionalServiceForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tb.TB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tb.TBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tb.TBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tc.TC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tc.TCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tc.TCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.td.TD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.td.TDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.td.TDForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tpartner.TPartner;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tpartner.TPartnerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tpartner.TPartnerForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tserviceprice.TServicePrice;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tserviceprice.TServicePriceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tserviceprice.TServicePriceForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tstocktransaction.TStockTransaction;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tstocktransaction.TStockTransactionDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tstocktransaction.TStockTransactionForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ContainerTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class MappedTransferContainerTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("ContainerTest", new ContainerTestDaoModules());

    @Inject
    BDao bDao;
    @Inject
    CDao cDao;
    @Inject
    DDao dDao;

    @Inject
    TBDao tbDao;
    @Inject
    TCDao tcDao;
    @Inject
    TDDao tdDao;

    @Inject
    TADao taDao;

    @Inject
    TPartnerDao tpartnerDao;

    @Inject
    TServicePriceDao tservicePriceDao;

    @Inject
    TStockTransactionDao tstockTransactionDao;

    @Inject
    TAdditionalServiceDao tadditionalServiceDao;

    @Inject
    AdditionalServiceDao additionalServiceDao;

    /**
     * The test checks the container Instance function work well on transfer object.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     * @type Behaviour
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     * @jslModel ContainerTest.jsl
     * @positiveRequirements REQ-EXPR-021
     */
    @Test
    @TestCase("ContainerFunctionOnMappedTransfer")
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-021",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SRV-002"
    })
    public void testContainerFunctionOnMappedTransfer() {
        TA ta = taDao.create(TAForCreate.builder().withConA(TCForCreate.builder().build()).build());

        TB tb = tbDao.create(TBForCreate.builder()
                .withConA(TCForCreate.builder().build())
                .withDonB(TDForCreate.builder().build())
                .build());
        TC tc = tb.getConA();
        TA tcA = tcDao.queryContainerA(tc).orElseThrow();
        TB tcB = tcDao.queryContainerB(tc).orElseThrow();
        TB tcB1 = tcDao.queryContainerB1(tc).orElseThrow();
        assertEquals(tb.identifier().getIdentifier(), tcA.identifier().getIdentifier());
        assertEquals(tb.identifier().getIdentifier(), tcB.identifier().getIdentifier());
        assertEquals(tb.identifier().getIdentifier(), tcB1.identifier().getIdentifier());

        TB tb1 = tbDao.create(TBForCreate.builder()
                .withConA(TCForCreate.builder().build())
                .withDonB(TDForCreate.builder().build())
                .build());
        TD td = tb1.getDonB();
        TA tdA = tdDao.queryContainerA(td).orElseThrow();
        TA tdA1 = tdDao.queryContainerA(td).orElseThrow();
        TB tdB = tdDao.queryContainerB(td).orElseThrow();
        assertEquals(tb1.identifier().getIdentifier(), tdA.identifier().getIdentifier());
        assertEquals(tb1.identifier().getIdentifier(), tdA1.identifier().getIdentifier());
        assertEquals(tb1.identifier().getIdentifier(), tdB.identifier().getIdentifier());

        // Entity representation

        B b = bDao.getById(tb.identifier().adaptTo(BIdentifier.class)).orElseThrow();

        C c = b.getConA();
        A cA = cDao.queryContainerA(c).orElseThrow();
        B cB = cDao.queryContainerB(c).orElseThrow();
        B cB1 = cDao.queryContainerB1(c).orElseThrow();
        assertEquals(b.identifier().getIdentifier(), cA.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB1.identifier().getIdentifier());

        B b1 = bDao.getById(tb1.identifier().adaptTo(BIdentifier.class)).orElseThrow();
        D d = b1.getDonB();
        A dA = dDao.queryContainerA(d).orElseThrow();
        A dA1 = dDao.queryContainerA(d).orElseThrow();
        B dB = dDao.queryContainerB(d).orElseThrow();
        assertEquals(b1.identifier().getIdentifier(), dA.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dA1.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dB.identifier().getIdentifier());

    }

    @Test
    @Requirement(reqs = {
            "REQ-ENT-001",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-ENT-008",
            "REQ-ENT-012",
            "REQ-EXPR-001",
            "REQ-EXPR-003",
            "REQ-EXPR-004",
            "REQ-EXPR-006",
            "REQ-EXPR-021",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SRV-002"
    })
    public void testTransferContainerFunctionWithRange() {
        // TODO JNG-5104
        TServicePrice outOfPrice = tservicePriceDao.create(TServicePriceForCreate.builder().build());

        TPartner partner = tpartnerDao.create(TPartnerForCreate
                .builder()
                .withServicePrices(List.of(
                        TServicePriceForCreate.builder().build(),
                        TServicePriceForCreate.builder().build(),
                        TServicePriceForCreate.builder().build()
                ))
                .build()
        );
        TStockTransaction stockTransaction = tstockTransactionDao.create(
                TStockTransactionForCreate
                        .builder()
                        .withAdditionalServices(List.of(
                                TAdditionalServiceForCreate
                                        .builder()
                                        .build()
                        ))
                        .withClient(partner)
                        .build()
        );

        AdditionalService additionalService = additionalServiceDao.getAll().get(0);
        Assertions.assertEquals(3, additionalServiceDao.queryServicePriceDerived(additionalService).count());

        TAdditionalService tadditionalService = tadditionalServiceDao.getAll().get(0);
        tadditionalServiceDao.setServicePrice(tadditionalService, partner.getServicePrices().get(0));

        assertTrue(tadditionalServiceDao.queryServicePrice(tadditionalService).isPresent());

        Assertions.assertEquals(partner.getServicePrices().get(0).identifier().getIdentifier(), tadditionalServiceDao.queryServicePrice(tadditionalService).get().identifier().getIdentifier());

        // Should throw error because the Price instance is out of range
        tadditionalServiceDao.setServicePrice(tadditionalService, outOfPrice);

        Assertions.assertEquals(outOfPrice.identifier().getIdentifier(), tadditionalServiceDao.queryServicePrice(tadditionalService).get().identifier().getIdentifier());


    }
}