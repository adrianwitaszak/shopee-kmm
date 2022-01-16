package com.shoppe.repository

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.shoppe.models.Service
import com.shoppe.models.ServicePage
import com.shoppe.util.Constants.DATABASE_NAME
import com.shoppe.util.Constants.SERVICE_COLLECTION
import models.PagingInfo
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection

class ServiceRepository(client: MongoClient) : RepositoryInterface<Service> {

    override lateinit var col: MongoCollection<Service>

    init {
        val database = client.getDatabase(DATABASE_NAME)
        col = database.getCollection<Service>(SERVICE_COLLECTION)
    }

    fun getServicesByShopId(shopId: String): List<Service> {
        return try {
            val res = col.find(Service::shopId eq shopId)
                ?: throw Exception("No service with that shop ID exists")
            res.asIterable().map { it }
        } catch (t: Throwable) {
            throw Exception("Cannot find service")
        }
    }

    fun getServicePageByShopId(
        shopId: String,
        page: Int,
        size: Int,
    ): ServicePage {
        try {
            val skips = page * size
            val res = col.find(Service::shopId eq shopId).skip(skips).limit(size)
                ?: throw Exception("No services exist")
            val results = res.asIterable().toList()
            val total = col.countDocuments(Service::shopId eq shopId)
            val totalPages = (total / size) + 1
            val next = if (results.isNotEmpty()) page + 1 else null
            val prev = if (page > 0) page - 1 else null
            val info = PagingInfo(total.toInt(), totalPages.toInt(), next, prev)
            return ServicePage(results, info)
        } catch (t: Throwable) {
            throw Exception("Cannot get services page")
        }
    }
}