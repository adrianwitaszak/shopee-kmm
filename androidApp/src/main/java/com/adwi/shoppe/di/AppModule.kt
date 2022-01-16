package com.adwi.shoppe.di

import com.adwi.shoppe.ApolloProvider
import com.adwi.shoppe.cache.DatabaseDriverFactory
import com.adwi.shoppe.repository.*
import com.adwi.shoppe.ui.screens.dashboard.DashboardViewModel
import com.adwi.shoppe.ui.screens.login.LoginViewModel
import com.adwi.shoppe.ui.screens.manager.ManagerViewModel
import com.adwi.shoppe.ui.screens.planner.PlannerViewModel
import com.adwi.shoppe.ui.screens.settings.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { DashboardViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { ManagerViewModel() }
    viewModel { PlannerViewModel() }
    viewModel { SettingsViewModel() }

    single { ShopRepository(get()) }
    single { ReviewRepository(get()) }
    single { ServiceRepository(get()) }
    single { ShopOrderRepository(get()) }
    single { AuthRepository(get()) }

    single { ApolloProvider(DatabaseDriverFactory(get())) }
    single { DatabaseDriverFactory(this.androidApplication().applicationContext) }
}
