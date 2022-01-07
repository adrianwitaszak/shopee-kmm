package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

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
            fontSize = fontSize
        ),
        modifier = modifier.layoutId(layoutId)
    )
}