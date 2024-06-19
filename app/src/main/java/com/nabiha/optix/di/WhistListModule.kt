package com.nabiha.optix.di

import com.nabiha.data.repoiml.WishListRepoImpl
import com.nabiha.domain.repository.WishListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WhistListModule {
    @Binds
    fun bindWhistListRepository(whistListRepoImpl: WishListRepoImpl): WishListRepository
}