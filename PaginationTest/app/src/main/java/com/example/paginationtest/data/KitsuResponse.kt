package com.example.paginationtest.data

data class KitsuResponse(
    val data: List<AnimeData>
)


data class AnimeData(
    val id: String, val attributes: Attributes
)

data class Attributes(
    val canonicalTitle: String, val originalTitle: String, val posterImage: PosterImage
)

data class PosterImage(
    val original: String
)
