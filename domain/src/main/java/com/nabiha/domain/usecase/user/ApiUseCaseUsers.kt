package com.nabiha.domain.usecase.user

import android.content.Context
import com.nabiha.apiresponse.users.UserApiUpdateRequest
import com.nabiha.domain.utils.Result
import com.nabiha.domain.utils.UseCase
import com.nabiha.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseUsers<ID,Register,Login,Update,Entity, EntityLogin> : UseCase {
    suspend fun profile(): Flow<Result<Entity>>
    suspend fun register(params: Register): Flow<Result<Entity>>
    suspend fun login(params: Login): Flow<Result<EntityLogin>>
    suspend fun loginGoogle(idToken: String): Flow<Result<EntityLogin>>
    suspend fun update(params:ID, context: Context,data: Update): Flow<Result<Entity>>
    suspend fun fetchUpdateUser(data: UserApiUpdateRequest): Flow<Result<UserEntity>>
    suspend fun fetchUpdatePassword(newPassword: String): Flow<Result<UserEntity>>
}