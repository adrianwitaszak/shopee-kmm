package com.adwi.shoppe.ui.screens.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.adwi.shoppe.ui.screens.list.ShopListRowView
import comadwishoppe.Shop

@Composable
fun FavoriteListView(
    viewModel: FavoriteListViewModel,
    shopSelected: (shop: Shop) -> Unit,
) {
    val lazyShopList = viewModel.shops.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Favorites") }) },
    ) {
        LazyColumn(contentPadding = it) {
            items(items = lazyShopList, itemContent = { favorite ->
                favorite?.let { shop ->
                    ShopListRowView(shop, shopSelected)
                }
            })
        }
    }
}