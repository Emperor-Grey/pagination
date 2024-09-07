package com.example.composepaginationtest.data.remote

import com.example.composepaginationtest.data.KitsuResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuApi {

    @GET("anime")
    suspend fun getTrendingAnime(
        @Query("page[limit]") limit: Int = 10, @Query("page[offset]") offset: Int = 0
    ): KitsuResponse
}
