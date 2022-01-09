package com.adwi.shoppe.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@Composable
fun ComingSoonText(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(percent = 35),
    elevation: Dp = 20.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    gradientColor: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    val elevationState by animateDpAsState(
        targetValue = if (state.targetState) elevation else 0.dp,
        animationSpec = tween(1000)
    )
    val horizontalGradientBrush = Brush.linearGradient(
        colors = listOf(gradientColor, backgroundColor)
    )

    Card(
        elevation = elevationState,
        shape = shape,
        backgroundColor = Color.Transparent,
        modifier = modifier
            .padding(paddingValues)
    ) {
        Box(
            modifier = Modifier
                .clip(shape)
                .background(horizontalGradientBrush)
        ) {
            Text(
                text = "Coming soon..",
                style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .padding(paddingValues)
                    .alpha(.7f),
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun ComingSoonTextPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ComingSoonText()
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
private fun ComingSoonTextPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ComingSoonText()
        }
    }
}