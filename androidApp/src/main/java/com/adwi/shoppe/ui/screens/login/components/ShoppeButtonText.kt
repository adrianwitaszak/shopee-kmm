package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.Transition
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@ExperimentalAnimationApi
@Composable
fun ShoppeButtonText(
    modifier: Modifier = Modifier,
    textA: String = "Sign in",
    textB: String = "Reset",
    color: Color = MaterialTheme.colorScheme.onPrimary,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    visibility: Boolean = true,
) {
    val progressFloat by animateFloatAsState(
        targetValue = if (visibility) 1f else 0f,
        animationSpec = tween(1000)
    )
    MotionLayout(
        progress = progressFloat,
        start = ConstraintSet("""
            {
              a: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
//                alpha: 1,
                rotationX: 0
              },
              b: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
//                alpha: 0,
                rotationX: -90
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
//                alpha: 0,
                rotationX: 90
              },
              b: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
//                alpha: 1,
                rotationX: 0
              }
            }
            """
        ),
        transition = Transition("""
            {
              KeyFrames: {
                KeyAttributes: [
                  {
                  target: ['a'],
                  frames: [0, 50, 100],
                  rotationX: [0, 90, 90],
                  },
                  {
                  target: ['b'],
                  frames: [0, 50, 100],
                  rotationX: [90, 90, 0],
                  }
                ]
              }
            }
            """),
        modifier = modifier
    ) {
        Text(
            text = textA,
            color = color,
            style = style,
            modifier = modifier
                .padding(paddingValues / 2)
                .layoutId("a")
        )
        Text(
            text = textB,
            color = color,
            style = style,
            modifier = modifier
                .padding(paddingValues / 2)
                .layoutId("b")
        )
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true, name = "Light")
@Composable
fun ShoppeButtonTextPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ShoppeButtonText(visibility = false)
        }
    }
}