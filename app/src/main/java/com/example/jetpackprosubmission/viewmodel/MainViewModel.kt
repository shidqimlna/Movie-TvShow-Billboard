package com.example.jetpackprosubmission.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackprosubmission.data.MainAPI
import com.example.jetpackprosubmission.model.MovieApiResponse
import com.example.jetpackprosubmission.model.MovieItem
import com.example.jetpackprosubmission.model.TvShowApiResponse
import com.example.jetpackprosubmission.model.TvShowItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var mainAPI = MainAPI()
    private var listMovies = MutableLiveData<ArrayList<MovieItem>>()
    private var listTvShows = MutableLiveData<ArrayList<TvShowItem>>()
    private var movieItem = MovieItem()
    private var tvShowItem = TvShowItem()
    private var errorMessage = ""

    fun setMovieList() {
        errorMessage = ""

        mainAPI.mainApiInterface?.getMovieList()
            ?.enqueue(object : Callback<MovieApiResponse> {

                override fun onResponse(
                    call: Call<MovieApiResponse>,
                    response: Response<MovieApiResponse>
                ) {
                    try {
                        val movieApiResponse = response.body()
                        if (movieApiResponse != null) {
                            val movieItem: ArrayList<MovieItem> = movieApiResponse.results
                            listMovies.postValue(movieItem)
                            errorMessage = "success"
                        }
                    } catch (e: Exception) {
                        errorMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                    errorMessage = t.message.toString()
                }
            })
    }

    fun setTvShowList() {
        errorMessage = ""

        mainAPI.mainApiInterface?.getTvShowList()
            ?.enqueue(object : Callback<TvShowApiResponse> {

                override fun onResponse(
                    call: Call<TvShowApiResponse>,
                    response: Response<TvShowApiResponse>
                ) {
                    try {
                        val tvShowApiResponse = response.body()
                        if (tvShowApiResponse != null) {
                            val tvShowItem: ArrayList<TvShowItem> = tvShowApiResponse.results
                            listTvShows.postValue(tvShowItem)
                            errorMessage = "success"
                        }
                    } catch (e: Exception) {
                        errorMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<TvShowApiResponse>, t: Throwable) {
                    errorMessage = t.message.toString()
                }
            })
    }

    fun setMovieDetail(id: String?) {
        errorMessage = ""

        mainAPI.mainApiInterface?.getMovieDetail(id)
            ?.enqueue(object : Callback<MovieItem> {
                override fun onResponse(
                    call: Call<MovieItem>,
                    response: Response<MovieItem>
                ) {
                    try {
                        val movieResponse = response.body()
                        if (movieResponse != null) {
                            movieItem = movieResponse
                            errorMessage = "success"
                        }
                    } catch (e: Exception) {
                        errorMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<MovieItem>, t: Throwable) {
                    errorMessage = t.message.toString()
                }
            })
    }

    fun setTvShowDetail(id: String?) {
        errorMessage = ""

        mainAPI.mainApiInterface?.getTvShowDetail(id)
            ?.enqueue(object : Callback<TvShowItem> {
                override fun onResponse(
                    call: Call<TvShowItem>,
                    response: Response<TvShowItem>
                ) {
                    try {
                        val tvShowResponse = response.body()
                        if (tvShowResponse != null) {
                            tvShowItem = tvShowResponse
                            errorMessage = "success"
                        }
                    } catch (e: Exception) {
                        errorMessage = e.message.toString()
                    }
                }

                override fun onFailure(call: Call<TvShowItem>, t: Throwable) {
                    errorMessage = t.message.toString()
                }
            })
    }

    fun getMovieList(): LiveData<ArrayList<MovieItem>> {
        return listMovies
    }

    fun getTvShowList(): LiveData<ArrayList<TvShowItem>> {
        return listTvShows
    }

    fun getMovieDetail(): MovieItem {
        return movieItem
    }

    fun getTvShowDetail(): TvShowItem {
        return tvShowItem
    }

    fun getErrorMessage(): String {
        return errorMessage
    }
}