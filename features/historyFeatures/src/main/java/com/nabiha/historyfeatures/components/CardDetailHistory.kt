package com.nabiha.historyfeatures.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import com.nabiha.designsystem.theme.OptixTheme

@Composable
fun CardDetailHistory(
    title: String,
    status: Status,
    imageUrl: String,
    onClickText: () -> Unit,
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

                Row (modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)){
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

                Text(
                    text = "View Product",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable { onClickText.invoke() }
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardHistoryPrvs() {
    OptixTheme {
        CardDetailHistory(
            title = "Purple Glasses",
            status = Status.Completed,
            imageUrl = UrlApiService.default + "/images_product/file_1718690997281_552.png",
            onClickText = { /*TODO*/ })
    }
}
