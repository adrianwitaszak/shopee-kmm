package com.adwi.ktor.di

import org.koin.dsl.module
import org.litote.kmongo.KMongo

val mainModule = module(createdAtStart = true) {
    factory { KMongo.createClient(System.getenv("MONGO_URI") ?: "") }
}