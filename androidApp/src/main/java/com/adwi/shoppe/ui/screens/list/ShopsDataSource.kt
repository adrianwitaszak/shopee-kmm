package com.adwi.shoppe.ui.screens.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adwi.shoppe.repository.ShopRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop

@ApolloExperimental
class ShopDataSource  constructor(private val repository: ShopRepository): PagingSource<Int, Shop>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Shop> {
        val pageNumber = params.key ?: 0

        return try {
            val response = repository.getShops(page = pageNumber, size = 10)
            val shops = response?.results
            val prevKey = response?.info?.prev
            val nextKey = response?.info?.next
            LoadResult.Page(data = shops ?: emptyList(), prevKey = prevKey, nextKey = nextKey)
        } catch (err: Exception) {
            LoadResult.Error(err)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Shop>): Int? { return 1 }
}