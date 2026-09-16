# AGENTS.md — `judo-runtime-core-jsl-itest/models/ContainerTestModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for JSL container relationship navigation on entities.

| File | Purpose |
| --- | --- |
| `ContainerTest.java` | JUnit 5 entity integration test bootstrapping runtime with `JudoRuntimeExtension("ContainerTest", new ContainerTestDaoModules())`. Asserts `container()` navigations from contained entities `B`, `C`, and `D` back to their containing parent instances. |
