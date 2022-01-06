package com.adwi.shoppe.ui.screens.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.adwi.shoppe.ui.components.BackButton
import com.adwi.shoppe.ui.components.ShoppeButton
import com.adwi.shoppe.ui.components.ShoppeTextField
import com.adwi.shoppe.ui.screens.login.components.BottomText
import com.adwi.shoppe.ui.screens.login.components.ForgotHeader
import com.adwi.shoppe.ui.screens.login.components.ForgotPassword
import com.adwi.shoppe.ui.screens.login.components.ShoppeButtonText
import com.adwi.shoppe.ui.screens.login.components.WelcomeHeader
import com.adwi.shoppe.ui.theme.Pink40
import org.kodein.di.compose.rememberInstance

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LoginScreen() {
    val viewModel: LoginViewModel by rememberInstance()

    val screenState by viewModel.screenState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var animateToEnd by remember { mutableStateOf(false) }

    val progressFloat by animateFloatAsState(
        targetValue = if (animateToEnd) 1f else 0f,
        animationSpec = tween(1000)
    )
    val progressPrimaryColor by animateColorAsState(
        targetValue = if (animateToEnd) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.primary,
        animationSpec = tween(1000)
    )
    val progressSecondaryColor by animateColorAsState(
        targetValue = if (animateToEnd) Pink40 else MaterialTheme.colorScheme.secondary,
        animationSpec = tween(1000)
    )
    val progressBackgroundColor by animateColorAsState(
        targetValue = if (animateToEnd) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.background,
        animationSpec = tween(1000)
    )

    MotionLayout(
        motionScene = MotionScene("""{
    ConstraintSets: {
        start: {                                        
          BackButton: {
            top: ['parent', 'top', 0],
            bottom: ['ForgotHeader', 'top', 0],
            start: ['parent', 'start', 0],
            alpha: 0
        },
        ForgotHeader: {
            top: ['parent', 'top', 200],
            end: ['parent', 'start', 0],
            alpha: 0
        },
        WelcomeHeader: {
            top: ['parent', 'top', 0],
            bottom: ['email', 'top', 0],
            centerHorizontally: 'parent',
            alpha: 1
        },
        email: {
            top: ['parent', 'top', 0],
            bottom: ['parent', 'bottom', 0],
            centerHorizontally: 'parent',
            alpha: 1
        },
        password: {
            top: ['email', 'bottom', 16],
            centerHorizontally: 'parent',
            alpha: 1
        },
        signIn: {
            top: ['password', 'bottom', 32],
            end: ['password', 'end', 0],
            alpha: 1
        },
        ForgotPassword: {
            top: ['signIn', 'top', 0],
            bottom: ['signIn', 'bottom', 0],
            end: ['signIn', 'start', 10],
            alpha: 1
        },
        BottomText: {
            bottom: ['parent', 'bottom', 32],
            centerHorizontally: 'parent',
            alpha: 1
        }
      },
      end: {                                 
        BackButton: {
            top: ['parent', 'top', 64],
            bottom: ['ForgotHeader', 'top', 0],
            start: ['parent', 'start', 24],
            alpha: 1
        },        
        ForgotHeader: {
            bottom: ['email', 'top', 64],
            start: ['email', 'start', 0],
            alpha: 1
        },
        WelcomeHeader: {
            top: ['parent', 'top', -100],
            centerHorizontally: 'parent',
            alpha: 0
        },
        email: {
            top: ['parent', 'top', 30],
            bottom: ['parent', 'bottom', 0],
            centerHorizontally: 'parent',
            alpha: 1
        },
        password: {
            top: ['email', 'bottom', 16],
            end: ['parent', 'start', 0],
            alpha: 1
        },
        signIn: {
            top: ['email', 'bottom', 12],
            end: ['email', 'end', 0],
            alpha: 1
        },
        ForgotPassword: {
            top: ['signIn', 'top', 0],
            bottom: ['signIn', 'bottom', 0],
            start: ['signIn', 'start', 0],
            alpha: 0
        },
        BottomText: {
            top: ['parent', 'bottom', 0],
            centerHorizontally: 'parent',
            alpha: 0
        }
      }
    }
}"""),
        progress = progressFloat,
        modifier = Modifier
            .fillMaxSize()
            .background(progressBackgroundColor),
    ) {
        BackButton(
            layoutId = "BackButton",
            onClick = {},
        )
        ForgotHeader(layoutId = "ForgotHeader")
        WelcomeHeader(layoutId = "WelcomeHeader")
        ShoppeTextField(
            layoutId = "email",
            text = email,
            onTextChange = { email = it },
            label = "Email",
            leadingIcon = Icons.Filled.Email,
            backgroundColor = progressSecondaryColor,
            keyboardActions = KeyboardActions { defaultKeyboardAction(ImeAction.Next) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth(.8f)
        )
        ShoppeTextField(
            layoutId = "password",
            text = password,
            onTextChange = { password = it },
            label = "Password",
            leadingIcon = Icons.Filled.Password,
            backgroundColor = progressSecondaryColor,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = if (email.isEmpty()) ImeAction.Previous else ImeAction.Send),
            keyboardActions = KeyboardActions(
                onPrevious = { focusManager.moveFocus(FocusDirection.Up) },
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.fillMaxWidth(.8f)
        )
        ForgotPassword(
            layoutId = "ForgotPassword",
            onClick = { animateToEnd = !animateToEnd }
        )
        ShoppeButton(
            layoutId = "signIn",
            label = { ShoppeButtonText(visibility = animateToEnd) },
            onClick = {
                animateToEnd = !animateToEnd
            },
            buttonColor = progressPrimaryColor,
            modifier = Modifier.fillMaxWidth(.4f),
        )
        BottomText(
            layoutId = "BottomText",
            onClick = {
                animateToEnd = !animateToEnd
            },
        )
    }
}
