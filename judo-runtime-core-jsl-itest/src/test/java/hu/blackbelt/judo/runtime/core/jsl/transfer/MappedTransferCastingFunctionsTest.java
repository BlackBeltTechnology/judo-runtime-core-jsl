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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.ta.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.tb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.tca.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.transfertester.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferCastingFunctionsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferCastingFunctionsTest extends AbstractJslTest {

    @Inject
    TBDao tbDao;
    @Inject
    TCADao tcaDao;
    @Inject
    TransferTesterDao transferTesterDao;

    private TransferTester transferTester;

    @Override
    public Module getModelDaoModule() {
        return new MappedTransferCastingFunctionsDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferCastingFunctions";
    }

    @Override
    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);
        TCA ca = tcaDao.create(TCA.builder().withNameA("aca1").withNameB("bca1").withNameCA("ca1").build());
        TCA ca1 = tcaDao.create(TCA.builder().withNameA("aca2").withNameB("bca2").withNameCA("ca2").build());
        TCA ca2 = tcaDao.create(TCA.builder().withNameA("aca3").withNameB("bca3").withNameCA("ca3").build());
        TB caAsB = tbDao.getById(ca.identifier().adaptTo(TBIdentifier.class)).orElseThrow();
        TB caAsB1 = tbDao.getById(ca1.identifier().adaptTo(TBIdentifier.class)).orElseThrow();
        TB caAsB2 = tbDao.getById(ca2.identifier().adaptTo(TBIdentifier.class)).orElseThrow();

        transferTester = transferTesterDao.create(
                                    TransferTester.builder()
                                        .build(),
                                    TransferTesterAttachedRelationsForCreate
                                        .builder()
                                        .withB(tbDao.create(TB.builder().withNameA("ab").withNameB("b").build()))
                                        .withBs(List.of(
                                                tbDao.create(TB.builder().withNameA("ab1").withNameB("b1").build()),
                                                tbDao.create(TB.builder().withNameA("ab2").withNameB("b2").build())
                                        ))
                                        .withCaAsB(caAsB)
                                        .withCaAsBs(List.of(caAsB1, caAsB2))
                                        .build());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003",
            "REQ-EXPR-021"
    })
    public void testKindOf() {
        assertTrue(transferTester.getKindOfA().orElseThrow());
        assertTrue(transferTester.getKindOfB().orElseThrow());
        assertFalse(transferTester.getKindOfCA().orElseThrow());
        assertTrue(transferTester.getKindOfCA1().orElseThrow());
        assertFalse(transferTester.getKindOfCB().orElseThrow());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003",
            "REQ-EXPR-021"
    })
    public void testTypeOf() {
        assertFalse(transferTester.getTypeOfA().orElseThrow());
        assertTrue(transferTester.getTypeOfB().orElseThrow());
        assertFalse(transferTester.getTypeOfCA().orElseThrow());
        assertTrue(transferTester.getTypeOfCA1().orElseThrow());
        assertFalse(transferTester.getTypeOfCB().orElseThrow());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003",
            "REQ-EXPR-021"
    })
    public void testAsType() {
        assertEquals("ab", transferTesterDao.queryAsTypeA(transferTester).orElseThrow().getNameA().orElseThrow());

        TB b = transferTesterDao.queryAsTypeB(transferTester).orElseThrow();
        assertEquals("ab", b.getNameA().orElseThrow());
        assertEquals("b", b.getNameB().orElseThrow());

        assertTrue(transferTesterDao.queryAsTypeCA(transferTester).isEmpty());

        TCA caFromT = transferTesterDao.queryAsTypeCA1(transferTester).orElseThrow();
        assertEquals("aca1", caFromT.getNameA().orElseThrow());
        assertEquals("bca1", caFromT.getNameB().orElseThrow());
        assertEquals("ca1", caFromT.getNameCA().orElseThrow());

        assertTrue(transferTesterDao.queryAsTypeCB(transferTester).isEmpty());
    }

    @Test
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-EXPR-003",
            "REQ-EXPR-022"
    })
    public void testAsCollection() {
        List<TA> asCollectionA = transferTesterDao.queryAsCollectionA(transferTester).execute();
        assertEquals(2, asCollectionA.size());
        assertTrue(asCollectionA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab1")));
        assertTrue(asCollectionA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab2")));

        List<TB> asCollectionB = transferTesterDao.queryAsCollectionB(transferTester).execute();
        assertEquals(2, asCollectionB.size());
        assertTrue(asCollectionB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab1") &&
                                                         lb.getNameB().orElseThrow().equals("b1")));
        assertTrue(asCollectionB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab2") &&
                                                         lb.getNameB().orElseThrow().equals("b2")));

        assertEquals(0, transferTesterDao.queryAsCollectionCA(transferTester).execute().size());

        List<TCA> asCollectionCA1 = transferTesterDao.queryAsCollectionCA1(transferTester).execute();
        assertEquals(2, asCollectionCA1.size());
        assertTrue(asCollectionCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca2") &&
                                                            lca.getNameB().orElseThrow().equals("bca2") &&
                                                            lca.getNameCA().orElseThrow().equals("ca2")));
        assertTrue(asCollectionCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca3") &&
                                                            lca.getNameB().orElseThrow().equals("bca3") &&
                                                            lca.getNameCA().orElseThrow().equals("ca3")));
    }

}
