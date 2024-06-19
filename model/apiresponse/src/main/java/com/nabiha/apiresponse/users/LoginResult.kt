package com.nabiha.apiresponse.users

data class LoginResult(
    val token:TokenResult,
    val users:UserResult
)
