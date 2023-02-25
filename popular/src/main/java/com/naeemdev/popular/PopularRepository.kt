package com.naeemdev.popular

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.naeemdev.domain.entity.NetworkMovie
import com.naeemdev.domain.usecase.PopularUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PopularRepository @Inject constructor(
    private val popularUseCase: PopularUseCase
) {

    fun popular(): Flow<PagingData<NetworkMovie>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        )
        return Pager(config) {
            PopularDataSource(
                popularUseCase = popularUseCase
            )
        }.flow
    }
}
