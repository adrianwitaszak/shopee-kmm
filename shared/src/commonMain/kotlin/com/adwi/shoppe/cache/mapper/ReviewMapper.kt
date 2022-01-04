package com.adwi.shoppe.cache.mapper

import com.adwi.shoppe.android.GetReviewQuery
import com.adwi.shoppe.android.GetShopQuery
import com.adwi.shoppe.android.NewReviewMutation
import com.adwi.shoppe.android.UpdateReviewMutation
import comadwishoppe.Review

fun GetShopQuery.Review.toReview() = Review(
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

fun NewReviewMutation.CreateReview.toReview() = Review(
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