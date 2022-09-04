package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.guice.navigationtest.NavigationTestDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.A;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.B;
import hu.blackbelt.judo.runtime.core.jsl.itest.navigationtest.sdk.navigationtest.navigationtest.C;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
abstract class AbstractJslTest {

    Injector injector;

    @BeforeEach
    protected void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader
                .loadFromDirectory(getModelName(), new File("target/generated-sources/model"), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                getModelDaoModule(),
                new JudoDefaultModule(this, modelHolder));
    }

    public abstract Module getModelDaoModule();

    public abstract String getModelName();

}

