package com.example.jetpackprosubmission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.ui.movie.MovieViewModel
import com.example.jetpackprosubmission.ui.tvshow.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        with(modelClass) {
            return when {
                isAssignableFrom(MovieViewModel::class.java) -> {
                    MovieViewModel(repository) as T
                }
                isAssignableFrom(TvShowViewModel::class.java) -> {
                    TvShowViewModel(repository) as T
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}
