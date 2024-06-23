package com.example.wishlistfeatures.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nabiha.designsystem.R
import com.nabiha.designsystem.theme.OptixTheme

@Composable
fun CardWishlist(
    title: String,
    price: String,
    imageUrl: String,
    like: Boolean,
    onClick: () -> Unit,
    button: () -> Unit,
    likeClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(131.dp)
            .padding(bottom = 16.dp)
            .clickable { onClick.invoke() },
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Gambar Product",
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(115.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Black.copy(alpha = 0.1f), RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
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
                    onClick = { button.invoke() },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                    contentPadding = PaddingValues(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(bottom = 0.dp)
                ) {
                    Text(
                        text = "Add to Cart",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
            Icon(
                painter = if (like) painterResource(id = R.drawable.love_icn) else painterResource(
                    id = R.drawable.love_icn_ns
                ),
                contentDescription = "Icon Love",
                tint = if (like) Color.Red else Color.Black,
                modifier = Modifier.padding(top = 16.dp).size(18.dp).clickable {
                    likeClick.invoke()
                }
            )
        }
    }
}

