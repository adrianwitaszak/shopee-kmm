package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.android.GetProfileQuery
import com.adwi.shoppe.android.SignInMutation
import com.adwi.shoppe.android.SignUpMutation
import com.adwi.shoppe.android.type.UserInput
import com.adwi.shoppe.cache.mapper.toShop
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop
import comadwishoppe.UserState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Suppress("TooGenericExceptionThrown")
@ExperimentalCoroutinesApi
@ApolloExperimental
class AuthRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    suspend fun signIn(userInput: UserInput): String {
        val response = apolloClient.mutation(SignInMutation(userInput)).execute()
        response.data?.signIn?.let { data ->
            data.user.also {
                database.saveUserState(data.user.id, data.token)
            }
            return data.token
        }
        throw Exception("Could not sign in")
    }

    suspend fun signUp(userInput: UserInput): String {
        val response = apolloClient.mutation(SignUpMutation(userInput)).execute()
        response.data?.signUp?.let { data ->
            data.user.also {
                database.saveUserState(data.user.id, data.token)
            }
            return data.token
        }
        throw Exception("Could not sign up")
    }

    suspend fun getProfileShops(): List<Shop> {
        val response = apolloClient.query(GetProfileQuery()).execute()
        return response.data?.getProfile?.shops?.map { it.toShop() } ?: emptyList()
    }

    fun getUserState(): Flow<UserState?> = flowOf(database.getUserState())

    fun deleteUserState() {
        return database.deleteUserState()
    }
}