package com.nabiha.optix.di

import com.nabiha.data.repoiml.HistoryRepoImpl
import com.nabiha.domain.repository.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HistoryModule {
    @Binds
    fun bindHistoryRepository(historyRepoImpl: HistoryRepoImpl): HistoryRepository
}