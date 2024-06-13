package com.nabiha.data.mapper.user

import android.util.Log
import com.nabiha.apiresponse.users.UserApiResponse
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.UserEntity
import timber.log.Timber
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserApiResponse, UserEntity> {
    override fun mapFromApiResponse(type: UserApiResponse): UserEntity {
        return if (type.status == "SUCCESS") {
            val user = type.results
            UserEntity(
                id = user.id,
                email = user.email,
                password = user.password,
                name = user.name,
                phone = user.phone,
                role = user.role,
                imageurl = user.imageurl ?: ""
            )
        } else {
            UserEntity()
        }
    }
}