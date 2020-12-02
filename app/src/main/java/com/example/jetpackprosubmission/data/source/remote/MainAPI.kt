package com.example.jetpackprosubmission.data.source.remote

import com.example.jetpackprosubmission.util.ConstantValue.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainAPI {
    private var retrofit: Retrofit? = null
    fun create(): MainApiInterface? {
        if (retrofit == null) {
            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit?.create(MainApiInterface::class.java)
    }
}