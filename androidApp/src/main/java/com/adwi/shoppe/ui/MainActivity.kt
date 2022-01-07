package com.adwi.shoppe.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.adwi.shoppe.ui.components.BottomNav
import com.adwi.shoppe.ui.navigation.NavHost
import com.adwi.shoppe.ui.navigation.Screens
import com.adwi.shoppe.ui.screens.login.LoginScreen
import com.adwi.shoppe.ui.screens.login.LoginViewModel
import com.adwi.shoppe.ui.theme.ShoppeTheme
import org.kodein.di.compose.rememberInstance

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppeTheme {
                val navController = rememberNavController()
                val bottomNavigationItems =
                    listOf(Screens.ShopsScreen, Screens.FavoritesScreen, Screens.LoginScreen)

                val loginViewModel: LoginViewModel by rememberInstance()
                val token by loginViewModel.token.collectAsState()

                val isLoggedIn = token.isNotEmpty()

                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(isLoggedIn) {
                            BottomNav(
                                navController = navController,
                                items = bottomNavigationItems
                            )
                        }
                    }
                ) {
                    if (isLoggedIn) {
                        NavHost(
                            navController = navController,
                            startDestination = Screens.ShopsScreen.route,
                        )
                    } else {
                        LoginScreen { navController.navigate(Screens.ShopsScreen.route) }
                    }
                }
            }
        }
    }
}