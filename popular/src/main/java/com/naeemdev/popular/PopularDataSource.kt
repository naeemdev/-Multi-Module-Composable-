package com.naeemdev.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.naeemdev.domain.entity.NetworkMovie
import com.naeemdev.domain.usecase.PopularUseCase
import retrofit2.HttpException
import javax.inject.Inject

class PopularDataSource @Inject constructor(private val popularUseCase: PopularUseCase) :
    PagingSource<Int, NetworkMovie>() {

    override fun getRefreshKey(state: PagingState<Int, NetworkMovie>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovie> {
        return try {
            val nextPage = params.key ?: 1
            val response = popularUseCase(page = nextPage)

            if (response.body()?.results.isNullOrEmpty()) {
                return LoadResult.Error(throw Exception("Something went wrong"))
            }

            val list = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = list,
                prevKey =
                if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        } catch (t: Throwable) {
            return LoadResult.Error(t)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
