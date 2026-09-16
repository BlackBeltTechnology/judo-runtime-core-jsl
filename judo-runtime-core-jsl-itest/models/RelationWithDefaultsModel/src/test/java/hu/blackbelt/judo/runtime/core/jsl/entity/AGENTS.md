# AGENTS.md — `judo-runtime-core-jsl-itest/models/RelationWithDefaultsModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests checking default value assignments across optional and required relations on entities and transfer objects.

| File | Purpose |
| --- | --- |
| `EntityRelationWithDefaultsModelTest.java` | Tests entity default relation initialization and inheritance across `CollectorWithSingleOptionalDefaultRelation`, `CollectorWithSingleRequiredDefaultRelation`, and their extended subclasses. Asserts default targets are assigned during create and validates constraint violations via `ValidationResult`. |
| `TransferRelationWithDefaultsModelTest.java` | Tests default value assignment matrix across transfer object relations matching optional/required defaults on underlying entities. Verifies create behavior across sixteen combinations (e.g. `CollectorWithSingleOptionalDefaultRelationOptionalDefaultTransfer`). |
