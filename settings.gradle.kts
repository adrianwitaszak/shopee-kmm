pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "shoppe-kmm"

include(
    ":androidApp",
    ":shared",
    ":core"
)
