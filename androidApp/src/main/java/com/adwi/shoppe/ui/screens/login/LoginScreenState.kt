package com.adwi.shoppe.ui.screens.login


import com.adwi.shoppe.util.ProgressBarState

data class LoginScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    var token: String = "",
)

enum class LoginScreenUiState { LOGIN, REGISTER, FORGOT, COMPLETE, LOADING }
