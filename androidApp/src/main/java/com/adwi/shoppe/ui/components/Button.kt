package com.adwi.shoppe.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.Transition
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@Composable
fun ShoppeButton(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    label: @Composable () -> Unit,
    onClick: () -> Unit,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    shape: Shape = RoundedCornerShape(paddingValues / 2),
    elevation: Dp = 10.dp,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val pressed = updateTransition(label = "Card", targetState = isPressed)
    val scaleState by pressed.animateFloat(label = "Scale") { if (it) .99f else 1f }
    val elevationState by pressed.animateDp(label = "Elevation") { if (it) elevation / 2 else elevation }

    Surface(modifier = modifier.layoutId(layoutId), elevation = elevationState, shape = shape) {
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
            label()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ShoppeButtonText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    visibility: Boolean = true,
) {
    var animateToEnd by remember { mutableStateOf(visibility) }

    // Animation progress
    val progressFloat by animateFloatAsState(
        targetValue = if (visibility) 1f else 0f,
        animationSpec = tween(3000)
    )
    MotionLayout(
        progress = progressFloat,
        start = ConstraintSet("""
            {
              a: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
                alpha: 1,
                rotationX: 0
              },
              b: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
                alpha: 0,
                rotationX: 90
              }
            }
            """
        ),
        end = ConstraintSet(
            """
            {
              a: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
                alpha: 0,
                rotationX: 90
              },
              b: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
                alpha: 1,
                rotationX: 0
              }
            }
            """
        ),
        transition = Transition("""
            {
              KeyFrames: {
                KeyPositions: [
                {
                   target: ['a'],
                   frames: [50],
                   percentX: [1],
                   percentY: [1]
                }
                ]
              }
            }
            """),
        modifier = modifier
    ) {
        Text(
            text = "Sign in",
            color = color,
            style = style,
            modifier = modifier
                .padding(paddingValues / 2)
                .layoutId("a")
        )
        Text(
            text = "Reset",
            color = color,
            style = style,
            modifier = modifier
                .padding(paddingValues / 2)
                .layoutId("b")
        )
    }

}

@Preview(showBackground = true, name = "Light")
@Composable
fun ShoppeButtonPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(horizontal = paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)) {
            ShoppeButton(label = { Text("Sign in") }, onClick = {})
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
fun ShoppeButtonPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(horizontal = paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)) {
            ShoppeButton(label = { Text("Sign in") }, onClick = {})
        }
    }
}