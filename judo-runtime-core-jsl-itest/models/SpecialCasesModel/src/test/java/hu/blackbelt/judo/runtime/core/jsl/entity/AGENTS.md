# AGENTS.md — `judo-runtime-core-jsl-itest/models/SpecialCasesModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for edge cases including dialect-specific collation, reserved keyword escapes, and builder recursion.

| File | Purpose |
| --- | --- |
| `HungarianStringQueryCustomizerTest.java` | Tests Hungarian collation sorting and query customization on PostgreSQL dialect. Injects `TesterDao` via `SpecialCasesDaoModules` to persist and verify ordered retrieval of localized strings with Hungarian accents and casing rules. |
| `SpecialCasesTest.java` | Tests edge-case entity models including entities named after Java keywords (`Abstract`, `Case`, `Class_`), identical query names, recursive collection copies in builders, and serialized method invocations via `SpecialCasesDaoModules`. |
