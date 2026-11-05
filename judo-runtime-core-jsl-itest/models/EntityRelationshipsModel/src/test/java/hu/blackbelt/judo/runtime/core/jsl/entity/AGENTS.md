# AGENTS.md — `judo-runtime-core-jsl-itest/models/EntityRelationshipsModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for entity association and composition relationship semantics in JSL.

| File | Purpose |
| --- | --- |
| `AssociationRelationshipsTest.java` | JUnit 5 integration test bootstrapping runtime with `JudoRuntimeExtension("AssociationRelationships", new AssociationRelationshipsDaoModules())`. Tests association links: set/unset single relations, add/remove multiple, required constraints, traversal, opposite add, and eager loading. |
| `CompositionRelationshipsTest.java` | JUnit 5 integration test bootstrapping runtime with `JudoRuntimeExtension("CompositionRelationships", new CompositionRelationshipsDaoModules())`. Validates composition lifecycle semantics: cascade creation, delete cascades, container back-reference consistency, and containment boundaries. |
