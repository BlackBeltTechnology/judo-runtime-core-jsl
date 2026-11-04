# AGENTS.md — `judo-runtime-core-jsl-itest/models/MappedTransferPrimitivesModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests for mapped transfer objects covering primitive types, defaults, validation rules, sorting, and precision constraints.

| File | Purpose |
| --- | --- |
| `MappedTransferPrimitivesTest.java` | Tests CRUD operations and validation rules for mapped transfer primitives via `JudoRuntimeExtension`. Verifies required fields, unique identifiers, optional field updates, primitive defaults, constant and expression defaults, regex patterns, and string length boundaries. |
| `MappedTransferQueryCustomizerFunctionsTest.java` | Tests transfer query customization features against `TransferOptionalPrimitivesDao`. Verifies ascending/descending ordering on primitive and derived attributes, multi-attribute sorting precedence, non-existent sort property handling, and query masking. |
| `TransferPrecisionTest.java` | Tests precision and scale constraints on numeric transfer attributes. Verifies `ValidationException` thrown on precision overflow and scale overflow, and ensures valid scaled values pass validation. |
