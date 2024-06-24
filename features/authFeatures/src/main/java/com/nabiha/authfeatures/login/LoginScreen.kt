package com.nabiha.authfeatures.login

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.draw.scale
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.nabiha.apiresponse.users.UserApiLoginRequest
import com.nabiha.authfeatures.components.AuthTextField
import com.nabiha.common.utils.navigateToHomeScreen
import com.nabiha.common.utils.navigateToRegisterScreen
import com.nabiha.designsystem.component.COutlinedTextField
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
internal fun LoginScreenRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()
    LoginScreen(navController = navController, viewModel, loginUiState)
}

@Composable
private fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel,
    loginUiState: LoginUiState
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

    var isLoading by remember {
        mutableStateOf(false)
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val googleSignInClient = viewModel.googleSignInClient

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val idToken = account.idToken
            viewModel.fetchLoginGoogle(idToken!!)
        } catch (e: ApiException) {
            Timber.e("signInResult:failed code=" + e.statusCode)
        }
    }

    LaunchedEffect(loginUiState) {
        when (loginUiState) {
            is LoginUiState.Success -> {
                Toast.makeText(context, "Login Success!", Toast.LENGTH_SHORT).show()
                navController.navigateToHomeScreen()
                viewModel.resetRegisterUiState()
            }

            is LoginUiState.Error -> {
                Toast.makeText(context, loginUiState.message, Toast.LENGTH_LONG).show()
                isLoading = false
                viewModel.resetRegisterUiState()
            }

            LoginUiState.Loading -> isLoading = true
            LoginUiState.Neutral -> isLoading = false
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
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Welcome to",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Text(
                            text = " OPTIX ! ",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                    Text(
                        text = "Your Personal Virtual Try-On Glasses",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = com.nabiha.designsystem.R.drawable.login_img),
                        contentDescription = "Sign In Picture",
                        modifier = Modifier
                            .size(width = 340.dp, height = 246.dp)
                            .padding(start = 24.dp, bottom = 32.dp, top = 32.dp)
                    )
                    Column(modifier = Modifier.fillMaxSize()) {
                        AuthTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholderText = "Email",
                            modifier = Modifier.padding(bottom = 16.dp),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.envelope),
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
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.lock_fill),
                                    contentDescription = "PW Icon",
                                    tint = Color.Black,
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .size(18.dp)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = if (showPassword) painterResource(id = com.nabiha.designsystem.R.drawable.eye) else painterResource(
                                        id = com.nabiha.designsystem.R.drawable.eye_slash
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

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 10.dp, bottom = 32.dp)
                        ) {
                            Spacer(modifier = Modifier.weight(1f))
                            Checkbox(
                                colors = CheckboxDefaults.colors(
                                    uncheckedColor = Color.White,
                                    checkedColor = MaterialTheme.colorScheme.secondary
                                ),
                                checked = rememberMe,
                                onCheckedChange = { rememberMe = it },
                                modifier = Modifier
                                    .size(10.dp)
                                    .scale(0.8f)
                            )
                            Text(
                                "Remember Me",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White,
                                modifier = Modifier.padding(start = 12.dp)
                            )

                        }
                        Button(
                            onClick = {
                                viewModel.viewModelScope.launch {
                                    viewModel.fetchRegister(
                                        UserApiLoginRequest(
                                            email = email,
                                            password = password,
                                        )
                                    )
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
                                text = "Sign In",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White
                            )
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .padding(start = 12.dp)
                                        .size(16.dp),
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
                                viewModel.viewModelScope.launch {
                                    launcher.launch(googleSignInClient.signInIntent)
                                }
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
                                    painter = painterResource(id = com.nabiha.designsystem.R.drawable.google),
                                    contentDescription = "Google Icon",
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Sign In with Google",
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
                                    text = "New to OPTIX? ",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White,
                                )
                                ClickableText(
                                    text = AnnotatedString("Create Account"),
                                    onClick = { offset ->
                                        navController.navigateToRegisterScreen()
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

