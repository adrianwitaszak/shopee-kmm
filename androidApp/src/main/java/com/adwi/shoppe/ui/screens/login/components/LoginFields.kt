package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.adwi.shoppe.ui.components.ShoppeTextField
import com.adwi.shoppe.ui.screens.login.LoginScreenUiState
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LoginFields(
    modifier: Modifier = Modifier,
    state: LoginScreenUiState = LoginScreenUiState.LOGIN,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onActionDone: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.padding(vertical = paddingValues)
    ) {
        ShoppeTextField(
            text = email,
            onTextChange = onEmailChanged,
            label = "Email",
            leadingIcon = Icons.Filled.Email,
            keyboardActions = KeyboardActions {
                defaultKeyboardAction(ImeAction.Next)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        Spacer(modifier = Modifier.size(paddingValues / 2))
        AnimatedVisibility(
            visible = state != LoginScreenUiState.FORGOT,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ShoppeTextField(
                text = password,
                onTextChange = onPasswordChanged,
                label = "Password",
                leadingIcon = Icons.Filled.Password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onPrevious = { focusManager.moveFocus(FocusDirection.Up) },
                    onDone = {
                        onActionDone()
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction =
                    if (email.isEmpty()) ImeAction.Previous else ImeAction.Send
                )
            )
        }
        AnimatedVisibility(
            visible = state == LoginScreenUiState.REGISTER,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Spacer(modifier = Modifier.size(paddingValues / 2))
            ShoppeTextField(
                text = password,
                onTextChange = onPasswordChanged,
                label = "Password",
                leadingIcon = Icons.Filled.Password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onPrevious = { focusManager.moveFocus(FocusDirection.Up) },
                    onDone = {
                        onActionDone()
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction =
                    if (email.isEmpty()) ImeAction.Previous else ImeAction.Send
                )
            )
            Spacer(modifier = Modifier.size(paddingValues / 2))
            ShoppeTextField(
                text = password,
                onTextChange = onPasswordChanged,
                label = "Password",
                leadingIcon = Icons.Filled.Password,
                visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onPrevious = { focusManager.moveFocus(FocusDirection.Up) },
                    onDone = {
                        onActionDone()
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction =
                    if (email.isEmpty()) ImeAction.Previous else ImeAction.Send
                )
            )
            Spacer(modifier = Modifier.size(paddingValues / 2))
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true, name = "Light")
@Composable
fun LoginFieldsPreview() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {

            LoginFields(
                email = "email@gmail.com",
                onEmailChanged = {},
                password = "passwordasdas",
                onPasswordChanged = {},
                onActionDone = {},
                state = LoginScreenUiState.LOGIN
            )
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview(showBackground = true, name = "Dark")
@Composable
fun LoginFieldsPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = paddingValues)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            LoginFields(
                email = "email@gmail.com",
                onEmailChanged = {},
                password = "passwordasd",
                onPasswordChanged = {},
                onActionDone = {},
                state = LoginScreenUiState.LOGIN
            )
        }
    }
}