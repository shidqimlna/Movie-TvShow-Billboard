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
    var movie: LiveData<Resource<MovieEntity>>? =
        Transformations.switchMap(movieId) { mMovieId ->
            mainRepository.getMovieDetail(mMovieId)
        }
    var movies: LiveData<Resource<PagedList<MovieEntity>>>? = null

    fun setMovie(movieId: String?) {
        this.movieId.value = movieId
    }

    fun getMovieDetail(): LiveData<Resource<MovieEntity>> {
        if (movie == null) movie = mainRepository.getMovieDetail(movieId.value)
        return movie as LiveData<Resource<MovieEntity>>
    }

    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> {
        if (movies == null) movies = mainRepository.getMovieList()
        Log.i("VIEWMODEL", "VIEWMODEL :" + movies?.value?.data?.get(0)?.title)
        return movies as LiveData<Resource<PagedList<MovieEntity>>>
    }

    fun insertFavoriteMovie() {
        val favorite = getFavorite()
        if (favorite != null)
            mainRepository.insertFavoriteMovie(favorite)
    }

    fun deleteFavoriteMovie() {
        val favorite = getFavorite()
        if (favorite != null)
            mainRepository.deleteFavoriteMovie(favorite)
    }

    private fun getFavorite(): FavoriteMovieEntity? {
        var favoriteEntity: FavoriteMovieEntity? = null
        val movieResource = movie?.value
        Log.d("ERROR 1", movieResource.toString())
        if (movieResource != null) {
            val movie = movieResource.data
            Log.d("ERROR 2", movie.toString())
            if (movie != null) {
                favoriteEntity = FavoriteMovieEntity(
                    id = movie.id,
                    title = movie.title,
                    releaseDate = movie.releaseDate,
                    posterPath = movie.posterPath,
                )
                Log.d("ERROR 3", favoriteEntity.toString())
            }
        }
        return favoriteEntity
    }
}