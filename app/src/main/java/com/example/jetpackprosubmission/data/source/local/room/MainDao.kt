package com.example.jetpackprosubmission.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity

@Dao
interface MainDao {

    @Query("SELECT * FROM movieEntity")
    fun getMovieList(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movieEntity WHERE id = :movieId")
    fun getMovieDetail(movieId: String?): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tvShowEntity")
    fun getTvShowList(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM tvShowEntity WHERE id = :tvShowId")
    fun getTvShowDetail(tvShowId: String?): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: TvShowEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM favoriteMovieEntity")
    fun getFavoriteMovies(): DataSource.Factory<Int, FavoriteMovieEntity>

    @Query("SELECT * FROM favoriteTvShowEntity")
    fun getFavoriteTvShows(): DataSource.Factory<Int, FavoriteTvShowEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM favoriteMovieEntity WHERE title = :title)")
    fun existFavoriteMovie(title: String?): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favoriteEntity: FavoriteMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteTvShow(favoriteEntity: FavoriteTvShowEntity)

    @Delete
    fun deleteFavoriteMovie(favoriteEntity: FavoriteMovieEntity)

    @Delete
    fun deleteFavoriteTvShow(favoriteEntity: FavoriteTvShowEntity)
}
