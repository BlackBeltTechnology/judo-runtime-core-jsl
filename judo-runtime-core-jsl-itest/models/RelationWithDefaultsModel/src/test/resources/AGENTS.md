# AGENTS.md — `judo-runtime-core-jsl-itest/models/RelationWithDefaultsModel/src/test/resources`

JSL model defining test entities and transfers with combinations of optional, required, and defaulted relations.

| File | Purpose |
| --- | --- |
| `RelationWithDefaultsModel.jsl` | Defines model `RelationWithDefaultsModel` with `Item`, `CollectorWithSingleOptionalRelation`, `CollectorWithSingleRequiredRelation`, and their defaulted/extended variants. Exercises relation default expressions using `Item.all().filter(...).any()` across entity and transfer declarations. |
