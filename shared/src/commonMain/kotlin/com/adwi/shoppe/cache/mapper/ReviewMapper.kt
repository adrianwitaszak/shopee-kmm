package com.adwi.shoppe.cache.mapper

import com.adwi.shoppe.android.*
import comadwishoppe.Review

fun GetShopByIdQuery.Review.toReview() = Review(
    id = id,
    userId = userId,
    shopId = shopId,
    text = text,
    rating = rating.toLong()
)

fun GetReviewQuery.GetReview.toReview() = Review(
    id = id,
    userId = userId,
    shopId = shopId,
    text = text,
    rating = rating.toLong()
)

fun ReviewsPagedByShopIdQuery.Result.toReview() = Review(
    id = id,
    userId = userId,
    shopId = shopId,
    text = text,
    rating = rating.toLong()
)

fun CreateReviewMutation.CreateReview.toReview() = Review(
    id = id,
    userId = userId,
    shopId = shopId,
    text = text,
    rating = rating.toLong()
)

fun UpdateReviewMutation.UpdateReview.toReview() = Review(
    id = id,
    userId = userId,
    shopId = shopId,
    text = text,
    rating = rating.toLong()
)