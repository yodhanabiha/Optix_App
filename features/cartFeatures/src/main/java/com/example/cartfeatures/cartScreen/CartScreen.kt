package com.example.cartfeatures.cartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.cartfeatures.cartComponent.CardCart
import com.example.cartfeatures.cartComponent.CartEmptyLayout
import com.example.cartfeatures.cartComponent.CheckoutButton
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.formatPrice
import com.nabiha.common.utils.navigateToDetailScreen
import com.nabiha.entity.CartEntity
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
internal fun CartScreenRoute(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartUiState by viewModel.cartUiState.collectAsStateWithLifecycle()
    when (cartUiState) {
        is CartUiState.Error -> Timber.e((cartUiState as CartUiState.Error).message)
        CartUiState.Loading -> {}
        is CartUiState.Success -> CartScreen(
            navController = navController,
            viewModel,
            (cartUiState as CartUiState.Success).data
        )
    }

}

@Composable
private fun CartScreen(
    navController: NavHostController,
    viewModel: CartViewModel,
    cartList: List<CartEntity>
) {

    var totalItems by remember {
        mutableIntStateOf(0)
    }
    var discount by remember {
        mutableIntStateOf(0)
    }

    Column {
        Text(
            text = "My Cart",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        if (cartList.isEmpty()){
            CartEmptyLayout()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp)
        ) {

            totalItems = cartList.sumOf { it.total * it.product.price }

            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .width(240.dp)
                            .padding(bottom = 16.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(16.dp)
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = com.nabiha.designsystem.R.drawable.bag_fill),
                                contentDescription = "Bag Icon",
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .height(15.dp)
                                    .width(14.dp)
                            )
                            Text(
                                text = "You have ${cartList.size} items in your cart",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 8.dp),
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }

            items(cartList) { cart ->

                var totalCart by remember {
                    mutableIntStateOf(cart.total)
                }
                var previousTotalCart by remember { mutableIntStateOf(cart.total) }

                LaunchedEffect(totalCart) {
                    val difference = totalCart - previousTotalCart
                    totalItems += difference * cart.product.price
                    previousTotalCart = totalCart
                }

                CardCart(
                    title = cart.product.title,
                    price = "Rp" + formatPrice(cart.product.price),
                    imageUrl = UrlApiService.default + cart.product.imageurl,
                    quantity = totalCart,
                    onClick = { navController.navigateToDetailScreen(cart.product.id) },
                    onIncrease = {
                        viewModel.viewModelScope.launch {
                            totalCart++
                            viewModel.updateCarts(
                                cart.id,
                                CartApiRequest(
                                    cart.product.id,
                                    cart.userId,
                                    totalCart,
                                    cart.selected
                                )
                            )
                        }
                    },
                    onDecrease = {
                        if (totalCart > 1) {
                            viewModel.viewModelScope.launch {
                                totalCart--
                                viewModel.updateCarts(
                                    cart.id,
                                    CartApiRequest(
                                        cart.product.id,
                                        cart.userId,
                                        totalCart,
                                        cart.selected
                                    )
                                )
                            }
                        }
                    }
                )
            }

            item {
                CheckoutButton(
                    items = "Rp. ${formatPrice(totalItems)}",
                    discount = "-Rp. ${formatPrice(discount)}",
                    total = "Rp. ${formatPrice(totalItems - discount)}"
                )
            }

        }
    }
}


