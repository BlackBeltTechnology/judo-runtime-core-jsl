# AGENTS.md — `judo-runtime-core-jsl-spring-itest/src/main/resources/model`

JSL domain model definitions used in Spring integration test scenarios.

| File | Purpose |
| --- | --- |
| `SalesModel.jsl` | Defines `SalesModel` domain DSL with custom scalar types, `Person` abstract entity, `SalesPerson` and `Customer` entities, `Lead` relations, and derived queries (`leadsOver`, `rootAllLeads`, `totalNumberOfLeads`). Imports `SalesModelContract`. |
| `SalesModelContract.jsl` | Defines auxiliary `SalesModelContract` domain model declaring empty `Contract` entity referenced across cross-model relationship tests. |
