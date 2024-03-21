package com.nabiha.apiresponse.users

data class UserResult(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val role: String
)
