# AGENTS.md — `judo-runtime-core-jsl-itest/models/ContainsTestModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for JSL collection `contains` expressions on entities.

| File | Purpose |
| --- | --- |
| `ContainsTest.java` | JUnit 5 entity integration test bootstrapping runtime with `JudoRuntimeExtension("ContainsModel", new ContainsModelDaoModules())`. Tests `contains` expressions on single relation references and collection relations between `A` and `B`. |
