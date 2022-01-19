package com.adwi.shoppe.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = AccentDarker,
    primaryVariant = AccentLight,
    onPrimary = Neutral1,
    secondary = PrimaryDark,
    secondaryVariant = PrimaryLight,
    onSecondary = Neutral8,
    onBackground = Neutral1,

    )

private val LightColorPalette = lightColors(
    primary = AccentDarker,
    primaryVariant = AccentLightBlue,
    onPrimary = Neutral6,
    secondary = PrimaryDark,
    secondaryVariant = PrimaryDarker,
    onSecondary = Neutral0,
    background = PrimaryLightShadow,
    onBackground = Neutral8,
    surface = Neutral1,
    onSurface = Neutral6
)

@Composable
fun ShoppeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}