package com.nabiha.authfeatures.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.designsystem.theme.OptixTheme

@Composable
internal fun LoginScreenRoute(
    navController: NavHostController,
) {
    LoginScreen(navController=navController)
}

@Composable
private fun LoginScreen(
    navController: NavHostController
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var rememberMe by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary) //background(color = MaterialTheme.colorScheme.background)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Welcome to",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text = " OPTIX ! ",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                    Text(
                        text = "Your Personal Virtual Try-On Glasses",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.surface
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = com.nabiha.designsystem.R.drawable.login_img),
                        contentDescription = "Sign In Picture",
                        modifier = Modifier
                            .size(width = 400.dp, height = 250.dp)
                            .padding(bottom = 16.dp)
                    )
                    Column(modifier = Modifier.fillMaxSize()) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            textStyle = MaterialTheme.typography.bodySmall,
                            placeholder = {
                                Text(
                                    text = "Email",
                                    fontSize = 12.sp,
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.envelope),
                                    contentDescription = "Email Icon",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                )
                            },
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            textStyle = MaterialTheme.typography.bodyLarge,
                            placeholder = {
                                Text(
                                    text = "Password",
                                    fontSize = 12.sp,
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.lock),
                                    contentDescription = "Password Icon",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.eye_slash),
                                    contentDescription = "End Icon",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                )
                            },

                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .fillMaxWidth()
                                .height(53.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 10.dp, bottom = 32.dp)
                        ) {
                            Checkbox(
                                colors = CheckboxDefaults.colors(
                                    uncheckedColor = MaterialTheme.colorScheme.surface,
                                    checkedColor = MaterialTheme.colorScheme.secondary
                                ),
                                checked = rememberMe,
                                onCheckedChange = { rememberMe = it },
                                modifier = Modifier.size(10.dp).scale(0.8f)
                            )
                            Text(
                                "Remember Me",
                                fontSize = 13.sp,
                                color = Color.White,
                                modifier = Modifier.padding(start = 12.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            ClickableText(
                                text = AnnotatedString("Create Account"),
                                onClick = { offset ->
                                    // Handle click event
                                    // You can navigate to sign-in screen or perform any action here
                                },
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 15.sp,
                                    color = Color(android.graphics.Color.parseColor("#AC7C58")),
                                    textDecoration = TextDecoration.Underline,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            )
                        }
                        Button(
                            onClick = {
                                //                        viewModel.viewModelScope.launch {
//                            viewModel.fetchRegister(
//                                userReg = UserApiRegisterRequest(
//                                    email = email,
//                                    password = password,
//                                    name = name,
//                                    phone = phone,
//                                    role = "USER"
//                                )
//                          )
                                //}
                                navController.navigate("destination_route")
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .height(37.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),

                        ) {
                            Text(
                                text = "Sign In",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.surface
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    Modifier
                                        .weight(1f)
                                        .padding(bottom = 4.dp, end = 8.dp)
                                        .height(1.dp)
                                        .background(color = MaterialTheme.colorScheme.surface)
                                )

                                Text(
                                    text = "Or",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(bottom = 8.dp),
                                    color = MaterialTheme.colorScheme.surface
                                )

                                Box(
                                    Modifier
                                        .weight(1f)
                                        .padding(bottom = 4.dp, start = 8.dp)
                                        .height(1.dp)
                                        .background(color = MaterialTheme.colorScheme.surface)
                                )
                            }
                        }

                        Button(
                            onClick = {
                                navController.navigate("destination_route")
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .height(37.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.google),
                                    contentDescription = "Google Icon",
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Sign In with Google",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "New to OPTIX? ",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                ClickableText(
                                    text = AnnotatedString("Create Account"),
                                    onClick = { offset ->
                                        // Handle click event
                                        // You can navigate to sign-in screen or perform any action here
                                    },
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.secondary,
                                        textDecoration = TextDecoration.Underline
                                    )
                                )
                            }
                        }
                    }

//                when (registerUiState) {
//                    is RegisterUiState.Error -> Text(text = registerUiState.message)
//                    RegisterUiState.Loading -> Text(text = "")
//                    is RegisterUiState.Success -> Text(text = registerUiState.data.email)
//                }
                }
            }
        }
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    val navController = rememberNavController()
    OptixTheme {
        LoginScreenRoute(navController = navController)
    }
}
