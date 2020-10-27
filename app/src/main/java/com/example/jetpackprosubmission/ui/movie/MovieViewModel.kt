package com.example.jetpackprosubmission.ui.movie

import androidx.lifecycle.ViewModel
import com.example.jetpackprosubmission.data.MovieEntity
import com.example.jetpackprosubmission.util.DataDummy

class MovieViewModel : ViewModel() {
    private var movieId: String? = null

    fun setSelectedMovie(movieId: String?) {
        this.movieId = movieId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val moviesEntities = DataDummy.generateDummyMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getMovies(): List<MovieEntity> = DataDummy.generateDummyMovies()
}