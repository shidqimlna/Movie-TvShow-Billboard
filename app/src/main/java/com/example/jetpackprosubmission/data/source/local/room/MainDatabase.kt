package com.example.jetpackprosubmission.data.source.local.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteMovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.FavoriteTvShowEntity
import com.example.jetpackprosubmission.data.source.local.entity.MovieEntity
import com.example.jetpackprosubmission.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, FavoriteMovieEntity::class, FavoriteTvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun favoriteDao(): MainDao

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            if (INSTANCE == null) {
                synchronized(MainDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            MainDatabase::class.java, "mainDatabase"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE as MainDatabase
        }
    }
}