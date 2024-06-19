package com.nabiha.data.mapper.user

import com.nabiha.apiresponse.users.UserApiResponse
import com.nabiha.common.utils.formatDateOfBirth
import com.nabiha.data.utils.Mapper
import com.nabiha.entity.UserEntity
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserApiResponse, UserEntity> {
    override fun mapFromApiResponse(type: UserApiResponse): UserEntity {
        return if (type.status == "SUCCESS") {
            val user = type.results
            val formattedDateBirth = formatDateOfBirth(user.date_birth)
            UserEntity(
                id = user.id,
                email = user.email,
                password = user.password,
                name = user.name,
                phone = user.phone,
                role = user.role,
                imageurl = user.imageurl ?: "",
                gender = user.gender ?: "",
                date_birth = formattedDateBirth
            )
        } else {
            UserEntity()
        }
    }
}