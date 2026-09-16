# AGENTS.md — `judo-runtime-core-jsl-itest/models/FilterCountModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for count operations combined with filters and masks.

| File | Purpose |
| --- | --- |
| `FilterCountTest.java` | Integration test suite verifying DAO count and filtered query methods (`query().filterBy().count()`) across inherited attributes, masks (`AMask`), and related entities (`ADao`, `BDao`, `CDao`, `ParentDao`, `ToyDao`) via `JudoRuntimeExtension`. |
