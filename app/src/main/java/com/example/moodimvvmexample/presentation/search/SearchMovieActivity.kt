package com.example.moodimvvmexample.presentation.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodimvvmexample.base.BaseActivity
import com.example.moodimvvmexample.databinding.ActivityMainBinding
import com.example.moodimvvmexample.databinding.ActivitySearchMovieBinding
import org.koin.android.ext.android.inject

internal class SearchMovieActivity : BaseActivity<SearchViewModel, ActivitySearchMovieBinding>() {

    override val viewModel: SearchViewModel by inject()

    override fun getViewBinding(): ActivitySearchMovieBinding = ActivitySearchMovieBinding.inflate(layoutInflater)

    private val searchAdapter : SearchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //ViewModel의 State를 구독하다가 State의 변화에 따라서 UI를 변경
    override fun observeData() = viewModel.tmdbSearchListStateLiveData.observe(this) {
        when(it) {
            is SearchState.UnInitialized -> initViews()
            is SearchState.Loading -> { handleLoading(it)}
            is SearchState.Success -> { handleSuccess(it)}
            is SearchState.Error -> { handleError(it)}
        }
    }

    private fun initViews() = with(binding) {
        searchRecyclerView.adapter = searchAdapter
        searchRecyclerView.layoutManager = LinearLayoutManager(this@SearchMovieActivity, RecyclerView.VERTICAL, false)
        searchBtn.setOnClickListener {
            viewModel.searchMovie(1, searchEditText.text.toString() ?: "", "ko")
        }
    }

    private fun handleLoading(state : SearchState.Loading) = with(binding) {

    }

    private fun handleSuccess(state : SearchState.Success) = with(binding) {
        val tmResults = state.tmdbSearchList
        searchAdapter.setSearchMovieList(tmResults) { tmResult ->
            Toast.makeText(this@SearchMovieActivity, tmResult.title, Toast.LENGTH_SHORT).show()
        }

    }

    private fun handleError(state : SearchState.Error) = with(binding) {
        Toast.makeText(this@SearchMovieActivity, "실패", Toast.LENGTH_SHORT).show()
    }
}