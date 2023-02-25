package com.naeemdev.domain.usecase

import com.naeemdev.domain.entity.Movies
import com.naeemdev.domain.gateway.APIGateway
import retrofit2.Response
import javax.inject.Inject


class UpcomingUseCase @Inject constructor(
    private val apiGateway: APIGateway,
) {

    suspend operator fun invoke(page: Int): Response<Movies> {
        return apiGateway.getUpcomingMovies(page)
    }
}