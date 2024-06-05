package com.example.sampleapp.domain

import com.example.sampleapp.data.models.roommodel.UserTable
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
    suspend fun refreshUsers()
    val allUsers: Flow<List<UserTable>>
    //suspend fun getUsersFromServer(): ResultWrapper<Response<UserDataResponse>>
}