package com.example.jetpackprosubmission.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity

class TvShowViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var tvshowId: String? = null

    fun setTvShow(tvshowId: String?) {
        this.tvshowId = tvshowId
    }

    fun getTvShowDetail(): LiveData<TvShowEntity> = mainRepository.getTvShowDetail(tvshowId)

    fun getTvShowList(): LiveData<List<TvShowEntity>> = mainRepository.getTvShowList()

}