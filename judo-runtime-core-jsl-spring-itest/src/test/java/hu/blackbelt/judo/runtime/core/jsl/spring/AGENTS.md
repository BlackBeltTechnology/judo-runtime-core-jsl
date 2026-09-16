# AGENTS.md — `judo-runtime-core-jsl-spring-itest/src/test/java/hu/blackbelt/judo/runtime/core/jsl/spring`

Spring Boot integration test suite validating generated DAO operations and transaction management for JSL models.

| File | Purpose |
| --- | --- |
| `JudoRuntimeCoreSpringApplicationTests.java` | Integration test suite annotated with `@SpringBootTest` and `@EnableTransactionManagement`. Injects generated DAOs (`SalesPersonDao`, `LeadDao`, `RootAllLeadsDao`, `RootOneLeadDao`, `TotalNumberOfLeadsDao`) and `PlatformTransactionManager` to verify CRUD lifecycle, filtered queries, manual transaction commit/rollback isolation, and `@Transactional` synchronization. |
