package com.example.jetpackprosubmission.data.source.remote

import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.data.source.remote.response.MovieApiResponse
import com.example.jetpackprosubmission.data.source.remote.response.TvShowApiResponse
import com.example.jetpackprosubmission.util.IdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val mainApiInterface: MainApiInterface) {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(mainApiInterface: MainApiInterface): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(mainApiInterface)
            }
    }

    fun getMovieList(callback: LoadMoviesCallback) {
        IdlingResource.increment()
        mainApiInterface.getMovieList()
            .enqueue(object : Callback<MovieApiResponse> {

                override fun onResponse(
                    call: Call<MovieApiResponse>,
                    response: Response<MovieApiResponse>
                ) {
                    try {
                        IdlingResource.decrement()
                        if (response.isSuccessful) {
                            val movieResponse: List<MovieEntity> =
                                response.body()?.results ?: emptyList()
                            callback.onAllMoviesReceived(movieResponse)
                        }
                    } catch (e: Exception) {
                        throw Exception(e.message.toString())
                    }
                }

                override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                    throw Exception(t.message.toString())
                }
            })
    }

    fun getMovieDetail(id: String?, callback: LoadMovieDetailCallback) {
        IdlingResource.increment()
        mainApiInterface.getMovieDetail(id).enqueue(object : Callback<MovieEntity> {
            override fun onResponse(
                call: Call<MovieEntity>,
                response: Response<MovieEntity>
            ) {
                try {
                    IdlingResource.decrement()
                    val movieResponse = response.body()
                    if (movieResponse != null) {
                        callback.onDetailMovieReceived(movieResponse)
                    }
                } catch (e: Exception) {
                    throw Exception(e.message.toString())
                }
            }

            override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                throw Exception(t.message.toString())
            }
        })
    }

    fun getTvShowList(callback: LoadTvShowsCallback) {
        IdlingResource.increment()
        mainApiInterface.getTvShowList()
            .enqueue(object : Callback<TvShowApiResponse> {

                override fun onResponse(
                    call: Call<TvShowApiResponse>,
                    response: Response<TvShowApiResponse>
                ) {
                    try {
                        IdlingResource.decrement()
                        if (response.isSuccessful) {
                            val tvShowResponse: List<TvShowEntity> =
                                response.body()?.results ?: emptyList()
                            callback.onAllTvShowsReceived(tvShowResponse)
                        }
                    } catch (e: Exception) {
                        throw Exception(e.message.toString())
                    }
                }

                override fun onFailure(call: Call<TvShowApiResponse>, t: Throwable) {
                    throw Exception(t.message.toString())
                }
            })
    }

    fun getTvShowDetail(id: String?, callback: LoadTvShowDetailCallback) {
        IdlingResource.increment()
        mainApiInterface.getTvShowDetail(id).enqueue(object : Callback<TvShowEntity> {
            override fun onResponse(
                call: Call<TvShowEntity>,
                response: Response<TvShowEntity>
            ) {
                try {
                    IdlingResource.decrement()
                    val tvShowResponse = response.body()
                    if (tvShowResponse != null) {
                        callback.onDetailTvShowReceived(tvShowResponse)
                    }
                } catch (e: Exception) {
                    throw Exception(e.message.toString())
                }
            }

            override fun onFailure(call: Call<TvShowEntity>, t: Throwable) {
                throw Exception(t.message.toString())
            }
        })
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieEntity>)
    }

    interface LoadMovieDetailCallback {
        fun onDetailMovieReceived(movieResponse: MovieEntity)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowEntity>)
    }

    interface LoadTvShowDetailCallback {
        fun onDetailTvShowReceived(tvShowResponse: TvShowEntity)
    }
}


