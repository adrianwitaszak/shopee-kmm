package com.adwi.shoppe

import com.adwi.shoppe.util.Constants.BASE_URL
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader

class ApolloProvider {

    internal val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .httpHeaders(
            listOf(
                HttpHeader("Accept", "application/json"),
                HttpHeader("Content-Type", "application/json")
            )
        )
//        .addHttpHeader("Accept", "application/json")
//        .addHttpHeader("Content-Type", "application/json")
        .build()
}