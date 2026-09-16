# AGENTS.md — `judo-runtime-core-jsl-itest/models/InheritanceModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests verifying entity and transfer inheritance hierarchies, polymorphic queries, and attribute propagation against the Inheritance model.

| File | Purpose |
| --- | --- |
| `InheritanceTest.java` | Tests entity and transfer inheritance in the `Inheritance` model via `JudoRuntimeExtension` and `InheritanceDaoModules`. Verifies attribute inheritance across single and multi-level hierarchies (`E`, `F`, `G`, `H`, `I`), abstract parent querying via `ParentAbstractDao`, composition navigation, and transfer type reflection. |
