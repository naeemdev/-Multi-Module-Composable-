package com.naeemdev.upcoming

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.naeemdev.domain.entity.NetworkMovie
import com.naeemdev.domain.usecase.UpcomingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UpcomingRepository @Inject constructor(
    private val upcomingUseCase: UpcomingUseCase
) {

    fun upcoming(): Flow<PagingData<NetworkMovie>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        )
        return Pager(config) {
            UpcomingDataSource(
                upcomingUseCase = upcomingUseCase
            )
        }.flow
    }
}
