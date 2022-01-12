package com.adwi.shoppe.cache.mapper

import com.adwi.shoppe.android.*
import comadwishoppe.Review
import comadwishoppe.Service
import comadwishoppe.Shop
import comadwishoppe.ShopOrder

data class Shops(val results: List<Shop>, val info: ShopsPagedByUserIdQuery.Info?)
data class ShopDetail(
    val shop: Shop,
    val services: List<Service>,
    val reviews: List<Review>,
    val orders: List<ShopOrder>,
)

fun ShopsPagedByUserIdQuery.Result.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun ShopsPagedByUserIdQuery.ShopsPagedByUserId.toShops() = Shops(
    results = results.map { it.toShop() },
    info = info
)

fun GetShopByIdQuery.GetShopById.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun GetShopByIdQuery.GetShopById.toShopDetail() =
    ShopDetail(
        shop = this.toShop(),
        services = services.map { it.toService() },
        reviews = reviews.map { it.toReview() },
        orders = orders.map { it.toShopOrder() }
    )

fun GetProfileQuery.Shop.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun CreateShopMutation.CreateShop.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun UpdateShopMutation.UpdateShop.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)