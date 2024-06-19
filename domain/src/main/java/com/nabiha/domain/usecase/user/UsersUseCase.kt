package com.nabiha.domain.usecase.user

import android.content.Context
import com.nabiha.apiresponse.users.UserApiLoginRequest
import com.nabiha.apiresponse.users.UserApiRegisterRequest
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import com.nabiha.domain.repository.UserRepository
import com.nabiha.domain.utils.Result
import com.nabiha.entity.UserEntity
import com.nabiha.entity.UserEntityLogin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersUseCase @Inject constructor(
    private val repository: UserRepository
): ApiUseCaseUsers<Long, UserApiRegisterRequest, UserApiLoginRequest, UserApiUpdateRequest, UserEntity, UserEntityLogin> {

    override suspend fun profile(): Flow<Result<UserEntity>> {
        return repository.fetchUserProfile()
    }

    override suspend fun update(params: Long,context: Context, data: UserApiUpdateRequest): Flow<Result<UserEntity>> {
        return repository.fetchUpdaterUser(params,context ,data)
    }

    override suspend fun login(params: UserApiLoginRequest): Flow<Result<UserEntityLogin>> {
        return repository.fetchLoginUser(params)
    }

    override suspend fun register(params: UserApiRegisterRequest): Flow<Result<UserEntity>> {
        return repository.fetchRegisterUser(params)
    }


}