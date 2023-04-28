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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.l.LDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.m.MDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.n.NDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.o.ODao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.p.PDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.q.QDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstractmodel.abstractmodel.j.J;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AbstractModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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

        J jOptionalComposition = jDao.create(J.builder().build());
        I iOptionalComposition = JtoI(jOptionalComposition);

        List<J> jMultiComposition = List.of(
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build())
        );
        List<I> iMultiComposition = jMultiComposition.stream().map(l -> JtoI(l) ).collect(Collectors.toList());

        J jOptionalRelation = jDao.create(J.builder().build());
        I iOptionalRelation = JtoI(jOptionalRelation);
        J jRequiredRelation = jDao.create(J.builder().build());
        I iRequiredRelation = JtoI(jRequiredRelation);

        List<J> jMultiRelation = List.of(
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build()),
                jDao.create(J.builder().build())
        );
        List<I> iMultiRelation= jMultiComposition.stream().map(l -> JtoI(l) ).collect(Collectors.toList());

        H h = hDao.create(H.builder()
                        .withCompositionIOnHSingle(iOptionalComposition)
                        .withCompositionIOnHSingleRequired(JtoI(jDao.create(J.builder().build())))
                        .withCompositionIOnHMulti(iMultiComposition)
                        .build(),
                HAttachedRelationsForCreate.builder()
                        .withRelationIOnHSingle(iOptionalRelation)
                        .withRelationIOnHSingleIOnHSingleRequired(iRequiredRelation)
                        .withRelationIOnHSingleIOnHMulti(iMultiRelation)
                        .build()
        );
        //TODO JNG-4803
//        iDao.delete(iOptionalComposition);
//        assertTrue(iDao.getById(iOptionalComposition.identifier()).isEmpty());
//        assertTrue(jDao.getById(jOptionalComposition.identifier()).isEmpty());

        //update

        h.getCompositionIOnHSingle().get().setNameI("CompositionSingleChanged");
        h.getCompositionIOnHSingleRequired().setNameI("CompositionSingleRequiredChanged");
        h.getCompositionIOnHMulti().forEach(i -> i.setNameI("MultiCompositionNameChanged"));

        iOptionalRelation.setNameI("iOptionalRelationNamechanged");
        iRequiredRelation.setNameI("iRequiredRelationNamechanged");
        iMultiRelation.forEach(i -> i.setNameI("iMultiRelationNameChanged"));



        assertTrue(iDao.getById(iOptionalComposition.identifier()).isPresent());
        assertTrue(jDao.getById(jOptionalComposition.identifier()).isPresent());
        jDao.delete(jOptionalComposition);
        assertTrue(jDao.getById(jOptionalComposition.identifier()).isEmpty());
        assertTrue(iDao.getById(iOptionalComposition.identifier()).isEmpty());



    }

    private I JtoI(J j){
        return iDao.getById(j.identifier().adaptTo(IIdentifier.class)).orElseThrow();
    }


}
