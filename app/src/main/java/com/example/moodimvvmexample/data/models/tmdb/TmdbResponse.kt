package com.example.moodimvvmexample.data.models.tmdb


import com.google.gson.annotations.SerializedName

data class TmdbResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val tmdbResults: List<TmdbResult>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
    //네임 테스트
    //추가햅ㅆ다
)