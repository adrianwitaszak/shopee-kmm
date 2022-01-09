package com.adwi.shoppe.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.adwi.shoppe.ui.theme.*

enum class AnimatedShadeBackgroundState { FIRST, SECOND, THIRD }

@Composable
fun AnimatedShadeBackground(
    state: AnimatedShadeBackgroundState = AnimatedShadeBackgroundState.FIRST,
    duration: Int = 1000,
    debug: Boolean = false,
    content: @Composable () -> Unit,
) {
    val firstConstraintSet = """{
            shadeViolet: {
                top: ['parent', 'bottom', -200],
                end: ['parent', 'start', -200],
            },
            shadeGreen: {
                top: ['parent', 'top', -100],
                start: ['parent', 'end', -300],
            },
            shadePink: {
                top: ['parent', 'top', -300],
                bottom: ['parent', 'bottom', -200],
                end: ['parent', 'start', -200],
            },
            shadeBlue: {
                bottom: ['parent', 'bottom', 0],
                start: ['parent', 'end', -200],
            }
        }"""
    val secondConstraintSet = """{
            shadeViolet: {
                bottom: ['parent', 'bottom', -200],
                start: ['parent', 'end', -200],
            },
            shadeGreen: {
                start: ['parent', 'start', -100],
                bottom: ['parent', 'bottom', 100],
            },
            shadePink: {
                top: ['parent', 'top', -200],
                end: ['parent', 'start', -200],
            },
            shadeBlue: {
                bott: ['parent', 'bottom', -200],
                start: ['parent', 'end', -200],
            }
        }"""
    val thirdConstraintSet = """{
            shadeViolet: {
                top: ['parent', 'bottom', -200],
                end: ['parent', 'start', -200],
            },
            shadeGreen: {
                top: ['parent', 'top', 100],
                end: ['parent', 'end', -100],
            },
            shadePink: {
                top: ['parent', 'top', 0],
                end: ['parent', 'start', -100],
            },
            shadeBlue: {
                bottom: ['parent', 'bottom', -100],
                start: ['parent', 'end', -200],
            }
        }"""

    val constraints = when (state) {
        AnimatedShadeBackgroundState.FIRST -> firstConstraintSet
        AnimatedShadeBackgroundState.SECOND -> secondConstraintSet
        AnimatedShadeBackgroundState.THIRD -> thirdConstraintSet
    }
    Box(modifier = Modifier.fillMaxSize()) {

        ConstraintLayout(
            constraintSet = ConstraintSet(constraints),
            animateChanges = true,
            animationSpec = tween(duration),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ShoppeShade(
                layoutId = "shadeViolet",
                color = ShadeRed,
                text = if (debug) "violet" else "",
            )
            ShoppeShade(
                layoutId = "shadeGreen",
                color = ShadeGreen,
                text = if (debug) "green" else "",
            )
            ShoppeShade(
                layoutId = "shadePink",
                color = ShadePink,
                text = if (debug) "pink" else "",
            )
            ShoppeShade(
                layoutId = "shadeBlue",
                color = ShadeBlue,
                text = if (debug) "blue" else "",
            )
        }
        content()
    }
}

fun AnimatedShadeBackgroundState.toggleState() = when (this) {
    AnimatedShadeBackgroundState.FIRST -> AnimatedShadeBackgroundState.SECOND
    AnimatedShadeBackgroundState.SECOND -> AnimatedShadeBackgroundState.THIRD
    AnimatedShadeBackgroundState.THIRD -> AnimatedShadeBackgroundState.FIRST
}

@Preview(showBackground = true, name = "Light")
@Composable
private fun AnimatedShadeBackgroundPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            AnimatedShadeBackground {}
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
private fun AnimatedShadeBackgroundPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            AnimatedShadeBackground {}
        }
    }
}