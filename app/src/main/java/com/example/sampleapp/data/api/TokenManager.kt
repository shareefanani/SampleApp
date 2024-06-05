package com.example.sampleapp.data.api

import com.example.sampleapp.common.PreferenceDataStoreConstants.ACCESS_TOKEN_KEY
import com.example.sampleapp.common.PreferenceDataStoreConstants.REFRESH_TOKEN
import com.example.sampleapp.common.PreferenceDataStoreHelper
import com.example.sampleapp.data.models.api_models.RefreshTokenData
import com.example.sampleapp.data.models.api_models.RefreshTokenRequest
import com.example.sampleapp.data.models.api_models.RefreshTokenResponse
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(
    private val apiService: ApiService,
    private val dataStore: PreferenceDataStoreHelper
) {

    suspend fun refreshToken(): Boolean {
        val refreshToken = getRefreshTokenFromDB()
        val resultDeferred = CompletableDeferred<Boolean>()

        getNewToken(refreshToken).collect { response ->
            if (response.isSuccessful && response.code() == 200) {
                response.body()?.data?.let { newAccessTokens ->
                    storeNewAccessToken(newAccessTokens)
                    resultDeferred.complete(true)
                }
            } else {
                resultDeferred.complete(false)
            }
        }

        return resultDeferred.await()
    }

    private fun getNewToken(refreshToken: String): Flow<Response<RefreshTokenResponse>> {
        val request = RefreshTokenRequest("Bearer $refreshToken")
        return flow { emit(apiService.refreshToken(request)) }.flowOn(Dispatchers.IO)
    }

    private suspend fun getRefreshTokenFromDB(): String {
        return withContext(Dispatchers.Default) {
            val tokenFlow = dataStore.getPreference(REFRESH_TOKEN, "")
            tokenFlow.first()
        }
    }

    private suspend fun storeNewAccessToken(data: RefreshTokenData) {
        dataStore.putPreference(ACCESS_TOKEN_KEY, data.accessToken.toString())
        dataStore.putPreference(REFRESH_TOKEN, data.refreshToken.toString())
    }
}