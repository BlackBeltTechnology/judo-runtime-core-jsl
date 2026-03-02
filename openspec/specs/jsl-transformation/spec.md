# JSL Transformation Specification

## Purpose

The `judo-runtime-core-jsl-guice` module provides the `JSLTransformationModelLoader` class that loads JSL (JUDO Specific Language) models and transforms them into PSM (Platform Specific Model) representations using the tatami workflow engine. This is the entry point for all JSL-to-runtime transformations.

## Architecture

The transformation is centered on a single static utility class:

- **`JSLTransformationModelLoader`** — orchestrates the full transformation pipeline
  - Uses `JslParser` to parse JSL sources into `JslDslModel`
  - Uses `JslStreamSource` to abstract URI-based model loading
  - Delegates to `JslDefaultWorkflow` with `DefaultWorkflowSetupParameters` for the actual transformation
  - Produces a `TransformationContext` containing all generated metamodels (PSM, RDBMS, ASM, etc.)
  - Supports dialect specification (HSQLDB, PostgreSQL) to control database-specific output

## Requirements

### Requirement: Load JSL model from URIs

The loader SHALL accept one or more URIs pointing to JSL source files and produce a fully transformed model.

#### Scenario: Single JSL file transformation
- **GIVEN** a valid JSL file at a known URI
- **WHEN** `loadAndTransformJslModels(modelName, dialect, outputDirectory, uri)` is called
- **THEN** a `TransformationContext` is returned containing the PSM model and all derived metamodels

#### Scenario: Multiple JSL file transformation
- **GIVEN** multiple valid JSL files at known URIs
- **WHEN** `loadAndTransformJslModels(modelName, dialect, outputDirectory, uri1, uri2, ...)` is called
- **THEN** all JSL sources are parsed together into a single `JslDslModel` and transformed as one unit

### Requirement: Load JSL model from pre-parsed JslDslModel

The loader SHALL accept an already-parsed `JslDslModel` object for transformation.

#### Scenario: Pre-parsed model transformation
- **GIVEN** a `JslDslModel` that has been parsed externally
- **WHEN** `loadAndTransformJslModels(model, dialect, outputDirectory)` is called
- **THEN** the model is transformed using `JslDefaultWorkflow` and a `TransformationContext` is returned

### Requirement: Dialect-aware transformation

The loader SHALL produce database-specific output based on the specified dialect.

#### Scenario: HSQLDB dialect
- **GIVEN** a valid JSL model
- **WHEN** transformation is invoked with `dialect = "hsqldb"`
- **THEN** the generated RDBMS model uses HSQLDB-compatible SQL types and syntax

#### Scenario: PostgreSQL dialect
- **GIVEN** a valid JSL model
- **WHEN** transformation is invoked with `dialect = "postgresql"`
- **THEN** the generated RDBMS model uses PostgreSQL-compatible SQL types and syntax

### Requirement: Transformation failure reporting

The loader SHALL throw an `IllegalStateException` when the workflow reports a failure.

#### Scenario: Failed workflow
- **GIVEN** a JSL model that causes a workflow error
- **WHEN** `loadAndTransformJslModels()` is called
- **THEN** an `IllegalStateException` is thrown with details from the `WorkReport`
