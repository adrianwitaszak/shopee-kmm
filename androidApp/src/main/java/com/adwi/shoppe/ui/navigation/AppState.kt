package com.adwi.shoppe.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

object MainDestinations {
    const val DASHBOARD_ROUTE = "home"
    const val LOGIN_ROUTE = "login"
    const val SHOP_PREVIEW_ROUTE = "shop_preview"
    const val SHOP_ID_KEY = "shopId"
    const val REVIEW_ROUTE = "review_route"
    const val REVIEW_ID_KEY = "shopId"
}

@ExperimentalAnimationApi
@Composable
fun rememberMyAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberAnimatedNavController()
) =
    remember(scaffoldState, navController) {
        PexAppState(scaffoldState, navController)
    }

@Stable
class PexAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
) {
    val bottomBarTabs = HomeSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(findStartDestination(navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateToShop(shopId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.SHOP_PREVIEW_ROUTE}/$shopId")
        }
    }

    fun navigateToReview(reviewId: String, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.REVIEW_ROUTE}/$reviewId")
        }
    }

    fun navigateToLogin() {
        navController.navigate(MainDestinations.LOGIN_ROUTE) {
            launchSingleTop = true
            popUpTo(HomeSections.DASHBOARD.route) { inclusive = true }
        }
    }
}