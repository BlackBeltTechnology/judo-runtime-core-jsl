# AGENTS.md — `judo-runtime-core-jsl-itest/models/CastingFunctionsModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for JSL polymorphic casting functions on entity hierarchies.

| File | Purpose |
| --- | --- |
| `CastingFunctionsTest.java` | JUnit 5 entity integration test bootstrapping runtime with `JudoRuntimeExtension("CastingFunctions", new CastingFunctionsDaoModules())`. Tests `kindOf`, `typeOf`, `asType`, `asCollection`, and sub-type expressions across `A`, `B`, `CA`, `CB`, and `Tester` entity instances. |
