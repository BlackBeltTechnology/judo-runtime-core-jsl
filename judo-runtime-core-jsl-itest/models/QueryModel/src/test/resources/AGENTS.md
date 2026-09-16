# AGENTS.md — `judo-runtime-core-jsl-itest/models/QueryModel/src/test/resources`

Test resources declaring JSL model definitions with entity and root-level query declarations for query integration testing.

| File | Purpose |
| --- | --- |
| `QueryModel.jsl` | JSL model definition declaring `Lead` entity with integer `value`, `Queries` entity with derived relations, and root/scoped queries (`rootAllLeadsBetween`, `rootCountAllLeadsBetween`, `rootAllLeads`, `rootOneLead`, `totalNumberOfLeads`, `queriesAllLeadsBetween`, `queriesCountAllLeadsBetween`) filtering on `min`/`max` ranges. |
