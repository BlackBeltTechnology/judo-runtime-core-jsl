# AGENTS.md — `judo-runtime-core-jsl-guice/src/test/java/hu/blackbelt/judo/runtime/core/bootstrap/jsl`

Unit tests verifying Guice-based JSL model transformation and loading workflows. Directory is `bootstrap/jsl` but Java package is `hu.blackbelt.judo.runtime.core.guice.jsl` (pre-existing mismatch).

| File | Purpose |
| --- | --- |
| `JSLTransformationModelLoaderTest.java` | Verifies `JSLTransformationModelLoader.loadAndTransformJslModels()` on `sample.jsl` and `sample2.jsl` for HSQLDB dialect, writing transformed models to target test output via `saveModels()`. `asm2QueryStageIsOffByDefault` (TP-49): context has no `QueryModel`, has ASM / expression / hsqldb RDBMS models; saved output has no `*-query.model`, has one `*-asm.model`. See change: persisted-query-model. |
| `JSLTransformationModelLoaderEtlAbsentTest.java` | Only in surefire execution `etl-addons-absent` (all 10 add-ons excluded); excluded at plugin level so CI's bare `surefire:test` (PostgreSQL pass, default-cli) skips it. Property `ETL` → `EtlEngineMissingException` naming jsl2psm or jsl2ui add-on. See change: split-etl-artifacts. |
| `JSLTransformationModelLoaderModeTest.java` | Aggregate on test classpath; counting `Jsl2PsmEtlEngine`/`Psm2AsmEtlEngine` decorators. Unset → 0 ETL calls; `ETL` → 1 each; invalid → 0. Static `load(dir)` helper. See change: split-etl-artifacts. |
