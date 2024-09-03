package com.apps.profilepeek.core.data.remote

import com.apps.profilepeek.core.data.remote.network.ApiResponse
import com.apps.profilepeek.core.data.remote.network.ApiService
import com.apps.profilepeek.core.data.remote.response.PersonResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getPerson(): Flow<ApiResponse<PersonResponse>>{
        return flow {
            try {
                val response = apiService.getPerson()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}