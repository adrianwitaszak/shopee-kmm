package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.adwi.shoppe.ui.theme.paddingValues

@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    text1: String = "Welcome to",
    text2: String = "Shoppe",
    text3: String = "for Sellers",
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.layoutId(layoutId)
    ) {
        Text(
            text = text1,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Light),
            color = contentColor,
            modifier = Modifier.alpha(1f)
        )
        Text(
            text = text2,
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold,
                fontSize = 70.sp,
                letterSpacing = 4.sp),
            color = contentColor,
            modifier = Modifier.alpha(.7f)
        )
        Text(
            text = text3,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Light),
            color = contentColor,
            modifier = Modifier.alpha(1f).padding(top = paddingValues / 2),
        )
    }
}