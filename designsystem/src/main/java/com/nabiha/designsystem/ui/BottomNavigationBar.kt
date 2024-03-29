package com.nabiha.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun RowScope.BottomNavigationBar(
    selected: Boolean,
    navController: NavHostController,
    route: String,
    title: String,
    iconSelected: Int,
    iconUnselected: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .weight(1f)
            .padding(top = if (route == "virtual") 0.dp else 18.dp)
            .clickable { navController.navigate(route) }
            .background(
                color = if (route == "virtual") Color.Transparent else MaterialTheme.colorScheme.primary,
            )) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {

//            if (selected && route != "virtual") {
//                Divider(
//                    color = MaterialTheme.colorScheme.surface,
//                    thickness = 3.dp,
//                    modifier = Modifier
//                        .width(48.dp)
//                        .align(Alignment.CenterHorizontally)
//                )
//            }

            if (route != "virtual") {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navController.navigate(route) }
                ) {
                    Icon(
                        painter = painterResource(id = if (selected) iconSelected else iconUnselected),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.surface
                    )

                    Text(
                        text = title,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 2.dp),
                        color = MaterialTheme.colorScheme.surface
                    )
                }
            } else {

                Box(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 18.dp)
                            .background(MaterialTheme.colorScheme.primary),
                    ) {

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { navController.navigate(route) }
                    ) {
                        Box(
                            modifier = Modifier
                                .height(40.dp)
                                .width(46.dp)
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                        ) {
                            Icon(
                                painter = painterResource(id = if (selected) iconSelected else iconUnselected),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                tint = MaterialTheme.colorScheme.surface
                            )
                        }

                        Text(
                            text = title,
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 2.dp),
                            color = MaterialTheme.colorScheme.surface
                        )
                    }

                }
            }


        }
    }

}