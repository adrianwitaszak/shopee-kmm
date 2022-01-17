package com.adwi.ktor.di

import org.koin.dsl.module
import org.litote.kmongo.KMongo

val key = System.getenv("MONGO_URI")
    ?: "mongodb+srv://shoppe:1n1ezm1en1as1en1c@shoppe.9q104.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"

val mainModule = module(createdAtStart = true) {
    factory { KMongo.createClient(key) }
}