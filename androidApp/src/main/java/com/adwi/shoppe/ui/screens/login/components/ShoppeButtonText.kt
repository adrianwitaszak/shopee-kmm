package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Transition
import com.adwi.shoppe.ui.screens.login.LoginScreenState
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@ExperimentalAnimationApi
@Composable
fun ShoppeButtonText(
    modifier: Modifier = Modifier,
    textA: String = "Sign in",
    textB: String = "Reset",
    textC: String = "Register",
    color: Color = MaterialTheme.colorScheme.onPrimary,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    state: LoginScreenState = LoginScreenState.LOGIN,
    duration: Int = 1000,
) {
    val loginConstraintSet = ConstraintSet("""{
              a: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
                rotationX: 0,
                alpha: 1
              },
              b: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
                rotationX: -180,
                alpha: 0
              },
               c: {
                 centerHorizontally: 'parent',
                 centerVertically: 'parent',
                 rotationX: -180,
                alpha: 0
               }
        }""")

    val registerConstraintSet = ConstraintSet("""{
          a: {
             centerHorizontally: 'parent',
             centerVertically: 'parent',
             rotationX: 180,
             alpha: 0
              },
           b: {
             centerHorizontally: 'parent',
             centerVertically: 'parent',
             rotationX: -180,
             alpha: 0
           },
           c: {
             centerHorizontally: 'parent',
             centerVertically: 'parent',
             rotationX: 0,
             alpha: 1
           }
        }""")

    val forgotConstraintSet = ConstraintSet("""{
          a: {
                centerHorizontally: 'parent',
                centerVertically: 'parent',
                rotationX: 180,
                alpha: 0
              },
           b: {
             centerHorizontally: 'parent',
             centerVertically: 'parent',
             rotationX: 0,
             alpha: 1
           },
           c: {
             centerHorizontally: 'parent',
             centerVertically: 'parent',
             rotationX: -180,
             alpha: 0
           }
        }""")

    val keyframes = Transition("""
            {
              Header: { 'animated text' : 'asdasd'},
              KeyFrames: {
                KeyAttributes: [
                  {
                  target: ['a'],
                  frames: [0, 50, 100],
                  rotationX: [0, 90, 90],
                  },
                  {
                  target: ['b', 'c'],
                  frames: [0, 50, 100],
                  rotationX: [90, 90, 0],
                  }
                ]
              }
            }
            """)

    val constraints = when (state) {
        LoginScreenState.LOGIN -> loginConstraintSet
        LoginScreenState.REGISTER -> registerConstraintSet
        LoginScreenState.FORGOT -> forgotConstraintSet
        LoginScreenState.COMPLETE -> forgotConstraintSet
    }

    ConstraintLayout(
        constraintSet = constraints,
        animateChanges = true,
        animationSpec = tween(duration),
        modifier = modifier,
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
        Text(
            text = textC,
            color = color,
            style = style,
            modifier = modifier
                .padding(paddingValues / 2)
                .layoutId("c")
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
            ShoppeButtonText()
        }
    }
}