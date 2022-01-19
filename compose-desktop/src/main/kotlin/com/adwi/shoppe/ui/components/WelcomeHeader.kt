package com.adwi.shoppe.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier,
    text1: String = "Welcome to",
    text2: String = "Shoppe",
    text3: String = "for Sellers",
    contentColor: Color = MaterialTheme.colors.onBackground,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = text1,
            style = MaterialTheme.typography.h4
//                .copy(fontWeight = FontWeight.Light)
            ,
            color = contentColor,
            modifier = Modifier.alpha(1f)
        )
        Text(
            text = text2,
//            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold,
            fontSize = 70.sp,
//                letterSpacing = 4.sp),
            color = contentColor,
            modifier = Modifier.alpha(.7f)
        )
        Text(
            text = text3,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Light),
            color = contentColor,
            modifier = Modifier.alpha(1f).padding(top = 16.dp),
        )
    }
}