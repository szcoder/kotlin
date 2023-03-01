plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "szcoder.kotlin.demo"
    compileSdk = 32
    defaultConfig {
        applicationId = "szcoder.kotlin.demo"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":android:library"))
}