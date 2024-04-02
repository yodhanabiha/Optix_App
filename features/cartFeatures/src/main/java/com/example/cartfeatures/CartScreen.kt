package com.example.cartfeatures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.designsystem.component.ScaffoldTopAppbar
import com.nabiha.designsystem.theme.OptixTheme

@Composable
internal fun CartScreenRoute(
    navController: NavHostController,
    onBackBtnClick: () -> Unit
) {
    CartScreen(navController = navController, onBackBtnClick = onBackBtnClick)
}

@Composable
private fun CartScreen(navController: NavHostController, onBackBtnClick: () -> Unit) {
    ScaffoldTopAppbar(
        title = "My Cart",
        onNavigationIconClick = onBackBtnClick,
    ) {
        val modifier = Modifier.padding(it)

        var show by remember {
            mutableStateOf(false)
        }

        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top = 16.dp, bottom = 8.dp)
        ) {

            item {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = com.nabiha.designsystem.R.drawable.bag_fill),
                            contentDescription = "Bag Icon",
                            modifier = Modifier.size(26.dp)
                        )
                        Text(
                            text = "You Have 3 Items in your cart",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun CartScreenPreview() {
    val navController = rememberNavController()
    OptixTheme {
        CartScreen(navController = navController, onBackBtnClick = { navController.popBackStack() })
    }
}
