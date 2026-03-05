# JUDO Runtime Core JSL

[![Build](https://github.com/BlackBeltTechnology/judo-runtime-core-jsl/actions/workflows/build.yml/badge.svg?branch=develop)](https://github.com/BlackBeltTechnology/judo-runtime-core-jsl/actions/workflows/build.yml)

## What This Project Does

This module extends [judo-runtime-core](https://github.com/BlackBeltTechnology/judo-runtime-core) by adding support for **JSL (JUDO Specific Language)** input sources. JSL is a domain-specific language for defining entities, transfer objects, relationships, and business logic. This project handles the full pipeline from parsing JSL models through to generated runtime code — including DAO classes, SDK types, and database schema — for both Guice and Spring dependency injection frameworks.

It is a building block of the [judo-community](https://github.com/BlackBeltTechnology/judo-community) aggregator project.

## Module Overview

```mermaid
graph TD
    subgraph "Core"
        API["judo-runtime-core-jsl<br/>(API bundle)"]
        GUICE["judo-runtime-core-jsl-guice<br/>(Guice integration)"]
    end

    subgraph "Integration Tests"
        ITEST_COMMON["itest/common<br/>(test fixtures)"]
        ITEST_MODELS["itest/models/*<br/>(36 test model modules)"]
        SPRING_ITEST["spring-itest<br/>(Spring Boot tests)"]
    end

    GUICE --> API
    ITEST_COMMON --> GUICE
    ITEST_MODELS --> ITEST_COMMON
    SPRING_ITEST --> API
```

| Module | Purpose |
|--------|---------|
| `judo-runtime-core-jsl` | Core API definitions (OSGi bundle) |
| `judo-runtime-core-jsl-guice` | Guice DI integration — contains `JSLTransformationModelLoader` for loading and transforming JSL models |
| `judo-runtime-core-jsl-itest/common` | Shared test infrastructure: `JudoRuntimeExtension`, datasource fixtures, TestContainers setup |
| `judo-runtime-core-jsl-itest/models/*` | 36 integration test modules, each with a JSL model definition and JUnit 5 tests |
| `judo-runtime-core-jsl-spring-itest` | Spring Boot integration tests demonstrating DAO usage and transaction management |

## Model Transformation Pipeline

The core value of this project is the end-to-end pipeline that takes a JSL source file and produces a running, testable application with generated DAOs and database schema:

```mermaid
flowchart LR
    JSL["JSL Source<br/>(.jsl file)"] --> PARSE["JslParser"]
    PARSE --> MODEL["JslDslModel"]
    MODEL --> LOADER["JSLTransformationModelLoader"]
    LOADER --> WORKFLOW["JslDefaultWorkflow<br/>(tatami)"]
    WORKFLOW --> PSM["PSM<br/>(Platform Specific Model)"]
    PSM --> CODEGEN["Code Generation"]
    CODEGEN --> DAO["Generated DAOs"]
    CODEGEN --> SDK["SDK Types"]
    CODEGEN --> SCHEMA["DB Schema<br/>(Liquibase)"]
```

## Quick Start

```bash
# Full build and test with HSQLDB (in-memory)
./mvnw clean install -Ddialect=hsqldb

# Test with PostgreSQL (requires Docker for TestContainers)
./mvnw clean install -Ddialect=postgresql

# Run a single test module
./mvnw test -pl judo-runtime-core-jsl-itest/models/PrimitivesModel -Ddialect=hsqldb

# Run a single test class
./mvnw test -pl judo-runtime-core-jsl-itest/models/PrimitivesModel \
  -Dtest=PrimitivesTest -Ddialect=hsqldb
```

## Key Dependencies

```mermaid
graph LR
    subgraph "External"
        EMF["Eclipse EMF"]
        OSGi["OSGi Framework"]
        Guice["Google Guice 6.0"]
        Spring["Spring Boot 3.5"]
        TC["TestContainers 1.21"]
    end

    subgraph "JUDO Ecosystem"
        TATAMI["judo-tatami-jsl<br/>(JSL→PSM workflow)"]
        CORE["judo-runtime-core<br/>(runtime engine)"]
        PSM_GEN["judo-psm-generator<br/>(SDK codegen)"]
        META["judo-meta-psm<br/>(PSM metamodel)"]
    end

    subgraph "This Project"
        JSL_GUICE["jsl-guice"]
        JSL_ITEST["jsl-itest"]
    end

    JSL_GUICE --> TATAMI
    JSL_GUICE --> CORE
    JSL_ITEST --> TC
    JSL_ITEST --> Guice
    JSL_GUICE --> OSGi
    TATAMI --> META
    CORE --> EMF
```

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for development setup, submission guidelines, and coding conventions.

## License

This project is licensed under the [Eclipse Public License - v 2.0](https://www.eclipse.org/legal/epl-2.0/).
