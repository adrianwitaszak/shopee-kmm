package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.android.DeleteReviewMutation
import com.adwi.shoppe.android.GetReviewQuery
import com.adwi.shoppe.android.NewReviewMutation
import com.adwi.shoppe.android.UpdateReviewMutation
import com.adwi.shoppe.android.type.ReviewInput
import com.adwi.shoppe.cache.mapper.toReview
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Review
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ApolloExperimental
class ReviewRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {
    suspend fun getReview(reviewId: String): Review? {
        val response = apolloClient.query(GetReviewQuery(reviewId)).execute()
        return response.data?.getReview?.toReview()
    }

    suspend fun newReview(shopId: String, reviewInput: ReviewInput): Review? {
        val response = apolloClient.mutation(NewReviewMutation(shopId, reviewInput)).execute()
        return response.data?.createReview?.toReview()
    }

    suspend fun updateReview(reviewId: String, reviewInput: ReviewInput): Review? {
        val response = apolloClient.mutation(UpdateReviewMutation(reviewId, reviewInput)).execute()
        return response.data?.updateReview?.toReview()
    }

    suspend fun deleteReview(reviewId: String): Boolean? {
        val response = apolloClient.mutation(DeleteReviewMutation(reviewId)).execute()
        return response.data?.deleteReview
    }
}