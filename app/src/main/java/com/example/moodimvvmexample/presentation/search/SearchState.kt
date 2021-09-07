package com.example.moodimvvmexample.presentation.search

import com.example.moodimvvmexample.data.models.tmdb.TmdbResult

sealed class SearchState {

    object UnInitialized: SearchState()

    object Loading: SearchState()

    data class Success(
        val tmdbSearchList : List<TmdbResult>
    ): SearchState()

    object Error: SearchState()
}