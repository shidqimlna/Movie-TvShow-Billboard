package com.example.jetpackprosubmission.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackprosubmission.data.model.MovieApiResponse
import com.example.jetpackprosubmission.data.model.MovieEntity
import com.example.jetpackprosubmission.data.model.TvShowApiResponse
import com.example.jetpackprosubmission.data.model.TvShowEntity
import com.example.jetpackprosubmission.data.network.MainAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var mainAPI = MainAPI()
    private var listMovies = MutableLiveData<ArrayList<MovieEntity>>()
    private var listTvShows = MutableLiveData<ArrayList<TvShowEntity>>()
    private var movieItem = MovieEntity()
    private var tvShowItem = TvShowEntity()
    private var statusMessage = ""

    fun setMovieList() {
        statusMessage = ""

        mainAPI.mainApiInterface?.getMovieList()
            ?.enqueue(object : Callback<MovieApiResponse> {

                override fun onResponse(
                    call: Call<MovieApiResponse>,
                    response: Response<MovieApiResponse>
                ) {
                    try {
                        val movieApiResponse = response.body()
                        if (movieApiResponse != null) {
                            val movieEntity: ArrayList<MovieEntity> = movieApiResponse.results
                            listMovies.postValue(movieEntity)
                            statusMessage = "success"
                        }
                    } catch (e: Exception) {
                        statusMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                    statusMessage = t.message.toString()
                }
            })
    }

    fun setTvShowList() {
        statusMessage = ""

        mainAPI.mainApiInterface?.getTvShowList()
            ?.enqueue(object : Callback<TvShowApiResponse> {

                override fun onResponse(
                    call: Call<TvShowApiResponse>,
                    response: Response<TvShowApiResponse>
                ) {
                    try {
                        val tvShowApiResponse = response.body()
                        if (tvShowApiResponse != null) {
                            val tvShowEntity: ArrayList<TvShowEntity> = tvShowApiResponse.results
                            listTvShows.postValue(tvShowEntity)
                            statusMessage = "success"
                        }
                    } catch (e: Exception) {
                        statusMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<TvShowApiResponse>, t: Throwable) {
                    statusMessage = t.message.toString()
                }
            })
    }

    fun setMovieDetail(id: String?) {
        statusMessage = ""

        mainAPI.mainApiInterface?.getMovieDetail(id)
            ?.enqueue(object : Callback<MovieEntity> {
                override fun onResponse(
                    call: Call<MovieEntity>,
                    response: Response<MovieEntity>
                ) {
                    try {
                        val movieResponse = response.body()
                        if (movieResponse != null) {
                            movieItem = movieResponse
                            statusMessage = "success"
                        }
                    } catch (e: Exception) {
                        statusMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<MovieEntity>, t: Throwable) {
                    statusMessage = t.message.toString()
                }
            })
    }

    fun setTvShowDetail(id: String?) {
        statusMessage = ""

        mainAPI.mainApiInterface?.getTvShowDetail(id)
            ?.enqueue(object : Callback<TvShowEntity> {
                override fun onResponse(
                    call: Call<TvShowEntity>,
                    response: Response<TvShowEntity>
                ) {
                    try {
                        val tvShowResponse = response.body()
                        if (tvShowResponse != null) {
                            tvShowItem = tvShowResponse
                            statusMessage = "success"
                        }
                    } catch (e: Exception) {
                        statusMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<TvShowEntity>, t: Throwable) {
                    statusMessage = t.message.toString()
                }
            })
    }

    fun getMovieList(): LiveData<ArrayList<MovieEntity>> {
        return listMovies
    }

    fun getTvShowList(): LiveData<ArrayList<TvShowEntity>> {
        return listTvShows
    }

    fun getMovieDetail(): MovieEntity {
        return movieItem
    }

    fun getTvShowDetail(): TvShowEntity {
        return tvShowItem
    }

    fun getErrorMessage(): String {
        return statusMessage
    }
}