# AGENTS.md — `judo-runtime-core-jsl-itest/models/EntityRelationshipsModel/src/test/resources`

Test JSL models for entity association and composition relationship scenarios.

| File | Purpose |
| --- | --- |
| `AssociationRelationships.jsl` | JSL test model declaring association relations across entities (`EntityA` through `EntityF`, `A`, `B`, `C`, `Collector`, `D`, `E`, `F`, `G`); exercises single, multiple, bidirectional opposites (`opposite:twoWayMultipleAonC`), `opposite-add`, and eager path traversal expressions. |
| `CompositionRelationships.jsl` | JSL test model declaring containment fields and transfers (`Composition`, `EntityA` through `EntityO`, `TransferA`, `TransferC`, `TransferD`); exercises single/collection containment, inheritance over compositions, transfer mappings with bidirectional field bindings (`<=>`), and composition back-references. |
