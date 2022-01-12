package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.android.CreateOrderMutation
import com.adwi.shoppe.android.DeleteOrderMutation
import com.adwi.shoppe.android.GetOrderQuery
import com.adwi.shoppe.android.UpdateOrderMutation
import com.adwi.shoppe.android.type.OrderInput
import com.adwi.shoppe.cache.mapper.toShopOrder
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.ShopOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ApolloExperimental
class ShopOrderRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    suspend fun getShopOrder(shopOrderId: String): ShopOrder? {
        val response = apolloClient.query(GetOrderQuery(shopOrderId)).execute()
        return response.data?.getOrder?.toShopOrder()
    }

    suspend fun createShopOrder(
        shopId: String,
        serviceId: String,
        orderInput: OrderInput,
    ): ShopOrder? {
        val response =
            apolloClient.mutation(CreateOrderMutation(shopId, serviceId, orderInput)).execute()
        return response.data?.createOrder?.toShopOrder()
    }

    suspend fun updateShopOrder(
        shopOrderId: String,
        shopId: String,
        serviceId: String,
        orderInput: OrderInput,
    ): ShopOrder? {
        val response =
            apolloClient.mutation(UpdateOrderMutation(shopOrderId, shopId, serviceId, orderInput))
                .execute()
        return response.data?.updateOrder?.toShopOrder()
    }

    suspend fun deleteShopOrder(shopOrderId: String): Boolean? {
        val response = apolloClient.mutation(DeleteOrderMutation(shopOrderId)).execute()
        return response.data?.deleteOrder
    }
}