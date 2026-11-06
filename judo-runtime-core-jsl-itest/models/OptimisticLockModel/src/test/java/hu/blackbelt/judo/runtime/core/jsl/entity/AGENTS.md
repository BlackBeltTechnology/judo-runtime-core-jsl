# AGENTS.md — `judo-runtime-core-jsl-itest/models/OptimisticLockModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests verifying optimistic locking and timestamp tracking on entity DAOs.

| File | Purpose |
| --- | --- |
| `OptimisticLockTest.java` | Tests entity-level optimistic locking, version increments, and timestamp management (`createdTimestamp`, `updateTimestamp`). Injects `PersonDao` and `StudentDao` via `JudoRuntimeExtension` with `OptimisticLockDaoModules`. |
