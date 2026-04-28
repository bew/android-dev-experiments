// WHAT:
// Core build config for the app module.
// Declares applicationId, SDK versions, build types, Java/Kotlin targets, the Compose feature flag,
// and all dependencies.
//
// NEEDED: Yes — required.
// This is how the Android Gradle Plugin knows how to compile and package your app.
//
// MAINTAINANCE:
// Update compileSdk/targetSdk yearly.
// Add new dependencies in the dependencies {} block and their versions in libs.versions.toml.

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "dev.bew.myenergybar"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.bew.myenergybar"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
}
