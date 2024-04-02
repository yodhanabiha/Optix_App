package com.example.wishlistfeatures.wishlistScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.wishlistfeatures.components.CardWishlist
import com.nabiha.homefeatures.detail.navigateToDetailScreen

@Composable
internal fun WishlistScreenRoute(
    navController: NavHostController
) {
    WishlistScreen(navController)
}

@Composable
private fun WishlistScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
    )
    {
        item {
            Text(
                text = "Wishlist",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(10) {
            CardWishlist(
                title = "Purple Glasses",
                price = "Rp155.000",
                imageUrl = "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/6/1/021c1d56-cca6-482a-91b5-e16aa4af4de2.jpg",
                like = true
            ) {
                navController.navigateToDetailScreen()
            }
        }
    }
}