package com.adwi.shoppe.ui.navigation

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class Screen : Parcelable {

    @Parcelize
    object Login : Screen()

    @Parcelize
    data class Dashboard(val text: String) : Screen()
}