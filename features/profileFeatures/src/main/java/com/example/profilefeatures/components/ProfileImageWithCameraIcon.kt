package com.example.profilefeatures.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nabiha.designsystem.R
import com.nabiha.designsystem.theme.OptixTheme

@Composable
fun ProfileImageWithCameraIcon(
    painter: Painter
) {
    Box(
        modifier = Modifier.size(120.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Icon(
            painter = painterResource(id = R.drawable.camera),
            contentDescription = "Camera Icon",
            modifier = Modifier
                .size(32.dp)
                .background(MaterialTheme.colorScheme.secondary, CircleShape)
                .padding(6.dp),
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileImageWithCameraIconPreview() {
    OptixTheme {
        ProfileImageWithCameraIcon(painterResource(id = R.drawable.profile_pic))
    }
}
