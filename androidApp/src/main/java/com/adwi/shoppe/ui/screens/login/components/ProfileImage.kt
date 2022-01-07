package com.adwi.shoppe.com.adwi.shoppe.ui.screens.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.ui.theme.ShoppeTheme
import com.adwi.shoppe.ui.theme.paddingValues

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    layoutId: String = "",
    shape: Shape = RoundedCornerShape(paddingValues),
    elevation: Dp = 10.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.inversePrimary,
    size: Dp = 150.dp,
) {
    var inChangePictureMode by remember { mutableStateOf(false) }

    // Temp image impl
    Box(
        modifier = modifier
            .layoutId(layoutId)
            .size(size)
    ) {
        Card(
            elevation = elevation,
            shape = shape,
            backgroundColor = backgroundColor,
            modifier = Modifier
                .fillMaxSize(.9f),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Picture",
                    modifier = Modifier
                        .fillMaxSize(.7f)
                        .align(Alignment.BottomCenter)
                )
                Icon(
                    imageVector = Icons.Rounded.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier
                        .padding(paddingValues / 2)
                        .size(20.dp)
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
fun ProfileImagePreviewLight() {
    ShoppeTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ProfileImage()
        }
    }
}

@Preview(showBackground = true, name = "Dark")
@Composable
fun ProfileImagePreviewDark() {
    ShoppeTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(paddingValues)
        ) {
            ProfileImage()
        }
    }
}