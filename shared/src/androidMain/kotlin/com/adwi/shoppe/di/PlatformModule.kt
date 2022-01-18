package com.adwi.shoppe.di

import com.adwi.shoppe.cache.DatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DatabaseDriverFactory(get()) }
}