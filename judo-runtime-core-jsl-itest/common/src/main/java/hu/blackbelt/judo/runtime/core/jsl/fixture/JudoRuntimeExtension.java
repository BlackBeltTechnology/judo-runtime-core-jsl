package hu.blackbelt.judo.runtime.core.jsl.fixture;


import com.google.inject.Module;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
public class JudoRuntimeExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private JudoDatasourceFixture judoDatasourceFixture;
    private JudoRuntimeFixture judoRuntimeFixture;

    private String modelName;

    private Module module;

    public JudoRuntimeExtension(String modelName, Module module) {
        judoDatasourceFixture = new JudoDatasourceFixture();
        judoRuntimeFixture = new JudoRuntimeFixture();
        this.modelName = modelName;
        this.module = module;
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        judoDatasourceFixture.setupDatabase();
        judoDatasourceFixture.prepareDatasources();
        try {
            judoRuntimeFixture.prepare(modelName, judoDatasourceFixture);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        judoDatasourceFixture.teardownDatasource();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        judoRuntimeFixture.init(module, context.getTestInstance().get());
        judoRuntimeFixture.beginTransaction();
        log.info("Running test: {}", context.getTestClass().map(c -> c.getSimpleName()).orElse("") + context.getTestMethod().map(m -> "#" + m.getName()).orElse(""));
    }

    @Override
    public void afterEach(ExtensionContext context) {
        judoRuntimeFixture.commitTransaction();
        judoDatasourceFixture.dropSchema();
        try {
            TransactionStatus transactionStatus = judoDatasourceFixture.getTransactionManager().getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_MANDATORY));
            judoDatasourceFixture.getTransactionManager().commit(transactionStatus);
        } catch (Exception e) {
        }
        log.info("Completed test: {}", context.getTestClass().map(c -> c.getSimpleName()).orElse("") + context.getTestMethod().map(m -> "#" + m.getName()).orElse(""));
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(JudoRuntimeFixture.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return judoRuntimeFixture;
    }
}
