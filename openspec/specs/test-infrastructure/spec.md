# Test Infrastructure Specification

## Purpose

The `judo-runtime-core-jsl-itest/common` module provides reusable JUnit 5 test fixtures for integration testing of JSL models against real databases. It manages the full lifecycle of database setup, model loading, Guice injection, and per-test transaction isolation.

## Architecture

The test infrastructure consists of three main classes working together:

- **`JudoRuntimeExtension`** — JUnit 5 extension that orchestrates the full test lifecycle; implements `BeforeAllCallback`, `AfterAllCallback`, `BeforeEachCallback`, `AfterEachCallback`, and `ParameterResolver`
- **`JudoDatasourceFixture`** — manages database lifecycle: starts TestContainers or HSQLDB, creates `DataSource` and `PlatformTransactionManager`, handles table truncation
- **`JudoRuntimeFixture`** — manages the JUDO runtime: loads JSL models, creates Guice injector, handles transactions, injects DAOs into test instances

Alternative extension variants:
- **`JudoDatasourceSingletonExtension`** — singleton datasource shared across all test classes
- **`JudoDatasourceByClassExtension`** — per-class datasource lifecycle
- **`JudoRuntimeByClassExtension`** — per-class runtime lifecycle
- **`YugabytedbSQLContainer`** — TestContainers implementation for YugabyteDB

## Requirements

### Requirement: Database lifecycle management

The datasource fixture SHALL set up and tear down databases based on the configured dialect.

#### Scenario: HSQLDB in-memory database
- **GIVEN** the system property `dialect` is set to `hsqldb`
- **WHEN** `setupDatabase()` is called
- **THEN** an in-memory HSQLDB datasource is created without requiring Docker

#### Scenario: PostgreSQL via TestContainers
- **GIVEN** the system property `dialect` is set to `postgresql`
- **WHEN** `setupDatabase()` is called
- **THEN** a PostgreSQL container is started via TestContainers and a HikariCP connection pool is created

#### Scenario: Datasource teardown
- **WHEN** `teardownDatasource()` is called
- **THEN** the datasource is closed and any running container is stopped

### Requirement: Per-test transaction isolation

Each test method SHALL execute within its own transaction that is committed and followed by table truncation.

#### Scenario: Test transaction lifecycle
- **GIVEN** a test class with `@RegisterExtension JudoRuntimeExtension`
- **WHEN** a test method executes
- **THEN** a transaction is begun before the test, committed after the test, and all tables are truncated

### Requirement: Guice DAO injection

The runtime fixture SHALL inject generated DAO instances into test class fields annotated with `@Inject`.

#### Scenario: DAO injection
- **GIVEN** a test class with `@Inject SomeEntityDao someEntityDao` fields
- **WHEN** `beforeEach()` runs
- **THEN** a Guice injector is created from the test's `DaoModules` and all `@Inject` fields are populated

### Requirement: Model loading and schema generation

The runtime fixture SHALL load JSL models from generated test sources and apply database schema via Liquibase.

#### Scenario: Model preparation
- **GIVEN** generated model files exist in `target/generated-test-sources/model/`
- **WHEN** `prepare(modelName, datasourceFixture)` is called
- **THEN** the ASM model, RDBMS model, and Liquibase changeset are loaded and the database schema is applied

### Requirement: Table truncation between tests

The datasource fixture SHALL truncate all model tables between test methods to ensure isolation.

#### Scenario: PostgreSQL truncation
- **GIVEN** the dialect is PostgreSQL
- **WHEN** `truncateTables(rdbmsModel)` is called
- **THEN** `TRUNCATE TABLE <name> RESTART IDENTITY CASCADE` is executed for each table

#### Scenario: HSQLDB truncation
- **GIVEN** the dialect is HSQLDB
- **WHEN** `truncateTables(rdbmsModel)` is called
- **THEN** `TRUNCATE TABLE <name> RESTART IDENTITY AND COMMIT NO CHECK` is executed for each table

### Requirement: JudoRuntimeFixture parameter resolution

Tests SHALL be able to receive `JudoRuntimeFixture` as a test method parameter for manual transaction control.

#### Scenario: Parameter injection
- **GIVEN** a test method with parameter `JudoRuntimeFixture runtime`
- **WHEN** JUnit 5 resolves parameters
- **THEN** `JudoRuntimeExtension` provides the active `JudoRuntimeFixture` instance
