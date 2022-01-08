package com.adwi.shoppe.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.adwi.shoppe.ui.components.ShoppeBottomBar
import com.adwi.shoppe.ui.components.ShoppeScaffold
import com.adwi.shoppe.ui.components.ShoppeSnackBarHost
import com.adwi.shoppe.ui.navigation.HomeSections
import com.adwi.shoppe.ui.navigation.MainDestinations
import com.adwi.shoppe.ui.navigation.myNavGraph
import com.adwi.shoppe.ui.navigation.rememberMyAppState
import com.adwi.shoppe.ui.screens.login.LoginViewModel
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.InternalCoroutinesApi
import org.kodein.di.compose.rememberInstance

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
        setContent {
            ProvideWindowInsets {
                ShoppeTheme {
                    val appState = rememberMyAppState()
                    val loginViewModel: LoginViewModel by rememberInstance()
                    val token = loginViewModel.getAuthToken()
                    Log.d(tag, "TOKEN = $token")

                    ShoppeScaffold(
                        viewModel = loginViewModel,
                        bottomBar = {
                            if (appState.shouldShowBottomBar) {
                                ShoppeBottomBar(
                                    tabs = appState.bottomBarTabs,
                                    currentRoute = appState.currentRoute!!,
                                    navigateToRoute = appState::navigateToBottomBarRoute,
                                )
                            }
                        },
                        snackbarHost = {
                            ShoppeSnackBarHost(
                                hostState = it,
                                modifier = Modifier
                            )
                        },
                        scaffoldState = appState.scaffoldState
                    ) {
                        AnimatedNavHost(
                            navController = appState.navController,
                            startDestination = if (token.isNotEmpty())
                                MainDestinations.DASHBOARD_ROUTE else MainDestinations.LOGIN_ROUTE
                        ) {
                            myNavGraph(
                                upPress = appState::upPress,
                                onSignInComplete = { appState.navigateToBottomBarRoute(HomeSections.DASHBOARD.route) },
                                onSignOutClick = {
                                    loginViewModel.signOut()
                                    appState.navigateToLogin()
                                },
                                onShopClick = appState::navigateToShop,
                                onReviewClick = appState::navigateToReview,
                            )
                        }
                    }
                }
            }
        }
    }
}