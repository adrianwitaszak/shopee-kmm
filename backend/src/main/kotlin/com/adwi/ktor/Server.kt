package com.adwi.ktor

import com.adwi.ktor.di.mainModule
import com.adwi.ktor.plugins.configureContentNegotiations
import com.adwi.ktor.plugins.configureGraphQL
import com.adwi.ktor.plugins.configureRouting
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.core.context.startKoin

val PORT = System.getenv("PORT")?.toInt() ?: 0

fun main() {
    startKoin {
        modules(mainModule)
    }

    embeddedServer(Netty, PORT) {
        configureGraphQL()
        configureContentNegotiations()
        configureRouting()
    }.start(wait = true)
}