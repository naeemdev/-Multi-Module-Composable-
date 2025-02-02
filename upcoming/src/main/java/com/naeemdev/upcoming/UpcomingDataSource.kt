package com.naeemdev.upcoming

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.naeemdev.domain.entity.NetworkMovie
import com.naeemdev.domain.usecase.UpcomingUseCase

import java.lang.IllegalStateException
import javax.inject.Inject


class UpcomingDataSource @Inject constructor(private val upcomingUseCase: UpcomingUseCase) :
    PagingSource<Int, NetworkMovie>() {
    override fun getRefreshKey(state: PagingState<Int, NetworkMovie>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovie> {
        try {
            val nextPage = params.key ?: 1
            val movieResponse = upcomingUseCase(nextPage)

            if (movieResponse.body()?.results.isNullOrEmpty()){
                return LoadResult.Error(throw Exception("Something went wrong"))
            }

            val list = movieResponse.body()?.results ?: emptyList()

            return LoadResult.Page(
                data = list,
                prevKey =
                if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (t: Throwable) {
            return LoadResult.Error(t)
        }
    }
}
