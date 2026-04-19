// File: app/build.gradle.kts
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.vicious.viciouslayer" // Make sure this matches your package
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vicious.viciouslayer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    // Add your library dependencies here
}
