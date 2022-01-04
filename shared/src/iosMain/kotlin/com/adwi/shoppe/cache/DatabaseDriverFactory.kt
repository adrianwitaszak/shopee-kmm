package com.adwi.shoppe.cache

import com.adwi.shoppe.android.Shoppe
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Shoppe.Schema, "shoppe.db")
    }
}