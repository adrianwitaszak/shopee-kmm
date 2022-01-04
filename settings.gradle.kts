pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "shoppe-kmm"

include(
//    ":iosApp",
    ":androidApp",
    ":shared"
)
