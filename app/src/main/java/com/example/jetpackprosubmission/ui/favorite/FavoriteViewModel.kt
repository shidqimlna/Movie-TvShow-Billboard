package com.example.jetpackprosubmission.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity

class FavoriteViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var liveDataMovies: LiveData<PagedList<FavoriteMovieEntity>>? = null
    private var liveDataTvShowsEntity: LiveData<PagedList<FavoriteTvShowEntity>>? = null

    fun getMovieList(): LiveData<PagedList<FavoriteMovieEntity>> {
        if (liveDataMovies == null) liveDataMovies = mainRepository.getAllFavoriteMovies()
        return liveDataMovies as LiveData<PagedList<FavoriteMovieEntity>>
    }

    fun getTvShowList(): LiveData<PagedList<FavoriteTvShowEntity>> {
        if (liveDataTvShowsEntity == null) liveDataTvShowsEntity =
            mainRepository.getAllFavoriteTvShow()
        return liveDataTvShowsEntity as LiveData<PagedList<FavoriteTvShowEntity>>
    }
}