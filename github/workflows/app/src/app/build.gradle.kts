plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yourpackage.name"
    compileSdk = 34 // Or latest version

    defaultConfig {
        applicationId = "com.yourpackage.name"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}
