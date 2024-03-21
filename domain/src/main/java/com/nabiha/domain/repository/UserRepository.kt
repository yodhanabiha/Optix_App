package com.nabiha.domain.repository

import com.nabiha.apiresponse.users.UserApiLoginRequest
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import com.nabiha.domain.utils.Result
import com.nabiha.entity.UserEntity
import com.nabiha.entity.UserEntityLogin
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun fetchUserProfile(): Flow<Result<UserEntity>>
    suspend fun fetchRegisterUser(params: UserApiRegisterRequest): Flow<Result<UserEntity>>
    suspend fun fetchLoginUser(params: UserApiLoginRequest): Flow<Result<UserEntityLogin>>
    suspend fun fetchUpdaterUser(params: Long, data: UserApiUpdateRequest): Flow<Result<UserEntity>>
}