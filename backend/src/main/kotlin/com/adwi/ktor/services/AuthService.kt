package com.adwi.ktor.services

import at.favre.lib.crypto.bcrypt.BCrypt
import com.adwi.ktor.models.User
import com.adwi.ktor.models.UserInput
import com.adwi.ktor.models.UserResponse
import com.adwi.ktor.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.mongodb.client.MongoClient
import io.ktor.application.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.nio.charset.StandardCharsets
import java.util.*

class AuthService : KoinComponent {
    private val client: MongoClient by inject()
    private val repo: UserRepository = UserRepository(client)
    private val secret: String = "secret"
    private val algorithm: Algorithm = Algorithm.HMAC256(secret)
    private val verifier: JWTVerifier = JWT.require(algorithm).build()

    /**
     * @param email the email of the user signing up
     * @param password the password of the user signing up
     *
     * @return UserResponse object with no null values if sign in successful
     * UserResponse object with two null values if unsuccessful
     */

    fun signIn(userInput: UserInput): UserResponse? {
        val user = repo.getUserByEmail(userInput.email) ?: error("No such user by that email")
        // hash incoming password and compare it to saved
        if (!BCrypt.verifyer()
                .verify(
                    userInput.password.toByteArray(Charsets.UTF_8),
                    user.hashedPass
                ).verified
        ) {
            error("Password incorrect")
        }

        val token = signAccessToken(user.id)
        return UserResponse(token, user)
    }

    /**
     * @param email the email of the user signing up
     * @param password the password of the user signing up
     *
     * @return UserResponse object with no null values if sign up successful
     * UserResponse object with two null values if unsuccessful
     */

    fun signUp(userInput: UserInput): UserResponse? {
        val hashedPassword =
            BCrypt.withDefaults().hash(10, userInput.password.toByteArray(StandardCharsets.UTF_8))
        val id = UUID.randomUUID().toString()
        val emailUser = repo.getUserByEmail(userInput.email)
        if (emailUser != null) {
            error("Email already in use")
        }
        val newUser = repo.add(
            User(
                id = id,
                email = userInput.email,
                hashedPass = hashedPassword,
            )
        )
        val token = signAccessToken(newUser.id)
        return UserResponse(token, newUser)
    }

    /**
     * @param id the user Id associated with the access token intended to be generated
     * @return a valid and fresh access token
     */
    private fun signAccessToken(id: String): String {
        return JWT.create()
            .withIssuer(id)
            .withClaim("userId", id)
            .sign(algorithm)
    }

    fun verifyToken(call: ApplicationCall): User? {
        return try {
            val authHeader = call.request.headers["Authorization"] ?: ""
            val token = authHeader.split("Bearer ").last()
            val accessToken = verifier.verify(JWT.decode(token))
            val userId = accessToken.getClaim("userId").asString()
            return User(id = userId, email = "", hashedPass = ByteArray(0))
        } catch (e: Exception) {
            null
        }
    }

}