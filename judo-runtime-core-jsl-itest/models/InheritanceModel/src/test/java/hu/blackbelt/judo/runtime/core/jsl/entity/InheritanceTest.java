package hu.blackbelt.judo.runtime.core.jsl.entity;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.compositionentity.CompositionEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.compositionentity.CompositionEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.compositiontransfer.CompositionTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.compositiontransfer.CompositionTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.e.E;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.e.EDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.e.EIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.f.F;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.f.FDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.g.G;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.g.GDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.h.H;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.h.HDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.i.I;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.i.IDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parenta.ParentA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parenta.ParentADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parenta.ParentAIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentabstract.ParentAbstractDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentatransfer.ParentATransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentatransfer.ParentATransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentatransfer.ParentATransferIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentb.ParentB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentb.ParentBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentbtransfer.ParentBTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.parentbtransfer.ParentBTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.relationentity.RelationEntity;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.relationentity.RelationEntityDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.relationtransfer.RelationTransfer;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.relationtransfer.RelationTransferDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.transfere.TransferE;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritance.inheritance.transfere.TransferEDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.InheritanceDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class InheritanceTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("Inheritance", new InheritanceDaoModules());

    @Inject
    EDao eDao;

    @Inject
    FDao fDao;

    @Inject
    GDao gDao;

    @Inject
    HDao hDao;

    @Inject
    IDao iDao;

    @Inject
    ParentAbstractDao parentAbstractDao;

    @Inject
    ParentADao parentADao;

    @Inject
    CompositionEntityDao compositionEntityDao;

    @Inject
    CompositionTransferDao compositionTransferDao;

    @Inject
    ParentATransferDao parentATransferDao;

    @Inject
    TransferEDao transferEDao;

    @Inject
    ParentBDao parentBDao;

    @Inject
    ParentBTransferDao parentBTransferDao;

    @Inject
    RelationEntityDao relationEntityDao;
    @Inject
    RelationTransferDao relationTransferDao;

    /**
     * This test check of the child entity contains all the parent entities attributes.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel inheritance.jsl
     *
     *
     * @positiveRequirements  REQ-ENT-012
     *
     * @scenario
     *
     *  Create one instance of the E entity
     *
     *  Check all the attributes is present of the parent entity in the E instance.
     *
     *  Create one instance of the F entity
     *
     *  Check all the of the parents entities attributes is present (parentA,ParentB,ParentAbstract) in the F instance.
     *
     */
    @Test
    @TestCase("InheritedEntitiesContainsAllOfParentAttributes")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-EXPR-005",
    })
    void testInheritedEntitiesContainsAllOfParentAttributes() {


        E e = eDao.create(E.builder().build());

        assertTrue(hasMethodWithName("getNameA", e));
        assertTrue(hasMethodWithName("getIdentifierA", e));
        assertTrue(hasMethodWithName("getDerivedA", e));
        assertTrue(hasMethodWithName("queryRelationEntities", eDao));


        F f = fDao.create(F.builder().build());

        assertTrue(hasMethodWithName("getNameA", f));
        assertTrue(hasMethodWithName("getNameB", f));
        assertTrue(hasMethodWithName("getNameAbstract", f));
        assertTrue(hasMethodWithName("getIdentifierA", f));
        assertTrue(hasMethodWithName("getDerivedA", f));
        assertTrue(hasMethodWithName("queryRelationEntities", fDao));

    }

    /**
     * Testing an entity that inheritance form an inherited entity.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel inheritance.jsl
     *
     *
     * @positiveRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Create one instance of the G entity
     *
     *  Check all the of the parents entities attributes is present (E -> parentA,ParentB) in the G instance.
     *
     */
    @Test
    @TestCase("MultiLevelInheritanceIsAllowed")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004"
    })
    void testMultiLevelInheritanceIsAllowed() {

        G g = gDao.create(G.builder().build());

        assertTrue(hasMethodWithName("getNameA", g));
        assertTrue(hasMethodWithName("getNameB", g));
        assertTrue(hasMethodWithName("getNameE", g));
        assertTrue(hasMethodWithName("getIdentifierA", g));
        assertTrue(hasMethodWithName("getDerivedA", g));
        assertTrue(hasMethodWithName("queryRelationEntities", gDao));

    }

    /**
     * Testing the inherited required attributes missing in the child entity
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel inheritance.jsl
     *

     * @negativeRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Create one instance of the H entity without attribute parameters.
     *
     *  Check the thrown expectation contains all the missing required attributes
     */
    @Test
    @TestCase("InheritedEntitiesContainsAllOfRequiredParentAttributes")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004"
    })
    void testInheritedEntitiesContainsAllOfRequiredParentAttributes() {

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> hDao.create(H.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchMissingAttribute("integerAttr"),
                matchMissingAttribute("scaledAttr"),
                matchMissingAttribute("stringAttr"),
                matchMissingAttribute("regexAttr"),
                matchMissingAttribute("boolAttr"),
                matchMissingAttribute("dateAttr"),
                matchMissingAttribute("timestampAttr"),
                matchMissingAttribute("timeAttr"),
                matchMissingAttribute("enumAttr")
        ));
        List<H> list = hDao.query().selectList();

        assertEquals(0, list.size());

    }

    /**
     * Testing the inherited entity contains the attributes with default values of the parent entity.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel inheritance.jsl
     *
     *
     * @positiveRequirements REQ-ENT-012
     *
     *
     * @scenario
     *
     *  Create one instance of the I entity without attribute parameters.
     *
     *  Check all the of the parents entity attributes is present (ParentDefaultD) in the I instance and all of them contains the default value.
     *
     */
    @Test
    @TestCase("InheritedEntitiesContainsAllOfDefaultValueParentAttributes")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
    })
    void testInheritedEntitiesContainsAllOfDefaultValueParentAttributes() {

        I i = iDao.create(I.builder().build());

        assertEquals(Optional.of(1), i.getIntegerAttr());
        assertEquals(Optional.of(2.34), i.getScaledAttr());
        assertEquals(Optional.of("test"), i.getStringAttr());
        assertEquals(Optional.of("+36 (30) 123 1234"), i.getRegexAttr());
        assertEquals(Optional.of(true), i.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), i.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), i.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), i.getTimeAttr());
        assertEquals(Optional.of(MyEnum.Bombastic), i.getEnumAttr());

    }

    /**
     * Testing the abstract entity Dao cannot create instances.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel inheritance.jsl

     * @negativeRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Try to create one instance of the ParentAbstract entity.
     *
     *  Should throw a validation error, because abstract entities cannot create instances.
     *
     */
    @Test
    @TestCase("AbstractEntityInstanceThrowError")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
    })
    void testAbstractEntityInstanceThrowError() {
        assertFalse(hasMethodWithName("create", parentAbstractDao));
    }

    @Test
    void testMultipleQueryByID() {
        CompositionEntity compositionEntity1 = compositionEntityDao.create(CompositionEntity.builder().withName("C1").build());
        CompositionEntity compositionEntity2 = compositionEntityDao.create(CompositionEntity.builder().withName("C2").build());

        ParentA parentA1 = parentADao.create(ParentA.builder().withNameA("A1").withEntity(compositionEntity1).build());
        ParentA parentA2 = parentADao.create(ParentA.builder().withNameA("A2").withEntity(compositionEntity2).build());
        ParentA parentA3 = parentADao.create(ParentA.builder().withNameA("A3").build());
        parentADao.createRelationEntities(parentA1, List.of(RelationEntity.builder().withName("R1").build()
                                                , RelationEntity.builder().withName("R2").build()));
        parentADao.createRelationEntities(parentA2, List.of(RelationEntity.builder().withName("R3").build()));
        List<UUID> uuids = new ArrayList<>();
        List<ParentA> parentAS = parentADao.findAllById(uuids);
        assertEquals(0, parentAS.size());

        uuids.add((UUID) parentA1.identifier().getIdentifier());

        parentAS = parentADao.findAllById(uuids);

        assertEquals(1, parentAS.size());
        ParentA parentA1Test = parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A1")).findFirst().orElseThrow();

        assertEquals(parentA1Test.identifier().getIdentifier(), parentA1.identifier().getIdentifier());
        assertEquals("C1", parentA1Test.getEntity().orElseThrow().getName().orElseThrow());

        List<RelationEntity> relationEntities = parentADao.queryRelationEntities(parentA1Test).selectList();

        assertEquals(2, relationEntities.size());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R1")).count());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R2")).count());

        uuids.add((UUID) parentA2.identifier().getIdentifier());
        uuids.add((UUID) parentA3.identifier().getIdentifier());

        parentAS = parentADao.findAllById(uuids);

        assertEquals(3, parentAS.size());

        parentA1Test = parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A1")).findFirst().orElseThrow();

        assertEquals(parentA1Test.identifier().getIdentifier(), parentA1.identifier().getIdentifier());
        assertEquals("C1", parentA1Test.getEntity().orElseThrow().getName().orElseThrow());

        relationEntities = parentADao.queryRelationEntities(parentA1Test).selectList();

        assertEquals(2, relationEntities.size());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R1")).count());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R2")).count());

        ParentA parentA2Test = parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A2")).findFirst().orElseThrow();

        assertEquals(parentA1Test.identifier().getIdentifier(), parentA1.identifier().getIdentifier());
        assertEquals("C2", parentA2Test.getEntity().orElseThrow().getName().orElseThrow());

        relationEntities = parentADao.queryRelationEntities(parentA2Test).selectList();

        assertEquals(1, relationEntities.size());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R3")).count());

        assertEquals(1, parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A3")).count());

        // E is a descandent of ParentA
        E entityE = eDao.create(E.builder().withNameA("AInherited").build());

        uuids.add((UUID) entityE.identifier().getIdentifier());

        parentAS = parentADao.findAllById(uuids);

        assertEquals(4, parentAS.size());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A1")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A2")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A3")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("AInherited")).count());

        // ParentB is not a descandent of ParentA
        ParentB parentB = parentBDao.create(ParentB.builder().build());

        uuids.add((UUID) parentB.identifier().getIdentifier());

        parentAS = parentADao.findAllById(uuids);

        assertEquals(4, parentAS.size());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A1")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A2")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A3")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("AInherited")).count());
    }


    @Test
    void testMultipleQueryByIDOnTransfer() {

        CompositionTransfer compositionTransfer1 = compositionTransferDao.create(CompositionTransfer.builder().withName("C1").build());
        CompositionTransfer compositionTransfer2 = compositionTransferDao.create(CompositionTransfer.builder().withName("C2").build());

        ParentATransfer parentA1 = parentATransferDao.create(ParentATransfer.builder().withNameA("A1").withEntity(compositionTransfer1).build());
        ParentATransfer parentA2 = parentATransferDao.create(ParentATransfer.builder().withNameA("A2").withEntity(compositionTransfer2).build());
        ParentATransfer parentA3 = parentATransferDao.create(ParentATransfer.builder().withNameA("A3").build());
        parentATransferDao.createRelationTransfers(parentA1, List.of(RelationTransfer.builder().withName("R1").build()
                , RelationTransfer.builder().withName("R2").build()));
        parentATransferDao.createRelationTransfers(parentA2, List.of(RelationTransfer.builder().withName("R3").build()));
        List<UUID> uuids = new ArrayList<>();
        List<ParentATransfer> parentAS = parentATransferDao.findAllById(uuids);
        assertEquals(0, parentAS.size());

        uuids.add((UUID) parentA1.identifier().getIdentifier());

        parentAS = parentATransferDao.findAllById(uuids);

        assertEquals(1, parentAS.size());
        ParentATransfer parentA1Test = parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A1")).findFirst().orElseThrow();

        assertEquals(parentA1Test.identifier().getIdentifier(), parentA1.identifier().getIdentifier());
        assertEquals("C1", parentA1Test.getEntity().orElseThrow().getName().orElseThrow());

        List<RelationTransfer> relationEntities = parentATransferDao.queryRelationTransfers(parentA1Test).selectList();

        assertEquals(2, relationEntities.size());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R1")).count());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R2")).count());

        uuids.add((UUID) parentA2.identifier().getIdentifier());
        uuids.add((UUID) parentA3.identifier().getIdentifier());

        parentAS = parentATransferDao.findAllById(uuids);

        assertEquals(3, parentAS.size());

        parentA1Test = parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A1")).findFirst().orElseThrow();

        assertEquals(parentA1Test.identifier().getIdentifier(), parentA1.identifier().getIdentifier());
        assertEquals("C1", parentA1Test.getEntity().orElseThrow().getName().orElseThrow());

        relationEntities = parentATransferDao.queryRelationTransfers(parentA1Test).selectList();

        assertEquals(2, relationEntities.size());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R1")).count());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R2")).count());

        ParentATransfer parentA2Test = parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A2")).findFirst().orElseThrow();

        assertEquals(parentA1Test.identifier().getIdentifier(), parentA1.identifier().getIdentifier());
        assertEquals("C2", parentA2Test.getEntity().orElseThrow().getName().orElseThrow());

        relationEntities = parentATransferDao.queryRelationTransfers(parentA2Test).selectList();

        assertEquals(1, relationEntities.size());
        assertEquals(1, relationEntities.stream()
                .filter(r -> r.getName().orElseThrow().equals("R3")).count());

        assertEquals(1, parentAS.stream().filter(p -> p.getNameA().orElseThrow().equals("A3")).count());

        // E is a descandent of ParentATransfer
        TransferE transferE = transferEDao.create(TransferE.builder().withNameA("AInherited").build());

        uuids.add((UUID) transferE.identifier().getIdentifier());

        parentAS = parentATransferDao.findAllById(uuids);

        assertEquals(4, parentAS.size());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A1")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A2")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A3")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("AInherited")).count());

        // ParentB is not a descandent of ParentATransfer
        ParentBTransfer parentB = parentBTransferDao.create(ParentBTransfer.builder().build());

        uuids.add((UUID) parentB.identifier().getIdentifier());

        parentAS = parentATransferDao.findAllById(uuids);

        assertEquals(4, parentAS.size());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A1")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A2")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("A3")).count());
        assertEquals(1, parentAS.stream().filter(p -> !Optional.empty().equals(p.getNameA()) && p.getNameA().orElseThrow().equals("AInherited")).count());
    }

    @Test
    void testExistsByID() {
        CompositionEntity compositionEntity = compositionEntityDao.create(CompositionEntity.builder().withName("C1").build());

        RelationEntity relationEntity = RelationEntity.builder().withName("R1").build();

        ParentA parentA1 = parentADao.create(ParentA.builder().withNameA("A1").withEntity(compositionEntity).build());
        parentADao.createRelationEntities(parentA1, List.of(relationEntity));

        assertTrue(parentADao.existsById((UUID) parentA1.identifier().getIdentifier()));
        parentA1 = parentADao.getById(parentA1.identifier()).orElseThrow();
        assertEquals("A1", parentA1.getNameA().orElseThrow());

        assertTrue(compositionTransferDao.existsById((UUID) parentA1.getEntity().orElseThrow().identifier().getIdentifier()));
        assertEquals("C1", parentA1.getEntity().orElseThrow().getName().orElseThrow());

        relationEntity = parentADao.queryRelationEntities(parentA1).selectOne().orElseThrow();
        assertTrue(relationEntityDao.existsById((UUID) relationEntity.identifier().getIdentifier()));
        assertEquals("R1", relationEntity.getName().orElseThrow());

        parentADao.delete(parentA1);

        assertFalse(parentADao.existsById((UUID) parentA1.identifier().getIdentifier()));
        assertFalse(compositionEntityDao.existsById((UUID) parentA1.getEntity().orElseThrow().identifier().getIdentifier()));
        assertTrue(relationEntityDao.existsById((UUID) relationEntity.identifier().getIdentifier()));
        assertEquals("R1", relationEntity.getName().orElseThrow());

        E entityE = eDao.create(E.builder().withNameA("E1").build());

        assertTrue(eDao.existsById((UUID) entityE.identifier().getIdentifier()));

        entityE = eDao.getById(entityE.identifier()).orElseThrow();

        assertEquals("E1", entityE.getNameA().orElseThrow());

        assertTrue(parentADao.existsById((UUID) entityE.identifier().getIdentifier()));
        assertTrue(parentADao.existsById((UUID) entityE.identifier().adaptTo(ParentAIdentifier.class).getIdentifier()));

        ParentA parentA = parentADao.getById(entityE.identifier().adaptTo(ParentAIdentifier.class)).orElseThrow();

        assertEquals("E1", parentA.getNameA().orElseThrow());

        eDao.delete(entityE);

        assertFalse(eDao.existsById((UUID) entityE.identifier().getIdentifier()));

        assertFalse(parentADao.existsById(null));
    }

    @Test
    void testExistsByIDOnTransfer() {
        CompositionTransfer compositionTransfer1 = compositionTransferDao.create(CompositionTransfer.builder().withName("C1").build());

        RelationTransfer relationTransfer1 = RelationTransfer.builder().withName("R1").build();

        ParentATransfer parentA1 = parentATransferDao.create(ParentATransfer.builder().withNameA("A1").withEntity(compositionTransfer1).build());
        parentATransferDao.createRelationTransfers(parentA1, List.of(relationTransfer1));

        assertTrue(parentATransferDao.existsById((UUID) parentA1.identifier().getIdentifier()));
        parentA1 = parentATransferDao.getById(parentA1.identifier()).orElseThrow();
        assertEquals("A1", parentA1.getNameA().orElseThrow());

        assertTrue(compositionTransferDao.existsById((UUID) parentA1.getEntity().orElseThrow().identifier().getIdentifier()));
        assertEquals("C1", parentA1.getEntity().orElseThrow().getName().orElseThrow());

        relationTransfer1 = parentATransferDao.queryRelationTransfers(parentA1).selectOne().orElseThrow();
        assertTrue(relationTransferDao.existsById((UUID) relationTransfer1.identifier().getIdentifier()));
        assertEquals("R1", relationTransfer1.getName().orElseThrow());

        ParentA parentAEntity = parentADao.getById(parentA1.identifier().adaptTo(ParentAIdentifier.class)).orElseThrow();
        RelationEntity relationEntity = parentADao.queryRelationEntities(parentAEntity).selectOne().orElseThrow();

        assertEquals("A1", parentAEntity.getNameA().orElseThrow());
        assertEquals("C1", parentAEntity.getEntity().orElseThrow().getName().orElseThrow());
        assertEquals("R1", relationEntity.getName().orElseThrow());

        parentATransferDao.delete(parentA1);

        assertFalse(parentATransferDao.existsById((UUID) parentA1.identifier().getIdentifier()));
        assertFalse(compositionTransferDao.existsById((UUID) parentA1.getEntity().orElseThrow().identifier().getIdentifier()));
        assertTrue(relationTransferDao.existsById((UUID) relationTransfer1.identifier().getIdentifier()));
        assertEquals("R1", relationTransfer1.getName().orElseThrow());

        TransferE transferE = transferEDao.create(TransferE.builder().withNameA("E1").build());

        assertTrue(eDao.existsById((UUID) transferE.identifier().getIdentifier()));

        transferE = transferEDao.getById(transferE.identifier()).orElseThrow();
        assertEquals("E1", transferE.getNameA().orElseThrow());

        E entityE = eDao.getById(transferE.identifier().adaptTo(EIdentifier.class)).orElseThrow();
        assertEquals("E1", entityE.getNameA().orElseThrow());


        assertTrue(parentATransferDao.existsById((UUID) transferE.identifier().getIdentifier()));
        assertTrue(parentATransferDao.existsById((UUID) transferE.identifier().adaptTo(ParentATransferIdentifier.class).getIdentifier()));
        ParentATransfer parentATransfer = parentATransferDao.getById(transferE.identifier().adaptTo(ParentATransferIdentifier.class)).orElseThrow();

        assertEquals("E1", parentATransfer.getNameA().orElseThrow());
        assertTrue(transferEDao.existsById((UUID) parentATransfer.identifier().getIdentifier()));

        transferEDao.delete(transferE);

        assertFalse(transferEDao.existsById((UUID) transferE.identifier().getIdentifier()));

        assertFalse(parentATransferDao.existsById(null));
    }


    Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

    public boolean hasMethodWithName(String methodName,Object object) {
        return Arrays.stream(object.getClass().getDeclaredMethods())
                .anyMatch(f -> f.getName().equals(methodName));
    }
}
