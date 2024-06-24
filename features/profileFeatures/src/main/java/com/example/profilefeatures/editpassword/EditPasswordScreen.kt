package com.example.profilefeatures.editpassword

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.profilefeatures.components.EditComponent
import com.nabiha.common.utils.navigateToProfileScreen
import com.nabiha.designsystem.component.ScaffoldTopAppbar


@Composable
internal fun EditPasswordScreenRoute(
    navController: NavHostController,
    viewModel: EditPasswordViewModel = hiltViewModel()
) {
    val update by viewModel.ePasswordState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    EditPasswordScreen(context, viewModel, navController)

    LaunchedEffect(update) {
        when (update) {
            is EPasswordState.Error -> {
                Toast.makeText(context, (update as EPasswordState.Error).message, Toast.LENGTH_LONG)
                    .show()
                viewModel.resetEPasswordState()
            }

            EPasswordState.Idle -> {}
            EPasswordState.Loading -> {}
            is EPasswordState.Success -> {
                Toast.makeText(context, "Update Success!", Toast.LENGTH_LONG).show()
                navController.navigateToProfileScreen()
                viewModel.resetEPasswordState()
            }
        }
    }

}


@Composable
fun EditPasswordScreen(
    context: Context,
    viewModel: EditPasswordViewModel,
    navController: NavHostController
) {

    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }

    ScaffoldTopAppbar(
        title = "Edit Password",
        onNavigationIconClick = navController::popBackStack,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {

            EditComponent(
                label = "New Password",
                value = newPassword,
                onValueChange = { newPassword = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(top = 16.dp)
            )

            EditComponent(
                label = "Confirm New Password",
                value = confirmNewPassword,
                onValueChange = { confirmNewPassword = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(top = 16.dp)
            )

            Button(
                onClick = {
                    if (newPassword == confirmNewPassword) {
                        viewModel.updatePassword(newPassword)
                    } else {
                        Toast.makeText(context, "Password doesn't match", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Save Change",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }

        }
    }
}