# AGENTS.md — `judo-runtime-core-jsl-itest/models/SalesModels/src/test/resources`

JSL model definitions for sales domain entities, contract attachments, queries, and errors.

| File | Purpose |
| --- | --- |
| `SalesModel.jsl` | Defines model `SalesModel` importing `SalesModelContract`. Declares `Person`, `SalesPerson`, and `Lead` entities with opposite relations, custom queries (`leadsOver`), derived fields (`fullName`, `numberOfLeads`), errors (`MyError`, `MyExtendedError`), and `ContractsAggregator`. |
| `SalesModelContract.jsl` | Defines model `SalesModelContract` declaring `Contract` with `creationDate` and child composite entity `ContractDetail`. Serves as an imported external contract model for `SalesModel`. |
