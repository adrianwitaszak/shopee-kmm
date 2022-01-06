package com.adwi.shoppe.ui.screens.favorites

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adwi.shoppe.repository.ShopRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop

@ApolloExperimental
class FavoriteDataSource  constructor(private val repository: ShopRepository): PagingSource<Int, Shop>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Shop> {
        val favorites = repository.getFavoriteShops()
        return LoadResult.Page(data = favorites, prevKey = null, nextKey = null)
    }

    override fun getRefreshKey(state: PagingState<Int, Shop>): Int? {
        return 1
    }
}