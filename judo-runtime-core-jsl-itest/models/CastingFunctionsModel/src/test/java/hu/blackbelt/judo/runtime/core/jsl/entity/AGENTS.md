# AGENTS.md — `judo-runtime-core-jsl-itest/models/CastingFunctionsModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for JSL polymorphic casting functions on entity hierarchies.

| File | Purpose |
| --- | --- |
| `CastingFunctionsTest.java` | JUnit 5 entity integration test bootstrapping runtime with `JudoRuntimeExtension("CastingFunctions", new CastingFunctionsDaoModules())`. Tests `kindOf`, `typeOf`, `asType`, `asCollection`, and sub-type expressions across `A`, `B`, `CA`, `CB`, and `Tester` entity instances. |
| `PersistedQueryModelFixtureTest.java` | TP-27 anchor. `generatedModelsIncludeTheQueryFile`: `target/generated-test-sources/model/CastingFunctions-query.model` exists, non-empty; loader located it (`modelHolder.getPersistedQueryModel()`), ASM / expression digests present. In surefire execution `query-model-load` the class boots in strict `load`. See change: persisted-query-model. |
