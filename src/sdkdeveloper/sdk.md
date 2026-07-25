# Android SDK Developer — Interview Preparation Guide
**Role:** Android SDK Developer | Bangalore | 1.5–3.5 Years

This guide maps every line of the JD to concrete topics, likely questions, and what "good" looks like in an answer. Work top to bottom — the order roughly matches how interviewers usually probe (fundamentals → architecture → SDK-specific → performance → bonus).

---

## 1. Kotlin & Java Fundamentals (Core Language)

**Topics to revise**
- Kotlin: null safety (`?`, `!!`, `?:`), data classes, sealed classes, extension functions, inline/reified functions, scope functions (`let`, `run`, `apply`, `also`, `with`)
- Higher-order functions, lambdas, `companion object` vs Java `static`
- Java interop — `@JvmStatic`, `@JvmOverloads`, platform types, checked vs unchecked exceptions
- Visibility modifiers and why they matter *more* in an SDK (`internal`, `public`, explicit API mode)

**Likely questions**
- Difference between `let`, `apply`, `run`, `also`, `with` — when would you pick one over another in SDK code?
- Why is `internal` visibility important when designing a public SDK?
- What is Kotlin's "explicit API mode" and why would you enable it for a library module?
- How do sealed classes help model SDK result/error states?

**What interviewers want to hear:** you don't just know syntax — you know *which access modifiers and language features reduce what a consuming app can accidentally break*.

---

## 2. Architecture & Clean Code (MVVM / MVI / Clean Architecture)

**Topics to revise**
- MVVM: ViewModel, LiveData/StateFlow, unidirectional data binding
- MVI: single immutable state, intent → reducer → state, why it suits SDKs with complex async state
- Clean Architecture layers: presentation / domain / data, dependency inversion, use-cases/interactors
- Modularization: `:core`, `:network`, `:ui`, `:public-api` module splits; why SDKs are modularized differently from apps (minimizing exposed surface, avoiding transitive dependency bloat)
- Repository pattern, dependency injection (Hilt/Koin/manual DI) — and why SDKs often avoid forcing a DI framework on the host app

**Likely questions**
- How would you structure a Clean Architecture module set for an SDK vs. a full app?
- Why might MVI be preferred over MVVM for SDK-internal state management?
- How do you prevent internal architecture classes (repositories, use-cases) from leaking into the SDK's public API?
- How do you avoid forcing your DI framework choice onto the host app?

**What interviewers want to hear:** an SDK's architecture goal isn't just "clean code" — it's *minimal, stable public surface + hidden internals* so you can refactor freely without breaking consumers.

---

## 3. Cross-Platform Bridge Integration (Flutter / React Native / Cordova)

**Topics to revise**
- **Flutter:** `MethodChannel`, `EventChannel`, `PlatformView`, `Pigeon` for type-safe codegen, plugin folder structure
- **React Native:** Native Modules (`ReactContextBaseJavaModule`), `Promise`/callback bridging, `ReactPackage` registration, New Architecture (TurboModules/JSI) awareness
- **Cordova:** `CordovaPlugin`, `execute()` method, `CallbackContext`
- Serialization across the bridge (primitives only — how to pass complex objects: JSON encode/decode, `Map<String, Any>`)
- Threading — bridge calls often land on non-main threads; marshaling back to main thread for UI callbacks
- Error propagation across the bridge (native exceptions → JS/Dart-readable errors)

**Likely questions**
- Walk me through how a native SDK method call reaches Flutter/Dart code and how the result comes back.
- How do you pass a complex native object (e.g., a custom result class) across a MethodChannel?
- What happens if a native exception occurs mid-call — how do you surface it safely to the cross-platform layer without crashing the host app?
- Have you dealt with threading issues in bridge callbacks (e.g., calling back on the wrong thread)?

**What interviewers want to hear:** hands-on scars — a specific bug you hit with a bridge call (e.g., a channel invoked after the engine was destroyed, or a leak from a channel not being cleaned up in `onDetachedFromEngine`).

---

## 4. Modern UI & Async (Jetpack Compose, XML, Coroutines, Flow)

**Topics to revise**
- Jetpack Compose basics: composable functions, recomposition, state hoisting, `remember`/`mutableStateOf`, side-effects (`LaunchedEffect`, `DisposableEffect`)
- Interop: `ComposeView` inside XML, `AndroidView` inside Compose — relevant because SDKs often must support both UI systems for different host apps
- Coroutines: `suspend` functions, structured concurrency, `CoroutineScope`, `Dispatchers`, cancellation, `SupervisorJob`
- Flow vs StateFlow vs SharedFlow — cold vs hot streams, when to expose which from an SDK's public API
- Why SDKs should be cautious about which `CoroutineScope` they use internally (avoid leaking host app's lifecycle-bound scope; avoid GlobalScope)

**Likely questions**
- Why is `GlobalScope` discouraged inside an SDK, and what would you use instead?
- What's the difference between exposing a `Flow` vs a `StateFlow` from your SDK's public API, and which would you pick for "current connection status"?
- How do you support both XML and Compose consumers from the same SDK UI component?
- How do you handle coroutine cancellation gracefully so an in-flight SDK operation doesn't leave the host app in a broken state?

---

## 5. Performance & Memory Tuning

**Topics to revise**
- Android Studio Profiler: CPU, Memory, Network, Energy tabs — how to read a heap dump
- LeakCanary: how it detects leaks, common SDK leak sources (static references to `Context`/`Activity`, unregistered listeners/receivers, lingering coroutine scopes)
- Techniques to reduce **binary size** (AAR): ProGuard/R8 shrinking, avoiding unnecessary dependencies, splitting optional features into separate modules
- Reducing **battery drain**: batching network calls, respecting Doze/App Standby, WorkManager instead of raw background threads/alarms
- Reducing **network latency**: connection pooling (OkHttp), caching, request coalescing
- Reducing **memory footprint**: avoiding large static caches, bitmap/image handling, weak references where appropriate

**Likely questions**
- Give an example of a memory leak you found with LeakCanary and how you fixed it.
- How do you keep an SDK's AAR size small while still shipping a lot of functionality?
- What steps would you take if a client app complained your SDK was draining battery in the background?
- How do you profile network calls made by your SDK to find latency issues?

**What interviewers want to hear:** concrete, quantified stories — "reduced AAR size from X MB to Y MB by..." or "found a leak caused by a static listener registered in `init()` and never unregistered."

---

## 6. API Stability, Backward Compatibility & Exception Handling

**Topics to revise**
- Semantic versioning (major.minor.patch) and what constitutes a breaking change in an SDK
- `@Deprecated` annotations with migration paths, keeping old APIs functional during transition windows
- Defensive coding: wrapping all public entry points in try/catch, never letting an SDK exception propagate uncaught into the host app
- Custom exception types / `Result<T>` / sealed-class result wrappers instead of throwing raw exceptions
    - Handling `minSdkVersion` differences gracefully, feature-detection instead of hard OS-version assumptions
    - Contract testing — ensuring new SDK versions don't silently change behavior consumers depend on

    **Likely questions**
    - How do you evolve a public method's signature without breaking existing integrations?
    - Describe your approach to exception handling at the SDK's public boundary — what's your last line of defense against crashing the host app?
    - How would you deprecate an old API while giving integrators time to migrate?
    - How do you test backward compatibility across SDK versions?

    ---

    ## 7. Cross-Cutting: Testing & CI/CD (Bonus, but often asked)

    **Topics to revise**
    - JUnit + MockK: writing unit tests for ViewModels/use-cases, mocking coroutines (`runTest`, `TestDispatcher`)
    - Instrumented tests vs unit tests — what's realistic to test at the SDK level
    - CI/CD basics: Gradle build variants, publishing pipelines, automated versioning/tagging, running lint/tests on PRs

    **Likely questions**
    - How do you unit test a suspend function or a Flow-emitting function with MockK?
    - What does your CI pipeline check before an SDK release is published?

    ---

    ## 8. Bonus Topics (Good to Have — worth at least skimming)

    | Topic | What to know |
    |---|---|
    | **Publishing AARs** | Maven Central / JitPack / internal Nexus/Artifactory — `maven-publish` Gradle plugin, POM metadata, signing artifacts |
    | **SSL Pinning** | `OkHttp CertificatePinner`, or Network Security Config XML pinning |
    | **ProGuard/R8** | Writing `consumer-rules.pro` for a library (critical difference from app-level ProGuard rules — you must protect the *consumer's* build, not just your own) |
    | **Secure KeyStore** | Android Keystore system, `EncryptedSharedPreferences`, avoiding hardcoded secrets in SDK code |
    | **Network debugging tools** | Charles Proxy, Chucker, Flipper — how you've used them to debug SDK network issues |

    ---

    ## 9. Behavioral / Scenario Questions to Prepare For

    Since this is an SDK role (not app dev), expect scenario questions that test *SDK-specific thinking*, not just Android knowledge:

    1. "A client integrates your SDK and their app crashes — but your SDK isn't the cause. How do you investigate?"
    2. "How do you design a public API so it's hard for integrators to misuse?"
    3. "Two client apps use conflicting versions of a dependency your SDK also uses — how do you avoid dependency collision?" *(hint: shading/relocating packages, or minimizing/removing the dependency)*
    4. "How would you roll out a breaking change to thousands of integrators with minimal disruption?"
    5. "Tell me about a time you had to reduce your SDK's size/latency/memory — what trade-offs did you make?"

    ---

    ## 10. Quick Self-Check Before the Interview

    - [ ] Can I explain MVVM vs MVI in under a minute, with an SDK-specific example?
    - [ ] Can I explain how a MethodChannel call flows from Dart → Kotlin → back to Dart, including error cases?
    - [ ] Do I have one real LeakCanary/memory-leak story ready to tell?
    - [ ] Can I explain the difference between app-level and library-level ProGuard rules (`consumer-rules.pro`)?
    - [ ] Can I explain why `GlobalScope` is risky in a library and what I use instead?
    - [ ] Do I have a number-backed story about reducing AAR size, latency, or battery drain?
    - [ ] Can I describe my exception-handling strategy at a public SDK boundary?

    Good luck — for a 1.5–3.5 yr role, they're weighing fundamentals heavily but will pay close attention to any hands-on SDK/bridging/performance stories, since those separate "app developer" candidates from "SDK developer" candidates.