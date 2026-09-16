# `JSLTransformationModelLoader.java`

Runs `JslDefaultWorkflow` in `TransformationMode.ZETA` over one JSL model; returns its `TransformationContext`. Exports heavy-lifting `loadAndTransformJslModels(JslDslModel, dialect, outputDirectory)` plus `(modelName, dialect, outputDirectory, URI...)` overload parsing sources via `JslStreamSource`.

Model name taken from `model.getName()`, not the caller. On `WorkStatus.FAILED` or any exception dumps models into outputDirectory via `DefaultWorkflowSave.saveModels`, then rethrows.