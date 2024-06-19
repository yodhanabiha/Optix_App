package com.example.profilefeatures.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nabiha.designsystem.R
import com.nabiha.designsystem.component.COutlinedTextField
import com.nabiha.designsystem.theme.OptixTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun EditComponent(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        COutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.pencil_fill),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface.copy(0.2f),
                    modifier = Modifier.padding(
                        start = 12.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                        end = 12.dp
                    )
                )
            },
            visualTransformation = visualTransformation,
            leadingIcon = {
                Spacer(modifier = Modifier.width(12.dp))
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background,
                    RoundedCornerShape(percent = 50)
                )
                .height(36.dp)
                .fillMaxWidth(),
            borderColor = MaterialTheme.colorScheme.onSurface.copy(0.2f),
            focusedBorderColor =  MaterialTheme.colorScheme.onSurface,
            placeholderText = ""
        )
    }
}


@Preview(showBackground = true)
@Composable
fun EditComponentPrv() {
    OptixTheme {
        Column {
            EditComponent("Name", value = "Apipoy", onValueChange = {})
        }
    }
}