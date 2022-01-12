package com.adwi.shoppe.cache.mapper

import com.adwi.shoppe.android.*
import comadwishoppe.ShopOrder

//fun ShopQuery.Review.toShopOrder() = ShopOrder(
//    id = id,
//    userId = userId,
//    shopId = shopId,
//    serviceId = serviceId,
//    quantity = quantity,
//    purchasedAt = purchasedAt.toString().toDouble(),
//    scheduledAt = scheduledAt.toString().toDouble(),
//    paid = paid
//)

fun GetOrderQuery.GetOrder.toShopOrder() = ShopOrder(
    id = id,
    userId = userId,
    shopId = shopId,
    serviceId = serviceId,
    quantity = quantity,
    purchasedAt = purchasedAt.toString().toDouble(),
    scheduledAt = scheduledAt.toString().toDouble(),
    paid = paid.toString().toLong()
)

fun OrdersPagedByShopIdQuery.Result.toShopOrder() = ShopOrder(
    id = id,
    userId = userId,
    shopId = shopId,
    serviceId = serviceId,
    quantity = quantity,
    purchasedAt = purchasedAt.toString().toDouble(),
    scheduledAt = scheduledAt.toString().toDouble(),
    paid = paid.toString().toLong()
)

fun CreateOrderMutation.CreateOrder.toShopOrder() = ShopOrder(
    id = id,
    userId = userId,
    shopId = shopId,
    serviceId = serviceId,
    quantity = quantity,
    purchasedAt = purchasedAt.toString().toDouble(),
    scheduledAt = scheduledAt.toString().toDouble(),
    paid = paid.toString().toLong()
)

fun UpdateOrderMutation.UpdateOrder.toShopOrder() = ShopOrder(
    id = id,
    userId = userId,
    shopId = shopId,
    serviceId = serviceId,
    quantity = quantity,
    purchasedAt = purchasedAt.toString().toDouble(),
    scheduledAt = scheduledAt.toString().toDouble(),
    paid = paid.toString().toLong()
)

fun GetShopByIdQuery.Order.toShopOrder() = ShopOrder(
    id = id,
    userId = userId,
    shopId = shopId,
    serviceId = serviceId,
    quantity = quantity,
    purchasedAt = purchasedAt.toString().toDouble(),
    scheduledAt = scheduledAt.toString().toDouble(),
    paid = paid.toString().toLong()
)