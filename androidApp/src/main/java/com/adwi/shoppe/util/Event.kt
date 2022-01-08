package com.adwi.shoppe.util

sealed class Event {
    data class ShowErrorMessage(val error: Throwable) : Event()
    data class ShowSnackBar(val message: String) : Event()
    data class ShowToast(val message: String) : Event()
    data class ShowLog(val tag: String, val message: String) : Event()
}