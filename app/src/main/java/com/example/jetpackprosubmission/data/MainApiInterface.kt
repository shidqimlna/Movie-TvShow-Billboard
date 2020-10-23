package com.example.jetpackprosubmission.data

import com.example.jetpackprosubmission.model.MovieApiResponse
import com.example.jetpackprosubmission.model.MovieItem
import com.example.jetpackprosubmission.model.TvShowApiResponse
import com.example.jetpackprosubmission.model.TvShowItem
import com.example.jetpackprosubmission.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApiInterface {

    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}")
    fun getMovieList(
    ): Call<MovieApiResponse>

    @GET("/movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: String?
    ): Call<MovieItem>

    @GET("movie/top_rated?api_key=${BuildConfig.API_KEY}")
    fun getTvShowList(
    ): Call<TvShowApiResponse>

    @GET("/movie/{tv_id}?api_key=${BuildConfig.API_KEY}")
    fun getTvShowDetail(
        @Path("tv_id") tv_id: String?
    ): Call<TvShowItem>
}