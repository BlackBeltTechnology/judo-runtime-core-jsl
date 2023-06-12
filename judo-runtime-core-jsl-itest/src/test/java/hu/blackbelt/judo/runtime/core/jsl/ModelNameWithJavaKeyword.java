package hu.blackbelt.judo.runtime.core.jsl;


import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AbstractDaoModules;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstract_.abstract_.enum_.Enum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelNameWithJavaKeyword extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new AbstractDaoModules();
    }

    @Override
    public String getModelName() {
        return "Abstract";
    }
}