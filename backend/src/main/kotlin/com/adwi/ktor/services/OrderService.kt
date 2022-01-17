package com.adwi.ktor.services

import com.adwi.ktor.models.Order
import com.adwi.ktor.models.OrderInput
import com.adwi.ktor.models.OrderPage
import com.adwi.ktor.repository.OrderRepository
import com.mongodb.client.MongoClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class OrderService : KoinComponent {
    private val client: MongoClient by inject()
    private val repo = OrderRepository(client)

    fun getOrder(id: String): Order {
        return repo.getById(id)
    }

    fun getShopsPageByShopId(shopId: String, page: Int, size: Int): OrderPage {
        return repo.getOrdersPageByShopId(shopId, page, size)
    }

    fun getShopsPageByUserId(userId: String, page: Int, size: Int): OrderPage {
        return repo.getOrdersPageByUserId(userId, page, size)
    }

    fun createOrder(
        userId: String,
        shopId: String,
        serviceId: String,
        orderInput: OrderInput,
    ): Order {
        val uid = UUID.randomUUID().toString()
        val review = Order(
            id = uid,
            userId = userId,
            shopId = shopId,
            serviceId = serviceId,
            quantity = orderInput.quantity,
            scheduledAt = orderInput.scheduledAt,
            paid = orderInput.paid
        )
        return repo.add(review)
    }

    fun updateOrder(
        userId: String,
        orderId: String,
        shopId: String,
        serviceId: String,
        orderInput: OrderInput,
    ): Order {
        val review = repo.getById(orderId)
        if (review.userId == userId) {
            val updates = Order(
                id = orderId,
                userId = userId,
                shopId = shopId,
                serviceId = serviceId,
                quantity = orderInput.quantity,
                scheduledAt = orderInput.scheduledAt,
                paid = orderInput.paid
            )
            return repo.update(updates)
        }
        error("Cannot update order")
    }

    fun deleteOrder(userId: String, orderId: String): Boolean {
        val review = repo.getById(orderId)
        if (review.userId == userId) {
            return repo.delete(orderId)
        }
        error("Cannot delete order")
    }
}