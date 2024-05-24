package com.nabiha.optix.di

import com.nabiha.data.repoiml.WhistListRepoImpl
import com.nabiha.domain.repository.WhistListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WhistListModule {
    @Binds
    fun bindWhistListRepository(whistListRepoImpl: WhistListRepoImpl): WhistListRepository
}