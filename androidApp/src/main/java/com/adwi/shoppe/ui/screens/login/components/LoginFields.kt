package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.adwi.shoppe.ui.screens.login.LoginScreenState
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LoginFields(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onActionDone: () -> Unit,
    state: LoginScreenState = LoginScreenState.LOGIN
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
            visible = state != LoginScreenState.FORGOT,
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
                keyboardOptions = KeyboardOptions(imeAction =
                if (email.isEmpty()) ImeAction.Previous else ImeAction.Send
                )
            )
        }
        AnimatedVisibility(
            visible = state == LoginScreenState.REGISTER,
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
                keyboardOptions = KeyboardOptions(imeAction =
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
                keyboardOptions = KeyboardOptions(imeAction =
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
                onActionDone = {}
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
                onActionDone = {}
            )
        }
    }
}