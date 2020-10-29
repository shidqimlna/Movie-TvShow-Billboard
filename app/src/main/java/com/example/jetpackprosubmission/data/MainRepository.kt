package com.example.jetpackprosubmission.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource.*

class MainRepository constructor(private val remoteDataSource: RemoteDataSource) :
    MainRepositoryInterface {

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(remoteData)
            }
    }

    override fun getMovieList(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getMovieList(object : LoadMoviesCallback {

            override fun onAllMoviesReceived(movieResponse: List<MovieEntity>) {
                movieResults.postValue(movieResponse)
            }
        })

        return movieResults
    }

    override fun getMovieDetail(movieId: String?): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovieDetail(movieId, object : LoadMovieDetailCallback {

            override fun onDetailMovieReceived(movieResponse: MovieEntity) {
                movieResult.postValue(movieResponse)
            }
        })

        return movieResult
    }


    override fun getTvShowList(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getTvShowList(object : LoadTvShowsCallback {

            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowEntity>) {
                tvShowResults.postValue(tvShowResponse)
            }
        })

        return tvShowResults
    }

    override fun getTvShowDetail(tvShowId: String?): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShowDetail(tvShowId, object : LoadTvShowDetailCallback {

            override fun onDetailTvShowReceived(tvShowResponse: TvShowEntity) {
                tvShowResult.postValue(tvShowResponse)
            }
        })

        return tvShowResult
    }


}
