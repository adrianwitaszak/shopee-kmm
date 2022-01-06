package com.adwi.shoppe.ui.screens.review

import androidx.lifecycle.ViewModel
import com.adwi.shoppe.android.type.ReviewInput
import com.adwi.shoppe.repository.ReviewRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Review

@ApolloExperimental
class ReviewFormViewModel constructor(private val repository: ReviewRepository) : ViewModel() {
    suspend fun getReview(reviewId: String): Review? {
        return repository.getReview(reviewId)
    }

    suspend fun createReview(shopId: String, review: Review): Review? {
        return repository.newReview(shopId, ReviewInput(text = review.text, rating = review.rating.toInt()))
    }

    suspend fun updateReview(review: Review): Review? {
        return repository.updateReview(review.id, ReviewInput(text = review.text, rating = review.rating.toInt()))
    }

    suspend fun deleteReview(reviewId: String): Boolean? {
        return repository.deleteReview(reviewId)
    }
}