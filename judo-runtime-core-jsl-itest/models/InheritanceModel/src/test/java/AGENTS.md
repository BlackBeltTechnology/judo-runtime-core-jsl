# AGENTS.md — `judo-runtime-core-jsl-itest/models/InheritanceModel/src/test/java`

Logging configuration for InheritanceModel test execution.

| File | Purpose |
| --- | --- |
| `logback.xml` | Configures Logback logging for test execution; defines `CONSOLE` ConsoleAppender with `PatternLayout` (`%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n`) and routes root logger to it at `error` level. |
