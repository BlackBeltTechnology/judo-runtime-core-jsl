# `judo-runtime-core-jsl-itest/common/src/main/java/hu/blackbelt/judo/runtime/core/jsl` — logging config shipped on the itest-common classpath

Java sources of this package live in the `fixture/` and `util/` subdirectories; only a logback
config sits here. It is under `src/main/java`, so it reaches the classpath only because the build
copies non-Java resources from the source root — moving it to `src/main/resources` would change
nothing functionally.

| File | Purpose |
|---|---|
| `logback.xml` | Silences integration-test logging: `<root level="error">` with a single `CONSOLE` `ch.qos.logback.core.ConsoleAppender` using `PatternLayout` `%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n`. Any INFO/DEBUG a fixture emits is dropped unless a downstream module supplies its own `logback.xml` earlier on the classpath. |
