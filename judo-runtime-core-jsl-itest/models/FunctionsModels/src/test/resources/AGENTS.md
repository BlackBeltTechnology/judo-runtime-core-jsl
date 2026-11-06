# AGENTS.md — `judo-runtime-core-jsl-itest/models/FunctionsModels/src/test/resources`

Test JSL models defining built-in functions, expressions, and external model imports.

| File | Purpose |
| --- | --- |
| `Functions.jsl` | JSL test model exercising built-in-function semantics: entities `AnyTypeFunctions`, `Kleene`, `TimestampAsString` compute `isDefined()`/`isUndefined()`/`orElse()` over the intentionally-unset fields of `Entity`; `ExternalChild extends FE::ExternalEntity` navigates cross-model after `import FunctionsExternals as FE`; each entity pairs with a mapped `Transfer*`. |
| `FunctionsExternals.jsl` | JSL test model declaring external entity `ExternalEntity` and mapped transfer `TransferExternalEntity` with default string fields, used to test cross-model imports in `Functions.jsl`. |
