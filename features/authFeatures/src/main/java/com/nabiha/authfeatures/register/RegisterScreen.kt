package com.nabiha.authfeatures.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.authfeatures.R
import com.nabiha.designsystem.theme.OptixTheme
import kotlinx.coroutines.launch

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
        modifier = Modifier.fillMaxSize().background(color=MaterialTheme.colorScheme.background)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column (modifier = Modifier.fillMaxSize()) {
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = { Text(text = "Username") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.person_icn),
                                contentDescription = "Username Icon",
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(),
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = { Text(text = "Email") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.mail_icn),
                                contentDescription = "Username Icon",
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(),
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.lock_icn),
                                contentDescription = "Username Icon",
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(),
                    )
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = { Text(text = "Confirm Password") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.lock_icn),
                                contentDescription = "Username Icon",
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(),
                    )

                    Button(onClick = {
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

                    }) {
                        Text(text = "Sign Up")
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

@Composable
@Preview
private fun RegisterScreenPreview() {
    val navController = rememberNavController()
    OptixTheme {
        RegisterScreenRoute(navController = navController)
    }
}
