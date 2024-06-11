package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.a.AForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.b.BForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.relationcreationwithattachment.relationcreationwithattachment.c.CForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.RelationCreationWithAttachmentDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
public class RelationCreateWithAttachmentTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("RelationCreationWithAttachment", new RelationCreationWithAttachmentDaoModules());

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

        A a = aDao.create(AForCreate.builder().build());

        C c = cDao.create(CForCreate.builder().withName("H").build());
        C c1 = cDao.create(CForCreate.builder().build());

        B b = aDao.createRelB(a, BForCreate.builder().withRelC(c).build());

        assertTrue(aDao.queryRelB(a).isPresent());
        assertTrue(bDao.queryRelC(b).isPresent());
        assertEquals(Optional.of("H"), bDao.queryRelC(b).get().getName());

        aDao.createRelBColl(a, List.of(BForCreate.builder().withName("B1").withRelC(c).build(), BForCreate.builder().withName("B2").withRelC(c1).build()));

        assertEquals(2, aDao.queryRelBColl(a).count());
        B b1 = aDao.queryRelBColl(a).filterByName(StringFilter.equalTo("B1")).selectOne().get();
        B b2 = aDao.queryRelBColl(a).filterByName(StringFilter.equalTo("B2")).selectOne().get();

        assertEquals(c.identifier().getIdentifier(), bDao.queryRelC(b1).get().identifier().getIdentifier());
        assertEquals(c1.identifier().getIdentifier(), bDao.queryRelC(b2).get().identifier().getIdentifier());

        B b3 = aDao.createCompB(a, BForCreate.builder().withRelC(c).build());

        assertEquals(c.identifier().getIdentifier(), bDao.queryRelC(b3).get().identifier().getIdentifier());

    }

}
