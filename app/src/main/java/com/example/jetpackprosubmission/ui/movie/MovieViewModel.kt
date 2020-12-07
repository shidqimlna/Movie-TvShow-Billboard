package com.example.jetpackprosubmission.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.vo.Resource

class MovieViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var movieId = MutableLiveData<String>()
    private var movie: LiveData<Resource<MovieEntity>>? =
        Transformations.switchMap(movieId) { mMovieId ->
            mainRepository.getMovieDetail(mMovieId)
        }
    private var movies: LiveData<Resource<PagedList<MovieEntity>>>? = null

    fun setMovieId(movieId: String?) {
        this.movieId.value = movieId
    }

    fun setMovieDetail(movie: MovieEntity?) {
        this.movie?.value?.data = movie
    }

    fun getMovieDetail(): LiveData<Resource<MovieEntity>> {
        if (movie == null) movie = mainRepository.getMovieDetail(movieId.value)
        return movie as LiveData<Resource<MovieEntity>>
    }

    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> {
        if (movies == null) movies = mainRepository.getMovieList()
        return movies as LiveData<Resource<PagedList<MovieEntity>>>
    }

    fun checkFavorite(): LiveData<Int>? = movieId.value?.let { mainRepository.checkFavorite(it) }

    fun existFavoriteMovie(title: String?): Boolean {
        return mainRepository.existFavoriteMovie(title)
    }

    fun insertFavoriteMovie(movieEntity: MovieEntity) {
        val favorite = getFavorite(movieEntity)
        mainRepository.insertFavoriteMovie(favorite)
    }

    fun deleteFavoriteMovie(movieEntity: MovieEntity) {
        Log.e("Movie VM", "DELETE FAV MOVIE ENTITY :" + movieEntity.title)
        val favorite = getFavorite(movieEntity)
        Log.e("Movie VM", "DELETE FAV FAVORITE :" + favorite.title)
        mainRepository.deleteFavoriteMovie(favorite)
    }

    private fun getFavorite(movie: MovieEntity): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = movie.id,
            title = movie.title,
            releaseDate = movie.releaseDate,
            overview = movie.overview,
            posterPath = movie.posterPath,
            runtime = movie.runtime,
            voteAverage = movie.voteAverage,
            genres = movie.genres
        )
    }

    fun insertFavorite() {
        val favorite = getFavorite()
        if (favorite != null)
            mainRepository.insertFavoriteMovie(favorite)
    }

    fun deleteFavorite() {
        val favorite = getFavorite()
        if (favorite != null)
            mainRepository.deleteFavoriteMovie(favorite)
    }

    private fun getFavorite(): FavoriteMovieEntity? {
        var favorite: FavoriteMovieEntity? = null

        val movieResource = movie?.value
        Log.d("ERROR 1", movieResource.toString())
        if (movieResource != null) {
            val movie = movieResource.data
            Log.d("ERROR 2", movie.toString())
            if (movie != null) {
                favorite = FavoriteMovieEntity(
                    id = movie.id,
                    title = movie.title,
                    releaseDate = movie.releaseDate,
                    overview = movie.overview,
                    posterPath = movie.posterPath,
                    runtime = movie.runtime,
                    voteAverage = movie.voteAverage,
                    genres = movie.genres
                )
                Log.d("ERROR 3", favorite.toString())

            }
        }
        return favorite
    }
}