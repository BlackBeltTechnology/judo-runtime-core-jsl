# AGENTS.md — `judo-runtime-core-jsl-itest/models/AbstractModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for abstract and concrete entity inheritance hierarchies and DAO lifecycle methods.

| File | Purpose |
| --- | --- |
| `AbstractTest.java` | Integration test suite using `JudoRuntimeExtension("AbstractModel", new AbstractModelDaoModules())` to verify DAO operations across abstract and concrete entity inheritance trees (A through Q), checking absence of create methods on abstract entities and CRUD/query behavior on concrete subclasses. |
