package com.nabiha.data.module

import android.content.Context
import com.nabiha.data.datastore.PreferenceDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun providePreferenceDatastore(@ApplicationContext context: Context): PreferenceDatastore {
        return PreferenceDatastore(context)
    }
}