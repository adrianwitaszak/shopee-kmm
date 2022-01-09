package com.adwi.shoppe.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.shoppe.util.Event
import com.adwi.shoppe.util.exhaustive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val tag: String = javaClass.simpleName

    private var _isRefreshing = MutableStateFlow(false)
    private val _snackBarMessage = MutableStateFlow("")
    private val _toastMessage = MutableStateFlow("")

    val isRefreshing = _isRefreshing.asStateFlow()
    val snackBarMessage = _snackBarMessage.asStateFlow()
    val toastMessage = _toastMessage.asStateFlow()

    private val eventChannel = Channel<Event>()

    private val events = eventChannel.receiveAsFlow()

    init {
        getEvents()
        setEvent(Event.ShowLog(tag, "Initiated"))
    }

    fun setEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            eventChannel.send(event)
        }
    }

    private fun getEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            events.collect { event ->
                when (event) {
                    is Event.ShowErrorMessage -> {
                        Log.d(tag, event.error.localizedMessage)
                    }
                    is Event.ShowSnackBar -> setSnackBar(event.message)
                    is Event.ShowToast -> setToast(event.message)
                    is Event.ShowLog -> Log.d(event.tag, event.message)
                }.exhaustive
            }
        }
    }

    private fun setToast(message: String) {
        _toastMessage.value = message
    }

    fun setSnackBar(message: String) {
        Log.d("snackbar", "setSnackBar - $message")
        _snackBarMessage.value = message
    }
}