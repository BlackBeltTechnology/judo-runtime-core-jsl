package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;

//import hu.blackbelt.skillmatrix.test.guice.test.TestDaoModules;
//import hu.blackbelt.skillmatrix.test.sdk.test.test.A;
//import hu.blackbelt.skillmatrix.test.sdk.test.test.B;
//import hu.blackbelt.skillmatrix.test.sdk.test.test.C;


import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.guice.navigationtest.NavigationTestDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.A;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.B;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.C;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.cli.Options;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class NavigationTest {

    Injector injector;

    @Inject
    A.ADao aDao;

    @Inject
    B.BDao bDao;

    @Inject
    C.CDao cDao;

    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("NavigationTest", NavigationTest.class.getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new NavigationTestDaoModules(),
                new JudoDefaultModule(this, modelHolder));

    }

    @Test
    public void test() {
        A a = aDao.create(A.builder().build());
        B b = bDao.create(B.builder().build());
        C c = cDao.create(C.builder().build());

        aDao.addBlist(a, List.of(b));
        bDao.setC(b, c);

        // Read derived list over DAO call
        List<C> cList = aDao.getClist(a);
        assertEquals(1, cList.size());

    }
}

