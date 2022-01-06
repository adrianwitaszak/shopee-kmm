package com.adwi.shoppe.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.shoppe.cache.mapper.ShopDetail
import com.adwi.shoppe.repository.AuthRepository
import com.adwi.shoppe.repository.ShopRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop
import comadwishoppe.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ApolloExperimental
class ShopDetailViewModel constructor(
    private val repository: ShopRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {

    val userState: MutableStateFlow<UserState?> = MutableStateFlow(null)

    suspend fun getShop(shopId: String): ShopDetail? {
        return repository.getShop(shopId)
    }

    fun saveFavorite(shop: Shop) {
        return repository.saveFavorite(shop)
    }

    fun updateFavorite(shop: Shop) {
        return repository.updateFavorite(shop)
    }

    fun removeFavorite(shopId: String) {
        return repository.removeFavorite(shopId)
    }

    fun getFavorite(shopId: String): Shop? {
        return repository.getFavoriteShop(shopId)
    }

    fun getUserState() {
        viewModelScope.launch {
            authRepository.getUserState().collect {
                userState.value = it
            }
        }
    }
}