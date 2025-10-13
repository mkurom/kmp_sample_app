This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

## Architecture

### KMP (Shared Logic)
- Pattern: Clean Architecture + MVVM + Repository + DI (Koin)
- Layers
  - Domain: entities, repository interfaces (e.g., `domain/model/*`, `domain/repository/*`)
  - Data: repository implementations, data sources (e.g., `data/repository/*`, `data/datasource/*`)
  - Presentation: ViewModels exposing `StateFlow` (e.g., `presentation/viewmodel/*`)
  - DI: Koin modules wiring dependencies (e.g., `di/AppModule.kt`)

### CMP (Compose Multiplatform UI)
- Thin UI that renders state from KMP ViewModels.
- App-level navigation is delegated to the platform layer.
- Example screens: `composeApp/src/commonMain/kotlin/com/my/composedemo/ScreenContent.kt`

### iOS (SwiftUI)
- Pattern: MVVM + Coordinator (Router) + `EnvironmentObject`.
- `AppCoordinator` owns app navigation and receives `NavigationState` via `.environmentObject(...)`.
- Entry point: `iosApp/iosApp/App/iOSApp.swift` (`@main`).
- Coordinator: `iosApp/iosApp/Platform/AppCoordinator.swift`.
- App navigation state: `iosApp/iosApp/Platform/NavigationState.swift`.

### Android
- Entry point: `composeApp/src/androidMain/AndroidManifest.xml` → `com.my.composedemo.ui.activities.MainActivity`.
- `MainActivity` bootstraps DI (Koin) and sets Compose content with `App()`.
- App-level navigation can be added with Compose Navigation as needed.

### Data Flow
- UI (CMP/SwiftUI) observes KMP ViewModel `StateFlow`.
- User actions call KMP use-cases via ViewModel methods.
- Repositories abstract data sources; DI provides implementations.

### Migration/Replace Strategy (CMP → SwiftUI Native)
1. Keep KMP as the single source of business logic.
2. Replace screens UI-first in SwiftUI while bridging to KMP ViewModels.
3. Optionally migrate specific use-cases to Swift over time.
4. Remove CMP screens once SwiftUI reaches parity.

### Notes
- Common `NavigationState` in KMP is deprecated to avoid platform navigation overlap; prefer platform-native navigation (SwiftUI Coordinator on iOS).