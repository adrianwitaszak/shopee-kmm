package com.shoppe.services

import com.mongodb.client.MongoClient
import com.shoppe.models.Shop
import com.shoppe.models.ShopInput
import com.shoppe.models.ShopsPage
import com.shoppe.repository.ReviewRepository
import com.shoppe.repository.ServiceRepository
import com.shoppe.repository.ShopRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class ShopService : KoinComponent {

    private val client: MongoClient by inject()
    private val repo: ShopRepository = ShopRepository(client)
    private val reviewRepo: ReviewRepository = ReviewRepository(client)
    private val serviceRepo: ServiceRepository = ServiceRepository(client)

    fun getShop(id: String): Shop {
        val shop = repo.getById(id)
        shop.reviews = reviewRepo.getReviewsByShopId(id)
        shop.services = serviceRepo.getServicesByShopId(id)
        return shop
    }

    fun getShopsPage(userId: String, page: Int, size: Int): ShopsPage {
        return repo.getShopsPage(userId, page, size)
    }

    fun createShop(shopInput: ShopInput, userId: String): Shop {
        val uid = UUID.randomUUID().toString()
        val shop = Shop(
            id = uid,
            userId = userId,
            name = shopInput.name,
            description = shopInput.description,
            imageUrl = shopInput.imageUrl
        )
        return repo.add(shop)
    }

    fun updateShop(userId: String, shopId: String, shopInput: ShopInput): Shop {
        val shop = repo.getById(shopId)
        if (shop.userId == userId) {
            val updates = Shop(
                id = shopId,
                userId = userId,
                name = shopInput.name,
                description = shopInput.description,
                imageUrl = shopInput.imageUrl
            )
            return repo.update(updates)
        }
        error("Cannot update shop")
    }

    fun deleteShop(userId: String, shopId: String): Boolean {
        val shop = repo.getById(shopId)
        if (shop.userId == userId) {
            return repo.delete(shopId)
        }
        error("Cannot delete shop")
    }
}