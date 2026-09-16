# AGENTS.md — `judo-runtime-core-jsl-itest/models/FilterModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for DAO query filters across types and operators.

| File | Purpose |
| --- | --- |
| `FiltersTest.java` | Integration test suite verifying SDK query filters (`hu.blackbelt.judo.sdk.query.*`) across primitives, numbers, strings, dates, enums, optional fields, and associations using DAOs (`FilterEntityDao`, `MyEntityWithOptionalFieldsDao`, `CarDao`, etc.) with `JudoRuntimeExtension`. |
