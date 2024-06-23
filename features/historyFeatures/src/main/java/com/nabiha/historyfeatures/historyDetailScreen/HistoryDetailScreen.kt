package com.nabiha.historyfeatures.historyDetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.formatDateTime
import com.nabiha.common.utils.formatPrice
import com.nabiha.common.utils.navigateToDetailScreen
import com.nabiha.designsystem.component.ScaffoldTopAppbar
import com.nabiha.entity.HistoryEntity
import com.nabiha.historyfeatures.components.CardDetailHistory
import com.nabiha.historyfeatures.components.Status
import timber.log.Timber

@Composable
internal fun HistoryDetailScreenRoute(
    navController: NavHostController,
    idHistory: Long,
    viewModel: HistoryDetailViewModel = hiltViewModel()
) {
    val historyUiState by viewModel.historyUiState.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel) {
        viewModel.fetchHistoryDetail(idHistory)
    }
    when (historyUiState) {
        is HistoryUiState.Error -> Timber.e((historyUiState as HistoryUiState.Error).message)
        HistoryUiState.Loading -> {}
        is HistoryUiState.Success -> DetailHistoryScreen(
            navController,
            (historyUiState as HistoryUiState.Success).data
        )
    }
}

@Composable
fun DetailHistoryScreen(navController: NavHostController, data: HistoryEntity) {
    ScaffoldTopAppbar(
        title = "Details",
        onNavigationIconClick = navController::popBackStack,
    ) { topAppBar ->
        Column(
            modifier = Modifier
                .padding(topAppBar)
                .padding(16.dp)
        ) {

            CardDetailHistory(
                title = data.product.title,
                status = Status.Completed,
                imageUrl = UrlApiService.default + data.product.imageurl,
                onClickText = { navController.navigateToDetailScreen(data.product.id) })

            Row {
                Text(
                    text = "Order ID",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "OPTX-${data.id}",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Row {
                Text(
                    text = "Buying Price",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Rp${formatPrice(data.price_item)} x ${data.total_item}",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Row {
                Text(
                    text = "Total Price",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Rp${formatPrice(data.total_price)}",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Row {
                Text(
                    text = "Purchase Date",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = formatDateTime(data.purchase_date),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Row {
                Text(
                    text = "Shipping Courier",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = data.shipping,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Row {
                Text(
                    text = "Address",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = data.address,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.width(241.dp)
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))


        }
    }

}
