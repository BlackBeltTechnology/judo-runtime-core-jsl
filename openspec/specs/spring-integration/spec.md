# Spring Integration Specification

## Purpose

The `judo-runtime-core-jsl-spring-itest` module demonstrates and tests the JUDO runtime's integration with Spring Boot. It uses Maven plugins to generate a complete Spring Boot application from JSL models, including Spring-managed DAOs, transaction management, and auto-configuration.

## Architecture

- **`JudoRuntimeCoreSpringApplication`** — `@SpringBootApplication` entry point; logs readiness via `@EventListener(ApplicationReadyEvent.class)`
- **Generated Spring Components** — DAOs generated with Spring-specific implementations (`@Autowired`-compatible) from the SalesModel JSL definition
- **Maven Plugin Pipeline** — `judo-tatami-jsl-workflow-maven-plugin` generates models, `judo-psm-generator-maven-plugin` generates Spring DAOs and SDK types

The JSL model is located in `src/main/resources/model/` and generated sources are output to `target/generated-sources/`.

## Requirements

### Requirement: Spring Boot application startup

The application SHALL start as a standard Spring Boot application with all JUDO runtime components auto-configured.

#### Scenario: Application ready
- **GIVEN** the Spring Boot application is configured with HSQLDB
- **WHEN** the application starts
- **THEN** all generated DAOs are available as Spring beans and the database schema is applied

### Requirement: DAO operations via Spring autowiring

Generated DAOs SHALL be injectable via Spring's `@Autowired` and support standard CRUD operations.

#### Scenario: Create and query entities
- **GIVEN** a running Spring Boot application with generated `PersonDao`, `LeadDao`, etc.
- **WHEN** entities are created via `dao.create()` and queried via `dao.query()`
- **THEN** entities are persisted and retrievable through the Spring-managed transaction

### Requirement: Spring transaction management

The module SHALL support both declarative (`@Transactional`) and programmatic transaction management.

#### Scenario: Declarative transaction rollback
- **GIVEN** a test method annotated with `@Transactional`
- **WHEN** the test completes
- **THEN** changes are automatically rolled back (Spring test default behavior)

#### Scenario: Programmatic transaction commit
- **GIVEN** a `PlatformTransactionManager` autowired into a test
- **WHEN** a transaction is manually begun, operations are performed, and `commit()` is called
- **THEN** changes are persisted to the database

#### Scenario: Programmatic transaction rollback
- **GIVEN** a `PlatformTransactionManager` autowired into a test
- **WHEN** a transaction is manually begun, operations are performed, and `rollback()` is called
- **THEN** changes are discarded
