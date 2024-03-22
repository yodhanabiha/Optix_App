package com.nabiha.homefeatures.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.designsystem.component.ScaffoldTopAppbar
import com.nabiha.designsystem.theme.OptixTheme

@Composable
internal fun DetailScreenRoute(
    navController: NavHostController,
    onBackBtnClick: () -> Unit
) {
    DetailScreen(navController,onBackBtnClick)
}

@Composable
private fun DetailScreen(navController: NavHostController, onBackBtnClick: () -> Unit) {
    ScaffoldTopAppbar(title = "Detail Product", onNavigationIconClick = onBackBtnClick) {
        val modifier = Modifier.padding(it)
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "DETAIL SCREEN", style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Composable
@Preview
private fun DetailScreenPrv() {
    val navController = rememberNavController()
    OptixTheme {
        DetailScreen(navController = navController, navController::popBackStack)
    }
}