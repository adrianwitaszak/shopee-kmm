package com.adwi.shoppe.ui.screens.login

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.adwi.shoppe.com.adwi.shoppe.ui.screens.login.components.ProfileImage
import com.adwi.shoppe.ui.components.*
import com.adwi.shoppe.ui.screens.login.components.*
import com.adwi.shoppe.ui.theme.Pink40
import com.adwi.shoppe.util.ProgressBarState
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay


@ApolloExperimental
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onSignInComplete: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val event = viewModel::onTriggerEvent

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var currentScreen by rememberSaveable { mutableStateOf(LoginScreenUiState.LOGIN) }
    var backgroundState by rememberSaveable { mutableStateOf(AnimatedShadeBackgroundState.FIRST) }

    var animateToEnd by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    if (state.token.isNotEmpty()) {
        currentScreen = LoginScreenUiState.COMPLETE
        animateToEnd = !animateToEnd
        LaunchedEffect(currentScreen == LoginScreenUiState.COMPLETE) {
            delay(1000)
            onSignInComplete()
            viewModel.setSnackBar("Signed in")
        }
    }
    if (state.progressBarState == ProgressBarState.Loading) {
        currentScreen = LoginScreenUiState.LOADING
    }

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

    val completeConstraintSet = ConstraintSet(
        """{
          back: {
            top: ['parent', 'top', 0],
            bottom: ['ForgotHeader', 'top', 0],
            start: ['parent', 'start', 0],
            alpha: 0
        },
        register: {
            start:  ['parent', 'end', 0],
            centerVertically: 'back',
            alpha: 1
        },
        ForgotHeader: {
            top: ['parent', 'top', 200],
            end: ['parent', 'start', 0],
            alpha: 0
        },
        WelcomeHeader: {
            top: ['parent', 'top', -100],
            centerHorizontally: 'parent',
            alpha: 0
        },
        image: {
            top: ['email', 'top', 0],
            centerHorizontally: 'parent',
            alpha: 0
        },
        name: {
            centerHorizontally: 'email',
            centerVertically: 'email',
            alpha: 0
        },                                          // Login
        email: {
            top: ['parent', 'top', 0],
            bottom: ['parent', 'bottom', 0],
            start: ['parent', 'end', 0],
            alpha: 1
        },
        password: {
            top: ['email', 'bottom', 16],
            end: ['parent', 'start', 0],
            alpha: 1
        },
        signIn: {
            top: ['password', 'bottom', 32],
            end: ['password', 'end', 0],
            alpha: 1
        },
        forgot: {
            top: ['signIn', 'top', 0],
            bottom: ['signIn', 'bottom', 0],
            end: ['signIn', 'start', 10],
            alpha: 1
        },
        BottomText: {
            top: ['parent', 'bottom', 0],
            centerHorizontally: 'parent',
            alpha: 0
        }  
    }"""
    )

    val registerConstraintSet = ConstraintSet(
        """{
        back: {
            top: ['parent', 'top', 20],
            start: ['parent', 'start', 24],
            alpha: 1
        },
        register: {
            top: ['back', 'top', 0],
            bottom: ['back', 'bottom', 0],
            centerHorizontally: 'parent',
            alpha: 1
        },
        ForgotHeader: {
            top: ['parent', 'top', 200],
            end: ['parent', 'start', 0],
            alpha: 0
        },
        WelcomeHeader: {
            top: ['parent', 'top', -100],
            centerHorizontally: 'parent',
            alpha: 0
        },
        image: {
            top: ['back', 'bottom', 0],
            bottom: ['name', 'top', 0],
            centerHorizontally: 'parent',
            alpha: 1
        },
        name: {                                       // Register
            centerHorizontally: 'parent',
            centerVertically: 'parent',
            alpha: 1
        },
        email: {
            top: ['name', 'bottom', 16],
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
            end: ['email', 'end', 0],
            alpha: 1
        },
        forgot: {
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
      }"""
    )

    val forgotConstraintSet = ConstraintSet(
        """{
        back: {
            top: ['parent', 'top', 20],
            start: ['parent', 'start', 24],
            alpha: 1
        },
        register: {
            start:  ['parent', 'end', 0],
            centerVertically: 'back',
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
        image: {
            bottom: ['parent', 'top', 0],
            centerHorizontally: 'parent',
            alpha: 0
        },
        name: {
            centerHorizontally: 'email',
            centerVertically: 'email',
            alpha: 0
        },                                            // Forgot
        email: {
            top: ['parent', 'top', 50],
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
            top: ['email', 'bottom', 32],
            end: ['email', 'end', 0],
            alpha: 1
        },
        forgot: {
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
      }"""
    )

    val loginConstraintSet = ConstraintSet(
        """{
          back: {
            top: ['parent', 'top', 0],
            bottom: ['ForgotHeader', 'top', 0],
            start: ['parent', 'start', 0],
            alpha: 0
        },
        register: {
            start:  ['parent', 'end', 0],
            centerVertically: 'back',
            alpha: 1
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
        image: {
            top: ['email', 'top', 0],
            centerHorizontally: 'parent',
            alpha: 0
        },
        name: {
            centerHorizontally: 'email',
            centerVertically: 'email',
            alpha: 0
        },                                          // Login
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
        forgot: {
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
    }"""
    )
    val loadingConstraintSet = ConstraintSet(
        """{
        back: {
            top: ['parent', 'top', 20],
            start: ['parent', 'start', 24],
            alpha: 1
        },
        register: {
            start:  ['parent', 'end', 0],
            centerVertically: 'back',
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
        image: {
            bottom: ['parent', 'top', 0],
            centerHorizontally: 'parent',
            alpha: 0
        },
        name: {
            centerHorizontally: 'email',
            centerVertically: 'email',
            alpha: 0
        },                                            // Forgot
        email: {
            top: ['parent', 'top', 50],
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
            top: ['email', 'bottom', 32],
            end: ['email', 'end', 0],
            alpha: 1
        },
        forgot: {
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
      }"""
    )

    val constrains = when (currentScreen) {
        LoginScreenUiState.LOGIN -> loginConstraintSet
        LoginScreenUiState.REGISTER -> registerConstraintSet
        LoginScreenUiState.FORGOT -> forgotConstraintSet
        LoginScreenUiState.COMPLETE -> completeConstraintSet
        LoginScreenUiState.LOADING -> loadingConstraintSet
    }

    ShoppeScaffold(viewModel = viewModel) {
        ConstraintLayout(
            constraintSet = constrains,
            animateChanges = true,
            animationSpec = tween(1000),
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
        ) {
            AnimatedShadeBackground(
                state = backgroundState,
                background = progressBackgroundColor,
            )
            BackButton(
                layoutId = "back",
                onClick = {
                    currentScreen = LoginScreenUiState.LOGIN
                    animateToEnd = !animateToEnd
                    backgroundState = backgroundState.toggleState()
                    clearFocus(keyboardController, focusManager)
                },
            )
            Header(
                layoutId = "register",
                text = "Register"
            )
            Header(
                layoutId = "ForgotHeader",
                text = "Forgot \nPassword?"
            )
            WelcomeHeader(layoutId = "WelcomeHeader")
            ProfileImage(layoutId = "image")
            ShoppeTextField(
                layoutId = "name",
                text = name,
                onTextChange = { name = it },
                label = "Name",
                leadingIcon = Icons.Filled.Person,
                backgroundColor = progressSecondaryColor,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions { defaultKeyboardAction(ImeAction.Next) },
                modifier = Modifier.fillMaxWidth(.8f)
            )
            ShoppeTextField(
                layoutId = "email",
                text = email,
                onTextChange = { email = it },
                label = "Email",
                leadingIcon = Icons.Filled.Email,
                backgroundColor = progressSecondaryColor,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = if (currentScreen == LoginScreenUiState.FORGOT)
                        ImeAction.Send else ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { defaultKeyboardAction(ImeAction.Next) },
                    onSend = { clearFocus(keyboardController, focusManager) }
                ),
                modifier = Modifier.fillMaxWidth(.8f)
            )
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
                        event(LoginScreenEvents.SignIn(email, password))
                    }
                ),
                modifier = Modifier.fillMaxWidth(.8f)
            )

            ForgotPassword(
                layoutId = "forgot",
                onClick = {
                    currentScreen = LoginScreenUiState.FORGOT
                    animateToEnd = !animateToEnd
                    backgroundState = backgroundState.toggleState()
                    clearFocus(keyboardController, focusManager)
                }
            )
            ShoppeButton(
                layoutId = "signIn",
                label = { ShoppeButtonText(state = currentScreen) },
                onClick = {
                    clearFocus(keyboardController, focusManager)
                    backgroundState = backgroundState.toggleState()
                    when (currentScreen) {
                        LoginScreenUiState.LOGIN -> {
                            event(LoginScreenEvents.SignIn(email, password))
                        }
                        LoginScreenUiState.REGISTER -> {
                            event(LoginScreenEvents.SignUp(name, email, password))
                        }
                        LoginScreenUiState.FORGOT -> {
                            Toast.makeText(context, "Comming soon", Toast.LENGTH_SHORT).show()
                        }
                        LoginScreenUiState.COMPLETE -> {}
                        LoginScreenUiState.LOADING -> {}
                    }
                },
                buttonColor = progressPrimaryColor,
                modifier = Modifier.fillMaxWidth(.4f),
            )
            BottomText(
                layoutId = "BottomText",
                onClick = {
                    currentScreen = LoginScreenUiState.REGISTER
                    animateToEnd = !animateToEnd
                    backgroundState = backgroundState.toggleState()
                    clearFocus(keyboardController, focusManager)
                },
            )
        }
    }
}

@ExperimentalComposeUiApi
fun clearFocus(keyboardController: SoftwareKeyboardController?, focusManager: FocusManager) {
    keyboardController?.hide()
    focusManager.clearFocus()
}
