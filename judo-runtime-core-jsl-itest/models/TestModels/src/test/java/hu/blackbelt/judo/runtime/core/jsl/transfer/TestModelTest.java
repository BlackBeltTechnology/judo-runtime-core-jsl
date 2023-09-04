package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.a.A;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.a.AAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.a.ADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.b.B;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.b.BDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.c.C;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testmodel.testmodel.c.CDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestModelDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.Assert.assertEquals;

@Slf4j
public class TestModelTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TestModel", new TestModelDaoModules());

    @Inject
    ADao aDao;

    @Inject
    BDao bDao;

    @Inject
    CDao cDao;

    @Test
    public void test1(){

        B bRel = bDao.create(B.builder().build());

        A a = aDao.create(A.builder().withComp(B.builder().withComp(C.builder().build()).build()).build(), AAttachedRelationsForCreate.builder().withRel(bRel).build());

        assertEquals(1, aDao.countAll());
        assertEquals(1, cDao.countAll());
        assertEquals(2, bDao.countAll());

    }

    @Test
    public void test2(){
        assertEquals(0, aDao.countAll());
        assertEquals(0, bDao.countAll());
        assertEquals(0, cDao.countAll());
    }

}
