package com.naeemdev.data.remote

import com.naeemdev.data.remote.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int
    ): Response<MoviesResponse>

}
