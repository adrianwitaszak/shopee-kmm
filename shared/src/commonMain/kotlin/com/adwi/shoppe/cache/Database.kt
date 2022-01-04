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

    internal fun getShopById(dessertId: String): Shop? {
        return dbQuery.selectShopById(dessertId).executeAsOneOrNull()
    }

    internal fun saveShop(dessert: Shop) {
        dbQuery.transaction {
            insertShop(dessert)
        }
    }

    internal fun updateShop(dessert: Shop) {
        dbQuery.transaction {
            updateShopById(dessert)
        }
    }

    internal fun deleteShop(dessertId: String) {
        dbQuery.transaction {
            removeShop(dessertId)
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

    private fun removeShop(dessertId: String) {
        dbQuery.removeShopById(dessertId)
    }

    private fun insertShop(dessert: Shop) {
        dbQuery.insertShop(
            dessert.id,
            dessert.userId,
            dessert.name,
            dessert.description,
            dessert.imageUrl
        )
    }

    private fun updateShopById(dessert: Shop) {
        dbQuery.updateShopById(
            name = dessert.name,
            description = dessert.description,
            imageUrl = dessert.imageUrl,
            id = dessert.id
        )
    }

    private fun insertUserState(userId: String, token: String) {
        dbQuery.insertUserState(userId, token)
    }

    private fun removeUserState() {
        dbQuery.removeUserState()
    }
}