package hu.blackbelt.judo.runtime.core.guice.jsl;

import hu.blackbelt.judo.meta.asm.runtime.AsmModel;
import hu.blackbelt.judo.meta.jsl.jsldsl.runtime.JslDslModel;
import hu.blackbelt.judo.meta.psm.runtime.PsmModel;
import hu.blackbelt.judo.tatami.core.TransformationMode;
import hu.blackbelt.judo.tatami.core.etl.EtlEngineRegistry;
import hu.blackbelt.judo.tatami.core.workflow.work.TransformationContext;
import hu.blackbelt.judo.tatami.jsl.jsl2psm.Jsl2PsmEtlEngine;
import hu.blackbelt.judo.tatami.psm2asm.Psm2AsmEtlEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Spec "Transformation mode is selectable per run": {@link JSLTransformationModelLoader} follows
 * {@code judo.transformation.mode}. The ETL add-ons are on the test classpath here; the real jsl2psm and psm2asm ETL
 * engines are wrapped in counting decorators (registered engines take precedence over ServiceLoader) to see which
 * engine ran. See change: split-etl-artifacts.
 */
class JSLTransformationModelLoaderModeTest {

    private final AtomicInteger jsl2psmEtlCalls = new AtomicInteger();
    private final AtomicInteger psm2asmEtlCalls = new AtomicInteger();
    private Jsl2PsmEtlEngine jsl2psm;
    private Psm2AsmEtlEngine psm2asm;
    private String previous;

    @BeforeEach
    void registerCountingEngines() {
        previous = System.getProperty(TransformationMode.SYSTEM_PROPERTY);
        Jsl2PsmEtlEngine realJsl2Psm = EtlEngineRegistry.require(Jsl2PsmEtlEngine.class, Jsl2PsmEtlEngine.ARTIFACT_COORDINATES);
        Psm2AsmEtlEngine realPsm2Asm = EtlEngineRegistry.require(Psm2AsmEtlEngine.class, Psm2AsmEtlEngine.ARTIFACT_COORDINATES);
        jsl2psm = (JslDslModel j, PsmModel p, Logger l, boolean trace, boolean behaviours, boolean parallel) -> {
            jsl2psmEtlCalls.incrementAndGet();
            return realJsl2Psm.execute(j, p, l, trace, behaviours, parallel);
        };
        psm2asm = (PsmModel p, AsmModel a, Logger l, boolean trace, boolean cache, boolean parallel) -> {
            psm2asmEtlCalls.incrementAndGet();
            return realPsm2Asm.execute(p, a, l, trace, cache, parallel);
        };
        EtlEngineRegistry.register(Jsl2PsmEtlEngine.class, jsl2psm);
        EtlEngineRegistry.register(Psm2AsmEtlEngine.class, psm2asm);
    }

    @AfterEach
    void restore() {
        EtlEngineRegistry.unregister(Jsl2PsmEtlEngine.class, jsl2psm);
        EtlEngineRegistry.unregister(Psm2AsmEtlEngine.class, psm2asm);
        if (previous == null) {
            System.clearProperty(TransformationMode.SYSTEM_PROPERTY);
        } else {
            System.setProperty(TransformationMode.SYSTEM_PROPERTY, previous);
        }
    }

    static TransformationContext load(String dir) {
        File target = new File("target/test-classes/loader-mode-" + dir);
        target.mkdirs();
        return JSLTransformationModelLoader.loadAndTransformJslModels("test2::SalesModel2", "hsqldb", target,
                new File("src/test/resources/models/sample.jsl").toURI(),
                new File("src/test/resources/models/sample2.jsl").toURI());
    }

    @Test
    void unsetRunsZeta() {
        System.clearProperty(TransformationMode.SYSTEM_PROPERTY);
        TransformationContext context = load("unset");
        assertTrue(context.getByClass(AsmModel.class).isPresent());
        assertEquals(0, jsl2psmEtlCalls.get() + psm2asmEtlCalls.get(), "no ETL engine may run in default mode");
    }

    @Test
    void etlWithAddOnsRunsEtl() {
        System.setProperty(TransformationMode.SYSTEM_PROPERTY, "ETL");
        TransformationContext context = load("etl");
        assertTrue(context.getByClass(AsmModel.class).isPresent());
        assertEquals(1, jsl2psmEtlCalls.get(), "jsl2psm ETL calls");
        assertEquals(1, psm2asmEtlCalls.get(), "psm2asm ETL calls (downstream stage follows the property)");
    }

    @Test
    void invalidValueRunsZeta() {
        System.setProperty(TransformationMode.SYSTEM_PROPERTY, "NO_SUCH_MODE");
        TransformationContext context = load("invalid");
        assertTrue(context.getByClass(AsmModel.class).isPresent());
        assertEquals(0, jsl2psmEtlCalls.get() + psm2asmEtlCalls.get(), "invalid value must fall back to ZETA");
    }
}
