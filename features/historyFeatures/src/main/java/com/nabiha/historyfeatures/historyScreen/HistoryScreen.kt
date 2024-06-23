package com.nabiha.historyfeatures.historyScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.navigateToDetailHistoryScreen
import com.nabiha.common.utils.navigateToDetailScreen
import com.nabiha.entity.HistoryEntity
import com.nabiha.historyfeatures.components.CardHistory
import com.nabiha.historyfeatures.components.HistoryEmptyLayout
import com.nabiha.historyfeatures.components.Status
import timber.log.Timber

@Composable
internal fun HistoryListScreenRoute(
    navController: NavHostController,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val historyListUiState by viewModel.historyListUiState.collectAsStateWithLifecycle()
    when (historyListUiState) {
        is HistoryListUiState.Error -> Timber.e((historyListUiState as HistoryListUiState.Error).message)
        HistoryListUiState.Loading -> {}
        is HistoryListUiState.Success -> HistoryScreen((historyListUiState as HistoryListUiState.Success).data, navController)
    }
}

@Composable
fun HistoryScreen(data: List<HistoryEntity>, navController: NavHostController) {
    Column {
        Text(
            text = "History",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.onSurface
        )

        if (data.isEmpty()){
            HistoryEmptyLayout()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(data){ history->
                CardHistory(
                    title = history.product.title,
                    price = history.price_item,
                    status = Status.Completed,
                    quantity = history.total_item,
                    imageUrl = UrlApiService.default + history.product.imageurl,
                    onDetails = {navController.navigateToDetailHistoryScreen(history.id)},
                    onBuy = {navController.navigateToDetailScreen(history.product.id)}
                )
            }
        }
    }
}
