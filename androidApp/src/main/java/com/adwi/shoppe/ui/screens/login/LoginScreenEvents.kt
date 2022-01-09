package com.adwi.shoppe.ui.screens.login

sealed class LoginScreenEvents {
    object GetAuthToken : LoginScreenEvents()
    object SignOut : LoginScreenEvents()
    data class SignIn(val email: String, val password: String) : LoginScreenEvents()
    data class SignUp(val name: String, val email: String, val password: String) :
        LoginScreenEvents()
}
