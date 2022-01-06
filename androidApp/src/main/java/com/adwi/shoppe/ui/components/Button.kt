package com.adwi.shoppe.ui.components

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@Composable
fun ShoppeButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    shape: Shape = RoundedCornerShape(paddingValues / 2),
    elevation: Dp = 10.dp,
    buttonColor: Color = MaterialTheme.colorScheme.primary
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressed = updateTransition(label = "Card", targetState = isPressed)
    val scaleState by pressed.animateFloat(label = "Scale") { if (it) .99f else 1f }
    val elevationState by pressed.animateDp(label = "Elevation") { if (it) elevation / 2 else elevation }

    Surface(modifier = modifier, elevation = elevationState, shape = shape) {
        Button(
            onClick = onClick,
            shape = shape,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = contentColor
            ),
            modifier = Modifier.graphicsLayer {
                scaleX = scaleState
                scaleY = scaleState
            },
        ) {
            Text(text = label,
                color = contentColor,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(paddingValues / 2))
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
fun ShoppeButtonPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(horizontal = paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)) {
            ShoppeButton(label = "Sign in", onClick = {})
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
fun ShoppeButtonPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(horizontal = paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)) {
            ShoppeButton(label = "Sign in", onClick = {})
        }
    }
}