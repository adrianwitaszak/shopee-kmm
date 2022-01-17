package com.shoppe.services

import com.mongodb.client.MongoClient
import com.shoppe.models.Service
import com.shoppe.models.ServiceInput
import com.shoppe.models.ServicePage
import com.shoppe.repository.ServiceRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class ServiceService : KoinComponent {
    private val client: MongoClient by inject()
    private val repo = ServiceRepository(client)

    fun getService(id: String): Service {
        return repo.getById(id)
    }

    fun getServicesPageByShopId(
        shopId: String,
        page: Int,
        size: Int,
    ): ServicePage {
        return repo.getServicePageByShopId(
            shopId,
            page,
            size)
    }

    fun createService(shopId: String, serviceInput: ServiceInput): Service {
        val uid = UUID.randomUUID().toString()
        val review = Service(
            id = uid,
            shopId = shopId,
            name = serviceInput.name,
            description = serviceInput.description,
            price = serviceInput.price,
            duration = serviceInput.duration,
        )
        return repo.add(review)
    }

    fun updateService(serviceId: String, shopId: String, serviceInput: ServiceInput): Service {
        val service = repo.getById(serviceId)
        val updates = Service(
            id = serviceId,
            shopId = shopId,
            name = serviceInput.name,
            description = serviceInput.description,
            price = serviceInput.price,
            duration = serviceInput.duration,
        )
        return repo.update(updates)
    }

    fun deleteService(serviceId: String): Boolean {
        return repo.delete(serviceId)
    }
}