package com.example.jetpackprosubmission.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity

class MovieViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var movieId: String? = null

    fun setMovie(movieId: String?) {
        this.movieId = movieId
    }

    fun getMovieDetail(): LiveData<MovieEntity> = mainRepository.getMovieDetail(movieId)

    fun getMovieList(): LiveData<List<MovieEntity>> = mainRepository.getMovieList()
}