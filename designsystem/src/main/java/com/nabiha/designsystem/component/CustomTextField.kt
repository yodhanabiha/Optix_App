package com.nabiha.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun COutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    shape: Shape = RoundedCornerShape(24.dp),
    borderColor: Color = Color(0xFF838383),
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    borderWidth: Dp = 1.dp,
    textStyle: TextStyle = LocalTextStyle.current.copy(
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = fontSize
    ),
    cursorBrush: Brush = SolidColor(MaterialTheme.colorScheme.primary)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val currentBorderColor = if (isFocused) focusedBorderColor else borderColor

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .border(width = borderWidth, color = currentBorderColor, shape = shape)
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        cursorBrush = cursorBrush,
        textStyle = textStyle,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = textStyle.color.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}

@Composable
fun MutableInteractionSource.collectIsFocusedAsState(): State<Boolean> {
    val isFocused = remember { mutableStateOf(false) }
    LaunchedEffect(this) {
        interactions.collect { interaction ->
            when (interaction) {
                is FocusInteraction.Focus -> isFocused.value = true
                is FocusInteraction.Unfocus -> isFocused.value = false
            }
        }
    }
    return isFocused
}
