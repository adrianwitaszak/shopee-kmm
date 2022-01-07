package com.adwi.shoppe.ui.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.shoppe.android.type.UserInput
import com.adwi.shoppe.repository.AuthRepository
import com.apollographql.apollo3.annotations.ApolloExperimental
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ApolloExperimental
class LoginViewModel constructor(private val repository: AuthRepository) : ViewModel() {

    val token = MutableStateFlow("")

    init {
        getAuthToken()
        Log.d("MainViewModel", "INITIALIZED")
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
        }
    }

    private fun getAuthToken() {
        viewModelScope.launch {
            repository.getUserState().collect { state ->
                token.value = state?.token ?: ""
                Log.d("MainViewModel", token.value)
            }
        }
    }

    fun deleteAuthToken() {
        repository.deleteUserState()
    }
}
