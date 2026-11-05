# AGENTS.md — `judo-runtime-core-jsl-spring-itest/src/test/resources`

Test logging configuration for the Spring integration test suite.

| File | Purpose |
| --- | --- |
| `logback.xml` | Logback logging configuration defining `CONSOLE` `ConsoleAppender` with `PatternLayout` (`%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n`), setting default root log level to `warn`, and registering `LevelChangePropagator` with `resetJUL=true` to bridge Java Util Logging. |
