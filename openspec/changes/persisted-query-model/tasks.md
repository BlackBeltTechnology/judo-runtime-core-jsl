# Tasks

Module tasks of the master change `persisted-query-model` (judo-ng superproject, `openspec/changes/persisted-query-model`) for `judo-runtime-core-jsl` (rank 10, folded into feature branch `feature/JNG-6349_AddZetaValidatiob`).

## 1. JSLTransformationModelLoader stage off

- [x] 1.1 `JSLTransformationModelLoader` defaults `ignoreAsm2Query=true`. Verify: `JSLTransformationModelLoaderTest` asserts no `QueryModel` in the context.
- [x] 1.2 Add unit test: JSLTransformationModelLoader stage off by default — copy harness glue from runtime/judo-runtime-core-jsl/judo-runtime-core-jsl-guice/src/test/java/hu/blackbelt/judo/runtime/core/bootstrap/jsl/JSLTransformationModelLoaderTest.java (suite-level: jsl-itest models/CastingFunctionsModel/.../CastingFunctionsTest.java); Triple: `JSLTransformationModelLoader` on its test model / `loadAndTransform` / no `QueryModel` in the returned context (test-plan #TP-49)

## 2. jsl-itest fixture through the shared decision

- [x] 2.1 jsl-itest: the generated models now include the query file, and the common `JudoRuntimeFixture` obtains `QueryFactory` through `QueryModelLoadOrBuild` in strict `load` and in `build`. Add `jsl-transformation` and `test-infrastructure` spec deltas. Verify: the jsl-itest suite passes in both modes; `openspec validate` in the store.
- [x] 2.2 Add itest test: jsl-itest suite in strict load — copy harness glue from runtime/judo-runtime-core-jsl/judo-runtime-core-jsl-guice/src/test/java/hu/blackbelt/judo/runtime/core/bootstrap/jsl/JSLTransformationModelLoaderTest.java (suite-level: jsl-itest models/CastingFunctionsModel/.../CastingFunctionsTest.java); Triple: jsl-itest models (now including the artifacts) / full jsl-itest suite with fixture mode `load` (strict) / suite green; 0 fallbacks (strict mode would fail boot otherwise) (test-plan #TP-27)

## 3. Documentation

- [x] 3.1 Documentation: `AGENTS.md` rows for the changed files. Verify: `kb_search --doc-type agents "QueryModelLoadOrBuild"` hits the fixture rows.
