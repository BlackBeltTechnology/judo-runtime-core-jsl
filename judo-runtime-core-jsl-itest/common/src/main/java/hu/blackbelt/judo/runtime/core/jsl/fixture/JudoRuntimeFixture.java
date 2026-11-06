package hu.blackbelt.judo.runtime.core.jsl.fixture;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import hu.blackbelt.judo.meta.expression.builder.jql.JqlExpressionBuilderConfig;
import hu.blackbelt.judo.meta.expression.builder.jql.asm.AsmJqlExtractor;
import hu.blackbelt.judo.runtime.core.dao.rdbms.Dialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.RdbmsInit;
import hu.blackbelt.judo.runtime.core.guice.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.guice.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbRdbmsInit;
import hu.blackbelt.judo.runtime.core.dao.rdbms.liquibase.SimpleLiquibaseExecutor;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlDialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlRdbmsInit;
import hu.blackbelt.judo.runtime.core.guice.dao.rdbms.hsqldb.JudoHsqldbModule;
import hu.blackbelt.judo.runtime.core.guice.dao.rdbms.postgresql.JudoPostgresqlModule;
import hu.blackbelt.judo.runtime.core.query.CustomJoinDefinition;
import hu.blackbelt.judo.runtime.core.query.QueryFactory;
import hu.blackbelt.judo.runtime.core.query.QueryModelLoadOrBuild;
import hu.blackbelt.mapper.api.ExtendableCoercer;
import hu.blackbelt.mapper.impl.DefaultCoercer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testcontainers.containers.JdbcDatabaseContainer;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_HSQLDB;
import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_POSTGRESQL;

@Slf4j
public class JudoRuntimeFixture {

    static {
        SLF4JBridgeHandler.install();
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
    }

    public JudoRuntimeFixture(Map<String, Object> context) {
        if (context == null) {
            return;
        }
    }

    public JudoRuntimeFixture() {}

    public static final String MODEL_SOURCES = "target/generated-test-sources/model";

    Injector injector;

    private TransactionStatus transactionStatus;

    private PlatformTransactionManager transactionManager;

    public JudoModelLoader modelHolder;

    JdbcDatabaseContainer sqlContainer;

    Dialect dialect;

    JudoDefaultModule.JudoDefaultModuleBuilder judoDefaultModuleBuilder;
    Module databaseModule;

    SimpleLiquibaseExecutor simpleLiquibaseExecutor;

    ExtendableCoercer coercer;

    QueryFactory queryFactory;

    /**
     * System property selecting the default {@link #queryModelMode} (same name as the guice testkit and esm-itest fixtures').
     */
    public static final String QUERY_MODEL_MODE_PROPERTY = "judo.queryModel.mode";

    /**
     * Query model mode of the runtime: {@code build} (default: the query factory is built from the live models, the
     * {@code <name>-query.model} file is never opened), {@code load} (strict: the query factory is restored from the
     * generated {@code <name>-query.model}, or {@link #prepare(String, JudoDatasourceFixture)} fails with no fallback build)
     * or {@code auto}. Set it before {@code prepare(...)}.
     */
    @Getter
    @Setter
    private String queryModelMode = System.getProperty(QUERY_MODEL_MODE_PROPERTY, "build");

    /**
     * The query factory goes through {@link QueryModelLoadOrBuild}, the same decision the runtime takes at boot, with the
     * digests of the model files as loaded: in {@code build} it is built from the live models (JQL extraction with
     * {@code resolveOnlyCurrentLambdaScope=false}), in strict {@code load} it is restored from the generated query file.
     * See change: persisted-query-model.
     */
    private void initQueryFactory() {

        coercer = new DefaultCoercer();

        final java.util.function.Supplier<QueryFactory> build = () -> {
            JqlExpressionBuilderConfig jqlExpressionBuilderConfig = new JqlExpressionBuilderConfig();
            jqlExpressionBuilderConfig.setResolveOnlyCurrentLambdaScope(false);

            final AsmJqlExtractor asmJqlExtractor = new AsmJqlExtractor(modelHolder.getAsmModel().getResourceSet(),
                    modelHolder.getMeasureModel().getResourceSet(), URI.createURI("expr:" + modelHolder.getAsmModel().getName()), jqlExpressionBuilderConfig);

            // own map per runtime: custom joins disable the restore of the query model
            return new QueryFactory(
                    modelHolder.getAsmModel().getResourceSet(),
                    modelHolder.getMeasureModel().getResourceSet(),
                    asmJqlExtractor.extractExpressions(),
                    coercer,
                    customJoinDefinitions);
        };

        queryFactory = QueryModelLoadOrBuild.queryModelLoadOrBuild()
                .asmModel(modelHolder.getAsmModel())
                .asmDigest(modelHolder.getAsmDigest())
                .measureModel(modelHolder.getMeasureModel())
                .measureDigest(modelHolder.getMeasureDigest())
                .expressionModel(modelHolder.getExpressionModel())
                .expressionDigest(modelHolder.getExpressionDigest())
                .persistedQueryModel(modelHolder.getPersistedQueryModel())
                .customJoinDefinitions(customJoinDefinitions)
                .mode(queryModelMode)
                .buildQueryFactory(build)
                .build()
                .get();
    }

    // One map per runtime, passed to the load-or-build decision and to the built QueryFactory. A join put into
    // getQueryFactory().getCustomJoinDefinitions() after construction has no effect on that factory, but it would make
    // a later strict-load decision of the same runtime fail (custom joins disable restore), as in the esm-itest fixture.
    private final Map<EReference, CustomJoinDefinition> customJoinDefinitions = new ConcurrentHashMap<>();

    private void initModules(JudoDatasourceFixture datasource, Dialect dialect) {
        RdbmsInit init = null;
        simpleLiquibaseExecutor = new SimpleLiquibaseExecutor();
        if (dialect instanceof HsqldbDialect) {
            init = HsqldbRdbmsInit.builder()
                    .liquibaseExecutor(simpleLiquibaseExecutor)
                    .liquibaseModel(modelHolder.getLiquibaseModel())
                    .build();
            databaseModule = JudoHsqldbModule
                    .builder().build();
        }
        if (dialect instanceof PostgresqlDialect) {
            init = PostgresqlRdbmsInit.builder()
                    .liquibaseExecutor(simpleLiquibaseExecutor)
                    .liquibaseModel(modelHolder.getLiquibaseModel())
                    .build();
            databaseModule = JudoPostgresqlModule
                    .builder()
                    .dataSource(datasource.getDataSource())
                    .build();
        }
        init.execute(datasource.getDataSource());

        judoDefaultModuleBuilder = JudoDefaultModule.builder()
                .judoModelLoader(modelHolder);

    }

    public void prepare(String modelName, JudoDatasourceFixture datasource) throws Exception {

        if (DIALECT_POSTGRESQL.equals(datasource.getDialect())) {
            dialect = new PostgresqlDialect();
        } else if (DIALECT_HSQLDB.equals(datasource.getDialect())) {
            dialect = new HsqldbDialect();
        } else {
            throw new IllegalArgumentException("Unsupported dialect: " + datasource.getDialect());
        }
        modelHolder = JudoModelLoader.loadFromDirectory(modelName, new File(MODEL_SOURCES), dialect, true, false);
        initQueryFactory();
        initModules(datasource, dialect);
    }

    public void init(Module module, Object injectModulesTo) {
        judoDefaultModuleBuilder = judoDefaultModuleBuilder
                .injectModulesTo(injectModulesTo)
                .judoModelLoader(modelHolder)
                .extendableCoercer(coercer)
                .queryFactory(queryFactory);

        Module modules = Modules.combine(
                module,
                judoDefaultModuleBuilder.build(),
                databaseModule
        );
        injector = Guice.createInjector(modules);
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
