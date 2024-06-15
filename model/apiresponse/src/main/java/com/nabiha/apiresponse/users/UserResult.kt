package com.nabiha.apiresponse.users

import java.util.Date

data class UserResult(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val role: String,
    val imageurl: String?,
    val date_birth: String?,
    val gender: String?
)
