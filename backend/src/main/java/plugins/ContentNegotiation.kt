package com.shoppe.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*

fun Application.configureContentNegotiations() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            disableHtmlEscaping()
        }
    }
}