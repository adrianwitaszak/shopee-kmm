package com.adwi.shoppe.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.ShoppeTheme

@Composable
fun ShoppeButton(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    label: @Composable () -> Unit,
    onClick: () -> Unit,
    contentColor: Color = MaterialTheme.colors.onPrimary,
    shape: Shape = RoundedCornerShape(percent = 20),
    elevation: Dp = 10.dp,
    buttonColor: Color = MaterialTheme.colors.primary,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressed = updateTransition(label = "Card", targetState = isPressed)
    val scaleState by pressed.animateFloat(label = "Scale") { if (it) .99f else 1f }
    val elevationState by pressed.animateDp(label = "Elevation") { if (it) elevation / 2 else elevation }

    Surface(
        elevation = elevationState,
        shape = shape,
        modifier = modifier
            .width(200.dp)
            .height(70.dp)
            .graphicsLayer {
                scaleX = scaleState
                scaleY = scaleState
            },
    ) {
        Button(
            onClick = onClick,
            shape = shape,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor,
                contentColor = contentColor
            ),
            modifier = Modifier.fillMaxSize(),
        ) {
            label()
        }
    }
}

@Preview()
@Composable
fun ShoppeButtonPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)) {
            ShoppeButton(label = { Text("Sign in") }, onClick = {})
        }
    }
}

@Preview()
@Composable
fun ShoppeButtonPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colors.background).padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)) {
            ShoppeButton(label = { Text("Sign in") }, onClick = {})
        }
    }
}