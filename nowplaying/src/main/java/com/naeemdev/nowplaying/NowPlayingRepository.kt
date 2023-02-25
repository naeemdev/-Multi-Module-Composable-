package com.naeemdev.nowplaying

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.naeemdev.domain.entity.NetworkMovie
import com.naeemdev.domain.usecase.NowPlayingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NowPlayingRepository @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase
) {

    fun nowPlaying(): Flow<PagingData<NetworkMovie>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        )
        return Pager(config) {
            NowPlayingDataSource(
                nowPlayingUseCase = nowPlayingUseCase
            )
        }.flow
    }
}
