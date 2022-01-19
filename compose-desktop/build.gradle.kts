import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") version Versions.composeDesktopWeb
    application
}

group = "com.adwi.shoppe"
version = "0.0.1"

application {
    mainClass.set("ApplicationKt")
}

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(project(":shared"))
    with(compose) {
        implementation(desktop.currentOs)
        implementation(runtime)
        implementation(foundation)
        implementation(material)
        implementation(animation)
        implementation(animationGraphics)
        implementation(ui)
        implementation(uiTooling)
        implementation(preview)
        implementation(materialIconsExtended)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = AndroidConfig.javaVersion
}