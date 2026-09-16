# `judo-runtime-core-jsl-guice/src/main/java/hu/blackbelt/judo/runtime/core/bootstrap/jsl` — JSL→ASM/RDBMS transformation entry point for Guice bootstrap

Single static façade that drives the tatami `JslDefaultWorkflow` so Guice-based runtime setup can
turn `.jsl` sources into a `TransformationContext`. Note the directory path says `bootstrap/jsl`
but the declared package is `hu.blackbelt.judo.runtime.core.guice.jsl` — the two disagree.

| File | Purpose |
|---|---|
| `JSLTransformationModelLoader.java` | Drives `JslDefaultWorkflow` in `TransformationMode.ZETA` to a `TransformationContext`; exports `loadAndTransformJslModels` overloads. → see `JSLTransformationModelLoader.java.AGENTS.md` |
