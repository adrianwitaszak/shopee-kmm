package com.adwi.shoppe.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.adwi.shoppe.ui.theme.PrimaryMedium
import com.adwi.shoppe.ui.theme.PrimaryShadowDark
import com.adwi.shoppe.ui.theme.TransparentColor

@Preview(group = "motion10")
@Composable
fun ScreenExample18(modifier: Modifier = Modifier) {
    var animateToEnd by remember { mutableStateOf(false) }

    // Animation progress
    val progressFloat by animateFloatAsState(
        targetValue = if (animateToEnd) 1f else 0f,
        animationSpec = tween(3000)
    )
    
    // Canvas
    Column {
        MotionLayout(
            modifier = modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(PrimaryMedium),
            motionScene = MotionScene("""{
  ConstraintSets: {
    start: {
      a: {
        width: 100,
        height: 100,
        start: ['parent', 'start', 22],
        top: ['c', 'top', 0],
      },
      b: {
        width: 100,
        height: 100,
        start: ['parent', 'start', 40],
        top: ['c', 'top', 20],
      },
      c: {
        width: 100,
        height: 100,
        start: ['parent', 'start', 24],
        centerVertically: 'parent'
      }
    },
    end: {
      a: {
        width: 100,
        height: 100,
        end: ['parent', 'end', 5],
        top: ['c', 'top', 0],
      },
      b: {
        width: 100,
        height: 100,
        end: ['parent', 'end', 22],
        top: ['c', 'top', 20],
      },
      c: {
        width: 100,
        height: 100,
        end: ['parent', 'end', 24],
        centerVertically: 'parent'
      }
    }
  }
}
"""),
            progress = progressFloat) {
            // Light shadow
            Box(
                modifier = Modifier
                    .layoutId("a")
                    .background(TransparentColor)
                    .neumorphicShadow()
            )
            // Darker shadow
            Box(
                modifier = Modifier
                    .layoutId("b")
                    .background(TransparentColor)
                    .neumorphicShadow(
                        topShadowLight = PrimaryShadowDark
                    )
            )
            // Box
            Surface(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .layoutId("c")
            ) {}
        }

        Button(onClick = { animateToEnd = !animateToEnd }) {
            Text(text = "Run")
        }
    }
}