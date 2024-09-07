package com.example.paginationtest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationtest.data.AnimeData
import com.example.paginationtest.data.remote.KitsuApi
import javax.inject.Inject


class AnimePagingSource @Inject constructor(
    private val animeApi: KitsuApi
) : PagingSource<Int, AnimeData>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeData>): Int? {
        return state.anchorPosition?.let { anchorPos ->
            state.closestPageToPosition(anchorPos)?.prevKey?.plus(1) ?: 0
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeData> {
        val page = params.key ?: 1
        return try {
            val response =
                animeApi.getTrendingAnime(params.loadSize, offset = page * params.loadSize)
            val animeList = response.data
            LoadResult.Page(
                data = animeList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (animeList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
