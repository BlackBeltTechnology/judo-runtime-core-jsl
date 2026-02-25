# Contributing to JUDO Runtime Core JSL

Thank you for your interest in contributing! This guide covers everything you need to get started.

## Development Environment

### Prerequisites

| Requirement | Version | Notes |
|------------|---------|-------|
| Java JDK | 21 | Zulu distribution recommended |
| Maven | 3.9.4+ | Wrapper (`./mvnw`) included |
| Docker | Latest | Required for PostgreSQL integration tests via TestContainers |

For the full environment requirements, see the parent project's [CONTRIBUTING guide](https://github.com/BlackBeltTechnology/judo-community/blob/develop/CONTRIBUTING.adoc).

## Project Architecture

This is a multi-module Maven project with four top-level modules. Understanding how they relate helps you decide where to make changes:

```mermaid
graph TD
    PARENT["judo-runtime-core-jsl-parent<br/>(reactor POM)"]

    API["judo-runtime-core-jsl<br/>Core API bundle"]
    GUICE["judo-runtime-core-jsl-guice<br/>Guice integration"]
    ITEST["judo-runtime-core-jsl-itest<br/>Integration test parent"]
    SPRING["judo-runtime-core-jsl-spring-itest<br/>Spring Boot tests"]

    COMMON["itest/common<br/>Test fixtures"]
    MODELS["itest/models/<br/>36 test modules"]

    PARENT --> API
    PARENT --> GUICE
    PARENT --> ITEST
    PARENT --> SPRING

    ITEST --> COMMON
    ITEST --> MODELS

    MODELS -.->|uses| COMMON
    GUICE -->|depends on| API
    SPRING -->|depends on| API

    style PARENT fill:#f5f5f5,stroke:#333
    style API fill:#e1f5fe,stroke:#0288d1
    style GUICE fill:#e1f5fe,stroke:#0288d1
    style COMMON fill:#fff3e0,stroke:#f57c00
    style MODELS fill:#fff3e0,stroke:#f57c00
    style SPRING fill:#e8f5e9,stroke:#388e3c
```

### Build Lifecycle

The build involves a model transformation pipeline that runs during the `generate-test-sources` phase. Understanding this pipeline is essential when adding new test models:

```mermaid
flowchart LR
    subgraph "Maven Build Phases"
        CLEAN["clean"] --> VALIDATE["validate"]
        VALIDATE --> COMPILE["compile"]
        COMPILE --> GEN["generate-test-sources"]
        GEN --> TEST_COMPILE["test-compile"]
        TEST_COMPILE --> TEST["test"]
        TEST --> PACKAGE["package"]
        PACKAGE --> INSTALL["install"]
    end

    subgraph "Code Generation (generate-test-sources)"
        JSL["JSL model<br/>(.jsl file)"] --> TATAMI["tatami-jsl-workflow<br/>Maven plugin"]
        TATAMI --> PSM_MODEL["PSM model"]
        PSM_MODEL --> PSM_GEN["psm-generator<br/>Maven plugin"]
        PSM_GEN --> DAO["Generated DAOs"]
        PSM_GEN --> ENTITIES["Generated entities"]
        PSM_GEN --> MODULES["Guice modules"]
    end

    GEN -.->|triggers| JSL
```

## Build Commands

```bash
# Full build with HSQLDB tests (fastest for development)
./mvnw clean install -Ddialect=hsqldb

# Full build with PostgreSQL tests (requires Docker)
./mvnw clean install -Ddialect=postgresql

# Build without tests
./mvnw clean install -DskipTests

# Run tests only (after a prior build)
./mvnw surefire:test -Ddialect=hsqldb

# Run a single test module
./mvnw test -pl judo-runtime-core-jsl-itest/models/PrimitivesModel -Ddialect=hsqldb

# Run a single test class
./mvnw test -pl judo-runtime-core-jsl-itest/models/PrimitivesModel \
  -Dtest=PrimitivesTest -Ddialect=hsqldb

# Parallel build (CI uses 4 threads)
./mvnw clean install -T 4 -Ddialect=hsqldb

# Skip itest modules (build core only)
./mvnw clean install -DskipModules=true
```

## Test Architecture

Each integration test module follows a consistent pattern. Here is the interaction flow during test execution:

```mermaid
sequenceDiagram
    participant JUnit as JUnit 5
    participant Ext as JudoRuntimeExtension
    participant DS as JudoDatasourceFixture
    participant RT as JudoRuntimeFixture
    participant Guice as Guice Injector
    participant Test as Test Class

    Note over JUnit,Test: beforeAll
    JUnit->>Ext: beforeAll()
    Ext->>DS: setupDatabase()
    DS-->>DS: Start TestContainer or HSQLDB
    Ext->>DS: prepareDatasources()
    Ext->>RT: prepare(modelName, datasource)
    RT-->>RT: Load JSL model, run Liquibase

    Note over JUnit,Test: beforeEach (per test method)
    JUnit->>Ext: beforeEach()
    Ext->>RT: init(module, testInstance)
    RT->>Guice: createInjector()
    Guice->>Test: @Inject DAOs
    Ext->>RT: beginTransaction()

    Note over JUnit,Test: test execution
    Test->>Test: Use injected DAOs

    Note over JUnit,Test: afterEach
    JUnit->>Ext: afterEach()
    Ext->>RT: commitTransaction()
    Ext->>DS: truncateTables()

    Note over JUnit,Test: afterAll
    JUnit->>Ext: afterAll()
    Ext->>DS: teardownDatasource()
```

### Adding a New Test Model

1. Create a new directory under `judo-runtime-core-jsl-itest/models/YourModel/`
2. Add a `pom.xml` modeled after an existing test module (e.g., `PrimitivesModel/pom.xml`)
3. Write your JSL model file in `src/test/resources/YourModel.jsl`
4. Create test classes in `src/test/java/` using `@RegisterExtension` with `JudoRuntimeExtension`
5. Register the module in `judo-runtime-core-jsl-itest/pom.xml`

## Submitting Issues

Before submitting, search the [issue tracker](https://github.com/BlackBeltTechnology/judo-runtime-core-jsl/issues) for existing reports.

When filing a bug, include:
- Output of `java -version` and `mvn -version`
- The `pom.xml` or `.flattened-pom.xml` (when applicable)
- A minimal reproducing use-case

File new issues via the [issue form](https://github.com/BlackBeltTechnology/judo-runtime-core-jsl/issues/new/choose).

## Submitting a Pull Request

This project follows [GitHub's standard forking model](https://guides.github.com/activities/forking/). Fork the project, create a feature branch, and submit a pull request.

> **Important:** Every commit must reference a JIRA ticket number (`JNG-xxx`). There is no commit without a ticket number.
