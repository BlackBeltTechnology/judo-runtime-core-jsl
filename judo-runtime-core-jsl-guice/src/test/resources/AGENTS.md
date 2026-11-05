# AGENTS.md — `judo-runtime-core-jsl-guice/src/test/resources`

Test logging configuration for Guice-based JSL bootstrap test suites.

| File | Purpose |
| --- | --- |
| `logback.xml` | Test Logback configuration defining `CONSOLE` appender with timestamp/thread/level formatting. Sets root logging to `warn` and configures `LevelChangePropagator` with `resetJUL=true` for java.util.logging synchronization. |
