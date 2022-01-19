package com.adwi.shoppe.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.components.BottomText
import com.adwi.shoppe.ui.components.ShoppeButton
import com.adwi.shoppe.ui.components.ShoppeTextField
import com.adwi.shoppe.ui.components.WelcomeHeader

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun LoginScreen(
    onSignInClick: (String) -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var animateToEnd by remember { mutableStateOf(false) }

    val progressPrimaryColor by animateColorAsState(
        targetValue = if (animateToEnd) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary,
        animationSpec = tween(1000)
    )
    val progressSecondaryColor by animateColorAsState(
        targetValue = if (animateToEnd) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.secondaryVariant,
        animationSpec = tween(1000)
    )
    val progressBackgroundColor by animateColorAsState(
        targetValue = if (animateToEnd) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.background,
        animationSpec = tween(1000)
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(Modifier.weight(1f))
        WelcomeHeader()
        Spacer(Modifier.weight(1f))
        Column(
            modifier = Modifier.width(300.dp)
        ) {
            ShoppeTextField(
                layoutId = "email",
                text = email,
                onTextChange = { email = it },
                label = "Email",
                leadingIcon = Icons.Filled.Email,
                backgroundColor = progressSecondaryColor,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { defaultKeyboardAction(ImeAction.Next) },
                    onSend = { clearFocus(keyboardController, focusManager) }
                ),
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.size(16.dp))
            ShoppeTextField(
                layoutId = "password",
                text = password,
                onTextChange = { password = it },
                label = "Password",
                leadingIcon = Icons.Filled.Password,
                backgroundColor = progressSecondaryColor,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = if (email.isEmpty()) ImeAction.Previous else ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onPrevious = { focusManager.moveFocus(FocusDirection.Up) },
                    onSend = {
                        clearFocus(keyboardController, focusManager)
                        onSignInClick("$email $password")
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(32.dp))
            ShoppeButton(
                label = { Text(text = "Sign in") },
                onClick = { onSignInClick("$email $password") },
                buttonColor = progressPrimaryColor,
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .align(Alignment.End)
            )
        }
        Spacer(Modifier.weight(1f))
        BottomText(
            layoutId = "BottomText",
            onClick = {
                animateToEnd = !animateToEnd
                clearFocus(keyboardController, focusManager)
            },
            modifier = Modifier
        )

        Spacer(Modifier.size(16.dp))
    }
}

@ExperimentalComposeUiApi
fun clearFocus(keyboardController: SoftwareKeyboardController?, focusManager: FocusManager) {
    keyboardController?.hide()
    focusManager.clearFocus()
}