package com.nabiha.optix.di

import com.nabiha.data.repoiml.UserRepoImpl
import com.nabiha.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserModule {
    @Binds
    fun bindUserRepository(userRepoImpl: UserRepoImpl): UserRepository
}