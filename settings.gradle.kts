pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "shopee-kmm"

include(
//    ":iosApp",
    ":backend",
    ":androidApp",
    ":shared"
)
