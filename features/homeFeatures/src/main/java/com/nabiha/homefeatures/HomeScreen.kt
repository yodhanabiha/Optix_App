package com.nabiha.homefeatures

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nabiha.designsystem.theme.OptixTheme
import com.nabiha.homefeatures.components.CardSaya

@Composable
internal fun HomeScreenRoute(
    navController: NavHostController
) {
//    val albumUiState by viewModel.albumUiState.collectAsStateWithLifecycle()
    HomeScreen()
}


@Composable
private fun HomeScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
        ) {

            CardSaya(text = "Card1", modifier = Modifier.weight(1f))
            CardSaya(text = "Card2", modifier = Modifier.weight(1f))
            CardSaya(text = "Card3", modifier = Modifier.weight(1f))
            
        }
    }


}


@Composable
@Preview
private fun HomeScreenPrv() {
    OptixTheme {
        HomeScreen()
    }
}