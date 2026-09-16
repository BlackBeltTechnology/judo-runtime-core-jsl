# AGENTS.md — `judo-runtime-core-jsl-itest/models/RecursiveCompositionModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity/mappedto`

Integration tests evaluating mapped transfer object projections over recursive composition entity structures.

| File | Purpose |
| --- | --- |
| `RecursiveCompositionTest.java` | Verifies transfer object mappings (`TransferATO`, `TransferBTO`, `TransferXTO`, `TransferYTO`) mapped to recursively composed entities (`EntityA`, `EntityB`, `EntityX`, `EntityY`). Injects respective DAOs and tests deep DTO-to-entity mapping, CRUD projection synchronization, and cyclic reference safety through `JudoRuntimeExtension("RecursiveComposition")`. |
