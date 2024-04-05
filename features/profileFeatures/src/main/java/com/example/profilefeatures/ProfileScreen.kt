package com.example.profilefeatures

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appinfofeatures.navigateToAppInfoScreen
import com.nabiha.designsystem.R
import com.nabiha.designsystem.theme.OptixTheme

@Composable
internal fun ProfileScreenRoute(
    navController: NavHostController,
) {
    ProfileScreen(navController,)
}

@Composable
private fun ProfileScreen(navController: NavHostController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        contentPadding = PaddingValues(16.dp)
    ) {

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "Gambar Product",
                    modifier = Modifier
                        .size(115.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape),
                    contentScale = ContentScale.FillBounds,

                    )
                Text(
                    text = "Apipoy",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 4.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
                Text(
                    text = "+628123456789",
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }

        item {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.person_icn_ns),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(16.dp)
                )
                Text(
                    text = "Edit Profile",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(18.dp)
                )
            }
            Divider(
                modifier = Modifier
                    .padding(top= 4.dp, bottom = 16.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lock),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(16.dp)
                )
                Text(
                    text = "Change Password",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(18.dp)
                )
            }
            Divider(
                modifier = Modifier
                    .padding(top= 4.dp, bottom = 16.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.receipt),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(16.dp)
                )
                Text(
                    text = "My Orders",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(18.dp)
                )
            }
            Divider(
                modifier = Modifier
                    .padding(top= 4.dp, bottom = 16.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.question_circle),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(16.dp)
                )
                Text(
                    text = "Help",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(18.dp)
                )
            }
            Divider(
                modifier = Modifier
                    .padding(top= 4.dp, bottom = 16.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigateToAppInfoScreen() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info_circle),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(16.dp)

                )
                Text(
                    text = "App Info",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription ="profile",
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(18.dp),
                )
            }
            Divider(
                modifier = Modifier
                    .padding(top= 4.dp, bottom = 16.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.power),
                    contentDescription ="profile",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(16.dp)
                )
                Text(
                    text = "Log Out",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription ="profile" ,
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(18.dp),

                )
            }
            Divider(
                modifier = Modifier
                    .padding(top= 4.dp, bottom = 16.dp)
            )

        }
    }
}

@Composable
@Preview
private fun ProfileScreenPreview() {
    val navController = rememberNavController()
    OptixTheme {
        ProfileScreenRoute(navController = navController)
    }
}
