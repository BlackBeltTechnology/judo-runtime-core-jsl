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
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.additionalservice.AdditionalService;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.containertest.containertest.additionalservice.AdditionalServiceDao;
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
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class MappedContainerTest extends AbstractJslTest {

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
    public void testTransferContainerFunctionWithRange() {
        // TODO JNG-5104
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

        // Should throw error because the Price instance is out of range
        tadditionalServiceDao.setServicePrice(tadditionalService, outOfPrice);

        assertEquals(outOfPrice.identifier(), tadditionalServiceDao.queryServicePrice(tadditionalService).get().identifier());

    }

}
