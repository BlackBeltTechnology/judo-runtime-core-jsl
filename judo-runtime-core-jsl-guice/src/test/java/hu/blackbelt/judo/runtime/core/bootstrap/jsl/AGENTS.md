# AGENTS.md — `judo-runtime-core-jsl-guice/src/test/java/hu/blackbelt/judo/runtime/core/bootstrap/jsl`

Unit tests verifying Guice-based JSL model transformation and loading workflows.

| File | Purpose |
| --- | --- |
| `JSLTransformationModelLoaderTest.java` | Verifies `JSLTransformationModelLoader.loadAndTransformJslModels()` on `sample.jsl` and `sample2.jsl` for HSQLDB dialect, writing transformed models to target test output via `saveModels()`. |
