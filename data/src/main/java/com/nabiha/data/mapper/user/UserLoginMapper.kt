package com.nabiha.data.mapper.user

import com.nabiha.apiresponse.users.UserApiLoginResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.UserEntity
import com.nabiha.entity.UserEntityLogin
import javax.inject.Inject

class UserLoginMapper @Inject constructor() : Mapper<UserApiLoginResponse, UserEntityLogin> {
    override fun mapFromApiResponse(type: UserApiLoginResponse): UserEntityLogin {
        return if (type.status == "SUCCESS") {
            val user = type.results.users
            val userE = UserEntity(
                id = user.id,
                email = user.email,
                password = user.password,
                name = user.name,
                phone = user.phone,
                role = user.role,
                imageurl = user.imageurl ?: ""
            )
            UserEntityLogin(userE, type.results.token.accessToken)
        } else {
            UserEntityLogin(UserEntity(), "")
        }
    }
}