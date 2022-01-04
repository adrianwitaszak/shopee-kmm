package com.adwi.shoppe.cache

import android.content.Context
import com.adwi.shoppe.android.Shoppe
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Shoppe.Schema, context, "shoppe.db")
    }
}