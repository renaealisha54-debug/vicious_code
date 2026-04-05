plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.chaquo.python") version "15.0.1"
}

android {
    namespace = "com.viciouscode.viciouscode"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.viciouscode.viciouscode"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.7.5"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.ui:ui:1.7.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.5")
    implementation("androidx.compose.material3:material3:1.3.1")

    // REAL CODE EDITOR
    implementation("io.github.rosemoe.sora-editor:sora-editor:0.18.0")
    implementation("io.github.rosemoe.sora-editor:sora-language-textmate:0.18.0")

    // Python runtime (real execution)
    implementation("com.chaquo.python:chaquopy:15.0.1")

    // LSP for all languages
    implementation("org.lsp4j:org.lsp4j:0.23.1")

    // Terminal for native compile/run
    implementation("com.github.hussainderry:android-terminal-view:1.2.0")

    // WorkManager for background builds
    implementation("androidx.work:work-runtime-ktx:2.10.0")

    // Gson for extensions
    implementation("com.google.code.gson:gson:2.11.0")

    // APK parser & signing tools
    implementation("net.dongliu:apk-parser:2.7.3")
    implementation("com.android.tools.build:gradle-api:8.7.2")
}
