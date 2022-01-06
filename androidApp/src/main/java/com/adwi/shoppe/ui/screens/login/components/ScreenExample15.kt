package com.adwi.shoppe.ui.screens.login.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionLayoutDebugFlags
import androidx.constraintlayout.compose.Transition
import java.util.*

@Preview(group = "motion7")
@Composable
fun ScreenExample15() {
    var animateToEnd by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        targetValue = if (animateToEnd) 1f else 0f,
        animationSpec = tween(6000)
    )
    Column {
        MotionLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.White),
            start = ConstraintSet("""
            {
              a: {
                width: 40,
                height: 40,
                start: ['parent', 'start', 16],
                bottom: ['parent', 'bottom', 16]
              }
            }
            """
            ),
            end = ConstraintSet(
                """
            {
              a: {
                width: 150,
                height: 100,
                rotationZ: 390,
                end: ['parent', 'end', 16],
                top: ['parent', 'top', 16]
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
                   frames: [25,50,75],
                   percentX: [0.6, 0.8, 0.1],
                   percentY: [0.4, 0.8, 0.0]
                }
                ]
              }
            }
            """),
            debug = EnumSet.of(MotionLayoutDebugFlags.SHOW_ALL),
            progress = progress) {
            Box(modifier = Modifier
                .layoutId("a")
                .background(Color.Red))
        }

        Button(onClick = { animateToEnd = !animateToEnd }) {
            Text(text = "Run")
        }
    }
}