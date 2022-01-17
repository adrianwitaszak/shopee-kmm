package com.adwi.ktor.repository

import com.adwi.ktor.models.User
import com.adwi.ktor.util.Constants.DATABASE_NAME
import com.adwi.ktor.util.Constants.USER_COLLECTION
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class UserRepository(client: MongoClient) : RepositoryInterface<User> {

    override lateinit var col: MongoCollection<User>

    init {
        val database = client.getDatabase(DATABASE_NAME)
        col = database.getCollection<User>(USER_COLLECTION)
    }

    fun getUserByEmail(email: String? = null): User? {
        return try {
            col.findOne(
                User::email eq email,
            )
        } catch (t: Throwable) {
            throw Exception("Cannot get user with that email")
        }
    }
}