package com.adwi.shoppe.ui.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.Dimensions
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
    leadingIcon: ImageVector = Icons.Filled.Email,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onSecondary,
    cursorColor: Color = Pink80,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    layoutId: String = "",
) {
    val focusRequester = FocusRequester()
    var focusState by remember { mutableStateOf(false) }

    val scaleState by animateFloatAsState(
        targetValue = if (focusState) 1.02f else 1f,
        animationSpec = tween(500)
    )

    val transition = updateTransition(targetState = text.isNotEmpty(), label = "No text")
    val alpha by transition.animateFloat(label = "", transitionSpec = { tween(300) }
    ) { if (it) .5f else 0f }

    Surface(
        shape = shape,
        elevation = elevation,
        color = backgroundColor,
        modifier = modifier
            .layoutId(layoutId)
            .graphicsLayer {
                scaleY = scaleState
                scaleX = scaleState
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TextField(
                    value = text,
                    onValueChange = onTextChange,
                    label = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyMedium.copy(color = contentColor),
                        )
                    },
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = contentColor,
                            modifier = Modifier
                        )
                    },
                    trailingIcon = {
//                        if (text.isNotEmpty()) {
                        Surface(
                            onClick = { if (text.isNotEmpty()) onTextChange("") },
                            color = backgroundColor,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear text input",
                                tint = cursorColor,
                                modifier = Modifier.alpha(alpha)
                            )
                        }
//                        }
                    },
                    visualTransformation = visualTransformation,
                    keyboardActions = keyboardActions,
                    keyboardOptions = keyboardOptions,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = contentColor),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = cursorColor,
                        backgroundColor = backgroundColor,
                        unfocusedIndicatorColor = backgroundColor,
                        focusedIndicatorColor = backgroundColor
                    ),

                    modifier = Modifier
                        .padding(horizontal = paddingValues, vertical = Dimensions.small)
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState = it.isFocused },
                )
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









