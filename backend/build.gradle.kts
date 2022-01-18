plugins {
    application
    kotlin(Plugins.JVM)
    id(Plugins.SHADOW)
}

//group = "com.adwi.ktor"
//version = "0.0.1"

//kotlin.sourceSets["main"].kotlin.srcDirs("src")
//sourceSets["main"].resources.srcDirs("resources")

application {
    mainClassName = "com.adwi.ktor.ServerKt"
}

//tasks.withType<Jar> {
//    manifest {
//        attributes(
//            mapOf(
//                "Main-Class" to application.mainClassName
//            )
//        )
//    }
//}

//tasks.create("stage") {
//    dependsOn("installDist")
//}

//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = AndroidConfig.javaVersion
//}

dependencies {
    with(Ktor) {
        implementation(core)
        implementation(netty)
        implementation(gson)
        implementation(auth)
        implementation(jwt)
        implementation(test)
    }
    with(Backend) {
        implementation(logback)
        implementation(kMongo)
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