package com.adwi.shoppe.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.adwi.shoppe.repository.ShopRepository
import comadwishoppe.Shop
import kotlinx.coroutines.flow.Flow

class FavoriteListViewModel constructor(repository: ShopRepository): ViewModel() {
    val shops: Flow<PagingData<Shop>> = Pager(PagingConfig(pageSize = 100)) {
        FavoriteDataSource(repository)
    }.flow
}