package com.example.jetpackprosubmission.data.source.remote

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

class RemoteDataSource constructor(private val mainAPI: MainAPI) {

    companion object {
        const val ERROR_RESPONSE = "Response Error"
        const val ERROR_DATA = "Data Error"

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(mainApi: MainAPI): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(mainApi)
            }
    }

    fun getMovieList(): LiveData<ApiResponse<List<MovieApiItem>>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieApiItem>>>()
        mainAPI.create()?.getMovieList()
            ?.enqueue(object : Callback<MovieApiResponse> {
                override fun onResponse(
                    call: Call<MovieApiResponse>,
                    response: Response<MovieApiResponse>
                ) {
                    try {
                        IdlingResource.decrement()
                        if (response.isSuccessful) {
                            val movieResponse: List<MovieApiItem> =
                                response.body()?.results ?: emptyList()
                            if (movieResponse.isNullOrEmpty())
                                result.value = ApiResponse.empty(ERROR_DATA)
                            else
                                result.value = ApiResponse.success(movieResponse)
                        } else result.value = ApiResponse.error(ERROR_RESPONSE)
                    } catch (e: Exception) {
                        result.value = e.message?.let { ApiResponse.error(it) }
                        throw Exception(e.message.toString())
                    }
                }

                override fun onFailure(call: Call<MovieApiResponse>, t: Throwable) {
                    IdlingResource.decrement()
                    result.value = t.message?.let { ApiResponse.error(it) }
                    throw Exception(t.message.toString())
                }
            })
        return result
    }

    fun getMovieDetail(id: String?): LiveData<ApiResponse<MovieApiItem>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieApiItem>>()
        mainAPI.create()?.getMovieDetail(id)?.enqueue(object : Callback<MovieApiItem> {
            override fun onResponse(
                call: Call<MovieApiItem>,
                response: Response<MovieApiItem>
            ) {
                try {
                    IdlingResource.decrement()
                    if (response.isSuccessful) {
                        val movieResponse = response.body()
                        if (movieResponse != null) result.value = ApiResponse.success(movieResponse)
                        else result.value = ApiResponse.empty(ERROR_DATA)
                    } else result.value = ApiResponse.error(ERROR_RESPONSE)
                } catch (e: Exception) {
                    result.value = e.message?.let { ApiResponse.error(it) }
                    throw Exception(e.message.toString())
                }
            }

            override fun onFailure(call: Call<MovieApiItem>, t: Throwable) {
                IdlingResource.decrement()
                result.value = t.message?.let { ApiResponse.error(it) }
                throw Exception(t.message.toString())
            }
        })
        return result
    }

    fun getTvShowList(): LiveData<ApiResponse<List<TvShowApiItem>>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowApiItem>>>()
        mainAPI.create()?.getTvShowList()
            ?.enqueue(object : Callback<TvShowApiResponse> {
                override fun onResponse(
                    call: Call<TvShowApiResponse>,
                    response: Response<TvShowApiResponse>
                ) {
                    try {
                        IdlingResource.decrement()
                        if (response.isSuccessful) {
                            val tvShowResponse: List<TvShowApiItem> =
                                response.body()?.results ?: emptyList()
                            if (tvShowResponse.isNullOrEmpty())
                                result.value = ApiResponse.empty(ERROR_DATA)
                            else
                                result.value = ApiResponse.success(tvShowResponse)
                        } else result.value = ApiResponse.error(ERROR_RESPONSE)
                    } catch (e: Exception) {
                        result.value = e.message?.let { ApiResponse.error(it) }
                        throw Exception(e.message.toString())
                    }
                }

                override fun onFailure(call: Call<TvShowApiResponse>, t: Throwable) {
                    IdlingResource.decrement()
                    result.value = t.message?.let { ApiResponse.error(it) }
                    throw Exception(t.message.toString())
                }
            })
        return result
    }

    fun getTvShowDetail(id: String?): LiveData<ApiResponse<TvShowApiItem>> {
        IdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShowApiItem>>()
        mainAPI.create()?.getTvShowDetail(id)?.enqueue(object : Callback<TvShowApiItem> {
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
                        else result.value = ApiResponse.empty(ERROR_DATA)
                    } else result.value = ApiResponse.error(ERROR_RESPONSE)
                } catch (e: Exception) {
                    result.value = e.message?.let { ApiResponse.error(it) }
                    throw Exception(e.message.toString())
                }
            }

            override fun onFailure(call: Call<TvShowApiItem>, t: Throwable) {
                IdlingResource.decrement()
                result.value = t.message?.let { ApiResponse.error(it) }
                throw Exception(t.message.toString())
            }
        })
        return result
    }

}


