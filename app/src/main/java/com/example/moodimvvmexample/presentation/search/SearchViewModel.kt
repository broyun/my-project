package com.example.moodimvvmexample.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moodimvvmexample.base.BaseViewModel
import com.example.moodimvvmexample.data.repository.repository.MovieSearchRepository
import com.example.moodimvvmexample.data.repository.repository.MovieSearchRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val movieSearchRepository: MovieSearchRepository
) : BaseViewModel() {

    private var _tmdbSearchListStateLiveData =
        MutableLiveData<SearchState>(SearchState.UnInitialized)
    val tmdbSearchListStateLiveData: LiveData<SearchState> = _tmdbSearchListStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        setState(
            SearchState.Loading
        )
    }

    fun searchMovie(page : Int, query : String, language : String): Job = viewModelScope.launch {
        setState(
            SearchState.Loading
        )
        movieSearchRepository.getSearchMovieAll(page, query, language)?.let { tmdbResults ->
            Log.d("Success", "Success")
            setState(
                SearchState.Success(
                    tmdbResults
                )
            )
        } ?: kotlin.run {
            setState(SearchState.Error)
        }


    }

    private fun setState(state: SearchState) {
        _tmdbSearchListStateLiveData.postValue(state)
    }

}