package com.nabiha.homefeatures

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nabiha.designsystem.R
import com.nabiha.designsystem.theme.OptixTheme
import com.nabiha.designsystem.ui.COutlinedTextField
import kotlinx.coroutines.delay

@Composable
internal fun HomeScreenRoute(
    navController: NavHostController
) {
//    val albumUiState by viewModel.albumUiState.collectAsStateWithLifecycle()
    HomeScreen()
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun HomeScreen() {

    var search by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hi, Apipoy!",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f)
                    )
                    BadgedBox(badge = {
                        Badge(
                            containerColor = Color(0xFFFB0101),
                            contentColor = Color.White
                        ) {
                            Text(text = "8", style = MaterialTheme.typography.labelSmall)
                        }
                    }) {

                        Icon(
                            painter = painterResource(id = R.drawable.person),
                            contentDescription = "Person icon",
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape),
                        )

                    }

                }

                COutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.padding(
                                start = 12.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 12.dp
                            )
                        )
                    },
                    trailingIcon = null,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.background,
                            RoundedCornerShape(percent = 50)
                        )
                        .height(36.dp)
                        .fillMaxWidth(),
                    fontSize = 10.sp,
                    placeholderText = "Search"
                )

            }

        }
        item {
            val count = 3
            val pagerState = rememberPagerState(initialPage = 1, pageCount = {count})

            LaunchedEffect(Unit) {
                while (true) {
                    delay(5000)
                    pagerState.animateScrollToPage((pagerState.currentPage + 1) % 3)
                }
            }

            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = "Best Collection",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )

                HorizontalPager(state = pagerState) { page ->
                    val drawableId = when (page) {
                        0 -> R.drawable.carr_1
                        1 -> R.drawable.carr_2
                        2 -> R.drawable.carr_3
                        else -> throw IllegalArgumentException("Invalid page: $page")
                    }

                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shadow(12.dp, RoundedCornerShape(12.dp))
                    )
                }

                Canvas(modifier = Modifier.width(width = 60.dp).align(Alignment.CenterHorizontally).padding(top = 16.dp)) {
                    val spacing = 8.dp.toPx()
                    val dotWidth = 8.dp.toPx()
                    val dotHeight = 8.dp.toPx()

                    val activeDotWidth = 28.dp.toPx()
                    var x = 0f
                    val y = center.y

                    repeat(3) { i ->
                        val posOffset = pagerState.pageOffset
                        val dotOffset = posOffset % 1
                        val current = posOffset.toInt()

                        val factor = (dotOffset * (activeDotWidth - dotWidth))

                        val calculatedWidth = when {
                            i == current -> activeDotWidth - factor
                            i - 1 == current || (i == 0 && posOffset > count - 1) -> dotWidth + factor
                            else -> dotWidth
                        }

                        drawIndicator(x, y, calculatedWidth, dotHeight, CornerRadius(16f))
                        x += calculatedWidth + spacing
                    }
                }

            }

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
val PagerState.pageOffset: Float
    get() = this.currentPage + this.currentPageOffsetFraction

private fun DrawScope.drawIndicator(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radius: CornerRadius
) {
    val rect = RoundRect(
        x,
        y - height / 2,
        x + width,
        y + height / 2,
        radius
    )
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = Color(0xFFD9D9D9))
}


@Composable
@Preview
private fun HomeScreenPrv() {
    OptixTheme {
        HomeScreen()
    }
}