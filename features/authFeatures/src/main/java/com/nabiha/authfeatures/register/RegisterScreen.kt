 package com.nabiha.authfeatures.register

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.authfeatures.login.navigateToLoginScreen
import com.nabiha.designsystem.theme.OptixTheme
import java.time.format.TextStyle

@Composable
internal fun RegisterScreenRoute(
//    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController,
) {
//    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()
    RegisterScreen(navController=navController)
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

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
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
                            .size(width = 451.dp, height = 250.dp)
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
                                .padding(bottom = 16.dp)
                                .fillMaxWidth()
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),

                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
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
                                .padding(bottom = 16.dp)
                                .fillMaxWidth()
                                .height(53.dp)
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
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            textStyle = MaterialTheme.typography.bodyLarge,
                            placeholder = {
                                Text(
                                    text = "Confirm Password",
                                    fontSize = 12.sp,
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.lock),
                                    contentDescription = "Confirm Password Icon",
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
                                        .size(20.dp)
                                )
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier
                                .padding(bottom = 32.dp)
                                .fillMaxWidth()
                                .height(53.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(color = Color.White),
                        )

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
                                navController.navigateToLoginScreen()
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
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
