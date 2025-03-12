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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.child.ChildForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.childtransfer.ChildTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.d.DForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.entityi.EntityIDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.entityi.EntityIForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.entityj.EntityJ;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.entityj.EntityJDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.entityj.EntityJForCreate;
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.parenttransfer.ParentTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.parenttransfer.ParentTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toy.Toy;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toy.ToyDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toy.ToyForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toytransfer.ToyTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toytransfer.ToyTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.filtercountmodel.filtercountmodel.toytransfer.ToyTransferForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.FilterCountModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
    ToyDao toyDao;

    @Inject
    ExpressionContainerDao expressionContainerDao;

    @Inject
    ExpressionContainerTransferDao expressionContainerTransferDao;

    @Inject
    ParentTransferDao parentTransferDao;

    @Inject
    ToyTransferDao toyTransferDao;

    @Inject
    EntityIDao entityIDao;

    @Inject
    EntityJDao entityJDao;

    // Test for https://blackbelt.atlassian.net/browse/JNG-6157
    @Test
    void testSizeInNavigatedExpression() {
        Toy toy = toyDao.create(ToyForCreate.builder()
                .withName("TeddyBear")
                .withPrice(20)
                .build());

        Toy toy2 = toyDao.create(ToyForCreate.builder()
                .withName("TeddyBear2")
                .withPrice(40)
                .build());

        Toy toy3 = toyDao.create(ToyForCreate.builder()
                .withName("TeddyBear3")
                .withPrice(30)
                .build());

        Toy toy4 = toyDao.create(ToyForCreate.builder()
                .withName("TeddyBear4")
                .withPrice(60)
                .build());

        ExpressionContainer expressionContainer = expressionContainerDao.create(ExpressionContainerForCreate.builder().build());
        ExpressionContainerTransfer expressionContainerTransfer = expressionContainerTransferDao
                .create(ExpressionContainerTransferForCreate.builder().build());

        assertFalse(expressionContainer.getB().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedB().orElseThrow());

        assertFalse(expressionContainer.getB().orElseThrow());
        assertEquals(0, expressionContainer.getNumberOfParent().orElseThrow());
        assertFalse(expressionContainer.getNumberOfParentNameJasonIsTwo().orElseThrow());
        assertEquals(0, expressionContainer.getNumberOfChildren().orElseThrow());
        assertTrue(expressionContainer.getMinimumOfChildren().isEmpty());
        assertTrue(expressionContainer.getMaximumOfChildren().isEmpty());
        assertTrue(expressionContainer.getSumOfChildren().isEmpty());
        assertTrue(expressionContainer.getAverageOfChildren().isEmpty());
        assertFalse(expressionContainer.getNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertFalse(expressionContainer.getNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertFalse(expressionContainer.getNumberOfToysWithChildFilterIsOne().orElseThrow());
        assertEquals(0, expressionContainerDao.queryToysWithParentNameJason().selectList().size());
        assertEquals(0, expressionContainer.getNumberOfToysParentNameJason().orElseThrow());
        assertEquals(0, expressionContainer.getNumberOfToys().orElseThrow());
        assertTrue(expressionContainer.getMinimumOfToys().isEmpty());
        assertTrue(expressionContainer.getMaximumOfToys().isEmpty());
        assertTrue(expressionContainer.getSumOfToys().isEmpty());
        assertTrue(expressionContainer.getAverageOfToys().isEmpty());


        assertFalse(expressionContainerTransfer.getMappedB().orElseThrow());
        assertEquals(0, expressionContainerTransfer.getNumberOfParent().orElseThrow());
        assertEquals(0, expressionContainerTransfer.getMappedNumberOfParent().orElseThrow());
        assertFalse(expressionContainerTransfer.getNumberOfParentNameJasonIsTwo().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedNumberOfParentNameJasonIsTwo().orElseThrow());
        assertEquals(0, expressionContainerTransfer.getNumberOfChildren().orElseThrow());
        assertEquals(0, expressionContainerTransfer.getMappedNumberOfChildren().orElseThrow());
        assertTrue(expressionContainerTransfer.getMinimumOfChildren().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedMinimumOfChildren().isEmpty());
        assertTrue(expressionContainerTransfer.getMaximumOfChildren().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedMaximumOfChildren().isEmpty());
        assertTrue(expressionContainerTransfer.getSumOfChildren().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedSumOfChildren().isEmpty());
        assertTrue(expressionContainerTransfer.getAverageOfChildren().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedAverageOfChildren().isEmpty());
        assertFalse(expressionContainerTransfer.getNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertFalse(expressionContainerTransfer.getNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertFalse(expressionContainerTransfer.getNumberOfToysWithChildFilterIsOne().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedNumberOfToysWithChildFilterIsOne().orElseThrow());
        assertEquals(0, expressionContainerTransferDao.queryToysWithParentNameJason().selectList().size());
        assertEquals(0, expressionContainerTransfer.getNumberOfToysParentNameJason().orElseThrow());
        assertEquals(0, expressionContainerTransfer.getMappedNumberOfToysParentNameJason().orElseThrow());
        assertEquals(0, expressionContainerTransfer.getNumberOfToys().orElseThrow());
        assertEquals(0, expressionContainerTransfer.getMappedNumberOfToys().orElseThrow());
        assertTrue(expressionContainerTransfer.getMinimumOfToys().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedMinimumOfToys().isEmpty());
        assertTrue(expressionContainerTransfer.getMaximumOfToys().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedMaximumOfToys().isEmpty());
        assertTrue(expressionContainerTransfer.getSumOfToys().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedSumOfToys().isEmpty());
        assertTrue(expressionContainerTransfer.getAverageOfToys().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedAverageOfToys().isEmpty());

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

        parentDao.create(ParentForCreate
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

        assertEquals("Jeremy", jason2.getChildName().orElseThrow());

        assertFalse(expressionContainer.getB().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedB().orElseThrow());

        expressionContainerDao.setParent(expressionContainer, jason);
        expressionContainer = expressionContainerDao.getById(expressionContainer.identifier()).orElseThrow();
        expressionContainerTransfer = expressionContainerTransferDao.getById(expressionContainerTransfer.identifier()).orElseThrow();

        assertTrue(expressionContainer.getB().orElseThrow());
        assertEquals(3, expressionContainer.getNumberOfParent().orElseThrow());
        assertTrue(expressionContainer.getNumberOfParentNameJasonIsTwo().orElseThrow());
        assertEquals(4, expressionContainer.getNumberOfChildren().orElseThrow());
        assertEquals(5, expressionContainer.getMinimumOfChildren().orElseThrow());
        assertEquals(20, expressionContainer.getMaximumOfChildren().orElseThrow());
        assertEquals(50, expressionContainer.getSumOfChildren().orElseThrow());
        assertEquals(12.5, expressionContainer.getAverageOfChildren().orElseThrow());
        assertTrue(expressionContainer.getNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertTrue(expressionContainer.getNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertTrue(expressionContainer.getNumberOfToysWithChildFilterIsOne().orElseThrow());

        List<Toy> toysWithParentNameJason = expressionContainerDao.queryToysWithParentNameJason().selectList();
        List<Serializable> ids = List.of(
                toy.identifier().getIdentifier(),
                toy3.identifier().getIdentifier(),
                toy4.identifier().getIdentifier()
        );

        List<Serializable> resultIds = toysWithParentNameJason.stream().map(t -> t.identifier().getIdentifier()).toList();

        assertEquals(3, toysWithParentNameJason.size());
        assertTrue(resultIds.containsAll(ids));
        assertFalse(resultIds.contains(toy2.identifier().getIdentifier()));

        assertEquals(3, expressionContainer.getNumberOfToysParentNameJason().orElseThrow());
        assertEquals(4, expressionContainer.getNumberOfToys().orElseThrow());
        assertEquals(20, expressionContainer.getMinimumOfToys().orElseThrow());
        assertEquals(60, expressionContainer.getMaximumOfToys().orElseThrow());
        assertEquals(150, expressionContainer.getSumOfToys().orElseThrow());
        assertEquals(37.5, expressionContainer.getAverageOfToys().orElseThrow());


        assertFalse(expressionContainerTransfer.getMappedB().orElseThrow());
        assertEquals(3, expressionContainerTransfer.getNumberOfParent().orElseThrow());
        assertEquals(3, expressionContainerTransfer.getMappedNumberOfParent().orElseThrow());
        assertTrue(expressionContainerTransfer.getNumberOfParentNameJasonIsTwo().orElseThrow());
        assertTrue(expressionContainerTransfer.getMappedNumberOfParentNameJasonIsTwo().orElseThrow());
        assertEquals(4, expressionContainerTransfer.getNumberOfChildren().orElseThrow());
        assertEquals(4, expressionContainerTransfer.getMappedNumberOfChildren().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getMinimumOfChildren().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getMappedMinimumOfChildren().orElseThrow());
        assertEquals(20, expressionContainerTransfer.getMaximumOfChildren().orElseThrow());
        assertEquals(20, expressionContainerTransfer.getMappedMaximumOfChildren().orElseThrow());
        assertEquals(50, expressionContainerTransfer.getSumOfChildren().orElseThrow());
        assertEquals(50, expressionContainerTransfer.getMappedSumOfChildren().orElseThrow());
        assertEquals(12.5, expressionContainerTransfer.getAverageOfChildren().orElseThrow());
        assertEquals(12.5, expressionContainerTransfer.getMappedAverageOfChildren().orElseThrow());
        assertTrue(expressionContainerTransfer.getNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertTrue(expressionContainerTransfer.getMappedNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertTrue(expressionContainerTransfer.getNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertTrue(expressionContainerTransfer.getMappedNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertTrue(expressionContainerTransfer.getNumberOfToysWithChildFilterIsOne().orElseThrow());
        assertTrue(expressionContainerTransfer.getMappedNumberOfToysWithChildFilterIsOne().orElseThrow());

        List<ToyTransfer> toyTransfersWithParentNameJason = expressionContainerTransferDao.queryToysWithParentNameJason().selectList();
        resultIds = toyTransfersWithParentNameJason.stream().map(t -> t.identifier().getIdentifier()).toList();

        assertEquals(3, toyTransfersWithParentNameJason.size());
        assertTrue(resultIds.containsAll(ids));
        assertFalse(resultIds.contains(toy2.identifier().getIdentifier()));

        assertEquals(3, expressionContainerTransfer.getNumberOfToysParentNameJason().orElseThrow());
        assertEquals(3, expressionContainerTransfer.getMappedNumberOfToysParentNameJason().orElseThrow());
        assertEquals(4, expressionContainerTransfer.getNumberOfToys().orElseThrow());
        assertEquals(4, expressionContainerTransfer.getMappedNumberOfToys().orElseThrow());
        assertEquals(20, expressionContainerTransfer.getMinimumOfToys().orElseThrow());
        assertEquals(20, expressionContainerTransfer.getMappedMinimumOfToys().orElseThrow());
        assertEquals(60, expressionContainerTransfer.getMaximumOfToys().orElseThrow());
        assertEquals(60, expressionContainerTransfer.getMappedMaximumOfToys().orElseThrow());
        assertEquals(150, expressionContainerTransfer.getSumOfToys().orElseThrow());
        assertEquals(150, expressionContainerTransfer.getMappedSumOfToys().orElseThrow());
        assertEquals(37.5, expressionContainerTransfer.getAverageOfToys().orElseThrow());
        assertEquals(37.5, expressionContainerTransfer.getMappedAverageOfToys().orElseThrow());

        ToyTransfer toyTransfer1 = toyTransferDao.create(ToyTransferForCreate.builder()
                .withName("TeddyBear")
                .build());

        ParentTransfer jasonTransfer1 = parentTransferDao.create(ParentTransferForCreate.builder()
                .withName("Jason")
                        .withChildren(List.of(ChildTransferForCreate.builder()
                                .withName("Ted")
                                        .withAge(30)
                                .withToys(List.of(toyTransfer1))
                                .build()))
                .build());

        expressionContainerTransferDao.setParent(expressionContainerTransfer, jasonTransfer1);
        expressionContainer = expressionContainerDao.getById(expressionContainer.identifier()).orElseThrow();
        expressionContainerTransfer = expressionContainerTransferDao.getById(expressionContainerTransfer.identifier()).orElseThrow();

        assertTrue(expressionContainer.getB().orElseThrow());
        assertEquals(4, expressionContainer.getNumberOfParent().orElseThrow());
        assertFalse(expressionContainer.getNumberOfParentNameJasonIsTwo().orElseThrow());
        assertEquals(5, expressionContainer.getNumberOfChildren().orElseThrow());
        assertEquals(5, expressionContainer.getMinimumOfChildren().orElseThrow());
        assertEquals(30, expressionContainer.getMaximumOfChildren().orElseThrow());
        assertEquals(80, expressionContainer.getSumOfChildren().orElseThrow());
        assertEquals(16, expressionContainer.getAverageOfChildren().orElseThrow());
        assertFalse(expressionContainer.getNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertTrue(expressionContainer.getNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertTrue(expressionContainer.getNumberOfToysWithChildFilterIsOne().orElseThrow());

        toysWithParentNameJason = expressionContainerDao.queryToysWithParentNameJason().selectList();
        ids = List.of(
                toy.identifier().getIdentifier(),
                toy3.identifier().getIdentifier(),
                toy4.identifier().getIdentifier(),
                toyTransfer1.identifier().getIdentifier()
        );

        resultIds = toysWithParentNameJason.stream().map(t -> t.identifier().getIdentifier()).toList();

        assertEquals(4, toysWithParentNameJason.size());
        assertTrue(resultIds.containsAll(ids));
        assertFalse(resultIds.contains(toy2.identifier().getIdentifier()));

        assertEquals(4, expressionContainer.getNumberOfToysParentNameJason().orElseThrow());
        assertEquals(5, expressionContainer.getNumberOfToys().orElseThrow());
        assertEquals(20, expressionContainer.getMinimumOfToys().orElseThrow());
        assertEquals(60, expressionContainer.getMaximumOfToys().orElseThrow());
        assertEquals(150, expressionContainer.getSumOfToys().orElseThrow());
        assertEquals(37.5, expressionContainer.getAverageOfToys().orElseThrow());


        assertTrue(expressionContainerTransfer.getMappedB().orElseThrow());
        assertEquals(4, expressionContainerTransfer.getNumberOfParent().orElseThrow());
        assertEquals(4, expressionContainerTransfer.getMappedNumberOfParent().orElseThrow());
        assertFalse(expressionContainerTransfer.getNumberOfParentNameJasonIsTwo().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedNumberOfParentNameJasonIsTwo().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getNumberOfChildren().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getMappedNumberOfChildren().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getMinimumOfChildren().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getMappedMinimumOfChildren().orElseThrow());
        assertEquals(30, expressionContainerTransfer.getMaximumOfChildren().orElseThrow());
        assertEquals(30, expressionContainerTransfer.getMappedMaximumOfChildren().orElseThrow());
        assertEquals(80, expressionContainerTransfer.getSumOfChildren().orElseThrow());
        assertEquals(80, expressionContainerTransfer.getMappedSumOfChildren().orElseThrow());
        assertEquals(16, expressionContainerTransfer.getAverageOfChildren().orElseThrow());
        assertEquals(16, expressionContainerTransfer.getMappedAverageOfChildren().orElseThrow());
        assertFalse(expressionContainerTransfer.getNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertFalse(expressionContainerTransfer.getMappedNumberOfToysWithParentNameJasonIsThree().orElseThrow());
        assertTrue(expressionContainerTransfer.getNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertTrue(expressionContainerTransfer.getMappedNumberOfToysWithParentNameAndChildJasonIsOne().orElseThrow());
        assertTrue(expressionContainerTransfer.getNumberOfToysWithChildFilterIsOne().orElseThrow());
        assertTrue(expressionContainerTransfer.getMappedNumberOfToysWithChildFilterIsOne().orElseThrow());

        toyTransfersWithParentNameJason = expressionContainerTransferDao.queryToysWithParentNameJason().selectList();
        resultIds = toyTransfersWithParentNameJason.stream().map(t -> t.identifier().getIdentifier()).toList();
        assertEquals(4, toyTransfersWithParentNameJason.size());
        assertTrue(resultIds.containsAll(ids));
        assertFalse(resultIds.contains(toy2.identifier().getIdentifier()));

        assertEquals(4, expressionContainerTransfer.getNumberOfToysParentNameJason().orElseThrow());
        assertEquals(4, expressionContainerTransfer.getMappedNumberOfToysParentNameJason().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getNumberOfToys().orElseThrow());
        assertEquals(5, expressionContainerTransfer.getMappedNumberOfToys().orElseThrow());
        assertEquals(20, expressionContainerTransfer.getMinimumOfToys().orElseThrow());
        assertEquals(20, expressionContainerTransfer.getMappedMinimumOfToys().orElseThrow());
        assertEquals(60, expressionContainerTransfer.getMaximumOfToys().orElseThrow());
        assertEquals(60, expressionContainerTransfer.getMappedMaximumOfToys().orElseThrow());
        assertEquals(150, expressionContainerTransfer.getSumOfToys().orElseThrow());
        assertEquals(150, expressionContainerTransfer.getMappedSumOfToys().orElseThrow());
        assertEquals(37.5, expressionContainerTransfer.getAverageOfToys().orElseThrow());
        assertEquals(37.5, expressionContainerTransfer.getMappedAverageOfToys().orElseThrow());

        expressionContainer = expressionContainerDao.getById(expressionContainer.identifier()).orElseThrow();

        assertEquals(0, expressionContainer.getCircularNavigationSize().orElseThrow());
        assertTrue(expressionContainer.getCircularNavigationMinimum().isEmpty());
        assertTrue(expressionContainer.getCircularNavigationMaximum().isEmpty());
        assertTrue(expressionContainer.getCircularNavigationSum().isEmpty());
        assertTrue(expressionContainer.getCircularNavigationAverage().isEmpty());

        // TODO: JNG-6157
//        assertEquals(0, expressionContainer.getJs().size());
//        assertEquals(0, expressionContainerDao.queryJs(expressionContainer).count());

        expressionContainerTransfer = expressionContainerTransferDao.getById(expressionContainerTransfer.identifier()).orElseThrow();

        assertEquals(0, expressionContainerTransfer.getCircularNavigationSize().orElseThrow());
        assertTrue(expressionContainerTransfer.getCircularNavigationMinimum().isEmpty());
        assertTrue(expressionContainerTransfer.getCircularNavigationMaximum().isEmpty());
        assertTrue(expressionContainerTransfer.getCircularNavigationSum().isEmpty());
        assertTrue(expressionContainerTransfer.getCircularNavigationAverage().isEmpty());

        assertEquals(0, expressionContainerTransfer.getMappedCircularNavigationSize().orElseThrow());
        assertTrue(expressionContainerTransfer.getMappedCircularNavigationMinimum().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedCircularNavigationMaximum().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedCircularNavigationSum().isEmpty());
        assertTrue(expressionContainerTransfer.getMappedCircularNavigationAverage().isEmpty());

        EntityJ entityJ1 = entityJDao.create(EntityJForCreate.builder()
                .withNumber(1)
                .build());

        EntityJ entityJ2 = entityJDao.create(EntityJForCreate.builder()
                .withNumber(2)
                .build());

        EntityJ entityJ3 = entityJDao.create(EntityJForCreate.builder()
                .withNumber(3)
                .build());

        EntityJ entityJ4 = entityJDao.create(EntityJForCreate.builder()
                .withNumber(10)
                .build());

        entityJDao.create(EntityJForCreate.builder()
                .withNumber(4)
                .build());

        entityIDao.create(EntityIForCreate.builder()
                .withNumber(10)
                .withJs(List.of(entityJ1, entityJ2, entityJ3, entityJ4))
                .build());

        expressionContainer = expressionContainerDao.getById(expressionContainer.identifier()).orElseThrow();

        assertEquals(3, expressionContainer.getCircularNavigationSize().orElseThrow());
        assertEquals(1, expressionContainer.getCircularNavigationMinimum().orElseThrow());
        assertEquals(3, expressionContainer.getCircularNavigationMaximum().orElseThrow());
        assertEquals(6, expressionContainer.getCircularNavigationSum().orElseThrow());
        assertEquals(2, expressionContainer.getCircularNavigationAverage().orElseThrow());

        // TODO: JNG-6157
//        assertEquals(4, expressionContainer.getJs().size());
//        assertEquals(4, expressionContainerDao.queryJs(expressionContainer).count());

        expressionContainerTransfer = expressionContainerTransferDao.getById(expressionContainerTransfer.identifier()).orElseThrow();

        assertEquals(3, expressionContainerTransfer.getCircularNavigationSize().orElseThrow());
        assertEquals(1, expressionContainerTransfer.getCircularNavigationMinimum().orElseThrow());
        assertEquals(3, expressionContainerTransfer.getCircularNavigationMaximum().orElseThrow());
        assertEquals(6, expressionContainerTransfer.getCircularNavigationSum().orElseThrow());
        assertEquals(2, expressionContainerTransfer.getCircularNavigationAverage().orElseThrow());

        assertEquals(3, expressionContainerTransfer.getMappedCircularNavigationSize().orElseThrow());
        assertEquals(1, expressionContainerTransfer.getMappedCircularNavigationMinimum().orElseThrow());
        assertEquals(3, expressionContainerTransfer.getMappedCircularNavigationMaximum().orElseThrow());
        assertEquals(6, expressionContainerTransfer.getMappedCircularNavigationSum().orElseThrow());
        assertEquals(2, expressionContainerTransfer.getMappedCircularNavigationAverage().orElseThrow());

    }

}
