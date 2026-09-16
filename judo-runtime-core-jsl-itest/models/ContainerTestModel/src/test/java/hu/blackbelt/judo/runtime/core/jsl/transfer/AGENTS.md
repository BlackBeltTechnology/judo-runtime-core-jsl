# AGENTS.md — `judo-runtime-core-jsl-itest/models/ContainerTestModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for JSL container navigations on mapped transfer objects.

| File | Purpose |
| --- | --- |
| `MappedTransferContainerTest.java` | JUnit 5 transfer integration test using `JudoRuntimeExtension("ContainerTest", new ContainerTestDaoModules())`. Tests `container()` navigation and range-filtered container queries across mapped transfer representations `TA`, `TB`, `TC`, `TD`, and `TAdditionalService`. |
