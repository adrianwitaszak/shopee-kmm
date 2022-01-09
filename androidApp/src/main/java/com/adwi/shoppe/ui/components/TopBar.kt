package com.adwi.shoppe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@ExperimentalMaterialApi
@Composable
fun ShoppeTopBar(
    modifier: Modifier = Modifier,
    text: String,
    layoutId: String = "",
    hasUpPress: Boolean = false,
    onUpPress: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    Box(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .layoutId(layoutId)
            .fillMaxWidth()
            .padding(vertical = paddingValues)
    ) {
        if (hasUpPress) {
            ShoppeIconButton(
                onClick = onUpPress,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
        ScreenHeader(
            text = text,
            modifier = Modifier.align(Alignment.Center),

            )
        ShoppeIconButton(
            onClick = onNotificationClick,
            icon = Icons.Outlined.Notifications,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, name = "Light")
@Composable
private fun TopBarPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ShoppeTopBar(
                text = "Manager",
                onUpPress = {},
                onNotificationClick = {},
                hasUpPress = true
            )
            ShoppeTopBar(
                text = "Manager",
                onUpPress = {},
                onNotificationClick = {},
                hasUpPress = false
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, name = "Dark")
@Composable
private fun TopBarPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ShoppeTopBar(
                text = "Manager",
                onUpPress = {},
                onNotificationClick = {},
                hasUpPress = true
            )
            ShoppeTopBar(
                text = "Manager",
                onUpPress = {},
                onNotificationClick = {},
                hasUpPress = false
            )
        }
    }
}