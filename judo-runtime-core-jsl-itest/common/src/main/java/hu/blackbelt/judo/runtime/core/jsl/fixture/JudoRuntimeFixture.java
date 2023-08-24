package hu.blackbelt.judo.runtime.core.jsl.fixture;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.postgresql.JudoPostgresqlModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.Dialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlDialect;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testcontainers.containers.JdbcDatabaseContainer;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

import java.io.File;

import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_HSQLDB;
import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_POSTGRESQL;

@Slf4j
public class JudoRuntimeFixture {

    static {
        SLF4JBridgeHandler.install();
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
    }

    public static final String MODEL_SOURCES = "target/generated-test-sources/model";

    Injector injector;

    private TransactionStatus transactionStatus;

    private PlatformTransactionManager transactionManager;

    public void init(String modelName, Module module,Object injectModulesTo, JudoDatasourceFixture datasource)  throws Exception {
        JudoModelLoader modelHolder;

        if (DIALECT_POSTGRESQL.equals(datasource.getDialect())) {
            modelHolder = JudoModelLoader.loadFromDirectory(modelName, new File(MODEL_SOURCES), new PostgresqlDialect(), true);
            JdbcDatabaseContainer sqlContainer = datasource.getSqlContainer();
            JudoPostgresqlModules judoPostgresqlModules = JudoPostgresqlModules.builder()
                    .databaseName(sqlContainer.getDatabaseName())
                    .user(sqlContainer.getUsername())
                    .password(sqlContainer.getPassword())
                    .port(sqlContainer.getFirstMappedPort())
                    .build();

            injector = Guice.createInjector(judoPostgresqlModules, module, new JudoDefaultModule(injectModulesTo, modelHolder));
        } else if (DIALECT_HSQLDB.equals(datasource.getDialect())) {
            modelHolder = JudoModelLoader.loadFromDirectory(modelName, new File(MODEL_SOURCES), new HsqldbDialect(), true);


            injector = Guice.createInjector(JudoHsqldbModules.builder().build(), module, new JudoDefaultModule(injectModulesTo, modelHolder));
        } else {
            throw new IllegalArgumentException("Unsupported dialect: " + datasource.getDialect());
        }
    }

    public void tearDown() {
        rollbackTransaction();
    }

    private PlatformTransactionManager getTransactionManager() {
        if (transactionManager == null) {
            transactionManager = injector.getInstance(PlatformTransactionManager.class);
        }
        return transactionManager;
    }

    public void beginTransaction() {
        if (transactionStatus != null && !transactionStatus.isCompleted()) {
            throw new IllegalStateException("Previous transaction was not completed");
        }
        transactionStatus = getTransactionManager().getTransaction(new DefaultTransactionDefinition());
    }

    public void commitTransaction() {
        checkTransactionStatus();
        getTransactionManager().commit(transactionStatus);
    }

    public void rollbackTransaction() {
        checkTransactionStatus();
        getTransactionManager().rollback(transactionStatus);
    }

    public Object createSavePoint() {
        checkTransactionStatus();
        return transactionStatus.createSavepoint();
    }

    public void rollbackToSavePoint(Object savePoint) {
        checkTransactionStatus();
        transactionStatus.rollbackToSavepoint(savePoint);
    }

    private void checkTransactionStatus() {
        if (transactionStatus == null) {
            throw new IllegalStateException("TransactionStatus is null");
        }
        if (transactionStatus.isCompleted()) {
            throw new IllegalStateException("Transaction was already completed");
        }
    }

}
