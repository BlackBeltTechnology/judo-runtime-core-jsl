# judo-runtime-core-jsl — module agent doctrine

## Module purpose

`judo-runtime-core-jsl` is the JSL front end of the JUDO runtime. Where
`judo-runtime-core` executes an application against already-built models and a
generic, untyped payload API, this reactor starts one step earlier — from `.jsl`
sources written in the JUDO Specific Language — and closes the last mile down to
typed Java a developer actually calls.

It contributes three things the core runtime does not have:

- **A model pipeline entry point.** `JSLTransformationModelLoader` (in
  `judo-runtime-core-jsl-guice`) drives `JslDefaultWorkflow` from
  `judo-tatami-jsl`, turning JSL source into PSM and onward into the ASM / RDBMS
  / Liquibase artefacts `judo-runtime-core` consumes. Both entry shapes exist:
  transform an already-parsed `JslDslModel`, or load from model URIs first.
- **Generated typed SDK bindings.** `judo-psm-generator-sdk-core` emits DAO
  interfaces, entity and transfer-object types, and a wiring module from the
  PSM. Generation runs in `generate-test-sources` (or the Spring generator path
  for the Spring module); the output is never committed.
- **A dialect-parameterised harness.** Every behaviour is proven against a real
  database on both dialects, so JSL language semantics — not just runtime
  plumbing — are the thing under test.

What a JSL-generated application binds to: on Guice, the generated `*DaoModules`
class installed into an injector, with typed DAO fields supplied by `@Inject`;
on Spring Boot, the equivalent generated beans supplied by `@Autowired`. Either
way the application talks to generated, model-shaped Java types, while the
persistence, dispatch, validation and access-control work underneath is done by
`judo-runtime-core`.

Reactor coordinates: root aggregator, packaging `pom`, Java 21, Maven 3.9.4 via
the bundled wrapper, version `${revision}` (currently `1.0.3-SNAPSHOT`),
EPL-2.0, upstream `BlackBeltTechnology/judo-runtime-core-jsl`.

## Reactor map

The root `pom.xml` declares its `<modules>` inside the `modules` profile, active
unless `-DskipModules=true` — which builds the parent POM alone.

| Module | Role |
|---|---|
| `judo-runtime-core-jsl` | OSGi bundle. Carries no Java sources; it publishes the `hu.blackbelt.judo.runtime.core.jsl.*` package coordinate and pins the DAO/mapper/validation API surface that JSL-generated code compiles against. |
| `judo-runtime-core-jsl-guice` | OSGi bundle. The transformation driver — `JSLTransformationModelLoader` runs the `judo-tatami-jsl` JSL→PSM workflow and hands the result to the `judo-runtime-core-guice` assembly. |
| `judo-runtime-core-jsl-itest` | Pom aggregator. Nested reactor holding the Guice-side integration suite; splits into `common` and `models`. |
| `judo-runtime-core-jsl-spring-itest` | Jar. A runnable Spring Boot application built from a JSL model under `src/main/resources/model/`, exercising the Spring generator path, the `judo-runtime-core-spring*` autoconfiguration and declarative transaction management. |

Inside `judo-runtime-core-jsl-itest`:

| Module | Role |
|---|---|
| `common` | Jar. The shared harness: `JudoRuntimeExtension` (JUnit 5 lifecycle + `ParameterResolver`), `JudoRuntimeFixture` (injector, model load, transactions), `JudoDatasourceFixture` (HSQLDB or TestContainers PostgreSQL, HikariCP pooling, inter-test truncation), the `JudoDatasourceSingletonExtension` / `JudoDatasourceByClassExtension` lifecycle variants, and `YugabytedbSQLContainer`. |
| `models` | Pom aggregator over the 34 per-feature test model modules below. |

Each `models/*` module is a jar carrying one `.jsl` model under
`src/test/resources` plus the JUnit 5 tests that assert its runtime behaviour;
the module name states the JSL feature it pins down.

| Model module | Feature under test |
|---|---|
| `AbstractModel` | Abstract entities — non-instantiable types and their concrete descendants. |
| `AllTypeOfTransferObjectsModel` | Every transfer-object flavour the language admits, mapped and unmapped. |
| `CastingFunctionsModel` | Type casting between related entity and transfer types. |
| `ContainerTestModel` | `container()` navigation from a contained instance back to its owner. |
| `ContainsTestModel` | Composition semantics — containment create/delete cascades. |
| `EntityRelationshipsModel` | Association cardinalities and both-ended relation maintenance. |
| `RelationWithDefaultsModel` | Default values attached to relations. |
| `EnvironmentAndSequenceModel` | Environment variables and sequence generators resolved at dispatch time. |
| `FilterModel` | Filter expressions on relations and queries. |
| `FunctionsModels` | The JSL built-in function library across types. |
| `InheritanceModel` | Entity inheritance, polymorphic navigation and overriding. |
| `MappedTransferPrimitivesModel` | Primitive attribute mapping between entity and mapped transfer objects. |
| `NavigationTestModel` | Multi-hop navigation expressions. |
| `OperatorsModel` | Operator precedence and evaluation across primitive types. |
| `PagingModel` | Ordering, offset and limit on collection queries. |
| `PrimitivesModel` | Every primitive type's storage, defaults and boundary behaviour. |
| `QueryModel` | Derived and parameterised entity queries. |
| `SalesModels` | A domain-shaped end-to-end scenario rather than a single feature. |
| `StaticQueryModels` | Model-level static queries with no owning instance. |
| `SpecialCasesModel` | Regression corner cases that resisted the feature-shaped modules. |
| `TaskModel` | A task-management domain scenario over the full stack. |
| `TernaryTestModel` | Ternary conditional expression evaluation. |
| `TestDefaultAndRequiredFieldsOnMappedTOModel` | Interaction of `required` and default values on mapped transfer objects. |
| `TransactionManagementModel` | Transaction boundaries, commit and rollback visibility. |
| `TransferAggregationModel` | Aggregation over transfer-object relations. |
| `TransferAssociationModel` | Associations declared between transfer objects. |
| `TransferConstructorModel` | Transfer-object constructors and their initialisation rules. |
| `RecursiveCompositionModel` | Self-referencing containment trees, entity and mapped-to sides. |
| `RelationCreateWithAttachmentTestModel` | Creating a relation and its attached instance in one call. |
| `OptimisticLockModel` | Version-based optimistic locking and conflict detection. |
| `TransferRangeModel` | `range` constraints restricting selectable related instances. |
| `FilterCountModel` | Counting over filtered collections. |
| `OperationModel` | Model-declared operations dispatched at runtime. |
| `TransferValidationModel` | Validation constraints declared on transfer objects. |

## Build commands

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

### Maven profiles

| Profile | Purpose |
|---|---|
| `modules` | Active by default; includes all 4 top-level modules. Deactivated with `-DskipModules=true` |
| `sign-artifacts` | Signs artifacts with GPG (used in CI deployments) |
| `release-dummy` | Deploys to local filesystem (for testing the release process) |
| `release-judong` | Deploys to Judong Nexus (`nexus.judo.technology`) |
| `release-central` | Deploys to Maven Central via Sonatype OSSRH |
| `generate-github-asciidoc-diagrams` | Generates PNG diagrams from AsciiDoc files |
| `update-source-code-license` | Updates EPL-2.0 license headers in source files |

### Maven properties

- `-Ddialect=hsqldb|postgresql` — selects the database dialect for tests.
- `-Drevision=<version>` — overrides the project version (CI-friendly versioning).
- `-DdeployOnly=true` — skips install, only deploys.
- `-DskipModules=true` — skips all submodules (builds parent POM only).

Surefire JVM args configured in the parent POM:

```
-Dfile.encoding=UTF-8
--add-opens java.base/java.lang=ALL-UNNAMED
--add-opens java.base/java.util=ALL-UNNAMED
--add-opens java.base/java.time=ALL-UNNAMED
```

## Development environment

Required: Java 21 JDK (Zulu distribution recommended); Maven 3.9.4+ or the
included `./mvnw` wrapper; Docker, for the PostgreSQL integration tests driven
by TestContainers.

Test logging is configured once at the reactor root and shared across all
modules through the `logback-test-config` property. The root POM is also where
every dependency version, plugin management block and profile is declared;
Dependabot configuration lives beside the CI workflow under `.github/`.

## Technology stack

Core: Java 21; Eclipse EMF (Eclipse Platform Dependencies 4.22) as the metamodel
framework; Google Guice 6.0 for the test runtime; Spring Boot 3.5.0 / Spring
Framework 6.2.7 for the Spring module; Lombok 1.18.34; OSGi packaging via
`maven-bundle-plugin` 5.1.8.

JUDO ecosystem: `judo-tatami-jsl` (JSL→PSM workflow), `judo-runtime-core` (the
runtime engine this layer sits on), `judo-psm-generator-sdk-core` (SDK code
generation from PSM), `judo-meta-psm` (PSM metamodel), `judo-requirement-report`
(requirement annotation processing).

Build and quality: Maven 3.9.4 with wrapper; Surefire 3.5.1; JUnit Jupiter
5.9.1; Hamcrest 3.0; Mockito 5.18.0; TestContainers 1.21.1; JaCoCo 0.8.12;
SonarQube; Liquibase.

Databases: HSQLDB in-memory for fast tests; PostgreSQL 42.4.1 through
TestContainers for production-grade runs; HikariCP for pooling.

## Git workflow

- Main branch `develop`; release branch `master` holds the latest released sources.
- Versioning uses the CI-friendly `${revision}` property; current `1.0.3-SNAPSHOT`.
- Branch naming: `feature/JNG-xxx_summary`, `bugfix/JNG-xxx_summary`, `release/X.Y.Z`.
- **Rule:** every commit must reference a JIRA ticket (`JNG-xxx`).
- CI runs on GitHub Actions on the `judong` runner with a 45-minute timeout and
  tests both HSQLDB and PostgreSQL; the pipeline builds, tests, deploys, tags
  and releases.

## Important notes

1. **Generated code is never committed.** All generated sources live in
   `target/generated-test-sources/` and are recreated during the
   `generate-test-sources` Maven phase. Never edit these files.
2. **JSL model files are the source of truth for test data models.** They live
   in `src/test/resources/*.jsl` within each test model module.
3. **Test classes use Guice `@Inject` for DAO injection.** `JudoRuntimeExtension`
   creates a Guice injector from the generated `*DaoModules` class and injects
   fields into the test instance before each test.
4. **Each test runs in its own transaction** that is committed, after which all
   tables are truncated, ensuring test isolation.
5. **The Spring Boot module** (`spring-itest`) uses a different code generation
   path with Spring-specific generators and `@Autowired` instead of Guice
   `@Inject`.
6. **Two-phase testing in CI:** tests run first against HSQLDB (during
   `clean install`), then against PostgreSQL (a separate `surefire:test` run).
7. **OSGi bundles:** the core API and Guice modules are packaged as OSGi bundles
   via `maven-bundle-plugin`.

## Working agreements

1. Think the problem through first; read the relevant files in the codebase
   before proposing anything.
2. Check in before any major change and let the plan be verified.
3. Give a high-level explanation of what changed at every step.
4. Keep every task and code change as simple as possible — no massive or
   complex changes, minimal blast radius. Simplicity governs.
5. Maintain a documentation file describing how the architecture works, inside
   and out.
6. Never speculate about code you have not opened. If a specific file is
   referenced, read it before answering. Investigate the relevant files before
   answering questions about the codebase; make no claim about code without
   investigating unless certain. Grounded, hallucination-free answers only.
7. Implement with TDD: write or update the tests first to define expected
   behaviour, verify they fail, then write the minimal implementation that
   makes them pass.
8. Apply DRY: extract reusable logic into separate classes, utilities or
   components; when the same pattern appears in several places, refactor it
   into a shared helper.

## Related documentation

- `README.md` — project introduction and quick start.
- `CONTRIBUTING.md` — development setup and contribution guidelines.
- `.github/CIFLOW.md` — CI/CD workflow details, branching strategy, version numbering.
- [judo-community](https://github.com/BlackBeltTechnology/judo-community) — parent ecosystem documentation.
- OpenSpec AI workflow configuration lives under `openspec/`.

<!-- dox-doctrine -->
## Documentation Update Protocol (WRITE discipline)

Per-directory `AGENTS.md` files form a tree. Each directory `AGENTS.md` is the
per-file record for the files in that directory. This module-root `AGENTS.md`
holds doctrine + architecture pointers only — never a per-file index.

**Keep the root lean.** This file loads into every agent turn — every byte costs
tokens on every turn. A verbose root file buries the rules the model must follow
(signal dilution) and measurably degrades adherence; a lean file keeps doctrine
salient. Default assumption: your update does NOT belong in the root — route it
by the table below.

**Route every doc update by kind:**

| Kind of update | Goes in |
|---|---|
| New file in a directory, or its per-file detail / change history | Nearest directory `AGENTS.md`. Add a `` | `<basename>` | <purpose> | `` row, path-alphabetical. |
| Data flow, protocol, architecture rationale | `docs/architecture.md` or a `docs/<topic>.md` |
| End-user / developer setup | `README.md` |
| Cross-cutting rule every agent needs every turn (rare) | this module-root `AGENTS.md` |

**Read before editing (chain walk).** Before editing a file, read the nearest
`AGENTS.md` chain root→leaf so you know the file's recorded purpose, contracts,
and change history. Do not edit blind.

**Update after editing (closeout pass).** After changing a file, update its row
in the nearest directory `AGENTS.md`: find the file's row, update its purpose in
place; if absent, add it in path-alphabetical order. New directory → scaffold
its `AGENTS.md`. One row per file. The purpose carries a one-line summary, key
exported symbols, contracts/invariants, and `See change: <id>` history.

**Row style (caveman).** Short declarative fragments. Drop articles. Subject →
verb → object, present tense. One fact per row. Prefer concrete tokens (paths,
symbols, env vars) over prose. Keep identifiers verbatim.

**Size rule — split an over-large directory `AGENTS.md` file-based.** pi
auto-injects a directory `AGENTS.md` on every turn when cwd sits at/below it, so
an over-large directory `AGENTS.md` is not supported. Split it file-based: a row
exceeding the length threshold promotes to a per-file `<File>.AGENTS.md`
sidecar carrying that file's full detail (including every `See change:`). The
sidecar is pull-only — its name is not `AGENTS.md`, so pi never auto-injects it
— yet it stays search-indexed (`agents` doc_type). The directory `AGENTS.md`
keeps a one-line summary plus a `→ see `<File>.AGENTS.md`` pointer. Rows within
the threshold stay verbatim (lossless).

## Finding docs (READ discipline)

`kb_*` tools are faster and cheaper than raw search — they return a one-line
purpose + key exports per file, not raw bytes. **This fires on the ACTION, not
the intent** — before you `grep`/`rg` for a symbol, `cat`/read a file to learn
what it does, or chase an import, the kb call goes first. It fires **even
mid-task when you already know the file**; knowing the file does not exempt you.
When your reflex is the left column, run the right column instead:

| You're about to… | Do this FIRST instead |
|---|---|
| `grep -rn "SymbolName" src/` — find where a fn / type / const lives | `kb_search --doc-type agents "SymbolName"` — tree indexes key exports per file |
| `grep -rn "feature\|topic" src/` — how does X work / where's X handled | `kb_search "feature topic"` |
| `cat` / read a file just to learn its purpose before editing | `kb agents <path>` — one-line purpose + exports + change history |
| chase imports / callers across files | `kb_neighbors <path\|heading>` |
| read one doc section in full | `kb_get <path> <section>` |

**Fall-through (explicit):** if the kb call returns nothing relevant, `rg` /
source read is allowed — then add the missing directory `AGENTS.md` row per the
WRITE discipline. kb does NOT replace grep; it goes first.
