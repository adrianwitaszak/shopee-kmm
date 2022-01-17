package com.adwi.ktor.models

data class Profile(
    val user: User,
    val shops: List<Shop> = emptyList(),
)