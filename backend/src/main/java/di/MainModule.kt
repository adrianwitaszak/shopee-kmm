package com.shoppe.di

import com.shoppe.util.MONGO_KEY
import org.koin.dsl.module
import org.litote.kmongo.KMongo

val mainModule = module(createdAtStart = true) {
    factory { KMongo.createClient(MONGO_KEY) }
}