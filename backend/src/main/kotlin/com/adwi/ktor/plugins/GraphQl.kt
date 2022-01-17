package com.adwi.ktor.plugins

import com.adwi.ktor.graphql.authSchema
import com.adwi.ktor.graphql.orderSchema
import com.adwi.ktor.graphql.profileSchema
import com.adwi.ktor.graphql.reviewSchema
import com.adwi.ktor.graphql.serviceSchema
import com.adwi.ktor.graphql.shopSchema
import com.adwi.ktor.services.AuthService
import com.adwi.ktor.services.OrderService
import com.adwi.ktor.services.ProfileService
import com.adwi.ktor.services.ReviewService
import com.adwi.ktor.services.ServiceService
import com.adwi.ktor.services.ShopService
import com.apurebase.kgraphql.GraphQL
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