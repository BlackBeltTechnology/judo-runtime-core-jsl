# AGENTS.md — `judo-runtime-core-jsl-itest/models/TransferAggregationModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for mapped transfer association and composition aggregation semantics.

| File | Purpose |
| --- | --- |
| `MappedTransferAssociationAggregationTest.java` | JUnit 5 itest of mapped association aggregation on injected `EntityADao`/`TransferADao` DAO pairs: single/required/multi, two-way optional/required, navigation. `testImplicitSingleRequiredAssociationAggregationCreate` — create without a required aggregate throws `ValidationException` `MISSING_REQUIRED_RELATION` at `relationDonC`; `testNavigationInRelationOnTransfer` asserts identity survives. |
| `MappedTransferCompositonAggregationTest.java` | JUnit 5 itest of mapped composition aggregation: `removeIdFromMap` strips id/`ENTITY_TYPE_KEY`/`VERSION` before `TransferBForCreate` rebuilds. Contract: `testCompositionWithRemovedIdsOnCreate` — assigning an existing DAO row into a composition throws `IllegalStateException` "Existing reference element cannot be set as a composition"; `testAddMethodOnBuilder` covers duplicate adds. |
