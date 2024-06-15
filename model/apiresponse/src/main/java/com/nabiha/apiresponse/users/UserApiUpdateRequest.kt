package com.nabiha.apiresponse.users

import android.net.Uri

data class UserApiUpdateRequest(
    val name: String,
    val phone: String,
    val gender: String,
    val date_birth: String,
    val image: Uri
)
