package com.adwi.shoppe.ui.screens.review

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.cache.ActionType
import comadwishoppe.Review
import kotlinx.coroutines.async

@Composable
fun ReviewFormView(
    viewModel: ReviewFormViewModel,
    reviewId: String,
    shopId: String,
    action: ActionType,
    popBack: () -> Unit,
) {
    val (review, setReview) = remember { mutableStateOf(Review("", "", "", "", 0)) }
    val isEditing = action != ActionType.CREATE
    val scope = rememberCoroutineScope()
    val label = if (isEditing) "Save" else "Create"

    LaunchedEffect(reviewId) {
        try {
            if (isEditing) {
                val readReview = viewModel.getReview(reviewId)
                readReview?.let {
                    setReview(it)
                }
            }
        } catch (err: Exception) {
            print(err.message)
        }
    }

    suspend fun handleReview(action: ActionType) {
        when (action) {
            ActionType.CREATE -> {
                viewModel.createReview(shopId, review)
                popBack()
            }
            ActionType.UPDATE -> {
                val updateShop = viewModel.updateReview(review)
                updateShop?.let {
                    setReview(it)
                }
                popBack()
            }
            ActionType.DELETE -> {
                val deleted = viewModel.deleteReview(review.id)
                if (deleted == true) popBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$label Review") },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
    ) {
        review.let {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Description",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                    TextField(
                        value = review.text,
                        onValueChange = { setReview(review.copy(text = it)) }

                    )
                    Text(
                        text = "Rating",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )

                    Row {
                        List(5) {
                            val rating = it + 1
                            if (rating <= review.rating.toInt()) {
                                IconButton(onClick = {
                                    setReview(review.copy(rating = rating.toLong()))
                                }) {
                                    Icon(Icons.Filled.Star,
                                        tint = MaterialTheme.colors.primary,
                                        contentDescription = null)
                                }
                            } else {
                                IconButton(onClick = {
                                    setReview(review.copy(rating = rating.toLong()))
                                }) {
                                    Icon(Icons.Filled.Star, tint = Color(0xFFd3d3d3), contentDescription = null)
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            scope.async {
                                handleReview(action)
                            }
                        }, modifier = Modifier
                            .padding(16.dp)
                            .height(320.dp)
                    ) {
                        Text(label)
                    }

                    if (isEditing) {
                        Button(
                            onClick = {
                                scope.async {
                                    handleReview(ActionType.DELETE)
                                }
                            }, modifier = Modifier
                                .padding(16.dp)
                                .height(320.dp)
                        ) {
                            Text("Delete")
                        }
                    }

                }
            }
        }
    }
}