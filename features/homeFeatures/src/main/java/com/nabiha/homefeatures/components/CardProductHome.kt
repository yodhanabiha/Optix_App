package com.nabiha.homefeatures.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nabiha.designsystem.theme.OptixTheme
import com.nabiha.designsystem.R

@Composable
fun CardProductHome(
    title: String,
    price: String,
    imageUrl: String,
    like: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFF000000).copy(alpha = 0.2f)),
    ) {
        Box(
            modifier = Modifier
                .height(123.dp)
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp, bottom = 16.dp, end = 16.dp)
        ) {
            Text(
                text = price,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp).weight(1f)
            )
            Icon(
                painter = painterResource(id = if (like) R.drawable.love_icn else R.drawable.love_icn_ns),
                contentDescription = "",
                tint = if (like) Color.Red else MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
@Preview
private fun CardProductHomePrv() {
    OptixTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column {
                CardProductHome(
                    title = "Purple Glasses",
                    price = "Rp. 155.000",
                    imageUrl = "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/6/1/021c1d56-cca6-482a-91b5-e16aa4af4de2.jpg",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(205.dp),
                    like = true
                )
            }
        }

    }
}

