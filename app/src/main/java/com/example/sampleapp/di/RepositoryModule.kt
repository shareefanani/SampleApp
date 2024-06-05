package com.example.sampleapp.di

import com.example.sampleapp.data.api.ApiService
import com.example.sampleapp.data.db.UserDao
import com.example.sampleapp.data.repository.UsersRepository
import com.example.sampleapp.domain.IUsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepo(userDao: UserDao, apiService: ApiService): IUsersRepository =
        UsersRepository(userDao = userDao, apiService = apiService)
}