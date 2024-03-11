package com.nabiha.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun  NetworkErrorMessage(
    message:String,
    modifier: Modifier = Modifier,
    onClickRefresh:()->Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = message, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
        Spacer(modifier = modifier.height(32.dp))
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = onClickRefresh,
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Refresh")
        }
    }
}

@Composable
@Preview
fun NetworkErrorMessagePrv(){
    NetworkErrorMessage(message = "Network Error") {

    }
}