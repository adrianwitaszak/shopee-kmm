package com.adwi.shoppe.ui.navigation

import androidx.compose.runtime.Composable
import com.adwi.shoppe.ui.screens.DashboardScreen
import com.adwi.shoppe.ui.screens.LoginScreen
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfadeScale
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import rememberRouter

@ExperimentalDecomposeApi
@Composable
fun ShoppeNavHost() {

    val router = rememberRouter<Screen>(
        initialConfiguration = { Screen.Login },
        handleBackButton = true
    )

    Children(
        routerState = router.state,
        animation = crossfadeScale()
    ) { screen ->
        when (val configuration = screen.configuration) {
            is Screen.Login -> LoginScreen(
                onSignInClick = { router.push(Screen.Dashboard(text = it)) }
            )
            is Screen.Dashboard -> DashboardScreen(
                text = configuration.text,
                onBack = router::pop
            )
        }
    }
}