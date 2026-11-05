# AGENTS.md — `judo-runtime-core-jsl-itest/common/src/main/java/hu/blackbelt/judo/runtime/core/jsl/util`

Testing utilities for intercepting and overriding system environment variables across integration tests.

| File | Purpose |
| --- | --- |
| `EnvironmentVariableMocker.java` | Intercepts `ProcessEnvironment` via `Mockito.mockStatic` to redirect `System.getenv()` calls to a scoped map stack. Exports `initMocked()`, `deinitMocked()`, `connect(Map)`, `pop()`, `remove(String)`. |
| `EnvironmentVariables.java` | Test resource managing temporary environment variable bindings during execution blocks. Extends `SingularTestResource` and implements `NameValuePairSetter`; exports `set(String, String)`, `and(String, String)`, `getVariables()`, activating changes via `doSetup()` and restoring environment via `doTeardown()`. |
