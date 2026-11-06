# AGENTS.md — `judo-runtime-core-jsl-itest/models/FunctionsModels/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for built-in JSL functions on transfer objects.

| File | Purpose |
| --- | --- |
| `MappedTransferFunctionsTest.java` | Integration test suite verifying JSL built-in functions evaluated on mapped transfer objects; exercises string, numeric, boolean, date/time/timestamp, enum, Kleene, and collection expressions across transfer DAOs (`TransferAnyTypeFunctionsDao`, `TransferBooleanFunctionsDao`, etc.) with `JudoRuntimeExtension`. |
