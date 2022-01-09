package com.adwi.shoppe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@Composable
fun ScreenHeader(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Light,
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .layoutId(layoutId)
            .alpha(.8f)
    )
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun ScreenHeaderPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ScreenHeader(text = "Dashboard")
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
private fun ScreenHeaderPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ScreenHeader(text = "Dashboard")
        }
    }
}