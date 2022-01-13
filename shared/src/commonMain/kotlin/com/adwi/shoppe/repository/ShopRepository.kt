package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.android.*
import com.adwi.shoppe.android.type.ShopInput
import com.adwi.shoppe.cache.mapper.*
import com.apollographql.apollo3.api.Optional
import comadwishoppe.Shop

class ShopRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    suspend fun getShops(page: Optional<Int>, size: Optional<Int>): Shops? {
        val response = apolloClient.query(ShopsPagedByUserIdQuery(page, size)).execute()
        return response.data?.shopsPagedByUserId?.toShops()
    }

    suspend fun getShop(shopId: String): ShopDetail? {
        val response = apolloClient.query(GetShopByIdQuery(shopId)).execute()
        return response.data?.getShopById?.toShopDetail()
    }

    suspend fun createShop(shopInput: ShopInput): Shop? {
        val response = apolloClient.mutation(CreateShopMutation(shopInput)).execute()
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