package com.adwi.shoppe.ui.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import comadwishoppe.Shop

@Composable
fun ShopListRowView(shop: Shop, shopSelected: (shop: Shop) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = { shopSelected(shop) }).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(modifier = Modifier.size(50.dp), shape = CircleShape) {
            Image(
                painter = rememberImagePainter(data = shop.imageUrl),
                contentDescription = shop.name,
                contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(shop.name, style = MaterialTheme.typography.titleMedium)
            Text(shop.description,
                style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
    Divider()
}