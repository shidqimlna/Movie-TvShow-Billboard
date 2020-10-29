package com.example.jetpackprosubmission.data.source.remote

import com.example.jetpackprosubmission.BuildConfig
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity
import com.example.jetpackprosubmission.data.source.remote.response.MovieApiResponse
import com.example.jetpackprosubmission.data.source.remote.response.TvShowApiResponse
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