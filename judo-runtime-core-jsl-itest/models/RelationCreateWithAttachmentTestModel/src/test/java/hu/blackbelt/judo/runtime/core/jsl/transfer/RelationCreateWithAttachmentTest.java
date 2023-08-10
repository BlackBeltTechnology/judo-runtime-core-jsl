package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.a.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.b.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.c.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RelationCreationWithAttachmentDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
public class RelationCreateWithAttachmentTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new RelationCreationWithAttachmentDaoModules();
    }

    @Override
    public String getModelName() {
        return "RelationCreationWithAttachment";
    }


    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    /**
     * The test checks when creating new compositions or relations with the DAO create relation methods using the attachedRelation parameter..
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel RelationCreationWithAttachment.jsl
     *
     *
     */
    @Test
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
    })
    void testAttachedRelationsForCreateMethod() {

        A a = aDao.create(A.builder().build());

        C c = cDao.create(C.builder().withName("H").build());
        C c1 = cDao.create(C.builder().build());

        B b = aDao.createRelB(a, B.builder().build(), BAttachedRelationsForCreate.builder().withRelC(c).build());

        assertTrue(aDao.queryRelB(a).isPresent());
        assertTrue(bDao.queryRelC(b).isPresent());
        assertEquals(Optional.of("H"), bDao.queryRelC(b).get().getName());

        aDao.createRelBColl(a, List.of(B.builder().withName("B1").build(), B.builder().withName("B2").build()), List.of(BAttachedRelationsForCreate.builder().withRelC(c).build(), BAttachedRelationsForCreate.builder().withRelC(c1).build()));

        assertEquals(2, aDao.queryRelBColl(a).count());
        B b1 = aDao.queryRelBColl(a).filterByName(StringFilter.equalTo("B1")).execute().get(0);
        B b2 = aDao.queryRelBColl(a).filterByName(StringFilter.equalTo("B2")).execute().get(0);

        assertEquals(c.identifier(), bDao.queryRelC(b1).get().identifier());
        assertEquals(c1.identifier(), bDao.queryRelC(b2).get().identifier());

        B b3 = aDao.createCompB(a, B.builder().build(), BAttachedRelationsForCreate.builder().withRelC(c).build());

        assertEquals(c.identifier(), bDao.queryRelC(b3).get().identifier());

    }

}