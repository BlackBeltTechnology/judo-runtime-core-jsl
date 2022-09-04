package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.guice.navigationtest.NavigationTestDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.A;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.B;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.C;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class NavigationTest extends AbstractJslTest {
    @Inject
    A.ADao aDao;

    @Inject
    B.BDao bDao;

    @Inject
    C.CDao cDao;

    @Override
    public Module getModelDaoModule() {
        return new NavigationTestDaoModules();
    }

    @Override
    public String getModelName() {
        return "NavigationTest";
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

