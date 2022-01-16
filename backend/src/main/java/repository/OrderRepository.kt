package com.shoppe.repository

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.shoppe.models.Order
import com.shoppe.models.OrderPage
import com.shoppe.util.Constants.DATABASE_NAME
import com.shoppe.util.Constants.ORDER_COLLECTION
import models.PagingInfo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection

class OrderRepository(client: MongoClient) : RepositoryInterface<Order> {

    override lateinit var col: MongoCollection<Order>

    init {
        val database = client.getDatabase(DATABASE_NAME)
        col = database.getCollection<Order>(ORDER_COLLECTION)
    }

    fun getOrdersByShopId(shopId: String): List<Order> {
        return try {
            val res = col.find(Order::shopId eq shopId)
                ?: throw Exception("No order with that shop ID exists")
            res.asIterable().map { it }
        } catch (t: Throwable) {
            throw Exception("Cannot find order")
        }
    }

    fun getOrdersByUserId(userId: String): List<Order> {
        return try {
            val res = col.find(Order::userId eq userId)
                ?: throw Exception("No order with that user ID exists")
            res.asIterable().map { it }
        } catch (t: Throwable) {
            throw Exception("Cannot find order")
        }
    }

    fun getOrdersByServiceId(serviceId: String): List<Order> {
        return try {
            val res = col.find(Order::serviceId eq serviceId)
                ?: throw Exception("No order with that service ID exists")
            res.asIterable().map { it }
        } catch (t: Throwable) {
            throw Exception("Cannot find order")
        }
    }

    fun getOrdersPageByUserId(userId: String, page: Int, size: Int): OrderPage {
        try {
            val skips = page * size
            val res = col.find(Order::userId eq userId).skip(skips).limit(size)
                ?: throw Exception("No orders exist")
            val results = res.asIterable().map { it }
            val total = col.countDocuments(Order::userId eq userId)
            val totalPages = (total / size) + 1
            val next = if (results.isNotEmpty()) page + 1 else null
            val prev = if (page > 0) page - 1 else null
            val info = PagingInfo(total.toInt(), totalPages.toInt(), next, prev)
            return OrderPage(results, info)
        } catch (t: Throwable) {
            throw Exception("Cannot get reviews page")
        }
    }

    fun getOrdersPageByShopId(shopId: String, page: Int, size: Int): OrderPage {
        try {
            val skips = page * size
            val res = col.find(Order::shopId eq shopId).skip(skips).limit(size)
                ?: throw Exception("No orders exist")
            val results = res.asIterable().map { it }
            val total = col.countDocuments(Order::shopId eq shopId)
            val totalPages = (total / size) + 1
            val next = if (results.isNotEmpty()) page + 1 else null
            val prev = if (page > 0) page - 1 else null
            val info = PagingInfo(total.toInt(), totalPages.toInt(), next, prev)
            return OrderPage(results, info)
        } catch (t: Throwable) {
            throw Exception("Cannot get orders page")
        }
    }
}