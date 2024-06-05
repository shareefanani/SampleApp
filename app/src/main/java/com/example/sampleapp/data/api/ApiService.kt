package com.example.sampleapp.data.api

import com.example.sampleapp.data.models.api_models.RefreshTokenRequest
import com.example.sampleapp.data.models.api_models.RefreshTokenResponse
import com.example.sampleapp.data.models.api_models.UserDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("users?page=2")
    suspend fun getAllUsers(): Response<UserDataResponse>

    // Dummy sample
    @POST("api/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<RefreshTokenResponse>
}