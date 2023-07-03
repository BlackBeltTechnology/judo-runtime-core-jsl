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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.a.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.ta.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.b.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.tb.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.ca.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.tca.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransfercastingfunctions.mappedtransfercastingfunctions.tester.*;
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
    BDao bDao;
    @Inject
    CADao caDao;
    @Inject
    TesterDao testerDao;

    @Inject
    TBDao tbDao;
    @Inject
    TCADao tcaDao;
    @Inject
    TransferTesterDao transferTesterDao;

    private TransferTester transferTester;

    private Tester tester;

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
        TCA tca = tcaDao.create(TCA.builder().withNameA("aca1").withNameB("bca1").withNameCA("ca1").build());
        TCA tca1 = tcaDao.create(TCA.builder().withNameA("aca2").withNameB("bca2").withNameCA("ca2").build());
        TCA tca2 = tcaDao.create(TCA.builder().withNameA("aca3").withNameB("bca3").withNameCA("ca3").build());
        TB tcaAsB = tbDao.getById(tca.identifier().adaptTo(TBIdentifier.class)).orElseThrow();
        TB tcaAsB1 = tbDao.getById(tca1.identifier().adaptTo(TBIdentifier.class)).orElseThrow();
        TB tcaAsB2 = tbDao.getById(tca2.identifier().adaptTo(TBIdentifier.class)).orElseThrow();

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
                                        .withCaAsB(tcaAsB)
                                        .withCaAsBs(List.of(tcaAsB1, tcaAsB2))
                                        .build());

        tester = testerDao.getById(transferTester.identifier().adaptTo(TesterIdentifier.class)).orElseThrow();

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

        //representation

        assertTrue(tester.getKindOfA().orElseThrow());
        assertTrue(tester.getKindOfB().orElseThrow());
        assertFalse(tester.getKindOfCA().orElseThrow());
        assertTrue(tester.getKindOfCA1().orElseThrow());
        assertFalse(tester.getKindOfCB().orElseThrow());

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

        //representation

        assertFalse(tester.getTypeOfA().orElseThrow());
        assertTrue(tester.getTypeOfB().orElseThrow());
        assertFalse(tester.getTypeOfCA().orElseThrow());
        assertTrue(tester.getTypeOfCA1().orElseThrow());
        assertFalse(tester.getTypeOfCB().orElseThrow());
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

        TB tb = transferTesterDao.queryAsTypeB(transferTester).orElseThrow();
        assertEquals("ab", tb.getNameA().orElseThrow());
        assertEquals("b", tb.getNameB().orElseThrow());

        assertTrue(transferTesterDao.queryAsTypeCA(transferTester).isEmpty());

        TCA tcaFromT = transferTesterDao.queryAsTypeCA1(transferTester).orElseThrow();
        assertEquals("aca1", tcaFromT.getNameA().orElseThrow());
        assertEquals("bca1", tcaFromT.getNameB().orElseThrow());
        assertEquals("ca1", tcaFromT.getNameCA().orElseThrow());

        assertTrue(transferTesterDao.queryAsTypeCB(transferTester).isEmpty());

        // representation

        assertEquals("ab", testerDao.queryAsTypeA(tester).orElseThrow().getNameA().orElseThrow());

        B b = testerDao.queryAsTypeB(tester).orElseThrow();
        assertEquals("ab", b.getNameA().orElseThrow());
        assertEquals("b", b.getNameB().orElseThrow());

        assertTrue(testerDao.queryAsTypeCA(tester).isEmpty());

        CA caFromT = testerDao.queryAsTypeCA1(tester).orElseThrow();
        assertEquals("aca1", caFromT.getNameA().orElseThrow());
        assertEquals("bca1", caFromT.getNameB().orElseThrow());
        assertEquals("ca1", caFromT.getNameCA().orElseThrow());

        assertTrue(testerDao.queryAsTypeCB(tester).isEmpty());

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
        List<TA> asCollectionTA = transferTesterDao.queryAsCollectionA(transferTester).execute();
        assertEquals(2, asCollectionTA.size());
        assertTrue(asCollectionTA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab1")));
        assertTrue(asCollectionTA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab2")));

        List<TB> asCollectionTB = transferTesterDao.queryAsCollectionB(transferTester).execute();
        assertEquals(2, asCollectionTB.size());
        assertTrue(asCollectionTB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab1") &&
                                                         lb.getNameB().orElseThrow().equals("b1")));
        assertTrue(asCollectionTB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab2") &&
                                                         lb.getNameB().orElseThrow().equals("b2")));

        assertEquals(0, transferTesterDao.queryAsCollectionCA(transferTester).execute().size());

        List<TCA> asCollectionTCA1 = transferTesterDao.queryAsCollectionCA1(transferTester).execute();
        assertEquals(2, asCollectionTCA1.size());
        assertTrue(asCollectionTCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca2") &&
                                                            lca.getNameB().orElseThrow().equals("bca2") &&
                                                            lca.getNameCA().orElseThrow().equals("ca2")));
        assertTrue(asCollectionTCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca3") &&
                                                            lca.getNameB().orElseThrow().equals("bca3") &&
                                                            lca.getNameCA().orElseThrow().equals("ca3")));

        // representation

        List<A> asCollectionA = testerDao.queryAsCollectionA(tester).execute();
        assertEquals(2, asCollectionA.size());
        assertTrue(asCollectionA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab1")));
        assertTrue(asCollectionA.stream().anyMatch(a -> a.getNameA().orElseThrow().equals("ab2")));

        List<B> asCollectionB = testerDao.queryAsCollectionB(tester).execute();
        assertEquals(2, asCollectionB.size());
        assertTrue(asCollectionB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab1") &&
                lb.getNameB().orElseThrow().equals("b1")));
        assertTrue(asCollectionB.stream().anyMatch(lb -> lb.getNameA().orElseThrow().equals("ab2") &&
                lb.getNameB().orElseThrow().equals("b2")));

        assertEquals(0, testerDao.queryAsCollectionCA(tester).execute().size());

        List<CA> asCollectionCA1 = testerDao.queryAsCollectionCA1(tester).execute();
        assertEquals(2, asCollectionCA1.size());
        assertTrue(asCollectionCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca2") &&
                lca.getNameB().orElseThrow().equals("bca2") &&
                lca.getNameCA().orElseThrow().equals("ca2")));
        assertTrue(asCollectionCA1.stream().anyMatch(lca -> lca.getNameA().orElseThrow().equals("aca3") &&
                lca.getNameB().orElseThrow().equals("bca3") &&
                lca.getNameCA().orElseThrow().equals("ca3")));
    }

}