 package com.nabiha.authfeatures.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.authfeatures.login.navigateToLoginScreen
import com.nabiha.designsystem.theme.OptixTheme
import com.nabiha.homefeatures.home.navigateToHomeScreen
import java.time.format.TextStyle

@Composable
internal fun RegisterScreenRoute(
//    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController,
) {
//    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()
    RegisterScreen(navController=navController)
}

 private fun isValidEmail(email: String): Boolean {
     val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")
     return email.matches(emailRegex)
 }
 private fun isValidPassword(password: String): Boolean {
     return password.length >= 8
 }

@Composable
private fun RegisterScreen(
//    viewModel: RegisterViewModel,
//    registerUiState: RegisterUiState,
    navController: NavHostController
) {

    var userName by remember {
        mutableStateOf("")
    }

    var userNameError by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var emailError by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var confirmPasswordError by remember {
        mutableStateOf("")
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisibility by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = com.nabiha.designsystem.R.drawable.register_pic),
                        contentDescription = "Sign Up Picture",
                        modifier = Modifier
                            .size(width = 451.dp, height = 270.dp)
                            .padding(bottom = 16.dp, end = 24.dp),
                    )
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Please complete your personal information first!",
                            style = MaterialTheme.typography.labelLarge,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.surface
                        )
                        OutlinedTextField(
                            value = userName,
                            onValueChange = { userName = it },
                            textStyle = MaterialTheme.typography.bodySmall,
                            placeholder = {
                                Text(
                                    text = "Username",
                                    fontSize = 12.sp
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.person),
                                    contentDescription = "Username Icon",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                )
                            },

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),

                        )
                        if (userNameError.isNotEmpty()) {
                            Text(
                                text = userNameError,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                emailError = if (isValidEmail(it)) "" else "Invalid email format!"
                            },
                            textStyle = MaterialTheme.typography.bodyLarge,
                            placeholder = {
                                Text(
                                    text = "Email",
                                    fontSize = 12.sp,
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.envelope),
                                    contentDescription = "Email Icon",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .height(53.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),
                        )
                        if (emailError.isNotEmpty()) {
                            Text(
                                text = emailError,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start= 16.dp)
                            )
                        }
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                if (isValidPassword(it)) {
                                    passwordError = ""
                                }
                            },
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
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.lock_fill),
                                    contentDescription = "Password Icon",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(
                                        id = if (passwordVisibility) {
                                            com.nabiha.designsystem.R.drawable.eye
                                        } else {
                                            com.nabiha.designsystem.R.drawable.eye_slash
                                        }
                                    ),
                                    contentDescription = "Toggle Password Visibility",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                        .clickable { passwordVisibility = !passwordVisibility }
                                )
                            },
                            visualTransformation = if (passwordVisibility) VisualTransformation.None
                            else {
                                PasswordVisualTransformation()
                            },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .height(53.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),
                        )
                        if (passwordError.isNotEmpty()) {
                            Text(
                                text = passwordError,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
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
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.lock_fill),
                                    contentDescription = "Password Icon",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(
                                        id = if (confirmPasswordVisibility) {
                                            com.nabiha.designsystem.R.drawable.eye
                                        } else {
                                            com.nabiha.designsystem.R.drawable.eye_slash
                                        }
                                    ),
                                    contentDescription = "Toggle Password Visibility",
                                    modifier = Modifier
                                        .padding(vertical = 12.dp, horizontal = 8.dp)
                                        .size(18.dp)
                                        .clickable { confirmPasswordVisibility = !confirmPasswordVisibility }
                                )
                            },
                            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None
                            else {
                                PasswordVisualTransformation()
                            },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth()
                                .height(53.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),
                        )
                        if (confirmPasswordError.isNotEmpty()) {
                            Text(
                                text = confirmPasswordError,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }

                        Button(
                            onClick = {
                                if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                                    userNameError = if (userName.isEmpty()) "Username cannot be empty!" else ""
                                    emailError = if (email.isEmpty()) "Email cannot be empty!" else ""
                                    passwordError = if (password.isEmpty()) "Password cannot be empty!" else ""
                                    confirmPasswordError = if (confirmPassword.isEmpty()) "Confirm password cannot be empty!" else ""
                                } else if (!isValidEmail(email)) {
                                    emailError = "Invalid email format!"
                                } else if (!isValidPassword(password)) {
                                    passwordError = "Password must be at least 8 characters long!"
                                } else if (password != confirmPassword) {
                                    confirmPasswordError = "Passwords do not match!"
                                } else {
                                    userNameError = ""
                                    emailError = ""
                                    passwordError = ""
                                    confirmPasswordError = ""
                                    navController.navigateToLoginScreen()
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, bottom = 16.dp)
                                .height(37.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                        ) {
                            Text(
                                text = "Sign Up",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.surface
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
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
                                    text = "Continue with Google",
                                    style = MaterialTheme.typography.labelMedium,
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
                                    text = "Already have an account? ",
                                    style =  MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.surface,
                                )
                                ClickableText(
                                    text = AnnotatedString("Sign in"),
                                    onClick = { offset ->
                                        navController.navigateToLoginScreen()
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
    private fun RegisterScreenPreview() {
        val navController = rememberNavController()
        OptixTheme {
            RegisterScreenRoute(navController = navController)
        }
    }
