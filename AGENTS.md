# JUDO Runtime Core JSL - Project Documentation

## Project Overview


**Repository:** BlackBeltTechnology/judo-runtime-core-jsl
**License:** Eclipse Public License 2.0 (EPL-2.0)
**Java Version:** 21
**Build System:** Maven 3.9.4 with Maven Wrapper (`./mvnw`)

1. Extends [judo-runtime-core](https://github.com/BlackBeltTechnology/judo-runtime-core) by adding support for JSL (JUDO Specific Language) input sources
2. Provides a model transformation pipeline: JSL source → PSM (Platform Specific Model) → generated DAOs, SDK types, and database schema
3. Supports both Guice and Spring Boot dependency injection frameworks
4. Includes 36 integration test modules covering all JSL language features (primitives, relationships, queries, inheritance, transactions, etc.)
5. Tests run against both HSQLDB (in-memory) and PostgreSQL (via TestContainers)

## Code Instructions

1. First think through the problem, read the codebase for relevant files.
2. Before you make any major changes, check in with me and I will verify the plan.
3. Please every step of the way just give me a high level explanation of what changes you made.
4. Make every task and code change you do as simple as possible. We want to avoid making any massive or complex changes. Every change should impact as little code as possible. Everything is about simplicity.
5. Maintain a documentation file that describes how the architecture of the app works inside and out.
6. Never speculate about code you have not opened. If the user references a specific file, you MUST read the file before answering. Make sure to investigate and read relevant files BEFORE answering questions about the codebase. Never make any claims about code before investigating unless you are certain of the correct answer - give grounded and hallucination-free answers.
7. For implementation use TDD (Test-Driven Development): write or update tests first to define the expected behaviour, verify they fail, then write the minimal implementation to make them pass.
8. Use DRY (Don't Repeat Yourself): extract reusable logic into separate classes, utilities, or components. If the same pattern appears in multiple places, refactor it into a shared helper.

## Directory Structure

```
judo-runtime-core-jsl/
├── judo-runtime-core-jsl/              # Core API module (OSGi bundle, minimal source)
├── judo-runtime-core-jsl-guice/        # Guice DI integration (JSLTransformationModelLoader)
│   └── src/main/java/.../bootstrap/jsl/
├── judo-runtime-core-jsl-itest/        # Integration tests parent POM
│   ├── common/                         # Shared test fixtures (extensions, datasource helpers)
│   │   └── src/main/java/.../fixture/
│   └── models/                         # 36 test model submodules
│       ├── PrimitivesModel/
│       ├── EntityRelationshipsModel/
│       ├── QueryModel/
│       └── ... (36 total)
├── judo-runtime-core-jsl-spring-itest/ # Spring Boot integration tests
│   ├── src/main/java/                  # Spring Boot app
│   └── src/main/resources/model/       # JSL model for Spring tests
├── openspec/                           # OpenSpec AI workflow configuration
├── .github/workflows/                  # CI/CD (build.yml)
├── pom.xml                             # Root reactor POM
├── mvnw / mvnw.cmd                     # Maven wrapper
└── logback-test.xml                    # Test logging configuration
```

## Core Modules

### Runtime Modules

| Module | Type | Purpose |
|--------|------|---------|
| `judo-runtime-core-jsl/` | OSGi bundle | Core API definitions; exports `hu.blackbelt.judo.runtime.core.jsl.*` |
| `judo-runtime-core-jsl-guice/` | OSGi bundle | Guice integration; contains `JSLTransformationModelLoader` which orchestrates JSL→PSM transformation via `JslDefaultWorkflow` |

### Test Modules

| Module | Type | Purpose |
|--------|------|---------|
| `judo-runtime-core-jsl-itest/common/` | jar | Shared test infrastructure: `JudoRuntimeExtension`, `JudoDatasourceFixture`, `JudoRuntimeFixture`, TestContainers setup |
| `judo-runtime-core-jsl-itest/models/*/` | jar (36 modules) | Individual integration test modules, each with a `.jsl` model and JUnit 5 tests |
| `judo-runtime-core-jsl-spring-itest/` | Spring Boot jar | Spring Boot integration tests demonstrating DAO usage and transaction management |

### Key Classes

**`JSLTransformationModelLoader`** (`judo-runtime-core-jsl-guice`):
- `loadAndTransformJslModels(JslDslModel model, String dialect, File outputDirectory)` — transforms an already-loaded JSL model
- `loadAndTransformJslModels(String modelName, String dialect, File outputDirectory, URI...modelUris)` — loads from URIs and transforms

**Test Fixtures** (`judo-runtime-core-jsl-itest/common`):
- `JudoRuntimeExtension` — JUnit 5 extension implementing `BeforeAllCallback`, `AfterAllCallback`, `BeforeEachCallback`, `AfterEachCallback`, `ParameterResolver`; manages full test lifecycle
- `JudoRuntimeFixture` — manages Guice injector, model loading, and transactions; injected DAOs via `init(Module, testInstance)`
- `JudoDatasourceFixture` — manages database setup (HSQLDB in-memory or PostgreSQL via TestContainers), connection pooling (HikariCP), and table truncation between tests
- `JudoDatasourceSingletonExtension` / `JudoDatasourceByClassExtension` — alternative extension patterns for datasource lifecycle
- `YugabytedbSQLContainer` — TestContainers implementation for YugabyteDB

## Technology Stack

### Core Technologies
- **Java 21** — target language version
- **Eclipse EMF** — metamodel framework (Eclipse Platform Dependencies 4.22)
- **Google Guice 6.0** — dependency injection for test runtime
- **Spring Boot 3.5.0 / Spring Framework 6.2.7** — Spring integration module
- **Lombok 1.18.34** — boilerplate reduction (`@Slf4j`, `@Data`, etc.)
- **OSGi** — bundle packaging via `maven-bundle-plugin 5.1.8`

### JUDO Ecosystem Dependencies
- **judo-tatami-jsl** — JSL-to-PSM transformation workflow
- **judo-runtime-core** — core runtime engine
- **judo-psm-generator-sdk-core** — SDK code generation from PSM
- **judo-meta-psm** — PSM metamodel definitions
- **judo-requirement-report** — requirement annotation processing

### Build & Quality
- **Maven 3.9.4** with wrapper (`./mvnw`)
- **Maven Surefire 3.5.1** — test execution
- **JUnit Jupiter 5.9.1** — test framework
- **Hamcrest 3.0** — test matchers
- **Mockito 5.18.0** — mocking
- **TestContainers 1.21.1** — database containerization (PostgreSQL, HSQLDB)
- **JaCoCo 0.8.12** — code coverage
- **SonarQube** — code quality analysis
- **Liquibase** — database schema management

### Database Support
- **HSQLDB** — in-memory database for fast tests
- **PostgreSQL 42.4.1** — production-grade database via TestContainers
- **HikariCP** — connection pooling

## Build Commands

```bash
# Full build and test with HSQLDB (fastest for development)
./mvnw clean install -Ddialect=hsqldb

# Full build and test with PostgreSQL (requires Docker)
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

# Build core modules only (skip itest)
./mvnw clean install -DskipModules=true
```

### Maven Profiles

| Profile | Purpose |
|---------|---------|
| `modules` | Active by default; includes all 4 top-level modules. Deactivated with `-DskipModules=true` |
| `sign-artifacts` | Signs artifacts with GPG (used in CI deployments) |
| `release-dummy` | Deploys to local filesystem (for testing the release process) |
| `release-judong` | Deploys to Judong Nexus (`nexus.judo.technology`) |
| `release-central` | Deploys to Maven Central via Sonatype OSSRH |
| `generate-github-asciidoc-diagrams` | Generates PNG diagrams from AsciiDoc files |
| `update-source-code-license` | Updates EPL-2.0 license headers in source files |

## Key Configuration Files

| File | Purpose |
|------|---------|
| `pom.xml` | Root reactor POM; defines all dependency versions, plugin management, and profiles |
| `logback-test.xml` | Logging configuration for tests (shared across all modules via `logback-test-config` property) |
| `.github/workflows/build.yml` | CI/CD pipeline: build, test (HSQLDB + PostgreSQL), deploy, tag, release |
| `.github/dependabot.yml` | Automated dependency updates |
| `judo-runtime-core-jsl-itest/models/*/src/test/resources/*.jsl` | JSL model definitions for each test module |

## Development Environment

**Required:**
- Java 21 JDK (Zulu distribution recommended)
- Maven 3.9.4+ (or use included `./mvnw` wrapper)
- Docker (for PostgreSQL integration tests via TestContainers)

**Key Maven Properties:**
- `-Ddialect=hsqldb|postgresql` — selects database dialect for tests
- `-Drevision=<version>` — overrides the project version (CI-friendly versioning)
- `-DdeployOnly=true` — skips install, only deploys
- `-DskipModules=true` — skips all submodules (builds parent POM only)

**Surefire JVM Args** (configured in parent POM):
```
-Dfile.encoding=UTF-8
--add-opens java.base/java.lang=ALL-UNNAMED
--add-opens java.base/java.util=ALL-UNNAMED
--add-opens java.base/java.time=ALL-UNNAMED
```

## Git Workflow

- **Main Branch:** `develop`
- **Release Branch:** `master` (contains latest released sources)
- **Versioning:** CI-friendly `${revision}` property; current `1.0.3-SNAPSHOT`
- **Branch Naming:** `feature/JNG-xxx_summary`, `bugfix/JNG-xxx_summary`, `release/X.Y.Z`
- **Rule:** Every commit must reference a JIRA ticket (`JNG-xxx`)
- **CI:** GitHub Actions on `judong` runner; 45-minute timeout; tests both HSQLDB and PostgreSQL

## Important Notes

1. **Generated code is never committed.** All generated sources live in `target/generated-test-sources/` and are recreated during the `generate-test-sources` Maven phase. Never edit these files.
2. **JSL model files** are the source of truth for test data models. They live in `src/test/resources/*.jsl` within each test model module.
3. **Test classes use Guice `@Inject`** for DAO injection. The `JudoRuntimeExtension` creates a Guice injector from the generated `*DaoModules` class and injects fields into the test instance before each test.
4. **Each test runs in its own transaction** that is committed and then all tables are truncated after each test method, ensuring test isolation.
5. **The Spring Boot module** (`spring-itest`) uses a different code generation path with Spring-specific generators and `@Autowired` instead of Guice `@Inject`.
6. **Two-phase testing in CI:** Tests run first against HSQLDB (during `clean install`), then against PostgreSQL (separate `surefire:test` run).
7. **OSGi bundles:** The core API and Guice modules are packaged as OSGi bundles via `maven-bundle-plugin`.

## Related Documentation

- [README.md](README.md) — Project introduction and quick start
- [CONTRIBUTING.md](CONTRIBUTING.md) — Development setup and contribution guidelines
- [.github/CIFLOW.md](.github/CIFLOW.md) — CI/CD workflow details, branching strategy, and version numbering
- [judo-community](https://github.com/BlackBeltTechnology/judo-community) — Parent ecosystem documentation
