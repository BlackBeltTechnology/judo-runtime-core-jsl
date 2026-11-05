# AGENTS.md — `judo-runtime-core-jsl-itest/models/TransactionManagementModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl`

| File | Purpose |
| --- | --- |
| `TransactionManagementTest.java` | Tests programmatic transaction boundary controls via `TransactionManagementModelDaoModules` and `JudoRuntimeExtension`. `testManualTransactionManagementCommitAndRollback` exercises `commitTransaction()`, `rollbackTransaction()`, and `beginTransaction()` on `JudoRuntimeFixture` using `TesterDao`. |
