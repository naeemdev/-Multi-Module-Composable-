package com.naeemdev.domain.gateway

import com.naeemdev.domain.entity.Movies
import retrofit2.Response

interface APIGateway {
    suspend fun getPopularMovies(page: Int): Response<Movies>
    suspend fun getNowPlayingMovies(page: Int): Response<Movies>
    suspend fun getUpcomingMovies(page: Int): Response<Movies>
}