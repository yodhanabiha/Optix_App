package com.nabiha.entity

import java.util.Date

data class UserEntity(
    val id: Long = 0,
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val phone: String = "",
    val imageurl: String = "",
    val role: String = "",
    val date_birth: String = "",
    val gender: String = ""
)

data class UserEntityLogin (
    val user: UserEntity,
    val token: String
)