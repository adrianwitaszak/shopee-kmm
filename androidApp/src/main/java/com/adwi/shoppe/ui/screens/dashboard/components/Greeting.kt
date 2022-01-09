package com.adwi.shoppe.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues
import com.adwi.shoppe.util.Emoji

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    name: String,
    message: String = "Have a nice day at work ${Emoji.smiley}",
    layoutId: String = "",
    color: Color = MaterialTheme.colorScheme.onBackground,
    alpha: Float = .8f,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier.layoutId(layoutId)
    ) {
        Text(
            text = "Hello $name",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            letterSpacing = -(2.sp),
            lineHeight = 20.sp,
            modifier = modifier
                .layoutId(layoutId)
                .alpha(.8f)
        )
        Text(
            text = message,
            color = color,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Light,
            letterSpacing = -(1.sp),
            lineHeight = 20.sp,
            modifier = Modifier.alpha(alpha)
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun GreetingPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            Greeting(
                name = "Adrian"
            )
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
private fun GreetingPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            Greeting(name = "Adrian")
        }
    }
}