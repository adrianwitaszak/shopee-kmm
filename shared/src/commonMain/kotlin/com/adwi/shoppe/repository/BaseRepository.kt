package com.adwi.shoppe.repository

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.cache.Database
import com.apollographql.apollo3.ApolloClient

open class BaseRepository(apolloProvider: ApolloProvider) {
    val apolloClient: ApolloClient = apolloProvider.apolloClient
    val database: Database = apolloProvider.database
}