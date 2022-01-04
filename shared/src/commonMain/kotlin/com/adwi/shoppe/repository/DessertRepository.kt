package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.android.DeleteShopMutation
import com.adwi.shoppe.android.GetShopQuery
import com.adwi.shoppe.android.GetShopsQuery
import com.adwi.shoppe.android.NewShopMutation
import com.adwi.shoppe.android.UpdateShopMutation
import com.adwi.shoppe.android.type.ShopInput
import com.adwi.shoppe.cache.mapper.ShopDetail
import com.adwi.shoppe.cache.mapper.Shops
import com.adwi.shoppe.cache.mapper.toShop
import com.adwi.shoppe.cache.mapper.toShopDetail
import com.adwi.shoppe.cache.mapper.toShops
import comadwishoppe.Shop

class ShopRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {
    suspend fun getShops(page: Int, size: Int): Shops? {
        val response = apolloClient.query(GetShopsQuery(page, size)).execute()
        return response.data?.shops?.toShops()
    }

    suspend fun getShop(shopId: String): ShopDetail? {
        val response = apolloClient.query(GetShopQuery(shopId)).execute()
        return response.data?.shop?.toShopDetail()
    }

    suspend fun newShop(shopInput: ShopInput): Shop? {
        val response = apolloClient.mutation(NewShopMutation(shopInput)).execute()
        return response.data?.createShop?.toShop()
    }

    suspend fun updateShop(shopId: String, shopInput: ShopInput): Shop? {
        val response = apolloClient.mutation(UpdateShopMutation(shopId, shopInput)).execute()
        return response.data?.updateShop?.toShop()
    }

    suspend fun deleteShop(shopId: String): Boolean? {
        val response = apolloClient.mutation(DeleteShopMutation(shopId)).execute()
        return response.data?.deleteShop
    }

    fun saveFavorite(shop: Shop) {
        database.saveShop(shop)
    }

    fun removeFavorite(shopId: String) {
        database.deleteShop(shopId)
    }

    fun updateFavorite(shop: Shop) {
        database.updateShop(shop)
    }

    fun getFavoriteShop(shopId: String): Shop? {
        return database.getShopById(shopId)
    }

    fun getFavoriteShops(): List<Shop> {
        return database.getShops()
    }
}