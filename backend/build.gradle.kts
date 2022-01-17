import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}

group = "com.shoppe"
version = "0.0.1"

kotlin.sourceSets["main"].kotlin.srcDirs("src")
sourceSets["main"].resources.srcDirs("resources")

application {
    mainClassName = "com.shoppe.ServerKt"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}

tasks.create("stage") {
    dependsOn("installDist")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = AndroidConfig.javaVersion
}

dependencies {
    with(Backend) {
        implementation(logback)
        implementation(ktorServer)
        implementation(ktorGson)
        implementation(ktorAuth)
        implementation(ktorAuthJwt)
        implementation(kMongo)
        implementation(kGraphQL)
        implementation(kGraphQLKtor)
        implementation(bCrypt)
        implementation(ktorTest)
    }
    with(Koin) {
        implementation(ktor)
    }
    with(Kotlin) {
        implementation(stdlib)
    }
}