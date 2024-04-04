package com.example.cartfeatures.cartComponent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nabiha.designsystem.theme.OptixTheme

@Composable
fun CheckoutButton(
    items: String,
    discount: String,
    total: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text(
                    text = items,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = discount,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                )
                Text(
                    text = total,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(modifier = Modifier.padding(bottom = 46.dp)) {
                    Button(
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        Text(
                            text = "Checkout",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.surface
                        )

                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCheckoutButton() {
    OptixTheme {
        CheckoutButton(
            items = "Rp.465.000",
            discount = "-Rp.65.000",
            total = "Rp.400.000"
        )
    }
}
