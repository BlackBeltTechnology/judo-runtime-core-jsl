package hu.blackbelt.judo.runtime.core.guice.jsl;

import hu.blackbelt.judo.tatami.core.TransformationMode;
import hu.blackbelt.judo.tatami.core.etl.EtlEngineMissingException;
import hu.blackbelt.judo.tatami.jsl.jsl2psm.Jsl2PsmEtlEngine;
import hu.blackbelt.judo.tatami.jsl.jsl2ui.Jsl2UiEtlEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code judo.transformation.mode=ETL} without the ETL add-ons fails fast, naming the missing artifact. Runs in the
 * surefire execution {@code etl-addons-absent}, which removes every {@code -etl} add-on from the test classpath.
 * See change: split-etl-artifacts.
 */
class JSLTransformationModelLoaderEtlAbsentTest {

    private final String previous = System.getProperty(TransformationMode.SYSTEM_PROPERTY);

    @AfterEach
    void restore() {
        if (previous == null) {
            System.clearProperty(TransformationMode.SYSTEM_PROPERTY);
        } else {
            System.setProperty(TransformationMode.SYSTEM_PROPERTY, previous);
        }
    }

    @Test
    void etlWithoutAddOnsFailsFastNamingArtifact() {
        assertFalse(hu.blackbelt.judo.tatami.core.etl.EtlEngineRegistry.find(Jsl2PsmEtlEngine.class).isPresent()
                        || hu.blackbelt.judo.tatami.core.etl.EtlEngineRegistry.find(Jsl2UiEtlEngine.class).isPresent(),
                "precondition: JSL ETL add-ons must be absent from this execution's classpath");
        System.setProperty(TransformationMode.SYSTEM_PROPERTY, "ETL");
        Throwable thrown = assertThrows(RuntimeException.class, () -> JSLTransformationModelLoaderModeTest.load("absent"));
        EtlEngineMissingException missing = null;
        for (Throwable t = thrown; t != null && missing == null; t = t.getCause()) {
            if (t instanceof EtlEngineMissingException) {
                missing = (EtlEngineMissingException) t;
            }
        }
        assertNotNull(missing, "expected EtlEngineMissingException in cause chain of " + thrown);
        // jsl2psm and jsl2ui run in parallel: whichever starts first reports its add-on.
        String message = missing.getMessage();
        assertTrue(message.contains(Jsl2PsmEtlEngine.ARTIFACT_COORDINATES)
                || message.contains(Jsl2UiEtlEngine.ARTIFACT_COORDINATES), message);
    }
}
