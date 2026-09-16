# AGENTS.md — `judo-runtime-core-jsl-itest/models/PrimitivesModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for primitive type handling, defaults, validations, precision/scale, and query customizers.

| File | Purpose |
| --- | --- |
| `DefaultValueValidationTest.java` | Tests validation failure triggers when entity default values violate defined constraints (regex, scale, precision, integer ranges). Injects DAOs via `PrimitivesDaoModules`. |
| `PrecisionTest.java` | Tests precision and scale overflow validation on numeric fields using `MyEntityWithOptionalFieldsDao`. Asserts `ValidationException` with codes `PRECISION_VALIDATION_FAILED` and `SCALE_VALIDATION_FAILED`. |
| `PrimitivesTest.java` | Tests over `PrimitivesDaoModules` DAOs (`MyEntityWithOptionalFieldsDao`, `EntityRequiredFieldsDao`) asserting `ValidationException` codes `MISSING_REQUIRED_ATTRIBUTE`, `IDENTIFIER_ATTRIBUTE_UNIQUENESS_VIOLATION`, `PRECISION_VALIDATION_FAILED`, `SCALE_VALIDATION_FAILED` via `testMissingRequiredFieldsThrowExceptions`, `testIdentifierFieldsAreUnique`, `testScaleValidation`. |
| `QueryCustomizerFunctionsTest.java` | Tests query customizer features including single/multi-column ordering (ascending/descending, primitive, derived, aggregated), projection masking, and customizer copying/appending on `ItemDao`. |
