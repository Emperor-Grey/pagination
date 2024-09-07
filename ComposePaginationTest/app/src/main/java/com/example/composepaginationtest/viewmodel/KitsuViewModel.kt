package com.example.paginationtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composepaginationtest.data.AnimeData
import com.example.composepaginationtest.data.remote.KitsuApi
import com.example.paginationtest.data.paging.AnimePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class KitsuViewModel @Inject constructor(
    private val animeApi: KitsuApi,
) : ViewModel() {

    val animes: Flow<PagingData<AnimeData>> = Pager(
        PagingConfig(
            10, prefetchDistance = 2, enablePlaceholders = true, initialLoadSize = 2
        )
    ) {
        AnimePagingSource(animeApi)
    }.flow.cachedIn(viewModelScope)
}
