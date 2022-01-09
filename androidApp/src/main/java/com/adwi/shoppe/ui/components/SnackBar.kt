package com.adwi.shoppe.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.paddingValues
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun ShoppeSnackBar(
    snackBarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = RoundedCornerShape(percent = 35),
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    actionColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = 20.dp,
) {
    Snackbar(
        snackbarData = snackBarData,
        modifier = modifier,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        actionColor = actionColor,
        elevation = elevation
    )
}

@Composable
fun ShoppeSnackBarHost(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState = SnackbarHostState(),
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier
            .wrapContentWidth(align = Alignment.Start)
            .widthIn(max = 550.dp)
            .padding(horizontal = paddingValues / 2)
            .padding(bottom = paddingValues * 3)
            .systemBarsPadding()
            .navigationBarsPadding(),
        snackbar = { snackbarData -> ShoppeSnackBar(snackbarData) }
    )
}