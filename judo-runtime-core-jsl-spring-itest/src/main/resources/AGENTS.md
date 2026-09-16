# AGENTS.md — `judo-runtime-core-jsl-spring-itest/src/main/resources`

Runtime configuration and logging declarations for Spring integration test executions.

| File | Purpose |
| --- | --- |
| `application.properties` | Spring Boot test configuration binding in-memory HSQLDB datasource (`jdbc:hsqldb:mem:testdb`), pointing `spring.liquibase.change-log` to `classpath:model/SalesModel-liquibase_hsqldb.changelog.xml` and specifying `judo.modelName=SalesModel`. |
| `logback-spring.xml` | Spring Logback configuration defining colored `Console` pattern appender and 10MB size/time-rolling `RollingFile` appender under `./logs/spring-boot-logger.log`. Sets root logging to `info` and filters `hu.blackbelt` to `debug`. |
