import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    application
    kotlin(Plugins.JVM)
    id(Plugins.SHADOW)
    id(Plugins.KTLINT)
}

group = "com.adwi.ktor"
version = "0.0.1"

kotlin.sourceSets["main"].kotlin.srcDirs("src")
sourceSets["main"].resources.srcDirs("resources")

application {
    mainClassName = "com.adwi.ktor.ServerKt"
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
    with(Ktor) {
        implementation(core)
        implementation(netty)
        implementation(gson)
        implementation(auth)
        implementation(jwt)
        implementation(jackson)
        implementation(locations)
        implementation(hostCommon)
        implementation(test)
    }
    with(Backend) {
        implementation(logback)
        implementation(kMongo)
        implementation(kMongoCoroutine)
        implementation(kGraphQL)
        implementation(kGraphQLKtor)
        implementation(bCrypt)
    }
    with(Koin) {
        implementation(ktor)
    }
    with(Kotlin) {
        implementation(stdlib)
    }
}