package com.adwi.shoppe.ui.screens.profile

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adwi.shoppe.repository.AuthRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop

@ApolloExperimental
class ProfileDataSource constructor(private val repository: AuthRepository) : PagingSource<Int, Shop>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Shop> {
        return try {
            val favorites = repository.getProfileShops()
            LoadResult.Page(data = favorites, prevKey = null, nextKey = null)
        } catch (err: Exception) {
            LoadResult.Error(err)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Shop>): Int? {
        return 1
    }
}