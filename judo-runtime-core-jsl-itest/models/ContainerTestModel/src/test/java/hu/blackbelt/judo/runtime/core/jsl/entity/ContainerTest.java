package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.additionalservice.AdditionalService;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.additionalservice.AdditionalServiceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.D;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.partner.Partner;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.partner.PartnerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.serviceprice.ServicePrice;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.serviceprice.ServicePriceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.stocktransaction.StockTransaction;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.stocktransaction.StockTransactionAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.stocktransaction.StockTransactionDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tadditionalservice.TAdditionalService;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tadditionalservice.TAdditionalServiceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tpartner.TPartner;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tpartner.TPartnerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tserviceprice.TServicePrice;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tserviceprice.TServicePriceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tstocktransaction.TStockTransaction;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tstocktransaction.TStockTransactionAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.tstocktransaction.TStockTransactionDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ContainerTestDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import liquibase.pro.packaged.P;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ContainerTest extends AbstractJslTest {

    @Inject BDao bDao;
    @Inject CDao cDao;
    @Inject DDao dDao;

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

    @Override
    public Module getModelDaoModule() {
        return new ContainerTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "ContainerTest";
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
            "REQ-SYNT-003"
    })
    public void testContainerFunction() {
        B b = bDao.create(B.builder()
                           .withConA(C.builder().build())
                           .withDonB(D.builder().build())
                           .build());
        C c = b.getConA();

        A cA = cDao.queryContainerA(c).orElseThrow();
        B cB = cDao.queryContainerB(c).orElseThrow();
        B cB1 = cDao.queryContainerB1(c).orElseThrow();
        assertEquals(b.identifier().getIdentifier(), cA.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB.identifier().getIdentifier());
        assertEquals(b.identifier().getIdentifier(), cB1.identifier().getIdentifier());

        B b1 = bDao.create(B.builder()
                            .withConA(C.builder().build())
                            .withDonB(D.builder().build())
                            .build());
        D d = b1.getDonB();
        A dA = dDao.queryContainerA(d).orElseThrow();
        A dA1 = dDao.queryContainerA(d).orElseThrow();
        B dB = dDao.queryContainerB(d).orElseThrow();
        assertEquals(b1.identifier().getIdentifier(), dA.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dA1.identifier().getIdentifier());
        assertEquals(b1.identifier().getIdentifier(), dB.identifier().getIdentifier());
    }

    @Test
    public void testTransferContainerFunctionWithRange() {

        TServicePrice outOfPrice = tservicePriceDao.create(TServicePrice.builder().build());

        TPartner partner = tpartnerDao.create(TPartner
                    .builder()
                    .withServicePrices(List.of(
                        TServicePrice.builder().build(),
                        TServicePrice.builder().build(),
                        TServicePrice.builder().build()
                    ))
                    .build()
        );
        TStockTransaction stockTransaction = tstockTransactionDao.create(
          TStockTransaction
                  .builder()
                  .withAdditionalServices(List.of(
                          TAdditionalService
                                  .builder()
                                  .build()
                  ))
                  .build()
                , TStockTransactionAttachedRelationsForCreate.builder().withClient(partner).build()
        );

        AdditionalService additionalService = additionalServiceDao.getAll().get(0);
        assertEquals(3,additionalServiceDao.queryServicePriceDerived(additionalService).count());

        TAdditionalService tadditionalService = tadditionalServiceDao.getAll().get(0);
        tadditionalServiceDao.setServicePrice(tadditionalService, partner.getServicePrices().get(0));

        assertTrue(tadditionalServiceDao.queryServicePrice(tadditionalService).isPresent());

        assertEquals(partner.getServicePrices().get(0).identifier(), tadditionalServiceDao.queryServicePrice(tadditionalService).get().identifier());

        tadditionalServiceDao.setServicePrice(tadditionalService, outOfPrice);

        assertEquals(outOfPrice.identifier(), tadditionalServiceDao.queryServicePrice(tadditionalService).get().identifier());

    }

}
