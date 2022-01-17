package com.shoppe

import com.shoppe.di.mainModule
import com.shoppe.plugins.configureContentNegotiations
import com.shoppe.plugins.configureGraphQL
import com.shoppe.plugins.configureRouting
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