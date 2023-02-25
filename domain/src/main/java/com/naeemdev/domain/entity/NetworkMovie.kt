package com.naeemdev.domain.entity

import com.naeemdev.domain.util.Constants

data class NetworkMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val voteAverage: Double?,
) {
    val posterUrl = Constants.POSTER_URL + posterPath
}