# `JSLTransformationModelLoader.java`

Runs `JslDefaultWorkflow` over one JSL model, in `TransformationMode.fromSystemProperty()` (was hardcoded ZETA); returns its `TransformationContext`. Exports heavy-lifting `loadAndTransformJslModels(JslDslModel, dialect, outputDirectory)` plus `(modelName, dialect, outputDirectory, URI...)` overload parsing sources via `JslStreamSource`.

Model name taken from `model.getName()`, not the caller. On `WorkStatus.FAILED` or any exception dumps models into outputDirectory via `DefaultWorkflowSave.saveModels`, then rethrows.

## Transformation mode

- Calls `.transformationMode(TransformationMode.fromSystemProperty())`: system property `judo.transformation.mode` → OSGi PID default → ZETA.
- ETL/DUAL need aggregate `hu.blackbelt.judo.tatami:judo-tatami-jsl-workflow-etl` (`pom`, all 10 add-ons) on the runtime classpath; else `EtlEngineMissingException` naming the missing artifact. Javadoc states this.
- The two jsl add-ons alone are NOT enough: every downstream stage Work reads the mode.
- Invalid property value is ignored → ZETA.
- Path quirk (pre-existing): file lives under `bootstrap/jsl/` but Java package is `hu.blackbelt.judo.runtime.core.guice.jsl`.

## Asm2Query stage off

- Sets `.ignoreAsm2Query(true)` on `DefaultWorkflowSetupParameters` (workflow default is false). No consumer of the query model here.
- Returned `TransformationContext` holds no `QueryModel`; ASM, expression, RDBMS (per dialect) models present as before.
- `DefaultWorkflowSave.saveModels` writes no `<name>-query.model`.
- Runtime builds its own query model at boot; persisted query model comes from the maven-plugin generated output (jsl-itest), not from this loader.
- Test: `JSLTransformationModelLoaderTest.asm2QueryStageIsOffByDefault` (TP-49).

See change: split-etl-artifacts, persisted-query-model.
