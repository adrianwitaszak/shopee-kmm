package com.adwi.shoppe.ui.screens.settings

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.adwi.shoppe.ui.components.AnimatedShadeBackground
import com.adwi.shoppe.ui.components.AnimatedShadeBackgroundState
import com.adwi.shoppe.ui.components.ShoppeTopBar

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
) {
    val backgroundState by remember { mutableStateOf(AnimatedShadeBackgroundState.FIRST) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(rememberScrollState(), Orientation.Vertical)
    ) {
        AnimatedShadeBackground(state = backgroundState)
        ShoppeTopBar(
            text = "Settings",
            onUpPress = {},
            onNotificationClick = {}
        )
    }
}
