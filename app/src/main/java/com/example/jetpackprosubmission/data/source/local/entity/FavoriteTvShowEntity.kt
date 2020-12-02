package com.example.jetpackprosubmission.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favoriteTvShowEntity")
data class FavoriteTvShowEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: String = "",
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "posterPath") val posterPath: String? = null,
    @ColumnInfo(name = "overview") val overview: String? = null,
    @ColumnInfo(name = "firstAirDate") val firstAirDate: String? = null,
    @ColumnInfo(name = "lastAirDate") val lastAirDate: String? = null,
    @ColumnInfo(name = "voteAverage") val voteAverage: String? = null,
    @ColumnInfo(name = "numberOfEpisodes") val numberOfEpisodes: String? = null,
    @ColumnInfo(name = "numberOfSeasons") val numberOfSeasons: String? = null,
    @ColumnInfo(name = "genres") val genres: String? = null
) : Parcelable