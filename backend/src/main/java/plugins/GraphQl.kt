package com.shoppe.plugins

import com.apurebase.kgraphql.GraphQL
import com.shoppe.graphql.*
import com.shoppe.services.*
import io.ktor.application.*

fun Application.configureGraphQL() {
    install(GraphQL) {
        val authService = AuthService()
        val shopService = ShopService()
        val reviewService = ReviewService()
        val profileService = ProfileService()
        val orderService = OrderService()
        val serviceService = ServiceService()

        playground = true
        context { call ->
            authService.verifyToken(call)?.let { +it }
            +log
            +call
        }

        schema {
            authSchema(authService)
            shopSchema(shopService)
            reviewSchema(reviewService)
            profileSchema(profileService)
            orderSchema(orderService)
            serviceSchema(serviceService)
        }
    }
}