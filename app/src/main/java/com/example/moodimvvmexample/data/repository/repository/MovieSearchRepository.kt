package com.example.moodimvvmexample.data.repository.repository

import com.example.moodimvvmexample.data.models.tmdb.TmdbResult

interface MovieSearchRepository {

    suspend fun getSearchMovieAll(page : Int, query : String, language : String) : List<TmdbResult>?
}