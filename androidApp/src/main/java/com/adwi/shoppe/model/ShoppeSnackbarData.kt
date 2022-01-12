package com.adwi.shoppe.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ShoppeSnackbarData(
    var message: String = "",
    var action: ShoppeSnackbarAction? = null,
)

data class ShoppeSnackbarAction(
    var label: String,
    var icon: ImageVector? = null,
    var action: () -> Unit,
)