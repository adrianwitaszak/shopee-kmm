package com.adwi.shoppe.ui.screens.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.adwi.shoppe.android.type.UserInput
import com.adwi.shoppe.repository.AuthRepository
import com.adwi.shoppe.ui.base.BaseViewModel
import com.adwi.shoppe.util.DataState
import com.adwi.shoppe.util.UIComponent
import com.apollographql.apollo3.annotations.ApolloExperimental
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ApolloExperimental
class LoginViewModel constructor(
    private val repository: AuthRepository,
) : BaseViewModel() {

    val state = MutableStateFlow(LoginScreenState())

    fun onTriggerEvent(event: LoginScreenEvents) {
        when (event) {
            LoginScreenEvents.GetAuthToken -> {
                state.value.token = getAuthToken()
            }
            is LoginScreenEvents.SignIn -> {
                signIn(event.email, event.password)
            }
            LoginScreenEvents.SignOut -> {
                signOut()
            }
            is LoginScreenEvents.SignUp -> {
                signUp(event.name, event.email, event.password)
            }
        }
    }

    init {
        onTriggerEvent(LoginScreenEvents.GetAuthToken)
    }

    private fun getAuthToken(): String {
        return repository.getUserState()?.token ?: ""
    }

    private fun signIn(email: String, password: String) {
        Log.d("LoginViewModel", "Sign in")
        repository.signIn(UserInput(email = email, password = password)).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    when (val state = dataState.uiComponent) {
                        is UIComponent.SnackBar -> {
                            Log.d(
                                "LoginViewModel",
                                "Sign in - response - snackbar - ${state.message}"
                            )
                            setSnackBar(state.message)
                        }
                        is UIComponent.Log -> {
                            Log.d("LoginViewModel", "Sign in - response - log - ${state.message}")
                            setToast(state.message)
                        }
                    }
                }
                is DataState.Data -> {
                    Log.d("LoginViewModel", "Sign in - data")
                    state.value = state.value.copy(token = dataState.data ?: "")
                    setSnackBar("Signed in")
                }
                is DataState.Loading -> {
                    Log.d("LoginViewModel", "Sign in - loading - ${dataState.progressBarState}")
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun signUp(name: String, email: String, password: String) {
        repository.signUp(UserInput(email = email, password = password)).onEach { dataState ->
            when (dataState) {
                is DataState.Response -> {
                    Log.d("LoginViewModel", "Sign in - response")
                }
                is DataState.Data -> {
                    Log.d("LoginViewModel", "Sign in - data")
                    state.value = state.value.copy(token = dataState.data ?: "")
                }
                is DataState.Loading -> {
                    Log.d("LoginViewModel", "Sign in - loading")
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun signOut() {
        repository.deleteUserState()
    }
}
