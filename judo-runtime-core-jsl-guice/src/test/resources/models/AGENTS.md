# AGENTS.md — `judo-runtime-core-jsl-guice/src/test/resources/models`

Sample JSL model definitions used for testing transformation and model loader behavior.

| File | Purpose |
| --- | --- |
| `sample.jsl` | Defines `test1::SalesModel` domain model with types, errors, `LeadStatus` enum, abstract `Person`, `SalesPerson`, `Lead`, `Customer` entities, and `leadsOver` query. |
| `sample2.jsl` | Defines `test2::SalesModel2` importing `test1::SalesModel` as `SalesModel1` and declaring abstract `Person` referencing `SalesModel1::String`. |
