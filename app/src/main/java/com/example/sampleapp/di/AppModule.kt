package com.example.sampleapp.di

import android.content.Context
import com.example.sampleapp.common.PreferenceDataStoreHelper
import com.example.sampleapp.data.api.ApiService
import com.example.sampleapp.data.api.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providePreferenceDataStore(@ApplicationContext context: Context): PreferenceDataStoreHelper {
        return PreferenceDataStoreHelper(context)
    }

    @Singleton
    @Provides
    fun provideTokenManager(apiService: ApiService, dataStoreHelper: PreferenceDataStoreHelper): TokenManager {
        return TokenManager(apiService, dataStoreHelper)
    }
}