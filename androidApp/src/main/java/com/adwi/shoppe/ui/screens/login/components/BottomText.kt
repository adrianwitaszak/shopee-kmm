package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomText(
    modifier: Modifier = Modifier,
    message: String = "Don't have an account?",
    onClick: () -> Unit,
    buttonText: String = "Sign up",
) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Text(
                text = message,
                modifier = Modifier.alpha(.7f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Surface(onClick = onClick) {
                Text(
                    text = buttonText,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
}