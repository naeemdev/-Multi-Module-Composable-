package com.naeemdev.data


import com.naeemdev.data.remote.RemoteDataSource
import com.naeemdev.domain.entity.Movies
import com.naeemdev.domain.gateway.APIGateway
import retrofit2.Response
import javax.inject.Inject


class NetworkRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): APIGateway {

    override suspend fun getPopularMovies(page: Int): Response<Movies> {
        return remoteDataSource.getPopularMovies(page)
    }

    override suspend fun getNowPlayingMovies(page: Int): Response<Movies> {
        return remoteDataSource.getNowPlaying(page)
    }

    override suspend fun getUpcomingMovies(page: Int): Response<Movies> {
        return remoteDataSource.getUpcoming(page)
    }

}