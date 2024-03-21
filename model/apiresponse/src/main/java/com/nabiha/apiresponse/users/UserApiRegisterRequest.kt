package com.nabiha.apiresponse.users

data class UserApiRegisterRequest(
    val email :String,
    val password: String,
    val name: String,
    val phone: String,
    val role: String
)
