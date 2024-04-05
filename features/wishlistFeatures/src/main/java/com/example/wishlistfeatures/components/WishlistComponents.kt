package com.example.wishlistfeatures.components

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
                .background(Color.White, RoundedCornerShape(8.dp))
                .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Inside
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
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            Text(
                text = price,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.surfaceVariant
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
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.love_icn),
            contentDescription = "Icon Love",
            tint = if (like) Color.Red else Color.Black,
            modifier = Modifier. padding(top = 16.dp).size(18.dp)
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
                imageUrl = "https://i.pinimg.com/564x/a5/67/92/a567923a663362b33af3f9741db8ec93.jpg",
                like = true
            ) {

            }
        }
    }
}