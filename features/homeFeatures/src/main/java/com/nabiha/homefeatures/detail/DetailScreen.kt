package com.nabiha.homefeatures.detail

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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.nabiha.common.utils.navigateToDetailScreen
import com.nabiha.designsystem.R
import com.nabiha.designsystem.component.ScaffoldTopAppbar
import com.nabiha.designsystem.component.gridItems
import com.nabiha.designsystem.theme.OptixTheme
import com.nabiha.homefeatures.components.BottomDetail
import com.nabiha.homefeatures.components.CardProductHome

@Composable
internal fun DetailScreenRoute(
    navController: NavHostController,
    onBackBtnClick: () -> Unit
) {
    DetailScreen(navController, onBackBtnClick)
}

@Composable
private fun DetailScreen(navController: NavHostController, onBackBtnClick: () -> Unit) {
    ScaffoldTopAppbar(
        title = "Product Detail",
        onNavigationIconClick = onBackBtnClick,
        bottomBar = { BottomDetail() }
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
                        painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/564x/a5/67/92/a567923a663362b33af3f9741db8ec93.jpg"),
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
                                    painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/564x/a5/67/92/a567923a663362b33af3f9741db8ec93.jpg"),
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
                            text = "Purple Glasses",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.love_icn_ns),
                            contentDescription = "",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Text(
                        text = "Rp 155.000",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    Color.Black.copy(alpha = 0.1f)
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
                        text = stringResource(id = com.nabiha.homefeatures.R.string.desc_ex),
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
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    thickness = 1.dp,
                    Color.Black.copy(alpha = 0.1f)
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
                        text = stringResource(id = com.nabiha.homefeatures.R.string.specification_ex),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 1.dp,
                    Color.Black.copy(alpha = 0.1f)
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

            gridItems(
                10,
                nColumns = 2,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                CardProductHome(
                    title = "Purple Glasses",
                    price = "Rp. 155.000",
                    imageUrl = "https://i.pinimg.com/564x/a5/67/92/a567923a663362b33af3f9741db8ec93.jpg",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(205.dp)
                        .clickable { navController.navigateToDetailScreen() },
                    like = true
                )
            }

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