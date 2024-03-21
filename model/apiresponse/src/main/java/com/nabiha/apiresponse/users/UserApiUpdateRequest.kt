package com.nabiha.apiresponse.users

data class UserApiUpdateRequest(
    val email: String,
    val name: String,
    val phone: String,
    val role: String
)
