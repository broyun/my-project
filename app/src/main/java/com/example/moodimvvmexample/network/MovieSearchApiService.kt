package com.example.moodimvvmexample.network

import com.example.moodimvvmexample.BuildConfig
import com.example.moodimvvmexample.data.models.tmdb.TmdbResponse
import com.example.moodimvvmexample.data.models.tmdb.TmdbResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchApiService {
    @GET("/3/search/movie")
    fun getTmdbMovieResult(
        @Query("api_key") api_key: String?,
        @Query("page") page: Int,
        @Query("query") query: String?,
        @Query("language") language: String?
    ): Call<TmdbResult?>


    @GET("/3/search/movie?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getSearchMovieAll(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("language") language: String
    ): Response<TmdbResponse>
}