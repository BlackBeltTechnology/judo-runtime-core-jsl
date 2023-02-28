package hu.blackbelt.judo.runtime.core.jsl.fixture;

import org.junit.jupiter.api.extension.*;
import org.springframework.transaction.TransactionStatus;

public class RdbmsDatasourceByClassExtension implements BeforeAllCallback, AfterAllCallback, AfterEachCallback, ParameterResolver, BeforeEachCallback {

    private RdbmsDatasourceFixture  rdbmsDatasourceFixture =  new RdbmsDatasourceFixture();

    private TransactionStatus transactionStatus;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        rdbmsDatasourceFixture.setupDatasource();
        rdbmsDatasourceFixture.prepareDatasources();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        rdbmsDatasourceFixture.teardownDatasource();
    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(RdbmsDatasourceFixture.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return rdbmsDatasourceFixture;
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
//        if (rdbmsDatasourceFixture.getTransactionManager(). == Status.STATUS_ACTIVE) {
//            rdbmsDatasourceFixture.getTransactionManager().rollback();
////            rdbmsDatasourceFixture.getTransactionManager().commit();
//        }
//        if (transactionStatus != null && !transactionStatus.isCompleted()) {
//            rdbmsDatasourceFixture.getTransactionManager().rollback(transactionStatus);
//        }
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
//        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
//        transactionStatus = rdbmsDatasourceFixture.getTransactionManager().getTransaction(defaultTransactionDefinition);
    }
}
