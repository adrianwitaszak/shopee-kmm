package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import com.adwi.shoppe.ui.theme.Pink40
import com.adwi.shoppe.ui.theme.Pink80

@Composable
fun ForgotPassword(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    text: String = "Forgot password?",
    onClick: () -> Unit,
    textColor: Color = if (isSystemInDarkTheme()) Pink80 else Pink40,
) {
    Surface(
        onClick = onClick,
        color = Color.Transparent,
        modifier = modifier.layoutId(layoutId)
    ) {
        Text(
            text = text,
            color = textColor,
        )
    }
}
