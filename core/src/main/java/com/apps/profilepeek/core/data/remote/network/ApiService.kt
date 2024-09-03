package com.apps.profilepeek.core.data.remote.network

import com.apps.profilepeek.core.data.remote.response.PersonResponse
import retrofit2.http.GET

interface ApiService {

    @GET("getData/test")
    suspend fun getPerson(): PersonResponse

}