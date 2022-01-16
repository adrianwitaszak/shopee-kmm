package com.adwi.shoppe.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.adwi.shoppe.ui.screens.dashboard.DashboardScreen
import com.adwi.shoppe.ui.screens.dashboard.DashboardViewModel
import com.adwi.shoppe.ui.screens.login.LoginScreen
import com.adwi.shoppe.ui.screens.login.LoginViewModel
import com.adwi.shoppe.ui.screens.manager.ManagerScreen
import com.adwi.shoppe.ui.screens.manager.ManagerViewModel
import com.adwi.shoppe.ui.screens.planner.PlannerScreen
import com.adwi.shoppe.ui.screens.planner.PlannerViewModel
import com.adwi.shoppe.ui.screens.settings.SettingsScreen
import com.adwi.shoppe.ui.screens.settings.SettingsViewModel
import com.google.accompanist.navigation.animation.composable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.compose.viewModel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
fun NavGraphBuilder.myNavGraph(
    upPress: () -> Unit,
    onSignInComplete: () -> Unit,
    onSignOutClick: () -> Unit,
    onShopClick: (String, NavBackStackEntry) -> Unit,
    onReviewClick: (String, NavBackStackEntry) -> Unit,
    message: (String) -> Unit,
//    navigateToSearch: (NavBackStackEntry) -> Unit,
//    onAboutUsClick: (NavBackStackEntry) -> Unit,
//    onPrivacyPolicyClick: (NavBackStackEntry) -> Unit,
//    onContactSupportClick: () -> Unit,
//    onSaveAutomationClick: () -> Unit,
//    cancelWorks: () -> Unit,
//    onGiveFeedbackClick: () -> Unit,
//    onRequestFeature: () -> Unit,
//    onReportBugClick: () -> Unit,
//    onHomeClick: (url: String) -> Unit,
//    onLockClick: (url: String) -> Unit,
) {
    navigation(
        route = MainDestinations.DASHBOARD_ROUTE,
        startDestination = HomeSections.DASHBOARD.route
    ) {
        addHomeGraph(
            onSignOutClick = onSignOutClick,
            message = message,
//            onWallpaperClick = onWallpaperClick,
//            onCategoryClick = onCategoryClick,
//            navigateToSearch = navigateToSearch,
//            onAboutUsClick = onAboutUsClick,
//            onPrivacyPolicyClick = onPrivacyPolicyClick,
//            onContactSupportClick = onContactSupportClick,
//            onSaveAutomationClick = onSaveAutomationClick,
//            cancelWorks = cancelWorks,
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick
        )
    }
    composable(route = MainDestinations.LOGIN_ROUTE) {
        val viewModel by viewModel<LoginViewModel>()

        LoginScreen(
            viewModel = viewModel,
            onSignInComplete = onSignInComplete,
//            onWallpaperClick = { id -> onWallpaperClick(id, backStackEntry) },
//            onCategoryClick = { query -> onCategoryClick(query, backStackEntry) },
//            navigateToSearch = { navigateToSearch(backStackEntry) },
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick
        )
    }
//    // PREVIEW
//    composable(
//        route = "${MainDestinations.WALLPAPER_PREVIEW_ROUTE}/{${MainDestinations.WALLPAPER_ID_KEY}}",
//        arguments = listOf(navArgument(MainDestinations.WALLPAPER_ID_KEY) {
//            type = NavType.IntType
//        })
//    ) { backStackEntry ->
//        val viewModel = hiltViewModel<PreviewViewModel>(backStackEntry)
//
//        getCurrentSettings(viewModel, settings)
//
//        PreviewScreen(
//            viewModel = viewModel,
//            upPress = upPress,
//            onShareClick = onShareClick,
//            onDownloadClick = onDownloadClick,
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick,
//            onHomeClick = onHomeClick,
//            onLockClick = onLockClick
//        )
//    }
//    // SEARCH
//    composable(
//        route = "${MainDestinations.SEARCH_ROUTE}/{${MainDestinations.SEARCH_QUERY}}",
//        arguments = listOf(navArgument(MainDestinations.SEARCH_QUERY) {
//            type = NavType.StringType
//        })
//    ) { backStackEntry ->
//        val viewModel = hiltViewModel<SearchViewModel>(backStackEntry)
//
//        viewModel.restoreSavedQuery()
//        getCurrentSettings(viewModel, settings)
//
//        SearchScreen(
//            viewModel = viewModel,
//            onWallpaperClick = { id -> onWallpaperClick(id, backStackEntry) },
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick
//        )
//    }
//    composable(
//        route = MainDestinations.PRIVACY_POLICY
//    ) { backStackEntry ->
//        val viewModel = hiltViewModel<SettingsViewModel>(backStackEntry)
//
//        getCurrentSettings(viewModel, settings)
//
//        PrivacyPolicyScreen(
//            viewModel = viewModel,
//            upPress = upPress,
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick
//        )
//    }
//    composable(
//        route = MainDestinations.ABOUT_US
//    ) { backStackEntry ->
//        val viewModel = hiltViewModel<SettingsViewModel>(backStackEntry)
//
//        getCurrentSettings(viewModel, settings)
//
////        AboutUsScreen(
////            viewModel = viewModel,
////            upPress = upPress
//    }
}

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.addHomeGraph(
    onSignOutClick: () -> Unit,
//    onWallpaperClick: (Int, NavBackStackEntry) -> Unit,
//    onCategoryClick: (String, NavBackStackEntry) -> Unit,
//    navigateToSearch: (NavBackStackEntry) -> Unit,
//    onAboutUsClick: (NavBackStackEntry) -> Unit,
//    onPrivacyPolicyClick: (NavBackStackEntry) -> Unit,
//    onContactSupportClick: () -> Unit,
//    onSaveAutomationClick: () -> Unit,
//    cancelWorks: () -> Unit,
//    onGiveFeedbackClick: () -> Unit,
//    onRequestFeature: () -> Unit,
//    onReportBugClick: () -> Unit
    message: (String) -> Unit,
) {
    composable(HomeSections.DASHBOARD.route) { backStackEntry ->
        val viewModel by viewModel<DashboardViewModel>()

        DashboardScreen(
            viewModel = viewModel,
//            onShopClick = { id -> onShopClick(id, backStackEntry) },
            onSignOutClick = onSignOutClick,
            message = message
        )
    }
    composable(HomeSections.MANAGER.route) { backStackEntry ->
        val viewModel by viewModel<ManagerViewModel>()
//
//        viewModel.restoreSavedQuery()
//        getCurrentSettings(viewModel, settings)
//
        ManagerScreen(
            viewModel = viewModel,
//            onWallpaperClick = { id -> onWallpaperClick(id, backStackEntry) },
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick
        )
    }
    composable(HomeSections.PLANNER.route) { backStackEntry ->
        val viewModel by viewModel<PlannerViewModel>()
//
//        viewModel.getFavorites()
//        getCurrentSettings(viewModel, settings)
//
        PlannerScreen(
            viewModel = viewModel,
//            onSearchClick = { navigateToSearch(backStackEntry) },
//            onWallpaperClick = { id -> onWallpaperClick(id, backStackEntry) },
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick
        )
    }
    composable(HomeSections.SETTINGS.route) { backStackEntry ->
        val viewModel by viewModel<SettingsViewModel>()
//
//        viewModel.getSettings()
//
        SettingsScreen(
            viewModel = viewModel,
//            onAboutUsClick = { onAboutUsClick(backStackEntry) },
//            onPrivacyPolicyClick = { onPrivacyPolicyClick(backStackEntry) },
//            onContactSupportClick = onContactSupportClick,
//            onSaveAutomationClick = onSaveAutomationClick,
//            cancelWorks = cancelWorks,
//            onGiveFeedbackClick = onGiveFeedbackClick,
//            onRequestFeature = onRequestFeature,
//            onReportBugClick = onReportBugClick
        )
    }
}

fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
