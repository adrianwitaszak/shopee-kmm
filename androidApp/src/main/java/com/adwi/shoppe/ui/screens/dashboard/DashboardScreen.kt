package com.adwi.shoppe.ui.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.components.*
import com.adwi.shoppe.ui.theme.paddingValues
import com.adwi.shoppe.util.Event
import com.google.accompanist.insets.statusBarsPadding

enum class DashboardState { FIRST, SECOND }

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onSignOutClick: () -> Unit
) {
    var state by remember { mutableStateOf(DashboardState.FIRST) }
    var debug by remember { mutableStateOf(false) }
    var backgroundState by remember { mutableStateOf(AnimatedShadeBackgroundState.FIRST) }

    viewModel.setEvent(Event.ShowSnackBar("test snackbar"))
    AnimatedShadeBackground(
        state = backgroundState,
        debug = debug
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(paddingValues),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(paddingValues)
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.headlineLarge
                        .copy(color = MaterialTheme.colorScheme.onBackground)
                )
                ShoppeButton(
                    onClick = onSignOutClick,
                    label = {
                        Text(
                            text = "Sign out",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .height(50.dp),
                )
                ShoppeButton(
                    onClick = {
                        backgroundState = backgroundState.toggleState()
                        viewModel.setEvent(Event.ShowSnackBar(backgroundState.name))
                    },
                    label = {
                        Text(
                            text = "State = ${backgroundState.name}",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .height(50.dp),
                )
                ShoppeButton(
                    onClick = { debug = !debug },
                    label = {
                        Text(
                            text = "Debug = $debug",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .height(50.dp),
                )
            }
            ComingSoonText(modifier = Modifier.align(Alignment.Center))
        }
    }
}

private fun DashboardState.toggleState() = when (this) {
    DashboardState.FIRST -> DashboardState.SECOND
    DashboardState.SECOND -> DashboardState.FIRST
}