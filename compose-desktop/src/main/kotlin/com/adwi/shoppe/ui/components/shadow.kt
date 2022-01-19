package com.adwi.shoppe.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
fun Modifier.shadowCustom(
    elevation: Dp = 10.dp,
    shape: Shape = RoundedCornerShape(percent = 20),
    clip: Boolean = elevation > 0.dp,
    color: Color,
) = if (elevation > 0.dp || clip) {
    inspectable(
        inspectorInfo = debugInspectorInfo {
            name = "shadow"
            properties["elevation"] = elevation
            properties["shape"] = shape
            properties["clip"] = clip
        }
    ) {
//        background(color = color)
        graphicsLayer {
            this.shadowElevation = elevation.toPx()
            this.shape = shape
            this.clip = clip
        }
    }
} else this