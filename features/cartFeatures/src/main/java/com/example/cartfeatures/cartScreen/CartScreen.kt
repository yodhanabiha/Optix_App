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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cartfeatures.cartComponent.CardCart
import com.example.cartfeatures.cartComponent.CheckoutButton
import com.nabiha.designsystem.component.ScaffoldTopAppbar
import com.nabiha.designsystem.theme.OptixTheme

@Composable
internal fun CartScreenRoute(
    navController: NavHostController,
) {
    CartScreen(navController = navController,)
}

@Composable
private fun CartScreen(navController: NavHostController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = "My Cart",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
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
                            text = "You have 10 items in your cart",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(start = 8.dp),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            }

        items(10
        ) {
            CardCart(
                title = "Purple Glasses",
                price = "Rp155.000",
                imageUrl = "https://i.pinimg.com/564x/a5/67/92/a567923a663362b33af3f9741db8ec93.jpg",
                quantity = 1
                ) {
                }
            }
        items (1
        ) {
            CheckoutButton(
                items = "Rp.465.000",
                discount = "-Rp.65.000",
                total = "Rp.400.000" )
        }
        }
    }

@Preview
@Composable
private fun CartScreenPreview() {
    val navController = rememberNavController()
    OptixTheme {
        CartScreen(navController = navController)
    }
}
