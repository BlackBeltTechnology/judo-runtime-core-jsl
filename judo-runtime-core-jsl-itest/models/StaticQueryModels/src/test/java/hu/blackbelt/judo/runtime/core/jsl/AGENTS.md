# AGENTS.md — `judo-runtime-core-jsl-itest/models/StaticQueryModels/src/test/java/hu/blackbelt/judo/runtime/core/jsl`

| File | Purpose |
| --- | --- |
| `TestStaticQueriesWithConstantParameters.java` | Verifies static queries declaring constant parameters via `TestStaticQueriesWithConstantParametersDaoModules` and `JudoRuntimeExtension`. Injects DAOs `MyEntityDao`, `Snapshot1Dao`, `Snapshot2Dao`, `LastAddedMyEntityDao` to assert literal matching and snapshot queries. |
| `TestStaticQueriesWithDefaultValues.java` | Verifies static queries using default parameter values via `TestStaticQueriesWithDefaultValuesDaoModules` and `JudoRuntimeExtension`. Tests queries with omitted vs explicit values for primitives and temporal types using `MyEntityDao` and `Snapshot1Dao`. |
| `TestStaticQueryDao.java` | Integration tests for generated static query DAOs across primitive, temporal, and entity types with parameter permutations (without params, with params, with default params). Boots runtime with `StaticQueryDaoTestDaoModules` and tests DAOs including `BooleanWithParamQueryDao`, `DateWithParamQueryDao`, `StringWithParamQueryDao`. |
