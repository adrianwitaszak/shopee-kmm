package com.adwi.shoppe.ui.screens.login

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.adwi.shoppe.ui.components.BackButton
import com.adwi.shoppe.ui.components.ShoppeButton
import com.adwi.shoppe.ui.components.ShoppeTextField
import com.adwi.shoppe.ui.screens.login.components.BottomText
import com.adwi.shoppe.ui.screens.login.components.ForgoPassword
import com.adwi.shoppe.ui.screens.login.components.WelcomeHeader
import com.adwi.shoppe.ui.theme.Pink40
import org.kodein.di.compose.rememberInstance

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

    // Animation
    var animateToEnd by remember { mutableStateOf(false) }

    // Animation progress
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

    Column {
        MotionLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(progressBackgroundColor),

            // header email password button forgot register

            motionScene = MotionScene("""{
                ConstraintSets: {
                  start: {                                        
                    backButton: {
                        top: ['parent', 'top', 0],
                        bottom: ['forgotHeader', 'top', 0],
                        start: ['parent', 'start', 0],
                        alpha: 0
                    },
                    forgotHeader: {
                        top: ['parent', 'top', 200],
                        end: ['parent', 'start', 0],
                        alpha: 0
                    },
                    header: {
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
                    button: {
                        top: ['password', 'bottom', 32],
                        end: ['password', 'end', 0],
                        alpha: 1
                    },
                    forgot: {
                        top: ['button', 'top', 0],
                        bottom: ['button', 'bottom', 0],
                        end: ['button', 'start', 10],
                        alpha: 1
                    },
                    register: {
                        bottom: ['parent', 'bottom', 32],
                        centerHorizontally: 'parent',
                        alpha: 1
                    }
                  },
                  end: {                                 
                    backButton: {
                        top: ['parent', 'top', 64],
                        bottom: ['forgotHeader', 'top', 0],
                        start: ['parent', 'start', 64],
                        alpha: 1
                    },        
                    forgotHeader: {
                        bottom: ['email', 'top', 64],
                        start: ['email', 'start', 0],
                        alpha: 1
                    },
                    header: {
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
                    button: {
                        top: ['email', 'bottom', 12],
                        end: ['email', 'end', 0],
                        alpha: 1
                    },
                    forgot: {
                        top: ['button', 'top', 0],
                        bottom: ['button', 'bottom', 0],
                        start: ['button', 'start', 0],
                        alpha: 0
                    },
                    register: {
                        top: ['parent', 'bottom', 0],
                        centerHorizontally: 'parent',
                        alpha: 0
                    }
                  }
                }
                }"""),
            progress = progressFloat
        ) {
            BackButton(
                onClick = {},
                modifier = Modifier.layoutId("backButton")
            )
            Text(
                text = "Forgot \nPassword?",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp
                ),
                modifier = Modifier.layoutId("forgotHeader")
            )
            WelcomeHeader(
                text1 = "Welcome to",
                text2 = "Shoppe",
                text3 = "for Sellers",
                modifier = Modifier.layoutId("header")
            )
            ShoppeTextField(
                text = email,
                onTextChange = { email = it },
                label = "Email",
                leadingIcon = Icons.Filled.Email,
                backgroundColor = progressSecondaryColor,
                keyboardActions = KeyboardActions {
                    defaultKeyboardAction(ImeAction.Next)
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .layoutId("email")
            )
            ShoppeTextField(
                text = password,
                onTextChange = { password = it },
                label = "Password",
                leadingIcon = Icons.Filled.Password,
                backgroundColor = progressSecondaryColor,
                visualTransformation = PasswordVisualTransformation(),
                keyboardActions = KeyboardActions(
                    onPrevious = { focusManager.moveFocus(FocusDirection.Up) },
                    onDone = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
                ),
                keyboardOptions = KeyboardOptions(imeAction = if (email.isEmpty()) ImeAction.Previous else ImeAction.Send),
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .layoutId("password")
            )
            ForgoPassword(
                onClick = {
                    animateToEnd = !animateToEnd
//                    viewModel.setScreenState(LoginScreenState.FORGOT)
                },
                modifier = Modifier.layoutId("forgot")
            )
            ShoppeButton(
                label = if (!animateToEnd) "Sign in" else "Reset",
                onClick = {
                    animateToEnd = !animateToEnd
//                    viewModel.setScreenState(LoginScreenState.LOGIN)
                },
                buttonColor = progressPrimaryColor,
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .layoutId("button")
            )
            BottomText(
                onClick = {
                    animateToEnd = !animateToEnd
//                    viewModel.setScreenState(LoginScreenState.REGISTER)
                },
                modifier = Modifier.layoutId("register")
            )
        }
    }
}