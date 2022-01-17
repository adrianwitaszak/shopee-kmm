package com.adwi.ktor.models

data class PagingInfo(
    var count: Int,
    var pages: Int,
    var next: Int?,
    var prev: Int?,
)