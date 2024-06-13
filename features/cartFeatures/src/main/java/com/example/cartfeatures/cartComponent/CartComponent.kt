package com.example.cartfeatures.cartComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nabiha.designsystem.theme.OptixTheme

@Composable
fun CardCart(
    title: String,
    price: String,
    imageUrl: String,
    quantity: Int = 0,
    onIncrease: () -> Unit = {},
    onClick: () -> Unit,
    onDecrease: () -> Unit = {}

) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 16.dp)
            .clickable { onClick.invoke() },
        color = MaterialTheme.colorScheme.background
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Gambar Product",
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
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.padding(bottom = 46.dp)) {
                    Button(
                        onClick = { onDecrease.invoke() },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$quantity",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onIncrease.invoke() },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
                        contentPadding = PaddingValues(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                            color = MaterialTheme.colorScheme.surfaceVariant
                        )

                    }
                }
            }
        }
        Box(
            Modifier
                .padding(top = 132.dp)
                .padding(start = 4.dp, end = 6.dp)
                .background(color = Color.LightGray)
        )

    }


}

@Preview
@Composable
fun PreviewCard() {
    OptixTheme {
        CardCart(
            title = "Purple Glasses",
            price = "Rp155.000",
            imageUrl = "https://i.pinimg.com/564x/a5/67/92/a567923a663362b33af3f9741db8ec93.jpg",
            quantity = 1,
            onClick = {}
        )
    }
}
