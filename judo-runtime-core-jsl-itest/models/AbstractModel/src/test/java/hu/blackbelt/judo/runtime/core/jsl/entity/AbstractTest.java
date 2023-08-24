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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.k.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.l.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.m.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.n.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.o.ODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.P;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.PDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.PIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.q.Q;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.q.QDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AbstractModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslWithOneInjectionTest;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslWithOneInjectionTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AbstractTest extends AbstractJslWithOneInjectionTest {

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
    @TestCase("AbstractEntitiesHaveNoCreateMethods")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002"
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

        B b = bDao.getById(c.identifier().adaptTo(BIdentifier.class)).orElseThrow();
        b.setNameB("NameChanged");
        b = bDao.update(b);
        c = cDao.getById(c.identifier()).orElseThrow();
        assertEquals(Optional.of("NameChanged"), c.getNameB());

        bDao.delete(b);
        assertTrue(bDao.getById(b.identifier()).isEmpty());
        assertTrue(cDao.getById(c.identifier()).isEmpty());

    }

    /**
     *  The test checks all the dao functionality in an Entity with all relation type
     *  which return type is an abstract entity, with inherited entity from the abstract entity.
     *
     *      H has all type of the relations, I is the type of the relations and J is a child entity of I.
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
     *  Create the H instance.
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
    @TestCase("AbstractInheritedEntityRelationsOnANotAbstractEntity")
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
    void testAbstractInheritedEntityRelationsOnANotAbstractEntity() {

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
                        .build(),
                HAttachedRelationsForCreate.builder()
                        .withRelationIOnHSingle(iOptionalRelation)
                        .withRelationIOnHSingleRequired(iRequiredRelation)
                        .withRelationIOnHMulti(iMultiRelation)
                        .build()
        );

        //Check if instances of j and i are equal because all instances of j are also instances of i.
        assertEquals(iDao.getAll().size(), jDao.getAll().size());

        //update

        iOptionalRelation.setNameI("iOptionalRelationNameChanged");
        iRequiredRelation.setNameI("iRequiredRelationNameChanged");
        iMultiRelation.forEach(i -> i.setNameI("iMultiRelationNameChanged"));

        iOptionalRelation = iDao.update(iOptionalRelation);
        iRequiredRelation = iDao.update(iRequiredRelation);
        iMultiRelation = iMultiRelation.stream().map(i -> iDao.update(i)).collect(Collectors.toList());

        h = hDao.getById(h.identifier()).orElseThrow();

        //Check update

        assertEquals(Optional.of("iOptionalRelationNameChanged"), hDao.queryRelationIOnHSingle(h).orElseThrow().getNameI());
        assertEquals(Optional.of("iRequiredRelationNameChanged"), hDao.queryRelationIOnHSingleRequired(h).getNameI());
        hDao.queryRelationIOnHMulti(h).execute().stream().forEach(i -> assertEquals(Optional.of("iMultiRelationNameChanged"), i.getNameI()));

        //delete relations with IDao

        I deleteSingleRelation = hDao.queryRelationIOnHSingle(h).orElseThrow();
        iDao.delete(deleteSingleRelation);
        assertTrue(iDao.getById(deleteSingleRelation.identifier()).isEmpty());
        assertTrue(jDao.getById(deleteSingleRelation.identifier().adaptTo(JIdentifier.class)).isEmpty());
        assertEquals(iDao.getAll().size(), jDao.getAll().size());


        I deleteSingleRequiredRelation = hDao.queryRelationIOnHSingleRequired(h);
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> iDao.delete(deleteSingleRequiredRelation)
        );

        assertTrue(thrown.getMessage().contains("There are mandatory references that cannot be removed"));
        assertTrue(thrown.getMessage().contains("#relationIOnHSingleRequired"));


        List<I> deleteMultiRelation = hDao.queryRelationIOnHMulti(h).execute();
        iDao.delete(deleteMultiRelation.get(0));
        assertTrue(iDao.getById(deleteMultiRelation.get(0).identifier()).isEmpty());
        h = hDao.getById(h.identifier()).orElseThrow();
        assertEquals(2, hDao.queryRelationIOnHMulti(h).execute().size());

        //check if other methods are working well

        //add
        assertEquals(2, hDao.countRelationIOnHMulti(h));
        hDao.addRelationIOnHMulti(h, List.of(JtoI(jDao.create(J.builder().build()))));
        assertEquals(3, hDao.countRelationIOnHMulti(h));
        h = hDao.getById(h.identifier()).orElseThrow();

        //remove
        assertEquals(3, hDao.countRelationIOnHMulti(h));
        hDao.removeRelationIOnHMulti(h, List.of(hDao.queryRelationIOnHMulti(h).execute().get(0)));
        assertEquals(2, hDao.countRelationIOnHMulti(h));
        h = hDao.getById(h.identifier()).orElseThrow();

        assertEquals(2, hDao.countRelationIOnHMulti(h));
        hDao.removeRelationIOnHMulti(h, List.of(hDao.queryRelationIOnHMulti(h).execute().get(0), hDao.queryRelationIOnHMulti(h).execute().get(1)));
        assertEquals(0, hDao.countRelationIOnHMulti(h));
        h = hDao.getById(h.identifier()).orElseThrow();

        //set
        hDao.setRelationIOnHSingle(h, JtoI(jDao.create(J.builder().withNameI("SetNewElement").build())));

        h = hDao.getById(h.identifier()).orElseThrow();

        assertEquals(Optional.of("SetNewElement"), hDao.queryRelationIOnHSingle(h).orElseThrow().getNameI());

        //Check if instances of j and i are equal because all instances of j are also instances of i.
        assertEquals(iDao.getAll().size(), jDao.getAll().size());

        //unset

        hDao.unsetRelationIOnHSingle(h);
        h = hDao.getById(h.identifier()).orElseThrow();
        assertEquals(Optional.empty(), hDao.queryRelationIOnHSingle(h));
        assertEquals(iDao.getAll().size(), jDao.getAll().size());

    }

    /**
     *  The test checks all the dao functionality in an abstract Entity with all relation type
     *  which return type is an abstract entity, with inherited entity from the abstract entity.
     *
     *      K has all type of the relations, M is the type of the relations and N is a child entity of M.
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
     *  Create the relation instance with the MDao.
     *
     *  Retrieve the relation representations with the help of NDao.
     *
     *  Create the L instance.
     *
     *  Retrieve the relation representations with the help of KDao.
     *
     *  Check if the L and K instance are equals.
     *
     *  Update relations String fields value with the MDao.
     *
     *  Check the relations String fields updated in the l representation with the lDao.
     *
     *  Check the relations String fields updated in the k representation with the kDao.
     *
     *  Delete relations with the MDao.
     *
     *  Check if the relations are deleted in the l representation with the lDao.
     *
     *  Check if the relations are deleted in the k representation with the kDao.
     *
     *  Add relations with the kDao.
     *
     *  Check the relations added with the kDao.
     *
     *  Check the relations added with the lDao.
     *
     *  Remove relations with the kDao.
     *
     *  Check if the relations are removed with the kDao.
     *
     *  Check if the relations are removed with the lDao.
     *
     *  Unset relations with the kDao.
     *
     *  Check if the relations are unset with the kDao.
     *
     *  Check if the relations are unset with the lDao.
     *
     */
    @Test
    @TestCase("AbstractEntityRelationsOnAbstractEntityWithInheritedEntity")
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
    void testAbstractEntityRelationsOnAbstractEntityWithInheritedEntity() {
        //TODO JNG-4831

        //Entity K.M,N,L

        N nOptionalRelation = nDao.create(N.builder().build());
        M mOptionalRelation = NtoM(nOptionalRelation);
        N nRequiredRelation = nDao.create(N.builder().build());
        M mRequiredRelation = NtoM(nRequiredRelation);

        List<N> nMultiRelation = List.of(
                nDao.create(N.builder().build()),
                nDao.create(N.builder().build()),
                nDao.create(N.builder().build())
        );
        List<M> mMultiRelation = nMultiRelation.stream().map(l -> NtoM(l)).collect(Collectors.toList());

        L l = lDao.create(L.builder()
                        .build(),
                LAttachedRelationsForCreate.builder()
                        .withRelationMOnKSingle(mOptionalRelation)
                        .withRelationMOnKSingleRequired(mRequiredRelation)
                        .withRelationMOnKMulti(mMultiRelation)
                        .build()
        );

        assertTrue(kDao.getById(l.identifier().adaptTo(KIdentifier.class)).isPresent());

        K k = kDao.getById(l.identifier().adaptTo(KIdentifier.class)).orElseThrow();

        assertEquals(lDao.getAll().size(), kDao.getAll().size());

        //update

        mOptionalRelation.setNameM("mOptionalRelationNameChanged");
        mRequiredRelation.setNameM("mRequiredRelationNameChanged");
        mMultiRelation.forEach(i -> i.setNameM("mMultiRelationNameChanged"));

        mOptionalRelation = mDao.update(mOptionalRelation);
        mRequiredRelation = mDao.update(mRequiredRelation);
        mMultiRelation = mMultiRelation.stream().map(i -> mDao.update(i)).collect(Collectors.toList());

        l = lDao.getById(l.identifier()).orElseThrow();
        k = kDao.getById(l.identifier().adaptTo(KIdentifier.class)).orElseThrow();

        //Check update

        assertEquals(Optional.of("mOptionalRelationNameChanged"), lDao.queryRelationMOnKSingle(l).orElseThrow().getNameM());
        assertEquals(Optional.of("mRequiredRelationNameChanged"), lDao.queryRelationMOnKSingleRequired(l).getNameM());
        lDao.queryRelationMOnKMulti(l).execute().stream().forEach(i -> assertEquals(Optional.of("mMultiRelationNameChanged"), i.getNameM()));

        assertEquals(Optional.of("mOptionalRelationNameChanged"), kDao.queryRelationMOnKSingle(k).orElseThrow().getNameM());
        assertEquals(Optional.of("mRequiredRelationNameChanged"), kDao.queryRelationMOnKSingleRequired(k).getNameM());
        kDao.queryRelationMOnKMulti(k).execute().stream().forEach(i -> assertEquals(Optional.of("mMultiRelationNameChanged"), i.getNameM()));


        //delete relations with IDao

        M deleteSingleRelation = kDao.queryRelationMOnKSingle(k).orElseThrow();
        mDao.delete(deleteSingleRelation);
        assertTrue(mDao.getById(deleteSingleRelation.identifier()).isEmpty());
        assertTrue(kDao.queryRelationMOnKSingle(k).isEmpty());
        assertTrue(lDao.queryRelationMOnKSingle(l).isEmpty());

        M deleteSingleRequiredRelation = kDao.queryRelationMOnKSingleRequired(k);
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> mDao.delete(deleteSingleRequiredRelation)
        );

        assertTrue(thrown.getMessage().contains("There are mandatory references that cannot be removed"));
        assertTrue(thrown.getMessage().contains("#relationMOnKSingleRequired"));

        List<M> deleteMultiRelation = kDao.queryRelationMOnKMulti(k).execute();
        mDao.delete(deleteMultiRelation.get(0));
        assertTrue(mDao.getById(deleteMultiRelation.get(0).identifier()).isEmpty());
        assertEquals(2, lDao.queryRelationMOnKMulti(l).execute().size());
        assertEquals(2, kDao.queryRelationMOnKMulti(k).execute().size());

        //Check the collection methods

        //add
        assertEquals(2, kDao.countRelationMOnKMulti(k));
        kDao.addRelationMOnKMulti(k, List.of(NtoM(nDao.create(N.builder().build()))));
        assertEquals(3, kDao.countRelationMOnKMulti(k));
        assertEquals(3, lDao.countRelationMOnKMulti(l));

        //remove
        assertEquals(3, kDao.countRelationMOnKMulti(k));
        kDao.removeRelationMOnKMulti(k, List.of(kDao.queryRelationMOnKMulti(k).execute().get(0)));
        assertEquals(2, kDao.countRelationMOnKMulti(k));
        assertEquals(2, lDao.countRelationMOnKMulti(l));

        assertEquals(2, kDao.countRelationMOnKMulti(k));
        kDao.removeRelationMOnKMulti(k, List.of(kDao.queryRelationMOnKMulti(k).execute().get(0), kDao.queryRelationMOnKMulti(k).execute().get(1)));
        assertEquals(0, kDao.countRelationMOnKMulti(k));
        assertEquals(0, lDao.countRelationMOnKMulti(l));

        //set
        kDao.setRelationMOnKSingle(k, NtoM(nDao.create(N.builder().withNameM("SetNewElement").build())));

        l = lDao.getById(l.identifier()).orElseThrow();
        k = kDao.getById(l.identifier().adaptTo(KIdentifier.class)).orElseThrow();

        assertEquals(Optional.of("SetNewElement"), kDao.queryRelationMOnKSingle(k).orElseThrow().getNameM());
        assertEquals(Optional.of("SetNewElement"), lDao.queryRelationMOnKSingle(l).orElseThrow().getNameM());

        //unset
        kDao.unsetRelationMOnKSingle(k);
        l = lDao.getById(l.identifier()).orElseThrow();
        k = kDao.getById(l.identifier().adaptTo(KIdentifier.class)).orElseThrow();
        assertEquals(Optional.empty(), kDao.queryRelationMOnKSingle(k));
        assertEquals(Optional.empty(), lDao.queryRelationMOnKSingle(l));

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

    private I JtoI(J j) {
        return iDao.getById(j.identifier().adaptTo(IIdentifier.class)).orElseThrow();
    }

    private M NtoM(N n) {
        return mDao.getById(n.identifier().adaptTo(MIdentifier.class)).orElseThrow();
    }

}
