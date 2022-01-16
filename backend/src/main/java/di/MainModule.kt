package com.shoppe.di

import com.shoppe.util.API_KEY
import org.koin.dsl.module
import org.litote.kmongo.KMongo

val key = System.getenv()

val mainModule = module(createdAtStart = true) {
    factory { KMongo.createClient(API_KEY) }
}