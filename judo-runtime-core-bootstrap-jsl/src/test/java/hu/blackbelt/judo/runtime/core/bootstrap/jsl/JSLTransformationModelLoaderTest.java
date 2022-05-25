package hu.blackbelt.judo.runtime.core.bootstrap.jsl;

import com.google.common.collect.ImmutableList;
import hu.blackbelt.judo.tatami.core.workflow.work.TransformationContext;
import org.junit.jupiter.api.Test;

import java.io.File;

import static hu.blackbelt.judo.tatami.jsl.workflow.DefaultWorkflowSave.saveModels;

class JSLTransformationModelLoaderTest {

    @Test
    void loadAndTransformJslModels() {
        File target = new File("target/test-classes/loader-test");
        target.mkdirs();

        TransformationContext context = JSLTransformationModelLoader.loadAndTransformJslModels("test2::SalesModel2", "hsqldb",
                target,
                new File("src/test/resources/models/sample.jsl").toURI(),
                new File("src/test/resources/models/sample2.jsl").toURI()
        );

        saveModels(context, target, ImmutableList.of("hsqldb"));
    }
}