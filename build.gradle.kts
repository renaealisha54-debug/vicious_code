plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.vicious.viciouslayer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vicious.viciouslayer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}
