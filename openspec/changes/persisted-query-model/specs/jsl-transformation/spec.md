## ADDED Requirements

### Requirement: Asm2Query stage off by default

The loader SHALL set `ignoreAsm2Query=true` on the workflow setup parameters, so that `JslDefaultWorkflow` does not run the asm2query stage and the returned `TransformationContext` contains no `QueryModel`.

#### Scenario: No query model in the returned context
- **GIVEN** a valid JSL model such as the loader test model
- **WHEN** `loadAndTransformJslModels(...)` returns
- **THEN** the returned `TransformationContext` contains no `QueryModel`
- **AND** the ASM, RDBMS and expression models are present as before

#### Scenario: Saved output has no query file
- **GIVEN** the context returned by the loader
- **WHEN** `saveModels(context, outputDirectory, dialects)` writes it
- **THEN** no `<name>-query.model` file is written to the output directory
