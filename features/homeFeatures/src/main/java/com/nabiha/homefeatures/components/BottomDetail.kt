package com.nabiha.homefeatures.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.nabiha.designsystem.R
import com.nabiha.designsystem.theme.OptixTheme

@Composable
fun BottomDetail() {
    OutlinedCard(modifier = Modifier.fillMaxWidth(), shape = RectangleShape, border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.2f))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ },
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sunglasses),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 4.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Vitual Try-On",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.cart_fill),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 4.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
                Text(
                    text = "Add to Cart",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.surface
                )
            }

        }

    }
}

@Preview
@Composable
private fun BottomDetailPreview() {
    val navController = rememberNavController()
    OptixTheme {
        BottomDetail(

        )
    }
}