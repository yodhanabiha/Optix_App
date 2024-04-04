package com.example.appinfofeatures

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.designsystem.R
import com.nabiha.designsystem.theme.OptixTheme

@Composable
internal fun AppInfoScreenRoute(
    navController: NavHostController,
) {
    AppInfoScreen(navController = navController,)
}

@Composable
private fun AppInfoScreen(navController: NavHostController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
    ) {
        item {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.chevron_left),
                    contentDescription = "Bag Icon",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .height(30.dp)
                        .width(20.dp)
                )
                Text(
                    text = "App Info",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppInfoScreenPreview() {
    val navController = rememberNavController()
    OptixTheme {
        AppInfoScreen(navController = navController)
    }
}