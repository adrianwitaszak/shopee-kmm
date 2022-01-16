package com.shoppe.models

import models.PagingInfo
import java.time.LocalDateTime

data class Order(
    override val id: String,
    val userId: String,
    val shopId: String,
    val serviceId: String,
    var quantity: Double,
    var purchasedAt: String = LocalDateTime.now().toString(),
    var scheduledAt: String,
    var paid: Boolean,
) : Model

data class OrderInput(
    var quantity: Double,
    var scheduledAt: String,
    var paid: Boolean,
)

data class OrderPage(
    val results: List<Order>,
    val info: PagingInfo,
)
