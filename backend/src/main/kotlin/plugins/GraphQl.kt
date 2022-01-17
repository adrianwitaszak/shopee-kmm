package com.shoppe.plugins

import com.apurebase.kgraphql.GraphQL
import com.shoppe.graphql.authSchema
import com.shoppe.graphql.orderSchema
import com.shoppe.graphql.profileSchema
import com.shoppe.graphql.reviewSchema
import com.shoppe.graphql.serviceSchema
import com.shoppe.graphql.shopSchema
import com.shoppe.services.AuthService
import com.shoppe.services.OrderService
import com.shoppe.services.ProfileService
import com.shoppe.services.ReviewService
import com.shoppe.services.ServiceService
import com.shoppe.services.ShopService
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.application.log

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