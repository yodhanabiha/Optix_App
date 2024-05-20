package com.nabiha.authfeatures.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nabiha.designsystem.component.COutlinedTextField


@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    leadingIcon: (@Composable () -> Unit),
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    COutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        shape = RoundedCornerShape(12),
        placeholderText = placeholderText,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        fontSize = 14.sp,
        borderColor = Color.Black.copy(alpha = 0.2F),
        cursorBrush = SolidColor(Color.Black),
        trailingIcon = trailingIcon,
        borderWidth = 1.dp,
        focusedBorderColor = Color.Black,
        modifier = modifier
            .background(
                Color(0xFFF1F1F1),
                RoundedCornerShape(12)
            )
            .height(42.dp)
            .fillMaxWidth(),
    )
}