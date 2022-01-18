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
    compileOptions {
        sourceCompatibility = javaVersionName
        targetCompatibility = javaVersionName
    }
}

kotlin {
    jvm()
    android()
//    ios {
//        binaries {
//            framework {
//                baseName = "shared"
//            }
//        }
//    }
// Block from https://github.com/cashapp/sqldelight/issues/2044#issuecomment-721299517.
// See also: https://stackoverflow.com/a/62916853
//    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
//    if (onPhone) {
//        iosArm64("ios")
//    } else {
//        iosX64("ios")
//    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(SqlDelight.runtime)
            api(Koin.core)
            with(Kotlin) {
                api(coroutinesCore) { isForce = true }
                api(apollo)
                api(kermit)
                api(decompose)
                api(decomposeComposeJetbrains)
                api(mviKotlin)
            }
        }
//        sourceSets["commonTest"].dependencies {
//            implementation(kotlin("test-common"))
//            implementation(kotlin("test-annotations-common"))
//        }
        sourceSets["androidMain"].dependencies {
            implementation(SqlDelight.androidDriver)
            api(Koin.android)
            api(Koin.compose)
        }
//        sourceSets["androidTest"].dependencies {
//            implementation(kotlin("test-junit"))
//            implementation(AndroidTestDependencies.junit4)
//        }
//        sourceSets["iosMain"].dependencies {
//            implementation(SqlDelight.nativeDriver)
//        }
//        sourceSets["iosTest"].dependencies {
//        }
        sourceSets["jvmMain"].dependencies {
            implementation(SqlDelight.sqliteDriver)
            api(Koin.core)

        }
//        sourceSets["macOSMain"].dependencies {
//            implementation(SqlDelight.nativeDriverMacos)
//        }
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