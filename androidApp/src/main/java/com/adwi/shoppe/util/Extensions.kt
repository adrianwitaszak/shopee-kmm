package com.adwi.shoppe.util

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.onDispatcher(dispatcher: CoroutineDispatcher, body: suspend () -> Unit): Job {
    return viewModelScope.launch(dispatcher) {
        body()
    }
}

val <T> T.exhaustive: T
    get() = this

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

val runningQOrLater =
    android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q

val runningSOrLater =
    android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S