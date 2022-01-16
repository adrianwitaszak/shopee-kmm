package com.shoppe

import com.shoppe.di.mainModule
import com.shoppe.plugins.configureContentNegotiations
import com.shoppe.plugins.configureGraphQL
import com.shoppe.plugins.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin

fun main() {

    startKoin {
        modules(mainModule)
    }

    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port) {
        configureGraphQL()
        configureContentNegotiations()
        configureRouting()
    }.start(wait = true)
}