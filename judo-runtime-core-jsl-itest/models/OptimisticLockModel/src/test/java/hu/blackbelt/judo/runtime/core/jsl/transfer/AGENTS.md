# AGENTS.md — `judo-runtime-core-jsl-itest/models/OptimisticLockModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests verifying optimistic locking and timestamp tracking on mapped transfer objects.

| File | Purpose |
| --- | --- |
| `MappedTransferOptimisticLockTest.java` | Tests optimistic locking, version increments, and timestamp behavior on transfer objects mapped to entities. Injects `TransferPersonDao` and `TransferStudentDao` using `JudoRuntimeExtension`. |
