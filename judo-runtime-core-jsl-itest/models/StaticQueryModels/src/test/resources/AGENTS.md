# AGENTS.md — `judo-runtime-core-jsl-itest/models/StaticQueryModels/src/test/resources`

| File | Purpose |
| --- | --- |
| `StaticQueryDaoTest.jsl` | Defines `StaticQueryDaoTest` model exercising static query permutations across primitives, strings, decimals, dates, times, and timestamps with no parameters, required parameters, and default parameter values against `EntityWithPrimitives`. |
| `TestStaticQueriesWithConstantParameters.jsl` | Defines `TestStaticQueriesWithConstantParameters` model testing static queries invoked with constant literal parameters inside default and derived attributes of snapshot entities `Snapshot1` and `Snapshot2` over `MyEntity`. |
| `TestStaticQueriesWithDefaultValues.jsl` | Defines `TestStaticQueriesWithDefaultValues` model evaluating static queries with parameter default expressions (`anyMyEntity001`, `anyMyEntity002`, `anyMyEntity003`, `setOfMyEntities`) bound to relations on `Snapshot1`. |
