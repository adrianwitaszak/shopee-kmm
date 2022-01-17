package com.adwi.ktor.services

import com.adwi.ktor.models.Profile
import com.adwi.ktor.repository.ShopRepository
import com.adwi.ktor.repository.UserRepository
import com.mongodb.client.MongoClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileService : KoinComponent {
    private val client: MongoClient by inject()
    private val repo: UserRepository = UserRepository(client)
    private val shopRepo: ShopRepository = ShopRepository(client)

    fun getProfile(userId: String): Profile {
        val user = repo.getById(userId)
        val shops = shopRepo.getShopsByUserId(userId)
        return Profile(user, shops)
    }
}