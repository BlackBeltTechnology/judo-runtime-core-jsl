package hu.blackbelt.judo.runtime.core.jsl.entity;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.d.DDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.e.EDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.f.FDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.g.GDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.h.HAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.h.HDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.h.H;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.i.IDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.i.I;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.i.IIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.j.JDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.k.KDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.l.L;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.l.LDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.m.MDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.n.NDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.o.ODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.PDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.q.QDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.j.J;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.j.JIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AbstractModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
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


    @Test
    @Disabled("JNG-4793")
    @TestCase("")
    @Requirement(reqs = {
            "",
    })
    void testAbstractEntitiesHasNotCreateMethodes() {

        assertFalse(hasMethodWithName("create", aDao));
        assertFalse(hasMethodWithName("create", bDao));
        assertFalse(hasMethodWithName("create", dDao));
        assertFalse(hasMethodWithName("create", eDao));
        assertFalse(hasMethodWithName("create", eDao));
        assertFalse(hasMethodWithName("create", iDao));
        assertFalse(hasMethodWithName("create", kDao));
        assertFalse(hasMethodWithName("create", oDao));
        assertFalse(hasMethodWithName("create", pDao));

    }

    @Test
    @TestCase("")
    @Requirement(reqs = {
            "",
    })
    void testInheritanceFromAnAbstractEntities() {

        //Entities B,C

        //assertFalse(hasMethodWithName("create",bDao));
        C c = cDao.create(C.builder().build());
        assertTrue(cDao.getById(c.identifier()).isPresent());

        B b = CtoB(c);
        b.setNameB("NameChanged");
        b=bDao.update(b);
        c=cDao.getById(c.identifier()).orElseThrow();
        assertEquals(Optional.of("NameChanged"),c.getNameB());

        cDao.delete(c);
        assertTrue(cDao.getById(c.identifier()).isEmpty());
        assertTrue(bDao.getById(b.identifier()).isEmpty());

        //Entities D,E

        //assertFalse(hasMethodWithName("create", dDao));
        //assertFalse(hasMethodWithName("create", eDao));

    }

    private B CtoB(C c){
        return bDao.getById(c.identifier().adaptTo(BIdentifier.class)).orElseThrow();
    }

    @Test
    @TestCase("")
    @Requirement(reqs = {
            "",
    })
    void testAbstractEntitiesRelationsAndCompositionsOnANotAbstractEntity() {

        //Should this case not allowed on the JSL validation

        //Entity F and G

        //assertFalse(hasMethodWithName("createGOnFSingle", fDao));
        //assertFalse(hasMethodWithName("createGOnFSingleRequired", fDao));
        //assertFalse(hasMethodWithName("createGOnFMulti", fDao));

    }

    @Test
    @TestCase("")
    @Requirement(reqs = {
            "",
    })
    void testAbstractEntitiesWithInheritedEntityRelationsAndCompositionsOnANotAbstractEntity() {

        //Entity H,I,J

        //assertFalse(hasMethodWithName("create",iDao));

//        J jOptionalComposition = jDao.create(J.builder().build());
//        I iOptionalComposition = JtoI(jOptionalComposition);
//
//        List<J> jMultiComposition = List.of(
//                jDao.create(J.builder().build()),
//                jDao.create(J.builder().build()),
//                jDao.create(J.builder().build())
//        );
//        List<I> iMultiComposition = jMultiComposition.stream().map(l -> JtoI(l) ).collect(Collectors.toList());

        J jOptionalRelation = jDao.create(J.builder().build());
        I iOptionalRelation = JtoI(jOptionalRelation);
        J jRequiredRelation = jDao.create(J.builder().build());
        I iRequiredRelation = JtoI(jRequiredRelation);

        List<J> jMultiRelation = List.of(
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build())
        );
        List<I> iMultiRelation= jMultiRelation.stream().map(l -> JtoI(l) ).collect(Collectors.toList());

        H h = hDao.create(H.builder()
                        .withCompositionIOnHSingle(JtoI(jDao.create(J.builder().build())))
                        .withCompositionIOnHSingleRequired(JtoI(jDao.create(J.builder().build())))
                        .withCompositionIOnHMulti(List.of(
                                jDao.create(J.builder().withNameI("V").build()),
                                jDao.create(J.builder().withNameI("V").build()),
                                jDao.create(J.builder().withNameI("V").build())
                        ).stream().map(l -> JtoI(l) ).collect(Collectors.toList()))
                        .build(),
                HAttachedRelationsForCreate.builder()
                        .withRelationIOnHSingle(iOptionalRelation)
                        .withRelationIOnHSingleRequired(iRequiredRelation)
                        .withRelationIOnHMulti(iMultiRelation)
                        .build()
        );


        //Check j and i instance ar equal because i is live with j only
        assertEquals(iDao.getAll().size(),jDao.getAll().size());

        //update

        h.getCompositionIOnHSingle().get().setNameI("CompositionSingleChanged");
        h.getCompositionIOnHSingleRequired().setNameI("CompositionSingleRequiredChanged");
        h.getCompositionIOnHMulti().forEach(i -> i.setNameI("MultiCompositionNameChanged"));

        iOptionalRelation.setNameI("iOptionalRelationNameChanged");
        iRequiredRelation.setNameI("iRequiredRelationNameChanged");
        iMultiRelation.forEach(i -> i.setNameI("iMultiRelationNameChanged"));

        //update

        iDao.update(h.getCompositionIOnHSingle().get());
        iDao.update(h.getCompositionIOnHSingleRequired());
        h.getCompositionIOnHMulti().stream().map(i -> iDao.update(i));

        iOptionalRelation = iDao.update(iOptionalRelation);
        iRequiredRelation = iDao.update(iRequiredRelation);
        iMultiRelation = iMultiRelation.stream().map(i -> iDao.update(i)).collect(Collectors.toList());

        h = hDao.getById(h.identifier()).orElseThrow();
        //h = hDao.update(h);

        assertEquals(Optional.of("CompositionSingleChanged"),h.getCompositionIOnHSingle().orElseThrow().getNameI());
        assertEquals(Optional.of("CompositionSingleRequiredChanged"),h.getCompositionIOnHSingleRequired().getNameI());
        //h.getCompositionIOnHMulti().stream().forEach( i -> assertEquals(Optional.of("MultiCompositionNameChanged"),i.getNameI()));
        //hDao.queryCompositionIOnHMulti.execute().stream().forEach( i -> assertEquals(Optional.of("iMultiRelationNameChanged"),i.getNameI()));

        assertEquals(Optional.of("iOptionalRelationNameChanged"),hDao.queryRelationIOnHSingle(h).orElseThrow().getNameI());
        assertEquals(Optional.of("iRequiredRelationNameChanged"),hDao.queryRelationIOnHSingleRequired(h).getNameI());
        hDao.queryRelationIOnHMulti(h).execute().stream().forEach( i -> assertEquals(Optional.of("iMultiRelationNameChanged"),i.getNameI()));

        //delete with IDao

        //TODO JNG-4803
//        iDao.delete(h.getCompositionIOnHSingle().get());
//        assertTrue(iDao.getById(h.getCompositionIOnHSingle().get().identifier()).isEmpty());
//        assertEquals(iDao.getAll().size(),jDao.getAll().size()); // the j representation not deleted

        //TODO JNG-XXXX
//        iDao.delete(h.getCompositionIOnHSingleRequired()); //unfortunately you can delete required Component
//        h = hDao.getById(h.identifier()).orElseThrow();

        //TODO JNG-4803
//        iDao.delete(h.getCompositionIOnHMulti().get(0));
//        assertTrue(iDao.getById(h.getCompositionIOnHMulti().get(0).identifier()).isEmpty());
//        h = hDao.getById(h.identifier()).orElseThrow();
//        assertEquals(2,h.getCompositionIOnHMulti().size());

        //check the other method is work well

        //add

        hDao.addCompositionIOnHMulti(h, Collections.singletonList(JtoI( jDao.create(J.builder().build()))));
        assertEquals(3,h.getCompositionIOnHMulti().size());
        h = hDao.getById(h.identifier()).orElseThrow();
        assertEquals(4,h.getCompositionIOnHMulti().size());

        assertEquals(3,hDao.countRelationIOnHMulti(h));
        hDao.addRelationIOnHMulti(h,List.of(JtoI(jDao.create(J.builder().build()))));
        assertEquals(4,hDao.countRelationIOnHMulti(h));
        h = hDao.getById(h.identifier()).orElseThrow();

        //remove
        //TODO JNG-4803
//        hDao.removeCompositionIOnHMulti(h, List.of(h.getCompositionIOnHMulti().get(0)));
//        assertEquals(4,h.getCompositionIOnHMulti().size());
//        h = hDao.getById(h.identifier()).orElseThrow();
//        assertEquals(3,h.getCompositionIOnHMulti().size());
//
//        assertEquals(4,hDao.countRelationIOnHMulti(h));
//        hDao.removeRelationIOnHMulti(h, List.of(hDao.queryRelationIOnHMulti(h).execute().get(0)) );
//        assertEquals(3,hDao.countRelationIOnHMulti(h));
//        h = hDao.getById(h.identifier()).orElseThrow();
//
//        hDao.removeCompositionIOnHMulti(h, List.of(h.getCompositionIOnHMulti().get(0),h.getCompositionIOnHMulti().get(1)));
//        assertEquals(3,h.getCompositionIOnHMulti().size());
//        h = hDao.getById(h.identifier()).orElseThrow();
//        assertEquals(1,h.getCompositionIOnHMulti().size());
//
//        assertEquals(3,hDao.countRelationIOnHMulti(h));
//        hDao.removeRelationIOnHMulti(h, List.of(hDao.queryRelationIOnHMulti(h).execute().get(0),hDao.queryRelationIOnHMulti(h).execute().get(1)) );
//        assertEquals(1,hDao.countRelationIOnHMulti(h));
//        h = hDao.getById(h.identifier()).orElseThrow();

        //set

        //TODO JNG-4803
//        hDao.setCompositionIOnHSingle(h,JtoI(jDao.create(J.builder().withNameI("SetNewElement").build()))); //this delete the old comp but j is not deleted
        hDao.setRelationIOnHSingle(h,JtoI(jDao.create(J.builder().withNameI("SetNewElement").build())));

        h = hDao.getById(h.identifier()).orElseThrow();

        //TODO JNG-4803
//        assertEquals(Optional.of("SetNewElement"),h.getCompositionIOnHSingle().orElseThrow().getNameI());
        assertEquals(Optional.of("SetNewElement"),hDao.queryRelationIOnHSingle(h).orElseThrow().getNameI());

        //Check j and i instance ar equal because i is live with j only
        assertEquals(iDao.getAll().size(),jDao.getAll().size());

        //unset

        //TODO JNG-4803
//        hDao.unsetCompositionIOnHSingle(h); //this delete the old comp but j is not deleted
        hDao.unsetRelationIOnHSingle(h);

        h = hDao.getById(h.identifier()).orElseThrow();

        //TODO JNG-4803
//        assertEquals(Optional.empty(),h.getCompositionIOnHSingle());
        assertEquals(Optional.empty(),hDao.queryRelationIOnHSingle(h));

        //Check j and i instance ar equal because i is live with j only
        assertEquals(iDao.getAll().size(),jDao.getAll().size());

        //TODO JNG-4803
//        iDao.delete(iOptionalComposition);
//        assertTrue(iDao.getById(iOptionalComposition.identifier()).isEmpty());
//        assertTrue(jDao.getById(jOptionalComposition.identifier()).isEmpty());

//        J jOptionalComposition = ItoJ(h.getCompositionIOnHSingle().get());
//        assertTrue(jDao.getById(jOptionalComposition.identifier()).isPresent());
//        assertTrue(iDao.getById(h.getCompositionIOnHSingle().get().identifier()).isPresent());
//        jDao.delete(jOptionalComposition);
//        assertTrue(jDao.getById(jOptionalComposition.identifier()).isEmpty());
//        assertTrue(iDao.getById(h.getCompositionIOnHSingle().get().identifier()).isEmpty());

    }

    private I JtoI(J j){
        return iDao.getById(j.identifier().adaptTo(IIdentifier.class)).orElseThrow();
    }
    private J ItoJ(I i){
        return jDao.getById(i.identifier().adaptTo(JIdentifier.class)).orElseThrow();
    }


}
