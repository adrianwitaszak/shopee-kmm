package com.adwi.shoppe.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.adwi.shoppe.repository.AuthRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop
import kotlinx.coroutines.flow.Flow

@ApolloExperimental
class ProfileViewModel constructor(private val repository: AuthRepository) : ViewModel() {
    val shops: Flow<PagingData<Shop>> = Pager(PagingConfig(pageSize = 100)) {
        ProfileDataSource(repository)
    }.flow
}