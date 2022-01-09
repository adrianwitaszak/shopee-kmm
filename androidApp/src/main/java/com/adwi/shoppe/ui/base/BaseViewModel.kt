package com.adwi.shoppe.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {

    val tag: String = javaClass.simpleName

    private val _snackBarMessage = MutableStateFlow("")
    private val _toastMessage = MutableStateFlow("")

    val snackBarMessage = _snackBarMessage.asStateFlow()
    val toastMessage = _toastMessage.asStateFlow()


    fun setToast(message: String) {
        _toastMessage.value = message
    }

    fun setSnackBar(message: String) {
        Log.d("snackbar", "setSnackBar - $message")
        _snackBarMessage.value = message
    }
}