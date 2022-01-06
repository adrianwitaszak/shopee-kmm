package com.adwi.shoppe.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.adwi.shoppe.ui.screens.review.ReviewListView
import comadwishoppe.Review
import comadwishoppe.Shop

@Composable
fun ShopDetailView(
    viewModel: ShopDetailViewModel,
    shopId: String,
    editShopSelected: (shopId: String) -> Unit,
    editReviewSelected: (reviewId: String) -> Unit,
    createReviewSelected: (shopId: String) -> Unit,
    popBack: () -> Unit,
) {
    val (shop, setShop) = remember { mutableStateOf(Shop("", "", "", "", "")) }
    val (reviews, setReviews) = remember { mutableStateOf(emptyList<Review>()) }
    val (isFavorite, setIsFavorite) = remember { mutableStateOf(false) }
    val (userId, setUserId) = remember { mutableStateOf("") }

    val userState by viewModel.userState.collectAsState()

    LaunchedEffect(shopId) {
        val favShop = viewModel.getFavorite(shopId)
        val isFavoriteShop = favShop != null
        setIsFavorite(isFavoriteShop)
        userState?.let {
            setUserId(it.userId)
        }
        try {
            // Read Shop from local database
            if (isFavoriteShop) {
                setShop(favShop!!)
            }
            val readShop = viewModel.getShop(shopId)
            readShop?.let {
                setShop(it.shop)
                setReviews(it.reviews)
                // Update Favorite Shop
                if (isFavoriteShop) {
                    viewModel.updateFavorite(shop)
                }
            } ?: run {
                popBack()
            }
        } catch (err: Exception) {
            print(err.message)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(shop.name) },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Toggle Favorite
                        if (isFavorite) {
                            viewModel.removeFavorite(shopId)
                        } else {
                            viewModel.saveFavorite(shop)
                        }
                        setIsFavorite(!isFavorite)
                    }) {
                        if (isFavorite) {
                            Icon(Icons.Outlined.Delete, null)
                        } else {
                            Icon(Icons.Filled.Favorite, null)
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            if (userId == shop.userId) {
                FloatingActionButton(onClick = {
                    editShopSelected(shopId)
                }, backgroundColor = MaterialTheme.colors.primary) {
                    Icon(Icons.Outlined.Create, null)
                }
            }
        },
    ) {
        Surface(color = Color.White) {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Surface(color = Color.White) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val imageUrl = shop.imageUrl
                        Card(
                            modifier = Modifier.size(150.dp),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Image(painter = rememberImagePainter(imageUrl),
                                contentScale = ContentScale.Crop,
                                contentDescription = shop.name
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Summary", style = typography.h5,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )

                Text(
                    shop.description, style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Text(
                        "Reviews", style = typography.h5,
                    )
                    if (userId.isNotEmpty()) {
                        Button(onClick = {
                            createReviewSelected(shopId)
                        }) {
                            Icon(Icons.Outlined.Add, null)
                        }
                    }
                }

                Surface(color = Color.White) {
                    ReviewListView(
                        reviews,
                        userId,
                        editReviewSelected = {
                            editReviewSelected(it.id)
                        }
                    )
                }
            }
        }
    }
}
