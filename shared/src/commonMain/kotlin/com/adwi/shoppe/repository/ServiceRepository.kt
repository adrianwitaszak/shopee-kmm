package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.android.CreateServiceMutation
import com.adwi.shoppe.android.DeleteServiceMutation
import com.adwi.shoppe.android.GetServiceQuery
import com.adwi.shoppe.android.UpdateServiceMutation
import com.adwi.shoppe.android.type.ServiceInput
import com.adwi.shoppe.cache.mapper.toService
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Service
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ApolloExperimental
class ServiceRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    suspend fun getService(serviceId: String): Service? {
        val response = apolloClient.query(GetServiceQuery(serviceId)).execute()
        return response.data?.getService?.toService()
    }

    suspend fun createService(shopId: String, serviceInput: ServiceInput): Service? {
        val response = apolloClient.mutation(CreateServiceMutation(shopId, serviceInput)).execute()
        return response.data?.createService?.toService()
    }

    suspend fun updateService(
        serviceId: String,
        shopId: String,
        serviceInput: ServiceInput,
    ): Service? {
        val response =
            apolloClient.mutation(UpdateServiceMutation(serviceId, shopId, serviceInput)).execute()
        return response.data?.updateService?.toService()
    }

    suspend fun deleteService(serviceId: String): Boolean? {
        val response = apolloClient.mutation(DeleteServiceMutation(serviceId)).execute()
        return response.data?.deleteService
    }
}