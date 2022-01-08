package com.adwi.shoppe.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onSignOutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(text = "Home", color = Color.Black)
        Button(onClick = onSignOutClick) {
            Text(text = "Sign out", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}