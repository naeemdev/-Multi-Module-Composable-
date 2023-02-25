package com.naeemdev.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.naeemdev.upcoming.UpcomingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val upcomingRepository: UpcomingRepository,
) : ViewModel() {

    val upcomingList = upcomingRepository.upcoming().cachedIn(viewModelScope)

}
