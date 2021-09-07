package com.example.moodimvvmexample.data.repository.repository

import com.example.moodimvvmexample.data.models.tmdb.TmdbResult
import com.example.moodimvvmexample.network.MovieSearchApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieSearchRepositoryImpl(
    val movieSearchApiService: MovieSearchApiService,
    val ioDispatcher: CoroutineDispatcher
) : MovieSearchRepository {
    
    override suspend fun getSearchMovieAll(
        page: Int,
        query: String,
        language: String
    ): List<TmdbResult>? = withContext(ioDispatcher) {
        val tmdbResponse = movieSearchApiService.getSearchMovieAll(page, query, language)
        return@withContext if (tmdbResponse.isSuccessful) {
            tmdbResponse?.body()?.tmdbResults
        } else {
            null
        }
    }


}