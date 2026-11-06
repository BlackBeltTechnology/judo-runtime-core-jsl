# AGENTS.md — `judo-runtime-core-jsl-itest/models/AllTypeOfTransferObjectsModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for creation and persistence of transfer objects with entity mappings.

| File | Purpose |
| --- | --- |
| `CreationOfTransferObjects.java` | Integration test suite using `JudoRuntimeExtension("AllTypeOfTransferObjectsModel", new AllTypeOfTransferObjectsDaoModules())` to verify instantiation and DAO persistence of transfer objects (`TransferA` through `TransferD`) with embedded entity references. |
