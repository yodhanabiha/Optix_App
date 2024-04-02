package com.nabiha.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nabiha.designsystem.R


val PoppinsFamily = FontFamily(
    Font(R.font.poppins_black, FontWeight.Black),
    Font(R.font.poppins_blackitalic, FontWeight.Black, FontStyle.Italic),

    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_blackitalic, FontWeight.Bold, FontStyle.Italic),

    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),

    Font(R.font.poppins_extralight, FontWeight.ExtraLight),
    Font(R.font.poppins_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),

    Font(R.font.poppins_italic, FontWeight.Normal, FontStyle.Italic),

    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_lightitalic, FontWeight.Light, FontStyle.Italic),

    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_mediumitalic, FontWeight.Medium, FontStyle.Italic),

    Font(R.font.poppins_regular, FontWeight.Normal),

    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),

    Font(R.font.poppins_thin, FontWeight.Thin),
    Font(R.font.poppins_thinitalic, FontWeight.Thin, FontStyle.Italic)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    bodyMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    bodySmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    labelSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 8.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    labelMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    titleLarge = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    titleMedium = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
    titleSmall = TextStyle(
        fontFamily = PoppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    ),
)