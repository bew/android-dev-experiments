// WHAT:
// Declares the authoritative plugin versions for the whole project using "apply false".
// "apply false" means: pin the version here, but don't run the plugin at the root level —
// each module opts in via its own `build.gradle.kts`.
//
// In a single-module app this is mostly a convention with no real benefit — the versions
// could be declared directly in app/build.gradle.kts instead. But it's kept here because
// it's the standard layout Android Studio and most projects expect.
//
// NEEDED:
// Not strictly, but conventional.
// Safe to keep as-is.

// NOTE: This controls which plugins are used for the Gradle build.
// Versions are taken from `gradle/libs.versions.toml` file.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}
