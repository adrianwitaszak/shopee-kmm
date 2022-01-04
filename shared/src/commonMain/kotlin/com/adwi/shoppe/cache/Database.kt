package com.adwi.shoppe.cache

import com.adwi.shoppe.android.Shoppe
import comadwishoppe.Shop
import comadwishoppe.UserState

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Shoppe(databaseDriverFactory.createDriver())
    private val dbQuery = database.shoppeQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllShops()
        }
    }

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

    private fun insertUserState(userId: String, token: String) {
        dbQuery.insertUserState(userId, token)
    }

    private fun removeUserState() {
        dbQuery.removeUserState()
    }
}