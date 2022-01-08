package com.adwi.shoppe.ui.screens.login.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@Composable
fun Header(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    text: String,
    fontSize: TextUnit = 50.sp,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
        ),
        modifier = modifier
            .layoutId(layoutId)
            .alpha(.8f)
    )
}

@Preview(showBackground = true, name = "Light")
@Composable
fun HeaderPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            Header(text = "Register")
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
fun HeaderPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            Header(text = "Register")
        }
    }
}