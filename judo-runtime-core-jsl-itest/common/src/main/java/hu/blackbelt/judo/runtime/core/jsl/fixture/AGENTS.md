# AGENTS.md — `judo-runtime-core-jsl-itest/common/src/main/java/hu/blackbelt/judo/runtime/core/jsl/fixture`

JUnit 5 test extensions, testcontainer fixtures, and runtime database bootstrap wiring for JSL integration test suites.

| File | Purpose |
| --- | --- |
| `JudoDatasourceByClassExtension.java` | JUnit 5 extension managing lifecycle of fresh `JudoDatasourceFixture` per test class. Implements `BeforeAllCallback`, `AfterAllCallback`, `AfterEachCallback`, `ParameterResolver`; resolves `JudoDatasourceFixture` parameter and commits mandatory transaction after each test. |
| `JudoDatasourceFixture.java` | Configures and boots JDBC datasources and containers across HSQLDB, PostgreSQL, and YugabyteDB dialects. Exports `setupDatabase()`, `prepareDatasources()`, `teardownDatasource()`, `truncateTables(RdbmsModel)`, `runInTransaction(Supplier)`, `assertThrowsInTransaction(Class, Executable)`. |
| `JudoDatasourceSingletonExtension.java` | JUnit 5 extension reusing static singleton `JudoDatasourceFixture` across test classes without teardown. Implements `BeforeAllCallback`, `AfterAllCallback`, `AfterEachCallback`, `ParameterResolver`; resolves singleton fixture and commits pending transaction after each test. |
| `JudoRuntimeByClassExtension.java` | JUnit 5 extension managing class-scoped `JudoRuntimeFixture` lifecycle and test method logging. Implements `ParameterResolver`, `BeforeAllCallback`, `BeforeEachCallback`, `AfterEachCallback`; resolves `JudoRuntimeFixture` parameter for injected test classes. |
| `JudoRuntimeExtension.java` | JUnit 5 extension combining `JudoDatasourceFixture` and `JudoRuntimeFixture` with Guice module injection. Implements `BeforeAllCallback`, `AfterAllCallback`, `BeforeEachCallback`, `AfterEachCallback`, `ParameterResolver`; starts transaction before each test and truncates tables after each test. |
| `JudoRuntimeFixture.java` | Bootstraps Guice injector, EMF model loader, Liquibase schema migrations, and transaction demarcations for JSL runtime testing. Exports `prepare(String, JudoDatasourceFixture)`, `init(Module, Object)`, `beginTransaction()`, `commitTransaction()`, `rollbackTransaction()`, `createSavePoint()`. |
| `YugabytedbSQLContainer.java` | Testcontainers JDBC container managing `yugabytedb/yugabyte` image lifecycle. Extends `JdbcDatabaseContainer`; exposes port 5433, waits for log message `yugabyted started successfully`, and configures PostgreSQL-compatible environment credentials. |
| `logback.xml` | Logback logging configuration for integration test fixtures. Configures `CONSOLE` appender with timestamp/thread/level formatting and sets root logging level to `error`. |
