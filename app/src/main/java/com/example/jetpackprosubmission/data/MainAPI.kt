package com.example.jetpackprosubmission.data

import com.example.jetpackprosubmission.util.ConstantValue.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainAPI {
    private var retrofit: Retrofit? = null
    val mainApiInterface: MainApiInterface?
        get() {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit?.create(MainApiInterface::class.java)
        }
}