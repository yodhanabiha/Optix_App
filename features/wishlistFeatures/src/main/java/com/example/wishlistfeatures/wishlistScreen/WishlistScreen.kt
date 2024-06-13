package com.example.wishlistfeatures.wishlistScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wishlistfeatures.components.CardWishlist
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.formatPrice
import com.nabiha.common.utils.navigateToCartScreen
import com.nabiha.common.utils.navigateToDetailScreen
import com.nabiha.designsystem.theme.OptixTheme
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
internal fun WishlistScreenRoute(
    navController: NavHostController
) {
    WishlistScreen(navController)
}

@Composable
private fun WishlistScreen(
    navController: NavHostController,
    viewModel: WishlistViewModel = hiltViewModel()
) {

    val wishlistUiState by viewModel.wishListUiState.collectAsStateWithLifecycle()

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
        when(wishlistUiState){
            is WishListUiState.Error -> Timber.e((wishlistUiState as WishListUiState.Error).message)
            WishListUiState.Loading -> {}
            is WishListUiState.Success -> {
                val wishlist = (wishlistUiState as WishListUiState.Success).data
                items(wishlist) {wish->
                    var likeStatus by remember {
                        mutableStateOf(true)
                    }
                    CardWishlist(
                        title = wish.product.title,
                        price = formatPrice(wish.product.price),
                        imageUrl = UrlApiService.default+wish.product.imageurl,
                        like = likeStatus,
                        onClick = {navController.navigateToDetailScreen(wish.product.id) },
                        button = {
                            viewModel.viewModelScope.launch {
                                viewModel.addCart(CartApiRequest(
                                    productId = wish.product.id,
                                    userId = wish.user.id,
                                    selected = false,
                                    total = 1,
                                ))
                                navController.navigateToCartScreen()
                            }
                        },
                        likeClick={
                            viewModel.viewModelScope.launch {
                                if (likeStatus) {
                                    viewModel.unLikeProduct(wish.id)
                                } else{
                                    viewModel.likeProduct(
                                        wish.product.id,
                                        onResult = {
                                            wish.id = it
                                        }
                                    )
                                }
                                likeStatus = !likeStatus
                            }
                        }
                    )
                }
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