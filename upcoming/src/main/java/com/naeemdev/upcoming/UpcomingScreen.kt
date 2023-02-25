package com.naeemdev.upcoming

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.naeemdev.common.items.MovieItem
import com.naeemdev.common.loading.ShimmerAnimation
import com.naeemdev.common.theme.MultiModuleComposable
import com.naeemdev.domain.entity.NetworkMovie


@Composable
fun UpcomingScreen() {
    val viewModel = hiltViewModel<UpcomingViewModel>()

    val upcomingList = viewModel.upcomingList.collectAsLazyPagingItems()

    MultiModuleComposable(darkTheme = true) {
        UpcomingList(movieList = upcomingList)
    }
}

@Composable
fun UpcomingList(movieList: LazyPagingItems<NetworkMovie>) {
    LazyColumn(
        modifier = Modifier
            .background(color = Color.DarkGray)
    ) {
        items(movieList.itemCount) { index ->
            movieList[index]?.let { movie ->
                MovieItem(
                    posterPath = movie.posterUrl,
                    title = movie.title,
                    desc = movie.overview
                )
            }
        }

        movieList.apply {
            val error = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }

            val loading = when {
                loadState.prepend is LoadState.Loading -> loadState.prepend as LoadState.Loading
                loadState.append is LoadState.Loading -> loadState.append as LoadState.Loading
                loadState.refresh is LoadState.Loading -> loadState.refresh as LoadState.Loading
                else -> null
            }

            if (loading != null) {
                repeat((0..20).count()) {
                    item {
                        Box(
                            modifier = Modifier
                                .background(color = Color.DarkGray)
                        ) {
                            ShimmerAnimation()
                        }
                    }
                }
            }

            if (error != null) {
                item {
                    Toast.makeText(LocalContext.current,
                        error.error.localizedMessage ?: "Error",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
