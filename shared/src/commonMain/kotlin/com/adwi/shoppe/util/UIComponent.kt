package com.adwi.shoppe.util

sealed class UIComponent {
    data class SnackBar(val message: String) : UIComponent()
    data class Log(val message: String) : UIComponent()
}