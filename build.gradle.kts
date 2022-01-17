buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(uri("https://plugins.gradle.org/m2/"))
    }
    dependencies {
        classpath(Build.gradle)
        classpath(Build.kotlin)
        classpath(Build.sqlDelight)
        classpath(Build.serialization)
        classpath(Build.shadow)
        classpath(Build.ktLint)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
        maven(url = "https://jitpack.io")
        maven(url = "https://androidx.dev/snapshots/builds/7888785/artifacts/repository")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
