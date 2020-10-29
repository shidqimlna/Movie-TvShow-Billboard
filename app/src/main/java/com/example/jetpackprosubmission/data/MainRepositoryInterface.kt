package com.example.jetpackprosubmission.data


import androidx.lifecycle.LiveData
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity

interface MainRepositoryInterface {

    fun getMovieList(): LiveData<List<MovieEntity>>

    fun getMovieDetail(movieId: String?): LiveData<MovieEntity>

    fun getTvShowList(): LiveData<List<TvShowEntity>>

    fun getTvShowDetail(tvShowId: String?): LiveData<TvShowEntity>

}