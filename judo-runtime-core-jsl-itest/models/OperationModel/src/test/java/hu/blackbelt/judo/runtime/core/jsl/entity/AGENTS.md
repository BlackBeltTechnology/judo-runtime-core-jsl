# AGENTS.md — `judo-runtime-core-jsl-itest/models/OperationModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for generated operations and fault declarations on mapped and unmapped transfer objects.

| File | Purpose |
| --- | --- |
| `OperationModelTest.java` | Tests generated method signatures, parameter types, return types, and fault exceptions on mapped and unmapped transfer interfaces using `OperationModelDaoModules` via reflection. Verifies generated operations match model-defined signatures and thrown faults (`Fault1Exception`, `Fault2Exception`). |
