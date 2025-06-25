# Why KMP?

> Duplication may be the root of all evil in software.
>
> - Robert C Martin

- Trully native UI
- Shared business logic
- Shared infrastructure and boilerplate code.

There's a lot of big company that uses KMP such as Netflix, McDonalds, Philips, Shopify, etc.

# What is Kotlin Multiplatform?

- Kotlin based framework to support code sharing.
- Currently KMP Support
  - Android
  - IOS
  - Mobile
  - Desktop.
- We can keep the UI 100% written in native while having the single source of logic code.

# Project Structure

When project is created we have 3 big parts:

- `androidApp` : android app, build able and runable on Android Studio.
- `iosApp` : ios app, buildable and runable on Xcode
- `shared` : kotlin multiplatform magic

Android will import

- `androidMain`
- `commonMain`

iOSApp will import as Framework we included code in:

- `iosMain`
- `commonMain`

# Actual/Expect Mechanism

expect keyword can be added into a func or class

```kotlin
interface Platform {
    val name: String
}

expect func getPlatform(): Platform
```

It works same like an abstract class. If a function of class are attached with `expect`, then both on `androidMain` and `iosMain` have to add `actial` func or class as well. The compiler will enforce the "actualization" from the `commonMain` expectation.

# Adding 3rd party dependency for each module
1. Open The build gradle file `shared.build.gradle.kts`
2. We can add them on the `sourceSets`.

```kotlin
sourceSets {
    val commonMain by getting {
        dependencies {
            //put your multiplatform dependencies here
        }
    }
    
    val androidMain by getting {
        dependencies {
            // android main dependency
        }
    }
    
    val iosMain by getting { 
        dependencies { 
            // ios main dependency
        }
    }
    
    val commonTest by getting {
        dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
```