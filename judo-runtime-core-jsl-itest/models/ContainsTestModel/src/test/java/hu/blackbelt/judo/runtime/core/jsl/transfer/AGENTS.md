# AGENTS.md — `judo-runtime-core-jsl-itest/models/ContainsTestModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for JSL collection `contains` expressions on mapped transfer objects.

| File | Purpose |
| --- | --- |
| `MappedTransferContainsTest.java` | JUnit 5 transfer integration test using `JudoRuntimeExtension("ContainsModel", new ContainsModelDaoModules())`. Validates that transfer representation `TA` correctly evaluates `contains` expressions on linked transfer objects `TB`. |
