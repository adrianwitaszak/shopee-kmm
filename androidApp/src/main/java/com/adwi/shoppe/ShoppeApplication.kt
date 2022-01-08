package com.adwi.shoppe

import android.app.Application
import com.adwi.shoppe.cache.DatabaseDriverFactory
import com.adwi.shoppe.repository.AuthRepository
import com.adwi.shoppe.repository.ReviewRepository
import com.adwi.shoppe.repository.ShopRepository
import com.adwi.shoppe.ui.screens.dashboard.DashboardViewModel
import com.adwi.shoppe.ui.screens.login.LoginViewModel
import com.adwi.shoppe.ui.screens.manager.ManagerViewModel
import com.adwi.shoppe.ui.screens.planner.PlannerViewModel
import com.adwi.shoppe.ui.screens.settings.SettingsViewModel
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

        bindProvider { LoginViewModel(instance()) }
        bindProvider { DashboardViewModel() }
        bindProvider { ManagerViewModel() }
        bindProvider { PlannerViewModel() }
        bindProvider { SettingsViewModel() }
    }

    override fun onCreate() {
        super.onCreate()
        LinkServer().start()
    }
}