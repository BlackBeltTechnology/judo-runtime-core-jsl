# AGENTS.md — `judo-runtime-core-jsl-itest/models/RecursiveCompositionModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity/entity`

Integration tests verifying recursive composition entity relationships across self-referential and cyclic model hierarchies.

| File | Purpose |
| --- | --- |
| `RecursiveCompositionTest.java` | Tests deep and recursive entity composition lifecycles across `EntityA`, `EntityB`, `EntityX`, and `EntityY`. Injects DAOs via `RecursiveCompositionDaoModules` and registers `JudoRuntimeExtension("RecursiveComposition")`. Asserts cascading persistence, nested retrieval, and cycle containment across tree-structured parent-child relations. |
