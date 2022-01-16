package com.shoppe.services

import com.mongodb.client.MongoClient
import com.shoppe.models.Review
import com.shoppe.models.ReviewInput
import com.shoppe.models.ReviewPage
import com.shoppe.repository.ReviewRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class ReviewService : KoinComponent {
    private val client: MongoClient by inject()
    private val repo = ReviewRepository(client)

    fun getReview(id: String): Review {
        return repo.getById(id)
    }

    fun getReviewsPageByShopId(shopId: String, page: Int, size: Int): ReviewPage {
        return repo.getReviewsPageByShopId(shopId, page, size)
    }

    fun createReview(userId: String, shopId: String, reviewInput: ReviewInput): Review {
        val uid = UUID.randomUUID().toString()
        val review = Review(
            id = uid,
            userId = userId,
            shopId = shopId,
            text = reviewInput.text,
            rating = reviewInput.rating
        )
        return repo.add(review)
    }

    fun updateReview(userId: String, reviewId: String, reviewInput: ReviewInput): Review {
        val review = repo.getById(reviewId)
        if (review.userId == userId) {
            val updates = Review(
                id = reviewId,
                shopId = review.shopId,
                userId = userId,
                text = reviewInput.text,
                rating = reviewInput.rating
            )
            return repo.update(updates)
        }
        error("Cannot update review")
    }

    fun deleteReview(userId: String, reviewId: String): Boolean {
        val review = repo.getById(reviewId)
        if (review.userId == userId) {
            return repo.delete(reviewId)
        }
        error("Cannot delete review")
    }
}