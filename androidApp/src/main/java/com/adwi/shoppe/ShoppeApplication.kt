package com.adwi.shoppe

import android.app.Application
import com.adwi.shoppe.cache.DatabaseDriverFactory
import com.adwi.shoppe.repository.AuthRepository
import com.adwi.shoppe.repository.ReviewRepository
import com.adwi.shoppe.repository.ShopRepository
import com.adwi.shoppe.ui.screens.detail.ShopDetailViewModel
import com.adwi.shoppe.ui.screens.favorites.FavoriteListViewModel
import com.adwi.shoppe.ui.screens.form.ShopFormViewModel
import com.adwi.shoppe.ui.screens.login.LoginViewModel
import com.adwi.shoppe.ui.screens.profile.ProfileViewModel
import com.adwi.shoppe.ui.screens.review.ReviewFormViewModel
import com.adwi.shoppe.ui.screens.shops.ShopListViewModel
import com.apollographql.apollo3.annotations.ApolloExperimental
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.*
import tools.LinkServer

@ApolloExperimental
@ExperimentalCoroutinesApi
class ShoppeApplication : Application(), DIAware {

    override val di: DI by DI.lazy {

        bindSingleton { DatabaseDriverFactory(this@ShoppeApplication.applicationContext) }
        bindSingleton { ApolloProvider(instance()) }

        bindSingleton { ShopRepository(instance()) }
        bindSingleton { AuthRepository(instance()) }
        bindSingleton { ReviewRepository(instance()) }

        bindProvider { ShopListViewModel(instance()) }
        bindProvider { ShopDetailViewModel(instance(), instance()) }
        bindProvider { ReviewFormViewModel(instance()) }
        bindProvider { FavoriteListViewModel(instance()) }
        bindProvider { ShopFormViewModel(instance()) }
        bindProvider { LoginViewModel(instance()) }
        bindProvider { ProfileViewModel(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        LinkServer().start()
    }
}