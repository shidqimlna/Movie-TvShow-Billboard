package com.example.jetpackprosubmission.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity

import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.data.source.local.room.MainDao

class LocalDataSource private constructor(private val mainDao: MainDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mainDao: MainDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(mainDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getMovieList(): DataSource.Factory<Int, MovieEntity> = mainDao.getMovieList()

    fun insertMovies(movie: List<MovieEntity>) {
        mainDao.insertMovies(movie)
    }

    fun getMovieDetail(movieId: String?): LiveData<MovieEntity> = mainDao.getMovieDetail(movieId)

    fun insertMovie(movies: MovieEntity) = mainDao.insertMovie(movies)

    fun setDetailMovie(movie: MovieEntity) = mainDao.updateMovie(movie)

    fun getTvShowList(): DataSource.Factory<Int, TvShowEntity> = mainDao.getTvShowList()

    fun insertTvShows(tvShow: List<TvShowEntity>) {
        mainDao.insertTvShows(tvShow)
    }

    fun getTvShowDetail(tvShowId: String?): LiveData<TvShowEntity> =
        mainDao.getTvShowDetail(tvShowId)

    fun insertTvShow(tvShow: TvShowEntity) = mainDao.insertTvShow(tvShow)

    fun setDetailTvShow(tvShow: TvShowEntity) = mainDao.updateTvShow(tvShow)

    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteMovieEntity> =
        mainDao.getFavoriteMovies()

    fun getFavoriteTvShow(): DataSource.Factory<Int, FavoriteTvShowEntity> =
        mainDao.getFavoriteTvShows()

    fun checkFavorite(favoriteId: String): LiveData<Int> = mainDao.checkFavorite(favoriteId)

    fun existFavoriteMovie(title: String?): Boolean =
        mainDao.existFavoriteMovie(title)

    fun insertFavoriteMovie(favoriteEntity: FavoriteMovieEntity) =
        mainDao.insertFavoriteMovie(favoriteEntity)

    fun insertFavoriteTvShow(favoriteEntity: FavoriteTvShowEntity) =
        mainDao.insertFavoriteTvShow(favoriteEntity)

    fun deleteFavoriteMovie(favoriteEntity: FavoriteMovieEntity) =
        mainDao.deleteFavoriteMovie(favoriteEntity)

    fun deleteFavoriteTvShow(favoriteEntity: FavoriteTvShowEntity) =
        mainDao.deleteFavoriteTvShow(favoriteEntity)
}