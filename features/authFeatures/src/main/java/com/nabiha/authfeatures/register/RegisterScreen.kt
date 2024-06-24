package com.nabiha.authfeatures.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.authfeatures.components.AuthTextField
import com.nabiha.common.utils.navigateToLoginScreen
import com.nabiha.designsystem.R
import kotlinx.coroutines.launch

@Composable
internal fun RegisterScreenRoute(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()
    RegisterScreen(viewModel, registerUiState, navController)
}

@Composable
private fun RegisterScreen(
    viewModel: RegisterViewModel,
    registerUiState: RegisterUiState,
    navController: NavHostController
) {

    val context = LocalContext.current

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

    var isLoading by remember {
        mutableStateOf(false)
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var showConfirmPassword by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(registerUiState) {
        when (registerUiState) {
            is RegisterUiState.Success -> {
                Toast.makeText(context, "Register Success!", Toast.LENGTH_SHORT).show()
                navController.navigateToLoginScreen()
                viewModel.resetRegisterUiState()
            }

            is RegisterUiState.Error -> {
                Toast.makeText(context, registerUiState.message, Toast.LENGTH_SHORT).show()
                isLoading = false
                viewModel.resetRegisterUiState()
            }

            RegisterUiState.Loading -> isLoading = true
            RegisterUiState.Neutral -> isLoading = false
        }
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
                        painter = painterResource(id = R.drawable.register_pic),
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
                            color = Color.White
                        )
                        AuthTextField(
                            value = userName,
                            onValueChange = { userName = it },
                            placeholderText = "Username",
                            modifier = Modifier.padding(bottom = 16.dp),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.person),
                                    contentDescription = "",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .size(18.dp)
                                )
                            })

                        AuthTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholderText = "Email",
                            modifier = Modifier.padding(bottom = 16.dp),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.envelope),
                                    contentDescription = "Email Icon",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .size(18.dp)
                                )
                            })

                        AuthTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholderText = "Password",
                            visualTransformation = if (showPassword) VisualTransformation.None  else PasswordVisualTransformation(),
                            modifier = Modifier.padding(bottom = 16.dp),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_fill),
                                    contentDescription = "PW Icon",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .size(18.dp)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = if (showPassword) painterResource(id = R.drawable.eye) else painterResource(
                                        id = R.drawable.eye_slash
                                    ),
                                    contentDescription = "End Icon",
                                    tint = Color(0xFF515151),
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .size(18.dp)
                                        .clickable {
                                            showPassword = !showPassword
                                        }
                                )
                            })
                        AuthTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            placeholderText = "Password",
                            visualTransformation = if (showConfirmPassword) VisualTransformation.None  else PasswordVisualTransformation(),
                            modifier = Modifier.padding(bottom = 16.dp),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock_fill),
                                    contentDescription = "PW Icon",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .size(18.dp)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = if (showConfirmPassword) painterResource(id = R.drawable.eye) else painterResource(
                                        id = R.drawable.eye_slash
                                    ),
                                    contentDescription = "End Icon",
                                    tint = Color(0xFF515151),
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .size(18.dp)
                                        .clickable {
                                            showConfirmPassword = !showConfirmPassword
                                        }
                                )
                            })

                        Button(
                            onClick = {
                                if (password == confirmPassword){
                                    viewModel.viewModelScope.launch {
                                        viewModel.fetchRegister(
                                            userReg = UserApiRegisterRequest(
                                                email = email,
                                                password = password,
                                                name = userName,
                                                phone = "0",
                                                role = "USER"
                                            )
                                        )
                                    }
                                }else{
                                    Toast.makeText(context, "Password don't match", Toast.LENGTH_SHORT).show()
                                }

                            },
                            shape = RoundedCornerShape(12.dp),
                            enabled = !isLoading,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .height(37.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                        ) {
                            Text(
                                text = "Sign Up",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White
                            )
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(start = 12.dp).size(16.dp),
                                    color = MaterialTheme.colorScheme.secondary,
                                    trackColor = Color.White
                                )
                            }
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
                                        .background(color = Color.White)
                                )

                                Text(
                                    text = "Or",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(bottom = 8.dp),
                                    color = Color.White
                                )

                                Box(
                                    Modifier
                                        .weight(1f)
                                        .padding(bottom = 4.dp, start = 8.dp)
                                        .height(1.dp)
                                        .background(color = Color.White)
                                )
                            }
                        }

                        Button(
                            onClick = {
                                //TODO
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                                .height(37.dp),
                            colors = ButtonDefaults.buttonColors(Color.White)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.google),
                                    contentDescription = "Google Icon",
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Continue with Google",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.Black,
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
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White,
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


                }
            }
        }
    }
}



