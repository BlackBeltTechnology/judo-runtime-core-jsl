# AGENTS.md — `judo-runtime-core-jsl-itest/models/TaskModel/src/test/resources`

| File | Purpose |
| --- | --- |
| `TaskModel.jsl` | Defines `TaskModel` DSL schema testing nested aggregation expressions: `Person.allTaskLong` sums `tasks.taskTimeInDay`, while `Workplace.allTime` and `MarketPlace.allTime` evaluate sums over child entity derived sums (`persons.sum(p \| p.allTaskLong)`). |
