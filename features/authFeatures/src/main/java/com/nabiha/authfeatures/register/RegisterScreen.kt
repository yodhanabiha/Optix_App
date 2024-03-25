package com.nabiha.authfeatures.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nabiha.designsystem.theme.OptixTheme

@Composable
internal fun RegisterScreenRoute(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()
    RegisterScreen(viewModel = viewModel, registerUiState = registerUiState, navController=navController)
}

@Composable
private fun RegisterScreen(
    viewModel: RegisterViewModel,
    registerUiState: RegisterUiState,
    navController: NavHostController
) {


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
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
                        value = email,
                        onValueChange = { email = it },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = { Text(text = "Email") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(),
                    )
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = { Text(text = "Name") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .fillMaxWidth(),
                    )
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        placeholder = { Text(text = "Phone") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
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
//                            )
//                        }

                    }) {
                        Text(text = "Sign Up")
                    }
                }

                when (registerUiState) {
                    is RegisterUiState.Error -> Text(text = registerUiState.message)
                    RegisterUiState.Loading -> Text(text = "")
                    is RegisterUiState.Success -> Text(text = registerUiState.data.email)
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
