package hu.blackbelt.judo.runtime.core.jsl.fixture;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

@Slf4j
public class JudoRuntimeByClassExtension implements ParameterResolver, BeforeEachCallback, AfterEachCallback, BeforeAllCallback {

    private JudoRuntimeFixture judoRuntimeFixture;

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(JudoRuntimeFixture.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return judoRuntimeFixture;
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        judoRuntimeFixture = new JudoRuntimeFixture();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("Running test: {}", context.getTestClass().map(c -> c.getSimpleName()).orElse("") + context.getTestMethod().map(m -> "#" + m.getName()).orElse(""));
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        log.info("Completed test: {}", context.getTestClass().map(c -> c.getSimpleName()).orElse("") + context.getTestMethod().map(m -> "#" + m.getName()).orElse(""));
    }
}
