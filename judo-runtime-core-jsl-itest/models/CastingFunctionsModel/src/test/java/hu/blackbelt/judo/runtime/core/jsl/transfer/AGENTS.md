# AGENTS.md — `judo-runtime-core-jsl-itest/models/CastingFunctionsModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for JSL polymorphic casting functions on mapped transfer object hierarchies.

| File | Purpose |
| --- | --- |
| `MappedTransferCastingFunctionsTest.java` | JUnit 5 transfer integration test using `JudoRuntimeExtension("CastingFunctions", new CastingFunctionsDaoModules())`. Validates `kindOf`, `typeOf`, `asType`, `asCollection`, and sub-type expressions on mapped transfer hierarchies `TA`, `TB`, `TCA`, `TCB`, and `TransferTester`. |
