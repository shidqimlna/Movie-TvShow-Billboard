package com.example.jetpackprosubmission.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.vo.Resource

class TvShowViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var tvshowId = MutableLiveData<String>()
    private var tvShow: LiveData<Resource<TvShowEntity>>? =
        Transformations.switchMap(tvshowId) { mTvShowId ->
            mainRepository.getTvShowDetail(mTvShowId)
        }
    private var tvShows: LiveData<Resource<PagedList<TvShowEntity>>>? = null

    fun setTvShowId(tvshowId: String?) {
        this.tvshowId.value = tvshowId
    }

    fun getTvShowDetail(): LiveData<Resource<TvShowEntity>> {
        if (tvShow == null) tvShow = mainRepository.getTvShowDetail(tvshowId.value)
        return tvShow as LiveData<Resource<TvShowEntity>>
    }

    fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>> {
        if (tvShows == null) tvShows = mainRepository.getTvShowList()
        return tvShows as LiveData<Resource<PagedList<TvShowEntity>>>
    }

    fun checkFavoriteTvShow(): LiveData<Int>? =
        tvshowId.value?.let { mainRepository.checkFavoriteTvShow(it) }

    fun insertFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val favorite = convertFavorite(tvShowEntity)
        mainRepository.insertFavoriteTvShow(favorite)
    }

    fun deleteFavoriteTvShow(tvShowEntity: TvShowEntity) {
        val favorite = convertFavorite(tvShowEntity)
        mainRepository.deleteFavoriteTvShow(favorite)
    }

    private fun convertFavorite(tvShowEntity: TvShowEntity): FavoriteTvShowEntity {
        return FavoriteTvShowEntity(
            id = tvShowEntity.id,
            name = tvShowEntity.name,
            posterPath = tvShowEntity.posterPath,
            overview = tvShowEntity.overview,
            firstAirDate = tvShowEntity.firstAirDate,
            lastAirDate = tvShowEntity.lastAirDate,
            voteAverage = tvShowEntity.voteAverage,
            numberOfEpisodes = tvShowEntity.numberOfEpisodes,
            numberOfSeasons = tvShowEntity.numberOfSeasons,
            genres = tvShowEntity.genres
        )
    }

}