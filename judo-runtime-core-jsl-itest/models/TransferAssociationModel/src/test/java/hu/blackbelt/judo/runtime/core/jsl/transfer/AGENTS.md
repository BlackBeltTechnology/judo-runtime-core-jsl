# AGENTS.md — `judo-runtime-core-jsl-itest/models/TransferAssociationModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for mapped transfer association-association and composition-association relations.

| File | Purpose |
| --- | --- |
| `MappedTransferAssociationAssociationTest.java` | Tests mapped association-association transfers via `JudoRuntimeExtension` + `MappedTransferAssociationAssociationDaoModules`, injecting `EntityADao`/`TransferADao` pairs. Runs optional/required/collection and two-way cases with `queryRelationBonA`, `setRelationBonA`, `unsetRelationBonA`; transfer/entity DAO reads mirror. Each `@Test` pairs `@TestCase` with a `@Requirement(reqs = {...})` tag. |
| `MappedTransferCompositonAssociationTest.java` | Tests transfer composition-association mappings via JUnit 5 and Guice. Validates single, single required, and multi composition-association relations using `MappedBankAccount` and `MappedCompany`. |
