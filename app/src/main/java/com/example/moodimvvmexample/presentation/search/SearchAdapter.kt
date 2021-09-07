package com.example.moodimvvmexample.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moodimvvmexample.data.models.tmdb.TmdbResult
import com.example.moodimvvmexample.databinding.ItemSearchMovieBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchTmdbMovieViewHolder>() {

    private var tmdbResultList: List<TmdbResult> = listOf()
    private lateinit var searchMovieItemClickListener: (TmdbResult) -> Unit

    inner class SearchTmdbMovieViewHolder(
        private val binding: ItemSearchMovieBinding,
        val searchMovieItemClickListener: (TmdbResult) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: TmdbResult) = with(binding) {
            movieTitle.text = data.title
            movieOverview.text = data.overview
            Glide.with(moviePoster.context)
                .load("https://image.tmdb.org/t/p/w92${data.posterPath}")
                .into(moviePoster)
        }

        fun bindViews(data: TmdbResult) = with(binding) {
            root.setOnClickListener {
                searchMovieItemClickListener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTmdbMovieViewHolder {
        val view =
            ItemSearchMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchTmdbMovieViewHolder(view, searchMovieItemClickListener)
    }

    override fun onBindViewHolder(holder: SearchTmdbMovieViewHolder, position: Int) {
        holder.bindData(tmdbResultList[position])
        holder.bindViews(tmdbResultList[position])
    }

    override fun getItemCount(): Int = tmdbResultList.size

    fun setSearchMovieList(tmdbList : List<TmdbResult>, searchMovieItemClickListener: (TmdbResult) -> Unit) {
        this.tmdbResultList = tmdbList
        this.searchMovieItemClickListener = searchMovieItemClickListener
        notifyDataSetChanged()
    }
}