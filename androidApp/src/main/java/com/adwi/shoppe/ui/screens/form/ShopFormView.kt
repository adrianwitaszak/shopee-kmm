package com.adwi.shoppe.ui.screens.form

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adwi.shoppe.cache.ActionType
import comadwishoppe.Shop
import kotlinx.coroutines.async

@Composable
fun ShopFormView(
    viewModel: ShopFormViewModel,
    shopId: String,
    action: ActionType,
    popBack: () -> Unit,
) {
    val (shop, setShop) = remember { mutableStateOf(Shop("", "", "", "", "")) }
    val isEditing = action != ActionType.CREATE
    val scope = rememberCoroutineScope()
    val label = if (isEditing) "Save" else "Create"

    LaunchedEffect(shopId) {
        try {
            if (isEditing) {
                val readShop = viewModel.getShop(shopId)
                readShop?.let {
                    setShop(it.shop)
                }
            }
        } catch (err: Exception) {
            print(err.message)
        }
    }

    suspend fun handleShop(action: ActionType) {
        when (action) {
            ActionType.CREATE -> {
                viewModel.createShop(shop)
                popBack()
            }
            ActionType.UPDATE -> {
                val updateShop = viewModel.updateShop(shop)
                updateShop?.let {
                    setShop(it)
                }
                popBack()
            }
            ActionType.DELETE -> {
                val deleted = viewModel.deleteShop(shop.id)
                if (deleted == true) popBack()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$label Shop") },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
    ) {
        shop.let {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Vertical
                    )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "Name",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                    TextField(
                        value = shop.name,
                        onValueChange = { setShop(shop.copy(name = it)) }

                    )
                    Text(
                        text = "Description",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                    TextField(
                        value = shop.description,
                        onValueChange = { setShop(shop.copy(description = it)) }
                    )
                    Text(
                        text = "Image URL",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.body1
                    )
                    TextField(
                        value = shop.imageUrl,
                        onValueChange = { setShop(shop.copy(imageUrl = it)) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        scope.async {
                            handleShop(action)
                        }
                    }, modifier = Modifier.padding(16.dp).height(320.dp)) {
                        Text(label)
                    }

                    if (isEditing) {
                        Button(onClick = {
                            scope.async {
                                handleShop(ActionType.DELETE)
                            }
                        }, modifier = Modifier.padding(16.dp).height(320.dp)) {
                            Text("Delete")
                        }
                    }

                }
            }
        }
    }
}