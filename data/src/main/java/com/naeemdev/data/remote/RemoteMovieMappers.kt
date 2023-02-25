package com.naeemdev.data.remote

import com.naeemdev.data.remote.response.Movie
import com.naeemdev.data.remote.response.MoviesResponse
import com.naeemdev.domain.entity.Movies
import com.naeemdev.domain.entity.NetworkMovie
import retrofit2.Response


fun Response<MoviesResponse>.asMovies(): Response<Movies> = Response.success(body()?.let {
    Movies(
        results = it.results.map { movies -> movies.asMovie() },
        currentPage = 1,
        totalPages = it.totalPages,
    )
})

fun Movie.asMovie() = NetworkMovie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    voteAverage = voteAverage,
)