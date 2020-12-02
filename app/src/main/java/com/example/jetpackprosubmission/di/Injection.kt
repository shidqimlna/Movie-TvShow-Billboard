package com.example.jetpackprosubmission.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackprosubmission.data.MainRepository
import com.example.jetpackprosubmission.data.source.local.LocalDataSource
import com.example.jetpackprosubmission.data.source.local.room.MainDatabase
import com.example.jetpackprosubmission.data.source.remote.MainAPI
import com.example.jetpackprosubmission.data.source.remote.RemoteDataSource
import com.example.jetpackprosubmission.util.AppExecutors
import com.example.jetpackprosubmission.viewmodel.ViewModelFactory

object Injection {
    private fun provideMainRepository(context: Context): MainRepository {
        val remoteDataSource = RemoteDataSource.getInstance(MainAPI())
        val localDataSource =
            LocalDataSource.getInstance(MainDatabase.getInstance(context).mainDao())
        val appExecutors = AppExecutors()

        return MainRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideMainRepository(context))
    }
}
