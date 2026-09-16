# AGENTS.md — `judo-runtime-core-jsl-itest/models/EnvironmentAndSequenceModel/src/test/java/hu/blackbelt/judo/runtime/core/jsl/entity`

Integration tests for environment variable mapping and database sequence generation.

| File | Purpose |
| --- | --- |
| `EnvironmentAndSequenceTest.java` | Integration test suite verifying JSL `!getVariables("ENVIRONMENT", key)` bindings across primitive types and sequence generators; tests `EnvVars*Dao`, `SequencesDao`, and `SystemEnvironmentVariableWithExpressionDao` using `JudoRuntimeExtension` with mocked environment maps. |
