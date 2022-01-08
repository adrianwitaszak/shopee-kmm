package com.adwi.shoppe.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adwi.shoppe.cache.ActionType
import com.adwi.shoppe.ui.screens.detail.ShopDetailView
import com.adwi.shoppe.ui.screens.detail.ShopDetailViewModel
import com.adwi.shoppe.ui.screens.favorites.FavoriteListView
import com.adwi.shoppe.ui.screens.favorites.FavoriteListViewModel
import com.adwi.shoppe.ui.screens.form.ShopFormView
import com.adwi.shoppe.ui.screens.form.ShopFormViewModel
import com.adwi.shoppe.ui.screens.login.LoginScreen
import com.adwi.shoppe.ui.screens.review.ReviewFormView
import com.adwi.shoppe.ui.screens.review.ReviewFormViewModel
import com.adwi.shoppe.ui.screens.shops.ShopListViewModel
import com.adwi.shoppe.ui.screens.shops.ShopsScreen
import com.apollographql.apollo3.annotations.ApolloExperimental
import org.kodein.di.compose.rememberInstance

@ApolloExperimental
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.ShopsScreen.route) {
            val viewModel: ShopListViewModel by rememberInstance()
            ShopsScreen(
                viewModel = viewModel,
            ) {
                navController.navigate(Screens.ShopDetailsScreen.route + "/${it.id}")
            }
        }
        composable(Screens.ShopDetailsScreen.route + "/{id}") { backStackEntry ->
            val viewModel: ShopDetailViewModel by rememberInstance()
            ShopDetailView(
                viewModel = viewModel,
                backStackEntry.arguments?.get("id") as String,
                editShopSelected = {
                    navController.navigate(Screens.ShopFormScreen.route + "/${it}")
                },
                popBack = { navController.popBackStack() },
                editReviewSelected = { navController.navigate(Screens.ReviewFormScreen.route + "?reviewId=${it}") },
                createReviewSelected = {
                    navController.navigate(Screens.ReviewFormScreen.route + "?shopId=${it}")
                })
        }

        composable(Screens.ShopFormScreen.route + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.get("id") as? String ?: "new"
            val action = if (id != "new") ActionType.UPDATE else ActionType.CREATE
            val viewModel: ShopFormViewModel by rememberInstance()
            ShopFormView(
                viewModel = viewModel,
                id,
                action,
                popBack = { navController.popBackStack() }
            )
        }

        composable(Screens.ReviewFormScreen.route + "?reviewId={reviewId}&shopId={shopId}") { backStackEntry ->
            val reviewId = backStackEntry.arguments?.get("reviewId") as? String ?: "new"
            val shopId = backStackEntry.arguments?.get("shopId") as? String ?: "new"
            val action = if (reviewId != "new") ActionType.UPDATE else ActionType.CREATE
            val viewModel: ReviewFormViewModel by rememberInstance()
            ReviewFormView(
                viewModel = viewModel,
                reviewId,
                shopId,
                action,
                popBack = { navController.popBackStack() }
            )
        }

        composable(Screens.FavoritesScreen.route) {
            val viewModel: FavoriteListViewModel by rememberInstance()
            FavoriteListView(
                viewModel = viewModel,
                shopSelected = { navController.navigate(Screens.ShopDetailsScreen.route + "/${it.id}") }
            )
        }

        composable(Screens.LoginScreen.route) {
            LoginScreen(
                onSignInComplete = { navController.navigate(Screens.ShopsScreen.route) }
            )
        }
    }
}
