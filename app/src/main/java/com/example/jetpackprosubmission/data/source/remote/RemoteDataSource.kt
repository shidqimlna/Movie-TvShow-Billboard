package com.example.jetpackprosubmission.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackprosubmission.data.source.remote.response.MovieApiItem
import com.example.jetpackprosubmission.data.source.remote.response.MovieApiResponse
import com.example.jetpackprosubmission.data.source.remote.response.TvShowApiItem
import com.example.jetpackprosubmission.data.source.remote.response.TvShowApiResponse
import com.example.jetpackprosubmission.util.IdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource constructor(private val mainApiInterface: MainApiInterface) {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(mainApiInterface: MainApiInterface): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(mainApiInterface)
            }
    }

    fun getMovieList(): LiveData<ApiResponse<List<MovieApiItem>>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieApiItem>>>()
        mainApiInterface.getMovieList()
            .enqueue(object : Callback<MovieApiResponse> {
                override fun onResponse(
                    call: Call<MovieApiResponse>,
                    response: Response<MovieApiResponse>
                ) {
                    try {
                        IdlingResource.decrement()
                        if (response.isSuccessful) {
                            val movieResponse: List<MovieApiItem> =
                                response.body()?.results ?: emptyList()
                            result.value = ApiResponse.success(movieResponse)
                            Log.i(
                                "REMOTEDATASOUCRE",
                                "REMOTEDATASOUCRE :" + movieResponse.get(0).title
                            )
                        }
                    } catch (e: Exception) {
                        Log.i("REMOTEDATASOUCRE", "REMOTEDATASOUCRE : EXCEPT")
                        throw Exception(e.message.toString())
                    }
                }

                override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                    IdlingResource.decrement()
                    Log.i("REMOTEDATASOUCRE", "REMOTEDATASOUCRE : FAIL")
                    throw Exception(t.message.toString())
                }
            })
        return result
    }

    fun getMovieDetail(id: String?): LiveData<ApiResponse<MovieApiItem>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieApiItem>>()
        mainApiInterface.getMovieDetail(id).enqueue(object : Callback<MovieApiItem> {
            override fun onResponse(
                call: Call<MovieApiItem>,
                response: Response<MovieApiItem>
            ) {
                try {
                    IdlingResource.decrement()
                    if (response.isSuccessful) {
                        val movieResponse = response.body()
                        if (movieResponse != null) result.value = ApiResponse.success(movieResponse)
                    }
                } catch (e: Exception) {
                    throw Exception(e.message.toString())
                }
            }

            override fun onFailure(call: Call<MovieApiItem>, t: Throwable) {
                IdlingResource.decrement()
                throw Exception(t.message.toString())
            }
        })
        return result
    }

    fun getTvShowList(): LiveData<ApiResponse<List<TvShowApiItem>>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowApiItem>>>()
        mainApiInterface.getTvShowList()
            .enqueue(object : Callback<TvShowApiResponse> {
                override fun onResponse(
                    call: Call<TvShowApiResponse>,
                    response: Response<TvShowApiResponse>
                ) {
                    try {
                        IdlingResource.decrement()
                        if (response.isSuccessful) {
                            val tvShowResponse: List<TvShowApiItem> =
                                response.body()?.results ?: emptyList()
                            result.value = ApiResponse.success(tvShowResponse)
                        }
                    } catch (e: Exception) {
                        throw Exception(e.message.toString())
                    }
                }

                override fun onFailure(call: Call<TvShowApiResponse>, t: Throwable) {
                    IdlingResource.decrement()
                    throw Exception(t.message.toString())
                }
            })
        return result
    }

    fun getTvShowDetail(id: String?): LiveData<ApiResponse<TvShowApiItem>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShowApiItem>>()
        mainApiInterface.getTvShowDetail(id).enqueue(object : Callback<TvShowApiItem> {
            override fun onResponse(
                call: Call<TvShowApiItem>,
                response: Response<TvShowApiItem>
            ) {
                try {
                    IdlingResource.decrement()
                    if (response.isSuccessful) {
                        val tvShowResponse = response.body()
                        if (tvShowResponse != null) result.value =
                            ApiResponse.success(tvShowResponse)
                    }
                } catch (e: Exception) {
                    throw Exception(e.message.toString())
                }
            }

            override fun onFailure(call: Call<TvShowApiItem>, t: Throwable) {
                IdlingResource.decrement()
                throw Exception(t.message.toString())
            }
        })
        return result
    }

}


