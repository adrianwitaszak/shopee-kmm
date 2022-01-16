package com.shoppe

import com.shoppe.di.mainModule
import com.shoppe.plugins.configureContentNegotiations
import com.shoppe.plugins.configureGraphQL
import com.shoppe.plugins.configureRouting
import com.shoppe.util.PORT
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin

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