# AGENTS.md — `judo-runtime-core-jsl-itest/models/TransferRangeModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for transfer range specifications and choices.

| File | Purpose |
| --- | --- |
| `TransferRangeTest.java` | Tests transfer range specs for single and collection association/aggregation/transient relations with default, derived, self-related ranges. Boots `TransferRangeDaoModules` via `JudoRuntimeExtension`; `hasMethodWithName` proves default-range DAOs expose no `getRangeOf`; `createItemListUpTo`/`getSelectedItems` over `List<ItemTransfer>` check `__selected` markers on `ItemTransfer::toMap`. |
