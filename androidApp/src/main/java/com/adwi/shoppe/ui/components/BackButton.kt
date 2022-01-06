package com.adwi.shoppe.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.Dimensions
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@ExperimentalMaterialApi
@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    icon: ImageVector = Icons.Outlined.ArrowBack,
    tint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
    contentDescription: String = "Back",
    size: Dp = Dimensions.Button,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val transition = updateTransition(targetState = isPressed, label = "Button state")

    val scaleState by transition.animateFloat(
        label = "Scale", transitionSpec = { tween(300) }
    ) { state ->
        if (state) .9f else 1.2f
    }
    val colorState by transition.animateColor(label = "Color") { state ->
        if (state) MaterialTheme.colorScheme.secondary else tint
    }

    Surface(
        onClick = onClick,
        interactionSource = interactionSource,
        indication = null,
        color = Color.Transparent,
        modifier = Modifier
            .layoutId(layoutId)
            .size(size)
            .padding(start = 4.dp, end = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = colorState,
            modifier = modifier
                .padding(horizontal = paddingValues / 2)
                .graphicsLayer(
                    scaleX = scaleState,
                    scaleY = scaleState
                )
        )
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, name = "Light")
@Composable
private fun BackButtonPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            BackButton(onClick = {})
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, name = "Dark")
@Composable
private fun BackButtonPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            BackButton(onClick = {})
        }
    }
}