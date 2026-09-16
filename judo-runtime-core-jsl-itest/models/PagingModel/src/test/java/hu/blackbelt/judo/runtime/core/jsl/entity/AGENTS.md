# AGENTS.md — `judo-runtime-core-jsl-itest/models/PagingModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for entity-level pagination, windowing, ordering, and filtering.

| File | Purpose |
| --- | --- |
| `PagingTest.java` | Tests pagination query customizers (limit, offset, lastItem/keyset pagination, ordering, filters) across `ItemDao`, `ListDao`, and `LogEntryDao` entities via `JudoRuntimeExtension`. |
