package com.adwi.shoppe.ui.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.adwi.shoppe.android.type.UserInput
import com.adwi.shoppe.repository.AuthRepository
import com.adwi.shoppe.ui.base.BaseViewModel
import com.adwi.shoppe.util.Event
import com.apollographql.apollo3.annotations.ApolloExperimental
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ApolloExperimental
class LoginViewModel constructor(private val repository: AuthRepository) : BaseViewModel() {

    val token = MutableStateFlow("")

    init {
        Log.d("MainViewModel", "INITIALIZED")
    }

    fun getAuthToken(): String {
        return repository.getUserState()?.token ?: ""
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signIn(UserInput(email = email, password = password))
            token.value = result
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signUp(UserInput(email = email, password = password))
            token.value = result
            if (result.isNotEmpty()) {
                setEvent(Event.ShowToast("Signed up successfully"))
            }
        }
    }

    fun signOut() {
        repository.deleteUserState()
    }
}
