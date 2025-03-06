package com.example.newsapp.data.apiService

import retrofit2.http.GET
import retrofit2.http.Query
import com.example.newsapp.data.model.ApiResponse
interface ApiService {

    @GET("top-headlines")
   suspend fun getHeadLines(
        @Query("country") country: String="us",
        @Query("apiKey") apiKey: String="b45226e5a0a64ec5a2454b4f92cd155a"
    ): ApiResponse

   @GET("everything")
   suspend fun getEverything(
       @Query("q") q: String="us",
       @Query("apiKey") apiKey: String="b45226e5a0a64ec5a2454b4f92cd155a"
   ): ApiResponse

}