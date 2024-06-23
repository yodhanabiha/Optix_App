package com.nabiha.historyfeatures.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nabiha.common.utils.UrlApiService
import com.nabiha.common.utils.formatPrice
import com.nabiha.designsystem.theme.OptixTheme

@Composable
fun CardHistory(
    title: String,
    price: Int,
    status: Status,
    imageUrl: String,
    quantity: Int,
    onDetails: () -> Unit = {},
    onBuy: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
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
                modifier = Modifier.padding(start = 16.dp).height(115.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row {
                    Text(
                        text = "Rp${formatPrice(price)}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = " x $quantity",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Row (modifier = Modifier.padding(top = 4.dp).weight(1f)){
                    Text(
                        text = "Status: ",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = status.status,
                        style = MaterialTheme.typography.bodySmall,
                        color = status.color,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row{
                    OutlinedButton(
                        onClick = { onDetails.invoke() },
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        Text(
                            text = "See Details",
                            softWrap = false,
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onBuy.invoke() },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(8.dp),
                    ) {
                        Text(
                            text = "Buy Again",
                            softWrap = false,
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardHistoryPrv() {
    OptixTheme {
        CardHistory(
            title = "Purple Glasses",
            price = 155000,
            status = Status.Completed,
            quantity = 2,
            imageUrl = UrlApiService.default + "/images_product/file_1718690997281_552.png"
        )
    }
}

sealed class Status(
    val status: String,
    val color: Color
) {
    object Completed : Status(
        status = "Compeleted",
        color = Color(0xFF1FCE5A)
    )

    object Failed : Status(
        status = "Failed",
        color = Color(0xFFCE1F1F)
    )

    object Unpaid : Status(
        status = "Unpaid",
        color = Color(0xFFEE991B)
    )
}