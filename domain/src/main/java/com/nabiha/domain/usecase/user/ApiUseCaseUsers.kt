package com.nabiha.domain.usecase.user

import com.nabiha.domain.utils.Result
import com.nabiha.domain.utils.UseCase
import kotlinx.coroutines.flow.Flow

interface ApiUseCaseUsers<ID,Register,Login,Update,Entity, EntityLogin> : UseCase {
    suspend fun profile(): Flow<Result<Entity>>
    suspend fun register(params: Register): Flow<Result<Entity>>
    suspend fun login(params: Login): Flow<Result<EntityLogin>>
    suspend fun update(params:ID, data: Update): Flow<Result<Entity>>
}