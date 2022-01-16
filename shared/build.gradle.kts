import AndroidConfig.javaVersionName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin(Plugins.KOTLIN_MULTIPLATFORM)
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.APOLLO).version("3.0.0")
    id(Plugins.SQL_DELIGHT)
}

version = "1.0"

// workaround for https://youtrack.jetbrains.com/issue/KT-43944
android {
    compileSdk = AndroidConfig.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
    }
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
    compileOptions {
        sourceCompatibility = javaVersionName
        targetCompatibility = javaVersionName
    }
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
// Block from https://github.com/cashapp/sqldelight/issues/2044#issuecomment-721299517.
// See also: https://stackoverflow.com/a/62916853
    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(SqlDelight.runtime)
                with(Kotlin) {
                    api(coroutinesCore) { isForce = true }
                    api(apollo)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(SqlDelight.androidDriver)
                api(Koin.android)
                api(Koin.compose)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(project(Modules.SHARED))
                implementation(kotlin("test-junit"))
                implementation(AndroidTestDependencies.junit4)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(SqlDelight.nativeDriver)
            }
        }
        val iosTest by getting
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = AndroidConfig.javaVersion
    }
}

apollo {
    packageName.set(AndroidConfig.applicationId)
}

sqldelight {
    database("Shoppe") {
        packageName = AndroidConfig.applicationId
    }
}