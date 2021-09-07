package com.example.moodimvvmexample.di

import com.example.moodimvvmexample.BuildConfig
import com.example.moodimvvmexample.data.repository.repository.MovieSearchRepository
import com.example.moodimvvmexample.data.repository.repository.MovieSearchRepositoryImpl
import com.example.moodimvvmexample.network.MovieSearchApiService
import com.example.moodimvvmexample.network.Url
import com.example.moodimvvmexample.presentation.search.SearchViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val MoodiAppModule = module {

    //코루틴 디스패쳐 등록
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // Api
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }
    single<MovieSearchApiService> {
        Retrofit.Builder().baseUrl(Url.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    // Repositories 등록
    single<MovieSearchRepository> { MovieSearchRepositoryImpl(get(), get()) }

    //ViewModels
    viewModel { SearchViewModel(get()) }
}