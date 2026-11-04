# AGENTS.md — `judo-runtime-core-jsl-itest/models/SampleModels/src/test/resources`

Sample JSL models testing error hierarchies, parameterized navigation, and cross-model imports.

| File | Purpose |
| --- | --- |
| `sample.jsl` | Defines model `SalesModel3` specifying `Person`, `SalesPerson`, `Lead`, and `Customer` entities. Declares error inheritance (`MyExtendedError` extending `MyError`), opposite relations, default values, and parameterized relation navigation (`leadsOver`). |
| `sample2.jsl` | Defines model `SalesModel2` importing `SalesModel3`. Declares abstract entity `Person` referencing qualified type `SalesModel3::String` for cross-model type resolution tests. |
