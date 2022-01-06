package com.adwi.shoppe.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.Pink80
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@ExperimentalMaterialApi
@Composable
fun ShoppeTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String = "",
    shape: Shape = RoundedCornerShape(paddingValues / 2),
    elevation: Dp = 10.dp,
    leadingIcon: ImageVector? = Icons.Filled.Email,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onSecondary,
    cursorColor: Color = Pink80,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    layoutId: String = "",
) {
    val transition = updateTransition(targetState = text.isEmpty(), label = "No text")
    val alpha by transition.animateFloat(label = "", transitionSpec = { tween(100) }
    ) { if (it) .5f else 0f }

    Surface(
        modifier = modifier.layoutId(layoutId),
        shape = shape,
        elevation = elevation,
        color = backgroundColor,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            leadingIcon?.let {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.padding(start = paddingValues)
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = text,
                    onValueChange = onTextChange,
                    singleLine = true,
                    maxLines = 1,
                    visualTransformation = visualTransformation,
                    keyboardActions = keyboardActions,
                    keyboardOptions = keyboardOptions,
                    cursorBrush = SolidColor(cursorColor),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = contentColor),
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxWidth()
                )
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium.copy(color = contentColor),
                    modifier = Modifier
                        .alpha(alpha)
                        .padding(start = paddingValues + 4.dp)
                        .align(Alignment.CenterStart)
                )
            }
            AnimatedVisibility(visible = text.isNotEmpty()) {
                Surface(
                    onClick = { onTextChange("") },
                    color = backgroundColor
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear text input",
                        tint = cursorColor,
                        modifier = Modifier.padding(horizontal = paddingValues)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, name = "Light")
@Composable
fun ShoppeTextFieldPreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ShoppeTextField(text = "Email", onTextChange = {})
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true, name = "Dark")
@Composable
fun ShoppeTextFieldPreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ShoppeTextField(text = "Email", onTextChange = {})
        }
    }
}









