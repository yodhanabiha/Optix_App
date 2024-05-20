package com.nabiha.optix.di

import com.nabiha.data.repoiml.ProductRepoImpl
import com.nabiha.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProductModule {
    @Binds
    fun bindProductRepository(productRepoImpl: ProductRepoImpl): ProductRepository
}

