plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    buildToolsVersion = "30.0.3"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        consumerProguardFiles += file("consumer-rules.pro")

        externalNativeBuild {
            cmake {
                cppFlags("-std=c++17", "-fexceptions")
                abiFilters("armeabi-v7a", "arm64-v8a", "x86", "x86_64")

                arguments(
                    "-DANDROID_CPP_FEATURES=rtti exceptions",
                    "-DANDROID_PLATFORM=android-21",
                    "-DANDROID_STL=c++_static",
                )
            }
        }
    }

    packagingOptions {
        jniLibs.keepDebugSymbols += "*/*/*.so"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    externalNativeBuild {
        cmake {
            path("src/main/jni/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

val preBuild by tasks.getting
preBuild.dependsOn(":kmm:copyLibs")



