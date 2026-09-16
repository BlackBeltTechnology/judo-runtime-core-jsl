# AGENTS.md — `judo-runtime-core-jsl-spring-itest/src/main/java/hu/blackbelt/judo/runtime/core/jsl/spring`

Spring Boot application entry point for JSL runtime Spring integration test executions.

| File | Purpose |
| --- | --- |
| `JudoRuntimeCoreSpringApplication.java` | Spring Boot main application class for JSL core runtime integration tests. Annotated with `@SpringBootApplication`; exports `main(String[])` to run the application and logs startup completion via `ready()` on `ApplicationReadyEvent`. |
