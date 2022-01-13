package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.android.GetProfileQuery
import com.adwi.shoppe.android.SignInMutation
import com.adwi.shoppe.android.SignUpMutation
import com.adwi.shoppe.android.type.UserInput
import com.adwi.shoppe.cache.mapper.toShop
import com.adwi.shoppe.util.DataState
import com.adwi.shoppe.util.ProgressBarState
import com.adwi.shoppe.util.UIComponent
import com.apollographql.apollo3.annotations.ApolloExperimental
import comadwishoppe.Shop
import comadwishoppe.UserState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

@Suppress("TooGenericExceptionThrown")
@ExperimentalCoroutinesApi
@ApolloExperimental
class AuthRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    fun signIn(userInput: UserInput): Flow<DataState<String>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
        val response = apolloClient.mutation(SignInMutation(userInput)).execute()
        response.data?.signIn?.let { data ->
            data.user.also {
                database.saveUserState(data.user.id, data.token)
            }
            emit(DataState.Data(data.token))
        } ?: emit(DataState.Response(UIComponent.Log("Could not sign in")))
    }
        .catch { e -> emit(DataState.Response(e.logMessage())) }
        .onCompletion { cause ->
            cause?.let { println("SignIn flow completed with $cause") }
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }

    fun signUp(userInput: UserInput): Flow<DataState<String>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
        val response = apolloClient.mutation(SignUpMutation(userInput)).execute()
        response.data?.signUp?.let { data ->
            data.user.also {
                database.saveUserState(data.user.id, data.token)
            }
            emit(DataState.Data(data.token))
        } ?: emit(DataState.Response(UIComponent.Log("Could not sign up")))
    }
        .catch { e -> emit(DataState.Response(e.logMessage())) }
        .onCompletion { cause ->
            cause?.let { println("SignIn flow completed with $cause") }
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }

    suspend fun getProfileShops(): List<Shop> {
        val response = apolloClient.query(GetProfileQuery()).execute()
        return response.data?.getProfile?.shops?.map { it.toShop() } ?: emptyList()
    }

    fun getUserState(): UserState? = database.getUserState()

    fun deleteUserState() {
        return database.deleteUserState()
    }

    private fun Throwable.logMessage() =
        UIComponent.Log(this.message ?: "Could not sign up")

}