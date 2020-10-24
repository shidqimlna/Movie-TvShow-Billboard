package com.example.jetpackprosubmission.data.network

import com.example.jetpackprosubmission.BuildConfig
import com.example.jetpackprosubmission.data.model.MovieApiResponse
import com.example.jetpackprosubmission.data.model.MovieEntity
import com.example.jetpackprosubmission.data.model.TvShowApiResponse
import com.example.jetpackprosubmission.data.model.TvShowEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApiInterface {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun getMovieList(
    ): Call<MovieApiResponse>

    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
    fun getMovieDetail(
        @Path("movie_id") movie_id: String?
    ): Call<MovieEntity>

    @GET("tv/popular?api_key=${BuildConfig.API_KEY}")
    fun getTvShowList(
    ): Call<TvShowApiResponse>

    @GET("tv/{tv_id}?api_key=${BuildConfig.API_KEY}")
    fun getTvShowDetail(
        @Path("tv_id") tv_id: String?
    ): Call<TvShowEntity>
}