import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    listOf(
        androidNativeX86(),
        androidNativeX64(),
        androidNativeArm32(),
        androidNativeArm64()
    ).forEach {
        it.binaries.staticLib {
            baseName = "kmm"
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "kmm"
        }
    }

    sourceSets {
        val commonMain by getting

        val androidNativeArm64Main by getting
        val androidNativeArm32Main by getting
        val androidNativeX86Main by getting
        val androidNativeX64Main by getting
        val androidMain by creating {
            dependsOn(commonMain)
            androidNativeArm64Main.dependsOn(this)
            androidNativeArm32Main.dependsOn(this)
            androidNativeX86Main.dependsOn(this)
            androidNativeX64Main.dependsOn(this)
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

// Configure copying K/N binaries into output directories.
val kmmLibs = rootProject.file("android/library/src/main/jni/kmmLibs")
val platforms = mapOf(
    kotlin.targets["androidNativeX86"] as KotlinNativeTarget to "x86",
    kotlin.targets["androidNativeX64"] as KotlinNativeTarget to "x86_64",
    kotlin.targets["androidNativeArm32"] as KotlinNativeTarget to "armeabi-v7a",
    kotlin.targets["androidNativeArm64"] as KotlinNativeTarget to "arm64-v8a"
)

val copyLibs by tasks.creating {
    platforms.forEach { (target, subdir) ->
        val lib = target.binaries.getStaticLib(org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG)
        dependsOn(lib.linkTask)
        lib.linkTask.doLast {
            copy {
                from(lib.outputDirectory)
                into(kmmLibs.resolve(subdir))
            }
        }
    }
}

val deleteOut by tasks.creating(Delete::class.java) {
    delete(kmmLibs)
}

tasks["clean"].dependsOn(deleteOut)