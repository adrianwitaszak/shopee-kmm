package com.adwi.shoppe.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.adwi.shoppe.repository.ShopRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop
import kotlinx.coroutines.flow.Flow

@ApolloExperimental
class ShopListViewModel constructor(private val repository: ShopRepository) : ViewModel() {

    val shops: Flow<PagingData<Shop>> =
        Pager(PagingConfig(pageSize = 10)) { ShopDataSource(repository) }.flow
}