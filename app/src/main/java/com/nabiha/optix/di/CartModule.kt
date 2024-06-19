package com.nabiha.optix.di

import com.nabiha.data.repoiml.CartRepoImpl
import com.nabiha.domain.repository.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CartModule {
    @Binds
    fun bindCartRepository(cartRepoImpl: CartRepoImpl): CartRepository
}