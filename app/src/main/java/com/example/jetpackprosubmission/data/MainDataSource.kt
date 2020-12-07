package com.example.jetpackprosubmission.data


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.vo.Resource

interface MainDataSource {

    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieDetail(movieId: String?): LiveData<Resource<MovieEntity>>

    fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTvShowDetail(tvShowId: String?): LiveData<Resource<TvShowEntity>>

    fun getAllFavoriteMovies(): LiveData<PagedList<FavoriteMovieEntity>>

    fun getAllFavoriteTvShow(): LiveData<PagedList<FavoriteTvShowEntity>>

    fun checkFavorite(favoriteId: String): LiveData<Int>

    fun existFavoriteMovie(id: String?): Boolean

    fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    fun insertFavoriteTvShow(favoriteTvShowEntity: FavoriteTvShowEntity)

    fun deleteFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    fun deleteFavoriteTvShow(favoriteTvShowEntity: FavoriteTvShowEntity)

}