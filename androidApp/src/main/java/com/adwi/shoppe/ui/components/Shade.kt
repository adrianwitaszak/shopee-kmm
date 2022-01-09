package com.adwi.shoppe.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.PrimaryShadowLight
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues


@Composable
fun ShoppeShade(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    text: String = "",
    size: Dp = 400.dp,
    cornerRadius: Dp = size,
    shadowRadius: Dp = size,
    offset: Dp = 0.dp,
    alpha: Float = .2f,
    color: Color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = modifier
            .layoutId(layoutId)
            .size(size)
            .neumorphicShadow(
                cornerRadius = cornerRadius,
                shadowRadius = shadowRadius,
                color = color,
                alpha = if (isSystemInDarkTheme()) alpha / 2 else alpha,
                offset = offset
            )
    ) {
        Text(
            text = text,
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
                .copy(color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@SuppressLint("UnnecessaryComposedModifier", "NewApi")
fun Modifier.neumorphicShadow(
    color: Color = PrimaryShadowLight,
    alpha: Float = .99f,
    cornerRadius: Dp = 30.dp,
    shadowRadius: Dp = 10.dp,
    offset: Dp = 0.dp,
) = composed {

    this.drawBehind {
        val transparentColor =
            android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
        val shadowColorTop =
            android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())

        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparentColor

            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offset.toPx(),
                offset.toPx(),
                shadowColorTop
            )
            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                cornerRadius.toPx(),
                cornerRadius.toPx(),
                paint
            )
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun NeumorphicPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(paddingValues)
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .neumorphicShadow(),
                content = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
private fun NeumorphicPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(paddingValues)
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .neumorphicShadow(),
                content = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "light")
@Composable
fun ShoppeShadePreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ShoppeShade()
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
fun ShoppeShadePreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ShoppeShade()
        }
    }
}