package com.example.jetpackprosubmission.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.vo.Resource

class TvShowViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var tvshowId: String? = null
    var tvShow: LiveData<Resource<TvShowEntity>>? = null
    var tvShows: LiveData<Resource<PagedList<TvShowEntity>>>? = null

    fun setTvShow(tvshowId: String?) {
        this.tvshowId = tvshowId
    }

    fun getTvShowDetail(): LiveData<Resource<TvShowEntity>> {
        if (tvShow == null) tvShow = mainRepository.getTvShowDetail(tvshowId)
        return tvShow as LiveData<Resource<TvShowEntity>>
    }

    fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>> {
        if (tvShows == null) tvShows = mainRepository.getTvShowList()
        return tvShows as LiveData<Resource<PagedList<TvShowEntity>>>
    }

//    fun insertFavoriteTvShow(tvShow: TvShowEntity){
//        mainRepository.insertFavoriteTvShow(tvShow)
//    }
//
//    fun deleteFavoriteTvShow(tvShow: TvShowEntity){
//        mainRepository.deleteFavoriteTvShow(tvShow)
//    }

}