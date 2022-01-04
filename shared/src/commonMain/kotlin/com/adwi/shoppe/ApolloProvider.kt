package com.adwi.shoppe

import com.adwi.shoppe.cache.Database
import com.adwi.shoppe.cache.DatabaseDriverFactory
import com.adwi.shoppe.util.Constants.BASE_URL
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.BearerTokenInterceptor
import com.apollographql.apollo3.network.http.LoggingInterceptor
import com.apollographql.apollo3.network.http.TokenProvider

class ApolloProvider(databaseDriverFactory: DatabaseDriverFactory) : TokenProvider {

    internal val database = Database(databaseDriverFactory)
    internal val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .addHttpHeader("Accept", "application/json")
        .addHttpHeader("Content-Type", "application/json")
        .addHttpInterceptor(BearerTokenInterceptor(this))
        .addHttpInterceptor(LoggingInterceptor())
        .build()

    override suspend fun currentToken(): String {
        return database.getUserState()?.token ?: ""
    }

    override suspend fun refreshToken(previousToken: String): String {
        return ""
    }
}
