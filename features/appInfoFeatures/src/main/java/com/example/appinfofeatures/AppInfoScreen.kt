package com.example.appinfofeatures

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.designsystem.R
import com.nabiha.designsystem.theme.OptixTheme
import com.nabiha.designsystem.theme.PoppinsFamily

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
            Column {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.chevron_left),
                        contentDescription = "Back Icon",
                        tint = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .height(30.dp)
                            .width(20.dp)
                    )
                    Text(
                        text = "App Info",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.info_circle),
                        contentDescription = "Info Icon",
                        tint = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .height(45.dp)
                            .width(40.dp)
                            .padding(top = 5.dp)
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Text(text = "Version", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "2.0", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.labelLarge)
                        Canvas(modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .padding(top = 4.dp)) {
                            drawLine(
                                color = Color.Black.copy(alpha = 0.1f),
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                strokeWidth = 4f
                            )
                        }
                    }
                }
                Text(
                    text = buildAnnotatedString {
                        append("OPTIX adalah sebuah aplikasi yang memanfaatkan teknologi ")
                        withStyle(style = SpanStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)) {
                            append("Augmented Reality")
                        }
                        append(" untuk menciptakan pengalaman yang realistis dan personal bagi penggunanya. Dengan fitur-fitur inovatifnya, OPTIX membantu mengurangi ketidakpastian yang seringkali terkait dengan pembelian kacamata secara online, sehingga pengguna dapat memiliki keyakinan lebih dalam memilih produk yang sesuai dengan kebutuhan dan preferensi mereka.")
                    },
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Justify, lineHeight = 24.sp),
                    modifier = Modifier.padding(top = 20.dp)
                )
                Column(modifier = Modifier.padding(top = 20.dp)) {
                    Text(
                        text = "Developers",
                        style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Canvas(modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .padding(top = 12.dp)) {
                        drawLine(
                            color = Color.Black.copy(alpha = 0.1f),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 4f
                        )
                    }
                    Column(modifier = Modifier.padding(top = 24.dp)) {
                        Text(
                            text = "Muhammad Farid Adika",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "NIM. 2110511049",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Column(modifier = Modifier.padding(top = 24.dp)) {
                        Text(
                            text = "Afif Fakhri",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "NIM. 2110511063",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Column(modifier = Modifier.padding(top = 24.dp)) {
                        Text(
                            text = "Yodha Nabiha Rafif",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "NIM. 2110511075",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Column(modifier = Modifier.padding(top = 24.dp)) {
                        Text(
                            text = "Ichsan Maldini Hamid",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "NIM. 2110511076",
                            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
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