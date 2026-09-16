# AGENTS.md — `judo-runtime-core-jsl-itest/models/RelationCreateWithAttachmentTestModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/transfer`

Integration tests testing entity relation and composition creation with attached child elements.

| File | Purpose |
| --- | --- |
| `RelationCreateWithAttachmentTest.java` | Tests simultaneous relation and composition creation using `AForCreate`, `BForCreate`, and `CForCreate` via `ADao`, `BDao`, and `CDao`. Verifies that created root and attached child instances are persisted with valid identifiers and retrievable via `StringFilter`. |
