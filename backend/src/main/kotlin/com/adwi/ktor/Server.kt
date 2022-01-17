package com.adwi.ktor

import com.adwi.ktor.di.mainModule
import com.adwi.ktor.plugins.configureContentNegotiations
import com.adwi.ktor.plugins.configureGraphQL
import com.adwi.ktor.plugins.configureRouting
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.core.context.startKoin

fun main() {

    startKoin {
        modules(mainModule)
    }

    val port = System.getenv("PORT")?.toInt() ?: 0
    embeddedServer(Netty, port) {
        configureGraphQL()
        configureContentNegotiations()
        configureRouting()
    }.start(wait = true)
}