package com.adwi.shoppe.di

import com.adwi.shoppe.cache.DatabaseDriverFactory
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

actual fun platformModule() = module {
    single<SqlDriver> { DatabaseDriverFactory() }
}