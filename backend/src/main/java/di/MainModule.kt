package com.shoppe.di

import org.koin.dsl.module
import org.litote.kmongo.KMongo

val key = System.getenv("MONGO_URI") ?: ""

val mainModule = module(createdAtStart = true) {
    factory { KMongo.createClient(key) }
}