package com.nabiha.domain.utils

import kotlinx.coroutines.flow.Flow

interface UseCase
interface ApiUseCaseParams<Params, Type> : UseCase {
    suspend fun execute(params: Params): Flow<Result<Type>>
}
interface ApiUseCaseNonParams<Type> : UseCase {
    suspend fun execute(): Flow<Result<Type>>
}

interface ApiUseCaseUsers<ID,Register,Login,Update,Entity, EntityLogin> : UseCase {
    suspend fun profile(): Flow<Result<Entity>>
    suspend fun register(params: Register): Flow<Result<Entity>>
    suspend fun login(params: Login): Flow<Result<EntityLogin>>
    suspend fun update(params:ID, data: Update): Flow<Result<Entity>>
}