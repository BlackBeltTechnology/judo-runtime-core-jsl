# AGENTS.md — `judo-runtime-core-jsl-itest/models/TransferValidationModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for transfer validations including derived relations, identifiers, and transient relations.

| File | Purpose |
| --- | --- |
| `DerivedRelationValidationTest.java` | Tests derived relation behavior on transfers via JUnit 5 and Guice. Validates that derived relations on transfer objects bypass standard validation constraints without throwing exceptions. |
| `IdentifierValidationTest.java` | Tests identifier resolution and validation in two-way entity relations via JUnit 5 and Guice using `AccountEntity` and `IdentifierEntity`. |
| `TransientRelationValidationTest.java` | Tests validation constraints on transient transfer relations via JUnit 5 and Guice. Asserts `ValidationException` is thrown when required transient relation constraints are violated. |
