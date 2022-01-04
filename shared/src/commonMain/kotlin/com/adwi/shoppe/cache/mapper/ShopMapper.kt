package com.adwi.shoppe.cache.mapper

import com.adwi.shoppe.android.GetProfileQuery
import com.adwi.shoppe.android.GetShopQuery
import com.adwi.shoppe.android.GetShopsQuery
import com.adwi.shoppe.android.NewShopMutation
import com.adwi.shoppe.android.UpdateShopMutation
import comadwishoppe.Review
import comadwishoppe.Shop

data class Shops(val results: List<Shop>, val info: GetShopsQuery.Info?)
data class ShopDetail(val shop: Shop, val reviews: List<Review>)

fun GetShopsQuery.Result.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun GetShopsQuery.Shops.toShops() = Shops(
    results = results.map {
        it.toShop()
    },
    info = info
)

fun GetShopQuery.Shop.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun GetShopQuery.Shop.toShopDetail() =
    ShopDetail(
        shop = this.toShop(),
        reviews = emptyList() // reviews.map { it.toReview() }
    )

fun GetProfileQuery.Shop.toShop() = Shop(
    id = id,
    userId = userId,
    name = name,
    description = description,
    imageUrl = imageUrl
)

fun NewShopMutation.CreateShop.toShop() = Shop(
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