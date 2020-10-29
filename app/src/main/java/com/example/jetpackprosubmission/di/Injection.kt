package com.example.jetpackprosubmission.di

import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.remote.MainAPI
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource
import com.example.jetpackprosubmission.viewmodel.ViewModelFactory

object Injection {
    private fun provideRepository(): MainRepository {
        return MainRepository.getInstance(RemoteDataSource.getInstance(MainAPI().create()))
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository())
    }
}
