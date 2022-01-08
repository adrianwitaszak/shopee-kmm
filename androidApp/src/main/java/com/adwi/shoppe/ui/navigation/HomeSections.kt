package com.adwi.shoppe.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Task
import androidx.compose.ui.graphics.vector.ImageVector
import com.adwi.shoppe.android.R


enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    DASHBOARD(R.string.dashboard, Icons.Outlined.Dashboard, "home/dashboard"),
    MANAGER(R.string.manager, Icons.Outlined.ManageAccounts, "home/manager"),
    PLANNER(R.string.planner, Icons.Outlined.Task, "home/planner"),
    SETTINGS(R.string.settings, Icons.Outlined.AccountCircle, "home/settings")
}
