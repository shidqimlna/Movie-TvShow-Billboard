package com.example.jetpackprosubmission.ui.tvshow

import androidx.lifecycle.ViewModel
import com.example.jetpackprosubmission.data.TvShowEntity
import com.example.jetpackprosubmission.util.DataDummy

class TvShowViewModel : ViewModel() {
    private var tvshowId: String? = null

    fun setSelectedTvShow(tvshowId: String?) {
        this.tvshowId = tvshowId
    }

    fun getTvShow(): TvShowEntity {
        lateinit var tvShow: TvShowEntity
        val tvShowsEntities = DataDummy.generateDummyTvShows()
        for (tvShowEntity in tvShowsEntities) {
            if (tvShowEntity.id == tvshowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }

    fun getTvShows(): List<TvShowEntity> = DataDummy.generateDummyTvShows()
}