package com.nabiha.homefeatures.detail

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.nabiha.apiresponse.carts.CartApiRequest
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.formatPrice
import com.nabiha.common.utils.navigateToCartScreen
import com.nabiha.common.utils.navigateToDetailScreen
import com.nabiha.designsystem.R
import com.nabiha.designsystem.component.ScaffoldTopAppbar
import com.nabiha.designsystem.component.gridItems
import com.nabiha.designsystem.ui.NetworkErrorMessage
import com.nabiha.entity.ProductEntity
import com.nabiha.entity.UserEntity
import com.nabiha.homefeatures.components.BottomDetail
import com.nabiha.homefeatures.components.CardProductHome
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import timber.log.Timber

@Composable
internal fun DetailScreenRoute(
    navController: NavHostController,
    idProduct: Long,
    viewModel: DetailViewModel = hiltViewModel(),
    onBackBtnClick: () -> Unit
) {
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()
    val user by viewModel.user.collectAsStateWithLifecycle()
    val recommended by viewModel.recommendedState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.fetchDetailProduct(idProduct)
    }
    when (detailUiState) {
        is DetailUiState.Error -> NetworkErrorMessage(message = (detailUiState as DetailUiState.Error).message) {}
        DetailUiState.Loading -> {}
        is DetailUiState.Success -> {
            DetailScreen(
                navController,
                onBackBtnClick,
                (detailUiState as DetailUiState.Success).data,
                user,
                recommended,
                viewModel
            )
        }
    }

}

@Composable
private fun DetailScreen(
    navController: NavHostController,
    onBackBtnClick: () -> Unit,
    detailUiState: ProductEntity,
    user: UserEntity,
    recommended: RecommendedState,
    viewModel: DetailViewModel
) {

    val userLikeDetail = detailUiState.likes.find {
        it.user.id == user.id
    }

    var likeStatus by remember {
        mutableStateOf(userLikeDetail != null)
    }

    var likeIdDetail by remember { mutableStateOf(userLikeDetail?.id) }

    val context = LocalContext.current
    ScaffoldTopAppbar(
        title = "Product Detail",
        onNavigationIconClick = onBackBtnClick,
        bottomBar = { BottomDetail(
            onCartBtn = {
                viewModel.viewModelScope.launch {
                    viewModel.addCart(CartApiRequest(
                        productId = detailUiState.id,
                        userId = user.id,
                        total = 1,
                        selected = false
                    ))
                }
                navController.navigateToCartScreen()
            },
            onTryOnBtn = {
                //TODO()
            }
        ) }
    ) {
        val modifier = Modifier.padding(it)

        var show by remember {
            mutableStateOf(false)
        }

        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
        ) {

            item {
                Column {
                    Image(
                        painter = rememberAsyncImagePainter(model = "http://100.97.75.94:8080${detailUiState.imageurl}"),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(272.dp)
                            .background(Color.White),
                    )
                }
            }
            item {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(127.dp)
                        .padding(top = 16.dp),
                    contentPadding = PaddingValues(start = 16.dp)
                ) {
                    items(6) {
                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(end = 16.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.2f))
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .width(120.dp)
                                    .fillMaxHeight()
                                    .padding(8.dp)
                                    .background(
                                        Color.Gray
                                    )
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = UrlApiService.default + detailUiState.imageurl),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.White)
                                )
                            }
                        }
                    }

                }
            }
            item {
                Column(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 32.dp,
                        bottom = 16.dp
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = detailUiState.title,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter = if (likeStatus) painterResource(id = R.drawable.love_icn) else painterResource(
                                id = R.drawable.love_icn_ns
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    viewModel.viewModelScope.launch {
                                        if (likeStatus && likeIdDetail != null) {
                                            viewModel.unLikeProduct(likeIdDetail!!)
                                            likeIdDetail = null
                                        } else if (!likeStatus) {
                                            viewModel.likeProduct(
                                                detailUiState.id,
                                                onResult = {
                                                    likeIdDetail = it
                                                }
                                            )
                                        }
                                        likeStatus = !likeStatus
                                    }

                                },
                            tint = if (likeStatus) Color.Red else Color.Black
                        )
                    }

                    Text(
                        text = formatPrice(detailUiState.price),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.Black.copy(alpha = 0.1f)
                )
            }
            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 22.dp,
                        )
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    Text(
                        text = detailUiState.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .animateContentSize(),
                        maxLines = if (show) Int.MAX_VALUE else 8
                    )
                    if (!show) {
                        Text(
                            text = "...",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                    Text(
                        text = if (show) "Show less" else "Show more",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp)
                            .clickable {
                                show = !show
                            },
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    thickness = 1.dp,
                    color = Color.Black.copy(alpha = 0.1f)
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                        )
                ) {
                    Text(
                        text = "Specification",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = detailUiState.spec,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 1.dp,
                    color = Color.Black.copy(alpha = 0.1f)
                )
            }
            item {
                Text(
                    text = "Recommended",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 16.dp, start = 16.dp)
                )
            }

            when (recommended) {
                is RecommendedState.Error -> Toast.makeText(
                    context,
                    recommended.message,
                    Toast.LENGTH_SHORT
                ).show()

                RecommendedState.Loading -> {}
                is RecommendedState.Success -> {
                    gridItems(
                        recommended.data,
                        nColumns = 2,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) { product ->

                        val userLike = product.likes.find {
                            it.user.id == user.id
                        }
                        var likeStatusGrid by remember { mutableStateOf(userLike != null) }
                        var likeId by remember { mutableStateOf(userLike?.id) }
                        if (likeId == 62L) {
                            Timber.e("LIKE ID: $likeId")
                        }

                        CardProductHome(
                            title = product.title,
                            price = "Rp. ${formatPrice(product.price)}",
                            imageUrl = "http://100.97.75.94:8080${product.imageurl}",
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .height(205.dp)
                                .clickable { navController.navigateToDetailScreen(product.id) },
                            like = likeStatusGrid,
                            likeClikable = {
                                viewModel.viewModelScope.launch {
                                    if (likeStatusGrid && likeId != null) {
                                        viewModel.unLikeProduct(likeId!!)
                                        likeId = null
                                    } else if (!likeStatusGrid) {
                                        viewModel.likeProduct(
                                            product.id,
                                            onResult = { likeId = it })
                                    }
                                    likeStatusGrid = !likeStatusGrid
                                }
                            }
                        )
                    }
                }
            }


        }
    }
}

