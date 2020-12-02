package com.example.jetpackprosubmission.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favoriteMovieEntity")
data class FavoriteMovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "posterPath") val posterPath: String? = null,
    @ColumnInfo(name = "overview") val overview: String? = null,
    @ColumnInfo(name = "runtime") val runtime: String? = null,
    @ColumnInfo(name = "releaseDate") val releaseDate: String? = null,
    @ColumnInfo(name = "voteAverage") val voteAverage: String? = null,
    @ColumnInfo(name = "genres") val genres: String? = null
) : Parcelable