import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    android {
        minSdk = 21
        compileSdk = 37
        namespace = "com.cortena.ui.foundation"
        compilerOptions { jvmTarget = JvmTarget.JVM_11 }
    }

    sourceSets {
        commonMain.dependencies {
            // Intentionally empty — foundation has zero external dependencies.
            // This module must remain framework-agnostic so it can be consumed
            // by Compose, View system, and future ROM integration alike.
        }
    }
}
