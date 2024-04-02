package com.example.wishlistfeatures.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nabiha.designsystem.theme.OptixTheme
import com.nabiha.designsystem.R

@Composable
fun CardWishlist(
    title: String,
    price: String,
    imageUrl: String,
    like: Boolean,
    button: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp).padding(bottom = 16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Gambar Product",
            modifier = Modifier
                .size(115.dp)
                .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 0.dp)
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = price,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { button },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                contentPadding = PaddingValues(8.dp),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(bottom = 0.dp)
            ) {
                Text(
                    text = "Add to Cart",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.love_icn),
            contentDescription = "Icon Love",
            tint = if (like) Color.Red else Color.Black,
            modifier = Modifier. padding(top = 16.dp).size(20.dp)
        )
    }
}

@Preview
@Composable
fun PreviewCard() {
    OptixTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            CardWishlist(
                title = "Purple Glasses",
                price = "Rp155.000",
                imageUrl = "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/6/1/021c1d56-cca6-482a-91b5-e16aa4af4de2.jpg",
                like = true
            ) {

            }
        }
    }
}