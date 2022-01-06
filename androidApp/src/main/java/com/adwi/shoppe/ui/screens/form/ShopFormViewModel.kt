package com.adwi.shoppe.ui.screens.form

import androidx.lifecycle.ViewModel
import com.adwi.shoppe.android.type.ShopInput
import com.adwi.shoppe.cache.mapper.ShopDetail
import com.adwi.shoppe.repository.ShopRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop

@ApolloExperimental
class ShopFormViewModel constructor(private val repository: ShopRepository) : ViewModel() {
    suspend fun getShop(shopId: String): ShopDetail? {
        return repository.getShop(shopId)
    }

    suspend fun createShop(shop: Shop): Shop? {
        return repository.newShop(ShopInput(name = shop.name,
            description = shop.description,
            imageUrl = shop.imageUrl))
    }

    suspend fun updateShop(shop: Shop): Shop? {
        return repository.updateShop(shop.id,
            ShopInput(name = shop.name, description = shop.description, imageUrl = shop.imageUrl))
    }

    suspend fun deleteShop(shopId: String): Boolean? {
        return repository.deleteShop(shopId)
    }
}