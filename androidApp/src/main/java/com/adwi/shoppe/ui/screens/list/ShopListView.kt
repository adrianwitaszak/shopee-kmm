package com.adwi.shoppe.ui.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import comadwishoppe.Shop

@ExperimentalMaterial3Api
@Composable
fun ShopListView(
    viewModel: ShopListViewModel,
    shopSelected: (shop: Shop) -> Unit,
) {
    val lazyShopList = viewModel.shops.collectAsLazyPagingItems()

    LazyColumn(contentPadding = PaddingValues(12.dp)) {
        items(lazyShopList) { item ->
            item?.let { shop ->
                ShopListRowView(shop, shopSelected)
            }
        }
        lazyShopList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally) {
//                  CircularProgressIndicator (modifier = Modifier.padding(16.dp))
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Column(modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally) {
//                  CircularProgressIndicator (modifier = Modifier.padding(16.dp))
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyShopList.loadState.refresh as LoadState.Error
                    item {
                        Text(
                            text = e.error.localizedMessage!!,
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyShopList.loadState.append as LoadState.Error
                    item {
                        Text(
                            text = e.error.localizedMessage!!
                        )
                    }
                }
            }
        }
    }
}