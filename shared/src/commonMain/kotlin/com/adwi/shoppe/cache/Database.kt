package com.adwi.shoppe.cache

import com.adwi.shoppe.android.Shoppe
import comadwishoppe.*

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Shoppe(databaseDriverFactory.createDriver())
    private val dbQuery = database.shoppeQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllShops()
        }
    }

    // Shop
    internal fun getShops(): List<Shop> {
        return dbQuery.selectAllShops().executeAsList()
    }

    internal fun getShopById(shopId: String): Shop? {
        return dbQuery.selectShopById(shopId).executeAsOneOrNull()
    }

    internal fun saveShop(shop: Shop) {
        dbQuery.transaction {
            insertShop(shop)
        }
    }

    internal fun updateShop(shop: Shop) {
        dbQuery.transaction {
            updateShopById(shop)
        }
    }

    internal fun deleteShop(shopId: String) {
        dbQuery.transaction {
            removeShop(shopId)
        }
    }

    private fun removeShop(shopId: String) {
        dbQuery.removeShopById(shopId)
    }

    private fun insertShop(shop: Shop) {
        dbQuery.insertShop(
            shop.id,
            shop.userId,
            shop.name,
            shop.description,
            shop.imageUrl
        )
    }

    private fun updateShopById(shop: Shop) {
        dbQuery.updateShopById(
            name = shop.name,
            description = shop.description,
            imageUrl = shop.imageUrl,
            id = shop.id
        )
    }

    // Review
    internal fun getReviews(): List<Review> {
        return dbQuery.selectAllReviews().executeAsList()
    }

    internal fun getReviewById(reviewId: String): Review? {
        return dbQuery.selectReviewById(reviewId).executeAsOneOrNull()
    }

    internal fun saveReview(review: Review) {
        dbQuery.transaction {
            insertReview(review)
        }
    }

    internal fun updateReview(review: Review) {
        dbQuery.transaction {
            updateReviewById(review)
        }
    }

    internal fun deleteReview(reviewId: String) {
        dbQuery.transaction {
            removeReview(reviewId)
        }
    }

    private fun removeReview(reviewId: String) {
        dbQuery.removeReviewById(reviewId)
    }

    private fun insertReview(review: Review) {
        dbQuery.insertReview(
            review.id,
            review.userId,
            review.shopId,
            review.text,
            review.rating
        )
    }

    private fun updateReviewById(review: Review) {
        dbQuery.updateReviewById(
            text = review.text,
            rating = review.rating,
            id = review.id
        )
    }

    // Service
    internal fun getServices(): List<Service> {
        return dbQuery.selectAllServices().executeAsList()
    }

    internal fun getServiceById(serviceId: String): Service? {
        return dbQuery.selectServiceById(serviceId).executeAsOneOrNull()
    }

    internal fun saveService(service: Service) {
        dbQuery.transaction {
            insertService(service)
        }
    }

    internal fun updateService(service: Service) {
        dbQuery.transaction {
            updateServiceById(service)
        }
    }

    internal fun deleteService(serviceId: String) {
        dbQuery.transaction {
            removeService(serviceId)
        }
    }

    private fun removeService(serviceId: String) {
        dbQuery.removeServiceById(serviceId)
    }

    private fun insertService(service: Service) {
        dbQuery.insertService(
            service.id,
            service.shopId,
            service.name,
            service.description,
            service.price,
            service.duration,
        )
    }

    private fun updateServiceById(service: Service) {
        dbQuery.updateServiceById(
            id = service.id,
            name = service.name,
            description = service.description,
            duration = service.duration,
            price = service.price,
        )
    }

    // ShopOrder
    internal fun getShopOrders(): List<ShopOrder> {
        return dbQuery.selectAllShopOrders().executeAsList()
    }

    internal fun getShopOrderById(shopOrderId: String): ShopOrder? {
        return dbQuery.selectShopOrderById(shopOrderId).executeAsOneOrNull()
    }

    internal fun saveShopOrder(shopOrder: ShopOrder) {
        dbQuery.transaction {
            insertShopOrder(shopOrder)
        }
    }

    internal fun updateService(shopOrder: ShopOrder) {
        dbQuery.transaction {
            updateShopOrderById(shopOrder)
        }
    }

    internal fun deleteShopOrder(shopOrderId: String) {
        dbQuery.transaction {
            removeShopOrder(shopOrderId)
        }
    }

    private fun removeShopOrder(shopOrderId: String) {
        dbQuery.removeShopOrderById(shopOrderId)
    }

    private fun insertShopOrder(shopOrder: ShopOrder) {
        dbQuery.insertShopOrder(
            shopOrder.id,
            shopOrder.userId,
            shopOrder.shopId,
            shopOrder.serviceId,
            shopOrder.quantity,
            shopOrder.purchasedAt,
            shopOrder.scheduledAt,
            shopOrder.paid,
        )
    }

    private fun updateShopOrderById(shopOrder: ShopOrder) {
        dbQuery.updateShopOrderById(
            id = shopOrder.id,
            quantity = shopOrder.quantity,
            purchasedAt = shopOrder.purchasedAt,
            scheduledAt = shopOrder.scheduledAt,
            paid = shopOrder.paid,
        )
    }

    // UserState
    internal fun getUserState(): UserState? {
        return dbQuery.selectUserState().executeAsOneOrNull()
    }

    internal fun saveUserState(userId: String, token: String) {
        dbQuery.transaction {
            insertUserState(userId, token)
        }
    }

    internal fun deleteUserState() {
        dbQuery.transaction {
            removeUserState()
        }
    }

    private fun insertUserState(userId: String, token: String) {
        dbQuery.insertUserState(userId, token)
    }

    private fun removeUserState() {
        dbQuery.removeUserState()
    }
}