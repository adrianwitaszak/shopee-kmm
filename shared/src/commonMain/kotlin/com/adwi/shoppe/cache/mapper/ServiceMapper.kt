package com.adwi.shoppe.cache.mapper

import com.adwi.shoppe.android.*
import comadwishoppe.Service


fun GetServiceQuery.GetService.toService() = Service(
    id = id,
    userId = userId,
    shopId = shopId,
    name = name,
    description = description,
    price = price,
    duration = duration.toLong()
)

fun ServicePagedByShopIdQuery.Result.toService() = Service(
    id = id,
    userId = userId,
    shopId = shopId,
    name = name,
    description = description,
    price = price,
    duration = duration.toLong()
)

fun GetShopByIdQuery.Service.toService() = Service(
    id = id,
    userId = userId,
    shopId = shopId,
    name = name,
    description = description,
    price = price,
    duration = duration.toLong()
)

fun CreateServiceMutation.CreateService.toService() = Service(
    id = id,
    userId = userId,
    shopId = shopId,
    name = name,
    description = description,
    price = price,
    duration = duration.toLong()
)

fun UpdateServiceMutation.UpdateService.toService() = Service(
    id = id,
    userId = userId,
    shopId = shopId,
    name = name,
    description = description,
    price = price,
    duration = duration.toLong()
)