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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wishlistfeatures.components.CardWishlist
import com.nabiha.common.utils.navigateToDetailScreen
import com.nabiha.designsystem.theme.OptixTheme

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
        contentPadding = PaddingValues(16.dp)
    )
    {
        item {
            Text(
                text = "My Wishlist",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(10) {
            CardWishlist(
                title = "Purple Glasses",
                price = "Rp155.000",
                imageUrl = "https://i.pinimg.com/564x/a5/67/92/a567923a663362b33af3f9741db8ec93.jpg",
                like = true
            ) {
                navController.navigateToDetailScreen()
            }
        }
    }
}
@Composable
@Preview
private fun WishlistScreenPrv() {
    val navController = rememberNavController()
    OptixTheme {
        WishlistScreen(navController = navController)
    }
}