package com.adwi.shoppe.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.adwi.shoppe.ui.components.CustomBottomNavigation
import com.adwi.shoppe.ui.components.ShoppeScaffold
import com.adwi.shoppe.ui.components.ShoppeSnackBarHost
import com.adwi.shoppe.ui.navigation.HomeSections
import com.adwi.shoppe.ui.navigation.MainDestinations
import com.adwi.shoppe.ui.navigation.myNavGraph
import com.adwi.shoppe.ui.navigation.rememberMyAppState
import com.adwi.shoppe.ui.screens.login.LoginScreenEvents
import com.adwi.shoppe.ui.screens.login.LoginViewModel
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import org.koin.androidx.compose.viewModel

@ApolloExperimental
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalPagingApi
@InternalCoroutinesApi
class MainActivity : ComponentActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = useDarkIcons)
                systemUiController.setStatusBarColor(Color.Transparent, darkIcons = useDarkIcons)
            }

            ShoppeTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    val appState = rememberMyAppState()
                    val loginViewModel: LoginViewModel by viewModel()

                    val loginState by loginViewModel.state.collectAsState()

                    var message by remember { mutableStateOf("") }

                    LaunchedEffect(message.isEmpty()) {
                        delay(3000)
                        message = ""
                    }

                    ShoppeScaffold(
                        viewModel = loginViewModel,
                        bottomBar = {
                            if (appState.shouldShowBottomBar) {
                                CustomBottomNavigation(
                                    items = appState.bottomBarTabs.toList(),
                                    currentScreen = appState.currentRoute!!,
                                    onItemSelected = appState::navigateToBottomBarRoute,
                                    message = message,
                                    modifier = Modifier
                                        .padding(paddingValues)
                                        .navigationBarsWithImePadding()
                                )
                            }
                        },
                        snackbarHost = {
                            Log.d("snackbar", "MainActivity")
                            ShoppeSnackBarHost(
                                hostState = it,
                                modifier = Modifier
                            )
                        },
                        scaffoldState = appState.scaffoldState
                    ) {
                        AnimatedNavHost(
                            navController = appState.navController,
                            startDestination = if (loginState.token.isNotEmpty())
                                MainDestinations.DASHBOARD_ROUTE else MainDestinations.LOGIN_ROUTE
                        ) {
                            myNavGraph(
                                upPress = appState::upPress,
                                onSignInComplete = { appState.navigateToBottomBarRoute(HomeSections.DASHBOARD.route) },
                                onSignOutClick = {
                                    loginViewModel.onTriggerEvent(LoginScreenEvents.SignOut)
                                    appState.navigateToLogin()
                                },
                                onShopClick = appState::navigateToShop,
                                onReviewClick = appState::navigateToReview,
                                message = { message = it }
                            )
                        }
                    }
                }
            }
        }
    }
}