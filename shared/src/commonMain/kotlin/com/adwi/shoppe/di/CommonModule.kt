package com.adwi.shoppe.di

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.repository.AuthRepository
import com.adwi.shoppe.repository.ReviewRepository
import com.adwi.shoppe.repository.ServiceRepository
import com.adwi.shoppe.repository.ShopOrderRepository
import com.adwi.shoppe.repository.ShopRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@ApolloExperimental
fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule(), commonModule)
    }

@ApolloExperimental
@ExperimentalCoroutinesApi
val commonModule = module {

    single { ApolloProvider(get()) }
    single { AuthRepository(get()) }
    single { ReviewRepository(get()) }
    single { ServiceRepository(get()) }
    single { ShopOrderRepository(get()) }
    single { ShopRepository(get()) }
}