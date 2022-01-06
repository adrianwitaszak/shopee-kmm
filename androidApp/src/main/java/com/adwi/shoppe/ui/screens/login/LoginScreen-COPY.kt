//package com.adwi.shoppe.ui.screens.login
//
//import androidx.compose.foundation.layout.BoxWithConstraints
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
//import com.adwi.shoppe.ui.components.ScreenExample18
//import com.adwi.shoppe.ui.components.ShoppeButton
//import com.adwi.shoppe.ui.screens.login.components.BottomText
//import com.adwi.shoppe.ui.screens.login.components.ForgoPassword
//import com.adwi.shoppe.ui.screens.login.components.LoginFields
//import com.adwi.shoppe.ui.screens.login.components.WelcomeHeader
//import com.adwi.shoppe.ui.theme.paddingValues
//import org.kodein.di.compose.rememberInstance
//
//@ExperimentalMaterialApi
//@ExperimentalComposeUiApi
//@Composable
//fun LoginScreen() {
//    val viewModel: LoginViewModel by rememberInstance()
//
//    val screenState by viewModel.screenState.collectAsState()
//
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    // Animations
////    val isLogin = updateTransition(label = "Login state", targetState = screenState == LoginScreenState.LOGIN)
////    val bottomDpState by isLogin.animateDp(label = "Bottom text dp") { if (it) paddingValues else 0.dp }
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
//
//            val margin = if (minWidth < 600.dp) 100.dp else 0.dp
//            val spacing by remember { mutableStateOf(margin) }
//
//            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//                val (
//                    header,
//                    fields,
//                    signIn,
//                    forgot,
//                    bottomText,
//                ) = createRefs()
//
//                WelcomeHeader(
//                    text1 = "Welcome to",
//                    text2 = "Shoppe",
//                    text3 = "for Sellers",
//                    visible = screenState == LoginScreenState.LOGIN,
//                    modifier = Modifier
//                        .constrainAs(header) {
//                            top.linkTo(parent.top)
//                            bottom.linkTo(fields.top)
//                            centerHorizontallyTo(parent)
//                        }
//                )
//                LoginFields(
//                    email = email,
//                    onEmailChanged = { email = it },
//                    password = password,
//                    onPasswordChanged = { password = it },
//                    onActionDone = { viewModel.signIn(email, password) },
//                    state = screenState,
//                    modifier = Modifier
//                        .fillMaxWidth(.8f)
//                        .constrainAs(fields) {
//                            top.linkTo(parent.top, margin = spacing)
//                            bottom.linkTo(parent.bottom)
//                            centerHorizontallyTo(parent)
//                        },
//                )
//                ShoppeButton(
//                    label = "Sign in",
//                    onClick = {
//                        viewModel.setScreenState(LoginScreenState.LOGIN)
////                        viewModel.signIn(email, password)
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth(.4f)
//                        .constrainAs(signIn) {
//                            top.linkTo(fields.bottom)
//                            end.linkTo(fields.end)
//                        }
//                )
//                ForgoPassword(
//                    onClick = { viewModel.setScreenState(LoginScreenState.FORGOT) },
//                    visible = screenState == LoginScreenState.LOGIN,
//                    modifier = Modifier.constrainAs(forgot) {
//                        top.linkTo(signIn.top)
//                        bottom.linkTo(signIn.bottom)
//                        start.linkTo(fields.start)
//                        end.linkTo(signIn.start)
//                    }
//                )
//                BottomText(
//                    onClick = { viewModel.setScreenState(LoginScreenState.REGISTER) },
//                    visible = screenState == LoginScreenState.LOGIN,
//                    modifier = Modifier.constrainAs(bottomText) {
//                        bottom.linkTo(parent.bottom, margin = paddingValues)
//                        centerHorizontallyTo(parent)
//                    }
//                )
//            }
//        }
//    }
//}