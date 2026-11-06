# AGENTS.md — `judo-runtime-core-jsl-itest/models/SalesModels/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests evaluating operations, cross-model imports, and static collection navigation across sales entities.

| File | Purpose |
| --- | --- |
| `SalesModelTest.java` | Tests entity lifecycle and domain logic on `SalesPerson`, `Lead`, `Contract`, `ContractDetail`, and `ContractsAggregator`. Exercises operations (`leadsOver`), cross-model imported entities, static collection navigation, and error inheritance (`MyExtendedError`) via `JudoRuntimeExtension("SalesModel")`. |
