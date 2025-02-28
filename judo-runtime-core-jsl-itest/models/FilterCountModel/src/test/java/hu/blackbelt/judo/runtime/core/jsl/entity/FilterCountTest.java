package hu.blackbelt.judo.runtime.core.jsl.entity;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
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

import java.time.LocalDate;
import java.util.List;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.AForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.a.AMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.b.BForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.child.ChildDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.child.ChildForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.d.DForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.expressioncontainer.ExpressionContainer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.expressioncontainer.ExpressionContainerDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.expressioncontainer.ExpressionContainerForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.expressioncontainertransfer.ExpressionContainerTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.expressioncontainertransfer.ExpressionContainerTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.expressioncontainertransfer.ExpressionContainerTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.parent.Parent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.parent.ParentDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.parent.ParentForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.parenttransfer.ParentTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toy.Toy;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toy.ToyDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toy.ToyForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FilterCountModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class FilterCountTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("FilterCountModel", new FilterCountModelDaoModules());

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Test
    void testAncestorFilterCount(JudoRuntimeFixture fixture) {
        // Filter on the entity attributes inherited from the ancestor and count the matched ones.

        C c = createC(2, LocalDate.of(2024, 12, 13));
        C c1 = createC(10, LocalDate.of(2024, 12, 14));
        C c2 = createC(15);

        B b = bDao.create(BForCreate.builder().withName("B").build());

        A a = aDao.create(AForCreate.builder()
                                    .withName("A")
                                    .withAncestorName("PA")
                                    .withNumb(2)
                                    .withC(c)
                                    .withCs(List.of(c, c1, c2))
                                    .withAncestorB(b)
                                    .build());
        assertEquals(LocalDate.of(2024, 12, 13), aDao.queryEarliestC(a).orElseThrow().getD().orElseThrow().getDate().orElseThrow());
        assertEquals(LocalDate.of(2024, 12, 14), aDao.queryLatestC(a).orElseThrow().getD().orElseThrow().getDate().orElseThrow());

        assertEquals("A", a.getName().orElseThrow());
        assertEquals("PA", a.getAncestorName().orElseThrow());
        assertEquals("B", a.getAncestorBName().orElseThrow());
        assertEquals("B", a.getBname().orElseThrow());

        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.ancestorBName!like('B')");
        assertEquals(1, aDao.query().filterBy("this.ancestorBName!like('B')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.ancestorBName!like('B')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.ancestorName!like('PA')");
        assertEquals(1, aDao.query().filterBy("this.ancestorName!like('PA')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.ancestorName!like('PA')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.bname!like('B')");
        assertEquals(1, aDao.query().filterBy("this.bname!like('B')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.bname!like('B')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.name!like('A')");
        assertEquals(1, aDao.query().filterBy("this.name!like('A')").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.name!like('A')").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.cnum == 2");
        assertEquals(1, aDao.query().filterBy("this.cnum == 2").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.cnum == 2").count());
        log.debug("\n" + Strings.repeat("=", 40) + "\nFilter by: this.sum == 2");
        assertEquals(1, aDao.query().filterBy("this.sum == 27").maskedBy(AMask.aMask()).selectList().size());
        assertEquals(1, aDao.query().filterBy("this.sum == 27").count());
    }

    private C createC(int num, LocalDate date) {
        return cDao.create(CForCreate.builder()
                                     .withNum(num)
                                     .withD(DForCreate.builder().withDate(date).build())
                                     .build());
    }

    private C createC(int num) {
        return cDao.create(CForCreate.builder()
                                     .withNum(num)
                                     .build());
    }

    @Inject
    ParentDao parentDao;

    @Inject
    ChildDao childrenDao;

    @Inject
    ToyDao toyDao;

    @Inject
    ExpressionContainerDao expressionContainerDao;

    @Inject
    ExpressionContainerTransferDao expressionContainerTransferDao;

    // Test for https://blackbelt.atlassian.net/browse/JNG-6157
    @Test
    void testSizeInNavigatedExpression() {
        Toy toy = toyDao.create(ToyForCreate.builder().withName("TeddyBear").build());
        Toy toy2 = toyDao.create(ToyForCreate.builder().withName("TeddyBear2").build());
        Toy toy3 = toyDao.create(ToyForCreate.builder().withName("TeddyBear3").build());
        Toy toy4 = toyDao.create(ToyForCreate.builder().withName("TeddyBear3").build());

        ExpressionContainer expressionContainer = expressionContainerDao.create(ExpressionContainerForCreate.builder().build());
        ExpressionContainerTransfer expressionContainerTransfer = expressionContainerTransferDao
                .create(ExpressionContainerTransferForCreate.builder().build());

        assertFalse(expressionContainer.getB().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedB().orElseThrow());

        Parent jason = parentDao.create(ParentForCreate
                .builder()
                .withName("Jason")
                .withChildren(List.of(
                        ChildForCreate.builder()
                                .withName("Jason")
                                .withAge(10)
                                .withToys(List.of(toy)).build())
                )
                .build()
        );

        Parent ted = parentDao.create(ParentForCreate
                .builder()
                .withName("Ted")
                .withChildren(List.of(
                        ChildForCreate.builder()
                                .withName("Jeremy")
                                .withAge(15)
                                .withToys(List.of(toy2)).build())
                )
                .build()
        );

        Parent jason2 = parentDao.create(ParentForCreate
                .builder()
                .withName("Jason")
                .withChildren(List.of(
                        ChildForCreate.builder()
                                .withName("Jack")
                                .withAge(20)
                                .withToys(List.of(toy3, toy4)).build(),
                        ChildForCreate.builder()
                                .withName("Oliver")
                                .withAge(5)
                                .withToys(List.of(toy3, toy4)).build())
                )
                .build()
        );

        assertFalse(expressionContainer.getB().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedB().orElseThrow());

        expressionContainerDao.setParent(expressionContainer, jason);
        expressionContainer = expressionContainerDao.getById(expressionContainer.identifier()).orElseThrow();

        assertTrue(expressionContainer.getB().orElseThrow());
        assertEquals(3, expressionContainer.getNumberOfParent().orElseThrow());
        assertTrue(expressionContainer.getNumberOfParentNameJasonIsTwo().orElseThrow());
        assertEquals(4, expressionContainer.getNumberOfChildren().orElseThrow());
        assertEquals(5, expressionContainer.getMinimumOfChildren().orElseThrow());
        assertEquals(20, expressionContainer.getMaximumOfChildren().orElseThrow());
        assertEquals(50, expressionContainer.getSumOfChildren().orElseThrow());
        assertEquals(12.5, expressionContainer.getAverageOfChildren().orElseThrow());

//        ParentTransfer jasonTransfer1 = parenttra
//        expressionContainerTransferDao.setParent(expressionContainerTransfer, jason);

//        assertTrue(expressionContainerTransfer.getMappedB().orElseThrow());
//        assertEquals(3, expressionContainerTransfer.getNumberOfParent().orElseThrow());
//        assertEquals(3, expressionContainerTransfer.getMappedNumberOfParent().orElseThrow());
//        assertTrue(expressionContainerTransfer.getNumberOfParentNameJasonIsTwo().orElseThrow());
//        assertTrue(expressionContainerTransfer.getMappedNumberOfParentNameJasonIsTwo().orElseThrow());
//        assertEquals(4, expressionContainerTransfer.getNumberOfChildren().orElseThrow());
//        assertEquals(4, expressionContainerTransfer.getMappedNumberOfChildren().orElseThrow());
//        assertEquals(5, expressionContainerTransfer.getMinimumOfChildren().orElseThrow());
//        assertEquals(5, expressionContainerTransfer.getMappedMinimumOfChildren().orElseThrow());
//        assertEquals(20, expressionContainerTransfer.getMaximumOfChildren().orElseThrow());
//        assertEquals(20, expressionContainerTransfer.getMappedMaximumOfChildren().orElseThrow());
//        assertEquals(50, expressionContainerTransfer.getSumOfChildren().orElseThrow());
//        assertEquals(50, expressionContainerTransfer.getMappedSumOfChildren().orElseThrow());
//        assertEquals(12.5, expressionContainerTransfer.getAverageOfChildren().orElseThrow());
//        assertEquals(12.5, expressionContainerTransfer.getMappedAverageOfChildren().orElseThrow());


//        assertEquals(2, expressionContainer.getNumberOfToys().get());
//        assertEquals(true, expressionContainer.getNumberOfToysIsTwo().get());
//        assertEquals(0, expressionContainer.getNumberOfChildrenSelf().get());

//        assertEquals(1, expressionContainer.getNumberOfChildren().get());
//        assertEquals(0, expressionContainer.getMinimumOfChildren().get());
//        assertEquals(0, expressionContainer.getMaximumOfChildren().get());
//        assertEquals(0, expressionContainer.getSumOfChildren().get());
//        assertEquals(0, expressionContainer.getAverageOfChildren().get());
//
//        assertEquals(1, expressionContainer.getNumberOfToys().get());
//        assertEquals(0, expressionContainer.getMinimumOfToys().get());
//        assertEquals(0, expressionContainer.getMaximumOfToys().get());
//        assertEquals(0, expressionContainer.getSumOfToys().get());
//        assertEquals(0, expressionContainer.getAverageOfToys().get());

    }

}
