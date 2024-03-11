package com.nabiha.homefeatures.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardSaya(text: String, modifier: Modifier= Modifier){
    Card(
        modifier = modifier.height(100.dp).padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = text)
    }
}
