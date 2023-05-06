package hu.blackbelt.judo.runtime.core.jsl.entity;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.e.EDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.f.FDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.g.GDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.h.H;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.h.HAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.h.HDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.i.I;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.i.IDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.i.IIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.j.J;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.j.JDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.j.JIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.k.KDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.l.LDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.m.MDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.n.NDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.o.ODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.P;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.PDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.PIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.q.Q;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.q.QDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AbstractModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AbstractTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new AbstractModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "AbstractModel";
    }


    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Inject
    DDao dDao;

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
    JDao jDao;

    @Inject
    KDao kDao;

    @Inject
    MDao mDao;

    @Inject
    NDao nDao;

    @Inject
    LDao lDao;

    @Inject
    ODao oDao;

    @Inject
    PDao pDao;
    @Inject
    QDao qDao;

    /**
     * This test checks the abstract entities to have no create method.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AbstractModel.jsl
     *
     *
     * @positiveRequirements
     *
     * @negativeRequirements
     *
     * @scenario
     *
     *  Check the Dao's have no create methods
     *
     *
     */
    @Test
    @Disabled("JNG-4793")
    @TestCase("AbstractEntitiesHaveNoCreateMethods")
    @Requirement(reqs = {
            "",
    })
    void testAbstractEntitiesHaveNoCreateMethods() {

        assertFalse(hasMethodWithName("create", aDao));
        assertFalse(hasMethodWithName("create", bDao));
        assertFalse(hasMethodWithName("create", dDao));
        assertFalse(hasMethodWithName("create", eDao));
        assertFalse(hasMethodWithName("create", eDao));
        assertFalse(hasMethodWithName("create", gDao));
        assertFalse(hasMethodWithName("create", iDao));
        assertFalse(hasMethodWithName("create", kDao));
        assertFalse(hasMethodWithName("create", oDao));
        assertFalse(hasMethodWithName("create", pDao));

    }

    /**
     * The test checks dao functionality for an abstract entity with inherited child. (create,delete,update)
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AbstractModel.jsl
     *
     *
     * @negativeRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Create a C entity instance with the CDao.
     *
     *  Retrieve the c representation with the help of BDao.
     *
     *  Update the String field value with the BDao.
     *
     *  Check the String field updated in the c representation with the CDao.
     *
     *  Delete the b instance with the BDao.
     *
     *  Check if the c instance is also deleted.
     *
     */
    @Test
    @TestCase("InheritanceFromAnAbstractEntity")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
    })
    void testInheritanceFromAnAbstractEntity() {

        //Entities B,C

        C c = cDao.create(C.builder().build());
        assertTrue(cDao.getById(c.identifier()).isPresent());

        B b = CtoB(c);
        b.setNameB("NameChanged");
        b = bDao.update(b);
        c = cDao.getById(c.identifier()).orElseThrow();
        assertEquals(Optional.of("NameChanged"), c.getNameB());

        //TODO JNG-4803
//        bDao.delete(b);
//        assertTrue(bDao.getById(b.identifier()).isEmpty());
//        assertTrue(cDao.getById(c.identifier()).isEmpty());

    }

    private B CtoB(C c){
        return bDao.getById(c.identifier().adaptTo(BIdentifier.class)).orElseThrow();
    }

    /**
     *  The test checks all the dao functionality in an Entity with all relation type (Composition, Relation)
     *  which return type is an abstract entity, with inherited entity from the abstract entity.
     *
     *      H has all type of the relations, I is the type of the relations and J is a child entity of J.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AbstractModel.jsl
     *
     *
     * @positiveRequirements REQ-ENT-012
     *
     *
     * @scenario
     *
     *  Create the relation instance with the JDao.
     *
     *  Retrieve the relation representations with the help of IDao.
     *
     *  Create the H instance, with the composition elements inside.
     *
     *  Check if the I and J instance are equals.
     *
     *  Update relations String fields value with the IDao.
     *
     *  Check the relations String fields updated in the h representation with the HDao.
     *
     *  Delete relations with the IDao.
     *
     *  Check if the relations are deleted in the j representation with the JDao.
     *
     *  Add relations with the HDao.
     *
     *  Check the relations added with the HDao.
     *
     *  Remove relations with the HDao.
     *
     *  Check if the relations are removed with the HDao.
     *
     *  Unset relations with the HDao.
     *
     *  Check if the relations are unset with the HDao.
     *
     */
    @Test
    @TestCase("AbstractInheritedEntityRelationsAndCompositionsOnANotAbstractEntity")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004"
    })
    void testAbstractInheritedEntityRelationsAndCompositionsOnANotAbstractEntity() {

        //Entity H,I,J

        J jOptionalRelation = jDao.create(J.builder().build());
        I iOptionalRelation = JtoI(jOptionalRelation);
        J jRequiredRelation = jDao.create(J.builder().build());
        I iRequiredRelation = JtoI(jRequiredRelation);

        List<J> jMultiRelation = List.of(
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build())
        );
        List<I> iMultiRelation = jMultiRelation.stream().map(l -> JtoI(l)).collect(Collectors.toList());

        H h = hDao.create(H.builder()
                        .withCompositionIOnHSingle(JtoI(jDao.create(J.builder().build())))
                        .withCompositionIOnHSingleRequired(JtoI(jDao.create(J.builder().build())))
                        .withCompositionIOnHMulti(List.of(
                                jDao.create(J.builder().withNameI("V").build()),
                                jDao.create(J.builder().withNameI("V").build()),
                                jDao.create(J.builder().withNameI("V").build())
                        ).stream().map(l -> JtoI(l)).collect(Collectors.toList()))
                        .build(),
                HAttachedRelationsForCreate.builder()
                        .withRelationIOnHSingle(iOptionalRelation)
                        .withRelationIOnHSingleRequired(iRequiredRelation)
                        .withRelationIOnHMulti(iMultiRelation)
                        .build()
        );

        //Check j and i instance ar equal because i is live with j only
        assertEquals(iDao.getAll().size(), jDao.getAll().size());

        //update

        h.getCompositionIOnHSingle().get().setNameI("CompositionSingleChanged");
        h.getCompositionIOnHSingleRequired().setNameI("CompositionSingleRequiredChanged");
        h.getCompositionIOnHMulti().forEach(i -> i.setNameI("MultiCompositionNameChanged"));

        iOptionalRelation.setNameI("iOptionalRelationNameChanged");
        iRequiredRelation.setNameI("iRequiredRelationNameChanged");
        iMultiRelation.forEach(i -> i.setNameI("iMultiRelationNameChanged"));


        iDao.update(h.getCompositionIOnHSingle().get());
        iDao.update(h.getCompositionIOnHSingleRequired());
        h.getCompositionIOnHMulti().stream().map(i -> iDao.update(i));

        iOptionalRelation = iDao.update(iOptionalRelation);
        iRequiredRelation = iDao.update(iRequiredRelation);
        iMultiRelation = iMultiRelation.stream().map(i -> iDao.update(i)).collect(Collectors.toList());

        h = hDao.getById(h.identifier()).orElseThrow();

        //Check update
        assertEquals(Optional.of("CompositionSingleChanged"), h.getCompositionIOnHSingle().orElseThrow().getNameI());
        assertEquals(Optional.of("CompositionSingleRequiredChanged"), h.getCompositionIOnHSingleRequired().getNameI());

        //TODO JNG-4813
        //h.getCompositionIOnHMulti().stream().forEach( i -> assertEquals(Optional.of("MultiCompositionNameChanged"),i.getNameI()));
        //hDao.queryCompositionIOnHMulti.execute().stream().forEach( i -> assertEquals(Optional.of("iMultiRelationNameChanged"),i.getNameI()));

        assertEquals(Optional.of("iOptionalRelationNameChanged"), hDao.queryRelationIOnHSingle(h).orElseThrow().getNameI());
        assertEquals(Optional.of("iRequiredRelationNameChanged"), hDao.queryRelationIOnHSingleRequired(h).getNameI());
        hDao.queryRelationIOnHMulti(h).execute().stream().forEach(i -> assertEquals(Optional.of("iMultiRelationNameChanged"), i.getNameI()));

        //delete with IDao

        iDao.delete(h.getCompositionIOnHSingle().get());
        assertTrue(iDao.getById(h.getCompositionIOnHSingle().get().identifier()).isEmpty());
        assertTrue(jDao.getById(h.getCompositionIOnHSingle().get().identifier().adaptTo(JIdentifier.class)).isEmpty());
        assertEquals(iDao.getAll().size(),jDao.getAll().size());

        //TODO JNG-4812
//        iDao.delete(h.getCompositionIOnHSingleRequired()); //unfortunately you can delete required Component
//        h = hDao.getById(h.identifier()).orElseThrow();

        iDao.delete(h.getCompositionIOnHMulti().get(0));
        assertTrue(iDao.getById(h.getCompositionIOnHMulti().get(0).identifier()).isEmpty());
        h = hDao.getById(h.identifier()).orElseThrow();
        assertEquals(2,h.getCompositionIOnHMulti().size());

        //check the other methods are work well

        //add
        hDao.addCompositionIOnHMulti(h, Collections.singletonList(JtoI(jDao.create(J.builder().build()))));
        assertEquals(2, h.getCompositionIOnHMulti().size());
        h = hDao.getById(h.identifier()).orElseThrow();
        assertEquals(3, h.getCompositionIOnHMulti().size());

        assertEquals(3, hDao.countRelationIOnHMulti(h));
        hDao.addRelationIOnHMulti(h, List.of(JtoI(jDao.create(J.builder().build()))));
        assertEquals(4, hDao.countRelationIOnHMulti(h));
        h = hDao.getById(h.identifier()).orElseThrow();

        //remove
        hDao.removeCompositionIOnHMulti(h, List.of(h.getCompositionIOnHMulti().get(0)));
        assertEquals(3,h.getCompositionIOnHMulti().size());
        h = hDao.getById(h.identifier()).orElseThrow();
        assertEquals(2,h.getCompositionIOnHMulti().size());

        assertEquals(4,hDao.countRelationIOnHMulti(h));
        hDao.removeRelationIOnHMulti(h, List.of(hDao.queryRelationIOnHMulti(h).execute().get(0)) );
        assertEquals(3,hDao.countRelationIOnHMulti(h));
        h = hDao.getById(h.identifier()).orElseThrow();

        hDao.removeCompositionIOnHMulti(h, List.of(h.getCompositionIOnHMulti().get(0),h.getCompositionIOnHMulti().get(1)));
        assertEquals(2,h.getCompositionIOnHMulti().size());
        h = hDao.getById(h.identifier()).orElseThrow();
        assertEquals(0,h.getCompositionIOnHMulti().size());

        assertEquals(3,hDao.countRelationIOnHMulti(h));
        hDao.removeRelationIOnHMulti(h, List.of(hDao.queryRelationIOnHMulti(h).execute().get(0),hDao.queryRelationIOnHMulti(h).execute().get(1)) );
        assertEquals(1,hDao.countRelationIOnHMulti(h));
        h = hDao.getById(h.identifier()).orElseThrow();

        //set
        hDao.setCompositionIOnHSingle(h,JtoI(jDao.create(J.builder().withNameI("SetNewElement").build())));
        hDao.setRelationIOnHSingle(h, JtoI(jDao.create(J.builder().withNameI("SetNewElement").build())));

        h = hDao.getById(h.identifier()).orElseThrow();

        assertEquals(Optional.of("SetNewElement"),h.getCompositionIOnHSingle().orElseThrow().getNameI());
        assertEquals(Optional.of("SetNewElement"), hDao.queryRelationIOnHSingle(h).orElseThrow().getNameI());

        //Check j and i instance ar equal because i is live with j only
        assertEquals(iDao.getAll().size(), jDao.getAll().size());

        //unset

        hDao.unsetCompositionIOnHSingle(h);
        hDao.unsetRelationIOnHSingle(h);

        h = hDao.getById(h.identifier()).orElseThrow();

        assertEquals(Optional.empty(),h.getCompositionIOnHSingle());
        assertEquals(Optional.empty(), hDao.queryRelationIOnHSingle(h));

        assertEquals(iDao.getAll().size(), jDao.getAll().size());

    }

    /**
     * [description]
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AbstractModel.jsl
     *
     *
     * @positiveRequirements REQ-ENT-012
     *
     * @scenario
     *
     *
     */
    @Test
    @TestCase("AbstractEntityRelationsAndCompositionsOnAbstractEntityWithInheritedEntity")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-007",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004"
    })
    void testAbstractEntityRelationsAndCompositionsOnAbstractEntityWithInheritedEntity() {
        //TODO JNG-4803 If this issue will solve then implement this case.

        //Entity K.M,N,L

    }

    /**
     * The test checks the representation of the abstract entity and the child with query. The query collects and
     *  gives back the entity that is existing on the representation table.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel AbstractModel.jsl
     *
     *
     * @positiveRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Create an Q instance.
     *
     *  Get the I representation with the q instance id.
     *
     *  Check with p Dao query the instance exists.
     *
     *  Check with q Dao query the instance exists.
     *
     */
    @Test
    @TestCase("AbstractEntityQueryTest")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-009",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004"
    })
    void testAbstractEntityQueryTest() {

        //Entity O,P,Q

        Q q = qDao.create(Q.builder().build());
        P p = pDao.getById(q.identifier().adaptTo(PIdentifier.class)).orElseThrow();

        assertEquals(p.identifier().getIdentifier(), pDao.queryPany(p).orElseThrow().identifier().getIdentifier());
        assertEquals(p.identifier().getIdentifier(), pDao.queryPQany(p).orElseThrow().identifier().getIdentifier());
        assertEquals(q.identifier().getIdentifier(), pDao.queryQany(p).orElseThrow().identifier().getIdentifier());

        assertEquals(p.identifier().getIdentifier(), qDao.queryPany(q).orElseThrow().identifier().getIdentifier());
        assertEquals(p.identifier().getIdentifier(), qDao.queryPQany(q).orElseThrow().identifier().getIdentifier());
        assertEquals(q.identifier().getIdentifier(), qDao.queryQany(q).orElseThrow().identifier().getIdentifier());

    }

    private I JtoI(J j){
        return iDao.getById(j.identifier().adaptTo(IIdentifier.class)).orElseThrow();
    }

}
