package hu.blackbelt.judo.runtime.core.jsl.fixture;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import hu.blackbelt.judo.meta.expression.builder.jql.JqlExpressionBuilderConfig;
import hu.blackbelt.judo.meta.expression.builder.jql.asm.AsmJqlExtractor;
import hu.blackbelt.judo.runtime.core.guice.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbRdbmsInit;
import hu.blackbelt.judo.runtime.core.dao.rdbms.liquibase.SimpleLiquibaseExecutor;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlDialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlRdbmsInit;
import hu.blackbelt.judo.runtime.core.jsl.modules.JudoDefaultTestModule;
import hu.blackbelt.judo.runtime.core.jsl.modules.JudoHsqldbTestModules;
import hu.blackbelt.judo.runtime.core.jsl.modules.JudoPostgresqlTestModules;
import hu.blackbelt.judo.runtime.core.query.QueryFactory;
import hu.blackbelt.mapper.api.ExtendableCoercer;
import hu.blackbelt.mapper.impl.DefaultCoercer;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.URI;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testcontainers.containers.JdbcDatabaseContainer;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_HSQLDB;
import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_POSTGRESQL;
import static java.util.Objects.requireNonNullElse;

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

    public JudoModelLoader modelHolder;

    JdbcDatabaseContainer sqlContainer;

    JudoPostgresqlTestModules judoPostgresqlTestModules;

    JudoHsqldbTestModules judoHsqldbTestModules;

    HsqldbDialect hsqldbDialect;

    PostgresqlDialect postgresqlDialect;

    SimpleLiquibaseExecutor simpleLiquibaseExecutor;

    ExtendableCoercer coercer;

    QueryFactory queryFactory;

    private void initQueryFactory() {

        coercer = new DefaultCoercer();

        JqlExpressionBuilderConfig jqlExpressionBuilderConfig = new JqlExpressionBuilderConfig();
        jqlExpressionBuilderConfig.setResolveOnlyCurrentLambdaScope(false);

        final AsmJqlExtractor asmJqlExtractor = new AsmJqlExtractor(modelHolder.getAsmModel().getResourceSet(),
                modelHolder.getMeasureModel().getResourceSet(), URI.createURI("expr:" + modelHolder.getAsmModel().getName()), jqlExpressionBuilderConfig);

        queryFactory = new QueryFactory(
                modelHolder.getAsmModel().getResourceSet(),
                modelHolder.getMeasureModel().getResourceSet(),
                asmJqlExtractor.extractExpressions(),
                coercer,
                requireNonNullElse(null, ECollections.asEMap(new ConcurrentHashMap<>())));

    }

    private void initJudoHsqldbModules(JudoDatasourceFixture datasource) {

        simpleLiquibaseExecutor = new SimpleLiquibaseExecutor();
        HsqldbRdbmsInit init = HsqldbRdbmsInit.builder()
                .liquibaseExecutor(simpleLiquibaseExecutor)
                .liquibaseModel(modelHolder.getLiquibaseModel())
                .build();
        init.execute(datasource.getDataSource());

        judoHsqldbTestModules = JudoHsqldbTestModules
                .builder()
                .dataSource(datasource.getDataSource())
                .hsqldbRdbmsInit(init)
                .build();
    }

    private void initJudoPostgresqlModules(JudoDatasourceFixture datasource) {

        simpleLiquibaseExecutor = new SimpleLiquibaseExecutor();
        PostgresqlRdbmsInit init = PostgresqlRdbmsInit.builder()
                .liquibaseExecutor(simpleLiquibaseExecutor)
                .liquibaseModel(modelHolder.getLiquibaseModel())
                .build();
        init.execute(datasource.getDataSource());

        sqlContainer = datasource.getSqlContainer();
        judoPostgresqlTestModules = JudoPostgresqlTestModules.builder()
                .databaseName(sqlContainer.getDatabaseName())
                .user(sqlContainer.getUsername())
                .password(sqlContainer.getPassword())
                .port(sqlContainer.getFirstMappedPort())
                .dataSource(datasource.getDataSource())
                .postgresqlRdbmsInit(init)
                .build();
    }

    public void prepare(String modelName, JudoDatasourceFixture datasource) throws Exception {

        if (DIALECT_POSTGRESQL.equals(datasource.getDialect())) {
            postgresqlDialect = new PostgresqlDialect();
            modelHolder = JudoModelLoader.loadFromDirectory(modelName, new File(MODEL_SOURCES), postgresqlDialect, true);
            initQueryFactory();
            initJudoPostgresqlModules(datasource);
        } else if (DIALECT_HSQLDB.equals(datasource.getDialect())) {
            hsqldbDialect = new HsqldbDialect();
            modelHolder = JudoModelLoader.loadFromDirectory(modelName, new File(MODEL_SOURCES), hsqldbDialect, true);
            initQueryFactory();
            initJudoHsqldbModules(datasource);
        } else {
            throw new IllegalArgumentException("Unsupported dialect: " + datasource.getDialect());
        }
    }

    public void init(Module module, Object injectModulesTo) {

        if (postgresqlDialect != null ) {
            injector = Guice.createInjector(judoPostgresqlTestModules, module, JudoDefaultTestModule.builder().injectModulesTo(injectModulesTo).judoModelLoader(modelHolder).coercer(coercer).queryFactory(queryFactory).build());
        } else if (hsqldbDialect != null ) {
            injector = Guice.createInjector(judoHsqldbTestModules, module, JudoDefaultTestModule.builder().injectModulesTo(injectModulesTo).judoModelLoader(modelHolder).coercer(coercer).queryFactory(queryFactory).build());
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
