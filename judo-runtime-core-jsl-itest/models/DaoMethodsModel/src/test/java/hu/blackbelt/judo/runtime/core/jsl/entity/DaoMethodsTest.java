package hu.blackbelt.judo.runtime.core.jsl.entity;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.daomethods.daomethods.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.daomethods.daomethods.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.daomethods.daomethods.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.daomethods.daomethods.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.daomethods.daomethods.b.BIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.DaoMethodsDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DaoMethodsTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new DaoMethodsDaoModules();
    }

    @Override
    public String getModelName() {
        return "DaoMethods";
    }

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;


    /**
     * This test checks the collection sdk methods work well.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel DaoMethods.jsl
     *
     */
    @Test
    @TestCase("CollectionSDKMethods")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003"
    })
    void testCollectionSDKMethods() {

        A a = aDao.create(A.builder().build());

        B b1 = aDao.createRelationBOnA(a, List.of(B.builder().withName("B1").build())).get(0);

        assertFalse(aDao.queryRelationBOnA(a).execute().isEmpty());
        assertEquals(1, aDao.queryRelationBOnA(a).count());
        assertThat(ListOfRelationBonANames(a), hasItems("B1"));
        assertThat(ListOfRelationBonAIds(a), hasItems(b1.identifier()));

        B b2 = aDao.createRelationBOnA(a, List.of(B.builder().withName("B2").build())).get(0);

        assertEquals(2, aDao.queryRelationBOnA(a).count());
        assertThat(ListOfRelationBonANames(a), hasItems("B1", "B2"));
        assertThat(ListOfRelationBonAIds(a), hasItems(b1.identifier(), b2.identifier()));

        B b3 = aDao.createRelationBOnA(a, B.builder().withName("B3").build());

        assertTrue(bDao.getById(b3.identifier()).isPresent());

        assertEquals(3, aDao.queryRelationBOnA(a).count());
        assertThat(ListOfRelationBonANames(a), hasItems("B1", "B2", "B3"));
        assertThat(ListOfRelationBonAIds(a), hasItems(b1.identifier(), b2.identifier(), b3.identifier()));

        B b4 = bDao.create(B.builder().withName("B4").build());

        aDao.addRelationBOnA(a, b4);

        assertEquals(4, aDao.queryRelationBOnA(a).count());
        assertThat(ListOfRelationBonANames(a), hasItems("B1", "B2", "B3", "B4"));
        assertThat(ListOfRelationBonAIds(a), hasItems(b1.identifier(), b2.identifier(), b3.identifier(), b4.identifier()));

        aDao.removeRelationBOnA(a, b3);

        assertEquals(3, aDao.queryRelationBOnA(a).count());
        assertThat(ListOfRelationBonANames(a), hasItems("B1", "B2", "B4"));
        assertThat(ListOfRelationBonAIds(a), hasItems(b1.identifier(), b2.identifier(), b4.identifier()));

        aDao.removeRelationBOnA(a, List.of(b1, b2));

        assertEquals(1, aDao.queryRelationBOnA(a).count());
        assertThat(ListOfRelationBonANames(a), hasItems("B4"));
        assertThat(ListOfRelationBonAIds(a), hasItems(b4.identifier()));

    }

    private  List<String> ListOfRelationBonANames(A a){
        return aDao.queryRelationBOnA(a).execute().stream().map(B::getName).filter(Optional::isPresent).map(Optional::get).toList();
    }

    private  List<BIdentifier> ListOfRelationBonAIds(A a){
        return aDao.queryRelationBOnA(a).execute().stream().map(B::identifier).toList();
    }
}
