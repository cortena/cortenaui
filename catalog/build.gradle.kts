plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.cortena.components.catalog"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.cortena.components.catalog"
        minSdk = 35
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures { compose = true }
}

dependencies {
    // Foundation tokens (our source of truth)
    implementation(project(":foundation"))
    implementation(project(":compose"))

    // Compose — catalog needs this for rendering
    // When :compose module is ready, most of these move there
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.foundation)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)

    // Temporary: material3 for Text until cortena-compose is ready
    implementation(libs.compose.material3)
}
