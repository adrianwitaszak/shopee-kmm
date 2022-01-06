package com.adwi.shoppe.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

const val SHOPS_ROUTE = "Shops"
const val LOGIN_ROUTE = "Profile"

sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object ShopsScreen : Screens(SHOPS_ROUTE, "Shops", Icons.Default.List)
    object ShopDetailsScreen : Screens("ShopDetails", "Shop details")
    object ShopFormScreen : Screens("ShopForm", "Shop form")
    object ReviewFormScreen : Screens("ReviewForm", "Review form")
    object FavoritesScreen : Screens("Favorites", "Favorites", Icons.Default.Favorite)
    object LoginScreen : Screens("Profile", "Profile", Icons.Default.AccountCircle)
}
